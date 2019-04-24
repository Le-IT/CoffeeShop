package whz.informatik.coffeeshop.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whz.informatik.coffeeshop.common.DTOUtils;
import whz.informatik.coffeeshop.shop.domain.*;
import whz.informatik.coffeeshop.shop.domain.repository.ItemRepository;
import whz.informatik.coffeeshop.shop.domain.repository.ShoppingCartRepository;
import whz.informatik.coffeeshop.shop.domain.repository.ShoppingOrderRepository;
import whz.informatik.coffeeshop.shop.service.dto.ShoppingCartDTO;
import whz.informatik.coffeeshop.shop.service.dto.ShoppingOrderDTO;

import java.time.Instant;
import java.util.*;

@Service
public class ShoppingOrderServiceImpl implements ShoppingOrderService {

    private ShoppingOrderRepository shoppingOrderRepository;
    private ShoppingCartService shoppingCartService;
    private CustomerService customerService;



    @Autowired
    public ShoppingOrderServiceImpl(ShoppingOrderRepository shoppingOrderRepository,
                                    ShoppingCartService shoppingCartService,
                                    CustomerService customerService,
                                    ProductService productService) {
        this.shoppingOrderRepository = shoppingOrderRepository;
        this.shoppingCartService = shoppingCartService;
        this.customerService = customerService;

    }


    @Override
    public Optional<ShoppingOrder> getShoppingOrderById(long shoppingOrderId) {
        return shoppingOrderRepository.findById(shoppingOrderId);
    }

    @Override
    public ShoppingOrder createShoppingOrderForCustomer(Customer customer) {
        ShoppingOrder shoppingOrder = new ShoppingOrder();
        Date date = Date.from(Instant.now());
        shoppingOrder.setup(date);
        shoppingOrder.addItems(shoppingCartService.getShoppingCartsByCustomerId(customer.getId()).get(0).getItems());
        customer.addOrder(shoppingOrder);
        return shoppingOrderRepository.save(shoppingOrder);
    }

    @Override
    public ShoppingOrder createShoppingOrderForCustomerWithId(long customerId) {
        ShoppingOrder shoppingOrder = new ShoppingOrder();
        Date date = Date.from(Instant.now());
        shoppingOrder.setup(date);
        shoppingOrder.addItems(shoppingCartService.getShoppingCartsByCustomerId(customerId).get(0).getItems());
        return  shoppingOrderRepository.save(shoppingOrder);
    }

    @Override
    public List<ShoppingOrder> getShoppingOrdersByCustomer(Customer customer) {
        return customer.getOrders();
    }

    @Override
    public List<ShoppingOrder> getShoppingOrdersByCustomerId(long customerId) {
        return customerService.getById(customerId).get().getOrders();
    }

    @Override
    public Optional<ShoppingOrderDTO> getShoppingOrderDTOById(long shoppingOrderId) {
        Optional<ShoppingOrder> optionalShoppingOrder = getShoppingOrderById(shoppingOrderId);
        if(optionalShoppingOrder.isPresent()) {
            ShoppingOrderDTO shoppingOrderDTO = DTOUtils.createDTO(optionalShoppingOrder.get());
            return Optional.of(shoppingOrderDTO);
        } return Optional.empty();
    }

    @Override
    public List<ShoppingOrderDTO> getShoppingOrdersDTOByCustomer(Customer customer) {
        List<ShoppingOrder> targetListOrigin = getShoppingOrdersByCustomer(customer);
        List<ShoppingOrderDTO> targetList = new ArrayList<>();
        targetListOrigin.forEach(order -> targetList.add(DTOUtils.createDTO(order)));
        return targetList;
    }

    @Override
    public List<ShoppingOrderDTO> getShoppingOrdersDTOByCustomerId(long customerId) {
        Optional<Customer> optionalCustomer = customerService.getById(customerId);
        if(optionalCustomer.isPresent())
            return getShoppingOrdersDTOByCustomer(optionalCustomer.get());
        return new ArrayList<>();
    }

    @Override
    public void addAllItemsToOrder(long shoppingOrderId, Collection<Item> items) {
        if(shoppingOrderRepository.findById(shoppingOrderId).isPresent()) {
            ShoppingOrder shoppingOrder = shoppingOrderRepository.findById(shoppingOrderId).get();
            shoppingOrder.addItems(items);
        }
    }

    @Override
    public ShoppingOrder update(ShoppingOrder shoppingOrder) {
        if (shoppingOrderRepository.existsById(shoppingOrder.getId()))
            return shoppingOrderRepository.save(shoppingOrder);
        return null;
    }
}
