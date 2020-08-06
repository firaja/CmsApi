package com.app.cms.validator.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ValidateOnlySetValuesValidator.class)
public @interface ValidateOnlySetValues {
    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
