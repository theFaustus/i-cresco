package com.evil.inc.icresco.config.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ResolvedErrorAttributes extends DefaultErrorAttributes {

    private final MessageSource messageSource;

    @Override
    public Map<String, Object> getErrorAttributes(final WebRequest webRequest, final ErrorAttributeOptions options) {
        final Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);
        resolveBindingErrors(errorAttributes);
        return errorAttributes;
    }

    private void resolveBindingErrors(Map<String, Object> errorAttributes) {
        List<ObjectError> errors = (List<ObjectError>) errorAttributes.get("errors");
        if (errors == null) return;

        List<String> errorMessages = new ArrayList<>();
        for (ObjectError error : errors) {
            String resolved = messageSource.getMessage(error, Locale.US);
            if (error instanceof FieldError) {
                FieldError fieldError = (FieldError) error;
                errorMessages.add(
                        fieldError.getField() + " " + resolved + " but value was " + fieldError.getRejectedValue());
            } else {
                errorMessages.add(resolved);
            }
        }
        errorAttributes.put("errors", errorMessages);
    }
}