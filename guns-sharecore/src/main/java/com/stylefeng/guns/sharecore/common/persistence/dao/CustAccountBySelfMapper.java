package com.stylefeng.guns.sharecore.common.persistence.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.stylefeng.guns.sharecore.common.persistence.model.CustAccountBO;
import com.stylefeng.guns.sharecore.common.persistence.model.CustAccountModel;


public interface CustAccountBySelfMapper {
    int deleteByPrimaryKey(Long accountId);

    int insert(CustAccountModel record);

    int insertSelective(CustAccountModel record);

    CustAccountModel selectByPrimaryKey(Long accountId);
    
    CustAccountModel selectByCustNoForUpdate(@Param("custNo")Long custNo,@Param("accountType") int accountType);
    
    CustAccountModel selectByCustNo(@Param("custNo")Long custNo,@Param("accountType") int accountType);
    
   
    void batchUpdateCustAccount(
    		@Param("list")List<CustAccountModel> list,
    		@Param("amtSource")Integer amtSource,
    		@Param("accountType")Integer accountType,
    		@Param("updateTime")Date updateTime);
    
    void batchCreateRewardJifenAccount(@Param("list")List<CustAccountModel> list);
    
    void moveOrigDate2JifenAccount(@Param("list")List<CustAccountModel> list);

    int updateByPrimaryKeySelective(CustAccountModel record);

    int updateByPrimaryKey(CustAccountModel record);
    
    CustAccountModel selectAccountByCustNo(@Param("custNo")Long custNo,@Param("accountType") int accountType,@Param("amtSource") int amtSource);
    
    /**
     * 通过客户号和账号类型查询
     */
    CustAccountModel selectByCustNoAndAccountType(@Param("custNo")Long custNo,@Param("accountType") int accountType);
    		
    List<Long> queryAllOfflineJiamengHasNotTsfCustAccount();
    /**
     * 查看商户可用余额
     */
    List<CustAccountModel> queryMerchantAvailableBalance(@Param("merchantType")Integer merchantType,@Param("merchantStatus")Integer merchantStatus); 
    
    CustAccountBO queryAvailableAmtCustNoByCustTypeAndMerId(@Param("custType")int custType,@Param("merchantId")Long merchantId);
    /**
     * 查看客户的总可用余额
     */
    CustAccountModel queryMerchantAvailableBalanceByCustNoAndMerchantId(@Param("custNo")Long custNo,@Param("merchantId")Long merchantId);

	List<CustAccountModel> selectListByCustNo(@Param("custNo") Long custNo); 
}