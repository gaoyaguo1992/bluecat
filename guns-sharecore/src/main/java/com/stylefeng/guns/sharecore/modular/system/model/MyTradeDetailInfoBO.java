package com.stylefeng.guns.sharecore.modular.system.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.stylefeng.guns.sharecore.common.persistence.model.BaseObject;

/**
 * 
 *
 */
public class MyTradeDetailInfoBO extends BaseObject {
	
	/**
	 * TODO
	 */
	private static final long serialVersionUID = -1113647133276848994L;

	// 交易编号
	private Long tradeId;
	
	// 交易金额
	private String tradeAmount;
	
	// 交易状态
	private Integer tradeStatus;
	
	// 交易状态描述
	private String tradeStatusCn;
	
	// 交易时间
	private Date tradeTime;
	
	// 格式化交易时间
	private String tradeTimeFromat;
	
	// 归还时间
	private Date backTime;
	
	// 格式化归还时间
	private String backTimeFromat;
	
	// 交易类型
	private Integer tradeType;
	
	// 交易名称
	private String tradeName;
	
	// 退款原因
	private String operateComment;
	
	private Long refTradeId;
	
	private Long custNo;//客户编号
	
	private Long merchantId;//终端id
	/**
	 * 对应商名称
	 */
	private String merchantCn;
	/**
	 * 代理商1id
	 */
	private Long agents1Id;
	/**
	 * 代理商名1
	 */
	private String agents1Cn;
	/**
	 * 代理商2id.
	 */
	private Long agents2Id;
	/**
	 *代理商2名
	 */
	private String agents2Cn;
	/**
	 * 代理商3id
	 */
	private Long agents3Id;
	/**
	 * 代理商3名
	 */
	private String agents3Cn;
	/**
	 * 铺货商id
	 */
	private Long shopkeeperId;
	/**
	 * 铺货商名
	 */
	private String shopkeeperCn;
	/**
	 * 加盟商名id
	 */
	private Long allianceBusinessId;
	/**
	 * 加盟商名
	 */
	private String allianceBusinessCn;
	
	
	private Long agentsId;//代理商id
	
	private Long deviceId;//设备id
	
	private Long ratioId;//产品id
	
	private Integer tradeChannel;//交易渠道
	
	private Long chargerId;//充电器id
	
	private Date borrowTime;//借出时间
	
	private String borrowTimeFormat;//格式化借出时间
	
    private Date updateTime;//更新时间
	
	private String updateTimeFormat;//格式化更新时间
	
	private BigDecimal yfjAmount;//预付金额
	
    private Date createTime;//新建时间
	
	private String createTimeFormat;//格式化新建时间
	
	private Long firstLevelAgentsId;
	
	private Long secondLevelAgentsId;
	
	private Long franchiseeId;
		
	private BigDecimal amount;
	
	private String profitAmountStr;
	
	public String getMerchantCn() {
		return merchantCn;
	}

	public void setMerchantCn(String merchantCn) {
		this.merchantCn = merchantCn;
	}

	public Long getAgents1Id() {
		return agents1Id;
	}

	public void setAgents1Id(Long agents1Id) {
		this.agents1Id = agents1Id;
	}

	public String getAgents1Cn() {
		return agents1Cn;
	}

	public void setAgents1Cn(String agents1Cn) {
		this.agents1Cn = agents1Cn;
	}

	public Long getAgents2Id() {
		return agents2Id;
	}

	public void setAgents2Id(Long agents2Id) {
		this.agents2Id = agents2Id;
	}

	public String getAgents2Cn() {
		return agents2Cn;
	}

	public void setAgents2Cn(String agents2Cn) {
		this.agents2Cn = agents2Cn;
	}

	public Long getAgents3Id() {
		return agents3Id;
	}

	public void setAgents3Id(Long agents3Id) {
		this.agents3Id = agents3Id;
	}

	public String getAgents3Cn() {
		return agents3Cn;
	}

	public void setAgents3Cn(String agents3Cn) {
		this.agents3Cn = agents3Cn;
	}

	public String getShopkeeperCn() {
		return shopkeeperCn;
	}

	public void setShopkeeperCn(String shopkeeperCn) {
		this.shopkeeperCn = shopkeeperCn;
	}

	public Long getAllianceBusinessId() {
		return allianceBusinessId;
	}

	public void setAllianceBusinessId(Long allianceBusinessId) {
		this.allianceBusinessId = allianceBusinessId;
	}

	public String getAllianceBusinessCn() {
		return allianceBusinessCn;
	}

	public void setAllianceBusinessCn(String allianceBusinessCn) {
		this.allianceBusinessCn = allianceBusinessCn;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getTradeId() {
		return tradeId;
	}

	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}

	public Integer getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(Integer tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public String getTradeStatusCn() {
		return tradeStatusCn;
	}

	public void setTradeStatusCn(String tradeStatusCn) {
		this.tradeStatusCn = tradeStatusCn;
	}

	public Date getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}

	public String getTradeTimeFromat() {
		return tradeTimeFromat;
	}

	public void setTradeTimeFromat(String tradeTimeFromat) {
		this.tradeTimeFromat = tradeTimeFromat;
	}

	public Date getBackTime() {
		return backTime;
	}

	public void setBackTime(Date backTime) {
		this.backTime = backTime;
	}

	public String getBackTimeFromat() {
		return backTimeFromat;
	}

	public void setBackTimeFromat(String backTimeFromat) {
		this.backTimeFromat = backTimeFromat;
	}

	public Integer getTradeType() {
		return tradeType;
	}

	public void setTradeType(Integer tradeType) {
		this.tradeType = tradeType;
	}

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public String getOperateComment() {
		return operateComment;
	}

	public void setOperateComment(String operateComment) {
		this.operateComment = operateComment;
	}

	public Long getRefTradeId() {
		return refTradeId;
	}

	public void setRefTradeId(Long refTradeId) {
		this.refTradeId = refTradeId;
	}

	public Long getCustNo() {
		return custNo;
	}

	public void setCustNo(Long custNo) {
		this.custNo = custNo;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public Long getAgentsId() {
		return agentsId;
	}

	public void setAgentsId(Long agentsId) {
		this.agentsId = agentsId;
	}

	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	public Long getRatioId() {
		return ratioId;
	}

	public void setRatioId(Long ratioId) {
		this.ratioId = ratioId;
	}

	public Integer getTradeChannel() {
		return tradeChannel;
	}

	public void setTradeChannel(Integer tradeChannel) {
		this.tradeChannel = tradeChannel;
	}

	public Long getChargerId() {
		return chargerId;
	}

	public void setChargerId(Long chargerId) {
		this.chargerId = chargerId;
	}

	public Date getBorrowTime() {
		return borrowTime;
	}

	public void setBorrowTime(Date borrowTime) {
		this.borrowTime = borrowTime;
	}

	public String getBorrowTimeFormat() {
		return borrowTimeFormat;
	}

	public void setBorrowTimeFormat(String borrowTimeFormat) {
		this.borrowTimeFormat = borrowTimeFormat;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateTimeFormat() {
		return updateTimeFormat;
	}

	public void setUpdateTimeFormat(String updateTimeFormat) {
		this.updateTimeFormat = updateTimeFormat;
	}

	public BigDecimal getYfjAmount() {
		return yfjAmount;
	}

	public void setYfjAmount(BigDecimal yfjAmount) {
		this.yfjAmount = yfjAmount;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateTimeFormat() {
		return createTimeFormat;
	}

	public void setCreateTimeFormat(String createTimeFormat) {
		this.createTimeFormat = createTimeFormat;
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

	public Long getFranchiseeId() {
		return franchiseeId;
	}

	public void setFranchiseeId(Long franchiseeId) {
		this.franchiseeId = franchiseeId;
	}

	public Long getShopkeeperId() {
		return shopkeeperId;
	}

	public void setShopkeeperId(Long shopkeeperId) {
		this.shopkeeperId = shopkeeperId;
	}

	public String getProfitAmountStr() {
		return profitAmountStr;
	}

	public void setProfitAmountStr(String profitAmountStr) {
		this.profitAmountStr = profitAmountStr;
	}

	public String getTradeAmount() {
		return tradeAmount;
	}

	public void setTradeAmount(String tradeAmount) {
		this.tradeAmount = tradeAmount;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}
