package org.example.validator;

import jakarta.validation.Constraint;
import org.example.validator.imp.IpAddressImp;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = {IpAddressImp.class})
@Target({java.lang.annotation.ElementType.FIELD})
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)

public @interface IpAddress {

    java.lang.String message() default "{validation.constraints.ip-address.message}";

    java.lang.Class<?>[] groups() default {};

    java.lang.Class<? extends jakarta.validation.Payload>[] payload() default {};


}

