package net.naffets.nevsuite.backend.timeseries.lang.persistance.jdbc;

import oracle.jdbc.OracleTypes;
import oracle.jpub.runtime.MutableArray;
import oracle.sql.*;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author br4sk4
 * created on 15.03.2016
 */
public class TimeseriesPkTableType extends TimeseriesManagementDBType implements ORAData, ORADataFactory {

    public static final String _SQL_NAME = "TSM_OWNER.TIMESERIES_PK_TABLE";
    public static final int _SQL_TYPECODE = OracleTypes.ARRAY;
    private static final TimeseriesPkTableType _RasterblockPkTableTypeFactory = new TimeseriesPkTableType();
    MutableArray _array;

    public TimeseriesPkTableType() {
        this((TimeseriesPkRowType[]) null);
    }

    public TimeseriesPkTableType(TimeseriesPkRowType[] a) {
        _array = new MutableArray(OracleTypes.STRUCT, a, TimeseriesPkRowType.getORADataFactory());
    }

    public static ORADataFactory getORADataFactory() {
        return _RasterblockPkTableTypeFactory;
    }

    /* ************************ */
    /* ORADataFactory interface */
    /* ************************ */

    public ORAData create(Datum d, int sqlType) throws SQLException {
        if (d == null)
            return null;

        TimeseriesPkTableType a = new TimeseriesPkTableType();
        a._array = new MutableArray(OracleTypes.STRUCT, (ARRAY) d, TimeseriesPkRowType.getORADataFactory());

        return a;
    }

    public int length() throws SQLException {
        return _array.length();
    }

    public int getBaseType() throws SQLException {
        return _array.getBaseType();
    }

    public String getBaseTypeName() throws SQLException {
        return _array.getBaseTypeName();
    }

    public ArrayDescriptor getDescriptor() throws SQLException {
        return _array.getDescriptor();
    }

    /* ***************** */
    /* ORAData interface */
    /* ***************** */

    public Datum toDatum(Connection con) throws SQLException {
        return _array.toDatum(con, _SQL_NAME);
    }

    /* **************** */
    /* Accessor Methods */
    /* **************** */

    public TimeseriesPkRowType[] getArray() throws SQLException {
        return (TimeseriesPkRowType[]) _array.getObjectArray(new TimeseriesPkRowType[_array.length()]);
    }

    public void setArray(TimeseriesPkRowType[] a) throws SQLException {
        _array.setObjectArray(a);
    }

    public TimeseriesPkRowType[] getArray(long index, int count) throws SQLException {
        return (TimeseriesPkRowType[]) _array.getObjectArray(index, new TimeseriesPkRowType[_array.sliceLength(index, count)]);
    }

    public void setArray(TimeseriesPkRowType[] a, long index) throws SQLException {
        _array.setObjectArray(a, index);
    }

    public TimeseriesPkRowType getElement(long index) throws SQLException {
        return (TimeseriesPkRowType) _array.getObjectElement(index);
    }

    public void setElement(TimeseriesPkRowType a, long index) throws SQLException {
        _array.setObjectElement(a, index);
    }
}
