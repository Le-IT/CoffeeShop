package whz.informatik.coffeeshop.shop.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import whz.informatik.coffeeshop.shop.domain.Address;

/**
 * Interface for interaction with database
 */
public interface AddressRepository extends JpaRepository<Address, Long> {
}
