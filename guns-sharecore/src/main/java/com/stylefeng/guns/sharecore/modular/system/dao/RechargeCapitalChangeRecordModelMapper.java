package com.stylefeng.guns.sharecore.modular.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.stylefeng.guns.sharecore.modular.system.model.RechargeCapitalChangeRecordModel;

public interface RechargeCapitalChangeRecordModelMapper {
    int deleteByPrimaryKey(Long capitalChangeId);
   RechargeCapitalChangeRecordModel selectByPrimaryKey(Long capitalChangeId);

    int updateByPrimaryKeySelective(RechargeCapitalChangeRecordModel record);

    int updateByPrimaryKey(RechargeCapitalChangeRecordModel record);

    int insert(RechargeCapitalChangeRecordModel record);

    int insertSelective(RechargeCapitalChangeRecordModel record);
}