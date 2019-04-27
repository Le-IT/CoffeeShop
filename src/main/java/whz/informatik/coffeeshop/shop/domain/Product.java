package whz.informatik.coffeeshop.shop.domain;

import whz.informatik.coffeeshop.common.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.Currency;

/**
 * Class for Product Entity
 */
@Entity
public class Product extends BaseEntity<Long> {
    private String name;
    private String description;
    private Currency currency;
    private BigDecimal price;
    @ManyToOne
    private ProductType productType;


    /** Constructor ommited **/


    public void setName(String name) { this.name = name; }
    public String getName() { return name; }
    public void setDescription(String description) { this.description = description; }
    public String getDescription() { return description; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public BigDecimal getPrice() { return price; }
    public void setCurrency(Currency currency) { this.currency = currency; }
    public Currency getCurrency() { return currency; }
    public void setProductType(ProductType productType) { this.productType = productType; }
    public ProductType getProductType() { return productType; }

    /**
     * setup method to set all necessary fields/ prepare for persist
     * @param name
     * @param description
     * @param price
     * @param currency
     * @param productType
     */
    public void setup(String name, String description, BigDecimal price, Currency currency, ProductType productType) {
        setName(name);
        setDescription(description);
        setPrice(price);
        setCurrency(currency);
        setProductType(productType);
    }

    @Override
    public String toString() {
        return "Product{" +
                    "id=" + getId() + " " +
                    "name='" + getName() + "' " +
                    "description='" + getDescription() + "' " +
                    "price=" + getPrice() + " " +
                    "currency=" + getCurrency() + " " +
                    "productType=" + getProductType() +
                "}";
    }
}
