package whz.informatik.coffeeshop.shop.service;

import whz.informatik.coffeeshop.shop.domain.Address;
import whz.informatik.coffeeshop.shop.service.dto.AddressDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service-Interface to provide functionality to controllers to
 * handle Address-Persistence
 */
public interface AddressService {
    /**
     * get all addresses
     * @return listAddress
     */
    List<Address> getAll();

    /**
     * find address with given id
     * @param addressId
     * @return optional_address
     */
    Optional<Address> getById(long addressId);

    /**
     * persist an address in the database
     * @param address
     * @return address with set id
     */
    Address addAddress(Address address);

    /**
     * create new Address based on the given properties
     * @param street
     * @param housenumber
     * @param zipCode
     * @param town
     * @return address
     */
    Address createAddress(String street,String housenumber,String zipCode,String town);


    /**
     * get all addresses as DTOs
     * @return addressDTOs
     */
    List<AddressDTO> getAllDTO();

    /**
     * find address with given id
     * @param addressId
     * @return addressDTO
     */
    Optional<AddressDTO> getDTOById(long addressId);


    /**
     * If Adress with address.id exists
     * updates the adress in db with the current state of address(param)
     * @param address - the customer to update
     * @return address - the customer for further calls, if non existent return null
     */
    Address updateAddress(Address address);

    /**
     * remove given address
     * @param address
     */
    void removeAddress(Address address);

    /**
     * remove the Address with the given id
     * @param addressId
     */
    void removeAddressById(long addressId);

    /**
     * check whether an Address with given id exists or not
     * @param addressId
     * @return true if there is an address with the given id
     */
    boolean existsWithId(long addressId);
}
