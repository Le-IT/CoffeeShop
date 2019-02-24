package whz.informatik.coffeeshop.shop.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import whz.informatik.coffeeshop.shop.domain.Warranty;

public interface WarrantyRepository extends JpaRepository<Warranty, Long> {
}
