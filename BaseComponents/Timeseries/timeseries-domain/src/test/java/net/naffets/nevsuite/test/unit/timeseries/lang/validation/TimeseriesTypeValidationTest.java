package net.naffets.nevsuite.test.unit.timeseries.lang.validation;

import junit.framework.TestCase;
import net.naffets.nevsuite.backend.timeseries.domain.basictype.*;
import net.naffets.nevsuite.backend.timeseries.domain.entity.TimeseriesHead;
import net.naffets.nevsuite.backend.timeseries.domain.factory.TimeseriesHeadFactory;
import net.naffets.nevsuite.backend.timeseries.lang.exception.InvalidTimeseriesConfigException;
import org.junit.Ignore;

/**
 * User:    Braska<br />
 * Date:    02.07.2015<br />
 * <br />
 * <br />
 */
@Ignore
public class TimeseriesTypeValidationTest extends TestCase {

    public void testSourceWithoutDerivationType() {

        try {
            TimeseriesHeadFactory factory = new TimeseriesHeadFactory();

            factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.SOURCE,
                    TimeseriesDerivationType.NONE,
                    TimeseriesRastertype.MIN15,
                    TimeseriesBlocksize.DAY,
                    TimeseriesPersistence.PERSISTENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            fail("TimeseriesHead with Config: " + TimeseriesType.SOURCE.toString() + ":" + TimeseriesDerivationType.NONE.toString() + " is valid and cannot be created!");
        }

    }

    public void testSourceWithDerivationTypeAVG() {

        try {
            TimeseriesHeadFactory factory = new TimeseriesHeadFactory();

            factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.SOURCE,
                    TimeseriesDerivationType.AVG,
                    TimeseriesRastertype.MIN15,
                    TimeseriesBlocksize.DAY,
                    TimeseriesPersistence.PERSISTENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            return;
        }

        fail("TimeseriesHead with Config: " + TimeseriesType.SOURCE.toString() + ":" + TimeseriesDerivationType.AVG.toString() + " is invalid and may be created!");

    }

    public void testSourceWithDerivationTypeMIN() {

        try {
            TimeseriesHeadFactory factory = new TimeseriesHeadFactory();

            factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.SOURCE,
                    TimeseriesDerivationType.MIN,
                    TimeseriesRastertype.MIN15,
                    TimeseriesBlocksize.DAY,
                    TimeseriesPersistence.PERSISTENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            return;
        }

        fail("TimeseriesHead with Config: " + TimeseriesType.SOURCE.toString() + ":" + TimeseriesDerivationType.MIN.toString() + " is invalid and may be created!");

    }

    public void testSourceWithDerivationTypeMAX() {

        try {
            TimeseriesHeadFactory factory = new TimeseriesHeadFactory();

            factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.SOURCE,
                    TimeseriesDerivationType.MAX,
                    TimeseriesRastertype.MIN15,
                    TimeseriesBlocksize.DAY,
                    TimeseriesPersistence.PERSISTENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            return;
        }

        fail("TimeseriesHead with Config: " + TimeseriesType.SOURCE.toString() + ":" + TimeseriesDerivationType.MAX.toString() + " is invalid and may be created!");

    }

    public void testSourceWithDerivationTypeSUM() {

        try {

            TimeseriesHeadFactory factory = new TimeseriesHeadFactory();

            factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.SOURCE,
                    TimeseriesDerivationType.SUM,
                    TimeseriesRastertype.MIN15,
                    TimeseriesBlocksize.DAY,
                    TimeseriesPersistence.PERSISTENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            return;
        }

        fail("TimeseriesHead with Config: " + TimeseriesType.SOURCE.toString() + ":" + TimeseriesDerivationType.SUM.toString() + " is invalid and may be created!");

    }

    public void testDerivationWithoutDerivationType() {

        TimeseriesHead ts = null;

        try {
            TimeseriesHeadFactory factory = new TimeseriesHeadFactory();

            ts =
                    factory.createTimeseriesHead(
                            "123",
                            TimeseriesType.DERIVATION,
                            TimeseriesDerivationType.NONE,
                            TimeseriesRastertype.MIN15,
                            TimeseriesBlocksize.DAY,
                            TimeseriesPersistence.PERSISTENT,
                            TimeseriesPeriodicity.PERIODIC
                    );

        } catch (InvalidTimeseriesConfigException e) {
            return;
        }

        fail("TimeseriesHead with Config: " + TimeseriesType.DERIVATION.toString() + ":" + TimeseriesDerivationType.NONE.toString() + " is invalid and may be created!");

    }

    public void testDerivationWithDerivationTypeAVG() {

        try {

            TimeseriesHeadFactory factory = new TimeseriesHeadFactory();

            factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.DERIVATION,
                    TimeseriesDerivationType.AVG,
                    TimeseriesRastertype.MIN15,
                    TimeseriesBlocksize.DAY,
                    TimeseriesPersistence.PERSISTENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            fail("TimeseriesHead with Config: " + TimeseriesType.DERIVATION.toString() + ":" + TimeseriesDerivationType.AVG.toString() + " is valid and cannot be created!");
        }

    }

    public void testDerivationWithDerivationTypeMIN() {

        try {

            TimeseriesHeadFactory factory = new TimeseriesHeadFactory();

            factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.DERIVATION,
                    TimeseriesDerivationType.MIN,
                    TimeseriesRastertype.MIN15,
                    TimeseriesBlocksize.DAY,
                    TimeseriesPersistence.PERSISTENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            fail("TimeseriesHead with Config: " + TimeseriesType.DERIVATION.toString() + ":" + TimeseriesDerivationType.MIN.toString() + " is valid and cannot be created!");
        }

    }

    public void testDerivationWithDerivationTypeMAX() {

        try {

            TimeseriesHeadFactory factory = new TimeseriesHeadFactory();

            factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.DERIVATION,
                    TimeseriesDerivationType.MAX,
                    TimeseriesRastertype.MIN15,
                    TimeseriesBlocksize.DAY,
                    TimeseriesPersistence.PERSISTENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            fail("TimeseriesHead with Config: " + TimeseriesType.DERIVATION.toString() + ":" + TimeseriesDerivationType.MAX.toString() + " is valid and cannot be created!");
        }

    }

    public void testDerivationWithDerivationTypeSUM() {

        try {

            TimeseriesHeadFactory factory = new TimeseriesHeadFactory();

            factory.createTimeseriesHead(
                    "123",
                    TimeseriesType.DERIVATION,
                    TimeseriesDerivationType.SUM,
                    TimeseriesRastertype.MIN15,
                    TimeseriesBlocksize.DAY,
                    TimeseriesPersistence.PERSISTENT,
                    TimeseriesPeriodicity.PERIODIC
            );

        } catch (InvalidTimeseriesConfigException e) {
            fail("TimeseriesHead with Config: " + TimeseriesType.DERIVATION.toString() + ":" + TimeseriesDerivationType.SUM.toString() + " is valid and cannot be created!");
        }

    }

}
