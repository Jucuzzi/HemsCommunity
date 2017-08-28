package com.telek.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.telek.daoImpl.PreferenceDaoImpl;
import com.telek.daoImpl.ReportDaoImpl;
import com.telek.model.CommentEntity;
import com.telek.model.PreferenceEntity;
import com.telek.model.ReportEntity;
import com.telek.model.UserEntity;
import com.telek.service.ICommentService;
import com.telek.service.IUserService;
import com.telek.service.PreferenceService;
import com.telek.util.ConstantVar;

@Controller
public class CommentController {

	@Autowired
	private IUserService userServiceImpl;
	@Autowired
	private ICommentService commentServiceImpl;
	@Autowired
	private PreferenceService preferenceServiceImpl;
	@Autowired
	private ReportDaoImpl reportDaoImpl;
	
	@RequestMapping("/getComments")
	public void getComments(HttpServletRequest request, HttpServletResponse response) {
		HttpServletResponse servletResponse = (HttpServletResponse) response;

//		servletResponse.setHeader("Access-Control-Allow-Origin", "*");
		int informationId = Integer.parseInt(request.getParameter("informationId"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		int selfUserId = Integer.parseInt(request.getParameter("userId"));
		List<CommentEntity> list= commentServiceImpl.getByInfomationId(informationId, pageNum);
		JSONArray jsonArray = new JSONArray();
		
		System.out.println("list.size():"+list);
		
		for(int i=0; i<list.size(); i++) {
			CommentEntity commentEntity = list.get(i);
			
			int preferenceCount = preferenceServiceImpl.getCountByCommentId(commentEntity.getCommentId());
//			System.out.println("preferenceCount:"+preferenceCount);
			int isThumbed = preferenceServiceImpl.getCountByCommentIdAndUserId(commentEntity.getCommentId(), selfUserId);
//			System.out.println("isThumbed:"+isThumbed);
			UserEntity user = userServiceImpl.get(commentEntity.getUserId());
			System.out.println("userId:"+user.getId());
			System.out.println("username:"+user.getNickName());
//			System.out.println("user:"+user.toString());
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("commentId", ""+commentEntity.getCommentId());
			jsonObject.put("commentTime", ""+commentEntity.getCommentTime());
			jsonObject.put("commentDetail", ""+commentEntity.getCommentDetail());
			jsonObject.put("informationId", ""+commentEntity.getInformationId());
			jsonObject.put("userId", ""+commentEntity.getUserId());
			
			jsonObject.put("preferenceCount", ""+preferenceCount);
			jsonObject.put("isThumbed", ""+isThumbed);
			jsonObject.put("name", ""+user.getNickName());
			jsonObject.put("imgPath", ""+user.getUserIconPath());
			jsonArray.add(jsonObject);
		}
		
		JSONObject jsonObjectAll = new JSONObject();
		jsonObjectAll.put("result", jsonArray);
		
		System.out.println(jsonObjectAll);
		try {
			response.getWriter().write(jsonObjectAll.toString());
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 点赞
	 * @param request
	 * @param response
	 */
	@RequestMapping("/thumbsUp")
	public void thumbsUp(HttpServletRequest request, HttpServletResponse response) {
		
		int commentId = Integer.parseInt(request.getParameter("commentId"));
		int selfUserId = Integer.parseInt(request.getParameter("userId"));
		
		int isThumbed = preferenceServiceImpl.getCountByCommentIdAndUserId(commentId, selfUserId);
		
		if(isThumbed == 0) {
			PreferenceEntity preferenceEntity = new PreferenceEntity(commentId, selfUserId);
			preferenceServiceImpl.save(preferenceEntity);
		}
		
		try {
			JSONObject jsonObjectAll = new JSONObject();
			jsonObjectAll.put("result", "successed");
			response.getWriter().write(jsonObjectAll.toString());
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 取消点赞
	 * @param request
	 * @param response
	 */
	@RequestMapping("/cancelThumbsUp")
	public void cancelThumbsUp(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("commentId:"+request.getParameter("commentId"));
		
		int commentId = Integer.parseInt(request.getParameter("commentId"));
		int selfUserId = Integer.parseInt(request.getParameter("userId"));
		
		preferenceServiceImpl.deleteByCommentIdAndUserId(commentId, selfUserId);
		
		try {
			JSONObject jsonObjectAll = new JSONObject();
			jsonObjectAll.put("result", "successed");
			response.getWriter().write(jsonObjectAll.toString());
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 举报
	 * @param request
	 * @param response
	 */
	@RequestMapping("/reported")
	public void reported(HttpServletRequest request, HttpServletResponse response) {
		
		int contentId = Integer.parseInt(request.getParameter("commentId"));
		String reportType = request.getParameter("reportType");
		int userId = Integer.parseInt(request.getParameter("userId"));
		String contenttype = request.getParameter("contenttype");
		
		ReportEntity reportEntity = new ReportEntity(contenttype, reportType, userId, contentId);
		reportDaoImpl.save(reportEntity);
		
		try {
			JSONObject jsonObjectAll = new JSONObject();
			jsonObjectAll.put("result", "successed");
			response.getWriter().write(jsonObjectAll.toString());
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 删除评论
	 * @param commentId
	 */
	@RequestMapping("/deleteComment")
	public void deleteComment(HttpServletRequest request, HttpServletResponse response) {
		
		int commentId = Integer.parseInt(request.getParameter("commentId"));
		
		int temp = commentServiceImpl.sofetDelete(commentId);
		
		try {
			JSONObject jsonObjectAll = new JSONObject();
			if (temp == 1) {
				jsonObjectAll.put("result", "successed");
			} else {
				jsonObjectAll.put("result", "failed");
			}
			response.getWriter().write(jsonObjectAll.toString());
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 评论
	 * @param request
	 * @param response
	 */
	@RequestMapping("/addComment")
	public void addComment(HttpServletRequest request, HttpServletResponse response) {
		
		String commentDetail = request.getParameter("commentDetail");
		int informationId = Integer.parseInt(request.getParameter("informationId"));
		int userId = Integer.parseInt(request.getParameter("userId"));
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String time = df.format(new Date());// new Date()为获取当前系统时间
		
		CommentEntity commentEntityTemp = new CommentEntity(time, commentDetail, userId, informationId);
		
		commentServiceImpl.save(commentEntityTemp);
		
		int pageNum = 1;
		int selfUserId = Integer.parseInt(request.getParameter("userId"));
		List<CommentEntity> list= commentServiceImpl.getByInfomationId(informationId, pageNum);
		JSONArray jsonArray = new JSONArray();
		
//		System.out.println("list.size():"+list.size());
		
		for(int i=0; i<list.size(); i++) {
			CommentEntity commentEntity = list.get(i);
			
			int preferenceCount = preferenceServiceImpl.getCountByCommentId(commentEntity.getCommentId());
//			System.out.println("preferenceCount:"+preferenceCount);
			int isThumbed = preferenceServiceImpl.getCountByCommentIdAndUserId(commentEntity.getCommentId(), selfUserId);
//			System.out.println("isThumbed:"+isThumbed);
			UserEntity user = userServiceImpl.get(commentEntity.getUserId());
//			System.out.println("userId:"+user.getUserId());
//			System.out.println("username:"+user.getNickName());
//			System.out.println("user:"+user.toString());
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("commentId", ""+commentEntity.getCommentId());
			jsonObject.put("commentTime", ""+commentEntity.getCommentTime());
			jsonObject.put("commentDetail", ""+commentEntity.getCommentDetail());
			jsonObject.put("informationId", ""+commentEntity.getInformationId());
			jsonObject.put("userId", ""+commentEntity.getUserId());
			
			jsonObject.put("preferenceCount", ""+preferenceCount);
			jsonObject.put("isThumbed", ""+isThumbed);
			jsonObject.put("name", ""+user.getNickName());
			jsonObject.put("imgPath", ""+user.getUserIconPath());
			
			jsonArray.add(jsonObject);
		}
		
		JSONObject jsonObjectAll = new JSONObject();
		jsonObjectAll.put("result", jsonArray);

		try {
			response.getWriter().write(jsonObjectAll.toString());
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
