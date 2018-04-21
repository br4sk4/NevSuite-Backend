package net.naffets.nevsuite.eventsourcing.domain.entity;

import lombok.*;
import net.naffets.nevsuite.eventsourcing.domain.basictype.EventQualifier;
import net.naffets.nevsuite.framework.core.api.Reference;
import net.naffets.nevsuite.framework.core.base.AbstractEntityBean;
import net.naffets.nevsuite.framework.core.base.BaseReference;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author br4sk4
 * created on 26.06.2016
 */
@Entity
@Table(name = "T_EVSC_EVENT_DESCRIPTOR")
@AttributeOverride(name = "primaryKey", column = @Column(name = "EVDS_ID"))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class EventDescriptor extends AbstractEntityBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @Column(name = "EVDS_QUALIFIER")
    @Enumerated(EnumType.STRING)
    protected EventQualifier qualifier;

    @Builder
    public EventDescriptor(String primaryKey, EventQualifier qualifier) {
        super(primaryKey);
        this.qualifier = qualifier;
    }

    @Override
    public Reference asReference() {
        return new BaseReference(this) {
            @Override
            public String getRepresentableName() {
                return qualifier.toString();
            }
        };
    }

}
