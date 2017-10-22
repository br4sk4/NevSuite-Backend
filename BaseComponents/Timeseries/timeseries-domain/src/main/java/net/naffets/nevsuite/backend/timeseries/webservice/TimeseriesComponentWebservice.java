package net.naffets.nevsuite.backend.timeseries.webservice;

import net.naffets.nevsuite.backend.timeseries.domain.builder.TimeseriesHeadBuilder;
import net.naffets.nevsuite.backend.timeseries.domain.dto.TimeseriesHeadDTO;
import net.naffets.nevsuite.backend.timeseries.domain.entity.TimeseriesHead;
import net.naffets.nevsuite.backend.timeseries.domain.service.TimeseriesComponentService;
import net.naffets.nevsuite.backend.timeseries.domain.service.TimeseriesHeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<TimeseriesHeadDTO> findTimeseriesHead() {
        return timeseriesHeadService.findAll().stream().map(timeseriesHead -> new TimeseriesHeadBuilder()
                .withPrimaryKey(timeseriesHead.getPrimaryKey())
                .withIdentifier(timeseriesHead.getIdentifier())
                .withType(timeseriesHead.getType().toString())
                .withDerivationType(timeseriesHead.getDerivationType().toString())
                .withPersistence(timeseriesHead.getPersistence().toString())
                .withPeriodicity(timeseriesHead.getPeriodicity().toString())
                .withBlockSize(timeseriesHead.getBlocksize().toString())
                .withRasterType(timeseriesHead.getRastertype().toString())
                .build()).collect(Collectors.toList());
    }

    @RequestMapping(value = "/timeseriesHead/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public TimeseriesHeadDTO findTimeseriesHead(@PathVariable(name = "id") String id) {
        TimeseriesHead timeseriesHead = timeseriesHeadService.findByPrimaryKey(id);
        return new TimeseriesHeadBuilder()
                .withPrimaryKey(timeseriesHead.getPrimaryKey())
                .withIdentifier(timeseriesHead.getIdentifier())
                .withType(timeseriesHead.getType().toString())
                .withDerivationType(timeseriesHead.getDerivationType().toString())
                .withPersistence(timeseriesHead.getPersistence().toString())
                .withPeriodicity(timeseriesHead.getPeriodicity().toString())
                .withBlockSize(timeseriesHead.getBlocksize().toString())
                .withRasterType(timeseriesHead.getRastertype().toString())
                .build();
    }

}
