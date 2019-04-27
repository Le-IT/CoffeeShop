package whz.informatik.coffeeshop.shop.boundary;


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
import whz.informatik.coffeeshop.shop.domain.CurrentShoppingCart;
import whz.informatik.coffeeshop.shop.domain.Customer;
import whz.informatik.coffeeshop.shop.domain.Item;
import whz.informatik.coffeeshop.shop.domain.ShoppingCart;
import whz.informatik.coffeeshop.shop.service.CustomerService;
import whz.informatik.coffeeshop.shop.service.ShoppingCartService;

import java.util.List;
import java.util.Optional;

/**
 * Controller for handling shoppingCart related pages/actions
 * these pages are only accessible from the USER-perspective
 * respectively to users with associated customer accounts
 */
@Controller
public class ShoppingCartController {

    private Logger log = LoggerFactory.getLogger(ShoppingCartController.class);

    private CurrentShoppingCart currentShoppingCart;
    private ShoppingCartService shoppingCartService;
    private CustomerService customerService;

    /**
     * Constructor for ShoppingCartController
     * @param currentShoppingCart - session-scoped shoppingCart wrapper
     * @param shoppingCartService - service to provide info about shoppingCarts
     * @param customerService - service to provide info about customers
     */
    @Autowired
    public ShoppingCartController(CurrentShoppingCart currentShoppingCart,
                                  ShoppingCartService shoppingCartService,
                                  CustomerService customerService) {
        this.currentShoppingCart = currentShoppingCart;
        this.shoppingCartService = shoppingCartService;
        this.customerService = customerService;
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

    /**
     * request handling method for addItemToCart-Action
     * @param productId
     * @param amount
     * @param model
     * @return redirect to homepage
     */
    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping (value = "/addToCart", method = RequestMethod.POST)
    public String handleAddItem(@RequestParam Long productId, @RequestParam int amount, Model model){
        String from = CurrentUserUtil.getCurrentUser(model);
        Customer customer = customerService.getByLoginName(from).get();
        ShoppingCart shoppingCart = getCurrentShoppingCart(customer);

        shoppingCartService.createAndAddItemToCart(shoppingCart, productId, amount);

        currentShoppingCart.setShoppingCart(shoppingCart);

        return "redirect:/";
    }

    /**
     * request handling method for deleteItemFromCart-Action
     * @param itemId
     * @param model
     * @return redirect to shoppingCart page
     */
    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping (value = "/deleteItemFromCart")
    public String handleDeleteItem(@RequestParam long itemId, Model model){
        log.debug("Deleting Item itemId={}", itemId);
        String from = CurrentUserUtil.getCurrentUser(model);
        Customer customer = customerService.getByLoginName(from).get();
        ShoppingCart shoppingCart = getCurrentShoppingCart(customer);

        Optional<Item> item = shoppingCart.getItemById(itemId);
        if (item.isPresent()) {
            shoppingCart.getItems().remove(item.get());
            shoppingCartService.update(shoppingCart);
            shoppingCartService.deleteItem(item.get());
        }

        if(shoppingCart.getItems().isEmpty()) {
            log.debug("Discarding empty shoppingCart");
            shoppingCartService.deleteShoppingCart(shoppingCart);
            currentShoppingCart.setShoppingCart(null);
        } else
            currentShoppingCart.setShoppingCart(shoppingCart);

        return "redirect:/shoppingCart";
    }

    /**
     * request handling method for shoppingCart overview page
     * @param model
     * @return shoppingCart overview page
     */
    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping (value = "shoppingCart")
    public String handleShoppingCart(Model model ){
        String from = CurrentUserUtil.getCurrentUser(model);
        Customer customer = customerService.getByLoginName(from).get();
        ShoppingCart shoppingCart = currentShoppingCart.getShoppingCart();
        List<ShoppingCart> shoppingCartList = shoppingCartService.getShoppingCartsByCustomer(customer);
        if(!shoppingCartList.isEmpty())
            shoppingCart = shoppingCartList.get(shoppingCartList.size() - 1);

        model.addAttribute("actualCart", shoppingCart);
        if(shoppingCart != null) {
            model.addAttribute("summedPrice", shoppingCart.getCalculatedSum());
            currentShoppingCart.setShoppingCart(shoppingCart);
        }
        model.addAttribute("currentCustomer", customer);
        return "shoppingCart";
    }



}
