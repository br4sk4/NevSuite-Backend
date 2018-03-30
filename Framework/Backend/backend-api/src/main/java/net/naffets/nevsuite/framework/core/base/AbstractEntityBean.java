package net.naffets.nevsuite.framework.core.base;

import lombok.Getter;
import lombok.Setter;
import net.naffets.nevsuite.framework.core.api.EntityBean;

import javax.persistence.Basic;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * @author br4sk4
 * created on 03.07.2015
 */
@MappedSuperclass
@Getter
@Setter
public abstract class AbstractEntityBean extends AbstractDomainObject implements EntityBean {

    @Id
    @Basic(optional = false)
    @NotNull
    protected String primaryKey;

    public AbstractEntityBean() {
        this.primaryKey = UUID.randomUUID().toString();
    }

    public AbstractEntityBean(String primaryKey) {
        this.primaryKey = primaryKey != null ? primaryKey : UUID.randomUUID().toString();
    }

}
