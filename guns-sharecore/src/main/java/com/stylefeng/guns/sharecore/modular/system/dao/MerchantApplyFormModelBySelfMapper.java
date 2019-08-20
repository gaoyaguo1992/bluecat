package com.stylefeng.guns.sharecore.modular.system.dao;

import java.util.HashMap;
import java.util.List;

import com.stylefeng.guns.sharecore.modular.system.model.MerchantApplyFormModel;


public interface MerchantApplyFormModelBySelfMapper {
	 /**
     * 查询
     * @param offset
     * @param limit
     * @param order
     * @param condition
     * @return
     */
    List<MerchantApplyFormModel> selectByConditionForPage(HashMap<String,Object> pageFilter);
    
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