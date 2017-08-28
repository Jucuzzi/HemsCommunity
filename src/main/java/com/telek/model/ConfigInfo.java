package com.telek.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 系统配置相关信息
 * 
 * @author Xugl
 * 
 * @date 2017-3-14 下午3:43:24
 */
@Entity
@Table(name = "t_configinfo")
public class ConfigInfo extends BaseModel {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name = "config_name", length = 32)
	private String configName;// 配置名称

	@Column(name = "config_value")
	private String configValue; // 配置值

	@Column(name = "config_desc")
	private String configDesc; // 配置描述

	public ConfigInfo() {

	}

	public String getConfigName() {
		return configName;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
	}

	public String getConfigValue() {
		return configValue;
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}

	public String getConfigDesc() {
		return configDesc;
	}

	public void setConfigDesc(String configDesc) {
		this.configDesc = configDesc;
	}
}
