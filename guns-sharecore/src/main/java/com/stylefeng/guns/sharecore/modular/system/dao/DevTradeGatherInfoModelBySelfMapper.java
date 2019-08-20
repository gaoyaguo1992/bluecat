package com.stylefeng.guns.sharecore.modular.system.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.stylefeng.guns.sharecore.modular.system.model.DevTradeGatherInfoModel;
import com.stylefeng.guns.sharecore.modular.system.model.DevTradeGatherInfoModelExample;

public interface DevTradeGatherInfoModelBySelfMapper {
    /**
     * 更新统计任务最后执行时间...	
     */
    void updateDevTradeGaterTask();
    /**
     * 初始设备统计表..以便下一步计算...主要是处理新增的...    
     * @return
     */
    int initCalculateDeviceData();
    /**
     * 更新调设备统计表... OrdersCnt
     * @param updateDate
     */
    void updateCalculateDeviceDataOrdersCnt(@Param("updateDate")Date updateDate);
    /**
     * 更新调设备统计表... OrdersCnt1
     * @param updateDate
     */
    void updateCalculateDeviceDataOrdersCnt1(@Param("updateDate")Date updateDate);
    /**
     * 更新调设备统计表... OrdersCnt2
     * @param updateDate
     */
    void updateCalculateDeviceDataOrdersCnt2(@Param("updateDate")Date updateDate);
    /**
     * 更新调设备统计表... OrdersCnt3
     * @param updateDate
     */
    void updateCalculateDeviceDataOrdersCnt3(@Param("updateDate")Date updateDate);
    /**
     * 更新调设备统计表... OrdersCnt4
     * @param updateDate
     */
    void updateCalculateDeviceDataOrdersCnt4(@Param("updateDate")Date updateDate);
    /**
     * 更新调设备统计表... OrdersCnt5
     * @param updateDate
     */
    void updateCalculateDeviceDataOrdersCnt5(@Param("updateDate")Date updateDate);
    /**
     * 更新调设备统计表... OrdersCnt6
     * @param updateDate
     */
    void updateCalculateDeviceDataOrdersCnt6(@Param("updateDate")Date updateDate);
    /**
     * 更新调设备统计表... OrdersCnt7
     * @param updateDate
     */
    void updateCalculateDeviceDataOrdersCnt7(@Param("updateDate")Date updateDate);
    
    
    
    /**
     * 更新调设备统计表...处理完成的订单...
     * @param updateDate
     */
    void updateCalDeviceDataForFinish(@Param("updateDate")Date updateDate);
    /**
     * 更新调设备统计表...处理完成的订单1...
     * @param updateDate
     */
    void updateCalDeviceDataForFinish1(@Param("updateDate")Date updateDate);
    /**
     * 更新调设备统计表...处理完成的订单..2.
     * @param updateDate
     */
    void updateCalDeviceDataForFinish2(@Param("updateDate")Date updateDate);
    /**
     * 更新调设备统计表...处理完成的订单...3
     * @param updateDate
     */
    void updateCalDeviceDataForFinish3(@Param("updateDate")Date updateDate);
    /**
     * 更新调设备统计表...处理完成的订单...4
     * @param updateDate
     */
    void updateCalDeviceDataForFinish4(@Param("updateDate")Date updateDate);
    /**
     * 更新调设备统计表...处理完成的订单..5
     * @param updateDate
     */
    void updateCalDeviceDataForFinish5(@Param("updateDate")Date updateDate);
    /**
     * 更新调设备统计表...处理完成的订单..6
     * @param updateDate
     */
    void updateCalDeviceDataForFinish6(@Param("updateDate")Date updateDate);
    /**
     * 更新调设备统计表...处理完成的订单..7
     * @param updateDate
     */
    void updateCalDeviceDataForFinish7(@Param("updateDate")Date updateDate);
    /**
     * 更新计算时间
     * @param updateDate
     */
    void updateCalDeviceDataForUpdateDate(@Param("updateDate")Date updateDate);
    /**
     * 初始化商户数据..        
     * @return
     */
    int initCalculateMerchantData();
    /**
     * 更新代理商 QTY
     * @param updateDate
     */
    void updateCalMerDtForUpdateDateQty(@Param("updateDate")Date updateDate);
    /**
     * 更新代理商1 Amount
     * @param updateDate
     */
    void updateCalMerDtAg1ForUpdateDateAmount(@Param("updateDate")Date updateDate);
    /**
     * 更新代理商1 cnt7
     * @param updateDate
     */
    void updateCalMerDtAg1ForUpdateDateCNT7(@Param("updateDate")Date updateDate);
    /**
     * 更新代理商2 amount
     * @param updateDate
     */
    void updateCalMerDtAg2ForUpdateDateAmount(@Param("updateDate")Date updateDate);
    /**
     * 更新代理商2 CNT7
     * @param updateDate
     */
    void updateCalMerDtAg2ForUpdateDateCNT7(@Param("updateDate")Date updateDate);
    /**
     * 更新代理商3Amount
     * @param updateDate
     */
    void updateCalMerDtAg3ForUpdateDateAmount(@Param("updateDate")Date updateDate);
    /**
     * 更新代理商3 CNT7
     * @param updateDate
     */
    void updateCalMerDtAg3ForUpdateDateCNT7(@Param("updateDate")Date updateDate);
    /**
     * 更铺货商 Amount
     * @param updateDate
     */
    void updateCalMerDtKeeperForUpdateDateAmount(@Param("updateDate")Date updateDate);
    /**
     * 更铺货商 Amount
     * @param updateDate
     */
    void updateCalMerDtKeeperForUpdateDateCNT7(@Param("updateDate")Date updateDate); 
    /**
     * 更加盟商 Amount
     * @param updateDate
     */
    void updateCalMerDtAlliForUpdateDateAmount(@Param("updateDate")Date updateDate);
    /**
     * 更加盟商CNT7
     * @param updateDate
     */
    void updateCalMerDtAlliForUpdateDateCNT7(@Param("updateDate")Date updateDate);   
    /**
     * 更新店铺id amount
     * @param updateDate
     */
    void updateCalMerDtMerForUpdateDateAmount(@Param("updateDate")Date updateDate); 
    /**
     * 更新店铺id amount
     * @param updateDate
     */
    void updateCalMerDtMerForUpdateDateCNT7(@Param("updateDate")Date updateDate);
    /**
     * 更新时间
     * @param updateDate
     */
    void updateCalMerDtForUpdateDate(@Param("updateDate")Date updateDate);
    /**
     * 7天使用率...
     */
    void updateCalMerDtFor7daysUsageRate();
    /**
     * 7天使用率...为0
     */
    void updateCalMerDtFor7daysUsageRate0();
    /**
     * 查询设备id
     * @param deviceId
     * @return
     */
    DevTradeGatherInfoModel selectByDeviceId(Long deviceId);
}