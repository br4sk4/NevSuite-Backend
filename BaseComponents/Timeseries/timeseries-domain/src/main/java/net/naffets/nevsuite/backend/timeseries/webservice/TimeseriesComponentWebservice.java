package net.naffets.nevsuite.backend.timeseries.webservice;

import net.naffets.nevsuite.backend.timeseries.domain.service.TimeseriesComponentService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * @author br4sk4 / created on 12.10.2017
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/ComponentService")
public class TimeseriesComponentWebservice {

    private TimeseriesComponentService componentService;

    @Inject
    public TimeseriesComponentWebservice(
            TimeseriesComponentService componentService) {
        this.componentService = componentService;
    }

    @RequestMapping("/respond")
    public String respond() {
        return componentService.respond();
    }

}
