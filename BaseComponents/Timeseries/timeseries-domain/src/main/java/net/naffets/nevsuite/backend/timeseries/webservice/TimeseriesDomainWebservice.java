package net.naffets.nevsuite.backend.timeseries.webservice;

import net.naffets.nevsuite.backend.timeseries.core.timeseries.TimeseriesInterval;
import net.naffets.nevsuite.backend.timeseries.core.valueplugin.BigDecimalPlugin;
import net.naffets.nevsuite.backend.timeseries.domain.assembler.TimeseriesAssembler;
import net.naffets.nevsuite.backend.timeseries.domain.builder.TimeseriesBuilder;
import net.naffets.nevsuite.backend.timeseries.domain.builder.TimeseriesHeadBuilder;
import net.naffets.nevsuite.backend.timeseries.domain.dto.TimeseriesHeadDTO;
import net.naffets.nevsuite.backend.timeseries.domain.entity.TimeseriesHead;
import net.naffets.nevsuite.backend.timeseries.domain.service.TimeseriesDataProviderService;
import net.naffets.nevsuite.backend.timeseries.domain.service.TimeseriesDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author br4sk4 / created on 22.10.2017
 */
@RestController
@CrossOrigin(origins = "*")
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

    @RequestMapping(
            value = "/timeseriesHead/{id}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
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

    @RequestMapping(
            value = "/timeseriesHead",
            method = RequestMethod.PUT,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public String saveTimeseriesHead(@RequestBody String dto) {
        TimeseriesHead timeseriesHead = domainService.saveTimeseriesHead(new TimeseriesHeadBuilder().fromJson(dto));
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

    @RequestMapping(value = "/timeseries/{headId}/{timestampFrom}/{timestampTo}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String loadValueMap(
            @PathVariable(name = "headId") String headId,
            @PathVariable(name = "timestampFrom") String timestampFrom,
            @PathVariable(name = "timestampTo") String timestampTo) {
        TimeseriesHead head = Optional.ofNullable(domainService.findTimeseriesHeadByPrimaryKey(headId))
                .orElse(new TimeseriesHead());
        if (head.getIdentifier() == null) head.setIdentifier("memory:" + head.getPrimaryKey());

        dataProviderService.setValuePlugin(new BigDecimalPlugin());
        dataProviderService.setTimeseriesIdentifier(head.getIdentifier());

        Instant measurementTimestamp = Instant.now();
        TimeseriesBuilder timeseries = new TimeseriesAssembler<BigDecimal>().assembleTimeseries(
                head,
                dataProviderService.load(new TimeseriesInterval(Instant.parse(timestampFrom), Instant.parse(timestampTo))));
        System.out.println("ReadOperation: " + (Instant.now().toEpochMilli() - measurementTimestamp.toEpochMilli()) + " ms");

        return timeseries
                .setPrettyPrintingEnabled(true)
                .toJson();
    }

}
