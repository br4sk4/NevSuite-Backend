package net.naffets.nevsuite.backend.timeseries.lang.util;

import java.util.Properties;

/**
 * @author br4sk4
 * created on 18.11.2016
 */
public class DatabaseHub {

    public final String basePath = "net.naffets.nevsuite.databasehub.";

    public final String connectorPath = "connector.";
    public final String hostPath = "host.";
    public final String portPath = "port.";
    public final String databasePath = "database.";
    public final String userPath = "user.";
    public final String passwordPath = "password.";

    private String identifier;
    private Properties dbHubProperties;

    public DatabaseHub(
            String identifier,
            Properties dbHubProperties
    ) {
        this.identifier = identifier;
        this.dbHubProperties = dbHubProperties;
    }

    public String getConnector() {
        return dbHubProperties.getProperty(basePath + connectorPath + identifier);
    }

    public String getHost() {
        return dbHubProperties.getProperty(basePath + hostPath + identifier);
    }

    public String getPort() {
        return dbHubProperties.getProperty(basePath + portPath + identifier);
    }

    public String getDatabase() {
        return dbHubProperties.getProperty(basePath + databasePath + identifier);
    }

    public String getUser() {
        return dbHubProperties.getProperty(basePath + userPath + identifier);
    }

    public String getPassword() {
        return dbHubProperties.getProperty(basePath + passwordPath + identifier);
    }

}
