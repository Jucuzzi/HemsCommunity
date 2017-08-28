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
 * 按钮操作信息
 * 
 * @author Xugl
 * 
 * @date 2016-2-2 下午2:47:01
 */
@Entity
@Table(name = "t_buttoninfo")
public class ButtonInfo extends BaseModel {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name = "btn_id", length = 32)
	private String btnId;// 按钮标识

	@Column(name = "btn_name", length = 50)
	private String btnName;// 按钮名称

	@Column(name = "btn_alias", length = 20)
	private String btnAlias;// 操作别名

	@Column(name = "btn_url", nullable = true, length = 250)
	private String btnUrl;// 操作url，用于拥有专属界面的操作

	@Column(name = "menu_id", length = 32)
	private String menuId;// 所属菜单

	@Column(name = "btn_method", length = 50)
	private String btnMethod;// 绑定js方法名

	public String getBtnId() {
		return btnId;
	}

	public void setBtnId(String btnId) {
		this.btnId = btnId;
	}

	public String getBtnName() {
		return btnName;
	}

	public void setBtnName(String btnName) {
		this.btnName = btnName;
	}

	public String getBtnAlias() {
		return btnAlias;
	}

	public void setBtnAlias(String btnAlias) {
		this.btnAlias = btnAlias;
	}

	public String getBtnUrl() {
		return btnUrl;
	}

	public void setBtnUrl(String btnUrl) {
		this.btnUrl = btnUrl;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getBtnMethod() {
		return btnMethod;
	}

	public void setBtnMethod(String btnMethod) {
		this.btnMethod = btnMethod;
	}

	public ButtonInfo() {
		super();
	}

	public ButtonInfo(String btnName, String btnAlias, String btnUrl, String menuId, String btnMehtod) {
		super();
		this.btnName = btnName;
		this.btnAlias = btnAlias;
		this.btnUrl = btnUrl;
		this.menuId = menuId;
		this.btnMethod = btnMehtod;
	}

	public ButtonInfo(String btnId, String btnName, String btnAlias, String btnUrl, String menuId, String btnMehtod) {
		super();
		this.btnId = btnId;
		this.btnName = btnName;
		this.btnAlias = btnAlias;
		this.btnUrl = btnUrl;
		this.menuId = menuId;
		this.btnMethod = btnMehtod;
	}

	public JSONObject getJsonObj() {
		JSONObject obj = new JSONObject();
		obj.put("btnId", this.btnId);
		obj.put("btnName", this.btnName);
		obj.put("btnAlias", this.btnAlias == null ? "" : this.btnAlias);
		obj.put("btnUrl", this.btnUrl == null ? "" : this.btnUrl);
		obj.put("menuId", this.menuId);
		obj.put("btnMethod", this.btnMethod == null ? "" : this.btnMethod);
		return obj;
	}

	@Override
	public String toString() {
		return "ButtonInfo [btnId=" + btnId + ", btnName=" + btnName + ", btnAlias=" + btnAlias + ", btnUrl=" + btnUrl
				+ ", menuId=" + menuId + ", btnMethod=" + btnMethod + "]";
	}

}
