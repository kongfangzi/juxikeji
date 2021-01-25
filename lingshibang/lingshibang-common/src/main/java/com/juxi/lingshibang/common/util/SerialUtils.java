package com.juxi.lingshibang.common.util;

import java.util.UUID;

/**
 * @author hongqiang.chai
 * 用来生成接入图像数据的唯一编码
 */
public class SerialUtils {
    public synchronized static String initCode(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
