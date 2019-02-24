package whz.informatik.coffeeshop.shop.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import whz.informatik.coffeeshop.shop.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
