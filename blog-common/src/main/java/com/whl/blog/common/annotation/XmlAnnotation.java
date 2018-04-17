package com.whl.blog.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by whling on 2017/6/10.
 * 获取xml的key
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface XmlAnnotation {
    String value();

    String type() default XmlFieldType.STRING;

    String subType() default XmlFieldType.OBJECT;

    String className() default "";

    public class XmlFieldType {
        public static final String INT = "int";
        public static final String DOUBLE = "Double";
        public static final String LONG = "Long";
        public static final String BOOLEAN = "boolean";
        public static final String STRING = "string";
        public static final String OBJECT = "object";
        public static final String LIST = "list";
        public static final String BIGDECIMAL = "bigdecimal";
        public static final String TIMESTAMP = "timestamp";
    }
}
