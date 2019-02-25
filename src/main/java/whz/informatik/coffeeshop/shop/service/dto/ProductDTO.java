package whz.informatik.coffeeshop.shop.service.dto;

import whz.informatik.coffeeshop.common.BaseDTO;

import java.math.BigDecimal;
import java.util.Currency;

public class ProductDTO extends BaseDTO<Long> {

    private String name;
    private String description;
    private Currency currency;
    private BigDecimal price;
    private ProductTypeDTO productType;

    /** Constructor ommited **/


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ProductTypeDTO getProductType() {
        return productType;
    }

    public void setProductType(ProductTypeDTO productType) {
        this.productType = productType;
    }
}
