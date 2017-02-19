package net.naffets.nevsuite.test.unit.timeseries.core.valueplugin;

import junit.framework.TestCase;
import net.naffets.nevsuite.backend.timeseries.core.valueplugin.BigDecimalPlugin;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author br4sk4
 * created on 22.03.2016
 */
public class BigDecimalPluginTest extends TestCase {

    private BigDecimalPlugin plugin = new BigDecimalPlugin();

    @Test
    public void testAddOperation() {
        assertEquals(new BigDecimal(5), plugin.add(new BigDecimal(2), new BigDecimal(3)));
    }

    @Test
    public void testSubtractOperation() {
        assertEquals(new BigDecimal(3), plugin.subtract(new BigDecimal(5), new BigDecimal(2)));
    }

    @Test
    public void testMultiplyOperation() {
        assertEquals(new BigDecimal(6), plugin.multiply(new BigDecimal(2), new BigDecimal(3)));
    }

    @Test
    public void testMultiplyOperationFloating() {
        assertEquals(new BigDecimal("7.26"), plugin.multiply(new BigDecimal("2.2"), new BigDecimal("3.3")));
    }

    @Test
    public void testDivideOperation() {
        assertEquals(new BigDecimal("4"), plugin.divide(new BigDecimal("8.0"), new BigDecimal("2.0")));
    }

    @Test
    public void testDivideOperationFloating() {
        assertEquals(new BigDecimal("1.25"), plugin.divide(new BigDecimal("2.5"), new BigDecimal("2.0")));
    }

    @Test
    public void testMaxOperationFirst() {
        assertEquals(new BigDecimal("1.5"), plugin.max(new BigDecimal("1.5"), new BigDecimal("1.25")));
    }

    @Test
    public void testMaxOperationOther() {
        assertEquals(new BigDecimal("1.5"), plugin.max(new BigDecimal("1.25"), new BigDecimal("1.5")));
    }

    @Test
    public void testMinOperationFirst() {
        assertEquals(new BigDecimal("1.25"), plugin.min(new BigDecimal("1.25"), new BigDecimal("1.5")));
    }

    @Test
    public void testMinOperationOther() {
        assertEquals(new BigDecimal("1.25"), plugin.min(new BigDecimal("1.5"), new BigDecimal("1.25")));
    }

    @Test
    public void testIntValue() {
        assertEquals(new Integer(1), plugin.intValue(new BigDecimal("1.5")));
    }

    @Test
    public void testLongValue() {
        assertEquals(new Long(1), plugin.longValue(new BigDecimal("1.5")));
    }

    @Test
    public void testDoubleValue() {
        assertEquals(1.5d, plugin.doubleValue(new BigDecimal("1.5")));
    }

    @Test
    public void testBigDecimalValue() {
        assertEquals(new BigDecimal("1.5"), plugin.bigDecimalValue(new BigDecimal("1.5")));
    }

}
