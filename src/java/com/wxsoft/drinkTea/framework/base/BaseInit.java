package com.wxsoft.drinkTea.framework.base;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

import com.allinpay.syb.lib.SybConstants;
import com.wxsoft.drinkTea.framework.properties.DomainProperties;
import com.wxsoft.drinkTea.framework.utils.WeiXinInfo;


public class BaseInit extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void init() {
		InputStream inputStream2 = this.getClass().getResourceAsStream("/conf/domain.properties");
		Properties p = new Properties();
		try {
			p.load(inputStream2);
			DomainProperties.DOMAIN_DEFAULT = p.getProperty("domain.default");
			DomainProperties.DOMAIN_WWW = p.getProperty("domain.default");
			DomainProperties.DOMAIN_RESOURCE = p.getProperty("domain.resource");
			DomainProperties.DOMAIN_IMG = p.getProperty("domain.img");
			DomainProperties.DEF_TECHNICAL_SUPPORT = p.getProperty("def.technical_support");
			DomainProperties.PATH_IMG_MAIN = p.getProperty("path.img.main");
			DomainProperties.PATH_IMG_QRCODE = p.getProperty("path.img.qrcode");
			DomainProperties.WEIXIN_APPID = p.getProperty("weixin.appid");
			DomainProperties.WEIXIN_SECRET = p.getProperty("weixin.secret");

			//通联支付
			SybConstants.SYB_CUSID = p.getProperty("allinpay.cusid");
			SybConstants.SYB_APPID = p.getProperty("allinpay.appid");
			SybConstants.SYB_APPKEY = p.getProperty("allinpay.appkey");
			SybConstants.SYB_APIURL = p.getProperty("allinpay.apiurl");
			//微信设置的合作者
			SybConstants.PARTNERKEY = p.getProperty("weixin.partnerkey");
			inputStream2.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		ServletContext application = this.getServletContext();
		WeiXinInfo.APPID = DomainProperties.WEIXIN_APPID;
		WeiXinInfo.APPSECRET = DomainProperties.WEIXIN_SECRET;
		application.setAttribute("DOMAIN", DomainProperties.DOMAIN_DEFAULT);
		// 设置css,js等静态文件路径
		application.setAttribute("RESOURCEDOMAIN", DomainProperties.DOMAIN_RESOURCE);
		application.setAttribute("DOMAIN_WWW", DomainProperties.DOMAIN_WWW);
		application.setAttribute("IMGDOMAIN", DomainProperties.DOMAIN_IMG);
		application.setAttribute("TECHNICALSUPPORT", DomainProperties.DEF_TECHNICAL_SUPPORT);
		application.setAttribute("RES", DomainProperties.DOMAIN_RES);

	}
}