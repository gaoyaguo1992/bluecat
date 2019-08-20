package com.stylefeng.guns.sharecore.modular.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.stylefeng.guns.sharecore.modular.system.model.NotifyMessageModel;

public interface NotifyMessageMapper {

    int updateByPrimaryKeySelective(NotifyMessageModel record);

    int updateByPrimaryKey(NotifyMessageModel record);	
    
    int deleteByPrimaryKey(Long id);

    int insert(NotifyMessageModel record);

    int insertSelective(NotifyMessageModel record);

    NotifyMessageModel selectByPrimaryKey(Long id);
}