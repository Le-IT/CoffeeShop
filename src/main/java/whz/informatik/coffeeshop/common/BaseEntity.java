package whz.informatik.coffeeshop.common;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Base-Class for Entity Objects
 * Class is integrated in tables of sub-classes
 * @param <PK> - type of the primary key
 */
@MappedSuperclass
public abstract class BaseEntity<PK extends Serializable> {
    @Id
    @GeneratedValue
    private PK id;

    public PK getId() {
        return id;
    }
    public void setId(PK id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        if (getId() != null)
            return getId().hashCode();
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        BaseEntity<?> other = (BaseEntity<?>) obj;
        return id != null && id.equals(other.id);
    }
}
