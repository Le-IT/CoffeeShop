package whz.informatik.coffeeshop.shop.domain;

import whz.informatik.coffeeshop.common.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer extends BaseEntity<Long> {

    private String firstName;
    private String lastName;
    private String loginName;
    @ManyToMany
    private List<Address> addressList = new ArrayList<>();
    @OneToMany
    private List<ShoppingOrder> orders = new ArrayList<>();
    @OneToMany
    private List<Warranty> warrantyList = new ArrayList<>();


    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getLoginName() {
        return loginName;
    }
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    public void addAddress(Address address) {
        address.addCustomer(this);
        addressList.add(address);
    }
    public List<Address> getAddressList() {
        return addressList;
    }
    public boolean removeAddress(Address address) {
        if (addressList.size()<2) return false;
        return addressList.remove(address);
    }
    public void addOrder(ShoppingOrder order) {
        orders.add(order);
    }
    public List<ShoppingOrder> getOrders() {
        return orders;
    }
    public void addWarranty(Warranty warranty) {
        warrantyList.add(warranty);
    }
    public List<Warranty> getWarrantyList() {
        return warrantyList;
    }
    public void setup(String firstName, String lastName, String loginName, Address address) {
        setFirstName(firstName);
        setLastName(lastName);
        setLoginName(loginName);
        addAddress(address);
    }


    @Override
    public String toString() {
        return "Customer{" +
                    "id=" + getId() + " " +
                    "firstName='" + getFirstName() + "' " +
                    "lastName='" + getLastName() + "' " +
                    "loginName='" + getLoginName() + "' " +
                    "addressList=" + getAddressList() + " " +
                    "orders=" + getOrders() + " " +
                    "warrantyList=" + getWarrantyList() +
                "}";
    }
}
