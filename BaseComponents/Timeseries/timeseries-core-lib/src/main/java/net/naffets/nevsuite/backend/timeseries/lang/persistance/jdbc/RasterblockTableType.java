package net.naffets.nevsuite.backend.timeseries.lang.persistance.jdbc;

import oracle.jdbc.OracleTypes;
import oracle.jpub.runtime.MutableArray;
import oracle.sql.*;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author br4sk4
 * created on 12.03.2016
 */
public class RasterblockTableType extends TimeseriesManagementDBType implements ORAData, ORADataFactory {

    public static final String _SQL_NAME = "TSM_OWNER.RASTERBLOCK_TABLE";
    public static final int _SQL_TYPECODE = OracleTypes.ARRAY;
    private static final RasterblockTableType _RasterblockTableTypeFactory = new RasterblockTableType();
    MutableArray _array;

    public RasterblockTableType() {
        this((RasterblockRowType[]) null);
    }

    public RasterblockTableType(RasterblockRowType[] a) {
        _array = new MutableArray(OracleTypes.STRUCT, a, RasterblockRowType.getORADataFactory());
    }

    public static ORADataFactory getORADataFactory() {
        return _RasterblockTableTypeFactory;
    }

    /* ************************ */
    /* ORADataFactory interface */
    /* ************************ */

    public ORAData create(Datum d, int sqlType) throws SQLException {
        if (d == null)
            return null;

        RasterblockTableType a = new RasterblockTableType();
        a._array = new MutableArray(OracleTypes.STRUCT, (ARRAY) d, RasterblockRowType.getORADataFactory());

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

    public RasterblockRowType[] getArray() throws SQLException {
        return (RasterblockRowType[]) _array.getObjectArray(new RasterblockRowType[_array.length()]);
    }

    public void setArray(RasterblockRowType[] a) throws SQLException {
        _array.setObjectArray(a);
    }

    public RasterblockRowType[] getArray(long index, int count) throws SQLException {
        return (RasterblockRowType[]) _array.getObjectArray(index, new RasterblockRowType[_array.sliceLength(index, count)]);
    }

    public void setArray(RasterblockRowType[] a, long index) throws SQLException {
        _array.setObjectArray(a, index);
    }

    public RasterblockRowType getElement(long index) throws SQLException {
        return (RasterblockRowType) _array.getObjectElement(index);
    }

    public void setElement(RasterblockRowType a, long index) throws SQLException {
        _array.setObjectElement(a, index);
    }

}
