package net.naffets.nevsuite.backend.timeseries.lang.persistance.strategy;

import net.naffets.nevsuite.backend.timeseries.lang.util.DatabaseHub;

import java.util.Properties;

/**
 * @author br4sk4
 * created on 10.03.2016
 */
public abstract class PersistenceStrategyFactory {

    public static PersistenceStrategy getInstance(String databaseIdentifier, Properties databaseHubProperties) {
        DatabaseHub databaseHub = new DatabaseHub(databaseIdentifier, databaseHubProperties);
        if (MongoDBPersistenceStrategy.class.getSimpleName().equals(
                databaseHubProperties.getProperty(databaseHub.basePath
                        + databaseHub.connectorPath
                        + databaseIdentifier))) {
            return new MongoDBPersistenceStrategy(databaseHub);
        } else if (OracleTSMPersistenceStrategy.class.getSimpleName().equals(
                databaseHubProperties.getProperty(databaseHub.basePath
                        + databaseHub.connectorPath
                        + databaseIdentifier))) {
            return new OracleTSMPersistenceStrategy(databaseHub);
        } else {
            return null;
        }
    }

}
