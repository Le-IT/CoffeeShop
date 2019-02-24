package whz.informatik.coffeeshop.shop.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import whz.informatik.coffeeshop.shop.domain.ShoppingOrder;

public interface ShoppingOrderRepository extends JpaRepository<ShoppingOrder, Long> {
}
