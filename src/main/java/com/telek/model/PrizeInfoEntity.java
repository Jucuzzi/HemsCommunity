/**
 * 
 */
package com.telek.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import net.sf.json.JSONObject;

/**
 * 奖品信息
 * 
 * @author Xugl
 * 
 * @date 2016-2-2 下午3:13:34
 */
@Entity
@Table(name = "t_prizeInfo")
public class PrizeInfoEntity extends BaseModel {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)  
    @Column(name = "prize_id", nullable = false) 
	public int prizeId;// 奖品标识

	@Column(name = "prize_name", length = 50)
	public String prizeName;// 奖品名称

	@Column(name = "prize_allCount", nullable = true, length = 250)
	public String prizeAllCount;// 奖品总量
	
	@Column(name = "prize_balanceCount", nullable = true, length = 250)
	public String prizeBalanceCount;// 奖品余量


	public PrizeInfoEntity() {
		super();
	}

	public int getPrizeId() {
		return prizeId;
	}

	public void setPrizeId(int prizeId) {
		this.prizeId = prizeId;
	}

	public String getPrizeName() {
		return prizeName;
	}

	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}

	public String getPrizeAllCount() {
		return prizeAllCount;
	}

	public void setPrizeAllCount(String prizeAllCount) {
		this.prizeAllCount = prizeAllCount;
	}

	public String getPrizeBalanceCount() {
		return prizeBalanceCount;
	}

	public void setPrizeBalanceCount(String prizeBalanceCount) {
		this.prizeBalanceCount = prizeBalanceCount;
	}

	@Override
	public String toString() {
		return "PrizeInfo [prizeId=" + prizeId + ", prizeName=" + prizeName + ", prizeAllCount=" + prizeAllCount + ", prizeBalanceCount="
				+ prizeBalanceCount + "]";
	}

	public JSONObject getJsonObj() {
		JSONObject obj = new JSONObject();
		obj.put("prizeId", prizeId);
		obj.put("prizeName", prizeName);
		obj.put("prizeAllCount", prizeAllCount);
		obj.put("prizeBalanceCount", prizeBalanceCount);
		return obj;
	}

}
