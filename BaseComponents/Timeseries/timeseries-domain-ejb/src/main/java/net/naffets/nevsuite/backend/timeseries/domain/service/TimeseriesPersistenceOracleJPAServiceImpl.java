package net.naffets.nevsuite.backend.timeseries.domain.service;

import net.naffets.nevsuite.backend.framework.core.base.TransactionCachedRepository;
import net.naffets.nevsuite.backend.timeseries.core.datatype.ValueStatusPair;
import net.naffets.nevsuite.backend.timeseries.domain.builder.IntervalBuilder;
import net.naffets.nevsuite.backend.timeseries.domain.builder.RasterblockBuilder;
import net.naffets.nevsuite.backend.timeseries.domain.builder.ValueMapBuilder;
import net.naffets.nevsuite.backend.timeseries.domain.dto.RasterblockDTO;
import net.naffets.nevsuite.backend.timeseries.domain.entity.Rasterblock;
import net.naffets.nevsuite.backend.timeseries.domain.facade.TimeseriesPersistenceOracleJPAServiceFacade;
import net.naffets.nevsuite.backend.timeseries.domain.factory.RasterblockFactory;
import net.naffets.nevsuite.backend.timeseries.domain.repository.RasterblockRepository;
import net.naffets.nevsuite.backend.timeseries.domain.valueobject.Interval;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;

/**
 * @author br4sk4
 * created on 26.10.2016
 */
@Stateless
@Remote(TimeseriesPersistenceOracleJPAServiceFacade.class)
@Local(TimeseriesPersistenceOracleJPAService.class)
public class TimeseriesPersistenceOracleJPAServiceImpl implements TimeseriesPersistenceOracleJPAService {

    private static final Logger logger = LogManager.getLogger(TimeseriesPersistenceOracleJPAService.class.getName());

    @Inject
    private RasterblockRepository rblRepo;
    @Inject
    private RasterblockFactory rblFactory;

    @Override
    public void connect() {
    }

    @Override
    public void disconnect() {
    }

    @Override
    public boolean isConnected() {
        return true;
    }

    @Override
    public HashMap<Instant, ValueStatusPair<BigDecimal, byte[]>> read(String timeseriesIdentifier, Instant timestampFrom, Instant timestampTo) {
        final HashMap<Instant, ValueStatusPair<BigDecimal, byte[]>> valueMap = new HashMap<>();

        ValueMapBuilder vmBuilder = new ValueMapBuilder();
        Long measureTimestamp = System.currentTimeMillis();

        rblRepo.find().byInterval(timeseriesIdentifier, new Interval(timestampFrom, timestampTo)).stream()
                .forEach(rbl -> vmBuilder.fromJson(new StreamSource(new StringReader(ValueMapBuilder.gunzip(rbl.getValueMap())))).getValue().stream()
                        .forEach(vsp -> valueMap.put(vsp.getTimestamp(), new ValueStatusPair<>(vsp.getValue(), vsp.getStatus().getBytes()))));

        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        logger.info("ReadOperation(Server): " + measureTimestamp.doubleValue() / 1000 + " s");

        return valueMap;
    }

    @Override
    public HashMap<Instant, ValueStatusPair<BigDecimal, byte[]>> read(String timeseriesIdentifier, ZonedDateTime timestampFrom, ZonedDateTime timestampTo) {
        return this.read(timeseriesIdentifier, timestampFrom.toInstant(), timestampTo.toInstant());
    }

    @Override
    public void write(String timeseriesIdentifier, HashMap<Instant, ValueStatusPair<BigDecimal, byte[]>> valueMap) {
        Long size = (long) valueMap.size();
        Long timestamp = System.currentTimeMillis();
        List<Rasterblock<RasterblockDTO>> rblList = rblFactory.fromValueMap(timeseriesIdentifier, valueMap);

        TransactionCachedRepository<Rasterblock<RasterblockDTO>> repository = new TransactionCachedRepository<>(rblRepo);
        rblList.stream()
                .map(rbl -> {
                    Rasterblock<RasterblockDTO> newRbl = rblFactory.createEmpty().fromDTO(new RasterblockBuilder()
                            .withUuid(rbl.getUuid())
                            .withTimeseriesIdentifier(rbl.getTimeseriesIdentifier())
                            .withInterval(new IntervalBuilder()
                                    .withTimestampFrom(rbl.getInterval().getInstantFrom())
                                    .withTimestampTo(rbl.getInterval().getInstantTo())
                                    .build())
                            .build());
                    newRbl.setValueMap(ValueMapBuilder.gzip(rbl.getValueMap()));
                    return newRbl;
                })
                .forEach(repository::insert);
        repository.flush();

        timestamp = System.currentTimeMillis() - timestamp;
        logger.info("WriteOperation(Server, " + size + " Value" + (size > 1 ? "s" : "") + "): " + timestamp.doubleValue() / 1000 + " s");
    }

    @Override
    public void delete(String timeseriesIdentifier, Instant timestampFrom, Instant timestampTo) {
        Long timestamp = System.currentTimeMillis();

        TransactionCachedRepository<Rasterblock<RasterblockDTO>> rblRepository = new TransactionCachedRepository<>(rblRepo);
        rblRepo.find().byInterval(timeseriesIdentifier, new Interval(timestampFrom, timestampTo)).stream().forEach(rblRepository::delete);
        rblRepository.flush();

        timestamp = System.currentTimeMillis() - timestamp;
        logger.info("DeleteOperation(Server): " + timestamp.doubleValue() / 1000 + " s");
    }

    @Override
    public void delete(String timeseriesIdentifier, ZonedDateTime timestampFrom, ZonedDateTime timestampTo) {
        this.delete(timeseriesIdentifier, timestampFrom.toInstant(), timestampTo.toInstant());
    }

}
