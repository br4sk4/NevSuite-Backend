package net.naffets.nevsuite.eventsourcing.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.naffets.nevsuite.eventsourcing.domain.basictype.EventQualifier;
import net.naffets.nevsuite.framework.core.api.Reference;
import net.naffets.nevsuite.framework.core.base.AbstractEntityBean;
import net.naffets.nevsuite.framework.core.base.AbstractReference;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author br4sk4
 * created on 26.06.2016
 */
@Entity
@Table(name = "T_EVSC_EVENT_DESCRIPTOR")
@AttributeOverride(name = "primaryKey", column = @Column(name = "evds_id"))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class EventDescriptor extends AbstractEntityBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @Column(name = "EVDS_QUALIFIER")
    @Enumerated(EnumType.STRING)
    protected EventQualifier qualifier;

    @Override
    public Reference asReference() {
        return new AbstractReference(this) {
            @Override
            public String getRepresentableName() {
                return "EventDescriptor";
            }

            @Override
            public String getTypeDiscriminator() {
                return "EVDS";
            }
        };
    }

}
