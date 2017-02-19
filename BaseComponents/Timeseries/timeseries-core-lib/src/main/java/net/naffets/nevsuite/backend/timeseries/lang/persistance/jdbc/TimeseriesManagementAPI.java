package net.naffets.nevsuite.backend.timeseries.lang.persistance.jdbc;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

import java.sql.SQLException;

/**
 * @author br4sk4
 * created on 12.03.2016
 */
public class TimeseriesManagementAPI extends TimeseriesManagementDBType {

    public void writeRasterblockRow(RasterblockRowType rbl) throws SQLException {

        RasterblockRowType resultRbl = null;
        OracleCallableStatement cstmt = null;

        // Prepare Call
        String theSqlTS = "BEGIN TSM_OWNER.P_API.writeRasterblockRow( :1 ) ; END;";
        cstmt = (OracleCallableStatement) getConnection().prepareCall(theSqlTS);

        // Set In Parameters
        cstmt.setORAData(1, rbl);
        cstmt.execute();
        cstmt.close();

    }

    public void writeRasterblockTable(RasterblockTableType rblTab) throws SQLException {
        RasterblockRowType resultRbl = null;
        OracleCallableStatement cstmt = null;

        // Prepare Call
        String theSqlTS = "BEGIN TSM_OWNER.P_API.writeRasterblockTable( :1 ) ; END;";
        cstmt = (OracleCallableStatement) getConnection().prepareCall(theSqlTS);

        // Set In Parameters
        cstmt.setORAData(1, rblTab);
        cstmt.execute();
        cstmt.close();
    }

    public void deleteRasterblocks(TimeseriesPkRowType ts) throws SQLException {

        OracleCallableStatement cstmt = null;

        // Prepare Call
        String theSqlTS = "BEGIN TSM_OWNER.P_API.deleteRasterblocks( :1 ) ; END;";
        cstmt = (OracleCallableStatement) getConnection().prepareCall(theSqlTS);

        // Set In Parameters
        cstmt.setORAData(1, ts);
        cstmt.execute();
        cstmt.close();

    }

    public RasterblockTableType readRasterblocks(TimeseriesPkRowType ts) throws SQLException {

        RasterblockTableType rblTable = null;
        OracleCallableStatement cstmt = null;

        // Prepare Call
        String theSqlTS = "BEGIN :1 := TSM_OWNER.P_API.readRasterblocks( :2 ) ; END;";
        cstmt = (OracleCallableStatement) getConnection().prepareCall(theSqlTS);

        // Register Out Parameters
        cstmt.registerOutParameter(1, OracleTypes.ARRAY, "TSM_OWNER.RASTERBLOCK_TABLE");

        // Set In Parameters
        cstmt.setORAData(2, ts);
        cstmt.execute();

        // Get Out Parameters
        rblTable = (RasterblockTableType) cstmt.getORAData(1, RasterblockTableType.getORADataFactory());

        cstmt.close();

        return rblTable;

    }

}
