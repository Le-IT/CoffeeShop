package whz.informatik.coffeeshop.shop.service;

import whz.informatik.coffeeshop.shop.domain.Customer;
import whz.informatik.coffeeshop.shop.domain.Item;
import whz.informatik.coffeeshop.shop.domain.ShoppingCart;
import whz.informatik.coffeeshop.shop.service.dto.ShoppingCartDTO;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Service-Interface to provide functionality to controllers to
 * handle ShoppingCart-Persistence
 */
public interface ShoppingCartService {
    /**
     * find shoppingCart with given Id
     * @param shoppingCartId
     * @return optional_shoppingCard
     */
    Optional<ShoppingCart> getShoppingCartById(long shoppingCartId);

    /**
     * create new ShoppingCart for given customer
     * and persist it in the database
     * @param customer
     * @return shoppingCart
     */
    ShoppingCart createShoppingCartForCustomer(Customer customer);

    /**
     * create new ShoppingCart for customer with given id
     * and persist it in the database
     * @param customerId
     * @return shoppingCart
     */
    ShoppingCart createShoppingCartForCustomerWithId(long customerId);

    /**
     * find all shoppingCarts of given customer
     * @param customer
     * @return shoppingCarts
     */
    List<ShoppingCart> getShoppingCartsByCustomer(Customer customer);

    /**
     * find all shoppingCarts of customer with given id
     * @param customerId
     * @return shoppingCarts
     */
    List<ShoppingCart> getShoppingCartsByCustomerId(long customerId);

    /**
     * delete given shoppingCart from database
     * @param shoppingCart
     */
    void deleteShoppingCart(ShoppingCart shoppingCart);

    /**
     * find shoppingCart with given id
     * and create DTO for network usage
     * @param shoppingCartId
     * @return optional_shoppingCartDTO
     */
    Optional<ShoppingCartDTO> getShoppingCartDTOById(long shoppingCartId);

    /**
     * get all shoppingcarts of the given customer
     * and create DTOs for network usage
     * @param customer
     * @return shoppingCartDTOs
     */
    List<ShoppingCartDTO> getShoppingCartsDTOByCustomer(Customer customer);

    /**
     * get all shoppingCarts of the customer with
     * the given id and create DTOs for network
     * usage
     * @param customerId
     * @return shoppingCartDTOs
     */
    List<ShoppingCartDTO> getShoppingCartsDTOByCustomerId(long customerId);

    /**
     * persist the given shoppingCart in the database
     * @param shoppingCart
     * @return
     */
    ShoppingCart update(ShoppingCart shoppingCart);

    /**
     * add an item to the given shoppingCart
     * if the product of the given item
     * is already contained, the amount will
     * be added instead of creating a new item
     * @param shoppingCart
     * @param productId
     * @param amount
     */
    void createAndAddItemToCart(ShoppingCart shoppingCart, long productId, int amount);

    /**
     * add given collection of items to the
     * shoppingCart with the given id
     * @param shoppingCartId
     * @param items
     */
    void addAllItemsToCart(long shoppingCartId, Collection<Item> items);

    /**
     * add given collection of items to the shoppingCart
     * @param shoppingCart
     * @param items
     */
    void addAllItemsToCart(ShoppingCart shoppingCart, Collection<Item> items);

    /**
     * clear shoppingCart (remove all items)
     * @param shoppingCartId
     */
    void clearCart(long shoppingCartId);

    /**
     * delete given Item from the database
     * @param item
     */
    void deleteItem(Item item);

    /**
     * create new item and save it in the database
     * @param productId
     * @param amount
     * @return item
     */
    Item createItem(long productId, int amount);
}
