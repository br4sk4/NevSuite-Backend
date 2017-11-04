package net.naffets.nevsuite.backend.timeseries.lang.persistence;

import net.naffets.nevsuite.backend.framework.core.api.DataTransferObject;
import net.naffets.nevsuite.backend.timeseries.domain.dto.TimeseriesValueDTO;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * @author br4sk4 / created on 22.10.2017
 */
@XmlType(propOrder = {
        "valueMap"
})
@XmlRootElement(name = "document")
public class ValueMapDocument implements Serializable, DataTransferObject {

    private final static long serialVersionUID = 1L;

    private List<TimeseriesValueDTO> valueMap = new LinkedList<>();

    public List<TimeseriesValueDTO> getValueMap() {
        return valueMap;
    }

    public void setValueMap(List<TimeseriesValueDTO> valueMap) {
        this.valueMap = valueMap;
    }
}
