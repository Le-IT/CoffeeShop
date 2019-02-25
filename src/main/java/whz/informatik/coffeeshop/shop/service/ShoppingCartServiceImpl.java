package whz.informatik.coffeeshop.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whz.informatik.coffeeshop.shop.domain.Customer;
import whz.informatik.coffeeshop.shop.domain.Item;
import whz.informatik.coffeeshop.shop.domain.Product;
import whz.informatik.coffeeshop.shop.domain.ShoppingCart;
import whz.informatik.coffeeshop.shop.domain.repository.ItemRepository;
import whz.informatik.coffeeshop.shop.domain.repository.ShoppingCartRepository;

import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private ShoppingCartRepository shoppingCartRepository;
    private ItemRepository itemRepository;
    private CustomerService customerService;


    @Autowired
    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository,
                                   ItemRepository itemRepository,
                                   CustomerService customerService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.itemRepository = itemRepository;
        this.customerService = customerService;
    }

    @Override
    public Optional<ShoppingCart> getShoppingCartById(long shoppingCartId) {
        return shoppingCartRepository.findById(shoppingCartId);
    }

    @Override
    public ShoppingCart createShoppingCartForCustomer(Customer customer) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setup(Date.from(Instant.now()),customer);
        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
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
    public void update(ShoppingCart shoppingCart) {
        shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public void addItemToCart(long shoppingCartId, Item item) {
        Optional<ShoppingCart> shoppingCartOptional = shoppingCartRepository.findById(shoppingCartId);

        if(shoppingCartOptional.isPresent())
            addItemToCart(shoppingCartOptional.get(),item);
    }

    @Override
    public void addItemToCart(ShoppingCart shoppingCart, Item item) {
        shoppingCart.addItem(item);
        update(shoppingCart);
    }

    @Override
    public void addAllItemsToCart(long shoppingCartId, Collection<Item> items) {
        Optional<ShoppingCart> shoppingCartOptional = shoppingCartRepository.findById(shoppingCartId);

        if(shoppingCartOptional.isPresent())
            addAllItemsToCart(shoppingCartOptional.get(),items);
    }

    @Override
    public void addAllItemsToCart(ShoppingCart shoppingCart, Collection<Item> items) {
        shoppingCart.addAllItems(items);
        update(shoppingCart);
    }

    @Override
    public void deleteProductFromCart(ShoppingCart shoppingCart, Product product) {
        //ToDo
    }

    @Override
    public void deleteItem(Item item) {
        if(itemRepository.existsById(item.getId()))
            itemRepository.delete(item);
    }
}
