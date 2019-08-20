package com.stylefeng.guns.sharecore.modular.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.stylefeng.guns.sharecore.modular.system.model.CustMerchantRefInfoModel;

public interface CustMerchantRefInfoBySelfModelMapper {

    /**
     * 根据商户号，得到关联关系
     * @param merchantId
     * @return
     */
    List<CustMerchantRefInfoModel> selectByMerchantId(@Param("merchantId")Long merchantId);
    /**
     * 根据客户号得到客户关联信息
     * @param custNo
     * @return
     */
    List<CustMerchantRefInfoModel> selectByCustNo(@Param("custNo")Long custNo);
}