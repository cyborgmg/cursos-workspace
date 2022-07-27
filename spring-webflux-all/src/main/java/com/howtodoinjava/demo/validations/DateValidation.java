package com.howtodoinjava.demo.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

@Target({FIELD, CONSTRUCTOR, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = DateValidator.class)
public @interface DateValidation {

    String message() default "A data informada é inválida!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String format() default "dd/MM/yyyy";

    DateType type() default DateType.DATE;

    Class<String> value();

}
