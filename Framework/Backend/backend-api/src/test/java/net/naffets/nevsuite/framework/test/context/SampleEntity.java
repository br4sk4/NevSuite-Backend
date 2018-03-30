package net.naffets.nevsuite.framework.test.context;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.naffets.nevsuite.framework.core.api.EntityBean;
import net.naffets.nevsuite.framework.core.api.Reference;
import net.naffets.nevsuite.framework.core.base.AbstractEntityBean;
import net.naffets.nevsuite.framework.core.base.AbstractReference;
import net.naffets.nevsuite.framework.lang.annotation.PropertyValidation;

/**
 * @author br4sk4 / created on 30.03.2018
 */
@PropertyValidation
@NoArgsConstructor
@Getter
@Setter
public class SampleEntity extends AbstractEntityBean implements EntityBean {

    private String firstName;
    private String lastName;

    @Builder
    public SampleEntity(
            String primaryKey,
            String firstName,
            String lastName
    ) {
        super(primaryKey);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public Reference asReference() {
        return new AbstractReference(this) {
            @Override
            public String getRepresentableName() {
                return firstName + " " + lastName;
            }

            @Override
            public String getTypeDiscriminator() {
                return SampleEntity.class.getSimpleName().toUpperCase();
            }
        };
    }

}
