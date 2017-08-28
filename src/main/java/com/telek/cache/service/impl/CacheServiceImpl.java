package com.telek.cache.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.telek.cache.model.CacheModelManager;
import com.telek.cache.service.CacheService;
import com.telek.dao.IDAO;
import com.telek.model.BaseModel;
import com.telek.util.ConstantVar;

@Component("cacheService2")
public class CacheServiceImpl implements CacheService {
	@Autowired
	private IDAO iDAO;

	public Object updCacheData(BaseModel javaBean, int operatingType) {
		if (null == javaBean) {
			System.out.println("error: null");
			return null;
		}
		System.out.println("loadingl");
		// 更新数据库
		dealUpdCahceDate(javaBean, operatingType);
		// 更新缓存
		Map<String, Object> map = formatArgPattern(javaBean, operatingType);
		CacheModelManager.cachemode.notifyObservers(map);
		return javaBean;
	}

	private Map<String, Object> formatArgPattern(BaseModel javaBean, int operatingType) {
		// 封装成协议的格式
		Map<String, Object> updMap = new HashMap<String, Object>();
		updMap.put("javaBean", javaBean);
		updMap.put("operatingType", operatingType);
		return updMap;
	}

	/**
	 * 更新数据库
	 * 
	 * @author wm
	 * @param @param
	 *            javaBean
	 * @param @param
	 *            operatingType
	 * @return void
	 */
	private void dealUpdCahceDate(BaseModel javaBean, int operatingType) {
		// 根据操作类型执行相应数据库操作
		switch (operatingType) {
		case ConstantVar.ADD_DATA:
			addBaseModel(javaBean);
			break;
		case ConstantVar.UPD_DATA:
			updBaseModel(javaBean);
			break;
		case ConstantVar.DEL_DATA:
			deleteBaseModel(javaBean);
			break;
		default:
			break;
		}
	}

	/**
	 * 删除数据 Description: Date:2013-3-27
	 * 
	 * @author wm
	 * @param @param
	 *            javaBean
	 * @return void
	 */
	private void deleteBaseModel(BaseModel javaBean) {
		iDAO.delBaseModel(javaBean);
	}

	/**
	 * 修改数据 Description: Date:2013-3-27
	 * 
	 * @author wm
	 * @param @param
	 *            javaBean
	 * @return void
	 */
	private void updBaseModel(BaseModel javaBean) {
		iDAO.saveOrUpdate(javaBean);
	}

	/**
	 * 向数据库中新增数据 Description: Date:2013-3-26
	 * 
	 * @author wm
	 * @param session
	 * @param @param
	 *            javaBean
	 * @return void
	 */
	private void addBaseModel(BaseModel javaBean) {
		iDAO.save(javaBean);
	}
}
