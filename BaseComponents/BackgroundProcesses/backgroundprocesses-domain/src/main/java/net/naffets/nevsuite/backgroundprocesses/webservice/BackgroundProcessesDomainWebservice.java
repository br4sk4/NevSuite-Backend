package net.naffets.nevsuite.backgroundprocesses.webservice;

import net.naffets.nevsuite.backgroundprocesses.domain.adapter.EventSourcingDomainClient;
import net.naffets.nevsuite.eventsourcing.domain.dto.EventDescriptorDTO;
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
public class BackgroundProcessesDomainWebservice {

    @Autowired
    private EventSourcingDomainClient eventSourcingDomainClient;

    @RequestMapping(value = "eventdescriptors", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EventDescriptorDTO> getEventDescriptors() {
        return eventSourcingDomainClient.getEventDescriptors();
    }

}
