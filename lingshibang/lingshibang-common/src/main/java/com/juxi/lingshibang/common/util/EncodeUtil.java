package com.juxi.lingshibang.common.util;

import com.google.common.base.Utf8;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringEscapeUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.SecureRandom;

/**
 * @version 1.0
 * @date 2017-03-22
 */
@Slf4j
public final class EncodeUtil extends StringEscapeUtils {
    private static final String HEX_STRING = "0123456789abcdef";
    private static final char[] HEX_DIGITS = HEX_STRING.toCharArray();

    private EncodeUtil() {}

    public static String base64DecodeStr(String str) {
        return encodeBytes(Base64.decodeBase64(str), "");
    }

    public static byte[] base64Decode(String str) {
        return Base64.decodeBase64(str);
    }

    public static String base64EncodeStr(String str) {
        return Base64.encodeBase64String(decodeBytes(str));
    }

    public static String base64Encode(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }

    public static String base64EncodeStrForUrl(String str) {
        return urlEncodeStr(base64EncodeStr(str));
    }

    public static String base64DecodeStrFromUrl(String str) {
        return base64DecodeStr(urlDecodeStr(str));
    }

    public static String toHex(byte[] bytes) {
        StringBuilder ret = new StringBuilder(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
            ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
        }
        return ret.toString();
    }

    public static byte[] fromHex(String str) {
        if (StringUtil.isBlank(str)) {
            return null;
        }
        str = str.toLowerCase();
        int len = str.length() / 2;
        byte[] bytes = new byte[len];
        char[] hexChars = str.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            bytes[i] = (byte) (Character.getNumericValue(hexChars[pos]) << 4
                    | Character.getNumericValue(hexChars[pos + 1]));
        }
        return bytes;
    }

    /**
     * 字符串编码工具,用来转换url中的乱码情况
     * @param str
     * @return
     */
    public static String encodeStr(String str) {
        try {
            return new String(str.getBytes("ISO_8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String encodeBytes(byte[] bytes, String defVal) {
        if (bytes == null) {
            return defVal;
        }
        String str = null;
        try {
            str = new String(bytes, "utf-8");
        } catch (UnsupportedEncodingException e) {
            str = defVal;
        }
        return str;
    }

    public static String encodeBytesWithURLEncode(byte[] bytes, String defVal) {
        String str = encodeBytes(bytes, defVal);
        if (StringUtil.isNotBlank(str)) {
            try {
                str = URLEncoder.encode(str, "utf-8");
            } catch (UnsupportedEncodingException e) {
                str = defVal;
            }
        }
        return str;
    }

    public static byte[] decodeBytesObj(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof String) {
            return decodeBytes((String) obj);
        }
        return decodeBytes(obj.toString());
    }

    public static byte[] decodeBytes(String str) {
        byte[] bytes = null;
        if (StringUtil.isNotBlank(str)) {
            try {
                bytes = str.getBytes("utf-8");
            } catch (UnsupportedEncodingException e) {
                bytes = null;
            }
        }
        return bytes;
    }

    public static int utf8EncodedLength(String str) {
        if (StringUtil.isBlank(str)) {
            return 0;
        }
        return Utf8.encodedLength(str);
    }

    public static String getEncryptionToken(String token) {
        for (int i = 0; i < 6; i++) {
            token = base64EncodeStr(token);
        }
        return token;
    }

    public static String getDecryptToken(String encryptionToken) {
        for (int i = 0; i < 6; i++) {
            encryptionToken = base64DecodeStr(encryptionToken);
        }
        return encryptionToken;
    }

    public static String noHtml(String s) {
        if (StringUtil.isBlank(s)) {
            return "";
        }
        return s.replaceAll("<[.[^<]]*>", "");
    }

    public static String transHtml(String s) {
        if (StringUtil.isBlank(s)) {
            return "";
        }
        return s.replace("<", "&lt;").replace(">", "&gt;");
    }

    public static boolean hasSqlWildcard(String str) {
        if (str != null) {
            return !StringUtil.equals(str, str.replaceAll("[*%]*", ""));
        }
        return false;
    }

    /**
     * URL 编码, Encode默认为UTF-8.
     *
     * 转义后的URL可作为URL中的参数
     */
    public static String urlDecodeStr(String str) {
        if (StringUtil.isNotBlank(str)) {
            try {
                str = URLDecoder.decode(str, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            str = encodeStr(str);
        }
        return str;
    }
    /**
     * URL 解码, Encode默认为UTF-8. 转义后的URL可作为URL中的参数
     */
    public static String urlEncodeStr(String str) {
        if (StringUtil.isNotBlank(str)) {
            try {
                str = URLEncoder.encode(str, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return str;
    }

    public static String strUrlPlus(String p) {
        if (StringUtil.isNotBlank(p)) {
            if (StringUtil.contains(p, " ")) {
                p = StringUtil.replaceAll(p, " ", "+");
            }
        }
        return p;
    }

    public static String encrypt(String content, String key) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(key.getBytes());
            kgen.init(128, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] result = cipher.doFinal(content.getBytes("utf-8"));
            return new String(new BASE64Encoder().encode(result));
        } catch (Exception e) {
            log.error("加密异常", e);
        }
        return null;
    }

    public static String decrypt(String content, String key) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(key.getBytes());
            kgen.init(128, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] byteContent = new BASE64Decoder().decodeBuffer(content);
            byte[] result = cipher.doFinal(byteContent);
            return new String(result, "utf-8");
        } catch (Exception e) {
            log.error("解密异常", e);
        }
        return null;
    }

    /**
     * Xml转码，将字符串转码为符合XML1.1格式的字符串.
     *
     * 比如 "bread" & "butter" 转化为 &quot;bread&quot; &amp; &quot;butter&quot;
     */
    public static String escapeXML(String xml) {
        return escapeXml11(xml);
    }

    /**
     * Html转码，将字符串转码为符合HTML4格式的字符串.
     *
     * 比如 "bread" & "butter" 转化为 &quot;bread&quot; &amp; &quot;butter&quot;
     */
    public static String escapeHtml(String html) {
        return escapeHtml4(html);
    }

    /**
     * Html解码，将HTML4格式的字符串转码解码为普通字符串.
     *
     * 比如 &quot;bread&quot; &amp; &quot;butter&quot;转化为"bread" & "butter"
     */
    public static String unescapeHtml(String html) {
        return unescapeHtml4(html);
    }
}
