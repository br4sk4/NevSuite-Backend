package net.naffets.nevsuite.backend.timeseries.domain.service;

import net.naffets.nevsuite.backend.timeseries.core.datatype.ValueStatusPair;
import net.naffets.nevsuite.backend.timeseries.domain.facade.TimeseriesPersistenceMongoDBServiceFacade;
import net.naffets.nevsuite.backend.timeseries.lang.persistance.strategy.PersistenceStrategy;
import net.naffets.nevsuite.backend.timeseries.lang.persistance.strategy.PersistenceStrategyFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Properties;

/**
 * @author br4sk4
 * created on 30.10.2016
 */
@Stateless
@Remote(TimeseriesPersistenceMongoDBServiceFacade.class)
@Local(TimeseriesPersistenceMongoDBService.class)
public class TimeseriesPersistenceMongoDBServiceImpl implements TimeseriesPersistenceMongoDBService {

    private static final Logger logger = LogManager.getLogger(TimeseriesPersistenceMongoDBService.class.getName());
    private PersistenceStrategy persistenceStrategy;

    public TimeseriesPersistenceMongoDBServiceImpl() {
        try {
            Properties properties = new Properties();
            properties.load(this.getClass().getClassLoader().getResourceAsStream("timeseries-database-hub.properties"));
            this.persistenceStrategy = PersistenceStrategyFactory.getInstance("mongodb", properties);
        } catch (Exception e) {
            logger.fatal(e.getMessage());
        }
    }

    @Override
    public void connect() {
        persistenceStrategy.connect();
    }

    @Override
    public void disconnect() {
        persistenceStrategy.disconnect();
    }

    @Override
    public boolean isConnected() {
        return persistenceStrategy.isConnected();
    }

    @Override
    public HashMap<Instant, ValueStatusPair<BigDecimal, byte[]>> read(String timeseriesIdentifier, Instant timestampFrom, Instant timestampTo) {
        Long timestamp = System.currentTimeMillis();
        HashMap<Instant, ValueStatusPair<BigDecimal, byte[]>> valueMap = persistenceStrategy.read(timeseriesIdentifier, timestampFrom, timestampTo);
        timestamp = System.currentTimeMillis() - timestamp;
        logger.info("ReadOperation(Server): " + timestamp.doubleValue() / 1000 + " s");

        return valueMap;
    }

    @Override
    public HashMap<Instant, ValueStatusPair<BigDecimal, byte[]>> read(String timeseriesIdentifier, ZonedDateTime timestampFrom, ZonedDateTime timestampTo) {
        Long timestamp = System.currentTimeMillis();
        HashMap<Instant, ValueStatusPair<BigDecimal, byte[]>> valueMap = persistenceStrategy.read(timeseriesIdentifier, timestampFrom, timestampTo);
        timestamp = System.currentTimeMillis() - timestamp;
        logger.info("ReadOperation(Server): " + timestamp.doubleValue() / 1000 + " s");

        return valueMap;
    }

    @Override
    public void write(String timeseriesIdentifier, HashMap<Instant, ValueStatusPair<BigDecimal, byte[]>> valueMap) {
        Long size = (long) valueMap.size();
        Long timestamp = System.currentTimeMillis();
        persistenceStrategy.write(timeseriesIdentifier, valueMap);
        timestamp = System.currentTimeMillis() - timestamp;
        logger.info("WriteOperation(Server, " + size + " Value" + (size > 1 ? "s" : "") + "): " + timestamp.doubleValue() / 1000 + " s");
    }

    @Override
    public void delete(String timeseriesIdentifier, Instant timestampFrom, Instant timestampTo) {
        Long timestamp = System.currentTimeMillis();
        persistenceStrategy.delete(timeseriesIdentifier, timestampFrom, timestampTo);
        timestamp = System.currentTimeMillis() - timestamp;
        logger.info("DeleteOperation(Server): " + timestamp.doubleValue() / 1000 + " s");
    }

    @Override
    public void delete(String timeseriesIdentifier, ZonedDateTime timestampFrom, ZonedDateTime timestampTo) {
        Long timestamp = System.currentTimeMillis();
        persistenceStrategy.delete(timeseriesIdentifier, timestampFrom, timestampTo);
        timestamp = System.currentTimeMillis() - timestamp;
        logger.info("DeleteOperation(Server): " + timestamp.doubleValue() / 1000 + " s");
    }

}
