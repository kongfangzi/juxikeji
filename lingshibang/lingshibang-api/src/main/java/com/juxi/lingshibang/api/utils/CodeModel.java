package com.juxi.lingshibang.api.utils;

import java.io.File;

/**
 * @author zxm
 *
 */
public class CodeModel {
	private String contents;
	private int top = 10;
	private int wordTopMargin = 8;
	private int width = 400;
	private int height = 400;
	private int textWidth=600;
	private int left = 30;
	private int left2 = 10;
	private String format = "png";
	private String character_set = "utf-8";
	private int fontSize = 12;
	private File logoFile;
	private float logoRatio = 0.20f;
	//品名:
	private String itemName;
	private int itemName_lineNo=0;
	private int itemName_lineNum=0;
	//系列:
	private String seriesName;
	private int seriesName_lineNo=0;
	private int seriesName_lineNum=0;
	//描述
	private String description;
	private int description_lineNo=0;
	private int description_lineNum=0;
	private int whiteWidth;//白边的宽度
	private int lineNum=4;
	private int[] bottomStart;//二维码最下边的开始坐标
	private int[] bottomEnd;//二维码最下边的结束坐标

	public int gettotoalNum(){
		return this.getItemName_lineNum()+this.getDescription_lineNum()+this.getSeriesName_lineNum();
	}
	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getCharacter_set() {
		return character_set;
	}

	public void setCharacter_set(String character_set) {
		this.character_set = character_set;
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public File getLogoFile() {
		return logoFile;
	}

	public void setLogoFile(File logoFile) {
		this.logoFile = logoFile;
	}

	public float getLogoRatio() {
		return logoRatio;
	}

	public void setLogoRatio(float logoRatio) {
		this.logoRatio = logoRatio;
	}

	public int getWhiteWidth() {
		return whiteWidth;
	}

	public void setWhiteWidth(int whiteWidth) {
		this.whiteWidth = whiteWidth;
	}

	public int[] getBottomStart() {
		return bottomStart;
	}

	public void setBottomStart(int[] bottomStart) {
		this.bottomStart = bottomStart;
	}

	public int[] getBottomEnd() {
		return bottomEnd;
	}

	public void setBottomEnd(int[] bottomEnd) {
		this.bottomEnd = bottomEnd;
	}

	public int getTextWidth() {
		return textWidth;
	}

	public void setTextWidth(int textWidth) {
		this.textWidth = textWidth;
	}

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public int getLeft2() {
		return left2;
	}

	public void setLeft2(int left2) {
		this.left2 = left2;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public int getLineNum() {
		return lineNum;
	}

	public void setLineNum(int lineNum) {
		this.lineNum = lineNum;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
		this.itemName_lineNo = 1;
		this.itemName_lineNum = 1;
	}

	public int getItemName_lineNo() {
		return itemName_lineNo;
	}

	public void setItemName_lineNo(int itemName_lineNo) {
		this.itemName_lineNo = itemName_lineNo;
	}

	public int getItemName_lineNum() {
		return itemName_lineNum;
	}

	public void setItemName_lineNum(int itemName_lineNum) {
		this.itemName_lineNum = itemName_lineNum;
	}

	public String getSeriesName() {
		return seriesName;
	}

	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
		this.seriesName_lineNo = 2;
		this.seriesName_lineNum = 1;
	}

	public int getSeriesName_lineNo() {
		return seriesName_lineNo;
	}

	public void setSeriesName_lineNo(int seriesName_lineNo) {
		this.seriesName_lineNo = seriesName_lineNo;
	}

	public int getSeriesName_lineNum() {
		return seriesName_lineNum;
	}

	public void setSeriesName_lineNum(int seriesName_lineNum) {
		this.seriesName_lineNum = seriesName_lineNum;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
		this.description_lineNo = 3;
		this.description_lineNum = 1;
	}

	public int getDescription_lineNo() {
		return description_lineNo;
	}

	public void setDescription_lineNo(int description_lineNo) {
		this.description_lineNo = description_lineNo;
	}

	public int getDescription_lineNum() {
		return description_lineNum;
	}

	public void setDescription_lineNum(int description_lineNum) {
		this.description_lineNum = description_lineNum;
	}
	public int getWordTopMargin() {
		return wordTopMargin;
	}
	public void setWordTopMargin(int wordTopMargin) {
		this.wordTopMargin = wordTopMargin;
	}
}