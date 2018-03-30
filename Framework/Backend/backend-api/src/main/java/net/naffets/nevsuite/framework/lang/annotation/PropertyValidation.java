package net.naffets.nevsuite.framework.lang.annotation;

import net.naffets.nevsuite.framework.lang.validation.PropertyValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author br4sk4 / created on 21.11.2017
 */
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PropertyValidator.class)
public @interface PropertyValidation {

    String[] propertiesToValidate() default {};

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
