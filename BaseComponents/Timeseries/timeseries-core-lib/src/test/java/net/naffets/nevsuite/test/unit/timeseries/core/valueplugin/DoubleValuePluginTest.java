package net.naffets.nevsuite.test.unit.timeseries.core.valueplugin;

import junit.framework.TestCase;
import net.naffets.nevsuite.backend.timeseries.core.valueplugin.DoublePlugin;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author br4sk4
 * created on 22.03.2016
 */
public class DoubleValuePluginTest extends TestCase {

    private DoublePlugin plugin = new DoublePlugin();

    @Test
    public void testAddOperation() {
        assertEquals(5.05d, plugin.add(2.02d, 3.03d));
    }

    @Test
    public void testSubtractOperation() {
        assertEquals(3.03d, plugin.subtract(5.05d, 2.02d));
    }

    @Test
    public void testMultiplyOperationFloating() {
        assertEquals(6d, plugin.multiply(2d, 3d));
    }

    @Test
    public void testMultiplyOperation() {
        assertEquals(7.26d, plugin.multiply(2.2d, 3.3d));
    }

    @Test
    public void testDivideOperation() {
        assertEquals(4d, plugin.divide(8d, 2d));
    }

    @Test
    public void testDivideOperationFloating() {
        assertEquals(1.25d, plugin.divide(2.5d, 2.0d));
    }

    @Test
    public void testMaxOperationFirst() {
        assertEquals(1.5d, plugin.max(1.5d, 1.25d));
    }

    @Test
    public void testMaxOperationOther() {
        assertEquals(1.5d, plugin.max(1.5d, 1.25d));
    }

    @Test
    public void testMinOperationFirst() {
        assertEquals(1.25d, plugin.min(1.25d, 1.5d));
    }

    @Test
    public void testMinOperationOther() {
        assertEquals(1.25d, plugin.min(1.5d, 1.25d));
    }

    @Test
    public void testIntValue() {
        assertEquals(new Integer(1), plugin.intValue(1.5d));
    }

    @Test
    public void testLongValue() {
        assertEquals(new Long(1), plugin.longValue(1.5d));
    }

    @Test
    public void testDoubleValue() {
        assertEquals(1.5d, plugin.doubleValue(1.5d));
    }

    @Test
    public void testBigDecimalValue() {
        assertEquals(new BigDecimal("1.5"), plugin.bigDecimalValue(1.5d));
    }

}
