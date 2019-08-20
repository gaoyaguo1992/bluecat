package com.stylefeng.guns.sharecore.modular.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.stylefeng.guns.sharecore.modular.system.model.CapitalChangeFlowModel;

public interface CapitalChangeFlowModelMapper {
    int deleteByPrimaryKey(Long flowId);

    int insert(CapitalChangeFlowModel record);

    int insertSelective(CapitalChangeFlowModel record);

    CapitalChangeFlowModel selectByPrimaryKey(Long flowId);
    
    List<CapitalChangeFlowModel> selectAllUnSumedFlowByPtCustNo(@Param("custNo")Long custNo,@Param("isSumed")Integer isSumed);

    int updateByPrimaryKeySelective(CapitalChangeFlowModel record);

    int updateByPrimaryKey(CapitalChangeFlowModel record);
}