package com.telek.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.telek.dao.UserDaoImpl;
import com.telek.model.UserEntity;
import com.telek.service.IUserService;
import com.telek.service.UserServiceImpl;

@Controller
public class UserController{
	@Autowired
	private IUserService userServiceImpl;
	
	@RequestMapping ( "/showView" )
	public ModelAndView saveUser () {
		ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName( "index" );
	    String[] arg = new String[]{"spring-hibernate.xml","spring-mvc.xml","spring.xml"};
//	    ApplicationContext ctx = new ClassPathXmlApplicationContext(arg);// 读取bean.xml中的内容  
//	    
//	    UserDaoImpl userDaoImpl = ctx.getBean("UserDaoImpl", UserDaoImpl.class);// 创建bean的引用对象
//	    UserDaoImpl userDaoImpl = new UserDaoImpl();
	    UserEntity user = new UserEntity("wlf", "dtc", "19940414");
//	    user.setId("1");
//	    user.setCreateDate(new Date());
//	    user.setModifyDate(new Date());
	    
	    userServiceImpl.save(user);
//	    userDaoImpl.save(user);
	    modelAndView.addObject( " 需要放到 model 中的属性名称 " , " 对应的属性值，它是一个对象 " );
	    return modelAndView;
	}
}
