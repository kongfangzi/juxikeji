package com.juxi.lingshibang.common.util;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.SecureRandom;

/**
 * @version 1.0
 * @date 2017-05-03
 */
public final class HashUtil {
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final char[] HEX_DIGITS = "0123456789abcdef".toCharArray();
    private static final char[] CHAR_ARRAY = "_-0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    public static final String md5(String str) {
        return hash("MD5", str);
    }

    public static final String MD5(Charset charset, String... str) {
        String s = AryUtil.Join(str);
        return MD(s.getBytes(charset), "MD5");
    }

    public static final String MD5(String key, Charset charset, String... str) {
        String s = AryUtil.Join(str) + key;
        return MD(s.getBytes(charset), "MD5");
    }

    public static final byte[] md5Bytes(String str) {
        return hashBytes("MD5", str);
    }

    public static final String sha1(String str) {
        return hash("SHA-1", str);
    }

    public static final String sha256(String str) {
        return hash("SHA-256", str);
    }

    public static final String sha384(String str) {
        return hash("SHA-384", str);
    }

    public static final String sha512(String str) {
        return hash("SHA-512", str);
    }

    public static String hash(String algorithm, String srcStr) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            byte[] bytes = md.digest(srcStr.getBytes("utf-8"));
            return toHex(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] hashBytes(String algorithm, String srcStr) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            return md.digest(srcStr.getBytes("utf-8"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String toHex(byte[] bytes) {
        StringBuilder ret = new StringBuilder(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
            ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
        }
        return ret.toString();
    }

    private static String MD(byte[] stream, String type) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance(type);
            md.update(stream);
            return HexCoder(md.digest());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String HexCoder(byte[] stream) {
        StringBuilder sb = new StringBuilder();
        for (byte b : stream) {
            String ss = "00" + Integer.toHexString(b & 0xff);
            sb.append(ss.substring(ss.length() - 2));
        }
        return sb.toString();
    }

    /**
     * md5 128bit 16bytes
     * sha1 160bit 20bytes
     * sha256 256bit 32bytes
     * sha384 384bit 48bytes
     * sha512 512bit 64bytes
     */
    public static final String generateSaltForMd5() {
        return generateSalt(16);
    }

    public static final String generateSaltForSha1() {
        return generateSalt(20);
    }

    public static final String generateSaltForSha256() {
        return generateSalt(32);
    }

    public static final String generateSaltForSha384() {
        return generateSalt(48);
    }

    public static final String generateSaltForSha512() {
        return generateSalt(64);
    }

    public static String generateSalt(int saltLength) {
        StringBuilder salt = new StringBuilder();
        for (int i = 0; i < saltLength; i++) {
            salt.append(CHAR_ARRAY[RANDOM.nextInt(CHAR_ARRAY.length)]);
        }
        return salt.toString();
    }

    public static boolean slowEquals(byte[] a, byte[] b) {
        if (a == null || b == null) {
            return false;
        }
        int diff = a.length ^ b.length;
        for (int i = 0; i < a.length && i < b.length; i++) {
            diff |= a[i] ^ b[i];
        }
        return diff == 0;
    }

    public static void main(String[] args) {
        String sss = md5("123123");
        byte[] bytes = hashBytes("md5", "123123");
        System.out.println(bytes.length);
        System.out.println(sss);
        System.out.println(sss.length());
    }
}
