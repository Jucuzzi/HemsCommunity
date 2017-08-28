package com.telek.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.telek.model.ButtonInfo;
import com.telek.model.ExchangeEntity;
import com.telek.model.MenuInfo;
import com.telek.model.PrizeInfoEntity;
import com.telek.model.RoleInfo;
import com.telek.model.UserEntity;
import com.telek.service.PrvlgService;
import com.telek.util.Util;
import com.telek.service.ICommentService;

/**
 * 公共模块-系统管理服务类
 * 
 * @author Xugl
 * 
 * @date 2015-12-15 下午1:49:14
 */
@Controller
@RequestMapping("/prvlg")
public class PrvlgController {
	private static final Logger LOG = Logger.getLogger(PrvlgController.class);

	@Autowired
	private ICommentService commentService;
	@Autowired
	private PrvlgService prvlgService;


	/**
	 * 系统用户登录
	 * 
	 * @param request
	 * @param response
	 * @author Xugl @2017-3-16 下午5:55:19
	 */
	@RequestMapping(value = "/userlogin.do", method = RequestMethod.POST)
	public void userLogin(HttpServletRequest request,
			HttpServletResponse response) {
		String userAccount = Util.transferUserInput(request
				.getParameter("userAccount"));
		String password = Util.transferUserInput(request
				.getParameter("password"));
		// 返回数据
		try {
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(
					prvlgService.userLogin(userAccount, password, request));
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 系统用户登出
	 * 
	 * @param request
	 * @param response
	 * @author Xugl @2017-3-16 下午5:55:19
	 */
	@RequestMapping(value = "/usersignout.do", method = RequestMethod.POST)
	public void userSignout(HttpServletRequest request,
			HttpServletResponse response) {
		// 返回数据
		try {
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(prvlgService.userSignout(request));
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 系统用户登出
	 * 
	 * @param request
	 * @param response
	 * @author Xugl @2017-3-16 下午5:55:19
	 */
//	@RequestMapping(value = "/updateuserpwd.do", method = RequestMethod.POST)
//	public void updateUserPwd(HttpServletRequest request,
//			HttpServletResponse response) {
//		// 返回数据
//		try {
//			response.setCharacterEncoding("utf-8");
//			response.getWriter().write(
//					prvlgService.updateUserPwd(Util.getCurrentUser(request)
//							.getId()+"", request.getParameter("oldPwd"),
//							request.getParameter("newPwd")));
//			response.getWriter().flush();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * 查询系统用户列表
	 * 
	 * @param request
	 * @param response
	 * @author Xugl @2017-3-16 下午5:55:19
	 */
	@RequestMapping(value = "/userlist.do", method = RequestMethod.POST)
	public void queryUserListByName(HttpServletRequest request,
			HttpServletResponse response) {
		String userName = Util.transferUserInput(request
				.getParameter("condition"));
		// 返回数据
		try {
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(
					prvlgService.queryUserListByName(userName));
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除系统用户
	 * 
	 * @param request
	 * @return
	 * @author Xugl @2017-3-16 下午2:37:51
	 */
	@RequestMapping(value = "/delusers.do", method = RequestMethod.POST)
	public void delUserListByIds(HttpServletRequest request,
			HttpServletResponse response) {
		String userIds = request.getParameter("userIds");
		 System.out.println(userIds);
		 System.out.println("`````````````````");
		// 返回数据
		try {
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(
//					prvlgService.delUserListByIds(Util.getCurrentUser(request)
//							.getId()+"", userIds));
					prvlgService.delUserListByIds(userIds));
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取系统用户信息
	 * 
	 * @param request
	 * @return
	 * @author Xugl @2017-3-16 下午2:37:51
	 */
	@RequestMapping(value = "/getuserinfobyid.do", method = RequestMethod.POST)
	public void getUserInfoById(HttpServletRequest request,
			HttpServletResponse response) {
		String userId = request.getParameter("userId");
		// 返回数据
		try {
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(prvlgService.getUserInfoById(userId));
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取系统角色列表信息
	 * 
	 * @param request
	 * @return
	 * @author Xugl @2017-3-16 下午2:37:51
	 */
	@RequestMapping(value = "/queryrolelistall.do", method = RequestMethod.POST)
	public void queryRoleListAll(HttpServletRequest request,
			HttpServletResponse response) {
		// 返回数据
		try {
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(prvlgService.queryRoleListAll());
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取系统角色列表信息
	 * 
	 * @param request
	 * @return
	 * @author Xugl @2017-3-16 下午2:37:51
	 */
	@RequestMapping(value = "/checkusernamerepate.do", method = RequestMethod.POST)
	public void checkUserNameRepate(HttpServletRequest request,
			HttpServletResponse response) {
		// 返回数据
		try {
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(
					prvlgService.checkUserNameRepate(
							request.getParameter("userId"),
							request.getParameter("userAccount")));
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 新增编辑系统用户
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author Xugl @2017-3-16 下午2:37:51
	 */
	@RequestMapping(value = "/saveOrUpdateUser.do", method = RequestMethod.POST)
	public void saveOrUpdateUser(HttpServletRequest request,
			HttpServletResponse response) {
		 System.out.println("hello");
//		UserEntity user = (UserEntity) JSONObject.toBean(
//				JSONObject.fromObject(request.getParameter("user")),
//				UserEntity.class);
		 UserEntity user = new UserEntity();
		 user.name = request.getParameter("name");
		 user.nickName = request.getParameter("nickName");
		 user.userType = request.getParameter("userType");
		 user.userIconPath = request.getParameter("userIconPath");
		 System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		 if (request.getParameter("id") != null  && !request.getParameter("id").equals("") ) {
			 user.id = Integer.parseInt(request.getParameter("id"));
			 
		 }
		 
		 System.out.println(user);
		 System.out.println("------------------");
		String roleIdList = request.getParameter("roleIdList");
		 System.out.println(roleIdList);
		 System.out.println("------------------");
		// 返回数据
		try {
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(
					prvlgService.saveOrUpdateUser(request, user, roleIdList));
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 批量删除系统角色
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author Xugl @2017-3-16 下午2:37:51
	 */
	@RequestMapping(value = "/delrolelist.do", method = RequestMethod.POST)
	public void delRoleListByIds(HttpServletRequest request,
			HttpServletResponse response) {
		String roleIds = request.getParameter("roleIds");
		// 返回数据
		System.out.println(roleIds);
		try {
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(prvlgService.delRoleListByIds(roleIds));
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取系统角色信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author Xugl @2017-3-16 下午2:37:51
	 */
	@RequestMapping(value = "/getroleinfo.do", method = RequestMethod.POST)
	public void getRoleInfoById(HttpServletRequest request,
			HttpServletResponse response) {
		String roleId = request.getParameter("roleId");
		// 返回数据
		try {
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(prvlgService.getRoleInfoById(roleId));
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取系统角色信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author Xugl @2017-3-16 下午2:37:51
	 */
	@RequestMapping(value = "/checkrolenamerepate.do", method = RequestMethod.POST)
	public void checkRoleNameRepate(HttpServletRequest request,
			HttpServletResponse response) {
		String roleId = request.getParameter("roleId");
		String roleName = request.getParameter("roleName");
		// 返回数据
		try {
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(
					prvlgService.checkRoleNameRepate(roleId, roleName));
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 新增或修改系统角色信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author Xugl @2017-3-16 下午2:37:51
	 */
	@RequestMapping(value = "/saveorupdaterole.do", method = RequestMethod.POST)
	public void saveOrUpdateRole(HttpServletRequest request,
			HttpServletResponse response) {
		RoleInfo role = (RoleInfo) JSONObject.toBean(
				JSONObject.fromObject(request.getParameter("role")),
				RoleInfo.class);
		// 返回数据
		try {
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(prvlgService.saveOrUpdateRole(role));
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取系统菜单列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author Xugl @2017-3-16 下午2:37:51
	 */
	@RequestMapping(value = "/queryconfiguremenu.do", method = RequestMethod.POST)
	public void configurePermissions(HttpServletRequest request,
			HttpServletResponse response) {
		// 返回数据
		try {
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(prvlgService.queryConfigureMenu());
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取系统按钮列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author Xugl @2017-3-16 下午2:37:51
	 */
	@RequestMapping(value = "/queryconfigurebtn.do", method = RequestMethod.POST)
	public void queryConfigureBtn(HttpServletRequest request,
			HttpServletResponse response) {
		// 返回数据
		try {
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(prvlgService.queryConfigureBtn());
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除菜单/按钮
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author Xugl @2017-3-16 下午2:37:51
	 */
	@RequestMapping(value = "/delmenuorbtn.do", method = RequestMethod.POST)
	public void delMenuOrBtnByIdAndFlag(HttpServletRequest request,
			HttpServletResponse response) {
		String id = request.getParameter("id");
		String flag = request.getParameter("flag");
		// 返回数据
		try {
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(
					prvlgService.delMenuOrBtnByIdAndFlag(id, flag));
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取是否有子菜单
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author Xugl @2017-3-16 下午2:37:51
	 */
	@RequestMapping(value = "/checksubmenuexist.do", method = RequestMethod.POST)
	public void checkSubMenuExistByParentId(HttpServletRequest request,
			HttpServletResponse response) {
		String parentId = request.getParameter("id");
		// 返回数据
		try {
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(
					prvlgService.checkSubMenuExistByParentId(parentId));
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 新增或修改菜单
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author Xugl @2017-3-16 下午2:37:51
	 */
	@RequestMapping(value = "/saveorupdatemenu.do", method = RequestMethod.POST)
	public void saveOrUpdataMenu(HttpServletRequest request,
			HttpServletResponse response) {
		MenuInfo menu = (MenuInfo) JSONObject.toBean(
				JSONObject.fromObject(request.getParameter("menu")),
				MenuInfo.class);
		// 返回数据
		try {
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(prvlgService.saveOrUpdataMenu(menu));
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取系统父菜单列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author Xugl @2017-3-16 下午2:37:51
	 */
	@RequestMapping(value = "/queryparentmenulist.do", method = RequestMethod.POST)
	public void queryParentMenuList(HttpServletRequest request,
			HttpServletResponse response) {
		// 返回数据
		try {
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(prvlgService.queryParentMenuList());
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据菜单ID获取菜单信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author Xugl @2017-3-16 下午2:37:51
	 */
	@RequestMapping(value = "/getmenuinfo.do", method = RequestMethod.POST)
	public void getMenuInfoById(HttpServletRequest request,
			HttpServletResponse response) {
		String menuId = request.getParameter("menuId");
		// 返回数据
		try {
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(prvlgService.getMenuInfoById(menuId));
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 新增或修改按钮
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author Xugl @2017-3-16 下午2:37:51
	 */
	@RequestMapping(value = "/saveorupdatebtn.do", method = RequestMethod.POST)
	public void saveOrUpdateBtn(HttpServletRequest request,
			HttpServletResponse response) {
		ButtonInfo btn = (ButtonInfo) JSONObject.toBean(
				JSONObject.fromObject(request.getParameter("btn")),
				ButtonInfo.class);

		// 返回数据
		try {
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(prvlgService.saveOrUpdateBtn(btn));
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取系统按钮列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author Xugl @2017-3-16 下午2:37:51
	 */
	@RequestMapping(value = "/queryselectbtnmenu.do", method = RequestMethod.POST)
	public void querySelectBtnMenu(HttpServletRequest request,
			HttpServletResponse response) {
		ButtonInfo btn = (ButtonInfo) JSONObject.toBean(
				JSONObject.fromObject(request.getParameter("btn")),
				ButtonInfo.class);

		// 返回数据
		try {
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(prvlgService.querySelectBtnMenu());
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取系统按钮列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author Xugl @2017-3-16 下午2:37:51
	 */
	@RequestMapping(value = "/getbuttoninfo.do", method = RequestMethod.POST)
	public void getButtonInfoById(HttpServletRequest request,
			HttpServletResponse response) {
		String btnId = request.getParameter("btnId");
		// 返回数据
		try {
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(prvlgService.getButtonInfoById(btnId));
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取系统按钮列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author Xugl @2017-3-16 下午2:37:51
	 */
	@RequestMapping(value = "/loadprvlg.do", method = RequestMethod.POST)
	public void loadPrvlgByRole(HttpServletRequest request,
			HttpServletResponse response) {
//		String loginUserId = Util.getCurrentUser(request).getId()+"";
		String roleId = request.getParameter("roleId");
		System.out.println(roleId);
		// 返回数据
		try {
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(
					prvlgService.loadPrvlgByRole("1", roleId));
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/saveroleprvlg.do", method = RequestMethod.POST)
	public void saveRolePrvlg(HttpServletRequest request,
			HttpServletResponse response) {
		String roleId = request.getParameter("roleId");
		String prvlgList = request.getParameter("prvlgList");
		// 返回数据
		try {
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(
					prvlgService.saveRolePrvlg(roleId, prvlgList));
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取评论列表信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getCommentList.do", method = RequestMethod.POST)
	public void queryCommentListByName(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		String userName = request.getParameter("condition");
		System.out.println(userName);
		// 返回数据
		try {
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(commentService.queryCommentsListByName(userName));
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除评论
	 * 
	 */
	@RequestMapping(value = "/delcomment.do", method = RequestMethod.POST)
	public void delCommentsListByIds(HttpServletRequest request,
			HttpServletResponse response) {
		String commentIds = request.getParameter("commentIds");
		 System.out.println("`````````````````");
		// 返回数据
		try {
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(
//					prvlgService.delUserListByIds(Util.getCurrentUser(request)
//							.getId()+"", userIds));
					commentService.delCommentListByIds(commentIds));
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取奖品列表信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getPrizeList.do", method = RequestMethod.POST)
	public void queryPrizeListByName(HttpServletRequest request,
			HttpServletResponse response) {
		// 返回数据
		try {
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(commentService.queryPrizeListAll());
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改奖品信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author Xugl @2017-3-16 下午2:37:51
	 */
	@RequestMapping(value = "/updatePrize.do", method = RequestMethod.POST)
	public void updatePrize(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("%%%%%%%%%%%%%%%%%%%%");
		
		PrizeInfoEntity prizeInfo = new PrizeInfoEntity();
		prizeInfo.prizeId = Integer.parseInt(request.getParameter("prizeId"));
		prizeInfo.prizeName = request.getParameter("prizeName");
		prizeInfo.prizeAllCount = request.getParameter("prizeAllCount");
		prizeInfo.prizeBalanceCount = request.getParameter("prizeBalanceCount");
		
//		PrizeInfoEntity prizeInfo = (PrizeInfoEntity) JSONObject.toBean(
//				JSONObject.fromObject(request.getParameter("prizeInfo")),
//				PrizeInfoEntity.class);
		// 返回数据
		try {
			System.out.println("---------------------");
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(commentService.UpdatePrize(prizeInfo));
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据奖品ID获取奖品信息
	 * 
	 */
	@RequestMapping(value = "/getprieinfobyid.do", method = RequestMethod.POST)
	public void getPrizeInfoById(HttpServletRequest request,
			HttpServletResponse response) {
		int prizeId = Integer.parseInt(request.getParameter("prizeId"));
		// 返回数据
		try {
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(commentService.getMenuInfoById(prizeId));
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取兑换券列表信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getexchangeList.do", method = RequestMethod.POST)
	public void queryExchangeListByName(HttpServletRequest request,
			HttpServletResponse response) {
		// 返回数据
		try {
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(commentService.queryExchangeListAll());
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 修改兑换券信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author Xugl @2017-3-16 下午2:37:51
	 */
	@RequestMapping(value = "/updateexchange.do", method = RequestMethod.POST)
	public void updateExchange(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("%%%%%%%%%%%%%%%%%%%%");
		
		ExchangeEntity exchangeInfo = new ExchangeEntity();
		exchangeInfo.prizeId = Integer.parseInt(request.getParameter("prizeId"));
		exchangeInfo.prizeName = request.getParameter("prizeName");
		exchangeInfo.exchangeId = Integer.parseInt(request.getParameter("exchangeId"));
		exchangeInfo.exchangeNUmber = request.getParameter("exchangeNUmber");
		exchangeInfo.exchangeIsExchange = request.getParameter("exchangeIsExchange");
		exchangeInfo.exchangeDate = request.getParameter("exchangeDate");
		
//		PrizeInfoEntity prizeInfo = (PrizeInfoEntity) JSONObject.toBean(
//				JSONObject.fromObject(request.getParameter("prizeInfo")),
//				PrizeInfoEntity.class);
		// 返回数据
		try {
			System.out.println("---------------------");
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(commentService.UpdateExchange(exchangeInfo));
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
