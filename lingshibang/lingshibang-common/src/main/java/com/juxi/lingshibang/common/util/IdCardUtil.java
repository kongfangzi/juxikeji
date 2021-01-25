package com.juxi.lingshibang.common.util;

import com.juxi.lingshibang.common.util.text.CommonTextValidateRegex;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * 身份证工具类
 * @author Xukai
 * @date 2017-08-04
 */
public final class IdCardUtil {
    /**
     * <pre>
     * 省、直辖市代码表：
     *     11 : 北京  12 : 天津  13 : 河北       14 : 山西  15 : 内蒙古
     *     21 : 辽宁  22 : 吉林  23 : 黑龙江  31 : 上海  32 : 江苏
     *     33 : 浙江  34 : 安徽  35 : 福建       36 : 江西  37 : 山东
     *     41 : 河南  42 : 湖北  43 : 湖南       44 : 广东  45 : 广西      46 : 海南
     *     50 : 重庆  51 : 四川  52 : 贵州       53 : 云南  54 : 西藏
     *     61 : 陕西  62 : 甘肃  63 : 青海       64 : 宁夏  65 : 新疆
     *     71 : 台湾
     *     81 : 香港  82 : 澳门
     *     91 : 国外
     * </pre>
     */
    private static final String PROVINCE_CODE[] = { "11", "12", "13", "14", "15", "21",
            "22", "23", "31", "32", "33", "34", "35", "36", "37", "41", "42",
            "43", "44", "45", "46", "50", "51", "52", "53", "54", "61", "62",
            "63", "64", "65", "71", "81", "82", "91" };

    private static final String PROVINCE_NAME[] = { "北京市", "天津市", "河北省", "山西省", "内蒙古自治区", "辽宁省",
            "吉林省", "黑龙江省", "上海市", "江苏省", "浙江省", "安徽省", "福建省", "江西省", "山东省", "河南省", "湖北省",
            "湖南省", "广东省", "广西壮族自治区", "海南省", "重庆市", "四川省", "贵州省", "云南省", "西藏自治区", "陕西省", "甘肃省",
            "青海省", "宁夏回族自治区", "新疆维吾尔自治区", "台湾省", "香港特别行政区", "澳门特别行政区", "国外" };


    private static final int[] CHECK_CODE_WEIGHT = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
    private static final int[] LAST_CODE = {1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2};

    /**
     * <p>
     * 判断18位身份证的合法性
     * </p>
     * 根据〖中华人民共和国国家标准GB11643-1999〗中有关公民身份号码的规定，公民身份号码是特征组合码，由十七位数字本体码和一位数字校验码组成。
     * 排列顺序从左至右依次为：六位数字地址码，八位数字出生日期码，三位数字顺序码和一位数字校验码。
     * <p>
     * 顺序码: 表示在同一地址码所标识的区域范围内，对同年、同月、同 日出生的人编定的顺序号，顺序码的奇数分配给男性，偶数分配 给女性。
     * </p>
     * <p>
     * 1.前1、2位数字表示：所在省份的代码； 2.第3、4位数字表示：所在城市的代码； 3.第5、6位数字表示：所在区县的代码；
     * 4.第7~14位数字表示：出生年、月、日； 5.第15、16位数字表示：所在地的派出所的代码；
     * 6.第17位数字表示性别：奇数表示男性，偶数表示女性；
     * 7.第18位数字是校检码：也有的说是个人信息码，一般是随计算机的随机产生，用来检验身份证的正确性。校检码可以是0~9的数字，有时也用x表示。
     * </p>
     * <p>
     * 第十八位数字(校验码)的计算方法为： 1.将前面的身份证号码17位数分别乘以不同的系数。从第一位到第十七位的系数分别为：7 9 10 5 8 4
     * 2 1 6 3 7 9 10 5 8 4 2
     * </p>
     * <p>
     * 2.将这17位数字和系数相乘的结果相加。
     * </p>
     * <p>
     * 3.用加出来和除以11，看余数是多少
     * </p>
     * 4.余数只可能有0 1 2 3 4 5 6 7 8 9 10这11个数字。其分别对应的最后一位身份证的号码为1 0 X 9 8 7 6 5 4 3
     * 2。
     * <p>
     * 5.通过上面得知如果余数是2，就会在身份证的第18位数字上出现罗马数字的Ⅹ。如果余数是10，身份证的最后一位号码就是2。
     * </p>
     *
     * @param id
     * @return
     */
    public static boolean isIdCard(String id) {
        return isIdCard15(id) || isIdCard18(id);
    }

    public static boolean isIdCard15(String id) {
        return TextUtil.isMatch(CommonTextValidateRegex.PATTERN_REGEX_ID_CARD15, id);
    }

    public static boolean isIdCard18(String id) {
        return TextUtil.isMatch(CommonTextValidateRegex.PATTERN_REGEX_ID_CARD18, id);
    }

    public static int[] toIntArr(String id) {
        if (!isIdCard(id)) {
            return null;
        }
        int[] ids = new int[18];
        for (int i = 0; i < id.length(); i++) {
            ids[i] = toInt(StringUtil.substring(id, i, i + 1));
        }
        return ids;
    }

    private static String toString(int[] ids) {
        if (ArrayUtil.isEmpty(ids)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int id : ids) {
            sb.append(toVal(id));
        }
        return sb.toString();
    }

    private static int toInt(String i) {
        if (i.isEmpty() || i.length() != 1) {
            return -1;
        }
        if (StringUtil.equalsIgnoreCase(i, "X")) {
            return 10;
        }
        return NumberUtil.toInt(i, -1);
    }
    private static String toVal(int i) {
        if (i >= 10) {
            return "X";
        }
        return StringUtil.valueOf(i, "");
    }

    public static String idCard15To18(String id) {
        if (!isIdCard15(id)) {
            return id;
        }
        int[] ids = toIntArr(id);
        for (int i = 14; i > 5; i--) {
            ids[i + 2] = ids[i];
        }
        ids[6] = 1;
        ids[7] = 9;
        Pair<Boolean, Integer> r = calCheckNumber(ids);
        ids[17] = r.getRight();
        return toString(ids);
    }

    /**
     * 计算身份证校验位
     * @param ids 必须是18位身份证
     * @return boolean-表示原校验码是否正确，int-表示正确校验码
     */
    public static Pair<Boolean, Integer> calCheckNumber(int[] ids) {
        if (ArrayUtil.isEmpty(ids) || ids.length != 18) {
            return null;
        }
        boolean right = false;
        int code = -1;
        int sum = 0;
        for (int i = 0; i < 17; i++) {
            sum += ids[i] * CHECK_CODE_WEIGHT[i];
        }
        int r = sum % 11;
        code = LAST_CODE[r];
        right = code == ids[17];
        return Pair.of(right, code);
    }

    /**
     * 获取生日
     * @param id
     * @return yyyy-MM-dd
     */
    public static String getBirthday(String id) {
        if (!isIdCard(id)) {
            return null;
        }
        if (isIdCard15(id)) {
            id = idCard15To18(id);
        }
        String yyyy = StringUtil.substring(id, 6, 10);
        String MM = StringUtil.substring(id, 10, 12);
        String dd = StringUtil.substring(id, 12, 14);
        return String.format("%s-%s-%s", yyyy, MM, dd);
    }

    public static Date getBirthdayDate(String id) {
        String d = getBirthday(id);
        if (StringUtil.isNotBlank(d)) {
            try {
                return DateUtils.parseDate(d, "yyyy-MM-dd");
            } catch (ParseException e) {
            }
        }
        return new Date();
    }

    /**
     * 获取性别
     * @param id
     * @return 0-女 1-男 -1-错误
     */
    public static int getSex(String id) {
        String code = StringUtil.substring(id, 14, 17);
        return NumberUtil.toInt(code, -1) % 2;
    }

    public static String getProvinceCode(String id) {
        if (isIdCard(id)) {
            String code = id.substring(0, 2);
            for (int i = 0; i < PROVINCE_CODE.length; i++) {
                if (StringUtil.equals(PROVINCE_CODE[i], code)) {
                    return PROVINCE_CODE[i];
                }
            }
        }
        return "";
    }
    public static String getProvinceName(String id) {
        if (isIdCard(id)) {
            String code = id.substring(0, 2);
            for (int i = 0; i < PROVINCE_CODE.length; i++) {
                if (StringUtil.equals(PROVINCE_CODE[i], code)) {
                    return PROVINCE_NAME[i];
                }
            }
        }
        return "";
    }

    public static boolean isAdult(String id) {
        if (StringUtil.isNotBlank(id) && isIdCard(id)) {
            int age = getAge(id);
            if (age >= 18) {
                return true;
            }
        }
        return false;
    }

    public static int getAge(String id) {
        Date date = getBirthdayDate(id);
        return getAgeByDate(date);
    }

    private static int getAgeByDate(Date birthday) {
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        if (now.before(birthday)) {
            return -1;
        }

        int yn = calendar.get(Calendar.YEAR);
        int mn = calendar.get(Calendar.MONTH);
        int dn = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.setTime(birthday);
        int yb = calendar.get(Calendar.YEAR);
        int mb = calendar.get(Calendar.MONTH);
        int db = calendar.get(Calendar.DAY_OF_MONTH);

        int age = yn - yb;
        if (mn <= mb && mn == mb && dn < db || mn < mb) {
            age--;
        }
        return age;
    }

    public static boolean valid(String id) {
        if (isIdCard(id)) {
            if (isIdCard15(id)) {
                id = idCard15To18(id);
            }
            Pair<Boolean, Integer> result = calCheckNumber(toIntArr(id));
            if (result != null) {
                return result.getLeft();
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(isIdCard("220122199212171715"));
        System.out.println(valid("220122199212171713"));

        String id = "450702830730686";
//        String id = "43252219830721741X";
        System.out.println(getBirthday(id));
        System.out.println(getBirthdayDate(id));
        System.out.println(getSex(id));
        System.out.println(isAdult(id));

        System.out.println(getProvinceName(id));

        id = idCard15To18(id);
        System.out.println(toString(toIntArr(id)));

        Pair<Boolean, Integer> result = calCheckNumber(toIntArr(id));
        System.out.println(result);
    }
}
