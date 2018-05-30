package com.wxsoft.drinkTea.platform.system.teaKing.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.wxsoft.drinkTea.framework.base.BaseAction;
import com.wxsoft.drinkTea.framework.utils.DateUtils;
import com.wxsoft.drinkTea.platform.weixin.mapper.KingBaseInfoMapper;
import com.wxsoft.drinkTea.platform.weixin.model.KingBaseInfo;

/**
 * 茶王争霸的后台管理的controller
 * @author 王桐睿
 * com.wxsoft.drinkTea.platform.system.teaKing.controller
 * 2017年4月1日下午2:52:34
 */
@Controller
@RequestMapping("system/tking")
public class TKingController extends BaseAction{

	/** */
	private static final long serialVersionUID = 1L;

	@Autowired
	private KingBaseInfoMapper kingBaseInfoMapper;

	/**
	 * @Title: list
	 * @date 时间：2017年4月1日下午2:59:09
	 * @author 作者： 王桐睿
	 * @return 返回类型：ModelAndView
	 * @Description:跳转到茶王争霸赛
	 */
	@RequestMapping("/list")
	public ModelAndView list(KingBaseInfo kingBaseInfo){
		ModelAndView mav = new ModelAndView("/system/teaKing/teaKingList");
		List<KingBaseInfo> list = kingBaseInfoMapper.getPageListBy(kingBaseInfo);
		mav.addObject("objList",list);
		mav.addObject("page", kingBaseInfo);
		return mav;
	}

	/**
	 * @Title: edit
	 * @date 时间：2017年4月1日下午4:25:53
	 * @author 作者： 王桐睿
	 * @return 返回类型：ModelAndView
	 * @Description:跳转到编辑赛事的页面
	 */
	@RequestMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable Integer id){
		ModelAndView mav = new ModelAndView("/system/teaKing/editTeaKing");
		KingBaseInfo ki = kingBaseInfoMapper.selectByPrimaryKey(id);
		if(ki != null){
			mav.addObject("ki",ki);
			mav.addObject("id",id);
			if(ki.getType() == 1){
				mav.addObject("kiType","日赛");
				String st = new SimpleDateFormat("HH:mm").format(ki.getStartTime());
				String times[] = st.split(":");
				String hour = times[0];
				String min = times[1];
				mav.addObject("shour",hour);
				mav.addObject("smin",min);
				String et = new SimpleDateFormat("HH:mm").format(ki.getEndTime());
				String etimes[] = et.split(":");
				String ehour = etimes[0];
				String emin = etimes[1];
				mav.addObject("ehour",ehour);
				mav.addObject("emin",emin);
			}else if(ki.getType() == 2){
				mav.addObject("kiType","周赛");
				mav.addObject("startTime",DateUtils.toDateTime(ki.getStartTime()));
				mav.addObject("endTime",DateUtils.toDateTime(ki.getEndTime()));
			}else if(ki.getType() == 3){
				mav.addObject("kiType","月赛");
				mav.addObject("startTime",DateUtils.toDateTime(ki.getStartTime()));
				mav.addObject("endTime",DateUtils.toDateTime(ki.getEndTime()));
			}else if(ki.getType() == 4){
				mav.addObject("kiType","季赛");
				mav.addObject("startTime",DateUtils.toDateTime(ki.getStartTime()));
				mav.addObject("endTime",DateUtils.toDateTime(ki.getEndTime()));
			}else if(ki.getType() == 5){
				mav.addObject("kiType","年赛");
				mav.addObject("startTime",DateUtils.toDateTime(ki.getStartTime()));
				mav.addObject("endTime",DateUtils.toDateTime(ki.getEndTime()));
			}
		}
		return mav;
	}

	/**
	 * @Title: udpate
	 * @date 时间：2017年4月5日上午9:58:52
	 * @author 作者： 王桐睿
	 * @return 返回类型：void
	 * @Description:保存编辑的赛事信息
	 */
	@RequestMapping("/update")
	public void udpate(HttpServletResponse response,@RequestParam("time") String time,KingBaseInfo info){
		Map<String,String> result = new HashMap<String,String>();//存放返回前台的信息
		String[] times = time.split("~");
		String start = times[0];
		String end = times[1];

SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
         try {
			Date starts =  sdf.parse(start);
			 Date ends = sdf.parse(end);
			 info.setStartTime(starts);
			 info.setEndTime(ends);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Date startTime = dateFormat(start);
//		Date endTime = dateFormat(end);
		int a = kingBaseInfoMapper.updateByPrimaryKey(info);
		if(a < 1){
			result.put("status", "0");
			result.put("Message", "修改赛事失败，请稍后再试");
		}else{
			result.put("status", "1");
		}
		try {
			responseAjax(result, response);
		} catch (UnsupportedEncodingException h) {
			h.printStackTrace();
		}
	}

	/**
	 * @Title: add
	 * @date 时间：2017年4月5日上午10:58:43
	 * @author 作者： 王桐睿
	 * @return 返回类型：ModelAndView
	 * @Description: 跳转到添加页面
	 */
	@RequestMapping("/add")
	public ModelAndView add(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/system/teaKing/addTeaking");
		return mav;
	}

	private  Date dateFormat(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }


}
