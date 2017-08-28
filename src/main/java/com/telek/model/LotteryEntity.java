package com.telek.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;


@Entity
@Table(name="t_lottery")
public class LotteryEntity extends BaseModel{
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)  
    @Column(name = "id", nullable = false) 
	public int id;
	@Column(name = "type")
	public int type;
	@Column(name = "name")
	public String name;
	@Column(name = "imgPath")
	public String imgPath;
	@Column(name = "updateTime")
	public String updateTime;
	
	public LotteryEntity() {
		super();
	}
	@Override
	public String toString() {
		return "LotteryEntity [id=" + id + ", type=" + type + ", name=" + name
				+ ", imgPath=" + imgPath + ", updateTime=" + updateTime + "]";
	}
	public LotteryEntity(int type, String name, String imgPath, String updateTime) {
		super();
		this.type = type;
		this.name = name;
		this.imgPath = imgPath;
		this.updateTime = updateTime;
	}
	
	public JSONObject getJsonObj() {
		JSONObject obj = new JSONObject();
		obj.put("id", id);
		obj.put("name", name);
		obj.put("type", type);
		obj.put("imgPath", imgPath);
		obj.put("updateTime", updateTime);
		return obj;
	}
	
	
}
