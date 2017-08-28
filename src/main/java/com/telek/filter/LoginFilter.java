package com.telek.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.telek.model.UserEntity;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public LoginFilter() {
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 获得在下面代码中要用的request,response,session对象
		HttpServletRequest servletRequest = (HttpServletRequest) request;

		HttpServletResponse servletResponse = (HttpServletResponse) response;

//		servletResponse.setHeader("Access-Control-Allow-Origin", "*");
//
//		servletResponse.setHeader("Access-Control-Allow-Credentials", "false");
		
		servletResponse.setHeader("Access-Control-Allow-Origin",
		servletRequest.getHeader("Origin"));
		servletResponse.setHeader("Access-Control-Allow-Credentials", "true");// 支持cookie跨域
		HttpSession session = servletRequest.getSession();
		System.out.println("进过滤器了");
		// 获得用户请求的URI
		String path = servletRequest.getRequestURI();
		String pageName = "";
		if ("".equals(path.replace("/MicroGridDemo/", ""))) {
			pageName = "login";
		} else {
			// 该if条件是为了判断http://localhost:8080/BuildingMS/build/jsp/logi的类似情况
			if (path.lastIndexOf(".") > path.lastIndexOf("/")) {
				pageName = path.substring(path.lastIndexOf("/"), path.lastIndexOf("."));
			}
		}
	
				// 当前用户登录
				UserEntity admin = (UserEntity) session.getAttribute("loginUser");
				// 用户可以访问的页面
				String prvlgMenuBtn = (String) session.getAttribute("prvlgMenuBtnJsp");
//				// 登陆无需过滤
//				if (path.indexOf("/login.jsp") > -1 || path.indexOf("/transition.html") > -1) {
//					chain.doFilter(servletRequest, servletResponse);
//					return;
//				}
//				// 判断如果没有取到员工信息,就跳转到登陆页面
//				if (admin == null) {
//					// 跳转到登陆页面
//					servletResponse.sendRedirect("/MicroGridDemo/jsp/transition.html");
//				} else if (!prvlgMenuBtn.contains(pageName)) {
//					servletResponse.sendRedirect("/MicroGridDemo/jsp/prvlgerror.html");
//				} else {
//					// 已经登陆,继续此次请求
//					chain.doFilter(request, response);
//				}
				
		System.out.println(pageName);
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
