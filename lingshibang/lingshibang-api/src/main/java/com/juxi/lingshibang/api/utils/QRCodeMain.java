package com.juxi.lingshibang.api.utils;

import java.io.OutputStream;

/**
 * @author zxm
 *
 */
public class QRCodeMain{
	public static void encode(String content, OutputStream out) {
		CodeCreator creator = new CodeCreator();
		CodeModel info = new CodeModel();
		info.setWidth(200);
		info.setHeight(200);
		info.setContents(content);
		//creator.createCodeImage(info, "\\D\\work\\FMK\\dest." + info.getFormat());
		creator.createCodeImage(info,out);
	}
	
	public static void main(String[] args) throws Exception{
		String content = "问问iejiowerjwieorjweisdfsdfsdfsdf哈哈哈哈哈万水千山只等斯蒂芬斯蒂芬斯蒂芬第三方斯蒂芬速度闲";
		CodeCreator creator = new CodeCreator();
		CodeModel info = new CodeModel();
		info.setWidth(200);
		info.setHeight(200);
		info.setContents(content);
		//creator.createCodeImage(info, "\\D\\work\\FMK\\dest." + info.getFormat());
	}
}