package logger.logic;

import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.*;

/**
 * This file will contain Bean validation
 * Bean resources: 
 * https://www.baeldung.com/java-validation 
 * https://docs.spring.io/spring-framework/reference/core/validation/beanvalidation.html
 */


public class LogValidation {
    private final Validator validator;

    public LogValidation() {
        // Crate engine for Bean validation
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    public boolean isValid(LogEntry entry) {
        Set<ConstraintViolation<LogEntry>> violations = validator.validate(entry);
        
        return violations.isEmpty();
    }

}



