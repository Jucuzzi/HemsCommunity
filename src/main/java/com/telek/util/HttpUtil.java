package com.telek.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.telek.cache.model.ConfigInfoCache;
import com.telek.cache.model.UnNormalCache;

//import com.telehems.microgrid.cache.model.ConfigInfoCache;
//import com.telehems.microgrid.cache.model.UnNormalCache;

import net.sf.json.JSONObject;

/**
 * Web端向云端发送请求和接收数据的工具类
 * 
 * @author wang-xinglei(王星磊) 2015-5-26 上午10:55:07
 */
public class HttpUtil {
	private static final Logger LOG = Logger.getLogger(HttpUtil.class);
	private static long count = 10000;

	/**
	 * 以Post方式发送请求和接收数据
	 * 
	 * @param obj
	 * @return
	 */
	public static String doPost(JSONObject obj) {
		LOG.info("云端请求：" + obj.toString());
		String key = ConfigInfoCache.configMap.get(ConstantVar.DYN_PASSWORD);
		String res = "";

		HttpPost httpRequest = new HttpPost(Util.getCloudPropByKey("cloudurl"));
		httpRequest.setHeader("Connection", "close");// 短连接
		List<NameValuePair> params = new ArrayList<NameValuePair>();

		try {
			JSONObject json = putOtherParam(obj);
			params.add(new BasicNameValuePair("jsonParameter", json.toString()));
			
			/* 添加请求参数到请求对象 */
			// jdk7及以上可使用StandardCharsets.UTF_8
			httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			/* 发送请求并等待响应 */
			HttpClient httpClient = new DefaultHttpClient();
			if (Util.getCloudPropByKey("isNetError").equals("true")) {
				httpClient.getParams().setLongParameter(CoreConnectionPNames.SO_TIMEOUT, 1000);
			}
			HttpResponse httpResponse = httpClient.execute(httpRequest);
			/* 若状态码为200 ok */
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				/* 读返回数据 */
				String strResult = EntityUtils.toString(httpResponse.getEntity());
				
				if (strResult.indexOf("错误编码") > -1) {
					LOG.error(strResult);
				} else {
					
					byte[] bs;
					try {
						System.out.println(strResult);
						bs = AESUtil.hex2Byte(strResult);
					} catch (Exception e) {
						LOG.info("加解密异常：" + strResult);
						return null;
					}
					// 首先用新密码解密
					
					res = new String(AESUtil.decrypt(bs, key.getBytes()), "UTF-8");
					if ("".equals(res)) {
						res = new String(AESUtil.decrypt(bs, UnNormalCache.oldDynPassword.getBytes()), "UTF-8");
					}
				}
			} else {
				res = ConstantVar.NET_ERROR;
				LOG.info("网络异常");
			}
		} catch (Exception e) {
			res = ConstantVar.DECRYPT_ERROR;
			LOG.info("加解密异常");
			LOG.info(e.getMessage(), e);
		}
		LOG.info("云端返回：" + (res.length() > 3000 ? "返回值过长，不显示": res));
		return res;
	}

	private static JSONObject putOtherParam(JSONObject obj) {
		inc();
		String key = ConfigInfoCache.configMap.get(ConstantVar.DYN_PASSWORD);
		System.out.println("AESkey值为"+key);
		JSONObject json = new JSONObject();
		obj.put(ConstantVar.WEB_ID_MSG, Calendar.getInstance().getTimeInMillis() + "" + count);
		String str = "";
		byte[] infos;
		try {
			System.out.println(key.getBytes());
			infos = AESUtil.encrypt(obj.toString().getBytes("UTF-8"), key.getBytes());
			str = AESUtil.byte2Hex(infos);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		json.put(ConstantVar.WEB_ID_STR, ConfigInfoCache.configMap.get(ConstantVar.WEB_ID_NUM));
		json.put("data", str);
		json.put(ConstantVar.WEB_ID_HMAC, HMACUtil.encryptHMAC(str.getBytes(), key.getBytes()));
		return json;
	}

	/**
	 * 计数器,防止多个接口在同一毫秒请求时，msgId验证不通过
	 * 
	 * @author Shoukr @2017-2-22 下午5:29:22
	 */
	private synchronized static void inc() {
		count++;
		if (count > 99999) {
			count = 10000;
		}
	}

}
