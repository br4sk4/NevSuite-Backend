package net.naffets.nevsuite.backend.timeseries.domain.dto;

import net.naffets.nevsuite.backend.framework.core.api.DataTransferObject;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.List;

/**
 * @author br4sk4 / created on 22.10.2017
 */
@XmlType(propOrder = {
        "reference",
        "valueMap"
})
@XmlRootElement(name = "timeseries")
public class TimeseriesDTO implements Serializable, DataTransferObject {

    private final static long serialVersionUID = 1L;

    private TimeseriesReferenceDTO reference;
    private List<TimeseriesValueDTO> valueMap;

    public TimeseriesReferenceDTO getReference() {
        return reference;
    }

    public void setReference(TimeseriesReferenceDTO reference) {
        this.reference = reference;
    }

    public List<TimeseriesValueDTO> getValueMap() {
        return valueMap;
    }

    public void setValueMap(List<TimeseriesValueDTO> valueMap) {
        this.valueMap = valueMap;
    }
}
