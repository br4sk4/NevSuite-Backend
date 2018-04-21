package net.naffets.nevsuite.eventsourcing.webservice;

import net.naffets.nevsuite.eventsourcing.domain.dto.EventDescriptorDTO;
import net.naffets.nevsuite.eventsourcing.domain.service.EventSourcingDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author br4sk4 / created on 21.04.2018
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/DomainService")
public class EventSourcingDomainWebservice {

    @Autowired
    private EventSourcingDomainService eventSourcingDomainService;

    @RequestMapping(value = "eventdescriptors", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EventDescriptorDTO> getEventDescriptors() {
        return eventSourcingDomainService.findEventDescriptors();
    }

}
