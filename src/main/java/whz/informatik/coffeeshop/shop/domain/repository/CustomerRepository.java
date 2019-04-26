package whz.informatik.coffeeshop.shop.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import whz.informatik.coffeeshop.shop.domain.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository <Customer, Long> {
    Optional<Customer> findByLoginName(String loginName);
    boolean existsByLoginName(String loginName);
    List<Customer> findAllByOrderByLoginName();
}
