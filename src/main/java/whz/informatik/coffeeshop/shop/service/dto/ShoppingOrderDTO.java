package whz.informatik.coffeeshop.shop.service.dto;

import whz.informatik.coffeeshop.common.BaseDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * DTO for ShoppingOrder for more efficient networking
 */
public class ShoppingOrderDTO extends BaseDTO<Long> {

    private Date orderDate;
    List<ItemDTO> items = new ArrayList<>();

    /** Constructor ommited **/


    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public List<ItemDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemDTO> items) {
        this.items = items;
    }
}
