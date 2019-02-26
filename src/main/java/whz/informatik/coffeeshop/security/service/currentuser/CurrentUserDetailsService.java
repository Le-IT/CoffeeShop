package whz.informatik.coffeeshop.security.service.currentuser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import whz.informatik.coffeeshop.security.domain.CurrentUser;
import whz.informatik.coffeeshop.security.domain.User;
import whz.informatik.coffeeshop.security.service.user.UserService;

@Service
@Qualifier("currentUser")
public class CurrentUserDetailsService implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(CurrentUserDetailsService.class);

    private UserService userService;

    @Autowired
    public CurrentUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CurrentUser loadUserByUsername(String loginName) throws UsernameNotFoundException {
        log.debug("Authenticating user with loginName=" + loginName);
        User user =
                userService.getUserByLoginName(loginName).orElseThrow(() ->
                        new UsernameNotFoundException("User with loginName= " + loginName + " cannot be not found"));

        return new CurrentUser(user);
    }

}
