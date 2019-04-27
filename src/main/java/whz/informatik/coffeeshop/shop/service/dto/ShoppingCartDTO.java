package whz.informatik.coffeeshop.shop.service.dto;

import whz.informatik.coffeeshop.common.BaseDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * DTO for ShoppingCart for more efficient networking
 */
public class ShoppingCartDTO extends BaseDTO<Long> {

    private Date creationDate;
    private CustomerDTO customer;
    private List<ItemDTO> items = new ArrayList<>();

    /** Constructor ommited **/


    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public List<ItemDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemDTO> items) {
        this.items = items;
    }
}
