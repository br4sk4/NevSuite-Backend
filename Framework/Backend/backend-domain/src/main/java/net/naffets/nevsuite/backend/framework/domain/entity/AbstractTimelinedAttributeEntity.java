package net.naffets.nevsuite.backend.framework.domain.entity;

import lombok.Getter;
import lombok.Setter;
import net.naffets.nevsuite.framework.core.api.Reference;
import net.naffets.nevsuite.framework.core.base.AbstractEntityBean;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * @author br4sk4 / created on 13.06.2018
 */
@Entity
@Getter
@Setter
public class AbstractTimelinedAttributeEntity<T extends AbstractEntityBean> extends AbstractEntityBean {

    @OneToMany(mappedBy = "entity", cascade = CascadeType.ALL)
    protected List<TimelinedAttributeValue<T>> timelinedAttributeValues;

    @Override
    public Reference asReference() {
        return null;
    }

}
