package com.stylefeng.guns.sharecore.modular.system.dao;

import java.util.List;

import com.stylefeng.guns.sharecore.modular.system.model.FrozenCapitaInfoBO;
import com.stylefeng.guns.sharecore.modular.system.model.MerchantProfitDayModel;
import com.stylefeng.guns.sharecore.modular.system.model.ShareTrdTotalInfo;

public interface MerchantProfitDayModelBySelfMapper {
	/**
     * 批量插入设备信息
     * @param list
     */
    void insertBatch(List<MerchantProfitDayModel> list);
    /**
     * 
     * <p>获取指定商户的收益统计信息</p>
     */
    ShareTrdTotalInfo getMerIncmCntAndAmtNew(FrozenCapitaInfoBO frozenCapitaInfoBO);
}