package net.naffets.nevsuite.backend.timeseries.webservice;

import net.naffets.nevsuite.backend.timeseries.core.timeseries.Timeseries;
import net.naffets.nevsuite.backend.timeseries.core.timeseries.TimeseriesInterval;
import net.naffets.nevsuite.backend.timeseries.core.valueplugin.BigDecimalPlugin;
import net.naffets.nevsuite.backend.timeseries.domain.assembler.TimeseriesAssembler;
import net.naffets.nevsuite.backend.timeseries.domain.assembler.TimeseriesHeadAssembler;
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
        return new TimeseriesHeadAssembler().toDTO(domainService.findAllTimeseriesHeads());
    }

    @RequestMapping(
            value = "/timeseriesHead/{id}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public String findTimeseriesHead(@PathVariable(name = "id") String id) {
        return new TimeseriesHeadAssembler().toJson(domainService.findTimeseriesHeadByPrimaryKey(id));
    }

    @RequestMapping(
            value = "/timeseriesHead",
            method = RequestMethod.PUT,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public String saveTimeseriesHead(@RequestBody String dto) {
        return new TimeseriesHeadAssembler().toJson(domainService.saveTimeseriesHead(new TimeseriesHeadAssembler().fromJson(dto, TimeseriesHeadDTO.class)));
    }

    @RequestMapping(value = "/timeseries/{headId}/{timestampFrom}/{timestampTo}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String loadValueMap(
            @PathVariable(name = "headId") String headId,
            @PathVariable(name = "timestampFrom") String timestampFrom,
            @PathVariable(name = "timestampTo") String timestampTo) {
        TimeseriesHead head = Optional.ofNullable(domainService.findTimeseriesHeadByPrimaryKey(headId))
                .orElse(TimeseriesHead.builder().build());
        if (head.getIdentifier() == null) head.setIdentifier("memory:" + head.getPrimaryKey());

        TimeseriesInterval interval = new TimeseriesInterval(Instant.parse(timestampFrom), Instant.parse(timestampTo));
        BigDecimalPlugin valuePlugin = new BigDecimalPlugin();
        dataProviderService.setValuePlugin(valuePlugin);
        dataProviderService.setTimeseriesIdentifier(head.getIdentifier());

        Timeseries<BigDecimal> timeseries = new Timeseries<>(valuePlugin, dataProviderService, interval);

        Instant measurementTimestamp = Instant.now();
        timeseries.load(interval);
        System.out.println("ReadOperation: " + (Instant.now().toEpochMilli() - measurementTimestamp.toEpochMilli()) + " ms");

        TimeseriesAssembler<BigDecimal> assembler = new TimeseriesAssembler<>();
        assembler.setPrettyPrintingEnabled(true);
        return assembler.toJson(timeseries, interval.getTimestampFrom(), interval.getTimestampTo());
    }

}
