package net.naffets.nevsuite.eventsourcing.domain.service;

import net.naffets.nevsuite.eventsourcing.domain.assembler.EventDescriptorAssembler;
import net.naffets.nevsuite.eventsourcing.domain.dto.EventDescriptorDTO;
import net.naffets.nevsuite.eventsourcing.domain.repository.EventDescriptorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author br4sk4
 * created on 03.07.2016
 */
@Service
public class EventSourcingDomainService {

    @Autowired
    private EventDescriptorRepository eventDescriptorRepository;

    public List<EventDescriptorDTO> findEventDescriptors() {
        return new EventDescriptorAssembler().toDTO(eventDescriptorRepository.findAll());
    }

}
