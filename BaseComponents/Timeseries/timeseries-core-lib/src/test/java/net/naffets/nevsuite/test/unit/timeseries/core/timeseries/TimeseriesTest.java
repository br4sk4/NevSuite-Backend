package net.naffets.nevsuite.test.unit.timeseries.core.timeseries;

import net.naffets.nevsuite.backend.timeseries.core.dataprovider.TimeseriesDataProvider;
import net.naffets.nevsuite.backend.timeseries.core.dataprovider.TimeseriesDataProviderConstantValue;
import net.naffets.nevsuite.backend.timeseries.core.datatype.ValueStatusPair;
import net.naffets.nevsuite.backend.timeseries.core.timeseries.Timeseries;
import net.naffets.nevsuite.backend.timeseries.core.timeseries.TimeseriesInterval;
import net.naffets.nevsuite.backend.timeseries.core.timeseries.TimeseriesPeriod;
import net.naffets.nevsuite.backend.timeseries.core.timeseries.TimeseriesUnit;
import net.naffets.nevsuite.backend.timeseries.core.valueplugin.DoublePlugin;
import net.naffets.nevsuite.backend.timeseries.core.valueplugin.ValueStatusPairPlugin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.SortedSet;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author br4sk4
 * created on 22.03.2016
 */
public class TimeseriesTest {

    private final Logger logger = LogManager.getLogger(Timeseries.class.getName());

    private DTO initTest(
            Instant timestampFrom,
            Instant timestampTo,
            Double firstValue,
            Double otherValue) {

        DTO dto = new DTO();
        ValueStatusPairPlugin<Double, byte[]> valuePlugin;
        TimeseriesDataProvider<ValueStatusPair<Double, byte[]>> dataProvider;

        valuePlugin = new ValueStatusPairPlugin<>(
                new DoublePlugin(),
                new byte[8]
        );

        dataProvider = new TimeseriesDataProviderConstantValue<>(
                valuePlugin,
                new ValueStatusPair<>(firstValue, new byte[8]),
                TimeseriesPeriod.MIN15
        );

        dto.t1 = new Timeseries<>(
                valuePlugin,
                dataProvider,
                new TimeseriesInterval(timestampFrom, timestampTo)
        );

        dataProvider = new TimeseriesDataProviderConstantValue<>(
                valuePlugin,
                new ValueStatusPair<>(otherValue, new byte[8]),
                TimeseriesPeriod.MIN15
        );

        dto.t2 = new Timeseries<>(
                valuePlugin,
                dataProvider,
                new TimeseriesInterval(timestampFrom, timestampTo)
        );

        dto.t1.load(new TimeseriesInterval(timestampFrom, timestampTo));
        dto.t2.load(new TimeseriesInterval(timestampFrom, timestampTo));

        return dto;
    }

    private void doTest(
            TimeseriesInterval interval,
            Timeseries<ValueStatusPair<Double, byte[]>> timeseries,
            Double checkValue,
            Integer checkSize,
            Boolean debug) {
        SortedSet<Instant> keySet = new TreeSet<>(timeseries.getValueMap(interval).keySet());
        Instant checkTimestamp = Instant.from(interval.getTimestampFrom());

        assertEquals(checkSize.intValue(), keySet.size());

        for (Instant timestamp : keySet) {
            ValueStatusPair<Double, byte[]> vp = timeseries.getValue(timestamp);

            StringBuilder sb = new StringBuilder();
            StringBuilder statusHexString = new StringBuilder();
            for (Byte b : vp.getStatus()) {
                statusHexString.append(String.format("%02x", b));
            }

            sb.append(timeseries.getTimeseriesIdentifier())
                    .append(checkTimestamp.plus(timeseries.getPeriod().toTemporalAmount()))
                    .append(":")
                    .append(vp.getValue())
                    .append(timeseries.getUnit() != null && !timeseries.getUnit().equals(TimeseriesUnit.NONE) ? " " : "")
                    .append(timeseries.getUnit() != null ? timeseries.getUnit().toMeasurementUnit().toString() : "")
                    .append(":")
                    .append(statusHexString.toString())
                    .append("; ");

            if (this.logger.isDebugEnabled() && debug) this.logger.debug(sb.toString());

            assertEquals(checkTimestamp.toString(), timestamp.toString());
            assertTrue(checkValue.equals(vp.getValue()));
            assertEquals("0000000000000000", statusHexString.toString());
            checkTimestamp = checkTimestamp.plus(timeseries.getPeriod().toTemporalAmount());
        }
    }

    @Test
    public void testAddOperationDay() {

        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        DTO dto = this.initTest(timestampFrom, timestampTo, 1d, 1d);

        Long measureTimestamp = System.currentTimeMillis();
        Timeseries<ValueStatusPair<Double, byte[]>> timeseries = dto.t1.add(dto.t2);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("AddOperation(Day): " + measureTimestamp.doubleValue() / 1000 + " s");

        doTest(interval, timeseries, 2d, 96, true);

    }

    @Test
    public void testAddOperationMonth() {

        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        DTO dto = this.initTest(timestampFrom, timestampTo, 1d, 1d);

        Long measureTimestamp = System.currentTimeMillis();
        Timeseries<ValueStatusPair<Double, byte[]>> timeseries = dto.t1.add(dto.t2);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("AddOperation(Month): " + measureTimestamp.doubleValue() / 1000 + " s");

        doTest(interval, timeseries, 2d, 2976, false);

    }

    @Test
    public void testAddOperationYear() {

        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2017, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        DTO dto = this.initTest(timestampFrom, timestampTo, 1d, 1d);

        Long measureTimestamp = System.currentTimeMillis();
        Timeseries<ValueStatusPair<Double, byte[]>> timeseries = dto.t1.add(dto.t2);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("AddOperation(Year): " + measureTimestamp.doubleValue() / 1000 + " s");

        doTest(interval, timeseries, 2d, 35136, false);

    }

    @Test
    public void testAddOperationWithNullValue() {

        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        DTO dto = this.initTest(timestampFrom, timestampTo, 1d, null);

        Timeseries<ValueStatusPair<Double, byte[]>> timeseries = dto.t1.add(dto.t2);

        doTest(interval, timeseries, 1d, 96, false);

    }

    @Test
    public void testSubtractOperationDay() {

        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        DTO dto = this.initTest(timestampFrom, timestampTo, 5d, 2d);

        Long measureTimestamp = System.currentTimeMillis();
        Timeseries<ValueStatusPair<Double, byte[]>> timeseries = dto.t1.subtract(dto.t2);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("SubtractOperation(Day): " + measureTimestamp.doubleValue() / 1000 + " s");

        doTest(interval, timeseries, 3d, 96, true);

    }

    @Test
    public void testSubtractOperationMonth() {

        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        DTO dto = this.initTest(timestampFrom, timestampTo, 5d, 2d);

        Long measureTimestamp = System.currentTimeMillis();
        Timeseries<ValueStatusPair<Double, byte[]>> timeseries = dto.t1.subtract(dto.t2);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("SubtractOperation(Month): " + measureTimestamp.doubleValue() / 1000 + " s");

        doTest(interval, timeseries, 3d, 2976, false);

    }

    @Test
    public void testSubtractOperationYear() {

        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2017, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        DTO dto = this.initTest(timestampFrom, timestampTo, 5d, 2d);

        Long measureTimestamp = System.currentTimeMillis();
        Timeseries<ValueStatusPair<Double, byte[]>> timeseries = dto.t1.subtract(dto.t2);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("SubtractOperation(Year): " + measureTimestamp.doubleValue() / 1000 + " s");

        doTest(interval, timeseries, 3d, 35136, false);

    }

    @Test
    public void testSubtractOperationWithNullValue() {

        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        DTO dto = this.initTest(timestampFrom, timestampTo, 1d, null);

        Timeseries<ValueStatusPair<Double, byte[]>> timeseries = dto.t1.subtract(dto.t2);

        doTest(interval, timeseries, 1d, 96, false);

    }

    @Test
    public void testMultiplyOperationDay() {

        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        DTO dto = this.initTest(timestampFrom, timestampTo, 2d, 3d);

        Long measureTimestamp = System.currentTimeMillis();
        Timeseries<ValueStatusPair<Double, byte[]>> timeseries = dto.t1.multiply(dto.t2);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("MultiplyOperation(Day): " + measureTimestamp.doubleValue() / 1000 + " s");

        doTest(interval, timeseries, 6d, 96, true);

    }

    @Test
    public void testMultiplyOperationMonth() {

        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        DTO dto = this.initTest(timestampFrom, timestampTo, 2d, 3d);

        Long measureTimestamp = System.currentTimeMillis();
        Timeseries<ValueStatusPair<Double, byte[]>> timeseries = dto.t1.multiply(dto.t2);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("MultiplyOperation(Month): " + measureTimestamp.doubleValue() / 1000 + " s");

        doTest(interval, timeseries, 6d, 2976, false);

    }

    @Test
    public void testMultiplyOperationYear() {

        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2017, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        DTO dto = this.initTest(timestampFrom, timestampTo, 2d, 3d);

        Long measureTimestamp = System.currentTimeMillis();
        Timeseries<ValueStatusPair<Double, byte[]>> timeseries = dto.t1.multiply(dto.t2);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("MultiplyOperation(Year): " + measureTimestamp.doubleValue() / 1000 + " s");

        doTest(interval, timeseries, 6d, 35136, false);

    }

    @Test
    public void testMultiplyOperationWithNullValue() {

        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        DTO dto = this.initTest(timestampFrom, timestampTo, 1d, null);

        Timeseries<ValueStatusPair<Double, byte[]>> timeseries = dto.t1.multiply(dto.t2);

        doTest(interval, timeseries, 1d, 96, false);

    }

    @Test
    public void testDivideDay() {

        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        DTO dto = this.initTest(timestampFrom, timestampTo, 15d, 3d);

        Long measureTimestamp = System.currentTimeMillis();
        Timeseries<ValueStatusPair<Double, byte[]>> timeseries = dto.t1.divide(dto.t2);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("DivideOperation(Day): " + measureTimestamp.doubleValue() / 1000 + " s");

        doTest(interval, timeseries, 5d, 96, true);

    }

    @Test
    public void testDivideMonth() {

        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        DTO dto = this.initTest(timestampFrom, timestampTo, 15d, 3d);

        Long measureTimestamp = System.currentTimeMillis();
        Timeseries<ValueStatusPair<Double, byte[]>> timeseries = dto.t1.divide(dto.t2);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("DivideOperation(Month): " + measureTimestamp.doubleValue() / 1000 + " s");

        doTest(interval, timeseries, 5d, 2976, false);

    }

    @Test
    public void testDivideYear() {

        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2017, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        DTO dto = this.initTest(timestampFrom, timestampTo, 15d, 3d);

        Long measureTimestamp = System.currentTimeMillis();
        Timeseries<ValueStatusPair<Double, byte[]>> timeseries = dto.t1.divide(dto.t2);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("DivideOperation(Year): " + measureTimestamp.doubleValue() / 1000 + " s");

        doTest(interval, timeseries, 5d, 35136, false);

    }

    @Test
    public void testDivideOperationWithNullValue() {

        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        DTO dto = this.initTest(timestampFrom, timestampTo, 1d, null);

        Timeseries<ValueStatusPair<Double, byte[]>> timeseries = dto.t1.divide(dto.t2);

        doTest(interval, timeseries, 1d, 96, false);

    }

    @Test
    public void testMaxOperationDay() {

        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        DTO dto = this.initTest(timestampFrom, timestampTo, 5d, 2d);

        Long measureTimestamp = System.currentTimeMillis();
        Timeseries<ValueStatusPair<Double, byte[]>> timeseries = dto.t1.max(dto.t2);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("MaxOperation(Day): " + measureTimestamp.doubleValue() / 1000 + " s");

        doTest(interval, timeseries, 5d, 96, true);

    }

    @Test
    public void testMaxOperationMonth() {

        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        DTO dto = this.initTest(timestampFrom, timestampTo, 5d, 2d);

        Long measureTimestamp = System.currentTimeMillis();
        Timeseries<ValueStatusPair<Double, byte[]>> timeseries = dto.t1.max(dto.t2);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("MaxOperation(Month): " + measureTimestamp.doubleValue() / 1000 + " s");

        doTest(interval, timeseries, 5d, 2976, false);

    }

    @Test
    public void testMaxOperationYear() {

        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2017, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        DTO dto = this.initTest(timestampFrom, timestampTo, 5d, 2d);

        Long measureTimestamp = System.currentTimeMillis();
        Timeseries<ValueStatusPair<Double, byte[]>> timeseries = dto.t1.max(dto.t2);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("MaxOperation(Year): " + measureTimestamp.doubleValue() / 1000 + " s");

        doTest(interval, timeseries, 5d, 35136, false);

    }

    @Test
    public void testMaxOperationWithNullValue() {

        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        DTO dto = this.initTest(timestampFrom, timestampTo, 2d, null);

        Timeseries<ValueStatusPair<Double, byte[]>> timeseries = dto.t1.max(dto.t2);

        doTest(interval, timeseries, 2d, 96, false);

    }

    @Test
    public void testMinOperationDay() {

        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        DTO dto = this.initTest(timestampFrom, timestampTo, 5d, 2d);

        Long measureTimestamp = System.currentTimeMillis();
        Timeseries<ValueStatusPair<Double, byte[]>> timeseries = dto.t1.min(dto.t2);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("MinOperation(Day): " + measureTimestamp.doubleValue() / 1000 + " s");

        doTest(interval, timeseries, 2d, 96, true);

    }

    @Test
    public void testMinOperationMonth() {

        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        DTO dto = this.initTest(timestampFrom, timestampTo, 5d, 2d);

        Long measureTimestamp = System.currentTimeMillis();
        Timeseries<ValueStatusPair<Double, byte[]>> timeseries = dto.t1.min(dto.t2);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("MinOperation(Month): " + measureTimestamp.doubleValue() / 1000 + " s");

        doTest(interval, timeseries, 2d, 2976, false);

    }

    @Test
    public void testMinOperationYear() {

        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2017, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        DTO dto = this.initTest(timestampFrom, timestampTo, 5d, 2d);

        Long measureTimestamp = System.currentTimeMillis();
        Timeseries<ValueStatusPair<Double, byte[]>> timeseries = dto.t1.min(dto.t2);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("MinOperation(Year): " + measureTimestamp.doubleValue() / 1000 + " s");

        doTest(interval, timeseries, 2d, 35136, false);

    }

    @Test
    public void testMinOperationWithNullValue() {

        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        DTO dto = this.initTest(timestampFrom, timestampTo, 2d, null);

        Timeseries<ValueStatusPair<Double, byte[]>> timeseries = dto.t1.min(dto.t2);

        doTest(interval, timeseries, 2d, 96, false);

    }

    @Test
    public void testUnitConversionDay() {

        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<ValueStatusPair<Double, byte[]>> timeseries;
        ValueStatusPairPlugin<Double, byte[]> valuePlugin;
        TimeseriesDataProvider<ValueStatusPair<Double, byte[]>> dataProvider;

        valuePlugin = new ValueStatusPairPlugin<>(
                new DoublePlugin(),
                new byte[8]
        );

        dataProvider = new TimeseriesDataProviderConstantValue<>(
                valuePlugin,
                new ValueStatusPair<>(1d, new byte[8]),
                TimeseriesPeriod.MIN15
        );

        timeseries = new Timeseries<>(
                valuePlugin,
                dataProvider,
                new TimeseriesInterval(timestampFrom, timestampTo),
                TimeseriesPeriod.MIN15,
                TimeseriesUnit.MEGA_WATT_HOUR
        );

        timeseries.load(new TimeseriesInterval(timestampFrom, timestampTo));

        Long measureTimestamp = System.currentTimeMillis();
        timeseries = timeseries.toUnit(TimeseriesUnit.KILO_WATT_HOUR);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("UnitConversion(Day): " + measureTimestamp.doubleValue() / 1000 + " s");

        doTest(interval, timeseries, 1000d, 96, true);

    }

    @Test
    public void testUnitConversionMonth() {

        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<ValueStatusPair<Double, byte[]>> timeseries;
        ValueStatusPairPlugin<Double, byte[]> valuePlugin;
        TimeseriesDataProvider<ValueStatusPair<Double, byte[]>> dataProvider;

        valuePlugin = new ValueStatusPairPlugin<>(
                new DoublePlugin(),
                new byte[8]
        );

        dataProvider = new TimeseriesDataProviderConstantValue<>(
                valuePlugin,
                new ValueStatusPair<>(1d, new byte[8]),
                TimeseriesPeriod.MIN15
        );

        timeseries = new Timeseries<>(
                valuePlugin,
                dataProvider,
                new TimeseriesInterval(timestampFrom, timestampTo),
                TimeseriesPeriod.MIN15,
                TimeseriesUnit.MEGA_WATT_HOUR
        );

        timeseries.load(new TimeseriesInterval(timestampFrom, timestampTo));

        Long measureTimestamp = System.currentTimeMillis();
        timeseries = timeseries.toUnit(TimeseriesUnit.KILO_WATT_HOUR);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("UnitConversion(Month): " + measureTimestamp.doubleValue() / 1000 + " s");

        doTest(interval, timeseries, 1000d, 2976, false);

    }

    @Test
    public void testUnitConversionYear() {

        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2017, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<ValueStatusPair<Double, byte[]>> timeseries;
        ValueStatusPairPlugin<Double, byte[]> valuePlugin;
        TimeseriesDataProvider<ValueStatusPair<Double, byte[]>> dataProvider;

        valuePlugin = new ValueStatusPairPlugin<>(
                new DoublePlugin(),
                new byte[8]
        );

        dataProvider = new TimeseriesDataProviderConstantValue<>(
                valuePlugin,
                new ValueStatusPair<>(1d, new byte[8]),
                TimeseriesPeriod.MIN15
        );

        timeseries = new Timeseries<>(
                valuePlugin,
                dataProvider,
                new TimeseriesInterval(timestampFrom, timestampTo),
                TimeseriesPeriod.MIN15,
                TimeseriesUnit.MEGA_WATT_HOUR
        );

        timeseries.load(new TimeseriesInterval(timestampFrom, timestampTo));

        Long measureTimestamp = System.currentTimeMillis();
        timeseries = timeseries.toUnit(TimeseriesUnit.MEGA_WATT_HOUR);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("UnitConversion(Year): " + measureTimestamp.doubleValue() / 1000 + " s");

        doTest(interval, timeseries, 1d, 35136, false);

    }

    @Test
    public void testGasBurnValueUnitConversionDay() {

        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<ValueStatusPair<Double, byte[]>> timeseries;
        Timeseries<ValueStatusPair<Double, byte[]>> burnValueTimeseries;
        Timeseries<ValueStatusPair<Double, byte[]>> volumeTimeseries;

        ValueStatusPairPlugin<Double, byte[]> valuePlugin;
        TimeseriesDataProvider<ValueStatusPair<Double, byte[]>> dataProvider;

        valuePlugin = new ValueStatusPairPlugin<>(
                new DoublePlugin(),
                new byte[8]
        );

        dataProvider = new TimeseriesDataProviderConstantValue<>(
                valuePlugin,
                new ValueStatusPair<>(10d, new byte[8]),
                TimeseriesPeriod.MIN15
        );

        burnValueTimeseries = new Timeseries<>(
                valuePlugin,
                dataProvider,
                new TimeseriesInterval(timestampFrom, timestampTo),
                TimeseriesPeriod.MIN15,
                TimeseriesUnit.BURN_VALUE
        );

        burnValueTimeseries.load(new TimeseriesInterval(timestampFrom, timestampTo));

        dataProvider = new TimeseriesDataProviderConstantValue<>(
                valuePlugin,
                new ValueStatusPair<>(5d, new byte[8]),
                TimeseriesPeriod.MIN15
        );

        volumeTimeseries = new Timeseries<>(
                valuePlugin,
                dataProvider,
                new TimeseriesInterval(timestampFrom, timestampTo),
                TimeseriesPeriod.MIN15,
                TimeseriesUnit.CUBIC_METRE
        );

        volumeTimeseries.load(new TimeseriesInterval(timestampFrom, timestampTo));

        Long measureTimestamp = System.currentTimeMillis();
        timeseries = burnValueTimeseries.multiply(volumeTimeseries);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("GasBurnValueUnitConversion(Day): " + measureTimestamp.doubleValue() / 1000 + " s");

        doTest(interval, timeseries, 50d, 96, true);

    }

    @Test
    public void testGasBurnValueUnitConversionMonth() {

        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<ValueStatusPair<Double, byte[]>> timeseries;
        Timeseries<ValueStatusPair<Double, byte[]>> burnValueTimeseries;
        Timeseries<ValueStatusPair<Double, byte[]>> volumeTimeseries;

        ValueStatusPairPlugin<Double, byte[]> valuePlugin;
        TimeseriesDataProvider<ValueStatusPair<Double, byte[]>> dataProvider;

        valuePlugin = new ValueStatusPairPlugin<>(
                new DoublePlugin(),
                new byte[8]
        );

        dataProvider = new TimeseriesDataProviderConstantValue<>(
                valuePlugin,
                new ValueStatusPair<>(10d, new byte[8]),
                TimeseriesPeriod.MIN15
        );

        burnValueTimeseries = new Timeseries<>(
                valuePlugin,
                dataProvider,
                new TimeseriesInterval(timestampFrom, timestampTo),
                TimeseriesPeriod.MIN15,
                TimeseriesUnit.BURN_VALUE
        );

        burnValueTimeseries.load(new TimeseriesInterval(timestampFrom, timestampTo));

        dataProvider = new TimeseriesDataProviderConstantValue<>(
                valuePlugin,
                new ValueStatusPair<>(5d, new byte[8]),
                TimeseriesPeriod.MIN15
        );

        volumeTimeseries = new Timeseries<>(
                valuePlugin,
                dataProvider,
                new TimeseriesInterval(timestampFrom, timestampTo),
                TimeseriesPeriod.MIN15,
                TimeseriesUnit.CUBIC_METRE
        );

        volumeTimeseries.load(new TimeseriesInterval(timestampFrom, timestampTo));

        Long measureTimestamp = System.currentTimeMillis();
        timeseries = burnValueTimeseries.multiply(volumeTimeseries);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("GasBurnValueUnitConversion(Month): " + measureTimestamp.doubleValue() / 1000 + " s");

        doTest(interval, timeseries, 50d, 2976, false);

    }

    @Test
    public void testGasBurnValueUnitConversionYear() {

        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2017, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<ValueStatusPair<Double, byte[]>> timeseries;
        Timeseries<ValueStatusPair<Double, byte[]>> burnValueTimeseries;
        Timeseries<ValueStatusPair<Double, byte[]>> volumeTimeseries;

        ValueStatusPairPlugin<Double, byte[]> valuePlugin;
        TimeseriesDataProvider<ValueStatusPair<Double, byte[]>> dataProvider;

        valuePlugin = new ValueStatusPairPlugin<>(
                new DoublePlugin(),
                new byte[8]
        );

        dataProvider = new TimeseriesDataProviderConstantValue<>(
                valuePlugin,
                new ValueStatusPair<>(10d, new byte[8]),
                TimeseriesPeriod.MIN15
        );

        burnValueTimeseries = new Timeseries<>(
                valuePlugin,
                dataProvider,
                new TimeseriesInterval(timestampFrom, timestampTo),
                TimeseriesPeriod.MIN15,
                TimeseriesUnit.BURN_VALUE
        );

        burnValueTimeseries.load(new TimeseriesInterval(timestampFrom, timestampTo));

        dataProvider = new TimeseriesDataProviderConstantValue<>(
                valuePlugin,
                new ValueStatusPair<>(5d, new byte[8]),
                TimeseriesPeriod.MIN15
        );

        volumeTimeseries = new Timeseries<>(
                valuePlugin,
                dataProvider,
                new TimeseriesInterval(timestampFrom, timestampTo),
                TimeseriesPeriod.MIN15,
                TimeseriesUnit.CUBIC_METRE
        );

        volumeTimeseries.load(new TimeseriesInterval(timestampFrom, timestampTo));

        Long measureTimestamp = System.currentTimeMillis();
        timeseries = burnValueTimeseries.multiply(volumeTimeseries);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("GasBurnValueUnitConversion(Year): " + measureTimestamp.doubleValue() / 1000 + " s");

        doTest(interval, timeseries, 50d, 35136, false);

    }

    @Test
    public void testRemunerationUnitConversionDay() {

        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<ValueStatusPair<Double, byte[]>> timeseries;
        Timeseries<ValueStatusPair<Double, byte[]>> energyTimeseries;
        Timeseries<ValueStatusPair<Double, byte[]>> remunerationTimeseries;

        ValueStatusPairPlugin<Double, byte[]> valuePlugin;
        TimeseriesDataProvider<ValueStatusPair<Double, byte[]>> dataProvider;

        valuePlugin = new ValueStatusPairPlugin<>(
                new DoublePlugin(),
                new byte[8]
        );

        dataProvider = new TimeseriesDataProviderConstantValue<>(
                valuePlugin,
                new ValueStatusPair<>(50d, new byte[8]),
                TimeseriesPeriod.MIN15
        );

        energyTimeseries = new Timeseries<>(
                valuePlugin,
                dataProvider,
                new TimeseriesInterval(timestampFrom, timestampTo),
                TimeseriesPeriod.MIN15,
                TimeseriesUnit.KILO_WATT_HOUR
        );

        energyTimeseries.load(new TimeseriesInterval(timestampFrom, timestampTo));

        dataProvider = new TimeseriesDataProviderConstantValue<>(
                valuePlugin,
                new ValueStatusPair<>(2.6d, new byte[8]),
                TimeseriesPeriod.MIN15
        );

        remunerationTimeseries = new Timeseries<>(
                valuePlugin,
                dataProvider,
                new TimeseriesInterval(timestampFrom, timestampTo),
                TimeseriesPeriod.MIN15,
                TimeseriesUnit.EURO_CENT_PER_KILO_WATT_HOUR
        );

        remunerationTimeseries.load(new TimeseriesInterval(timestampFrom, timestampTo));

        Long measureTimestamp = System.currentTimeMillis();
        timeseries = energyTimeseries.multiply(remunerationTimeseries).toUnit(TimeseriesUnit.EURO);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("RemunerationUnitConversion(Day): " + measureTimestamp.doubleValue() / 1000 + " s");

        doTest(interval, timeseries, 1.3d, 96, true);

    }

    @Test
    public void testRemunerationUnitConversionMonth() {

        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<ValueStatusPair<Double, byte[]>> timeseries;
        Timeseries<ValueStatusPair<Double, byte[]>> energyTimeseries;
        Timeseries<ValueStatusPair<Double, byte[]>> remunerationTimeseries;

        ValueStatusPairPlugin<Double, byte[]> valuePlugin;
        TimeseriesDataProvider<ValueStatusPair<Double, byte[]>> dataProvider;

        valuePlugin = new ValueStatusPairPlugin<>(
                new DoublePlugin(),
                new byte[8]
        );

        dataProvider = new TimeseriesDataProviderConstantValue<>(
                valuePlugin,
                new ValueStatusPair<>(50d, new byte[8]),
                TimeseriesPeriod.MIN15
        );

        energyTimeseries = new Timeseries<>(
                valuePlugin,
                dataProvider,
                new TimeseriesInterval(timestampFrom, timestampTo),
                TimeseriesPeriod.MIN15,
                TimeseriesUnit.KILO_WATT_HOUR
        );

        energyTimeseries.load(new TimeseriesInterval(timestampFrom, timestampTo));

        dataProvider = new TimeseriesDataProviderConstantValue<>(
                valuePlugin,
                new ValueStatusPair<>(2.6d, new byte[8]),
                TimeseriesPeriod.MIN15
        );

        remunerationTimeseries = new Timeseries<>(
                valuePlugin,
                dataProvider,
                new TimeseriesInterval(timestampFrom, timestampTo),
                TimeseriesPeriod.MIN15,
                TimeseriesUnit.EURO_CENT_PER_KILO_WATT_HOUR
        );

        remunerationTimeseries.load(new TimeseriesInterval(timestampFrom, timestampTo));

        Long measureTimestamp = System.currentTimeMillis();
        timeseries = energyTimeseries.multiply(remunerationTimeseries).toUnit(TimeseriesUnit.EURO);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("RemunerationUnitConversion(Month): " + measureTimestamp.doubleValue() / 1000 + " s");

        doTest(interval, timeseries, 1.3d, 2976, false);

    }

    @Test
    public void testRemunerationUnitConversionYear() {

        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2017, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<ValueStatusPair<Double, byte[]>> timeseries;
        Timeseries<ValueStatusPair<Double, byte[]>> energyTimeseries;
        Timeseries<ValueStatusPair<Double, byte[]>> remunerationTimeseries;

        ValueStatusPairPlugin<Double, byte[]> valuePlugin;
        TimeseriesDataProvider<ValueStatusPair<Double, byte[]>> dataProvider;

        valuePlugin = new ValueStatusPairPlugin<>(
                new DoublePlugin(),
                new byte[8]
        );

        dataProvider = new TimeseriesDataProviderConstantValue<>(
                valuePlugin,
                new ValueStatusPair<>(50d, new byte[8]),
                TimeseriesPeriod.MIN15
        );

        energyTimeseries = new Timeseries<>(
                valuePlugin,
                dataProvider,
                new TimeseriesInterval(timestampFrom, timestampTo),
                TimeseriesPeriod.MIN15,
                TimeseriesUnit.KILO_WATT_HOUR
        );

        energyTimeseries.load(new TimeseriesInterval(timestampFrom, timestampTo));

        dataProvider = new TimeseriesDataProviderConstantValue<>(
                valuePlugin,
                new ValueStatusPair<>(2.6d, new byte[8]),
                TimeseriesPeriod.MIN15
        );

        remunerationTimeseries = new Timeseries<>(
                valuePlugin,
                dataProvider,
                new TimeseriesInterval(timestampFrom, timestampTo),
                TimeseriesPeriod.MIN15,
                TimeseriesUnit.EURO_CENT_PER_KILO_WATT_HOUR
        );

        remunerationTimeseries.load(new TimeseriesInterval(timestampFrom, timestampTo));

        Long measureTimestamp = System.currentTimeMillis();
        timeseries = energyTimeseries.multiply(remunerationTimeseries).toUnit(TimeseriesUnit.EURO);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("RemunerationUnitConversion(Year): " + measureTimestamp.doubleValue() / 1000 + " s");

        doTest(interval, timeseries, 1.3d, 35136, false);

    }

    @Test
    public void testPowerToEnergyUnitConversionDay() {

        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<ValueStatusPair<Double, byte[]>> timeseries;
        Timeseries<ValueStatusPair<Double, byte[]>> powerTimeseries;

        ValueStatusPairPlugin<Double, byte[]> valuePlugin;
        TimeseriesDataProvider<ValueStatusPair<Double, byte[]>> dataProvider;

        valuePlugin = new ValueStatusPairPlugin<>(
                new DoublePlugin(),
                new byte[8]
        );

        dataProvider = new TimeseriesDataProviderConstantValue<>(
                valuePlugin,
                new ValueStatusPair<>(1d, new byte[8]),
                TimeseriesPeriod.MIN15
        );

        powerTimeseries = new Timeseries<>(
                valuePlugin,
                dataProvider,
                new TimeseriesInterval(timestampFrom, timestampTo),
                TimeseriesPeriod.MIN15,
                TimeseriesUnit.KILO_WATT
        );

        powerTimeseries.load(new TimeseriesInterval(timestampFrom, timestampTo));

        Long measureTimestamp = System.currentTimeMillis();
        timeseries = powerTimeseries.toUnit(TimeseriesUnit.KILO_WATT_HOUR);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("PowerToEnergyUnitConversion(Day): " + measureTimestamp.doubleValue() / 1000 + " s");

        doTest(interval, timeseries, 4d, 96, true);

    }

    @Test
    public void testPowerToEnergyUnitConversionMonth() {

        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<ValueStatusPair<Double, byte[]>> timeseries;
        Timeseries<ValueStatusPair<Double, byte[]>> powerTimeseries;

        ValueStatusPairPlugin<Double, byte[]> valuePlugin;
        TimeseriesDataProvider<ValueStatusPair<Double, byte[]>> dataProvider;

        valuePlugin = new ValueStatusPairPlugin<>(
                new DoublePlugin(),
                new byte[8]
        );

        dataProvider = new TimeseriesDataProviderConstantValue<>(
                valuePlugin,
                new ValueStatusPair<>(1d, new byte[8]),
                TimeseriesPeriod.MIN15
        );

        powerTimeseries = new Timeseries<>(
                valuePlugin,
                dataProvider,
                new TimeseriesInterval(timestampFrom, timestampTo),
                TimeseriesPeriod.MIN15,
                TimeseriesUnit.KILO_WATT
        );

        powerTimeseries.load(new TimeseriesInterval(timestampFrom, timestampTo));

        Long measureTimestamp = System.currentTimeMillis();
        timeseries = powerTimeseries.toUnit(TimeseriesUnit.KILO_WATT_HOUR);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("PowerToEnergyUnitConversion(Month): " + measureTimestamp.doubleValue() / 1000 + " s");

        doTest(interval, timeseries, 4d, 2976, false);

    }

    @Test
    public void testPowerToEnergyUnitConversionYear() {

        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2017, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));


        Timeseries<ValueStatusPair<Double, byte[]>> timeseries;
        Timeseries<ValueStatusPair<Double, byte[]>> powerTimeseries;

        ValueStatusPairPlugin<Double, byte[]> valuePlugin;
        TimeseriesDataProvider<ValueStatusPair<Double, byte[]>> dataProvider;

        valuePlugin = new ValueStatusPairPlugin<>(
                new DoublePlugin(),
                new byte[8]
        );

        dataProvider = new TimeseriesDataProviderConstantValue<>(
                valuePlugin,
                new ValueStatusPair<>(1d, new byte[8]),
                TimeseriesPeriod.MIN15
        );

        powerTimeseries = new Timeseries<>(
                valuePlugin,
                dataProvider,
                new TimeseriesInterval(timestampFrom, timestampTo),
                TimeseriesPeriod.MIN15,
                TimeseriesUnit.KILO_WATT
        );

        powerTimeseries.load(new TimeseriesInterval(timestampFrom, timestampTo));

        Long measureTimestamp = System.currentTimeMillis();
        timeseries = powerTimeseries.toUnit(TimeseriesUnit.KILO_WATT_HOUR);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("PowerToEnergyUnitConversion(Year): " + measureTimestamp.doubleValue() / 1000 + " s");

        doTest(interval, timeseries, 4d, 35136, false);

    }

    @Test
    public void testPowerToEnergyUnitConversionRegular() {

        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<ValueStatusPair<Double, byte[]>> timeseries;
        Timeseries<ValueStatusPair<Double, byte[]>> powerTimeseries;

        ValueStatusPairPlugin<Double, byte[]> valuePlugin;
        TimeseriesDataProvider<ValueStatusPair<Double, byte[]>> dataProvider;

        valuePlugin = new ValueStatusPairPlugin<>(
                new DoublePlugin(),
                new byte[8]
        );

        dataProvider = new TimeseriesDataProviderConstantValue<>(
                valuePlugin,
                new ValueStatusPair<>(24d, new byte[8]),
                TimeseriesPeriod.DAY1
        );

        powerTimeseries = new Timeseries<>(
                valuePlugin,
                dataProvider,
                new TimeseriesInterval(timestampFrom, timestampTo),
                TimeseriesPeriod.DAY1,
                TimeseriesUnit.KILO_WATT
        );

        powerTimeseries.load(new TimeseriesInterval(timestampFrom, timestampTo));

        Long measureTimestamp = System.currentTimeMillis();
        timeseries = powerTimeseries.toUnit(TimeseriesUnit.KILO_WATT_HOUR);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("PowerToEnergyUnitConversion(Day): " + measureTimestamp.doubleValue() / 1000 + " s");

        doTest(interval, timeseries, 1d, 1, false);

    }

    @Test
    public void testPowerToEnergyUnitConversionMissingHour() {

        Instant timestampFrom = ZonedDateTime.of(2016, 3, 27, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 3, 28, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<ValueStatusPair<Double, byte[]>> timeseries;
        Timeseries<ValueStatusPair<Double, byte[]>> powerTimeseries;

        ValueStatusPairPlugin<Double, byte[]> valuePlugin;
        TimeseriesDataProvider<ValueStatusPair<Double, byte[]>> dataProvider;

        valuePlugin = new ValueStatusPairPlugin<>(
                new DoublePlugin(),
                new byte[8]
        );

        dataProvider = new TimeseriesDataProviderConstantValue<>(
                valuePlugin,
                new ValueStatusPair<>(23d, new byte[8]),
                TimeseriesPeriod.DAY1
        );

        powerTimeseries = new Timeseries<>(
                valuePlugin,
                dataProvider,
                new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET")),
                TimeseriesPeriod.DAY1,
                TimeseriesUnit.KILO_WATT
        );

        powerTimeseries.load(new TimeseriesInterval(timestampFrom, timestampTo));

        Long measureTimestamp = System.currentTimeMillis();
        timeseries = powerTimeseries.toUnit(TimeseriesUnit.KILO_WATT_HOUR);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("PowerToEnergyUnitConversion(Day): " + measureTimestamp.doubleValue() / 1000 + " s");

        doTest(interval, timeseries, 1d, 1, false);

    }

    @Test
    public void testPowerToEnergyUnitConversionAdditionalHour() {

        Instant timestampFrom = ZonedDateTime.of(2016, 10, 30, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 10, 31, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<ValueStatusPair<Double, byte[]>> timeseries;
        Timeseries<ValueStatusPair<Double, byte[]>> powerTimeseries;

        ValueStatusPairPlugin<Double, byte[]> valuePlugin;
        TimeseriesDataProvider<ValueStatusPair<Double, byte[]>> dataProvider;

        valuePlugin = new ValueStatusPairPlugin<>(
                new DoublePlugin(),
                new byte[8]
        );

        dataProvider = new TimeseriesDataProviderConstantValue<>(
                valuePlugin,
                new ValueStatusPair<>(25d, new byte[8]),
                TimeseriesPeriod.DAY1
        );

        powerTimeseries = new Timeseries<>(
                valuePlugin,
                dataProvider,
                new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET")),
                TimeseriesPeriod.DAY1,
                TimeseriesUnit.KILO_WATT
        );

        powerTimeseries.load(new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET")));

        Long measureTimestamp = System.currentTimeMillis();
        timeseries = powerTimeseries.toUnit(TimeseriesUnit.KILO_WATT_HOUR);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("PowerToEnergyUnitConversion(Day): " + measureTimestamp.doubleValue() / 1000 + " s");

        doTest(interval, timeseries, 1d, 1, false);

    }

    @Test
    public void testEnergyToPowerUnitConversionDay() {

        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<ValueStatusPair<Double, byte[]>> timeseries;
        Timeseries<ValueStatusPair<Double, byte[]>> energyTimeseries;

        ValueStatusPairPlugin<Double, byte[]> valuePlugin;
        TimeseriesDataProvider<ValueStatusPair<Double, byte[]>> dataProvider;

        valuePlugin = new ValueStatusPairPlugin<>(
                new DoublePlugin(),
                new byte[8]
        );

        dataProvider = new TimeseriesDataProviderConstantValue<>(
                valuePlugin,
                new ValueStatusPair<>(1d, new byte[8]),
                TimeseriesPeriod.MIN15
        );

        energyTimeseries = new Timeseries<>(
                valuePlugin,
                dataProvider,
                new TimeseriesInterval(timestampFrom, timestampTo),
                TimeseriesPeriod.MIN15,
                TimeseriesUnit.KILO_WATT_HOUR
        );

        energyTimeseries.load(new TimeseriesInterval(timestampFrom, timestampTo));

        Long measureTimestamp = System.currentTimeMillis();
        timeseries = energyTimeseries.toUnit(TimeseriesUnit.KILO_WATT);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("EnergyToPowerUnitConversion(Day): " + measureTimestamp.doubleValue() / 1000 + " s");

        doTest(interval, timeseries, 0.25d, 96, true);

    }

    @Test
    public void testEnergyToPowerUnitConversionMonth() {

        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<ValueStatusPair<Double, byte[]>> timeseries;
        Timeseries<ValueStatusPair<Double, byte[]>> energyTimeseries;

        ValueStatusPairPlugin<Double, byte[]> valuePlugin;
        TimeseriesDataProvider<ValueStatusPair<Double, byte[]>> dataProvider;

        valuePlugin = new ValueStatusPairPlugin<>(
                new DoublePlugin(),
                new byte[8]
        );

        dataProvider = new TimeseriesDataProviderConstantValue<>(
                valuePlugin,
                new ValueStatusPair<>(1d, new byte[8]),
                TimeseriesPeriod.MIN15
        );

        energyTimeseries = new Timeseries<>(
                valuePlugin,
                dataProvider,
                new TimeseriesInterval(timestampFrom, timestampTo),
                TimeseriesPeriod.MIN15,
                TimeseriesUnit.KILO_WATT_HOUR
        );

        energyTimeseries.load(new TimeseriesInterval(timestampFrom, timestampTo));

        Long measureTimestamp = System.currentTimeMillis();
        timeseries = energyTimeseries.toUnit(TimeseriesUnit.KILO_WATT);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("EnergyToPowerUnitConversion(Month): " + measureTimestamp.doubleValue() / 1000 + " s");

        doTest(interval, timeseries, 0.25d, 2976, false);

    }

    @Test
    public void testEnergyToPowerUnitConversionYear() {

        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2017, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<ValueStatusPair<Double, byte[]>> timeseries;
        Timeseries<ValueStatusPair<Double, byte[]>> energyTimeseries;

        ValueStatusPairPlugin<Double, byte[]> valuePlugin;
        TimeseriesDataProvider<ValueStatusPair<Double, byte[]>> dataProvider;

        valuePlugin = new ValueStatusPairPlugin<>(
                new DoublePlugin(),
                new byte[8]
        );

        dataProvider = new TimeseriesDataProviderConstantValue<>(
                valuePlugin,
                new ValueStatusPair<>(1d, new byte[8]),
                TimeseriesPeriod.MIN15
        );

        energyTimeseries = new Timeseries<>(
                valuePlugin,
                dataProvider,
                new TimeseriesInterval(timestampFrom, timestampTo),
                TimeseriesPeriod.MIN15,
                TimeseriesUnit.KILO_WATT_HOUR
        );

        energyTimeseries.load(new TimeseriesInterval(timestampFrom, timestampTo));

        Long measureTimestamp = System.currentTimeMillis();
        timeseries = energyTimeseries.toUnit(TimeseriesUnit.KILO_WATT);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("EnergyToPowerUnitConversion(Year): " + measureTimestamp.doubleValue() / 1000 + " s");

        doTest(interval, timeseries, 0.25d, 35136, false);

    }

    @Test
    public void testEnergyToPowerUnitConversionRegular() {

        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<ValueStatusPair<Double, byte[]>> timeseries;
        Timeseries<ValueStatusPair<Double, byte[]>> powerTimeseries;

        ValueStatusPairPlugin<Double, byte[]> valuePlugin;
        TimeseriesDataProvider<ValueStatusPair<Double, byte[]>> dataProvider;

        valuePlugin = new ValueStatusPairPlugin<>(
                new DoublePlugin(),
                new byte[8]
        );

        dataProvider = new TimeseriesDataProviderConstantValue<>(
                valuePlugin,
                new ValueStatusPair<>(1d, new byte[8]),
                TimeseriesPeriod.DAY1
        );

        powerTimeseries = new Timeseries<>(
                valuePlugin,
                dataProvider,
                new TimeseriesInterval(timestampFrom, timestampTo),
                TimeseriesPeriod.DAY1,
                TimeseriesUnit.KILO_WATT_HOUR
        );

        powerTimeseries.load(new TimeseriesInterval(timestampFrom, timestampTo));

        Long measureTimestamp = System.currentTimeMillis();
        timeseries = powerTimeseries.toUnit(TimeseriesUnit.KILO_WATT);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("EnergyToPowerUnitConversionRegular: " + measureTimestamp.doubleValue() / 1000 + " s");

        doTest(interval, timeseries, 24d, 1, false);

    }

    @Test
    public void testEnergyToPowerUnitConversionMissingHour() {

        Instant timestampFrom = ZonedDateTime.of(2016, 3, 27, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 3, 28, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<ValueStatusPair<Double, byte[]>> timeseries;
        Timeseries<ValueStatusPair<Double, byte[]>> powerTimeseries;

        ValueStatusPairPlugin<Double, byte[]> valuePlugin;
        TimeseriesDataProvider<ValueStatusPair<Double, byte[]>> dataProvider;

        valuePlugin = new ValueStatusPairPlugin<>(
                new DoublePlugin(),
                new byte[8]
        );

        dataProvider = new TimeseriesDataProviderConstantValue<>(
                valuePlugin,
                new ValueStatusPair<>(1d, new byte[8]),
                TimeseriesPeriod.DAY1
        );

        powerTimeseries = new Timeseries<>(
                valuePlugin,
                dataProvider,
                new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET")),
                TimeseriesPeriod.DAY1,
                TimeseriesUnit.KILO_WATT_HOUR
        );

        powerTimeseries.load(new TimeseriesInterval(timestampFrom, timestampTo));

        Long measureTimestamp = System.currentTimeMillis();
        timeseries = powerTimeseries.toUnit(TimeseriesUnit.KILO_WATT);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("EnergyToPowerUnitConversionMissingHour: " + measureTimestamp.doubleValue() / 1000 + " s");

        doTest(interval, timeseries, 23d, 1, false);

    }

    @Test
    public void testEnergyToPowerUnitConversionAdditionalHour() {

        Instant timestampFrom = ZonedDateTime.of(2016, 10, 30, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 10, 31, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<ValueStatusPair<Double, byte[]>> timeseries;
        Timeseries<ValueStatusPair<Double, byte[]>> powerTimeseries;

        ValueStatusPairPlugin<Double, byte[]> valuePlugin;
        TimeseriesDataProvider<ValueStatusPair<Double, byte[]>> dataProvider;

        valuePlugin = new ValueStatusPairPlugin<>(
                new DoublePlugin(),
                new byte[8]
        );

        dataProvider = new TimeseriesDataProviderConstantValue<>(
                valuePlugin,
                new ValueStatusPair<>(1d, new byte[8]),
                TimeseriesPeriod.DAY1
        );

        powerTimeseries = new Timeseries<>(
                valuePlugin,
                dataProvider,
                new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET")),
                TimeseriesPeriod.DAY1,
                TimeseriesUnit.KILO_WATT_HOUR
        );

        powerTimeseries.load(new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET")));

        Long measureTimestamp = System.currentTimeMillis();
        timeseries = powerTimeseries.toUnit(TimeseriesUnit.KILO_WATT);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("EnergyToPowerUnitConversionAdditionalHour: " + measureTimestamp.doubleValue() / 1000 + " s");

        doTest(interval, timeseries, 25d, 1, false);

    }

    @Test
    public void testSpreadOperationWithEnergyUnitDay() {

        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<ValueStatusPair<Double, byte[]>> timeseries;

        ValueStatusPairPlugin<Double, byte[]> valuePlugin;
        TimeseriesDataProvider<ValueStatusPair<Double, byte[]>> dataProvider;

        valuePlugin = new ValueStatusPairPlugin<>(
                new DoublePlugin(),
                new byte[8]
        );

        dataProvider = new TimeseriesDataProviderConstantValue<>(
                valuePlugin,
                new ValueStatusPair<>(1d, new byte[8]),
                TimeseriesPeriod.HOUR1
        );

        timeseries = new Timeseries<>(
                valuePlugin,
                dataProvider,
                new TimeseriesInterval(timestampFrom, timestampTo),
                TimeseriesPeriod.HOUR1,
                TimeseriesUnit.KILO_WATT_HOUR
        );

        timeseries.load(new TimeseriesInterval(timestampFrom, timestampTo));
        doTest(interval, timeseries, 1d, 24, false);

        Long measureTimestamp = System.currentTimeMillis();
        timeseries = timeseries.toPeriod(TimeseriesPeriod.MIN15);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("SpreadOperationWithEnergyUnit(Day): " + measureTimestamp.doubleValue() / 1000 + " s");

        doTest(interval, timeseries, 0.25d, 96, true);

    }

    @Test
    public void testSpreadOperationWithEnergyUnitMonth() {

        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<ValueStatusPair<Double, byte[]>> timeseries;

        ValueStatusPairPlugin<Double, byte[]> valuePlugin;
        TimeseriesDataProvider<ValueStatusPair<Double, byte[]>> dataProvider;

        valuePlugin = new ValueStatusPairPlugin<>(
                new DoublePlugin(),
                new byte[8]
        );

        dataProvider = new TimeseriesDataProviderConstantValue<>(
                valuePlugin,
                new ValueStatusPair<>(1d, new byte[8]),
                TimeseriesPeriod.HOUR1
        );

        timeseries = new Timeseries<>(
                valuePlugin,
                dataProvider,
                new TimeseriesInterval(timestampFrom, timestampTo),
                TimeseriesPeriod.HOUR1,
                TimeseriesUnit.KILO_WATT_HOUR
        );

        timeseries.load(new TimeseriesInterval(timestampFrom, timestampTo));
        doTest(interval, timeseries, 1d, 744, false);

        Long measureTimestamp = System.currentTimeMillis();
        timeseries = timeseries.toPeriod(TimeseriesPeriod.MIN15);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("SpreadOperationWithEnergyUnit(Month): " + measureTimestamp.doubleValue() / 1000 + " s");

        doTest(interval, timeseries, 0.25d, 2976, false);

    }

    @Test
    public void testSpreadOperationWithEnergyUnitYear() {

        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2017, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<ValueStatusPair<Double, byte[]>> timeseries;

        ValueStatusPairPlugin<Double, byte[]> valuePlugin;
        TimeseriesDataProvider<ValueStatusPair<Double, byte[]>> dataProvider;

        valuePlugin = new ValueStatusPairPlugin<>(
                new DoublePlugin(),
                new byte[8]
        );

        dataProvider = new TimeseriesDataProviderConstantValue<>(
                valuePlugin,
                new ValueStatusPair<>(1d, new byte[8]),
                TimeseriesPeriod.HOUR1
        );

        timeseries = new Timeseries<>(
                valuePlugin,
                dataProvider,
                new TimeseriesInterval(timestampFrom, timestampTo),
                TimeseriesPeriod.HOUR1,
                TimeseriesUnit.KILO_WATT_HOUR
        );

        timeseries.load(new TimeseriesInterval(timestampFrom, timestampTo));
        doTest(interval, timeseries, 1d, 8784, false);

        Long measureTimestamp = System.currentTimeMillis();
        timeseries = timeseries.toPeriod(TimeseriesPeriod.MIN15);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("SpreadOperationWithEnergyUnit(Year): " + measureTimestamp.doubleValue() / 1000 + " s");

        doTest(interval, timeseries, 0.25d, 35136, false);

    }

    @Test
    public void testSpreadOperationWithPowerUnitDay() {

        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<ValueStatusPair<Double, byte[]>> timeseries;

        ValueStatusPairPlugin<Double, byte[]> valuePlugin;
        TimeseriesDataProvider<ValueStatusPair<Double, byte[]>> dataProvider;

        valuePlugin = new ValueStatusPairPlugin<>(
                new DoublePlugin(),
                new byte[8]
        );

        dataProvider = new TimeseriesDataProviderConstantValue<>(
                valuePlugin,
                new ValueStatusPair<>(1d, new byte[8]),
                TimeseriesPeriod.HOUR1
        );

        timeseries = new Timeseries<>(
                valuePlugin,
                dataProvider,
                new TimeseriesInterval(timestampFrom, timestampTo),
                TimeseriesPeriod.HOUR1,
                TimeseriesUnit.KILO_WATT
        );

        timeseries.load(new TimeseriesInterval(timestampFrom, timestampTo));
        doTest(interval, timeseries, 1d, 24, false);

        Long measureTimestamp = System.currentTimeMillis();
        timeseries = timeseries.toPeriod(TimeseriesPeriod.MIN15);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("SpreadOperationWithPowerUnit(Day): " + measureTimestamp.doubleValue() / 1000 + " s");

        doTest(interval, timeseries, 1d, 96, true);

    }

    @Test
    public void testSpreadOperationWithPowerUnitMonth() {

        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<ValueStatusPair<Double, byte[]>> timeseries;

        ValueStatusPairPlugin<Double, byte[]> valuePlugin;
        TimeseriesDataProvider<ValueStatusPair<Double, byte[]>> dataProvider;

        valuePlugin = new ValueStatusPairPlugin<>(
                new DoublePlugin(),
                new byte[8]
        );

        dataProvider = new TimeseriesDataProviderConstantValue<>(
                valuePlugin,
                new ValueStatusPair<>(1d, new byte[8]),
                TimeseriesPeriod.HOUR1
        );

        timeseries = new Timeseries<>(
                valuePlugin,
                dataProvider,
                new TimeseriesInterval(timestampFrom, timestampTo),
                TimeseriesPeriod.HOUR1,
                TimeseriesUnit.KILO_WATT
        );

        timeseries.load(new TimeseriesInterval(timestampFrom, timestampTo));
        doTest(interval, timeseries, 1d, 744, false);

        Long measureTimestamp = System.currentTimeMillis();
        timeseries = timeseries.toPeriod(TimeseriesPeriod.MIN15);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("SpreadOperationWithPowerUnit(Month): " + measureTimestamp.doubleValue() / 1000 + " s");

        doTest(interval, timeseries, 1d, 2976, false);

    }

    @Test
    public void testSpreadOperationWithPowerUnitYear() {

        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2017, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<ValueStatusPair<Double, byte[]>> timeseries;

        ValueStatusPairPlugin<Double, byte[]> valuePlugin;
        TimeseriesDataProvider<ValueStatusPair<Double, byte[]>> dataProvider;

        valuePlugin = new ValueStatusPairPlugin<>(
                new DoublePlugin(),
                new byte[8]
        );

        dataProvider = new TimeseriesDataProviderConstantValue<>(
                valuePlugin,
                new ValueStatusPair<>(1d, new byte[8]),
                TimeseriesPeriod.HOUR1
        );

        timeseries = new Timeseries<>(
                valuePlugin,
                dataProvider,
                new TimeseriesInterval(timestampFrom, timestampTo),
                TimeseriesPeriod.HOUR1,
                TimeseriesUnit.KILO_WATT
        );

        timeseries.load(new TimeseriesInterval(timestampFrom, timestampTo));
        doTest(interval, timeseries, 1d, 8784, false);

        Long measureTimestamp = System.currentTimeMillis();
        timeseries = timeseries.toPeriod(TimeseriesPeriod.MIN15);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("SpreadOperationWithPowerUnit(Year): " + measureTimestamp.doubleValue() / 1000 + " s");

        doTest(interval, timeseries, 1d, 35136, false);

    }

    @Test
    public void testCompactOperationWithEnergyUnitDay() {

        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<ValueStatusPair<Double, byte[]>> timeseries;

        ValueStatusPairPlugin<Double, byte[]> valuePlugin;
        TimeseriesDataProvider<ValueStatusPair<Double, byte[]>> dataProvider;

        valuePlugin = new ValueStatusPairPlugin<>(
                new DoublePlugin(),
                new byte[8]
        );

        dataProvider = new TimeseriesDataProviderConstantValue<>(
                valuePlugin,
                new ValueStatusPair<>(1d, new byte[8]),
                TimeseriesPeriod.MIN15
        );

        timeseries = new Timeseries<>(
                valuePlugin,
                dataProvider,
                new TimeseriesInterval(timestampFrom, timestampTo),
                TimeseriesPeriod.MIN15,
                TimeseriesUnit.KILO_WATT_HOUR
        );

        timeseries.load(new TimeseriesInterval(timestampFrom, timestampTo));
        doTest(interval, timeseries, 1d, 96, false);

        Long measureTimestamp = System.currentTimeMillis();
        timeseries = timeseries.toPeriod(TimeseriesPeriod.HOUR1);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("CompactOperationWithEnergyUnit(Day): " + measureTimestamp.doubleValue() / 1000 + " s");

        doTest(interval, timeseries, 4d, 24, true);

    }

    @Test
    public void testCompactOperationWithEnergyUnitMonth() {

        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<ValueStatusPair<Double, byte[]>> timeseries;

        ValueStatusPairPlugin<Double, byte[]> valuePlugin;
        TimeseriesDataProvider<ValueStatusPair<Double, byte[]>> dataProvider;

        valuePlugin = new ValueStatusPairPlugin<>(
                new DoublePlugin(),
                new byte[8]
        );

        dataProvider = new TimeseriesDataProviderConstantValue<>(
                valuePlugin,
                new ValueStatusPair<>(1d, new byte[8]),
                TimeseriesPeriod.MIN15
        );

        timeseries = new Timeseries<>(
                valuePlugin,
                dataProvider,
                new TimeseriesInterval(timestampFrom, timestampTo),
                TimeseriesPeriod.MIN15,
                TimeseriesUnit.KILO_WATT_HOUR
        );

        timeseries.load(new TimeseriesInterval(timestampFrom, timestampTo));
        doTest(interval, timeseries, 1d, 2976, false);

        Long measureTimestamp = System.currentTimeMillis();
        timeseries = timeseries.toPeriod(TimeseriesPeriod.HOUR1);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("CompactOperationWithEnergyUnit(Month): " + measureTimestamp.doubleValue() / 1000 + " s");

        doTest(interval, timeseries, 4d, 744, false);

    }

    @Test
    public void testCompactOperationWithEnergyUnitYear() {

        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2017, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<ValueStatusPair<Double, byte[]>> timeseries;

        ValueStatusPairPlugin<Double, byte[]> valuePlugin;
        TimeseriesDataProvider<ValueStatusPair<Double, byte[]>> dataProvider;

        valuePlugin = new ValueStatusPairPlugin<>(
                new DoublePlugin(),
                new byte[8]
        );

        dataProvider = new TimeseriesDataProviderConstantValue<>(
                valuePlugin,
                new ValueStatusPair<>(1d, new byte[8]),
                TimeseriesPeriod.MIN15
        );

        timeseries = new Timeseries<>(
                valuePlugin,
                dataProvider,
                new TimeseriesInterval(timestampFrom, timestampTo),
                TimeseriesPeriod.MIN15,
                TimeseriesUnit.KILO_WATT_HOUR
        );

        timeseries.load(new TimeseriesInterval(timestampFrom, timestampTo));
        doTest(interval, timeseries, 1d, 35136, false);

        Long measureTimestamp = System.currentTimeMillis();
        timeseries = timeseries.toPeriod(TimeseriesPeriod.HOUR1);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("CompactOperationWithEnergyUnit(Year): " + measureTimestamp.doubleValue() / 1000 + " s");

        doTest(interval, timeseries, 4d, 8784, false);

    }

    @Test
    public void testCompactOperationWithPowerUnitDay() {

        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<ValueStatusPair<Double, byte[]>> timeseries;

        ValueStatusPairPlugin<Double, byte[]> valuePlugin;
        TimeseriesDataProvider<ValueStatusPair<Double, byte[]>> dataProvider;

        valuePlugin = new ValueStatusPairPlugin<>(
                new DoublePlugin(),
                new byte[8]
        );

        dataProvider = new TimeseriesDataProviderConstantValue<>(
                valuePlugin,
                new ValueStatusPair<>(1d, new byte[8]),
                TimeseriesPeriod.MIN15
        );

        timeseries = new Timeseries<>(
                valuePlugin,
                dataProvider,
                new TimeseriesInterval(timestampFrom, timestampTo),
                TimeseriesPeriod.MIN15,
                TimeseriesUnit.KILO_WATT
        );

        timeseries.load(new TimeseriesInterval(timestampFrom, timestampTo));
        doTest(interval, timeseries, 1d, 96, false);

        Long measureTimestamp = System.currentTimeMillis();
        timeseries = timeseries.toPeriod(TimeseriesPeriod.HOUR1);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("CompactOperationWithPowerUnit(Day): " + measureTimestamp.doubleValue() / 1000 + " s");

        doTest(interval, timeseries, 1d, 24, true);

    }

    @Test
    public void testCompactOperationWithPowerUnitMonth() {

        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<ValueStatusPair<Double, byte[]>> timeseries;

        ValueStatusPairPlugin<Double, byte[]> valuePlugin;
        TimeseriesDataProvider<ValueStatusPair<Double, byte[]>> dataProvider;

        valuePlugin = new ValueStatusPairPlugin<>(
                new DoublePlugin(),
                new byte[8]
        );

        dataProvider = new TimeseriesDataProviderConstantValue<>(
                valuePlugin,
                new ValueStatusPair<>(1d, new byte[8]),
                TimeseriesPeriod.MIN15
        );

        timeseries = new Timeseries<>(
                valuePlugin,
                dataProvider,
                new TimeseriesInterval(timestampFrom, timestampTo),
                TimeseriesPeriod.MIN15,
                TimeseriesUnit.KILO_WATT
        );

        timeseries.load(new TimeseriesInterval(timestampFrom, timestampTo));
        doTest(interval, timeseries, 1d, 2976, false);

        Long measureTimestamp = System.currentTimeMillis();
        timeseries = timeseries.toPeriod(TimeseriesPeriod.HOUR1);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("CompactOperationWithPowerUnit(Month): " + measureTimestamp.doubleValue() / 1000 + " s");

        doTest(interval, timeseries, 1d, 744, false);

    }

    @Test
    public void testCompactOperationWithPowerUnitYear() {

        Instant timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2017, 1, 1, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<ValueStatusPair<Double, byte[]>> timeseries;

        ValueStatusPairPlugin<Double, byte[]> valuePlugin;
        TimeseriesDataProvider<ValueStatusPair<Double, byte[]>> dataProvider;

        valuePlugin = new ValueStatusPairPlugin<>(
                new DoublePlugin(),
                new byte[8]
        );

        dataProvider = new TimeseriesDataProviderConstantValue<>(
                valuePlugin,
                new ValueStatusPair<>(1d, new byte[8]),
                TimeseriesPeriod.MIN15
        );

        timeseries = new Timeseries<>(
                valuePlugin,
                dataProvider,
                new TimeseriesInterval(timestampFrom, timestampTo),
                TimeseriesPeriod.MIN15,
                TimeseriesUnit.KILO_WATT
        );

        timeseries.load(new TimeseriesInterval(timestampFrom, timestampTo));
        doTest(interval, timeseries, 1d, 35136, false);

        Long measureTimestamp = System.currentTimeMillis();
        timeseries = timeseries.toPeriod(TimeseriesPeriod.HOUR1);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("CompactOperationWithPowerUnit(Year): " + measureTimestamp.doubleValue() / 1000 + " s");

        doTest(interval, timeseries, 1d, 8784, false);

    }

    @Test
    public void testCompactOperationDaylightSavingMissingHour() {

        Instant timestampFrom = ZonedDateTime.of(2016, 3, 27, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 3, 28, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<ValueStatusPair<Double, byte[]>> timeseries;

        ValueStatusPairPlugin<Double, byte[]> valuePlugin;
        TimeseriesDataProvider<ValueStatusPair<Double, byte[]>> dataProvider;

        valuePlugin = new ValueStatusPairPlugin<>(
                new DoublePlugin(),
                new byte[8]
        );

        dataProvider = new TimeseriesDataProviderConstantValue<>(
                valuePlugin,
                new ValueStatusPair<>(1d, new byte[8]),
                TimeseriesPeriod.HOUR1
        );

        timeseries = new Timeseries<>(
                valuePlugin,
                dataProvider,
                interval,
                TimeseriesPeriod.HOUR1,
                TimeseriesUnit.KILO_WATT_HOUR
        );

        timeseries.load(interval);
        doTest(interval, timeseries, 1d, 23, false);

        Long measureTimestamp = System.currentTimeMillis();
        timeseries = timeseries.toPeriod(TimeseriesPeriod.DAY1);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("CompactOperationDaylightSavingMissingHour: " + measureTimestamp.doubleValue() / 1000 + " s");

        doTest(interval, timeseries, 23d, 1, false);

    }

    @Test
    public void testCompactOperationDaylightSavingAdditionalHour() {

        Instant timestampFrom = ZonedDateTime.of(2016, 10, 30, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 10, 31, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<ValueStatusPair<Double, byte[]>> timeseries;

        ValueStatusPairPlugin<Double, byte[]> valuePlugin;
        TimeseriesDataProvider<ValueStatusPair<Double, byte[]>> dataProvider;

        valuePlugin = new ValueStatusPairPlugin<>(
                new DoublePlugin(),
                new byte[8]
        );

        dataProvider = new TimeseriesDataProviderConstantValue<>(
                valuePlugin,
                new ValueStatusPair<>(1d, new byte[8]),
                TimeseriesPeriod.HOUR1
        );

        timeseries = new Timeseries<>(
                valuePlugin,
                dataProvider,
                interval,
                TimeseriesPeriod.HOUR1,
                TimeseriesUnit.KILO_WATT_HOUR
        );

        timeseries.load(interval);
        doTest(interval, timeseries, 1d, 25, false);

        Long measureTimestamp = System.currentTimeMillis();
        timeseries = timeseries.toPeriod(TimeseriesPeriod.DAY1);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("CompactOperationDaylightSavingAdditionalHour: " + measureTimestamp.doubleValue() / 1000 + " s");

        doTest(interval, timeseries, 25d, 1, false);

    }

    @Test
    public void testSpreadOperationDaylightSavingMissingHour() {

        Instant timestampFrom = ZonedDateTime.of(2016, 3, 27, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 3, 28, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<ValueStatusPair<Double, byte[]>> timeseries;

        ValueStatusPairPlugin<Double, byte[]> valuePlugin;
        TimeseriesDataProvider<ValueStatusPair<Double, byte[]>> dataProvider;

        valuePlugin = new ValueStatusPairPlugin<>(
                new DoublePlugin(),
                new byte[8]
        );

        dataProvider = new TimeseriesDataProviderConstantValue<>(
                valuePlugin,
                new ValueStatusPair<>(23d, new byte[8]),
                TimeseriesPeriod.DAY1
        );

        timeseries = new Timeseries<>(
                valuePlugin,
                dataProvider,
                interval,
                TimeseriesPeriod.DAY1,
                TimeseriesUnit.KILO_WATT_HOUR
        );

        timeseries.load(interval);
        doTest(interval, timeseries, 23d, 1, false);

        Long measureTimestamp = System.currentTimeMillis();
        timeseries = timeseries.toPeriod(TimeseriesPeriod.HOUR1);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("SpreadOperationDaylightSavingMissingHour: " + measureTimestamp.doubleValue() / 1000 + " s");

        doTest(interval, timeseries, 1d, 23, false);

    }

    @Test
    public void testSpreadOperationDaylightSavingAdditionalHour() {

        Instant timestampFrom = ZonedDateTime.of(2016, 10, 30, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        Instant timestampTo = ZonedDateTime.of(2016, 10, 31, 0, 0, 0, 0, ZoneId.of("CET")).toInstant();
        TimeseriesInterval interval = new TimeseriesInterval(timestampFrom, timestampTo, ZoneId.of("CET"));

        Timeseries<ValueStatusPair<Double, byte[]>> timeseries;

        ValueStatusPairPlugin<Double, byte[]> valuePlugin;
        TimeseriesDataProvider<ValueStatusPair<Double, byte[]>> dataProvider;

        valuePlugin = new ValueStatusPairPlugin<>(
                new DoublePlugin(),
                new byte[8]
        );

        dataProvider = new TimeseriesDataProviderConstantValue<>(
                valuePlugin,
                new ValueStatusPair<>(25d, new byte[8]),
                TimeseriesPeriod.DAY1
        );

        timeseries = new Timeseries<>(
                valuePlugin,
                dataProvider,
                interval,
                TimeseriesPeriod.DAY1,
                TimeseriesUnit.KILO_WATT_HOUR
        );

        timeseries.load(interval);
        doTest(interval, timeseries, 25d, 1, false);

        Long measureTimestamp = System.currentTimeMillis();
        timeseries = timeseries.toPeriod(TimeseriesPeriod.HOUR1);
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("SpreadOperationDaylightSavingAdditionalHour: " + measureTimestamp.doubleValue() / 1000 + " s");

        doTest(interval, timeseries, 1d, 25, false);

    }

    private class DTO {

        Timeseries<ValueStatusPair<Double, byte[]>> t1;
        Timeseries<ValueStatusPair<Double, byte[]>> t2;
    }

}
