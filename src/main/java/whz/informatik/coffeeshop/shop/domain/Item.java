package whz.informatik.coffeeshop.shop.domain;

import whz.informatik.coffeeshop.common.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Class for Item Entity
 */
@Entity
public class Item extends BaseEntity<Long> {
    private int quantity;
    @ManyToOne
    private Product product;


    /** Constructor ommited **/


    public void setQuantity(int quantity) { this.quantity = quantity; }
    public int getQuantity() { return quantity; }

    public void setProduct(Product product) { this.product = product; }
    public Product getProduct() { return product; }

    /**
     * setup method to set all necessary fields/ prepare for persist
     * @param quantity
     * @param product
     */
    public void setup(int quantity, Product product) {
        setQuantity(quantity);
        setProduct(product);
    }

    @Override
    public String toString() {
        return "Item{" +
                    "id=" + getId() + " " +
                    "quantity=" + getQuantity() + " " +
                    "product=" + getProduct() +
                "}";
    }
}
