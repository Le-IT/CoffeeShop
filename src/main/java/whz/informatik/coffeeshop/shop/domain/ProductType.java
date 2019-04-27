package whz.informatik.coffeeshop.shop.domain;

import whz.informatik.coffeeshop.common.BaseEntity;

import javax.persistence.Entity;

/**
 * Class for ProductType Entity
 */
@Entity
public class ProductType extends BaseEntity<Long> {
    private String name;
    private boolean withWarranty;


    /** Constructor ommited **/


    public void setName(String name) { this.name = name; }
    public String getName() { return name; }
    public void setWithWarranty(boolean withWarranty) { this.withWarranty = withWarranty; }
    public boolean isWithWarranty() { return withWarranty; }

    /**
     * setup method to set all necessary fields/ prepare for persist
     * @param name
     * @param withWarranty
     */
    public void setup(String name, boolean withWarranty) {
        setName(name);
        setWithWarranty(withWarranty);
    }

    @Override
    public String toString() {
        return "ProductType{" +
                    "id=" + getId() + " " +
                    "name='" + getName() + "' " +
                    "withWarranty=" + isWithWarranty() +
                "}";
    }
}
