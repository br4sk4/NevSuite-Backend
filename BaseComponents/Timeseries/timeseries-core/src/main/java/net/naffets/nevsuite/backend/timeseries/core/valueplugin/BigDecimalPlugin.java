package net.naffets.nevsuite.backend.timeseries.core.valueplugin;

import java.math.BigDecimal;

/**
 * @author br4sk4
 * created on 22.03.2016
 */
public class BigDecimalPlugin extends ValuePluginBase<BigDecimal> implements ValuePlugin<BigDecimal> {

    public BigDecimal create(String value) {
        return new BigDecimal(value);
    }

    public BigDecimal create(Integer value) {
        return new BigDecimal(Integer.toString(value));
    }

    public BigDecimal create(Long value) {
        return new BigDecimal(Long.toString(value));
    }

    public BigDecimal create(Double value) {
        return new BigDecimal(Double.toString(value));
    }

    public BigDecimal create(BigDecimal value) {
        return value;
    }

    public BigDecimal createZero() {
        return new BigDecimal(0);
    }

    public BigDecimal createOne() {
        return new BigDecimal(1);
    }

    public BigDecimal add(BigDecimal t1, BigDecimal t2) {
        if (t1 == null) return null;
        return t1.add(t2 == null ? createZero() : t2);
    }

    public BigDecimal subtract(BigDecimal t1, BigDecimal t2) {
        if (t1 == null) return null;
        return t1.subtract(t2 == null ? createZero() : t2);
    }

    public BigDecimal multiply(BigDecimal t1, BigDecimal t2) {
        if (t1 == null) return null;
        return t1.multiply(t2 == null ? createOne() : t2);
    }

    public BigDecimal divide(BigDecimal t1, BigDecimal t2) {
        if (t1 == null) return null;
        return t1.divide(t2 == null ? createOne() : t2);
    }

    public BigDecimal min(BigDecimal t1, BigDecimal t2) {
        if (t1 == null) return null;
        return t1.compareTo(t2 == null ? t1 : t2) <= 0 ? t1 : t2;
    }

    public BigDecimal max(BigDecimal t1, BigDecimal t2) {
        if (t1 == null) return null;
        return t1.compareTo(t2 == null ? t1 : t2) >= 0 ? t1 : t2;
    }

    public Integer intValue(BigDecimal value) {
        return (value == null) ? null : value.intValue();
    }

    public Long longValue(BigDecimal value) {
        return (value == null) ? null : value.longValue();
    }

    public Double doubleValue(BigDecimal value) {
        return (value == null) ? null : value.doubleValue();
    }

    public BigDecimal bigDecimalValue(BigDecimal value) {
        return value;
    }
}
