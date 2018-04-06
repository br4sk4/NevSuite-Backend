package net.naffets.nevsuite.test.unit.timeseries.core.dataprovider;

import net.naffets.nevsuite.backend.timeseries.core.dataprovider.TimeseriesDataProvider;
import net.naffets.nevsuite.backend.timeseries.core.dataprovider.TimeseriesDataProviderRandomValue;
import net.naffets.nevsuite.backend.timeseries.core.datatype.ValueStatusPair;
import net.naffets.nevsuite.backend.timeseries.core.timeseries.TimeseriesInterval;
import net.naffets.nevsuite.backend.timeseries.core.timeseries.TimeseriesPeriod;
import net.naffets.nevsuite.backend.timeseries.core.valueplugin.LongPlugin;
import net.naffets.nevsuite.backend.timeseries.core.valueplugin.ValueStatusPairPlugin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author br4sk4
 * created on 21.03.2016
 */
public class TimeseriesDataProviderRandomValueTest {

    private final Logger logger = LogManager.getLogger(TimeseriesDataProviderRandomValue.class.getName());

    @Test
    public void testLoadValuesDay() {
        ZonedDateTime timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET"));
        ZonedDateTime timestampTo = ZonedDateTime.of(2016, 1, 2, 0, 0, 0, 0, ZoneId.of("CET"));

        TimeseriesDataProvider<ValueStatusPair<Long, byte[]>> dataProvider = new TimeseriesDataProviderRandomValue<>(
                new ValueStatusPairPlugin<>(new LongPlugin(), new byte[8]),
                TimeseriesPeriod.MIN15
        );

        Long measureTimestamp = System.currentTimeMillis();
        HashMap<Instant, ValueStatusPair<Long, byte[]>> intervalMap = dataProvider.load(new TimeseriesInterval(timestampFrom.toInstant(), timestampTo.toInstant()));
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;

        if (this.logger.isInfoEnabled())
            this.logger.info("ReadOperation(Day): " + measureTimestamp.doubleValue() / 1000 + " s");

        int loop = 0;
        SortedSet<Instant> keySet = new TreeSet<>(intervalMap.keySet());
        Instant checkTimestamp = Instant.from(timestampFrom).plus(Duration.ofMinutes(15));

        assertEquals(96, keySet.size());

        for (Instant timestamp : keySet) {
            ValueStatusPair<Long, byte[]> vp = intervalMap.get(timestamp);

            StringBuilder sb = new StringBuilder();
            StringBuilder statusHexString = new StringBuilder();
            for (Byte b : vp.getStatus()) {
                statusHexString.append(String.format("%02x", b));
            }

            sb.append(timestamp)
                    .append(":")
                    .append(vp.getValue())
                    .append(":")
                    .append(statusHexString.toString())
                    .append("; ");

            if (this.logger.isDebugEnabled()) this.logger.debug(sb.toString());

            assertEquals(checkTimestamp.plus(Duration.ofMinutes(15 * loop)).toString(), timestamp.toString());
            assertTrue((vp.getValue() >= 0 && vp.getValue() < 10));
            assertEquals("0000000000000000", statusHexString.toString());
            loop++;
        }
    }

    @Test
    public void testLoadValuesMonth() {
        ZonedDateTime timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET"));
        ZonedDateTime timestampTo = ZonedDateTime.of(2016, 2, 1, 0, 0, 0, 0, ZoneId.of("CET"));

        TimeseriesDataProvider<ValueStatusPair<Long, byte[]>> dataProvider = new TimeseriesDataProviderRandomValue<>(
                new ValueStatusPairPlugin<>(new LongPlugin(), new byte[8]),
                TimeseriesPeriod.MIN15
        );

        Long measureTimestamp = System.currentTimeMillis();
        HashMap<Instant, ValueStatusPair<Long, byte[]>> intervalMap = dataProvider.load(new TimeseriesInterval(timestampFrom.toInstant(), timestampTo.toInstant()));
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;

        if (this.logger.isInfoEnabled())
            this.logger.info("ReadOperation(Month): " + measureTimestamp.doubleValue() / 1000 + " s");

        int loop = 0;
        SortedSet<Instant> keySet = new TreeSet<>(intervalMap.keySet());
        Instant checkTimestamp = Instant.from(timestampFrom).plus(Duration.ofMinutes(15));

        assertEquals(2976, keySet.size());

        for (Instant timestamp : keySet) {
            ValueStatusPair<Long, byte[]> vp = intervalMap.get(timestamp);

            StringBuilder statusHexString = new StringBuilder();
            for (Byte b : vp.getStatus()) {
                statusHexString.append(String.format("%02x", b));
            }

            assertEquals(checkTimestamp.plus(Duration.ofMinutes(15 * loop)).toString(), timestamp.toString());
            assertTrue((vp.getValue() >= 0 && vp.getValue() < 10));
            assertEquals("0000000000000000", statusHexString.toString());
            loop++;
        }
    }

    @Test
    public void testLoadValuesYear() {
        ZonedDateTime timestampFrom = ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.of("CET"));
        ZonedDateTime timestampTo = ZonedDateTime.of(2017, 1, 1, 0, 0, 0, 0, ZoneId.of("CET"));

        TimeseriesDataProvider<ValueStatusPair<Long, byte[]>> dataProvider = new TimeseriesDataProviderRandomValue<>(
                new ValueStatusPairPlugin<>(new LongPlugin(), new byte[8]),
                TimeseriesPeriod.MIN15
        );

        Long measureTimestamp = System.currentTimeMillis();
        HashMap<Instant, ValueStatusPair<Long, byte[]>> intervalMap = dataProvider.load(new TimeseriesInterval(timestampFrom.toInstant(), timestampTo.toInstant()));
        measureTimestamp = System.currentTimeMillis() - measureTimestamp;

        if (this.logger.isInfoEnabled())
            this.logger.info("ReadOperation(Year): " + measureTimestamp.doubleValue() / 1000 + " s");

        int loop = 0;
        SortedSet<Instant> keySet = new TreeSet<>(intervalMap.keySet());
        Instant checkTimestamp = Instant.from(timestampFrom).plus(Duration.ofMinutes(15));

        assertEquals(35136, keySet.size());

        for (Instant timestamp : keySet) {
            ValueStatusPair<Long, byte[]> vp = intervalMap.get(timestamp);

            StringBuilder statusHexString = new StringBuilder();
            for (Byte b : vp.getStatus()) {
                statusHexString.append(String.format("%02x", b));
            }

            assertEquals(checkTimestamp.plus(Duration.ofMinutes(15 * loop)).toString(), timestamp.toString());
            assertTrue((vp.getValue() >= 0 && vp.getValue() < 10));
            assertEquals("0000000000000000", statusHexString.toString());
            loop++;
        }
    }

}
