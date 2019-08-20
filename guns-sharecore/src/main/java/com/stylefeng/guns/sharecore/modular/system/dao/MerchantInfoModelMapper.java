package com.stylefeng.guns.sharecore.modular.system.dao;

import com.stylefeng.guns.sharecore.modular.system.model.MerchantInfoModel;
import com.stylefeng.guns.sharecore.modular.system.model.MerchantInfoModelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MerchantInfoModelMapper {
    int countByExample(MerchantInfoModelExample example);

    int deleteByExample(MerchantInfoModelExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MerchantInfoModel record);

    int insertSelective(MerchantInfoModel record);

    List<MerchantInfoModel> selectByExample(MerchantInfoModelExample example);

    MerchantInfoModel selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MerchantInfoModel record, @Param("example") MerchantInfoModelExample example);

    int updateByExample(@Param("record") MerchantInfoModel record, @Param("example") MerchantInfoModelExample example);

    int updateByPrimaryKeySelective(MerchantInfoModel record);

    int updateByPrimaryKey(MerchantInfoModel record);
}