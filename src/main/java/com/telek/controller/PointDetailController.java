package com.telek.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.telek.util.ConstantVar;
import com.telek.util.HttpUtil;
import com.telek.util.Util;

import net.sf.json.JSONObject;

@Controller
public class PointDetailController {
	
	/**
	 * 查询积分详情界面
	 * 输出参数：
	 * String expendPoints 支出积分总量
	 * String incomePoints 收入积分总量
	 * String points 积分总量
	 * Array pointsHistory 积分变动历史
	 *  	 String date 日期
	 *  	 Array history 当天历史纪录
	 *  		   String changeNum 变动数目
	 *  		   String changeType 变动类型（0支出1收入）
	 *  		   String date 时间
	 *  		   String useType 用途（0抽奖，1签到，2绿色能量兑换）
	 * */
	@RequestMapping("/getPointsDetail")
	public void getCityState(HttpServletRequest request, HttpServletResponse response) {
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		/* 组装数据发送云端请求 */
		JSONObject obj = new JSONObject();
		JSONObject paramObj = new JSONObject();
		String userId = request.getParameter("userId");
		String recordId = request.getParameter("recordId");
		paramObj.put("userId", userId);
		paramObj.put("recordId", recordId);
		obj.put(ConstantVar.METHOD_NAME, "hemsCircleQueryPointsHistory");
		System.out.println(paramObj);
		obj.put(ConstantVar.PARAMETER, paramObj);
		String result = HttpUtil.doPost(obj);
		result = Util.checkCloudResponseValid(result);
		JSONObject jsonObjectAll = new JSONObject();
		jsonObjectAll.put("result", result);
		try {
			response.getWriter().write(jsonObjectAll.toString());
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
