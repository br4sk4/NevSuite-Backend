package net.naffets.nevsuite.framework.lang.annotation;

import net.naffets.nevsuite.framework.core.api.ValidationVisistor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author br4sk4 / created on 21.11.2017
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface VisitorValidation {
    Class<? extends ValidationVisistor>[] validators();
}
