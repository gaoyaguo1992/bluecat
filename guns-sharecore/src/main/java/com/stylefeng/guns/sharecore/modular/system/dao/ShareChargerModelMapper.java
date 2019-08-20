package com.stylefeng.guns.sharecore.modular.system.dao;

import com.stylefeng.guns.sharecore.modular.system.model.ShareChargerModel;
import com.stylefeng.guns.sharecore.modular.system.model.ShareChargerModelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShareChargerModelMapper {
    int countByExample(ShareChargerModelExample example);

    int deleteByExample(ShareChargerModelExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ShareChargerModel record);

    int insertSelective(ShareChargerModel record);

    List<ShareChargerModel> selectByExample(ShareChargerModelExample example);

    ShareChargerModel selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ShareChargerModel record, @Param("example") ShareChargerModelExample example);

    int updateByExample(@Param("record") ShareChargerModel record, @Param("example") ShareChargerModelExample example);

    int updateByPrimaryKeySelective(ShareChargerModel record);

    int updateByPrimaryKey(ShareChargerModel record);
}