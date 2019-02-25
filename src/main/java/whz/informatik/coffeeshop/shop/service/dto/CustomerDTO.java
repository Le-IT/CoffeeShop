package whz.informatik.coffeeshop.shop.service.dto;

import whz.informatik.coffeeshop.common.BaseDTO;

import java.util.ArrayList;
import java.util.List;

public class CustomerDTO extends BaseDTO<Long> {
    private String firstName;
    private String lastName;
    private String loginName;
    private List<AddressDTO> addressList = new ArrayList<>();
    private List<WarrantyDTO> warrantyList = new ArrayList<>();

    /** Constructor ommited **/


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

    public List<AddressDTO> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<AddressDTO> addressList) {
        this.addressList = addressList;
    }

    public List<WarrantyDTO> getWarrantyList() {
        return warrantyList;
    }

    public void setWarrantyList(List<WarrantyDTO> warrantyList) {
        this.warrantyList = warrantyList;
    }
}
