package com.telek.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import com.telek.cache.model.CacheModelManager;
import com.telek.dao.IDAO;
import com.telek.listener.DIBean;
import com.telek.model.CommentEntity;
import com.telek.model.UserEntity;
import com.telek.service.ICommentService;
import com.telek.service.IUserService;
import com.telek.service.UserServiceImpl;
import com.telek.util.Util;

import net.sf.json.JSONObject;

@Controller
public class UserController {
	@Autowired
	private IUserService userServiceImpl;
	@Autowired
	private ICommentService commentServiceImpl;
	@Autowired
	private IUserService userService;

	@RequestMapping("/showView")
	public String saveUser(HttpServletRequest request, HttpServletResponse response, UserEntity u) {
		System.out.println(u);
		System.out.println(request.getParameter("username"));
		System.out.println(request.getParameter("name"));
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		UserEntity user = new UserEntity("wlf", "dtc", "www.baidu.com", "1");
		userServiceImpl.save(user);
		UserEntity user2 = userServiceImpl.get(27);
		System.out.println(user2);
		// UserEntity user3 = new UserEntity("wlf","dtc","www.baidu.com","1");
		// userServiceImpl.save(user3);
		return "index.html";
		// return modelAndView;
	}

	/**
	 * 系统用户登录
	 * 
	 * @param request
	 * @param response
	 * @author Xugl @2017-3-16 下午5:55:19
	 */
	// @RequestMapping(value = "/userlogin.do", method = RequestMethod.POST)
	@RequestMapping(value = "/userlogin")
	public void userLogin(HttpServletRequest request, HttpServletResponse response) {
		String userAccount = Util.transferUserInput(request.getParameter("userAccount"));
		// String password = Util.transferUserInput(request
		// .getParameter("password"));
		UserEntity user = new UserEntity();
		user.setName(userAccount);
		// 返回数据
		try {
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(userService.userLogin(user, request));
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
