package org.zookong.springboot.lab.core.common.validation.annotation;

import org.zookong.springboot.lab.core.common.enums.ValidtaionType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodScope {
    ValidtaionType[] value();
    String message() default "";
}