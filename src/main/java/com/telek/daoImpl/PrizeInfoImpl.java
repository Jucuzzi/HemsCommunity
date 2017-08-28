package com.telek.daoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.telek.dao.IDAO;
import com.telek.model.PrizeInfoEntity;

public class PrizeInfoImpl {
	
	@Autowired
	private IDAO iDAO;
	
	@SuppressWarnings("unchecked")

	public List<PrizeInfoEntity> queryPrizeListAll() {
		return (List<PrizeInfoEntity>) iDAO.findByHql("from PrizeInfoEntity order by prizeId");
	}


}
