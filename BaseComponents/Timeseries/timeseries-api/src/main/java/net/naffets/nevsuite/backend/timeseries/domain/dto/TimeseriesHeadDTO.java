package net.naffets.nevsuite.backend.timeseries.domain.dto;

import lombok.Builder;
import net.naffets.nevsuite.framework.core.api.DataTransferObject;

import javax.xml.bind.annotation.XmlRootElement;
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
@XmlRootElement(name = "timeseriesHead")
public class TimeseriesHeadDTO implements Serializable, DataTransferObject {

    private final static long serialVersionUID = 1L;

    @Builder
    public TimeseriesHeadDTO(
            String primaryKey,
            String identifier,
            String type,
            String derivationType,
            String persistence,
            String periodicity,
            String blockSize,
            String rasterType) {
        this.primaryKey = primaryKey;
        this.identifier = identifier;
        this.type = type;
        this.derivationType = derivationType;
        this.persistence = persistence;
        this.periodicity = periodicity;
        this.blockSize = blockSize;
        this.rasterType = rasterType;
    }

    public String primaryKey;
    public String identifier;
    public String type;
    public String derivationType;
    public String persistence;
    public String periodicity;
    public String blockSize;
    public String rasterType;
}
