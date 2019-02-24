package whz.informatik.coffeeshop.shop.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import whz.informatik.coffeeshop.shop.domain.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
