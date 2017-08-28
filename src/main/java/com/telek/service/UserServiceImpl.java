package com.telek.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telek.cache.model.UserInfoCache;
import com.telek.daoImpl.UserDaoImpl;
import com.telek.model.UserEntity;
import com.telek.util.ConstantVar;
import com.telek.util.HttpUtil;
import com.telek.util.Util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("UserServiceImpl")
public class UserServiceImpl implements IUserService {
	
//	private static final Logger LOG = Logger.getLogger(PrvlgService.class);
	
	
	@Autowired
	private UserDaoImpl userDaoImpl;

	// @Transactional
	public boolean save(UserEntity entity) {
		return userDaoImpl.save(entity);
	}

	public UserEntity get(int id) {
		String str = "from UserEntity where id = "+id;
		List<UserEntity> list = (List<UserEntity>) userDaoImpl.findByHql(str);
		if (list.size() == 0) {
			return null;
		}
		return list.get(0);
	}
	
	public String getUserIconPath(int id) {
		String str = "from UserEntity where id ="+id;
		List<UserEntity> list = (List<UserEntity>) userDaoImpl.findByHql(str);
		if (list.size() == 0) {
			return "";
		}
		String userImgPath ;
		
		return list.get(0).getUserIconPath();
	}
	

	public String userLogin(UserEntity mUser,HttpServletRequest request) {
		
		
		// 登录标识
		boolean flag = false;
		JSONObject backJson = new JSONObject();
		// 遍历用户缓存，查找账号密码匹配的用户
		for (Map.Entry<String, UserEntity> entry : UserInfoCache.userInfoMap.entrySet()) {
			UserEntity user = entry.getValue();
			// 账号密码匹配
			System.out.println("从表中取出了用户"+user);
			if (user.getId() == user.getId()) {
				// 获取用户登录请求的session
				HttpSession session = request.getSession();
				// 将用户对象存入session
				session.setAttribute("loginUser", user);

				// 查找用户信息及相应权限
				backJson.put("result", "successed");
				backJson.put("loginUserId", user.getId());
				backJson.put("loginUserName", user.getName());
				backJson.put("loginUserNickName", user.getNickName());
				// 将该用户所拥有的权限放入session中，供前台拼接成一颗树
//				String jsonarray = queryMenuInfoListByUserId(user.getId()).replaceAll("\"", "#");
//				backJson.put("userOwnMenuPrvlg", jsonarray);

				// 查找用户信息及相应权限
//				JSONObject prvlgJson = loadPrvlgByUser(user.getUserId());
				// 用户菜单及操作权限存入session(页面，供后台过滤)
//				session.setAttribute("prvlgMenuBtnJsp",
//						prvlgJson.getString("prvlgMenuJsp") + prvlgJson.getString("prvlgBtnJsp"));

				// 用户拥有的按钮权限
//				backJson.put("prvlgUserOwnBtn", queryUserOwnBtn(user.getUserId()).replaceAll("\"", "#"));

				// 登录成功
				flag = true;
				// 用户操作中将用户登录记录 add by scc 20161011
//				prvlgDAO.saveOrUpdateUserOpt(
//						new UserOperateInfo("", "登入", userAccount, new Date(), Util.getRemortIP(request)));
				// 跳出循环
				break;
			}
		}
		
		// 组装格式向云端请求用户数据
		JSONObject obj = new JSONObject();
		obj.put("hemsUserAccount", "15958032222");
		obj.put("hemsUserPWD", "111111");
		obj.put("editType", ConstantVar.SAVE_OR_UPDATE);
		JSONArray toSynchUserArr = new JSONArray();
		toSynchUserArr.add(obj);
		String result = synchHemsUser(toSynchUserArr);

//		LOG.info("【userLogin】用户登录：" + userAccount + (flag ? "成功" : "账号或密码错误！"));
		if (!flag) {
			backJson.put(ConstantVar.RESULT, ConstantVar.FAILED);
			backJson.put(ConstantVar.DESC, "账号或密码错误！");
		}
		return backJson.toString();
	}
	
	/**
	 * 同步云端赫马用户信息
	 * 
	 * @author zhanxf
	 * @date 2016年2月24日 下午1:54:37
	 * @param obj
	 *            直接传json对象是为了以后可扩展性考虑
	 * @return
	 */
	public String synchHemsUser(JSONArray arr) {
		JSONObject obj = new JSONObject();
		JSONObject paramObj = new JSONObject();
		paramObj.put("accountList", arr);
		obj.put(ConstantVar.METHOD_NAME, "webSynchHemsUser");
		obj.put(ConstantVar.PARAMETER, paramObj);
		String result = HttpUtil.doPost(obj);
		result = Util.checkCloudResponseValid(result);
		return result;
	}
	

	
}
