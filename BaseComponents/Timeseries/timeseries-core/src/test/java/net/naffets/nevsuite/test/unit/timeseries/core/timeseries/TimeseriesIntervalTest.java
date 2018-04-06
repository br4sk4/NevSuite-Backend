package net.naffets.nevsuite.test.unit.timeseries.core.timeseries;

import net.naffets.nevsuite.backend.timeseries.core.timeseries.TimeseriesInterval;
import net.naffets.nevsuite.backend.timeseries.core.timeseries.TimeseriesPeriod;
import org.junit.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * @author br4sk4
 * created on 22.03.2016
 */
public class TimeseriesIntervalTest {

    @Test
    public void testGetIntervalSetDayRegularWithQuarterHourPeriodAtCET() {
        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        assertEquals(96, interval.getPeriodicIntervalSet(TimeseriesPeriod.MIN15).size());
    }

    @Test
    public void testGetIntervalSetDayRegularWithQuarterHourPeriodAtUTC() {
        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("UTC"));

        assertEquals(96, interval.getPeriodicIntervalSet(TimeseriesPeriod.MIN15).size());
    }

    @Test
    public void testGetIntervalSetDayMissingHourWithQuarterHourPeriodAtCET() {
        Instant timestampFrom = ZonedDateTime.of(2016, 3, 27, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 3, 28, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        assertEquals(92, interval.getPeriodicIntervalSet(TimeseriesPeriod.MIN15).size());
    }

    @Test
    public void testGetIntervalSetDayMissingHourWithQuarterHourPeriodAtUTC() {
        Instant timestampFrom = ZonedDateTime.of(2016, 3, 27, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 3, 28, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("UTC"));

        assertEquals(96, interval.getPeriodicIntervalSet(TimeseriesPeriod.MIN15).size());
    }

    @Test
    public void testGetIntervalSetDayAdditionalHourWithQuarterHourPeriodAtCET() {
        Instant timestampFrom = ZonedDateTime.of(2016, 10, 30, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 10, 31, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        assertEquals(100, interval.getPeriodicIntervalSet(TimeseriesPeriod.MIN15).size());
    }

    @Test
    public void testGetIntervalSetDayAdditionalWithQuarterHourPeriodHourAtUTC() {
        Instant timestampFrom = ZonedDateTime.of(2016, 10, 30, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 10, 31, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("UTC"));

        assertEquals(96, interval.getPeriodicIntervalSet(TimeseriesPeriod.MIN15).size());
    }

    @Test
    public void testGetIntervalSetMonth28DaysWithDayPeriod() {
        Instant timestampFrom = ZonedDateTime.of(2015, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2015, 3, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        assertEquals(28, interval.getPeriodicIntervalSet(TimeseriesPeriod.DAY1).size());
    }

    @Test
    public void testGetIntervalSetMonth29DaysWithDayPeriod() {
        Instant timestampFrom = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 3, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        assertEquals(29, interval.getPeriodicIntervalSet(TimeseriesPeriod.DAY1).size());
    }

    @Test
    public void testGetIntervalSetMonth30DaysWithDayPeriod() {
        Instant timestampFrom = ZonedDateTime.of(2016, 4, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 5, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        assertEquals(30, interval.getPeriodicIntervalSet(TimeseriesPeriod.DAY1).size());
    }

    @Test
    public void testGetIntervalSetMonth31DaysWithDayPeriod() {
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

    @Test
    public void testPeriodicIntervalIntervalSetSEC1() {
        Instant timestampFrom, timestampTo;

        timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2016, 1, 1, 0, 1, 0, 0, ZoneId.of("CET")).toInstant();
        Set<Instant> periodicIntervalSet = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET")).getPeriodicIntervalSet(TimeseriesPeriod.SEC1);

        assertEquals(60, periodicIntervalSet.size());

        periodicIntervalSet.stream()
                .sorted(Instant::compareTo)
                .findFirst()
                .ifPresent(timestamp -> assertEquals(ZonedDateTime.of(2016, 1, 1, 0, 0, 1, 0, ZoneId.of("CET")).toInstant(), timestamp));

        periodicIntervalSet.stream()
                .filter(timestamp -> timestamp.equals(timestampTo))
                .findFirst()
                .ifPresent(timestamp -> assertEquals(ZonedDateTime.of(2016, 1, 1, 0, 1, 0, 0, ZoneId.of("CET")).toInstant(), timestamp));
    }

    @Test
    public void testPeriodicIntervalIntervalSetMIN15() {
        Instant timestampFrom, timestampTo;

        timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Set<Instant> periodicIntervalSet = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET")).getPeriodicIntervalSet(TimeseriesPeriod.MIN15);

        assertEquals(96, periodicIntervalSet.size());

        periodicIntervalSet.stream()
                .sorted(Instant::compareTo)
                .findFirst()
                .ifPresent(timestamp -> assertEquals(ZonedDateTime.of(2016, 1, 1, 0, 15, 0, 0, ZoneId.of("CET")).toInstant(), timestamp));

        periodicIntervalSet.stream()
                .filter(timestamp -> timestamp.equals(timestampTo))
                .findFirst()
                .ifPresent(timestamp -> assertEquals(ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant(), timestamp));
    }

    @Test
    public void testPeriodicIntervalIntervalSetHOUR1() {
        Instant timestampFrom, timestampTo;

        timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Set<Instant> periodicIntervalSet = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET")).getPeriodicIntervalSet(TimeseriesPeriod.HOUR1);

        assertEquals(24, periodicIntervalSet.size());

        periodicIntervalSet.stream()
                .sorted(Instant::compareTo)
                .findFirst()
                .ifPresent(timestamp -> assertEquals(ZonedDateTime.of(2016, 1, 1, 1, 0, 0, 0, ZoneId.of("CET")).toInstant(), timestamp));

        periodicIntervalSet.stream()
                .filter(timestamp -> timestamp.equals(timestampTo))
                .findFirst()
                .ifPresent(timestamp -> assertEquals(ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant(), timestamp));
    }

    @Test
    public void testPeriodicIntervalIntervalSetDAY1() {
        Instant timestampFrom, timestampTo;

        timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Set<Instant> periodicIntervalSet = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET")).getPeriodicIntervalSet(TimeseriesPeriod.DAY1);

        assertEquals(31, periodicIntervalSet.size());

        periodicIntervalSet.stream()
                .sorted(Instant::compareTo)
                .findFirst()
                .ifPresent(timestamp -> assertEquals(ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant(), timestamp));

        periodicIntervalSet.stream()
                .filter(timestamp -> timestamp.equals(timestampTo))
                .findFirst()
                .ifPresent(timestamp -> assertEquals(ZonedDateTime.of(2016, 1, 31, 0, 0, 0, 0, ZoneId.of("CET")).toInstant(), timestamp));
    }

    @Test
    public void testPeriodicIntervalIntervalSetMONTH1() {
        Instant timestampFrom, timestampTo;

        timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2017, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Set<Instant> periodicIntervalSet = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET")).getPeriodicIntervalSet(TimeseriesPeriod.MONTH1);

        assertEquals(12, periodicIntervalSet.size());

        periodicIntervalSet.stream()
                .sorted(Instant::compareTo)
                .findFirst()
                .ifPresent(timestamp -> assertEquals(ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant(), timestamp));

        periodicIntervalSet.stream()
                .filter(timestamp -> timestamp.equals(timestampTo))
                .findFirst()
                .ifPresent(timestamp -> assertEquals(ZonedDateTime.of(2016, 12, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant(), timestamp));
    }

    @Test
    public void testPeriodicIntervalIntervalSetYEAR1() {
        Instant timestampFrom, timestampTo;

        timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2017, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Set<Instant> periodicIntervalSet = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET")).getPeriodicIntervalSet(TimeseriesPeriod.YEAR1);

        assertEquals(1, periodicIntervalSet.size());

        periodicIntervalSet.stream()
                .sorted(Instant::compareTo)
                .findFirst()
                .ifPresent(timestamp -> assertEquals(ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant(), timestamp));

        periodicIntervalSet.stream()
                .filter(timestamp -> timestamp.equals(timestampTo))
                .findFirst()
                .ifPresent(timestamp -> assertEquals(ZonedDateTime.of(2016, 12, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant(), timestamp));
    }

}
