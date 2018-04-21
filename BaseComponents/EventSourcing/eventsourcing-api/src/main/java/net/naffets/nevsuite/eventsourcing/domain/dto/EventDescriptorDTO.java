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
        "qualifier",
        "shortText"
})
@XmlRootElement(name = "eventdescriptor")
@NoArgsConstructor
public class EventDescriptorDTO implements Serializable, DataTransferObject {

    public String qualifier;
    public String shortText;

    @Builder
    public EventDescriptorDTO(
            String qualifier,
            String shortText) {
        this.qualifier = qualifier;
        this.shortText = shortText;
    }
}
