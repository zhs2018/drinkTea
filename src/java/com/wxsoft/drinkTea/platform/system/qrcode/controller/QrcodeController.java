package com.wxsoft.drinkTea.platform.system.qrcode.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.wxsoft.drinkTea.framework.base.BaseAction;
import com.wxsoft.drinkTea.framework.utils.OrderSendExcelView;
import com.wxsoft.drinkTea.framework.utils.QrcodeUtils;
import com.wxsoft.drinkTea.platform.system.login.model.ManageProducts;
import com.wxsoft.drinkTea.platform.system.login.model.SysUser;
import com.wxsoft.drinkTea.platform.system.order.model.GoodsOrder;
import com.wxsoft.drinkTea.platform.system.order.model.ProductOrder;
import com.wxsoft.drinkTea.platform.system.qrcode.mapper.PackageSubjectMapper;
import com.wxsoft.drinkTea.platform.system.qrcode.mapper.SmallPackageMapper;
import com.wxsoft.drinkTea.platform.system.qrcode.model.PackageSubject;
import com.wxsoft.drinkTea.platform.system.qrcode.model.SmallPackage;
import com.wxsoft.drinkTea.platform.system.qrcode.service.QrcodeService;
import com.wxsoft.drinkTea.platform.system.sysconfig.mapper.SubjectKuMapper;
import com.wxsoft.drinkTea.platform.system.sysconfig.model.SubjectKu;
import com.wxsoft.drinkTea.platform.weixin.mapper.SubOptionMapper;
import com.wxsoft.drinkTea.platform.weixin.model.SubOption;
import com.wxsoft.drinkTea.platform.weixin.model.UserAddress;

/**
 * 小包管理的controller com.wxsoft.drinkTea.platform.system.qrcode.controller
 * 2017年3月30日上午8:24:31
 */
@Controller
@RequestMapping("/system/qrcode")
public class QrcodeController extends BaseAction {

	/** */
	private static final long serialVersionUID = 1L;

	@Autowired
	private QrcodeService qrodeService;

	@Autowired
	private SmallPackageMapper smallPackageMapper;

	@Autowired
	private PackageSubjectMapper packageSubejectMapper;

	@Autowired
	private SubjectKuMapper subjectKuMapper;

	@Autowired
	private SubOptionMapper subOptionMapper;

	/**
	 * @Title: smallPackageList
	 * @return 返回类型：ModelAndView
	 * @Description:跳转到小包列表，需要把每个小包的题号显示出来。
	 */
	@RequestMapping("/smallPackage")
	public ModelAndView smallPackageList(SmallPackage param) {
		ModelAndView mav = new ModelAndView("/system/qrcode/smallPackage");
		// 查出所有的小包
		param.setVisable(1);
		List<SmallPackage> list = smallPackageMapper.getPageListBy(param);
		// 遍历所有的小包集合，取到每个小包的id，通过它找到题库id
		Integer m = null;
		for (SmallPackage one : list) {
			PackageSubject ps = new PackageSubject();
			ps.setSmlPakId(Integer.parseInt(one.getFalseId()));
			List<PackageSubject> pss = packageSubejectMapper.getListBy(ps);
			for (PackageSubject p : pss) {
				p.getSubId();
				one.setQuestionNum(p.getSubId());
			}
		}
		mav.addObject("smallPackageList", list);
		mav.addObject("su", param);
		return mav;
	}

	/**
	 * @Title: addSmallPackage
	 * @return 返回类型：ModelAndView
	 * @Description:跳转到添加二维码页面，需要显示所有的题目
	 */
	@RequestMapping("/addSmallPackage")
	public ModelAndView addSmallPackage(HttpSession session) {
		ModelAndView mav = new ModelAndView("/system/qrcode/addSmallPackage");
		// 查询所有的题目
		List<SubjectKu> list = subjectKuMapper.getListBy(new SubjectKu());
		// 给所有题目排序，升序排列，题号小的在前头显示
		Collections.sort(list, new Comparator<SubjectKu>() {
			@Override
			public int compare(SubjectKu o1, SubjectKu o2) {
				return o1.getId() - o2.getId();
			}
		});
		session.setAttribute("subjectList", list);
		return mav;
	}

	/**
	 * @Title: saveSmallPackage
	 * @return 返回类型：void
	 * @Description:保存添加的小包二维码
	 */
	@RequestMapping("/saveSmallPackage")
	@ResponseBody
	public void saveSmallPackage(HttpSession session, HttpServletResponse response, HttpServletRequest request,
			SmallPackage sp) {
		Map<String, String> map = new HashMap<String, String>();// 存放返回前台的信息
		if (sp != null) {
			sp.setCreateBy(((SysUser) session.getAttribute("sessionUser")).getId());
			if (sp.getFalseId() != "") {
				int result = qrodeService.update(sp);
				if (result == 4) {
					map.put("status", "1");
					map.put("Message", "修改成功");
				} else {
					map.put("status", "2");
					map.put("Message", "修改小包二维码信息失败");
				}
			} else {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				String ms = sdf.format(new Date());
				String savePath = request.getSession().getServletContext().getRealPath("/") + "/attached/"+ms+".png";
				sp.setUrl(savePath);
				int result = qrodeService.save(sp);
				SmallPackage sps =	smallPackageMapper.selectBy(sp);
				String text = "http://chawenhua.tunnel.echomod.cn/drinkTea/answer/index?id=" + sps.getId();
			        int width = 100;
			        int height = 100;
			        String format = "png";
			        Hashtable hints= new Hashtable();
			        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
			         BitMatrix bitMatrix;
					try {
						bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height,hints);
						File outputFile = new File(savePath);
				         MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
					} catch (WriterException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}


				if (result == 4) {
					map.put("status", "1");
					map.put("Message", "保存成功");
				} else {
					map.put("status", "2");
					map.put("Message", "保存小包二维码信息失败");
				}
			}
		} else {
			map.put("status", "2");
			map.put("Message", "保存小包二维码信息失败");
		}
		try {
			responseAjax(map, response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: deleteSmallPackage
	 * @return 返回类型：void
	 * @Description: 删除一个小包二维码
	 */
	@RequestMapping("/deleteSmallPackage/{id}")
	public void deleteSmallPackage(@PathVariable Integer id, HttpServletResponse response) {
		Map<String, String> result = new HashMap<String, String>();// 存放返回前台的信息
		PackageSubject ps = new PackageSubject();
		ps.setSmlPakId(id);
		int b = packageSubejectMapper.delete(ps);
		int a = smallPackageMapper.deleteById(id);
		if (a < 1 || b < 1) {
			result.put("status", "0");
			result.put("Message", "删除二维码失败，请稍后再试");
		} else {
			result.put("status", "1");
		}
		try {
			responseAjax(result, response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: editSmallPackage
	 * @return 返回类型：ModelAndView
	 * @Description:跳到编辑页面
	 * @修改日期 2017.4.19
	 */
	@RequestMapping("/editSmallPackage/{id}")
	public ModelAndView editSmallPackage(HttpSession session, @PathVariable Integer id) {
		ModelAndView mav = new ModelAndView("/system/qrcode/addSmallPackage");
		logger.info("小包id：" + id);
		if (id != null) {
			SmallPackage smallPackage = smallPackageMapper.selectByPrimaryKey(id);
			mav.addObject("smlPk", smallPackage);
			if (smallPackage != null) {
				SubjectKu sk = new SubjectKu();
				List<SubjectKu> subList = subjectKuMapper.getListBy(sk);
				mav.addObject("subjectList", subList);
			}
		}
		return mav;
	}

	/**
	 * @Title: selectOptionBySubId
	 * @return 返回类型：void
	 * @Description:根据题目id找到所对应的所有选项
	 */
	@RequestMapping("/selectOptionBySubId/{subId}")
	public void selectOptionBySubId(@PathVariable Integer subId, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();// 存放返回前台的信息
		SubjectKu sk = subjectKuMapper.selectByPrimaryKey(subId);
		map.put("sk", sk);
		SubOption param = new SubOption();
		param.setSubId(subId);
		param.setSign(1);
		List<SubOption> list = subOptionMapper.getListBy(param);
		map.put("optionList", list);
		SubOption param1 = new SubOption();
		param1.setSubId(subId);
		param1.setSign(2);
		List<SubOption> list1 = subOptionMapper.getListBy(param1);
		map.put("optionList1", list1);
		SubOption param2 = new SubOption();
		param2.setSubId(subId);
		param2.setSign(3);
		List<SubOption> list2 = subOptionMapper.getListBy(param2);
		map.put("optionList2", list2);
		try {
			responseAjax(map, response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/toexcel")
	public ModelAndView toexcelbatch(HttpSession session, String ttime, HttpServletRequest request) {
		SmallPackage smallPackage = new SmallPackage();
		String start = ttime.split("~")[0].trim();
		String end = ttime.split("~")[1].trim();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date1;
		Date date2;
		try {
			date1 = dateFormat.parse(start);
			date2 = dateFormat.parse(end);
			smallPackage.setStartTime(date1);
			smallPackage.setEndTime(date2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    List<SmallPackage> orResultlist = smallPackageMapper.getListByTime(smallPackage);
    List<BufferedImage> images = new ArrayList<BufferedImage>();
    for (SmallPackage smallPackage2 : orResultlist) {
    	try {
			images.add(ImageIO.read(new File(smallPackage2.getUrl())));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    FileOutputStream fileOut = null;
    try {
    // 创建一个工作薄
    HSSFWorkbook wb = new HSSFWorkbook();
    HSSFSheet sheet1 = wb.createSheet("new sheet");
    // HSSFRow row = sheet1.createRow(2);
    HSSFPatriarch patriarch = sheet1.createDrawingPatriarch();
    short i = 0;
    for (BufferedImage image : images) {
    ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
    ImageIO.write(image, "jpg", byteArrayOut);
    HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 0, (short) 1, 1 + i, (short) 2, 2 + i);
    anchor.setAnchorType(0);
    // 插入图片
    patriarch.createPicture(anchor, wb.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
    i++;
    }
    HSSFRow row = sheet1.createRow(10);
    short s = 10;
    HSSFCell cell = row.createCell(s);
    HSSFCellStyle style = wb.createCellStyle();
    HSSFFont font = wb.createFont();
    font.setStrikeout(true);
    style.setFont(font);
    cell.setCellStyle(style);
    cell.setCellValue("aaaaa");
    fileOut = new FileOutputStream("d:/workbook.xls");
    // 写入excel文件
    wb.write(fileOut);
    fileOut.close();
    } catch (IOException io) {
    io.printStackTrace();
    System.out.println("io erorr : " + io.getMessage());
    } finally {
    if (fileOut != null) {
    try {
    fileOut.close();
    } catch (IOException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
    }
    }
    }

	return null;


//		List<String> headtitle = new ArrayList<String>();// 标题
//		Map<String, Object> dataMap = new HashMap<String, Object>();
//		headtitle.add("订单编号");
//		headtitle.add("商品名称");
//		headtitle.add("商品单价");
//		headtitle.add("商品数量");
//		headtitle.add("商品总价格");
//		headtitle.add("下单时间");
//		headtitle.add("收货人姓名");
//		headtitle.add("联系方式");
//		headtitle.add("买家留言");
//		dataMap.put("titles", headtitle);
//		OrderSendExcelView erv = null;
//		ModelAndView mv = null;
//		erv = new OrderSendExcelView();
//		dataMap.put("orResultlist", orResultlist);
//		mv = new ModelAndView(erv, dataMap);

	}


}
