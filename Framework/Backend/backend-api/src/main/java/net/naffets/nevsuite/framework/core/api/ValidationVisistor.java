package net.naffets.nevsuite.framework.core.api;

import net.naffets.nevsuite.framework.core.base.AbstractValidatable;

/**
 * @author br4sk4 / created on 21.11.2017
 */
public interface ValidationVisistor<T extends AbstractValidatable> {

    void validate(T validatable);

}
