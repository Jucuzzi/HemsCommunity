package com.telek.cache.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import org.springframework.transaction.annotation.Transactional;

import com.telek.listener.DIBean;
import com.telek.model.BaseModel;
import com.telek.model.UserEntity;
import com.telek.util.ConstantVar;
import com.telek.util.Util;


/**
 * 操作关联缓存
 * 
 * @author Xugl
 *
 * @date 2017年5月12日 上午9:50:43
 */
public class UserInfoCache extends ObserverModel {
	/**
	 * 用户缓存，key-userId， value-UserInfo
	 */
	public static Map<String, UserEntity> userInfoMap;

	@Override
	public void update(Observable o, Object arg) {
		Map<String, Object> map = (Map<String, Object>) arg;
		BaseModel baseModel = (BaseModel) map.get("javaBean");
		if (baseModel instanceof UserEntity) {
			int operatingType = Util.parseInt(Util.toString(map.get("operatingType")));
			UserEntity userInfo = (UserEntity) baseModel;
			if (userInfo != null) {
				switch (operatingType) {
				case ConstantVar.DEL_DATA:
					userInfoMap.remove(userInfo.getId());
					break;
				case ConstantVar.ADD_DATA:
					userInfoMap.put(userInfo.getId()+"", userInfo);
					break;
				case ConstantVar.UPD_DATA:
					userInfoMap.put(userInfo.getId()+"", userInfo);
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
		loadUserInfoCache();
	}

	@Override
	public void clearAll() {
		userInfoMap.clear();
	}

	/**
	 * 加载用户信息表,groupUserMap将不同用户按分组id归到不同的分组下
	 * 
	 * @author zhanxf
	 * @date 2016-2-1
	 */
	@SuppressWarnings("unchecked")
	private void loadUserInfoCache() {
		List<UserEntity> list = (List<UserEntity>) DIBean.iDAO.findByHql("from UserEntity");
		userInfoMap = new HashMap<String, UserEntity>();
		for (UserEntity user : list) {
			userInfoMap.put(user.getId()+"", user);
		}
	}

}
