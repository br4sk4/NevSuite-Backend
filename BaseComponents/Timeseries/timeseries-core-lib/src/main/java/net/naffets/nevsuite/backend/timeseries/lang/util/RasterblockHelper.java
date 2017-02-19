package net.naffets.nevsuite.backend.timeseries.lang.util;

import net.naffets.nevsuite.backend.timeseries.core.timeseries.TimeseriesPeriod;
import net.naffets.nevsuite.backend.timeseries.lang.persistance.unit.PersistenceBlocksize;

/**
 * @author br4sk4
 * created on 25.03.2016
 */
public class RasterblockHelper {

    private static Long getMaximumSecondCountForBlock(PersistenceBlocksize blockSize) {
        switch (blockSize) {
            case MINUTE:
                return 60L;
            case HOUR:
                return 3600L;
            case DAY:
                return 86400L;
            case MONTH:
                return 2678400L;
            case YEAR:
                return 31622400L;
            default:
                return 0L;
        }
    }

    public static Long getRasterblockValueCount(TimeseriesPeriod period,
                                                PersistenceBlocksize blockSize) {
        long maxSecondsForBlock = getMaximumSecondCountForBlock(blockSize);
        switch (period) {
            case SEC1:
                return maxSecondsForBlock;
            case SEC15:
                return maxSecondsForBlock / 15;
            case SEC30:
                return maxSecondsForBlock / 30;
            case MIN1:
                return maxSecondsForBlock / 60;
            case MIN15:
                return maxSecondsForBlock / 900;
            case MIN30:
                return maxSecondsForBlock / 1800;
            case HOUR1:
                return maxSecondsForBlock / 3600;
            case HOUR6:
                return maxSecondsForBlock / 21600;
            case HOUR12:
                return maxSecondsForBlock / 43200;
            case DAY1:
                return maxSecondsForBlock / 86400;

            default:
                return 0L;
        }
    }

}
