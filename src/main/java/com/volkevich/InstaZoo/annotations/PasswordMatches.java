package com.volkevich.InstaZoo.annotations;

import com.volkevich.InstaZoo.validations.EmailValidator;
import com.volkevich.InstaZoo.validations.PasswordMatchersValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatchersValidator.class)
@Documented
public @interface PasswordMatches {

    String message() default "Password do not match";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
