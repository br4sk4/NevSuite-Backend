package net.naffets.nevsuite.backend.timeseries.core.dataprovider;

import net.naffets.nevsuite.backend.timeseries.core.timeseries.TimeseriesInterval;
import net.naffets.nevsuite.backend.timeseries.core.timeseries.TimeseriesPeriod;
import net.naffets.nevsuite.backend.timeseries.core.valueplugin.ValuePlugin;

import java.time.Instant;
import java.util.HashMap;
import java.util.Set;

/**
 * @author br4sk4
 * created on 21.03.2016
 */
public abstract class TimeseriesDataProviderBase<T> implements TimeseriesDataProvider<T> {

    protected TimeseriesPeriod period;
    protected ValuePlugin<T> valuePlugin;
    protected HashMap<Instant, T> valueMap;

    protected TimeseriesDataProviderBase(
            ValuePlugin<T> valuePlugin,
            TimeseriesPeriod period) {
        this.valueMap = new HashMap<>();
        this.valuePlugin = valuePlugin;
        this.period = period;
    }

    protected TimeseriesDataProviderBase(TimeseriesDataProviderBase<T> dataProvider) {
        this.valueMap = new HashMap<>();
        this.valuePlugin = dataProvider.valuePlugin;
        this.period = dataProvider.period;
    }

    public void save(HashMap<Instant, T> valueMap) {
    }

    public abstract HashMap<Instant, T> load(TimeseriesInterval interval);

    @Override
    public HashMap<Instant, T> getValueMap() {
        return this.valueMap;
    }

    public HashMap<Instant, T> getValueMap(TimeseriesInterval interval) {
        HashMap<Instant, T> valueMap = new HashMap<>();
        Set<Instant> keySet = this.valueMap.keySet();

        keySet.stream().filter(instant -> instant.compareTo(interval.getTimestampFrom()) >= 0 && instant.compareTo(interval.getTimestampTo()) < 0)
                .forEach((Instant timestamp) -> valueMap.put(timestamp, this.valueMap.get(timestamp)));

        return valueMap;
    }

    public T getValue(Instant timestamp) {
        return this.valueMap.get(timestamp);
    }

    public void setValue(Instant timestamp, T value) {
        this.valueMap.put(timestamp, value);
    }

    public abstract TimeseriesDataProvider<T> clone();

    public TimeseriesDataProvider<T> clone(HashMap<Instant, T> valueMap) {
        TimeseriesDataProviderBase<T> dataProvider = (TimeseriesDataProviderBase<T>) this.clone();
        dataProvider.valueMap = valueMap;
        return dataProvider;
    }
}
