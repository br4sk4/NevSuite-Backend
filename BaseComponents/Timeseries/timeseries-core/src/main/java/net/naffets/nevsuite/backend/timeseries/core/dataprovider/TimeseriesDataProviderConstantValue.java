package net.naffets.nevsuite.backend.timeseries.core.dataprovider;

import net.naffets.nevsuite.backend.timeseries.core.timeseries.TimeseriesInterval;
import net.naffets.nevsuite.backend.timeseries.core.timeseries.TimeseriesPeriod;
import net.naffets.nevsuite.backend.timeseries.core.valueplugin.ValuePlugin;

import java.time.Instant;
import java.util.HashMap;

/**
 * @author br4sk4
 * created on 21.03.2016
 */
public class TimeseriesDataProviderConstantValue<T> extends TimeseriesDataProviderBase<T> {

    protected T constantValue;

    protected TimeseriesDataProviderConstantValue(TimeseriesDataProviderConstantValue<T> dataProvider) {
        super(dataProvider.valuePlugin, dataProvider.period);
        this.constantValue = dataProvider.constantValue;
        this.valueMap.putAll(dataProvider.valueMap);
    }

    public TimeseriesDataProviderConstantValue(
            ValuePlugin<T> valuePlugin,
            T constantValue,
            TimeseriesPeriod period) {
        super(valuePlugin, period);
        this.constantValue = constantValue;
    }

    public HashMap<Instant, T> load(TimeseriesInterval interval) {
        this.valueMap = new HashMap<>();

        interval.getPeriodicIntervalSet(this.period)
                .forEach(timestamp -> this.valueMap.put(timestamp, constantValue));

        return this.valueMap;
    }

    @SuppressWarnings("CloneDoesntCallSuperClone")
    public TimeseriesDataProvider<T> clone() {
        return new TimeseriesDataProviderConstantValue<>(this);
    }
}
