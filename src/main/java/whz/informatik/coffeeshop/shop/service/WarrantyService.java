package whz.informatik.coffeeshop.shop.service;



import whz.informatik.coffeeshop.shop.domain.Customer;
import whz.informatik.coffeeshop.shop.domain.Item;
import whz.informatik.coffeeshop.shop.domain.Warranty;
import whz.informatik.coffeeshop.shop.service.dto.WarrantyDTO;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface WarrantyService {

    Optional<Warranty> getWarrantyById(long warrentyId);

    Warranty warrantProduct(Item item, Customer customer);

    List<Warranty> getWarrantysByCustomer(Customer customer);


    Optional<WarrantyDTO> getWarrentyDTOById(long warrantyId);
    List<WarrantyDTO> getWarrantysDTOByCustomer(Customer customer);
    Warranty update(Warranty warranty);
}
