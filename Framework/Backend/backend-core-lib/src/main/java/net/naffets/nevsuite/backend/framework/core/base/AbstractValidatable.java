package net.naffets.nevsuite.backend.framework.core.base;

import net.naffets.nevsuite.backend.framework.core.api.Validatable;
import net.naffets.nevsuite.backend.framework.core.api.ValidationConstraint;

import java.util.LinkedList;

/**
 * @author br4sk4
 * created on 12.09.2015
 */
public abstract class AbstractValidatable implements Validatable {

    protected LinkedList<ValidationConstraint> constraints;

    public AbstractValidatable() {
        constraints = new LinkedList<>();
    }

    public boolean addValidationConstraint(ValidationConstraint validationConstraint) {
        return constraints.add(validationConstraint);
    }

    public boolean removeValidationConstraint(ValidationConstraint validationConstraint) {
        return constraints.remove(validationConstraint);
    }

    public boolean validate() throws Exception {
        boolean result = true;

        for (ValidationConstraint constraint : constraints) {
            result = result && constraint.validate(this);
        }

        return result;
    }

}
