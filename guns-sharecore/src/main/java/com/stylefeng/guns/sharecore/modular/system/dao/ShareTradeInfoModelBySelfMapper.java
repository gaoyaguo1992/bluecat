package com.stylefeng.guns.sharecore.modular.system.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.stylefeng.guns.sharecore.modular.system.model.RechargeAndWithdrawBO;
import com.stylefeng.guns.sharecore.modular.system.model.ShareDeviceInfoModel;
import com.stylefeng.guns.sharecore.modular.system.model.ShareTradeInfoBySelfModel;
import com.stylefeng.guns.sharecore.modular.system.model.ShareTradeInfoModel;
import com.stylefeng.guns.sharecore.modular.system.model.ShareTradeInfoModelSearchBO;
import com.stylefeng.guns.sharecore.modular.system.model.TotalModel;
import com.stylefeng.guns.sharecore.modular.system.model.ShareTrdTotalInfo;
import com.stylefeng.guns.sharecore.modular.system.model.WithdrawBO;
import com.stylefeng.guns.sharecore.modular.system.model.WithdrawTradeInfoModel;

public interface ShareTradeInfoModelBySelfMapper {
	/**
	 * 根据客户id得到客户订单数..
	 * @param custNo
	 * @return
	 */
    Long selectOrderCountByCustNo(Long custNo);
    /**
     * 未结束的订单
     * @param custNo
     * @param status
     * @return
     */
    Long selectUnfinishedOrderCountByCustNo(@Param("custNo")Long custNo,@Param("status")int status);
    /**
     * 查询订单信息..
     * @param paramMap
     * @return
     */
    List<ShareTradeInfoBySelfModel> selectOrdersForPageByFilter(HashMap<String,Object> paramMap);
    
    /**
     * 查询订单信息..
     * @param paramMap
     * @return
     */
    List<ShareTradeInfoBySelfModel> selectMerchantOrdersForPageByFilter(HashMap<String,Object> paramMap);
    
    /**
     * 计算订单汇总数据...
     * @param paramMap
     * @return
     */
    ShareTrdTotalInfo countMerchantOrdersForPageByFilter(HashMap<String,Object> paramMap);
    /**
     * 
     * 获起用户提现明细记录...
     * @return
     */
    List<WithdrawTradeInfoModel> selectWithdrawForPage(RechargeAndWithdrawBO rechargeAndWithdrawBO);
    
    /**
     * 根据交易id得到交易对应的提现记录...
     * @param tradeId
     * @return
     */
    List<WithdrawBO> getWithdrawItemList(Long tradeId);
    
    /**
     * 查询并分页
     * @param offset
     * @param limit
     * @param order
     * @param condition
     * @return
     */
    List<ShareTradeInfoModelSearchBO> selectByConditionForPage(HashMap<String,Object> pageFilter);
    /**
     * 查询
     * @param offset
     * @param limit
     * @param order
     * @param condition
     * @return
     */
    TotalModel countByConditionForPage(HashMap<String,Object> pageFilter);
    
    /**
     * 得到所有需要归还的交易订单
     * @return
     */
    List<Long> getMustBackTradeOrders();  
    /**
     * 得到所有要分润的订单
     * @return
     */
    List<Long> getMustBenefitTradeOrders();  
    
    
    /**
     * 得到所有需要归还的交易订单
     * @return
     */
    ShareTradeInfoModel getShareTradeInfoModelByIdForUpdate(@Param("id")Long id);
    
    /**
     * 根据设备id查询当前一天交易数量（今天）
     * @param deviceId
     * @return
     */
	Integer queryCurrentCountByDeviceId(@Param("deviceId")Long deviceId); 
    
   
}