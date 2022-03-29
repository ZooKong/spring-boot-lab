package org.jjr.flowerbook.core.common.validation.annotation;


import org.jjr.flowerbook.core.common.enums.ValidtaionType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldScope {
    ValidtaionType value();
    Class<?>[] constraints() default {};
}