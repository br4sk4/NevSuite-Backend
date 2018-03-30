package net.naffets.nevsuite.framework.lang.validation;

import com.google.gson.Gson;

/**
 * @author br4sk4 / created on 21.11.2017
 */
public class ValidationError {

    public ValidationError(String fieldName, String errorMessage) {
        this.fieldName = fieldName;
        this.errorMessage = errorMessage;
    }

    private String fieldName;
    private String errorMessage;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
