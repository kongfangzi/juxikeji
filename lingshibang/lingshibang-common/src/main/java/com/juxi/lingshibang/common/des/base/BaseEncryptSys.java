package com.juxi.lingshibang.common.des.base;

import com.juxi.lingshibang.common.util.EncodeUtil;
import com.juxi.lingshibang.common.util.RandomUtil;
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
public abstract class BaseEncryptSys {
    public abstract byte[] encrypt(byte[] data, String key, String iv);

    public abstract byte[] decrypt(byte[] data, String key, String iv);

    public abstract String encryptToBase64(byte[] data, String key, String iv);

    public abstract byte[] decryptFromBase64(String str, String key, String iv);

    protected String randomKey(int len) {
        return RandomUtil.randomAscii(len);
    }

    protected Pair<Cipher, Cipher> getCipher(String key, String iv, String mode, String keyMode) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(generateKeySpec(key), keyMode);
            IvParameterSpec ivSpec = new IvParameterSpec(generateIvSpec(iv));
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

    protected byte[] generateKeySpec(String key) {
        try {
            return key.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected byte[] generateIvSpec(String iv) {
        try {
            return iv.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected Cipher getEncryptCipher(String key, String iv, String mode, String keyMode) {
        Pair<Cipher, Cipher> p = getCipher(key, iv, mode, keyMode);
        if (p != null) {
            return p.getLeft();
        }
        return null;
    }

    protected Cipher getDecryptCipher(String key, String iv, String mode, String keyMode) {
        Pair<Cipher, Cipher> p = getCipher(key, iv, mode, keyMode);
        if (p != null) {
            return p.getRight();
        }
        return null;
    }

    protected byte[] encrypt(byte[] data, String key, String iv, String mode, String keyMode) {
        try {
            Cipher cipher = getEncryptCipher(key, iv, mode, keyMode);
            if (cipher != null) {
                return cipher.doFinal(data);
            }
        } catch (BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected byte[] decrypt(byte[] data, String key, String iv, String mode, String keyMode) {
        try {
            Cipher cipher = getDecryptCipher(key, iv, mode, keyMode);
            if (cipher != null) {
                return cipher.doFinal(data);
            }
        } catch (BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected String encryptToBase64(byte[] data, String key, String iv, String mode, String keyMode) {
        return EncodeUtil.base64Encode(encrypt(data, key, iv, mode, keyMode));
    }

    protected byte[] decryptFromBase64(String str, String key, String iv, String mode, String keyMode) {
        return decrypt(EncodeUtil.base64Decode(str), key, iv, mode, keyMode);
    }
}
