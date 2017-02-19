package net.naffets.nevsuite.backend.timeseries.lang.persistance.unit;

import net.naffets.nevsuite.backend.timeseries.core.datatype.ValueStatusPair;

import java.math.BigDecimal;

/**
 * @author br4sk4
 * created on 12.06.2015
 */
public class PersistentValueStatusPair extends ValueStatusPair<BigDecimal, byte[]> {

    protected Long index;

    public PersistentValueStatusPair(Long index, BigDecimal value, byte[] status) {
        super(value, status);
        this.index = index;
    }

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

}
