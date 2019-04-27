package whz.informatik.coffeeshop.shop.service.dto;

import whz.informatik.coffeeshop.common.BaseDTO;

/**
 * DTO for Item for more efficient networking
 */
public class ItemDTO extends BaseDTO<Long> {

    private int quantity;
    private ProductDTO product;

    /** Constructor ommited **/


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }
}
