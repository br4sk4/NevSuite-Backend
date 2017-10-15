package net.naffets.nevsuite.backend.timeseries.core.datatype;

import java.io.Serializable;

/**
 * @author br4sk4
 * created on 22.03.2016
 */
public class ValueStatusPair<V extends Comparable<V>, S> implements Serializable {

    private static final long serialVersionUID = 1L;

    V value;
    S status;

    public ValueStatusPair(V value, S status) {
        this.value = value;
        this.status = status;
    }

    public V getValue() {
        return value;
    }

    public S getStatus() {
        return status;
    }

    public boolean equals(ValueStatusPair<V, S> vsp) {
        return this.getValue().equals(vsp.getValue());
    }
}
