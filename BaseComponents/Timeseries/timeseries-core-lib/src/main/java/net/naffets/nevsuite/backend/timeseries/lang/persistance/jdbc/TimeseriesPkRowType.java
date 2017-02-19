package net.naffets.nevsuite.backend.timeseries.lang.persistance.jdbc;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import oracle.jpub.runtime.MutableStruct;
import oracle.sql.*;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author br4sk4
 * created on 15.03.2016
 */
public class TimeseriesPkRowType extends TimeseriesManagementDBType implements ORAData, ORADataFactory {

    public static final String _SQL_NAME = "TSM_OWNER.TIMESERIES_PK_ROW";
    protected static final int[] _sqlType = {
            OracleTypes.VARCHAR,
            OracleTypes.TIMESTAMPTZ,
            OracleTypes.TIMESTAMPTZ,
    };
    protected static final ORADataFactory[] _factory = new ORADataFactory[_sqlType.length];
    protected static final TimeseriesPkRowType RasterblockPkRowTypeFactory = new TimeseriesPkRowType();
    protected MutableStruct _struct;

    /* ************************ */
    /* ORADataFactory interface */
    /* ************************ */

    private TimeseriesPkRowType() {
        _init_struct(true);
    }

    public TimeseriesPkRowType(Connection con) {
        _init_struct(true);
        connection = con;
    }

    public TimeseriesPkRowType(String timeseriesIdentifier,
                               TIMESTAMPTZ pitFrom,
                               TIMESTAMPTZ pitTo) throws SQLException {
        _init_struct(true);
        setTimeseriesIdentifier(timeseriesIdentifier);
        setPointInTimeFrom(pitFrom);
        setPointInTimeTo(pitTo);
    }

    public static ORADataFactory getORADataFactory() {
        return RasterblockPkRowTypeFactory;
    }

    protected void _init_struct(boolean init) {
        if (init)
            _struct = new MutableStruct(new Object[_sqlType.length], _sqlType, _factory);
    }

    /* ***************** */
    /* ORAData interface */
    /* ***************** */

    public Datum toDatum(Connection con) throws SQLException {
        if (null != con) {
            if (connection != con)
                release();

            connection = con;
        }
        return _struct.toDatum(connection, _SQL_NAME);
    }

    public ORAData create(Datum d, int sqlType) throws SQLException {
        return create(null, d, sqlType);
    }

    public void setFrom(TimeseriesPkRowType o) throws SQLException {
        setContextFrom(o);
        setValueFrom(o);
    }

    protected void setContextFrom(TimeseriesPkRowType o) throws SQLException {
        release();
        connection = o.connection;
    }

    protected void setValueFrom(TimeseriesPkRowType o) {
        _struct = o._struct;
    }

    protected ORAData create(TimeseriesPkRowType o, Datum d, int sqlType) throws SQLException {
        if (d == null) {
            if (o != null) {
                o.release();
            }

            return null;
        }

        if (o == null)
            o = new TimeseriesPkRowType();

        o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
        o.connection = ((STRUCT) d).getJavaSqlConnection();

        return o;
    }

    /* **************** */
    /* Accessor Methods */
    /* **************** */

    public String getTimeseriesIdentifier() throws SQLException {
        return (String) _struct.getAttribute(0);
    }

    public void setTimeseriesIdentifier(String timeseriesIdentifier) throws SQLException {
        _struct.setAttribute(0, timeseriesIdentifier);
    }

    public TIMESTAMPTZ getPointInTimeFrom() throws SQLException {
        return (TIMESTAMPTZ) _struct.getOracleAttribute(1);
    }

    public void setPointInTimeFrom(TIMESTAMPTZ pointInTime) throws SQLException {
        _struct.setOracleAttribute(1, pointInTime);
    }

    public TIMESTAMPTZ getPointInTimeTo() throws SQLException {
        return (TIMESTAMPTZ) _struct.getOracleAttribute(2);
    }

    public void setPointInTimeTo(TIMESTAMPTZ pointInTime) throws SQLException {
        _struct.setOracleAttribute(2, pointInTime);
    }

    public TimeseriesPkRowType init(
            String tsIdentifier,
            TIMESTAMPTZ pointInTimeFrom,
            TIMESTAMPTZ pointInTimeTo
    ) throws java.sql.SQLException {

        TimeseriesPkRowType __jPt_result = null;
        oracle.jdbc.OracleCallableStatement __sJT_st = null;

        try {
            String theSqlTS = "BEGIN :1 := TSM_OWNER.TIMESERIES_PK_ROW( :2, :3, :4 ) ; END;";

            __sJT_st = (OracleCallableStatement) getConnection().prepareCall(theSqlTS);
            __sJT_st.registerOutParameter(1, OracleTypes.STRUCT, "TSM_OWNER.TIMESERIES_PK_ROW");

            // set IN parameters
            __sJT_st.setString(2, tsIdentifier);
            __sJT_st.setTIMESTAMPTZ(3, pointInTimeFrom);
            __sJT_st.setTIMESTAMPTZ(4, pointInTimeTo);

            // execute statement
            __sJT_st.execute();

            // retrieve OUT parameters
            __jPt_result = (TimeseriesPkRowType) __sJT_st.getORAData(1, TimeseriesPkRowType.getORADataFactory());

            return __jPt_result;
        } finally {
            __sJT_st.close();
        }

    }
}
