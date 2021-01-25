package com.juxi.lingshibang.common.des;

import com.alibaba.fastjson.JSONObject;
import com.juxi.lingshibang.common.util.EncodeUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * Hello world!
 *
 */
@Slf4j
public class App 
{
    public static void main( String[] args )
    {
        log.info( "Hello World!" );

        String data = "Xe//XCHbHBJIfNlx3AP9uRoKJOoF9SgX+MXlhPB8m/RwYo7+0PArI2yy81TTHuEn7LI6pt54nG1s2H/gvX6mDAOlP91fhalJ+0rWcEEGi7VXhVxTWFYFBc3YG3F7Q7M4";
        EncryptDESCBC encrypt = new EncryptDESCBC();

        log.info(EncodeUtil.encodeBytes(encrypt.decryptFromBase64(data, "ijielazz", "ijielazz"), ""));

        JSONObject obj = new JSONObject();
        obj.put("userId", 1178);

        log.info(encrypt.encryptStrToBase64(obj.toJSONString(), "ijielazz", "ijielazz"));

        log.info(EncryptEasy.encodeBase64(obj.toJSONString(), "ijielamc"));
    }
}
