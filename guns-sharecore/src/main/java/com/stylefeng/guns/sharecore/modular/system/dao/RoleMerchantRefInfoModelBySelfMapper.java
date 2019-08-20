package com.stylefeng.guns.sharecore.modular.system.dao;

import com.stylefeng.guns.sharecore.modular.system.model.RoleMerchantRefInfoModel;
import com.stylefeng.guns.sharecore.modular.system.model.RoleMerchantRefInfoModelExample;
import com.stylefeng.guns.sharecore.modular.system.model.RoleMerchantRefInfoModelKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleMerchantRefInfoModelBySelfMapper {
	/**
	 * 得到商户角色列表
	 * @param listRoleId
	 * @return
	 */
	List<RoleMerchantRefInfoModel> getRoleMerchantRefInfoList(List<Integer> list);
	

	/**
	 * 得到商户角色列表通过商户id
	 * @param listRoleId
	 * @return
	 */
	List<RoleMerchantRefInfoModel> getRoleMerchantRefInfoListByMerchantId(@Param("list")List<Integer> list,@Param("merchantId") Long merchantId);
} 