package com.juxi.lingshibang.api.utils;

import com.google.zxing.*;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zxm
 *
 */
public class CodeDecoder {
	public String decode(InputStream input){
		Map<DecodeHintType, Object> hint = new HashMap<DecodeHintType, Object>();
		hint.put(DecodeHintType.POSSIBLE_FORMATS, BarcodeFormat.QR_CODE);
		String result = "";
		try{
		BufferedImage img = ImageIO.read(input);
		int[] pixels = img.getRGB(0, 0, img.getWidth(), img.getHeight(), null, 0, img.getWidth());
		LuminanceSource source = new RGBLuminanceSource(img.getWidth(), img.getHeight(), pixels);
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
		QRCodeReader reader = new QRCodeReader();
		Result r = reader.decode(bitmap, hint);
		result = r.getText();
		}catch(Exception e){
			result="读取错误";
		}
		return result;
	}
}