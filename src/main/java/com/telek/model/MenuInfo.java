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

import net.sf.json.JSONObject;

/**
 * 菜单信息
 * 
 * @author Xugl
 * 
 * @date 2016-2-2 下午2:51:56
 */
@Entity
@Table(name = "t_menuinfo")
public class MenuInfo extends BaseModel {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name = "menu_id", length = 32)
	private String menuId;// 菜单标识

	@Column(name = "menu_name", length = 50)
	private String menuName;// 菜单名称

	@Column(name = "menu_url", length = 250)
	private String menuUrl;// 菜单路径

	@Column(name = "parent_id", nullable = true, length = 32)
	private String parentId;// 上级菜单

	@Column(name = "menu_order", length = 5)
	private int menuOrder;// 排序

	@Column(name = "menu_gourl", length = 250)
	private String menuGourl;// 跳转链接

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public int getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(int menuOrder) {
		this.menuOrder = menuOrder;
	}

	public String getMenuGourl() {
		return menuGourl;
	}

	public void setMenuGourl(String menuGourl) {
		this.menuGourl = menuGourl;
	}

	public MenuInfo() {
		super();
	}

	public MenuInfo(String menuName, String menuUrl, String parentId, Integer menuOrder, String menuGourl) {
		super();
		this.menuName = menuName;
		this.menuUrl = menuUrl;
		this.parentId = parentId;
		this.menuOrder = menuOrder;
		this.menuGourl = menuGourl;
	}

	public MenuInfo(String menuId, String menuName, String menuUrl, String parentId, Integer menuOrder,
			String menuGourl) {
		super();
		this.menuId = menuId;
		this.menuName = menuName;
		this.menuUrl = menuUrl;
		this.parentId = parentId;
		this.menuOrder = menuOrder;
		this.menuGourl = menuGourl;
	}

	@Override
	public String toString() {
		return "MenuInfo [menuId=" + menuId + ", menuName=" + menuName + ", menuUrl=" + menuUrl + ", parentId="
				+ parentId + ", menuOrder=" + menuOrder + ", menuGourl=" + menuGourl + "]";
	}

	public JSONObject getJsonObj() {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("menuId", this.menuId);
		jsonObj.put("menuName", this.menuName);
		jsonObj.put("menuUrl", this.menuUrl);
		jsonObj.put("parentId", this.parentId);
		jsonObj.put("menuOrder", this.menuOrder);
		jsonObj.put("menuGourl", this.menuGourl);
		return jsonObj;
	}

}
