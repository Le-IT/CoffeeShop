package whz.informatik.coffeeshop.security.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

/**
 * Entity Listener for User create/update/delete
 */
public class UserListener {
    private static final Logger log = LoggerFactory.getLogger(UserListener.class);

    @PostPersist
    public void logAfterCreate(User user) {
        log.info("Created User with loginName={}", user.getLoginName());
    }

    @PostUpdate
    public void logAfterUpdate(User user) {
        log.info("Updated User with loginName={}", user.getLoginName());
    }

    @PostRemove
    public void logAfterRemove(User user) {
        log.info("Deleted User with loginName={}", user.getLoginName());
    }
}
