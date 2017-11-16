package net.naffets.nevsuite.backend.framework.lang.exception;

import java.util.List;

/**
 * @author br4sk4 / created on 16.11.2017
 */
public class FieldErrorException extends RuntimeException {

    private List<String> fieldErrors;

    public FieldErrorException(String message, List<String> fieldErrors) {
        super(message);
        this.fieldErrors = fieldErrors;
    }

    public List<String> getFieldErrors() {
        return fieldErrors;
    }

}
