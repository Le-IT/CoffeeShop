package whz.informatik.coffeeshop.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import whz.informatik.coffeeshop.security.domain.Role;
import whz.informatik.coffeeshop.security.domain.User;
import whz.informatik.coffeeshop.security.domain.UserRepository;
import whz.informatik.coffeeshop.shop.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import whz.informatik.coffeeshop.shop.domain.repository.AddressRepository;
import whz.informatik.coffeeshop.shop.domain.repository.CustomerRepository;
import whz.informatik.coffeeshop.shop.domain.repository.ProductRepository;
import whz.informatik.coffeeshop.shop.domain.repository.ProductTypeRepository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

@Component
public class InitializeDB {
    private static final Logger log = LoggerFactory.getLogger(InitializeDB.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductTypeRepository productTypeRepository;


    @PostConstruct
    public void init() {
        log.debug("Database initialized");

        /** Initializing and Storing User-Objects **/
        User user = new User();
        user.setLoginName("kunde");
        user.setEmail("juliane.hartmann@mymail.de");
        user.setRole(Role.USER);
        user.setPasswordHash(new BCryptPasswordEncoder().encode("123"));
        userRepository.save(user);

        user = new User();
        user.setLoginName("kunde2");
        user.setEmail("luca.bachmeier@mymail.de");
        user.setRole(Role.USER);
        user.setPasswordHash(new BCryptPasswordEncoder().encode("123"));
        userRepository.save(user);

        user = new User();
        user.setLoginName("admin");
        user.setEmail("myadmin@de.foxy.coffeeshop.de");
        user.setRole(Role.ADMIN);
        user.setPasswordHash(new BCryptPasswordEncoder().encode("123"));
        userRepository.save(user);

        /** Initializing and Storing Address-Objects **/
        Address address1 = new Address(), address2 = new Address();
        address1.setup("Eichendorffstr.", "72", "72297", "Seewald");
        address2.setup("Ansbacher Straße","53","55624", "Schwerbach");
        addressRepository.save(address1);
        addressRepository.save(address2);

        /** Initializing and Storing Customer-Objects **/
        Customer customer1 = new Customer(), customer2 = new Customer();
        customer1.setup("Juliane", "Hartmann", "kunde", address1);
        customer2.setup("Luca", "Bachmeier", "kunde2", address2);
        customerRepository.save(customer1);
        customerRepository.save(customer2);

        /** Initializing and Storing ProductType-Objects **/
        ProductType type1 = new ProductType(),
                type2 = new ProductType(),
                type3 = new ProductType(),
                type4 = new ProductType(),
                type5 = new ProductType(),
                type6 = new ProductType();

        type1.setup("Tee",false);
        type2.setup("Trinkschokolade",false);
        type3.setup("Gebäck",false);
        type4.setup("Kaffee", false);
        type5.setup("Kaffeemühlen", true);
        type6.setup("Kaffeeautomaten", true);

        productTypeRepository.save(type1);productTypeRepository.save(type2);
        productTypeRepository.save(type3);productTypeRepository.save(type4);
        productTypeRepository.save(type5);productTypeRepository.save(type6);

        /** Initializing and Storing Product-Objects **/
        Product product1 = new Product(), product2 = new Product();
        product1.setup("Costadoro Caffè, Pads", "Kaffee-Pads", new BigDecimal(8.99), Currency.getInstance(Locale.GERMANY), type4);
        product2.setup("Senseo Ultima", "Kaffeemaschine von Senseo mit eingebautem Milchschäumer", new BigDecimal(199.99), Currency.getInstance(Locale.GERMANY), type6);

        productRepository.save(product1);
        productRepository.save(product2);

        log.debug("Data initialized");
    }

}
