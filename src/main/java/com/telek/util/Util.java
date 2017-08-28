/**
 * 
 */
package com.telek.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.telek.cache.model.UnNormalCache;
import com.telek.model.UserEntity;

//import com.telehems.microgrid.cache.model.UnNormalCache;
//import com.telehems.microgrid.model.UserInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 系统工具
 * 
 * @author Xugl
 * 
 * @date 2017年3月17日 下午7:17:10
 */
public class Util {
	private static final Logger LOG = Logger.getLogger(Util.class);

	/**
	 * 获取云端请求配置参数
	 * 
	 * @param key
	 *            关键字
	 * @return
	 * @author Xugl @2016-10-25 上午10:40:30
	 */
	public static String getCloudPropByKey(String key) {
		if (UnNormalCache.cloudProp == null) {
			UnNormalCache.cloudProp = Util.readPropertiesFile(HttpUtil.class, "cloudurl.properties");
		}
		return UnNormalCache.cloudProp.getProperty(key);
	}

	// 读取资源文件,并处理中文乱码
	public static Properties readPropertiesFile(Class clazz, String filename) {
		Properties properties = new Properties();
		InputStream inputStream = clazz.getClassLoader().getResourceAsStream(filename);
		try {
			properties.load(inputStream);
			inputStream.close(); // 关闭流
		} catch (IOException e) {
			properties = null;
			LOG.error("云端配置文件读取异常" + e.getMessage());
		} finally {
			return properties;
		}
	}

	/**
	 * 日期转化为制定的字符串类型
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 * @author Xugl
	 * @2017年3月17日 下午7:24:20
	 */
	public static String formatDate(Date date, String pattern) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	/**
	 * 判断字符串是否为空 null or ""
	 */
	public static boolean isEmpty(String str) {
		return StringUtils.isBlank(str);
	}

	public static boolean isEmpty(List<?> list) {
		return (list == null || list.size() == 0);
	}

	/**
	 * 将以splitStr分割的字符串转化为sql in语句中直接可以使用的字符串
	 * 
	 * @param str
	 * @param splitStr
	 * @return
	 * @author Xugl @2016-7-19 上午8:55:07
	 */
	public static String strToSqlInStr(String str, String splitStr) {
		if ("".equals(str)) {
			return str;
		}
		StringBuffer sb = new StringBuffer();
		sb.append("'");
		sb.append(str.replace(splitStr, "','"));
		sb.append("'");
		return sb.toString();
	}

	/**
	 * 将List转化为sql in语句中直接可以使用的字符串
	 * 
	 * @param list
	 * @return
	 * @author Shoukr
	 * @2017-5-9 上午9:52:02
	 */
	public static String listToSqlInStr(List<String> list) {
		if (list.size() == 0) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (String str : list) {
			sb.append("'");
			sb.append(str);
			sb.append("'");
			sb.append(",");
		}
		String result = sb.toString();
		return subStrLastChar(result);
	}

	/**
	 * list转字符串,按要求隔开
	 * 
	 * @author zhanxf
	 * @date 2016年2月26日 上午10:19:58
	 * @param list
	 * @return
	 */
	public static String listToStr(List<?> list, String regex) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i));
			if (i != list.size() - 1) {
				sb.append(regex);
			}
		}
		return sb.toString();
	}

	public static List<String> strToList(String param, String regex) {
		String[] arr = param.split(regex);
		List<String> list = new ArrayList<String>();
		for (String s : arr) {
			list.add(s);
		}
		return list;
	}

	/**
	 * String 字符串转List
	 * 
	 * @param str
	 * @return
	 * @author wangxl
	 * @date 2016-3-10
	 */
	public static List<Double> strToDoublelist(String str, String splitStr) {
		List<Double> list = new ArrayList<Double>();
		String[] strs = str.split(splitStr);
		for (int i = 0; i < strs.length; i++) {
			list.add(Double.parseDouble(strs[i]));
		}
		return list;
	}

	/**
	 * 字符串剪切最后一个字段
	 * 
	 * @param str
	 *            字符串
	 * @return String
	 * @author Xugl @2016-3-30 上午11:18:05
	 */
	public static String subStrLastChar(String str) {
		if (str.length() != 0) {
			str = str.substring(0, str.length() - 1);
		}
		return str;
	}

	/**
	 * 校验用户输入，防止sql注入
	 * 
	 * @param userInputStr
	 *            用户输入
	 * @return 校验结果
	 * @author Xugl @2016-7-19 下午1:55:29
	 */
	public static boolean checkUserInput4SqlInject(String userInputStr) {
		// 原始长度
		int srcLen = 0;
		// 校验后长度
		int decLen = 0;

		userInputStr = userInputStr.toLowerCase();
		srcLen = userInputStr.length();

		// 数据操作
		userInputStr = userInputStr.replace("insert ", "").replace("delete ", "").replace("update ", "")
				.replace("select ", "");

		// 数据结构操作
		userInputStr = userInputStr.replace("create ", "").replace("drop ", "").replace("alter ", "");

		// 特定非法字符
		userInputStr = userInputStr.replace("=", "").replace("<", "").replace(">", "").replace("'", "").replace("(", "")
				.replace(")", "");

		decLen = userInputStr.length();
		return srcLen == decLen;
	}

	/**
	 * 转义用户输入，用于转义某些业务允许，但会影响sql语句的关键词
	 * 
	 * @param userInputStr
	 *            用户输入
	 * @return 转义后的用户输入
	 * @author Xugl @2016-7-19 下午2:41:10
	 */
	public static String transferUserInput(String userInputStr) {
		System.out.println("userInputStr");
		return userInputStr.replaceAll("_", "\\\\_").replaceAll("%", "\\\\%");
	}

	/**
	 * 获取当前用户
	 * 
	 * @author zhanxf
	 * @date 2016年3月5日 下午5:32:15
	 * @return
	 */
	public static UserEntity getCurrentUser(HttpServletRequest request) {
		return (UserEntity) request.getSession().getAttribute("loginUser");
	}

	/**
	 * 获取ip地址
	 * 
	 * @param request
	 * @return
	 * @author Wanghj
	 * @2016-8-8 下午6:43:56
	 */
	public static String getRemortIP(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 获取uuid
	 * 
	 * @return uuid
	 * @author Xugl
	 * @2016-1-6 下午6:11:49
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 获取带描述的失败返回
	 * 
	 * @param desc
	 * @return
	 * @author Xugl
	 * @2017年3月21日 上午10:49:59
	 */
	public static String getFailedReturnWithDesc(String desc) {
		JSONObject backJson = new JSONObject();
		backJson.put(ConstantVar.RESULT, ConstantVar.FAILED);
		backJson.put(ConstantVar.DESC, desc);
		return backJson.toString();
	}

	public static String getSuccessedReturnWithJsonArray(JSONArray arr) {
		JSONObject backJson = new JSONObject();
		backJson.put(ConstantVar.TOTAL, arr.size());
		backJson.put(ConstantVar.ROOT, arr);
		return backJson.toString();
	}

	/**
	 * 验证云端返回结果
	 * 
	 * @param result
	 * @return
	 * @author zzy @2016-7-25 上午11:06:14
	 */
	public static String checkCloudResponseValid(String result) {
		if (isEmpty(result)) {
			return ConstantVar.NULL_ERROR;
		} else if (result.equals(ConstantVar.NET_ERROR)) {
			return ConstantVar.NET_ERROR;
		} else if (result.equals(ConstantVar.SYSTEM_ERROR)) {
			return ConstantVar.SYSTEM_ERROR;
		} else {
			return result;
		}
	}

	/**
	 * 获取基线所需的日期
	 * 
	 * @param time
	 *            开始时间
	 * @return List<String>基线基准日期列表
	 * @author wangxl
	 * @date 2016-2-29
	 */
	public static List<String> getForecastDate(String startTime, String eventExecuteDate) {
		List<String> forecateDays = new ArrayList<String>();
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(startTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int count = 1;
		int total = 10;
		if (judgeWeekend(date)) {// 如果是周六周日
			total = 4;
			while (true) {
				long l = date.getTime() - ConstantVar.MS_OF_DAY * count++;
				Date d = new Date(l);
				String timeToDay = Util.formatDate(d, "yyyy-MM-dd");
				// 若不是周末进入下一个循环
				if (!judgeWeekend(d))
					continue;
				// 若该天执行过需求响应，跳到下一个循环
				if (eventExecuteDate.contains(timeToDay))
					continue;
				forecateDays.add(timeToDay);
				total--;
				if (total <= 0)
					break;
			}
		} else {
			while (true) {
				long l = date.getTime() - ConstantVar.MS_OF_DAY * count++;
				Date d = new Date(l);
				String timeToDay = Util.formatDate(d, "yyyy-MM-dd");
				if (judgeWeekend(d))
					continue;
				if (eventExecuteDate.contains(timeToDay))
					continue;
				forecateDays.add(timeToDay);
				total--;
				if (total <= 0)
					break;
			}
		}
		Collections.reverse(forecateDays);
		return forecateDays;
	}

	/**
	 * 判断某一天是否是周六、周日
	 * 
	 * @param date
	 * @return true 是 false 不是
	 * @date 2016-1-4
	 * @author wang-xinglei
	 */
	public static boolean judgeWeekend(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if (cal.get(Calendar.DAY_OF_WEEK) - 1 == 6 || cal.get(Calendar.DAY_OF_WEEK) - 1 == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 保留若干位小数 Description: 2016-1-11
	 * 
	 * @author zhanxf
	 * @param data
	 * @param format
	 * @return
	 */
	public static double doubleFloor(double data, String format) {
		DecimalFormat df = new DecimalFormat(format);
		return Double.parseDouble(df.format(data));
	}

	/**
	 * 时间戳转时间格式
	 * 
	 * @param s
	 * @param partten
	 *            如"yyyy-MM-dd HH:mm"
	 * @return
	 * @author Shoukr @2017-3-22 上午10:28:23
	 */
	public static String stampToDate(long s, String partten) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(partten);
		Date date = new Date(s);
		return simpleDateFormat.format(date);
	}

	/**
	 * 
	 * @param startTime
	 *            开始时间，yyyy-MM-dd HH:mm:ss
	 * @param stepTimeSec
	 *            事件间隔，秒为单位
	 * @param count
	 * 
	 * @param formatPattern
	 *            输出格式
	 * @return
	 * @author Xugl
	 * @2017年3月23日 下午2:47:58
	 */
	public static List<String> getXAxisTimeListByParam(String startTime, long stepTimeSec, int count,
			String formatPattern) {
		List<String> list = new ArrayList<String>();
		DateFormat df = new SimpleDateFormat(formatPattern);
		try {
			long timeMills = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startTime).getTime();
			for (int i = 0; i < count; i++) {
				list.add(df.format(new Date(timeMills)));
				timeMills += stepTimeSec * 1000;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.gc();
		return list;
	}

	/**
	 * 根据jsonobject中的字段进行排序
	 * 
	 * @param array
	 * @param field
	 *            字段名称
	 * @param isAsc
	 *            是否升序
	 * @return
	 * @author Shoukr
	 * @2017-4-6 下午2:05:47
	 */
	@SuppressWarnings("unchecked")
	public static JSONArray sortJSONArray(JSONArray array, final String field, boolean isAsc) {
		Collections.sort(array, new Comparator<JSONObject>() {
			public int compare(JSONObject o1, JSONObject o2) {
				Object f1 = o1.get(field);
				Object f2 = o2.get(field);
				return f1.toString().compareTo(f2.toString());
			}
		});
		if (!isAsc) {
			Collections.reverse(array);
		}
		return array;
	}

	/**
	 * 补0
	 * 
	 * @author zhanxf
	 * @date 2016年5月20日 下午8:32:37
	 * @param i
	 * @return
	 */
	public static String addZero(int i) {
		return i < 10 ? ("0" + i) : ("" + i);
	}

	/**
	 * 获取前一天
	 * 
	 * @return
	 * @author Shoukr @2017-4-17 下午1:29:38
	 */
	public static String getDayBefore() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		Date time = cal.getTime();
		return ConstantVar.DAY_SDF.format(time);
	}

	/**
	 * 获取今年开始前n年所有的年份，包括今年
	 * 
	 * @param n
	 * @return
	 * @author Shoukr @2017-4-20 上午10:03:30
	 */
	public static String getYearBefore(int n) {
		StringBuilder str = new StringBuilder();
		if (n > 0) {
			for (int i = n; i >= 0; i--) {
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.YEAR, -i);
				Date time = cal.getTime();
				str.append(ConstantVar.YEARY_SDF.format(time) + ",");
			}
			return subStrLastChar(str.toString());
		} else {
			return null;
		}
	}

	/**
	 * 格式化时间
	 * 
	 * @param start
	 * @param end
	 * @return
	 * @author Shoukr @2017-4-17 下午4:56:52
	 */
	public static List<String> parseTime(String start, String end) {
		List<String> times = new ArrayList<String>();

		try {
			long s = ConstantVar.DAY_SDF.parse(start).getTime();
			long e = ConstantVar.DAY_SDF.parse(end).getTime();
			for (long i = s; i <= e; i += ConstantVar.MS_OF_DAY) {
				Date date = new Date(i);
				times.add(ConstantVar.DAY_SDF.format(date));
			}
			return times;
		} catch (ParseException e1) {
			e1.printStackTrace();
			return null;
		}

	}

	/**
	 * 根据间隔数获取2个时间点的时间间隔， Description: 2015-12-30
	 * 
	 * @author zhanxf
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws ParseException
	 */
	public static long getTimeSpanBySpanNum(String startTime, String endTime, int spanNum) throws ParseException {
		long lstartTime = ConstantVar.DAY_SDF.parse(startTime).getTime();
		long lendTime = ConstantVar.DAY_SDF.parse(endTime).getTime() + ConstantVar.MS_OF_DAY;
		return (lendTime - lstartTime) / spanNum;
	}

	/**
	 * 获取当前时间
	 * 
	 * @param pattern
	 *            时间模板
	 * @return
	 * @author Shoukr @2017-4-18 上午9:01:32
	 */
	public static String getCurrentTime(String pattern) {
		Calendar c = Calendar.getInstance();
		return new SimpleDateFormat(pattern).format(c.getTime());
	}

	/**
	 * 
	 * 将字符串转化为Date类型
	 * 
	 * @param pattern
	 *            字符串格式
	 * @param dateStr
	 *            待转换字符串
	 * @return 日期格式
	 * @date 2015-12-2
	 */
	public static Date parseDate(String pattern, String dateStr) {
		try {
			return new SimpleDateFormat(pattern).parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * Description: 同比计算 同比增长率=（本次的指标值-上次同期的值）÷|上次同期的值|*100%
	 * 
	 * @author zhanxf
	 * @date 2016-1-21
	 * @param before
	 *            去年同期数据
	 * @param current
	 *            当年该月数据
	 * @return
	 */
	public static double tongbi(double before, double current) {
		double tongbiD = 0d;
		if (before != 0d) {
			tongbiD = ((current - before) / Math.abs(before)) * 100;
			tongbiD = Util.doubleFloor(tongbiD, "#.00");
		} else if (current > 0d) {
			tongbiD = 100d;
		}
		return tongbiD;
	}

	/**
	 * 获取三相电压是否异常状态（此方法为暂定方法，后续待三相失衡评判标准敲定了再重写）
	 * 
	 * @return
	 * @author zzy @2016-11-07 上午10:40:30
	 */
	public static boolean getThreePhaseVoltageState(double aValue, double bValue, double cValue) {
		double minStandardVoltage = 200;
		double maxStandardVoltage = 400;
		if ((aValue > minStandardVoltage && aValue < maxStandardVoltage)
				&& (bValue > minStandardVoltage && bValue < maxStandardVoltage)
				&& (cValue > minStandardVoltage && cValue < maxStandardVoltage)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 返回对象字符串，对于出错情况返回空字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static String toString(Object obj) {
		try {
			return obj == null ? "" : obj.toString();
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 字符转int Description: Date:2012-11-3
	 * 
	 * @author wm
	 * @param @param
	 *            str
	 * @param @return
	 * @return long
	 */
	public static int parseInt(String str) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
			return 0;
		}
	}
	
	/**
	 * 此接口完全是为了顺应云端的接口传参（websetparameter这个云端接口）
	 * @param appTypeId
	 * @return 
	 * @author Shoukr
	 * @2017-6-29 下午1:57:04
	 */
	public static String getAppTypeOrder(String appTypeId){
		String order = "0";
		if(ConstantVar.APPTYPE_OF_WINDPOWER.equals(appTypeId)){
			order = "1";
		} else if(ConstantVar.APPTYPE_OF_PHOTOVOLTAICPOWER.equals(appTypeId)){
			order = "2";
		} else if(ConstantVar.APPTYPE_OF_GASTURBINEPOWER.equals(appTypeId)){
			order = "3";
		} else if(ConstantVar.APPTYPE_OF_BATTERY.equals(appTypeId)){
			order = "4";
		} else if(ConstantVar.APPTYPE_OF_HOT_STORAGE.equals(appTypeId)){
			order = "5";
		} else if(ConstantVar.APPTYPE_OF_COOL_STORAGE.equals(appTypeId)){
			order = "6";
		}
		return order;
	}

}
