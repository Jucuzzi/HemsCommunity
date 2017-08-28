/**
 * 
 */
package com.telek.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 * 用户角色关系信息
 * 
 * @author Xugl
 *
 * @date 2016-2-2 下午3:16:20
 */
@Entity
@Table(name = "t_userrole_rel")
public class UserRoleRel {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name = "ur_id", length = 32)
	private String urId;// 用户角色关系标识
	
	@Column(name = "user_id", length = 32)
	private String userId;// 用户标识
	
	@Column(name = "role_id", length = 32)
	private String roleId;// 角色标识

	public UserRoleRel() {
	}

	public String getUrId() {
		return urId;
	}

	public void setUrId(String urId) {
		this.urId = urId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "UserRoleRel : [urId : " + urId + ",userId : " + userId
				+ ",roleId : " + roleId + "]";
	}

}
