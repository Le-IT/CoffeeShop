package whz.informatik.coffeeshop.shop.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import whz.informatik.coffeeshop.common.CurrentUserUtil;
import whz.informatik.coffeeshop.shop.domain.*;
import whz.informatik.coffeeshop.shop.service.CustomerService;
import whz.informatik.coffeeshop.shop.service.ShoppingCartService;
import whz.informatik.coffeeshop.shop.service.ShoppingOrderService;

@Controller
public class ShoppingOrderController {

    @Autowired
    private CurrentShoppingCart currentShoppingCart;

    private CustomerService customerService;
    private ShoppingCartService shoppingCartService;
    private ShoppingOrderService shoppingOrderService;

    @Autowired
    public ShoppingOrderController(CustomerService customerService,
                                   ShoppingCartService shoppingCartService,
                                   ShoppingOrderService shoppingOrderService) {
        this.customerService = customerService;
        this.shoppingCartService = shoppingCartService;
        this.shoppingOrderService = shoppingOrderService;
    }

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping(value = "/orders")
    public String handleShoppingOrder(Model model) {
        String from = CurrentUserUtil.getCurrentUser(model);
        Customer customer = customerService.getByLoginName(from).get();
        model.addAttribute("currentCustomer", customer);
        model.addAttribute("orders", customer.getOrders());
        return "shoppingOrder";
    }

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping (value = "/sentOrder")
    public String handleSentOrder(Model model){
        String from = CurrentUserUtil.getCurrentUser(model);
        Customer customer = customerService.getByLoginName(from).get();
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCartsByCustomer(customer).get(0);
        ShoppingOrder shoppingOrder = shoppingOrderService.createShoppingOrderForCustomer(customer);

        shoppingCart.removeAllItems();
        shoppingCartService.update(shoppingCart);
        currentShoppingCart.setShoppingCart(shoppingCart);

        return "forward:/warranties/setup?orderId=" + shoppingOrder.getId();
    }

}
