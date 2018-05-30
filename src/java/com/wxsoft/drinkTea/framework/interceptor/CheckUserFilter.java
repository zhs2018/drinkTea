package com.wxsoft.drinkTea.framework.interceptor;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wxsoft.drinkTea.platform.system.login.model.SysUser;

/**
 * Servlet Filter implementation class CheckUserFilter
 */
public class CheckUserFilter implements Filter {

    /**
     * Default constructor.
     */
    public CheckUserFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String currentURL = req.getRequestURI();
		 String targetURL = currentURL.substring(currentURL.indexOf("/", 1),
				    currentURL.length());
		 System.out.println(targetURL);
		 if(targetURL.startsWith("/system/") && !"/system/login".equals(targetURL)){
			 System.out.println("请求路径："+targetURL);
			 if(req.getSession().getAttribute("sessionUser") == null){
					res.sendRedirect("/drinkTea");
				}else{
					chain.doFilter(request, response);
				}
		 }else{
			 chain.doFilter(request, response);
		 }
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
