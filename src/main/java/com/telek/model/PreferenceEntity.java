package com.telek.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_preference")
public class PreferenceEntity {
	
	@Id  
    @GeneratedValue(strategy=GenerationType.IDENTITY)  
    @Column(name = "fabulousId", nullable = false) 
	public int fabulousId;
	@Column(name = "userId")
	public int userId;
	@Column(name = "commentId")
	public int commentId;
	
	@Override
	public String toString() {
		return "PreferenceEntity [fabulousId=" + fabulousId + ", userId=" + userId + ", commentId=" + commentId + "]";
	}
	
	public PreferenceEntity(int commentId, int userId) {
		super();
		this.commentId = commentId;
		this.userId = userId;
	}
	
	public PreferenceEntity() {
		super();
	}
	
}
