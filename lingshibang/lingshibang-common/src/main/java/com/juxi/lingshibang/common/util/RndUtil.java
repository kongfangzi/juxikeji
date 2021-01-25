package com.juxi.lingshibang.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * 随机数生成
 * @author Administrator
 */
public class RndUtil {
    private static final AtomicInteger orderCounter = new AtomicInteger(0);
    private static Random r = null;

    static {
        r = new Random();
    }

    /*** 重新初始化随机种子 */
    public static void ResetSeed() {
        r = new Random();
    }


    //=========================================================================================================================基于UUID的随机处理

    /*** 重新初始化随机种子 */
    public static void ResetSeed(long seed) {
        r = new Random(seed);
    }

    /*** 取得一个全球统一编码的ID 如：<br><br> ba879def-5386-4350-b716-afa45e67f6be */
    public static String getStdUuid() {
        return UUID.randomUUID().toString();
    }

    //=========================================================================================================================基于时间的随机处理

    /*** 取得一个全球统一编码的ID 如：<br><br> ba879def53864350b716afa45e67f6be */
    public static String getUuid() {
        return getStdUuid().replace("-", "");
    }

    /***
     * 取得一个长的基于时间（1970年）的ID 如：<br> 1429710183629 */
    public static long getLongTimeID() {
        return System.currentTimeMillis();
    }

    /*** 取得可用于订单的Id  使用 订单前缀 + 时间标识(yyyyMMddHHmmss) + 全局序列(0 - 99999) 构成  */
    public static String getOrderId(String orderPrefix) {
        String dtStr = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String atomId = String.valueOf((orderCounter.getAndIncrement() % 100000) + 100000);
        return orderPrefix + dtStr + atomId.substring(1);
    }

    public static int getCode() {
        return getCode(4);
    }

    public static int getCode(int length) {
        Double d = Double.valueOf(Math.abs(r.nextInt()));
        d = d % Math.pow(10, length);
        if (d < Math.pow(10, length - 1)) d = d + Math.pow(10, length - 1);
        return d.intValue();
    }

    //=========================================================================================================================基于数字的随机处理


    /***  取得一个随机数  介于 0.0 - 1.0 之间  */
    public static double getRnd() {
        return r.nextDouble();
    }

    public static <T extends Number> Double getRnd(T max, T min) {
        double maxx = max.doubleValue();
        double minn = min.doubleValue();
        if (maxx > minn) {
            return (maxx - minn) * r.nextDouble() + minn;
        } else {
            return (minn - maxx) * r.nextDouble() + maxx;
        }
    }

    public static boolean getRndBoolean() {
        return r.nextBoolean();
    }

    public static byte getRndByte() {
        return (byte) r.nextInt();
    }

    public static byte getRndByte(byte max, byte min) {
        Double d = getRnd(Integer.MAX_VALUE, Integer.MIN_VALUE);
        return d.byteValue();
    }

    public static short getRndShort() {
        return (short) r.nextInt();
    }

    public static short getRndShort(short max, short min) {
        Double d = getRnd(Integer.MAX_VALUE, Integer.MIN_VALUE);
        return d.shortValue();
    }

    public static int getRndInt() {
        return r.nextInt();
    }

    public static int getRndInt(int max, int min) {
        Double d = getRnd(Integer.MAX_VALUE, Integer.MIN_VALUE);
        return d.intValue();
    }

    public static char getRndChar() {
        return (char) getRndInt();
    }

    public static char getRndChar(char max, char min) {
        return (char) getRndInt(max, min);
    }

    public static long getRndLong() {
        return r.nextLong();
    }

    public static long getRndLong(long max, long min) {
        Double d = getRnd(Long.MAX_VALUE, Long.MIN_VALUE);
        return d.longValue();
    }

    public static float getRndFloat() {
        return r.nextFloat();
    }

    public static float getRndFloat(float max, float min) {
        Double d = getRnd(Float.MAX_VALUE, Float.MIN_VALUE);
        return d.floatValue();
    }

    public static double getRndDoble() {
        return r.nextDouble();
    }

    public static double getRndDoble(double max, double min) {
        Double d = getRnd(Double.MAX_VALUE, Double.MIN_VALUE);
        return d;
    }

    //=========================================================================================================================基于数字数组的随机处理


    /***
     * 取得指定个数的随机数
     * @param length 个数
     * @param max 最大值
     * @param min 最小值
     * @return
     */
    public static <T extends Number> double[] getRnd(int length, T max, T min) {
        if (length > 0) {
            double[] b = new double[length];
            for (int i = 0; i < length; i++) b[i] = getRnd(max, min);
            return b;
        } else {
            return null;
        }
    }


}
