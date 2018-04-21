package net.naffets.nevsuite.eventsourcing.domain.service;

import net.naffets.nevsuite.eventsourcing.domain.assembler.EventDescriptorAssembler;
import net.naffets.nevsuite.eventsourcing.domain.assembler.EventNotificationAssembler;
import net.naffets.nevsuite.eventsourcing.domain.basictype.EventQualifier;
import net.naffets.nevsuite.eventsourcing.domain.dto.EventDescriptorDTO;
import net.naffets.nevsuite.eventsourcing.domain.dto.EventNotificationDTO;
import net.naffets.nevsuite.eventsourcing.domain.entity.EventDescriptor;
import net.naffets.nevsuite.eventsourcing.domain.entity.EventNotification;
import net.naffets.nevsuite.eventsourcing.domain.repository.EventDescriptorRepository;
import net.naffets.nevsuite.eventsourcing.domain.repository.EventNotificationRepository;
import net.naffets.nevsuite.framework.core.base.BaseReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

/**
 * @author br4sk4
 * created on 03.07.2016
 */
@Service
public class EventSourcingDomainService {

    @Autowired
    private EventDescriptorRepository eventDescriptorRepository;

    @Autowired
    private EventNotificationRepository eventNotificationRepository;

    public List<EventDescriptorDTO> findEventDescriptors() {
        return new EventDescriptorAssembler().toDTO(eventDescriptorRepository.findAll());
    }

    public EventDescriptorDTO findEventDescriptor(String id) {
        return new EventDescriptorAssembler().toDTO(eventDescriptorRepository.findById(id).orElse(EventDescriptor.builder().build()));
    }

    public List<EventNotificationDTO> findEventNotifications() {
        return new EventNotificationAssembler().toDTO(eventNotificationRepository.findAll());
    }

    public EventNotificationDTO findEventNotification(String id) {
        return new EventNotificationAssembler().toDTO(eventNotificationRepository.findById(id).orElse(EventNotification.builder().build()));
    }

    public EventNotificationDTO pushEvent(EventNotificationDTO dto) {
        EventNotification eventNotification = EventNotification.builder()
                .primaryKey(UUID.randomUUID().toString())
                .eventDescriptor(eventDescriptorRepository.findByQualifier(EventQualifier.valueOf(dto.descriptor)))
                .referencedObject(new BaseReference(dto.referencedObjectId, dto.referencedObjectType))
                .timestamp(Timestamp.from(Instant.now()))
                .build();
        return new EventNotificationAssembler().toDTO(eventNotificationRepository.save(eventNotification));
    }

}
