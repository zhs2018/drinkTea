package com.wxsoft.drinkTea.framework.utils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.springframework.web.servlet.view.document.AbstractExcelView;

public class CaiWuExcelView extends AbstractExcelView {

	private static Map<String, String> mapsCode;

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Date date = new Date();
		String filename = Tools.date2Str(date, "yyyyMMddHHmmss");
		HSSFSheet sheet;
		HSSFCell cell;
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ filename + ".xls");
		sheet = workbook.createSheet("销售资金记录");

		HSSFCellStyle headerStyle1 = workbook.createCellStyle(); // 标题样式
		headerStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerStyle1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		HSSFFont headerFont1 = workbook.createFont(); // 标题字体
		headerFont1.setFontHeightInPoints((short) 20);
		headerStyle1.setFont(headerFont1);

		sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 5));// 指定合并区域
		cell = getCell(sheet, 0, 0);
		cell.setCellStyle(headerStyle1);
		setText(cell,  model.get("date") +"月 销售资金记录");

		List<String> titles = (List<String>) model.get("titles");

		int len = titles.size();
		HSSFCellStyle headerStyle = workbook.createCellStyle(); // 标题样式
		headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		headerStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		HSSFFont headerFont = workbook.createFont(); // 标题字体
		headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headerFont.setFontHeightInPoints((short) 11);
		headerStyle.setFont(headerFont);
		short width = 20, height = 25 * 20;
		sheet.setDefaultColumnWidth(width);
		for (int i = 0; i < len; i++) { // 设置标题
			String title = titles.get(i);
			if (title == null || title.equals("")) {
				title = "备注";
			}
			cell = getCell(sheet, 1, i);
			cell.setCellStyle(headerStyle);
			setText(cell, title);
		}
		sheet.getRow(1).setHeight(height);

		HSSFCellStyle contentStyle = workbook.createCellStyle(); // 内容样式
		contentStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		List<Double> orderlist = (List<Double>) model.get("orResultlist");
		int userCount = orderlist.size();
		for (int i = 0; i < userCount; i++) {
			String content = null;
			cell = getCell(sheet,2, i);
			cell.setCellStyle(contentStyle);
			setText(cell,orderlist.get(i) + "元");
		}

	}
}
