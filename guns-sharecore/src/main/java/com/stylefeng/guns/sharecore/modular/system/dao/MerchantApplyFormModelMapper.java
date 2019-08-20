package com.stylefeng.guns.sharecore.modular.system.dao;

import com.stylefeng.guns.sharecore.modular.system.model.MerchantApplyFormModel;
import com.stylefeng.guns.sharecore.modular.system.model.MerchantApplyFormModelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MerchantApplyFormModelMapper {
    int countByExample(MerchantApplyFormModelExample example);

    int deleteByExample(MerchantApplyFormModelExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MerchantApplyFormModel record);

    int insertSelective(MerchantApplyFormModel record);

    List<MerchantApplyFormModel> selectByExample(MerchantApplyFormModelExample example);

    MerchantApplyFormModel selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MerchantApplyFormModel record, @Param("example") MerchantApplyFormModelExample example);

    int updateByExample(@Param("record") MerchantApplyFormModel record, @Param("example") MerchantApplyFormModelExample example);

    int updateByPrimaryKeySelective(MerchantApplyFormModel record);

    int updateByPrimaryKey(MerchantApplyFormModel record);
}