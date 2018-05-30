package com.wxsoft.drinkTea.platform.system.sysconfig.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.wxsoft.drinkTea.framework.base.BaseAction;
import com.wxsoft.drinkTea.framework.utils.DateUtils;
import com.wxsoft.drinkTea.framework.utils.KindeditorController;
import com.wxsoft.drinkTea.framework.utils.ThumbNailHelper;
import com.wxsoft.drinkTea.platform.system.catalog.mapper.ProvinceManageMapper;
import com.wxsoft.drinkTea.platform.system.catalog.mapper.SeasonManageMapper;
import com.wxsoft.drinkTea.platform.system.catalog.mapper.TypeManageMapper;
import com.wxsoft.drinkTea.platform.system.catalog.model.ProvinceManage;
import com.wxsoft.drinkTea.platform.system.catalog.model.SeasonManage;
import com.wxsoft.drinkTea.platform.system.catalog.model.TypeManage;
import com.wxsoft.drinkTea.platform.system.order.mapper.ProductOrderMapper;
import com.wxsoft.drinkTea.platform.system.order.model.ProductOrder;
import com.wxsoft.drinkTea.platform.system.sysconfig.mapper.SubjectKingMapper;
import com.wxsoft.drinkTea.platform.system.sysconfig.mapper.SubjectKuMapper;
import com.wxsoft.drinkTea.platform.system.sysconfig.model.SubjectKing;
import com.wxsoft.drinkTea.platform.system.sysconfig.model.SubjectKu;
import com.wxsoft.drinkTea.platform.system.sysconfig.service.SubjectService;
import com.wxsoft.drinkTea.platform.system.teaKing.mapper.RedMoneyMapper;
import com.wxsoft.drinkTea.platform.system.teaKing.model.RedMoney;
import com.wxsoft.drinkTea.platform.weixin.mapper.KingBaseInfoMapper;
import com.wxsoft.drinkTea.platform.weixin.mapper.KingUserAnswerMapper;
import com.wxsoft.drinkTea.platform.weixin.mapper.RedEnvelopeMapper;
import com.wxsoft.drinkTea.platform.weixin.mapper.SubOptionMapper;
import com.wxsoft.drinkTea.platform.weixin.mapper.WebUserMapper;
import com.wxsoft.drinkTea.platform.weixin.model.KingBaseInfo;
import com.wxsoft.drinkTea.platform.weixin.model.KingUserAnswer;
import com.wxsoft.drinkTea.platform.weixin.model.RedEnvelope;
import com.wxsoft.drinkTea.platform.weixin.model.SubOption;
import com.wxsoft.drinkTea.platform.weixin.model.WebUser;

@Controller
@RequestMapping("system/subject")
public class SubjectKuController extends BaseAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private SubjectKuMapper subjectKuMapper;
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private SubjectKingMapper subjectKingMapper;
	@Autowired
	private SubOptionMapper subOptionMapper;
	@Autowired
	private TypeManageMapper typeManageMapper;
	@Autowired
	private KingBaseInfoMapper kingBaseInfoMapper;
	@Autowired
	private KingUserAnswerMapper kingUserAnswerMapper;
	@Autowired
	private WebUserMapper webUserMapper;
	@Autowired
	private ProductOrderMapper productOrderMapper;
	@Autowired
	private ProvinceManageMapper provinceManageMapper;
	@Autowired
	private SeasonManageMapper seasonManageMapper;
	@Autowired
	private RedMoneyMapper redmoneyMapper;
	@Autowired
	private RedEnvelopeMapper redEnvelopeMapper;

	/**
	 * @Title 茶王争霸 添加页面点击跳转
	 * @date 2017-8-10 11:18
	 * @return ModelAndView
	 */
	@RequestMapping("/info")
	public ModelAndView jumptToJsp() {
		ModelAndView modelAndView = new ModelAndView("system/subject/subjectinfo");
		TypeManage typeManage = new TypeManage();
		typeManage.setIsVisable(1);
		List<TypeManage> typeManageList = typeManageMapper.getListBy(typeManage);
		modelAndView.addObject("list", typeManageList);
		return modelAndView;
	}

	/**
	 * @Title 扫码答题 添加页面点击跳转
	 * @date 2017-8-10 11:18
	 * @return ModelAndView
	 */
	@RequestMapping("/add")
	public ModelAndView jumptToJ() {
		ModelAndView modelAndView = new ModelAndView("system/subject/subjectadd");
		TypeManage typeManage = new TypeManage();
		typeManage.setIsVisable(1);
		List<TypeManage> typeManageList = typeManageMapper.getListBy(typeManage);
		modelAndView.addObject("typeList", typeManageList);

		ProvinceManage provinceManage = new ProvinceManage();
		provinceManage.setIsVisable(1);
		List<ProvinceManage> provinceManageList = provinceManageMapper.getListBy(provinceManage);
		modelAndView.addObject("provinceList", provinceManageList);

		SeasonManage seasonManage = new SeasonManage();
		provinceManage.setIsVisable(1);
		List<SeasonManage> seasonManageList = seasonManageMapper.getListBy(seasonManage);
		modelAndView.addObject("seasonList", seasonManageList);

		return modelAndView;
	}

	/**
	 * @Title 题库管理-扫码答题添加题目 保存题目信息、与更新操作
	 * @date 2017-8-10 11:18
	 * @return ModelAndView
	 */
	@RequestMapping("/savesubinfo")
	public String saveSubject(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("option") String[] arr, @RequestParam("id") Integer id,
			@RequestParam("option1") String[] arr1, @RequestParam("option2") String[] arr2,
			@RequestParam("type") Integer type, @RequestParam("name") String subName,
			@RequestParam("intro") String intro) {
		Map<String, String> result = new HashMap<String, String>();// 存放返回前台的信息
		// 该方法里面已知所有选项，答题类型，题目名称，首先需要根据type判断，如果是扫码答题，就将题目存入到subject_ku表，如果
		// 是茶王争霸，就将题目存到subject_king表中，然后把题目id、选项内容、type存入到sub_option表中
		if (type == 0) {
			SubjectKu ku = new SubjectKu();
			if (id != null) {
				ku.setName(subName);
				ku.setIntro(intro);
				ku.setId(id);
			} else {
				ku.setName(subName);
				ku.setIntro(intro);
			}

			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile myfile = multipartRequest.getFile("myfile");
			String originalFilename = null;
			originalFilename = myfile.getOriginalFilename();
			if (null != myfile && !myfile.isEmpty()) {
				try {
					String savePath = request.getSession().getServletContext().getRealPath("/") + "/attached/";
					String saveUrl = "/attached/";
					Map<String, String> maps = KindeditorController.mkdir(savePath, saveUrl, "catalog");
					String endName = originalFilename.substring(originalFilename.lastIndexOf("."),
							originalFilename.length());
					String filename = UUID.randomUUID() + endName;
					if (endName.equals(".png") || endName.equals(".gif") || endName.equals(".jpg")
							|| endName.equals(".jpeg")) {
						// 保存图片
						FileUtils.copyInputStreamToFile(myfile.getInputStream(),
								new File(maps.get("savePath"), filename));
						ThumbNailHelper.createThumbnailByRectangle(maps.get("savePath") + filename, 800, 600, 100,
								maps.get("savePath") + filename, 800, 600);
						ku.setImg(maps.get("saveUrl") + filename);
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (id != null) {
				int a = subjectKuMapper.updateByPrimaryKey(ku);
				int b = subjectService.updateOption(id, arr, 0, 1);
				int c = subjectService.updateOption(id, arr1, 0, 2);
				int d = subjectService.updateOption(id, arr2, 0, 3);
				if (a < 1 || b < 1 || c < 1 || d < 1) {
					result.put("status", "2");
					result.put("Message", "修改扫码答题失败");
				} else {
					result.put("status", "1");
					result.put("Message", "修改扫码答题成功");
				}
			} else {
				int a = subjectKuMapper.insert(ku);
				SubjectKu param = subjectKuMapper.selectBy(ku);
				if (param != null) {
					int b = subjectService.updateOption(param.getId(), arr, type, 1);
					int c = subjectService.updateOption(param.getId(), arr1, type, 2);
					int d = subjectService.updateOption(param.getId(), arr2, type, 3);
					if (a < 1 || b < 1 || c < 1 || d < 1) {
						result.put("status", "2");
						result.put("Message", "添加扫码答题失败");
					} else {
						result.put("status", "1");
						result.put("Message", "添加扫码答题成功");
					}
				} else {
					result.put("status", "2");
					result.put("Message", "添加扫码答题失败");
				}
			}
		}
		return "redirect:list";
	}

	@RequestMapping("/updateKinginfo")
	public String updateKinginfo(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("id") Integer id, @RequestParam("option") String[] arr, Integer answer) {
		Map<String, String> result = new HashMap<String, String>();// 存放返回前台的信息
		// 该方法里面已知所有选项，题目名称，题目id，根据id修改题目名称，然后根据删除原有题目id的选项，重新加入新选项
		SubjectKing king = new SubjectKing();
		king.setId(id);
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile myfile = multipartRequest.getFile("myfile");
		String originalFilename = null;
		originalFilename = myfile.getOriginalFilename();
		if (null != myfile && !myfile.isEmpty()) {
			try {
				String savePath = request.getSession().getServletContext().getRealPath("/") + "/attached/";
				String saveUrl = "/attached/";
				Map<String, String> maps = KindeditorController.mkdir(savePath, saveUrl, "catalog");
				String endName = originalFilename.substring(originalFilename.lastIndexOf("."),
						originalFilename.length());
				String filename = UUID.randomUUID() + endName;
				if (endName.equals(".png") || endName.equals(".gif") || endName.equals(".jpg")
						|| endName.equals(".jpeg")) {
					int b = subjectService.updateOption(id, arr, 1, 0);
					// 获取答案的id
					if (answer != -1) {
						SubOption subOption = new SubOption();
						subOption.setOption(arr[answer]);
						subOption.setSubId(id);
						subOption = subOptionMapper.selectBy(subOption);
						king.setAnswer(subOption.getId());
					}
					// 保存图片
					FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(maps.get("savePath"), filename));
					ThumbNailHelper.createThumbnailByRectangle(maps.get("savePath") + filename, 800, 600, 100,
							maps.get("savePath") + filename, 800, 600);
					king.setImg(maps.get("saveUrl") + filename);
					int a = subjectKingMapper.updateByPrimaryKey(king);
					if (a < 1 || b < 1) {
						result.put("status", "2");
						result.put("Message", "修改茶王争霸失败");
					} else {
						result.put("status", "1");
						result.put("Message", "修改茶王争霸成功");
					}

				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			int b = subjectService.updateOption(id, arr, 1, 0);
			// 获取答案的id
			int a = 2;
			if (answer != -1) {
				SubOption subOption = new SubOption();
				subOption.setOption(arr[answer]);
				subOption.setSubId(id);
				subOption = subOptionMapper.selectBy(subOption);
				king.setAnswer(subOption.getId());
				a = subjectKingMapper.updateByPrimaryKey(king);
			}
			if (a < 1 || b < 1) {
				result.put("status", "2");
				result.put("Message", "修改茶王争霸失败");
			} else {
				result.put("status", "1");
				result.put("Message", "修改茶王争霸成功");
			}
		}

		return "redirect:kingList";
	}

	@RequestMapping("/savesubinfoImg")
	public String savesubinfoImg(@RequestParam("option") String[] arr, HttpServletResponse response,
			HttpServletRequest request, String createTime, String intro, Integer type, String name) {
		if (type == 1) {
			SubjectKing king = new SubjectKing();
			king.setCreateTime(createTime);
			king.setIntro(intro);
			// 保存图片
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile myfile = multipartRequest.getFile("myfile");
			String originalFilename = null;
			originalFilename = myfile.getOriginalFilename();
			if (null != myfile && !myfile.isEmpty()) {
				try {
					String savePath = request.getSession().getServletContext().getRealPath("/") + "/attached/";
					String saveUrl = "/attached/";
					Map<String, String> maps = KindeditorController.mkdir(savePath, saveUrl, "catalog");
					String endName = originalFilename.substring(originalFilename.lastIndexOf("."),
							originalFilename.length());
					String filename = UUID.randomUUID() + endName;
					if (endName.equals(".png") || endName.equals(".gif") || endName.equals(".jpg")
							|| endName.equals(".jpeg")) {
						FileUtils.copyInputStreamToFile(myfile.getInputStream(),
								new File(maps.get("savePath"), filename));
						ThumbNailHelper.createThumbnailByRectangle(maps.get("savePath") + filename, 800, 600, 100,
								maps.get("savePath") + filename, 800, 600);
						king.setImg(maps.get("saveUrl") + filename);
						subjectKingMapper.insert(king);
						subjectService.updateOption(king.getId(), arr, 1, 0);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return "redirect:kingList";
		} else {
			SubjectKu ku = new SubjectKu();
			// 保存图片
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile myfile = multipartRequest.getFile("myfile");
			String originalFilename = null;
			originalFilename = myfile.getOriginalFilename();
			if (null != myfile && !myfile.isEmpty()) {
				try {
					String savePath = request.getSession().getServletContext().getRealPath("/") + "/attached/";
					String saveUrl = "/attached/";
					Map<String, String> maps = KindeditorController.mkdir(savePath, saveUrl, "catalog");
					String endName = originalFilename.substring(originalFilename.lastIndexOf("."),
							originalFilename.length());
					String filename = UUID.randomUUID() + endName;
					if (endName.equals(".png") || endName.equals(".gif") || endName.equals(".jpg")
							|| endName.equals(".jpeg")) {
						FileUtils.copyInputStreamToFile(myfile.getInputStream(),
								new File(maps.get("savePath"), filename));
						ThumbNailHelper.createThumbnailByRectangle(maps.get("savePath") + filename, 800, 600, 100,
								maps.get("savePath") + filename, 800, 600);
						ku.setImg(maps.get("saveUrl") + filename);

					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			subjectKuMapper.insert(ku);
			SubjectKu param = subjectKuMapper.selectBy(ku);
			if (param != null) {
				subjectService.updateOption(param.getId(), arr, type, 0);
			}

			return "redirect:list";
		}

	}

	/**
	 * @Title 题库管理-题目列表 查询所有题目
	 * @date 2017-8-10 11:18
	 * @author yzy
	 * @return ModelAndView
	 */
	@RequestMapping("/list")
	public ModelAndView findSubject(SubjectKu subinfo, Integer type) {
		ModelAndView modelAndView = new ModelAndView("system/subject/list");
		if (type != null) {
			if (type == 0) {
				List<SubjectKu> list = subjectKuMapper.getPageListLike(subinfo);
				modelAndView.addObject("subList", list);
				modelAndView.addObject("subInfo", subinfo);
			} else if (type == 1) {
				SubjectKing subjectKing = new SubjectKing();
				subjectKing.setSubNumber(subinfo.getSubNumber());
				subjectKing.setOrderBy(subinfo.getOrderBy());
				subjectKing.setPage(subinfo.getPage());
				List<SubjectKing> list = subjectKingMapper.getPageListLike(subjectKing);
				modelAndView.addObject("subList", list);
				modelAndView.addObject("subInfo", subjectKing);
			} else {
				modelAndView.addObject("result", "1");
				modelAndView.addObject("message", "类型选择错误！");
			}
		} else {
			type = 0;
			List<SubjectKu> list = subjectKuMapper.getPageListLike(subinfo);
			modelAndView.addObject("subList", list);
			modelAndView.addObject("subInfo", subinfo);
		}
		modelAndView.addObject("type", type);
		return modelAndView;
	}

	// 茶王争霸赛列表
	/**
	 * 2017-8-7修改
	 *
	 * @修改人
	 * @修改内容 将时间格式化 并显示到页面
	 * @return
	 */
	@RequestMapping("/kingList")
	public ModelAndView kingList(SubjectKing king) {
		ModelAndView modelAndView = new ModelAndView("system/subject/kingList");
		List<SubjectKing> list = subjectKingMapper.getPageListLike(king);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getCreateTime() != null) {
					list.get(i).setCreateTime(DateUtils.getDateFormat(
							DateUtils.getDateByFormat(list.get(i).getCreateTime(), "yyyyMMdd"), "yyyy年MM月dd日"));
				} else {
					list.get(i).setCreateTime("未添加");
				}
			}
		}
		KingBaseInfo kbi = new KingBaseInfo();
		kbi.setType(1);
		KingBaseInfo kingBi = kingBaseInfoMapper.selectBy(kbi);
		Date date = kingBi.getEndTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		String tm = sdf.format(date).substring(9);
		String time = sdf.format(date).substring(9, 11);
		String time1 = sdf.format(date).substring(12, 14);
		String time2 = sdf.format(date).substring(15, 17);
		modelAndView.addObject("subList", list);
		modelAndView.addObject("subInfo", king);
		modelAndView.addObject("sub", time);
		modelAndView.addObject("sub1", time1);
		modelAndView.addObject("sub2", time2);
		modelAndView.addObject("s", tm);
		return modelAndView;
	}

	/**
	 * @Title 题库管理-题目列表 编辑题目
	 * @date 2017-3-23 11:28
	 * @author yzy
	 * @return 返回查询到的题目信息到编辑页面
	 * @修改日期 2017.4.19
	 * @修改人 yzy
	 * @修改内容 修改....
	 *
	 */
	@RequestMapping(value = "/edit")
	public ModelAndView editSubject(Integer id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("system/subject/subjectadd");
		SubjectKu subinfo = new SubjectKu();
		subinfo = subjectKuMapper.selectByPrimaryKey(id);
		// SubOption record = new SubOption();
		// record.setSubId(subinfo.getId());
		// List<SubOption> options = subOptionMapper.getListBy(record);
		modelAndView.addObject("subInfo", subinfo);
		// modelAndView.addObject("options",options);
		modelAndView.addObject("id", id);
		TypeManage typeManage = new TypeManage();
		typeManage.setIsVisable(1);
		List<TypeManage> typeManageList = typeManageMapper.getListBy(typeManage);
		modelAndView.addObject("typeList", typeManageList);

		ProvinceManage provinceManage = new ProvinceManage();
		provinceManage.setIsVisable(1);
		List<ProvinceManage> provinceManageList = provinceManageMapper.getListBy(provinceManage);
		modelAndView.addObject("provinceList", provinceManageList);

		SeasonManage seasonManage = new SeasonManage();
		provinceManage.setIsVisable(1);
		List<SeasonManage> seasonManageList = seasonManageMapper.getListBy(seasonManage);
		modelAndView.addObject("seasonList", seasonManageList);
		return modelAndView;
	}

	/**
	 * 2017-4-17 yzy修改
	 *
	 * @修改人 yzy
	 * @修改内容 向view层传送答案
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/editKing")
	public ModelAndView editKing(Integer id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("system/subject/editKing");
		SubjectKing param = new SubjectKing();
		param.setId(id);
		SubjectKing list = subjectKingMapper.selectBy(param);
		SubOption record = new SubOption();
		record.setSubId(id);
		List<SubOption> options = subOptionMapper.getListBy(record);
		modelAndView.addObject("id", id);
		modelAndView.addObject("options", options);
		modelAndView.addObject("subInfo", param);
		modelAndView.addObject("KingList", list);
		return modelAndView;
	}

	/**
	 * @Title 题库管理-题目列表 删除题目
	 * @date 2017-3-23 11:28
	 * @author yzy
	 * @return 异步请求，返回json数据
	 */
	@RequestMapping(value = "/delone")
	@ResponseBody
	public String delete(HttpServletRequest request, HttpServletResponse res, String DATA, HttpSession session) {
		JSONObject jsonObject = JSONObject.parseObject(DATA);
		System.out.println(DATA);
		Integer listId = jsonObject.getInteger("CTIDS");
		Integer type = jsonObject.getInteger("TYPE");
		String result = "{'result':false}";
		if (type != null) {
			if (type == 0) {
				SubjectKu subjectKu = new SubjectKu();
				subjectKu.setId(listId);
				if (subjectService.deleteByPrimaryKey(subjectKu) > 0) {
					result = "{'result':true}";
				}
			} else if (type == 1) {
				SubjectKing subjectKing = new SubjectKing();
				subjectKing.setId(listId);
				if (subjectService.deleteByPrimaryKey(subjectKing) > 0) {
					result = "{'result':true}";
				}
			}
		}
		return result;
	}

	@RequestMapping("editorSub")
	public ModelAndView editorSub(Integer id, Integer cur) {
		ModelAndView mv = new ModelAndView();
		SubjectKing sbKing = subjectKingMapper.selectByPrimaryKey(id);
		mv.addObject("subject", sbKing);
		mv.addObject("cur", cur);
		TypeManage typeManage = new TypeManage();
		List<TypeManage> typeManageList = typeManageMapper.getListBy(typeManage);
		mv.addObject("list", typeManageList);
		mv.setViewName("system/subject/subjectinfo");
		return mv;
	}

	@RequestMapping("sendRed")
	public ModelAndView sendRed(Integer id) {
		ModelAndView mv = new ModelAndView();
		RedMoney redMoney = new RedMoney();
		RedMoney rm = redmoneyMapper.selectBy(redMoney);

		SubjectKing sk = subjectKingMapper.selectByPrimaryKey(id);
		if (sk.getAnswer() != null && sk.getSubNumber() == null) {
			sk.setSubNumber(1);
			subjectKingMapper.updateByPrimaryKey(sk);
			KingUserAnswer kua = new KingUserAnswer();
			kua.setSubKingId(id);
			kua.setOrderBy("over_time desc");
			List<KingUserAnswer> kuaList = kingUserAnswerMapper.getListBy(kua);
			Integer m = 0;
			if (kuaList.size() > rm.getCountWeb()) {
				m = rm.getCountWeb();
			} else {
				m = kuaList.size();
			}
			for (int i = 0; i < m; i++) {
				if (kuaList.get(i).getUserAnwser().equals(sk.getAnswer())) {
					ProductOrder productOrder = new ProductOrder();
					productOrder.setUserId(kuaList.get(i).getUserId());
					List<ProductOrder> list = productOrderMapper.getListBy(productOrder);
					if (list != null && list.size() > 1) {
						kuaList.get(i).setMoney(rm.getMoneyNum());
						WebUser webUser = webUserMapper.selectByPrimaryKey(kuaList.get(i).getUserId());
						webUser.setRestMoney(webUser.getRestMoney() == null ? rm.getMoneyNum()
								: webUser.getRestMoney() + rm.getMoneyNum());
						webUserMapper.updateByPrimaryKey(webUser);

						RedEnvelope re = new RedEnvelope();
						re.setPackageId(kuaList.get(i).getSubKingId());
						re.setType(1);
						re.setUserId(kuaList.get(i).getUserId());
						re.setMoney(rm.getMoneyNum());
						re.setAnswerTime(new Date());
						redEnvelopeMapper.insert(re);

					} else if (list.size() == 1) {
						for (ProductOrder productOrder2 : list) {
							if (productOrder2.getPrice() != null && productOrder2.getPrice() != 0) {
								WebUser webUser = webUserMapper.selectByPrimaryKey(kuaList.get(i).getUserId());
								webUser.setRestMoney(webUser.getRestMoney() == null ? rm.getMoneyNum()
										: webUser.getRestMoney() + rm.getMoneyNum());
								webUserMapper.updateByPrimaryKey(webUser);
								RedEnvelope re = new RedEnvelope();
								re.setPackageId(kuaList.get(i).getSubKingId());
								re.setType(1);
								re.setUserId(kuaList.get(i).getUserId());
								re.setMoney(rm.getMoneyNum());
								re.setAnswerTime(new Date());
								redEnvelopeMapper.insert(re);
							} else {
								kuaList.get(i).setMoney(rm.getMoneyNums());
								WebUser webUser = webUserMapper.selectByPrimaryKey(kuaList.get(i).getUserId());
								webUser.setRestMoney(webUser.getRestMoney() == null ? rm.getMoneyNums()
										: webUser.getRestMoney() + rm.getMoneyNums());
								RedEnvelope re = new RedEnvelope();
								re.setPackageId(kuaList.get(i).getSubKingId());
								re.setType(1);
								re.setUserId(kuaList.get(i).getUserId());
								re.setMoney(rm.getMoneyNums());
								re.setAnswerTime(new Date());
								redEnvelopeMapper.insert(re);
							}
						}
					} else {
						kuaList.get(i).setMoney(rm.getMoneyNums());
						WebUser webUser = webUserMapper.selectByPrimaryKey(kuaList.get(i).getUserId());
						webUser.setRestMoney(webUser.getRestMoney() == null ? rm.getMoneyNums()
								: webUser.getRestMoney() + rm.getMoneyNums());
						RedEnvelope re = new RedEnvelope();
						re.setPackageId(kuaList.get(i).getSubKingId());
						re.setType(1);
						re.setUserId(kuaList.get(i).getUserId());
						re.setMoney(rm.getMoneyNums());
						re.setAnswerTime(new Date());
						redEnvelopeMapper.insert(re);
					}
					kingUserAnswerMapper.updateByPrimaryKey(kuaList.get(i));
				}
			}
			mv.setViewName("redirect:kingList");
		} else {
			mv.setViewName("redirect:kingList");
		}

		return mv;
	}
}
