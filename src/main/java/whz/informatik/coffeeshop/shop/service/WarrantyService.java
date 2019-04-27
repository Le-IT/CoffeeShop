package whz.informatik.coffeeshop.shop.service;


import whz.informatik.coffeeshop.shop.domain.Customer;
import whz.informatik.coffeeshop.shop.domain.Item;
import whz.informatik.coffeeshop.shop.domain.Warranty;
import whz.informatik.coffeeshop.shop.service.dto.WarrantyDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service-Interface to provide functionality to controllers to
 * handle Warranty-Persistence
 */
public interface WarrantyService {

    /**
     * find Warranty with given id
     * @param warrentyId
     * @return optional_warranty
     */
    Optional<Warranty> getWarrantyById(long warrentyId);

    /**
     * create a new Warranty for a given Item-Customer
     * @param item
     * @param customer
     * @return warranty
     */
    Warranty warrantProduct(Item item, Customer customer);

    /**
     * find all Warranties of the specified customer
     * @param customer
     * @return warranties
     */
    List<Warranty> getWarrantysByCustomer(Customer customer);

    /**
     * find Warranty with given id
     * and create DTO for network usage
     * @param warrantyId
     * @return optional_warrantyDTO
     */
    Optional<WarrantyDTO> getWarrentyDTOById(long warrantyId);

    /**
     * find all Warranties of the specified customer
     * and create DTOs for network usage
     * @param customer
     * @return warrantyDTOs
     */
    List<WarrantyDTO> getWarrantysDTOByCustomer(Customer customer);

    /**
     * update any changes of a Warranty to
     * the database
     * this may also be used to store newly created
     * warranties
     * @param warranty
     * @return Warranty for further usage | if warranty
     * did not exist before, with set id
     */
    Warranty update(Warranty warranty);
}
