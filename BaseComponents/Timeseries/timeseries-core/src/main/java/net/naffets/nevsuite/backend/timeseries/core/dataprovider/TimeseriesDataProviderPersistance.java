package net.naffets.nevsuite.backend.timeseries.core.dataprovider;

import net.naffets.nevsuite.backend.timeseries.core.datatype.ValueStatusPair;
import net.naffets.nevsuite.backend.timeseries.core.timeseries.TimeseriesInterval;
import net.naffets.nevsuite.backend.timeseries.core.timeseries.TimeseriesPeriod;
import net.naffets.nevsuite.backend.timeseries.core.valueplugin.ValuePlugin;
import net.naffets.nevsuite.backend.timeseries.lang.persistance.strategy.PersistenceStrategy;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashMap;
import java.util.Set;

/**
 * @author br4sk4
 * created on 21.03.2016
 */
public class TimeseriesDataProviderPersistance<T> extends TimeseriesDataProviderBase<T> {

    protected PersistenceStrategy ps;
    protected String tsIdentifier;

    protected TimeseriesDataProviderPersistance(TimeseriesDataProviderPersistance<T> dataProvider) {
        super(dataProvider.valuePlugin, dataProvider.period);
        this.tsIdentifier = dataProvider.tsIdentifier;
        this.ps = dataProvider.ps;
    }

    public TimeseriesDataProviderPersistance(
            String tsIdentifier,
            TimeseriesPeriod period,
            ValuePlugin<T> valuePlugin,
            PersistenceStrategy ps) {
        super(valuePlugin, period);
        this.tsIdentifier = tsIdentifier;
        this.ps = ps;
    }

    public void save(HashMap<Instant, T> valueMap) {
        ps.connect();

        HashMap<Instant, ValueStatusPair<BigDecimal, byte[]>> convertedValueMap = new HashMap<>();
        Set<Instant> keySet = valueMap.keySet();
        for (Instant timestamp : keySet) {
            convertedValueMap.put(
                    timestamp,
                    new ValueStatusPair<>(
                            valuePlugin.bigDecimalValue(valueMap.get(timestamp)),
                            new byte[8])
            );
        }

        ps.write(this.tsIdentifier, convertedValueMap);
    }

    public HashMap<Instant, T> load(TimeseriesInterval interval) {
        ps.connect();

        HashMap<Instant, ValueStatusPair<BigDecimal, byte[]>> valueMap;
        HashMap<Instant, T> convertedValueMap = new HashMap<>();

        valueMap = ps.read(tsIdentifier, interval.getTimestampFrom(), interval.getTimestampTo());

        Set<Instant> keySet = valueMap.keySet();
        for (Instant timestamp : keySet) {
            convertedValueMap.put(
                    timestamp,
                    valuePlugin.create(valueMap.get(timestamp).getValue())
            );
        }

        return convertedValueMap;
    }

    @SuppressWarnings("CloneDoesntCallSuperClone")
    public TimeseriesDataProvider<T> clone() {
        return new TimeseriesDataProviderPersistance<>(this);
    }
}
