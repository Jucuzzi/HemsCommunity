package com.telek.daoImpl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.telek.cache.model.PageResults;
import com.telek.dao.IDAO;
import com.telek.model.BaseModel;

@Component("iDAO")
public class DAOImpl implements IDAO {
	private final Logger log = Logger.getLogger(getClass());

	@Autowired
    protected SessionFactory sessionFactory; 

	
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {  
        Session session = null;  
        if(null==session || false==session.isOpen()){  
            session = sessionFactory.openSession();  
        }  
          
        return session;  
    }  
      
    public void closeSession(Session session){  
        try {  
            if(null!=session && session.isOpen())  
                session.close();  
        } catch (HibernateException e) {  
            e.printStackTrace();  
        }  
    } 
    
    public void flush() {
        getSession().flush();
    }

    public void clear() {
        getSession().clear();
    }
  

	

	@SuppressWarnings("finally")
	public Object saveOrUpdate(Object entity) {
		
		if (entity == null) {
			log.error("load entity error: null");
			System.out.println("load entity error: null");
		}
		try {
			Session session = getSession();
			Transaction tx = session.beginTransaction();
			session.saveOrUpdate(entity);
			tx.commit();
			closeSession(session);
			System.out.println("loaded");
		} catch (DataAccessException e) {
			log.error("load entity error: " + e);
			entity = null;
		} finally {
			return entity;
		}
	}

	public boolean save(Object obj) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		session.save(obj);
		tx.commit();
		closeSession(session);
		return true;
	}

	public void delObject(Class<?> clazz, Serializable id) {
		Session session = getSession();
		session.delete(getObject(clazz, id));
		closeSession(session);
	}

	public void delBaseModel(BaseModel baseModel) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		session.delete(baseModel);
		tx.commit();
		closeSession(session);
	}

	public Object getObject(Class<?> clazz, Serializable id) {
		Session session = getSession();
		Object o = session.get(clazz, id);
		closeSession(session);
		return o;
	}

	public List<?> findByHql(String queryString) {
		Session session = getSession().getSessionFactory().openSession();
		Query query = session.createQuery(queryString);
		List<?> list = query.list();
		closeSession(session);
		return  list;
	}

	public static int sumIntArr(int[] intArr) {
		int result = 0;
		for (int i = 0; i < intArr.length; i++) {
			result += intArr[i];
		}
		return result;
	}

	public List<?> saveOrUpdateAll(Collection<?> entities) {
		Session session = getSession();
		
		for(Object entity : entities) {
			Transaction tx = session.beginTransaction();
            session.save(entity);
            tx.commit();
        }
		
		closeSession(session);
//		getHibernateTemplate().saveOrUpdateAll(entities);
		List<Object> list = new ArrayList<Object>();
		list.addAll(entities);
		return null;
	}


	public List<Object> getObjects(Class<Object> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<?> findByNamedParam(String queryString, String paramName, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<?> findByNamedParam(String queryString, String[] paramNames, Object[] values) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<?> findAllByClass(Class<?> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<?> findBySQL(String queryString, Class<?> clazz) {
		Session session = getSession();
		Query query = session.createSQLQuery(queryString);
		List<?> list = query.list();
		closeSession(session);
		return list;
	}

	public List<?> findBySQL(String queryString) {
		Session session = getSession();
		Query query = session.createSQLQuery(queryString);
		List<?> list = query.list();
		closeSession(session);
		return null;
	}

	public Integer executeSqlBatch(List<String> sqlList) {
		// TODO Auto-generated method stub
		return null;
	}

	public int executeUpdateSQL(String sql) {
		// TODO Auto-generated method stub
		return 0;
	}
	

	//对传入的所有实体类进行操作
	public void delObjectAll(Collection<?> entities) {
		Session session = getSession().getSessionFactory().openSession();
		for(Object entity : entities) {
            session.delete(entity);
        }
		closeSession(session);
	}


	public void delByHql(String hql) {
		Session session = getSession().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.createQuery(hql).executeUpdate();
		tx.commit();
		closeSession(session);
	}

	public void delObectAll(String hql) {
		Session session = getSession().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.createQuery(hql).executeUpdate();
		tx.commit();
		session.close();
		
	}
	
	

}
