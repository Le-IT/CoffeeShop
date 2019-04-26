package whz.informatik.coffeeshop.shop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import whz.informatik.coffeeshop.common.DTOUtils;
import whz.informatik.coffeeshop.security.domain.CustomerCreateForm;
import whz.informatik.coffeeshop.shop.domain.Address;
import whz.informatik.coffeeshop.shop.domain.Customer;
import whz.informatik.coffeeshop.shop.domain.repository.AddressRepository;
import whz.informatik.coffeeshop.shop.domain.repository.CustomerRepository;
import whz.informatik.coffeeshop.shop.service.dto.CustomerDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{
    private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

    private CustomerRepository customerRepository;
    private AddressRepository addressRepository;


    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository,
                               AddressRepository addressRepository) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
    }


    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> getById(long customerId) {
        return customerRepository.findById(customerId);
    }

    @Override
    public Optional<Customer> getByLoginName(String loginName) {
        return customerRepository.findByLoginName(loginName);
    }

    @Override
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<CustomerDTO> getAllDTO() {
        List<Customer> targetListOrigin = getAll();
        List<CustomerDTO> targetList = new ArrayList<>();
        targetListOrigin.forEach(customer -> targetList.add(DTOUtils.createDTO(customer)));
        return targetList;
    }

    @Override
    public List<CustomerDTO> getAllOrderedDTO() {
        List<Customer> targetListOrigin = customerRepository.findAllByOrderByLoginName();
        List<CustomerDTO> targetList = new ArrayList<>();
        targetListOrigin.forEach(customer -> targetList.add(DTOUtils.createDTO(customer)));
        return targetList;
    }

    @Override
    public Optional<CustomerDTO> getDTOById(long customerId) {
        System.out.println(customerId);
        Optional<Customer> optionalCustomer = getById(customerId);
        if(optionalCustomer.isPresent()) {
            CustomerDTO shoppingCartDTO = DTOUtils.createDTO(optionalCustomer.get());
            return Optional.of(shoppingCartDTO);
        } return Optional.empty();
    }

    @Override
    public Optional<CustomerDTO> getDTOByLoginName(String loginName) {
        Optional<Customer> optionalCustomer = getByLoginName(loginName);
        if(optionalCustomer.isPresent()) {
            CustomerDTO customerDTO = DTOUtils.createDTO(optionalCustomer.get());
            return Optional.of(customerDTO);
        } return Optional.empty();
    }

    /**
     * If customer with customer.id exists
     * updates the customer in db with the current state of customer(param)
     *
     * @param customer - the customer to update
     * @return customer - the customer for further calls, if non existant return null
     */
    @Override
    public Customer updateCustomer(Customer customer) {
        if(!customerRepository.existsById(customer.getId())) return null;
        return customerRepository.save(customer);
    }

    @Override
    public void removeCustomer(Customer customer) {
        customerRepository.delete(customer);
    }

    @Override
    public void removeCustomerById(long customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    public boolean existsWithLoginName(String loginName) {
        return customerRepository.existsByLoginName(loginName);
    }

    @Override
    public boolean existsWithId(long customerId) {
        return customerRepository.existsById(customerId);
    }

    @Transactional
    @Override
    public void createCustomer(CustomerCreateForm form) {
        log.info("Creating customer with loginName=" + form.getLoginName());

        Address address = new Address();
        address.setStreet(form.getStreet());
        address.setHousenumber(form.getHousenumber());
        address.setZipCode(form.getZipCode());
        address.setTown(form.getTown());

        addressRepository.save(address);

        Customer customer = new Customer();
        customer.setLoginName(form.getLoginName());
        customer.setFirstName(form.getFirstName());
        customer.setLastName(form.getLastName());

        customer.addAddress(address);

        customerRepository.save(customer);
    }
}
