package com.telek.cache.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import com.telek.listener.DIBean;
import com.telek.model.BaseModel;
import com.telek.model.MenuInfo;
import com.telek.util.ConstantVar;
import com.telek.util.Util;

/**
 * 电器关联缓存
 * 
 * @author Xugl
 *
 * @date 2017年5月12日 上午9:50:43
 */
public class MenuInfoCache extends ObserverModel {

	/**
	 * 菜单信息
	 */
	public static Map<String, MenuInfo> menuInfoMap;

	@Override
	public void update(Observable o, Object arg) {
		Map<String, Object> map = (Map<String, Object>) arg;
		BaseModel baseModel = (BaseModel) map.get("javaBean");
		if (baseModel instanceof MenuInfo) {
			int operatingType = Util.parseInt(Util.toString(map.get("operatingType")));
			MenuInfo obj = (MenuInfo) baseModel;
			if (obj != null) {
				switch (operatingType) {
				case ConstantVar.DEL_DATA:
					menuInfoMap.remove(obj.getMenuId());
					break;
				case ConstantVar.ADD_DATA:
					menuInfoMap.put(obj.getMenuId(), obj);
					break;
				case ConstantVar.UPD_DATA:
					menuInfoMap.put(obj.getMenuId(), obj);
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
		loadMenuInfoCache();
	}

	@Override
	public void clearAll() {
		menuInfoMap.clear();
	}

	/**
	 * 加载菜单信息
	 * 
	 * @author Xugl
	 * @2016-1-7 上午10:03:43
	 */
	@SuppressWarnings("unchecked")
	public void loadMenuInfoCache() {
		List<MenuInfo> list = (List<MenuInfo>) DIBean.iDAO.findByHql("from MenuInfo");
		menuInfoMap = new LinkedHashMap<String, MenuInfo>();
		for (MenuInfo menu : list) {
			menuInfoMap.put(menu.getMenuId(), menu);
		}
	}
}
