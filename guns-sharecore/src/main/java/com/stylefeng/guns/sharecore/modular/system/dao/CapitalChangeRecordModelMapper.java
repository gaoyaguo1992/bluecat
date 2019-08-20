package com.stylefeng.guns.sharecore.modular.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.stylefeng.guns.sharecore.modular.system.model.CapitalChangeRecordModel;

public interface CapitalChangeRecordModelMapper {
    int deleteByPrimaryKey(Long capitalChangeId);

    int insert(CapitalChangeRecordModel record);

    int insertSelective(CapitalChangeRecordModel record);

    CapitalChangeRecordModel selectByPrimaryKey(Long capitalChangeId);

    int updateByPrimaryKeySelective(CapitalChangeRecordModel record);

    int updateByPrimaryKey(CapitalChangeRecordModel record);
  }