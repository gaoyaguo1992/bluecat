package com.stylefeng.guns.sharecore.modular.system.dao;

import com.stylefeng.guns.sharecore.modular.system.model.MerchantProfitDayModel;
import com.stylefeng.guns.sharecore.modular.system.model.MerchantProfitDayModelExample;
import com.stylefeng.guns.sharecore.modular.system.model.MerchantProfitDayModelKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MerchantProfitDayModelMapper {
    int countByExample(MerchantProfitDayModelExample example);

    int deleteByExample(MerchantProfitDayModelExample example);

    int deleteByPrimaryKey(MerchantProfitDayModelKey key);

    int insert(MerchantProfitDayModel record);

    int insertSelective(MerchantProfitDayModel record);

    List<MerchantProfitDayModel> selectByExample(MerchantProfitDayModelExample example);

    MerchantProfitDayModel selectByPrimaryKey(MerchantProfitDayModelKey key);

    int updateByExampleSelective(@Param("record") MerchantProfitDayModel record, @Param("example") MerchantProfitDayModelExample example);

    int updateByExample(@Param("record") MerchantProfitDayModel record, @Param("example") MerchantProfitDayModelExample example);

    int updateByPrimaryKeySelective(MerchantProfitDayModel record);

    int updateByPrimaryKey(MerchantProfitDayModel record);
}