package whz.informatik.coffeeshop.shop.boundary;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import whz.informatik.coffeeshop.shop.service.CustomerService;
import whz.informatik.coffeeshop.shop.service.dto.CustomerDTO;


import java.util.List;

@Controller
public class AdministrationController {

    private Logger log = LoggerFactory.getLogger(AdministrationController.class);

    private CustomerService customerService;

    @Autowired
    public AdministrationController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = {"/users_managed"})
    public String getAdministrationPage(Model model) {
        List<CustomerDTO> customers = customerService.getAllDTO();
        model.addAttribute("listCustomers", customers);
        return "administration";
    }
}
