package net.naffets.nevsuite.backend.timeseries.domain.dto;

import net.naffets.nevsuite.backend.framework.core.api.DataTransferObject;

import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * @author br4sk4 / created on 22.10.2017
 */
@XmlType(propOrder = {
        "primaryKey",
        "identifier",
        "type",
        "derivationType",
        "persistence",
        "periodicity",
        "blockSize",
        "rasterType"
})

public class TimeseriesHeadDTO implements Serializable, DataTransferObject {

    private final static long serialVersionUID = 1L;

    private String primaryKey;
    private String identifier;
    private String type;
    private String derivationType;
    private String persistence;
    private String periodicity;
    private String blockSize;
    private String rasterType;

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDerivationType() {
        return derivationType;
    }

    public void setDerivationType(String derivationType) {
        this.derivationType = derivationType;
    }

    public String getPersistence() {
        return persistence;
    }

    public void setPersistence(String persistence) {
        this.persistence = persistence;
    }

    public String getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(String periodicity) {
        this.periodicity = periodicity;
    }

    public String getBlockSize() {
        return blockSize;
    }

    public void setBlockSize(String blockSize) {
        this.blockSize = blockSize;
    }

    public String getRasterType() {
        return rasterType;
    }

    public void setRasterType(String rasterType) {
        this.rasterType = rasterType;
    }
}
