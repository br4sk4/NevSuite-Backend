package net.naffets.nevsuite.framework.lang.validation;

import java.util.List;

/**
 * @author br4sk4 / created on 21.11.2017
 */
public class EntityValidationException extends RuntimeException {

    private List<ValidationError> validationErrors;

    public EntityValidationException(String message, List<ValidationError> validationErrors) {
        super(message);
        this.validationErrors = validationErrors;
    }

    public List<ValidationError> getValidationErrors() {
        return validationErrors;
    }

}
