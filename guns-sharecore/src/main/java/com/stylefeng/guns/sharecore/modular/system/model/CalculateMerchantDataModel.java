package com.stylefeng.guns.sharecore.modular.system.model;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

@TableName("calculate_merchant_data")
public class CalculateMerchantDataModel {

	@TableId(value="merchantId", type= IdType.AUTO)
	private Long merchantId;

	private Long merchantTypeId;

	private Long deviceQty;

	private Long deviceUsedTotalQty;

	private Long deviceUsed7daysQty;

	private BigDecimal device7daysUsageRate;

	private BigDecimal deviceTotalAmount;

	private Date lastUsedDate;

	private Date calculateDate;

	private MerchantInfoModel merchantInfoModel;

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public Long getMerchantTypeId() {
		return merchantTypeId;
	}

	public void setMerchantTypeId(Long merchantTypeId) {
		this.merchantTypeId = merchantTypeId;
	}

	public Long getDeviceQty() {
		return deviceQty;
	}

	public void setDeviceQty(Long deviceQty) {
		this.deviceQty = deviceQty;
	}

	public Long getDeviceUsedTotalQty() {
		return deviceUsedTotalQty;
	}

	public void setDeviceUsedTotalQty(Long deviceUsedTotalQty) {
		this.deviceUsedTotalQty = deviceUsedTotalQty;
	}

	public Long getDeviceUsed7daysQty() {
		return deviceUsed7daysQty;
	}

	public void setDeviceUsed7daysQty(Long deviceUsed7daysQty) {
		this.deviceUsed7daysQty = deviceUsed7daysQty;
	}

	public BigDecimal getDevice7daysUsageRate() {
		return device7daysUsageRate;
	}

	public void setDevice7daysUsageRate(BigDecimal device7daysUsageRate) {
		this.device7daysUsageRate = device7daysUsageRate;
	}

	public BigDecimal getDeviceTotalAmount() {
		return deviceTotalAmount;
	}

	public void setDeviceTotalAmount(BigDecimal deviceTotalAmount) {
		this.deviceTotalAmount = deviceTotalAmount;
	}

	public Date getLastUsedDate() {
		return lastUsedDate;
	}

	public void setLastUsedDate(Date lastUsedDate) {
		this.lastUsedDate = lastUsedDate;
	}

	public Date getCalculateDate() {
		return calculateDate;
	}

	public void setCalculateDate(Date calculateDate) {
		this.calculateDate = calculateDate;
	}

	public MerchantInfoModel getMerchantInfoModel() {
		return merchantInfoModel;
	}

	public void setMerchantInfoModel(MerchantInfoModel merchantInfoModel) {
		this.merchantInfoModel = merchantInfoModel;
	}
}