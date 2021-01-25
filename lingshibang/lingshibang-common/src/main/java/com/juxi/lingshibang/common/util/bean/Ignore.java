package com.juxi.lingshibang.common.util.bean;

import java.lang.annotation.*;

/**
 * 跳过
 * @version 1.0
 * @date 2016-10-18
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
@Inherited
public @interface Ignore {
}
