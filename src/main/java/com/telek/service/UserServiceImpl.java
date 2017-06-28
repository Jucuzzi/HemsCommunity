package com.telek.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telek.dao.IBaseDao;
import com.telek.dao.UserDaoImpl;
import com.telek.model.UserEntity;

@Service("UserServiceImpl")
public class UserServiceImpl implements IUserService{
	
	@Autowired
	private UserDaoImpl userDaoImpl;
	
//	@Transactional
	public String save(UserEntity entity) {
		
		return userDaoImpl.save(entity);
	}
	
	

	public UserEntity get(String id) {
		
		return userDaoImpl.get(id);
	}
	
}
