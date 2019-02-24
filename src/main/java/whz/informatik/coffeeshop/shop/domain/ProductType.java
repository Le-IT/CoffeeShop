package whz.informatik.coffeeshop.shop.domain;

import whz.informatik.coffeeshop.common.BaseEntity;

import javax.persistence.Entity;


@Entity
public class ProductType extends BaseEntity<Long> {
    private String name;
    private boolean withWarranty;


    public void setName(String name) { this.name = name; }
    public String getName() { return name; }
    public void setWithWarranty(boolean withWarranty) { this.withWarranty = withWarranty; }
    public boolean isWithWarranty() { return withWarranty; }
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
