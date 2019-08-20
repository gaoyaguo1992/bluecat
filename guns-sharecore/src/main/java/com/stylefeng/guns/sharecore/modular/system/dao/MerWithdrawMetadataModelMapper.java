package com.stylefeng.guns.sharecore.modular.system.dao;

import com.stylefeng.guns.sharecore.modular.system.model.MerWithdrawMetadataModel;
import com.stylefeng.guns.sharecore.modular.system.model.MerWithdrawMetadataModelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MerWithdrawMetadataModelMapper {

    int updateByExampleSelective(@Param("record") MerWithdrawMetadataModel record, @Param("example") MerWithdrawMetadataModelExample example);

    int updateByExample(@Param("record") MerWithdrawMetadataModel record, @Param("example") MerWithdrawMetadataModelExample example);

    int updateByPrimaryKeySelective(MerWithdrawMetadataModel record);

    int updateByPrimaryKey(MerWithdrawMetadataModel record);
    
    int countByExample(MerWithdrawMetadataModelExample example);

    int deleteByExample(MerWithdrawMetadataModelExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MerWithdrawMetadataModel record);

    int insertSelective(MerWithdrawMetadataModel record);

    List<MerWithdrawMetadataModel> selectByExample(MerWithdrawMetadataModelExample example);

    MerWithdrawMetadataModel selectByPrimaryKey(Long id);
}