package whz.informatik.coffeeshop.shop.service;

import whz.informatik.coffeeshop.shop.domain.Address;
import whz.informatik.coffeeshop.shop.service.dto.AddressDTO;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    List<Address> getAll();
    Optional<Address> getById(long addressId);
    Address addAddress(Address address);
    Address createAddress(String street,String housenumber,String zipCode,String town);

    List<AddressDTO> getAllDTO();
    Optional<AddressDTO> getDTOById(long customerId);


    /**
     * If Adress with address.id exists
     * updates the adress in db with the current state of address(param)
     * @param address - the customer to update
     * @return address - the customer for further calls, if non existant return null
     */
    Address updateAddress(Address address);
    void removeAddress(Address address);
    void removeAddressById(long addressId);
    boolean existsWithId(long addressId);
}
