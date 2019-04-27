package whz.informatik.coffeeshop.shop.domain;

import whz.informatik.coffeeshop.common.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Date;

/**
 * Class for Warranty Entity
 */
@Entity
public class Warranty extends BaseEntity<Long> {
    private Date endDate;
    @OneToOne
    private Item item;
    @ManyToOne
    private Customer customer;


    /** Constructor ommited **/


    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public Item getItem() {
        return item;
    }
    public void setItem(Item item) {
        this.item = item;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * setup method to set all necessary fields/ prepare for persist
     * @param endDate
     * @param item
     * @param customer
     */
    public void setup(Date endDate, Item item, Customer customer) {
        setEndDate(endDate);
        setItem(item);
        setCustomer(customer);
    }

    @Override
    public String toString() {
        return "Warranty{" +
                    "id=" + getId() + " " +
                    "endDate=" + getEndDate() + " " +
                    "item=" + getItem() + " " +
                    "customer=" + getCustomer() +
                "}";
    }
}
