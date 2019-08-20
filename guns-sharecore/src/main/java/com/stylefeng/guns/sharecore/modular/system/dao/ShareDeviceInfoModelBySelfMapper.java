package com.stylefeng.guns.sharecore.modular.system.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.stylefeng.guns.sharecore.modular.system.model.ChargerFewDayNotUsedBO;
import com.stylefeng.guns.sharecore.modular.system.model.DeviceUsageTrendBO;
import com.stylefeng.guns.sharecore.modular.system.model.PlatformRatioDeviceIdsBO;
import com.stylefeng.guns.sharecore.modular.system.model.ShareDeviceInfoBO;
import com.stylefeng.guns.sharecore.modular.system.model.ShareDeviceInfoBySelfModel;
import com.stylefeng.guns.sharecore.modular.system.model.ShareDeviceInfoModel;

public interface ShareDeviceInfoModelBySelfMapper {
	/**
	 * 根据充电器id得到充电器列表...
	 * 
	 * @param id
	 * @return
	 */
    ShareDeviceInfoModel getDeviceInfoByChargerId(Long chargerId);
    /**
     * 查询
     * @param offset
     * @param limit
     * @param order
     * @param condition
     * @return
     */
    List<ShareDeviceInfoBySelfModel> selectByConditionForPage(HashMap<String,Object> pageFilter);
    
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
     * 计算充电器数量..
     * @param pageFilter
     * @return
     */
    Long countDeviceChargersByConditionForPage(HashMap<String,Object> pageFilter);
    /**
     * 批量插入设备信息
     * @param list
     */
    void insertBatch(List<ShareDeviceInfoModel> list);
    /**
     * 根据充电设备号例表
     * @param tmpShareDeviceInfoModel
     * @param deviceNoForList
     */
    void batchBindMerByDeviceNoList(@Param("record")ShareDeviceInfoModel record,@Param("list") List<Long> list,@Param("filter")String filter);
    /**
     * 解绑商户..
     * @param tmpShareDeviceInfoModel
     * @param deviceNoForList
     */
    void batchUnBindMerByDeviceNoList(Map<String,Object> map);
    
    /**
     * 根据充电设备开始或结束号
     * @param tmpShareDeviceInfoModel
     * @param deviceNoForList
     */
    void batchBindMerByStartEndDeviceNo(@Param("record")ShareDeviceInfoModel record,@Param("start")Long start,@Param("end")Long end,@Param("filter")String filter);
    /**
     * 根据充电设备号例表批量绑定费用
     * @param tmpShareDeviceInfoModel
     * @param deviceNoForList
     */
    void batchBindFeeByDeviceNoList(@Param("record")ShareDeviceInfoModel record,@Param("list") List<Long> list,@Param("filter")String filter);
    
    /**
     * 根据充电设备开始或结束号批量绑定费用
     * @param tmpShareDeviceInfoModel
     * @param deviceNoForList
     */
    void batchBindFeeByStartEndDeviceNo(@Param("record")ShareDeviceInfoModel record,@Param("start")Long start,@Param("end")Long end,@Param("filter")String filter); 
    /**
     * 根据充电设备开始或结束号批量绑定费用
     */
    void batchBindFeeByOnlineMerchId(@Param("record")ShareDeviceInfoModel record,@Param("merchId")Long merchId,@Param("filter") String filter);
    /**
     * 根据充电设备号例表批量绑定费用
     * @param tmpShareDeviceInfoModel
     * @param deviceNoForList
     */
    void batchModifyByDeviceNoList(@Param("record")ShareDeviceInfoModel record,@Param("list") List<Long> list,@Param("filter") String filter);
    /**
     * 根据充电设备开始或结束号批量绑定费用
     * @param tmpShareDeviceInfoModel
     * @param deviceNoForList
     */
    void batchModifyByStartEndDeviceNo(@Param("record")ShareDeviceInfoModel record,@Param("start")Long start,@Param("end")Long end,@Param("filter") String filter);
    /**
     * 根据充电设备开始或结束号批量绑定费用
     */
    void batchModifyByOnlineMerchId(@Param("record")ShareDeviceInfoModel record,@Param("merchId")Long merchId,@Param("filter") String filter);
    /**
     * 根据充电设备号例表批量绑定费用
     * @param tmpShareDeviceInfoModel
     * @param deviceNoForList
     */
    void batchRatioByDeviceNoList(@Param("record")ShareDeviceInfoModel record,@Param("list") List<Long> list,@Param("filter") String filter);
    /**
     * 根据充电设备号例表批量绑定费用 用户商..
     * @param tmpShareDeviceInfoModel
     * @param deviceNoForList
     */
    void batchRatioByDeviceNoListForUser(@Param("record")ShareDeviceInfoModel record,@Param("list") List<Long> list,@Param("filter") String filter);
    /**
     * 根据充电设备开始或结束号批量绑定费用
     * @param tmpShareDeviceInfoModel
     * @param deviceNoForList
     */
    void batchRatioByStartEndDeviceNo(@Param("record")ShareDeviceInfoModel record,@Param("start")Long start,@Param("end")Long end,@Param("filter") String filter);
    /**
     * 根据充电设备开始或结束号批量绑定费用
     */
    void batchRatioByOnlineMerchId(@Param("record")ShareDeviceInfoModel record,@Param("merchId")Long merchId,@Param("filter") String filter);
    /**
     * 根据过虙条件，查询设备类型...    
     * @param pageFilter
     * @return
     */
    List<ShareDeviceInfoModel> getShareDeviceInfoModelByPageFilter(HashMap<String,Object> pageFilter);
	
	/**
	 * 得到商户名下已绑定的设备数...
	 * @param merchantId
	 * @return
	 */
	Long getDevicesCountByMerchantId(@Param("merchantId")Long merchantId);

	/**
	 * 得到商户名下已绑定的已激活的设备数...
	 * @param merchantId
	 * @return
	 */
	Long getActiveDevicesCountByMerchantId(@Param("merchantId")Long merchantId);
	/**
	 * 得到商户名下已绑定的未激活的设备数...
	 * @param merchantId
	 * @return
	 */
	Long getInactiveDevicesCountByMerchantId(@Param("merchantId")Long merchantId);
	
	/**
	 * 根据查询条件，得到设备。。
	 * @param pageFilter
	 * @return
	 */
    List<ShareDeviceInfoModel> getDevicesInfoByCondition(HashMap<String,Object> pageFilter);
    /**
     * 通过平台分组得到各分组平台分润比例
     * @param pageFilter
     * @return
     */
    List<PlatformRatioDeviceIdsBO> selectDeviceIdsGroupbyPlatformRatio(HashMap<String,Object> pageFilter);
    /**
     * 设置有个函数叫“group_concat”，平常使用可能发现不了问题，在处理大数据的时候，会发现内容被截取了，其实MYSQL内部对这个是有设置的，默认不设置的长度是1024
     */
    void setGroupConcatMaxLen(); 
    
    /**
     * 根据设备号查询七天内设备使用次数
     * @param deviceIdStr
     * @return
     */
    List<DeviceUsageTrendBO> getUsageTrendData(List<Long> list);
    
	Long countBeyondDaysNotUseCharger(HashMap<String, Object> pageFilter);
	
	List<Long> queryMerchantId(@Param("cloumName")String cloumName,@Param("parentMerchantId")Long parentMerchantId,@Param("group")String group);
	
	Long countMerchantBeyondDaysNotUseCharger(HashMap<String, Object> filter);
	
	/**
	 * 七天或三十天未使用的充电器信息列表
	 * @param pageFilter
	 * @return
	 */
	List<ChargerFewDayNotUsedBO> nousedChargerFewDays(HashMap<String, Object> pageFilter);


    void update(ShareDeviceInfoBO deviceInfo);
    
    void batchBindFeeByDeviceNoListNoValidate(@Param("record")ShareDeviceInfoModel record,@Param("list") List<Long> list,@Param("filter")String filter);
}