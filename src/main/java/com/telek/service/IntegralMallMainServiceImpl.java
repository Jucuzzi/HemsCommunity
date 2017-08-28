package com.telek.service;

import org.springframework.stereotype.Service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.telek.util.ConstantVar;
import com.telek.util.HttpUtil;
import com.telek.util.Util;


@Service("IntegralMallMainServiceImpl")
public class IntegralMallMainServiceImpl implements IntegralMallMainService {

	public String hemsCircleSignIn(int userId) {
		
		JSONObject jsonObj = new JSONObject();
		JSONObject paramObj = new JSONObject();
		paramObj.put("userId", "" + userId);
		jsonObj.put(ConstantVar.METHOD_NAME, "hemsCircleSignIn");
		jsonObj.put(ConstantVar.PARAMETER, paramObj);
		String result = HttpUtil.doPost(jsonObj);
		result = Util.checkCloudResponseValid(result);
		return result;
		
	}
	
	public String hemsCircleQueryPointsMain(int userId) {
		
		JSONObject jsonObj = new JSONObject();
		JSONObject paramObj = new JSONObject();
		paramObj.put("userId", "" + userId);
		jsonObj.put(ConstantVar.METHOD_NAME, "hemsCircleQueryPointsMain");
		jsonObj.put(ConstantVar.PARAMETER, paramObj);
		String result = HttpUtil.doPost(jsonObj);
		result = Util.checkCloudResponseValid(result);
		return result;
		
	}

	public String hemsCircleQuerySignInState(int userId) {
		
		JSONObject jsonObj = new JSONObject();
		JSONObject paramObj = new JSONObject();
		paramObj.put("userId", "" + userId);
		jsonObj.put(ConstantVar.METHOD_NAME, "hemsCircleQuerySignInState");
		jsonObj.put(ConstantVar.PARAMETER, paramObj);
		String result = HttpUtil.doPost(jsonObj);
		result = Util.checkCloudResponseValid(result);
		return result;
		
	}
	
	public String hemsCircleQueryPointsHistory(int userId){
		
		JSONObject jsonObj = new JSONObject();
		JSONObject paramObj = new JSONObject();
		paramObj.put("userId", "" + userId);
		jsonObj.put(ConstantVar.METHOD_NAME, "hemsCircleQueryPointsHistory");
		jsonObj.put(ConstantVar.PARAMETER, paramObj);
		String result = HttpUtil.doPost(jsonObj);
		result = Util.checkCloudResponseValid(result);
		return result;
		
	}
	
}
