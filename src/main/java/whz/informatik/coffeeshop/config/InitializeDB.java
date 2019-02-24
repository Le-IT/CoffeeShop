package whz.informatik.coffeeshop.config;

import whz.informatik.coffeeshop.shop.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import whz.informatik.coffeeshop.shop.domain.repository.AddressRepository;
import whz.informatik.coffeeshop.shop.domain.repository.CustomerRepository;

import javax.annotation.PostConstruct;

@Component
public class InitializeDB {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CustomerRepository customerRepository;



    @PostConstruct
    public void init() {

        /** Initializing and Storing Address-Objects **/
        Address address1 = new Address(), address2 = new Address();
        address1.setup("Eichendorffstr.", "72", "72297", "Seewald");
        address2.setup("Ansbacher Straße","53","55624", "Schwerbach");
        addressRepository.save(address1);
        addressRepository.save(address2);

        /** Initializing and Storing Customer-Objects **/
        Customer customer1 = new Customer(), customer2 = new Customer();
        customer1.setup("Juliane", "Hartmann", "kunde1", address1);
        customer2.setup("Luca", "Bachmeier", "kunde2", address2);
        customerRepository.save(customer1);
        customerRepository.save(customer2);

    }

}
