package com.stylefeng.guns.sharecore.modular.system.dao;


import java.util.List;

import com.stylefeng.guns.sharecore.modular.system.model.RefundTradeRefModel;
import com.stylefeng.guns.sharecore.modular.system.model.RefundTradeRefModelKey;

import org.apache.ibatis.annotations.Param;

public interface RefundTradeRefModelMapper {
    int deleteByPrimaryKey(RefundTradeRefModelKey key);

    int updateByPrimaryKeySelective(RefundTradeRefModel record);

    int updateByPrimaryKey(RefundTradeRefModel record);
    
    int insert(RefundTradeRefModel record);
    
    RefundTradeRefModel selectByPrimaryKey(RefundTradeRefModelKey key);
 }