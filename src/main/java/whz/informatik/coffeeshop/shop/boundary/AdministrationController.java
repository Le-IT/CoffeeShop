package whz.informatik.coffeeshop.shop.boundary;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import whz.informatik.coffeeshop.security.domain.Role;
import whz.informatik.coffeeshop.security.service.user.UserService;
import whz.informatik.coffeeshop.shop.service.CustomerService;

/**
 * Controller for handling administration related pages
 * only accessible in the ADMIN-perspective
 */
@Controller
public class AdministrationController {

    private Logger log = LoggerFactory.getLogger(AdministrationController.class);

    private UserService userService;
    private CustomerService customerService;

    /**
     * Constructor for OverviewController
     * @param userService- service to provide info about user
     * @param customerService - service to provide info about customer
     */
    @Autowired
    public AdministrationController(UserService userService,
                                    CustomerService customerService) {
        this.userService = userService;
        this.customerService = customerService;
    }

    /**
     * request handling method for user overview
     * @param model
     * @return userOverview
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = {"/users_managed"})
    public String getAdministrationPage(Model model) {
        model.addAttribute("listUser", userService.getAllUsersWithRoleDTO(Role.USER));
        model.addAttribute("listCustomer", customerService.getAllDTO());
        return "users_managed";
    }
}
