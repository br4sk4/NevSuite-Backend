package net.naffets.nevsuite.backend.timeseries.core.timeseries;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author br4sk4
 * created on 21.03.2016
 */
public class TimeseriesInterval {

    protected ZoneId zoneId;
    protected Instant timestampFrom;
    protected Instant timestampTo;

    public TimeseriesInterval(Instant timestampFrom, Instant timestampTo) {
        this.zoneId = ZoneId.of("UTC");
        this.timestampFrom = timestampFrom;
        this.timestampTo = timestampTo;
    }

    public TimeseriesInterval(Instant timestampFrom, Instant timestampTo, ZoneId zoneId) {
        this.zoneId = zoneId;
        this.timestampFrom = timestampFrom;
        this.timestampTo = timestampTo;
    }

    protected TimeseriesInterval(TimeseriesInterval interval) {
        this.zoneId = interval.zoneId;
        this.timestampFrom = interval.timestampFrom;
        this.timestampTo = interval.timestampTo;
    }

    public Instant getTimestampFrom() {
        return timestampFrom;
    }

    public Instant getTimestampTo() {
        return timestampTo;
    }

    public ZonedDateTime getZonedTimestampFrom() {
        return timestampFrom.atZone(this.zoneId);
    }

    public ZonedDateTime getZonedTimestampTo() {
        return timestampTo.atZone(this.zoneId);
    }

    public ZoneId getZoneId() {
        return zoneId;
    }

    public Set<Instant> getPeriodicIntervalSet(TimeseriesPeriod period) {
        Set<Instant> intervalSet = new TreeSet<>();
        Instant timestamp = this.getTimestampFrom();

        while (timestamp.compareTo(this.getTimestampTo()) < 0) {
            intervalSet.add(timestamp);
            timestamp = timestamp.plusSeconds(period.toSeconds(this.getZonedTimestampFrom()));
        }

        return intervalSet;
    }

    public TimeseriesInterval join(TimeseriesInterval interval) {
        if (this.equals(interval)) return new TimeseriesInterval(this);
        else if (this.timestampFrom.compareTo(interval.timestampFrom) < 0 && this.timestampTo.compareTo(interval.timestampTo) > 0)
            return new TimeseriesInterval(interval);
        else {
            TimeseriesInterval newInterval = new TimeseriesInterval(null, null, this.zoneId);
            newInterval.timestampFrom = (this.timestampFrom.compareTo(interval.timestampFrom) <= 0) ? this.timestampFrom : interval.timestampFrom;
            newInterval.timestampTo = (this.timestampTo.compareTo(interval.timestampTo) >= 0) ? this.timestampTo : interval.timestampTo;
            return newInterval;
        }
    }

    public boolean overlaps(TimeseriesInterval interval) {
        if (this.equals(interval) || (this.timestampFrom.compareTo(interval.timestampFrom) < 0 && this.timestampTo.compareTo(interval.timestampTo) > 0))
            return true; // overlaps whole
        else if (this.timestampFrom.compareTo(interval.timestampFrom) < 0 && this.timestampTo.compareTo(interval.timestampFrom) > 0)
            return true; // overlaps left
        else if (this.timestampFrom.compareTo(interval.timestampTo) < 0 && this.timestampTo.compareTo(interval.timestampTo) > 0)
            return true; // overlaps right
        else return false; // no overlaps
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = 31 * result + (timestampFrom != null ? timestampFrom.hashCode() : 0);
        result = 31 * result + (timestampTo != null ? timestampTo.hashCode() : 0);
        result = 31 * result + (zoneId != null ? zoneId.hashCode() : 0);

        return result;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (!(obj instanceof TimeseriesInterval)) return false;

        TimeseriesInterval interval = (TimeseriesInterval) obj;

        if (timestampFrom != null ? !timestampFrom.equals(interval.timestampFrom) : interval.timestampFrom != null)
            return false;
        if (timestampTo != null ? !timestampTo.equals(interval.timestampTo) : interval.timestampTo != null)
            return false;
        if (zoneId != null ? !zoneId.equals(interval.zoneId) : interval.zoneId != null) return false;

        return true;
    }
}
