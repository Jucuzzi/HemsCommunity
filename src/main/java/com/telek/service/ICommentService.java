package com.telek.service;



import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.telek.model.CommentEntity;
import com.telek.model.ExchangeEntity;
import com.telek.model.PrizeInfoEntity;

public interface ICommentService {
	@Transactional
	public boolean save(CommentEntity entity);
	public CommentEntity get(int id);
	
	public String queryCommentsListByName(String userName);
	public String delCommentListByIds(String commentIds);
	public List<CommentEntity> getByInfomationId(int id, int pageNumber);
	public int sofetDelete(int CommentId);
	public String queryPrizeListAll();
	public String UpdatePrize(PrizeInfoEntity prizeInfo);
	public String queryExchangeListAll();
	public String UpdateExchange(ExchangeEntity exchangeInfo);
	public String getMenuInfoById(int prizeId);
}

