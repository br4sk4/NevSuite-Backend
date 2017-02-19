package net.naffets.nevsuite.backend.timeseries.lang.persistance.strategy;

import net.naffets.nevsuite.backend.timeseries.core.datatype.ValueStatusPair;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.HashMap;

/**
 * @author br4sk4
 * created on 20.03.2016
 */
public interface PersistenceStrategy {

    void connect();

    void disconnect();

    boolean isConnected();

    HashMap<Instant, ValueStatusPair<BigDecimal, byte[]>> read(
            String timeseriesIdentifier,
            Instant timestampFrom,
            Instant timestampTo
    );

    HashMap<Instant, ValueStatusPair<BigDecimal, byte[]>> read(
            String timeseriesIdentifier,
            ZonedDateTime timestampFrom,
            ZonedDateTime timestampTo
    );

    void write(
            String timeseriesIdentifier,
            HashMap<Instant, ValueStatusPair<BigDecimal, byte[]>> valueMap
    );

    void delete(
            String timeseriesIdentifier,
            Instant timestampFrom,
            Instant timestampTo
    );

    void delete(
            String timeseriesIdentifier,
            ZonedDateTime timestampFrom,
            ZonedDateTime timestampTo
    );

}
