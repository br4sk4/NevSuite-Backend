package net.naffets.nevsuite.eventsourcing.webservice;

import net.naffets.nevsuite.eventsourcing.domain.service.EventSourcingComponentService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * @author br4sk4
 * created on 20.04.18
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/ComponentService")
public class EventSourcingComponentWebervice {

    private EventSourcingComponentService componentService;

    @Inject
    public EventSourcingComponentWebervice(
            EventSourcingComponentService componentService) {
        this.componentService = componentService;
    }

    @RequestMapping("/respond")
    public String respond() {
        return componentService.respond();
    }

}
