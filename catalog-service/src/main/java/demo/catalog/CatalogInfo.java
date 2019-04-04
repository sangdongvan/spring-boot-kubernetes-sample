package demo.catalog;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class CatalogInfo implements Serializable {

    private Long id;
    private Long catalogId;
    private Boolean active;

    public CatalogInfo() {
        active = false;
    }

    public CatalogInfo(Long catalogId) {
        this();
        this.catalogId = catalogId;
    }

    public CatalogInfo(Long catalogId, Boolean active) {
        this(catalogId);
        this.active = active;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Long catalogId) {
        this.catalogId = catalogId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "CatalogInfo{" +
                "id='" + id + '\'' +
                ", catalogId=" + catalogId +
                ", active=" + active +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CatalogInfo that = (CatalogInfo) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (catalogId != null ? !catalogId.equals(that.catalogId) : that.catalogId != null) return false;
        return active != null ? active.equals(that.active) : that.active == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (catalogId != null ? catalogId.hashCode() : 0);
        result = 31 * result + (active != null ? active.hashCode() : 0);
        return result;
    }
}
