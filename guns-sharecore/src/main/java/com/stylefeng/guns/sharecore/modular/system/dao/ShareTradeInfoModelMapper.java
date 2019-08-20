package com.stylefeng.guns.sharecore.modular.system.dao;

import com.stylefeng.guns.sharecore.modular.system.model.ShareTradeInfoModel;
import com.stylefeng.guns.sharecore.modular.system.model.ShareTradeInfoModelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShareTradeInfoModelMapper {
    int countByExample(ShareTradeInfoModelExample example);

    int deleteByExample(ShareTradeInfoModelExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ShareTradeInfoModel record);

    int insertSelective(ShareTradeInfoModel record);

    List<ShareTradeInfoModel> selectByExample(ShareTradeInfoModelExample example);

    ShareTradeInfoModel selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ShareTradeInfoModel record, @Param("example") ShareTradeInfoModelExample example);

    int updateByExample(@Param("record") ShareTradeInfoModel record, @Param("example") ShareTradeInfoModelExample example);

    int updateByPrimaryKeySelective(ShareTradeInfoModel record);

    int updateByPrimaryKey(ShareTradeInfoModel record);
}