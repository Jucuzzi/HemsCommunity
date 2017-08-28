package com.telek.daoImpl;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.telek.model.InformationEntity;
@Repository(value="InfarmationDaoImpl")  
public class InformationDaoImpl extends DAOImpl {
	
	public InformationEntity get(Integer id) {
		Session session= getSession();
		InformationEntity informationEntity = (InformationEntity) session.get(InformationEntity.class, id);
		session.close();
		return informationEntity;
	}
	
	public InformationEntity addEntity(InformationEntity informationEntity) {
		Session session= getSession();
		session.save(informationEntity);
		session.close();
		return informationEntity;
	}
	
	public List<?> queryInformation(int pageNumber,
			String condition) {
		Session session= getSession();
		String sql = "from InformationEntity where title like :title or author like :author";
		Query query = session.createQuery(sql);
		query.setFirstResult((pageNumber-1)*10);//设置起始行
		query.setMaxResults(10);//每页条数
		List<?> result = query
       .setString("title", "%"+condition+"%")
       .setString("author", "%"+condition+"%")
        .list();
		session.close();
		return result;
	}

	public List<?> queryInformationTotalNum(int pageNumber,
			String condition) {
		Session session= getSession();
		String sql = "from InformationEntity where title like :title or author like :author";
		List<?> result = session.createQuery(sql)
       .setString("title", "%"+condition+"%")
       .setString("author", "%"+condition+"%")
        .list();
		session.close();
		return result;
	}

	public boolean updateInformationDelete(int id,String isDelete) {
		Session session= getSession();
		String sql = "update InformationEntity set isDelete = :isDelete where informationId = :id";
		Query query = session.createQuery(sql)
			 	   .setString("isDelete", isDelete)
				   .setInteger("id", id);
		query.executeUpdate();
		session.close();
		return true;
	}
}
