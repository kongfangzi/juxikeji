package com.juxi.lingshibang.common.des;


import com.juxi.lingshibang.common.des.base.BaseEncryptSys;
import com.juxi.lingshibang.common.util.StringUtil;

import java.io.UnsupportedEncodingException;

/**
 * @version 1.0
 * @date 2017-05-09
 */
public class EncryptDESCBC extends BaseEncryptSys {
    private static final String MODE = "DES/CBC/PKCS5Padding";
    private static final String KEY_MODE = "DES";
    private static final int KEY_LEN = 8;

    public String randomKey() {
        return super.randomKey(KEY_LEN);
    }

    @Override
    public byte[] encrypt(byte[] data, String key, String iv) {
        return encrypt(data, key, iv, MODE, KEY_MODE);
    }

    @Override
    public byte[] decrypt(byte[] data, String key, String iv) {
        return decrypt(data, key, iv, MODE, KEY_MODE);
    }

    @Override
    public String encryptToBase64(byte[] data, String key, String iv) {
        return encryptToBase64(data, key, iv, MODE, KEY_MODE);
    }

    public String encryptStrToBase64(String data, String key, String iv) {
        if (StringUtil.isNotBlank(data)) {
            try {
                return encryptToBase64(data.getBytes("utf-8"), key, iv, MODE, KEY_MODE);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    @Override
    public byte[] decryptFromBase64(String str, String key, String iv) {
        return decryptFromBase64(str, key, iv, MODE, KEY_MODE);
    }

    public String decryptStrFromBase64(String str, String key, String iv) {
        try {
            return new String(decryptFromBase64(str, key, iv, MODE, KEY_MODE), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
