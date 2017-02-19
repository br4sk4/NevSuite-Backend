package net.naffets.nevsuite.backend.timeseries.core.valueplugin;

import java.math.BigDecimal;

/**
 * @author br4sk4
 * created on 23.03.2016
 */
public class IntegerPlugin extends ValuePluginBase<Integer> {

    public Integer create(Integer value) {
        return value;
    }

    public Integer create(Long value) {
        return value.intValue();
    }

    public Integer create(Double value) {
        return value.intValue();
    }

    public Integer create(BigDecimal value) {
        return value.intValue();
    }

    public Integer createZero() {
        return 0;
    }

    public Integer createOne() {
        return 1;
    }

    public Integer add(Integer t1, Integer t2) {
        if (t1 == null) return null;
        return t1 + (t2 == null ? createZero() : t2);
    }

    public Integer subtract(Integer t1, Integer t2) {
        if (t1 == null) return null;
        return t1 - (t2 == null ? createZero() : t2);
    }

    public Integer multiply(Integer t1, Integer t2) {
        if (t1 == null) return null;
        return t1 * (t2 == null ? createOne() : t2);
    }

    public Integer divide(Integer t1, Integer t2) {
        if (t1 == null) return null;
        return t1 / (t2 == null ? createOne() : t2);
    }

    public Integer min(Integer t1, Integer t2) {
        if (t1 == null) return null;
        return t1.compareTo(t2 == null ? t1 : t2) <= 0 ? t1 : t2;
    }

    public Integer max(Integer t1, Integer t2) {
        if (t1 == null) return null;
        return t1.compareTo(t2 == null ? t1 : t2) >= 0 ? t1 : t2;
    }

    public Integer intValue(Integer value) {
        return value;
    }

    public Long longValue(Integer value) {
        return (value == null) ? null : value.longValue();
    }

    public Double doubleValue(Integer value) {
        return (value == null) ? null : value.doubleValue();
    }

    public BigDecimal bigDecimalValue(Integer value) {
        return (value == null) ? null : new BigDecimal(value.toString());
    }

}
