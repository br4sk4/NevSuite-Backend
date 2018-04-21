package net.naffets.nevsuite.backgroundprocesses.webservice;

import net.naffets.nevsuite.backgroundprocesses.domain.facade.EventSourcingDomainServiceFacade;
import net.naffets.nevsuite.eventsourcing.domain.dto.EventDescriptorDTO;
import net.naffets.nevsuite.eventsourcing.domain.dto.EventNotificationDTO;
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
public class BackgroundProcessesDomainWebservice {

    @Autowired
    private EventSourcingDomainServiceFacade eventSourcingDomainClient;

    @RequestMapping(value = "eventdescriptors", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EventDescriptorDTO> getEventDescriptors() {
        return eventSourcingDomainClient.getEventDescriptors();
    }

    @RequestMapping(value = "eventnotifications", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EventNotificationDTO> getEventNotifcations() {
        return eventSourcingDomainClient.getEventNotifications();
    }

}
