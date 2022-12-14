package whz.informatik.coffeeshop.shop.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import whz.informatik.coffeeshop.shop.domain.Customer;
import whz.informatik.coffeeshop.shop.domain.ShoppingCart;

import java.util.List;

/**
 * Interface defining methods to interact with database using SPeL
 */
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    List<ShoppingCart> findAllByCustomer(Customer customer);
    List<ShoppingCart> findAllByCustomerId(long customerId);
}
