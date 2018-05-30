package com.wxsoft.drinkTea.platform.weixin.models;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wxsoft.drinkTea.framework.base.BaseAction;
import com.wxsoft.drinkTea.platform.system.abouts.mapper.AboutUsMapper;
import com.wxsoft.drinkTea.platform.system.order.mapper.ProductOrderMapper;
import com.wxsoft.drinkTea.platform.system.order.model.ProductOrder;
import com.wxsoft.drinkTea.platform.weixin.mapper.IdeaTicklingMapper;
import com.wxsoft.drinkTea.platform.weixin.mapper.WebUserMapper;
import com.wxsoft.drinkTea.platform.weixin.model.IdeaTickling;
import com.wxsoft.drinkTea.platform.weixin.model.WebUser;

/**
 * @个人中心入口
 * @xyc
 * */
@Controller
@RequestMapping()
public class personalCenterController extends BaseAction{
	private static final long serialVersionUID = 1L;


	@Autowired
	private WebUserMapper webUserMapper;

//    @Autowired
//	private ProductOrderMapper ProductOrder;
    @Autowired
    private IdeaTicklingMapper ideaTicklingMapper;

//    @Autowired
//    private AboutUsMapper aboutUsMapper;
	/**
	 * @跳转个人中心
	 * @xyc
	 * */
    @RequestMapping("/center")
	public ModelAndView model(ProductOrder product){
		ModelAndView mod=new ModelAndView("weixin/personalCenter");
		WebUser user = webUserMapper.selectByPrimaryKey(115);
		mod.addObject("user",user);
		if(user!=null){
			System.out.println(user.getUserName());
			System.out.println(user.getId());
			System.out.println(user.getClass());
			System.out.println(user.getImage());
		}
		return mod;
	}


    /**
	 * @关于我们-采茶去公司品牌简介
	 * @xyc
	 * */
    @RequestMapping("/aboutCompany")
    public ModelAndView aboutCompany(){
    	 ModelAndView modelAndView = new ModelAndView("weixin/aboutCompany");
//    	 AboutUs abouts = aboutUsMapper.selectByPrimaryKey(1);
//    	 modelAndView.addObject("abouts",abouts);
	    return modelAndView;
    }

    /**
     * 2017-4-17 yzy
     * @修改人  yzy
     * @修改内容  关于我们-采茶去品牌文化
	 * @xyc
	 * */
    @RequestMapping("/aboutUs")
    public ModelAndView aboutUs(){
    	 ModelAndView modelAndView = new ModelAndView("weixin/aboutUs");
//    	 AboutUs abouts = aboutUsMapper.selectByPrimaryKey(1);
//    	 modelAndView.addObject("abouts",abouts);
	    return modelAndView;
    }

    /**
	 * @意见反馈
	 * @xyc
	 * @修改lzj
	 * @时间：2017-4-18 14:50
	 * */
    @RequestMapping("/myfeedback")
    public ModelAndView modelAndVi(){
    	ModelAndView modelAndView=new ModelAndView("/weixin/feedback");
    	return modelAndView;
    }
    /**
	 * @意见反馈
	 * @zss
	 * @修改lzj
	 * @时间：2017-4-18 15:50
	 * */
    @RequestMapping("/myfeed")
    public void myfeed(HttpServletResponse response,String content,String phone,HttpSession session){
     WebUser webUser = (WebUser) session.getAttribute("user");
     if(webUser!=null){
    	 System.out.println("输出一下用户Id:"+webUser.getId());
    	 Map<String, String>  map= new HashMap<>();
    	 IdeaTickling ideaTickling = new IdeaTickling();
    	 ideaTickling.setContents(content);
    	 ideaTickling.setPhone(phone);
    	 ideaTickling.setState(20);
    	 ideaTickling.setCommodityId(webUser.getId());
    	 if(ideaTickling!=null){
    		int flag = ideaTicklingMapper.insert(ideaTickling);
    		if(flag==1){
        		map.put("status", "1");
        	}else{
        		map.put("status", "0");
        	}
    	  }
    	 try {
  			responseAjax(map, response);
  		} catch (UnsupportedEncodingException e) {
  			e.printStackTrace();
  		}
     }
      }

    /**
	 * @我的地址
	 * @xyc
	 * */
    @RequestMapping("/myaddress")
    public ModelAndView modelAnd(){
    	ModelAndView modelAndView=new ModelAndView("/weixin/myAddress");
		return modelAndView;
    }

    /**
	 * @我的红包
	 * @xyc
	 * */
    @RequestMapping("/redpacket")
    public ModelAndView models(){
    	ModelAndView modelAndView=new ModelAndView("/weixin/myRedEnvelope");
		return modelAndView;
    }

    /**
	 * @跳转我的订单
	 * @xyc
	 * */
    @RequestMapping("/myorder")
    public ModelAndView modean(){
    	ModelAndView modelAndView=new ModelAndView("/weixin/myOrder");
    //	ProductOrder.selectByPrimaryKey(id);
		return modelAndView;
    }

    /**
   	 * @跳转我的订单
   	 * @xyc
   	 * */
    @RequestMapping("/myorders")
    public ModelAndView modeans(){
    	ModelAndView modelAndView=new ModelAndView("/weixin/myOrder");
		return modelAndView;
    }

/**
   	 * @跳转我的订单
   	 * @xyc
   	 * */
    @RequestMapping("/myorderss")
    public ModelAndView modeanf(){
    	ModelAndView modelAndView=new ModelAndView("/weixin/myOrder");
		return modelAndView;
    }

    /**
   	 * @跳转我的订单
   	 * @xyc
   	 * */
    @RequestMapping("/myorde")
    public ModelAndView modeanl(){

    	ModelAndView modelAndView=new ModelAndView("/weixin/myOrder");
		return modelAndView;
    }

    /**
   	 * @跳转我的订单
   	 * @xyc
   	 * */
    @RequestMapping("/myord")
    public ModelAndView modeanll(){
    	ModelAndView modelAndView=new ModelAndView("/weixin/myOrder");
		return modelAndView;
    }

    /**
   	 * @订单退款
   	 * @xyc
   	 * */
    @RequestMapping("/refund")
    public ModelAndView modeanlf(){
    	ModelAndView modelAndView=new ModelAndView("/weixin/refundDetails");
		return modelAndView;
    }






































}
