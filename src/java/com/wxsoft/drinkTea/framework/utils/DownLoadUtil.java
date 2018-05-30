package com.wxsoft.drinkTea.framework.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.filechooser.FileSystemView;

public class DownLoadUtil {

	public static boolean downLoadFile(String filePath, HttpServletRequest request,
		    HttpServletResponse response, String fileName, String fileType)
		    {
		try {
			InputStream fis = new BufferedInputStream(new FileInputStream(filePath));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			response.reset();
			response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("gbk"),"ISO-8859-1"));
			//response.addHeader("Content-Length", "" + excelFile.length());
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/octet-stream");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
//		String str = "attachment;filename=";
//		String name="";
//		response.setCharacterEncoding("UTF-8");
//		response.setContentType("application/vnd.ms-excel");
//		InputStream fis = null;
//		try {
//		        name = java.net.URLEncoder.encode(fileName + ".xlsx","UTF-8");
//		        response.addHeader("Content-Disposition", str + new String(name.getBytes("UTF-8"),"UTF-8"));
//		        fis = new FileInputStream(downFile);
//			request.setCharacterEncoding("utf8");
//			byte[] bytes = new byte[1024];
//			while(fis.read(bytes) != -1 ) {
//			        response.getOutputStream().write(bytes);
//		        }
//			response.getOutputStream().flush();
//			response.getOutputStream().close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if(fis != null){
//				try{fis.close();}catch(Exception e){}
//			}



//		File files = new File(filePath);
//
//
//		File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
//		System.out.println("输出一下1："+desktopDir);
//		String desktopPath = desktopDir.getAbsolutePath();
//		System.out.println("输出一下2："+desktopPath);
//		String desktopDirPath = desktopPath.replace("\\", "\\\\");
//		String filePaths = desktopDirPath + "\\" + fileName + ".xls";
//		    File file = new File(filePath);//根据文件路径获得File文件
//		    //设置文件类型(这样设置就不止是下Excel文件了，一举多得)
//		    response.setCharacterEncoding("UTF-8");
//		    if("pdf".equals(fileType)){
//		       response.setContentType("application/pdf;charset=GBK");
//		    }else if("xls".equals(fileType)){
//		       response.setContentType("application/vnd.ms-excel");
//		    }else if("doc".equals(fileType)){
//		       response.setContentType("application/msword;charset=GBK");
//		    }
//		    //文件名
//		   response.setContentLength((int) file.length());
//		    byte[] buffer = new byte[4096];// 缓冲区
//		    BufferedOutputStream output = null;
//		    BufferedInputStream input = null;
//		    try {
//		    	 output = new BufferedOutputStream(new FileOutputStream(file));
//		      // output = new BufferedOutputStream(response.getOutputStream());
//		        input = new BufferedInputStream(new FileInputStream(files));
//		        int n = -1;
//		        //遍历，开始下载
//		        while ((n = input.read(buffer, 0, 4096)) > -1) {
//		            output.write(buffer, 0, n);
//		        }
//		        output.flush();   //不可少
//		        response.flushBuffer();//不可少
//		    } catch (Exception e) {
//		        //异常自己捕捉
//		    } finally {
//		        //关闭流，不可少
//		        if (input != null)
//		            input.close();
//		        if (output != null)
//		           output.close();
//		    }
//		    return false;
//		}

