package com.stylefeng.guns.sharecore.modular.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.stylefeng.guns.sharecore.common.persistence.model.MyIncomeDetailInfoBO;
import com.stylefeng.guns.sharecore.modular.system.model.DeviceTradeInfoBO;
import com.stylefeng.guns.sharecore.modular.system.model.ShareTrdTotalInfo;
import com.stylefeng.guns.sharecore.modular.system.model.WithdrawInfoBO;
import com.stylefeng.guns.sharecore.modular.system.model.WithdrawOrderInfoModel;
import com.stylefeng.guns.sharecore.modular.system.model.WithdrawTradeInfoBO;

public interface WithdrawOrderInfoModelMapper {
	Integer getTradeNumByDate(@Param("dateStr")String dateStr,@Param("deviceId")Long deviceId,@Param("queryMerchant")String queryMerchant,@Param("merchantId")Long merchantId);

  
    WithdrawOrderInfoModel selectByPrimaryKey(Long tradeId);

	Integer queryCountByMerchantId(WithdrawOrderInfoModel queryModel); 

    int updateByPrimaryKeySelective(WithdrawOrderInfoModel record);    
    
    ShareTrdTotalInfo getMerIncomeCountAndAmtNew(DeviceTradeInfoBO dtb);
    

    int deleteByPrimaryKey(Long tradeId);
    
	int updateOrgiTradeSelective(WithdrawOrderInfoModel record);

    List<WithdrawTradeInfoBO> getWithdrawTradeHistory(int startIndex, int rows,Long custNo);
    
    List<MyIncomeDetailInfoBO> getMyIncomeDetailNew(DeviceTradeInfoBO dtb);
    
    List<WithdrawInfoBO> getWithdrawHistory(Long trade_id);
    
    int insert(WithdrawOrderInfoModel record);
    
	int insertSelective(WithdrawOrderInfoModel record);

}