package com.telek.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_report")
public class ReportEntity {
	@Id  
    @GeneratedValue(strategy=GenerationType.IDENTITY)  
    @Column(name = "reportId", nullable = false) 
	public int reportId;
	@Column(name = "contentType")
	public String contentType;
	@Column(name = "reportType")
	public String reportType;
	@Column(name = "userId")
	public int userId;
	@Column(name = "contentId")
	public int contentId;
	
	public int getReportId() {
		return reportId;
	}
	public void setReportId(int reportId) {
		this.reportId = reportId;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getContentId() {
		return contentId;
	}
	public void setContentId(int contentId) {
		this.contentId = contentId;
	}
	@Override
	public String toString() {
		return "ReportEntity [reportId=" + reportId + ", contentType=" + contentType + ", reportType=" + reportType
				+ ", userId=" + userId + ", contentId=" + contentId + "]";
	}
	public ReportEntity(String contentType, String reportType, int userId, int contentId) {
		super();
		this.contentType = contentType;
		this.reportType = reportType;
		this.userId = userId;
		this.contentId = contentId;
	}
	
	public ReportEntity() {
		super();
	}
	
}
