package whz.informatik.coffeeshop.shop.service.dto;

import whz.informatik.coffeeshop.common.BaseDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO for Address for more efficient networking
 */
public class AddressDTO extends BaseDTO<Long> {

    private String street;
    private String housenumber;
    private String town;
    private String zipCode;
    private List<Long> customerIdList = new ArrayList<>();

    /** Constructor ommited **/


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

    public List<Long> getCustomerIdList() {
        return customerIdList;
    }

    public void setCustomerIdList(List<Long> customerIdList) {
        this.customerIdList = customerIdList;
    }
}
