package whz.informatik.coffeeshop.shop.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import whz.informatik.coffeeshop.shop.domain.ProductType;

public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {
}
