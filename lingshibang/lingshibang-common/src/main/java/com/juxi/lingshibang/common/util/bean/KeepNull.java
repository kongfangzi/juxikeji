package com.juxi.lingshibang.common.util.bean;

import java.lang.annotation.*;

/**
 * 当属性是Integer Double Long Float Boolean是有效
 * @version 1.0
 * @date 2016-10-18
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
public @interface KeepNull {
}
