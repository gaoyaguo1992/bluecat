package com.stylefeng.guns.sharecore.modular.system.dao;

import com.stylefeng.guns.sharecore.modular.system.model.ShareTradeDeviceInfoModel;
import com.stylefeng.guns.sharecore.modular.system.model.ShareTradeDeviceInfoModelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;


public interface ShareTradeDeviceInfoModelMapper {
    int countByExample(ShareTradeDeviceInfoModelExample example);

    int deleteByExample(ShareTradeDeviceInfoModelExample example);

    int deleteByPrimaryKey(Long tradeId);

    int insert(ShareTradeDeviceInfoModel record);

    int insertSelective(ShareTradeDeviceInfoModel record);

    List<ShareTradeDeviceInfoModel> selectByExample(ShareTradeDeviceInfoModelExample example);

    ShareTradeDeviceInfoModel selectByPrimaryKey(Long tradeId);

    int updateByExampleSelective(@Param("record") ShareTradeDeviceInfoModel record, @Param("example") ShareTradeDeviceInfoModelExample example);

    int updateByExample(@Param("record") ShareTradeDeviceInfoModel record, @Param("example") ShareTradeDeviceInfoModelExample example);

    int updateByPrimaryKeySelective(ShareTradeDeviceInfoModel record);

    int updateByPrimaryKey(ShareTradeDeviceInfoModel record);
}