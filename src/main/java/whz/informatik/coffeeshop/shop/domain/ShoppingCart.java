package whz.informatik.coffeeshop.shop.domain;


import whz.informatik.coffeeshop.common.BaseEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.text.DecimalFormat;
import java.util.*;

@Entity
public class ShoppingCart extends BaseEntity<Long> {
    private Date creationDate;
    @ManyToOne
    private Customer customer;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();

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
    public void setup(Date creationDate, Customer customer) {
        setCreationDate(creationDate);
        setCustomer(customer);
    }





    public Optional<Item> findProduct(Product product) {
        return items.stream()
                .filter(item -> product.equals(item.getProduct()))
                .findFirst();
    }

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
