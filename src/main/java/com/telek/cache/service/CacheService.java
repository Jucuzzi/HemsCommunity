package com.telek.cache.service;

import com.telek.model.BaseModel;

public interface CacheService {
	/**
	 * 缓存更新接口，单次操作 Description: Date:2013-3-28
	 * 
	 * @author wm
	 * @param @param
	 *            javaBean
	 * @param @param
	 *            operatingType
	 * @param @return
	 * @return Object
	 */
	public Object updCacheData(BaseModel javaBean, int operatingType);
}