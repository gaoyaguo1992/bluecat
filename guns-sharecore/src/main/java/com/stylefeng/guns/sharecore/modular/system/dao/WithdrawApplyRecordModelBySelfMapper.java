package com.stylefeng.guns.sharecore.modular.system.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.stylefeng.guns.sharecore.modular.system.model.MerchantInfoModel;
import com.stylefeng.guns.sharecore.modular.system.model.WithdrawApplyRecordDetailModel;
import com.stylefeng.guns.sharecore.modular.system.model.WithdrawApplyRecordModel;

public interface WithdrawApplyRecordModelBySelfMapper {
	/**
     * 查询
     * @param offset
     * @param limit
     * @param order
     * @param condition
     * @return
     */
    List<WithdrawApplyRecordDetailModel> selectByConditionForPage(HashMap<String,Object> pageFilter);
    /**
     * 查询
     * @param offset
     * @param limit
     * @param order
     * @param condition
     * @return
     */
    Long countByConditionForPage(HashMap<String,Object> pageFilter);

    /**
     * 得本月提现次数
     * @param merchantId
     * @return
     */
    Integer getWithDrawNumsThisMonth(Long merchantId);
}