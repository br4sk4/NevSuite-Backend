package net.naffets.nevsuite.test.unit.timeseries.core.timeseries;

import net.naffets.nevsuite.backend.timeseries.core.timeseries.TimeseriesPeriod;
import org.junit.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.Assert.assertEquals;

/**
 * @author br4sk4
 * created on 28.03.2016
 */
public class TimeseriesPeriodTest {

    private ZonedDateTime timestampRegular = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET"));
    private ZonedDateTime timestampMissingHour = ZonedDateTime.of(2016, 3, 27, 0, 0, 0, 0, ZoneId.of("CET"));
    private ZonedDateTime timestampAdditionalHour = ZonedDateTime.of(2016, 10, 30, 0, 0, 0, 0, ZoneId.of("CET"));

    @Test
    public void testCountSecondsForPeriodSEC1() {
        assertEquals(new Long(1), TimeseriesPeriod.SEC1.toSeconds());
    }

    @Test
    public void testCountSecondsForPeriodSEC15() {
        assertEquals(new Long(15), TimeseriesPeriod.SEC15.toSeconds());
    }

    @Test
    public void testCountSecondsForPeriodSEC30() {
        assertEquals(new Long(30), TimeseriesPeriod.SEC30.toSeconds());
    }

    @Test
    public void testCountSecondsForPeriodMIN1() {
        assertEquals(new Long(60), TimeseriesPeriod.MIN1.toSeconds());
    }

    @Test
    public void testCountSecondsForPeriodMIN15() {
        assertEquals(new Long(900), TimeseriesPeriod.MIN15.toSeconds());
    }

    @Test
    public void testCountSecondsForPeriodMIN30() {
        assertEquals(new Long(1800), TimeseriesPeriod.MIN30.toSeconds());
    }

    @Test
    public void testCountSecondsForPeriodHOUR1() {
        assertEquals(new Long(3600), TimeseriesPeriod.HOUR1.toSeconds());
    }

    @Test
    public void testCountSecondsForPeriodHOUR6Regular() {
        assertEquals(new Long(21600), TimeseriesPeriod.HOUR6.toSeconds(timestampRegular));
    }

    @Test
    public void testCountSecondsForPeriodHOUR6MissingHour() {
        assertEquals(new Long(18000), TimeseriesPeriod.HOUR6.toSeconds(timestampMissingHour));
    }

    @Test
    public void testCountSecondsForPeriodHOUR6AdditionalHour() {
        assertEquals(new Long(25200), TimeseriesPeriod.HOUR6.toSeconds(timestampAdditionalHour));
    }

    @Test
    public void testCountSecondsForPeriodHOUR12Regular() {
        assertEquals(new Long(43200), TimeseriesPeriod.HOUR12.toSeconds(timestampRegular));
    }

    @Test
    public void testCountSecondsForPeriodHOUR12MissingHour() {
        assertEquals(new Long(39600), TimeseriesPeriod.HOUR12.toSeconds(timestampMissingHour));
    }

    @Test
    public void testCountSecondsForPeriodHOUR12AdditionalHour() {
        assertEquals(new Long(46800), TimeseriesPeriod.HOUR12.toSeconds(timestampAdditionalHour));
    }

    @Test
    public void testCountSecondsForPeriodDAY1Regular() {
        assertEquals(new Long(86400), TimeseriesPeriod.DAY1.toSeconds(timestampRegular));
    }

    @Test
    public void testCountSecondsForPeriodDAY1MissingHour() {
        assertEquals(new Long(82800), TimeseriesPeriod.DAY1.toSeconds(timestampMissingHour));
    }

    @Test
    public void testCountSecondsForPeriodDAY1AdditionalHour() {
        assertEquals(new Long(90000), TimeseriesPeriod.DAY1.toSeconds(timestampAdditionalHour));
    }

}
