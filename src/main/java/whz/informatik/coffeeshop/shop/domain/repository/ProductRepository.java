package whz.informatik.coffeeshop.shop.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import whz.informatik.coffeeshop.shop.domain.Product;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);
}
