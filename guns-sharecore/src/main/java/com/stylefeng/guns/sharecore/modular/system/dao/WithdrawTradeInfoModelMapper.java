package com.stylefeng.guns.sharecore.modular.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.stylefeng.guns.sharecore.modular.system.model.WithdrawTradeInfoModel;

public interface WithdrawTradeInfoModelMapper {
    int deleteByPrimaryKey(Long tradeId);

    int insert(WithdrawTradeInfoModel record);

    int insertSelective(WithdrawTradeInfoModel record);

    WithdrawTradeInfoModel selectByPrimaryKey(Long tradeId);

    int updateByPrimaryKeySelective(WithdrawTradeInfoModel record);

    int updateByPrimaryKey(WithdrawTradeInfoModel record);
  }