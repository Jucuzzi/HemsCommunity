package com.telek.service;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.telek.util.ConstantVar;
import com.telek.util.HttpUtil;
import com.telek.util.Util;

@Service("LuckyDrawServiceImpl")
public class LuckyDrawServiceImpl implements LuckyDrawService {

	/**
	 * 查询中奖主页面信息接口
	 */
	public String hemsCircleQueryPrizeDrawMain(int userId) {

		JSONObject jsonObj = new JSONObject();
		JSONObject paramObj = new JSONObject();
		paramObj.put("userId", "" + userId);
		jsonObj.put(ConstantVar.METHOD_NAME, "hemsCircleQueryPrizeDrawMain");
		jsonObj.put(ConstantVar.PARAMETER, paramObj);
		String result = HttpUtil.doPost(jsonObj);
		result = Util.checkCloudResponseValid(result);
		return result;
		
	}
	
	/**
	 * 查询中奖历史记录接口
	 */
	public String hemsCircleQueryPrizeDrawHistory(int userId) {

		JSONObject jsonObj = new JSONObject();
		JSONObject paramObj = new JSONObject();
		paramObj.put("userId", "" + userId);
		jsonObj.put(ConstantVar.METHOD_NAME, "hemsCircleQueryPrizeDrawHistory");
		jsonObj.put(ConstantVar.PARAMETER, paramObj);
		String result = HttpUtil.doPost(jsonObj);
		result = Util.checkCloudResponseValid(result);
		return result;
		
	}
	
	/**
	 * 抽奖接口
	 */
	public String hemsCirclePrizeDraw(int userId) {

		JSONObject jsonObj = new JSONObject();
		JSONObject paramObj = new JSONObject();
		paramObj.put("userId", "" + userId);
		jsonObj.put(ConstantVar.METHOD_NAME, "hemsCirclePrizeDraw");
		jsonObj.put(ConstantVar.PARAMETER, paramObj);
		String result = HttpUtil.doPost(jsonObj);
		result = Util.checkCloudResponseValid(result);
		return result;
		
	}
	/*
	 * 签到接口
	 */
	public String queryHemsCircleSignIn(int userId) {

		JSONObject jsonObj = new JSONObject();
		JSONObject paramObj = new JSONObject();
		paramObj.put("userId", "" + userId);
		jsonObj.put(ConstantVar.METHOD_NAME, "hemsCircleSignIn");
		jsonObj.put(ConstantVar.PARAMETER, paramObj);
		String result = HttpUtil.doPost(jsonObj);
		result = Util.checkCloudResponseValid(result);
		return result;
		
	}
	/*
	 * 查询积分主界面接口
	 */
	public String queryemsCircleQueryPointsMain(int userId) {

		JSONObject jsonObj = new JSONObject();
		JSONObject paramObj = new JSONObject();
		paramObj.put("userId", "" + userId);
		jsonObj.put(ConstantVar.METHOD_NAME, "hemsCircleQueryPointsMain");
		jsonObj.put(ConstantVar.PARAMETER, paramObj);
		String result = HttpUtil.doPost(jsonObj);
		result = Util.checkCloudResponseValid(result);
		return result;	
	}
	
	/*
	 * 查询签到历史
	 */
	public String queryemsHemsCircleQuerySignInState(int userId) {

		JSONObject jsonObj = new JSONObject();
		JSONObject paramObj = new JSONObject();
		paramObj.put("userId", "" + userId);
		jsonObj.put(ConstantVar.METHOD_NAME, "hemsCircleQuerySignInState");
		jsonObj.put(ConstantVar.PARAMETER, paramObj);
		String result = HttpUtil.doPost(jsonObj);
		result = Util.checkCloudResponseValid(result);
		return result;	
	}

}
