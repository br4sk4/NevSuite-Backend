package net.naffets.nevsuite.eventsourcing.domain.assembler;

import net.naffets.nevsuite.eventsourcing.domain.dto.EventDescriptorDTO;
import net.naffets.nevsuite.eventsourcing.domain.entity.EventDescriptor;
import net.naffets.nevsuite.framework.core.base.AbstractAssembler;

/**
 * @author br4sk4
 * created on 20.04.18
 */
public class EventDescriptorAssembler extends AbstractAssembler<EventDescriptor, EventDescriptorDTO> {

    @Override
    public EventDescriptorDTO toDTO(EventDescriptor eventDescriptor) {
        return EventDescriptorDTO.builder()
                .qualifier(eventDescriptor.getQualifier().toString())
                .shortText(eventDescriptor.getQualifier().getShortText())
                .build();
    }
}
