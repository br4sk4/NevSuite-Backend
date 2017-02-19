package net.naffets.nevsuite.backend.framework.core.base;

/**
 * @author br4sk4
 * created on 19.04.2016
 */
public abstract class AbstractDomainObject extends AbstractValidatable {

    public boolean isEntity() {
        return false;
    }

    public boolean isValueObject() {
        return false;
    }

}
