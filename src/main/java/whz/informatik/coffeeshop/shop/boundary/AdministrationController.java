package whz.informatik.coffeeshop.shop.boundary;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import whz.informatik.coffeeshop.common.CurrentUserUtil;
import whz.informatik.coffeeshop.shop.domain.CurrentShoppingCart;
import whz.informatik.coffeeshop.shop.domain.Customer;
import whz.informatik.coffeeshop.shop.domain.Item;
import whz.informatik.coffeeshop.shop.domain.ShoppingCart;
import whz.informatik.coffeeshop.shop.service.CustomerService;
import whz.informatik.coffeeshop.shop.service.ShoppingCartService;
import whz.informatik.coffeeshop.shop.service.dto.CustomerDTO;
import whz.informatik.coffeeshop.shop.service.dto.ProductDTO;

import java.util.List;

@Controller
public class AdministrationController {

    private Logger log = LoggerFactory.getLogger(AdministrationController.class);

    private CustomerService customerService;

    @Autowired
    public AdministrationController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(value = {"/users_managed"})
    public String getAdministrationPage(Model model) {
        List<CustomerDTO> customers = customerService.getAllDTO();
        model.addAttribute("listCustomers", customers);
        return "administration";
    }





}
