package net.naffets.nevsuite.backend.timeseries.core.datatype;

import java.time.Instant;

/**
 * @author br4sk4
 * created on 22.03.2016
 */
public class VersionedValueStatusPair<V extends Comparable<V>, S> extends ValueStatusPair<V, S> {

    Instant version;

    public VersionedValueStatusPair(V value, S status, Instant version) {
        super(value, status);
        this.version = version;
    }

    public Instant getVersion() {
        return version;
    }

    public boolean equals(ValueStatusPair<V, S> vsp) {
        return this.getValue().equals(vsp.getValue());
    }
}
