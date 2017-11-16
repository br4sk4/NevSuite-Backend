package net.naffets.nevsuite.backend.framework.lang.annotation;

import net.naffets.nevsuite.backend.framework.core.api.ValidationVisistor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Classes, extending the class {@link net.naffets.nevsuite.backend.framework.core.base.AbstractDomainObject}, and annotated with this Annotation
 * will be validated by the {@link ValidationVisistor}s defined in {@link #validators()}
 *
 * @author br4sk4
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Validation {
    Class<? extends ValidationVisistor>[] validators();
}
