package com.stylefeng.guns.sharecore.modular.system.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.stylefeng.guns.sharecore.modular.system.model.RechargeTradeInfoModel;
import com.stylefeng.guns.sharecore.modular.system.model.RefundBO;

public interface RechargeTradeInfoModelMapper {
    List<RefundBO> getRefundAmts(Long custNo);
    
    List<RechargeTradeInfoModel> refundTradesByRefundAmtForUpdate(Set<Long> tradeIds);

    void insertRefundRecordsIfHaveNotRefundByCustNo(Long custNo);
    
    int deleteByPrimaryKey(Long tradeId);

    int insert(RechargeTradeInfoModel record);

    int insertSelective(RechargeTradeInfoModel record);

    RechargeTradeInfoModel selectByPrimaryKey(Long tradeId);

    int updateByPrimaryKeySelective(RechargeTradeInfoModel record);

    int updateByPrimaryKey(RechargeTradeInfoModel record);
}