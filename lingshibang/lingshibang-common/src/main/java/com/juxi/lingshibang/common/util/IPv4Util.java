package com.juxi.lingshibang.common.util;

import cn.hutool.core.util.StrUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

/**
 * IP
 */
public final class IPv4Util {
	// IpV4的正则表达式，用于判断IpV4地址是否合法
	private static final String IPV4_REGEX = "((\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})";

	/**
	 * 判断ipV4或者mask地址是否合法，通过正则表达式方式进行判断
	 * 
	 * @param ipv4
	 */
	public static boolean ipv4Validate(String ipv4) {
		if (StringUtil.isBlank(ipv4)) {
			return false;
		}
		return ipv4Validate(ipv4, IPV4_REGEX);
	}

	/**
	 * 判断ipV4或者mask地址是否合法，通过正则表达式方式进行判断
	 */
	private static boolean ipv4Validate(String addr, String regex) {
		if (addr == null) {
			return false;
		} else {
			return Pattern.matches(regex, addr.trim());
		}
	}

	/**
	 * 将ip byte数组值转换为如“192.168.0.1”等格式的字符串
	 * 
	 * @param ipBytes
	 *            32bit值
	 * @return
	 */
	public static String trans2IPv4Str(byte[] ipBytes) {
		// 保证每一位地址都是正整数
		return (ipBytes[0] & 0xff) + "." + (ipBytes[1] & 0xff) + "." + (ipBytes[2] & 0xff) + "." + (ipBytes[3] & 0xff);
	}

	/**
	 * ip或子网掩码转int
	 * 
	 * @param ipOrMask
	 * @return
	 */
	public static int getIPv4Value(String ipOrMask) {
		if (StringUtil.isBlank(ipOrMask)) {
			return 0;
		}
		byte[] addr = getIPv4Bytes(ipOrMask);
		int address1 = addr[3] & 0xFF;
		address1 |= ((addr[2] << 8) & 0xFF00);
		address1 |= ((addr[1] << 16) & 0xFF0000);
		address1 |= ((addr[0] << 24) & 0xFF000000);
		return address1;
	}

	/**
	 * ip或子网掩码转byte
	 * 
	 * @param ipOrMask
	 * @return
	 */
	public static byte[] getIPv4Bytes(String ipOrMask) {
		try {
			String[] addrs = ipOrMask.split("\\.");
			int length = addrs.length;
			byte[] addr = new byte[length];
			for (int index = 0; index < length; index++) {
				addr[index] = (byte) (Integer.parseInt(addrs[index]) & 0xff);
			}
			return addr;
		} catch (Exception e) {
		}
		return new byte[4];
	}

	public static String getRealIPAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		String regex = "((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)";
		String localIP = "127.0.0.1";
		if (StrUtil.isBlank(ip) || ip.equalsIgnoreCase(localIP) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}

		if (StrUtil.isBlank(ip) || ip.equalsIgnoreCase(localIP) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}

		if (StrUtil.isBlank(ip) || ip.equalsIgnoreCase(localIP) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}

		String[] ipAddrArr = ip.split(",");
		String[] arr$ = ipAddrArr;
		int len$ = ipAddrArr.length;

		for(int i$ = 0; i$ < len$; ++i$) {
			String ipAddr = arr$[i$];
			if (null != ipAddr && !"unknown".equals(ipAddr) && ipAddr.matches(regex)) {
				ip = ipAddr;
				break;
			}
		}
		return ip;
	}

	public static void main(String[] args) {
		System.out.println(getIPv4Value("10.8.11.11"));
		System.out.println(trans2IPv4Str(getIPv4Bytes("10.8.11.11")));
	}
}
