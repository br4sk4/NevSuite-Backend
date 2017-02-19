package net.naffets.nevsuite.test.unit.timeseries.core.timeseries;

import net.naffets.nevsuite.backend.timeseries.core.timeseries.TimeseriesUnit;
import org.jscience.physics.amount.Amount;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author br4sk4
 * created on 29.03.2016
 */
public class TimeseriesUnitTest {

    @Test
    public void testCompatibilityCUBIC_METER() {
        assertFalse(TimeseriesUnit.CUBIC_METRE.isCompatible(TimeseriesUnit.NONE));
        assertFalse(TimeseriesUnit.CUBIC_METRE.isCompatible(TimeseriesUnit.UNKNOWN));

        assertTrue(TimeseriesUnit.CUBIC_METRE.isCompatible(TimeseriesUnit.CUBIC_METRE));
        assertTrue(TimeseriesUnit.CUBIC_METRE.isCompatible(TimeseriesUnit.LITER));

        assertFalse(TimeseriesUnit.CUBIC_METRE.isCompatible(TimeseriesUnit.WATT));
        assertFalse(TimeseriesUnit.CUBIC_METRE.isCompatible(TimeseriesUnit.KILO_WATT));
        assertFalse(TimeseriesUnit.CUBIC_METRE.isCompatible(TimeseriesUnit.MEGA_WATT));
        assertFalse(TimeseriesUnit.CUBIC_METRE.isCompatible(TimeseriesUnit.GIGA_WATT));

        assertFalse(TimeseriesUnit.CUBIC_METRE.isCompatible(TimeseriesUnit.WATT_SECOND));
        assertFalse(TimeseriesUnit.CUBIC_METRE.isCompatible(TimeseriesUnit.WATT_HOUR));
        assertFalse(TimeseriesUnit.CUBIC_METRE.isCompatible(TimeseriesUnit.KILO_WATT_HOUR));
        assertFalse(TimeseriesUnit.CUBIC_METRE.isCompatible(TimeseriesUnit.MEGA_WATT_HOUR));
        assertFalse(TimeseriesUnit.CUBIC_METRE.isCompatible(TimeseriesUnit.GIGA_WATT_HOUR));

        assertFalse(TimeseriesUnit.CUBIC_METRE.isCompatible(TimeseriesUnit.EURO));
        assertFalse(TimeseriesUnit.CUBIC_METRE.isCompatible(TimeseriesUnit.EURO_CENT));

        assertFalse(TimeseriesUnit.CUBIC_METRE.isCompatible(TimeseriesUnit.EURO_CENT_PER_KILO_WATT_HOUR));
        assertFalse(TimeseriesUnit.CUBIC_METRE.isCompatible(TimeseriesUnit.EURO_PER_KILO_WATT));
        assertFalse(TimeseriesUnit.CUBIC_METRE.isCompatible(TimeseriesUnit.EURO_PER_MEGA_WATT));

        assertFalse(TimeseriesUnit.CUBIC_METRE.isCompatible(TimeseriesUnit.BURN_VALUE));
    }

    @Test
    public void testCompatibilityLITER() {
        assertFalse(TimeseriesUnit.LITER.isCompatible(TimeseriesUnit.NONE));
        assertFalse(TimeseriesUnit.LITER.isCompatible(TimeseriesUnit.UNKNOWN));

        assertTrue(TimeseriesUnit.LITER.isCompatible(TimeseriesUnit.CUBIC_METRE));
        assertTrue(TimeseriesUnit.LITER.isCompatible(TimeseriesUnit.LITER));

        assertFalse(TimeseriesUnit.LITER.isCompatible(TimeseriesUnit.WATT));
        assertFalse(TimeseriesUnit.LITER.isCompatible(TimeseriesUnit.KILO_WATT));
        assertFalse(TimeseriesUnit.LITER.isCompatible(TimeseriesUnit.MEGA_WATT));
        assertFalse(TimeseriesUnit.LITER.isCompatible(TimeseriesUnit.GIGA_WATT));

        assertFalse(TimeseriesUnit.LITER.isCompatible(TimeseriesUnit.WATT_SECOND));
        assertFalse(TimeseriesUnit.LITER.isCompatible(TimeseriesUnit.WATT_HOUR));
        assertFalse(TimeseriesUnit.LITER.isCompatible(TimeseriesUnit.KILO_WATT_HOUR));
        assertFalse(TimeseriesUnit.LITER.isCompatible(TimeseriesUnit.MEGA_WATT_HOUR));
        assertFalse(TimeseriesUnit.LITER.isCompatible(TimeseriesUnit.GIGA_WATT_HOUR));

        assertFalse(TimeseriesUnit.LITER.isCompatible(TimeseriesUnit.EURO));
        assertFalse(TimeseriesUnit.LITER.isCompatible(TimeseriesUnit.EURO_CENT));

        assertFalse(TimeseriesUnit.LITER.isCompatible(TimeseriesUnit.EURO_CENT_PER_KILO_WATT_HOUR));
        assertFalse(TimeseriesUnit.LITER.isCompatible(TimeseriesUnit.EURO_PER_KILO_WATT));
        assertFalse(TimeseriesUnit.LITER.isCompatible(TimeseriesUnit.EURO_PER_MEGA_WATT));

        assertFalse(TimeseriesUnit.LITER.isCompatible(TimeseriesUnit.BURN_VALUE));
    }

    @Test
    public void testCompatibilityWATT() {
        assertFalse(TimeseriesUnit.WATT.isCompatible(TimeseriesUnit.NONE));
        assertFalse(TimeseriesUnit.WATT.isCompatible(TimeseriesUnit.UNKNOWN));

        assertFalse(TimeseriesUnit.WATT.isCompatible(TimeseriesUnit.CUBIC_METRE));
        assertFalse(TimeseriesUnit.WATT.isCompatible(TimeseriesUnit.LITER));

        assertTrue(TimeseriesUnit.WATT.isCompatible(TimeseriesUnit.WATT));
        assertTrue(TimeseriesUnit.WATT.isCompatible(TimeseriesUnit.KILO_WATT));
        assertTrue(TimeseriesUnit.WATT.isCompatible(TimeseriesUnit.MEGA_WATT));
        assertTrue(TimeseriesUnit.WATT.isCompatible(TimeseriesUnit.GIGA_WATT));

        assertFalse(TimeseriesUnit.WATT.isCompatible(TimeseriesUnit.WATT_SECOND));
        assertFalse(TimeseriesUnit.WATT.isCompatible(TimeseriesUnit.WATT_HOUR));
        assertFalse(TimeseriesUnit.WATT.isCompatible(TimeseriesUnit.KILO_WATT_HOUR));
        assertFalse(TimeseriesUnit.WATT.isCompatible(TimeseriesUnit.MEGA_WATT_HOUR));
        assertFalse(TimeseriesUnit.WATT.isCompatible(TimeseriesUnit.GIGA_WATT_HOUR));

        assertFalse(TimeseriesUnit.WATT.isCompatible(TimeseriesUnit.EURO));
        assertFalse(TimeseriesUnit.WATT.isCompatible(TimeseriesUnit.EURO_CENT));

        assertFalse(TimeseriesUnit.WATT.isCompatible(TimeseriesUnit.EURO_CENT_PER_KILO_WATT_HOUR));
        assertFalse(TimeseriesUnit.WATT.isCompatible(TimeseriesUnit.EURO_PER_KILO_WATT));
        assertFalse(TimeseriesUnit.WATT.isCompatible(TimeseriesUnit.EURO_PER_MEGA_WATT));

        assertFalse(TimeseriesUnit.WATT.isCompatible(TimeseriesUnit.BURN_VALUE));
    }

    @Test
    public void testCompatibilityKILO_WATT() {
        assertFalse(TimeseriesUnit.KILO_WATT.isCompatible(TimeseriesUnit.NONE));
        assertFalse(TimeseriesUnit.KILO_WATT.isCompatible(TimeseriesUnit.UNKNOWN));

        assertFalse(TimeseriesUnit.KILO_WATT.isCompatible(TimeseriesUnit.CUBIC_METRE));
        assertFalse(TimeseriesUnit.KILO_WATT.isCompatible(TimeseriesUnit.LITER));

        assertTrue(TimeseriesUnit.KILO_WATT.isCompatible(TimeseriesUnit.WATT));
        assertTrue(TimeseriesUnit.KILO_WATT.isCompatible(TimeseriesUnit.KILO_WATT));
        assertTrue(TimeseriesUnit.KILO_WATT.isCompatible(TimeseriesUnit.MEGA_WATT));
        assertTrue(TimeseriesUnit.KILO_WATT.isCompatible(TimeseriesUnit.GIGA_WATT));

        assertFalse(TimeseriesUnit.KILO_WATT.isCompatible(TimeseriesUnit.WATT_SECOND));
        assertFalse(TimeseriesUnit.KILO_WATT.isCompatible(TimeseriesUnit.WATT_HOUR));
        assertFalse(TimeseriesUnit.KILO_WATT.isCompatible(TimeseriesUnit.KILO_WATT_HOUR));
        assertFalse(TimeseriesUnit.KILO_WATT.isCompatible(TimeseriesUnit.MEGA_WATT_HOUR));
        assertFalse(TimeseriesUnit.KILO_WATT.isCompatible(TimeseriesUnit.GIGA_WATT_HOUR));

        assertFalse(TimeseriesUnit.KILO_WATT.isCompatible(TimeseriesUnit.EURO));
        assertFalse(TimeseriesUnit.KILO_WATT.isCompatible(TimeseriesUnit.EURO_CENT));

        assertFalse(TimeseriesUnit.KILO_WATT.isCompatible(TimeseriesUnit.EURO_CENT_PER_KILO_WATT_HOUR));
        assertFalse(TimeseriesUnit.KILO_WATT.isCompatible(TimeseriesUnit.EURO_PER_KILO_WATT));
        assertFalse(TimeseriesUnit.KILO_WATT.isCompatible(TimeseriesUnit.EURO_PER_MEGA_WATT));

        assertFalse(TimeseriesUnit.KILO_WATT.isCompatible(TimeseriesUnit.BURN_VALUE));
    }

    @Test
    public void testCompatibilityMEGA_WATT() {
        assertFalse(TimeseriesUnit.MEGA_WATT.isCompatible(TimeseriesUnit.NONE));
        assertFalse(TimeseriesUnit.MEGA_WATT.isCompatible(TimeseriesUnit.UNKNOWN));

        assertFalse(TimeseriesUnit.MEGA_WATT.isCompatible(TimeseriesUnit.CUBIC_METRE));
        assertFalse(TimeseriesUnit.MEGA_WATT.isCompatible(TimeseriesUnit.LITER));

        assertTrue(TimeseriesUnit.MEGA_WATT.isCompatible(TimeseriesUnit.WATT));
        assertTrue(TimeseriesUnit.MEGA_WATT.isCompatible(TimeseriesUnit.KILO_WATT));
        assertTrue(TimeseriesUnit.MEGA_WATT.isCompatible(TimeseriesUnit.MEGA_WATT));
        assertTrue(TimeseriesUnit.MEGA_WATT.isCompatible(TimeseriesUnit.GIGA_WATT));

        assertFalse(TimeseriesUnit.MEGA_WATT.isCompatible(TimeseriesUnit.WATT_SECOND));
        assertFalse(TimeseriesUnit.MEGA_WATT.isCompatible(TimeseriesUnit.WATT_HOUR));
        assertFalse(TimeseriesUnit.MEGA_WATT.isCompatible(TimeseriesUnit.KILO_WATT_HOUR));
        assertFalse(TimeseriesUnit.MEGA_WATT.isCompatible(TimeseriesUnit.MEGA_WATT_HOUR));
        assertFalse(TimeseriesUnit.MEGA_WATT.isCompatible(TimeseriesUnit.GIGA_WATT_HOUR));

        assertFalse(TimeseriesUnit.MEGA_WATT.isCompatible(TimeseriesUnit.EURO));
        assertFalse(TimeseriesUnit.MEGA_WATT.isCompatible(TimeseriesUnit.EURO_CENT));

        assertFalse(TimeseriesUnit.MEGA_WATT.isCompatible(TimeseriesUnit.EURO_CENT_PER_KILO_WATT_HOUR));
        assertFalse(TimeseriesUnit.MEGA_WATT.isCompatible(TimeseriesUnit.EURO_PER_KILO_WATT));
        assertFalse(TimeseriesUnit.MEGA_WATT.isCompatible(TimeseriesUnit.EURO_PER_MEGA_WATT));

        assertFalse(TimeseriesUnit.MEGA_WATT.isCompatible(TimeseriesUnit.BURN_VALUE));
    }

    @Test
    public void testCompatibilityGIGA_WATT() {
        assertFalse(TimeseriesUnit.GIGA_WATT.isCompatible(TimeseriesUnit.NONE));
        assertFalse(TimeseriesUnit.GIGA_WATT.isCompatible(TimeseriesUnit.UNKNOWN));

        assertFalse(TimeseriesUnit.GIGA_WATT.isCompatible(TimeseriesUnit.CUBIC_METRE));
        assertFalse(TimeseriesUnit.GIGA_WATT.isCompatible(TimeseriesUnit.LITER));

        assertTrue(TimeseriesUnit.GIGA_WATT.isCompatible(TimeseriesUnit.WATT));
        assertTrue(TimeseriesUnit.GIGA_WATT.isCompatible(TimeseriesUnit.KILO_WATT));
        assertTrue(TimeseriesUnit.GIGA_WATT.isCompatible(TimeseriesUnit.MEGA_WATT));
        assertTrue(TimeseriesUnit.GIGA_WATT.isCompatible(TimeseriesUnit.GIGA_WATT));

        assertFalse(TimeseriesUnit.GIGA_WATT.isCompatible(TimeseriesUnit.WATT_SECOND));
        assertFalse(TimeseriesUnit.GIGA_WATT.isCompatible(TimeseriesUnit.WATT_HOUR));
        assertFalse(TimeseriesUnit.GIGA_WATT.isCompatible(TimeseriesUnit.KILO_WATT_HOUR));
        assertFalse(TimeseriesUnit.GIGA_WATT.isCompatible(TimeseriesUnit.MEGA_WATT_HOUR));
        assertFalse(TimeseriesUnit.GIGA_WATT.isCompatible(TimeseriesUnit.GIGA_WATT_HOUR));

        assertFalse(TimeseriesUnit.GIGA_WATT.isCompatible(TimeseriesUnit.EURO));
        assertFalse(TimeseriesUnit.GIGA_WATT.isCompatible(TimeseriesUnit.EURO_CENT));

        assertFalse(TimeseriesUnit.GIGA_WATT.isCompatible(TimeseriesUnit.EURO_CENT_PER_KILO_WATT_HOUR));
        assertFalse(TimeseriesUnit.GIGA_WATT.isCompatible(TimeseriesUnit.EURO_PER_KILO_WATT));
        assertFalse(TimeseriesUnit.GIGA_WATT.isCompatible(TimeseriesUnit.EURO_PER_MEGA_WATT));

        assertFalse(TimeseriesUnit.GIGA_WATT.isCompatible(TimeseriesUnit.BURN_VALUE));
    }

    @Test
    public void testCompatibilityWATT_SECOND() {
        assertFalse(TimeseriesUnit.WATT_SECOND.isCompatible(TimeseriesUnit.NONE));
        assertFalse(TimeseriesUnit.WATT_SECOND.isCompatible(TimeseriesUnit.UNKNOWN));

        assertFalse(TimeseriesUnit.WATT_SECOND.isCompatible(TimeseriesUnit.CUBIC_METRE));
        assertFalse(TimeseriesUnit.WATT_SECOND.isCompatible(TimeseriesUnit.LITER));

        assertFalse(TimeseriesUnit.WATT_SECOND.isCompatible(TimeseriesUnit.WATT));
        assertFalse(TimeseriesUnit.WATT_SECOND.isCompatible(TimeseriesUnit.KILO_WATT));
        assertFalse(TimeseriesUnit.WATT_SECOND.isCompatible(TimeseriesUnit.MEGA_WATT));
        assertFalse(TimeseriesUnit.WATT_SECOND.isCompatible(TimeseriesUnit.GIGA_WATT));

        assertTrue(TimeseriesUnit.WATT_SECOND.isCompatible(TimeseriesUnit.WATT_SECOND));
        assertTrue(TimeseriesUnit.WATT_SECOND.isCompatible(TimeseriesUnit.WATT_HOUR));
        assertTrue(TimeseriesUnit.WATT_SECOND.isCompatible(TimeseriesUnit.KILO_WATT_HOUR));
        assertTrue(TimeseriesUnit.WATT_SECOND.isCompatible(TimeseriesUnit.MEGA_WATT_HOUR));
        assertTrue(TimeseriesUnit.WATT_SECOND.isCompatible(TimeseriesUnit.GIGA_WATT_HOUR));

        assertFalse(TimeseriesUnit.WATT_SECOND.isCompatible(TimeseriesUnit.EURO));
        assertFalse(TimeseriesUnit.WATT_SECOND.isCompatible(TimeseriesUnit.EURO_CENT));

        assertFalse(TimeseriesUnit.WATT_SECOND.isCompatible(TimeseriesUnit.EURO_CENT_PER_KILO_WATT_HOUR));
        assertFalse(TimeseriesUnit.WATT_SECOND.isCompatible(TimeseriesUnit.EURO_PER_KILO_WATT));
        assertFalse(TimeseriesUnit.WATT_SECOND.isCompatible(TimeseriesUnit.EURO_PER_MEGA_WATT));

        assertFalse(TimeseriesUnit.WATT_SECOND.isCompatible(TimeseriesUnit.BURN_VALUE));
    }

    @Test
    public void testCompatibilityWATT_HOUR() {
        assertFalse(TimeseriesUnit.WATT_HOUR.isCompatible(TimeseriesUnit.NONE));
        assertFalse(TimeseriesUnit.WATT_HOUR.isCompatible(TimeseriesUnit.UNKNOWN));

        assertFalse(TimeseriesUnit.WATT_HOUR.isCompatible(TimeseriesUnit.CUBIC_METRE));
        assertFalse(TimeseriesUnit.WATT_HOUR.isCompatible(TimeseriesUnit.LITER));

        assertFalse(TimeseriesUnit.WATT_HOUR.isCompatible(TimeseriesUnit.WATT));
        assertFalse(TimeseriesUnit.WATT_HOUR.isCompatible(TimeseriesUnit.KILO_WATT));
        assertFalse(TimeseriesUnit.WATT_HOUR.isCompatible(TimeseriesUnit.MEGA_WATT));
        assertFalse(TimeseriesUnit.WATT_HOUR.isCompatible(TimeseriesUnit.GIGA_WATT));

        assertTrue(TimeseriesUnit.WATT_HOUR.isCompatible(TimeseriesUnit.WATT_SECOND));
        assertTrue(TimeseriesUnit.WATT_HOUR.isCompatible(TimeseriesUnit.WATT_HOUR));
        assertTrue(TimeseriesUnit.WATT_HOUR.isCompatible(TimeseriesUnit.KILO_WATT_HOUR));
        assertTrue(TimeseriesUnit.WATT_HOUR.isCompatible(TimeseriesUnit.MEGA_WATT_HOUR));
        assertTrue(TimeseriesUnit.WATT_HOUR.isCompatible(TimeseriesUnit.GIGA_WATT_HOUR));

        assertFalse(TimeseriesUnit.WATT_HOUR.isCompatible(TimeseriesUnit.EURO));
        assertFalse(TimeseriesUnit.WATT_HOUR.isCompatible(TimeseriesUnit.EURO_CENT));

        assertFalse(TimeseriesUnit.WATT_HOUR.isCompatible(TimeseriesUnit.EURO_CENT_PER_KILO_WATT_HOUR));
        assertFalse(TimeseriesUnit.WATT_HOUR.isCompatible(TimeseriesUnit.EURO_PER_KILO_WATT));
        assertFalse(TimeseriesUnit.WATT_HOUR.isCompatible(TimeseriesUnit.EURO_PER_MEGA_WATT));

        assertFalse(TimeseriesUnit.WATT_HOUR.isCompatible(TimeseriesUnit.BURN_VALUE));
    }

    @Test
    public void testCompatibilityKILO_WATT_HOUR() {
        assertFalse(TimeseriesUnit.KILO_WATT_HOUR.isCompatible(TimeseriesUnit.NONE));
        assertFalse(TimeseriesUnit.KILO_WATT_HOUR.isCompatible(TimeseriesUnit.UNKNOWN));

        assertFalse(TimeseriesUnit.KILO_WATT_HOUR.isCompatible(TimeseriesUnit.CUBIC_METRE));
        assertFalse(TimeseriesUnit.KILO_WATT_HOUR.isCompatible(TimeseriesUnit.LITER));

        assertFalse(TimeseriesUnit.KILO_WATT_HOUR.isCompatible(TimeseriesUnit.WATT));
        assertFalse(TimeseriesUnit.KILO_WATT_HOUR.isCompatible(TimeseriesUnit.KILO_WATT));
        assertFalse(TimeseriesUnit.KILO_WATT_HOUR.isCompatible(TimeseriesUnit.MEGA_WATT));
        assertFalse(TimeseriesUnit.KILO_WATT_HOUR.isCompatible(TimeseriesUnit.GIGA_WATT));

        assertTrue(TimeseriesUnit.KILO_WATT_HOUR.isCompatible(TimeseriesUnit.WATT_SECOND));
        assertTrue(TimeseriesUnit.KILO_WATT_HOUR.isCompatible(TimeseriesUnit.WATT_HOUR));
        assertTrue(TimeseriesUnit.KILO_WATT_HOUR.isCompatible(TimeseriesUnit.KILO_WATT_HOUR));
        assertTrue(TimeseriesUnit.KILO_WATT_HOUR.isCompatible(TimeseriesUnit.MEGA_WATT_HOUR));
        assertTrue(TimeseriesUnit.KILO_WATT_HOUR.isCompatible(TimeseriesUnit.GIGA_WATT_HOUR));

        assertFalse(TimeseriesUnit.KILO_WATT_HOUR.isCompatible(TimeseriesUnit.EURO));
        assertFalse(TimeseriesUnit.KILO_WATT_HOUR.isCompatible(TimeseriesUnit.EURO_CENT));

        assertFalse(TimeseriesUnit.KILO_WATT_HOUR.isCompatible(TimeseriesUnit.EURO_CENT_PER_KILO_WATT_HOUR));
        assertFalse(TimeseriesUnit.KILO_WATT_HOUR.isCompatible(TimeseriesUnit.EURO_PER_KILO_WATT));
        assertFalse(TimeseriesUnit.KILO_WATT_HOUR.isCompatible(TimeseriesUnit.EURO_PER_MEGA_WATT));

        assertFalse(TimeseriesUnit.KILO_WATT_HOUR.isCompatible(TimeseriesUnit.BURN_VALUE));
    }

    @Test
    public void testCompatibilityMEGA_WATT_HOUR() {
        assertFalse(TimeseriesUnit.MEGA_WATT_HOUR.isCompatible(TimeseriesUnit.NONE));
        assertFalse(TimeseriesUnit.MEGA_WATT_HOUR.isCompatible(TimeseriesUnit.UNKNOWN));

        assertFalse(TimeseriesUnit.MEGA_WATT_HOUR.isCompatible(TimeseriesUnit.CUBIC_METRE));
        assertFalse(TimeseriesUnit.MEGA_WATT_HOUR.isCompatible(TimeseriesUnit.LITER));

        assertFalse(TimeseriesUnit.MEGA_WATT_HOUR.isCompatible(TimeseriesUnit.WATT));
        assertFalse(TimeseriesUnit.MEGA_WATT_HOUR.isCompatible(TimeseriesUnit.KILO_WATT));
        assertFalse(TimeseriesUnit.MEGA_WATT_HOUR.isCompatible(TimeseriesUnit.MEGA_WATT));
        assertFalse(TimeseriesUnit.MEGA_WATT_HOUR.isCompatible(TimeseriesUnit.GIGA_WATT));

        assertTrue(TimeseriesUnit.MEGA_WATT_HOUR.isCompatible(TimeseriesUnit.WATT_SECOND));
        assertTrue(TimeseriesUnit.MEGA_WATT_HOUR.isCompatible(TimeseriesUnit.WATT_HOUR));
        assertTrue(TimeseriesUnit.MEGA_WATT_HOUR.isCompatible(TimeseriesUnit.KILO_WATT_HOUR));
        assertTrue(TimeseriesUnit.MEGA_WATT_HOUR.isCompatible(TimeseriesUnit.MEGA_WATT_HOUR));
        assertTrue(TimeseriesUnit.MEGA_WATT_HOUR.isCompatible(TimeseriesUnit.GIGA_WATT_HOUR));

        assertFalse(TimeseriesUnit.MEGA_WATT_HOUR.isCompatible(TimeseriesUnit.EURO));
        assertFalse(TimeseriesUnit.MEGA_WATT_HOUR.isCompatible(TimeseriesUnit.EURO_CENT));

        assertFalse(TimeseriesUnit.MEGA_WATT_HOUR.isCompatible(TimeseriesUnit.EURO_CENT_PER_KILO_WATT_HOUR));
        assertFalse(TimeseriesUnit.MEGA_WATT_HOUR.isCompatible(TimeseriesUnit.EURO_PER_KILO_WATT));
        assertFalse(TimeseriesUnit.MEGA_WATT_HOUR.isCompatible(TimeseriesUnit.EURO_PER_MEGA_WATT));

        assertFalse(TimeseriesUnit.MEGA_WATT_HOUR.isCompatible(TimeseriesUnit.BURN_VALUE));
    }

    @Test
    public void testCompatibilityGIGA_WATT_HOUR() {
        assertFalse(TimeseriesUnit.GIGA_WATT_HOUR.isCompatible(TimeseriesUnit.NONE));
        assertFalse(TimeseriesUnit.GIGA_WATT_HOUR.isCompatible(TimeseriesUnit.UNKNOWN));

        assertFalse(TimeseriesUnit.GIGA_WATT_HOUR.isCompatible(TimeseriesUnit.CUBIC_METRE));
        assertFalse(TimeseriesUnit.GIGA_WATT_HOUR.isCompatible(TimeseriesUnit.LITER));

        assertFalse(TimeseriesUnit.GIGA_WATT_HOUR.isCompatible(TimeseriesUnit.WATT));
        assertFalse(TimeseriesUnit.GIGA_WATT_HOUR.isCompatible(TimeseriesUnit.KILO_WATT));
        assertFalse(TimeseriesUnit.GIGA_WATT_HOUR.isCompatible(TimeseriesUnit.MEGA_WATT));
        assertFalse(TimeseriesUnit.GIGA_WATT_HOUR.isCompatible(TimeseriesUnit.GIGA_WATT));

        assertTrue(TimeseriesUnit.GIGA_WATT_HOUR.isCompatible(TimeseriesUnit.WATT_SECOND));
        assertTrue(TimeseriesUnit.GIGA_WATT_HOUR.isCompatible(TimeseriesUnit.WATT_HOUR));
        assertTrue(TimeseriesUnit.GIGA_WATT_HOUR.isCompatible(TimeseriesUnit.KILO_WATT_HOUR));
        assertTrue(TimeseriesUnit.GIGA_WATT_HOUR.isCompatible(TimeseriesUnit.MEGA_WATT_HOUR));
        assertTrue(TimeseriesUnit.GIGA_WATT_HOUR.isCompatible(TimeseriesUnit.GIGA_WATT_HOUR));

        assertFalse(TimeseriesUnit.GIGA_WATT_HOUR.isCompatible(TimeseriesUnit.EURO));
        assertFalse(TimeseriesUnit.GIGA_WATT_HOUR.isCompatible(TimeseriesUnit.EURO_CENT));

        assertFalse(TimeseriesUnit.GIGA_WATT_HOUR.isCompatible(TimeseriesUnit.EURO_CENT_PER_KILO_WATT_HOUR));
        assertFalse(TimeseriesUnit.GIGA_WATT_HOUR.isCompatible(TimeseriesUnit.EURO_PER_KILO_WATT));
        assertFalse(TimeseriesUnit.GIGA_WATT_HOUR.isCompatible(TimeseriesUnit.EURO_PER_MEGA_WATT));

        assertFalse(TimeseriesUnit.GIGA_WATT_HOUR.isCompatible(TimeseriesUnit.BURN_VALUE));
    }

    @Test
    public void testCompatibilityEURO() {
        assertFalse(TimeseriesUnit.EURO.isCompatible(TimeseriesUnit.NONE));
        assertFalse(TimeseriesUnit.EURO.isCompatible(TimeseriesUnit.UNKNOWN));

        assertFalse(TimeseriesUnit.EURO.isCompatible(TimeseriesUnit.CUBIC_METRE));
        assertFalse(TimeseriesUnit.EURO.isCompatible(TimeseriesUnit.LITER));

        assertFalse(TimeseriesUnit.EURO.isCompatible(TimeseriesUnit.WATT));
        assertFalse(TimeseriesUnit.EURO.isCompatible(TimeseriesUnit.KILO_WATT));
        assertFalse(TimeseriesUnit.EURO.isCompatible(TimeseriesUnit.MEGA_WATT));
        assertFalse(TimeseriesUnit.EURO.isCompatible(TimeseriesUnit.GIGA_WATT));

        assertFalse(TimeseriesUnit.EURO.isCompatible(TimeseriesUnit.WATT_SECOND));
        assertFalse(TimeseriesUnit.EURO.isCompatible(TimeseriesUnit.WATT_HOUR));
        assertFalse(TimeseriesUnit.EURO.isCompatible(TimeseriesUnit.KILO_WATT_HOUR));
        assertFalse(TimeseriesUnit.EURO.isCompatible(TimeseriesUnit.MEGA_WATT_HOUR));
        assertFalse(TimeseriesUnit.EURO.isCompatible(TimeseriesUnit.GIGA_WATT_HOUR));

        assertTrue(TimeseriesUnit.EURO.isCompatible(TimeseriesUnit.EURO));
        assertTrue(TimeseriesUnit.EURO.isCompatible(TimeseriesUnit.EURO_CENT));

        assertFalse(TimeseriesUnit.EURO.isCompatible(TimeseriesUnit.EURO_CENT_PER_KILO_WATT_HOUR));
        assertFalse(TimeseriesUnit.EURO.isCompatible(TimeseriesUnit.EURO_PER_KILO_WATT));
        assertFalse(TimeseriesUnit.EURO.isCompatible(TimeseriesUnit.EURO_PER_MEGA_WATT));

        assertFalse(TimeseriesUnit.EURO.isCompatible(TimeseriesUnit.BURN_VALUE));
    }

    @Test
    public void testCompatibilityEURO_CENT() {
        assertFalse(TimeseriesUnit.EURO_CENT.isCompatible(TimeseriesUnit.NONE));
        assertFalse(TimeseriesUnit.EURO_CENT.isCompatible(TimeseriesUnit.UNKNOWN));

        assertFalse(TimeseriesUnit.EURO_CENT.isCompatible(TimeseriesUnit.CUBIC_METRE));
        assertFalse(TimeseriesUnit.EURO_CENT.isCompatible(TimeseriesUnit.LITER));

        assertFalse(TimeseriesUnit.EURO_CENT.isCompatible(TimeseriesUnit.WATT));
        assertFalse(TimeseriesUnit.EURO_CENT.isCompatible(TimeseriesUnit.KILO_WATT));
        assertFalse(TimeseriesUnit.EURO_CENT.isCompatible(TimeseriesUnit.MEGA_WATT));
        assertFalse(TimeseriesUnit.EURO_CENT.isCompatible(TimeseriesUnit.GIGA_WATT));

        assertFalse(TimeseriesUnit.EURO_CENT.isCompatible(TimeseriesUnit.WATT_SECOND));
        assertFalse(TimeseriesUnit.EURO_CENT.isCompatible(TimeseriesUnit.WATT_HOUR));
        assertFalse(TimeseriesUnit.EURO_CENT.isCompatible(TimeseriesUnit.KILO_WATT_HOUR));
        assertFalse(TimeseriesUnit.EURO_CENT.isCompatible(TimeseriesUnit.MEGA_WATT_HOUR));
        assertFalse(TimeseriesUnit.EURO_CENT.isCompatible(TimeseriesUnit.GIGA_WATT_HOUR));

        assertTrue(TimeseriesUnit.EURO_CENT.isCompatible(TimeseriesUnit.EURO));
        assertTrue(TimeseriesUnit.EURO_CENT.isCompatible(TimeseriesUnit.EURO_CENT));

        assertFalse(TimeseriesUnit.EURO_CENT.isCompatible(TimeseriesUnit.EURO_CENT_PER_KILO_WATT_HOUR));
        assertFalse(TimeseriesUnit.EURO_CENT.isCompatible(TimeseriesUnit.EURO_PER_KILO_WATT));
        assertFalse(TimeseriesUnit.EURO_CENT.isCompatible(TimeseriesUnit.EURO_PER_MEGA_WATT));

        assertFalse(TimeseriesUnit.EURO_CENT.isCompatible(TimeseriesUnit.BURN_VALUE));
    }

    @Test
    public void testCompatibilityEURO_CENT_PER_KILO_WATT_HOUR() {
        assertFalse(TimeseriesUnit.EURO_CENT_PER_KILO_WATT_HOUR.isCompatible(TimeseriesUnit.NONE));
        assertFalse(TimeseriesUnit.EURO_CENT_PER_KILO_WATT_HOUR.isCompatible(TimeseriesUnit.UNKNOWN));

        assertFalse(TimeseriesUnit.EURO_CENT_PER_KILO_WATT_HOUR.isCompatible(TimeseriesUnit.CUBIC_METRE));
        assertFalse(TimeseriesUnit.EURO_CENT_PER_KILO_WATT_HOUR.isCompatible(TimeseriesUnit.LITER));

        assertFalse(TimeseriesUnit.EURO_CENT_PER_KILO_WATT_HOUR.isCompatible(TimeseriesUnit.WATT));
        assertFalse(TimeseriesUnit.EURO_CENT_PER_KILO_WATT_HOUR.isCompatible(TimeseriesUnit.KILO_WATT));
        assertFalse(TimeseriesUnit.EURO_CENT_PER_KILO_WATT_HOUR.isCompatible(TimeseriesUnit.MEGA_WATT));
        assertFalse(TimeseriesUnit.EURO_CENT_PER_KILO_WATT_HOUR.isCompatible(TimeseriesUnit.GIGA_WATT));

        assertFalse(TimeseriesUnit.EURO_CENT_PER_KILO_WATT_HOUR.isCompatible(TimeseriesUnit.WATT_SECOND));
        assertFalse(TimeseriesUnit.EURO_CENT_PER_KILO_WATT_HOUR.isCompatible(TimeseriesUnit.WATT_HOUR));
        assertFalse(TimeseriesUnit.EURO_CENT_PER_KILO_WATT_HOUR.isCompatible(TimeseriesUnit.KILO_WATT_HOUR));
        assertFalse(TimeseriesUnit.EURO_CENT_PER_KILO_WATT_HOUR.isCompatible(TimeseriesUnit.MEGA_WATT_HOUR));
        assertFalse(TimeseriesUnit.EURO_CENT_PER_KILO_WATT_HOUR.isCompatible(TimeseriesUnit.GIGA_WATT_HOUR));

        assertFalse(TimeseriesUnit.EURO_CENT_PER_KILO_WATT_HOUR.isCompatible(TimeseriesUnit.EURO));
        assertFalse(TimeseriesUnit.EURO_CENT_PER_KILO_WATT_HOUR.isCompatible(TimeseriesUnit.EURO_CENT));

        assertTrue(TimeseriesUnit.EURO_CENT_PER_KILO_WATT_HOUR.isCompatible(TimeseriesUnit.EURO_CENT_PER_KILO_WATT_HOUR));
        assertFalse(TimeseriesUnit.EURO_CENT_PER_KILO_WATT_HOUR.isCompatible(TimeseriesUnit.EURO_PER_KILO_WATT));
        assertFalse(TimeseriesUnit.EURO_CENT_PER_KILO_WATT_HOUR.isCompatible(TimeseriesUnit.EURO_PER_MEGA_WATT));

        assertFalse(TimeseriesUnit.EURO_CENT_PER_KILO_WATT_HOUR.isCompatible(TimeseriesUnit.BURN_VALUE));
    }

    @Test
    public void testCompatibilityEURO_PER_KILO_WATT() {
        assertFalse(TimeseriesUnit.EURO_PER_KILO_WATT.isCompatible(TimeseriesUnit.NONE));
        assertFalse(TimeseriesUnit.EURO_PER_KILO_WATT.isCompatible(TimeseriesUnit.UNKNOWN));

        assertFalse(TimeseriesUnit.EURO_PER_KILO_WATT.isCompatible(TimeseriesUnit.CUBIC_METRE));
        assertFalse(TimeseriesUnit.EURO_PER_KILO_WATT.isCompatible(TimeseriesUnit.LITER));

        assertFalse(TimeseriesUnit.EURO_PER_KILO_WATT.isCompatible(TimeseriesUnit.WATT));
        assertFalse(TimeseriesUnit.EURO_PER_KILO_WATT.isCompatible(TimeseriesUnit.KILO_WATT));
        assertFalse(TimeseriesUnit.EURO_PER_KILO_WATT.isCompatible(TimeseriesUnit.MEGA_WATT));
        assertFalse(TimeseriesUnit.EURO_PER_KILO_WATT.isCompatible(TimeseriesUnit.GIGA_WATT));

        assertFalse(TimeseriesUnit.EURO_PER_KILO_WATT.isCompatible(TimeseriesUnit.WATT_SECOND));
        assertFalse(TimeseriesUnit.EURO_PER_KILO_WATT.isCompatible(TimeseriesUnit.WATT_HOUR));
        assertFalse(TimeseriesUnit.EURO_PER_KILO_WATT.isCompatible(TimeseriesUnit.KILO_WATT_HOUR));
        assertFalse(TimeseriesUnit.EURO_PER_KILO_WATT.isCompatible(TimeseriesUnit.MEGA_WATT_HOUR));
        assertFalse(TimeseriesUnit.EURO_PER_KILO_WATT.isCompatible(TimeseriesUnit.GIGA_WATT_HOUR));

        assertFalse(TimeseriesUnit.EURO_PER_KILO_WATT.isCompatible(TimeseriesUnit.EURO));
        assertFalse(TimeseriesUnit.EURO_PER_KILO_WATT.isCompatible(TimeseriesUnit.EURO_CENT));

        assertFalse(TimeseriesUnit.EURO_PER_KILO_WATT.isCompatible(TimeseriesUnit.EURO_CENT_PER_KILO_WATT_HOUR));
        assertTrue(TimeseriesUnit.EURO_PER_KILO_WATT.isCompatible(TimeseriesUnit.EURO_PER_KILO_WATT));
        assertTrue(TimeseriesUnit.EURO_PER_KILO_WATT.isCompatible(TimeseriesUnit.EURO_PER_MEGA_WATT));

        assertFalse(TimeseriesUnit.EURO_PER_KILO_WATT.isCompatible(TimeseriesUnit.BURN_VALUE));
    }

    @Test
    public void testCompatibilityEURO_PER_MEGA_WATT() {
        assertFalse(TimeseriesUnit.EURO_PER_MEGA_WATT.isCompatible(TimeseriesUnit.NONE));
        assertFalse(TimeseriesUnit.EURO_PER_MEGA_WATT.isCompatible(TimeseriesUnit.UNKNOWN));

        assertFalse(TimeseriesUnit.EURO_PER_MEGA_WATT.isCompatible(TimeseriesUnit.CUBIC_METRE));
        assertFalse(TimeseriesUnit.EURO_PER_MEGA_WATT.isCompatible(TimeseriesUnit.LITER));

        assertFalse(TimeseriesUnit.EURO_PER_MEGA_WATT.isCompatible(TimeseriesUnit.WATT));
        assertFalse(TimeseriesUnit.EURO_PER_MEGA_WATT.isCompatible(TimeseriesUnit.KILO_WATT));
        assertFalse(TimeseriesUnit.EURO_PER_MEGA_WATT.isCompatible(TimeseriesUnit.MEGA_WATT));
        assertFalse(TimeseriesUnit.EURO_PER_MEGA_WATT.isCompatible(TimeseriesUnit.GIGA_WATT));

        assertFalse(TimeseriesUnit.EURO_PER_MEGA_WATT.isCompatible(TimeseriesUnit.WATT_SECOND));
        assertFalse(TimeseriesUnit.EURO_PER_MEGA_WATT.isCompatible(TimeseriesUnit.WATT_HOUR));
        assertFalse(TimeseriesUnit.EURO_PER_MEGA_WATT.isCompatible(TimeseriesUnit.KILO_WATT_HOUR));
        assertFalse(TimeseriesUnit.EURO_PER_MEGA_WATT.isCompatible(TimeseriesUnit.MEGA_WATT_HOUR));
        assertFalse(TimeseriesUnit.EURO_PER_MEGA_WATT.isCompatible(TimeseriesUnit.GIGA_WATT_HOUR));

        assertFalse(TimeseriesUnit.EURO_PER_MEGA_WATT.isCompatible(TimeseriesUnit.EURO));
        assertFalse(TimeseriesUnit.EURO_PER_MEGA_WATT.isCompatible(TimeseriesUnit.EURO_CENT));

        assertFalse(TimeseriesUnit.EURO_PER_MEGA_WATT.isCompatible(TimeseriesUnit.EURO_CENT_PER_KILO_WATT_HOUR));
        assertTrue(TimeseriesUnit.EURO_PER_MEGA_WATT.isCompatible(TimeseriesUnit.EURO_PER_KILO_WATT));
        assertTrue(TimeseriesUnit.EURO_PER_MEGA_WATT.isCompatible(TimeseriesUnit.EURO_PER_MEGA_WATT));

        assertFalse(TimeseriesUnit.EURO_PER_MEGA_WATT.isCompatible(TimeseriesUnit.BURN_VALUE));
    }

    @Test
    public void testCompatibilityBURN_VALUE() {
        assertFalse(TimeseriesUnit.BURN_VALUE.isCompatible(TimeseriesUnit.NONE));
        assertFalse(TimeseriesUnit.BURN_VALUE.isCompatible(TimeseriesUnit.UNKNOWN));

        assertFalse(TimeseriesUnit.BURN_VALUE.isCompatible(TimeseriesUnit.CUBIC_METRE));
        assertFalse(TimeseriesUnit.BURN_VALUE.isCompatible(TimeseriesUnit.LITER));

        assertFalse(TimeseriesUnit.BURN_VALUE.isCompatible(TimeseriesUnit.WATT));
        assertFalse(TimeseriesUnit.BURN_VALUE.isCompatible(TimeseriesUnit.KILO_WATT));
        assertFalse(TimeseriesUnit.BURN_VALUE.isCompatible(TimeseriesUnit.MEGA_WATT));
        assertFalse(TimeseriesUnit.BURN_VALUE.isCompatible(TimeseriesUnit.GIGA_WATT));

        assertFalse(TimeseriesUnit.BURN_VALUE.isCompatible(TimeseriesUnit.WATT_SECOND));
        assertFalse(TimeseriesUnit.BURN_VALUE.isCompatible(TimeseriesUnit.WATT_HOUR));
        assertFalse(TimeseriesUnit.BURN_VALUE.isCompatible(TimeseriesUnit.KILO_WATT_HOUR));
        assertFalse(TimeseriesUnit.BURN_VALUE.isCompatible(TimeseriesUnit.MEGA_WATT_HOUR));
        assertFalse(TimeseriesUnit.BURN_VALUE.isCompatible(TimeseriesUnit.GIGA_WATT_HOUR));

        assertFalse(TimeseriesUnit.BURN_VALUE.isCompatible(TimeseriesUnit.EURO));
        assertFalse(TimeseriesUnit.BURN_VALUE.isCompatible(TimeseriesUnit.EURO_CENT));

        assertFalse(TimeseriesUnit.BURN_VALUE.isCompatible(TimeseriesUnit.EURO_CENT_PER_KILO_WATT_HOUR));
        assertFalse(TimeseriesUnit.BURN_VALUE.isCompatible(TimeseriesUnit.EURO_PER_KILO_WATT));
        assertFalse(TimeseriesUnit.BURN_VALUE.isCompatible(TimeseriesUnit.EURO_PER_MEGA_WATT));

        assertTrue(TimeseriesUnit.BURN_VALUE.isCompatible(TimeseriesUnit.BURN_VALUE));
    }

    @Test
    public void testVolumeConversion() {
        Amount<?> v1 = Amount.valueOf(1000L, TimeseriesUnit.LITER.toMeasurementUnit());
        Amount<?> v2;

        assertEquals("1000 L", v1.toString());

        v2 = v1.to(TimeseriesUnit.CUBIC_METRE.toMeasurementUnit());
        assertEquals(1.0, v2.getEstimatedValue(), v2.getMaximumValue() - v2.getMinimumValue());
    }

    @Test
    public void testPowerConversion() {
        Amount<?> v1 = Amount.valueOf(1000000000L, TimeseriesUnit.WATT.toMeasurementUnit());
        Amount<?> v2;

        assertTrue(v1.toString().equals("1000000000 W"));

        v2 = v1.to(TimeseriesUnit.KILO_WATT.toMeasurementUnit());
        assertTrue(v2.toString().equals("1000000 kW"));

        v2 = v1.to(TimeseriesUnit.MEGA_WATT.toMeasurementUnit());
        assertTrue(v2.toString().equals("1000 MW"));

        v2 = v1.to(TimeseriesUnit.GIGA_WATT.toMeasurementUnit());
        assertTrue(v2.toString().equals("1 GW"));
    }

    @Test
    public void testEnergyConversion() {
        Amount<?> v1 = Amount.valueOf(3600000000000L, TimeseriesUnit.WATT_SECOND.toMeasurementUnit());
        Amount<?> v2;

        assertTrue(v1.toString().equals("3600000000000 Ws"));

        v2 = v1.to(TimeseriesUnit.WATT_HOUR.toMeasurementUnit());
        assertTrue(v2.toString().equals("1000000000 Wh"));

        v2 = v1.to(TimeseriesUnit.KILO_WATT_HOUR.toMeasurementUnit());
        assertTrue(v2.toString().equals("1000000 kWh"));

        v2 = v1.to(TimeseriesUnit.MEGA_WATT_HOUR.toMeasurementUnit());
        assertTrue(v2.toString().equals("1000 MWh"));

        v2 = v1.to(TimeseriesUnit.GIGA_WATT_HOUR.toMeasurementUnit());
        assertTrue(v2.toString().equals("1 GWh"));
    }

    @Test
    public void testMoneyConversion() {
        Amount<?> v1 = Amount.valueOf(1L, TimeseriesUnit.EURO.toMeasurementUnit());
        Amount<?> v2;

        assertEquals("1.00 EUR", v1.toString());

        v2 = v1.to(TimeseriesUnit.EURO_CENT.toMeasurementUnit());
        assertEquals("100.0 ct", v2.getEstimatedValue() + " " + v2.getUnit().toString());
    }

    @Test
    public void testPricingConversion() {
        Amount<?> v1 = Amount.valueOf(1L, TimeseriesUnit.EURO_PER_KILO_WATT.toMeasurementUnit());
        Amount<?> v2;

        assertEquals("1 EUR/kW", v1.toString());

        v2 = v1.to(TimeseriesUnit.EURO_PER_MEGA_WATT.toMeasurementUnit());
        assertEquals("1000.0 EUR/MW", v2.getEstimatedValue() + " " + v2.getUnit().toString());
    }

    @Test
    public void testMultiplyEnergyPricing() {
        Amount<?> v1 = Amount.valueOf(5L, TimeseriesUnit.KILO_WATT_HOUR.toMeasurementUnit());
        Amount<?> v2 = Amount.valueOf(2L, TimeseriesUnit.EURO_CENT_PER_KILO_WATT_HOUR.toMeasurementUnit());
        Amount<?> v3;

        assertEquals("5 kWh", v1.toString());
        assertEquals("2 ct/kWh", v2.toString());

        v3 = v1.times(v2);
        assertEquals("10.0 ct", v3.getEstimatedValue() + " " + v3.getUnit().toString());
    }

    @Test
    public void testMultiplyPowerPricing() {
        Amount<?> v1 = Amount.valueOf(5L, TimeseriesUnit.KILO_WATT.toMeasurementUnit());
        Amount<?> v2 = Amount.valueOf(2L, TimeseriesUnit.EURO_PER_KILO_WATT.toMeasurementUnit());
        Amount<?> v3;

        assertEquals("5 kW", v1.toString());
        assertEquals("2 EUR/kW", v2.toString());

        v3 = v1.times(v2);
        assertEquals("10.0 EUR", v3.getEstimatedValue() + " " + v3.getUnit().toString());

        v1 = Amount.valueOf(5L, TimeseriesUnit.MEGA_WATT.toMeasurementUnit());
        v2 = Amount.valueOf(2L, TimeseriesUnit.EURO_PER_MEGA_WATT.toMeasurementUnit());

        assertEquals("5 MW", v1.toString());
        assertEquals("2 EUR/MW", v2.toString());

        v3 = v1.times(v2);
        assertEquals("10.0 EUR", v3.getEstimatedValue() + " " + v3.getUnit().toString());
    }

    @Test
    public void testIsTimeintegralOf() {
        assertFalse(TimeseriesUnit.WATT.isTimeintegral());
        assertTrue(TimeseriesUnit.WATT_SECOND.isTimeintegral());
        assertTrue(TimeseriesUnit.WATT_SECOND.isTimeintegralOf(TimeseriesUnit.WATT));

        assertFalse(TimeseriesUnit.WATT.isTimeintegral());
        assertTrue(TimeseriesUnit.WATT_HOUR.isTimeintegral());
        assertTrue(TimeseriesUnit.WATT_HOUR.isTimeintegralOf(TimeseriesUnit.WATT));

        assertFalse(TimeseriesUnit.KILO_WATT.isTimeintegral());
        assertTrue(TimeseriesUnit.KILO_WATT_HOUR.isTimeintegral());
        assertTrue(TimeseriesUnit.KILO_WATT_HOUR.isTimeintegralOf(TimeseriesUnit.WATT));

        assertFalse(TimeseriesUnit.WATT.isTimeintegralOf(TimeseriesUnit.WATT));
        assertFalse(TimeseriesUnit.KILO_WATT.isTimeintegralOf(TimeseriesUnit.WATT));
    }

}
