package whz.informatik.coffeeshop.shop.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import whz.informatik.coffeeshop.common.CurrentUserUtil;
import whz.informatik.coffeeshop.shop.domain.*;
import whz.informatik.coffeeshop.shop.service.CustomerService;
import whz.informatik.coffeeshop.shop.service.ShoppingCartService;
import whz.informatik.coffeeshop.shop.service.ShoppingOrderService;

import java.util.ArrayList;
import java.util.List;

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
    @RequestMapping(value = "/sentOrder")
    public String handleSentOrder(Model model){
        String from = CurrentUserUtil.getCurrentUser(model);
        Customer customer = customerService.getByLoginName(from).get();
        ShoppingCart shoppingCart = currentShoppingCart.getShoppingCart();
        ShoppingOrder shoppingOrder = shoppingOrderService.createShoppingOrderForCustomer(customer);

        currentShoppingCart.setShoppingCart(null);
        shoppingCart.removeAllItems();
        shoppingCartService.update(shoppingCart);

        return "forward:/warranties/setup?orderId=" + shoppingOrder.getId();
    }

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping(value = "/templateOrder", method = RequestMethod.POST)
    public String handleTemplateOrder(Model model, @RequestParam Long orderid) {
        CurrentUserUtil.getCurrentUser(model);
        ShoppingOrder shoppingOrder = shoppingOrderService.getShoppingOrderById(orderid).get();
        ShoppingCart shoppingCart = currentShoppingCart.getShoppingCart();

//        currentShoppingCart.setShoppingCart(null);
//        shoppingCart.removeAllItems();
//
//        List<Item> copies = new ArrayList<>();
//        shoppingOrder.getItems().forEach(item -> {
//            Item it = new Item();
//            item.setup(item.getQuantity(), item.getProduct());
//            copies.add(it);
//        });
//
//        shoppingCartService.addAllItemsToCart(shoppingCart, copies);
//        shoppingCartService.update(shoppingCart);

        return "forward:/shoppingCart";
    }

}
