package whz.informatik.coffeeshop.shop.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import whz.informatik.coffeeshop.shop.domain.ShoppingOrder;

/**
 * Interface for interaction with database
 */
public interface ShoppingOrderRepository extends JpaRepository<ShoppingOrder, Long> {
}
