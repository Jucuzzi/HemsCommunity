package com.telek.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.telek.model.CommentEntity;
import com.telek.model.UserEntity;
import com.telek.util.ConstantVar;
import com.telek.util.HttpUtil;
import com.telek.util.Util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class CityViewController {

	/**
	 * 查询城市情况界面
	 * 输出参数：
	 * String closedPowerStations 已经关闭的发电站数量
	 * String level 当前等级
	 * String nowEnergy 当前能量
	 * String oneLevelMaxEnergy 每一级最大能量 
	 * */
	@RequestMapping("/getCityState")
	public void getCityState(HttpServletRequest request, HttpServletResponse response) {
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		/* 组装数据发送云端请求 */
		JSONObject obj = new JSONObject();
		JSONObject paramObj = new JSONObject();
//		paramObj.put("accountList", arr);
		obj.put(ConstantVar.METHOD_NAME, "hemsCircleQueryCityState");
		obj.put(ConstantVar.PARAMETER, paramObj);
		String result = HttpUtil.doPost(obj);
		result = Util.checkCloudResponseValid(result);
		System.out.println(result);
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
