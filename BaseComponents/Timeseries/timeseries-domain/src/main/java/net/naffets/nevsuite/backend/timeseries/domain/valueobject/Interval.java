package net.naffets.nevsuite.backend.timeseries.domain.valueobject;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.sql.Timestamp;
import java.time.Instant;

/**
 * @author br4sk4
 * created on 23.12.2016
 */
@Embeddable
@NoArgsConstructor
public class Interval {

    private static final long serialVersionUID = 1L;

    private Timestamp timestampFrom;
    private Timestamp timestampTo;

    @Builder
    public Interval(Instant timestampFrom, Instant timestampTo) {
        this.timestampFrom = timestampFrom != null ? Timestamp.from(timestampFrom) : null;
        this.timestampTo = timestampTo != null ? Timestamp.from(timestampTo) : null;
    }

    public Timestamp getTimestampFrom() {
        return timestampFrom;
    }

    public void setTimestampFrom(Timestamp timestampFrom) {
        this.timestampFrom = timestampFrom;
    }

    public Timestamp getTimestampTo() {
        return timestampTo;
    }

    public void setTimestampTo(Timestamp timestampTo) {
        this.timestampTo = timestampTo;
    }

    public Instant getInstantFrom() {
        return timestampFrom != null ? timestampFrom.toInstant() : null;
    }

    public void setInstantFrom(Instant timestampFrom) {
        this.timestampFrom = timestampFrom != null ? Timestamp.from(timestampFrom) : null;
    }

    public Instant getInstantTo() {
        return timestampTo != null ? timestampTo.toInstant() : null;
    }

    public void setInstantTo(Instant timestampTo) {
        this.timestampTo = timestampTo != null ? Timestamp.from(timestampTo) : null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Interval)) return false;

        Interval that = (Interval) o;

        if (timestampTo != null ? !timestampTo.equals(that.timestampTo) : that.timestampTo != null) return false;
        if (timestampFrom != null ? !timestampFrom.equals(that.timestampFrom) : that.timestampFrom != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = timestampFrom != null ? timestampFrom.hashCode() : 0;
        result = 31 * result + (timestampTo != null ? timestampTo.hashCode() : 0);
        return result;
    }

}
