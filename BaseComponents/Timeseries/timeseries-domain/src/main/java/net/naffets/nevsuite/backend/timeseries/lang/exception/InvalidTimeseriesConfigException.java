package net.naffets.nevsuite.backend.timeseries.lang.exception;

import net.naffets.nevsuite.backend.timeseries.domain.entity.TimeseriesHead;

/**
 * User:    Braska<br />
 * Date:    02.07.2015<br />
 * <br />
 * <br />
 */
public class InvalidTimeseriesConfigException extends RuntimeException {

    protected TimeseriesHead timeseries;

    public InvalidTimeseriesConfigException(TimeseriesHead ts) {
        this.timeseries = ts;
    }

    public TimeseriesHead getTimeseries() {
        return timeseries;
    }
}
