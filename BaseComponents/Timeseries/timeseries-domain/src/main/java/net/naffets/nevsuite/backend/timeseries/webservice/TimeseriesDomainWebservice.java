package net.naffets.nevsuite.backend.timeseries.webservice;

import net.naffets.nevsuite.backend.timeseries.domain.builder.TimeseriesHeadBuilder;
import net.naffets.nevsuite.backend.timeseries.domain.dto.TimeseriesHeadDTO;
import net.naffets.nevsuite.backend.timeseries.domain.dto.TimeseriesValueDTO;
import net.naffets.nevsuite.backend.timeseries.domain.entity.TimeseriesHead;
import net.naffets.nevsuite.backend.timeseries.domain.service.TimeseriesDomainService;
import net.naffets.nevsuite.backend.timeseries.lang.persistence.ValueMapDocumentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author br4sk4 / created on 22.10.2017
 */
@RestController
@RequestMapping("/DomainService")
public class TimeseriesDomainWebservice {

    @Autowired
    private TimeseriesDomainService domainService;

    @RequestMapping("/timeseriesHead")
    public List<TimeseriesHeadDTO> findTimeseriesHead() {
        return domainService.findAllTimeseriesHeads().stream().map(timeseriesHead -> new TimeseriesHeadBuilder()
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
    public String findTimeseriesHead(@PathVariable(name = "id") String id) {
        TimeseriesHead timeseriesHead = domainService.findTimeseriesHeadByPrimaryKey(id);
        return new TimeseriesHeadBuilder()
                .withPrimaryKey(timeseriesHead.getPrimaryKey())
                .withIdentifier(timeseriesHead.getIdentifier())
                .withType(timeseriesHead.getType().toString())
                .withDerivationType(timeseriesHead.getDerivationType().toString())
                .withPersistence(timeseriesHead.getPersistence().toString())
                .withPeriodicity(timeseriesHead.getPeriodicity().toString())
                //.withBlockSize(timeseriesHead.getBlocksize().toString())
                //.withRasterType(timeseriesHead.getRastertype().toString())
                .toJson();
    }

    @RequestMapping(value = "/timeseries", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String testTimeseriesPersistenceUnit() {
        List<TimeseriesValueDTO> list = new LinkedList<>();
        for (int x = 10; x <= 12; x++) {
            TimeseriesValueDTO valueDTO = new TimeseriesValueDTO();
            valueDTO.setTimestamp("2017-01-01T" + ((x < 10) ? "0" : "") + x + ":00:00Z");
            valueDTO.setValue(Integer.toString(x));
            list.add(valueDTO);
        }

        return new ValueMapDocumentBuilder()
                .withValues(list)
                .setPrettyPrintingEnabled(false)
                .setCompressionEnabled(false)
                .toJson();
    }

}
