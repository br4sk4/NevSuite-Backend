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
        "timestamp",
        "descriptor",
        "referencedObjectType",
        "referencedObjectId"
})
@XmlRootElement(name = "eventnotification")
@NoArgsConstructor
public class EventNotificationDTO implements Serializable, DataTransferObject {

    public String timestamp;
    public String descriptor;
    public String referencedObjectType;
    public String referencedObjectId;

    @Builder
    public EventNotificationDTO(String timestamp, String descriptor, String referencedObjectType, String referencedObjectId) {
        this.timestamp = timestamp;
        this.descriptor = descriptor;
        this.referencedObjectType = referencedObjectType;
        this.referencedObjectId = referencedObjectId;
    }
}
