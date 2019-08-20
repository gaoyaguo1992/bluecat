package com.stylefeng.guns.sharecore.modular.system.dao;

import com.stylefeng.guns.sharecore.modular.system.model.CustCanwithdrawChargeInfoModel;


public interface CustCanwithdrawChargeInfoModelMapper {
	int deleteByPrimaryKey(Long tradeId);

	int insert(CustCanwithdrawChargeInfoModel record);

	int insertSelective(CustCanwithdrawChargeInfoModel record);

	CustCanwithdrawChargeInfoModel selectByPrimaryKey(Long tradeId);

	int updateByPrimaryKeySelective(CustCanwithdrawChargeInfoModel record);

	int updateByPrimaryKey(CustCanwithdrawChargeInfoModel record);
}