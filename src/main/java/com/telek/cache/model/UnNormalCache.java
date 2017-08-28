package com.telek.cache.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//import com.telehems.microgrid.correspondence.HemsCloudMutual;
//import com.telehems.microgrid.dao.IControlDAO;
//import com.telehems.microgrid.dao.IDAO;
//import com.telehems.microgrid.model.ApplianceInfo;
//import com.telehems.microgrid.model.ApplianceTypeInfo;
//import com.telehems.microgrid.model.OptCommandInfo;
import com.telek.util.ConstantVar;
//import com.telehems.microgrid.util.ConstantVar;
//import com.telehems.microgrid.util.Util;

/**
 * 非常规缓存
 * 
 * @author Xugl
 * 
 * @date 2017年5月22日 下午6:28:52
 */
@Component("unNormalCache")
public class UnNormalCache {
	private static final Logger LOG = Logger.getLogger(UnNormalCache.class);

//	@Autowired
//	private IDAO iDAO;
//	@Autowired
//	private IControlDAO controlDAO;
//	@Autowired
//	private HemsCloudMutual hemsCloudMutual;
//
//	public void initData() {
//		LOG.info("非常规缓存加载开始");
//		loadRealAndBaseLoadLineMap();
//		initTimmingTaskMap();
//		LOG.info("非常规缓存加载完成");
//	}

	/**
	 * 云端请求配置参数
	 */
	public static Properties cloudProp;
	/**
	 * 用于临时存储上一个动态密码，已防止本地动态密码与云端动态密码在短时间内的不一致
	 */
	public static String oldDynPassword = "";
//
//	/**
//	 * 风电定时任务 key-执行时间
//	 */
//	public static Map<String, List<OptCommandInfo>> windPowerGeneratorTimmingTaskMap = new HashMap<String, List<OptCommandInfo>>();
//	/**
//	 * 光伏定时任务 key-执行时间
//	 */
//	public static Map<String, List<OptCommandInfo>> photovoltaicGeneratorTimmingTaskMap = new HashMap<String, List<OptCommandInfo>>();
//
//	/**
//	 * 燃机定时任务 key-执行时间
//	 */
//	public static Map<String, List<OptCommandInfo>> gasGeneratorTimmingTaskMap = new HashMap<String, List<OptCommandInfo>>();
//	/**
//	 * 蓄电池定时任务 key-执行时间
//	 */
//	public static Map<String, List<OptCommandInfo>> powerStorageTimmingTaskMap = new HashMap<String, List<OptCommandInfo>>();
//
//	/**
//	 * 蓄冷罐定时任务 key-执行时间
//	 */
//	public static Map<String, List<OptCommandInfo>> coolStorageTimmingTaskMap = new HashMap<String, List<OptCommandInfo>>();
//	/**
//	 * 蓄热罐定时任务 key-执行时间
//	 */
//	public static Map<String, List<OptCommandInfo>> hotStorageTimmingTaskMap = new HashMap<String, List<OptCommandInfo>>();
//
//	/**
//	 * 充电桩系统定时任务 key-执行时间
//	 */
//	public static Map<String, List<OptCommandInfo>> evTimmingTaskMap = new HashMap<String, List<OptCommandInfo>>();
//
//	// 实际负荷线临时记录 燃机做特殊处理（燃机设备id_1:电；燃机设备id_2:冷；燃机设备id_3:热；）
//	public static Map<String, Map<String, double[]>> realLoadLineMap = new HashMap<String, Map<String, double[]>>();
//	/**
//	 * 基线负荷
//	 */
//	public static Map<String, Map<String, double[]>> baseLoadLineMap = new HashMap<String, Map<String, double[]>>();
//
//	/**
//	 * 外网当天电量 key-hour,day
//	 */
//	public static Map<String, String> externalPowerMap = new HashMap<String, String>();
//
//	/**
//	 * 实时负荷记录，key-appTypeId，value-实时负荷
//	 */
//	public static Map<String, Double> realLoadMap = new HashMap<String, Double>();
//
//	@SuppressWarnings("unchecked")
//	public void loadRealAndBaseLoadLineMap() {
//		Calendar cal = Calendar.getInstance();
//		String today = ConstantVar._DAY_SDF.format(cal.getTime());
//		cal.add(Calendar.DATE, 1);
//		String tomorrow = ConstantVar._DAY_SDF.format(cal.getTime());
//		cal.add(Calendar.DATE, -2);
//		String yestoday = ConstantVar._DAY_SDF.format(cal.getTime());
//		List<ApplianceTypeInfo> list = (List<ApplianceTypeInfo>) iDAO
//				.findByHql("from ApplianceTypeInfo");
//		List<String> appTypeIdList = new LinkedList<String>();
//		for (ApplianceTypeInfo appType : list) {
//			appTypeIdList.add(appType.getAppTypeId());
//		}
//		String realLoad = hemsCloudMutual.getLoadLine(yestoday, today,
//				appTypeIdList, "1");
//		String baseLoad = hemsCloudMutual.getLoadLine(yestoday, tomorrow,
//				appTypeIdList, "3");
//		Map<String, Map<String, String>> realLoadLineResult = JSONObject
//				.fromObject(realLoad).getJSONObject("data");		
//		Map<String, Map<String, String>> baseLoadLineResult = JSONObject
//				.fromObject(baseLoad).getJSONObject("data");
//		for (Map.Entry<String, Map<String, String>> entry : realLoadLineResult
//				.entrySet()) {
//			realLoadLineMap
//					.put(entry.getKey(), new HashMap<String, double[]>());
//			JSONArray a = new JSONArray();
//			for (Map.Entry<String, String> subEntry : entry.getValue()
//					.entrySet()) {
//				a = JSONArray.fromObject(subEntry.getValue());
//				double[] d = (double[]) JSONArray.toArray(a, double.class);
//				realLoadLineMap.get(entry.getKey()).put(subEntry.getKey(),d);
//			}		
//		}
//		for (Map.Entry<String, Map<String, String>> entry : baseLoadLineResult
//				.entrySet()) {
//			baseLoadLineMap
//					.put(entry.getKey(), new HashMap<String, double[]>());
//			JSONArray a = new JSONArray();
//			for (Map.Entry<String, String> subEntry : entry.getValue()
//					.entrySet()) {
//				a = JSONArray.fromObject(subEntry.getValue());
//				baseLoadLineMap.get(entry.getKey()).put(subEntry.getKey(),
//						(double[]) JSONArray.toArray(a, double.class));
//			}
//		}
//		dealTotalLoad(yestoday,1);
//		dealTotalLoad(today,1);
//		dealTotalLoad(yestoday,2);
//		dealTotalLoad(today,2);
//		dealTotalLoad(tomorrow,2);
//	}
//	
//	/**
//	 * 
//	 * @param time 时间（20170612）
//	 * @param type 类型 1-实时负荷；2-基线
//	 * @author Shoukr
//	 * @2017-6-12 下午4:43:49
//	 */
//	public static void dealTotalLoad(String time,int type){
//		Map<String, Map<String, double[]>> map = new  HashMap<String, Map<String,double[]>>();
//		if(type==1){
//			map = realLoadLineMap;
//		} else if(type==2){
//			map = baseLoadLineMap;
//		}
//		double[] windResult = map.get(time).get(ConstantVar.APPTYPE_OF_WINDPOWER);
//		double[] photovoltaicResult = map.get(time).get(ConstantVar.APPTYPE_OF_PHOTOVOLTAICPOWER);
//		double[] gasResult = map.get(time).get(ConstantVar.APPTYPE_OF_GASTURBINEPOWER+"_1");
//		double[] evLoadResult = map.get(time).get(ConstantVar.APPTYPE_OF_CHARGINGPILE);
//		double[] cLoadResult = map.get(time).get(ConstantVar.APPTYPE_OF_CONVENTIONALLOAD);
//		double[] sLoadResult = map.get(time).get(ConstantVar.APPTYPE_OF_SENSITIVELOAD);
//		double[] battreyProductResult = map.get(time).get(ConstantVar.APPTYPE_OF_BATTERY+"_1");
//		double[] battreyConsumeResult = map.get(time).get(ConstantVar.APPTYPE_OF_BATTERY+"_2");
//
//		double[] consumeResult = new double[windResult.length];
//		double[] produceResult = new double[windResult.length];
//		for (int i = 0, len = windResult.length; i < len; i++) {
//			consumeResult[i] = Util.doubleFloor(evLoadResult[i]+cLoadResult[i]+sLoadResult[i]+battreyConsumeResult[i],".##");
//			produceResult[i] = Util.doubleFloor(windResult[i]+photovoltaicResult[i]+gasResult[i]+battreyProductResult[i],".##");
//		}
//		map.get(time).put(ConstantVar.APPTYPE_OF_MGUSELOAD, consumeResult);
//		if(type==1){
//			map.get(time).put(ConstantVar.APPTYPE_OF_MGPRODUCELOAD, produceResult);
//		}
//	}
//
//	/**
//	 * 初始化定时任务缓存
//	 * 
//	 * @author Shoukr @2017-3-23 上午9:20:06
//	 */
//	public void initTimmingTaskMap() {
//		String time = Util.stampToDate(System.currentTimeMillis(),
//				"yyyy-MM-dd HH:mm");
//		if (ConstantVar.MANUAL.equals(ConfigInfoCache.configMap
//				.get("photovoltaicGenerator"))) {
//			List<ApplianceInfo> appList = ApplianceInfoCache.appMapByAppType
//					.get(ConstantVar.APPTYPE_OF_PHOTOVOLTAICPOWER);
//			List<String> appIdList = new LinkedList<String>();
//			for (ApplianceInfo app : appList) {
//				appIdList.add(app.getAppId());
//			}
//			List<OptCommandInfo> list = controlDAO
//					.queryOptCommandListBySystemType1(
//							Util.listToSqlInStr(appIdList), time);
//			for (OptCommandInfo opt : list) {
//				if (!photovoltaicGeneratorTimmingTaskMap.containsKey(opt
//						.getExecuteTime())) {
//					photovoltaicGeneratorTimmingTaskMap.put(
//							opt.getExecuteTime(),
//							new ArrayList<OptCommandInfo>());
//				}
//				photovoltaicGeneratorTimmingTaskMap.get(opt.getExecuteTime())
//						.add(opt);
//			}
//		}
//		if (ConstantVar.MANUAL.equals(ConfigInfoCache.configMap
//				.get("windPowerGenerator"))) {
//			List<ApplianceInfo> appList = ApplianceInfoCache.appMapByAppType
//					.get(ConstantVar.APPTYPE_OF_WINDPOWER);
//			List<String> appIdList = new LinkedList<String>();
//			for (ApplianceInfo app : appList) {
//				appIdList.add(app.getAppId());
//			}
//			List<OptCommandInfo> list = controlDAO
//					.queryOptCommandListBySystemType1(
//							Util.listToSqlInStr(appIdList), time);
//			for (OptCommandInfo opt : list) {
//				if (!windPowerGeneratorTimmingTaskMap.containsKey(opt
//						.getExecuteTime())) {
//					windPowerGeneratorTimmingTaskMap.put(opt.getExecuteTime(),
//							new ArrayList<OptCommandInfo>());
//				}
//				windPowerGeneratorTimmingTaskMap.get(opt.getExecuteTime()).add(
//						opt);
//			}
//		}
//		if (ConstantVar.MANUAL.equals(ConfigInfoCache.configMap
//				.get("gasGenerator"))) {
//			List<ApplianceInfo> appList = ApplianceInfoCache.appMapByAppType
//					.get(ConstantVar.APPTYPE_OF_GASTURBINEPOWER);
//			List<String> appIdList = new LinkedList<String>();
//			for (ApplianceInfo app : appList) {
//				appIdList.add(app.getAppId());
//			}
//			List<OptCommandInfo> list = controlDAO
//					.queryOptCommandListBySystemType1(
//							Util.listToSqlInStr(appIdList), time);
//			for (OptCommandInfo opt : list) {
//				if (!gasGeneratorTimmingTaskMap.containsKey(opt
//						.getExecuteTime())) {
//					gasGeneratorTimmingTaskMap.put(opt.getExecuteTime(),
//							new ArrayList<OptCommandInfo>());
//				}
//				gasGeneratorTimmingTaskMap.get(opt.getExecuteTime()).add(opt);
//			}
//		}
//		if (ConstantVar.MANUAL.equals(ConfigInfoCache.configMap
//				.get("powerStorage"))) {
//			List<ApplianceInfo> appList = ApplianceInfoCache.appMapByAppType
//					.get(ConstantVar.APPTYPE_OF_BATTERY);
//			List<String> appIdList = new LinkedList<String>();
//			for (ApplianceInfo app : appList) {
//				appIdList.add(app.getAppId());
//			}
//			List<OptCommandInfo> list = controlDAO
//					.queryOptCommandListBySystemType1(
//							Util.listToSqlInStr(appIdList), time);
//			for (OptCommandInfo opt : list) {
//				if (!powerStorageTimmingTaskMap.containsKey(opt
//						.getExecuteTime())) {
//					powerStorageTimmingTaskMap.put(opt.getExecuteTime(),
//							new ArrayList<OptCommandInfo>());
//				}
//				powerStorageTimmingTaskMap.get(opt.getExecuteTime()).add(opt);
//			}
//		}
//		if (ConstantVar.MANUAL.equals(ConfigInfoCache.configMap
//				.get("coolStorage"))) {
//			List<ApplianceInfo> appList = ApplianceInfoCache.appMapByAppType
//					.get(ConstantVar.APPTYPE_OF_COOL_STORAGE);
//			List<String> appIdList = new LinkedList<String>();
//			for (ApplianceInfo app : appList) {
//				appIdList.add(app.getAppId());
//			}
//			List<OptCommandInfo> list = controlDAO
//					.queryOptCommandListBySystemType1(
//							Util.listToSqlInStr(appIdList), time);
//			for (OptCommandInfo opt : list) {
//				if (!coolStorageTimmingTaskMap
//						.containsKey(opt.getExecuteTime())) {
//					coolStorageTimmingTaskMap.put(opt.getExecuteTime(),
//							new ArrayList<OptCommandInfo>());
//				}
//				coolStorageTimmingTaskMap.get(opt.getExecuteTime()).add(opt);
//			}
//		}
//		if (ConstantVar.MANUAL.equals(ConfigInfoCache.configMap
//				.get("hotStorage"))) {
//			List<ApplianceInfo> appList = ApplianceInfoCache.appMapByAppType
//					.get(ConstantVar.APPTYPE_OF_HOT_STORAGE);
//			List<String> appIdList = new LinkedList<String>();
//			for (ApplianceInfo app : appList) {
//				appIdList.add(app.getAppId());
//			}
//			List<OptCommandInfo> list = controlDAO
//					.queryOptCommandListBySystemType1(
//							Util.listToSqlInStr(appIdList), time);
//			for (OptCommandInfo opt : list) {
//				if (!hotStorageTimmingTaskMap.containsKey(opt.getExecuteTime())) {
//					hotStorageTimmingTaskMap.put(opt.getExecuteTime(),
//							new ArrayList<OptCommandInfo>());
//				}
//				hotStorageTimmingTaskMap.get(opt.getExecuteTime()).add(opt);
//			}
//		}
//		if ((ConstantVar.MANUAL.equals(ConfigInfoCache.configMap
//				.get("EVChargerFlag")))) {
//			List<ApplianceInfo> appList = ApplianceInfoCache.appMapByAppType
//					.get(ConstantVar.APPTYPE_OF_CHARGINGPILE);
//			List<String> appIdList = new LinkedList<String>();
//			for (ApplianceInfo app : appList) {
//				appIdList.add(app.getAppId());
//			}
//			List<OptCommandInfo> list = controlDAO
//					.queryOptCommandListBySystemType1(
//							Util.listToSqlInStr(appIdList), time);
//			for (OptCommandInfo opt : list) {
//				if (!evTimmingTaskMap.containsKey(opt.getExecuteTime())) {
//					evTimmingTaskMap.put(opt.getExecuteTime(),
//							new ArrayList<OptCommandInfo>());
//				}
//				evTimmingTaskMap.get(opt.getExecuteTime()).add(opt);
//			}
//		}
//	}

}
