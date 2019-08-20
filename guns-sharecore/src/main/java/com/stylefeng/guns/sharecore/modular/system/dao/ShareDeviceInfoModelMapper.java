package com.stylefeng.guns.sharecore.modular.system.dao;

import com.stylefeng.guns.sharecore.modular.system.model.ShareDeviceInfoModel;
import com.stylefeng.guns.sharecore.modular.system.model.ShareDeviceInfoModelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShareDeviceInfoModelMapper {
    int countByExample(ShareDeviceInfoModelExample example);

    int deleteByExample(ShareDeviceInfoModelExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ShareDeviceInfoModel record);

    int insertSelective(ShareDeviceInfoModel record);

    List<ShareDeviceInfoModel> selectByExample(ShareDeviceInfoModelExample example);

    ShareDeviceInfoModel selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ShareDeviceInfoModel record, @Param("example") ShareDeviceInfoModelExample example);

    int updateByExample(@Param("record") ShareDeviceInfoModel record, @Param("example") ShareDeviceInfoModelExample example);

    int updateByPrimaryKeySelective(ShareDeviceInfoModel record);

    int updateByPrimaryKey(ShareDeviceInfoModel record);
}