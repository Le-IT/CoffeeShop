package whz.informatik.coffeeshop.shop.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import whz.informatik.coffeeshop.shop.domain.Warranty;

/**
 * Interface for interaction with database
 */
public interface WarrantyRepository extends JpaRepository<Warranty, Long> {
}
