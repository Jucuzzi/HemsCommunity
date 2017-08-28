package com.telek.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.telek.model.UserEntity;
@Repository(value="UserDaoImpl")  
public class UserDaoImpl extends DAOImpl {
	public UserEntity get(int userId) {
		System.out.println("userId.userId.userId:::::"+userId);
		Session session= getSession();
		String sql = "from UserEntity where id = ?";
		Query query = session.createQuery(sql);
		List<?> result = query
			.setInteger(0, userId)
			.list();
		session.close();
		System.out.println("result.lalalalal:::::"+result.toString());
		return (UserEntity)result.get(0);
	}
}
