package net.naffets.nevsuite.backend.timeseries.webservice;

import net.naffets.nevsuite.backend.timeseries.core.timeseries.TimeseriesInterval;
import net.naffets.nevsuite.backend.timeseries.core.valueplugin.BigDecimalPlugin;
import net.naffets.nevsuite.backend.timeseries.domain.builder.TimeseriesHeadBuilder;
import net.naffets.nevsuite.backend.timeseries.domain.dto.TimeseriesHeadDTO;
import net.naffets.nevsuite.backend.timeseries.domain.dto.TimeseriesValueDTO;
import net.naffets.nevsuite.backend.timeseries.domain.entity.TimeseriesHead;
import net.naffets.nevsuite.backend.timeseries.domain.service.TimeseriesDataProviderService;
import net.naffets.nevsuite.backend.timeseries.domain.service.TimeseriesDomainService;
import net.naffets.nevsuite.backend.timeseries.lang.persistence.ValueMapDocumentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashMap;
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

    @Autowired
    private TimeseriesDataProviderService<BigDecimal> dataProviderService;

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
                .withBlockSize(timeseriesHead.getBlocksize().toString())
                .withRasterType(timeseriesHead.getRastertype().toString())
                .toJson();
    }

    @RequestMapping(value = "/timeseries", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<Instant, BigDecimal> testTimeseriesPersistenceUnit() {
        dataProviderService.setValuePlugin(new BigDecimalPlugin());
        Instant measurementTimestamp = Instant.now();
        HashMap<Instant, BigDecimal> valueMap = dataProviderService.load(new TimeseriesInterval(Instant.parse("2017-01-01T00:00:00Z"), Instant.parse("2017-01-02T00:00:00Z")));
        System.out.println("ReadOperation: " + (Instant.now().toEpochMilli() - measurementTimestamp.toEpochMilli()) + " ms");
        return valueMap;
    }

}
