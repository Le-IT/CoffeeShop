package whz.informatik.coffeeshop.shop.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import whz.informatik.coffeeshop.shop.domain.Item;

/**
 * Interface defining methods to interact with database using SPeL
 */
public interface ItemRepository extends JpaRepository<Item, Long> {
}
