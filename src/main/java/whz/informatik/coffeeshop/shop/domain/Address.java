package whz.informatik.coffeeshop.shop.domain;

import whz.informatik.coffeeshop.common.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Address extends BaseEntity<Long> {

    private String street;
    private String housenumber;
    private String town;
    private String zipCode;
    @ManyToMany(mappedBy = "addressList")
    private List<Customer> customers = new ArrayList<>();


    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getHousenumber() {
        return housenumber;
    }
    public void setHousenumber(String housenumber) {
        this.housenumber = housenumber;
    }
    public String getTown() {
        return town;
    }
    public void setTown(String town) {
        this.town = town;
    }
    public String getZipCode() {
        return zipCode;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }
    public void removeCustomer(Customer customer) { customers.remove(customer); }
    public List<Customer> getAllCustomer() { return customers; }
    public void setup(String street, String housenumber, String zipCode, String town) {
        setStreet(street);
        setHousenumber(housenumber);
        setZipCode(zipCode);
        setTown(town);
    }

    public String toPrintable() {
        return getStreet() + " " + getHousenumber() + ", " + getZipCode() + " " + getTown();
    }

    @Override
    public String toString() {
        return "Address{" +
                    "id=" + getId() + " " +
                    "street='" + getStreet() + "' " +
                    "housenumber='" + getHousenumber() + "' " +
                    "zipCode='" + getZipCode() + "' " +
                    "town='" + getTown() + "' " +
                    "customers=[" +
                        getAllCustomer().stream()
                                .map(customer -> customer.getId().toString())
                                .collect(Collectors.joining(" "))
                    + ']' +
                "}";
    }

}
