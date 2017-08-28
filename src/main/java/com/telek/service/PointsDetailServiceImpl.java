package com.telek.service;

import org.springframework.stereotype.Service;

import net.sf.json.JSONObject;

import com.telek.util.ConstantVar;
import com.telek.util.HttpUtil;
import com.telek.util.Util;

@Service("PointsDetailServiceImpl")
public class PointsDetailServiceImpl implements PointsDetailService {

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
