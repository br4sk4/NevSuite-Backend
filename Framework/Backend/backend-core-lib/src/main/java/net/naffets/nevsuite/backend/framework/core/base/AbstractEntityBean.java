package net.naffets.nevsuite.backend.framework.core.base;

import net.naffets.nevsuite.backend.framework.core.api.DataTransferObject;
import net.naffets.nevsuite.backend.framework.core.api.EntityBean;
import net.naffets.nevsuite.backend.framework.core.api.Validatable;
import net.naffets.nevsuite.backend.framework.core.api.ValidationConstraint;

import java.util.UUID;

/**
 * @author br4sk4
 * created on 03.07.2015
 */
public abstract class AbstractEntityBean<ENTITY extends EntityBean, DTO extends DataTransferObject> extends AbstractDomainObject implements EntityBean<ENTITY, DTO> {

    public AbstractEntityBean() {
        this.setPrimaryKey(null);
        this.setUuid(UUID.randomUUID().toString());
    }

    public AbstractEntityBean(Long primaryKey) {
        this.setPrimaryKey(primaryKey);

        this.addValidationConstraint(new ValidationConstraint<Validatable>() {
            @Override
            public boolean validate(Validatable object) throws Exception {
                return object != null;
            }
        });

        this.addValidationConstraint(new ValidationConstraint<Validatable>() {
            @Override
            public boolean validate(Validatable object) throws Exception {
                return ((AbstractEntityBean) object).getPrimaryKey() != null;
            }
        });

        this.addValidationConstraint(new ValidationConstraint<Validatable>() {
            @Override
            public boolean validate(Validatable object) throws Exception {
                return ((AbstractEntityBean) object).getUuid() != null;
            }
        });
    }

    @Override
    public boolean isEntity() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        AbstractEntityBean that = (AbstractEntityBean) o;

        if (!this.getPrimaryKey().equals(that.getPrimaryKey())) return false;
        return this.getUuid().equals(that.getUuid());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + this.getPrimaryKey().hashCode();
        result = 31 * result + this.getUuid().hashCode();
        return result;
    }

}
