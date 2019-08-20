package com.stylefeng.guns.sharecore.modular.system.model;

import java.math.BigDecimal;
import com.stylefeng.guns.sharecore.modular.system.constants.ShareFeeTypeEnum;

/**
 * 费用模式..
 * @author Alan.huang
 *
 */
public class ShareFeeBO {
	/**
	 * 每小 时多少钱
	 */
	private BigDecimal amountPerHour;
	/**
	 * 24小时最大费用
	 */
	private BigDecimal maxAmountPer24Hours;
	/**
	 * 预付金
	 */
	private BigDecimal yfj;
	/**
	 * 首付充电费用
	 */
	private BigDecimal firstAmount;
	/**
	 * 首付充电费充电时长
	 */
	private Long firstMinutes;
	/**
	 * 充电使用时长
	 */
	private int useHours;
	/**
	 * 费用类型id
	 */
	private int feeTypeId;
	/**
	 * 总共费用（充电使用）
	 */
	private BigDecimal totalAmountForUseHour;
	
	public BigDecimal getAmountPerHour() {
		return amountPerHour;
	}
	public void setAmountPerHour(BigDecimal amountPerHour) {
		this.amountPerHour = amountPerHour;
	}
	public BigDecimal getMaxAmountPer24Hours() {
		return maxAmountPer24Hours;
	}
	public void setMaxAmountPer24Hours(BigDecimal maxAmountPer24Hours) {
		this.maxAmountPer24Hours = maxAmountPer24Hours;
	}
	public BigDecimal getYfj() {
		return yfj;
	}
	public void setYfj(BigDecimal yfj) {
		this.yfj = yfj;
	}
	public int getUseHours() {
		return useHours;
	}
	public void setUseHours(int useHours) {
		this.useHours = useHours;
	}
	public int getFeeTypeId() {
		return feeTypeId;
	}
	public void setFeeTypeId(int feeTypeId) {
		this.feeTypeId = feeTypeId;
	}
	public BigDecimal getTotalAmountForUseHour() {
		return totalAmountForUseHour;
	}
	public void setTotalAmountForUseHour(BigDecimal totalAmountForUseHour) {
		this.totalAmountForUseHour = totalAmountForUseHour;
	}
	public BigDecimal getFirstAmount() {
		return firstAmount;
	}
	public void setFirstAmount(BigDecimal firstAmount) {
		this.firstAmount = firstAmount;
	}
	public Long getFirstMinutes() {
		return firstMinutes;
	}
	public void setFirstMinutes(Long firstMinutes) {
		this.firstMinutes = firstMinutes;
	}
	/**
	 * 根据费用类型id及设备信息，得到对应的费用bo对象..
	 * @param deviceInfoModel
	 * @return
	 */
	public static ShareFeeBO getShareFeeBOByFeeTypeId(ShareDeviceInfoModel deviceInfoModel,ShareFeeTypeEnum feeTypeEnum){
		ShareFeeBO feeBo=new ShareFeeBO();
		feeBo.setFeeTypeId(feeTypeEnum.getCode());
		//预付金模式
		if(deviceInfoModel.getFeeTypeId()!=null&&
				deviceInfoModel.getFeeTypeId().intValue()==feeTypeEnum.getCode()){
			feeBo.setFeeTypeId(feeTypeEnum.getCode());
			feeBo.setFirstAmount(deviceInfoModel.getFirstAmount());
			feeBo.setFirstMinutes(deviceInfoModel.getFirstMinutes());
			feeBo.setAmountPerHour(deviceInfoModel.getAmountPerHour());
			feeBo.setMaxAmountPer24Hours(deviceInfoModel.getAmountMax24hour());
			feeBo.setYfj(deviceInfoModel.getYfjAmount());
			return feeBo;
		}
		//按时间模式
		if(deviceInfoModel.getFee1TypeId()!=null&&
				deviceInfoModel.getFee1TypeId().intValue()==feeTypeEnum.getCode()){
			feeBo.setFeeTypeId(feeTypeEnum.getCode());
			feeBo.setFirstAmount(deviceInfoModel.getFirstAmount());
			feeBo.setFirstMinutes(deviceInfoModel.getFirstMinutes());
			feeBo.setAmountPerHour(deviceInfoModel.getAmountPerHour());
			feeBo.setMaxAmountPer24Hours(deviceInfoModel.getAmountMax24hour());
			
			feeBo.setYfj(deviceInfoModel.getFee1HourAmount());
			feeBo.setUseHours(deviceInfoModel.getFee1HourType());
			feeBo.setTotalAmountForUseHour(deviceInfoModel.getFee1HourAmount());
			return feeBo;
		}
		if(deviceInfoModel.getFee2TypeId()!=null&&
				deviceInfoModel.getFee2TypeId().intValue()==feeTypeEnum.getCode()){
			feeBo.setFeeTypeId(feeTypeEnum.getCode());
			feeBo.setFirstAmount(deviceInfoModel.getFirstAmount());
			feeBo.setFirstMinutes(deviceInfoModel.getFirstMinutes());
			feeBo.setAmountPerHour(deviceInfoModel.getAmountPerHour());
			feeBo.setMaxAmountPer24Hours(deviceInfoModel.getAmountMax24hour());
			
			feeBo.setYfj(deviceInfoModel.getFee2HourAmount());
			feeBo.setUseHours(deviceInfoModel.getFee2HourType());
			feeBo.setTotalAmountForUseHour(deviceInfoModel.getFee2HourAmount());
			return feeBo;
		}
		if(deviceInfoModel.getFee3TypeId()!=null&&
				deviceInfoModel.getFee3TypeId().intValue()==feeTypeEnum.getCode()){
			feeBo.setFeeTypeId(feeTypeEnum.getCode());
			feeBo.setFirstAmount(deviceInfoModel.getFirstAmount());
			feeBo.setFirstMinutes(deviceInfoModel.getFirstMinutes());
			feeBo.setAmountPerHour(deviceInfoModel.getAmountPerHour());
			feeBo.setMaxAmountPer24Hours(deviceInfoModel.getAmountMax24hour());
			
			feeBo.setYfj(deviceInfoModel.getFee3HourAmount());
			feeBo.setUseHours(deviceInfoModel.getFee3HourType());
			feeBo.setTotalAmountForUseHour(deviceInfoModel.getFee3HourAmount());
			return feeBo;
		}
		
		return feeBo;
	}
}
