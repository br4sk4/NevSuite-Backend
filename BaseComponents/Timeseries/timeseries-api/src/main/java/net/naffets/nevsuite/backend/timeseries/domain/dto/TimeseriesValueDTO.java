package net.naffets.nevsuite.backend.timeseries.domain.dto;

import lombok.Builder;
import lombok.NoArgsConstructor;
import net.naffets.nevsuite.framework.core.api.DataTransferObject;

import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * @author br4sk4 / created on 22.10.2017
 */
@XmlType(propOrder = {
        "timestamp",
        "version",
        "value",
        "status"
})
@NoArgsConstructor
public class TimeseriesValueDTO implements Serializable, DataTransferObject {

    private final static long serialVersionUID = 1L;

    @Builder
    public TimeseriesValueDTO(
            String timestamp,
            String version,
            String value,
            String status) {
        this.timestamp = timestamp;
        this.version = version;
        this.value = value;
        this.status = status;
    }

    public String timestamp;
    public String version;
    public String value;
    public String status;
}
