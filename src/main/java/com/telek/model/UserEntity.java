package com.telek.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import net.sf.json.JSONObject;



@Entity
@Table(name="t_user")
public class UserEntity extends BaseModel{
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)  
    @Column(name = "id", nullable = false) 
	public int id;
	@Column(name = "name")
	public String name;
	@Column(name = "nickName")
	public String nickName;
	@Column(name = "userIconPath")
	public String userIconPath;
	@Column(name = "userType")
	public String userType;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getUserIconPath() {
		return userIconPath;
	}
	public void setUserIconPath(String userIconPath) {
		this.userIconPath = userIconPath;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public UserEntity() {
		super();
	}
	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", name=" + name + ", nickName=" + nickName
				+ ", userIconPath=" + userIconPath + ", userType=" + userType + "]";
	}
	public UserEntity(String name, String nickName, String userIconPath, String userType) {
		super();
		this.name = name;
		this.nickName = nickName;
		this.userIconPath = userIconPath;
		this.userType = userType;
	}
	
	public JSONObject getJsonObj() {
		JSONObject obj = new JSONObject();
		obj.put("id", id);
		obj.put("name", name);
		obj.put("nickName", nickName);
		obj.put("userIconPath", userIconPath);
		obj.put("userType", userType);
		return obj;
	}
	
	
}
