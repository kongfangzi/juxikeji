package com.juxi.lingshibang.common.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.util.UUID;

/**
 * @version 1.0
 * @date 2017-03-22
 */
public final class RandomUtil extends RandomStringUtils {
    private RandomUtil() {}

    public static boolean nextBoolean() {
        return RandomUtils.nextBoolean();
    }

    public static byte[] nextBytes(int count) {
        return RandomUtils.nextBytes(count);
    }

    public static int nextInt(int startInclusive, int endExclusive) {
        return RandomUtils.nextInt(startInclusive, endExclusive);
    }

    public static int nextInt() {
        return RandomUtils.nextInt();
    }

    public static int nextInt(int n) {
        return RandomUtils.nextInt(0, n);
    }

    public static long nextLong(long startInclusive, long endExclusive) {
        return RandomUtils.nextLong(startInclusive, endExclusive);
    }

    public static long nextLong() {
        return RandomUtils.nextLong();
    }

    public static long nextLong(long n) {
        return RandomUtils.nextLong(0, n);
    }

    public static double nextDouble(double startInclusive, double endInclusive) {
        return RandomUtils.nextDouble(startInclusive, endInclusive);
    }

    public static double nextDouble() {
        return RandomUtils.nextDouble();
    }

    public static float nextFloat(float startInclusive, float endInclusive) {
        return RandomUtils.nextFloat(startInclusive, endInclusive);
    }

    public static float nextFloat() {
        return RandomUtils.nextFloat();
    }

    public static String randomUUID() {
        return StringUtil.replaceAll(UUID.randomUUID().toString(), "-", "");
    }

    public static String randomNumericNotStartZero(int count) {
        int s = nextInt(1, 9);
        return s + randomNumeric(count - 1);
    }
}
