package net.naffets.nevsuite.eventsourcing.domain.assembler;

import net.naffets.nevsuite.eventsourcing.domain.dto.EventNotificationDTO;
import net.naffets.nevsuite.eventsourcing.domain.entity.EventNotification;
import net.naffets.nevsuite.framework.core.base.AbstractAssembler;

/**
 * @author br4sk4
 * created on 20.04.18
 */
public class EventNotificationAssembler extends AbstractAssembler<EventNotification, EventNotificationDTO> {

    @Override
    public EventNotificationDTO toDTO(EventNotification eventNotification) {
        return EventNotificationDTO.builder()
                .timestamp(eventNotification.getTimestamp().toString())
                .descriptor(eventNotification.getEventDescriptor().getQualifier().toString())
                .referencedObjectType(eventNotification.getReferencedObject().getTypeDiscriminator())
                .referencedObjectId(eventNotification.getReferencedObject().getPrimaryKey())
                .build();
    }
}
