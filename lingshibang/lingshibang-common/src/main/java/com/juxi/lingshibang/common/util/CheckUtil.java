package com.juxi.lingshibang.common.util;

import lombok.extern.slf4j.Slf4j;


/**
 * @author xiaoxiong
 * @desc 处理验证签名问题
 */
@Slf4j
public class CheckUtil {

    /**
     * 判断验证
     * @param out_trade_no
     * @param timestamp
     * @param sign
     * @param signKey
     * @return
     */
    public static boolean checkReq(String out_trade_no, String timestamp, String sign, String signKey) {
        try {
            long reqTime = Long.valueOf(timestamp);
            long nowTime = System.currentTimeMillis();
            if (nowTime < reqTime - 1000 * 60 * 2 || nowTime > reqTime + 1000 * 60 * 5) {
                log.info("时间戳校验失败！当前系统时间戳：" + nowTime + " 请求过来的时间戳：" + reqTime);
                return false;
            }
            String signStr=MD5Utils.MD5(MD5Utils.MD5(out_trade_no + timestamp + signKey));
            log.info("signStr:"+signStr);
            if (!sign.equals(signStr)) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String time = ""+System.currentTimeMillis();
        System.out.println(time);
        System.out.println(checkReq("20181226025142","1545808266647","9d7afff0d1f0e7c5aaba338841bb7e3f","zbpay2018"));
    }
}
