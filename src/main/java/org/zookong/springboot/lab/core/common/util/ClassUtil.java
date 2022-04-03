package org.zookong.springboot.lab.core.common.util;

import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class ClassUtil<T, K> {

    /**
     * 해당 메서드는 orig 객체의 필드 값을 dest 값으로 복사해주는 메서드.
     * null 값도 복사함.
     *
     * @param dest 목적 객체
     * @param orig 원본 객체
     */
    public static void copyProperties(Object dest, Object orig) {
        try {
            BeanUtils.copyProperties(orig, dest);
        } catch (Exception e) {
            // FIXME : 제대로 된 에러 클래스를 사용
            // throw new BaseException("500", "Internal Error copyProperties");
        }
    }

    /**
     * 해당 메서드는 orig 객체의 필드 값을 dest 값으로 복사해주는 메서드.
     * null 값은 제외.
     *
     * @param destClass 목적 객체 클래스
     * @param orig 원본 객체
     */
    public static <T> T copyPropertiesWithCustom(Class destClass, Object orig, boolean isCalSuper) {
        try {
            Object dest = destClass.newInstance();
            copyPropertiesWithCustom(dest, orig, null, isCalSuper);
            return (T) dest;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 해당 메서드는 origs 객체의 필드 값을 dest 값으로 복사해주는 메서드.
     * null 값은 제외.
     *
     * @param destClass 목적 객체 클래스
     * @param origs     원본 목록 객체
     */
    public static <T> List<T> copyPropertiesWithCustom(Class<T> destClass, List origs) {
        List<T> result = new ArrayList<>();

        for (Object orig : origs) {
            T dest = copyPropertiesWithCustom(destClass, orig);
            result.add(dest);
        }

        return result;
    }

    /**
     * 해당 메서드는 orig 객체의 필드 값을 dest 값으로 복사해주는 메서드.
     * null 값은 제외.
     *
     * @param destClass 목적 객체 클래스
     * @param orig 원본 객체
     */
    public static <T> T copyPropertiesWithCustom(Class<T> destClass, Object orig) {
        try {
            Object dest = destClass.newInstance();
            copyPropertiesWithCustom(dest, orig, null);
            return (T) dest;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 해당 메서드는 orig 객체의 필드 값을 dest 값으로 복사해주는 메서드.
     * null 값은 제외.
     *
     * @param destClass 목적 객체 클래스
     * @param orig 원본 객체
     * @param conversion 프로퍼티 변경 객체
     * @param isCalSuper 부모 객체 계산 여부
     * @return
     */
    public static <T> T copyPropertiesWithCustom(Class destClass, Object orig, PropertyConversion conversion, boolean isCalSuper) {
        try {
            Object dest = destClass.newInstance();
            copyPropertiesWithCustom(dest, orig, conversion, isCalSuper);
            return (T) dest;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 해당 메서드는 orig 객체의 필드 값을 dest 값으로 복사해주는 메서드.
     * null 값은 제외.
     *
     * @param destClass  목적 객체 클래스
     * @param orig       원본 객체
     * @param conversion 프로퍼티 변경 객체
     */
    public static <T> T copyPropertiesWithCustom(Class destClass, Object orig, PropertyConversion conversion) {
        try {
            Object dest = destClass.newInstance();
            copyPropertiesWithCustom(dest, orig, conversion, false);
            return (T) dest;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 해당 메서드는 orig 객체의 필드 값을 dest 값으로 복사해주는 메서드.
     * null 값은 제외.
     *
     * @param dest 목적 객체
     * @param orig 원본 객체
     */
    public static void copyPropertiesWithCustom(Object dest, Object orig) {
        copyPropertiesWithCustom(dest, orig, null);
    }

    /**
     * 해당 메서드는 orig 객체의 필드 값을 dest 값으로 복사해주는 메서드.
     * null 값은 제외.
     *
     * @param dest 목적 객체
     * @param orig 원본 객체
     * @param conversion 프로퍼티 변경 객체
     */
    public static void copyPropertiesWithCustom(Object dest, Object orig, PropertyConversion conversion) {
        copyPropertiesWithCustom(dest, orig, conversion, false);
    }

    public static void copyPropertiesWithCustom(Object dest, Object orig, boolean isCalSuper) {
        copyPropertiesWithCustom(dest, orig, null, isCalSuper, false);
    }

    public static void copyPropertiesWithCustom(Object dest, Object orig, PropertyConversion conversion, boolean isCalSuper) {
        copyPropertiesWithCustom(dest, orig, conversion, isCalSuper, false);
    }


    /**
     * 해당 메서드는 orig 객체의 필드 값을 dest 값으로 복사해주는 메서드.
     * null 값은 제외.
     *
     * @param dest        목적 객체
     * @param orig        원본 객체
     * @param conversion  프로퍼티 변경 객체
     * @param isCalSuper  부모 객체 계산 여부
     * @param includeNull null 포함 여부
     */
    public static void copyPropertiesWithCustom(Object dest, Object orig, PropertyConversion conversion, boolean isCalSuper, boolean includeNull) {

        if(isEmptyObjects(dest, orig))
            return;

        // orig ==복사==> dest
        copyPropertiesWithCustom(dest, dest.getClass(), orig, orig.getClass(), conversion, includeNull);

        // 부모 클래스에 대한 계산 여부를 확인
        if(isCalSuper) {
            Class origSuperClass =  orig.getClass().getSuperclass();
            Class destSuperClass = dest.getClass().getSuperclass();

            if(origSuperClass != Object.class){
                // orig 부모 ==복사==> dest
                copyPropertiesWithCustom(dest, dest.getClass(), orig, origSuperClass, conversion, includeNull);

                if(destSuperClass != Object.class){
                    // orig 부모 ==복사==> dest 부모
                    copyPropertiesWithCustom(dest, destSuperClass, orig, origSuperClass, conversion, includeNull);
                }
            }

            if(destSuperClass != Object.class){
                // orig ==복사==> dest 부모
                copyPropertiesWithCustom(dest, destSuperClass, orig, orig.getClass(), conversion, includeNull);
            }
        }
    }


    /**
     * 해당 메서드는 orig 객체의 필드 값을 dest 값으로 복사해주는 메서드.
     * null 값은 제외.
     *
     * @param dest 목적 객체
     * @param orig 원본 객체
     * @param conversion 프로퍼티 변경 객체
     */
    private static void copyPropertiesWithCustom(Object dest, Class destClass, Object orig, Class origClass, PropertyConversion conversion, boolean includeNull) {

        try {
            Field[] origFields = origClass.getDeclaredFields();

            for (Field origField : origFields) {

                if (!origField.isAccessible())
                    origField.setAccessible(true);

                Object origValue = origField.get(orig);

                if (conversion != null && conversion.exist(origField.getName())) {
                    // 필드 지정

                    List<PropertyConverter> converters = conversion.getPropertyConverter(origField.getName());

                    for (PropertyConverter converter : converters) {

                        if (converter.isIgnore())
                            continue;

                        // 해당 converter에 includeNull 메서드를 커스텀 하였는가?
                        if(converter.includeNull() != null){
                            // 우선순위가 더 높은 개별 Null 포함 여부로 확인
                            if (origValue == null && converter.includeNull() == false)
                                continue;
                        }else {
                            // 공통 includeNull 설정으로 Null 포함 여부 확인
                            if (origValue == null && includeNull == false)
                                continue;
                        }

                        // 기본 값은 orig의 필드 네임
                        String destFieldName = origField.getName();

                        // 지정 필드가 있을 경우 지정필드 셋팅
                        if(converter.existIndicateField())
                            destFieldName = converter.getIndicateField();

                        Field destField = getFieldWithIsAccessible(destFieldName, destClass);

                        // dest에 필드가 존재하지 않으면 패스
                        if(destField == null)
                            continue;

                        destField.set(dest, converter.converter(origValue, orig));
                    }

                }else if(conversion != null && conversion.exist(origField.getType())){
                    // 타입 지정

                    PropertyConverter converter = conversion.getPropertyConverter(origField.getType());

                    if (converter.isIgnore())
                        continue;

                    // 해당 converter에 includeNull 메서드를 커스텀 하였는가?
                    if(converter.includeNull() != null){
                        // 우선순위가 더 높은 개별 Null 포함 여부로 확인
                        if (origValue == null && converter.includeNull() == false)
                            continue;
                    }else {
                        // 공통 includeNull 설정으로 Null 포함 여부 확인
                        if (origValue == null && includeNull == false)
                            continue;
                    }

                    Field destField = getFieldWithIsAccessible(origField.getName(), destClass);

                    // dest에 필드가 존재하지 않으면 패스
                    if(destField == null)
                        continue;

                    destField.set(dest, converter.converter(origValue, orig));
                }else {
                    // 그냥 복사

                    if (origValue == null && includeNull == false)
                        continue;

                    Field destField = getFieldWithIsAccessible(origField.getName(), destClass);

                    // dest에 필드가 존재하지 않으면 패스
                    if(destField == null)
                        continue;

                    if(destField.getType() == origField.getType()) {
                        destField.set(dest, origValue);
                    }
                }
            }
        } catch (Exception e) {
            // FIXME : 제대로 된 에러 클래스를 사용
            // throw new AdminApiException(AdminApiExceptionType.FUNCTION_FAIL, "copyPropertiesWithCustom", e);
        }
    }

    private static Field getFieldWithIsAccessible(String fieldName, Class claz){
        Field field = null;
        try {
            field = claz.getDeclaredField(fieldName);
            if (!field.isAccessible())
                field.setAccessible(true);
        } catch (NoSuchFieldException e) {
        }
        return field;
    }

    /**
     * 해당 값들이 null인지 조회
     *
     * @param objects 검사할 객체들
     * @return 검사결과 (True : 1개 이상 Null이 존재 , False : 전부 Null이 아님)
     */
    public static boolean isEmptyObjects(Object... objects) {
        for (Object object : objects) {
            if (object == null)
                return true;
        }
        return false;
    }

    /**
     * List 타입의 객체가 null 이거나 size가 0인 것을 체크
     *
     * @param list 검사 객체
     * @return 검사결과
     */
    public static boolean isEmptyObject(List list) {

        if (list == null)
            return true;

        if (list.size() == 0)
            return true;

        return false;
    }

    /**
     * Map 타입의 객체가 null 이거나 size가 0인 것을 체크
     *
     * @param map 검사 객체
     * @return 검사결과
     */
    public static boolean isEmptyObject(Map map) {

        if (map == null)
            return true;

        if (map.size() == 0)
            return true;

        return false;
    }

    public static Map<String, Method> getDeclaredMethods(Class clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        Map<String, Method> result = new HashMap<>();
        for (Method method :
                methods) {
            result.put(method.getName(), method);
        }
        return result;
    }

    public static Map<String, Method> getDeclaredMethodForName(Class clazz, String methodName) {
        Method[] methods = clazz.getDeclaredMethods();
        Map<String, Method> result = new HashMap<>();
        for (Method method :
                methods) {
            if (method.getName().equals(methodName))
                result.put(method.getName(), method);
        }
        return result;
    }

    /**
     * 해당 클래스의 필드 네임을 전부 조회하는 메서드
     *
     * @param clazz 클래스 정보
     * @return 필드 네임
     */
    public static Set<String> getDeclaredFieldName(Class clazz) {

        Set<String> names = new HashSet();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            names.add(field.getName());
        }

        return names;
    }

    public static Field getDeclaredField(Class clazz, String name) {
        try {
            return clazz.getDeclaredField(name);
        } catch (NoSuchFieldException e) {
            return null;
        }
    }

    /**
     * 비교군 목록에 비교 기준 객체가 equals 메서드로 완전 일치하는지 확인
     *
     * @param subject 비교 기준 객체
     * @param objects    비교군 목록
     * @return 포함 여부
     */
    public static boolean equals(Object subject, Object... objects) {

        if (objects == null || objects.length == 0 || subject == null)
            return false;

        for (Object object : objects) {
            if (subject.equals(object))
                continue;
            else
                return false;
        }

        return true;
    }

    /**
     * 비교군 목록에 비교 기준 객체가 equals 메서드로 완전 일치하는지 확인
     *
     * @param subject 비교 기준 객체
     * @param objects 비교군 목록
     * @return 포함 여부
     */
    public static boolean equals(Object subject, List objects) {

        if (isEmptyObjects(subject, objects))
            return false;

        return equals(subject, objects.toArray());
    }

    /**
     * 비교군 목록에 비교 기준 객체가 equals 메서드로 부분 일치하는지 확인
     *
     * @param subject 비교 기준 객체
     * @param objs    비교군 목록
     * @return 포함 여부
     */
    public static boolean contains(Object subject, Object... objs) {

        if (objs == null || objs.length == 0 || subject == null)
            return false;

        for (Object object : objs) {
            if (subject.equals(object))
                return true;
        }

        return false;
    }

    public static abstract class PropertyConversion {

        public PropertyConversion (){
            setConverter();
        }

        private Map<Class, PropertyConverter> typeConverters = new HashMap<>();
        private Map<String, List<PropertyConverter>> fieldConverters = new HashMap<>();

        public abstract void setConverter();

        public void addConverter(Class clazz, PropertyConverter converter) {
            typeConverters.put(clazz, converter);
        }

        public void addConverter(String field, PropertyConverter converter) {
            if(!fieldConverters.containsKey(field))
                fieldConverters.put(field, new ArrayList<>());
            fieldConverters.get(field).add(converter);
        }

        public boolean exist(Class clazz) {
            if (typeConverters.containsKey(clazz))
                return true;

            return false;
        }

        public boolean exist(String field) {
            if (fieldConverters.containsKey(field))
                return true;

            return false;
        }

        public PropertyConverter getPropertyConverter(Class clazz) {
            return typeConverters.get(clazz);
        }

        public List<PropertyConverter> getPropertyConverter(String field) {
            return fieldConverters.get(field);
        }

    }

    public static abstract class PropertyConverter<IT, OT> {

        public String getIndicateField(){
            return null;
        }

        public boolean isIgnore() {
            return false;
        }

        boolean existIndicateField(){
            if(!StringUtils.hasText(getIndicateField()))
                return true;
            else
                return false;
        }

        public Boolean includeNull(){
            return null;
        }

        public OT converter(IT value, Object origin) {
            return converter(value);
        }

        public OT converter(IT value) {
            return (OT) value;
        }
    }

}