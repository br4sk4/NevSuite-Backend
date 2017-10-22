package net.naffets.nevsuite.backend.timeseries.domain.dto;

import javax.xml.bind.annotation.XmlType;

/**
 * @author br4sk4 / created on 22.10.2017
 */
@XmlType(propOrder = {
        "timestamp",
        "version",
        "value",
        "status"
})
public class TimeseriesValueDTO {

    private final static long serialVersionUID = 1L;

    private String timestamp;
    private String version;
    private String value;
    private String status;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
