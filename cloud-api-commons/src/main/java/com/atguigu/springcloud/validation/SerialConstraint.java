package com.atguigu.springcloud.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = SerialValidation.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface SerialConstraint {

    String message() default "Invalid serial";

    Class<?>[] groups() default {};// this is mondatory

    Class<? extends Payload>[] payload() default {};

}
