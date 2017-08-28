package com.telek.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import net.sf.json.JSONObject;

/**
 * 兑换券列表信息
 * 
 * @author Xugl
 * 
 * @date 2016-2-2 下午3:13:34
 */
@Entity
@Table(name = "t_exchangeInfo")

public class ExchangeEntity extends BaseModel {


	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)  
    @Column(name = "exchange_id", nullable = false) 
	public int exchangeId;// 兑换券标识

	@Column(name = "prize_name", length = 50)
	public String prizeName;// 奖品名称

	@Column(name = "prize_id", nullable = true)
	public int prizeId;// 奖品标志
	
	@Column(name = "exchange_number", nullable = true, length = 250)
	public String exchangeNUmber;// 兑换码
	
	@Column(name = "exchange_isExchange", nullable = true, length = 250)
	public String exchangeIsExchange;// 是否已兑换 isExchange
	
//	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "exchange_date")
	public String exchangeDate;// 兑换时间


	public ExchangeEntity() {
		super();
	}

	public int getExchangeId() {
		return exchangeId;
	}

	public void setExchangeId(int exchangeId) {
		this.exchangeId = exchangeId;
	}

	public String getPrizeName() {
		return prizeName;
	}

	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}

	public int getPrizeId() {
		return prizeId;
	}

	public void setPrizeId(int prizeId) {
		this.prizeId = prizeId;
	}

	public String getExchangeNUmber() {
		return exchangeNUmber;
	}

	public void setExchangeNUmber(String exchangeNUmber) {
		this.exchangeNUmber = exchangeNUmber;
	}
	
	public String getExchangeIsExchange() {
		return exchangeIsExchange;
	}

	public void setExchangeIsExchange(String exchangeIsExchange) {
		this.exchangeIsExchange = exchangeIsExchange;
	}
	
	public String getExchangeDate() {
		return exchangeDate;
	}

	public void setCreateDate(String exchangeDate) {
		this.exchangeDate = exchangeDate;
	}

	@Override
	public String toString() {
		return "PrizeInfo [prizeId=" + prizeId + ", prizeName=" + prizeName + ", exchangeId=" + exchangeId + ", exchangeNUmber="
				+ exchangeNUmber + ", exchangeDate=" + exchangeDate + ", exchangeIsExchange=" + exchangeIsExchange + "]";
	}

	public JSONObject getJsonObj() {
		JSONObject obj = new JSONObject();
		obj.put("exchangeId", exchangeId);
		obj.put("prizeId", prizeId);
		obj.put("prizeName", prizeName);
		obj.put("exchangeNUmber", exchangeNUmber);
		obj.put("exchangeIsExchange", exchangeIsExchange);
		obj.put("exchangeDate", exchangeDate);
		return obj;
	}

}
