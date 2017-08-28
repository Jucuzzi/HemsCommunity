/**
 * 
 */
package com.telek.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.telek.cache.model.ButtonInfoCache;
import com.telek.cache.model.ConfigInfoCache;
import com.telek.cache.model.MenuInfoCache;
import com.telek.cache.model.RoleInfoCache;
import com.telek.cache.model.UserInfoCache;
import com.telek.dao.IPrvlgDAO;
import com.telek.model.ButtonInfo;
import com.telek.model.MenuInfo;
import com.telek.model.PrivilegeInfo;
import com.telek.model.RoleInfo;
import com.telek.model.UserEntity;
import com.telek.model.UserOperateInfo;
import com.telek.model.UserRoleRel;
import com.telek.util.ConstantVar;
import com.telek.util.Util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 权限管理service
 * 
 * @author Xugl
 * 
 * @date 2017-3-16 下午5:12:17
 */
@Component("prvlgService")
public class PrvlgService {
	private static final Logger LOG = Logger.getLogger(PrvlgService.class);

	@Autowired
	private IPrvlgDAO prvlgDAO;

	/**
	 * 用户登录
	 * 
	 * @param user
	 * @return
	 * @author Xugl
	 * @2016-1-8 上午11:03:09
	 */
	public String userLogin(String userAccount, String password, HttpServletRequest request) {
		// 登录标识
		boolean flag = false;
		JSONObject backJson = new JSONObject();
		// 遍历用户缓存，查找账号密码匹配的用户
		for (Map.Entry<String, UserEntity> entry : UserInfoCache.userInfoMap.entrySet()) {
			UserEntity user = entry.getValue();
			// 账号密码匹配
			if (user.getName().equals(userAccount)) {
				// 获取用户登录请求的session
				HttpSession session = request.getSession();
				// 将用户对象存入session
				session.setAttribute("loginUser", user);

				// 查找用户信息及相应权限
				backJson.put("result", "successed");
				backJson.put("loginUserId", user.getId());
				backJson.put("loginUserAccount", user.getName());
				backJson.put("loginUserName", user.getNickName());
				// 将该用户所拥有的权限放入session中，供前台拼接成一颗树
				String jsonarray = queryMenuInfoListByUserId(user.getId()+"").replaceAll("\"", "#");
				backJson.put("userOwnMenuPrvlg", jsonarray);

				// 查找用户信息及相应权限
				JSONObject prvlgJson = loadPrvlgByUser(user.getId()+"");
				// 用户菜单及操作权限存入session(页面，供后台过滤)
				session.setAttribute("prvlgMenuBtnJsp",
						prvlgJson.getString("prvlgMenuJsp") + prvlgJson.getString("prvlgBtnJsp"));

				// 用户拥有的按钮权限
				backJson.put("prvlgUserOwnBtn", queryUserOwnBtn(user.getId()+"").replaceAll("\"", "#"));

				// 登录成功
				flag = true;
				// 用户操作中将用户登录记录 add by scc 20161011
				prvlgDAO.saveOrUpdateUserOpt(
						new UserOperateInfo("", "登入", userAccount, new Date(), Util.getRemortIP(request)));
				// 跳出循环
				break;
			}
		}
		LOG.info("【userLogin】用户登录：" + userAccount + (flag ? "成功" : "账号或密码错误！"));
		if (!flag) {
			backJson.put(ConstantVar.RESULT, ConstantVar.FAILED);
			backJson.put(ConstantVar.DESC, "账号或密码错误！");
		}
		return backJson.toString();
	}

	/**
	 * 根据用户得到菜单树
	 * 
	 * @param userId
	 * @return
	 * @author wanghj
	 * @date 2016-7-8
	 */
	public String queryMenuInfoListByUserId(String userId) {
		// 获取用户角色关系
		List<UserRoleRel> urRelList = prvlgDAO.queryURRelListByUserId(userId);
		String roleIds = "";
		for (UserRoleRel urRel : urRelList) {
			roleIds += urRel.getRoleId() + ",";
		}
		List<MenuInfo> menulist = prvlgDAO.queryMenuListByRoleIds(Util.subStrLastChar(roleIds));
		JSONArray jsonarr = new JSONArray();
		for (MenuInfo menu : menulist) {
			jsonarr.add(menu.getJsonObj());
		}
		return jsonarr.toString();
	}

	/**
	 * 根据用户查询相应的权限
	 * 
	 * @param userId
	 * @return
	 * @author Xugl
	 * @2016-1-8 下午3:20:53
	 */
	public JSONObject loadPrvlgByUser(String userId) {
		JSONObject backJson = new JSONObject();
		// 菜单权限页面
		String prvlgMenuJsp = "";
		// 操作权限页面
		String prvlgBtnJsp = "";
		// 默认用户拥有的菜单权限
		prvlgMenuJsp = "/header.html,/prvlgerror.html,/microgrid_monitoring.html";
		// 默认用户拥有的操作权限
		prvlgBtnJsp = "";
		// 获取用户角色关系
		List<UserRoleRel> urRelList = prvlgDAO.queryURRelListByUserId(userId);
		// 组装
		String roleIds = "";
		for (UserRoleRel urRel : urRelList) {
			roleIds += urRel.getRoleId() + ",";
		}
		roleIds = Util.subStrLastChar(roleIds);
		// 获取角色下对应菜单权限
		List<MenuInfo> menuList = prvlgDAO.queryMenuListByRoleIds(roleIds);
		for (MenuInfo menu : menuList) {
			// 组装菜单url用于后台filter权限过滤
			if (!Util.isEmpty(menu.getMenuUrl())) {
				prvlgMenuJsp += menu.getMenuUrl() + ",";
			}
		}
		backJson.put("prvlgMenuJsp", prvlgMenuJsp);
		// 获取角色下对应操作权限
		List<ButtonInfo> btnList = prvlgDAO.queryBtnListByRoleIds(roleIds);
		for (ButtonInfo btn : btnList) {
			// 如果操作存在页面跳转，则获取url进行组装，eg：用户新增，权限配置等；反之，无url跳转，eg：用户删除
			if (!Util.isEmpty(btn.getBtnUrl())) {
				// 组装操作url用于后台filter权限过滤
				prvlgBtnJsp += btn.getBtnUrl() + ",";
			}
		}
		backJson.put("prvlgBtnJsp", prvlgBtnJsp);
		return backJson;
	}

	/**
	 * @param userId
	 * @return
	 * @author Wanghj @2016-9-10 下午4:37:23
	 */
	public String queryUserOwnBtn(String userId) {
		// 获取用户角色关系
		List<UserRoleRel> urRelList = prvlgDAO.queryURRelListByUserId(userId);
		String roleIds = "";
		for (UserRoleRel urRel : urRelList) {
			roleIds += urRel.getRoleId() + ",";
		}
		roleIds = Util.subStrLastChar(roleIds);
		List<MenuInfo> menulist = prvlgDAO.queryMenuListByRoleIds(roleIds);
		JSONArray jsonarr = new JSONArray();
		JSONObject obj = new JSONObject();
		for (MenuInfo menu : menulist) {
			if (menu.getMenuUrl() == null || "".equals(menu.getMenuUrl())) {
				continue;
			}
			String userOwnBtnStr = "";
			// 获取角色下对应操作权限
			List<ButtonInfo> btnList = prvlgDAO.queryBtnListByRoleIds(roleIds);
			for (ButtonInfo btnInfo : btnList) {
				if (btnInfo.getMenuId().equals(menu.getMenuId())) {
					userOwnBtnStr += btnInfo.getBtnAlias() + ",";
				}
			}
			obj.put("btnkey", menu.getMenuUrl());
			obj.put("btnvalue",
					userOwnBtnStr.length() > 0 ? userOwnBtnStr.substring(0, userOwnBtnStr.length() - 1) : "");
			jsonarr.add(obj);
		}
		return jsonarr.toString();
	}

	/**
	 * 用户退出登录
	 * 
	 * @return
	 * @author Xugl
	 * @2016-1-8 下午2:44:05
	 */
	public String userSignout(HttpServletRequest request) {
		// 用户操作中将用户登录记录
		prvlgDAO.saveOrUpdateUserOpt(new UserOperateInfo("", "登出", Util.getCurrentUser(request).getName(),
				new Date(), Util.getRemortIP(request)));
		return ConstantVar.SUCCESSED_RESULT;
	}

	/**
	 * 密码更新
	 * 
	 * @author zhanxf
	 * @date 2016年5月20日 下午5:17:39
	 * @param userId
	 * @param oldPwd
	 * @param newPwd
	 * @return
	 */
//	public String updateUserPwd(String userId, String oldPwd, String newPwd) {
//		UserEntity user = UserInfoCache.userInfoMap.get(userId);
//		if (!user.getPassword().equals(oldPwd)) {
//			return ConstantVar.FAILED_RESULT;
//		} else {
//			user.setPassword(newPwd);
//			prvlgDAO.updateUserInfo(user);
//			return ConstantVar.SUCCESSED_RESULT;
//		}
//	}

	public String queryUserListByName(String userName) {
		JSONObject backJson = new JSONObject();
		List<UserEntity> userList = prvlgDAO.queryUserListByParam(null, userName);
		JSONArray userArr = new JSONArray();
		for (UserEntity user : userList) {
			JSONObject userObj = user.getJsonObj();
//			List<RoleInfo> roleList = prvlgDAO.queryRoleListByUserId(user.getId()+"");
//			String roleNames = "";
//			for (RoleInfo role : roleList) {
//				roleNames += role.getRoleName() + ",";
//			}
//			userObj.put("roleNameList", Util.subStrLastChar(roleNames));
			userArr.add(userObj);
		}
		backJson.put(ConstantVar.TOTAL, userArr.size());
		backJson.put(ConstantVar.ROOT, userArr);

		System.out.println(userArr);
	
		return backJson.toString();
	}

//	public String delUserListByIds(String loginUserId, String userIds) {
//
//		String[] userIdArr = userIds.split(",");
//
//		for (String userId : userIdArr) {
//			if (loginUserId.equals(userId)) {
//				continue;
//			}
//			System.out.println(userId);
//			System.out.println("=======================");
//			prvlgDAO.delURRelByUserId(userId);
//			prvlgDAO.delUserInfoById(userId);
//		}
//		return ConstantVar.SUCCESSED_RESULT;
//	}
	public String delUserListByIds(String userIds) {

		String[] userIdArr = userIds.split(",");

		for (String userId : userIdArr) {
			System.out.println(userId);
			System.out.println("=======================");
			prvlgDAO.delURRelByUserId(userId);
			prvlgDAO.delUserInfoById(userId);
		}
		return ConstantVar.SUCCESSED_RESULT;
	}

	public String getUserInfoById(String userId) {
		JSONObject obj = UserInfoCache.userInfoMap.get(userId+"").getJsonObj();
		List<RoleInfo> roleInfoList = prvlgDAO.queryRoleListByUserId(userId);
		JSONArray arrJSON = new JSONArray();
		for (RoleInfo roleinfo : roleInfoList) {
			JSONObject objJSON = new JSONObject();
			objJSON.put("roleId", roleinfo.getRoleId());
			objJSON.put("roleName", roleinfo.getRoleName());
			arrJSON.add(objJSON);
		}
		obj.put("selfRoleArr", arrJSON);
		return obj.toString();
	}

	/**
	 * 获取全部角色
	 * 
	 * @return
	 * @author Xugl @2017-3-17 下午1:29:00
	 */
	public String queryRoleListAll() {
		JSONObject backJson = new JSONObject();
		List<RoleInfo> roleList = prvlgDAO.queryRoleListAll();
		JSONArray roleArray = new JSONArray();
		for (RoleInfo role : roleList) {
			roleArray.add(role.getJsonObj());
		}
		backJson.put(ConstantVar.TOTAL, roleArray.size());
		backJson.put(ConstantVar.ROOT, roleArray);
		return backJson.toString();
	}

	/**
	 * 校验用户是否已存在
	 * 
	 * @author zhanxf
	 * @date 2016年3月15日 下午4:24:32
	 * @param userName
	 * @return
	 */
	public String checkUserNameRepate(String userId, String userAccount) {
		List<UserEntity> list = prvlgDAO.queryUserListByAccurateParam(userAccount, null);
		if (!Util.isEmpty(list)) {
			if (Util.isEmpty(userId) || !userId.equals(list.get(0).getId()+"")) {
				return ConstantVar.FAILED_RESULT;
			}
		}
		return ConstantVar.SUCCESSED_RESULT;
	}

	public String saveOrUpdateUser(HttpServletRequest request, UserEntity user, String roleIdList) {
		if (!Util.isEmpty(user.getId()+"") && UserInfoCache.userInfoMap.containsKey(user.getId())) {
			String userName = user.getName();
			user = UserInfoCache.userInfoMap.get(user.getId());
			user.setName(userName);
		}
		if (request.getParameter("id") != null  && !request.getParameter("id").equals("") ) {
			prvlgDAO.updateUserInfo(user);
		} else {
			prvlgDAO.saveUserInfo(user);
		}
		System.out.println("*******************");
		if (user != null) {
			// 删除原先的用户角色关系信息
//			prvlgDAO.delURRelByUserId(user.getId()+"");
			// 新建用户角色关系信息
			List<String> roleList = Util.strToList(roleIdList, ",");

			List<UserRoleRel> userRoleRelList = new ArrayList<UserRoleRel>();
			for (int i = 0; i < roleList.size(); i++) {
				UserRoleRel userRoleRel = new UserRoleRel();
				userRoleRel.setUserId(user.getId()+"");
				userRoleRel.setRoleId(roleList.get(i));
				userRoleRelList.add(userRoleRel);
			}
			prvlgDAO.saveUserRoleRelList(userRoleRelList);
			LOG.info(user.getId() + "用户保存成功,角色关系保存成功");
//			if (Util.getCurrentUser(request).getId() == user.getId()) {
//				request.getSession().setAttribute("loginUser", user);
//			}
			return ConstantVar.SUCCESSED_RESULT;
		} else {
			LOG.info("用户保存失败");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return ConstantVar.FAILED_RESULT;
		}
	}

	/**
	 * 根据角色ids删除角色
	 * 
	 * @param roleIds
	 * @return 返回结果信息
	 * @author Xugl
	 * @2016-1-5 下午3:57:21
	 */
	public String delRoleListByIds(String roleIds) {
		List<UserRoleRel> rrList = prvlgDAO.queryURRelListByRoleIds(roleIds);
		if (rrList.size() > 0) {
			return ConstantVar.USER_USEING__ERROR;
		}
		// 删除角色
		prvlgDAO.delRoleInfoByIds(roleIds);
		// 删除角色权限关联
		prvlgDAO.delPrvlgByRoleIds(roleIds);
		LOG.info("删除角色：" + roleIds);
		return ConstantVar.SUCCESSED_RESULT;
	}

	/**
	 * 获取角色信息
	 * 
	 * @param roleId
	 * @return
	 * @author Xugl
	 * @2017年3月20日 下午4:32:55
	 */
	public String getRoleInfoById(String roleId) {
		return RoleInfoCache.roleInfoMap.get(roleId).getJsonObj().toString();
	}

	/**
	 * 角色名校验
	 * 
	 * @author zhanxf
	 * @date 2016年3月24日 下午4:43:13
	 * @param roleId
	 * @param roleName
	 * @return
	 */
	public String checkRoleNameRepate(String roleId, String roleName) {
		List<RoleInfo> list = prvlgDAO.queryRoleListByAccurateName(roleName);
		if (!Util.isEmpty(list)) {
			if (Util.isEmpty(roleId) || !roleId.equals(list.get(0).getRoleId())) {
				return ConstantVar.FAILED_RESULT;
			}
		}
		return ConstantVar.SUCCESSED_RESULT;
	}

	/**
	 * 新增或修改角色
	 * 
	 * @param role
	 *            对象
	 * @return 返回结果
	 * @author Xugl @2015-12-30 上午9:21:52
	 */
	public String saveOrUpdateRole(RoleInfo role) {
		if (Util.isEmpty(role.getRoleId())) {
			System.out.println("新增角色"+role);
			prvlgDAO.saveRoleInfo(role);
		} else {
			System.out.println("修改角色"+role);
			prvlgDAO.updateRoleInfo(role);
		}
		return ConstantVar.SUCCESSED_RESULT;
	}

	public String queryConfigureMenu() {
		JSONObject backJson = new JSONObject();
		Map<String, MenuInfo> menuMap = MenuInfoCache.menuInfoMap;
		JSONArray moduleArray = new JSONArray();
		for (Map.Entry<String, MenuInfo> entry1 : menuMap.entrySet()) {
			MenuInfo menu1 = entry1.getValue();
			// 模块
			JSONObject moduleObj = new JSONObject();
			if (menu1.getParentId() == null || "".equals(menu1.getParentId())) {
				// 父级
				moduleObj = menu1.getJsonObj();
				// 菜单列表
				JSONArray menuArray = new JSONArray();
				for (Map.Entry<String, MenuInfo> entry2 : menuMap.entrySet()) {
					MenuInfo menu2 = entry2.getValue();
					if (menu1.getMenuId().equals(menu2.getParentId())) {
						// 子集
						menuArray.add(menu2.getJsonObj());
					}
				}
				moduleObj.put("menuList", menuArray);
				moduleArray.add(moduleObj);
			}
		}
		backJson.put("menuBtnList", moduleArray.toString());
		return backJson.toString();
	}

	public String queryConfigureBtn() {
		JSONObject backJson = new JSONObject();
		Map<String, ButtonInfo> btnMap = ButtonInfoCache.btnInfoMap;
		List<Object[]> menuidlist = prvlgDAO.queryButtonInfoGroupByMenuId();
		JSONArray moduleArray = new JSONArray();
		for (Object[] obj : menuidlist) {
			if (obj[0] == null) {
				continue;
			}
			JSONObject moduleObj = new JSONObject();
			moduleObj.put("menuId", obj[0]);
			moduleObj.put("menuName", obj[1]);
			// 按钮列表
			JSONArray btnArray = new JSONArray();
			System.out.println(btnMap);
			for (Map.Entry<String, ButtonInfo> entry1 : btnMap.entrySet()) {
				System.out.println(entry1);
				ButtonInfo btn1 = entry1.getValue();
				if (btn1.getMenuId().equals(obj[0])) {
					btnArray.add(btn1.getJsonObj());
				}
			}
			moduleObj.put("btnList", btnArray);
			moduleArray.add(moduleObj);
		}
		backJson.put("menuBtnList", moduleArray.toString());
		return backJson.toString();
	}

	public String delMenuOrBtnByIdAndFlag(String id, String flag) {
		// 删除菜单
		if ("1".equals(flag)) {
			prvlgDAO.delMenuInfoById(id);
			prvlgDAO.delPrvlgByAccessValue(id);
			// 删除按钮
		} else {
			prvlgDAO.delBtnInfoById(id);
			prvlgDAO.delPrvlgByAccessValue(id);
		}
		return ConstantVar.SUCCESSED_RESULT;
	}

	public String checkSubMenuExistByParentId(String parentId) {
		return prvlgDAO.querySubMenuListByParentId(parentId).size() == 0 ? ConstantVar.SUCCESSED_RESULT
				: ConstantVar.FAILED_RESULT;
	}

	public String saveOrUpdataMenu(MenuInfo mi) {
		String menuId = mi.getMenuId();
		// 超级管理员角色添加权限
		if (Util.isEmpty(menuId)) {
			prvlgDAO.saveMenuInfo(mi);
			prvlgDAO.saveOrUpdatePrvlg(
					new PrivilegeInfo(ConfigInfoCache.configMap.get(ConstantVar.SUPER_ROLEID), 0, mi.getMenuId()));
		} else {
			prvlgDAO.updateMenuInfo(mi);
		}
		return ConstantVar.SUCCESSED_RESULT;
	}

	public String queryParentMenuList() {
		JSONObject backJson = new JSONObject();
		List<MenuInfo> menuList = prvlgDAO.queryParentMenuList();
		JSONArray jsonArray = new JSONArray();
		for (MenuInfo menu : menuList) {
			jsonArray.add(menu.getJsonObj());
		}
		backJson.put("menuList", jsonArray.toString());
		return backJson.toString();
	}

	/**
	 * 根据菜单id查询菜单信息
	 * 
	 * @author zhanxf
	 * @date 2016年5月24日 上午9:34:08
	 * @param menuId
	 * @return
	 */
	public String getMenuInfoById(String menuId) {
		return prvlgDAO.getMenuInfoById(menuId).getJsonObj().toString();
	}

	public String saveOrUpdateBtn(ButtonInfo btn) {
		String btnId = btn.getBtnId();
		if (btn.getBtnMethod() == null) {
			btn.setBtnMethod("");
		}
		// 超级管理员角色添加权限
		if (Util.isEmpty(btnId)) {
			prvlgDAO.saveBtnInfo(btn);
			prvlgDAO.saveOrUpdatePrvlg(
					new PrivilegeInfo(ConfigInfoCache.configMap.get(ConstantVar.SUPER_ROLEID), 1, btn.getBtnId()));
		} else {
			prvlgDAO.updateBtnInfo(btn);
		}
		return ConstantVar.SUCCESSED_RESULT;
	}

	public String querySelectBtnMenu() {
		Map<String, MenuInfo> menuMap = MenuInfoCache.menuInfoMap;
		JSONArray jsonArray = new JSONArray();
		for (Map.Entry<String, MenuInfo> entry : menuMap.entrySet()) {
			MenuInfo mi = entry.getValue();
			if (mi.getParentId() != null && !"".equals(mi.getParentId())) {// 只获取子集的
				JSONObject obj = new JSONObject();
				obj.put("menuId", mi.getMenuId());
				obj.put("menuName", mi.getMenuName());
				jsonArray.add(obj);
			}
		}
		return jsonArray.toString();
	}

	/**
	 * 根据按钮id查询按钮信息
	 * 
	 * @author zhanxf
	 * @date 2016年5月24日 上午9:34:28
	 * @param btnId
	 * @return
	 */
	public String getButtonInfoById(String btnId) {
		return ButtonInfoCache.btnInfoMap.get(btnId).getJsonObj().toString();
	}

	/**
	 * 根据角色加载权限列表
	 * 
	 * @param roleId
	 * @return 权限
	 * @author Xugl
	 * @2016-1-7 上午10:27:36
	 */
	public String loadPrvlgByRole(String loginUserId, String roleId) {
		JSONObject backJson = new JSONObject();
		// 可见权限列表
		List<PrivilegeInfo> allPrvlgList = null;

		List<UserRoleRel> urrList = prvlgDAO.queryURRelListByUserId(loginUserId);
		String roleIdArrStr = "";
		for (UserRoleRel urr : urrList) {
			roleIdArrStr += urr.getRoleId() + ",";
		}
		allPrvlgList = prvlgDAO.loadPrivilegeList(2, roleIdArrStr);

		// 加载角色可使用的菜单
		List<PrivilegeInfo> rolePrvlgList = prvlgDAO.loadPrivilegeList(2, roleId);

		Map<String, MenuInfo> menuMap = new HashMap<String, MenuInfo>();
		Map<String, ButtonInfo> btnMap = new HashMap<String, ButtonInfo>();
		Map<String, Integer> operatorMap = new HashMap<String, Integer>();
		if (!Util.isEmpty(rolePrvlgList)) {
			for (PrivilegeInfo prvlg : rolePrvlgList) {
				operatorMap.put(prvlg.getAccessValue(), 1);
			}
		}
		for (PrivilegeInfo prvlg : allPrvlgList) {
			if (prvlg.getAccess() == 0) {// 菜单
				menuMap.put(prvlg.getAccessValue(), prvlgDAO.getMenuInfoById(prvlg.getAccessValue()));
			} else if (prvlg.getAccess() == 1) {// 按钮
				btnMap.put(prvlg.getAccessValue(), prvlgDAO.getButtonInfoById(prvlg.getAccessValue()));
			}
		}
		JSONArray moduleArray = new JSONArray();
		for (String menuId : menuMap.keySet()) {
			MenuInfo menu1 = menuMap.get(menuId);
			// 模块
			JSONObject moduleObj = new JSONObject();
			if (menu1.getParentId() == null || "".equals(menu1.getParentId())) {
				moduleObj.put("moduleId", menu1.getMenuId());
				moduleObj.put("moduleName", menu1.getMenuName());
				moduleObj.put("operate", operatorMap.containsKey(menu1.getMenuId()) ? 1 : 0);
				// 菜单列表
				JSONArray menuArray = new JSONArray();
				for (String menuId2 : menuMap.keySet()) {
					MenuInfo menu2 = menuMap.get(menuId2);
					// 菜单
					JSONObject menuObj = new JSONObject();
					if (menu1.getMenuId().equals(menu2.getParentId())) {
						menuObj.put("menuId", menu2.getMenuId());
						menuObj.put("menuName", menu2.getMenuName());
						menuObj.put("operate", operatorMap.containsKey(menu2.getMenuId()) ? 1 : 0);
						// 操作列表
						JSONArray btnArray = new JSONArray();
						for (String btnId : btnMap.keySet()) {
							ButtonInfo btn = btnMap.get(btnId);
							// 操作
							JSONObject btnObj = new JSONObject();
							if (btn.getMenuId().equals(menu2.getMenuId())) {
								btnObj.put("btnId", btn.getBtnId());
								btnObj.put("btnName", btn.getBtnName());
								btnObj.put("btnAlias", btn.getBtnAlias());
								btnObj.put("operate", operatorMap.containsKey(btn.getBtnId()) ? 1 : 0);
								btnArray.add(btnObj);
							}
						}
						menuObj.put("btnList", btnArray);
						menuArray.add(menuObj);
					}
				}
				moduleObj.put("menuList", menuArray);
				moduleArray.add(moduleObj);
			}
		}
		backJson.put("menuBtnList", moduleArray.toString());
		return backJson.toString();
	}

	/**
	 * 更新角色的权限信息
	 * 
	 * @param roleId
	 *            角色标识
	 * @param prvlgList
	 *            权限列表,eg:menu1,menu3&btn1,btn4
	 * @return 返回结果
	 * @author Xugl
	 * @2016-1-7 下午4:34:22
	 */
	public String saveRolePrvlg(String roleId, String prvlgList) {
		if (ConfigInfoCache.configMap.get(ConstantVar.SUPER_ROLEID).equals(roleId)) {
			return Util.getFailedReturnWithDesc("超级管理员无法进行权限修改");
		}
		// 删除角色对应的菜单&操作
		prvlgDAO.delPrvlgByRoleIds(roleId);
		// 分割菜单与操作
		String[] prvlgListStr = prvlgList.split("&");
		String menuIdStr = "";
		String btnIdStr = "";
		int index = prvlgList.indexOf("&");
		if (prvlgListStr.length == 1) {
			if (index == 0) {
				btnIdStr = prvlgListStr[0];
			} else {
				menuIdStr = prvlgListStr[0];
			}
		} else if (prvlgListStr.length == 2) {
			menuIdStr = prvlgListStr[0];
			btnIdStr = prvlgListStr[1];
		}
		// 权限信息保存数据库操作
		dealWithPrvlgIntoDB(roleId, Util.strToList(menuIdStr, ","), Util.strToList(btnIdStr, ","));
		return ConstantVar.SUCCESSED_RESULT;
	}

	/**
	 * 权限信息保存数据库操作
	 * 
	 * @param roleId
	 * @param menuIds
	 * @param btnIds
	 * @author Xugl @2016-7-20 下午5:59:11
	 */
	private void dealWithPrvlgIntoDB(String roleId, List<String> menuIdList, List<String> btnIdList) {
		List<PrivilegeInfo> prvlgInfoList = new ArrayList<PrivilegeInfo>();
		// 更新角色对应的菜单
		if (menuIdList.size() != 1) {
			for (String menuId : menuIdList) {
				PrivilegeInfo prvlgInfo = new PrivilegeInfo();
				prvlgInfo.setRoleId(roleId);
				prvlgInfo.setAccess(0);
				prvlgInfo.setAccessValue(menuId);
				prvlgInfoList.add(prvlgInfo);
			}
		}
		// 更新角色对应的操作
		if (!"".equals(btnIdList.get(0))) {
			for (String btnId : btnIdList) {
				PrivilegeInfo prvlgInfo = new PrivilegeInfo();
				prvlgInfo.setRoleId(roleId);
				prvlgInfo.setAccess(1);
				prvlgInfo.setAccessValue(btnId);
				prvlgInfoList.add(prvlgInfo);
			}
		}
		prvlgDAO.saveOrUpdatePrvlgList(prvlgInfoList);
	}
}
