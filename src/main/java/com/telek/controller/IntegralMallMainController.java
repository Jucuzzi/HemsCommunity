package com.telek.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.telek.service.IntegralMallMainService;

@Controller
public class IntegralMallMainController {

	@Autowired
	private IntegralMallMainService integralMallMainService;
	
	/**
	 * 签到接口
	 * @param request
	 * @param response
	 */
	@RequestMapping("/hemsCircleSignIn")
	public void hemsCircleSignIn(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("云端请求我进来了");
		int userId = Integer.parseInt(request.getParameter("userId"));
		
		String result = integralMallMainService.hemsCircleSignIn(userId);
		
		try {
			JSONObject jsonObjectAll = new JSONObject();
			jsonObjectAll.put("result", result);
			System.out.println("云端请求的结果00000000000：   "+result);
			response.getWriter().write(jsonObjectAll.toString());
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 主界面查询接口
	 * @param request
	 * @param response
	 */
	@RequestMapping("/hemsCircleQueryPointsMain")
	public void hemsCircleQueryPointsMain(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("云端请求我进来了");
		int userId = Integer.parseInt(request.getParameter("userId"));
		
		String result = integralMallMainService.hemsCircleQueryPointsMain(userId);
		
		try {
			JSONObject jsonObjectAll = new JSONObject();
			jsonObjectAll.put("result", result);
			System.out.println("云端请求的结果00000000000：   "+result);
			response.getWriter().write(jsonObjectAll.toString());
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 签到日期查询
	 * @param request
	 * @param response
	 */
	@RequestMapping("/hemsCircleQuerySignInState")
	public void hemsCircleQuerySignInState(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("云端请求我进来了");
		int userId = Integer.parseInt(request.getParameter("userId"));
		
		String result = integralMallMainService.hemsCircleQuerySignInState(userId);
		
		try {
			JSONObject jsonObjectAll = new JSONObject();
			jsonObjectAll.put("result", result);
			System.out.println("云端请求的结果00000000000：   "+result);
			response.getWriter().write(jsonObjectAll.toString());
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
