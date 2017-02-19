package net.naffets.nevsuite.test.unit.timeseries.core.valueplugin;

import junit.framework.TestCase;
import net.naffets.nevsuite.backend.timeseries.core.datatype.ValueStatusPair;
import net.naffets.nevsuite.backend.timeseries.core.valueplugin.DoublePlugin;
import net.naffets.nevsuite.backend.timeseries.core.valueplugin.ValueStatusPairPlugin;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author br4sk4
 * created on 22.03.2016
 */
public class ValueStatusPairPluginTest extends TestCase {

    private ValueStatusPairPlugin<Double, byte[]> plugin = new ValueStatusPairPlugin<Double, byte[]>(new DoublePlugin(), new byte[8]);

    @Test
    public void testAddOperation() {
        assertTrue(
                new ValueStatusPair<Double, byte[]>(5.05d, new byte[8]).equals(
                        plugin.add(
                                new ValueStatusPair<Double, byte[]>(2.02d, new byte[8]),
                                new ValueStatusPair<Double, byte[]>(3.03d, new byte[8])
                        )
                )
        );
    }

    @Test
    public void testSubtractOperation() {
        assertTrue(
                new ValueStatusPair<Double, byte[]>(3.03d, new byte[8]).equals(
                        plugin.subtract(
                                new ValueStatusPair<Double, byte[]>(5.05d, new byte[8]),
                                new ValueStatusPair<Double, byte[]>(2.02d, new byte[8])
                        )
                )
        );
    }

    @Test
    public void testMultiplyOperation() {
        assertTrue(
                new ValueStatusPair<Double, byte[]>(6d, new byte[8]).equals(
                        plugin.multiply(
                                new ValueStatusPair<Double, byte[]>(2d, new byte[8]),
                                new ValueStatusPair<Double, byte[]>(3d, new byte[8])
                        )
                )
        );
    }

    @Test
    public void testMultiplyOperationFloating() {
        assertTrue(
                new ValueStatusPair<Double, byte[]>(7.26d, new byte[8]).equals(
                        plugin.multiply(
                                new ValueStatusPair<Double, byte[]>(2.2d, new byte[8]),
                                new ValueStatusPair<Double, byte[]>(3.3d, new byte[8])
                        )
                )
        );
    }

    @Test
    public void testDivideOperation() {
        assertTrue(
                new ValueStatusPair<Double, byte[]>(4d, new byte[8]).equals(
                        plugin.divide(
                                new ValueStatusPair<Double, byte[]>(8d, new byte[8]),
                                new ValueStatusPair<Double, byte[]>(2d, new byte[8])
                        )
                )
        );
    }

    @Test
    public void testDivideOperationFloating() {
        assertTrue(
                new ValueStatusPair<Double, byte[]>(1.25d, new byte[8]).equals(
                        plugin.divide(
                                new ValueStatusPair<Double, byte[]>(2.5d, new byte[8]),
                                new ValueStatusPair<Double, byte[]>(2d, new byte[8])
                        )
                )
        );
    }

    @Test
    public void testMaxOperationFirst() {
        assertTrue(
                new ValueStatusPair<Double, byte[]>(1.5d, new byte[8]).equals(
                        plugin.max(
                                new ValueStatusPair<Double, byte[]>(1.5d, new byte[8]),
                                new ValueStatusPair<Double, byte[]>(1.25d, new byte[8])
                        )
                )
        );
    }

    @Test
    public void testMaxOperationOther() {
        assertTrue(
                new ValueStatusPair<Double, byte[]>(1.5d, new byte[8]).equals(
                        plugin.max(
                                new ValueStatusPair<Double, byte[]>(1.25d, new byte[8]),
                                new ValueStatusPair<Double, byte[]>(1.5d, new byte[8])
                        )
                )
        );
    }

    @Test
    public void testMinOperationFirst() {
        assertTrue(
                new ValueStatusPair<Double, byte[]>(1.25d, new byte[8]).equals(
                        plugin.min(
                                new ValueStatusPair<Double, byte[]>(1.25d, new byte[8]),
                                new ValueStatusPair<Double, byte[]>(1.5d, new byte[8])
                        )
                )
        );
    }

    @Test
    public void testMinOperationOther() {
        assertTrue(
                new ValueStatusPair<Double, byte[]>(1.25d, new byte[8]).equals(
                        plugin.min(
                                new ValueStatusPair<Double, byte[]>(1.5d, new byte[8]),
                                new ValueStatusPair<Double, byte[]>(1.25d, new byte[8])
                        )
                )
        );
    }

    @Test
    public void testIntValue() {
        assertEquals(new Integer(1), plugin.intValue(new ValueStatusPair<Double, byte[]>(1.5d, new byte[8])));
    }

    @Test
    public void testLongValue() {
        assertEquals(new Long(1), plugin.longValue(new ValueStatusPair<Double, byte[]>(1.5d, new byte[8])));
    }

    @Test
    public void testDoubleValue() {
        assertEquals(1.5d, plugin.doubleValue(new ValueStatusPair<Double, byte[]>(1.5d, new byte[8])));
    }

    @Test
    public void testBigDecimalValue() {
        assertEquals(new BigDecimal("1.5"), plugin.bigDecimalValue(new ValueStatusPair<Double, byte[]>(1.5d, new byte[8])));
    }

}
