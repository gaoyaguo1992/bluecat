package com.stylefeng.guns.sharecore.modular.system.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.sharecore.modular.system.model.CalculateMerchantDataModel;
import com.stylefeng.guns.sharecore.modular.system.model.CalculateMerchantDataModelExample;
import com.stylefeng.guns.sharecore.modular.system.model.ChargerFewDayNotUsedBO;
import com.stylefeng.guns.sharecore.modular.system.model.DeviceNoUseOfMerchantBO;

public interface CalculateMerchantDataModelMapper extends BaseMapper<CalculateMerchantDataModel>{
    int countByExample(CalculateMerchantDataModelExample example);

    int deleteByExample(CalculateMerchantDataModelExample example);

    int deleteByPrimaryKey(Long merchantId);

    int insertSelective(CalculateMerchantDataModel record);

    List<CalculateMerchantDataModel> selectByExample(CalculateMerchantDataModelExample example);

    CalculateMerchantDataModel selectByPrimaryKey(Long merchantId);

    int updateByExampleSelective(@Param("record") CalculateMerchantDataModel record, @Param("example") CalculateMerchantDataModelExample example);

    int updateByExample(@Param("record") CalculateMerchantDataModel record, @Param("example") CalculateMerchantDataModelExample example);

    int updateByPrimaryKeySelective(CalculateMerchantDataModel record);

    int updateByPrimaryKey(CalculateMerchantDataModel record);

	Long countCalculateMerchantData(HashMap<String, Object> pageFilter);

	List<CalculateMerchantDataModel> listCalculateMerchantData(HashMap<String, Object> pageFilter);

	/**
	 * 连续七天未使用商家列表
	 * @param pageFilter
	 * @return
	 */
	List<DeviceNoUseOfMerchantBO> nouseByMerchantOf7Days(HashMap<String, Object> pageFilter);

}