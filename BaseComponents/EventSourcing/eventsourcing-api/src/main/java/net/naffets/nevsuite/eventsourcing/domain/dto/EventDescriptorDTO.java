package net.naffets.nevsuite.eventsourcing.domain.dto;

import lombok.Builder;
import lombok.NoArgsConstructor;
import net.naffets.nevsuite.framework.core.api.DataTransferObject;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * @author br4sk4
 * created on 20.04.18
 */
@XmlType(propOrder = {
        "qualifier"
})
@XmlRootElement(name = "backgroundprocess")
@NoArgsConstructor
public class EventDescriptorDTO implements Serializable, DataTransferObject {

    public String qualifier;

    @Builder
    public EventDescriptorDTO(String qualifier) {
        this.qualifier = qualifier;
    }
}
