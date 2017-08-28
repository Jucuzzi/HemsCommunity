package com.telek.cache.model;

import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

/**
 * Description: CacheModelManager.java Create on 2013-2-6 上午11:25:15
 * 
 * @author 吕蔡俊
 * @version 1.0 Copyright (c) 2013 telek. All Rights Reserved.
 */
@Component("cacheModelManager")
public class CacheModelManager {
	private static final Log LOG = LogFactory.getLog(CacheModelManager.class);
	public static CacheModel cachemode = new CacheModel();

	private static Set<ObserverModel> set;
	static {
		// 初始化缓存
		set = new LinkedHashSet<ObserverModel>();
	}

	/**
	 * Description:初始化缓存数据，并启动端口监听，启动队列处理线程 Date:2013-6-7
	 * 
	 * @author Administrator
	 * @param applicationContext_
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 *             void
	 */
	public static void init() {
		initObserverMap();
		registerObservable();
		for (ObserverModel o : set) {
			o.initData();
		}
	}

	public static void initObserverMap() {
		// code顺序必须为基本类型在扩展类型前面，防止扩展类型缓存更新失败
		set.clear();
		set.add(new ConfigInfoCache());
		set.add(new MenuInfoCache());
		set.add(new ButtonInfoCache());
		set.add(new RoleInfoCache());
		set.add(new UserInfoCache());
//		set.add(new ApplianceTypeInfoCache());
//		set.add(new HemsUserInfoCache());
//		set.add(new ApplianceInfoCache());
//		set.add(new DREventRecCache());
//		set.add(new SystemInfoCache());
	}

	/**
	 * Description:依据路由表将对应缓存注册为观察者 Date:2013-6-7
	 * 
	 * @author Administrator
	 * @param id
	 * @param version
	 *            void
	 */
	private static void registerObservable() {
		for (ObserverModel tempObserverModel : set) {
			try {
				cachemode.addObserver(tempObserverModel);
			} catch (Exception e) {
				LOG.error(tempObserverModel.getClass() + "配置错误", e);
			}
		}
	}

	public static Set<ObserverModel> getObserverModelSet() {
		return set;
	}
}
