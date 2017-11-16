package net.naffets.nevsuite.backend.framework.core.base;

import net.naffets.nevsuite.backend.framework.lang.annotation.Validation;
import net.naffets.nevsuite.backend.framework.lang.exception.FieldErrorException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static java.util.Objects.isNull;

/**
 * @author br4sk4
 * created on 19.04.2016
 */
public abstract class AbstractDomainObject extends AbstractValidatable {

    /**
     * Validates the underlying object structure with annotated {@link net.naffets.nevsuite.backend.framework.core.api.ValidationVisistor}s.
     * @see Validation
     */
    @Override
    public boolean validate() {
        List<FieldErrorException> fieldErrorExceptions = new ArrayList<>();
        Class[] validators = this.getClass().getAnnotation(Validation.class).validators();

        Predicate<Method> methodFilter = method -> method.getName().equals("validate")
                && !isNull(method.getParameterTypes()[0])
                && method.getParameterTypes()[0].getTypeName().equals(AbstractDomainObject.class.getName());

        Arrays.stream(validators).forEach(validator -> {
            try {
                Object instance = this.getClass().getClassLoader().loadClass(validator.getName()).newInstance();
                Arrays.stream(instance.getClass().getDeclaredMethods())
                        .filter(methodFilter)
                        .forEach(method -> {
                            try {
                                method.invoke(instance, this);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                fieldErrorExceptions.add((FieldErrorException) e.getCause());
                            }
                        });
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e ) {
                e.printStackTrace();
            }
        });

        if ( !fieldErrorExceptions.isEmpty() ) {
            List<String> fieldErrors = new ArrayList<>();
            fieldErrorExceptions.forEach(fieldErrorException -> fieldErrors.addAll(fieldErrorException.getFieldErrors()));
            throw new FieldErrorException("validation.error", fieldErrors);
        }

        return true;
    }

}
