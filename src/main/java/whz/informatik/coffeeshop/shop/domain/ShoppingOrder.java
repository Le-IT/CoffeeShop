package whz.informatik.coffeeshop.shop.domain;

import whz.informatik.coffeeshop.common.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class ShoppingOrder extends BaseEntity<Long> {
    private Date orderDate;
    @OneToMany
    List<Item> items = new ArrayList<>();


    public Date getOrderDate() { return orderDate; }
    public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }
    public List<Item> getItems() { return items; }
    public void addItem(Item item) {
        items.add(item);
    }
    public void removeItem(Item item) {
        items.remove(item);
    }
    public void setup(Date orderDate) { setOrderDate(orderDate); }

    @Override
    public String toString() {
        return "ShoppingOrder{" +
                    "id=" + getId() + " " +
                    "orderDate" + getOrderDate() + " " +
                    "items=" + getItems() +
                "}";
    }
}
