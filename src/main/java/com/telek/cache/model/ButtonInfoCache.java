package com.telek.cache.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import com.telek.listener.DIBean;
import com.telek.model.BaseModel;
import com.telek.model.ButtonInfo;
import com.telek.util.ConstantVar;
import com.telek.util.Util;


/**
 * 操作关联缓存
 * 
 * @author Xugl
 *
 * @date 2017年5月12日 上午9:50:43
 */
public class ButtonInfoCache extends ObserverModel {

	/**
	 * 按钮缓存
	 */
	public static Map<String, ButtonInfo> btnInfoMap;

	@Override
	public void update(Observable o, Object arg) {
		Map<String, Object> map = (Map<String, Object>) arg;
		BaseModel baseModel = (BaseModel) map.get("javaBean");
		if (baseModel instanceof ButtonInfo) {
			int operatingType = Util.parseInt(Util.toString(map.get("operatingType")));
			ButtonInfo btnInfo = (ButtonInfo) baseModel;
			if (btnInfo != null) {
				switch (operatingType) {
				case ConstantVar.DEL_DATA:
					btnInfoMap.remove(btnInfo.getBtnId());
					break;
				case ConstantVar.ADD_DATA:
					btnInfoMap.put(btnInfo.getBtnId(), btnInfo);
					break;
				case ConstantVar.UPD_DATA:
					btnInfoMap.put(btnInfo.getBtnId(), btnInfo);
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
		loadBtnInfoCache();
	}

	@Override
	public void clearAll() {
		btnInfoMap.clear();
	}

	/**
	 * 加载按钮缓存
	 * 
	 * @author Wanghj
	 * @2016-8-9 上午10:13:01
	 */
	@SuppressWarnings("unchecked")
	public void loadBtnInfoCache() {
		List<ButtonInfo> buttonList = (List<ButtonInfo>) DIBean.iDAO.findByHql("from ButtonInfo");
		btnInfoMap = new HashMap<String, ButtonInfo>();
		for (ButtonInfo btnInfo : buttonList) {
			btnInfoMap.put(btnInfo.getBtnId(), btnInfo);
		}
	}

}
