package net.naffets.nevsuite.backend.timeseries.domain.dto;

import net.naffets.nevsuite.backend.framework.core.api.DataTransferObject;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * @author br4sk4 / created on 22.10.2017
 */
@XmlType(propOrder = {
        "primaryKey",
        "identifier"
})
@XmlRootElement(name = "timeseriesReference")
public class TimeseriesReferenceDTO implements Serializable, DataTransferObject {

    private final static long serialVersionUID = 1L;

    private String primaryKey;
    private String identifier;

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
}
