package com.telek.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.telek.model.BaseModel;

public interface IDAO {

	/**
	 * 保存对象 - 同时处理更新和插入
	 * 
	 * @param entity
	 *            对象
	 * @return Object 返回对象
	 */
	public Object saveOrUpdate(final Object entity);

	/**
	 * 保存对象
	 */
	public boolean save(final Object obj);

	/**
	 * 批量保存对象 - 同时处理更新和插入
	 * 
	 * @param entities
	 *            对象集合
	 * @return List
	 */
	public List<?> saveOrUpdateAll(final Collection<?> entities);

	/**
	 * 删除对象 - 基于实例类及id
	 * 
	 * @param 数据库映射对象实例类
	 * @param id
	 *            对象id
	 */
	public void delObject(Class<?> clazz, Serializable id);

	/**
	 * 删除对象集合
	 * 
	 * @param entities
	 */
	public void delObjectAll(final Collection<?> entities);

	public void delByHql(final String hql);

	public void delBaseModel(BaseModel baseModel);

	/**
	 * 通用方法用于获得特定类型的对象。
	 * 
	 * @param clazz
	 *            数据库映射对象实例类
	 * @param id
	 *            对象id
	 * @return Object
	 */
	public Object getObject(Class<?> clazz, Serializable id);

	/**
	 * 通用方法用于获得特定类型的所有对象。
	 * 
	 * @param 数据库映射对象实例类
	 * @return 填充对象的列表
	 */
	public List<Object> getObjects(Class<Object> clazz);

	/**
	 * 查找指定字段值的对象集合
	 * 
	 * @param queryString
	 *            查询语句
	 * @param paramName
	 *            指定字段
	 * @param value
	 *            字段值
	 * @return 对象集合
	 */
	public List<?> findByNamedParam(String queryString, String paramName, Object value);

	/**
	 * 查找指定字段值（集）的对象集合
	 * 
	 * @param queryString
	 *            查询语句
	 * @param paramNames
	 *            指定字段集
	 * @param values
	 *            字段值集
	 * @return 对象集合
	 */
	public List<?> findByNamedParam(final String queryString, final String[] paramNames, final Object[] values);

	/**
	 * 查找对象集合
	 * 
	 * @param queryString
	 *            查询语句
	 * @return 对象集合
	 */
	public List<?> findByHql(String queryString);

	public List<?> findAllByClass(Class<?> clazz);

	/**
	 * 执行原生sql语句
	 * 
	 * @param queryString
	 *            sql语句
	 * @param Scalar
	 * @param clazz
	 * @return
	 */
	public List<?> findBySQL(String queryString, Class<?> clazz);

	/**
	 * 执行原生sql语句
	 * 
	 * @param queryString
	 *            sql语句
	 * @param Scalar
	 * @param clazz
	 * @return
	 */
	public List<?> findBySQL(String queryString);

	/**
	 * 批量执行sql语句
	 * 
	 * @param sqlList
	 * @return Integer
	 */
	public Integer executeSqlBatch(final List<String> sqlList);

	public int executeUpdateSQL(final String sql);

	public void flush();
	
	public void delObectAll(final String hql);

}
