package com.wxsoft.drinkTea.platform.weixin.models;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wxsoft.drinkTea.framework.base.BaseAction;
import com.wxsoft.drinkTea.platform.system.evaluate.mapper.EvaluateProMapper;
import com.wxsoft.drinkTea.platform.system.evaluate.model.EvaluatePro;
import com.wxsoft.drinkTea.platform.system.login.mapper.ManageProductsMapper;
import com.wxsoft.drinkTea.platform.system.login.model.ManageProducts;
import com.wxsoft.drinkTea.platform.system.order.mapper.GoodsOrderMapper;
import com.wxsoft.drinkTea.platform.system.order.mapper.ProductOrderMapper;
import com.wxsoft.drinkTea.platform.system.order.model.GoodsOrder;
import com.wxsoft.drinkTea.platform.system.order.model.ProductOrder;

/**
 * @商品评价
 * @zss
 * */

@Controller
@RequestMapping()
public class commentCommodityController extends BaseAction{
	  	private static final long serialVersionUID = 1L;
	    @Autowired
	    private EvaluateProMapper evaluateProMapper;
	    @Autowired
	    private GoodsOrderMapper goodsOrderMapper;
	    @Autowired
	    private ManageProductsMapper manageProductsMapper;
	    @Autowired
	    private ProductOrderMapper productOrderMapper;


	    @RequestMapping("/comment")
	    public void  saveContent(Integer id,String content,HttpServletRequest request,HttpServletResponse response){
	    	Map<String, String> map = new HashMap<>();
	    	EvaluatePro ep = new EvaluatePro();
	    	ProductOrder pOrder = new ProductOrder();
	    	  pOrder.setId(id);
	    	  pOrder.setOrderState(90);
	        productOrderMapper.updateByPrimaryKey(pOrder);
	    	ProductOrder productOrder = productOrderMapper.selectByPrimaryKey(id);
	    	GoodsOrder goodsOrder = new GoodsOrder();
	    	goodsOrder.setOrderId(id);
	    	GoodsOrder gd =goodsOrderMapper.selectBy(goodsOrder);
	    	ManageProducts mp = manageProductsMapper.selectByPrimaryKey(gd.getGoodsId());
	        ep.setEvaluateTime(new Date());
	    	ep.setContent(content);
	    	ep.setGoodsId(mp.getId());
	    	ep.setUserId(productOrder.getUserId());
	    	ep.setStatus(0);
	    	int eva = evaluateProMapper.insert(ep);
	    	if( eva > 0){
	    		map.put("name", mp.getName());
	    		map.put("introduce", mp.getIntroduce());
	    		map.put("status","0");
	    		map.put("message","评价成功");
	    	}
	    	try {
				responseAjax(map, response);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
 }
