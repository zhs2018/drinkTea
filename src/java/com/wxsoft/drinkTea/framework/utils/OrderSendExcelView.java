package com.wxsoft.drinkTea.framework.utils;

import java.util.Date;
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
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.wxsoft.drinkTea.platform.system.order.model.ProductOrder;


public class OrderSendExcelView extends AbstractExcelView {
           
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
		sheet = workbook.createSheet("订单导出");

		HSSFCellStyle headerStyle1 = workbook.createCellStyle(); // 标题样式
		headerStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerStyle1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		HSSFFont headerFont1 = workbook.createFont(); // 标题字体
		headerFont1.setFontHeightInPoints((short) 20);
		headerStyle1.setFont(headerFont1);

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
			cell = getCell(sheet, 0, i);
			cell.setCellStyle(headerStyle);
			setText(cell, title);
		}
		sheet.getRow(0).setHeight(height);

		HSSFCellStyle contentStyle = workbook.createCellStyle(); // 内容样式
		contentStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		List<ProductOrder> orderlist = (List<ProductOrder>) model.get("orResultlist");
		int userCount = orderlist.size();
		for (int i = 0; i < userCount; i++) {
			ProductOrder order = orderlist.get(i);
			int count = 0;
		//	String[] titles = { "订单编号", "商品名称", "商品单价", "商品数量", "商品总价格", "下单时间", "收货人姓名", "联系方式", "买家留言" };
			cell = getCell(sheet, i + 1, count);
			cell.setCellStyle(contentStyle);
			setText(cell, order.getOrderNumber()+"");
			count++;

			cell = getCell(sheet, i + 1, count);
			cell.setCellStyle(contentStyle);
			setText(cell, order.getManageProducts().getName());
			count++;

			cell = getCell(sheet, i + 1, count);
			cell.setCellStyle(contentStyle);
			setText(cell, order.getManageProducts().getNowPrice()+"");
			count++;

			cell = getCell(sheet, i + 1, count);
			cell.setCellStyle(contentStyle);
			setText(cell, order.getCount()+"");
			count++;

			cell = getCell(sheet, i + 1, count);
			cell.setCellStyle(contentStyle);
			setText(cell, order.getPrice()+"");
			count++;

			cell = getCell(sheet, i + 1, count);
			cell.setCellStyle(contentStyle);
			setText(cell, order.getOrderTime()+"");
			count++;

			cell = getCell(sheet, i + 1, count);
			cell.setCellStyle(contentStyle);
			setText(cell, order.getUserAddress().getUserName());
			count++;

			cell = getCell(sheet, i + 1, count);
			cell.setCellStyle(contentStyle);
			setText(cell, order.getUserAddress().getTelPhone()+"");
			count++;

			cell = getCell(sheet, i + 1, count);
			cell.setCellStyle(contentStyle);
			setText(cell, order.getCustomerMessage());
			count++;
		}

	}
}
