package com.wxsoft.drinkTea.platform.system.luckdraw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wxsoft.drinkTea.framework.base.BaseAction;
import com.wxsoft.drinkTea.framework.config.SCHOOLMGR;
import com.wxsoft.drinkTea.platform.system.luckdraw.mapper.PrizeProMapper;
import com.wxsoft.drinkTea.platform.system.luckdraw.model.PrizePro;
import com.wxsoft.drinkTea.platform.weixin.mapper.UserAddressMapper;
import com.wxsoft.drinkTea.platform.weixin.mapper.WebUserMapper;
import com.wxsoft.drinkTea.platform.weixin.mapper.WinningInformationMapper;
import com.wxsoft.drinkTea.platform.weixin.model.UserAddress;
import com.wxsoft.drinkTea.platform.weixin.model.WebUser;
import com.wxsoft.drinkTea.platform.weixin.model.WinningInformation;

@Controller
@RequestMapping("system/prizes")
public class WinningPrizeController extends BaseAction{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private WinningInformationMapper winningInformationMapper;
    @Autowired
    private WebUserMapper webUserMapper;
    @Autowired
    private PrizeProMapper prizeProMapper;
    @Autowired
    private UserAddressMapper userAddressMapper;



	@RequestMapping("/result")
	private ModelAndView winPrize(WinningInformation mip ){
		ModelAndView mv = new ModelAndView();
		mip.setVisible(1);
		List<WinningInformation> wipList = winningInformationMapper.getPageListBy(mip);
		for (WinningInformation winningInformation : wipList) {
		WebUser webuser=webUserMapper.selectByPrimaryKey(winningInformation.getUserid());
		UserAddress userAddress = userAddressMapper.selectByPrimaryKey(webuser.getAddressId());
		PrizePro prizePro =prizeProMapper.selectByPrimaryKey(winningInformation.getPrizeid());
		winningInformation.setWebUser(webuser);
		winningInformation.setPrizePro(prizePro);
		winningInformation.setUserAddress(userAddress);
		}
		mv.addObject("wipList",wipList);
		mv.addObject("obj",mip);
		 mv.setViewName(SCHOOLMGR.SYSTEM_PATH_ADMIN + "/prizes/windraw");
		return mv;
	}

	@RequestMapping("/del/{id}")
	public ModelAndView del(@PathVariable Integer id){
		ModelAndView  mv = new ModelAndView();
		WinningInformation wip = new WinningInformation();
		wip.setId(id);
		wip.setVisible(2);
	   winningInformationMapper.updateByPrimaryKey(wip);
		mv.setViewName("redirect:"+SCHOOLMGR.SYSTEM_PATH_ADMIN + "/prizes/result");
		return mv;

	}

	@RequestMapping("/sendWip/{id}")
	public ModelAndView send(@PathVariable Integer id){
		ModelAndView  mv = new ModelAndView();
		WinningInformation wip = new WinningInformation();
		wip.setId(id);
		wip.setSendSign(2);
	    winningInformationMapper.updateByPrimaryKey(wip);
		mv.setViewName("redirect:"+SCHOOLMGR.SYSTEM_PATH_ADMIN + "/prizes/result");
		return mv;

	}
}
