package net.naffets.nevsuite.backend.timeseries.core.dataprovider;

import net.naffets.nevsuite.backend.timeseries.core.timeseries.TimeseriesInterval;

import java.time.Instant;
import java.util.HashMap;

/**
 * @author br4sk4
 * created on 20.03.2016
 */
public interface TimeseriesDataProvider<T> {

    void save(HashMap<Instant, T> valueMap);

    HashMap<Instant, T> load(TimeseriesInterval interval);

    HashMap<Instant, T> getValueMap(TimeseriesInterval interval);

    T getValue(Instant timestamp);

    void setValue(Instant timestamp, T value);

    TimeseriesDataProvider<T> clone();

    TimeseriesDataProvider<T> clone(HashMap<Instant, T> valueMap);

}
