package com.stylefeng.guns.sharecore.modular.system.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.stylefeng.guns.sharecore.modular.system.constants.ShareFeeTypeEnum;
import com.stylefeng.guns.sharecore.modular.system.model.ShareDeviceInfoModel;
import com.stylefeng.guns.sharecore.modular.system.model.ShareFeeBO;

@Service
public class ShareFeeService extends BaseService {
	/**
	 * 得到费用描述....
	 * @param shareDeviceInfoModel
	 * @return
	 */
    public String getFeeDescByDeviceInfoModel(ShareDeviceInfoModel shareDeviceInfoModel){
    	String feeDesc="";
    	ShareFeeBO shareFeeBO=null;
    	List<ShareFeeBO> list=getFeeListByDeviceInfoModel(shareDeviceInfoModel);
    	BigDecimal tmp=new BigDecimal("0");
    	for (int i = 0; i < list.size(); i++) {
			shareFeeBO=list.get(i);
			if(shareFeeBO==null){
				continue;
			}
			if(ShareFeeTypeEnum.Prepayment.getCode()==shareDeviceInfoModel.getFeeTypeId()){
				feeDesc=String.format("%s预付%.0f元，%.0f元/小时，24小时扣%.0f元;",feeDesc
								,shareFeeBO.getYfj()==null?tmp:shareFeeBO.getYfj()
								,shareFeeBO.getAmountPerHour()==null?tmp:shareFeeBO.getAmountPerHour()
								,shareFeeBO.getMaxAmountPer24Hours()==null?tmp:shareFeeBO.getMaxAmountPer24Hours());
			}else if(ShareFeeTypeEnum.PrepaymentHaveFirstAmount.getCode()==shareDeviceInfoModel.getFeeTypeId()){
				feeDesc=String.format("%s预付%.0f元,前%d分钟扣款%.0f元，之后%.0f元/小时，24小时扣%.0f元;",feeDesc
								, shareFeeBO.getYfj()==null?tmp:shareFeeBO.getYfj()
								,shareFeeBO.getFirstMinutes()==null?0L:shareFeeBO.getFirstMinutes()
								,shareFeeBO.getFirstAmount()==null?tmp:shareFeeBO.getFirstAmount()
								,shareFeeBO.getAmountPerHour()==null?tmp:shareFeeBO.getAmountPerHour()
								,shareFeeBO.getMaxAmountPer24Hours()==null?tmp:shareFeeBO.getMaxAmountPer24Hours());
			}else{
				feeDesc=String.format("%s支付%.0f元，充电%d小时;",feeDesc
						,shareFeeBO.getTotalAmountForUseHour()==null?tmp:shareFeeBO.getTotalAmountForUseHour()
						,shareFeeBO.getUseHours());	
			}
		}
    	return feeDesc;
    }
	/**
	 * 根据充电器设备对象，返回充电器用户配置的费用..
	 * @param custNo
	 * @return
	 */
	public List<ShareFeeBO> getFeeListByDeviceInfoModel(ShareDeviceInfoModel shareDeviceInfoModel) {
		List<ShareFeeBO> list=new ArrayList<>();
		if(shareDeviceInfoModel!=null){
			ShareFeeBO shareFeeBO=new ShareFeeBO();
			//1. 处理预付金交易
			if(shareDeviceInfoModel.getFeeTypeId()!=null
					&&ShareFeeTypeEnum.Prepayment.getCode()==shareDeviceInfoModel.getFeeTypeId()){
				shareFeeBO.setAmountPerHour(shareDeviceInfoModel.getAmountPerHour());
				shareFeeBO.setFeeTypeId(shareDeviceInfoModel.getFeeTypeId());
				shareFeeBO.setMaxAmountPer24Hours(shareDeviceInfoModel.getAmountMax24hour());
				shareFeeBO.setYfj(shareDeviceInfoModel.getYfjAmount());	
				list.add(shareFeeBO);
			}else if(shareDeviceInfoModel.getFeeTypeId()!=null
					&&ShareFeeTypeEnum.PrepaymentHaveFirstAmount.getCode()==shareDeviceInfoModel.getFeeTypeId()){
				shareFeeBO.setAmountPerHour(shareDeviceInfoModel.getAmountPerHour());
				shareFeeBO.setFeeTypeId(shareDeviceInfoModel.getFeeTypeId());
				shareFeeBO.setMaxAmountPer24Hours(shareDeviceInfoModel.getAmountMax24hour());
				shareFeeBO.setYfj(shareDeviceInfoModel.getYfjAmount());	
				shareFeeBO.setFirstAmount(shareDeviceInfoModel.getFirstAmount());
				shareFeeBO.setFirstMinutes(shareDeviceInfoModel.getFirstMinutes());
				list.add(shareFeeBO);
			}else{
				if(shareDeviceInfoModel.getFee1TypeId()!=null){
					shareFeeBO=new ShareFeeBO();
					shareFeeBO.setFeeTypeId(shareDeviceInfoModel.getFee1TypeId());
					shareFeeBO.setUseHours(shareDeviceInfoModel.getFee1HourType());
					shareFeeBO.setTotalAmountForUseHour(shareDeviceInfoModel.getFee1HourAmount());
					list.add(shareFeeBO);
				}
				if(shareDeviceInfoModel.getFee2TypeId()!=null){
					shareFeeBO=new ShareFeeBO();
					shareFeeBO.setFeeTypeId(shareDeviceInfoModel.getFee2TypeId());
					shareFeeBO.setUseHours(shareDeviceInfoModel.getFee2HourType());
					shareFeeBO.setTotalAmountForUseHour(shareDeviceInfoModel.getFee2HourAmount());
					list.add(shareFeeBO);
				}
				if(shareDeviceInfoModel.getFee3TypeId()!=null){
					shareFeeBO=new ShareFeeBO();
					shareFeeBO.setFeeTypeId(shareDeviceInfoModel.getFee3TypeId());
					shareFeeBO.setUseHours(shareDeviceInfoModel.getFee3HourType());
					shareFeeBO.setTotalAmountForUseHour(shareDeviceInfoModel.getFee3HourAmount());
					list.add(shareFeeBO);
				}
				
			}
		}
		return list;		
	}
}
