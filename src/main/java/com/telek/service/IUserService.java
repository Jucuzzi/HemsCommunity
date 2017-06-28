package com.telek.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telek.model.UserEntity;


public interface IUserService {
	@Transactional
	public String save(UserEntity entity);
	public UserEntity get(String id);
}
