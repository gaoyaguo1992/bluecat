
package com.stylefeng.guns.sharecore.modular.system.model;

import java.math.BigDecimal;
import java.util.List;

import com.stylefeng.guns.sharecore.common.persistence.model.BaseObject;

/**
 * 
 *
 */
public class DeviceTradeInfoBO extends BaseObject {

	/**
	 * TODO
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 终端商户ID
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

	private Long firstLevelAgentsId;

	private Long secondLevelAgentsId;
	/**
	 * 铺货商ID
	 */
	private Long shopKeeperId;

	/**
	 * ID
	 */
	private Long zdId;

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

	/**
	 * 分页开始偏移量
	 */
	private Integer startIndex;

	/**
	 * 分页查询记录数
	 */
	private Integer rows;

	/**
	 * 根据开始时间查询交易明细
	 */
	private String beginDateTime;

	/**
	 * 根据结束时间查询交易明细
	 */
	private String endDateTime;

	// 产品ID
	private Long ratioId;

	// 经营模式
	private Integer manageModelType;
	
	private String merchantType;
	
	private List<String> salesmanIdList;
	
	/**
	 * 登录商户id,用于商户(代理商，铺货商等)登录权限控制
	 */
	private Long loginMerId;
	/**
	 * 登录商户查询类型,用于商户(代理商，铺货商等)登录权限控制
	 */
	private String loginMerTypeQuery;
	
	private Integer status;
	
	/**
	 * 交易渠道
	 */
	private Integer tradeChannel;
	
	private String roleType;
	
	private BigDecimal tradeAmount;//交易金额
	
	private List<Long> deviceZoneLists;//费用所属区域集合
	
	private Long tradeId;//交易编号
	
	private Long custNo;//客户编号
	
	private List<Long> merchantNameLists;//加盟商查询集合
	
	private Long merId;//商户id 
	
	private String merchantTypeQuery;
	
	private Long chargerId;
	
	private Long deviceId;
	
	private Integer tradeType;
	
	private Integer yfjType;
	
	private String backTimeCreateStartDate;

	private String backTimeCreateEndDate;
	
	private Long deviceIdStart;

	private Long deviceIdEnd;
	
	private List<Long> merchantIdLists;
	
	private List<Long> shopSubIdLists;
	
	private List<Long> shopAgencyIdLists;
	
	private List<Long> shopOperatorIdLists;
	
	private List<Long> industryCategoryLists;
	
	private List<Long> devTypeLists;
	
	private String tradeInfoCreateStartDate;
	
	private String tradeInfoCreateEndDate;
	
	private String isExport;//是否导出execl标识
	
	private String isShowAmount;//区分微信公众号标识（东莞铺货商关于其名下加盟商及终端交易明细交易金额不显示）
	
	/**
	 * 商户等级
	 */
	private Integer mptMerchantLevel;
	
	private Integer mptMerchantType;
	
	/**
	 * 当前登录系统的是顶级代理商
	 */
	private String topAgentsQuery;
	
	/**
	 * 当前登录系统的是一级代理商
	 */
	private String firstLevelAgentsQuery;
	
	/**
	 * 当前登录系统的是二级代理商
	 */
	private String secondLevelAgentsQuery;
	
	/**
	 * 当前登录系统的代理商编号
	 */
	private Long loginAgentsId;
	
	private Long targetQueryByMerchantId;
	
	private String targetQueryByMerchantName;
	
	private List<Long> merNameLists;
	
	private List<Long> indCategoryLists;
	
	private Long targetQueryByCustNo;
	
	private List<Long> custNoLists;
	
	private List<Long> nickNameLists;
	
	private String franchiseeQuery;//加盟商查询标识
	
	private String shopkeeperQuery;//铺货商查询标识
	
	private String userId;//当前登录用户id
	
	private List<Long> merchantList;//当前用户所绑定的商户集合
	
	private String roleId;
	
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

	public Long getRatioId() {
		return ratioId;
	}

	public void setRatioId(Long ratioId) {
		this.ratioId = ratioId;
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

	public String getMerchantType() {
		return merchantType;
	}

	public void setMerchantType(String merchantType) {
		this.merchantType = merchantType;
	}

	public List<String> getSalesmanIdList() {
		return salesmanIdList;
	}

	public void setSalesmanIdList(List<String> salesmanIdList) {
		this.salesmanIdList = salesmanIdList;
	}

	public Long getLoginMerId() {
		return loginMerId;
	}

	public void setLoginMerId(Long loginMerId) {
		this.loginMerId = loginMerId;
	}

	public String getLoginMerTypeQuery() {
		return loginMerTypeQuery;
	}

	public void setLoginMerTypeQuery(String loginMerTypeQuery) {
		this.loginMerTypeQuery = loginMerTypeQuery;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTradeChannel() {
		return tradeChannel;
	}

	public void setTradeChannel(Integer tradeChannel) {
		this.tradeChannel = tradeChannel;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public BigDecimal getTradeAmount() {
		return tradeAmount;
	}

	public void setTradeAmount(BigDecimal tradeAmount) {
		this.tradeAmount = tradeAmount;
	}

	public List<Long> getDeviceZoneLists() {
		return deviceZoneLists;
	}

	public void setDeviceZoneLists(List<Long> deviceZoneLists) {
		this.deviceZoneLists = deviceZoneLists;
	}

	public Long getTradeId() {
		return tradeId;
	}

	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}

	public Long getCustNo() {
		return custNo;
	}

	public void setCustNo(Long custNo) {
		this.custNo = custNo;
	}

	public List<Long> getMerchantNameLists() {
		return merchantNameLists;
	}

	public void setMerchantNameLists(List<Long> merchantNameLists) {
		this.merchantNameLists = merchantNameLists;
	}

	public Long getMerId() {
		return merId;
	}

	public void setMerId(Long merId) {
		this.merId = merId;
	}

	public String getMerchantTypeQuery() {
		return merchantTypeQuery;
	}

	public void setMerchantTypeQuery(String merchantTypeQuery) {
		this.merchantTypeQuery = merchantTypeQuery;
	}

	public Long getChargerId() {
		return chargerId;
	}

	public void setChargerId(Long chargerId) {
		this.chargerId = chargerId;
	}

	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	public Integer getTradeType() {
		return tradeType;
	}

	public void setTradeType(Integer tradeType) {
		this.tradeType = tradeType;
	}

	public Integer getYfjType() {
		return yfjType;
	}

	public void setYfjType(Integer yfjType) {
		this.yfjType = yfjType;
	}

	public String getBackTimeCreateStartDate() {
		return backTimeCreateStartDate;
	}

	public void setBackTimeCreateStartDate(String backTimeCreateStartDate) {
		this.backTimeCreateStartDate = backTimeCreateStartDate;
	}

	public String getBackTimeCreateEndDate() {
		return backTimeCreateEndDate;
	}

	public void setBackTimeCreateEndDate(String backTimeCreateEndDate) {
		this.backTimeCreateEndDate = backTimeCreateEndDate;
	}

	public Long getDeviceIdStart() {
		return deviceIdStart;
	}

	public void setDeviceIdStart(Long deviceIdStart) {
		this.deviceIdStart = deviceIdStart;
	}

	public Long getDeviceIdEnd() {
		return deviceIdEnd;
	}

	public void setDeviceIdEnd(Long deviceIdEnd) {
		this.deviceIdEnd = deviceIdEnd;
	}

	public List<Long> getMerchantIdLists() {
		return merchantIdLists;
	}

	public void setMerchantIdLists(List<Long> merchantIdLists) {
		this.merchantIdLists = merchantIdLists;
	}

	public List<Long> getShopSubIdLists() {
		return shopSubIdLists;
	}

	public void setShopSubIdLists(List<Long> shopSubIdLists) {
		this.shopSubIdLists = shopSubIdLists;
	}

	public List<Long> getShopAgencyIdLists() {
		return shopAgencyIdLists;
	}

	public void setShopAgencyIdLists(List<Long> shopAgencyIdLists) {
		this.shopAgencyIdLists = shopAgencyIdLists;
	}

	public List<Long> getShopOperatorIdLists() {
		return shopOperatorIdLists;
	}

	public void setShopOperatorIdLists(List<Long> shopOperatorIdLists) {
		this.shopOperatorIdLists = shopOperatorIdLists;
	}

	public List<Long> getIndustryCategoryLists() {
		return industryCategoryLists;
	}

	public void setIndustryCategoryLists(List<Long> industryCategoryLists) {
		this.industryCategoryLists = industryCategoryLists;
	}

	public List<Long> getDevTypeLists() {
		return devTypeLists;
	}

	public void setDevTypeLists(List<Long> devTypeLists) {
		this.devTypeLists = devTypeLists;
	}

	public String getTradeInfoCreateStartDate() {
		return tradeInfoCreateStartDate;
	}

	public void setTradeInfoCreateStartDate(String tradeInfoCreateStartDate) {
		this.tradeInfoCreateStartDate = tradeInfoCreateStartDate;
	}

	public String getTradeInfoCreateEndDate() {
		return tradeInfoCreateEndDate;
	}

	public void setTradeInfoCreateEndDate(String tradeInfoCreateEndDate) {
		this.tradeInfoCreateEndDate = tradeInfoCreateEndDate;
	}

	public String getIsExport() {
		return isExport;
	}

	public void setIsExport(String isExport) {
		this.isExport = isExport;
	}

	public String getTopAgentsQuery() {
		return topAgentsQuery;
	}

	public void setTopAgentsQuery(String topAgentsQuery) {
		this.topAgentsQuery = topAgentsQuery;
	}

	public String getFirstLevelAgentsQuery() {
		return firstLevelAgentsQuery;
	}

	public void setFirstLevelAgentsQuery(String firstLevelAgentsQuery) {
		this.firstLevelAgentsQuery = firstLevelAgentsQuery;
	}

	public String getSecondLevelAgentsQuery() {
		return secondLevelAgentsQuery;
	}

	public void setSecondLevelAgentsQuery(String secondLevelAgentsQuery) {
		this.secondLevelAgentsQuery = secondLevelAgentsQuery;
	}

	public Long getLoginAgentsId() {
		return loginAgentsId;
	}

	public void setLoginAgentsId(Long loginAgentsId) {
		this.loginAgentsId = loginAgentsId;
	}

	public Integer getMptMerchantLevel() {
		return mptMerchantLevel;
	}

	public void setMptMerchantLevel(Integer mptMerchantLevel) {
		this.mptMerchantLevel = mptMerchantLevel;
	}

	public Integer getMptMerchantType() {
		return mptMerchantType;
	}

	public void setMptMerchantType(Integer mptMerchantType) {
		this.mptMerchantType = mptMerchantType;
	}

	public Long getTargetQueryByMerchantId() {
		return targetQueryByMerchantId;
	}

	public void setTargetQueryByMerchantId(Long targetQueryByMerchantId) {
		this.targetQueryByMerchantId = targetQueryByMerchantId;
	}

	public String getTargetQueryByMerchantName() {
		return targetQueryByMerchantName;
	}

	public void setTargetQueryByMerchantName(String targetQueryByMerchantName) {
		this.targetQueryByMerchantName = targetQueryByMerchantName;
	}

	public List<Long> getMerNameLists() {
		return merNameLists;
	}

	public void setMerNameLists(List<Long> merNameLists) {
		this.merNameLists = merNameLists;
	}

	public List<Long> getIndCategoryLists() {
		return indCategoryLists;
	}

	public void setIndCategoryLists(List<Long> indCategoryLists) {
		this.indCategoryLists = indCategoryLists;
	}

	public Long getTargetQueryByCustNo() {
		return targetQueryByCustNo;
	}

	public void setTargetQueryByCustNo(Long targetQueryByCustNo) {
		this.targetQueryByCustNo = targetQueryByCustNo;
	}

	public List<Long> getCustNoLists() {
		return custNoLists;
	}

	public void setCustNoLists(List<Long> custNoLists) {
		this.custNoLists = custNoLists;
	}

	public String getIsShowAmount() {
		return isShowAmount;
	}

	public void setIsShowAmount(String isShowAmount) {
		this.isShowAmount = isShowAmount;
	}

	public List<Long> getNickNameLists() {
		return nickNameLists;
	}

	public void setNickNameLists(List<Long> nickNameLists) {
		this.nickNameLists = nickNameLists;
	}

	public String getFranchiseeQuery() {
		return franchiseeQuery;
	}

	public void setFranchiseeQuery(String franchiseeQuery) {
		this.franchiseeQuery = franchiseeQuery;
	}

	public String getShopkeeperQuery() {
		return shopkeeperQuery;
	}

	public void setShopkeeperQuery(String shopkeeperQuery) {
		this.shopkeeperQuery = shopkeeperQuery;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<Long> getMerchantList() {
		return merchantList;
	}

	public void setMerchantList(List<Long> merchantList) {
		this.merchantList = merchantList;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public Long getZdId() {
		return zdId;
	}

	public void setZdId(Long zdId) {
		this.zdId = zdId;
	}

}
