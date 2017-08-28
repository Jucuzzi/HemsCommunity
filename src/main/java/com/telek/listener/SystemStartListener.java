package com.telek.listener;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.telek.dao.IDAO;
import com.telek.util.PageModel;
import com.telek.cache.model.CacheModelManager;

/**
 * 启动加载类
 * 
 * @author zxf
 * 
 */
public class SystemStartListener extends HttpServlet {

	private static final long serialVersionUID = 6109669778075130544L;

	@Override
	public void init() throws ServletException {
		super.init();
		// 加载注入到spring中的bean
		ApplicationContext application = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());

		// 缓存初始化
		DIBean.iDAO = (IDAO) application.getBean("iDAO");
		// 常规缓存
		DIBean.cacheModelManager = (CacheModelManager) application.getBean("cacheModelManager");
		DIBean.cacheModelManager.init();
//		DIBean.appStateUpdQuartz = (ApplianceStateUpdQuartz) application.getBean("appStateUpdQuartz");
//		DIBean.appStateUpdQuartz
//				.addObserver((AppStateCacheUpdListener) application.getBean("appStateCacheUpdListener"));
//		DIBean.appStateUpdQuartz
//				.addObserver((RealtimeLoadUpdListener) application.getBean("realtimeLoadUpdListener"));
//		DIBean.appStateUpdQuartz
//				.addObserver((ExceptionDetectionListener) application.getBean("exceptionDetectionListener"));
//		DIBean.appStateUpdQuartz.updateAppState(1, 0, 0, 0);
//		SystemInfoCache systemInfoCache = new SystemInfoCache();
//		systemInfoCache.loadSystemInfoCache();
//		// 非常规缓存
//		DIBean.unNormalCache = (UnNormalCache) application.getBean("unNormalCache");
//		DIBean.unNormalCache.initData();
		PageModel.iDAO = DIBean.iDAO;
	}

}
