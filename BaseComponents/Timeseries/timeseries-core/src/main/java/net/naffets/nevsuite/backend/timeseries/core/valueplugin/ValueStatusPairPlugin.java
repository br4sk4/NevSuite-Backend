package net.naffets.nevsuite.backend.timeseries.core.valueplugin;

import net.naffets.nevsuite.backend.timeseries.core.datatype.ValueStatusPair;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author br4sk4
 * created on 21.03.2016
 */
public class ValueStatusPairPlugin<V extends Comparable<V>, S> extends ValuePluginBase<ValueStatusPair<V, S>> {

    ValuePlugin<V> valuePlugin;
    S defaultStatus;

    public ValueStatusPairPlugin(
            ValuePlugin<V> valueHandler,
            S defaultStatus) {
        this.valuePlugin = valueHandler;
        this.defaultStatus = defaultStatus;
    }

    public ValueStatusPair<V, S> create(Integer value) {
        return new ValueStatusPair<>(valuePlugin.create(value), defaultStatus);
    }

    public ValueStatusPair<V, S> create(Long value) {
        return new ValueStatusPair<>(valuePlugin.create(value), defaultStatus);
    }

    public ValueStatusPair<V, S> create(Double value) {
        return new ValueStatusPair<>(valuePlugin.create(value), defaultStatus);
    }

    public ValueStatusPair<V, S> create(BigDecimal value) {
        return new ValueStatusPair<>(valuePlugin.create(value), defaultStatus);
    }

    public ValueStatusPair<V, S> createZero() {
        return new ValueStatusPair<>(valuePlugin.createZero(), defaultStatus);
    }

    public ValueStatusPair<V, S> createOne() {
        return new ValueStatusPair<>(valuePlugin.createOne(), defaultStatus);
    }

    public ValueStatusPair<V, S> add(ValueStatusPair<V, S> t1, ValueStatusPair<V, S> t2) {
        t1 = (t1 == null ? createZero() : t1);
        return new ValueStatusPair<>(valuePlugin.add(t1.getValue(), (t2 == null ? createZero() : t2).getValue()), t1.getStatus());
    }

    public ValueStatusPair<V, S> subtract(ValueStatusPair<V, S> t1, ValueStatusPair<V, S> t2) {
        t1 = (t1 == null ? createZero() : t1);
        return new ValueStatusPair<>(valuePlugin.subtract(t1.getValue(), (t2 == null ? createZero() : t2).getValue()), t1.getStatus());
    }

    public ValueStatusPair<V, S> multiply(ValueStatusPair<V, S> t1, ValueStatusPair<V, S> t2) {
        t1 = (t1 == null ? createOne() : t1);
        return new ValueStatusPair<>(valuePlugin.multiply(t1.getValue(), (t2 == null ? createOne() : t2).getValue()), t1.getStatus());
    }

    public ValueStatusPair<V, S> divide(ValueStatusPair<V, S> t1, ValueStatusPair<V, S> t2) {
        t1 = (t1 == null ? createOne() : t1);
        return new ValueStatusPair<>(valuePlugin.divide(t1.getValue(), (t2 == null ? createOne() : t2).getValue()), t1.getStatus());
    }

    public ValueStatusPair<V, S> min(ValueStatusPair<V, S> t1, ValueStatusPair<V, S> t2) {
        if (t1 == null) return null;
        if (t2 == null || t2.getValue() == null) return t1;
        return t1.getValue().compareTo(t2.getValue()) <= 0 ? t1 : t2;
    }

    public ValueStatusPair<V, S> max(ValueStatusPair<V, S> t1, ValueStatusPair<V, S> t2) {
        if (t1 == null) return null;
        if (t2 == null || t2.getValue() == null) return t1;
        return t1.getValue().compareTo(t2.getValue()) >= 0 ? t1 : t2;
    }

    public List<ValueStatusPair<V, S>> spread(ValueStatusPair<V, S> value,
                                              Long estimatedSize,
                                              SpreadType spreadType) {
        LinkedList<ValueStatusPair<V, S>> spreadedValues = new LinkedList<>();
        for (V spreadedValue : this.valuePlugin.spread(value.getValue(), estimatedSize, spreadType)) {
            spreadedValues.addLast(new ValueStatusPair<>(spreadedValue, defaultStatus));
        }
        return spreadedValues;
    }

    public ValueStatusPair<V, S> compact(List<ValueStatusPair<V, S>> values,
                                         CompactType compactType) {
        List<V> values2compact = values.stream().map(ValueStatusPair::getValue).collect(Collectors.toCollection(LinkedList::new));
        V compactedValue = this.valuePlugin.compact(values2compact, compactType);
        return new ValueStatusPair<>(compactedValue, defaultStatus);
    }

    public Integer intValue(ValueStatusPair<V, S> value) {
        return (value == null) ? null : valuePlugin.intValue(value.getValue());
    }

    public Long longValue(ValueStatusPair<V, S> value) {
        return (value == null) ? null : valuePlugin.longValue(value.getValue());
    }

    public Double doubleValue(ValueStatusPair<V, S> value) {
        return (value == null) ? null : valuePlugin.doubleValue(value.getValue());
    }

    public BigDecimal bigDecimalValue(ValueStatusPair<V, S> value) {
        return (value == null) ? null : valuePlugin.bigDecimalValue(value.getValue());
    }
}
