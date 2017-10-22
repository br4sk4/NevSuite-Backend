package net.naffets.nevsuite.backend.framework.core.base;

import net.naffets.nevsuite.backend.framework.core.api.DataTransferObject;
import net.naffets.nevsuite.backend.framework.core.api.EntityBean;
import net.naffets.nevsuite.backend.framework.core.api.Validatable;
import net.naffets.nevsuite.backend.framework.core.api.ValidationConstraint;

import javax.persistence.Basic;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Objects;
import java.util.UUID;

/**
 * @author br4sk4
 * created on 03.07.2015
 */
@MappedSuperclass
public abstract class AbstractEntityBean<DTO extends DataTransferObject> extends AbstractDomainObject implements EntityBean<DTO> {

    @Id
    @Basic(optional = false)
    protected String primaryKey;

    public AbstractEntityBean() {
        this.primaryKey = UUID.randomUUID().toString();
    }

    public AbstractEntityBean(String primaryKey) {
        this.primaryKey = primaryKey;
        this.addValidationConstraint(Objects::nonNull);
        this.addValidationConstraint(object -> ((AbstractEntityBean) object).getPrimaryKey() != null);
    }

    @Override
    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        AbstractEntityBean that = (AbstractEntityBean) o;

        return this.getPrimaryKey().equals(that.getPrimaryKey());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + this.getPrimaryKey().hashCode();
        return result;
    }

}
