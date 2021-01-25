package com.juxi.lingshibang.common.des;

import com.juxi.lingshibang.common.des.base.BaseEncryptSys;
import com.juxi.lingshibang.common.util.ArrayUtil;
import com.juxi.lingshibang.common.util.HashUtil;
import com.juxi.lingshibang.common.util.StringUtil;
import org.apache.commons.lang3.tuple.Pair;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @version 1.0
 * @date 2017-05-09
 */
public class EncryptDES2CBC extends BaseEncryptSys {
    private static final String MODE = "DES/CBC/PKCS5Padding";
    private static final String KEY_MODE = "DES";
    private static final int KEY_LEN = 8;

    public String randomKey() {
        return super.randomKey(KEY_LEN);
    }

    protected Pair<Cipher, Cipher> getCipher(byte[] key, byte[] iv, String mode, String keyMode) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(key, keyMode);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            Cipher enCipher = Cipher.getInstance(mode);
            enCipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            Cipher deCipher = Cipher.getInstance(mode);
            deCipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            Pair p = Pair.of(enCipher, deCipher);
            return p;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException
                | InvalidAlgorithmParameterException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }
    protected Cipher getEncryptCipher(byte[] key, byte[] iv, String mode, String keyMode) {
        Pair<Cipher, Cipher> p = getCipher(key, iv, mode, keyMode);
        if (p != null) {
            return p.getLeft();
        }
        return null;
    }
    protected Cipher getDecryptCipher(byte[] key, byte[] iv, String mode, String keyMode) {
        Pair<Cipher, Cipher> p = getCipher(key, iv, mode, keyMode);
        if (p != null) {
            return p.getRight();
        }
        return null;
    }

    @Override
    protected byte[] encrypt(byte[] data, String key, String iv, String mode, String keyMode) {
        try {
            byte[] mKey = HashUtil.md5Bytes(key);
            byte[] key1 = ArrayUtil.subarray(mKey, 0, 8);
            byte[] key2 = ArrayUtil.subarray(mKey, 8, 16);

            byte[] mIv = HashUtil.md5Bytes(iv);
            byte[] iv1 = ArrayUtil.subarray(mIv, 0, 8);
            byte[] iv2 = ArrayUtil.subarray(mIv, 8, 16);

            byte[] data1 = null;
            Cipher cipher = getEncryptCipher(key1, iv1, mode, keyMode);
            if (cipher != null) {
                data1 = cipher.doFinal(data);
            } else {
                data1 = data;
            }
            Cipher cipher2 = getEncryptCipher(key2, iv2, mode, keyMode);
            if (cipher2 != null) {
                return cipher2.doFinal(data1);
            }
        } catch (BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected byte[] decrypt(byte[] data, String key, String iv, String mode, String keyMode) {
        try {
            byte[] mKey = HashUtil.md5Bytes(key);
            byte[] key1 = ArrayUtil.subarray(mKey, 0, 8);
            byte[] key2 = ArrayUtil.subarray(mKey, 8, 16);

            byte[] mIv = HashUtil.md5Bytes(iv);
            byte[] iv1 = ArrayUtil.subarray(mIv, 0, 8);
            byte[] iv2 = ArrayUtil.subarray(mIv, 8, 16);

            byte[] data1 = null;
            Cipher cipher = getDecryptCipher(key2, iv2, mode, keyMode);
            if (cipher != null) {
                data1 = cipher.doFinal(data);
            }
            Cipher cipher1 = getDecryptCipher(key1, iv1, mode, keyMode);
            if (cipher1 != null) {
                return cipher1.doFinal(data1);
            }
        } catch (BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
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
