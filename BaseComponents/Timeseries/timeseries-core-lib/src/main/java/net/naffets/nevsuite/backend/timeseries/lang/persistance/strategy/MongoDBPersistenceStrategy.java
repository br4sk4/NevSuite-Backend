package net.naffets.nevsuite.backend.timeseries.lang.persistance.strategy;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import net.naffets.nevsuite.backend.timeseries.core.datatype.ValueStatusPair;
import net.naffets.nevsuite.backend.timeseries.lang.persistance.unit.PersistentRasterblock;
import net.naffets.nevsuite.backend.timeseries.lang.persistance.unit.PersistentRasterblockFactory;
import net.naffets.nevsuite.backend.timeseries.lang.util.DatabaseHub;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * @author br4sk4
 * created on 10.06.2015
 */
public class MongoDBPersistenceStrategy extends PersistenceStrategyBase {

    private final Logger logger = LogManager.getLogger(MongoDBPersistenceStrategy.class.getName());

    private MongoClient client = null;
    private MongoDatabase db = null;

    public MongoDBPersistenceStrategy(DatabaseHub dbHub) {
        super(dbHub);

        java.util.logging.Logger mongoLogger = java.util.logging.Logger.getLogger("org.mongodb");
        mongoLogger.setLevel(java.util.logging.Level.WARNING);
    }

    public void connect() {

        if (this.isConnected())
            return;

        try {
            this.client = new MongoClient(this.getHost(), Integer.parseInt(this.getPort()));
            this.db = this.client.getDatabase(this.getDatabase());

            if (this.logger.isInfoEnabled()) {
                this.logger.info("Conncected to "
                        + this.getHost() + ":"
                        + this.getPort() + ":"
                        + this.getDatabase()
                );
            }
        } catch (Exception e) {
            this.logger.catching(Level.FATAL, e);
        }

    }

    public void disconnect() {
        try {
            this.db = null;
            this.client.close();
            this.client = null;

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
        return client != null && this.db != null;
    }

    public HashMap<Instant, ValueStatusPair<BigDecimal, byte[]>> read(
            String timeseriesIdentifier,
            ZonedDateTime timestampFrom,
            ZonedDateTime timestampTo) {
        return this.read(
                timeseriesIdentifier,
                timestampFrom.toInstant(),
                timestampTo.toInstant()
        );
    }

    public HashMap<Instant, ValueStatusPair<BigDecimal, byte[]>> read(
            String timeseriesIdentifier,
            Instant timestampFrom,
            Instant timestampTo
    ) {
        Gson gson = new Gson();
        Document rblDoc = new Document();
        HashMap<Instant, ValueStatusPair<BigDecimal, byte[]>> valueMap = new HashMap<>();
        Long measureTimestamp = System.currentTimeMillis();

        rblDoc.append("timeseriesIdentifier", timeseriesIdentifier);
        rblDoc.append("pointInTime", new Document("$gt", Document.parse(gson.toJson(timestampFrom))));
        rblDoc.append("pointInTime", new Document("$lt", Document.parse(gson.toJson(timestampTo))));
        FindIterable<Document> iterable = db.getCollection("rasterblock").find(rblDoc);

        for (Document doc : iterable) {
            PersistentRasterblock rbl = gson.fromJson(doc.toJson(), PersistentRasterblock.class);
            Instant timestamp = rbl.getPointInTime();
            rbl.getValueMap().stream()
                    .forEach(vsp -> valueMap.put(timestamp.plus(Duration.ofMinutes(15 * vsp.getIndex())), new ValueStatusPair<>(vsp.getValue(), vsp.getStatus())));
        }

        measureTimestamp = System.currentTimeMillis() - measureTimestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("ReadOperation(DB): " + measureTimestamp.doubleValue() / 1000 + " s");

        return valueMap;
    }

    public void write(
            String timeseriesIdentifier,
            HashMap<Instant, ValueStatusPair<BigDecimal, byte[]>> valueMap
    ) {
        Gson gson = new Gson();
        Long timestamp = System.currentTimeMillis();

        this.db.getCollection("rasterblock").insertMany(PersistentRasterblockFactory.valueMapToPersistentRasterblockList(timeseriesIdentifier, valueMap).stream()
                .map(rbl -> Document.parse(gson.toJson(rbl)))
                .collect(Collectors.toList()));

        timestamp = System.currentTimeMillis() - timestamp;
        if (this.logger.isInfoEnabled())
            this.logger.info("WriteOperation(DB): " + timestamp.doubleValue() / 1000 + " s");
    }

    public void delete(
            String timeseriesIdentifier,
            ZonedDateTime timestampFrom,
            ZonedDateTime timestampTo) {
        delete(
                timeseriesIdentifier,
                timestampFrom.toInstant(),
                timestampTo.toInstant()
        );
    }

    public void delete(
            String timeseriesIdentifier,
            Instant timestampFrom,
            Instant timestampTo) {
        Long timestamp = System.currentTimeMillis();
        this.db.getCollection("rasterblock").drop();
        timestamp = System.currentTimeMillis() - timestamp;

        if (this.logger.isInfoEnabled())
            this.logger.info("DeleteOperation(DB): " + timestamp.doubleValue() / 1000 + " s");
    }

}