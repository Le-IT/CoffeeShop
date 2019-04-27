package whz.informatik.coffeeshop.shop.service;

import whz.informatik.coffeeshop.shop.domain.Customer;
import whz.informatik.coffeeshop.shop.domain.Item;
import whz.informatik.coffeeshop.shop.domain.ShoppingOrder;
import whz.informatik.coffeeshop.shop.service.dto.ShoppingOrderDTO;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Service-Interface to provide functionality to controllers to
 * handle ShoppingOrder-Persistence
 */
public interface ShoppingOrderService {

    /**
     * find ShoppingOrder with given id
     * @param shoppingOrderId
     * @return optional_shoppingOrder
     */
    Optional<ShoppingOrder> getShoppingOrderById(long shoppingOrderId);

    /**
     * create ShoppingOrder for Customer
     * overtake items from the shoppingCart of the Customer
     * and discard the shoppingCart
     * @param customer
     * @return shoppingOrder
     */
    ShoppingOrder createShoppingOrderForCustomer(Customer customer);

    /**
     * find customer with given id
     * create ShoppingOrder for Customer
     * overtake items from the shoppingCart of the Customer
     * and discard the shoppingCart
     * @param customerId
     * @return
     */
    ShoppingOrder createShoppingOrderForCustomerWithId(long customerId);

    /**
     * find all shoppingOrders from the specified customer
     * @param customer
     * @return shoppingOrders
     */
    List<ShoppingOrder> getShoppingOrdersByCustomer(Customer customer);

    /**
     * find all shoppingOrders from the specified customer
     * @param customerId
     * @return shoppingOrders
     */
    List<ShoppingOrder> getShoppingOrdersByCustomerId(long customerId);

    /**
     * find shoppingOrder with given id
     * and return as DTO for network usage
     * @param shoppingCartId
     * @return optional_shoppingOrderDTO
     */
    Optional<ShoppingOrderDTO> getShoppingOrderDTOById(long shoppingCartId);

    /**
     * find all shoppingOrders from the specified customer
     * and create DTOs for network usage
     * @param customer
     * @return shoppingOrderDTOs
     */
    List<ShoppingOrderDTO> getShoppingOrdersDTOByCustomer(Customer customer);

    /**
     * find all shoppingOrders from the specified customer
     * @param customerId
     * @return shoppingOrderDTOs
     */
    List<ShoppingOrderDTO> getShoppingOrdersDTOByCustomerId(long customerId);

    /**
     * persist shoppingOrder in database
     * if shoppingOrder is not persisted, yet
     * the id will be set after
     * @param shoppingOrder
     * @return shoppingOrder for further usage
     */
    ShoppingOrder update(ShoppingOrder shoppingOrder);

    /**
     * add given collection of items to the specified
     * shoppingOrder
     * @param shoppingOrderId
     * @param items
     */
    void addAllItemsToOrder(long shoppingOrderId, Collection<Item> items);
}
