package com.stylefeng.guns.sharecore.common.persistence.model;

import java.math.BigDecimal;
import java.util.Map;

/**
 */
public class MyDeviceInfoBO {
	
	private Long id;
	
	private String province;
	
	private String city;
	
	private String zone;
	
	private String addr;
	
	private int chargerNum;
	
	private int devType;
	
	private String devDesc;
	/**
	 * 设备启用状态：13：启用，10：禁用
	 */
	private Integer status;
	/**
	 * 设备状态
	 */
	private String statusCn;
	/**
	 * 是否检验归还距离 0不检查，1检查
	 */
	private Integer isBackCheck;
	/**
	 * 费用名
	 */
	private String deviceFeeDesc;
	/**
	 * 预付金
	 */
	private BigDecimal yfjAmt;
	/**
	 * 交易次数
	 */
	private Long tradeNum;
	/**
	 * 收费模式
	 */
	private String chargingStr;
	/**
	 * 使用趋势 0：持平， 1:上升，2.下降
	 */
	private Integer trend;
	
	private Boolean flag;
	/**
	 * 给前台判断，是否已经绑定了非的加盟商或者终端
	 */
	private Boolean isBind;
	
	/**
	 * 担保方式
	 */
	private String guaranteeTypeCn; 
	/**
	 * 对应的商户号 
	 */
	private Long merchantId;
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
	
	private Long oppMerchantId; //操作者的商户号（当前登陆公众号的商户）

	public Long getOppMerchantId() {
		return oppMerchantId;
	}

	public void setOppMerchantId(Long oppMerchantId) {
		this.oppMerchantId = oppMerchantId;
	}
	
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

	public Long getShopkeeperId() {
		return shopkeeperId;
	}

	public void setShopkeeperId(Long shopkeeperId) {
		this.shopkeeperId = shopkeeperId;
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

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public String getStatusCn() {
		return statusCn;
	}

	public void setStatusCn(String statusCn) {
		this.statusCn = statusCn;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public int getChargerNum() {
		return chargerNum;
	}

	public void setChargerNum(int chargerNum) {
		this.chargerNum = chargerNum;
	}

	public int getDevType() {
		return devType;
	}

	public void setDevType(int devType) {
		this.devType = devType;
	}

	public String getDevDesc() {
		return devDesc;
	}

	public void setDevDesc(String devDesc) {
		this.devDesc = devDesc;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsBackCheck() {
		return isBackCheck;
	}

	public void setIsBackCheck(Integer isBackCheck) {
		this.isBackCheck = isBackCheck;
	}

	public String getDeviceFeeDesc() {
		return deviceFeeDesc;
	}

	public void setDeviceFeeDesc(String deviceFeeDesc) {
		this.deviceFeeDesc = deviceFeeDesc;
	}

	public BigDecimal getYfjAmt() {
		return yfjAmt;
	}

	public void setYfjAmt(BigDecimal yfjAmt) {
		this.yfjAmt = yfjAmt;
	}

	public Long getTradeNum() {
		return tradeNum;
	}

	public void setTradeNum(Long tradeNum) {
		this.tradeNum = tradeNum;
	}

	public String getChargingStr() {
		return chargingStr;
	}

	public void setChargingStr(String chargingStr) {
		this.chargingStr = chargingStr;
	}

	public Integer getTrend() {
		return trend;
	}

	public void setTrend(Integer trend) {
		this.trend = trend;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public Boolean getIsBind() {
		return isBind;
	}

	public void setIsBind(Boolean isBind) {
		this.isBind = isBind;
	}

	public String getGuaranteeTypeCn() {
		return guaranteeTypeCn;
	}

	public void setGuaranteeTypeCn(String guaranteeTypeCn) {
		this.guaranteeTypeCn = guaranteeTypeCn;
	}
}
