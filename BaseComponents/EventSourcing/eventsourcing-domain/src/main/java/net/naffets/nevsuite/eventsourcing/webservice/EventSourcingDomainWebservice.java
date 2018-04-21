package net.naffets.nevsuite.eventsourcing.webservice;

import net.naffets.nevsuite.eventsourcing.domain.dto.EventDescriptorDTO;
import net.naffets.nevsuite.eventsourcing.domain.dto.EventNotificationDTO;
import net.naffets.nevsuite.eventsourcing.domain.service.EventSourcingDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "eventdescriptors/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public EventDescriptorDTO getEventDescriptor(@PathVariable String id) {
        return eventSourcingDomainService.findEventDescriptor(id);
    }

    @RequestMapping(value = "eventnotifications", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EventNotificationDTO> getEventNotifications() {
        return eventSourcingDomainService.findEventNotifications();
    }

    @RequestMapping(value = "eventnotifications/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public EventNotificationDTO getEventNotification(@PathVariable String id) {
        return eventSourcingDomainService.findEventNotification(id);
    }

    @RequestMapping(value = "eventnotifications", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public EventNotificationDTO createEventNotification(@RequestBody EventNotificationDTO dto) {
        return eventSourcingDomainService.pushEvent(dto);
    }

}
