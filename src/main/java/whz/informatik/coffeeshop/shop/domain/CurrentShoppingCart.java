package whz.informatik.coffeeshop.shop.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Wrapper class for shoppingCart to handle shoppingCart
 * easier in session
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CurrentShoppingCart {

    private ShoppingCart shoppingCart;


    /** Constructor ommited **/


    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Date getCreationDate(){
        return shoppingCart.getCreationDate();
    }

    public Customer getCustomer(){
        return shoppingCart.getCustomer();
    }

    public void addItem(Item item){ shoppingCart.addItem(item); }

    public void removeItem(Item item){
        shoppingCart.removeItem(item);
    }

    public List<Item> getItems(){
        return shoppingCart.getItems();
    }

}
