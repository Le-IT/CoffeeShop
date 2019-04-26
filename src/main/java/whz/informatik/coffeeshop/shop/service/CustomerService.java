package whz.informatik.coffeeshop.shop.service;

import whz.informatik.coffeeshop.security.domain.CustomerCreateForm;
import whz.informatik.coffeeshop.shop.domain.Customer;
import whz.informatik.coffeeshop.shop.service.dto.CustomerDTO;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Customer> getAll();
    Optional<Customer> getById(long customerId);
    Optional<Customer> getByLoginName(String loginName);
    Customer addCustomer(Customer customer);

    List<CustomerDTO> getAllDTO();
    List<CustomerDTO> getAllOrderedDTO();
    Optional<CustomerDTO> getDTOById(long customerId);
    Optional<CustomerDTO> getDTOByLoginName(String loginName);


    /**
     * If customer with customer.id exists
     * updates the customer in db with the current state of customer(param)
     * @param customer - the customer to update
     * @return customer - the customer for further calls, if non existant return null
     */
    Customer updateCustomer(Customer customer);
    void removeCustomer(Customer customer);
    void removeCustomerById(long customerId);
    boolean existsWithLoginName(String loginName);
    boolean existsWithId(long customerId);

    void createCustomer(CustomerCreateForm form);

}
