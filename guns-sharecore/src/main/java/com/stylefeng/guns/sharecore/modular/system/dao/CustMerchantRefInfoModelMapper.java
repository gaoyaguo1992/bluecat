package com.stylefeng.guns.sharecore.modular.system.dao;

import com.stylefeng.guns.sharecore.modular.system.model.CustMerchantRefInfoModel;
import com.stylefeng.guns.sharecore.modular.system.model.CustMerchantRefInfoModelExample;
import com.stylefeng.guns.sharecore.modular.system.model.CustMerchantRefInfoModelKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CustMerchantRefInfoModelMapper {
    int countByExample(CustMerchantRefInfoModelExample example);

    int deleteByExample(CustMerchantRefInfoModelExample example);

    int deleteByPrimaryKey(CustMerchantRefInfoModelKey key);

    int insert(CustMerchantRefInfoModel record);

    int insertSelective(CustMerchantRefInfoModel record);

    List<CustMerchantRefInfoModel> selectByExample(CustMerchantRefInfoModelExample example);

    CustMerchantRefInfoModel selectByPrimaryKey(CustMerchantRefInfoModelKey key);

    int updateByExampleSelective(@Param("record") CustMerchantRefInfoModel record, @Param("example") CustMerchantRefInfoModelExample example);

    int updateByExample(@Param("record") CustMerchantRefInfoModel record, @Param("example") CustMerchantRefInfoModelExample example);

    int updateByPrimaryKeySelective(CustMerchantRefInfoModel record);

    int updateByPrimaryKey(CustMerchantRefInfoModel record);
}