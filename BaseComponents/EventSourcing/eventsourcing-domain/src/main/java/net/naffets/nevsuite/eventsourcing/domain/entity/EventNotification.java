package net.naffets.nevsuite.eventsourcing.domain.entity;

import lombok.*;
import net.naffets.nevsuite.framework.core.api.Reference;
import net.naffets.nevsuite.framework.core.base.AbstractEntityBean;
import net.naffets.nevsuite.framework.core.base.BaseReference;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author br4sk4
 * created on 30.06.2016
 */
@Entity
@Table(name = "T_EVSC_EVENT_NOTIFICATION")
@AttributeOverride(name = "primaryKey", column = @Column(name = "EVNF_ID"))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class EventNotification extends AbstractEntityBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "EVNF_TIMESTAMP")
    private Timestamp timestamp;

    @OneToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "EVNF_EVDS_ID")
    private EventDescriptor eventDescriptor;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "typeDiscriminator", column = @Column(name = "EVNF_REFERENCE_TYPE")),
            @AttributeOverride(name = "primaryKey", column = @Column(name = "EVNF_REFERENCE_UUID"))
    })
    BaseReference referencedObject;

    @Builder
    public EventNotification(
            String primaryKey,
            Timestamp timestamp,
            EventDescriptor eventDescriptor,
            BaseReference referencedObject) {
        super(primaryKey);
        this.timestamp = timestamp;
        this.eventDescriptor = eventDescriptor;
        this.referencedObject = referencedObject;
    }

    @Override
    public Reference asReference() {
        return new BaseReference(this) {
            @Override
            public String getRepresentableName() {
                return "EventNotification";
            }
        };
    }

}
