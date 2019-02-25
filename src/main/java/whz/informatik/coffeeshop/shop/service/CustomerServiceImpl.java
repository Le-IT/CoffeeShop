package whz.informatik.coffeeshop.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whz.informatik.coffeeshop.common.DTOUtils;
import whz.informatik.coffeeshop.shop.domain.Customer;
import whz.informatik.coffeeshop.shop.domain.repository.CustomerRepository;
import whz.informatik.coffeeshop.shop.service.dto.CustomerDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

    private CustomerRepository customerRepository;


    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
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
    public Optional<CustomerDTO> getDTOById(long customerId) {
        Optional<Customer> optionalCustomer = getById(customerId);
        if(optionalCustomer.isPresent()) {
            CustomerDTO shoppingCartDTO = DTOUtils.createDTO(optionalCustomer.get());
            return Optional.of(shoppingCartDTO);
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
}
