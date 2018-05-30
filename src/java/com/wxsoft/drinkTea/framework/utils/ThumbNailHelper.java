/**
 * @文件名称: ThumbNailHelper.java
 * @类路径: com.wxsoft.framework.util
 * @描述: 图片生成缩略图脚手架
 * @作者：kasiaits
 * @时间：2013-3-28 上午10:34:27
 */

package com.wxsoft.drinkTea.framework.utils;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import Decoder.BASE64Decoder;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.wxsoft.drinkTea.framework.config.BaseConfigOne;

/**
 * @类功能说明：图片缩略图脚手架
 * @类修改者：kasiait
 * @修改日期：2013-3-28
 * @修改说明：
 * @公司名称：kyz
 * @作者：kasiaits
 * @创建时间：2013-3-28 上午10:34:27
 */

public class ThumbNailHelper {

	/**
	 *
	 * @描述: java生成缩略图 个人秀
	 * @作者: kasiaits
	 * @日期:2013-3-28
	 * @修改内容
	 * @参数： @param filename 原文件名
	 * @参数： @param thumbWidth 目标宽度
	 * @参数： @param thumbHeight 目标高度
	 * @参数： @param quality 质量 1-100 数值越大，质量越高，size越大
	 * @参数： @param outFilename 缩略图名称
	 * @参数： @throws InterruptedException
	 * @参数： @throws FileNotFoundException
	 * @参数： @throws IOException
	 * @return String 生成的缩略图路径
	 * @throws
	 */
	public static String createThumbnailByPShow(String fileNames,
			int thumbWidth, int thumbHeight, int quality, String outFileName,
			int backgroundEdge) {
		String returnfile;
		File file = new File(fileNames);
		String fileName = file.getName();
		/** 拼成完整的文件保存路径加文件 **/
		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1)
				.toLowerCase();
		int len = fileName.length() - 4;
		String fileExts = fileName.substring(0, len).toLowerCase();
		String yymd = "";
		yymd = fileExts + "_" + backgroundEdge + "." + fileExt;

		String newFile = outFileName;
		int last = outFileName.lastIndexOf("/") + 1;
		String fileExtss = outFileName.substring(0, last);
		outFileName = fileExtss + yymd;
		returnfile = yymd;
		Image image = Toolkit.getDefaultToolkit().getImage(fileNames);
		MediaTracker mediaTracker = new MediaTracker(new Container());
		mediaTracker.addImage(image, 0);
		try {
			mediaTracker.waitForID(0);
		} catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		}

		/**
		 * 如果用户没有指定，则根据数据库配置生成缩略图大小
		 */

		BufferedImage thumbImage = new BufferedImage(backgroundEdge,
				backgroundEdge, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2D = thumbImage.createGraphics();

		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2D.setBackground(Color.WHITE);
		graphics2D.clearRect(0, 0, backgroundEdge, backgroundEdge);// 通过使用当前绘图表面的背景色进行填充来清除指定的矩形。
		graphics2D.drawImage(image, (backgroundEdge - thumbWidth) / 2,
				(backgroundEdge - thumbHeight) / 2, thumbWidth, thumbHeight,
				null);
		BufferedOutputStream out = null;

		File fileNew = new File(newFile);
		if (!fileNew.exists()) {
			try {
				fileNew.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			out = new BufferedOutputStream(new FileOutputStream(outFileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(thumbImage);
		quality = Math.max(0, Math.min(quality, 100));
		param.setQuality(quality / 100.0f, false);
		encoder.setJPEGEncodeParam(param);
		try {
			encoder.encode(thumbImage);
			out.close();
		} catch (ImageFormatException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		return returnfile;
	}


	public static void main(String argvs[]) {
		createThumbnailByRectangle("d:\\4.jpg", 400, 100, 90, "d:\\root\\",400,100);
	}


	/**
	 *
	 * @描述: java生成缩略图 个人秀
	 * @作者: kasiaits
	 * @日期:2013-3-28
	 * @修改内容
	 * @参数： @param filename 原文件名
	 * @参数： @param thumbWidth 目标宽度
	 * @参数： @param thumbHeight 目标高度
	 * @参数： @param quality 质量 1-100 数值越大，质量越高，size越大
	 * @参数： @param outFilename 缩略图名称
	 * @参数： @throws InterruptedException
	 * @参数： @throws FileNotFoundException
	 * @参数： @throws IOException
	 * @return String 生成的缩略图路径
	 * @throws
	 */
	public static String createThumbnailByRectangle(String fileNames,
			int thumbWidth, int thumbHeight, int quality, String outFileName,
			Integer widthTemp, Integer heightTemp) {
		String returnfile;
		File file = new File(fileNames);
		String fileName = file.getName();
		/** 拼成完整的文件保存路径加文件 **/
		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1)
				.toLowerCase();
		int len = fileName.length() - 4;
		String fileExts = fileName.substring(0, len).toLowerCase();
		String yymd = "";
		yymd = fileExts + "_" + widthTemp + "_" + heightTemp + "." + fileExt;

		String newFile = outFileName;
		int last = outFileName.lastIndexOf("/") + 1;
		String fileExtss = outFileName.substring(0, last);
		outFileName = fileExtss + yymd;
		returnfile = yymd;
		Image image = Toolkit.getDefaultToolkit().getImage(fileNames);
		MediaTracker mediaTracker = new MediaTracker(new Container());
		mediaTracker.addImage(image, 0);
		try {
			mediaTracker.waitForID(0);
		} catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		}
		/**
		 * 如果用户没有指定，则根据数据库配置生成缩略图大小
		 */

		BufferedImage thumbImage = new BufferedImage(widthTemp, heightTemp,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2D = thumbImage.createGraphics();

		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2D.setBackground(Color.WHITE);
		graphics2D.clearRect(0, 0, widthTemp, heightTemp);// 通过使用当前绘图表面的背景色进行填充来清除指定的矩形。
		graphics2D.drawImage(image, (widthTemp - thumbWidth) / 2,
				(heightTemp - thumbHeight) / 2, thumbWidth, thumbHeight, null);
		BufferedOutputStream out = null;

		File fileNew = new File(newFile);
		if (!fileNew.exists()) {
			try {
				fileNew.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			out = new BufferedOutputStream(new FileOutputStream(outFileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(thumbImage);
		quality = Math.max(0, Math.min(quality, 100));
		param.setQuality(quality / 100.0f, false);
		encoder.setJPEGEncodeParam(param);
		try {
			encoder.encode(thumbImage);
			out.close();
		} catch (ImageFormatException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		return returnfile;
	}

	/**
	 *
	 * @描述: java生成缩略图 头像处理
	 * @作者: kasiaits
	 * @日期:2013-3-28
	 * @修改内容
	 * @参数： @param filename 原文件名
	 * @参数： @param thumbWidth 目标宽度
	 * @参数： @param thumbHeight 目标高度
	 * @参数： @param quality 质量 1-100 数值越大，质量越高，size越大
	 * @参数： @param outFilename 缩略图名称
	 * @参数： @throws InterruptedException
	 * @参数： @throws FileNotFoundException
	 * @参数： @throws IOException
	 * @return String 生成的缩略图路径
	 * @throws
	 */
	public static String createThumbnail(String fileNames, int thumbWidth,
			int thumbHeight, int quality, String outFileName) {
		String returnfile;
		File file = new File(fileNames);
		String fileName = file.getName();
		/** 拼成完整的文件保存路径加文件 **/
		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1)
				.toLowerCase();
		int len = fileName.length() - 4;
		String fileExts = fileName.substring(0, len).toLowerCase();
		String yymd = fileExts + "_" + thumbWidth + "_" + thumbHeight + "."
				+ fileExt;

		int last = outFileName.lastIndexOf("/") + 1;
		String fileExtss = outFileName.substring(0, last);
		outFileName = fileExtss + yymd;
		returnfile = yymd;
		Image image = Toolkit.getDefaultToolkit().getImage(fileNames);
		MediaTracker mediaTracker = new MediaTracker(new Container());
		mediaTracker.addImage(image, 0);
		try {
			mediaTracker.waitForID(0);
		} catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		}
		/**
		 * 如果用户没有指定，则根据数据库配置生成缩略图大小
		 */

		BufferedImage thumbImage = new BufferedImage(thumbWidth, thumbHeight,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2D = thumbImage.createGraphics();
		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2D.drawImage(image, 0, 0, thumbWidth, thumbHeight, null);
		BufferedOutputStream out = null;
		try {
			out = new BufferedOutputStream(new FileOutputStream(outFileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(thumbImage);
		quality = Math.max(0, Math.min(quality, 100));
		param.setQuality(quality / 100.0f, false);
		encoder.setJPEGEncodeParam(param);
		try {
			encoder.encode(thumbImage);
			out.close();
		} catch (ImageFormatException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		return returnfile;
	}

	/**
	 *
	 * @描述: java生成缩略图
	 * @作者: kasiaits
	 * @日期:2013-3-28
	 * @修改内容
	 * @参数： @param filename 原文件名
	 * @参数： @param thumbWidth 目标宽度
	 * @参数： @param thumbHeight 目标高度
	 * @参数： @param quality 质量 1-100 数值越大，质量越高，size越大
	 * @参数： @param outFilename 缩略图名称
	 * @参数： @throws InterruptedException
	 * @参数： @throws FileNotFoundException
	 * @参数： @throws IOException
	 * @return String 生成的缩略图路径
	 * @throws
	 */
	public static String createIndexThubm(String fileName, int thumbWidth,
			int thumbHeight, int quality, String outFileName) {
		Image image = Toolkit.getDefaultToolkit().getImage(fileName);
		MediaTracker mediaTracker = new MediaTracker(new Container());
		mediaTracker.addImage(image, 0);
		try {
			mediaTracker.waitForID(0);
		} catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		}
		/**
		 * 如果用户没有指定，则根据数据库配置生成缩略图大小
		 */
		if (thumbWidth == 0)
			thumbWidth = BaseConfigOne.getInteger("IMAGE_THUMB_WIDTH");
		if (thumbHeight == 0)
			thumbHeight = BaseConfigOne.getInteger("IMAGE_THUMB_HEIGHT");
		if (quality == 0)
			quality = BaseConfigOne.getInteger("IMAGE_THUMB_QUALITY");
		// draw original image to thumbnail image object and
		// scale it to the new size on-the-fly
		BufferedImage thumbImage = new BufferedImage(thumbWidth, thumbHeight,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2D = thumbImage.createGraphics();
		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2D.drawImage(image, 0, 0, thumbWidth, thumbHeight, null);

		// save thumbnail image to outFilename
		if (outFileName == null || outFileName.length() == 0)
			outFileName = getThunmbFileName(fileName);
		File file = new File(outFileName);
		if (!file.exists()) {
			file.mkdirs();
		}
		BufferedOutputStream out = null;
		try {
			out = new BufferedOutputStream(new FileOutputStream(outFileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(thumbImage);
		quality = Math.max(0, Math.min(quality, 100));
		param.setQuality(quality / 100.0f, false);
		encoder.setJPEGEncodeParam(param);
		try {
			encoder.encode(thumbImage);
			out.close();
		} catch (ImageFormatException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		return outFileName;
	}

	/**
	 *
	 * @描述: 根据原文件名生成缩略图名称
	 * @作者: kasiaits
	 * @日期:2013-3-28
	 * @修改内容
	 * @参数： @param fileName
	 * @参数： @return
	 * @return String
	 * @throws
	 */
	public static String getThunmbFileName(String fileName) {
		/** 根据源文件名加后缀名称生成缩略图名称 */
		fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
		String destFile = BaseConfigOne.getString("IMAGE_THUMB_PATH")
				+ fileName.substring(0, fileName.lastIndexOf("."))
				+ BaseConfigOne.getString("IMAGE_THUMB_SUFFIX") + "."
				+ Tools.getFileSuffix(fileName);
		return destFile;
	}



	/**
	 *
	 * @描述: 根据路径从磁盘上删除文件
	 * @作者: kyz
	 * @日期:2013-3-29
	 * @修改内容
	 * @参数： @param fileName
	 * @参数： @return
	 * @return byte[]
	 * @throws 根据路径删除指定的目录或文件
	 *             ，无论存在与否
	 * @param sPath
	 *            要删除的目录或文件
	 * @return 删除成功返回 true，否则返回 false。
	 */
	public static boolean DeleteFolder(String sPath) {
		sPath = BaseConfigOne.getString("SYS_UPLOAD_ROOT") + sPath;
		boolean flag = false;
		File file = new File(sPath);
		// 判断目录或文件是否存在
		if (!file.exists()) { // 不存在返回 false
			return flag;
		} else {
			// 判断是否为文件
			if (file.isFile()) { // 为文件时调用删除文件方法
				return deleteFile(sPath);
			}
		}
		return flag;
	}

	/**
	 *
	 * @描述: 根据路径从磁盘上删除文件
	 * @作者: kyz
	 * @日期:2013-3-29
	 * @修改内容
	 * @参数： @param fileName
	 * @参数： @return
	 * @return byte[]
	 * @throws 根据路径删除指定的目录或文件
	 *             ，无论存在与否
	 * @param sPath
	 *            要删除的目录或文件
	 * @return 删除成功返回 true，否则返回 false。
	 */
	public static boolean DeleteFolderMusic(String sPath) {
		boolean flag = false;
		try {
			sPath = BaseConfigOne.getString("SYS_UPLOAD_ROOT_MUSIC")
					+ sPath.substring(17, -1);
		} catch (Exception e) {
			return flag;
		}
		File file = new File(sPath);
		// 判断目录或文件是否存在
		if (!file.exists()) { // 不存在返回 false
			return flag;
		} else {
			// 判断是否为文件
			if (file.isFile()) { // 为文件时调用删除文件方法
				return deleteFile(sPath);
			}
		}
		return flag;
	}

	/**
	 * 删除单个文件
	 *
	 * @param sPath
	 *            被删除文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public static boolean deleteFile(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}

	/**
	 * 上传图片
	 *
	 * @param filename
	 *            html input中的name
	 * @param request
	 * @return 如果用户选择了图片，那么返回的list.get(0)大图片 1为小图片 ；如果用户没有选择文件那么返回null
	 */
	public static List<String> upLoadPic(String filename,
			HttpServletRequest request) {
		List<String> list = new ArrayList<String>();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		/** 页面控件的文件流 **/
		List<MultipartFile> multipartFile = multipartRequest.getFiles(filename);
		/** 获取文件的后缀 **/
		for (MultipartFile multipartFile2 : multipartFile) {
			// 文件上传物理路径
			String savePath = BaseConfigOne.getString("SYS_DOC_BIG_ROOTPATH");
			// 文件保存目录URL
			String saveUrl = BaseConfigOne.getString("SYS_DOC_BIG_URLPATH");
			savePath += "image" + "/";
			saveUrl += "image" + "/";
			File saveDirFile = new File(savePath);
			if (!saveDirFile.exists()) {
				saveDirFile.mkdirs();
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String ymd = sdf.format(new Date());
			savePath += ymd + "/";
			saveUrl += ymd + "/";
			File dirFile = new File(savePath);
			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}
			String logImageName = multipartFile2.getOriginalFilename();
			/** 拼成完整的文件保存路径加文件 **/
			String thumb = "";
			if (!logImageName.equals("")) {
				String fileExt = logImageName.substring(
						logImageName.lastIndexOf(".") + 1).toLowerCase();
				String yymd = new SimpleDateFormat("yyyyMMddHHmmss")
						.format(new Date())
						+ "_"
						+ new Random().nextInt(1000)
						+ "." + fileExt;
				String fileName = savePath + yymd;
				saveUrl += yymd;
				File file = new File(fileName);
				try {
					multipartFile2.transferTo(file);
					String name = ThumbNailHelper.createThumbnail(fileName, 0,
							0, 0, null);
					if (name != null) {
						// 商品图片保存目录URL
						thumb = name.substring(name.lastIndexOf("/") + 1);
					}
					list.add(saveUrl);
					list.add(ThumbNailHelper.createThumbnail(savePath + thumb,
							0, 0, 0, ""));
					// product.setPicuri(saveUrl);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	public static List<String> upLoadMusic(String filename,
			HttpServletRequest request) {
		List<String> list = new ArrayList<String>();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		/** 页面控件的文件流 **/
		List<MultipartFile> multipartFile = multipartRequest.getFiles(filename);
		/** 获取文件的后缀 **/
		for (MultipartFile multipartFile2 : multipartFile) {
			// 文件上传物理路径
			String savePath = BaseConfigOne.getString("SYS_UPLOAD_ROOT_MUSIC");
			// 文件保存目录URL
			String saveUrl = BaseConfigOne.getString("SYS_UPLOAD_URLPATH_MUSIC");
			savePath += "music" + "/";
			saveUrl += "music" + "/";
			File saveDirFile = new File(savePath);
			if (!saveDirFile.exists()) {
				saveDirFile.mkdirs();
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String ymd = sdf.format(new Date());
			savePath += ymd + "/";
			saveUrl += ymd + "/";
			File dirFile = new File(savePath);
			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}
			String logImageName = multipartFile2.getOriginalFilename();
			/** 拼成完整的文件保存路径加文件 **/
			String thumb = "";
			if (!logImageName.equals("")) {
				String fileExt = logImageName.substring(
						logImageName.lastIndexOf(".") + 1).toLowerCase();
				String yymd = new SimpleDateFormat("yyyyMMddHHmmss")
						.format(new Date())
						+ "_"
						+ new Random().nextInt(1000)
						+ "." + fileExt;
				String fileName = savePath + yymd;
				saveUrl += yymd;
				File file = new File(fileName);
				try {
					multipartFile2.transferTo(file);
					String name = ThumbNailHelper.createThumbnail(fileName, 0,
							0, 0, null);
					if (name != null) {
						// 商品图片保存目录URL
						thumb = name.substring(name.lastIndexOf("/") + 1);
					}
					list.add(saveUrl);
					// product.setPicuri(saveUrl);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

}
