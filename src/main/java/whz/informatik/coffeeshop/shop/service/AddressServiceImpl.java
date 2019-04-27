package whz.informatik.coffeeshop.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whz.informatik.coffeeshop.common.DTOUtils;
import whz.informatik.coffeeshop.shop.domain.Address;
import whz.informatik.coffeeshop.shop.domain.repository.AddressRepository;
import whz.informatik.coffeeshop.shop.service.dto.AddressDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService{

    private AddressRepository addressRepository;


    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository){
        this.addressRepository = addressRepository; }


    @Override
    public List<Address> getAll() {
        return addressRepository.findAll();
    }

    @Override
    public Optional<Address> getById(long addressId) {
        return addressRepository.findById(addressId);
    }

    @Override
    public Address addAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address createAddress(String street, String housenumber, String zipCode, String town) {
        Address adr = new Address();
        adr.setup(street,housenumber,zipCode,town);
        return addressRepository.save(adr);
    }

    @Override
    public List<AddressDTO> getAllDTO() {
        List<Address> targetListOrigin = getAll();
        List<AddressDTO> targetList = new ArrayList<>();
        targetListOrigin.forEach(address -> targetList.add(DTOUtils.createDTO(address)));
        return targetList;
    }

    @Override
    public Optional<AddressDTO> getDTOById(long addressId) {
        Optional<Address> optionalAddress = getById(addressId);
        if(optionalAddress.isPresent()) {
            AddressDTO customerDTO = DTOUtils.createDTO(optionalAddress.get());
            return Optional.of(customerDTO);
        } return Optional.empty();
    }

    /**
     * If Adress with address.id exists
     * updates the adress in db with the current state of address(param)
     *
     * @param address - the customer to update
     * @return address - the customer for further calls, if non existant return null
     */
    @Override
    public Address updateAddress(Address address) {
        if(!addressRepository.existsById(address.getId())) return null;
        return addressRepository.save(address);
    }

    @Override
    public void removeAddress(Address address) {
        addressRepository.delete(address);
    }

    @Override
    public void removeAddressById(long addressId) {
        addressRepository.deleteById(addressId);
    }

    @Override
    public boolean existsWithId(long addressId) {
        return addressRepository.existsById(addressId);
    }
}
