package whz.informatik.coffeeshop.shop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import whz.informatik.coffeeshop.common.DTOUtils;
import whz.informatik.coffeeshop.shop.domain.Customer;
import whz.informatik.coffeeshop.shop.domain.Item;
import whz.informatik.coffeeshop.shop.domain.Product;
import whz.informatik.coffeeshop.shop.domain.ShoppingCart;
import whz.informatik.coffeeshop.shop.domain.repository.ItemRepository;
import whz.informatik.coffeeshop.shop.domain.repository.ShoppingCartRepository;
import whz.informatik.coffeeshop.shop.service.dto.ShoppingCartDTO;

import java.time.Instant;
import java.util.*;

/**
 * Implementation of ShoppingCartService
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final Logger log = LoggerFactory.getLogger(ShoppingCartServiceImpl.class);

    private ShoppingCartRepository shoppingCartRepository;
    private ItemRepository itemRepository;
    private CustomerService customerService;
    private ProductService productService;


    @Autowired
    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository,
                                   ItemRepository itemRepository,
                                   CustomerService customerService,
                                   ProductService productService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.itemRepository = itemRepository;
        this.customerService = customerService;
        this.productService = productService;
    }


    @Override
    public Optional<ShoppingCartDTO> getShoppingCartDTOById(long shoppingCartId) {
        Optional<ShoppingCart> optionalShoppingCart = getShoppingCartById(shoppingCartId);
        if(optionalShoppingCart.isPresent()) {
            ShoppingCartDTO shoppingCartDTO = DTOUtils.createDTO(optionalShoppingCart.get());
            return Optional.of(shoppingCartDTO);
        } return Optional.empty();
    }

    @Override
    public List<ShoppingCartDTO> getShoppingCartsDTOByCustomer(Customer customer) {
        List<ShoppingCart> targetListOrigin = getShoppingCartsByCustomer(customer);
        List<ShoppingCartDTO> targetList = new ArrayList<>();
        targetListOrigin.forEach(cart -> targetList.add(DTOUtils.createDTO(cart)));
        return targetList;
    }

    @Override
    public List<ShoppingCartDTO> getShoppingCartsDTOByCustomerId(long customerId) {
        Optional<Customer> optionalCustomer = customerService.getById(customerId);
        if(optionalCustomer.isPresent())
            return getShoppingCartsDTOByCustomer(optionalCustomer.get());
        return new ArrayList<>();
    }


    @Override
    public Optional<ShoppingCart> getShoppingCartById(long shoppingCartId) {
        return shoppingCartRepository.findById(shoppingCartId);
    }

    @Override
    @Transactional
    public ShoppingCart createShoppingCartForCustomer(Customer customer) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setup(Date.from(Instant.now()),customer);
        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    @Transactional
    public ShoppingCart createShoppingCartForCustomerWithId(long customerId) {
        Optional<Customer> optionalCustomer = customerService.getById(customerId);

        if(optionalCustomer.isPresent())
            return createShoppingCartForCustomer(optionalCustomer.get());
        return null;
    }

    @Override
    public List<ShoppingCart> getShoppingCartsByCustomer(Customer customer) {
        return shoppingCartRepository.findAllByCustomer(customer);
    }

    @Override
    public List<ShoppingCart> getShoppingCartsByCustomerId(long customerId) {
        return shoppingCartRepository.findAllByCustomerId(customerId);
    }

    @Override
    public void deleteShoppingCart(ShoppingCart shoppingCart) {
        if(shoppingCartRepository.existsById(shoppingCart.getId()))
            shoppingCartRepository.deleteById(shoppingCart.getId());
    }

    @Override
    @Transactional
    public ShoppingCart update(ShoppingCart shoppingCart) {
        if(shoppingCartRepository.existsById(shoppingCart.getId()))
            return shoppingCartRepository.save(shoppingCart);
        return null;
    }

    @Override
    @Transactional
    public void createAndAddItemToCart(ShoppingCart shoppingCart, long productId, int amount) {
        for (Item it : shoppingCart.getItems()) {
            if(it.getProduct().getId() == productId) {
                it.setQuantity(it.getQuantity() + amount);
                itemRepository.save(it);
                return;
            }
        }
        Item item = createItem(productId,amount);
        shoppingCart.addItem(item);
        shoppingCartRepository.save(shoppingCart);
    }

    @Override
    @Transactional
    public void clearCart(long shoppingCartId) {
        Optional<ShoppingCart> shoppingCartOptional = shoppingCartRepository.findById(shoppingCartId);

        if(shoppingCartOptional.isPresent())
            shoppingCartOptional.get().removeAllItems();
    }

    @Override
    @Transactional
    public void addAllItemsToCart(long shoppingCartId, Collection<Item> items) {
        Optional<ShoppingCart> shoppingCartOptional = shoppingCartRepository.findById(shoppingCartId);

        if(shoppingCartOptional.isPresent())
            addAllItemsToCart(shoppingCartOptional.get(),items);
    }

    @Override
    @Transactional
    public void addAllItemsToCart(ShoppingCart shoppingCart, Collection<Item> items) {
        shoppingCart.addAllItems(items);
        shoppingCartRepository.save(shoppingCart);
    }

    @Override
    @Transactional
    public void deleteItem(Item item) {
        if(itemRepository.existsById(item.getId()))
            itemRepository.delete(item);
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public Item createItem(long productId, int amount) {
        Product product = productService.getById(productId).get();
        Item item = new Item();
        item.setup(amount,product);
        item = itemRepository.save(item);
        log.debug("Created item itemId={}", item.getId());
        return item;
    }
}
