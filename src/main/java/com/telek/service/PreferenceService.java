package com.telek.service;

import org.springframework.transaction.annotation.Transactional;

import com.telek.model.PreferenceEntity;
import com.telek.model.UserEntity;

public interface PreferenceService {
	@Transactional
	public boolean save(PreferenceEntity preferenceEntity);
	public int getCountByCommentId(int commentId);
	public int getCountByCommentIdAndUserId(int commentId, int userId);
	public void deleteByCommentIdAndUserId(int commentId, int userId);
}
