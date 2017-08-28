package com.telek.listener;

import com.telek.cache.model.CacheModelManager;
import com.telek.cache.model.UnNormalCache;
import com.telek.dao.IDAO;

/**
 * 获取spring依赖注入的类，当new一个类，类中需要一些spring管理的类时，可以使用该类
 * 
 * @author zhanxf 2016年3月14日
 */
public class DIBean {
	public static IDAO iDAO;
	public static CacheModelManager cacheModelManager;
	public static UnNormalCache unNormalCache;
}
