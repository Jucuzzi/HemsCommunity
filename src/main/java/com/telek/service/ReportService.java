package com.telek.service;

import org.springframework.transaction.annotation.Transactional;

import com.telek.model.ReportEntity;

public interface ReportService {
	@Transactional
	public int save(ReportEntity entity);
	public ReportEntity get(int id);
}
