package com.telek.dao;

import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.telek.model.UserEntity;
@Repository(value="UserDaoImpl")  
public class UserDaoImpl extends BaseDaoImpl<UserEntity, String> {
	@Override
	public UserEntity get(String id) {
		getSession();
		
		return super.get(id);
	}
	
	
	@Override
	public String save(UserEntity entity) {
		setSessionFactory(sessionFactory);
//		Transaction transaction= getSession().beginTransaction();
//		 try {
//			getSession().save(entity);
//			transaction.commit();
//		} catch (Exception e) {
//			transaction.rollback();
//			e.printStackTrace();
//		}
//		
//		 getSession().flush();
//		 getSession().close();
		
		getSession().save(entity);
		return "";
	}
}
