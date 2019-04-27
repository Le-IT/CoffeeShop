package whz.informatik.coffeeshop.shop.domain;


import whz.informatik.coffeeshop.common.BaseEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Class for ShoppingCart Entity
 */
@Entity
public class ShoppingCart extends BaseEntity<Long> {
    private Date creationDate;
    @ManyToOne(cascade = CascadeType.DETACH)
    private Customer customer;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();


    /** Constructor ommited **/


    public Date getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public List<Item> getItems() {
        return items;
    }

    public  Optional<Item>  getItemById(long itemId){
        return items.stream()
                .filter(item -> item.getId() == itemId)
                .findFirst();
    }
    public void addItem(Item item) { items.add(item); }
    public void addAllItems(Collection<Item> items) {
        items.forEach(item -> addItem(item));
    }
    public void removeItem(Item item) {
        items.remove(item);
    }
    public void removeAllItems(){
       items.clear();
    }

    /**
     * setup method to set all necessary fields/ prepare for persist
     * @param creationDate
     * @param customer
     */
    public void setup(Date creationDate, Customer customer) {
        setCreationDate(creationDate);
        setCustomer(customer);
    }

    /**
     * TODO
     * @return
     */
    public String getCalculatedSum(){
        double tmpSum = 0;
        DecimalFormat f = new DecimalFormat("#0.00");
        for(Item item: items){
            tmpSum = tmpSum+ (item.getQuantity()* item.getProduct().getPrice().doubleValue());
        }
        return f.format(tmpSum);
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                    "id=" + getId() + " " +
                    "creationDate=" + getCreationDate() + " " +
                    "customer=" + getCustomer() + " " +
                    "items=" + getItems() +
                "}";
    }
}
