package net.naffets.nevsuite.backend.timeseries.lang.persistance.strategy;

import net.naffets.nevsuite.backend.timeseries.lang.util.DatabaseHub;

/**
 * @author br4sk4
 * created on 20.03.2016
 */
public abstract class PersistenceStrategyBase implements PersistenceStrategy {

    private DatabaseHub dbHub;

    PersistenceStrategyBase(DatabaseHub dbHub) {
        this.dbHub = dbHub;
    }

    protected String getConnector() {
        return dbHub.getConnector();
    }

    protected String getUser() {
        return dbHub.getUser();
    }

    protected String getHost() {
        return dbHub.getHost();
    }

    protected String getPort() {
        return dbHub.getPort();
    }

    protected String getPassword() {
        return dbHub.getPassword();
    }

    protected String getDatabase() {
        return dbHub.getDatabase();
    }

    public abstract void connect();

    public abstract void disconnect();

    public abstract boolean isConnected();

}
