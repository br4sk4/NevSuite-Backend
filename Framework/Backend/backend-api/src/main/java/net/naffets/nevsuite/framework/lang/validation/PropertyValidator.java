package net.naffets.nevsuite.framework.lang.validation;

import com.google.gson.Gson;
import net.naffets.nevsuite.framework.core.base.AbstractValidatable;
import net.naffets.nevsuite.framework.lang.annotation.PropertyValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

/**
 * @author br4sk4 / created on 21.11.2017
 */
public class PropertyValidator implements ConstraintValidator<PropertyValidation, AbstractValidatable> {

    private PropertyValidation propertyValidation;

    @Override
    public void initialize(PropertyValidation propertyValidation) {
        this.propertyValidation = propertyValidation;
    }

    @Override
    public boolean isValid(AbstractValidatable validatable, ConstraintValidatorContext constraintValidatorContext) {
        try {
            if (!isNull(validatable)) {
                Set<String> properties = (propertyValidation.propertiesToValidate().length > 0 )
                        ? Arrays.stream(propertyValidation.propertiesToValidate()).collect(Collectors.toSet())
                        : propertiesToValidate(validatable);
                validatable.validateProperties(properties);
            }
        } catch( EntityValidationException e ) {
            final List<ValidationError> validationErrors = this.buildFieldErrorList(e);
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(new Gson().toJson(validationErrors))
                    .addPropertyNode("validationErrors")
                    .addConstraintViolation();

            return false;
        }
        return true;
    }

    public static Set<String> propertiesToValidate(AbstractValidatable entity) {
        return propertyCrawler(entity.getClass());
    }

    private static Set<String> propertyCrawler(Class<?> clazz) {
        if (clazz.getSimpleName().equals(Object.class.getSimpleName())) {
            return Collections.emptySet();
        } else {
            Set<String> propertiesToValidate = new HashSet<>();
            propertiesToValidate.addAll(
                    Arrays.stream(clazz.getDeclaredFields())
                            .map(Field::getName)
                            .collect(Collectors.toSet())
            );
            propertiesToValidate.addAll(propertyCrawler(clazz.getSuperclass()));
            return propertiesToValidate;
        }
    }

    private List<ValidationError> buildFieldErrorList(EntityValidationException exception) {
        final List<ValidationError> validationErrors = new ArrayList<>();

        this.addSimpleFieldErrors(exception, validationErrors);
        this.addEmbeddedEntityFieldErrors(exception, validationErrors);

        return validationErrors;
    }

    private void addSimpleFieldErrors(final EntityValidationException exception, final List<ValidationError> validationErrors) {
        validationErrors.addAll(exception.getValidationErrors().stream()
                .filter(fieldError -> !fieldError.getFieldName().endsWith("validationErrors"))
                .collect(Collectors.toList()));
    }

    private void addEmbeddedEntityFieldErrors(final EntityValidationException exception, final List<ValidationError> validationErrors) {
        exception.getValidationErrors().stream()
                .filter(byEmbeddedEntityFilter())
                .forEach(erroneousEntity -> {
                    try {
                        ValidationError[] errors = new Gson().fromJson(erroneousEntity.getErrorMessage(), ValidationError[].class);
                        List<ValidationError> errorList = Arrays.asList(errors);
                        errorList = errorList.stream()
                                .map(erroneousField -> this.createPropertyPathString(erroneousEntity, erroneousField))
                                .collect(Collectors.toList());
                        validationErrors.addAll(errorList);
                    } catch ( Exception e ) {
                        e.printStackTrace();
                    }
                });
    }

    private Predicate<ValidationError> byEmbeddedEntityFilter() {
        return (fieldError) ->
                fieldError.getFieldName().split("\\.").length == 2
                && fieldError.getFieldName().endsWith("validationErrors");
    }

    private ValidationError createPropertyPathString(ValidationError erroneousEntity, ValidationError erroneousField) {
        String erroneousFieldPath = erroneousEntity.getFieldName().split("\\.")[0]
                + "."
                + erroneousField.getFieldName();

        return new ValidationError(
                erroneousFieldPath,
                erroneousField.getErrorMessage()
        );
    }

}
