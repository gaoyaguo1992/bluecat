package com.stylefeng.guns.sharecore.modular.system.dao;

import java.util.HashMap;
import java.util.List;

import com.stylefeng.guns.sharecore.common.persistence.model.CustInfoModel;

public interface CustInfoModelBySelfMapper {

    
    /**
     * 查询
     * @param offset
     * @param limit
     * @param order
     * @param condition
     * @return
     */
    List<CustInfoModel> selectByConditionForPage(HashMap<String,Object> pageFilter);
    /**
     * 查询
     * @param offset
     * @param limit
     * @param order
     * @param condition
     * @return
     */
    Long countByConditionForPage(HashMap<String,Object> pageFilter);
    
}