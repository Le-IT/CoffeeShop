package whz.informatik.coffeeshop.shop.service;

import whz.informatik.coffeeshop.shop.domain.Customer;
import whz.informatik.coffeeshop.shop.domain.Item;
import whz.informatik.coffeeshop.shop.domain.Product;
import whz.informatik.coffeeshop.shop.domain.ShoppingCart;
import whz.informatik.coffeeshop.shop.service.dto.ShoppingCartDTO;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ShoppingCartService {
    Optional<ShoppingCart> getShoppingCartById(long shoppingCartId);

    ShoppingCart createShoppingCartForCustomer(Customer customer);
    ShoppingCart createShoppingCartForCustomerWithId(long customerId);
    List<ShoppingCart> getShoppingCartsByCustomer(Customer customer);
    List<ShoppingCart> getShoppingCartsByCustomerId(long customerId);

    Optional<ShoppingCartDTO> getShoppingCartDTOById(long shoppingCartId);
    List<ShoppingCartDTO> getShoppingCartsDTOByCustomer(Customer customer);
    List<ShoppingCartDTO> getShoppingCartsDTOByCustomerId(long customerId);

    ShoppingCart update(ShoppingCart shoppingCart);

    void addItemToCart(long shoppingCartId, Item item);
    void addItemToCart(ShoppingCart shoppingCart, Item item);
    void addAllItemsToCart(long shoppingCartId, Collection<Item> items);
    void addAllItemsToCart(ShoppingCart shoppingCart, Collection<Item> items);
    void deleteProductFromCart(ShoppingCart shppingCard, Product product);

    void deleteItem(Item item);
    Item createItem(long productId, int amount);

}
