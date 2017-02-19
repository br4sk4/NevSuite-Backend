package net.naffets.nevsuite.backend.timeseries.core.valueplugin;

import java.math.BigDecimal;

/**
 * @author br4sk4
 * created on 22.03.2016
 */
public class LongPlugin extends ValuePluginBase<Long> {

    public Long create(Integer value) {
        return value.longValue();
    }

    public Long create(Long value) {
        return value;
    }

    public Long create(Double value) {
        return value.longValue();
    }

    public Long create(BigDecimal value) {
        return value.longValue();
    }

    public Long createZero() {
        return 0L;
    }

    public Long createOne() {
        return 1L;
    }

    public Long add(Long t1, Long t2) {
        if (t1 == null) return null;
        return t1 + (t2 == null ? createZero() : t2);
    }

    public Long subtract(Long t1, Long t2) {
        if (t1 == null) return null;
        return t1 - (t2 == null ? createZero() : t2);
    }

    public Long multiply(Long t1, Long t2) {
        if (t1 == null) return null;
        return t1 * (t2 == null ? createOne() : t2);
    }

    public Long divide(Long t1, Long t2) {
        if (t1 == null) return null;
        return t1 / (t2 == null ? createOne() : t2);
    }

    public Long min(Long t1, Long t2) {
        if (t1 == null) return null;
        return t1.compareTo(t2 == null ? t1 : t2) <= 0 ? t1 : t2;
    }

    public Long max(Long t1, Long t2) {
        if (t1 == null) return null;
        return t1.compareTo(t2 == null ? t1 : t2) >= 0 ? t1 : t2;
    }

    public Integer intValue(Long value) {
        return (value == null) ? null : value.intValue();
    }

    public Long longValue(Long value) {
        return value;
    }

    public Double doubleValue(Long value) {
        return (value == null) ? null : value.doubleValue();
    }

    public BigDecimal bigDecimalValue(Long value) {
        return (value == null) ? null : new BigDecimal(value.toString());
    }
}
