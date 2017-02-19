package net.naffets.nevsuite.backend.timeseries.domain.service;

import net.naffets.nevsuite.backend.framework.core.api.EntityBean;
import net.naffets.nevsuite.backend.framework.core.base.TransactionCachedRepository;
import net.naffets.nevsuite.backend.framework.core.base.TransactionSingleRepository;
import net.naffets.nevsuite.backend.timeseries.domain.builder.*;
import net.naffets.nevsuite.backend.timeseries.domain.dto.RasterblockDTO;
import net.naffets.nevsuite.backend.timeseries.domain.dto.TimeseriesDTO;
import net.naffets.nevsuite.backend.timeseries.domain.dto.TimeseriesHeadDTO;
import net.naffets.nevsuite.backend.timeseries.domain.dto.ValueListDTO;
import net.naffets.nevsuite.backend.timeseries.domain.entity.Rasterblock;
import net.naffets.nevsuite.backend.timeseries.domain.facade.TimeseriesDomainServiceFacade;
import net.naffets.nevsuite.backend.timeseries.domain.factory.RasterblockFactory;
import net.naffets.nevsuite.backend.timeseries.domain.factory.TimeseriesHeadFactory;
import net.naffets.nevsuite.backend.timeseries.domain.repository.RasterblockRepository;
import net.naffets.nevsuite.backend.timeseries.domain.repository.TimeseriesHeadRepository;
import net.naffets.nevsuite.backend.timeseries.domain.valueobject.Interval;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author br4sk4
 * created on 12.09.2015
 */
@Stateless
@Remote(TimeseriesDomainServiceFacade.class)
@Local(TimeseriesDomainService.class)
public class TimeseriesDomainServiceImpl implements TimeseriesDomainService {

    private static final Logger logger = LogManager.getLogger(TimeseriesDomainServiceFacade.class.getName());

    @Inject
    private TimeseriesHeadRepository headRepo;
    @Inject
    private TimeseriesHeadFactory headFactory;

    @Inject
    private RasterblockRepository rblRepo;
    @Inject
    private RasterblockFactory rblFactory;

    @Override
    public TimeseriesHeadDTO findTimeseriesHeadByUuid(String uuid) {
        return headRepo.find().byUuid(uuid).asDTO();
    }

    @Override
    public List<TimeseriesHeadDTO> findTimeseriesHeadByIdentifier(String identifier) {
        return headRepo.find().byIdentifier(identifier).stream()
                .map(EntityBean::asDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TimeseriesHeadDTO> findAllTimeseriesHeads() {
        return headRepo.find().all().stream()
                .map(EntityBean::asDTO)
                .collect(Collectors.toList());
    }

    @Override
    public String insertTimeseriesHead(TimeseriesHeadDTO dto) {
        return new TransactionSingleRepository<>(headRepo)
                .insert(headFactory.createEmpty().fromDTO(dto));
    }

    @Override
    public String updateTimeseriesHead(TimeseriesHeadDTO dto) {
        return new TransactionSingleRepository<>(headRepo)
                .update(headFactory.createEmpty().fromDTO(dto));
    }

    @Override
    public String deleteTimeseriesHead(TimeseriesHeadDTO dto) {
        return new TransactionSingleRepository<>(headRepo)
                .delete(headFactory.createEmpty().fromDTO(dto));
    }

    @Override
    public TimeseriesDTO findTimeseriesValuesByInterval(String timeseriesIdentifier, ZonedDateTime timestampFrom, ZonedDateTime timestampTo) {
        ValueListDTO valueList = new ValueListDTO();

        rblRepo.find().byInterval(timeseriesIdentifier, new Interval(timestampFrom.toInstant(), timestampTo.toInstant())).stream()
                .forEach(rbl -> valueList.getValue().addAll(new ValueMapBuilder().fromJson(new StreamSource(new StringReader(rbl.getValueMap()))).getValue()));

        return new TimeseriesBuilder()
                .withTimeseriesHead(new TimeseriesHeadBuilder()
                        .withIdentifier(timeseriesIdentifier)
                        .build())
                .withValueMap(valueList)
                .build();
    }

    @Override
    public String insertTimeseriesValues(TimeseriesDTO dto) {
        List<Rasterblock<RasterblockDTO>> rblList = rblFactory.fromTimeseriesDTO(dto);

        TransactionCachedRepository<Rasterblock<RasterblockDTO>> repository = new TransactionCachedRepository<>(rblRepo);
        rblList.stream()
                .map(rbl -> rblFactory.createEmpty().fromDTO(new RasterblockBuilder()
                        .withUuid(rbl.getUuid())
                        .withTimeseriesIdentifier(dto.getHead().getIdentifier())
                        .withInterval(new IntervalBuilder()
                                .withTimestampFrom(rbl.getInterval().getInstantFrom())
                                .withTimestampTo(rbl.getInterval().getInstantTo())
                                .build())
                        .withValueMap(rbl.getValueMap())
                        .build()
                ))
                .forEach(repository::insert);
        repository.flush();

        return "OK";
    }

    @Override
    public String deleteTimeseriesValues(String timeseriesIdentifier, ZonedDateTime timestampFrom, ZonedDateTime timestampTo) {
        List<Rasterblock<RasterblockDTO>> rblList = rblRepo.find().byInterval(timeseriesIdentifier, new Interval(timestampFrom.toInstant(), timestampTo.toInstant()));

        TransactionCachedRepository<Rasterblock<RasterblockDTO>> repository = new TransactionCachedRepository<>(rblRepo);
        rblList.stream().forEach(repository::delete);
        repository.flush();

        return "OK";
    }

}
