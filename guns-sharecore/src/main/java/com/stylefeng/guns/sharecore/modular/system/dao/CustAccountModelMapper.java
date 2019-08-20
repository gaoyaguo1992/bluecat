package com.stylefeng.guns.sharecore.modular.system.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.stylefeng.guns.sharecore.common.persistence.model.CustAccountBO;
import com.stylefeng.guns.sharecore.common.persistence.model.CustAccountModel;

public interface CustAccountModelMapper {
    int deleteByPrimaryKey(Long accountId);

    int insert(CustAccountModel record);

    int insertSelective(CustAccountModel record);

    CustAccountModel selectByCustNo(@Param("custNo")Long custNo,@Param("accountType") int accountType);
    
    CustAccountModel selectByPrimaryKey(Long accountId);
    
    CustAccountModel selectByCustNoForUpdate(@Param("custNo")Long custNo,@Param("accountType") int accountType);
    
    int updateByPrimaryKeySelective(CustAccountModel record);

    int updateByPrimaryKey(CustAccountModel record);
     /**
     * 通过客户号和账号类型查询
     */
    CustAccountModel selectByCustNoAndAccountType(@Param("custNo")Long custNo,@Param("accountType") int accountType);
    /**
     * 得到用户账号 
     * @return
     */
    CustAccountModel selectAccountByCustNo(@Param("custNo")Long custNo,@Param("accountType") int accountType,@Param("amtSource") int amtSource);
   }