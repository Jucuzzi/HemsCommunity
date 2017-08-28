package com.telek.cache.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import org.springframework.transaction.annotation.Transactional;

import com.telek.listener.DIBean;
import com.telek.model.BaseModel;
import com.telek.model.ConfigInfo;
import com.telek.util.ConstantVar;
import com.telek.util.Util;

/**
 * 操作关联缓存
 * 
 * @author Xugl
 *
 * @date 2017年5月12日 上午9:50:43
 */
public class ConfigInfoCache extends ObserverModel {

	/**
	 * 系统配置信息
	 */
	public static Map<String, String> configMap;

	@Override
	public void update(Observable o, Object arg) {
		Map<String, Object> map = (Map<String, Object>) arg;
		BaseModel baseModel = (BaseModel) map.get("javaBean");
		if (baseModel instanceof ConfigInfo) {
			int operatingType = Util.parseInt(Util.toString(map.get("operatingType")));
			ConfigInfo obj = (ConfigInfo) baseModel;
			if (obj != null) {
				switch (operatingType) {
				case ConstantVar.DEL_DATA:
					configMap.remove(obj.getConfigName());
					break;
				case ConstantVar.ADD_DATA:
					configMap.put(obj.getConfigName(), obj.getConfigValue());
					break;
				case ConstantVar.UPD_DATA:
					configMap.put(obj.getConfigName(), obj.getConfigValue());
					break;
				case ConstantVar.INIT_DATA:
					this.initData();
					break;
				default:
					break;
				}
			}
		}
	}

	@Override
	public void initData() {
		loadConfigInfoCache();
	}

	@Override
	public void clearAll() {
		configMap.clear();
	}

	@SuppressWarnings("unchecked")
	public void loadConfigInfoCache() {
		configMap = new HashMap<String, String>();
		List<ConfigInfo> configList = (List<ConfigInfo>) DIBean.iDAO.findByHql("from ConfigInfo");
		System.out.println(configList);
		for (ConfigInfo config : configList) {
			configMap.put(config.getConfigName(), config.getConfigValue());
		}
	}
}
