package com.stylefeng.guns.sharecore.modular.system.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.stylefeng.guns.sharecore.modular.system.model.WithdrawApplyRecordModel;

public interface WithdrawApplyRecordModelMapper {
	
	BigDecimal queryCashWithdrawInfoByMerchantId(@Param("merchantId")Long merchantId,@Param("withdrawStatus")Integer withdrawStatus);
	
	int deleteByPrimaryKey(Long id);

    int insert(WithdrawApplyRecordModel record);

    int insertSelective(WithdrawApplyRecordModel record);

    WithdrawApplyRecordModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WithdrawApplyRecordModel record);

    int updateByPrimaryKey(WithdrawApplyRecordModel record);
    
    List<WithdrawApplyRecordModel> queryWithdrawRecord(@Param("merchantId")Long merchantId,@Param("beginTime")String beginTime,@Param("nowTime")String nowTime,@Param("type")Integer type);
    
    List<WithdrawApplyRecordModel> queryMerchantWithdrawRecord(@Param("merchantId")Long merchantId);
    
    WithdrawApplyRecordModel selectByPrimaryKeyForUpdate(Long id);

}