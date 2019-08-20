package com.stylefeng.guns.sharecore.modular.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.sharecore.modular.system.model.CalculateDeviceDataModel;
import com.stylefeng.guns.sharecore.modular.system.model.CalculateDeviceDataModelExample;

public interface CalculateDeviceDataModelMapper extends BaseMapper<CalculateDeviceDataModel>{
    int countByExample(CalculateDeviceDataModelExample example);

    int deleteByExample(CalculateDeviceDataModelExample example);

    int deleteByPrimaryKey(Long deviceId);

    int insertSelective(CalculateDeviceDataModel record);

    List<CalculateDeviceDataModel> selectByExample(CalculateDeviceDataModelExample example);

    CalculateDeviceDataModel selectByPrimaryKey(Long deviceId);

    int updateByExampleSelective(@Param("record") CalculateDeviceDataModel record, @Param("example") CalculateDeviceDataModelExample example);

    int updateByExample(@Param("record") CalculateDeviceDataModel record, @Param("example") CalculateDeviceDataModelExample example);

    int updateByPrimaryKeySelective(CalculateDeviceDataModel record);

    int updateByPrimaryKey(CalculateDeviceDataModel record);
}