package whz.informatik.coffeeshop.security.domain;

import org.springframework.security.core.authority.AuthorityUtils;

public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private User user;

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

