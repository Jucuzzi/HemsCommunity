/**
 * 
 */
package com.telek.util;

import java.text.SimpleDateFormat;

/**
 * 系统常量
 * 
 * @author Xugl
 * 
 * @date 2017-3-14 下午2:02:51
 */
public class ConstantVar {
	/**
	 * WEB系统标识
	 */
	public final static String WEB_ID_STR = "webId";
	/**
	 * 数据库版本
	 */
	public static final String DB_VERSION_STR = "dbVersion";
	/**
	 * 系统webid
	 */
	public final static String WEB_ID_NUM = "webIdNum";

	/**
	 * 纬度，N-北纬，S-南纬
	 */
	public final static String LATITUDE = "latitude";

	/**
	 * 经度，E-东经，W-西经
	 */
	public final static String LONGITUDE = "longitude";
	/**
	 * 蓄电池备用储能比率
	 */
	public final static String RESERVE_BATTERY_RATE = "reserveBatteryRate";
	/**
	 * 蓄冷罐备用储能比率
	 */
	public final static String RESERVE_COLD_RATE = "reserveColdRate";
	/**
	 * 蓄热罐备用储能比率
	 */
	public final static String RESERVE_HOT_RATE = "reserveHotRate";
	
	/**
	 * 天然气价格
	 */
	public final static String GAS_PRICE = "gasPrice";

	/**
	 * MSGID标识
	 */
	public final static String WEB_ID_MSG = "msgId";
	/**
	 * HMAC摘要
	 */
	public final static String WEB_ID_HMAC = "hmac";
	/**
	 * 动态密码
	 */
	public static final String DYN_PASSWORD = "dynPassword";
	/**
	 * 电压等级
	 */
	public static final String VOLTAGE_LEVEL = "voltageLevel";
	/**
	 * 最大可接入负荷
	 */
	public static final String MAX_ACCESS_LOAD = "maxAccessLoad";
	/**
	 * 装机容量
	 */
	public static final String CLEAN_ENERGE_CAPACITY = "cleanEnergyCapacity";
	/**
	 * 离并网状态 1-离 0-并
	 */
	public static final String EX_GRID_STATE = "exGridState";
	/**
	 * 动态密码
	 */
	public static final String SUPER_ROLEID = "superRoleId";
	/**
	 * 动态密码生成源
	 */
	public static final String PASSWORD_SOURCE = "0123456789abcdef";
	/**
	 * 允许交换的时段
	 */
	public static final String SWAP_TIME = "swapTime";
	/**
	 * 购电上限
	 */
	public static final String EX_BUY_LIMIT = "exBuyLimit";
	/**
	 * 售电上限
	 */
	public static final String EX_SALE_LIMIT = "exSaleLimit";

	/**
	 * 集合总数
	 */
	public final static String TOTAL = "total";
	/**
	 * 集合列表
	 */
	public final static String ROOT = "root";
	/**
	 * 返回结果
	 */
	public final static String RESULT = "result";
	/**
	 * 返回描述
	 */
	public final static String DESC = "desc";
	/**
	 * 成功
	 */
	public final static String SUCCESSED = "successed";
	/**
	 * 失败
	 */
	public final static String FAILED = "failed";
	/**
	 * 成功
	 */
	public final static String SUCCESS = "success";
	/**
	 * 失败
	 */
	public final static String FAIL = "fail";
	public final static String ERROR = "error";
	/**
	 * 返回成功
	 */
	public final static String SUCCESSED_RESULT = "{\"result\":\"successed\"}";

	/**
	 * 返回失败
	 */
	public final static String FAILED_RESULT = "{\"result\":\"failed\"}";

	/**
	 * 数据有误
	 */
	public final static String DATA_ERROR = "{\"result\":\"failed\",\"desc\":\"数据有误\"}";

	/**
	 * 网络异常
	 */
	public final static String NET_ERROR = "{\"result\":\"failed\",\"desc\":\"网络异常\"}";

	/**
	 * 网络异常
	 */
	public final static String SYSTEM_ERROR = "{\"result\":\"failed\",\"desc\":\"系统异常,请联系管理员\"}";
	/**
	 * 数据为空
	 */
	public final static String NULL_ERROR = "{\"result\":\"failed\",\"desc\":\"暂无数据\"}";

	public static final String CHECK_INPUT_FAILED = "{\"result\":\"failed\",\"desc\":\"请求包含非法字符的输入字符\"}";

	/**
	 * 加解密异常
	 */
	public final static String DECRYPT_ERROR = "{\"result\":\"failed\",\"desc\":\"数据有误\"}";
	
	/**
	 * 请先删除用户
	 */
	public final static String USER_USEING__ERROR = "{\"result\":\"failed\",\"desc\":\"请先删除用户\"}";

	/**
	 * 新增或修改
	 */
	public final static String SAVE_OR_UPDATE = "1";
	/**
	 * 删除
	 */
	public final static String DELETE = "0";

	/**
	 * 方法名
	 */
	public final static String METHOD_NAME = "methodName";
	/**
	 * 参数内容
	 */
	public final static String PARAMETER = "parameter";

	/**
	 * 光伏发电设备
	 */
	public final static String APPTYPE_OF_PHOTOVOLTAICPOWER = "1001135";
	/**
	 * 风力发电设备
	 */
	public final static String APPTYPE_OF_WINDPOWER = "1001136";
	/**
	 * 燃机发电设备
	 */
	public final static String APPTYPE_OF_GASTURBINEPOWER = "1001137";
	/**
	 * 储能蓄电池
	 */
	public final static String APPTYPE_OF_BATTERY = "1001138";
	/**
	 * 充电桩
	 */
	public final static String APPTYPE_OF_CHARGINGPILE = "1001139";
	/**
	 * 微电网常规负荷
	 */
	public final static String APPTYPE_OF_CONVENTIONALLOAD = "1001140";
	/**
	 * 微电网敏感负荷
	 */
	public final static String APPTYPE_OF_SENSITIVELOAD = "1001141";

	/**
	 * 外网负荷
	 */
	public final static String APPTYPE_OF_EXTERNALPOWERGRID = "1001100";
	/**
	 * 微网电消纳负荷
	 */
	public final static String APPTYPE_OF_MGUSELOAD = "1001101";
	/**
	 * 微网电供应负荷
	 */
	public final static String APPTYPE_OF_MGPRODUCELOAD = "1001102";
	/**
	 * 微网供应负荷(不包括蓄电池，即风电、光电、燃机的总和)
	 */
	public final static String APPTYPE_OF_MGPRODUCELOAD_ONLY = "1001103";
	/**
	 * 微网消纳负荷(不包括蓄电池，即敏感负荷和常规负荷的总和)
	 */
	public final static String APPTYPE_OF_MGUSELOAD_ONLY = "1001104";
	/**
	 * 微网热消纳负荷
	 */
	public final static String APPTYPE_OF_HOTUSELOAD = "1001105";
	/**
	 * 微网热供应负荷
	 */
	public final static String APPTYPE_OF_HOTPRODUCELOAD = "1001106";
	/**
	 * 微网冷消纳负荷
	 */
	public final static String APPTYPE_OF_COLDUSELOAD = "1001107";
	/**
	 * 微网冷供应负荷
	 */
	public final static String APPTYPE_OF_COLDPRODUCELOAD = "1001108";

	/**
	 * 储能蓄热罐
	 */
	public final static String APPTYPE_OF_HOT_STORAGE = "1001142";
	/**
	 * 储能蓄冷罐
	 */
	public final static String APPTYPE_OF_COOL_STORAGE = "1001143";
	/**
	 * 热负荷
	 */
	public final static String APPTYPE_OF_HOT_LOAD = "1001144";
	/**
	 * 冷负荷
	 */
	public final static String APPTYPE_OF_COOL_LOAD = "1001145";
	/**
	 * 微电网电缺口
	 */
	public final static String APPTYPE_OF_POWER_VIRTUAL = "1001146";
	/**
	 * 微电网热缺口
	 */
	public final static String APPTYPE_OF_HOT_VIRTUAL = "1001147";
	/**
	 * 微电网冷缺口
	 */
	public final static String APPTYPE_OF_COOL_VIRTUAL = "1001148";
	/**
	 * 微电网电丢弃设备
	 */
	public final static String APPTYPE_OF_POWER_DISCARD = "1001149";
	/**
	 * 微电网热丢弃设备
	 */
	public final static String APPTYPE_OF_HOT_DISCARD = "1001150";
	/**
	 * 微电网冷丢弃设备
	 */
	public final static String APPTYPE_OF_COOL_DISCARD = "1001151";
	
	/**
	 * 外网购电设备id
	 */
	public final static String POWER_VIRTUAL_APPID = "TZ1703160000004520170208185200";
	
	/**
	 * 外网售电设备id
	 */
	public final static String POWER_DISCARD_APPID = "TZ1703160000004820170208185200";
	
	
	/**
	 * 外网峰谷电价虚拟设备id
	 */
	public final static String SETP_POWER_APPID = "TZ1703160000005120170208185200";
	
	/**
	 * 燃气成本虚拟设备id
	 */
	public final static String GAS_POWER_APPID = "TZ1703160000005720170208185200";
	/**
	 * 售电给居民价格虚拟设备id
	 */
	public final static String RESIDENT_POWER_APPID = "TZ1703160000005520170208185200";
	/**
	 * 外网售电价格虚拟设备id
	 */
	public final static String SALE_POWER_APPID = "TZ1703160000005320170208185200";
	
	/**
	 * 电能质量检测仪设备id
	 */
	public final static String E_QUALITY_APPID = "TZ1703160000005920170208185200";




	/**
	 * 燃机发电设备_冷(仅用于实时负荷)
	 */
	public final static String APPTYPE_OF_GAS_COLD = "1001137_2";
	/**
	 * 燃机发电设备_热(仅用于实时负荷)
	 */
	public final static String APPTYPE_OF_GAS_HOT = "1001137_3";
	/**
	 * 燃机发电设备_电(仅用于实时负荷)
	 */
	public final static String APPTYPE_OF_GAS_POWER = "1001137_1";

	/**************************** 常用时间毫秒数 开始 **********************************/
	/**
	 * 一天的毫秒数
	 */
	public static final long MS_OF_DAY = 1000 * 60 * 60 * 24;

	/**
	 * 15分钟的毫秒数
	 */
	public static final int MS_OF_15MINUTE = 1000 * 60 * 15;
	/**
	 * 一小时的毫秒数
	 */
	public static final int MS_OF_HOUR = 1000 * 60 * 60;

	/**
	 * 每分钟的毫秒数
	 */
	public static final int MS_OF_MINUTE = 1000 * 60;

	/**
	 * 30分钟的毫秒数
	 */
	public static final int MS_OF_30MINUTE = 1000 * 60 * 30;

	/**************************** 常用时间毫秒数 结束 **********************************/

	/**
	 * 发电系统
	 */
	public final static int GENERATOR_SYSTEM = 1;
	/**
	 * 储能系统
	 */
	public final static int BATTERY_SYSTEM = 2;
	/**
	 * 充电桩系统
	 */
	public final static int EV_SYSTEM = 3;

	/**
	 * 自动控制
	 */
	public final static String AUTO = "0";
	/**
	 * 手动控制
	 */
	public final static String MANUAL = "1";
	/**
	 * 不可控制
	 */
	public final static String UNCONTROL = "2";

	/**
	 * 蓄电池状态-未工作
	 */
	public final static int UNWORK = 0;
	/**
	 * 蓄电池状态-充电
	 */
	public final static int INPUT_POWER = 2;
	/**
	 * 蓄电池状态-放电
	 */
	public final static int OUTPUT_POWER = 1;

	/**
	 * 关闭指令
	 */
	public final static String CLOSE_COMMAND = "0A-1A-1A-1A-1A-1A-1A-1A-1A-1A-1A-1A-1A-1A-1";

	public final static SimpleDateFormat DAY_SDF = new SimpleDateFormat("yyyy-MM-dd");
	public final static SimpleDateFormat _DAY_SDF = new SimpleDateFormat("yyyyMMdd");
	public final static SimpleDateFormat SECOND_SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public final static SimpleDateFormat YEARY_SDF = new SimpleDateFormat("yyyy");
	public final static SimpleDateFormat MINUTE_SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public final static SimpleDateFormat _SECOND_SDF = new SimpleDateFormat("yyyyMMddHHmmss");
	public final static String TIME_HOUR = "yyyy-MM-dd HH";
	public final static String TIME_DAY = "yyyy-MM-dd";
	public final static String Month_DAY = "yyyy-MM";
	public final static String YEAR_DAY = "yyyy";

	/**
	 * 网络异常
	 */
	public static final String APP_ERROR = "11";
	/**
	 * 三相失衡
	 */
	public static final String APP_UNBALANCE = "13";
	
	/**
	 * 维护中
	 */
	public static final String APP_MAINTENANCE = "14";
	/**
	 * 电压异常波动
	 */
	public static final String APP_UNVOLTAGE = "15";
	/**
	 * 频率波动异常
	 */
	public static final String APP_UNFREQUENCY = "16";

	/**
	 * 二级验证密码 telek7890-
	 */
	public static final String PASSWORD_TWO = "2ab70f409de4624a3403b8ecb10310b2";

	/**
	 * 新增数据
	 */
	public static final int ADD_DATA = 1;
	/**
	 * 更新数据
	 */
	public static final int UPD_DATA = 2;
	/**
	 * 删除数据
	 */
	public static final int DEL_DATA = 3;
	/**
	 * 通知进行数据传输
	 */
	public static final int NOTIFY_DATA = 4;
	/**
	 * 通知进行数据赋值
	 */
	public static final int INIT_DATA = 5;
}
