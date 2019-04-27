package whz.informatik.coffeeshop.shop.service.dto;

import whz.informatik.coffeeshop.common.BaseDTO;

/**
 * DTO for ProductType for more efficient networking
 */
public class ProductTypeDTO extends BaseDTO<Long> {

    private String name;
    private boolean withWarranty;

    /** Constructor ommited **/


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isWithWarranty() {
        return withWarranty;
    }

    public void setWithWarranty(boolean withWarranty) {
        this.withWarranty = withWarranty;
    }
}
