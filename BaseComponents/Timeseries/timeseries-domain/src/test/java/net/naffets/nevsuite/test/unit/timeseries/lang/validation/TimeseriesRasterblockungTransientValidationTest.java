package net.naffets.nevsuite.test.unit.timeseries.lang.validation;

import junit.framework.TestCase;
import net.naffets.nevsuite.backend.timeseries.domain.basictype.*;
import net.naffets.nevsuite.backend.timeseries.domain.entity.TimeseriesHead;
import net.naffets.nevsuite.backend.timeseries.domain.factory.TimeseriesHeadFactory;
import net.naffets.nevsuite.backend.timeseries.lang.exception.InvalidTimeseriesConfigException;
import org.junit.Ignore;

/**
 * @author br4sk4
 * created on 03.07.2015
 */
@Ignore
public class TimeseriesRasterblockungTransientValidationTest extends TestCase {

    private TimeseriesHeadFactory factory;

    public void setUp() {
        factory = new TimeseriesHeadFactory();
    }

    public void testRasterBlockungMinuteSec1() {

        try {

            factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.SOURCE,
                    TimeseriesDerivationType.NONE,
                    TimeseriesRastertype.SEC1,
                    TimeseriesBlocksize.MINUTE,
                    TimeseriesPersistence.TRANSIENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            fail("Transient TimeseriesHead with RasterBlockung: " + e.getTimeseries().getBlocksize().toString() + ":" + e.getTimeseries().getRastertype().toString() + " is valid and cannot be created!");
        }

    }

    public void testRasterBlockungMinuteSec15() {

        try {

            factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.SOURCE,
                    TimeseriesDerivationType.NONE,
                    TimeseriesRastertype.SEC15,
                    TimeseriesBlocksize.MINUTE,
                    TimeseriesPersistence.TRANSIENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            fail("Transient TimeseriesHead with RasterBlockung: " + e.getTimeseries().getBlocksize().toString() + ":" + e.getTimeseries().getRastertype().toString() + " is valid and cannot be created!");
        }

    }

    public void testRasterBlockungMinuteSec30() {

        try {

            factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.SOURCE,
                    TimeseriesDerivationType.NONE,
                    TimeseriesRastertype.SEC30,
                    TimeseriesBlocksize.MINUTE,
                    TimeseriesPersistence.TRANSIENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            fail("Transient TimeseriesHead with RasterBlockung: " + e.getTimeseries().getBlocksize().toString() + ":" + e.getTimeseries().getRastertype().toString() + " is valid and cannot be created!");
        }

    }

    public void testRasterBlockungMinuteMin1() {

        try {

            factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.SOURCE,
                    TimeseriesDerivationType.NONE,
                    TimeseriesRastertype.MIN1,
                    TimeseriesBlocksize.MINUTE,
                    TimeseriesPersistence.TRANSIENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            fail("Transient TimeseriesHead with RasterBlockung: " + e.getTimeseries().getBlocksize().toString() + ":" + e.getTimeseries().getRastertype().toString() + " is valid and cannot be created!");
        }

    }

    public void testRasterBlockungMinuteMin15() {

        TimeseriesHead ts;

        try {

            ts = factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.SOURCE,
                    TimeseriesDerivationType.NONE,
                    TimeseriesRastertype.MIN15,
                    TimeseriesBlocksize.MINUTE,
                    TimeseriesPersistence.TRANSIENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            return;
        }

        fail("Transient TimeseriesHead with RasterBlockung: " + ts.getBlocksize().toString() + ":" + ts.getRastertype().toString() + " is invalid and may be created!");

    }

    public void testRasterBlockungMinuteMin30() {

        TimeseriesHead ts;

        try {

            ts = factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.SOURCE,
                    TimeseriesDerivationType.NONE,
                    TimeseriesRastertype.MIN30,
                    TimeseriesBlocksize.MINUTE,
                    TimeseriesPersistence.TRANSIENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            return;
        }

        fail("Transient TimeseriesHead with RasterBlockung: " + ts.getBlocksize().toString() + ":" + ts.getRastertype().toString() + " is invalid and may be created!");

    }

    public void testRasterBlockungMinuteHour1() {

        TimeseriesHead ts;

        try {

            ts = factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.SOURCE,
                    TimeseriesDerivationType.NONE,
                    TimeseriesRastertype.HOUR1,
                    TimeseriesBlocksize.MINUTE,
                    TimeseriesPersistence.TRANSIENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            return;
        }

        fail("Transient TimeseriesHead with RasterBlockung: " + ts.getBlocksize().toString() + ":" + ts.getRastertype().toString() + " is invalid and may be created!");

    }

    public void testRasterBlockungMinuteHour6() {

        TimeseriesHead ts;

        try {

            ts = factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.SOURCE,
                    TimeseriesDerivationType.NONE,
                    TimeseriesRastertype.HOUR6,
                    TimeseriesBlocksize.MINUTE,
                    TimeseriesPersistence.TRANSIENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            return;
        }

        fail("Transient TimeseriesHead with RasterBlockung: " + ts.getBlocksize().toString() + ":" + ts.getRastertype().toString() + " is invalid and may be created!");

    }

    public void testRasterBlockungMinuteHour12() {

        TimeseriesHead ts;

        try {

            ts = factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.SOURCE,
                    TimeseriesDerivationType.NONE,
                    TimeseriesRastertype.HOUR12,
                    TimeseriesBlocksize.MINUTE,
                    TimeseriesPersistence.TRANSIENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            return;
        }

        fail("Transient TimeseriesHead with RasterBlockung: " + ts.getBlocksize().toString() + ":" + ts.getRastertype().toString() + " is invalid and may be created!");

    }

    public void testRasterBlockungMinuteDay1() {

        TimeseriesHead ts;

        try {

            ts = factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.SOURCE,
                    TimeseriesDerivationType.NONE,
                    TimeseriesRastertype.DAY1,
                    TimeseriesBlocksize.MINUTE,
                    TimeseriesPersistence.TRANSIENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            return;
        }

        fail("Transient TimeseriesHead with RasterBlockung: " + ts.getBlocksize().toString() + ":" + ts.getRastertype().toString() + " is invalid and may be created!");

    }

    public void testRasterBlockungHourSec1() {

        try {

            factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.SOURCE,
                    TimeseriesDerivationType.NONE,
                    TimeseriesRastertype.SEC1,
                    TimeseriesBlocksize.HOUR,
                    TimeseriesPersistence.TRANSIENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            fail("Transient TimeseriesHead with RasterBlockung: " + e.getTimeseries().getBlocksize().toString() + ":" + e.getTimeseries().getRastertype().toString() + " is valid and cannot be created!");
        }

    }

    public void testRasterBlockungHourSec15() {

        try {

            factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.SOURCE,
                    TimeseriesDerivationType.NONE,
                    TimeseriesRastertype.SEC15,
                    TimeseriesBlocksize.HOUR,
                    TimeseriesPersistence.TRANSIENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            fail("Transient TimeseriesHead with RasterBlockung: " + e.getTimeseries().getBlocksize().toString() + ":" + e.getTimeseries().getRastertype().toString() + " is valid and cannot be created!");
        }

    }

    public void testRasterBlockungHourSec30() {

        try {

            factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.SOURCE,
                    TimeseriesDerivationType.NONE,
                    TimeseriesRastertype.SEC30,
                    TimeseriesBlocksize.HOUR,
                    TimeseriesPersistence.TRANSIENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            fail("Transient TimeseriesHead with RasterBlockung: " + e.getTimeseries().getBlocksize().toString() + ":" + e.getTimeseries().getRastertype().toString() + " is valid and cannot be created!");
        }

    }

    public void testRasterBlockungHourMin1() {

        try {

            factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.SOURCE,
                    TimeseriesDerivationType.NONE,
                    TimeseriesRastertype.MIN1,
                    TimeseriesBlocksize.HOUR,
                    TimeseriesPersistence.TRANSIENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            fail("Transient TimeseriesHead with RasterBlockung: " + e.getTimeseries().getBlocksize().toString() + ":" + e.getTimeseries().getRastertype().toString() + " is valid and cannot be created!");
        }

    }

    public void testRasterBlockungHourMin15() {

        try {

            factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.SOURCE,
                    TimeseriesDerivationType.NONE,
                    TimeseriesRastertype.MIN15,
                    TimeseriesBlocksize.HOUR,
                    TimeseriesPersistence.TRANSIENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            fail("Transient TimeseriesHead with RasterBlockung: " + e.getTimeseries().getBlocksize().toString() + ":" + e.getTimeseries().getRastertype().toString() + " is valid and cannot be created!");
        }

    }

    public void testRasterBlockungHourMin30() {

        try {

            factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.SOURCE,
                    TimeseriesDerivationType.NONE,
                    TimeseriesRastertype.MIN30,
                    TimeseriesBlocksize.HOUR,
                    TimeseriesPersistence.TRANSIENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            fail("Transient TimeseriesHead with RasterBlockung: " + e.getTimeseries().getBlocksize().toString() + ":" + e.getTimeseries().getRastertype().toString() + " is valid and cannot be created!");
        }

    }

    public void testRasterBlockungHourHour1() {

        try {

            factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.SOURCE,
                    TimeseriesDerivationType.NONE,
                    TimeseriesRastertype.HOUR1,
                    TimeseriesBlocksize.HOUR,
                    TimeseriesPersistence.TRANSIENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            fail("Transient TimeseriesHead with RasterBlockung: " + e.getTimeseries().getBlocksize().toString() + ":" + e.getTimeseries().getRastertype().toString() + " is valid and cannot be created!");
        }

    }

    public void testRasterBlockungHourHour6() {

        TimeseriesHead ts;

        try {

            ts = factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.SOURCE,
                    TimeseriesDerivationType.NONE,
                    TimeseriesRastertype.HOUR6,
                    TimeseriesBlocksize.HOUR,
                    TimeseriesPersistence.TRANSIENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            return;
        }

        fail("Transient TimeseriesHead with RasterBlockung: " + ts.getBlocksize().toString() + ":" + ts.getRastertype().toString() + " is invalid and may be created!");

    }

    public void testRasterBlockungHourHour12() {

        TimeseriesHead ts;

        try {

            ts = factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.SOURCE,
                    TimeseriesDerivationType.NONE,
                    TimeseriesRastertype.HOUR12,
                    TimeseriesBlocksize.HOUR,
                    TimeseriesPersistence.TRANSIENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            return;
        }

        fail("Transient TimeseriesHead with RasterBlockung: " + ts.getBlocksize().toString() + ":" + ts.getRastertype().toString() + " is invalid and may be created!");

    }

    public void testRasterBlockungHourDay1() {

        TimeseriesHead ts;

        try {

            ts = factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.SOURCE,
                    TimeseriesDerivationType.NONE,
                    TimeseriesRastertype.DAY1,
                    TimeseriesBlocksize.HOUR,
                    TimeseriesPersistence.TRANSIENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            return;
        }

        fail("Transient TimeseriesHead with RasterBlockung: " + ts.getBlocksize().toString() + ":" + ts.getRastertype().toString() + " is invalid and may be created!");

    }

    public void testRasterBlockungDaySec1() {

        try {

            factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.SOURCE,
                    TimeseriesDerivationType.NONE,
                    TimeseriesRastertype.SEC1,
                    TimeseriesBlocksize.DAY,
                    TimeseriesPersistence.TRANSIENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            fail("Transient TimeseriesHead with RasterBlockung: " + e.getTimeseries().getBlocksize().toString() + ":" + e.getTimeseries().getRastertype().toString() + " is valid and cannot be created!");
        }

    }

    public void testRasterBlockungDaySec15() {

        try {

            factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.SOURCE,
                    TimeseriesDerivationType.NONE,
                    TimeseriesRastertype.SEC15,
                    TimeseriesBlocksize.DAY,
                    TimeseriesPersistence.TRANSIENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            fail("Transient TimeseriesHead with RasterBlockung: " + e.getTimeseries().getBlocksize().toString() + ":" + e.getTimeseries().getRastertype().toString() + " is valid and cannot be created!");
        }

    }

    public void testRasterBlockungDaySec30() {

        try {

            factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.SOURCE,
                    TimeseriesDerivationType.NONE,
                    TimeseriesRastertype.SEC30,
                    TimeseriesBlocksize.DAY,
                    TimeseriesPersistence.TRANSIENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            fail("Transient TimeseriesHead with RasterBlockung: " + e.getTimeseries().getBlocksize().toString() + ":" + e.getTimeseries().getRastertype().toString() + " is valid and cannot be created!");
        }

    }

    public void testRasterBlockungDayMin1() {

        try {

            factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.SOURCE,
                    TimeseriesDerivationType.NONE,
                    TimeseriesRastertype.MIN1,
                    TimeseriesBlocksize.DAY,
                    TimeseriesPersistence.TRANSIENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            fail("Transient TimeseriesHead with RasterBlockung: " + e.getTimeseries().getBlocksize().toString() + ":" + e.getTimeseries().getRastertype().toString() + " is valid and cannot be created!");
        }

    }

    public void testRasterBlockungDayMin15() {

        try {

            factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.SOURCE,
                    TimeseriesDerivationType.NONE,
                    TimeseriesRastertype.MIN15,
                    TimeseriesBlocksize.DAY,
                    TimeseriesPersistence.TRANSIENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            fail("Transient TimeseriesHead with RasterBlockung: " + e.getTimeseries().getBlocksize().toString() + ":" + e.getTimeseries().getRastertype().toString() + " is valid and cannot be created!");
        }

    }

    public void testRasterBlockungDayMin30() {

        try {

            factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.SOURCE,
                    TimeseriesDerivationType.NONE,
                    TimeseriesRastertype.MIN30,
                    TimeseriesBlocksize.DAY,
                    TimeseriesPersistence.TRANSIENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            fail("Transient TimeseriesHead with RasterBlockung: " + e.getTimeseries().getBlocksize().toString() + ":" + e.getTimeseries().getRastertype().toString() + " is valid and cannot be created!");
        }

    }

    public void testRasterBlockungDayHour1() {

        try {

            factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.SOURCE,
                    TimeseriesDerivationType.NONE,
                    TimeseriesRastertype.HOUR1,
                    TimeseriesBlocksize.DAY,
                    TimeseriesPersistence.TRANSIENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            fail("Transient TimeseriesHead with RasterBlockung: " + e.getTimeseries().getBlocksize().toString() + ":" + e.getTimeseries().getRastertype().toString() + " is valid and cannot be created!");
        }

    }

    public void testRasterBlockungDayHour6() {

        try {

            factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.SOURCE,
                    TimeseriesDerivationType.NONE,
                    TimeseriesRastertype.HOUR6,
                    TimeseriesBlocksize.DAY,
                    TimeseriesPersistence.TRANSIENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            fail("Transient TimeseriesHead with RasterBlockung: " + e.getTimeseries().getBlocksize().toString() + ":" + e.getTimeseries().getRastertype().toString() + " is valid and cannot be created!");
        }

    }

    public void testRasterBlockungDayHour12() {

        try {

            factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.SOURCE,
                    TimeseriesDerivationType.NONE,
                    TimeseriesRastertype.HOUR12,
                    TimeseriesBlocksize.DAY,
                    TimeseriesPersistence.TRANSIENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            fail("Transient TimeseriesHead with RasterBlockung: " + e.getTimeseries().getBlocksize().toString() + ":" + e.getTimeseries().getRastertype().toString() + " is valid and cannot be created!");
        }

    }

    public void testRasterBlockungDayDay1() {

        try {

            factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.SOURCE,
                    TimeseriesDerivationType.NONE,
                    TimeseriesRastertype.DAY1,
                    TimeseriesBlocksize.DAY,
                    TimeseriesPersistence.TRANSIENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            fail("Transient TimeseriesHead with RasterBlockung: " + e.getTimeseries().getBlocksize().toString() + ":" + e.getTimeseries().getRastertype().toString() + " is valid and cannot be created!");
        }

    }

    public void testRasterBlockungMonthSec1() {

        try {

            factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.SOURCE,
                    TimeseriesDerivationType.NONE,
                    TimeseriesRastertype.SEC1,
                    TimeseriesBlocksize.MONTH,
                    TimeseriesPersistence.TRANSIENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            fail("Transient TimeseriesHead with RasterBlockung: " + e.getTimeseries().getBlocksize().toString() + ":" + e.getTimeseries().getRastertype().toString() + " is valid and cannot be created!");
        }

    }

    public void testRasterBlockungMonthSec15() {

        try {

            factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.SOURCE,
                    TimeseriesDerivationType.NONE,
                    TimeseriesRastertype.SEC15,
                    TimeseriesBlocksize.MONTH,
                    TimeseriesPersistence.TRANSIENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            fail("Transient TimeseriesHead with RasterBlockung: " + e.getTimeseries().getBlocksize().toString() + ":" + e.getTimeseries().getRastertype().toString() + " is valid and cannot be created!");
        }

    }

    public void testRasterBlockungMonthSec30() {

        try {

            factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.SOURCE,
                    TimeseriesDerivationType.NONE,
                    TimeseriesRastertype.SEC30,
                    TimeseriesBlocksize.MONTH,
                    TimeseriesPersistence.TRANSIENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            fail("Transient TimeseriesHead with RasterBlockung: " + e.getTimeseries().getBlocksize().toString() + ":" + e.getTimeseries().getRastertype().toString() + " is valid and cannot be created!");
        }

    }

    public void testRasterBlockungMonthMin1() {

        try {

            factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.SOURCE,
                    TimeseriesDerivationType.NONE,
                    TimeseriesRastertype.MIN1,
                    TimeseriesBlocksize.MONTH,
                    TimeseriesPersistence.TRANSIENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            fail("Transient TimeseriesHead with RasterBlockung: " + e.getTimeseries().getBlocksize().toString() + ":" + e.getTimeseries().getRastertype().toString() + " is valid and cannot be created!");
        }

    }

    public void testRasterBlockungMonthMin15() {

        try {

            factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.SOURCE,
                    TimeseriesDerivationType.NONE,
                    TimeseriesRastertype.MIN15,
                    TimeseriesBlocksize.MONTH,
                    TimeseriesPersistence.TRANSIENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            fail("Transient TimeseriesHead with RasterBlockung: " + e.getTimeseries().getBlocksize().toString() + ":" + e.getTimeseries().getRastertype().toString() + " is valid and cannot be created!");
        }

    }

    public void testRasterBlockungMonthMin30() {

        try {

            factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.SOURCE,
                    TimeseriesDerivationType.NONE,
                    TimeseriesRastertype.MIN30,
                    TimeseriesBlocksize.MONTH,
                    TimeseriesPersistence.TRANSIENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            fail("Transient TimeseriesHead with RasterBlockung: " + e.getTimeseries().getBlocksize().toString() + ":" + e.getTimeseries().getRastertype().toString() + " is valid and cannot be created!");
        }

    }

    public void testRasterBlockungMonthHour1() {

        try {

            factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.SOURCE,
                    TimeseriesDerivationType.NONE,
                    TimeseriesRastertype.HOUR1,
                    TimeseriesBlocksize.MONTH,
                    TimeseriesPersistence.TRANSIENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            fail("Transient TimeseriesHead with RasterBlockung: " + e.getTimeseries().getBlocksize().toString() + ":" + e.getTimeseries().getRastertype().toString() + " is valid and cannot be created!");
        }

    }

    public void testRasterBlockungMonthHour6() {

        try {

            factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.SOURCE,
                    TimeseriesDerivationType.NONE,
                    TimeseriesRastertype.HOUR6,
                    TimeseriesBlocksize.MONTH,
                    TimeseriesPersistence.TRANSIENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            fail("Transient TimeseriesHead with RasterBlockung: " + e.getTimeseries().getBlocksize().toString() + ":" + e.getTimeseries().getRastertype().toString() + " is valid and cannot be created!");
        }

    }

    public void testRasterBlockungMonthHour12() {

        try {

            factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.SOURCE,
                    TimeseriesDerivationType.NONE,
                    TimeseriesRastertype.HOUR12,
                    TimeseriesBlocksize.MONTH,
                    TimeseriesPersistence.TRANSIENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            fail("Transient TimeseriesHead with RasterBlockung: " + e.getTimeseries().getBlocksize().toString() + ":" + e.getTimeseries().getRastertype().toString() + " is valid and cannot be created!");
        }

    }

    public void testRasterBlockungMonthDay1() {

        try {

            factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.SOURCE,
                    TimeseriesDerivationType.NONE,
                    TimeseriesRastertype.DAY1,
                    TimeseriesBlocksize.MONTH,
                    TimeseriesPersistence.TRANSIENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            fail("Transient TimeseriesHead with RasterBlockung: " + e.getTimeseries().getBlocksize().toString() + ":" + e.getTimeseries().getRastertype().toString() + " is valid and cannot be created!");
        }

    }

    public void testRasterBlockungYearSec1() {

        try {

            factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.SOURCE,
                    TimeseriesDerivationType.NONE,
                    TimeseriesRastertype.SEC1,
                    TimeseriesBlocksize.YEAR,
                    TimeseriesPersistence.TRANSIENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            fail("Transient TimeseriesHead with RasterBlockung: " + e.getTimeseries().getBlocksize().toString() + ":" + e.getTimeseries().getRastertype().toString() + " is valid and cannot be created!");
        }

    }

    public void testRasterBlockungYearSec15() {

        try {

            factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.SOURCE,
                    TimeseriesDerivationType.NONE,
                    TimeseriesRastertype.SEC15,
                    TimeseriesBlocksize.YEAR,
                    TimeseriesPersistence.TRANSIENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            fail("Transient TimeseriesHead with RasterBlockung: " + e.getTimeseries().getBlocksize().toString() + ":" + e.getTimeseries().getRastertype().toString() + " is valid and cannot be created!");
        }

    }

    public void testRasterBlockungYearSec30() {

        try {

            factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.SOURCE,
                    TimeseriesDerivationType.NONE,
                    TimeseriesRastertype.SEC30,
                    TimeseriesBlocksize.YEAR,
                    TimeseriesPersistence.TRANSIENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            fail("Transient TimeseriesHead with RasterBlockung: " + e.getTimeseries().getBlocksize().toString() + ":" + e.getTimeseries().getRastertype().toString() + " is valid and cannot be created!");
        }

    }

    public void testRasterBlockungYearMin1() {

        try {

            factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.SOURCE,
                    TimeseriesDerivationType.NONE,
                    TimeseriesRastertype.MIN1,
                    TimeseriesBlocksize.YEAR,
                    TimeseriesPersistence.TRANSIENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            fail("Transient TimeseriesHead with RasterBlockung: " + e.getTimeseries().getBlocksize().toString() + ":" + e.getTimeseries().getRastertype().toString() + " is valid and cannot be created!");
        }

    }

    public void testRasterBlockungYearMin15() {

        try {

            factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.SOURCE,
                    TimeseriesDerivationType.NONE,
                    TimeseriesRastertype.MIN15,
                    TimeseriesBlocksize.YEAR,
                    TimeseriesPersistence.TRANSIENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            fail("Transient TimeseriesHead with RasterBlockung: " + e.getTimeseries().getBlocksize().toString() + ":" + e.getTimeseries().getRastertype().toString() + " is valid and cannot be created!");
        }

    }

    public void testRasterBlockungYearMin30() {

        try {

            factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.SOURCE,
                    TimeseriesDerivationType.NONE,
                    TimeseriesRastertype.MIN30,
                    TimeseriesBlocksize.YEAR,
                    TimeseriesPersistence.TRANSIENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            fail("Transient TimeseriesHead with RasterBlockung: " + e.getTimeseries().getBlocksize().toString() + ":" + e.getTimeseries().getRastertype().toString() + " is valid and cannot be created!");
        }

    }

    public void testRasterBlockungYearHour1() {

        try {

            factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.SOURCE,
                    TimeseriesDerivationType.NONE,
                    TimeseriesRastertype.HOUR1,
                    TimeseriesBlocksize.YEAR,
                    TimeseriesPersistence.TRANSIENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            fail("Transient TimeseriesHead with RasterBlockung: " + e.getTimeseries().getBlocksize().toString() + ":" + e.getTimeseries().getRastertype().toString() + " is valid and cannot be created!");
        }

    }

    public void testRasterBlockungYearHour6() {

        try {

            factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.SOURCE,
                    TimeseriesDerivationType.NONE,
                    TimeseriesRastertype.HOUR6,
                    TimeseriesBlocksize.YEAR,
                    TimeseriesPersistence.TRANSIENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            fail("Transient TimeseriesHead with RasterBlockung: " + e.getTimeseries().getBlocksize().toString() + ":" + e.getTimeseries().getRastertype().toString() + " is valid and cannot be created!");
        }

    }

    public void testRasterBlockungYearHour12() {

        try {

            factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.SOURCE,
                    TimeseriesDerivationType.NONE,
                    TimeseriesRastertype.HOUR12,
                    TimeseriesBlocksize.YEAR,
                    TimeseriesPersistence.TRANSIENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            fail("Transient TimeseriesHead with RasterBlockung: " + e.getTimeseries().getBlocksize().toString() + ":" + e.getTimeseries().getRastertype().toString() + " is valid and cannot be created!");
        }

    }

    public void testRasterBlockungYearDay1() {

        try {

            factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.SOURCE,
                    TimeseriesDerivationType.NONE,
                    TimeseriesRastertype.DAY1,
                    TimeseriesBlocksize.YEAR,
                    TimeseriesPersistence.TRANSIENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            fail("Transient TimeseriesHead with RasterBlockung: " + e.getTimeseries().getBlocksize().toString() + ":" + e.getTimeseries().getRastertype().toString() + " is valid and cannot be created!");
        }

    }

}
