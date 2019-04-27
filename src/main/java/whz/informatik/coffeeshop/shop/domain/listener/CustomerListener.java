package whz.informatik.coffeeshop.shop.domain.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import whz.informatik.coffeeshop.shop.domain.Customer;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

/**
 * Entity Listener for Customer create/update/delete
 */
public class CustomerListener {
    private static final Logger log = LoggerFactory.getLogger(CustomerListener.class);

    @PostPersist
    public void logAfterCreate(Customer customer) {
        log.info("Created Customer with loginName={}", customer.getLoginName());
    }

    @PostUpdate
    public void logAfterUpdate(Customer customer) {
        log.info("Updated Customer with loginName={}", customer.getLoginName());
    }

    @PostRemove
    public void logAfterRemove(Customer customer) {
        log.info("Deleted Customer with loginName={}", customer.getLoginName());
    }
}
