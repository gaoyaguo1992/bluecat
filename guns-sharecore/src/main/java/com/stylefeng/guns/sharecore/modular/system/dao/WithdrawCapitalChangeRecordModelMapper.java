package com.stylefeng.guns.sharecore.modular.system.dao;

import com.stylefeng.guns.sharecore.modular.system.model.WithdrawCapitalChangeRecordModel;


public interface WithdrawCapitalChangeRecordModelMapper {
	int deleteByPrimaryKey(Long capitalChangeId);

	int insert(WithdrawCapitalChangeRecordModel record);

	int insertSelective(WithdrawCapitalChangeRecordModel record);

	WithdrawCapitalChangeRecordModel selectByPrimaryKey(Long capitalChangeId);

	int updateByPrimaryKeySelective(WithdrawCapitalChangeRecordModel record);

	int updateByPrimaryKey(WithdrawCapitalChangeRecordModel record);
}