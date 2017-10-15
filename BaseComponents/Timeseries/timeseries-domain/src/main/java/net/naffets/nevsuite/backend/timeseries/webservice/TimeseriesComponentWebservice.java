package net.naffets.nevsuite.backend.timeseries.webservice;

import net.naffets.nevsuite.backend.timeseries.domain.entity.TimeseriesHead;
import net.naffets.nevsuite.backend.timeseries.domain.service.TimeseriesComponentService;
import net.naffets.nevsuite.backend.timeseries.domain.service.TimeseriesHeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author br4sk4 / created on 12.10.2017
 */
@RestController
@RequestMapping("/ComponentService")
public class TimeseriesComponentWebservice {

    @Autowired
    private TimeseriesComponentService componentService;

    @Autowired
    private TimeseriesHeadService timeseriesHeadService;

    @RequestMapping("/respond")
    public String respond() {
        return componentService.respond();
    }

    @RequestMapping("/timeseriesHead")
    public List<TimeseriesHead> findPerson() {
        return timeseriesHeadService.findAll();
    }

    @RequestMapping("/timeseriesHead/{id}")
    public TimeseriesHead findPerson(@PathVariable(name = "id") String id) {
        return timeseriesHeadService.findByPrimaryKey(id);
    }

}
