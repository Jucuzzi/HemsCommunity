package com.telek.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import net.sf.json.JSONObject;

@Entity
@Table(name="t_comment")
public class CommentEntity {
	@Id  
    @GeneratedValue(strategy=GenerationType.IDENTITY)  
    @Column(name = "commentId", nullable = false) 
	public int commentId;
	@Column(name = "commentTime")
	public String commentTime;
	@Column(name = "commentDetail")
	public String commentDetail;
	@Column(name = "userId")
	public int userId;
	@Column(name = "informationId")
	public int informationId;
	@Column(name = "isDelete")
	public int isDelete;
	
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public String getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(String commentTime) {
		this.commentTime = commentTime;
	}
	public String getCommentDetail() {
		return commentDetail;
	}
	public void setCommentDetail(String commentDetail) {
		this.commentDetail = commentDetail;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getInformationId() {
		return informationId;
	}
	public void setInformationId(int informationId) {
		this.informationId = informationId;
	}
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	
	@Override
	public String toString() {
		return "CommentEntity [commentId=" + commentId + ", commentTime=" + commentTime + ", commentDetail=" + commentDetail
				+ ", userId=" + userId + ", informationId=" + informationId + "]";
	}
	public CommentEntity( String commentTime, String commentDetail, int userId, int informationId) {
		super();
		this.commentTime = commentTime;
		this.commentDetail = commentDetail;
		this.userId = userId;
		this.informationId = informationId;
	}
	public CommentEntity() {
		super();
	}
	
	public JSONObject getJsonObj() {
		JSONObject obj = new JSONObject();
		obj.put("commentId", this.commentId);
		obj.put("commentTime", this.commentTime);
		obj.put("commentDetail", this.commentDetail == null ? "" : this.commentDetail);
		obj.put("userId", this.userId);
		obj.put("informationId", this.informationId);
		return obj;
	}
	
}
