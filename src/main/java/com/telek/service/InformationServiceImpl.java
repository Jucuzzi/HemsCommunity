package com.telek.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telek.daoImpl.InformationDaoImpl;
import com.telek.model.InformationEntity;


@Service("InformationServiceImpl")
public class InformationServiceImpl implements IInformationService{
	
	@Autowired
	private InformationDaoImpl infarmationDaoImpl;
	
//	@Transactional
	public boolean addEntity(InformationEntity entity) {
		
		return infarmationDaoImpl.save(entity);
	}
	
	

	public InformationEntity get(int id) {
		
		return infarmationDaoImpl.get(id);
	}



	@SuppressWarnings("unchecked")
	public List<InformationEntity> queryInformation(int pageNumber,
			String condition) {
		// TODO Auto-generated method stub
		return (List<InformationEntity>)infarmationDaoImpl.queryInformation(pageNumber, condition);
	}

	@SuppressWarnings("unchecked")
	public int queryInformationTotalNum(int pageNumber,
			String condition) {
		// TODO Auto-generated method stub
		return ((List<InformationEntity>)infarmationDaoImpl.queryInformationTotalNum(pageNumber, condition)).size();
	}

	public boolean updateInformationDelete(int id,String isDelete) {
		// TODO Auto-generated method stub
		return infarmationDaoImpl.updateInformationDelete(id,isDelete);
	}
	
}
