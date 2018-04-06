package net.naffets.nevsuite.test.unit.timeseries.core.timeseries;

import net.naffets.nevsuite.backend.timeseries.core.dataprovider.TimeseriesDataProviderConstantValue;
import net.naffets.nevsuite.backend.timeseries.core.timeseries.*;
import net.naffets.nevsuite.backend.timeseries.core.valueplugin.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.*;

import static org.junit.Assert.assertEquals;

/**
 * @author br4sk4
 * created on 22.03.2016
 */
public class TimeseriesTest {

    private final Logger logger = LogManager.getLogger(Timeseries.class.getName());

    @Test
    public void testAddOperationDayWithQuarterHourPeriod() {
        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<BigDecimal> t1 = createTimeseries(new BigDecimal("1.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.NONE);
        Timeseries<BigDecimal> t2 = createTimeseries(new BigDecimal("1.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.NONE);

        t1.load();
        t2.load();

        Long measureTimestamp = System.currentTimeMillis();
        Timeseries<BigDecimal> timeseries = t1.add(t2);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("AddOperation(Day): " + measureTimestamp.doubleValue() / 1000 + " s");

        Instant expectedFirstTimestamp = ZonedDateTime.of(2016, 1, 1, 0, 15, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualFirstTimestamp = timeseries.getValueMap().keySet().stream().sorted().findFirst().orElse(null);

        Instant expectedLastTimestamp = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualLastTimestamp = timeseries.getValueMap().keySet().stream().max(Instant::compareTo).orElse(null);

        BigDecimal expectedValue = new BigDecimal("2.0");
        BigDecimal actualValue = timeseries.getValue(timestampTo);

        assertEquals(96, timeseries.getValueMap().size());
        assertEquals(expectedFirstTimestamp, actualFirstTimestamp);
        assertEquals(expectedLastTimestamp, actualLastTimestamp);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testAddOperationMonthWithQuarterHourPeriod() {
        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<BigDecimal> t1 = createTimeseries(new BigDecimal("1.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.NONE);
        Timeseries<BigDecimal> t2 = createTimeseries(new BigDecimal("1.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.NONE);

        t1.load();
        t2.load();

        Long measureTimestamp = System.currentTimeMillis();
        Timeseries<BigDecimal> timeseries = t1.add(t2);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("AddOperation(Month): " + measureTimestamp.doubleValue() / 1000 + " s");

        Instant expectedFirstTimestamp = ZonedDateTime.of(2016, 1, 1, 0, 15, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualFirstTimestamp = timeseries.getValueMap().keySet().stream().sorted().findFirst().orElse(null);

        Instant expectedLastTimestamp = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualLastTimestamp = timeseries.getValueMap().keySet().stream().max(Instant::compareTo).orElse(null);

        BigDecimal expectedValue = new BigDecimal("2.0");
        BigDecimal actualValue = timeseries.getValue(timestampTo);

        assertEquals(2976, timeseries.getValueMap().size());
        assertEquals(expectedFirstTimestamp, actualFirstTimestamp);
        assertEquals(expectedLastTimestamp, actualLastTimestamp);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testAddOperationYearWithQuarterHourPeriod() {
        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2017, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<BigDecimal> t1 = createTimeseries(new BigDecimal("1.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.NONE);
        Timeseries<BigDecimal> t2 = createTimeseries(new BigDecimal("1.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.NONE);

        t1.load();
        t2.load();

        Long measureTimestamp = System.currentTimeMillis();
        Timeseries<BigDecimal> timeseries = t1.add(t2);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("AddOperation(Year): " + measureTimestamp.doubleValue() / 1000 + " s");

        Instant expectedFirstTimestamp = ZonedDateTime.of(2016, 1, 1, 0, 15, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualFirstTimestamp = timeseries.getValueMap().keySet().stream().sorted().findFirst().orElse(null);

        Instant expectedLastTimestamp = ZonedDateTime.of(2017, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualLastTimestamp = timeseries.getValueMap().keySet().stream().max(Instant::compareTo).orElse(null);

        BigDecimal expectedValue = new BigDecimal("2.0");
        BigDecimal actualValue = timeseries.getValue(timestampTo);

        assertEquals(35136, timeseries.getValueMap().size());
        assertEquals(expectedFirstTimestamp, actualFirstTimestamp);
        assertEquals(expectedLastTimestamp, actualLastTimestamp);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testAddOperationWithNullValue() {
        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<BigDecimal> t1 = createTimeseries(new BigDecimal("1.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.NONE);
        Timeseries<BigDecimal> t2 = createTimeseries(null, new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.NONE);

        t1.load();
        t2.load();

        Timeseries<BigDecimal> timeseries = t1.add(t2);
        BigDecimal expectedValue = new BigDecimal("1.0");
        BigDecimal actualValue = timeseries.getValue(timestampTo);

        assertEquals(96, timeseries.getValueMap().size());
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testSubtractOperationDayWithQuarterHourPeriod() {
        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<BigDecimal> t1 = createTimeseries(new BigDecimal("1.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.NONE);
        Timeseries<BigDecimal> t2 = createTimeseries(new BigDecimal("1.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.NONE);

        t1.load();
        t2.load();

        Long measureTimestamp = System.currentTimeMillis();
        Timeseries<BigDecimal> timeseries = t1.subtract(t2);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("SubtractOperation(Day): " + measureTimestamp.doubleValue() / 1000 + " s");

        Instant expectedFirstTimestamp = ZonedDateTime.of(2016, 1, 1, 0, 15, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualFirstTimestamp = timeseries.getValueMap().keySet().stream().sorted().findFirst().orElse(null);

        Instant expectedLastTimestamp = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualLastTimestamp = timeseries.getValueMap().keySet().stream().max(Instant::compareTo).orElse(null);

        BigDecimal expectedValue = new BigDecimal("0.0");
        BigDecimal actualValue = timeseries.getValue(timestampTo);

        assertEquals(96, timeseries.getValueMap().size());
        assertEquals(expectedFirstTimestamp, actualFirstTimestamp);
        assertEquals(expectedLastTimestamp, actualLastTimestamp);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testSubtractOperationMonthWithQuarterHourPeriod() {
        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<BigDecimal> t1 = createTimeseries(new BigDecimal("1.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.NONE);
        Timeseries<BigDecimal> t2 = createTimeseries(new BigDecimal("1.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.NONE);

        t1.load();
        t2.load();

        Long measureTimestamp = System.currentTimeMillis();
        Timeseries<BigDecimal> timeseries = t1.subtract(t2);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("SubtractOperation(Month): " + measureTimestamp.doubleValue() / 1000 + " s");

        Instant expectedFirstTimestamp = ZonedDateTime.of(2016, 1, 1, 0, 15, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualFirstTimestamp = timeseries.getValueMap().keySet().stream().sorted().findFirst().orElse(null);

        Instant expectedLastTimestamp = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualLastTimestamp = timeseries.getValueMap().keySet().stream().max(Instant::compareTo).orElse(null);

        BigDecimal expectedValue = new BigDecimal("0.0");
        BigDecimal actualValue = timeseries.getValue(timestampTo);

        assertEquals(2976, timeseries.getValueMap().size());
        assertEquals(expectedFirstTimestamp, actualFirstTimestamp);
        assertEquals(expectedLastTimestamp, actualLastTimestamp);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testSubtractOperationYearWithQuarterHourPeriod() {
        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2017, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<BigDecimal> t1 = createTimeseries(new BigDecimal("1.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.NONE);
        Timeseries<BigDecimal> t2 = createTimeseries(new BigDecimal("1.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.NONE);

        t1.load();
        t2.load();

        Long measureTimestamp = System.currentTimeMillis();
        Timeseries<BigDecimal> timeseries = t1.subtract(t2);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("SubtractOperation(Year): " + measureTimestamp.doubleValue() / 1000 + " s");

        Instant expectedFirstTimestamp = ZonedDateTime.of(2016, 1, 1, 0, 15, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualFirstTimestamp = timeseries.getValueMap().keySet().stream().sorted().findFirst().orElse(null);

        Instant expectedLastTimestamp = ZonedDateTime.of(2017, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualLastTimestamp = timeseries.getValueMap().keySet().stream().max(Instant::compareTo).orElse(null);

        BigDecimal expectedValue = new BigDecimal("0.0");
        BigDecimal actualValue = timeseries.getValue(timestampTo);

        assertEquals(35136, timeseries.getValueMap().size());
        assertEquals(expectedFirstTimestamp, actualFirstTimestamp);
        assertEquals(expectedLastTimestamp, actualLastTimestamp);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testSubtractOperationWithNullValue() {
        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<BigDecimal> t1 = createTimeseries(new BigDecimal("1.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.NONE);
        Timeseries<BigDecimal> t2 = createTimeseries(null, new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.NONE);

        t1.load();
        t2.load();

        Timeseries<BigDecimal> timeseries = t1.subtract(t2);
        BigDecimal expectedValue = new BigDecimal("1.0");
        BigDecimal actualValue = timeseries.getValue(timestampTo);

        assertEquals(96, timeseries.getValueMap().size());
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testMultiplyOperationDayWithQuarterHourPeriod() {
        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<BigDecimal> t1 = createTimeseries(new BigDecimal("2.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.NONE);
        Timeseries<BigDecimal> t2 = createTimeseries(new BigDecimal("3.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.NONE);

        t1.load();
        t2.load();

        Long measureTimestamp = System.currentTimeMillis();
        Timeseries<BigDecimal> timeseries = t1.multiply(t2);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("MultiplyOperation(Day): " + measureTimestamp.doubleValue() / 1000 + " s");

        Instant expectedFirstTimestamp = ZonedDateTime.of(2016, 1, 1, 0, 15, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualFirstTimestamp = timeseries.getValueMap().keySet().stream().sorted().findFirst().orElse(null);

        Instant expectedLastTimestamp = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualLastTimestamp = timeseries.getValueMap().keySet().stream().max(Instant::compareTo).orElse(null);

        Double expectedValue = new BigDecimal("6.0").doubleValue();
        Double actualValue = timeseries.getValue(timestampTo).doubleValue();

        assertEquals(96, timeseries.getValueMap().size());
        assertEquals(expectedFirstTimestamp, actualFirstTimestamp);
        assertEquals(expectedLastTimestamp, actualLastTimestamp);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testMultiplyOperationMonthWithQuarterHourPeriod() {
        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<BigDecimal> t1 = createTimeseries(new BigDecimal("2.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.NONE);
        Timeseries<BigDecimal> t2 = createTimeseries(new BigDecimal("3.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.NONE);

        t1.load();
        t2.load();

        Long measureTimestamp = System.currentTimeMillis();
        Timeseries<BigDecimal> timeseries = t1.multiply(t2);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("MultiplyOperation(Month): " + measureTimestamp.doubleValue() / 1000 + " s");

        Instant expectedFirstTimestamp = ZonedDateTime.of(2016, 1, 1, 0, 15, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualFirstTimestamp = timeseries.getValueMap().keySet().stream().sorted().findFirst().orElse(null);

        Instant expectedLastTimestamp = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualLastTimestamp = timeseries.getValueMap().keySet().stream().max(Instant::compareTo).orElse(null);

        Double expectedValue = new BigDecimal("6.0").doubleValue();
        Double actualValue = timeseries.getValue(timestampTo).doubleValue();

        assertEquals(2976, timeseries.getValueMap().size());
        assertEquals(expectedFirstTimestamp, actualFirstTimestamp);
        assertEquals(expectedLastTimestamp, actualLastTimestamp);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testMultiplyOperationYearWithQuarterHourPeriod() {
        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2017, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<BigDecimal> t1 = createTimeseries(new BigDecimal("2.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.NONE);
        Timeseries<BigDecimal> t2 = createTimeseries(new BigDecimal("3.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.NONE);

        t1.load();
        t2.load();

        Long measureTimestamp = System.currentTimeMillis();
        Timeseries<BigDecimal> timeseries = t1.multiply(t2);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("MultiplyOperation(Year): " + measureTimestamp.doubleValue() / 1000 + " s");

        Instant expectedFirstTimestamp = ZonedDateTime.of(2016, 1, 1, 0, 15, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualFirstTimestamp = timeseries.getValueMap().keySet().stream().sorted().findFirst().orElse(null);

        Instant expectedLastTimestamp = ZonedDateTime.of(2017, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualLastTimestamp = timeseries.getValueMap().keySet().stream().max(Instant::compareTo).orElse(null);

        Double expectedValue = new BigDecimal("6.0").doubleValue();
        Double actualValue = timeseries.getValue(timestampTo).doubleValue();

        assertEquals(35136, timeseries.getValueMap().size());
        assertEquals(expectedFirstTimestamp, actualFirstTimestamp);
        assertEquals(expectedLastTimestamp, actualLastTimestamp);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testMyltiplyOperationWithNullValue() {
        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<BigDecimal> t1 = createTimeseries(new BigDecimal("1.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.NONE);
        Timeseries<BigDecimal> t2 = createTimeseries(null, new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.NONE);

        t1.load();
        t2.load();

        Timeseries<BigDecimal> timeseries = t1.multiply(t2);
        BigDecimal expectedValue = new BigDecimal("1.0");
        BigDecimal actualValue = timeseries.getValue(timestampTo);

        assertEquals(96, timeseries.getValueMap().size());
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testDivideOperationDayWithQuarterHourPeriod() {
        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<BigDecimal> t1 = createTimeseries(new BigDecimal("15.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.NONE);
        Timeseries<BigDecimal> t2 = createTimeseries(new BigDecimal("3.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.NONE);

        t1.load();
        t2.load();

        Long measureTimestamp = System.currentTimeMillis();
        Timeseries<BigDecimal> timeseries = t1.divide(t2);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("DivideOperation(Day): " + measureTimestamp.doubleValue() / 1000 + " s");

        Instant expectedFirstTimestamp = ZonedDateTime.of(2016, 1, 1, 0, 15, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualFirstTimestamp = timeseries.getValueMap().keySet().stream().sorted().findFirst().orElse(null);

        Instant expectedLastTimestamp = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualLastTimestamp = timeseries.getValueMap().keySet().stream().max(Instant::compareTo).orElse(null);

        Double expectedValue = new BigDecimal("5.0").doubleValue();
        Double actualValue = timeseries.getValue(timestampTo).doubleValue();

        assertEquals(96, timeseries.getValueMap().size());
        assertEquals(expectedFirstTimestamp, actualFirstTimestamp);
        assertEquals(expectedLastTimestamp, actualLastTimestamp);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testDivideOperationMonthWithQuarterHourPeriod() {
        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<BigDecimal> t1 = createTimeseries(new BigDecimal("15.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.NONE);
        Timeseries<BigDecimal> t2 = createTimeseries(new BigDecimal("3.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.NONE);

        t1.load();
        t2.load();

        Long measureTimestamp = System.currentTimeMillis();
        Timeseries<BigDecimal> timeseries = t1.divide(t2);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("DivideOperation(Month): " + measureTimestamp.doubleValue() / 1000 + " s");

        Instant expectedFirstTimestamp = ZonedDateTime.of(2016, 1, 1, 0, 15, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualFirstTimestamp = timeseries.getValueMap().keySet().stream().sorted().findFirst().orElse(null);

        Instant expectedLastTimestamp = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualLastTimestamp = timeseries.getValueMap().keySet().stream().max(Instant::compareTo).orElse(null);

        Double expectedValue = new BigDecimal("5.0").doubleValue();
        Double actualValue = timeseries.getValue(timestampTo).doubleValue();

        assertEquals(2976, timeseries.getValueMap().size());
        assertEquals(expectedFirstTimestamp, actualFirstTimestamp);
        assertEquals(expectedLastTimestamp, actualLastTimestamp);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testDivideOperationYearWithQuarterHourPeriod() {
        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2017, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<BigDecimal> t1 = createTimeseries(new BigDecimal("15.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.NONE);
        Timeseries<BigDecimal> t2 = createTimeseries(new BigDecimal("3.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.NONE);

        t1.load();
        t2.load();

        Long measureTimestamp = System.currentTimeMillis();
        Timeseries<BigDecimal> timeseries = t1.divide(t2);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("DivideOperation(Year): " + measureTimestamp.doubleValue() / 1000 + " s");

        Instant expectedFirstTimestamp = ZonedDateTime.of(2016, 1, 1, 0, 15, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualFirstTimestamp = timeseries.getValueMap().keySet().stream().sorted().findFirst().orElse(null);

        Instant expectedLastTimestamp = ZonedDateTime.of(2017, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualLastTimestamp = timeseries.getValueMap().keySet().stream().max(Instant::compareTo).orElse(null);

        Double expectedValue = new BigDecimal("5.0").doubleValue();
        Double actualValue = timeseries.getValue(timestampTo).doubleValue();

        assertEquals(35136, timeseries.getValueMap().size());
        assertEquals(expectedFirstTimestamp, actualFirstTimestamp);
        assertEquals(expectedLastTimestamp, actualLastTimestamp);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testDivideOperationWithNullValue() {
        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<BigDecimal> t1 = createTimeseries(new BigDecimal("1.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.NONE);
        Timeseries<BigDecimal> t2 = createTimeseries(null, new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.NONE);

        t1.load();
        t2.load();

        Timeseries<BigDecimal> timeseries = t1.divide(t2);
        BigDecimal expectedValue = new BigDecimal("1.0");
        BigDecimal actualValue = timeseries.getValue(timestampTo);

        assertEquals(96, timeseries.getValueMap().size());
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testMaxOperationDayWithQuarterHourPeriod() {
        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<BigDecimal> t1 = createTimeseries(new BigDecimal("15.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.NONE);
        Timeseries<BigDecimal> t2 = createTimeseries(new BigDecimal("3.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.NONE);

        t1.load();
        t2.load();

        Long measureTimestamp = System.currentTimeMillis();
        Timeseries<BigDecimal> timeseries = t1.max(t2);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("MaxOperation(Day): " + measureTimestamp.doubleValue() / 1000 + " s");

        Instant expectedFirstTimestamp = ZonedDateTime.of(2016, 1, 1, 0, 15, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualFirstTimestamp = timeseries.getValueMap().keySet().stream().sorted().findFirst().orElse(null);

        Instant expectedLastTimestamp = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualLastTimestamp = timeseries.getValueMap().keySet().stream().max(Instant::compareTo).orElse(null);

        Double expectedValue = new BigDecimal("15.0").doubleValue();
        Double actualValue = timeseries.getValue(timestampTo).doubleValue();

        assertEquals(96, timeseries.getValueMap().size());
        assertEquals(expectedFirstTimestamp, actualFirstTimestamp);
        assertEquals(expectedLastTimestamp, actualLastTimestamp);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testMaxOperationMonthWithQuarterHourPeriod() {
        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<BigDecimal> t1 = createTimeseries(new BigDecimal("15.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.NONE);
        Timeseries<BigDecimal> t2 = createTimeseries(new BigDecimal("3.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.NONE);

        t1.load();
        t2.load();

        Long measureTimestamp = System.currentTimeMillis();
        Timeseries<BigDecimal> timeseries = t1.max(t2);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("MaxOperation(Month): " + measureTimestamp.doubleValue() / 1000 + " s");

        Instant expectedFirstTimestamp = ZonedDateTime.of(2016, 1, 1, 0, 15, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualFirstTimestamp = timeseries.getValueMap().keySet().stream().sorted().findFirst().orElse(null);

        Instant expectedLastTimestamp = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualLastTimestamp = timeseries.getValueMap().keySet().stream().max(Instant::compareTo).orElse(null);

        Double expectedValue = new BigDecimal("15.0").doubleValue();
        Double actualValue = timeseries.getValue(timestampTo).doubleValue();

        assertEquals(2976, timeseries.getValueMap().size());
        assertEquals(expectedFirstTimestamp, actualFirstTimestamp);
        assertEquals(expectedLastTimestamp, actualLastTimestamp);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testMaxOperationYearWithQuarterHourPeriod() {
        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2017, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<BigDecimal> t1 = createTimeseries(new BigDecimal("15.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.NONE);
        Timeseries<BigDecimal> t2 = createTimeseries(new BigDecimal("3.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.NONE);

        t1.load();
        t2.load();

        Long measureTimestamp = System.currentTimeMillis();
        Timeseries<BigDecimal> timeseries = t1.max(t2);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("MaxOperation(Year): " + measureTimestamp.doubleValue() / 1000 + " s");

        Instant expectedFirstTimestamp = ZonedDateTime.of(2016, 1, 1, 0, 15, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualFirstTimestamp = timeseries.getValueMap().keySet().stream().sorted().findFirst().orElse(null);

        Instant expectedLastTimestamp = ZonedDateTime.of(2017, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualLastTimestamp = timeseries.getValueMap().keySet().stream().max(Instant::compareTo).orElse(null);

        Double expectedValue = new BigDecimal("15.0").doubleValue();
        Double actualValue = timeseries.getValue(timestampTo).doubleValue();

        assertEquals(35136, timeseries.getValueMap().size());
        assertEquals(expectedFirstTimestamp, actualFirstTimestamp);
        assertEquals(expectedLastTimestamp, actualLastTimestamp);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testMaxOperationWithNullValue() {
        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<BigDecimal> t1 = createTimeseries(new BigDecimal("1.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.NONE);
        Timeseries<BigDecimal> t2 = createTimeseries(null, new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.NONE);

        t1.load();
        t2.load();

        Timeseries<BigDecimal> timeseries = t1.max(t2);
        BigDecimal expectedValue = new BigDecimal("1.0");
        BigDecimal actualValue = timeseries.getValue(timestampTo);

        assertEquals(96, timeseries.getValueMap().size());
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testMinOperationDayWithQuarterHourPeriod() {
        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<BigDecimal> t1 = createTimeseries(new BigDecimal("15.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.NONE);
        Timeseries<BigDecimal> t2 = createTimeseries(new BigDecimal("3.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.NONE);

        t1.load();
        t2.load();

        Long measureTimestamp = System.currentTimeMillis();
        Timeseries<BigDecimal> timeseries = t1.min(t2);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("MinOperation(Day): " + measureTimestamp.doubleValue() / 1000 + " s");

        Instant expectedFirstTimestamp = ZonedDateTime.of(2016, 1, 1, 0, 15, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualFirstTimestamp = timeseries.getValueMap().keySet().stream().sorted().findFirst().orElse(null);

        Instant expectedLastTimestamp = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualLastTimestamp = timeseries.getValueMap().keySet().stream().max(Instant::compareTo).orElse(null);

        Double expectedValue = new BigDecimal("3.0").doubleValue();
        Double actualValue = timeseries.getValue(timestampTo).doubleValue();

        assertEquals(96, timeseries.getValueMap().size());
        assertEquals(expectedFirstTimestamp, actualFirstTimestamp);
        assertEquals(expectedLastTimestamp, actualLastTimestamp);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testMinOperationMonthWithQuarterHourPeriod() {
        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<BigDecimal> t1 = createTimeseries(new BigDecimal("15.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.NONE);
        Timeseries<BigDecimal> t2 = createTimeseries(new BigDecimal("3.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.NONE);

        t1.load();
        t2.load();

        Long measureTimestamp = System.currentTimeMillis();
        Timeseries<BigDecimal> timeseries = t1.min(t2);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("MinOperation(Month): " + measureTimestamp.doubleValue() / 1000 + " s");

        Instant expectedFirstTimestamp = ZonedDateTime.of(2016, 1, 1, 0, 15, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualFirstTimestamp = timeseries.getValueMap().keySet().stream().sorted().findFirst().orElse(null);

        Instant expectedLastTimestamp = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualLastTimestamp = timeseries.getValueMap().keySet().stream().max(Instant::compareTo).orElse(null);

        Double expectedValue = new BigDecimal("3.0").doubleValue();
        Double actualValue = timeseries.getValue(timestampTo).doubleValue();

        assertEquals(2976, timeseries.getValueMap().size());
        assertEquals(expectedFirstTimestamp, actualFirstTimestamp);
        assertEquals(expectedLastTimestamp, actualLastTimestamp);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testMinOperationYearWithQuarterHourPeriod() {
        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2017, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<BigDecimal> t1 = createTimeseries(new BigDecimal("15.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.NONE);
        Timeseries<BigDecimal> t2 = createTimeseries(new BigDecimal("3.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.NONE);

        t1.load();
        t2.load();

        Long measureTimestamp = System.currentTimeMillis();
        Timeseries<BigDecimal> timeseries = t1.min(t2);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("MinOperation(Year): " + measureTimestamp.doubleValue() / 1000 + " s");

        Instant expectedFirstTimestamp = ZonedDateTime.of(2016, 1, 1, 0, 15, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualFirstTimestamp = timeseries.getValueMap().keySet().stream().sorted().findFirst().orElse(null);

        Instant expectedLastTimestamp = ZonedDateTime.of(2017, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualLastTimestamp = timeseries.getValueMap().keySet().stream().max(Instant::compareTo).orElse(null);

        Double expectedValue = new BigDecimal("3.0").doubleValue();
        Double actualValue = timeseries.getValue(timestampTo).doubleValue();

        assertEquals(35136, timeseries.getValueMap().size());
        assertEquals(expectedFirstTimestamp, actualFirstTimestamp);
        assertEquals(expectedLastTimestamp, actualLastTimestamp);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testMinOperationWithNullValue() {
        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<BigDecimal> t1 = createTimeseries(new BigDecimal("1.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.NONE);
        Timeseries<BigDecimal> t2 = createTimeseries(null, new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.NONE);

        t1.load();
        t2.load();

        Timeseries<BigDecimal> timeseries = t1.min(t2);
        BigDecimal expectedValue = new BigDecimal("1.0");
        BigDecimal actualValue = timeseries.getValue(timestampTo);

        assertEquals(96, timeseries.getValueMap().size());
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testCompactOperationDayWithQuarterHourPeriodToHourPeriod() {
        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<BigDecimal> t1 = createTimeseries(new BigDecimal("1.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.NONE);

        t1.load();

        Long measureTimestamp = System.currentTimeMillis();
        Timeseries<BigDecimal> timeseries = t1.toPeriod(TimeseriesPeriod.HOUR1);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("CompactOperation(Day): " + measureTimestamp.doubleValue() / 1000 + " s");

        Instant expectedFirstTimestamp = ZonedDateTime.of(2016, 1, 1, 1, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualFirstTimestamp = timeseries.getValueMap().keySet().stream().sorted().findFirst().orElse(null);

        Instant expectedLastTimestamp = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualLastTimestamp = timeseries.getValueMap().keySet().stream().max(Instant::compareTo).orElse(null);

        Double expectedValue = new BigDecimal("4.0").doubleValue();
        Double actualValue = timeseries.getValue(timestampTo).doubleValue();

        assertEquals(TimeseriesPeriod.HOUR1, timeseries.getPeriod());
        assertEquals(24, timeseries.getValueMap().size());
        assertEquals(expectedFirstTimestamp, actualFirstTimestamp);
        assertEquals(expectedLastTimestamp, actualLastTimestamp);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testCompactOperationMonthWithQuarterHourPeriodToHourPeriod() {
        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<BigDecimal> t1 = createTimeseries(new BigDecimal("1.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.NONE);

        t1.load();

        Long measureTimestamp = System.currentTimeMillis();
        Timeseries<BigDecimal> timeseries = t1.toPeriod(TimeseriesPeriod.HOUR1);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("CompactOperation(Month): " + measureTimestamp.doubleValue() / 1000 + " s");

        Instant expectedFirstTimestamp = ZonedDateTime.of(2016, 1, 1, 1, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualFirstTimestamp = timeseries.getValueMap().keySet().stream().sorted().findFirst().orElse(null);

        Instant expectedLastTimestamp = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualLastTimestamp = timeseries.getValueMap().keySet().stream().max(Instant::compareTo).orElse(null);

        Double expectedValue = new BigDecimal("4.0").doubleValue();
        Double actualValue = timeseries.getValue(timestampTo).doubleValue();

        assertEquals(TimeseriesPeriod.HOUR1, timeseries.getPeriod());
        assertEquals(744, timeseries.getValueMap().size());
        assertEquals(expectedFirstTimestamp, actualFirstTimestamp);
        assertEquals(expectedLastTimestamp, actualLastTimestamp);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testCompactOperationYearWithQuarterHourPeriodToHourPeriod() {
        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2017, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<BigDecimal> t1 = createTimeseries(new BigDecimal("1.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.NONE);

        t1.load();

        Long measureTimestamp = System.currentTimeMillis();
        Timeseries<BigDecimal> timeseries = t1.toPeriod(TimeseriesPeriod.HOUR1);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("CompactOperation(Year): " + measureTimestamp.doubleValue() / 1000 + " s");

        Instant expectedFirstTimestamp = ZonedDateTime.of(2016, 1, 1, 1, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualFirstTimestamp = timeseries.getValueMap().keySet().stream().sorted().findFirst().orElse(null);

        Instant expectedLastTimestamp = ZonedDateTime.of(2017, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualLastTimestamp = timeseries.getValueMap().keySet().stream().max(Instant::compareTo).orElse(null);

        Double expectedValue = new BigDecimal("4.0").doubleValue();
        Double actualValue = timeseries.getValue(timestampTo).doubleValue();

        assertEquals(TimeseriesPeriod.HOUR1, timeseries.getPeriod());
        assertEquals(8784, timeseries.getValueMap().size());
        assertEquals(expectedFirstTimestamp, actualFirstTimestamp);
        assertEquals(expectedLastTimestamp, actualLastTimestamp);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testCompactOperationRegularDayWithQuarterHourPeriodToDayPeriod() {
        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<BigDecimal> t1 = createTimeseries(new BigDecimal("1.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.NONE);

        t1.load();
        Timeseries<BigDecimal> timeseries = t1.toPeriod(TimeseriesPeriod.DAY1);

        Instant expectedTimestamp = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualFirstTimestamp = timeseries.getValueMap().keySet().stream().sorted().findFirst().orElse(null);
        Instant actualLastTimestamp = timeseries.getValueMap().keySet().stream().max(Instant::compareTo).orElse(null);

        Double expectedValue = new BigDecimal("96.0").doubleValue();
        Double actualValue = timeseries.getValue(timestampFrom).doubleValue();

        assertEquals(TimeseriesPeriod.DAY1, timeseries.getPeriod());
        assertEquals(1, timeseries.getValueMap().size());
        assertEquals(expectedTimestamp, actualFirstTimestamp);
        assertEquals(expectedTimestamp, actualLastTimestamp);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testCompactOperationDaylightSavingMissingHourWithQuarterHourPeriodToDayPeriod() {
        Instant timestampFrom = ZonedDateTime.of(2016, 3, 27, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 3, 28, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<BigDecimal> t1 = createTimeseries(new BigDecimal("1.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.NONE);

        t1.load();
        Timeseries<BigDecimal> timeseries = t1.toPeriod(TimeseriesPeriod.DAY1);

        Instant expectedTimestamp = ZonedDateTime.of(2016, 3, 27, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualFirstTimestamp = timeseries.getValueMap().keySet().stream().sorted().findFirst().orElse(null);
        Instant actualLastTimestamp = timeseries.getValueMap().keySet().stream().max(Instant::compareTo).orElse(null);

        Double expectedValue = new BigDecimal("92.0").doubleValue();
        Double actualValue = timeseries.getValue(timestampFrom).doubleValue();

        assertEquals(TimeseriesPeriod.DAY1, timeseries.getPeriod());
        assertEquals(1, timeseries.getValueMap().size());
        assertEquals(expectedTimestamp, actualFirstTimestamp);
        assertEquals(expectedTimestamp, actualLastTimestamp);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testCompactOperationDaylightSavingAdditionalHourWithQuarterHourPeriodToDayPeriod() {
        Instant timestampFrom = ZonedDateTime.of(2016, 10, 30, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 10, 31, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<BigDecimal> t1 = createTimeseries(new BigDecimal("1.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.NONE);

        t1.load();
        Timeseries<BigDecimal> timeseries = t1.toPeriod(TimeseriesPeriod.DAY1);

        Instant expectedTimestamp = ZonedDateTime.of(2016, 10, 30, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualFirstTimestamp = timeseries.getValueMap().keySet().stream().sorted().findFirst().orElse(null);
        Instant actualLastTimestamp = timeseries.getValueMap().keySet().stream().max(Instant::compareTo).orElse(null);

        Double expectedValue = new BigDecimal("100.0").doubleValue();
        Double actualValue = timeseries.getValue(timestampFrom).doubleValue();

        assertEquals(TimeseriesPeriod.DAY1, timeseries.getPeriod());
        assertEquals(1, timeseries.getValueMap().size());
        assertEquals(expectedTimestamp, actualFirstTimestamp);
        assertEquals(expectedTimestamp, actualLastTimestamp);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testSpreadOperationDayWithHourPeriodToQuarterHourPeriod() {
        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<BigDecimal> t1 = createTimeseries(new BigDecimal("4.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.HOUR1, TimeseriesUnit.NONE);
        t1.load();

        Long measureTimestamp = System.currentTimeMillis();
        Timeseries<BigDecimal> timeseries = t1.toPeriod(TimeseriesPeriod.MIN15);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("SpreadOperation(Day): " + measureTimestamp.doubleValue() / 1000 + " s");

        Instant expectedFirstTimestamp = ZonedDateTime.of(2016, 1, 1, 0, 15, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualFirstTimestamp = timeseries.getValueMap().keySet().stream().sorted().findFirst().orElse(null);

        Instant expectedLastTimestamp = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualLastTimestamp = timeseries.getValueMap().keySet().stream().max(Instant::compareTo).orElse(null);

        Double expectedValue = new BigDecimal("1.0").doubleValue();
        Double actualValue = timeseries.getValue(timestampTo).doubleValue();

        assertEquals(TimeseriesPeriod.MIN15, timeseries.getPeriod());
        assertEquals(96, timeseries.getValueMap().size());
        assertEquals(expectedFirstTimestamp, actualFirstTimestamp);
        assertEquals(expectedLastTimestamp, actualLastTimestamp);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testSpreadOperationMonthWithHourPeriodToQuarterHourPeriod() {
        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<BigDecimal> t1 = createTimeseries(new BigDecimal("4.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.HOUR1, TimeseriesUnit.NONE);
        t1.load();

        Long measureTimestamp = System.currentTimeMillis();
        Timeseries<BigDecimal> timeseries = t1.toPeriod(TimeseriesPeriod.MIN15);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("SpreadOperation(Month): " + measureTimestamp.doubleValue() / 1000 + " s");

        Instant expectedFirstTimestamp = ZonedDateTime.of(2016, 1, 1, 0, 15, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualFirstTimestamp = timeseries.getValueMap().keySet().stream().sorted().findFirst().orElse(null);

        Instant expectedLastTimestamp = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualLastTimestamp = timeseries.getValueMap().keySet().stream().max(Instant::compareTo).orElse(null);

        Double expectedValue = new BigDecimal("1.0").doubleValue();
        Double actualValue = timeseries.getValue(timestampTo).doubleValue();

        assertEquals(TimeseriesPeriod.MIN15, timeseries.getPeriod());
        assertEquals(2976, timeseries.getValueMap().size());
        assertEquals(expectedFirstTimestamp, actualFirstTimestamp);
        assertEquals(expectedLastTimestamp, actualLastTimestamp);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testSpreadOperationYearWithHourPeriodToQuarterHourPeriod() {
        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2017, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<BigDecimal> t1 = createTimeseries(new BigDecimal("4.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.HOUR1, TimeseriesUnit.NONE);
        t1.load();

        Long measureTimestamp = System.currentTimeMillis();
        Timeseries<BigDecimal> timeseries = t1.toPeriod(TimeseriesPeriod.MIN15);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("SpreadOperation(Year): " + measureTimestamp.doubleValue() / 1000 + " s");

        Instant expectedFirstTimestamp = ZonedDateTime.of(2016, 1, 1, 0, 15, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualFirstTimestamp = timeseries.getValueMap().keySet().stream().sorted().findFirst().orElse(null);

        Instant expectedLastTimestamp = ZonedDateTime.of(2017, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualLastTimestamp = timeseries.getValueMap().keySet().stream().max(Instant::compareTo).orElse(null);

        Double expectedValue = new BigDecimal("1.0").doubleValue();
        Double actualValue = timeseries.getValue(timestampTo).doubleValue();

        assertEquals(TimeseriesPeriod.MIN15, timeseries.getPeriod());
        assertEquals(35136, timeseries.getValueMap().size());
        assertEquals(expectedFirstTimestamp, actualFirstTimestamp);
        assertEquals(expectedLastTimestamp, actualLastTimestamp);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testSpreadOperationRegularDayWithDayPeriodToQuarterHourPeriod() {
        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<BigDecimal> t1 = createTimeseries(new BigDecimal("96.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.DAY1, TimeseriesUnit.NONE);

        t1.load();
        Timeseries<BigDecimal> timeseries = t1.toPeriod(TimeseriesPeriod.MIN15);

        Instant expectedFirstTimestamp = ZonedDateTime.of(2016, 1, 1, 0, 15, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualFirstTimestamp = timeseries.getValueMap().keySet().stream().sorted().findFirst().orElse(null);

        Instant expectedLastTimestamp = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualLastTimestamp = timeseries.getValueMap().keySet().stream().max(Instant::compareTo).orElse(null);

        Double expectedValue = new BigDecimal("1.0").doubleValue();
        Double actualValue = timeseries.getValue(expectedFirstTimestamp).doubleValue();

        assertEquals(TimeseriesPeriod.MIN15, timeseries.getPeriod());
        assertEquals(96, timeseries.getValueMap().size());
        assertEquals(expectedFirstTimestamp, actualFirstTimestamp);
        assertEquals(expectedLastTimestamp, actualLastTimestamp);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testSpreadOperationDaylightSavingMissingHourWithQuarterHourPeriodToDayPeriod() {
        Instant timestampFrom = ZonedDateTime.of(2016, 3, 27, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 3, 28, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<BigDecimal> t1 = createTimeseries(new BigDecimal("92.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.DAY1, TimeseriesUnit.NONE);

        t1.load();
        Timeseries<BigDecimal> timeseries = t1.toPeriod(TimeseriesPeriod.MIN15);

        Instant expectedFirstTimestamp = ZonedDateTime.of(2016, 3, 27, 0, 15, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualFirstTimestamp = timeseries.getValueMap().keySet().stream().sorted().findFirst().orElse(null);

        Instant expectedLastTimestamp = ZonedDateTime.of(2016, 3, 28, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualLastTimestamp = timeseries.getValueMap().keySet().stream().max(Instant::compareTo).orElse(null);

        Double expectedValue = new BigDecimal("1.0").doubleValue();
        Double actualValue = timeseries.getValue(expectedFirstTimestamp).doubleValue();

        assertEquals(TimeseriesPeriod.MIN15, timeseries.getPeriod());
        assertEquals(92, timeseries.getValueMap().size());
        assertEquals(expectedFirstTimestamp, actualFirstTimestamp);
        assertEquals(expectedLastTimestamp, actualLastTimestamp);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testSpreadOperationDaylightSavingAdditionalHourWithQuarterHourPeriodToDayPeriod() {
        Instant timestampFrom = ZonedDateTime.of(2016, 10, 30, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 10, 31, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<BigDecimal> t1 = createTimeseries(new BigDecimal("100.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.DAY1, TimeseriesUnit.NONE);

        t1.load();
        Timeseries<BigDecimal> timeseries = t1.toPeriod(TimeseriesPeriod.MIN15);

        Instant expectedFirstTimestamp = ZonedDateTime.of(2016, 10, 30, 0, 15, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualFirstTimestamp = timeseries.getValueMap().keySet().stream().sorted().findFirst().orElse(null);

        Instant expectedLastTimestamp = ZonedDateTime.of(2016, 10, 31, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualLastTimestamp = timeseries.getValueMap().keySet().stream().max(Instant::compareTo).orElse(null);

        Double expectedValue = new BigDecimal("1.0").doubleValue();
        Double actualValue = timeseries.getValue(expectedFirstTimestamp).doubleValue();

        assertEquals(TimeseriesPeriod.MIN15, timeseries.getPeriod());
        assertEquals(100, timeseries.getValueMap().size());
        assertEquals(expectedFirstTimestamp, actualFirstTimestamp);
        assertEquals(expectedLastTimestamp, actualLastTimestamp);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testPowerToEnergyUnitConversionDayWithQuarterHourPeriod() {
        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<BigDecimal> t1 = createTimeseries(new BigDecimal("1.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.KILO_WATT);
        t1.load();

        Long measureTimestamp = System.currentTimeMillis();
        Timeseries<BigDecimal> timeseries = t1.toUnit(TimeseriesUnit.MEGA_WATT_HOUR);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("PowerToEnergyUnitConversion(Day): " + measureTimestamp.doubleValue() / 1000 + " s");

        Instant expectedFirstTimestamp = ZonedDateTime.of(2016, 1, 1, 0, 15, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualFirstTimestamp = timeseries.getValueMap().keySet().stream().sorted().findFirst().orElse(null);

        Instant expectedLastTimestamp = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualLastTimestamp = timeseries.getValueMap().keySet().stream().max(Instant::compareTo).orElse(null);

        BigDecimal expectedValue = new BigDecimal("0.00025");
        BigDecimal actualValue = timeseries.getValue(timestampTo);

        assertEquals(TimeseriesUnit.MEGA_WATT_HOUR, timeseries.getUnit());
        assertEquals(96, timeseries.getValueMap().size());
        assertEquals(expectedFirstTimestamp, actualFirstTimestamp);
        assertEquals(expectedLastTimestamp, actualLastTimestamp);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testPowerToEnergyUnitConversionMonthWithQuarterHourPeriod() {
        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<BigDecimal> t1 = createTimeseries(new BigDecimal("1.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.KILO_WATT);
        t1.load();

        Long measureTimestamp = System.currentTimeMillis();
        Timeseries<BigDecimal> timeseries = t1.toUnit(TimeseriesUnit.MEGA_WATT_HOUR);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("PowerToEnergyUnitConversion(Month): " + measureTimestamp.doubleValue() / 1000 + " s");

        Instant expectedFirstTimestamp = ZonedDateTime.of(2016, 1, 1, 0, 15, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualFirstTimestamp = timeseries.getValueMap().keySet().stream().sorted().findFirst().orElse(null);

        Instant expectedLastTimestamp = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualLastTimestamp = timeseries.getValueMap().keySet().stream().max(Instant::compareTo).orElse(null);

        BigDecimal expectedValue = new BigDecimal("0.00025");
        BigDecimal actualValue = timeseries.getValue(timestampTo);

        assertEquals(TimeseriesUnit.MEGA_WATT_HOUR, timeseries.getUnit());
        assertEquals(2976, timeseries.getValueMap().size());
        assertEquals(expectedFirstTimestamp, actualFirstTimestamp);
        assertEquals(expectedLastTimestamp, actualLastTimestamp);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testPowerToEnergyUnitConversionYearWithQuarterHourPeriod() {
        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2017, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<BigDecimal> t1 = createTimeseries(new BigDecimal("1.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.KILO_WATT);
        t1.load();

        Long measureTimestamp = System.currentTimeMillis();
        Timeseries<BigDecimal> timeseries = t1.toUnit(TimeseriesUnit.MEGA_WATT_HOUR);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("PowerToEnergyUnitConversion(Year): " + measureTimestamp.doubleValue() / 1000 + " s");

        Instant expectedFirstTimestamp = ZonedDateTime.of(2016, 1, 1, 0, 15, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualFirstTimestamp = timeseries.getValueMap().keySet().stream().sorted().findFirst().orElse(null);

        Instant expectedLastTimestamp = ZonedDateTime.of(2017, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualLastTimestamp = timeseries.getValueMap().keySet().stream().max(Instant::compareTo).orElse(null);

        BigDecimal expectedValue = new BigDecimal("0.00025");
        BigDecimal actualValue = timeseries.getValue(timestampTo);

        assertEquals(TimeseriesUnit.MEGA_WATT_HOUR, timeseries.getUnit());
        assertEquals(35136, timeseries.getValueMap().size());
        assertEquals(expectedFirstTimestamp, actualFirstTimestamp);
        assertEquals(expectedLastTimestamp, actualLastTimestamp);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testPowerToEnergyUnitConversionRegularDayWithQuarterHourPeriod() {
        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<BigDecimal> t1 = createTimeseries(new BigDecimal("1.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.KILO_WATT);
        t1.load();

        Timeseries<BigDecimal> timeseries = t1.toUnit(TimeseriesUnit.KILO_WATT_HOUR);

        Instant expectedFirstTimestamp = ZonedDateTime.of(2016, 1, 1, 0, 15, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualFirstTimestamp = timeseries.getValueMap().keySet().stream().sorted().findFirst().orElse(null);

        Instant expectedLastTimestamp = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualLastTimestamp = timeseries.getValueMap().keySet().stream().max(Instant::compareTo).orElse(null);

        BigDecimal expectedValue = new BigDecimal("0.25");
        BigDecimal actualValue = timeseries.getValue(timestampTo);

        assertEquals(TimeseriesUnit.KILO_WATT_HOUR, timeseries.getUnit());
        assertEquals(96, timeseries.getValueMap().size());
        assertEquals(expectedFirstTimestamp, actualFirstTimestamp);
        assertEquals(expectedLastTimestamp, actualLastTimestamp);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testPowerToEnergyUnitConversionDaylightSavingMissingHourWithQuarterHourPeriod() {
        Instant timestampFrom = ZonedDateTime.of(2016, 3, 27, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 3, 28, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<BigDecimal> t1 = createTimeseries(new BigDecimal("1.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.KILO_WATT);
        t1.load();

        Timeseries<BigDecimal> timeseries = t1.toUnit(TimeseriesUnit.KILO_WATT_HOUR);

        Instant expectedFirstTimestamp = ZonedDateTime.of(2016, 3, 27, 0, 15, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualFirstTimestamp = timeseries.getValueMap().keySet().stream().sorted().findFirst().orElse(null);

        Instant expectedLastTimestamp = ZonedDateTime.of(2016, 3, 28, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualLastTimestamp = timeseries.getValueMap().keySet().stream().max(Instant::compareTo).orElse(null);

        BigDecimal expectedValue = new BigDecimal("0.25");
        BigDecimal actualValue = timeseries.getValue(timestampTo);

        assertEquals(TimeseriesUnit.KILO_WATT_HOUR, timeseries.getUnit());
        assertEquals(92, timeseries.getValueMap().size());
        assertEquals(expectedFirstTimestamp, actualFirstTimestamp);
        assertEquals(expectedLastTimestamp, actualLastTimestamp);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testPowerToEnergyUnitConversionDaylightSavingAdditionalHourWithQuarterHourPeriod() {
        Instant timestampFrom = ZonedDateTime.of(2016, 10, 30, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 10, 31, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<BigDecimal> t1 = createTimeseries(new BigDecimal("1.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.KILO_WATT);
        t1.load();

        Timeseries<BigDecimal> timeseries = t1.toUnit(TimeseriesUnit.KILO_WATT_HOUR);

        Instant expectedFirstTimestamp = ZonedDateTime.of(2016, 10, 30, 0, 15, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualFirstTimestamp = timeseries.getValueMap().keySet().stream().sorted().findFirst().orElse(null);

        Instant expectedLastTimestamp = ZonedDateTime.of(2016, 10, 31, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualLastTimestamp = timeseries.getValueMap().keySet().stream().max(Instant::compareTo).orElse(null);

        BigDecimal expectedValue = new BigDecimal("0.25");
        BigDecimal actualValue = timeseries.getValue(timestampTo);

        assertEquals(TimeseriesUnit.KILO_WATT_HOUR, timeseries.getUnit());
        assertEquals(100, timeseries.getValueMap().size());
        assertEquals(expectedFirstTimestamp, actualFirstTimestamp);
        assertEquals(expectedLastTimestamp, actualLastTimestamp);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testEnergyToPowerUnitConversionDayWithQuarterHourPeriod() {
        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<BigDecimal> t1 = createTimeseries(new BigDecimal("1.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.KILO_WATT_HOUR);
        t1.load();

        Long measureTimestamp = System.currentTimeMillis();
        Timeseries<BigDecimal> timeseries = t1.toUnit(TimeseriesUnit.MEGA_WATT);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("EnergyToPowerUnitConversion(Day): " + measureTimestamp.doubleValue() / 1000 + " s");

        Instant expectedFirstTimestamp = ZonedDateTime.of(2016, 1, 1, 0, 15, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualFirstTimestamp = timeseries.getValueMap().keySet().stream().sorted().findFirst().orElse(null);

        Instant expectedLastTimestamp = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualLastTimestamp = timeseries.getValueMap().keySet().stream().max(Instant::compareTo).orElse(null);

        BigDecimal expectedValue = new BigDecimal("0.004");
        BigDecimal actualValue = timeseries.getValue(timestampTo);

        assertEquals(TimeseriesUnit.MEGA_WATT, timeseries.getUnit());
        assertEquals(96, timeseries.getValueMap().size());
        assertEquals(expectedFirstTimestamp, actualFirstTimestamp);
        assertEquals(expectedLastTimestamp, actualLastTimestamp);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testEnergyToPowerUnitConversionMonthWithQuarterHourPeriod() {
        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<BigDecimal> t1 = createTimeseries(new BigDecimal("1.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.KILO_WATT_HOUR);
        t1.load();

        Long measureTimestamp = System.currentTimeMillis();
        Timeseries<BigDecimal> timeseries = t1.toUnit(TimeseriesUnit.MEGA_WATT);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("EnergyToPowerUnitConversion(Month): " + measureTimestamp.doubleValue() / 1000 + " s");

        Instant expectedFirstTimestamp = ZonedDateTime.of(2016, 1, 1, 0, 15, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualFirstTimestamp = timeseries.getValueMap().keySet().stream().sorted().findFirst().orElse(null);

        Instant expectedLastTimestamp = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualLastTimestamp = timeseries.getValueMap().keySet().stream().max(Instant::compareTo).orElse(null);

        BigDecimal expectedValue = new BigDecimal("0.004");
        BigDecimal actualValue = timeseries.getValue(timestampTo);

        assertEquals(TimeseriesUnit.MEGA_WATT, timeseries.getUnit());
        assertEquals(2976, timeseries.getValueMap().size());
        assertEquals(expectedFirstTimestamp, actualFirstTimestamp);
        assertEquals(expectedLastTimestamp, actualLastTimestamp);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testEnergyToPowerUnitConversionYearWithQuarterHourPeriod() {
        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2017, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<BigDecimal> t1 = createTimeseries(new BigDecimal("1.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.KILO_WATT_HOUR);
        t1.load();

        Long measureTimestamp = System.currentTimeMillis();
        Timeseries<BigDecimal> timeseries = t1.toUnit(TimeseriesUnit.MEGA_WATT);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("EnergyToPowerUnitConversion(Year): " + measureTimestamp.doubleValue() / 1000 + " s");

        Instant expectedFirstTimestamp = ZonedDateTime.of(2016, 1, 1, 0, 15, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualFirstTimestamp = timeseries.getValueMap().keySet().stream().sorted().findFirst().orElse(null);

        Instant expectedLastTimestamp = ZonedDateTime.of(2017, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualLastTimestamp = timeseries.getValueMap().keySet().stream().max(Instant::compareTo).orElse(null);

        BigDecimal expectedValue = new BigDecimal("0.004");
        BigDecimal actualValue = timeseries.getValue(timestampTo);

        assertEquals(TimeseriesUnit.MEGA_WATT, timeseries.getUnit());
        assertEquals(35136, timeseries.getValueMap().size());
        assertEquals(expectedFirstTimestamp, actualFirstTimestamp);
        assertEquals(expectedLastTimestamp, actualLastTimestamp);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testEnergyToPowerUnitConversionRegularDayWithQuarterHourPeriod() {
        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<BigDecimal> t1 = createTimeseries(new BigDecimal("1.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.KILO_WATT_HOUR);
        t1.load();

        Timeseries<BigDecimal> timeseries = t1.toUnit(TimeseriesUnit.KILO_WATT);

        Instant expectedFirstTimestamp = ZonedDateTime.of(2016, 1, 1, 0, 15, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualFirstTimestamp = timeseries.getValueMap().keySet().stream().sorted().findFirst().orElse(null);

        Instant expectedLastTimestamp = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualLastTimestamp = timeseries.getValueMap().keySet().stream().max(Instant::compareTo).orElse(null);

        BigDecimal expectedValue = new BigDecimal("4.0");
        BigDecimal actualValue = timeseries.getValue(timestampTo);

        assertEquals(TimeseriesUnit.KILO_WATT, timeseries.getUnit());
        assertEquals(96, timeseries.getValueMap().size());
        assertEquals(expectedFirstTimestamp, actualFirstTimestamp);
        assertEquals(expectedLastTimestamp, actualLastTimestamp);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testEnergyToPowerUnitConversionDaylightSavingMissingHourWithQuarterHourPeriod() {
        Instant timestampFrom = ZonedDateTime.of(2016, 3, 27, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 3, 28, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<BigDecimal> t1 = createTimeseries(new BigDecimal("1.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.KILO_WATT_HOUR);
        t1.load();

        Timeseries<BigDecimal> timeseries = t1.toUnit(TimeseriesUnit.KILO_WATT);

        Instant expectedFirstTimestamp = ZonedDateTime.of(2016, 3, 27, 0, 15, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualFirstTimestamp = timeseries.getValueMap().keySet().stream().sorted().findFirst().orElse(null);

        Instant expectedLastTimestamp = ZonedDateTime.of(2016, 3, 28, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualLastTimestamp = timeseries.getValueMap().keySet().stream().max(Instant::compareTo).orElse(null);

        BigDecimal expectedValue = new BigDecimal("4.0");
        BigDecimal actualValue = timeseries.getValue(timestampTo);

        assertEquals(TimeseriesUnit.KILO_WATT, timeseries.getUnit());
        assertEquals(92, timeseries.getValueMap().size());
        assertEquals(expectedFirstTimestamp, actualFirstTimestamp);
        assertEquals(expectedLastTimestamp, actualLastTimestamp);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testEnergyToPowerUnitConversionDaylightSavingAdditionalHourWithQuarterHourPeriod() {
        Instant timestampFrom = ZonedDateTime.of(2016, 10, 30, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 10, 31, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<BigDecimal> t1 = createTimeseries(new BigDecimal("1.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.KILO_WATT_HOUR);
        t1.load();

        Timeseries<BigDecimal> timeseries = t1.toUnit(TimeseriesUnit.KILO_WATT);

        Instant expectedFirstTimestamp = ZonedDateTime.of(2016, 10, 30, 0, 15, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualFirstTimestamp = timeseries.getValueMap().keySet().stream().sorted().findFirst().orElse(null);

        Instant expectedLastTimestamp = ZonedDateTime.of(2016, 10, 31, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualLastTimestamp = timeseries.getValueMap().keySet().stream().max(Instant::compareTo).orElse(null);

        BigDecimal expectedValue = new BigDecimal("4.0");
        BigDecimal actualValue = timeseries.getValue(timestampTo);

        assertEquals(TimeseriesUnit.KILO_WATT, timeseries.getUnit());
        assertEquals(100, timeseries.getValueMap().size());
        assertEquals(expectedFirstTimestamp, actualFirstTimestamp);
        assertEquals(expectedLastTimestamp, actualLastTimestamp);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testGasBurnValueUnitConversionDay() {
        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<BigDecimal> burnValueTimeseries = createTimeseries(new BigDecimal("10.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.BURN_VALUE);
        Timeseries<BigDecimal> volumeTimeseries = createTimeseries(new BigDecimal("5.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.CUBIC_METRE);

        burnValueTimeseries.load();
        volumeTimeseries.load();

        Long measureTimestamp = System.currentTimeMillis();
        Timeseries<BigDecimal> timeseries = burnValueTimeseries.multiply(volumeTimeseries);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("GasBurnValueUnitConversion(Day): " + measureTimestamp.doubleValue() / 1000 + " s");

        Instant expectedFirstTimestamp = ZonedDateTime.of(2016, 1, 1, 0, 15, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualFirstTimestamp = timeseries.getValueMap().keySet().stream().sorted().findFirst().orElse(null);

        Instant expectedLastTimestamp = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualLastTimestamp = timeseries.getValueMap().keySet().stream().max(Instant::compareTo).orElse(null);

        Double expectedValue = new BigDecimal("50.0").doubleValue();
        Double actualValue = timeseries.getValue(timestampTo).doubleValue();

        assertEquals(TimeseriesUnit.KILO_WATT_HOUR, timeseries.getUnit());
        assertEquals(96, timeseries.getValueMap().size());
        assertEquals(expectedFirstTimestamp, actualFirstTimestamp);
        assertEquals(expectedLastTimestamp, actualLastTimestamp);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testGasBurnValueUnitConversionMonth() {
        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<BigDecimal> burnValueTimeseries = createTimeseries(new BigDecimal("10.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.BURN_VALUE);
        Timeseries<BigDecimal> volumeTimeseries = createTimeseries(new BigDecimal("5.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.CUBIC_METRE);

        burnValueTimeseries.load();
        volumeTimeseries.load();

        Long measureTimestamp = System.currentTimeMillis();
        Timeseries<BigDecimal> timeseries = burnValueTimeseries.multiply(volumeTimeseries);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("GasBurnValueUnitConversion(Day): " + measureTimestamp.doubleValue() / 1000 + " s");

        Instant expectedFirstTimestamp = ZonedDateTime.of(2016, 1, 1, 0, 15, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualFirstTimestamp = timeseries.getValueMap().keySet().stream().sorted().findFirst().orElse(null);

        Instant expectedLastTimestamp = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualLastTimestamp = timeseries.getValueMap().keySet().stream().max(Instant::compareTo).orElse(null);

        Double expectedValue = new BigDecimal("50.0").doubleValue();
        Double actualValue = timeseries.getValue(timestampTo).doubleValue();

        assertEquals(TimeseriesUnit.KILO_WATT_HOUR, timeseries.getUnit());
        assertEquals(2976, timeseries.getValueMap().size());
        assertEquals(expectedFirstTimestamp, actualFirstTimestamp);
        assertEquals(expectedLastTimestamp, actualLastTimestamp);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testGasBurnValueUnitConversionYear() {
        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2017, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<BigDecimal> burnValueTimeseries = createTimeseries(new BigDecimal("10.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.BURN_VALUE);
        Timeseries<BigDecimal> volumeTimeseries = createTimeseries(new BigDecimal("5.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.CUBIC_METRE);

        burnValueTimeseries.load();
        volumeTimeseries.load();

        Long measureTimestamp = System.currentTimeMillis();
        Timeseries<BigDecimal> timeseries = burnValueTimeseries.multiply(volumeTimeseries);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("GasBurnValueUnitConversion(Day): " + measureTimestamp.doubleValue() / 1000 + " s");

        Instant expectedFirstTimestamp = ZonedDateTime.of(2016, 1, 1, 0, 15, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualFirstTimestamp = timeseries.getValueMap().keySet().stream().sorted().findFirst().orElse(null);

        Instant expectedLastTimestamp = ZonedDateTime.of(2017, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualLastTimestamp = timeseries.getValueMap().keySet().stream().max(Instant::compareTo).orElse(null);

        Double expectedValue = new BigDecimal("50.0").doubleValue();
        Double actualValue = timeseries.getValue(timestampTo).doubleValue();

        assertEquals(TimeseriesUnit.KILO_WATT_HOUR, timeseries.getUnit());
        assertEquals(35136, timeseries.getValueMap().size());
        assertEquals(expectedFirstTimestamp, actualFirstTimestamp);
        assertEquals(expectedLastTimestamp, actualLastTimestamp);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testRemunerationUnitConversionDay() {
        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<BigDecimal> energyTimeseries = createTimeseries(new BigDecimal("50.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.KILO_WATT_HOUR);
        Timeseries<BigDecimal> remunerationTimeseries = createTimeseries(new BigDecimal("2.6"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.EURO_CENT_PER_KILO_WATT_HOUR);

        energyTimeseries.load();
        remunerationTimeseries.load();

        Long measureTimestamp = System.currentTimeMillis();
        Timeseries<BigDecimal> timeseries = energyTimeseries.multiply(remunerationTimeseries).toUnit(TimeseriesUnit.EURO);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("RemunerationUnitConversion(Day): " + measureTimestamp.doubleValue() / 1000 + " s");

        Instant expectedFirstTimestamp = ZonedDateTime.of(2016, 1, 1, 0, 15, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualFirstTimestamp = timeseries.getValueMap().keySet().stream().sorted().findFirst().orElse(null);

        Instant expectedLastTimestamp = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualLastTimestamp = timeseries.getValueMap().keySet().stream().max(Instant::compareTo).orElse(null);

        Double expectedValue = new BigDecimal("1.3").doubleValue();
        Double actualValue = timeseries.getValue(timestampTo).doubleValue();

        assertEquals(TimeseriesUnit.EURO, timeseries.getUnit());
        assertEquals(96, timeseries.getValueMap().size());
        assertEquals(expectedFirstTimestamp, actualFirstTimestamp);
        assertEquals(expectedLastTimestamp, actualLastTimestamp);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testRemunerationUnitConversionMonth() {
        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<BigDecimal> burnValueTimeseries = createTimeseries(new BigDecimal("50.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.KILO_WATT_HOUR);
        Timeseries<BigDecimal> volumeTimeseries = createTimeseries(new BigDecimal("2.6"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.EURO_CENT_PER_KILO_WATT_HOUR);

        burnValueTimeseries.load();
        volumeTimeseries.load();

        Long measureTimestamp = System.currentTimeMillis();
        Timeseries<BigDecimal> timeseries = burnValueTimeseries.multiply(volumeTimeseries).toUnit(TimeseriesUnit.EURO);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("RemunerationUnitConversion(Day): " + measureTimestamp.doubleValue() / 1000 + " s");

        Instant expectedFirstTimestamp = ZonedDateTime.of(2016, 1, 1, 0, 15, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualFirstTimestamp = timeseries.getValueMap().keySet().stream().sorted().findFirst().orElse(null);

        Instant expectedLastTimestamp = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualLastTimestamp = timeseries.getValueMap().keySet().stream().max(Instant::compareTo).orElse(null);

        Double expectedValue = new BigDecimal("1.3").doubleValue();
        Double actualValue = timeseries.getValue(timestampTo).doubleValue();

        assertEquals(TimeseriesUnit.EURO, timeseries.getUnit());
        assertEquals(2976, timeseries.getValueMap().size());
        assertEquals(expectedFirstTimestamp, actualFirstTimestamp);
        assertEquals(expectedLastTimestamp, actualLastTimestamp);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testRemunerationUnitConversionYear() {
        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2017, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<BigDecimal> burnValueTimeseries = createTimeseries(new BigDecimal("50.0"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.KILO_WATT_HOUR);
        Timeseries<BigDecimal> volumeTimeseries = createTimeseries(new BigDecimal("2.6"), new BigDecimalPlugin(), interval, TimeseriesPeriod.MIN15, TimeseriesUnit.EURO_CENT_PER_KILO_WATT_HOUR);

        burnValueTimeseries.load();
        volumeTimeseries.load();

        Long measureTimestamp = System.currentTimeMillis();
        Timeseries<BigDecimal> timeseries = burnValueTimeseries.multiply(volumeTimeseries).toUnit(TimeseriesUnit.EURO);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("RemunerationUnitConversion(Day): " + measureTimestamp.doubleValue() / 1000 + " s");

        Instant expectedFirstTimestamp = ZonedDateTime.of(2016, 1, 1, 0, 15, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualFirstTimestamp = timeseries.getValueMap().keySet().stream().sorted().findFirst().orElse(null);

        Instant expectedLastTimestamp = ZonedDateTime.of(2017, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant actualLastTimestamp = timeseries.getValueMap().keySet().stream().max(Instant::compareTo).orElse(null);

        Double expectedValue = new BigDecimal("1.3").doubleValue();
        Double actualValue = timeseries.getValue(timestampTo).doubleValue();

        assertEquals(TimeseriesUnit.EURO, timeseries.getUnit());
        assertEquals(35136, timeseries.getValueMap().size());
        assertEquals(expectedFirstTimestamp, actualFirstTimestamp);
        assertEquals(expectedLastTimestamp, actualLastTimestamp);
        assertEquals(expectedValue, actualValue);
    }

    private <T> Timeseries<T> createTimeseries(
            T value,
            ValuePlugin<T> valuePlugin,
            TimeseriesInterval interval,
            TimeseriesPeriod period,
            TimeseriesUnit unit) {
        return new Timeseries<>(
                valuePlugin,
                new TimeseriesDataProviderConstantValue<>(valuePlugin, value, period),
                interval,
                period,
                unit);
    }

}
