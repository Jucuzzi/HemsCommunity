package com.telek.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.telek.model.CommentEntity;
import com.telek.model.InformationEntity;
import com.telek.model.UserEntity;
import com.telek.service.ICommentService;
import com.telek.service.IInformationService;
import com.telek.service.IUserService;
import com.telek.util.ConstantVar;
@Controller
public class InformationController{

	@Autowired
	private IInformationService iInformationService;
	@RequestMapping ( "/addinformation" )
	public void addInformation (HttpServletRequest request,HttpServletResponse response,InformationEntity info) {
		// 返回数据
		try {
			if(iInformationService.addEntity(info)){
				response.getWriter().write(ConstantVar.SUCCESSED_RESULT);
			} else {
				response.getWriter().write(ConstantVar.FAILED_RESULT);
			}
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping ( "/queryinformation" )
	public void queryInformation (HttpServletRequest request,HttpServletResponse response) {
		// 返回数据
		try {
			int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
			String condition = request.getParameter("condition");
			List<InformationEntity> list = iInformationService.queryInformation(pageNumber, condition);
			JSONObject backJson = new JSONObject();
			backJson.put("total", ""+iInformationService.queryInformationTotalNum(pageNumber, condition));
			backJson.put("infoList", list);
			response.getWriter().write(backJson.toString());
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping ( "/queryinformationById" )
	public void queryInformationById (HttpServletRequest request,HttpServletResponse response) {
		// 返回数据
		try {
			int informationId = Integer.parseInt(request.getParameter("informationId"));
			InformationEntity informationEntity= iInformationService.get(informationId);
			response.getWriter().write(informationEntity.getEntityJSONObject().toString());
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping ( "/updateInformationIsDelete" )
	public void updateInformationIsDelete (HttpServletRequest request,HttpServletResponse response) {
		// 返回数据
		try {
			int informationId = Integer.parseInt(request.getParameter("informationId"));
			String  isDelete = request.getParameter("isDelete");
			if (iInformationService.updateInformationDelete(informationId, isDelete)){
				JSONObject backJson = new JSONObject();
				backJson.put("informationId", ""+informationId);
				backJson.put("result", "successed");
				response.getWriter().write(backJson.toString());
			} else {
				response.getWriter().write(ConstantVar.FAILED_RESULT);
			}
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
