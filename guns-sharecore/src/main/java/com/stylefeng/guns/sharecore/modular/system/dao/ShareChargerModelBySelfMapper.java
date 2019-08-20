package com.stylefeng.guns.sharecore.modular.system.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.stylefeng.guns.sharecore.modular.system.model.ChargerBeyondSevenDaysNotUseRequestCommand;
import com.stylefeng.guns.sharecore.modular.system.model.DeviceChargerGroup;
import com.stylefeng.guns.sharecore.modular.system.model.LastSevenDaysBO;
import com.stylefeng.guns.sharecore.modular.system.model.ShareChargerModel;
import com.stylefeng.guns.sharecore.modular.system.model.ShareDeviceInfoModel;

public interface ShareChargerModelBySelfMapper {

    /**
     * 批量插入设备信息
     * @param list
     */
    void insertBatch(List<ShareChargerModel> list);
    
    /**
     * 查询
     * @param offset
     * @param limit
     * @param order
     * @param condition
     * @return
     */
    List<ShareChargerModel> selectByConditionForPage(HashMap<String,Object> pageFilter);
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
     * 根据充电设备号例表批量绑定费用
     * @param tmpShareDeviceInfoModel
     * @param deviceNoForList
     */
    void batchBindDeviceNoByChargerNoList(@Param("record")ShareChargerModel record,@Param("list") List<Long> list);
    /**
     * 根据充电设备开始或结束号批量绑定费用
     * @param tmpShareDeviceInfoModel
     * @param deviceNoForList
     */
    void batchBindDeviceNoByStartEndDeviceNo(@Param("record")ShareChargerModel record,@Param("start")Long start,@Param("end")Long end);
    /**
     * 得到充电器分组数据....
     * @param deviceId
     * @return
     */
    DeviceChargerGroup getDeviceChargersGroupByDeviceNo(@Param("deviceId")Long deviceId);
    
    /**
     * 查询7天内未使用汇总数据...chargerLastSevenDaysNumQuery
     * @param command
     * @return
     */
	LastSevenDaysBO chargerLastSevenDaysNumQuery(ChargerBeyondSevenDaysNotUseRequestCommand command);
	/**
	 * 查询最近7天内未使用汇总数据... chargerLastSevenDaysInfoQuery
	 * @param command
	 * @return
	 */
	List<LastSevenDaysBO> chargerLastSevenDaysInfoQuery(ChargerBeyondSevenDaysNotUseRequestCommand command);
	
}