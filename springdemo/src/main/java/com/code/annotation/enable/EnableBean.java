package com.code.annotation.enable;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * spring Enablexx 注解
 * demo
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({MyImportSelect.class})
public @interface EnableBean {
}
