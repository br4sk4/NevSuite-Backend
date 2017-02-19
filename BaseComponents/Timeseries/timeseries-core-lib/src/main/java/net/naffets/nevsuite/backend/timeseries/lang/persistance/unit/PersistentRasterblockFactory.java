package net.naffets.nevsuite.backend.timeseries.lang.persistance.unit;

import net.naffets.nevsuite.backend.timeseries.core.datatype.ValueStatusPair;
import net.naffets.nevsuite.backend.timeseries.lang.persistance.jdbc.RasterblockRowType;
import net.naffets.nevsuite.backend.timeseries.lang.persistance.jdbc.ValueStatusPairRowType;
import net.naffets.nevsuite.backend.timeseries.lang.persistance.jdbc.ValueStatusPairTableType;
import net.naffets.nevsuite.backend.timeseries.lang.util.TimestampHelper;
import oracle.jdbc.OracleTypes;
import oracle.sql.Datum;

import java.math.BigDecimal;
import java.sql.Connection;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
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

    public static PersistentRasterblock createPersistantRasterblock(RasterblockRowType rblType) {
        PersistentRasterblock rbl = null;

        try {
            rbl = new PersistentRasterblock(
                    rblType.getTimeseriesIdentifier(),
                    TimestampHelper.toInstant(rblType.getPointInTime())
            );

            for (Datum dat : rblType.getValueStatusPairs().getOracleArray()) {
                ValueStatusPairRowType vspRowTyp = (ValueStatusPairRowType) ValueStatusPairRowType.getORADataFactory().create(dat, OracleTypes.STRUCT);
                rbl.getValueMap().add(
                        new PersistentValueStatusPair(
                                vspRowTyp.getIndex(),
                                vspRowTyp.getValue(),
                                vspRowTyp.getStatus()
                        )
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rbl;

    }

    public static RasterblockRowType createRasterblockType(Connection connection, PersistentRasterblock rbl) {
        ArrayList<ValueStatusPairRowType> tsmVspList = new ArrayList<>();
        RasterblockRowType tsmRbl = null;

        try {
            for (PersistentValueStatusPair vsp : rbl.getValueMap()) {
                ValueStatusPairRowType tsmVsp = new ValueStatusPairRowType(vsp.getIndex(), vsp.getValue(), new byte[8]);
                tsmVspList.add(tsmVsp);
            }

            ValueStatusPairTableType tsmVspTable = new ValueStatusPairTableType((tsmVspList.toArray(new ValueStatusPairRowType[tsmVspList.size()])));
            String tsIdentifier = rbl.getTimeseriesIdentifier();

            tsmRbl = new RasterblockRowType(connection);
            tsmRbl = tsmRbl.init(
                    tsIdentifier,
                    TimestampHelper.toTimestampTZ(rbl.getPointInTime().atZone(ZoneId.of("CET"))),
                    tsmVspTable
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tsmRbl;
    }

}
