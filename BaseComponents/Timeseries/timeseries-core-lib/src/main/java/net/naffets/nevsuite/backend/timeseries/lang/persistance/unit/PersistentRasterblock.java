package net.naffets.nevsuite.backend.timeseries.lang.persistance.unit;

import java.time.Instant;
import java.util.LinkedList;

/**
 * User:    Braska<br />
 * Date:    13.06.2015<br />
 * <br />
 * <br />
 */
public class PersistentRasterblock {

    protected String timeseriesIdentifier;
    protected Instant pointInTime;
    protected LinkedList<PersistentValueStatusPair> valueMap;

    public PersistentRasterblock(String timeseriesIdentifier, Instant pointInTime) {
        this.timeseriesIdentifier = timeseriesIdentifier;
        this.pointInTime = pointInTime;
        this.valueMap = new LinkedList<>();
    }

    public String getTimeseriesIdentifier() {
        return timeseriesIdentifier;
    }

    public Instant getPointInTime() {
        return pointInTime;
    }

    public LinkedList<PersistentValueStatusPair> getValueMap() {
        return valueMap;
    }
}
