package com.stylefeng.guns.sharecore.modular.system.dao;

import com.stylefeng.guns.sharecore.modular.system.model.ProvinceCityZoneInfoModel;
import com.stylefeng.guns.sharecore.modular.system.model.ProvinceCityZoneInfoModelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProvinceCityZoneInfoModelMapper {

    int updateByExampleSelective(@Param("record") ProvinceCityZoneInfoModel record, @Param("example") ProvinceCityZoneInfoModelExample example);

    int updateByExample(@Param("record") ProvinceCityZoneInfoModel record, @Param("example") ProvinceCityZoneInfoModelExample example);

    int updateByPrimaryKeySelective(ProvinceCityZoneInfoModel record);

    int updateByPrimaryKey(ProvinceCityZoneInfoModel record);
    
    int countByExample(ProvinceCityZoneInfoModelExample example);

    int insertSelective(ProvinceCityZoneInfoModel record);

    int insert(ProvinceCityZoneInfoModel record);

    List<ProvinceCityZoneInfoModel> selectByExample(ProvinceCityZoneInfoModelExample example);

    ProvinceCityZoneInfoModel selectByPrimaryKey(Long id);

    int deleteByExample(ProvinceCityZoneInfoModelExample example);

    int deleteByPrimaryKey(Long id);
}