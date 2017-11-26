package net.naffets.nevsuite.test.unit.timeseries.lang.util;

import net.naffets.nevsuite.backend.timeseries.domain.basictype.TimeseriesBlocksize;
import net.naffets.nevsuite.backend.timeseries.domain.basictype.TimeseriesRastertype;
import net.naffets.nevsuite.backend.timeseries.lang.util.TimeseriesDocumentSizeHelper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author br4sk4
 * created on 03L7.2015
 */
public class TimeseriesDocumentSizeHelperTest {

    @Test
    public void testMinuteSec1() {
        TimeseriesBlocksize blockSize = TimeseriesBlocksize.MINUTE;
        TimeseriesRastertype rasterType = TimeseriesRastertype.SEC1;

        Long valueCount = TimeseriesDocumentSizeHelper.getRasterblockungValueCount(
                rasterType,
                blockSize
        );

        assertEquals("Unerwartete Werte-Anzahl -> " + blockSize + ":" + rasterType, new Long(60), valueCount);
    }

    @Test
    public void testMinuteSec15() {
        TimeseriesBlocksize blockSize = TimeseriesBlocksize.MINUTE;
        TimeseriesRastertype rasterType = TimeseriesRastertype.SEC15;

        Long valueCount = TimeseriesDocumentSizeHelper.getRasterblockungValueCount(
                rasterType,
                blockSize
        );

        assertEquals("Unerwartete Werte-Anzahl -> " + blockSize + ":" + rasterType, new Long(4), valueCount);
    }

    @Test
    public void testMinuteSec30() {
        TimeseriesBlocksize blockSize = TimeseriesBlocksize.MINUTE;
        TimeseriesRastertype rasterType = TimeseriesRastertype.SEC30;

        Long valueCount = TimeseriesDocumentSizeHelper.getRasterblockungValueCount(
                rasterType,
                blockSize
        );

        assertEquals("Unerwartete Werte-Anzahl -> " + blockSize + ":" + rasterType, new Long(2), valueCount);
    }

    @Test
    public void testMinuteMin1() {
        TimeseriesBlocksize blockSize = TimeseriesBlocksize.MINUTE;
        TimeseriesRastertype rasterType = TimeseriesRastertype.MIN1;

        Long valueCount = TimeseriesDocumentSizeHelper.getRasterblockungValueCount(
                rasterType,
                blockSize
        );

        assertEquals("Unerwartete Werte-Anzahl -> " + blockSize + ":" + rasterType, new Long(1), valueCount);
    }

    @Test
    public void testMinuteMin15() {
        TimeseriesBlocksize blockSize = TimeseriesBlocksize.MINUTE;
        TimeseriesRastertype rasterType = TimeseriesRastertype.MIN15;

        Long valueCount = TimeseriesDocumentSizeHelper.getRasterblockungValueCount(
                rasterType,
                blockSize
        );

        assertTrue("Unerwartete Werte-Anzahl -> " + blockSize + ":" + rasterType, valueCount < 1.0);
    }

    @Test
    public void testMinuteMin30() {
        TimeseriesBlocksize blockSize = TimeseriesBlocksize.MINUTE;
        TimeseriesRastertype rasterType = TimeseriesRastertype.MIN30;

        Long valueCount = TimeseriesDocumentSizeHelper.getRasterblockungValueCount(
                rasterType,
                blockSize
        );

        assertTrue("Unerwartete Werte-Anzahl -> " + blockSize + ":" + rasterType, valueCount < 1.0);
    }

    @Test
    public void testMinuteHour1() {
        TimeseriesBlocksize blockSize = TimeseriesBlocksize.MINUTE;
        TimeseriesRastertype rasterType = TimeseriesRastertype.HOUR1;

        Long valueCount = TimeseriesDocumentSizeHelper.getRasterblockungValueCount(
                rasterType,
                blockSize
        );

        assertTrue("Unerwartete Werte-Anzahl -> " + blockSize + ":" + rasterType, valueCount < 1.0);
    }

    @Test
    public void testMinuteHour6() {
        TimeseriesBlocksize blockSize = TimeseriesBlocksize.MINUTE;
        TimeseriesRastertype rasterType = TimeseriesRastertype.HOUR6;

        Long valueCount = TimeseriesDocumentSizeHelper.getRasterblockungValueCount(
                rasterType,
                blockSize
        );
        assertTrue("Unerwartete Werte-Anzahl -> " + blockSize + ":" + rasterType, valueCount < 1.0);
    }

    @Test
    public void testMinuteHour12() {
        TimeseriesBlocksize blockSize = TimeseriesBlocksize.MINUTE;
        TimeseriesRastertype rasterType = TimeseriesRastertype.HOUR12;

        Long valueCount = TimeseriesDocumentSizeHelper.getRasterblockungValueCount(
                rasterType,
                blockSize
        );

        assertTrue("Unerwartete Werte-Anzahl -> " + blockSize + ":" + rasterType, valueCount < 1.0);
    }

    @Test
    public void testMinuteDay1() {
        TimeseriesBlocksize blockSize = TimeseriesBlocksize.MINUTE;
        TimeseriesRastertype rasterType = TimeseriesRastertype.DAY1;

        Long valueCount = TimeseriesDocumentSizeHelper.getRasterblockungValueCount(
                rasterType,
                blockSize
        );

        assertTrue("Unerwartete Werte-Anzahl -> " + blockSize + ":" + rasterType, valueCount < 1.0);
    }

    @Test
    public void testHourSec1() {
        TimeseriesBlocksize blockSize = TimeseriesBlocksize.HOUR;
        TimeseriesRastertype rasterType = TimeseriesRastertype.SEC1;

        Long valueCount = TimeseriesDocumentSizeHelper.getRasterblockungValueCount(
                rasterType,
                blockSize
        );

        assertEquals("Unerwartete Werte-Anzahl -> " + blockSize + ":" + rasterType, new Long(3600), valueCount);
    }

    @Test
    public void testHourSec15() {
        TimeseriesBlocksize blockSize = TimeseriesBlocksize.HOUR;
        TimeseriesRastertype rasterType = TimeseriesRastertype.SEC15;

        Long valueCount = TimeseriesDocumentSizeHelper.getRasterblockungValueCount(
                rasterType,
                blockSize
        );

        assertEquals("Unerwartete Werte-Anzahl -> " + blockSize + ":" + rasterType, new Long(240), valueCount);
    }

    @Test
    public void testHourSec30() {
        TimeseriesBlocksize blockSize = TimeseriesBlocksize.HOUR;
        TimeseriesRastertype rasterType = TimeseriesRastertype.SEC30;

        Long valueCount = TimeseriesDocumentSizeHelper.getRasterblockungValueCount(
                rasterType,
                blockSize
        );

        assertEquals("Unerwartete Werte-Anzahl -> " + blockSize + ":" + rasterType, new Long(120), valueCount);
    }

    @Test
    public void testHourMin1() {
        TimeseriesBlocksize blockSize = TimeseriesBlocksize.HOUR;
        TimeseriesRastertype rasterType = TimeseriesRastertype.MIN1;

        Long valueCount = TimeseriesDocumentSizeHelper.getRasterblockungValueCount(
                rasterType,
                blockSize
        );

        assertEquals("Unerwartete Werte-Anzahl -> " + blockSize + ":" + rasterType, new Long(60), valueCount);
    }

    @Test
    public void testHourMin15() {
        TimeseriesBlocksize blockSize = TimeseriesBlocksize.HOUR;
        TimeseriesRastertype rasterType = TimeseriesRastertype.MIN15;

        Long valueCount = TimeseriesDocumentSizeHelper.getRasterblockungValueCount(
                rasterType,
                blockSize
        );

        assertEquals("Unerwartete Werte-Anzahl -> " + blockSize + ":" + rasterType, new Long(4), valueCount);
    }

    @Test
    public void testHourMin30() {
        TimeseriesBlocksize blockSize = TimeseriesBlocksize.HOUR;
        TimeseriesRastertype rasterType = TimeseriesRastertype.MIN30;

        Long valueCount = TimeseriesDocumentSizeHelper.getRasterblockungValueCount(
                rasterType,
                blockSize
        );

        assertEquals("Unerwartete Werte-Anzahl -> " + blockSize + ":" + rasterType, new Long(2), valueCount);
    }

    @Test
    public void testHourHour1() {
        TimeseriesBlocksize blockSize = TimeseriesBlocksize.HOUR;
        TimeseriesRastertype rasterType = TimeseriesRastertype.HOUR1;

        Long valueCount = TimeseriesDocumentSizeHelper.getRasterblockungValueCount(
                rasterType,
                blockSize
        );

        assertEquals("Unerwartete Werte-Anzahl -> " + blockSize + ":" + rasterType, new Long(1), valueCount);
    }

    @Test
    public void testHourHour6() {
        TimeseriesBlocksize blockSize = TimeseriesBlocksize.HOUR;
        TimeseriesRastertype rasterType = TimeseriesRastertype.HOUR6;

        Long valueCount = TimeseriesDocumentSizeHelper.getRasterblockungValueCount(
                rasterType,
                blockSize
        );

        assertTrue("Unerwartete Werte-Anzahl -> " + blockSize + ":" + rasterType, valueCount < 1.0);
    }

    @Test
    public void testHourHour12() {
        TimeseriesBlocksize blockSize = TimeseriesBlocksize.HOUR;
        TimeseriesRastertype rasterType = TimeseriesRastertype.HOUR12;

        Long valueCount = TimeseriesDocumentSizeHelper.getRasterblockungValueCount(
                rasterType,
                blockSize
        );

        assertTrue("Unerwartete Werte-Anzahl -> " + blockSize + ":" + rasterType, valueCount < 1.0);
    }

    @Test
    public void testHourDay1() {
        TimeseriesBlocksize blockSize = TimeseriesBlocksize.HOUR;
        TimeseriesRastertype rasterType = TimeseriesRastertype.DAY1;

        Long valueCount = TimeseriesDocumentSizeHelper.getRasterblockungValueCount(
                rasterType,
                blockSize
        );

        assertTrue("Unerwartete Werte-Anzahl -> " + blockSize + ":" + rasterType, valueCount < 1.0);
    }

    @Test
    public void testDaySec1() {
        TimeseriesBlocksize blockSize = TimeseriesBlocksize.DAY;
        TimeseriesRastertype rasterType = TimeseriesRastertype.SEC1;

        Long valueCount = TimeseriesDocumentSizeHelper.getRasterblockungValueCount(
                rasterType,
                blockSize
        );

        assertEquals("Unerwartete Werte-Anzahl -> " + blockSize + ":" + rasterType, new Long(86400), valueCount);
    }

    @Test
    public void testDaySec15() {
        TimeseriesBlocksize blockSize = TimeseriesBlocksize.DAY;
        TimeseriesRastertype rasterType = TimeseriesRastertype.SEC15;

        Long valueCount = TimeseriesDocumentSizeHelper.getRasterblockungValueCount(
                rasterType,
                blockSize
        );

        assertEquals("Unerwartete Werte-Anzahl -> " + blockSize + ":" + rasterType, new Long(5760), valueCount);
    }

    @Test
    public void testDaySec30() {
        TimeseriesBlocksize blockSize = TimeseriesBlocksize.DAY;
        TimeseriesRastertype rasterType = TimeseriesRastertype.SEC30;

        Long valueCount = TimeseriesDocumentSizeHelper.getRasterblockungValueCount(
                rasterType,
                blockSize
        );

        assertEquals("Unerwartete Werte-Anzahl -> " + blockSize + ":" + rasterType, new Long(2880), valueCount);
    }

    @Test
    public void testDayMin1() {
        TimeseriesBlocksize blockSize = TimeseriesBlocksize.DAY;
        TimeseriesRastertype rasterType = TimeseriesRastertype.MIN1;

        Long valueCount = TimeseriesDocumentSizeHelper.getRasterblockungValueCount(
                rasterType,
                blockSize
        );

        assertEquals("Unerwartete Werte-Anzahl -> " + blockSize + ":" + rasterType, new Long(1440), valueCount);
    }

    @Test
    public void testDayMin15() {
        TimeseriesBlocksize blockSize = TimeseriesBlocksize.DAY;
        TimeseriesRastertype rasterType = TimeseriesRastertype.MIN15;

        Long valueCount = TimeseriesDocumentSizeHelper.getRasterblockungValueCount(
                rasterType,
                blockSize
        );
        assertEquals("Unerwartete Werte-Anzahl -> " + blockSize + ":" + rasterType, new Long(96), valueCount);
    }

    @Test
    public void testDayMin30() {
        TimeseriesBlocksize blockSize = TimeseriesBlocksize.DAY;
        TimeseriesRastertype rasterType = TimeseriesRastertype.MIN30;

        Long valueCount = TimeseriesDocumentSizeHelper.getRasterblockungValueCount(
                rasterType,
                blockSize
        );

        assertEquals("Unerwartete Werte-Anzahl -> " + blockSize + ":" + rasterType, new Long(48), valueCount);
    }

    @Test
    public void testDayHour1() {
        TimeseriesBlocksize blockSize = TimeseriesBlocksize.DAY;
        TimeseriesRastertype rasterType = TimeseriesRastertype.HOUR1;

        Long valueCount = TimeseriesDocumentSizeHelper.getRasterblockungValueCount(
                rasterType,
                blockSize
        );

        assertEquals("Unerwartete Werte-Anzahl -> " + blockSize + ":" + rasterType, new Long(24), valueCount);
    }

    @Test
    public void testDayHour6() {
        TimeseriesBlocksize blockSize = TimeseriesBlocksize.DAY;
        TimeseriesRastertype rasterType = TimeseriesRastertype.HOUR6;

        Long valueCount = TimeseriesDocumentSizeHelper.getRasterblockungValueCount(
                rasterType,
                blockSize
        );
        assertEquals("Unerwartete Werte-Anzahl -> " + blockSize + ":" + rasterType, new Long(4), valueCount);
    }

    @Test
    public void testDayHour12() {
        TimeseriesBlocksize blockSize = TimeseriesBlocksize.DAY;
        TimeseriesRastertype rasterType = TimeseriesRastertype.HOUR12;

        Long valueCount = TimeseriesDocumentSizeHelper.getRasterblockungValueCount(
                rasterType,
                blockSize
        );

        assertEquals("Unerwartete Werte-Anzahl -> " + blockSize + ":" + rasterType, new Long(2), valueCount);
    }

    @Test
    public void testDayDay1() {
        TimeseriesBlocksize blockSize = TimeseriesBlocksize.DAY;
        TimeseriesRastertype rasterType = TimeseriesRastertype.DAY1;

        Long valueCount = TimeseriesDocumentSizeHelper.getRasterblockungValueCount(
                rasterType,
                blockSize
        );

        assertEquals("Unerwartete Werte-Anzahl -> " + blockSize + ":" + rasterType, new Long(1), valueCount);
    }

    @Test
    public void testMonthSec1() {
        TimeseriesBlocksize blockSize = TimeseriesBlocksize.MONTH;
        TimeseriesRastertype rasterType = TimeseriesRastertype.SEC1;

        Long valueCount = TimeseriesDocumentSizeHelper.getRasterblockungValueCount(
                rasterType,
                blockSize
        );
        assertEquals("Unerwartete Werte-Anzahl -> " + blockSize + ":" + rasterType, new Long(2678400), valueCount);
    }

    @Test
    public void testMonthSec15() {
        TimeseriesBlocksize blockSize = TimeseriesBlocksize.MONTH;
        TimeseriesRastertype rasterType = TimeseriesRastertype.SEC15;

        Long valueCount = TimeseriesDocumentSizeHelper.getRasterblockungValueCount(
                rasterType,
                blockSize
        );

        assertEquals("Unerwartete Werte-Anzahl -> " + blockSize + ":" + rasterType, new Long(178560), valueCount);
    }

    @Test
    public void testMonthSec30() {
        TimeseriesBlocksize blockSize = TimeseriesBlocksize.MONTH;
        TimeseriesRastertype rasterType = TimeseriesRastertype.SEC30;

        Long valueCount = TimeseriesDocumentSizeHelper.getRasterblockungValueCount(
                rasterType,
                blockSize
        );

        assertEquals("Unerwartete Werte-Anzahl -> " + blockSize + ":" + rasterType, new Long(89280), valueCount);
    }

    @Test
    public void testMonthMin1() {
        TimeseriesBlocksize blockSize = TimeseriesBlocksize.MONTH;
        TimeseriesRastertype rasterType = TimeseriesRastertype.MIN1;

        Long valueCount = TimeseriesDocumentSizeHelper.getRasterblockungValueCount(
                rasterType,
                blockSize
        );

        assertEquals("Unerwartete Werte-Anzahl -> " + blockSize + ":" + rasterType, new Long(44640), valueCount);
    }

    @Test
    public void testMonthMin15() {
        TimeseriesBlocksize blockSize = TimeseriesBlocksize.MONTH;
        TimeseriesRastertype rasterType = TimeseriesRastertype.MIN15;

        Long valueCount = TimeseriesDocumentSizeHelper.getRasterblockungValueCount(
                rasterType,
                blockSize
        );

        assertEquals("Unerwartete Werte-Anzahl -> " + blockSize + ":" + rasterType, new Long(2976), valueCount);
    }

    @Test
    public void testMonthMin30() {
        TimeseriesBlocksize blockSize = TimeseriesBlocksize.MONTH;
        TimeseriesRastertype rasterType = TimeseriesRastertype.MIN30;

        Long valueCount = TimeseriesDocumentSizeHelper.getRasterblockungValueCount(
                rasterType,
                blockSize
        );

        assertEquals("Unerwartete Werte-Anzahl -> " + blockSize + ":" + rasterType, new Long(1488), valueCount);
    }

    @Test
    public void testMonthHour1() {
        TimeseriesBlocksize blockSize = TimeseriesBlocksize.MONTH;
        TimeseriesRastertype rasterType = TimeseriesRastertype.HOUR1;

        Long valueCount = TimeseriesDocumentSizeHelper.getRasterblockungValueCount(
                rasterType,
                blockSize
        );

        assertEquals("Unerwartete Werte-Anzahl -> " + blockSize + ":" + rasterType, new Long(744), valueCount);
    }

    @Test
    public void testMonthHour6() {
        TimeseriesBlocksize blockSize = TimeseriesBlocksize.MONTH;
        TimeseriesRastertype rasterType = TimeseriesRastertype.HOUR6;

        Long valueCount = TimeseriesDocumentSizeHelper.getRasterblockungValueCount(
                rasterType,
                blockSize
        );

        assertEquals("Unerwartete Werte-Anzahl -> " + blockSize + ":" + rasterType, new Long(124), valueCount);
    }

    @Test
    public void testMonthHour12() {
        TimeseriesBlocksize blockSize = TimeseriesBlocksize.MONTH;
        TimeseriesRastertype rasterType = TimeseriesRastertype.HOUR12;

        Long valueCount = TimeseriesDocumentSizeHelper.getRasterblockungValueCount(
                rasterType,
                blockSize
        );

        assertEquals("Unerwartete Werte-Anzahl -> " + blockSize + ":" + rasterType, new Long(62), valueCount);
    }

    @Test
    public void testMonthDay1() {
        TimeseriesBlocksize blockSize = TimeseriesBlocksize.MONTH;
        TimeseriesRastertype rasterType = TimeseriesRastertype.DAY1;

        Long valueCount = TimeseriesDocumentSizeHelper.getRasterblockungValueCount(
                rasterType,
                blockSize
        );

        assertEquals("Unerwartete Werte-Anzahl -> " + blockSize + ":" + rasterType, new Long(31), valueCount);
    }

    @Test
    public void testYearSec1() {
        TimeseriesBlocksize blockSize = TimeseriesBlocksize.YEAR;
        TimeseriesRastertype rasterType = TimeseriesRastertype.SEC1;

        Long valueCount = TimeseriesDocumentSizeHelper.getRasterblockungValueCount(
                rasterType,
                blockSize
        );

        assertEquals("Unerwartete Werte-Anzahl -> " + blockSize + ":" + rasterType, new Long(31622400), valueCount);
    }

    @Test
    public void testYearSec15() {
        TimeseriesBlocksize blockSize = TimeseriesBlocksize.YEAR;
        TimeseriesRastertype rasterType = TimeseriesRastertype.SEC15;

        Long valueCount = TimeseriesDocumentSizeHelper.getRasterblockungValueCount(
                rasterType,
                blockSize
        );

        assertEquals("Unerwartete Werte-Anzahl -> " + blockSize + ":" + rasterType, new Long(2108160), valueCount);
    }

    @Test
    public void testYearSec30() {
        TimeseriesBlocksize blockSize = TimeseriesBlocksize.YEAR;
        TimeseriesRastertype rasterType = TimeseriesRastertype.SEC30;

        Long valueCount = TimeseriesDocumentSizeHelper.getRasterblockungValueCount(
                rasterType,
                blockSize
        );

        assertEquals("Unerwartete Werte-Anzahl -> " + blockSize + ":" + rasterType, new Long(1054080), valueCount);
    }

    @Test
    public void testYearMin1() {
        TimeseriesBlocksize blockSize = TimeseriesBlocksize.YEAR;
        TimeseriesRastertype rasterType = TimeseriesRastertype.MIN1;

        Long valueCount = TimeseriesDocumentSizeHelper.getRasterblockungValueCount(
                rasterType,
                blockSize
        );

        assertEquals("Unerwartete Werte-Anzahl -> " + blockSize + ":" + rasterType, new Long(527040), valueCount);
    }

    @Test
    public void testYearMin15() {
        TimeseriesBlocksize blockSize = TimeseriesBlocksize.YEAR;
        TimeseriesRastertype rasterType = TimeseriesRastertype.MIN15;

        Long valueCount = TimeseriesDocumentSizeHelper.getRasterblockungValueCount(
                rasterType,
                blockSize
        );

        assertEquals("Unerwartete Werte-Anzahl -> " + blockSize + ":" + rasterType, new Long(35136), valueCount);
    }

    @Test
    public void testYearMin30() {
        TimeseriesBlocksize blockSize = TimeseriesBlocksize.YEAR;
        TimeseriesRastertype rasterType = TimeseriesRastertype.MIN30;

        Long valueCount = TimeseriesDocumentSizeHelper.getRasterblockungValueCount(
                rasterType,
                blockSize
        );

        assertEquals("Unerwartete Werte-Anzahl -> " + blockSize + ":" + rasterType, new Long(17568), valueCount);
    }

    @Test
    public void testYearHour1() {
        TimeseriesBlocksize blockSize = TimeseriesBlocksize.YEAR;
        TimeseriesRastertype rasterType = TimeseriesRastertype.HOUR1;

        Long valueCount = TimeseriesDocumentSizeHelper.getRasterblockungValueCount(
                rasterType,
                blockSize
        );

        assertEquals("Unerwartete Werte-Anzahl -> " + blockSize + ":" + rasterType, new Long(8784), valueCount);
    }

    @Test
    public void testYearHour6() {
        TimeseriesBlocksize blockSize = TimeseriesBlocksize.YEAR;
        TimeseriesRastertype rasterType = TimeseriesRastertype.HOUR6;

        Long valueCount = TimeseriesDocumentSizeHelper.getRasterblockungValueCount(
                rasterType,
                blockSize
        );

        assertEquals("Unerwartete Werte-Anzahl -> " + blockSize + ":" + rasterType, new Long(1464), valueCount);
    }

    @Test
    public void testYearHour12() {
        TimeseriesBlocksize blockSize = TimeseriesBlocksize.YEAR;
        TimeseriesRastertype rasterType = TimeseriesRastertype.HOUR12;

        Long valueCount = TimeseriesDocumentSizeHelper.getRasterblockungValueCount(
                rasterType,
                blockSize
        );

        assertEquals("Unerwartete Werte-Anzahl -> " + blockSize + ":" + rasterType, new Long(732), valueCount);
    }

    @Test
    public void testYearDay1() {
        TimeseriesBlocksize blockSize = TimeseriesBlocksize.YEAR;
        TimeseriesRastertype rasterType = TimeseriesRastertype.DAY1;

        Long valueCount = TimeseriesDocumentSizeHelper.getRasterblockungValueCount(
                rasterType,
                blockSize
        );

        assertEquals("Unerwartete Werte-Anzahl -> " + blockSize + ":" + rasterType, new Long(366), valueCount);
    }

}
