package com.stylefeng.guns.sharecore.modular.system.dao;

import com.stylefeng.guns.sharecore.modular.system.model.TaskRunInfoModel;
import com.stylefeng.guns.sharecore.modular.system.model.TaskRunInfoModelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaskRunInfoModelMapper {
    int countByExample(TaskRunInfoModelExample example);

    int deleteByExample(TaskRunInfoModelExample example);

    int deleteByPrimaryKey(String taskName);

    int insert(TaskRunInfoModel record);

    int insertSelective(TaskRunInfoModel record);

    List<TaskRunInfoModel> selectByExample(TaskRunInfoModelExample example);

    TaskRunInfoModel selectByPrimaryKey(String taskName);

    int updateByExampleSelective(@Param("record") TaskRunInfoModel record, @Param("example") TaskRunInfoModelExample example);

    int updateByExample(@Param("record") TaskRunInfoModel record, @Param("example") TaskRunInfoModelExample example);

    int updateByPrimaryKeySelective(TaskRunInfoModel record);

    int updateByPrimaryKey(TaskRunInfoModel record);
}