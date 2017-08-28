package com.telek.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.telek.service.PointsDetailService;

@Controller
public class PointsDetailController {

	@Autowired
	private PointsDetailService pointsDetailService;
	
	/**
	 * 积分详情查询
	 * @param request
	 * @param response
	 */
	@RequestMapping("/hemsCircleQueryPointsHistory")
	public void hemsCircleQueryPointsHistory(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("云端请求我进来了");
		int userId = Integer.parseInt(request.getParameter("userId"));
		
		String result = pointsDetailService.hemsCircleQueryPointsHistory(userId);
		
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
