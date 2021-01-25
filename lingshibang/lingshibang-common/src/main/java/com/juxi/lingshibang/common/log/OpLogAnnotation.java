package com.juxi.lingshibang.common.log;

import com.juxi.lingshibang.common.enums.DeviceEnum;
import com.juxi.lingshibang.common.enums.ModuleEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface OpLogAnnotation {

    /**
     * 操作模块
     */
    ModuleEnum module();

    /**
     * 操作来源(PC端，移动端)
     */
    DeviceEnum source();

    /**
     * 操作内容
     */
    String desc() default "";
}
