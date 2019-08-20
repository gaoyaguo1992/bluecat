package com.stylefeng.guns.sharecore.modular.system.dao;

import com.stylefeng.guns.sharecore.modular.system.model.ShareMerchantTradeProfitModel;
import com.stylefeng.guns.sharecore.modular.system.model.ShareMerchantTradeProfitModelExample;
import com.stylefeng.guns.sharecore.modular.system.model.ShareMerchantTradeProfitModelKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShareMerchantTradeProfitModelMapper {
    int countByExample(ShareMerchantTradeProfitModelExample example);

    int deleteByExample(ShareMerchantTradeProfitModelExample example);

    int deleteByPrimaryKey(ShareMerchantTradeProfitModelKey key);

    int insert(ShareMerchantTradeProfitModel record);

    int insertSelective(ShareMerchantTradeProfitModel record);

    List<ShareMerchantTradeProfitModel> selectByExample(ShareMerchantTradeProfitModelExample example);

    ShareMerchantTradeProfitModel selectByPrimaryKey(ShareMerchantTradeProfitModelKey key);

    int updateByExampleSelective(@Param("record") ShareMerchantTradeProfitModel record, @Param("example") ShareMerchantTradeProfitModelExample example);

    int updateByExample(@Param("record") ShareMerchantTradeProfitModel record, @Param("example") ShareMerchantTradeProfitModelExample example);

    int updateByPrimaryKeySelective(ShareMerchantTradeProfitModel record);

    int updateByPrimaryKey(ShareMerchantTradeProfitModel record);
}