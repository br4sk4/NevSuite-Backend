package net.naffets.nevsuite.backend.timeseries.core.valueplugin;

import java.math.BigDecimal;

/**
 * @author br4sk4
 * created on 22.03.2016
 */
public class DoublePlugin extends ValuePluginBase<Double> {

    public Double create(String value) {
        return Double.parseDouble(value);
    }

    public Double create(Integer value) {
        return value.doubleValue();
    }

    public Double create(Long value) {
        return value.doubleValue();
    }

    public Double create(Double value) {
        return value;
    }

    public Double create(BigDecimal value) {
        return value.doubleValue();
    }

    public Double createZero() {
        return 0d;
    }

    public Double createOne() {
        return 1d;
    }

    public Double add(Double t1, Double t2) {
        if (t1 == null) return null;
        return t1 + (t2 == null ? createZero() : t2);
    }

    public Double subtract(Double t1, Double t2) {
        if (t1 == null) return null;
        return t1 - (t2 == null ? createZero() : t2);
    }

    public Double multiply(Double t1, Double t2) {
        if (t1 == null) return null;
        return t1 * (t2 == null ? createOne() : t2);
    }

    public Double divide(Double t1, Double t2) {
        if (t1 == null) return null;
        return t1 / (t2 == null ? createOne() : t2);
    }

    public Double min(Double t1, Double t2) {
        if (t1 == null) return null;
        return t1.compareTo(t2 == null ? t1 : t2) <= 0 ? t1 : t2;
    }

    public Double max(Double t1, Double t2) {
        if (t1 == null) return null;
        return t1.compareTo(t2 == null ? t1 : t2) >= 0 ? t1 : t2;
    }

    public Integer intValue(Double value) {
        return (value == null) ? null : value.intValue();
    }

    public Long longValue(Double value) {
        return (value == null) ? null : value.longValue();
    }

    public Double doubleValue(Double value) {
        return value;
    }

    public BigDecimal bigDecimalValue(Double value) {
        return (value == null) ? null : new BigDecimal(value.toString());
    }
}
