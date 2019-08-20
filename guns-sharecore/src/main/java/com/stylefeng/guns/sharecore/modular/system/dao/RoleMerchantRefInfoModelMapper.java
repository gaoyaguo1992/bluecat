package com.stylefeng.guns.sharecore.modular.system.dao;

import com.stylefeng.guns.sharecore.modular.system.model.RoleMerchantRefInfoModel;
import com.stylefeng.guns.sharecore.modular.system.model.RoleMerchantRefInfoModelExample;
import com.stylefeng.guns.sharecore.modular.system.model.RoleMerchantRefInfoModelKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleMerchantRefInfoModelMapper {
    int countByExample(RoleMerchantRefInfoModelExample example);

    int deleteByExample(RoleMerchantRefInfoModelExample example);

    int deleteByPrimaryKey(RoleMerchantRefInfoModelKey key);

    int insert(RoleMerchantRefInfoModel record);

    int insertSelective(RoleMerchantRefInfoModel record);

    List<RoleMerchantRefInfoModel> selectByExample(RoleMerchantRefInfoModelExample example);

    RoleMerchantRefInfoModel selectByPrimaryKey(RoleMerchantRefInfoModelKey key);

    int updateByExampleSelective(@Param("record") RoleMerchantRefInfoModel record, @Param("example") RoleMerchantRefInfoModelExample example);

    int updateByExample(@Param("record") RoleMerchantRefInfoModel record, @Param("example") RoleMerchantRefInfoModelExample example);

    int updateByPrimaryKeySelective(RoleMerchantRefInfoModel record);

    int updateByPrimaryKey(RoleMerchantRefInfoModel record);
}