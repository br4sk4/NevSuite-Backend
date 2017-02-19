package net.naffets.nevsuite.test.unit.timeseries.core.valueplugin;

import junit.framework.TestCase;
import net.naffets.nevsuite.backend.timeseries.core.valueplugin.LongPlugin;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author br4sk4
 * created on 22.03.2016
 */
public class LongPluginTest extends TestCase {

    private LongPlugin plugin = new LongPlugin();

    @Test
    public void testAddOperation() {
        assertEquals(new Long(5), plugin.add(2L, 3L));
    }

    @Test
    public void testSubtractOperation() {
        assertEquals(new Long(3), plugin.subtract(5L, 2L));
    }

    @Test
    public void testMultiplyOperation() {
        assertEquals(new Long(6), plugin.multiply(2L, 3L));
    }

    @Test
    public void testDivideOperation() {
        assertEquals(new Long(4), plugin.divide(8L, 2L));
    }

    @Test
    public void testDivideOperationFloating() {
        assertEquals(new Long(3), plugin.divide(7L, 2L));
    }

    @Test
    public void testMaxOperationFirst() {
        assertEquals(new Long(2), plugin.max(2L, 1L));
    }

    @Test
    public void testMaxOperationOther() {
        assertEquals(new Long(2), plugin.max(1L, 2L));
    }

    @Test
    public void testMinOperationFirst() {
        assertEquals(new Long(1), plugin.min(1L, 2L));
    }

    @Test
    public void testMinOperationOther() {
        assertEquals(new Long(1), plugin.min(2L, 1L));
    }

    @Test
    public void testIntValue() {
        assertEquals(new Integer(1), plugin.intValue(1L));
    }

    @Test
    public void testLongValue() {
        assertEquals(new Long(1), plugin.longValue(1L));
    }

    @Test
    public void testDoubleValue() {
        assertEquals(1.0d, plugin.doubleValue(1L));
    }

    @Test
    public void testBigDecimalValue() {
        assertEquals(new BigDecimal("1"), plugin.bigDecimalValue(1L));
    }

}
