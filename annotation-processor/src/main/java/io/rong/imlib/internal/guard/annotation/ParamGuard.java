package io.rong.imlib.internal.guard.annotation;

import io.rong.imlib.enums.ErrorCode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 参数检查注解，主要作用于 Object 子类和 String 等可以写 != null 语句的对象
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface ParamGuard {
    ErrorCode value();
}
