package net.naffets.nevsuite.backend.timeseries.lang.persistance.jdbc;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import oracle.jpub.runtime.MutableStruct;
import oracle.sql.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author br4sk4
 * created on 12.03.2016
 */
public class ValueStatusPairRowType extends TimeseriesManagementDBType implements ORAData, ORADataFactory {

    public static final String _SQL_NAME = "TSM_OWNER.VALUE_STATUS_PAIR_ROW";
    protected static final int[] _sqlType = {
            OracleTypes.NUMBER,
            OracleTypes.NUMBER,
            OracleTypes.RAW
    };
    protected static final ORADataFactory[] _factory = new ORADataFactory[_sqlType.length];
    protected static final ValueStatusPairRowType ValueStatusPairRowTypeFactory = new ValueStatusPairRowType();
    protected MutableStruct _struct;

    /* ************************ */
    /* ORADataFactory interface */
    /* ************************ */

    private ValueStatusPairRowType() {
        _init_struct(true);
    }

    public ValueStatusPairRowType(Connection con) {
        _init_struct(true);
        connection = con;
    }

    public ValueStatusPairRowType(Long index,
                                  BigDecimal value,
                                  byte[] status) throws SQLException {
        _init_struct(true);
        setIndex(index);
        setValue(value);
        setStatus(status);
    }

    public static ORADataFactory getORADataFactory() {
        return ValueStatusPairRowTypeFactory;
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

    public void setFrom(ValueStatusPairRowType o) throws SQLException {
        setContextFrom(o);
        setValueFrom(o);
    }

    protected void setContextFrom(ValueStatusPairRowType o) throws SQLException {
        release();
        connection = o.connection;
    }

    protected void setValueFrom(ValueStatusPairRowType o) {
        _struct = o._struct;
    }

    protected ORAData create(ValueStatusPairRowType o, Datum d, int sqlType) throws SQLException {
        if (d == null) {
            if (o != null) {
                o.release();
            }

            return null;
        }

        if (o == null)
            o = new ValueStatusPairRowType();

        o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
        o.connection = ((STRUCT) d).getJavaSqlConnection();

        return o;
    }

    /* **************** */
    /* Accessor Methods */
    /* **************** */

    public Long getIndex() throws SQLException {
        java.math.BigDecimal index = (java.math.BigDecimal) _struct.getAttribute(0);
        return index == null ? null : index.longValue();
    }

    public void setIndex(Long index) throws SQLException {
        _struct.setAttribute(0, index);
    }

    public BigDecimal getValue() throws SQLException {
        java.math.BigDecimal value = (java.math.BigDecimal) _struct.getAttribute(1);
        return value == null ? null : value;
    }

    public void setValue(BigDecimal value) throws SQLException {
        _struct.setAttribute(1, value);
    }

    public byte[] getStatus() throws SQLException {
        return ((RAW) _struct.getOracleAttribute(2)).getBytes();
    }

    public void setStatus(byte[] status) throws SQLException {
        _struct.setOracleAttribute(2, new RAW(status));
    }

    public ValueStatusPairRowType init(
            Long index,
            Double value,
            RAW status
    ) throws java.sql.SQLException {

        ValueStatusPairRowType __jPt_result = null;
        oracle.jdbc.OracleCallableStatement __sJT_st = null;

        try {
            String theSqlTS = "BEGIN :1 := TSM_OWNER.VALUE_STATUS_PAIR_ROW.INIT( :2, :3, :4 ) ; END;";

            __sJT_st = (OracleCallableStatement) getConnection().prepareCall(theSqlTS);
            __sJT_st.registerOutParameter(1, OracleTypes.STRUCT, "TSM_OWNER.VALUE_STATUS_PAIR_ROW");

            // set IN parameters
            __sJT_st.setLong(2, index);
            __sJT_st.setDouble(3, value);
            __sJT_st.setRAW(4, status);

            // execute statement
            __sJT_st.execute();

            // retrieve OUT parameters
            __jPt_result = (ValueStatusPairRowType) __sJT_st.getORAData(1, ValueStatusPairRowType.getORADataFactory());

            return __jPt_result;
        } finally {
            __sJT_st.close();
        }

    }

}
