package com.telek.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.telek.service.LuckyDrawService;

@Controller
public class LuckyDrawController {

	@Autowired
	private LuckyDrawService luckyDrawService;
	
	/**
	 * 抽奖主页面查询
	 * @param request
	 * @param response
	 */
	@RequestMapping("/hemsCircleQueryPrizeDrawMain")
	public void hemsCircleQueryPrizeDrawMain(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("云端请求我进来了");
		int userId = Integer.parseInt(request.getParameter("userId"));
		
		String result = luckyDrawService.hemsCircleQueryPrizeDrawMain(userId);
		
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
	 * 中奖历史记录查询
	 * @param request
	 * @param response
	 */
	@RequestMapping("/hemsCircleQueryPrizeDrawHistory")
	public void hemsCircleQueryPrizeDrawHistory(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("云端请求我进来了");
		int userId = Integer.parseInt(request.getParameter("userId"));
		
		String result = luckyDrawService.hemsCircleQueryPrizeDrawHistory(userId);
		
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
	 * 抽奖接口
	 * @param request
	 * @param response
	 */
	@RequestMapping("/hemsCirclePrizeDraw")
	public void hemsCirclePrizeDraw(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("云端请求我进来了");
		int userId = Integer.parseInt(request.getParameter("userId"));
		
		String result = luckyDrawService.hemsCirclePrizeDraw(userId);
		
		try {
			JSONObject jsonObjectAll = new JSONObject();
			jsonObjectAll.put("result", result);
			System.out.println("云端请求的结果00000000000：   "+result);
			System.out.println("云端请求的结果11111111111：   "+jsonObjectAll.toString());
			response.getWriter().write(jsonObjectAll.toString());
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	 * 签到接口
	 */
	@RequestMapping("/hemsCircleSignInMain")
	public void hemsCircleSignInMain(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("云端请求我进来了");
		int userId = Integer.parseInt(request.getParameter("userId"));
		
		String result = luckyDrawService.queryHemsCircleSignIn(userId);
		
		try {
			JSONObject jsonObjectAll = new JSONObject();
			jsonObjectAll.put("result", result);
			System.out.println("云端请求的结果11111111111111：   "+result);
			response.getWriter().write(jsonObjectAll.toString());
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/*
	 * 积分主界面接口
	 */
	@RequestMapping("/hemsCircleQueryPointsMains")
	public void hemsCircleQueryPointsMains(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("云端请求我进来了");
		int userId = Integer.parseInt(request.getParameter("userId"));
		
		String result = luckyDrawService.queryemsCircleQueryPointsMain(userId);
		
		try {
			JSONObject jsonObjectAll = new JSONObject();
			jsonObjectAll.put("result", result);
			System.out.println("云端请求的结果222222222222222：   "+result);
			response.getWriter().write(jsonObjectAll.toString());
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/*
	 * 签到历史接口
	 */
	@RequestMapping("/hemsCircleQuerySignInStateHistory")
	public void hemsCircleQuerySignInStateHistory(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("云端请求我进来了");
		int userId = Integer.parseInt(request.getParameter("userId"));
		
		String result = luckyDrawService.queryemsHemsCircleQuerySignInState(userId);
		
		try {
			JSONObject jsonObjectAll = new JSONObject();
			jsonObjectAll.put("result", result);
			System.out.println("云端请求的结果33333333333333：   "+result);
			response.getWriter().write(jsonObjectAll.toString());
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
