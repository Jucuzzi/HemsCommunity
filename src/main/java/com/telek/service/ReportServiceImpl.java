package com.telek.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telek.daoImpl.ReportDaoImpl;
import com.telek.model.ReportEntity;
import com.telek.model.UserEntity;

@Service("ReportServiceImpl")
public class ReportServiceImpl {
	@Autowired
	private ReportDaoImpl reportDaoImpl;
	
//	@Transactional
	public boolean save(ReportEntity entity) {
		
		return reportDaoImpl.save(entity);
	}
	
	

	public ReportEntity get(int id) {
		String str = "from UserEntity where id = "+id;
		List<ReportEntity> list = (List<ReportEntity>) reportDaoImpl.findByHql(str);
		if (list.size() == 0) {
			return null;
		}
		return list.get(0);
	}
}
