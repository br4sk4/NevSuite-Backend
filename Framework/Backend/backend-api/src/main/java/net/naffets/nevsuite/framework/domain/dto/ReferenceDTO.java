package net.naffets.nevsuite.framework.domain.dto;

import lombok.Builder;
import lombok.NoArgsConstructor;
import net.naffets.nevsuite.framework.core.api.DataTransferObject;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author br4sk4 / created on 30.03.2018
 */
@XmlType(propOrder = {
        "primaryKey",
        "firstName",
        "lastName"
})
@XmlRootElement(name = "reference")
@NoArgsConstructor
public class ReferenceDTO implements DataTransferObject {

    public String primaryKey;
    public String typeDiscriminator;
    public String representableName;

    @Builder
    public ReferenceDTO(String primaryKey, String typeDiscriminator, String representableName) {
        this.primaryKey = primaryKey;
        this.typeDiscriminator = typeDiscriminator;
        this.representableName = representableName;
    }
}
