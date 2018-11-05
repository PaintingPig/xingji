package com.personal.xingji.xingji.runtimeanntation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)//运行时注解
@Target(ElementType.FIELD)
@Documented//表示加入到注释文档中
public @interface AnnotationTest {
    String value() default "default";
    int address() default 8080;
}
