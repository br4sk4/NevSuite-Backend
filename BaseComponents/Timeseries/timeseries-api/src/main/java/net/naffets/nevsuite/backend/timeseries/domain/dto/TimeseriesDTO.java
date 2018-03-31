package net.naffets.nevsuite.backend.timeseries.domain.dto;

import lombok.Builder;
import lombok.NoArgsConstructor;
import net.naffets.nevsuite.framework.core.api.DataTransferObject;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.List;

/**
 * @author br4sk4 / created on 22.10.2017
 */
@XmlType(propOrder = {
        "identifier",
        "valueMap"
})
@XmlRootElement(name = "timeseries")
@NoArgsConstructor
public class TimeseriesDTO implements Serializable, DataTransferObject {

    private final static long serialVersionUID = 1L;

    @Builder
    public TimeseriesDTO(String identifier, List<TimeseriesValueDTO> valueMap) {
        this.identifier = identifier;
        this.valueMap = valueMap;
    }

    public String identifier;
    public List<TimeseriesValueDTO> valueMap;
}
