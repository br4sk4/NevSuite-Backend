package net.naffets.nevsuite.backend.timeseries.core.valueplugin;

import net.naffets.nevsuite.backend.timeseries.core.datatype.ValueStatusPair;
import net.naffets.nevsuite.backend.timeseries.core.datatype.VersionedValueStatusPair;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author br4sk4
 * created on 21.03.2016
 */
public class VersionedValueStatusPairPlugin<V extends Comparable<V>, S> extends ValuePluginBase<VersionedValueStatusPair<V, S>> {

    ValuePlugin<V> valuePlugin;
    S defaultStatus;
    Instant version;

    public VersionedValueStatusPairPlugin(
            ValuePlugin<V> valueHandler,
            S defaultStatus) {
        this.valuePlugin = valueHandler;
        this.defaultStatus = defaultStatus;
        this.version = Instant.now();
    }

    public VersionedValueStatusPair<V, S> create(String value) {
        return new VersionedValueStatusPair<>(valuePlugin.create(value), defaultStatus, version);
    }

    public VersionedValueStatusPair<V, S> create(Integer value) {
        return new VersionedValueStatusPair<>(valuePlugin.create(value), defaultStatus, version);
    }

    public VersionedValueStatusPair<V, S> create(Long value) {
        return new VersionedValueStatusPair<>(valuePlugin.create(value), defaultStatus, version);
    }

    public VersionedValueStatusPair<V, S> create(Double value) {
        return new VersionedValueStatusPair<>(valuePlugin.create(value), defaultStatus, version);
    }

    public VersionedValueStatusPair<V, S> create(BigDecimal value) {
        return new VersionedValueStatusPair<>(valuePlugin.create(value), defaultStatus, version);
    }

    public VersionedValueStatusPair<V, S> createZero() {
        return new VersionedValueStatusPair<>(valuePlugin.createZero(), defaultStatus, version);
    }

    public VersionedValueStatusPair<V, S> createOne() {
        return new VersionedValueStatusPair<>(valuePlugin.createOne(), defaultStatus, version);
    }

    public VersionedValueStatusPair<V, S> add(VersionedValueStatusPair<V, S> t1, VersionedValueStatusPair<V, S> t2) {
        t1 = (t1 == null ? createZero() : t1);
        return new VersionedValueStatusPair<>(valuePlugin.add(t1.getValue(), (t2 == null ? createZero() : t2).getValue()), t1.getStatus(), t1.getVersion());
    }

    public VersionedValueStatusPair<V, S> subtract(VersionedValueStatusPair<V, S> t1, VersionedValueStatusPair<V, S> t2) {
        t1 = (t1 == null ? createZero() : t1);
        return new VersionedValueStatusPair<>(valuePlugin.subtract(t1.getValue(), (t2 == null ? createZero() : t2).getValue()), t1.getStatus(), t1.getVersion());
    }

    public VersionedValueStatusPair<V, S> multiply(VersionedValueStatusPair<V, S> t1, VersionedValueStatusPair<V, S> t2) {
        t1 = (t1 == null ? createOne() : t1);
        return new VersionedValueStatusPair<>(valuePlugin.multiply(t1.getValue(), (t2 == null ? createOne() : t2).getValue()), t1.getStatus(), t1.getVersion());
    }

    public VersionedValueStatusPair<V, S> divide(VersionedValueStatusPair<V, S> t1, VersionedValueStatusPair<V, S> t2) {
        t1 = (t1 == null ? createOne() : t1);
        return new VersionedValueStatusPair<>(valuePlugin.divide(t1.getValue(), (t2 == null ? createOne() : t2).getValue()), t1.getStatus(), t1.getVersion());
    }

    public VersionedValueStatusPair<V, S> min(VersionedValueStatusPair<V, S> t1, VersionedValueStatusPair<V, S> t2) {
        if (t1 == null) return null;
        if (t2 == null || t2.getValue() == null) return t1;
        return t1.getValue().compareTo(t2.getValue()) <= 0 ? t1 : t2;
    }

    public VersionedValueStatusPair<V, S> max(VersionedValueStatusPair<V, S> t1, VersionedValueStatusPair<V, S> t2) {
        if (t1 == null) return null;
        if (t2 == null || t2.getValue() == null) return t1;
        return t1.getValue().compareTo(t2.getValue()) >= 0 ? t1 : t2;
    }

    public List<VersionedValueStatusPair<V, S>> spread(VersionedValueStatusPair<V, S> value,
                                                       Long estimatedSize,
                                                       SpreadType spreadType) {
        LinkedList<VersionedValueStatusPair<V, S>> spreadedValues = new LinkedList<>();
        for (V spreadedValue : this.valuePlugin.spread(value.getValue(), estimatedSize, spreadType)) {
            spreadedValues.addLast(new VersionedValueStatusPair<>(spreadedValue, defaultStatus, value.getVersion()));
        }
        return spreadedValues;
    }

    public VersionedValueStatusPair<V, S> compact(List<VersionedValueStatusPair<V, S>> values,
                                                  CompactType compactType) {
        List<V> values2compact = values.stream().map(ValueStatusPair::getValue).collect(Collectors.toCollection(LinkedList::new));
        V compactedValue = this.valuePlugin.compact(values2compact, compactType);
        return new VersionedValueStatusPair<>(compactedValue, defaultStatus, values.get(0).getVersion());
    }

    public Integer intValue(VersionedValueStatusPair<V, S> value) {
        return (value == null) ? null : valuePlugin.intValue(value.getValue());
    }

    public Long longValue(VersionedValueStatusPair<V, S> value) {
        return (value == null) ? null : valuePlugin.longValue(value.getValue());
    }

    public Double doubleValue(VersionedValueStatusPair<V, S> value) {
        return (value == null) ? null : valuePlugin.doubleValue(value.getValue());
    }

    public BigDecimal bigDecimalValue(VersionedValueStatusPair<V, S> value) {
        return (value == null) ? null : valuePlugin.bigDecimalValue(value.getValue());
    }
}
