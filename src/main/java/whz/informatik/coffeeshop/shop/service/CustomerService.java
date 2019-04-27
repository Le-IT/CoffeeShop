package whz.informatik.coffeeshop.shop.service;

import whz.informatik.coffeeshop.security.domain.CustomerCreateForm;
import whz.informatik.coffeeshop.shop.domain.Customer;
import whz.informatik.coffeeshop.shop.service.dto.CustomerDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service-Interface to provide functionality to controllers to
 * handle Customer-Persistence
 */
public interface CustomerService {
    /**
     * get all Customers
     * @return customers
     */
    List<Customer> getAll();

    /**
     * find Customer by given id
     * @param customerId
     * @return optional_customer
     */
    Optional<Customer> getById(long customerId);

    /**
     * find Customer by given login
     * @param loginName
     * @return optional_customer
     */
    Optional<Customer> getByLoginName(String loginName);

    /**
     * store a customer in the database
     * @param customer
     * @return customer with set id
     */
    Customer addCustomer(Customer customer);

    /**
     * get all Customers as DTOs
     * @return customerDTOs
     */
    List<CustomerDTO> getAllDTO();

    /**
     * get all Customers ordered by login
     * and create DTOs for network usage
     * @return customerDTOs
     */
    List<CustomerDTO> getAllOrderedDTO();

    /**
     * find Customer by id
     * and create DTO for network usage
     * @param customerId
     * @return optional_customerDTO
     */
    Optional<CustomerDTO> getDTOById(long customerId);

    /**
     * find Customer by login
     * and create DTO for network usage
     * @param loginName
     * @return optional_customerDTO
     */
    Optional<CustomerDTO> getDTOByLoginName(String loginName);

    /**
     * If customer with customer.id exists
     * updates the customer in db with the current state of customer(param)
     * @param customer - the customer to update
     * @return customer - the customer for further calls, if non existant return null
     */
    Customer updateCustomer(Customer customer);

    /**
     * remove given Customer from database
     * @param customer
     */
    void removeCustomer(Customer customer);

    /**
     * remove Customer with given id
     * from database
     * @param customerId
     */
    void removeCustomerById(long customerId);

    /**
     * check whether a Customer exists with given login or not
     * @param loginName
     * @return true if there is a customer with given login
     */
    boolean existsWithLoginName(String loginName);

    /**
     *
     * @param customerId
     * @return
     */
    boolean existsWithId(long customerId);

    /**
     * create a Customer by form
     * @param form
     * @return customer
     */
    Customer createCustomer(CustomerCreateForm form);

}
