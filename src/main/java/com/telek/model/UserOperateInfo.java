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

/**
 * 
 * @author Wanghj
 *
 * @date 2016-8-8 下午4:17:37
 */
@Entity
@Table(name = "t_user_operate")
public class UserOperateInfo implements Cloneable {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name = "uo_id", length = 32)
	private String uoid;// 主键

	@Column(name = "uo_btn")
	private String uobtn;// 按钮名

	@Column(name = "uo_operate")
	private String uooperate;// 操作名称

	@Column(name = "uo_user")
	private String uouser;// 用户名

	@Column(name = "uo_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date uodate;// 创建时间

	@Column(name = "uo_ip")
	private String uoip;// IP地址
	
	public String getUoid() {
		return uoid;
	}

	public void setUoid(String uoid) {
		this.uoid = uoid;
	}

	public String getUobtn() {
		return uobtn;
	}

	public void setUobtn(String uobtn) {
		this.uobtn = uobtn;
	}

	public String getUooperate() {
		return uooperate;
	}

	public void setUooperate(String uooperate) {
		this.uooperate = uooperate;
	}

	public String getUouser() {
		return uouser;
	}

	public void setUouser(String uouser) {
		this.uouser = uouser;
	}

	public Date getUodate() {
		return uodate;
	}

	public void setUodate(Date uodate) {
		this.uodate = uodate;
	}

	public String getUoip() {
		return uoip;
	}

	public void setUoip(String uoip) {
		this.uoip = uoip;
	}

	@Override
	public String toString() {
		return "UserOperateInfo [uoid=" + uoid + ", uobtn=" + uobtn
				+ ", uooperate=" + uooperate + ", uouser=" + uouser
				+ ", uodate=" + uodate + ", uoip=" + uoip + "]";
	}

	@Override
	public UserOperateInfo clone() {
		try {
			return (UserOperateInfo) super.clone();
		} catch (CloneNotSupportedException e) {

			e.printStackTrace();
		}
		return null;
	}
	
	public UserOperateInfo() {
		
	}

	public UserOperateInfo(String uobtn, String uooperate,
			String uouser, Date uodate, String uoip) {
		super();
		this.uobtn = uobtn;
		this.uooperate = uooperate;
		this.uouser = uouser;
		this.uodate = uodate;
		this.uoip = uoip;
	}

	

}
