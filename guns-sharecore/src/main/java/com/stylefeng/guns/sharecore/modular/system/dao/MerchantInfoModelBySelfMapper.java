package com.stylefeng.guns.sharecore.modular.system.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.stylefeng.guns.sharecore.modular.system.model.ChildAgentsInfoBO;
import com.stylefeng.guns.sharecore.modular.system.model.ChildAgentsQueryCommand;
import com.stylefeng.guns.sharecore.modular.system.model.MerchantInfoModel;

public interface MerchantInfoModelBySelfMapper {

    
    /**
     * 查询
     * @param offset
     * @param limit
     * @param order
     * @param condition
     * @return
     */
    List<MerchantInfoModel> selectByConditionForPage(HashMap<String,Object> pageFilter);
    /**
     * 查询
     * @param offset
     * @param limit
     * @param order
     * @param condition
     * @return
     */
    Long countByConditionForPage(HashMap<String,Object> pageFilter);
    /**
     * 得到平台服务商...
     * @return
     */
    MerchantInfoModel getPlatformMerchantInfo();
    /**
     * 得到子商户
     * @param parentMerchantId
     * @return
     */
	List<Long> queryMerchantIdByPId(@Param("parentMerchantId")Long parentMerchantId);
    
	/**
	 * 查询所有相关商户信息..
	 * @param command
	 * @return
	 */
	List<ChildAgentsInfoBO> queryMerchantInfoAll(ChildAgentsQueryCommand command);
	/**
	 * 查询所有相关商户信息..
	 * @param command
	 * @return
	 */
	Long getMerInfoAllCount(ChildAgentsQueryCommand command);
}