package com.telek.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import net.sf.json.JSONObject;

@Entity
@Table(name="t_information")
public class InformationEntity  {
	//资讯Id
	@Id  
    @GeneratedValue(strategy=GenerationType.IDENTITY)  
    @Column(name = "informationId", nullable = false) 
	public int informationId;//资讯标题	
	
	@Column(name = "title")
	public String title;
	
	@Column(name = "type")
	public String type;//资讯类型（官方、精品、教程、娱乐、收藏）
	
	@Column(name = "releaseTime")
	public String releaseTime;//发布时间
	
	@Column(name = "imgPath",columnDefinition="TEXT")
	public String imgPath;//第一张图片封面
	
	@Column(name = "briefIntroduction")
	public String briefIntroduction;//资讯简介
	
	@Column(name = "detailContent",columnDefinition="TEXT")
	public String detailContent;//资讯内容
	
	@Column(name = "userId")
	public String userId;//发布用户Id

	@Column(name = "source")
	public String source;//来源
	
	@Column(name = "isDelete")
	public String isDelete = "0";//是否被删除
	
	@Column(name = "author")
	public String author = "";//作者
	
//	public int totalCollectNum;//被收藏总数
	
	public InformationEntity() {
		super();
	}
	/**
	 * 新增资讯构造方法
	 * @param title
	 * @param type
	 * @param releaseTime
	 * @param imgPath
	 * @param briefIntroduction
	 * @param detailContent
	 * @param userId
	 * @param source
	 * @param author
	 */
	public InformationEntity(String title, String type, String releaseTime,
			String imgPath, String briefIntroduction, String detailContent,
			  String userId,String source, String isDelete, String author) {
		super();
		this.title = title;
		this.type = type;
		this.releaseTime = releaseTime;
		this.imgPath = imgPath;
		this.briefIntroduction = briefIntroduction;
		this.detailContent = detailContent;
		this.userId = userId;
		this.source = source;
		this.isDelete = isDelete;
		this.author = author;
	}
	/**
	 * 查询资讯构造方法
	 * @param informationId
	 * @param title
	 * @param type
	 * @param releaseTime
	 * @param imgPath
	 * @param briefIntroduction
	 * @param detailContent
	 * @param userId
	 * @param source
	 * @param author
	 */
	public InformationEntity(int informationId, String title, String type, String releaseTime,
								String imgPath, String briefIntroduction, String detailContent,
								  String userId,String source, String isDelete, String author) {
		super();
		this.informationId = informationId;
		this.title = title;
		this.type = type;
		this.releaseTime = releaseTime;
		this.imgPath = imgPath;
		this.briefIntroduction = briefIntroduction;
		this.detailContent = detailContent;
		this.userId = userId;
		this.source = source;
		this.isDelete = isDelete;
		this.author = author;
	}
	@Override
	public String toString(){
		return "informationId:"+informationId+","+
				"title:"+title+","+
				"type:"+type+","+
				"releaseTime:"+releaseTime+","+
				"imgPath:"+imgPath+","+
				"briefIntroduction:"+briefIntroduction+","+
				"detailContent:"+detailContent+","+
				"userId:"+userId+","+
				"source:"+source+","+
				"isDelete:"+isDelete+","+
				"author:"+author;
	}
	public JSONObject getEntityJSONObject(){
		JSONObject jsb = new JSONObject();
		jsb.put("informationId",informationId);
		jsb.put("title",title);
		jsb.put("type",type);
		jsb.put("releaseTime",releaseTime);
		jsb.put("imgPath",imgPath);
		jsb.put("briefIntroduction",briefIntroduction);
		jsb.put("detailContent",detailContent);
		jsb.put("userId",userId);
		jsb.put("source",source);
		jsb.put("isDelete",isDelete);
		jsb.put("author",author);
		return jsb;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getInformationId() {
		return informationId;
	}
	public void setInformationId(int informationId) {
		this.informationId = informationId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public String getBriefIntroduction() {
		return briefIntroduction;
	}
	public void setBriefIntroduction(String briefIntroduction) {
		this.briefIntroduction = briefIntroduction;
	}
	public String getDetailContent() {
		return detailContent;
	}
	public void setDetailContent(String detailContent) {
		this.detailContent = detailContent;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
//	public int getTotalCollectNum() {
//		return totalCollectNum;
//	}
//	public void setTotalCollectNum(int totalCollectNum) {
//		this.totalCollectNum = totalCollectNum;
//	}
}
