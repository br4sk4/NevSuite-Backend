package net.naffets.nevsuite.backend.timeseries.lang.persistance.jdbc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author br4sk4
 * created on 12.03.2016
 */
public class TimeseriesManagementDBType {

    protected Connection connection = null;
    protected DataSource datasource = null;

    public void setDataSource(DataSource dataSource) throws SQLException {
        release();
        datasource = dataSource;
    }

    public void setDataSourceLocation(String dataSourceLocation) throws SQLException {
        javax.sql.DataSource dataSource;
        try {
            Class<?> cls = Class.forName("javax.naming.InitialContext");
            Object ctx = cls.newInstance();
            java.lang.reflect.Method meth = cls.getMethod("lookup", new Class[]{String.class});
            dataSource = (javax.sql.DataSource) meth.invoke(ctx, new Object[]{"java:comp/env/" + dataSourceLocation});
            setDataSource(dataSource);
        } catch (Exception e) {
            throw new java.sql.SQLException("Error initializing DataSource at " + dataSourceLocation + ": " + e);
        }
    }

    public Connection getConnection() throws SQLException {
        if (connection != null)
            return connection;
        else if (datasource != null)
            connection = datasource.getConnection();

        return connection;
    }

    public void setConnection(Connection con) throws SQLException {
        this.connection = con;
    }

    public void release() throws SQLException {
        if (connection != null) {
            closeConnection();
            connection = null;
        }

        datasource = null;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (java.sql.SQLException e) {

            }
        }
    }

}
