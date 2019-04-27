package whz.informatik.coffeeshop.security.domain;

import org.springframework.security.core.authority.AuthorityUtils;

/**
 * Wrapper class for spring::User
 */
public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private User user;

    /**
     * Constructor for CurrentUser
     * @param user the current User
     */
    public CurrentUser(User user) {
        super(user.getLoginName(),user.getPasswordHash(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
        this.user = user;
    }

    public User getUser() {
        return user;
    }
    public Long getId() {
        return user.getId();
    }
    public String getLoginName() { return user.getLoginName();}
    public Role getRole() { return user.getRole(); }


    @Override
    public String toString() {
        return "CurrentUser{" +
                "user='" + user + "' " +
                "getLoginName='" + user.getLoginName() + "'" +
                "} " + super.toString();
    }
}

