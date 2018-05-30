package com.wxsoft.drinkTea.framework.utils;

import java.io.File;
import java.util.Hashtable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QrcodeUtils {

	/**
	 * 编码
	 *
	 * @param contents 二维码的内容
	 * @param width 	宽度
	 * @param height	长度
	 * @param imgPath	保存生成二维码的路径
	 */
	public static void encode(String contents, int width, int height, String imgPath) {
		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		// 指定纠错等级
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		// 指定编码格式
		hints.put(EncodeHintType.CHARACTER_SET, "GBK");
		hints.put(EncodeHintType.MARGIN, 0); //二维码边框宽度，这里文档说设置0-4，但是设置后没有效果，不知原因，
		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,
					BarcodeFormat.QR_CODE, width, height, hints);
			File file = new File(imgPath);
			if(!file.exists()){
				if(file.isFile()){
					file.createNewFile();
				}else{
					file.mkdirs();
				}
			}
			MatrixToImageWriter
					.writeToFile(bitMatrix, "png", file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
