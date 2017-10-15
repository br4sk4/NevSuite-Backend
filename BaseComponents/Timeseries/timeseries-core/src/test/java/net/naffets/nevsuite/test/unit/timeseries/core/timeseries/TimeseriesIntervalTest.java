package net.naffets.nevsuite.test.unit.timeseries.core.timeseries;

import net.naffets.nevsuite.backend.timeseries.core.timeseries.Timeseries;
import net.naffets.nevsuite.backend.timeseries.core.timeseries.TimeseriesInterval;
import net.naffets.nevsuite.backend.timeseries.core.timeseries.TimeseriesPeriod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.Assert.*;

/**
 * @author br4sk4
 * created on 22.03.2016
 */
public class TimeseriesIntervalTest {

    private final Logger logger = LogManager.getLogger(Timeseries.class.getName());

    @Test
    public void testGetIntervalSetDayRegularAtCET() {

        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        assertEquals(96, interval.getPeriodicIntervalSet(TimeseriesPeriod.MIN15).size());

    }

    @Test
    public void testGetIntervalSetDayRegularAtUTC() {

        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("UTC"));

        assertEquals(96, interval.getPeriodicIntervalSet(TimeseriesPeriod.MIN15).size());

    }

    @Test
    public void testGetIntervalSetDayMissingHourAtCET() {

        Instant timestampFrom = ZonedDateTime.of(2016, 3, 27, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 3, 28, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        assertEquals(92, interval.getPeriodicIntervalSet(TimeseriesPeriod.MIN15).size());

    }

    @Test
    public void testGetIntervalSetDayMissingHourAtUTC() {

        Instant timestampFrom = ZonedDateTime.of(2016, 3, 27, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 3, 28, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("UTC"));

        assertEquals(96, interval.getPeriodicIntervalSet(TimeseriesPeriod.MIN15).size());

    }

    @Test
    public void testGetIntervalSetDayAdditionalHourAtCET() {

        Instant timestampFrom = ZonedDateTime.of(2016, 10, 30, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 10, 31, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        assertEquals(100, interval.getPeriodicIntervalSet(TimeseriesPeriod.MIN15).size());

    }

    @Test
    public void testGetIntervalSetDayAdditionalHourAtUTC() {

        Instant timestampFrom = ZonedDateTime.of(2016, 10, 30, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 10, 31, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("UTC"));

        assertEquals(96, interval.getPeriodicIntervalSet(TimeseriesPeriod.MIN15).size());

    }

    @Test
    public void testGetIntervalSetMonth28Days() {

        Instant timestampFrom = ZonedDateTime.of(2015, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2015, 3, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        assertEquals(28, interval.getPeriodicIntervalSet(TimeseriesPeriod.DAY1).size());

    }

    @Test
    public void testGetIntervalSetMonth29Days() {

        Instant timestampFrom = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 3, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        assertEquals(29, interval.getPeriodicIntervalSet(TimeseriesPeriod.DAY1).size());

    }

    @Test
    public void testGetIntervalSetMonth30Days() {

        Instant timestampFrom = ZonedDateTime.of(2016, 4, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 5, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        assertEquals(30, interval.getPeriodicIntervalSet(TimeseriesPeriod.DAY1).size());

    }

    @Test
    public void testGetIntervalSetMonth31Days() {

        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        assertEquals(31, interval.getPeriodicIntervalSet(TimeseriesPeriod.DAY1).size());

    }

    @Test
    public void testOverlapsWholeWithEquals() {

        Instant timestampFrom, timestampTo;

        timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval intervalOne = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval intervalTwo = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        assertTrue(intervalOne.overlaps(intervalTwo));
        assertTrue(intervalTwo.overlaps(intervalOne));

    }

    @Test
    public void testOverlapsWhole() {

        Instant timestampFrom, timestampTo;

        timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval intervalOne = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        timestampFrom = ZonedDateTime.of(2015, 12, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2016, 3, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval intervalTwo = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        assertTrue(intervalTwo.overlaps(intervalOne));
        assertFalse(intervalOne.overlaps(intervalTwo));

    }

    @Test
    public void testOverlapsLeft() {

        Instant timestampFrom, timestampTo;

        timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval intervalOne = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        timestampFrom = ZonedDateTime.of(2015, 12, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2016, 1, 15, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval intervalTwo = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        assertTrue(intervalTwo.overlaps(intervalOne));
        assertTrue(intervalOne.overlaps(intervalTwo));

    }

    @Test
    public void testOverlapsRight() {

        Instant timestampFrom, timestampTo;

        timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval intervalOne = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        timestampFrom = ZonedDateTime.of(2016, 1, 15, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2016, 3, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval intervalTwo = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        assertTrue(intervalTwo.overlaps(intervalOne));
        assertTrue(intervalOne.overlaps(intervalTwo));

    }

    @Test
    public void testNoOverlaps() {

        Instant timestampFrom, timestampTo;

        timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval intervalOne = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        timestampFrom = ZonedDateTime.of(2015, 12, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval intervalTwo = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        assertFalse(intervalTwo.overlaps(intervalOne));
        assertFalse(intervalOne.overlaps(intervalTwo));

    }

    @Test
    public void testJoinOverlapsWholeWithEquals() {

        Instant timestampFrom, timestampTo;

        timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval intervalOne = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval intervalTwo = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        TimeseriesInterval intervalThree = intervalOne.join(intervalTwo);

        assertEquals(0, intervalThree.getTimestampFrom().compareTo(intervalOne.getTimestampFrom()));
        assertEquals(0, intervalThree.getTimestampTo().compareTo(intervalOne.getTimestampTo()));

    }

    @Test
    public void testJoinOverlapsWhole() {

        Instant timestampFrom, timestampTo;

        timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval intervalOne = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        timestampFrom = ZonedDateTime.of(2015, 12, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2016, 3, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval intervalTwo = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        TimeseriesInterval intervalThree = intervalOne.join(intervalTwo);

        assertEquals(0, intervalThree.getTimestampFrom().compareTo(intervalTwo.getTimestampFrom()));
        assertEquals(0, intervalThree.getTimestampTo().compareTo(intervalTwo.getTimestampTo()));

    }

    @Test
    public void testJoinOverlapsLeft() {

        Instant timestampFrom, timestampTo;

        timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval intervalOne = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        timestampFrom = ZonedDateTime.of(2015, 12, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2016, 1, 15, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval intervalTwo = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        TimeseriesInterval intervalThree = intervalOne.join(intervalTwo);

        assertEquals(0, intervalThree.getTimestampFrom().compareTo(intervalTwo.getTimestampFrom()));
        assertEquals(0, intervalThree.getTimestampTo().compareTo(intervalOne.getTimestampTo()));

    }

    @Test
    public void testJoinOverlapsRight() {

        Instant timestampFrom, timestampTo;

        timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval intervalOne = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        timestampFrom = ZonedDateTime.of(2016, 1, 15, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2016, 3, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval intervalTwo = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        TimeseriesInterval intervalThree = intervalOne.join(intervalTwo);

        assertEquals(0, intervalThree.getTimestampFrom().compareTo(intervalOne.getTimestampFrom()));
        assertEquals(0, intervalThree.getTimestampTo().compareTo(intervalTwo.getTimestampTo()));

    }

    @Test
    public void testJoinNoOverlaps() {

        Instant timestampFrom, timestampTo;

        timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval intervalOne = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        timestampFrom = ZonedDateTime.of(2015, 12, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval intervalTwo = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        TimeseriesInterval intervalThree = intervalOne.join(intervalTwo);

        assertEquals(0, intervalThree.getTimestampFrom().compareTo(intervalTwo.getTimestampFrom()));
        assertEquals(0, intervalThree.getTimestampTo().compareTo(intervalOne.getTimestampTo()));

    }

}
