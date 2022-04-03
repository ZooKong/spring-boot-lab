package org.zookong.springboot.lab.core.common.validation.handler;


import org.zookong.springboot.lab.core.common.enums.ValidtaionType;
import org.zookong.springboot.lab.core.common.util.ClassUtil;
import org.zookong.springboot.lab.core.common.validation.annotation.FieldScope;
import org.zookong.springboot.lab.core.common.validation.annotation.FieldScopes;
import org.zookong.springboot.lab.core.common.validation.annotation.MethodScope;
import org.zookong.springboot.lab.framework.exception.SemiRpaCoreException;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * 도메인의 필드에 대한 유효성 검사 클래스
 *
 * @author Created by 유주빈 on 2020.11.02
 */
public class FieldValidationHandler {

    private Validator validator;

    public FieldValidationHandler(Validator validator) {
        this.validator = validator;
    }

    /**
     * 도메인 객체의 필드를 유효성 검사하여 검사에 불합격시 에러 발생
     *
     * @param obj 도메인 객체
     */
    public void throwFieldValidationException(Object obj, ValidtaionType type) {

        if (ClassUtil.isEmptyObjects(obj, type))
            throw new SemiRpaCoreException();

        Class clz = obj.getClass();

        Set<ConstraintViolation<Object>> validations = validator.validate(obj);

        for (ConstraintViolation violation : validations) {

            Field field;

            try {
                field = clz.getDeclaredField(violation.getPropertyPath().toString());
            } catch (Exception e) {
                throw new SemiRpaCoreException();
            }

            boolean canGenerateException = existViolation(field, type, violation);

            if (canGenerateException)
                throw new SemiRpaCoreException();
        }

        for (Method method : obj.getClass().getDeclaredMethods()) {
            MethodScope methodScope = method.getAnnotation(MethodScope.class);

            // 메서드에 Validation 관련 어노테이션이 없으면 로직을 수행하지 않음.
            if (methodScope == null)
                continue;

            // Return 타입이 void이면
            if (void.class == method.getReturnType())
                continue;

            ValidtaionType[] validtaionTypes = methodScope.value();

            // 지정한 ValidtaionType 이 존재해야 함.
            if (!existValidtaionType(validtaionTypes, type))
                continue;

            boolean originIsAccessible = method.isAccessible();
            Object returnValue = null;

            try {
                if (!originIsAccessible)
                    method.setAccessible(true);

                returnValue = method.invoke(obj);
            } catch (Exception e) {
                throw new SemiRpaCoreException();
            } finally {
                method.setAccessible(originIsAccessible);
            }

            if (returnValue == null) {
                String message = methodScope.message();
                throw new SemiRpaCoreException();
            }

        }

    }

    private boolean existViolation(Field field, ValidtaionType type, ConstraintViolation violation) {

        FieldScopes scopes = field.getAnnotation(FieldScopes.class);
        FieldScope[] scopeList = scopes.value();
        Class violationClass = violation.getConstraintDescriptor().getAnnotation().annotationType();

        for (FieldScope scope : scopeList) {

            if (scope.value() != type)
                continue;

            if (scope.constraints().length == 0)
                continue;

            for (Class constraint : scope.constraints()) {

                if (violationClass == constraint)
                    return true;
            }
        }

        return false;
    }

    private boolean existValidtaionType(ValidtaionType[] validtaionTypes, ValidtaionType validtaionType) {

        if (ClassUtil.isEmptyObjects(validtaionTypes, validtaionType))
            throw new SemiRpaCoreException();

        for (ValidtaionType type : validtaionTypes) {
            if (type == validtaionType)
                return true;
        }
        return false;
    }

}