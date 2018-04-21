package net.naffets.nevsuite.eventsourcing.domain.facade;

import net.naffets.nevsuite.eventsourcing.domain.dto.EventDescriptorDTO;
import net.naffets.nevsuite.eventsourcing.domain.dto.EventNotificationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author br4sk4 / created on 21.04.2018
 */
@FeignClient("eventsourcing")
public interface EventSourcingDomainServiceFacade {

    @RequestMapping(method = RequestMethod.GET, value = "/DomainService/eventdescriptors")
    List<EventDescriptorDTO> getEventDescriptors();

    @RequestMapping(method = RequestMethod.GET, value = "/DomainService/eventnotifications")
    List<EventNotificationDTO> getEventNotifications();

    @RequestMapping(method = RequestMethod.POST, value = "/DomainService/eventnotifications")
    EventNotificationDTO pushEvent(EventNotificationDTO dto);

}
