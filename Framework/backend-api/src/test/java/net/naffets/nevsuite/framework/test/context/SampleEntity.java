package net.naffets.nevsuite.framework.test.context;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.naffets.nevsuite.framework.core.api.EntityBean;
import net.naffets.nevsuite.framework.core.api.Reference;
import net.naffets.nevsuite.framework.core.base.AbstractEntityBean;
import net.naffets.nevsuite.framework.core.base.BaseReference;
import net.naffets.nevsuite.framework.lang.annotation.PropertyValidation;

import javax.validation.constraints.NotNull;

/**
 * @author br4sk4 / created on 30.03.2018
 */
@PropertyValidation
@NoArgsConstructor
@Getter
@Setter
public class SampleEntity extends AbstractEntityBean implements EntityBean {

    @NotNull(message = "darf nicht null sein")
    private String firstName;

    @NotNull(message = "darf nicht null sein")
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
        return new BaseReference(this) {
            @Override
            public String getRepresentableName() {
                return firstName + " " + lastName;
            }

            @Override
            public String getTypeDiscriminator() {
                return SampleEntity.class.getSimpleName();
            }
        };
    }

}
