package net.naffets.nevsuite.backend.timeseries.lang.persistance.jdbc;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import oracle.jpub.runtime.MutableStruct;
import oracle.sql.*;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author br4sk4
 * created on 12.03.2016
 */
public class RasterblockRowType extends TimeseriesManagementDBType implements ORAData, ORADataFactory {

    public static final String _SQL_NAME = "TSM_OWNER.RASTERBLOCK_ROW";
    protected static final int[] _sqlType = {
            OracleTypes.NUMBER,
            OracleTypes.VARCHAR,
            OracleTypes.TIMESTAMPTZ,
            OracleTypes.ARRAY
    };
    protected static final ORADataFactory[] _factory = new ORADataFactory[_sqlType.length];
    protected static final RasterblockRowType RasterblockRowTypeFactory = new RasterblockRowType();
    protected MutableStruct _struct;

    /* ************************ */
    /* ORADataFactory interface */
    /* ************************ */

    private RasterblockRowType() {
        _init_struct(true);
    }

    public RasterblockRowType(Connection con) {
        _init_struct(true);
        connection = con;
    }

    public RasterblockRowType(String timeseriesIdentifier,
                              TIMESTAMPTZ pointInTime,
                              ValueStatusPairTableType valueStatusPairs) throws SQLException {
        _init_struct(true);
        setTimeseriesIdentifier(timeseriesIdentifier);
        setPointInTime(pointInTime);
        setValueStatusPairs(valueStatusPairs);
    }

    public static ORADataFactory getORADataFactory() {
        return RasterblockRowTypeFactory;
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

    public void setFrom(RasterblockRowType o) throws SQLException {
        setContextFrom(o);
        setValueFrom(o);
    }

    protected void setContextFrom(RasterblockRowType o) throws SQLException {
        release();
        connection = o.connection;
    }

    protected void setValueFrom(RasterblockRowType o) {
        _struct = o._struct;
    }

    protected ORAData create(RasterblockRowType o, Datum d, int sqlType) throws SQLException {
        if (d == null) {
            if (o != null) {
                o.release();
            }

            return null;
        }

        if (o == null)
            o = new RasterblockRowType();

        o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
        o.connection = ((STRUCT) d).getJavaSqlConnection();

        return o;
    }

    /* **************** */
    /* Accessor Methods */
    /* **************** */

    public Long getRblId() throws SQLException {
        java.math.BigDecimal zrId = (java.math.BigDecimal) _struct.getAttribute(0);
        return zrId == null ? null : zrId.longValue();
    }

    public void setRblId(Long rblId) throws SQLException {
        _struct.setAttribute(0, rblId);
    }

    public String getTimeseriesIdentifier() throws SQLException {
        return (String) _struct.getAttribute(1);
    }

    public void setTimeseriesIdentifier(String timeseriesIdentifier) throws SQLException {
        _struct.setAttribute(1, timeseriesIdentifier);
    }

    public TIMESTAMPTZ getPointInTime() throws SQLException {
        return (TIMESTAMPTZ) _struct.getOracleAttribute(2);
    }

    public void setPointInTime(TIMESTAMPTZ pointInTime) throws SQLException {
        _struct.setOracleAttribute(2, pointInTime);
    }

    public ARRAY getValueStatusPairs() throws SQLException {
        return (ARRAY) _struct.getOracleAttribute(3);
    }

    public void setValueStatusPairs(ValueStatusPairTableType valueStatusPairs) throws SQLException {
        _struct.setOracleAttribute(3, valueStatusPairs.toDatum(connection));
    }

    public RasterblockRowType init(
            String tsIdentifier,
            TIMESTAMPTZ pointInTime,
            ValueStatusPairTableType valueStatusPairs
    ) throws java.sql.SQLException {

        RasterblockRowType __jPt_result = null;
        oracle.jdbc.OracleCallableStatement __sJT_st = null;

        try {
            String theSqlTS = "BEGIN :1 := TSM_OWNER.RASTERBLOCK_ROW( :2, :3, :4, :5 ) ; END;";

            __sJT_st = (OracleCallableStatement) getConnection().prepareCall(theSqlTS);
            __sJT_st.registerOutParameter(1, OracleTypes.STRUCT, "TSM_OWNER.RASTERBLOCK_ROW");

            // set IN parameters
            __sJT_st.setNull(2, OracleTypes.INTEGER);
            __sJT_st.setString(3, tsIdentifier);
            __sJT_st.setTIMESTAMPTZ(4, pointInTime);

            if (valueStatusPairs != null)
                __sJT_st.setORAData(5, valueStatusPairs);
            else
                __sJT_st.setNull(5, OracleTypes.ARRAY, "TSM_OWNER.VALUE_STATUS_PAIR_TABLE");

            // execute statement
            __sJT_st.execute();

            // retrieve OUT parameters
            __jPt_result = (RasterblockRowType) __sJT_st.getORAData(1, RasterblockRowType.getORADataFactory());

            return __jPt_result;
        } finally {
            __sJT_st.close();
        }

    }

}
