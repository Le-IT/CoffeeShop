package whz.informatik.coffeeshop.shop.boundary;


import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import whz.informatik.coffeeshop.common.CurrentUserUtil;
import whz.informatik.coffeeshop.shop.domain.*;
import whz.informatik.coffeeshop.shop.service.*;

import java.text.DecimalFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class ShoppingCartController {

    private Logger log = LoggerFactory.getLogger(ShoppingCartController.class);

    private CurrentShoppingCart currentShoppingCart;
    private ShoppingOrderService shoppingOrderService;
    private ShoppingCartService shoppingCartService;
    private CustomerService customerService;
    private ProductService productService;
    private WarrantyService warrantyService;

    @Autowired
    public ShoppingCartController(CurrentShoppingCart currentShoppingCart,
                                  ShoppingCartService shoppingCartService,
                                  ShoppingOrderService shoppingOrderService,
                                  ProductService productService,
                                  CustomerService customerService,
                                  WarrantyService warrantyService){
        this.currentShoppingCart = currentShoppingCart;
        this.shoppingCartService = shoppingCartService;
        this.shoppingOrderService = shoppingOrderService;
        this.customerService = customerService;
        this.productService = productService;
        this.warrantyService = warrantyService;
    }

    private ShoppingCart getCurrentShoppingCart(Customer customer){
        ShoppingCart shoppingCart = currentShoppingCart.getShoppingCart();
        if(shoppingCart != null)
            return shoppingCart;

        List<ShoppingCart> shoppingCartList = shoppingCartService.getShoppingCartsByCustomer(customer);
        if(!shoppingCartList.isEmpty()) {
            shoppingCart = shoppingCartList.get(shoppingCartList.size() - 1);
            currentShoppingCart.setShoppingCart(shoppingCart);
            return shoppingCart;
        }

        log.debug("Creating new ShoppingCart for Customer = "+customer.getLoginName());
        shoppingCart = shoppingCartService.createShoppingCartForCustomer(customer);
        currentShoppingCart.setShoppingCart(shoppingCart);
        return shoppingCart;
    }


    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping (value = "/addToCart", method = RequestMethod.POST)
    public String handleAddItem(@RequestParam Long productId, @RequestParam int amount, Model model){
        String from = CurrentUserUtil.getCurrentUser(model);
        Customer customer = customerService.getByLoginName(from).get();
        ShoppingCart shoppingCart = getCurrentShoppingCart(customer);
        Item item = shoppingCartService.createItem(productId,amount);

        shoppingCartService.addItemToCart(shoppingCart, item);

        return "redirect:/";
    }

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping (value = "/deleteItemFromCart")
    public String handleDeleteItem(@RequestParam long itemId, Model model){
        String from = CurrentUserUtil.getCurrentUser(model);
        Customer customer = customerService.getByLoginName(from).get();
        ShoppingCart shoppingCart = getCurrentShoppingCart(customer);

        Item item = shoppingCart.getItemById(itemId).get();

        shoppingCart.removeItem(item);
        shoppingCartService.update(shoppingCart);
        model.addAttribute("actualCart", shoppingCart);
        model.addAttribute( "summedPrice", shoppingCart.getCalculatedSum());

        return "redirect:/shoppingCart?id="+customer.getId();
    }

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping (value = "shoppingCart")
    public String handleShoppingCart(Model model ){
        String from = CurrentUserUtil.getCurrentUser(model);
        Customer customer = customerService.getByLoginName(from).get();
        ShoppingCart shoppingCart = getCurrentShoppingCart(customer);

        model.addAttribute("actualCart", shoppingCart);
        model.addAttribute( "summedPrice", shoppingCart.getCalculatedSum());

        return "shoppingCart";
    }

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping (value = "/sentOrder")
    public String handleSentOrder(Model model){
        String from = CurrentUserUtil.getCurrentUser(model);
        Customer customer = customerService.getByLoginName(from).get();
        ShoppingCart shoppingCart = getCurrentShoppingCart(customer);
        ShoppingOrder shoppingOrder = shoppingOrderService.createShoppingOrderForCustomer(customer);

        shoppingCart.removeAllItems();
        shoppingCartService.update(shoppingCart);

        for(Item item : shoppingOrder.getItems()){
            if(item.getProduct().getProductType().isWithWarranty()){
                warrantyService.createWarrantyForOrderedProducts(customer);

            }
        }
        return "redirect:/profile";
    }

}
