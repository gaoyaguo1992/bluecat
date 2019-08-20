package com.stylefeng.guns.sharecore.common.persistence.dao;

import com.stylefeng.guns.sharecore.common.persistence.model.SysSessionInfo;

public interface SysSessionInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysSessionInfo record);

    SysSessionInfo selectByPrimaryKey(String id);
    
    int insertSelective(SysSessionInfo record);
    
    int updateByPrimaryKeySelective(SysSessionInfo record);

    int updateByPrimaryKey(SysSessionInfo record);
    
   /**
    * 删除超时的session id..
    */
   void deleteTimeOutLogin(); 
}