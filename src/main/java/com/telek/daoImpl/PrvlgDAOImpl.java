package com.telek.daoImpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.telek.cache.model.ButtonInfoCache;
import com.telek.cache.model.MenuInfoCache;
import com.telek.cache.model.RoleInfoCache;
import com.telek.cache.model.UserInfoCache;
import com.telek.cache.service.CacheService;
import com.telek.dao.IDAO;
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



/**
 * 公共模块-系统管理实现类
 * 
 * @author Xugl
 * 
 * @date 2015-12-15 下午1:48:37
 */
@Component("prvlgDAO")
public class PrvlgDAOImpl implements IPrvlgDAO {
	private static final Logger LOG = Logger.getLogger(PrvlgDAOImpl.class);

	@Autowired
	private IDAO iDAO;

	@Autowired
	private CacheService cacheService;

	public boolean saveUserInfo(UserEntity user) {
		try {
			Object obj = cacheService.updCacheData(user, ConstantVar.ADD_DATA);
			return obj != null;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return false;
		}
	}

	public boolean updateUserInfo(UserEntity user) {
		try {
			Object obj = cacheService.updCacheData(user, ConstantVar.UPD_DATA);
			return obj != null;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return false;
		}
	}
	

	@SuppressWarnings("unchecked")
	public List<UserEntity> queryUserListByParam(String userAccount, String userName) {
		StringBuilder sb = new StringBuilder();
		sb.append("from UserEntity where 1=1 ");
		if (!Util.isEmpty(userAccount)) {
			sb.append("and name like '%");
			sb.append( userAccount );
			sb.append("%' ");
		}
		if (!Util.isEmpty(userName)) {
			sb.append("and nickName like '%" + userName + "%' ");
		}
//		sb.append("order by createDate desc");
		return (List<UserEntity>) iDAO.findByHql(sb.toString());
	}

	@SuppressWarnings("unchecked")
	public List<UserEntity> queryUserListByAccurateParam(String userAccount, String userName) {
		StringBuilder sb = new StringBuilder();
		sb.append("from UserEntity where 1=1 ");
		if (!Util.isEmpty(userAccount)) {
			sb.append("and name = '");
			sb.append(userAccount);
			sb.append("' ");
		}
		if (!Util.isEmpty(userName)) {
			sb.append("and nickName = '");
			sb.append(userName);
			sb.append("' ");
		}
//		sb.append("order by createDate desc");
		return (List<UserEntity>) iDAO.findByHql(sb.toString());
	}


	public UserEntity getUserInfoById(String userId) {
		Object obj = iDAO.getObject(UserEntity.class, userId);
		return obj == null ? null : (UserEntity) obj;
	}


	public void saveUserRoleRel(UserRoleRel userRoleRel) {
		iDAO.saveOrUpdate(userRoleRel);
	}


	public void saveUserRoleRelList(List<UserRoleRel> userRoleRelList) {
		iDAO.saveOrUpdateAll(userRoleRelList);
	}


	public void delURRelByUserId(String userId) {
		StringBuilder sb = new StringBuilder();
		sb.append("delete from UserRoleRel where userId = '");
		sb.append(userId);
		sb.append("'");
		iDAO.delObectAll(sb.toString());
	}

	@SuppressWarnings("unchecked")

	public List<UserRoleRel> queryURRelListByUserId(String userId) {
		StringBuilder sb = new StringBuilder();
		sb.append("from UserRoleRel where userId = '");
		sb.append(userId);
		sb.append("'");
		return (List<UserRoleRel>) iDAO.findByHql(sb.toString());
	}

	@SuppressWarnings("unchecked")

	public List<UserRoleRel> queryURRelListByRoleIds(String roleIds) {
		StringBuilder sb = new StringBuilder();
		sb.append("from UserRoleRel where roleId in (");
		sb.append(Util.strToSqlInStr(roleIds, ","));
		sb.append(")");
		return (List<UserRoleRel>) iDAO.findByHql(sb.toString());
	}


	public boolean saveRoleInfo(RoleInfo role) {
		try {
			Object obj = cacheService.updCacheData(role, ConstantVar.ADD_DATA);
			return obj != null;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return false;
		}
	}


	public boolean updateRoleInfo(RoleInfo role) {
		try {
			Object obj = cacheService.updCacheData(role, ConstantVar.UPD_DATA);
			return obj != null;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return false;
		}
	}


	public void delRoleInfoByIds(String roleIds) {
		String[] roleIdArr = roleIds.split(",");
		for (String roleId : roleIdArr) {
			RoleInfo role = RoleInfoCache.roleInfoMap.get(roleId);
			try {
				cacheService.updCacheData(role, ConstantVar.DEL_DATA);
			} catch (Exception e) {
				LOG.error(e.getMessage(), e);
			}
		}
	}
	
	public void delUserInfoById(String userId) {
		StringBuilder sb = new StringBuilder();
		sb.append("delete from UserEntity where id = '");
		sb.append(userId);
		sb.append("'");
		iDAO.delObectAll(sb.toString());
	}

	@SuppressWarnings("unchecked")

	public List<RoleInfo> queryRoleListByName(String roleName) {
		StringBuilder sb = new StringBuilder();
		sb.append("from RoleInfo where roleName like '%");
		sb.append(roleName);
		sb.append("%' order by createDate desc");
		return (List<RoleInfo>) iDAO.findByHql(sb.toString());
	}

	@SuppressWarnings("unchecked")

	public List<RoleInfo> queryRoleListByAccurateName(String roleName) {
		StringBuilder sb = new StringBuilder();
		sb.append("from RoleInfo where roleName = '");
		sb.append(roleName);
		sb.append("' order by createDate desc");
		return (List<RoleInfo>) iDAO.findByHql(sb.toString());
	}

	@SuppressWarnings("unchecked")

	public List<RoleInfo> queryRoleListByUserId(String userId) {
		String hql = "select t2 from UserRoleRel t1, RoleInfo t2 where t1.roleId = t2.roleId and t1.userId = '" + userId
				+ "'";
		return (List<RoleInfo>) iDAO.findByHql(hql);
	}

	@SuppressWarnings("unchecked")

	public List<RoleInfo> queryRoleListAll() {
		return (List<RoleInfo>) iDAO.findByHql("from RoleInfo order by createDate");
	}


	public boolean saveBtnInfo(ButtonInfo buttonInfo) {
		try {
			Object obj = cacheService.updCacheData(buttonInfo, ConstantVar.ADD_DATA);
			return obj != null;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return false;
		}
	}


	public boolean updateBtnInfo(ButtonInfo buttonInfo) {
		try {
			Object obj = cacheService.updCacheData(buttonInfo, ConstantVar.UPD_DATA);
			return obj != null;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return false;
		}
	}


	public boolean delBtnInfoById(String btnId) {
		try {
			ButtonInfo btn = ButtonInfoCache.btnInfoMap.get(btnId);
			Object obj = cacheService.updCacheData(btn, ConstantVar.DEL_DATA);
			return obj != null;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return false;
		}
	}


	public ButtonInfo getButtonInfoById(String btnId) {
		Object obj = iDAO.getObject(ButtonInfo.class, btnId);
		return obj == null ? null : (ButtonInfo) obj;
	}


	public String getBtnIdsByRoleId(String roleId) {
		StringBuilder sb = new StringBuilder();
		sb.append("select accessValue from PrivilegeInfo where roleId = '");
		sb.append(roleId);
		sb.append("' and access = 1");
		return Util.listToStr(iDAO.findByHql(sb.toString()), ",");
	}

	@SuppressWarnings("unchecked")

	public List<ButtonInfo> queryBtnListByRoleIds(String roleIds) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				"select t1 from ButtonInfo t1,PrivilegeInfo t2 where t1.btnId = t2.accessValue and t2.access = 1 and t2.roleId in (");
		sb.append(Util.strToSqlInStr(roleIds, ","));
		sb.append(")");
		return (List<ButtonInfo>) iDAO.findByHql(sb.toString());
	}

	@SuppressWarnings("unchecked")

	public List<ButtonInfo> queryBtnListByMenuId(String menuId) {
		StringBuilder sb = new StringBuilder();
		sb.append("from ButtonInfo where menuId = '");
		sb.append(menuId);
		sb.append("'");
		return (List<ButtonInfo>) iDAO.findByHql(sb.toString());
	}

	@SuppressWarnings("unchecked")

	public List<ButtonInfo> queryBtnListAll() {
		return (List<ButtonInfo>) iDAO.findAllByClass(ButtonInfo.class);
	}

	@SuppressWarnings("unchecked")

	public List<Object[]> queryButtonInfoGroupByMenuId() {
		return (List<Object[]>) iDAO.findByHql(
				"select btn.menuId, menu.menuName from ButtonInfo btn, MenuInfo menu where btn.menuId=menu.menuId group by btn.menuId");
	}


	public boolean saveMenuInfo(MenuInfo menuInfo) {
		try {
			Object obj = cacheService.updCacheData(menuInfo, ConstantVar.ADD_DATA);
			return obj != null;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return false;
		}
	}


	public boolean updateMenuInfo(MenuInfo menuInfo) {
		try {
			Object obj = cacheService.updCacheData(menuInfo, ConstantVar.UPD_DATA);
			return obj != null;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return false;
		}
	}


	public boolean delMenuInfoById(String menuId) {
		try {
			MenuInfo menuInfo = MenuInfoCache.menuInfoMap.get(menuId);
			Object obj = cacheService.updCacheData(menuInfo, ConstantVar.DEL_DATA);
			return obj != null;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return false;
		}
	}


	public MenuInfo getMenuInfoById(String menuId) {
		Object obj = iDAO.getObject(MenuInfo.class, menuId);
		return obj == null ? null : (MenuInfo) obj;
	}


	public String getMenuIdsByRoleId(String roleId) {
		StringBuilder sb = new StringBuilder();
		sb.append("select accessValue from PrivilegeInfo where roleId = '");
		sb.append(roleId);
		sb.append("' and access = 0");
		return Util.listToStr(iDAO.findByHql(sb.toString()), ",");
	}

	@SuppressWarnings("unchecked")

	public List<MenuInfo> queryParentMenuList() {
		return (List<MenuInfo>) iDAO
				.findByHql("from MenuInfo where parentId is null or parentId = '' order by menuOrder");
	}

	@SuppressWarnings("unchecked")

	public List<MenuInfo> querySubMenuListByParentId(String parentId) {
		StringBuilder sb = new StringBuilder();
		sb.append("from MenuInfo where parentId = '");
		sb.append(parentId);
		sb.append("' order by menuOrder");
		return (List<MenuInfo>) iDAO.findByHql(sb.toString());
	}

	@SuppressWarnings("unchecked")

	public List<MenuInfo> queryMenuListByRoleIds(String roleIds) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				"select t1 from MenuInfo t1,PrivilegeInfo t2 where t1.menuId = t2.accessValue and t2.access = 0 and t2.roleId in (");
		sb.append(Util.strToSqlInStr(roleIds, ","));
		sb.append(") order by t1.menuOrder");
		return (List<MenuInfo>) iDAO.findByHql(sb.toString());
	}


	public List<MenuInfo> queryMenuListAll() {
		return (List<MenuInfo>) iDAO.findByHql("from MenuInfo order by menuOrder");
	}


	public void saveOrUpdatePrvlg(PrivilegeInfo prvlg) {
		iDAO.saveOrUpdate(prvlg);
	}


	public void saveOrUpdatePrvlgList(List<PrivilegeInfo> prvlgList) {
		iDAO.saveOrUpdateAll(prvlgList);
	}


	public void delPrvlgByRoleIds(String roleIds) {
		StringBuilder sb = new StringBuilder();
		sb.append("delete from PrivilegeInfo where roleId in (");
		sb.append(Util.strToSqlInStr(roleIds, ","));
		sb.append(")");
		iDAO.delObectAll(sb.toString());
	}


	public void delPrvlgByAccessValue(String menuOrBtnId) {
		StringBuilder sb = new StringBuilder();
		sb.append("delete from PrivilegeInfo where accessValue = '");
		sb.append(menuOrBtnId);
		sb.append("'");
		iDAO.delObectAll(sb.toString());
	}

	@SuppressWarnings("unchecked")

	public List<PrivilegeInfo> loadPrivilegeList(int menuOrBtn, String roleIds) {
		StringBuilder sb = new StringBuilder();
		sb.append("from PrivilegeInfo where 1=1 ");
		sb.append("and roleId in (");
		sb.append(Util.strToSqlInStr(roleIds, ","));
		sb.append(") ");
		if (menuOrBtn == 0 || menuOrBtn == 1) {
			sb.append(" and access = ");
			sb.append(menuOrBtn);
		}
		return (List<PrivilegeInfo>) iDAO.findByHql(sb.toString());
	}


	public void saveOrUpdateUserOpt(UserOperateInfo userOpt) {
		iDAO.saveOrUpdate(userOpt);
	}

}
