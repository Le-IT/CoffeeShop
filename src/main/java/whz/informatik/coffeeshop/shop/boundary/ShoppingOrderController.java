package whz.informatik.coffeeshop.shop.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import whz.informatik.coffeeshop.common.CurrentUserUtil;
import whz.informatik.coffeeshop.shop.domain.Customer;
import whz.informatik.coffeeshop.shop.service.CustomerService;

@Controller
public class ShoppingOrderController {

    @Autowired
    CustomerService customerService;

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping(value = "/orders")
    public String handleShoppingOrder(Model model) {
        String from = CurrentUserUtil.getCurrentUser(model);
        Customer customer = customerService.getByLoginName(from).get();
        model.addAttribute("currentCustomer", customer);
        model.addAttribute("orders", customer.getOrders());
        return "shoppingOrder";
    }


}
