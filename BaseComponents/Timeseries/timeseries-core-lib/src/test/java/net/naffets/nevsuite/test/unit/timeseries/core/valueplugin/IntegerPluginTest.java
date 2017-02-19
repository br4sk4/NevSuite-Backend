package net.naffets.nevsuite.test.unit.timeseries.core.valueplugin;

import junit.framework.TestCase;
import net.naffets.nevsuite.backend.timeseries.core.valueplugin.IntegerPlugin;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author br4sk4
 * created on 23.03.2016
 */
public class IntegerPluginTest extends TestCase {

    private IntegerPlugin plugin = new IntegerPlugin();

    @Test
    public void testAddOperation() {
        assertEquals(new Integer(5), plugin.add(2, 3));
    }

    @Test
    public void testSubtractOperation() {
        assertEquals(new Integer(3), plugin.subtract(5, 2));
    }

    @Test
    public void testMultiplyOperation() {
        assertEquals(new Integer(6), plugin.multiply(2, 3));
    }

    @Test
    public void testDivideOperation() {
        assertEquals(new Integer(4), plugin.divide(8, 2));
    }

    @Test
    public void testDivideOperationFloating() {
        assertEquals(new Integer(3), plugin.divide(7, 2));
    }

    @Test
    public void testMaxOperationFirst() {
        assertEquals(new Integer(2), plugin.max(2, 1));
    }

    @Test
    public void testMaxOperationOther() {
        assertEquals(new Integer(2), plugin.max(1, 2));
    }

    @Test
    public void testMinOperationFirst() {
        assertEquals(new Integer(1), plugin.min(1, 2));
    }

    @Test
    public void testMinOperationOther() {
        assertEquals(new Integer(1), plugin.min(2, 1));
    }

    @Test
    public void testIntValue() {
        assertEquals(new Integer(1), plugin.intValue(1));
    }

    @Test
    public void testLongValue() {
        assertEquals(new Long(1), plugin.longValue(1));
    }

    @Test
    public void testDoubleValue() {
        assertEquals(1.0d, plugin.doubleValue(1));
    }

    @Test
    public void testBigDecimalValue() {
        assertEquals(new BigDecimal("1"), plugin.bigDecimalValue(1));
    }

}
