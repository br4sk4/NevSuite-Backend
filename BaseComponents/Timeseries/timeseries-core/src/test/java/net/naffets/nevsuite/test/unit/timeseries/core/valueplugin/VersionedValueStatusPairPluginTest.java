package net.naffets.nevsuite.test.unit.timeseries.core.valueplugin;

import junit.framework.TestCase;
import net.naffets.nevsuite.backend.timeseries.core.datatype.VersionedValueStatusPair;
import net.naffets.nevsuite.backend.timeseries.core.valueplugin.DoublePlugin;
import net.naffets.nevsuite.backend.timeseries.core.valueplugin.VersionedValueStatusPairPlugin;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * @author br4sk4
 * created on 23.03.2016
 */
public class VersionedValueStatusPairPluginTest extends TestCase {

    private VersionedValueStatusPairPlugin<Double, byte[]> plugin = new VersionedValueStatusPairPlugin<Double, byte[]>(new DoublePlugin(), new byte[8]);
    private Instant version = Instant.now();

    @Test
    public void testAddOperation() {
        assertTrue(
                new VersionedValueStatusPair<Double, byte[]>(5.05d, new byte[8], version).equals(
                        plugin.add(
                                new VersionedValueStatusPair<Double, byte[]>(2.02d, new byte[8], version),
                                new VersionedValueStatusPair<Double, byte[]>(3.03d, new byte[8], version)
                        )
                )
        );
    }

    @Test
    public void testSubtractOperation() {
        assertTrue(
                new VersionedValueStatusPair<Double, byte[]>(3.03d, new byte[8], version).equals(
                        plugin.subtract(
                                new VersionedValueStatusPair<Double, byte[]>(5.05d, new byte[8], version),
                                new VersionedValueStatusPair<Double, byte[]>(2.02d, new byte[8], version)
                        )
                )
        );
    }

    @Test
    public void testMultiplyOperation() {
        assertTrue(
                new VersionedValueStatusPair<Double, byte[]>(6d, new byte[8], version).equals(
                        plugin.multiply(
                                new VersionedValueStatusPair<Double, byte[]>(2d, new byte[8], version),
                                new VersionedValueStatusPair<Double, byte[]>(3d, new byte[8], version)
                        )
                )
        );
    }

    @Test
    public void testMultiplyOperationFloating() {
        assertTrue(
                new VersionedValueStatusPair<Double, byte[]>(7.26d, new byte[8], version).equals(
                        plugin.multiply(
                                new VersionedValueStatusPair<Double, byte[]>(2.2d, new byte[8], version),
                                new VersionedValueStatusPair<Double, byte[]>(3.3d, new byte[8], version)
                        )
                )
        );
    }

    @Test
    public void testDivideOperation() {
        assertTrue(
                new VersionedValueStatusPair<Double, byte[]>(4d, new byte[8], version).equals(
                        plugin.divide(
                                new VersionedValueStatusPair<Double, byte[]>(8d, new byte[8], version),
                                new VersionedValueStatusPair<Double, byte[]>(2d, new byte[8], version)
                        )
                )
        );
    }

    @Test
    public void testDivideOperationFloating() {
        assertTrue(
                new VersionedValueStatusPair<Double, byte[]>(1.25d, new byte[8], version).equals(
                        plugin.divide(
                                new VersionedValueStatusPair<Double, byte[]>(2.5d, new byte[8], version),
                                new VersionedValueStatusPair<Double, byte[]>(2d, new byte[8], version)
                        )
                )
        );
    }

    @Test
    public void testMaxOperationFirst() {
        assertTrue(
                new VersionedValueStatusPair<Double, byte[]>(1.5d, new byte[8], version).equals(
                        plugin.max(
                                new VersionedValueStatusPair<Double, byte[]>(1.5d, new byte[8], version),
                                new VersionedValueStatusPair<Double, byte[]>(1.25d, new byte[8], version)
                        )
                )
        );
    }

    @Test
    public void testMaxOperationOther() {
        assertTrue(
                new VersionedValueStatusPair<Double, byte[]>(1.5d, new byte[8], version).equals(
                        plugin.max(
                                new VersionedValueStatusPair<Double, byte[]>(1.25d, new byte[8], version),
                                new VersionedValueStatusPair<Double, byte[]>(1.5d, new byte[8], version)
                        )
                )
        );
    }

    @Test
    public void testMinOperationFirst() {
        assertTrue(
                new VersionedValueStatusPair<Double, byte[]>(1.25d, new byte[8], version).equals(
                        plugin.min(
                                new VersionedValueStatusPair<Double, byte[]>(1.25d, new byte[8], version),
                                new VersionedValueStatusPair<Double, byte[]>(1.5d, new byte[8], version)
                        )
                )
        );
    }

    @Test
    public void testMinOperationOther() {
        assertTrue(
                new VersionedValueStatusPair<Double, byte[]>(1.25d, new byte[8], version).equals(
                        plugin.min(
                                new VersionedValueStatusPair<Double, byte[]>(1.5d, new byte[8], version),
                                new VersionedValueStatusPair<Double, byte[]>(1.25d, new byte[8], version)
                        )
                )
        );
    }

    @Test
    public void testIntValue() {
        assertEquals(new Integer(1), plugin.intValue(new VersionedValueStatusPair<Double, byte[]>(1.5d, new byte[8], version)));
    }

    @Test
    public void testLongValue() {
        assertEquals(new Long(1), plugin.longValue(new VersionedValueStatusPair<Double, byte[]>(1.5d, new byte[8], version)));
    }

    @Test
    public void testDoubleValue() {
        assertEquals(1.5d, plugin.doubleValue(new VersionedValueStatusPair<Double, byte[]>(1.5d, new byte[8], version)));
    }

    @Test
    public void testBigDecimalValue() {
        assertEquals(new BigDecimal("1.5"), plugin.bigDecimalValue(new VersionedValueStatusPair<Double, byte[]>(1.5d, new byte[8], version)));
    }

}
