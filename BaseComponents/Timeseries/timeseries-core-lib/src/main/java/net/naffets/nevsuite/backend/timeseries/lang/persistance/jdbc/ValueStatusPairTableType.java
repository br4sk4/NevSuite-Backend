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
public class ValueStatusPairTableType extends TimeseriesManagementDBType implements ORAData, ORADataFactory {

    public static final String _SQL_NAME = "TSM_OWNER.VALUE_STATUS_PAIR_TABLE";
    public static final int _SQL_TYPECODE = OracleTypes.ARRAY;
    private static final ValueStatusPairTableType _ValueStatusPairTableTypeFactory = new ValueStatusPairTableType();
    MutableArray _array;

    public ValueStatusPairTableType() {
        this((ValueStatusPairRowType[]) null);
    }

    public ValueStatusPairTableType(ValueStatusPairRowType[] a) {
        _array = new MutableArray(OracleTypes.STRUCT, a, ValueStatusPairRowType.getORADataFactory());
    }

    public static ORADataFactory getORADataFactory() {
        return _ValueStatusPairTableTypeFactory;
    }

    /* ************************ */
    /* ORADataFactory interface */
    /* ************************ */

    public ORAData create(Datum d, int sqlType) throws SQLException {
        if (d == null)
            return null;

        ValueStatusPairTableType a = new ValueStatusPairTableType();
        a._array = new MutableArray(OracleTypes.STRUCT, (ARRAY) d, ValueStatusPairRowType.getORADataFactory());

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

    public ValueStatusPairRowType[] getArray() throws SQLException {
        return (ValueStatusPairRowType[]) _array.getObjectArray(new ValueStatusPairRowType[_array.length()]);
    }

    public void setArray(ValueStatusPairRowType[] a) throws SQLException {
        _array.setObjectArray(a);
    }

    public ValueStatusPairRowType[] getArray(long index, int count) throws SQLException {
        return (ValueStatusPairRowType[]) _array.getObjectArray(index, new ValueStatusPairRowType[_array.sliceLength(index, count)]);
    }

    public void setArray(ValueStatusPairRowType[] a, long index) throws SQLException {
        _array.setObjectArray(a, index);
    }

    public ValueStatusPairRowType getElement(long index) throws SQLException {
        return (ValueStatusPairRowType) _array.getObjectElement(index);
    }

    public void setElement(ValueStatusPairRowType a, long index) throws SQLException {
        _array.setObjectElement(a, index);
    }

}
