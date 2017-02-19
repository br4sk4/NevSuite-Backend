package net.naffets.nevsuite.backend.framework.core.api;

/**
 * @author br4sk4
 * created on 03.07.2015
 */
public interface ValidationConstraint<T> {

    boolean validate(T object) throws Exception;

}
