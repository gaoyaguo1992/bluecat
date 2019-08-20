package com.stylefeng.guns.modular.system.service;

import com.stylefeng.guns.modular.system.model.DeviceInfo;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.sharecore.modular.system.model.ShareDeviceInfoBO;
import com.stylefeng.guns.sharecore.modular.system.model.ShareDeviceInfoBySelfModel;
import com.stylefeng.guns.sharecore.modular.system.model.ShareDeviceInfoModel;

/**
 * <p>
 * 设备表 服务类
 * </p>
 *
 * @author alian li
 * @since 2019-01-20
 */
public interface IDeviceInfoService extends IService<DeviceInfo> {

	void exportExcel(HttpServletResponse response, HashMap<String, Object> pageFilter)throws IOException;

	/**
	 * 判断商户指定的商户类型是否进行修改,如果商户发生修改返回true,否则反之
	 * @param deviceInfoMode 需要判断的设备
	 * @param merchantType 需要判断的商户类型
	 * @return
	 */
	boolean isBindMer(ShareDeviceInfoBySelfModel deviceInfoMode,Integer merchantType);

	/**
	 * 判断商户是否进行修改,如果商户发生修改返回true,否则反之
	 * @param existsDevice 已存在的设备
	 * @param newDevice 用于判断的新设备
	 * @return
	 */
	boolean isBindMer(ShareDeviceInfoBySelfModel existsDevice,ShareDeviceInfoBySelfModel newDevice);

	/**
	 * 兼容性
	 * @param existsDevice
	 * @param newDevice
	 * @return
	 */
	boolean isBindMer(ShareDeviceInfoBySelfModel existsDevice, ShareDeviceInfoModel newDevice);

	/**
	 * 兼容性
	 * @param existsDevice
	 * @param newDevice
	 * @return
	 */
	boolean isBindMer(ShareDeviceInfoBySelfModel existsDevice, ShareDeviceInfoBO newDevice);

	/**
	 * 兼容性
	 * @param existsDevice
	 * @param newDevice
	 * @return
	 */
	boolean isBindMer(ShareDeviceInfoModel existsDevice, ShareDeviceInfoBO newDevice);

	/**
	 * 判断起始，结束设备对应的商户是否进行修改,如果商户发生修改返回true,否则反之
	 * @param startDeviceNo
	 * @param endDeviceNo
	 * @param newDevice
	 * @return
	 */
	boolean isBindMer(Long startDeviceNo, Long endDeviceNo, ShareDeviceInfoModel newDevice);

	/**
	 * 判断listForDeviceIds集合设备对应的商户是否进行修改,如果商户发生修改返回true,否则反之
	 * @param listForDeviceIds
	 * @param newDevice
	 * @return
	 */
	boolean isBindMer(List<Long> listForDeviceIds, ShareDeviceInfoModel newDevice);

}
