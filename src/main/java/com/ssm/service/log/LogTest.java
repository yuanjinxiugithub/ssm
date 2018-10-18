package com.ssm.service.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,ElementType.METHOD})//定义注解的使用地方
@Retention(RetentionPolicy.RUNTIME)//在运行时执行
public @interface LogTest {
   public LogType getLogType() default LogType.type1;
}
