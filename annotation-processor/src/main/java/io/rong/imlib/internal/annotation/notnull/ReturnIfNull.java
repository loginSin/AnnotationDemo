package io.rong.imlib.internal.annotation.notnull;

import io.rong.imlib.enums.ErrorCode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface ReturnIfNull {
    ErrorCode value();
}
