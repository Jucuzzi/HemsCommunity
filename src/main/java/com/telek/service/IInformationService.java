package com.telek.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.telek.model.InformationEntity;



public interface IInformationService {
	@Transactional
	public boolean addEntity(InformationEntity entity);
	public  InformationEntity get(int id);
	public  List<InformationEntity> queryInformation(int pageNumber,String condition);
	public  boolean updateInformationDelete(int id,String delete);
	public int queryInformationTotalNum(int pageNumber,String condition);

}
