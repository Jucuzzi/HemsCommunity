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
 * 系统权限信息
 * 
 * @author Xugl
 * 
 * @date 2016-2-2 下午2:59:03
 */
@Entity
@Table(name = "t_privilegeinfo")
public class PrivilegeInfo {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name = "privilege_id", length = 32)
	private String privilegeId;// 系统权限标识

	@Column(name = "role_id", length = 32)
	private String roleId;// 主体标识，roleId

	@Column(name = "access", length = 1)
	private int access;// 权限类别，0-menu,1-button

	@Column(name = "access_value", length = 32)
	private String accessValue;// 权限标识，menuId/buttonId

	public String getPrivilegeId() {
		return privilegeId;
	}

	public void setPrivilegeId(String privilegeId) {
		this.privilegeId = privilegeId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public int getAccess() {
		return access;
	}

	public void setAccess(int access) {
		this.access = access;
	}

	public String getAccessValue() {
		return accessValue;
	}

	public void setAccessValue(String accessValue) {
		this.accessValue = accessValue;
	}

	@Override
	public String toString() {
		return "PrivilegeInfo [privilegeId=" + privilegeId + ", roleId="
				+ roleId + ", access=" + access + ", accessValue="
				+ accessValue + "]";
	}

	public PrivilegeInfo() {
		super();
	}

	public PrivilegeInfo(String roleId, int access, String accessValue) {
		super();
		this.roleId = roleId;
		this.access = access;
		this.accessValue = accessValue;
	}

	public PrivilegeInfo(String privilegeId, String roleId, int access,
			String accessValue) {
		super();
		this.privilegeId = privilegeId;
		this.roleId = roleId;
		this.access = access;
		this.accessValue = accessValue;
	}

}
