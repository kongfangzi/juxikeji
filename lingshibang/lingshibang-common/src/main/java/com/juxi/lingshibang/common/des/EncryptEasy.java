package com.juxi.lingshibang.common.des;


import com.juxi.lingshibang.common.util.EncodeUtil;
import com.juxi.lingshibang.common.util.StringUtil;

import java.io.UnsupportedEncodingException;

/**
 * 简单加密编码
 * @date 2017-08-29
 */
public class EncryptEasy {

    public static byte[] encode(String str, String key) {
        if (StringUtil.isBlank(str) || StringUtil.isBlank(key)) {
            return null;
        }
        int keyHash = key.hashCode();
        try {
            // 加密编码
            byte[] bb = str.getBytes("utf-8");
            for (int i = 0; i < bb.length; i++) {
                bb[i] = (byte) (bb[i] ^ keyHash);   // 对每一个byte进行异或
            }
            return bb;     // 对结果进行base64
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String encodeBase64(String str, String key) {
        return EncodeUtil.base64Encode(encode(str, key));     // 对结果进行base64
    }

    public static String decode(byte[] bytes, String key) {
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        int keyHash = key.hashCode();
        // 解密编码
        byte[] bb = bytes;     // 对字符串base解码
        for (int i = 0; i < bb.length; i++) {
            bb[i] = (byte) (bb[i] ^ keyHash);   // 对每一个byte进行异或
        }
        try {
            return new String(bb, "utf-8").trim();                  // 获得原json字符串
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decodeBase64(String str, String key) {
        if (StringUtil.isBlank(str) || StringUtil.isBlank(key)) {
            return null;
        }
        // 解密编码
        byte[] bb = EncodeUtil.base64Decode(str);     // 对字符串base解码
        return decode(bb, key);
    }
}
