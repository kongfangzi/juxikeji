package com.juxi.lingshibang.common.des;


import com.juxi.lingshibang.common.des.base.BaseEncryptSys;
import com.juxi.lingshibang.common.util.HashUtil;

/**
 * @version 1.0
 * @date 2017-05-09
 */
public class EncryptAESCBC extends BaseEncryptSys {
    private static final String MODE = "AES/CBC/PKCS5Padding";
    private static final String KEY_MODE = "AES";
    private static final int KEY_LEN = 16;
    private boolean md5 = false;

    public EncryptAESCBC() {

    }
    public EncryptAESCBC(boolean md5) {
        this.md5 = md5;
    }

    public String randomKey() {
        return super.randomKey(KEY_LEN);
    }

    @Override
    protected byte[] generateKeySpec(String key) {
        if (md5) {
            return HashUtil.md5Bytes(key);
        }
        return super.generateKeySpec(key);
    }

    @Override
    protected byte[] generateIvSpec(String iv) {
        if (md5) {
            return HashUtil.md5Bytes(iv);
        }
        return super.generateIvSpec(iv);
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

    @Override
    public byte[] decryptFromBase64(String str, String key, String iv) {
        return decryptFromBase64(str, key, iv, MODE, KEY_MODE);
    }
}
