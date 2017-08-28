package com.telek.dao;

import java.util.List;

import com.telek.model.ButtonInfo;
import com.telek.model.MenuInfo;
import com.telek.model.PrivilegeInfo;
import com.telek.model.RoleInfo;
import com.telek.model.UserEntity;
import com.telek.model.UserOperateInfo;
import com.telek.model.UserRoleRel;


/**
 * 公共模块-系统管理接口类
 * 
 * @author Xugl
 * 
 * @date 2015-12-15 下午1:47:19
 */
public interface IPrvlgDAO {
	/********************************* 系统用户 *********************************/

	/**
	 * 新增系统用户
	 * 
	 * @param user
	 * @return
	 * @author Xugl
	 * @2017年5月12日 下午6:15:08
	 */
	boolean saveUserInfo(UserEntity user);

	/**
	 * 编辑系统用户
	 * 
	 * @param user
	 * @return
	 * @author Xugl
	 * @2017年5月12日 下午6:15:08
	 */
	boolean updateUserInfo(UserEntity user);

	/**
	 * 删除指定id的系统用户
	 * 
	 * @param userId
	 * @author Xugl @2017-3-15 下午5:09:39
	 */
	void delUserInfoById(String userId);

	/**
	 * 获取指定参数的系统用户列表（模糊）
	 * 
	 * @param userAccount
	 *            用户账号，null-无此条件
	 * @param userName
	 *            用户昵称，null-无此条件
	 * @return
	 * @author Xugl @2017-3-15 下午5:09:48
	 */
	List<UserEntity> queryUserListByParam(String userAccount, String userName);

	/**
	 * 获取指定参数的系统用户列表（精确）
	 * 
	 * @param userAccount
	 *            用户账号，null-无此条件
	 * @param userName
	 *            用户昵称，null-无此条件
	 * @return
	 * @author Xugl @2017-3-15 下午5:09:48
	 */
	List<UserEntity> queryUserListByAccurateParam(String userAccount, String userName);

	/**
	 * 获取指定id的系统用户
	 * 
	 * @param userId
	 * @return
	 * @author Xugl @2017-3-15 下午5:10:11
	 */
	UserEntity getUserInfoById(String userId);

	/********************************* 系统用户 *********************************/

	/********************************* 系统用户角色关系 *********************************/
	/**
	 * 新增、编辑用户角色关系
	 * 
	 * @param userRoleRel
	 * @return
	 * @author Xugl @2017-3-15 下午5:10:41
	 */
	void saveUserRoleRel(UserRoleRel userRoleRel);

	/**
	 * 新增、编辑用户角色关系（批量）
	 * 
	 * @param userRoleRelList
	 * @return
	 * @author Xugl @2017-3-15 下午5:11:21
	 */
	void saveUserRoleRelList(List<UserRoleRel> userRoleRelList);

	/**
	 * 删除指定用户id的用户角色关系
	 * 
	 * @param userId
	 *            用户编码
	 * @author Xugl @2016-1-25 下午1:37:22
	 */
	void delURRelByUserId(String userId);

	/**
	 * 根据用户id获取其下用户角色关系列表
	 * 
	 * @param userId
	 * @return
	 * @author Wanghj @2016-9-20 下午2:50:28
	 */
	List<UserRoleRel> queryURRelListByUserId(String userId);

	/**
	 * 根据角色ids获取其下用户角色关系列表
	 * 
	 * @param roleIds
	 * @return
	 * @author Wanghj @2016-9-19 上午9:49:00
	 */
	List<UserRoleRel> queryURRelListByRoleIds(String roleIds);

	/********************************* 系统用户角色关系 *********************************/
	/********************************* 系统角色 *********************************/

	/**
	 * 新增、编辑系统角色
	 * 
	 * @param role
	 * @return
	 * @author Xugl
	 * @2017年5月22日 下午3:15:45
	 */
	boolean saveRoleInfo(RoleInfo role);

	/**
	 * 新增、编辑系统角色
	 * 
	 * @param role
	 * @return
	 * @author Xugl
	 * @2017年5月22日 下午3:15:45
	 */
	boolean updateRoleInfo(RoleInfo role);

	/**
	 * 删除指定角色id的角色（批量）
	 * 
	 * @param roleIds
	 *            角色ids
	 * @author Xugl
	 * @2016-4-1 下午1:34:42
	 */
	void delRoleInfoByIds(String roleIds);

	/**
	 * 获取指定角色名称的角色列表（模糊）
	 * 
	 * @param roleName
	 * @return
	 * @author Xugl @2017-3-15 下午5:13:31
	 */
	List<RoleInfo> queryRoleListByName(String roleName);

	/**
	 * 获取指定角色名称的角色列表（精确）
	 * 
	 * @param roleName
	 * @return
	 * @author Xugl @2017-3-15 下午5:13:31
	 */
	List<RoleInfo> queryRoleListByAccurateName(String roleName);

	/**
	 * 获取指定用户id的角色列表
	 * 
	 * @param userId
	 * @return
	 * @author Xugl @2017-3-15 下午5:12:37
	 */
	List<RoleInfo> queryRoleListByUserId(String userId);

	/**
	 * 获取全部角色列表
	 * 
	 * @return
	 * @author Xugl @2017-3-17 下午1:31:40
	 */
	List<RoleInfo> queryRoleListAll();

	/********************************* 系统角色 *********************************/

	/********************************* 系统权限 *********************************/
	/********************************* 系统权限-按钮 *********************************/

	/**
	 * 新增操作权限
	 * 
	 * @param buttonInfo
	 * @author wanghj
	 * @date 2016-7-13
	 */
	boolean saveBtnInfo(ButtonInfo buttonInfo);

	/**
	 * 编辑操作权限
	 * 
	 * @param buttonInfo
	 * @author wanghj
	 * @date 2016-7-13
	 */
	boolean updateBtnInfo(ButtonInfo buttonInfo);

	/**
	 * 删除指定id的操作信息
	 * 
	 * @param btnId
	 * @author wanghj
	 * @date 2016-7-13
	 */
	boolean delBtnInfoById(String btnId);

	/**
	 * 获取指定id的按钮操作信息
	 * 
	 * @param btnId
	 * @return
	 * @author Xugl @2017-3-15 下午5:14:52
	 */
	ButtonInfo getButtonInfoById(String btnId);

	/**
	 * 获取指定角色id的操作ids字符串
	 * 
	 * @param roleId
	 *            角色id
	 * @return 操作ids
	 * @author Xugl
	 * @2016-4-1 下午5:20:44
	 */
	String getBtnIdsByRoleId(String roleId);

	/**
	 * 获取指定角色ids的按钮列表
	 * 
	 * @author zhanxf
	 * @date 2016年3月16日 下午5:18:09
	 * @param roleIds
	 * @return
	 */
	List<ButtonInfo> queryBtnListByRoleIds(String roleIds);

	/**
	 * 获取指定菜单id的按钮列表
	 * 
	 * @param menuId
	 * @return
	 * @author Xugl @2017-3-15 下午5:07:42
	 */
	List<ButtonInfo> queryBtnListByMenuId(String menuId);

	/**
	 * 获取全部的按钮列表
	 * 
	 * @return
	 * @author Xugl @2017-3-15 下午5:36:05
	 */
	List<ButtonInfo> queryBtnListAll();

	/**
	 * 查询menuid和menuname关联
	 * 
	 * @return
	 * @author wanghj
	 * @date 2016-7-13
	 */
	List<Object[]> queryButtonInfoGroupByMenuId();

	/********************************* 系统权限-按钮 *********************************/
	/********************************* 系统权限-菜单 *********************************/
	/**
	 * 保存菜单
	 * 
	 * @param menuInfo
	 *            菜单信息
	 * @author wanghj
	 * @date 2016-7-13
	 */
	boolean saveMenuInfo(MenuInfo menuInfo);

	/**
	 * 编辑菜单
	 * 
	 * @param menuInfo
	 *            菜单信息
	 * @author wanghj
	 * @date 2016-7-13
	 */
	boolean updateMenuInfo(MenuInfo menuInfo);

	/**
	 * 删除指定id的菜单信息
	 * 
	 * @param menuId
	 *            菜单id
	 * @author wanghj
	 * @date 2016-7-13
	 */
	boolean delMenuInfoById(String menuId);

	/**
	 * 获取指定id的菜单信息
	 * 
	 * @param menuId
	 * @return
	 * @author Jiang CJ @2016-7-26 下午3:35:08
	 */
	MenuInfo getMenuInfoById(String menuId);

	/**
	 * 获取指定角色id的菜单ids字符串
	 * 
	 * @param roleId
	 *            角色id
	 * @return 菜单ids
	 * @author Xugl
	 * @2016-4-1 下午5:20:44
	 */
	String getMenuIdsByRoleId(String roleId);

	/**
	 * 查询父级菜单
	 * 
	 * @return
	 * @author wanghj
	 * @date 2016-7-13
	 */
	List<MenuInfo> queryParentMenuList();

	/**
	 * 获取指定id的子级菜单集合
	 * 
	 * @param parentId
	 *            父级id
	 * @return 子级菜单集合
	 * @author Xugl @2016-7-21 上午9:18:35
	 */
	List<MenuInfo> querySubMenuListByParentId(String parentId);

	/**
	 * 查询指定角色ids的菜单列表
	 * 
	 * @author zhanxf
	 * @date 2016年3月16日 下午5:18:09
	 * @param roleIds
	 * @return
	 */
	public List<MenuInfo> queryMenuListByRoleIds(String roleIds);

	/**
	 * 获取全部的菜单列表
	 * 
	 * @return
	 * @author Xugl @2017-3-15 下午5:38:18
	 */
	public List<MenuInfo> queryMenuListAll();

	/********************************* 系统权限-菜单 *********************************/
	/********************************* 系统权限-权限关联 *********************************/
	/**
	 * 新增/修改权限信息
	 * 
	 * @param prvlg
	 * @author Xugl
	 * @2017年3月21日 下午3:06:16
	 */
	void saveOrUpdatePrvlg(PrivilegeInfo prvlg);

	/**
	 * 批量新增/修改权限信息
	 * 
	 * @param prvlgList
	 *            权限信息
	 * @author Xugl @2016-7-20 下午5:57:37
	 */
	void saveOrUpdatePrvlgList(List<PrivilegeInfo> prvlgList);

	/**
	 * 根据角色编码批量删除权限关联
	 * 
	 * @param roleIds
	 *            角色ids
	 * @author Xugl @2016-1-26 下午4:08:22
	 */
	void delPrvlgByRoleIds(String roleIds);

	/**
	 * 删除中间关联表的关联
	 * 
	 * @param menuOrBtnId
	 * @author wangxl
	 * @date 2016-7-15
	 */
	void delPrvlgByAccessValue(String menuOrBtnId);

	/**
	 * 加载权限列表
	 * 
	 * @author zhanxf
	 * @date 2016年3月22日 上午11:24:10
	 * @param menueOrBtn
	 *            0 菜单 1按钮 2全部
	 * @param roleIds
	 *            角色ids
	 * @return
	 */
	List<PrivilegeInfo> loadPrivilegeList(int menuOrBtn, String roleIds);

	/********************************* 系统权限-权限关联 *********************************/
	/********************************* 系统权限-用户操作 *********************************/
	/**
	 * 新增、编辑用户操作
	 * 
	 * @param userOpt
	 * @author Xugl @2017-3-17 上午9:58:18
	 */
	void saveOrUpdateUserOpt(UserOperateInfo userOpt);

	/********************************* 系统权限-用户操作 *********************************/
	/********************************* 系统权限 *********************************/

}
