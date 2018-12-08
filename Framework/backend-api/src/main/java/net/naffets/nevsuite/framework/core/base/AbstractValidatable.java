package net.naffets.nevsuite.framework.core.base;

import net.naffets.nevsuite.framework.core.api.Validatable;
import net.naffets.nevsuite.framework.lang.annotation.PropertyValidation;
import net.naffets.nevsuite.framework.lang.annotation.VisitorValidation;
import net.naffets.nevsuite.framework.lang.validation.ValidationError;
import net.naffets.nevsuite.framework.lang.validation.EntityValidationException;
import net.naffets.nevsuite.framework.core.api.ValidationVisistor;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static net.naffets.nevsuite.framework.lang.validation.PropertyValidator.propertiesToValidate;

/**
 * @author br4sk4
 * created on 12.09.2015
 */
public abstract class AbstractValidatable implements Validatable {

    @Override
    public void validate() {
        ArrayList<ValidationError> validationErrors = new ArrayList<>();
        Optional<PropertyValidation> propertyValidationAnnotation = Optional.ofNullable(this.getClass().getAnnotation(PropertyValidation.class));
        propertyValidationAnnotation.ifPresent(validationAnnotation -> {
            Set<String> properties = validationAnnotation.propertiesToValidate().length > 0
                    ? Arrays.stream(validationAnnotation.propertiesToValidate()).collect(Collectors.toSet())
                    : propertiesToValidate(this);
            validationErrors.addAll(this.validateProperties(properties));
        });

        Optional<VisitorValidation> visitorValidationAnnotation = Optional.ofNullable(this.getClass().getAnnotation(VisitorValidation.class));
        visitorValidationAnnotation.ifPresent(validationAnnotation -> validationErrors.addAll(this.validateByVisitors()));

        if ( !validationErrors.isEmpty() ) {
            throw new EntityValidationException("validation.error", validationErrors);
        }
    }

    public List<ValidationError> validateProperties(Set<String> properties) {
        Validator validator = javax.validation.Validation.buildDefaultValidatorFactory().getValidator();

        Set<ConstraintViolation<? extends AbstractValidatable>> constraintViolationSet = new HashSet<>();
        properties.forEach(property -> constraintViolationSet.addAll(validator.validateProperty(this, property)));

        return this.processConstraintViolations(constraintViolationSet);
    }

    private List<ValidationError> validateByVisitors() {
        List<EntityValidationException> validationErrorExceptions = new ArrayList<>();
        Optional<VisitorValidation> annotation = Optional.ofNullable(this.getClass().getAnnotation(VisitorValidation.class));
        annotation.ifPresent(validationAnnotation -> {
            Class<? extends ValidationVisistor>[] validators = validationAnnotation.validators();

            Predicate<Method> methodFilter = method -> method.getName().equals("validate")
                    && !isNull(method.getParameterTypes()[0])
                    && method.getParameterTypes()[0].getTypeName().equals(AbstractValidatable.class.getName());

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
                                    validationErrorExceptions.add((EntityValidationException) e.getCause());
                                }
                            });
                } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e ) {
                    e.printStackTrace();
                }
            });
        });

        List<ValidationError> validationErrors = new ArrayList<>();
        validationErrorExceptions.forEach(validationErrorException -> validationErrors.addAll(validationErrorException.getValidationErrors()));
        return validationErrors;
    }

    private List<ValidationError> processConstraintViolations(Set<ConstraintViolation<? extends AbstractValidatable>> constraintViolationSet) {
        List<ValidationError> validationErrors = new ArrayList<>();

        constraintViolationSet.forEach(violation -> {
            StringBuilder sb = new StringBuilder();
            violation.getPropertyPath().forEach(path -> {
                if (sb.length() > 0) {
                    sb.append(".");
                }
                sb.append(path.getName());
            });
            validationErrors.add(new ValidationError(sb.toString(), violation.getMessage()));
        });

        return validationErrors;
    }

}
