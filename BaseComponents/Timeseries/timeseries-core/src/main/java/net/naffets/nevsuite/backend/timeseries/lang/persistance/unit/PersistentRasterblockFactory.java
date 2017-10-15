package net.naffets.nevsuite.backend.timeseries.lang.persistance.unit;

import net.naffets.nevsuite.backend.timeseries.core.datatype.ValueStatusPair;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

/**
 * User:    Braska<br />
 * Date:    12.06.2015<br />
 * <br />
 * <br />
 */
public class PersistentRasterblockFactory {

    public static List<PersistentRasterblock> valueMapToPersistentRasterblockList(
            String timeseriesIdentifier,
            HashMap<Instant, ValueStatusPair<BigDecimal, byte[]>> valueMap) {
        Long index = 0L;

        SortedSet<Instant> keySet = new TreeSet<>(valueMap.keySet());
        Instant rblTimestamp = keySet.first();

        LinkedList<PersistentRasterblock> rblList = new LinkedList<>();
        PersistentRasterblock rbl = new PersistentRasterblock(timeseriesIdentifier, rblTimestamp);

        for (Instant timestamp : keySet) {
            if (timestamp.compareTo(rblTimestamp.plus(Duration.ofDays(1))) == 0) {
                index = 0L;
                rblList.add(rbl);
                rblTimestamp = rblTimestamp.plus(Duration.ofDays(1));
                rbl = new PersistentRasterblock(timeseriesIdentifier, rblTimestamp);
            } else if (timestamp.compareTo(keySet.last()) == 0) {
                rblList.add(rbl);
            }

            ValueStatusPair<BigDecimal, byte[]> vsp = valueMap.get(timestamp);
            rbl.getValueMap().add(
                    new PersistentValueStatusPair(
                            index,
                            vsp.getValue(),
                            vsp.getStatus()
                    )
            );
            index++;
        }

        return rblList;
    }

}
