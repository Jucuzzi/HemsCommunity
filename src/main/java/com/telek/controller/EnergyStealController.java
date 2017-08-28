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
public class EnergyStealController {
	/**
	 * 查询谷电能量主界面
	 * 输出参数：
	 * String AllValleyEnergy 当月谷电总能量
	 * String enviromentLevel 环境等级（窗外等级)
	 * Array friends 好友列表（可偷用户列表）
	 *       String AllValleyEnergy 本月谷电能量
	 *       String canBeSteal 是否有能量可以被偷
	 *       String pictureId 用户头像ID
	 *       String userId 用户ID
	 *       String userName 用户名
	 * Array generatedEnergy 
	 * 		 String ValleyEnergy 剩余的谷电能量
	 * 		 String applianceTypeCode 电器类型
	 *       String canBeSteal 是否可以被偷（1可以0不可以）
	 *       String generatedId 产生的能量Id
	 * String hasRedPacket 是否有红包可以领取
	 * String honorWallLevel 荣誉墙等级
	 * Array thieves 偷取能量的用户数组
	 *       String date 偷取的时间
	 *       String stealEnergy 偷取的能量
	 *       String userId 用户ID
	 *       String userName 用户名
	 * */
	@RequestMapping("/getValleyEnergyMain")
	public void getValleyEnergyMain(HttpServletRequest request, HttpServletResponse response) {
		/* 组装数据发送云端请求 */
		JSONObject obj = new JSONObject();
		JSONObject paramObj = new JSONObject();
		paramObj.put("userId", "1419");
		obj.put(ConstantVar.METHOD_NAME, "hemsCircleQueryValleyEnergyMain");
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
	
	
	/**
	 * 查询更多动态界面
	 * 输出参数：
	 * Array root 
	 *       String date 被偷取的时间
	 *       String pictureId 用户头像id
	 *       String stealEnergy 偷取的能量
	 *       String userId 用户ID
	 *       String userName 用户名
	 * String total 记录总条数（两天内）
	 * */
	@RequestMapping("/getStealedList")
	public void getStealedList(HttpServletRequest request, HttpServletResponse response) {
		/* 组装数据发送云端请求 */
		JSONObject obj = new JSONObject();
		JSONObject paramObj = new JSONObject();
		paramObj.put("userId", "1419");
		obj.put(ConstantVar.METHOD_NAME, "hemsCircleQueryStealUser");
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
	
	/**
	 * 查询收集记录界面
	 * 输出参数：
	 * Array root 
	 *       String date 被偷取的时间
	 *       String energy 获取到的能量
	 * String total 记录总条数
	 * */
	@RequestMapping("/getStealRecord")
	public void getStealRecord(HttpServletRequest request, HttpServletResponse response) {
		/* 组装数据发送云端请求 */
		JSONObject obj = new JSONObject();
		JSONObject paramObj = new JSONObject();
		paramObj.put("userId", "1419");
		obj.put(ConstantVar.METHOD_NAME, "hemsCircleQueryValleyEnergyRecord");
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
	
	/**
	 * 查询月度排行榜界面
	 * 输出参数：
	 * String AllValleyEnergy 用户自己的谷电能量
	 * String rank 用户自己的排名
	 * Array topUserList 前30名用户 
	 *       String AllValleyEnergy 总的古典能量
	 *       String pictureId 用户头像Id
	 *       String userId 用户ID
	 *       String userName 用户名
	 * String total 记录总条数
	 * */
	@RequestMapping("/getMonthContributionRank")
	public void getMonthContributionRank(HttpServletRequest request, HttpServletResponse response) {
		/* 组装数据发送云端请求 */
		JSONObject obj = new JSONObject();
		JSONObject paramObj = new JSONObject();
		paramObj.put("userId", "1419");
		obj.put(ConstantVar.METHOD_NAME, "hemsCircleQueryCumulativContributionRank");
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
	
	/**
	 * 查询总排行榜界面
	 * 输出参数：
	 * String AllValleyEnergy 用户自己的谷电能量
	 * String rank 用户自己的排名
	 * Array topUserList 前30名用户 
	 *       String AllValleyEnergy 总的古典能量
	 *       String pictureId 用户头像Id
	 *       String userId 用户ID
	 *       String userName 用户名
	 * String total 记录总条数
	 * */
	@RequestMapping("/getTotalContributionRank")
	public void getTotalContributionRank(HttpServletRequest request, HttpServletResponse response) {
		/* 组装数据发送云端请求 */
		JSONObject obj = new JSONObject();
		JSONObject paramObj = new JSONObject();
		paramObj.put("userId", "1419");
		obj.put(ConstantVar.METHOD_NAME, "hemsCircleQueryComulativContributionRank");
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
	
	/**
	 * 偷取能量功能
	 * 输出参数：
	 * String remainEnergy 剩余的能量，如果是自己收集，则可以全部收取完
	 * String stealEnergy 偷取的能量
	 * */
	@RequestMapping("/stealValleyEnergy")
	public void stealValleyEnergy(HttpServletRequest request, HttpServletResponse response) {
		/* 组装数据发送云端请求 */
		JSONObject obj = new JSONObject();
		JSONObject paramObj = new JSONObject();
		paramObj.put("userId", "1419");
		String generatedId = request.getParameter("generatedId");
		paramObj.put("generatedId", generatedId);
		obj.put(ConstantVar.METHOD_NAME, "hemsCircleStealValleyEnergy");
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
