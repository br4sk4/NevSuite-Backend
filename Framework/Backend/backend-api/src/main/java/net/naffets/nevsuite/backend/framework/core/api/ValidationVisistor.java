package net.naffets.nevsuite.backend.framework.core.api;

import net.naffets.nevsuite.backend.framework.core.base.AbstractDomainObject;
import net.naffets.nevsuite.backend.framework.lang.annotation.Validation;

/**
 * Classes, extending this interface may be used in the {@link Validation}-Annotation.
 * Entities annotated with this Annotation get validated by each defined ValidationVisitor.
 *
 * @author br4sk4
 */
public interface ValidationVisistor<T extends AbstractDomainObject> {

    void validate(T validatable);

}
