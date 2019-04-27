package whz.informatik.coffeeshop.shop.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import whz.informatik.coffeeshop.shop.domain.ProductType;

/**
 * Interface for interaction with database
 */
public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {
}
