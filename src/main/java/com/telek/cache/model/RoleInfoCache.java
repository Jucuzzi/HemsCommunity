package com.telek.cache.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import com.telek.listener.DIBean;
import com.telek.model.BaseModel;
import com.telek.model.RoleInfo;
import com.telek.util.ConstantVar;
import com.telek.util.Util;


/**
 * 操作关联缓存
 * 
 * @author Xugl
 *
 * @date 2017年5月12日 上午9:50:43
 */
public class RoleInfoCache extends ObserverModel {

	/**
	 * 用户缓存，key-userId， value-RoleInfo
	 */
	public static Map<String, RoleInfo> roleInfoMap;

	@Override
	public void update(Observable o, Object arg) {
		Map<String, Object> map = (Map<String, Object>) arg;
		BaseModel baseModel = (BaseModel) map.get("javaBean");
		if (baseModel instanceof RoleInfo) {
			int operatingType = Util.parseInt(Util.toString(map.get("operatingType")));
			RoleInfo roleInfo = (RoleInfo) baseModel;
			if (roleInfo != null) {
				switch (operatingType) {
				case ConstantVar.DEL_DATA:
					roleInfoMap.remove(roleInfo.getRoleId());
					break;
				case ConstantVar.ADD_DATA:
					roleInfoMap.put(roleInfo.getRoleId(), roleInfo);
					break;
				case ConstantVar.UPD_DATA:
					roleInfoMap.put(roleInfo.getRoleId(), roleInfo);
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
		loadRoleInfoCache();
	}

	@Override
	public void clearAll() {
		roleInfoMap.clear();
	}

	/**
	 * 加载角色信息表
	 * 
	 * @author Xugl @2015-12-31 上午8:59:58
	 */
	@SuppressWarnings("unchecked")
	private void loadRoleInfoCache() {
		List<RoleInfo> list = (List<RoleInfo>) DIBean.iDAO.findByHql("from RoleInfo");
		roleInfoMap = new HashMap<String, RoleInfo>();
		for (RoleInfo role : list) {
			roleInfoMap.put(role.getRoleId(), role);
		}
	}

}
