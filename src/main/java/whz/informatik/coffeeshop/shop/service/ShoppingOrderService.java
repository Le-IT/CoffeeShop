package whz.informatik.coffeeshop.shop.service;

import whz.informatik.coffeeshop.shop.domain.*;
import whz.informatik.coffeeshop.shop.service.dto.ShoppingCartDTO;
import whz.informatik.coffeeshop.shop.service.dto.ShoppingOrderDTO;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ShoppingOrderService {
    Optional<ShoppingOrder> getShoppingOrderById(long shoppingOrderId);

    ShoppingOrder createShoppingOrderForCustomer(Customer customer);
    ShoppingOrder createShoppingOrderForCustomerWithId(long customerId);
    List<ShoppingOrder> getShoppingOrdersByCustomer(Customer customer);
    List<ShoppingOrder> getShoppingOrdersByCustomerId(long customerId);

    Optional<ShoppingOrderDTO> getShoppingOrderDTOById(long shoppingCartId);
    List<ShoppingOrderDTO> getShoppingOrdersDTOByCustomer(Customer customer);
    List<ShoppingOrderDTO> getShoppingOrdersDTOByCustomerId(long customerId);
    ShoppingOrder update(ShoppingOrder shoppingOrder);


    void addAllItemsToOrder(long shoppingOrderId, Collection<Item> items);


}
