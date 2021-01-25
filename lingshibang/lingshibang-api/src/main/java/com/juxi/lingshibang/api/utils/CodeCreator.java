package com.juxi.lingshibang.api.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zxm
 *
 */
public class CodeCreator {
	private Logger logger = LoggerFactory.getLogger(CodeCreator.class);
	private static int BLACK = 0x000000;
	private static int WHITE = 0xFFFFFF;
	
	private BufferedImage createCodeImage(CodeModel info){
		String contents = info.getContents();
		int width = (new Double(info.getWidth()*0.98)).intValue();
		int height = (new Double(info.getWidth()*0.98)).intValue();
		Map<EncodeHintType, Object> hint = new HashMap<EncodeHintType, Object>();
		hint.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		hint.put(EncodeHintType.CHARACTER_SET, info.getCharacter_set());
		hint.put(EncodeHintType.MARGIN, 0);
		MultiFormatWriter writer = new MultiFormatWriter();
		BufferedImage img = null;
		try {
			BitMatrix bm = writer.encode(contents, BarcodeFormat.QR_CODE, width, height, hint);
			int[] locationTopLeft = bm.getTopLeftOnBit();
			int[] locationBottomRight = bm.getBottomRightOnBit();
			info.setBottomStart(new int[]{locationTopLeft[0], locationBottomRight[1]});
			info.setBottomEnd(locationBottomRight);
			int w = bm.getWidth();
			int h = bm.getHeight();
			img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
			for(int x=0;x<w;x++){
				for(int y=0;y<h;y++){
					img.setRGB(x, y, bm.get(x, y) ? BLACK : WHITE);
				}
			}
		} catch (WriterException e) {
			logger.error(e.getMessage(),e);
		}
		return img;
	}
	
	public void createCodeImage(CodeModel info, OutputStream output){
		BufferedImage bm = createCodeImage(info);
		try{
			ImageIO.write(bm, StringUtils.isEmpty(info.getFormat()) ? info.getFormat() : info.getFormat(), output);
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
	}
	
	public void createCodeImage(CodeModel info, File file){
		File parent = file.getParentFile();
		if(!parent.exists()) {
			parent.mkdirs();
		}
		OutputStream output = null;
		try{
			output = new BufferedOutputStream(new FileOutputStream(file));
			createCodeImage(info, output);
			output.flush();
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}finally{
			try {
				output.close();
			} catch (IOException e) {
				logger.error(e.getMessage(),e);
			}
		}
	}
	
	public void createCodeImage(CodeModel info, String filePath){
		createCodeImage(info, new File(filePath));
	}
}