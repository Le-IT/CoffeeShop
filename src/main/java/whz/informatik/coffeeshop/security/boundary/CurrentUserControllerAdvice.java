package whz.informatik.coffeeshop.security.boundary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import whz.informatik.coffeeshop.security.domain.CurrentUser;

/**
 * Advice for giving reference to current User to Controller
 */
@ControllerAdvice
public class CurrentUserControllerAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(CurrentUserControllerAdvice.class);

    @ModelAttribute("currentUser")
    public CurrentUser getCurrentUser(Authentication authentication) {
//        LOGGER.debug("authentication = " + authentication);
        return (authentication == null) ? null : (CurrentUser) authentication.getPrincipal();
    }
}
