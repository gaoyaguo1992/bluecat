
package com.stylefeng.guns.sharecore.modular.system.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stylefeng.guns.sharecore.common.persistence.model.BaseObject;

/**
 * 
 *
 */
public class DeviceTotalBO extends BaseObject  {
	private static final long serialVersionUID = 1756718642307498143L;
	private Map<String,Integer> devByTypeInfo = new HashMap<String,Integer>();
	private int totalCount;
	
	/**
	 * 加盟商为店铺的商户ID
	 */
	private Long merchantId;
	
	/**
	 * 加盟商ID
	 */
	private Long franchiseeId;
	
	
	/**
	 * 代理商ID
	 */
	private Long agentsId;
	
	/**
	 * 一级代理商编号
	 */
	private Long firstLevelAgentsId;
	
	/**
	 * 二级代理商编号
	 */
	private Long secondLevelAgentsId;
	
	/**
	 * 铺货商ID
	 */
	private Long shopKeeperId;

	
	/**
	 * 铺货商子公司ID
	 */
	private Long shopKeeperSubId;
	
	/**
	 * 铺货商地推团队ID
	 */
	private Long shopKeeperOperatorId;
	
	/**
	 * 铺货商中介机构ID
	 */
	private Long shopKeeperAgencyId;
	
	private Integer todayAllCount;
	
	private BigDecimal todayAllAmount;
	
	private Integer hisAllCount;
	
	private BigDecimal hisAllAmount;
	
	private Integer startIndex;
	
	private Integer rows;
	
	private Integer devType;
	
	// 经营模式
	private Integer manageModelType;

	private Long deviceId;
	
	/**
	 * 根据开始时间查询交易明细
	 */
	private String beginDateTime;

	/**
	 * 根据结束时间查询交易明细
	 */
	private String endDateTime;
	
	private String merchantType;
	
	private Long queryMerchantId;
	
	private String beginCreateTime;
	
	private String endCreateTime;
	
	private List<Integer> deviceTypes;
	
	
	/**
	 * 在线状态
	 */
	private Integer onlineStatus;
	
	/**
	 * 激活状态
	 */
	private Integer deviceStatus;
	
	/**
	 * 最近一次在线时间
	 */
	private String onlineTime;
	
	/**
	 * 设备状态
	 */
	private String status;
	/**
	 * 绑定的所有充电器...
	 */
	private String chargers;
	
	
	public String getChargers() {
		return chargers;
	}

	public void setChargers(String chargers) {
		this.chargers = chargers;
	}

	public String getOnlineTime() {
		return onlineTime;
	}

	public void setOnlineTime(String onlineTime) {
		this.onlineTime = onlineTime;
	}

	public Integer getOnlineStatus() {
		return onlineStatus;
	}

	public void setOnlineStatus(Integer onlineStatus) {
		this.onlineStatus = onlineStatus;
	}

	public Integer getDeviceStatus() {
		return deviceStatus;
	}

	public void setDeviceStatus(Integer deviceStatus) {
		this.deviceStatus = deviceStatus;
	}

	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	public Map<String, Integer> getDevByTypeInfo() {
		return devByTypeInfo;
	}

	public void setDevByTypeInfo(Map<String, Integer> devByTypeInfo) {
		this.devByTypeInfo = devByTypeInfo;
	}


	public int getTotalCount() {
		return totalCount;
	}


	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}


	public Long getMerchantId() {
		return merchantId;
	}


	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}


	public Long getFranchiseeId() {
		return franchiseeId;
	}


	public void setFranchiseeId(Long franchiseeId) {
		this.franchiseeId = franchiseeId;
	}


	public Long getAgentsId() {
		return agentsId;
	}


	public void setAgentsId(Long agentsId) {
		this.agentsId = agentsId;
	}


	public Long getShopKeeperId() {
		return shopKeeperId;
	}


	public void setShopKeeperId(Long shopKeeperId) {
		this.shopKeeperId = shopKeeperId;
	}


	public Long getShopKeeperSubId() {
		return shopKeeperSubId;
	}


	public void setShopKeeperSubId(Long shopKeeperSubId) {
		this.shopKeeperSubId = shopKeeperSubId;
	}


	public Long getShopKeeperOperatorId() {
		return shopKeeperOperatorId;
	}


	public void setShopKeeperOperatorId(Long shopKeeperOperatorId) {
		this.shopKeeperOperatorId = shopKeeperOperatorId;
	}


	public Long getShopKeeperAgencyId() {
		return shopKeeperAgencyId;
	}


	public void setShopKeeperAgencyId(Long shopKeeperAgencyId) {
		this.shopKeeperAgencyId = shopKeeperAgencyId;
	}


	public Integer getTodayAllCount() {
		return todayAllCount;
	}


	public void setTodayAllCount(Integer todayAllCount) {
		this.todayAllCount = todayAllCount;
	}


	public BigDecimal getTodayAllAmount() {
		return todayAllAmount;
	}


	public void setTodayAllAmount(BigDecimal todayAllAmount) {
		this.todayAllAmount = todayAllAmount;
	}


	public Integer getHisAllCount() {
		return hisAllCount;
	}


	public void setHisAllCount(Integer hisAllCount) {
		this.hisAllCount = hisAllCount;
	}


	public BigDecimal getHisAllAmount() {
		return hisAllAmount;
	}


	public void setHisAllAmount(BigDecimal hisAllAmount) {
		this.hisAllAmount = hisAllAmount;
	}


	public Integer getStartIndex() {
		return startIndex;
	}


	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}


	public Integer getRows() {
		return rows;
	}


	public void setRows(Integer rows) {
		this.rows = rows;
	}


	public Integer getDevType() {
		return devType;
	}


	public void setDevType(Integer devType) {
		this.devType = devType;
	}


	public Integer getManageModelType() {
		return manageModelType;
	}


	public void setManageModelType(Integer manageModelType) {
		this.manageModelType = manageModelType;
	}

	public Long getFirstLevelAgentsId() {
		return firstLevelAgentsId;
	}

	public void setFirstLevelAgentsId(Long firstLevelAgentsId) {
		this.firstLevelAgentsId = firstLevelAgentsId;
	}

	public Long getSecondLevelAgentsId() {
		return secondLevelAgentsId;
	}

	public void setSecondLevelAgentsId(Long secondLevelAgentsId) {
		this.secondLevelAgentsId = secondLevelAgentsId;
	}

	public String getBeginDateTime() {
		return beginDateTime;
	}

	public void setBeginDateTime(String beginDateTime) {
		this.beginDateTime = beginDateTime;
	}

	public String getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(String endDateTime) {
		this.endDateTime = endDateTime;
	}

	public String getMerchantType() {
		return merchantType;
	}

	public void setMerchantType(String merchantType) {
		this.merchantType = merchantType;
	}

	public Long getQueryMerchantId() {
		return queryMerchantId;
	}

	public void setQueryMerchantId(Long queryMerchantId) {
		this.queryMerchantId = queryMerchantId;
	}

	public String getBeginCreateTime() {
		return beginCreateTime;
	}

	public void setBeginCreateTime(String beginCreateTime) {
		this.beginCreateTime = beginCreateTime;
	}

	public String getEndCreateTime() {
		return endCreateTime;
	}

	public void setEndCreateTime(String endCreateTime) {
		this.endCreateTime = endCreateTime;
	}

	public List<Integer> getDeviceTypes() {
		return deviceTypes;
	}

	public void setDeviceTypes(List<Integer> deviceTypes) {
		this.deviceTypes = deviceTypes;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
