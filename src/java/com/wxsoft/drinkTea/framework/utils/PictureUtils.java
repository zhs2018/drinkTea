package com.wxsoft.drinkTea.framework.utils;

import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class PictureUtils {

	private Font font = new Font("微软雅黑", Font.PLAIN, 34);// 添加字体的属性设置
//	private Font font = new Font("",Font.PLAIN, 34);// 添加字体的属性设置

	private Graphics2D g = null;
	private Graphics2D g2 = null;

	private int fontsize = 0;

	private int x = 0;

	private int y = 0;
	/**
	 * 导入本地图片到缓冲区
	 */
	public BufferedImage loadImageLocal(String imgName) {
		try {
			return ImageIO.read(new File(imgName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 导入网络图片到缓冲区
	 */
	public BufferedImage loadImageUrl(String imgName) {
		try {
			URL url = new URL(imgName);
			return ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 生成新图片到本地
	 */
	public void writeImageLocal(String newImage, BufferedImage img) {
		if (newImage != null && img != null) {
			try {
				File outputfile = new File(newImage);
				ImageIO.write(img, "jpg", outputfile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 设定文字的字体等
	 */
	public void setFont(String fontStyle, int fontSize) {
		this.fontsize = fontSize;
		this.font = new Font(fontStyle, Font.PLAIN, fontSize);
	}

	/**
	 * 修改图片,返回修改后的图片缓冲区（只输出一行文本）
	 */
	public BufferedImage modifyImage(BufferedImage img, Object content,
			Object company, int x, int y) {

		try {
			int w = img.getWidth();
			int h = img.getHeight();
			System.out.println("背景宽高:"+w+":"+h);
			g = img.createGraphics();
			g2 = img.createGraphics();
			g.setBackground(Color.WHITE);
			g2.setBackground(Color.WHITE);
			g.setColor(Color.red);// 设置字体颜色
			g2.setColor(Color.black);// 设置字体颜色
			if (this.font != null) {
				g.setFont(this.font);
				g2.setFont(this.font);
			}
			// 验证输出位置的纵坐标和横坐标
			if (x >= h || y >= w) {
				this.x = h - this.fontsize + 2;
				this.y = w;
			} else {
				this.x = x;
				this.y = y;
			}
			if (content != null) {
				g2.drawString("我是", this.x + 20, this.y + 34);
				g.drawString(content.toString(), this.x + 20 + 34*2 + 10, this.y + 34);
				g2.drawString("我为", this.x +20, this.y + 40 + 40);
				g.drawString(company.toString(), this.x + 20 + 34*2 + 10, this.y + 40 + 40 );
				g2.drawString("代言",
						this.x + 20 + 34*2 + 10 + (company.toString().length()) * 34 +10,
						this.y + 40 + 40);
			}
			g.dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return img;
	}

	/**
	 * 修改图片,返回修改后的图片缓冲区（输出多个文本段） xory：true表示将内容在一行中输出；false表示将内容多行输出
	 */
	public BufferedImage modifyImage(BufferedImage img, Object[] contentArr,
			int x, int y, boolean xory) {
		try {
			int w = img.getWidth();
			int h = img.getHeight();
			System.out.println("背景图宽高："+w+":"+h);
			g = img.createGraphics();
			g.setBackground(Color.WHITE);
			g.setColor(Color.RED);
			if (this.font != null)
				g.setFont(this.font);
			// 验证输出位置的纵坐标和横坐标
			if (x >= h || y >= w) {
				this.x = h - this.fontsize + 2;
				this.y = w;
			} else {
				this.x = x;
				this.y = y;
			}
			if (contentArr != null) {
				int arrlen = contentArr.length;
				if (xory) {
					for (int i = 0; i < arrlen; i++) {
						g.drawString(contentArr[i].toString(), this.x, this.y);
						this.x += contentArr[i].toString().length()
								* this.fontsize / 2 + 5;// 重新计算文本输出位置
					}
				} else {
					for (int i = 0; i < arrlen; i++) {
						g.drawString(contentArr[i].toString(), this.x, this.y);
						this.y += this.fontsize + 2;// 重新计算文本输出位置
					}
				}
			}
			g.dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return img;
	}

//	/**
//	 * 修改图片,返回修改后的图片缓冲区（只输出一行文本）
//	 *
//	 * 时间:2007-10-8
//	 *
//	 * @param img
//	 * @return
//	 */
//	public BufferedImage modifyImageYe(BufferedImage img) {
//
//		try {
//			int w = img.getWidth();
//			int h = img.getHeight();
//			g = img.createGraphics();
//			g.setBackground(Color.WHITE);
//			g.setColor(Color.blue);// 设置字体颜色
//			if (this.font != null)
//				g.setFont(this.font);
//			g.drawString("www.hi.baidu.com?xia_mingjian", w - 85, h - 5);
//			g.dispose();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return img;
//	}

	public BufferedImage modifyImagetogeter(BufferedImage b, BufferedImage c,
			BufferedImage d) {

		try {
			int w = b.getWidth();
			int h = b.getHeight();

			int wc = 80;
			int hc = 80;

			Graphics2D graphics2d = d.createGraphics();
			graphics2d.setColor(Color.white);
			graphics2d.fillRect(234, 880, 280, 280);
			graphics2d.dispose();
			g = d.createGraphics();
			g.drawImage(b, 234+15, 880+15, w, h, null);
			g.drawImage(c, 80, 44, wc, hc, null);
			g.dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return d;
	}

	/**
	 *
	 * @param url
	 *            连接地址
	 * @param userName
	 *            用户昵称
	 * @param companyName
	 *            公司名称
	 * @return
	 */
	public static String getPic(String url, String userName,
			String companyName, String demoPic, String userPic) {
		System.out.println("url:" + url);
		System.out.println("userName:" + userName);
		System.out.println("companyName:" + companyName);
		System.out.println("demoPic:" + demoPic);
		System.out.println("userPic:" + userPic);

		String fileName = String.valueOf(System.nanoTime());
		String imgPath = "/mnt/temp/" + fileName + ".png";
		String imgPath2 = "/mnt/temp/" + fileName + "last.png";
		int width = 250, height = 250;
		QrcodeUtils.encode(url, width, height, imgPath);
		PictureUtils tt = new PictureUtils();
		BufferedImage d = tt.loadImageUrl(demoPic);
//		BufferedImage d = tt.loadImageLocal(demoPic);
		tt.writeImageLocal(imgPath2,
				tt.modifyImage(d, userName, companyName, 160, 44)
		);
		System.out.println("1success");
		BufferedImage b = tt.loadImageLocal(imgPath);
		BufferedImage c = tt.loadImageUrl(userPic);
		tt.writeImageLocal(imgPath2, tt.modifyImagetogeter(b, c, d));
		// 将多张图片合在一起
		System.out.println("2success");
		return imgPath2;
	}
//	public static void main(String[] args) {
//		String url = "http://weixin.qq.com/q/021u2Rh1jGcX210000g07H";
//		String demoPic = "E://fenxiangbg.png";
//		String userImg = "http://wx.qlogo.cn/mmopen/o1QQlnDibY5EMicvJ2cjjuaRSGee5gsiaeZj0CVKb4Hub54Sibj7icDDe06s6Jk9l6vbOcDDp14KqGr6Vz1u1N4tOMsczJfy3VO5F/0";
//		System.out.println(getPic(url, "杨振勇", "茶客茶道", demoPic, userImg));
//		File file = new File("WebContent/image/weixin/teaking/bg2.png");
//		System.out.println(file.getAbsolutePath());
//	}
}
