package net.naffets.nevsuite.backend.timeseries.webservice;

import net.naffets.nevsuite.backend.timeseries.domain.service.TimeseriesComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author br4sk4 / created on 12.10.2017
 */
@RestController
@RequestMapping("/ComponentService")
public class TimeseriesComponentWebservice {

    @Autowired
    private TimeseriesComponentService service;

    @RequestMapping("/respond")
    public String respond() {
        return service.respond();
    }

}
