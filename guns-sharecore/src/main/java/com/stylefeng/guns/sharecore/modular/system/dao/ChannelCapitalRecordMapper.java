package com.stylefeng.guns.sharecore.modular.system.dao;

import com.stylefeng.guns.sharecore.modular.system.model.ChannelCapitalRecord;


public interface ChannelCapitalRecordMapper {
    int deleteByPrimaryKey(Long recordId);

    int insert(ChannelCapitalRecord record);

    int insertSelective(ChannelCapitalRecord record);
    
    ChannelCapitalRecord selectByPrimaryKeyFromMaster(Long recordId);
    
    ChannelCapitalRecord selectByPrimaryKey(Long recordId);
    
    ChannelCapitalRecord selectByPrimaryKeyForUpdate(Long recordId);

    int updateByPrimaryKeySelective(ChannelCapitalRecord record);

    int updateByPrimaryKey(ChannelCapitalRecord record);

    ChannelCapitalRecord selectByOutTradeNoFromMaster(Long outTradeId);
}