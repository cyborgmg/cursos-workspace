package com.howtodoinjava.demo.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;

class DateValidator implements ConstraintValidator<DateValidation, String> {

    private String format;
    private DateType type;

    @Override
    public void initialize(final DateValidation values) {
        format = values.format();
        type = values.type();
    }

    public Boolean validationDateFormat(final String date, String pattern, DateType type) {
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern(pattern);
            if(type.equals(DateType.DATE)) {
                LocalDate.parse(date, format);
            }else{
                LocalDateTime.parse(date, format);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return validationDateFormat(value,this.format,type);
    }
}

