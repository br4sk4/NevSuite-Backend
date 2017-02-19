package net.naffets.nevsuite.backend.timeseries.lang.persistance.strategy;

import net.naffets.nevsuite.backend.timeseries.core.datatype.ValueStatusPair;
import net.naffets.nevsuite.backend.timeseries.lang.persistance.jdbc.RasterblockRowType;
import net.naffets.nevsuite.backend.timeseries.lang.persistance.jdbc.RasterblockTableType;
import net.naffets.nevsuite.backend.timeseries.lang.persistance.jdbc.TimeseriesManagementAPI;
import net.naffets.nevsuite.backend.timeseries.lang.persistance.jdbc.TimeseriesPkRowType;
import net.naffets.nevsuite.backend.timeseries.lang.persistance.unit.PersistentRasterblock;
import net.naffets.nevsuite.backend.timeseries.lang.persistance.unit.PersistentRasterblockFactory;
import net.naffets.nevsuite.backend.timeseries.lang.persistance.unit.PersistentValueStatusPair;
import net.naffets.nevsuite.backend.timeseries.lang.util.DatabaseHub;
import net.naffets.nevsuite.backend.timeseries.lang.util.TimestampHelper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

/**
 * @author br4sk4
 * created on 10.03.2016
 */
public class OracleTSMPersistenceStrategy extends PersistenceStrategyBase {

    private final Logger logger = LogManager.getLogger(OracleTSMPersistenceStrategy.class.getName());

    private Connection connection = null;
    private TimeseriesManagementAPI tsm_api = null;

    public OracleTSMPersistenceStrategy(DatabaseHub databaseHub) {
        super(databaseHub);
    }

    public void connect() {

        if (this.isConnected())
            return;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            this.connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@"
                            + this.getHost() + ":"
                            + this.getPort() + ":"
                            + this.getDatabase(),
                    this.getUser(),
                    this.getPassword()
            );

            tsm_api = new TimeseriesManagementAPI();
            tsm_api.setConnection(connection);
        } catch (SQLException e) {
            this.logger.catching(Level.FATAL, e);
        }

        if (this.connection != null && this.logger.isInfoEnabled()) {
            this.logger.info("Conncected to "
                    + this.getHost() + ":"
                    + this.getPort() + ":"
                    + this.getDatabase()
            );
        }
    }

    public void disconnect() {
        try {
            this.tsm_api = null;

            this.connection.close();
            this.connection = null;

            if (this.logger.isInfoEnabled()) {
                this.logger.info("Disconncected from "
                        + this.getHost() + ":"
                        + this.getPort() + ":"
                        + this.getDatabase()
                );
            }
        } catch (Exception e) {
            this.logger.catching(Level.FATAL, e);
        }
    }

    public boolean isConnected() {
        try {
            return this.connection != null && !this.connection.isClosed();
        } catch (SQLException e) {
            logger.catching(Level.FATAL, e);
            return false;
        }
    }

    public HashMap<Instant, ValueStatusPair<BigDecimal, byte[]>> read(
            String timeseriesIdentifier,
            Instant timestampFrom,
            Instant timestampTo) {
        return this.read(
                timeseriesIdentifier,
                ZonedDateTime.ofInstant(timestampFrom, ZoneId.of("CET")),
                ZonedDateTime.ofInstant(timestampTo, ZoneId.of("CET"))
        );
    }

    public HashMap<Instant, ValueStatusPair<BigDecimal, byte[]>> read(
            String timeseriesIdentifier,
            ZonedDateTime timestampFrom,
            ZonedDateTime timestampTo) {

        HashMap<Instant, ValueStatusPair<BigDecimal, byte[]>> tsmResult = new HashMap<>();

        try {
            TimeseriesPkRowType tsmPk = new TimeseriesPkRowType(connection);
            tsmPk =
                    tsmPk.init(
                            timeseriesIdentifier,
                            TimestampHelper.toTimestampTZ(timestampFrom),
                            TimestampHelper.toTimestampTZ(timestampTo)
                    );

            Long timestamp = System.currentTimeMillis();
            RasterblockTableType rblTable = this.tsm_api.readRasterblocks(tsmPk);
            timestamp = System.currentTimeMillis() - timestamp;

            if (this.logger.isInfoEnabled())
                this.logger.info("ReadOperation(DB): " + timestamp.doubleValue() / 1000 + " s");

            for (RasterblockRowType rblType : Arrays.asList(rblTable.getArray())) {
                PersistentRasterblock rbl = PersistentRasterblockFactory.createPersistantRasterblock(rblType);

                for (PersistentValueStatusPair pvsp : rbl.getValueMap()) {
                    Instant t = rbl.getPointInTime().plus(Duration.ofMinutes(15 * (pvsp.getIndex())));
                    tsmResult.put(t, new ValueStatusPair<>(pvsp.getValue(), pvsp.getStatus()));
                }
            }

            if (this.logger.isDebugEnabled()) {
                SortedSet<Instant> keySet = new TreeSet<>(tsmResult.keySet());

                for (Instant t : keySet) {
                    ValueStatusPair<BigDecimal, byte[]> vsp = tsmResult.get(t);

                    StringBuilder sb = new StringBuilder();
                    sb.append(timeseriesIdentifier)
                            .append("@")
                            .append(t.toString())
                            .append("; ");

                    StringBuilder statusHexString = new StringBuilder();
                    for (Byte b : vsp.getStatus()) {
                        statusHexString.append(String.format("%02x", b));
                    }

                    sb.append(vsp.getValue())
                            .append(":")
                            .append(statusHexString.toString())
                            .append("; ");

                    this.logger.debug(sb.toString());
                }
            }
        } catch (Exception e) {
            this.logger.catching(Level.FATAL, e);
        }

        return tsmResult;
    }

    public void write(
            String timeseriesIdentifier,
            HashMap<Instant, ValueStatusPair<BigDecimal, byte[]>> valueMap) {
        try {
            List<PersistentRasterblock> rblList = PersistentRasterblockFactory.valueMapToPersistentRasterblockList(timeseriesIdentifier, valueMap);
            LinkedList<RasterblockRowType> tsmRblList = new LinkedList<>();

            for (PersistentRasterblock rbl : rblList) {
                RasterblockRowType tsmRbl = PersistentRasterblockFactory.createRasterblockType(connection, rbl);
                tsmRblList.add(tsmRbl);
            }
            RasterblockTableType tsmRblTab = new RasterblockTableType(tsmRblList.toArray(new RasterblockRowType[rblList.size()]));

            Long measureTimestamp = System.currentTimeMillis();
            this.tsm_api.writeRasterblockTable(tsmRblTab);
            measureTimestamp = System.currentTimeMillis() - measureTimestamp;

            if (this.logger.isInfoEnabled())
                this.logger.info("WriteOperation(DB): " + measureTimestamp.doubleValue() / 1000 + " s");
        } catch (Exception e) {
            this.logger.catching(Level.FATAL, e);
        }
    }

    public void delete(
            String timeseriesIdentifier,
            Instant timestampFrom,
            Instant timestampTo) {
        this.delete(
                timeseriesIdentifier,
                ZonedDateTime.ofInstant(timestampFrom, ZoneId.of("CET")),
                ZonedDateTime.ofInstant(timestampTo, ZoneId.of("CET"))
        );
    }

    public void delete(
            String timeseriesIdentifier,
            ZonedDateTime timestampFrom,
            ZonedDateTime timestampTo) {
        try {
            TimeseriesPkRowType tsmPk = new TimeseriesPkRowType(connection);
            tsmPk =
                    tsmPk.init(
                            timeseriesIdentifier,
                            TimestampHelper.toTimestampTZ(timestampFrom),
                            TimestampHelper.toTimestampTZ(timestampTo)
                    );

            Long timestamp = System.currentTimeMillis();
            this.tsm_api.deleteRasterblocks(tsmPk);
            timestamp = System.currentTimeMillis() - timestamp;

            if (this.logger.isInfoEnabled())
                this.logger.info("DeleteOperation(DB): " + timestamp.doubleValue() / 1000 + " s");
        } catch (Exception e) {
            this.logger.catching(Level.FATAL, e);
        }
    }

}
