package com.wxsoft.drinkTea.framework.utils;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.wxsoft.drinkTea.framework.properties.DomainProperties;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import sun.misc.BASE64Encoder;

/**
 * 文件处理类
 *
 * @author ycb
 *
 */
public class FileUtils {

	private static Properties properties;

	/**
	 * 获取domain.properties中的value
	 *
	 * @param key
	 * @return
	 */
	public static String getParamConf(String key) {
		InputStream inputStream;
		try {
			if (properties == null) {
				inputStream = new BufferedInputStream(new FileInputStream(
						Thread.currentThread().getContextClassLoader().getResource("").getPath().replace("%20", " ")
								+ "/conf/domain.properties"));
				properties = new Properties();
				properties.load(inputStream);
			}
			return StringUtils.toString(properties.get(key));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据路径读取properties文件，并放到Map中返回。
	 *
	 * @param path
	 * @return
	 */
	public static Properties getProperties(String path) {
		InputStream inputStream;
		try {
			inputStream = new BufferedInputStream(new FileInputStream(
					Thread.currentThread().getContextClassLoader().getResource("").getPath() + path));
			Properties p = new Properties();
			p.load(inputStream);

			return p;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取文件绝对保存路径
	 *
	 * @param pathKey
	 * @return
	 */
	public static String getAbsolutePath(String relativePath) {
		String path = Thread.currentThread().getContextClassLoader().getResource("").getPath()
				.replace("/WEB-INF/classes", "") + relativePath;
		if (!new File(path).isDirectory()) {
			new File(path).mkdirs();
		}
		return path;
	}
	/**
	 * 获取文件绝对保存路径
	 *
	 * @param pathKey
	 * @return
	 */
	public static String getImgAbsolutePath() {
		String path = Thread.currentThread().getContextClassLoader().getResource("").getPath()
				.replace("/WEB-INF/classes", "") + getImgRelativePath();
		if (!new File(path).isDirectory()) {
			new File(path).mkdirs();
		}
		return path;
	}

	/**
	 * 获取文件相对保存路径
	 *
	 * @param pathKey
	 * @return
	 */
	public static String getImgRelativePath() {
		String savePath = DomainProperties.PATH_IMG_MAIN;
		if (!savePath.startsWith("/")) {
			savePath = "/" + savePath;
		}
		if (!savePath.endsWith("/")) {
			savePath = savePath + "/";
		}
		String path = savePath + DateUtils.getCurrentDateFormat("yyyy/MM/dd") + "/";
		return path;
	}

	/**
	 * 根据文件路径获取文件名
	 *
	 * @param path
	 * @return
	 */
	public static String getFileNameByPath(String path) {
		if (path != null) {
			String temp[] = path.replaceAll("\\\\", "/").split("/");
			String fileName = temp[temp.length - 1];
			return fileName;
		}
		return path;
	}

	/**
	 * 上传图片
	 *
	 * @param myfile
	 * @param picdir
	 * @return
	 */
	public static String uploadpic(MultipartFile myfile) {
		String relativePath = FileUtils.getImgRelativePath();
		String absolutePath = FileUtils.getAbsolutePath(relativePath);

		String filename = createFileName(myfile);
		try {
			org.apache.commons.io.FileUtils.copyInputStreamToFile(myfile.getInputStream(),
					new File(absolutePath, filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return relativePath + "/" + filename;

	}

	/**
	 * 上传图片
	 *
	 * @param myfile
	 * @param picdir
	 * @return
	 */
	public static String uploadpicByShow(MultipartFile myfile, String picdir, Integer... edges) {
		String filename = FileUtils.createFileName(myfile);
		String relativePath = FileUtils.getImgRelativePath();
		String absolutePath = FileUtils.getAbsolutePath(relativePath);
		for (Integer edge : edges) {
			try {
				org.apache.commons.io.FileUtils.copyInputStreamToFile(myfile.getInputStream(),
						new File(absolutePath, filename));
				BufferedImage sourceImg = ImageIO.read(myfile.getInputStream());
				Double height = (double) sourceImg.getHeight();
				Double width = (double) sourceImg.getWidth();
				if (width > height) {
					height = height * (edge / width);
					width = edge * 1.0;
				} else {
					width = width * (edge / height);
					height = edge * 1.0;
				}
				String imgpath = absolutePath + "/" + filename;
				int quality = 85;
				createThumbnailByPShow(imgpath, width.intValue(), height.intValue(), quality, imgpath, edge);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return relativePath + "/" + filename;
	}

	/**
	 * 上传图片base64
	 *
	 * @param myfile
	 * @param picdir
	 * @return
	 */
	public static String uploadpicByBase64(MultipartFile myfile, int width, int height) {
		String filename = FileUtils.createFileName(myfile);
		String relativePath = FileUtils.getImgRelativePath();
		String absolutePath = FileUtils.getAbsolutePath(relativePath);
		String baseStr = "";
		String imgpath = absolutePath + "/" + filename;
		try {
			org.apache.commons.io.FileUtils.copyInputStreamToFile(myfile.getInputStream(),
					new File(absolutePath, filename));
			/*
			 * BufferedImage sourceImg = ImageIO.read(myfile.getInputStream());
			 * Double height = (double) sourceImg.getHeight(); Double width =
			 * (double) sourceImg.getWidth(); if (width > height) { height =
			 * height * (edge / width); width = edge * 1.0; } else { width =
			 * width * (edge / height); height = edge * 1.0; }
			 */

			int quality = 85;
			createThumbnailByPShow(imgpath, width, height, quality, imgpath, width);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			File file = new File(imgpath);
			;
			FileInputStream inputFile = new FileInputStream(imgpath);
			byte[] buffer = new byte[(int) file.length()];
			inputFile.read(buffer);
			inputFile.close();
			baseStr = new BASE64Encoder().encode(buffer);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		return baseStr;
	}

	/**
	 * 上传图片
	 *
	 * @param myfile
	 * @param picdir
	 * @return
	 */
	public static String uploadpicByRectangle(MultipartFile myfile, String picdir, Integer[] widthTemp,
			Integer[] heightTemp) {
		String filename = FileUtils.createFileName(myfile);
		String relativePath = FileUtils.getImgRelativePath();
		String absolutePath = FileUtils.getAbsolutePath(relativePath);
		for (int i = 0; i < widthTemp.length; i++) {
			try {
				org.apache.commons.io.FileUtils.copyInputStreamToFile(myfile.getInputStream(),
						new File(absolutePath, filename));
				BufferedImage sourceImg = ImageIO.read(myfile.getInputStream());
				Double height = (double) sourceImg.getHeight();
				Double width = (double) sourceImg.getWidth();
				if (width > height) {
					height = height * (widthTemp[i] / width);
					width = widthTemp[i] * 1.0;
				} else {
					width = width * (heightTemp[i] / height);
					height = heightTemp[i] * 1.0;
				}
				String imgpath = absolutePath + "/" + filename;
				int quality = 85;
				createThumbnailByRectangle(imgpath, width.intValue(), height.intValue(), quality, imgpath, widthTemp[i],
						heightTemp[i]);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return relativePath + "/" + filename;
	}

	/**
	 *
	 *
	 * @param fileNames
	 *            原文件名
	 * @param thumbWidth
	 *            目标宽度
	 * @param thumbHeight
	 *            目标高度
	 * @param quality
	 *            质量 1-100 数值越大，质量越高，size越大
	 * @param outFileName
	 *            缩略图名称
	 * @param widthTemp
	 * @param heightTemp
	 * @return
	 */
	public static String createThumbnailByRectangle(String fileNames, int thumbWidth, int thumbHeight, int quality,
			String outFileName, Integer widthTemp, Integer heightTemp) {
		String returnfile;
		File file = new File(fileNames);
		String fileName = file.getName();
		/** 拼成完整的文件保存路径加文件 **/
		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
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
		// 如果用户没有指定，则根据数据库配置生成缩略图大小
		BufferedImage thumbImage = new BufferedImage(widthTemp, heightTemp, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2D = thumbImage.createGraphics();

		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2D.setBackground(Color.WHITE);
		graphics2D.clearRect(0, 0, widthTemp, heightTemp);// 通过使用当前绘图表面的背景色进行填充来清除指定的矩形。
		graphics2D.drawImage(image, (widthTemp - thumbWidth) / 2, (heightTemp - thumbHeight) / 2, thumbWidth,
				thumbHeight, null);
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
		param.setQuality((float) quality / 100.0f, false);
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
	 * java生成缩略图 个人秀
	 *
	 * @param fileNames
	 *            原文件名
	 * @param thumbWidth
	 *            目标宽度
	 * @param thumbHeight
	 *            目标高度
	 * @param quality
	 *            质量 1-100 数值越大，质量越高，size越大
	 * @param outFileName
	 *            缩略图名称
	 * @param backgroundEdge
	 * @return 生成的缩略图路径
	 */
	public static String createThumbnailByPShow(String fileNames, int thumbWidth, int thumbHeight, int quality,
			String outFileName, int backgroundEdge) {
		String returnfile;
		File file = new File(fileNames);
		String fileName = file.getName();
		/** 拼成完整的文件保存路径加文件 **/
		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
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
		BufferedImage thumbImage = new BufferedImage(backgroundEdge, backgroundEdge, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2D = thumbImage.createGraphics();

		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2D.setBackground(Color.WHITE);
		graphics2D.clearRect(0, 0, backgroundEdge, backgroundEdge);// 通过使用当前绘图表面的背景色进行填充来清除指定的矩形。
		graphics2D.drawImage(image, (backgroundEdge - thumbWidth) / 2, (backgroundEdge - thumbHeight) / 2, thumbWidth,
				thumbHeight, null);
		BufferedOutputStream out = null;

		File fileNew = new File(newFile);
		if (!fileNew.exists()) {
			try {
				fileNew.createNewFile();
			} catch (IOException e) {
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
		param.setQuality((float) quality / 100.0f, false);
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
	 * 生成随机新文件名。
	 *
	 * @param file
	 * @return
	 */
	public static String createFileName(MultipartFile file) {
		String fileName = file.getOriginalFilename();
		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String newName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
		return newName;
	}

	/**
	 *
	 * @param imgurl
	 * @param size
	 * @return
	 */
	public static String imgZuhe(String imgurl, String size) {
		String fisrt = imgurl.substring(0, imgurl.indexOf('.'));
		String end = imgurl.substring(imgurl.indexOf('.'), imgurl.length());
		return fisrt + size + end;
	}

	public static void writeFile(Template temp, String filename, Map<String, Object> root) {
		File file = new File(filename);
		FileOutputStream out;
		try {
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			out = new FileOutputStream(filename);
			try {
				temp.process(root, new OutputStreamWriter(out, "utf-8"));
			} catch (TemplateException e) {
				e.printStackTrace();
			}
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 复制
	 *
	 * @param resFilePath
	 *            源文件路径
	 * @param distFolder
	 *            目标文件夹
	 * @IOException 当操作发生异常时抛出
	 */
	public static void cpFile(String resFilePath, String distFolder) throws IOException {
		File resFile = new File(resFilePath);
		File distFile = new File(distFolder);
		org.apache.commons.io.FileUtils.copyFile(resFile, distFile);
	}
}
