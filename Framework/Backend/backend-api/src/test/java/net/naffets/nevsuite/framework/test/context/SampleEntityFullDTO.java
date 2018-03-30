package net.naffets.nevsuite.framework.test.context;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
@XmlRootElement(name = "person")
@NoArgsConstructor
public class SampleEntityFullDTO implements DataTransferObject {

    public String primaryKey;
    public String firstName;
    public String lastName;

    @Builder
    public SampleEntityFullDTO(
            String primaryKey,
            String firstName,
            String lastName
    ) {
        this.primaryKey = primaryKey;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
