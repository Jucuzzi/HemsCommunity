/**
 * 
 */
package com.telek.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.telek.util.Util;

import net.sf.json.JSONObject;

/**
 * 角色信息
 * 
 * @author Xugl
 * 
 * @date 2016-2-2 下午3:13:34
 */
@Entity
@Table(name = "t_roleinfo")
public class RoleInfo extends BaseModel {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name = "role_id", length = 32)
	private String roleId;// 角色标识

	@Column(name = "role_name", length = 50)
	private String roleName;// 角色名称

	@Column(name = "role_desc", nullable = true, length = 250)
	private String roleDesc;// 角色描述

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Date createDate;// 创建时间

	public RoleInfo() {
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "RoleInfo [roleId=" + roleId + ", roleName=" + roleName + ", roleDesc=" + roleDesc + ", createDate="
				+ createDate + "]";
	}

	public JSONObject getJsonObj() {
		JSONObject obj = new JSONObject();
		obj.put("roleId", roleId);
		obj.put("roleName", roleName);
		obj.put("roleDesc", roleDesc);
		obj.put("createDate", Util.formatDate(createDate, "yyyy-MM-dd"));
		return obj;
	}

}
