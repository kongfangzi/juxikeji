package com.juxi.lingshibang.common.des;

import com.juxi.lingshibang.common.util.EncodeUtil;
import com.juxi.lingshibang.common.util.StringUtil;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @date 2017-12-13
 */
public class EncryptAES {
    private static final String MODE = "AES";
    private static final String KEY_MODE = "AES";

    private boolean upper = true;

    public EncryptAES() {
        this.upper = true;
    }

    public EncryptAES(boolean upper) {
        this.upper = upper;
    }

    private Cipher getEncryptCipher(String key) {
        try {
            byte[] sKey = EncodeUtil.decodeBytes(key);
            byte[] bKey = new byte[16];
            System.arraycopy(sKey, 0, bKey, 0, Math.min(sKey.length, bKey.length));
            SecretKeySpec keySpec = new SecretKeySpec(bKey, "AES");
            Cipher enCipher = Cipher.getInstance(MODE);
            enCipher.init(Cipher.ENCRYPT_MODE, keySpec);
            return enCipher;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Cipher getDecryptCipher(String key) {
        try {
            byte[] sKey = EncodeUtil.decodeBytes(key);
            byte[] bKey = new byte[16];
            System.arraycopy(sKey, 0, bKey, 0, Math.min(sKey.length, bKey.length));
            SecretKeySpec keySpec = new SecretKeySpec(bKey, "AES");
            Cipher deCipher = Cipher.getInstance(MODE);
            deCipher.init(Cipher.DECRYPT_MODE, keySpec);

            return deCipher;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String encrypt(String str, String key) {
        try {
            Cipher cipher = getEncryptCipher(key);
            if (cipher != null) {
                String s = EncodeUtil.toHex(cipher.doFinal(EncodeUtil.decodeBytes(str)));
                if (StringUtil.isNotBlank(s)) {
                    if (upper) {
                        return StringUtil.upperCase(s);
                    } else {
                        return StringUtil.lowerCase(s);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String decrypt(String str, String key) {
        try {
            Cipher cipher = getDecryptCipher(key);
            if (cipher != null) {
                return EncodeUtil.encodeBytes(cipher.doFinal(EncodeUtil.fromHex(str)), "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
