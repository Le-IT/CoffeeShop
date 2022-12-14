package whz.informatik.coffeeshop.security.boundary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import whz.informatik.coffeeshop.common.CurrentUserUtil;

/**
 * Controller for handling login and registration requests
 */
@Controller
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    /** Constructor ommited **/


    /**
     * request handling method for login
     * @param model
     * @return login page
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage(Model model) {
        String from = CurrentUserUtil.getCurrentUser(model);
        if(!from.equals("")) {
            log.debug("On processing get::'/login': Already logged in as '" + from + "' >> redirecting to '/'");
            return "redirect:/";
        } return "login";
    }

    /**
     * request handling method for register
     * @param model
     * @return register page
     */
    @RequestMapping(value = "/register", method = {RequestMethod.GET,RequestMethod.POST})
    public String getRegisterPage(Model model) {
        log.debug("GET::/register?error=" +
                (model.containsAttribute("error") ?
                        model.asMap().get("error") : "none"));
        return "register";
    }
}
