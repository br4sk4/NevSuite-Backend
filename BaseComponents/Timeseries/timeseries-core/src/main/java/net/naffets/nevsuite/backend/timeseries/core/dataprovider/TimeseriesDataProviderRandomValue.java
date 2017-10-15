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
public class TimeseriesDataProviderRandomValue<T> extends TimeseriesDataProviderBase<T> {

    protected int maxValue;
    protected int minValue;
    protected double maxIncreaseTolerance;
    protected double maxDecreasTolerance;

    protected TimeseriesDataProviderRandomValue(TimeseriesDataProviderRandomValue<T> dataProvider) {
        super(dataProvider.valuePlugin, dataProvider.period);
        this.maxValue = dataProvider.maxValue;
        this.minValue = dataProvider.minValue;
        this.maxIncreaseTolerance = dataProvider.maxIncreaseTolerance;
        this.maxDecreasTolerance = dataProvider.maxDecreasTolerance;
        this.valueMap.putAll(dataProvider.valueMap);
    }

    public TimeseriesDataProviderRandomValue(
            ValuePlugin<T> valuePlugin,
            TimeseriesPeriod period) {
        super(valuePlugin, period);

        this.minValue = -1;
        this.maxValue = -1;

        this.maxIncreaseTolerance = -1.0;
        this.maxDecreasTolerance = -1.0;
    }

    public TimeseriesDataProviderRandomValue(
            ValuePlugin<T> valuePlugin,
            TimeseriesPeriod period,
            int maxValue,
            int minValue) {
        super(valuePlugin, period);

        this.minValue = maxValue;
        this.maxValue = minValue;

        this.maxIncreaseTolerance = -1.0;
        this.maxDecreasTolerance = -1.0;
    }

    public TimeseriesDataProviderRandomValue(
            ValuePlugin<T> valuePlugin,
            TimeseriesPeriod period,
            int maxValue,
            int minValue,
            double maxIncreaseTolerance,
            double maxDecreasTolerance) {
        super(valuePlugin, period);

        this.minValue = maxValue;
        this.maxValue = minValue;

        this.maxIncreaseTolerance = -1.0;
        this.maxDecreasTolerance = -1.0;
    }

    public HashMap<Instant, T> load(TimeseriesInterval interval) {
        this.valueMap = new HashMap<>();
        Instant timestamp = Instant.from(interval.getTimestampFrom());

        double lower = (this.minValue > 0) ? this.minValue : 0.0;
        double upper = (this.maxValue > 0) ? this.maxValue - lower : 10.0;

        while (timestamp.compareTo(interval.getTimestampTo()) < 0) {
            this.valueMap.put(timestamp, valuePlugin.create(((Math.random() * (upper)) + lower)));
            timestamp = timestamp.plusSeconds(this.period.toSeconds(interval.getZonedTimestampFrom()));
        }

        return this.valueMap;
    }

    @SuppressWarnings("CloneDoesntCallSuperClone")
    public TimeseriesDataProvider<T> clone() {
        return new TimeseriesDataProviderRandomValue<>(this);
    }
}
