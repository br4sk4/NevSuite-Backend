package net.naffets.nevsuite.test.unit.timeseries.core.timeseries;

import net.naffets.nevsuite.backend.timeseries.core.timeseries.TimeseriesAlignment;
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
    public void testGetIntervalSetDayRegularAtCET() {
        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"), TimeseriesAlignment.RIGHT);

        assertEquals(96, interval.getPeriodicIntervalSet(TimeseriesPeriod.MIN15).size());
    }

    @Test
    public void testGetIntervalSetDayRegularAtUTC() {
        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("UTC"), TimeseriesAlignment.RIGHT);

        assertEquals(96, interval.getPeriodicIntervalSet(TimeseriesPeriod.MIN15).size());
    }

    @Test
    public void testGetIntervalSetDayMissingHourAtCET() {
        Instant timestampFrom = ZonedDateTime.of(2016, 3, 27, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 3, 28, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"), TimeseriesAlignment.RIGHT);

        assertEquals(92, interval.getPeriodicIntervalSet(TimeseriesPeriod.MIN15).size());
    }

    @Test
    public void testGetIntervalSetDayMissingHourAtUTC() {
        Instant timestampFrom = ZonedDateTime.of(2016, 3, 27, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 3, 28, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("UTC"), TimeseriesAlignment.RIGHT);

        assertEquals(96, interval.getPeriodicIntervalSet(TimeseriesPeriod.MIN15).size());
    }

    @Test
    public void testGetIntervalSetDayAdditionalHourAtCET() {
        Instant timestampFrom = ZonedDateTime.of(2016, 10, 30, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 10, 31, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"), TimeseriesAlignment.RIGHT);

        assertEquals(100, interval.getPeriodicIntervalSet(TimeseriesPeriod.MIN15).size());
    }

    @Test
    public void testGetIntervalSetDayAdditionalHourAtUTC() {
        Instant timestampFrom = ZonedDateTime.of(2016, 10, 30, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 10, 31, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("UTC"), TimeseriesAlignment.RIGHT);

        assertEquals(96, interval.getPeriodicIntervalSet(TimeseriesPeriod.MIN15).size());
    }

    @Test
    public void testGetIntervalSetMonth28Days() {
        Instant timestampFrom = ZonedDateTime.of(2015, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2015, 3, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"), TimeseriesAlignment.RIGHT);

        assertEquals(28, interval.getPeriodicIntervalSet(TimeseriesPeriod.DAY1).size());
    }

    @Test
    public void testGetIntervalSetMonth29Days() {
        Instant timestampFrom = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 3, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"), TimeseriesAlignment.RIGHT);

        assertEquals(29, interval.getPeriodicIntervalSet(TimeseriesPeriod.DAY1).size());
    }

    @Test
    public void testGetIntervalSetMonth30Days() {
        Instant timestampFrom = ZonedDateTime.of(2016, 4, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 5, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"), TimeseriesAlignment.RIGHT);

        assertEquals(30, interval.getPeriodicIntervalSet(TimeseriesPeriod.DAY1).size());
    }

    @Test
    public void testGetIntervalSetMonth31Days() {
        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"), TimeseriesAlignment.RIGHT);

        assertEquals(31, interval.getPeriodicIntervalSet(TimeseriesPeriod.DAY1).size());
    }

    @Test
    public void testOverlapsWholeWithEquals() {
        Instant timestampFrom, timestampTo;

        timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval intervalOne = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"), TimeseriesAlignment.RIGHT);

        timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval intervalTwo = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"), TimeseriesAlignment.RIGHT);

        assertTrue(intervalOne.overlaps(intervalTwo));
        assertTrue(intervalTwo.overlaps(intervalOne));
    }

    @Test
    public void testOverlapsWhole() {
        Instant timestampFrom, timestampTo;

        timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval intervalOne = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"), TimeseriesAlignment.RIGHT);

        timestampFrom = ZonedDateTime.of(2015, 12, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2016, 3, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval intervalTwo = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"), TimeseriesAlignment.RIGHT);

        assertTrue(intervalTwo.overlaps(intervalOne));
        assertFalse(intervalOne.overlaps(intervalTwo));
    }

    @Test
    public void testOverlapsLeft() {
        Instant timestampFrom, timestampTo;

        timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval intervalOne = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"), TimeseriesAlignment.RIGHT);

        timestampFrom = ZonedDateTime.of(2015, 12, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2016, 1, 15, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval intervalTwo = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"), TimeseriesAlignment.RIGHT);

        assertTrue(intervalTwo.overlaps(intervalOne));
        assertTrue(intervalOne.overlaps(intervalTwo));
    }

    @Test
    public void testOverlapsRight() {
        Instant timestampFrom, timestampTo;

        timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval intervalOne = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"), TimeseriesAlignment.RIGHT);

        timestampFrom = ZonedDateTime.of(2016, 1, 15, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2016, 3, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval intervalTwo = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"), TimeseriesAlignment.RIGHT);

        assertTrue(intervalTwo.overlaps(intervalOne));
        assertTrue(intervalOne.overlaps(intervalTwo));
    }

    @Test
    public void testNoOverlaps() {
        Instant timestampFrom, timestampTo;

        timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval intervalOne = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"), TimeseriesAlignment.RIGHT);

        timestampFrom = ZonedDateTime.of(2015, 12, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval intervalTwo = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"), TimeseriesAlignment.RIGHT);

        assertFalse(intervalTwo.overlaps(intervalOne));
        assertFalse(intervalOne.overlaps(intervalTwo));
    }

    @Test
    public void testJoinOverlapsWholeWithEquals() {
        Instant timestampFrom, timestampTo;

        timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval intervalOne = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"), TimeseriesAlignment.RIGHT);

        timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval intervalTwo = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"), TimeseriesAlignment.RIGHT);

        TimeseriesInterval intervalThree = intervalOne.join(intervalTwo);

        assertEquals(0, intervalThree.getTimestampFrom().compareTo(intervalOne.getTimestampFrom()));
        assertEquals(0, intervalThree.getTimestampTo().compareTo(intervalOne.getTimestampTo()));
    }

    @Test
    public void testJoinOverlapsWhole() {
        Instant timestampFrom, timestampTo;

        timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval intervalOne = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"), TimeseriesAlignment.RIGHT);

        timestampFrom = ZonedDateTime.of(2015, 12, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2016, 3, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval intervalTwo = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"), TimeseriesAlignment.RIGHT);

        TimeseriesInterval intervalThree = intervalOne.join(intervalTwo);

        assertEquals(0, intervalThree.getTimestampFrom().compareTo(intervalTwo.getTimestampFrom()));
        assertEquals(0, intervalThree.getTimestampTo().compareTo(intervalTwo.getTimestampTo()));
    }

    @Test
    public void testJoinOverlapsLeft() {
        Instant timestampFrom, timestampTo;

        timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval intervalOne = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"), TimeseriesAlignment.RIGHT);

        timestampFrom = ZonedDateTime.of(2015, 12, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2016, 1, 15, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval intervalTwo = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"), TimeseriesAlignment.RIGHT);

        TimeseriesInterval intervalThree = intervalOne.join(intervalTwo);

        assertEquals(0, intervalThree.getTimestampFrom().compareTo(intervalTwo.getTimestampFrom()));
        assertEquals(0, intervalThree.getTimestampTo().compareTo(intervalOne.getTimestampTo()));
    }

    @Test
    public void testJoinOverlapsRight() {
        Instant timestampFrom, timestampTo;

        timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval intervalOne = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"), TimeseriesAlignment.RIGHT);

        timestampFrom = ZonedDateTime.of(2016, 1, 15, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2016, 3, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval intervalTwo = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"), TimeseriesAlignment.RIGHT);

        TimeseriesInterval intervalThree = intervalOne.join(intervalTwo);

        assertEquals(0, intervalThree.getTimestampFrom().compareTo(intervalOne.getTimestampFrom()));
        assertEquals(0, intervalThree.getTimestampTo().compareTo(intervalTwo.getTimestampTo()));
    }

    @Test
    public void testJoinNoOverlaps() {
        Instant timestampFrom, timestampTo;

        timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval intervalOne = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"), TimeseriesAlignment.RIGHT);

        timestampFrom = ZonedDateTime.of(2015, 12, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval intervalTwo = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"), TimeseriesAlignment.RIGHT);

        TimeseriesInterval intervalThree = intervalOne.join(intervalTwo);

        assertEquals(0, intervalThree.getTimestampFrom().compareTo(intervalTwo.getTimestampFrom()));
        assertEquals(0, intervalThree.getTimestampTo().compareTo(intervalOne.getTimestampTo()));
    }

    @Test
    public void testRightAlignedPeriodicIntervalIntervalSetSEC1() {
        Instant timestampFrom, timestampTo;

        timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2016, 1, 1, 0, 1, 0, 0, ZoneId.of("CET")).toInstant();
        Set<Instant> periodicIntervalSet = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"), TimeseriesAlignment.RIGHT).getPeriodicIntervalSet(TimeseriesPeriod.SEC1);

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
    public void testRightAlignedPeriodicIntervalIntervalSetMIN15() {
        Instant timestampFrom, timestampTo;

        timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Set<Instant> periodicIntervalSet = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"), TimeseriesAlignment.RIGHT).getPeriodicIntervalSet(TimeseriesPeriod.MIN15);

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
    public void testRightAlignedPeriodicIntervalIntervalSetHOUR1() {
        Instant timestampFrom, timestampTo;

        timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Set<Instant> periodicIntervalSet = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"), TimeseriesAlignment.RIGHT).getPeriodicIntervalSet(TimeseriesPeriod.HOUR1);

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
    public void testRightAlignedPeriodicIntervalIntervalSetDAY1() {
        Instant timestampFrom, timestampTo;

        timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Set<Instant> periodicIntervalSet = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"), TimeseriesAlignment.RIGHT).getPeriodicIntervalSet(TimeseriesPeriod.DAY1);

        assertEquals(31, periodicIntervalSet.size());

        periodicIntervalSet.stream()
                .sorted(Instant::compareTo)
                .findFirst()
                .ifPresent(timestamp -> assertEquals(ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant(), timestamp));

        periodicIntervalSet.stream()
                .filter(timestamp -> timestamp.equals(timestampTo))
                .findFirst()
                .ifPresent(timestamp -> assertEquals(ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant(), timestamp));
    }

    @Test
    public void testLeftAlignedPeriodicIntervalIntervalSetSEC1() {
        Instant timestampFrom, timestampTo;

        timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2016, 1, 1, 0, 1, 0, 0, ZoneId.of("CET")).toInstant();
        Set<Instant> periodicIntervalSet = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"), TimeseriesAlignment.LEFT).getPeriodicIntervalSet(TimeseriesPeriod.SEC1);

        assertEquals(60, periodicIntervalSet.size());

        periodicIntervalSet.stream()
                .sorted(Instant::compareTo)
                .findFirst()
                .ifPresent(timestamp -> assertEquals(ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant(), timestamp));

        periodicIntervalSet.stream()
                .filter(timestamp -> timestamp.equals(timestampTo))
                .findFirst()
                .ifPresent(timestamp -> assertEquals(ZonedDateTime.of(2016, 1, 1, 0, 0, 59, 0, ZoneId.of("CET")).toInstant(), timestamp));
    }

    @Test
    public void testLeftAlignedPeriodicIntervalIntervalSetMIN15() {
        Instant timestampFrom, timestampTo;

        timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Set<Instant> periodicIntervalSet = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"), TimeseriesAlignment.LEFT).getPeriodicIntervalSet(TimeseriesPeriod.MIN15);

        assertEquals(96, periodicIntervalSet.size());

        periodicIntervalSet.stream()
                .sorted(Instant::compareTo)
                .findFirst()
                .ifPresent(timestamp -> assertEquals(ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant(), timestamp));

        periodicIntervalSet.stream()
                .filter(timestamp -> timestamp.equals(timestampTo))
                .findFirst()
                .ifPresent(timestamp -> assertEquals(ZonedDateTime.of(2016, 1, 1, 23, 45, 0, 0, ZoneId.of("CET")).toInstant(), timestamp));
    }

    @Test
    public void testLeftAlignedPeriodicIntervalIntervalSetHOUR1() {
        Instant timestampFrom, timestampTo;

        timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Set<Instant> periodicIntervalSet = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"), TimeseriesAlignment.LEFT).getPeriodicIntervalSet(TimeseriesPeriod.HOUR1);

        assertEquals(24, periodicIntervalSet.size());

        periodicIntervalSet.stream()
                .sorted(Instant::compareTo)
                .findFirst()
                .ifPresent(timestamp -> assertEquals(ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant(), timestamp));

        periodicIntervalSet.stream()
                .filter(timestamp -> timestamp.equals(timestampTo))
                .findFirst()
                .ifPresent(timestamp -> assertEquals(ZonedDateTime.of(2016, 1, 1, 23, 0, 0, 0, ZoneId.of("CET")).toInstant(), timestamp));
    }

    @Test
    public void testLeftAlignedPeriodicIntervalIntervalSetDAY1() {
        Instant timestampFrom, timestampTo;

        timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Set<Instant> periodicIntervalSet = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"), TimeseriesAlignment.LEFT).getPeriodicIntervalSet(TimeseriesPeriod.DAY1);

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
    public void testExactPeriodicIntervalIntervalSetSEC1() {
        Instant timestampFrom, timestampTo;

        timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2016, 1, 1, 0, 1, 0, 0, ZoneId.of("CET")).toInstant();
        Set<Instant> periodicIntervalSet = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"), TimeseriesAlignment.EXACT).getPeriodicIntervalSet(TimeseriesPeriod.SEC1);

        assertEquals(61, periodicIntervalSet.size());

        periodicIntervalSet.stream()
                .sorted(Instant::compareTo)
                .findFirst()
                .ifPresent(timestamp -> assertEquals(ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant(), timestamp));

        periodicIntervalSet.stream()
                .filter(timestamp -> timestamp.equals(timestampTo))
                .findFirst()
                .ifPresent(timestamp -> assertEquals(ZonedDateTime.of(2016, 1, 1, 0, 1, 0, 0, ZoneId.of("CET")).toInstant(), timestamp));
    }

    @Test
    public void testExactPeriodicIntervalIntervalSetMIN15() {
        Instant timestampFrom, timestampTo;

        timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Set<Instant> periodicIntervalSet = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"), TimeseriesAlignment.EXACT).getPeriodicIntervalSet(TimeseriesPeriod.MIN15);

        assertEquals(97, periodicIntervalSet.size());

        periodicIntervalSet.stream()
                .sorted(Instant::compareTo)
                .findFirst()
                .ifPresent(timestamp -> assertEquals(ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant(), timestamp));

        periodicIntervalSet.stream()
                .filter(timestamp -> timestamp.equals(timestampTo))
                .findFirst()
                .ifPresent(timestamp -> assertEquals(ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant(), timestamp));
    }

    @Test
    public void testExactPeriodicIntervalIntervalSetHOUR1() {
        Instant timestampFrom, timestampTo;

        timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Set<Instant> periodicIntervalSet = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"), TimeseriesAlignment.EXACT).getPeriodicIntervalSet(TimeseriesPeriod.HOUR1);

        assertEquals(25, periodicIntervalSet.size());

        periodicIntervalSet.stream()
                .sorted(Instant::compareTo)
                .findFirst()
                .ifPresent(timestamp -> assertEquals(ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant(), timestamp));

        periodicIntervalSet.stream()
                .filter(timestamp -> timestamp.equals(timestampTo))
                .findFirst()
                .ifPresent(timestamp -> assertEquals(ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant(), timestamp));
    }

    @Test
    public void testExactPeriodicIntervalIntervalSetDAY1() {
        Instant timestampFrom, timestampTo;

        timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Set<Instant> periodicIntervalSet = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"), TimeseriesAlignment.EXACT).getPeriodicIntervalSet(TimeseriesPeriod.DAY1);

        assertEquals(32, periodicIntervalSet.size());

        periodicIntervalSet.stream()
                .sorted(Instant::compareTo)
                .findFirst()
                .ifPresent(timestamp -> assertEquals(ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant(), timestamp));

        periodicIntervalSet.stream()
                .filter(timestamp -> timestamp.equals(timestampTo))
                .findFirst()
                .ifPresent(timestamp -> assertEquals(ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant(), timestamp));
    }

}
