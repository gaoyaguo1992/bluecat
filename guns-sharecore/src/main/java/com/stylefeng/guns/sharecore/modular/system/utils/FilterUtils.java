package com.stylefeng.guns.sharecore.modular.system.utils;

import org.apache.commons.lang.StringUtils;

import com.stylefeng.guns.sharecore.modular.system.model.MerchantTypeEnum;
import com.stylefeng.guns.sharecore.modular.system.model.RoleMerchantRefInfoModel;

/**
 * 数据权限工具类
 * 
 * @author seven
 *
 */
public class FilterUtils {

	/**
	 * 按设备维度，添加数据查询权限(查商户)
	 * 
	 * @param filterDevice 
	 * @param roleMerchantRefInfoModel 商户权限关联关系
	 * @return
	 */
	public static String buildDeviceFilter(String filterDevice, RoleMerchantRefInfoModel roleMerchantRefInfoModel) {
		if (StringUtils.isEmpty(filterDevice)) {
			filterDevice = "";
		}
		if (roleMerchantRefInfoModel.getMerchantType() == null) {
			return filterDevice;
		}
		// 绑定顶级商户数据权限，能看此顶级商户所属设备的一二级代理，加盟，铺货，终端
		if (roleMerchantRefInfoModel.getMerchantType().intValue() == MerchantTypeEnum.DAI_LI_SHANG1.getCode()
				.intValue()) {
			filterDevice = (filterDevice.isEmpty()) ? String.format(
					"mi.id in(select  t.online_merchant_id from share_device_info t where t.agents1_id=%d union "
							+ "select  t.shopkeeper_id from share_device_info t where t.agents1_id=%d union "
							+ "select  t.alliance_business_id from share_device_info t where t.agents1_id=%d union "
							+ "select  t.agents3_id from share_device_info t where t.agents1_id=%d union "
							+ "select  t.agents2_id from share_device_info t where t.agents1_id=%d)",
					roleMerchantRefInfoModel.getMerchantId(), roleMerchantRefInfoModel.getMerchantId(),
					roleMerchantRefInfoModel.getMerchantId(), roleMerchantRefInfoModel.getMerchantId(),
					roleMerchantRefInfoModel.getMerchantId())
					: String.format(
							"mi.id in(select  t.online_merchant_id from share_device_info t where t.agents1_id=%d union "
									+ "select  t.shopkeeper_id from share_device_info t where t.agents1_id=%d union "
									+ "select  t.alliance_business_id from share_device_info t where t.agents1_id=%d union "
									+ "select  t.agents3_id from share_device_info t where t.agents1_id=%d union "
									+ "select  t.agents2_id from share_device_info t where t.agents1_id=%d) or %s",
							roleMerchantRefInfoModel.getMerchantId(), roleMerchantRefInfoModel.getMerchantId(),
							roleMerchantRefInfoModel.getMerchantId(), roleMerchantRefInfoModel.getMerchantId(),
							roleMerchantRefInfoModel.getMerchantId(), filterDevice);
		}
		// 绑定一级商户数据权限，能看此一级商户所属设备的二级代理，加盟，铺货，终端
		if (roleMerchantRefInfoModel.getMerchantType().intValue() == MerchantTypeEnum.DAI_LI_SHANG2.getCode()
				.intValue()) {
			filterDevice = (filterDevice.isEmpty()) ? String.format(
					"mi.id in(select  t.online_merchant_id from share_device_info t where t.agents2_id=%d union "
							+ "select  t.shopkeeper_id from share_device_info t where t.agents2_id=%d union "
							+ "select  t.alliance_business_id from share_device_info t where t.agents2_id=%d union "
							+ "select  t.agents3_id from share_device_info t where t.agents2_id=%d)",
					roleMerchantRefInfoModel.getMerchantId(), roleMerchantRefInfoModel.getMerchantId(),
					roleMerchantRefInfoModel.getMerchantId(), roleMerchantRefInfoModel.getMerchantId())
					: String.format(
							"mi.id in(select  t.online_merchant_id from share_device_info t where t.agents2_id=%d union "
									+ "select  t.shopkeeper_id from share_device_info t where t.agents2_id=%d union "
									+ "select  t.alliance_business_id from share_device_info t where t.agents2_id=%d union "
									+ "select  t.agents3_id from share_device_info t where t.agents2_id=%d) or %s",
							roleMerchantRefInfoModel.getMerchantId(), roleMerchantRefInfoModel.getMerchantId(),
							roleMerchantRefInfoModel.getMerchantId(), roleMerchantRefInfoModel.getMerchantId(),
							filterDevice);
		}
		// 绑定二级商户数据权限，能看此二级商户所属设备的加盟，铺货，终端
		if (roleMerchantRefInfoModel.getMerchantType().intValue() == MerchantTypeEnum.DAI_LI_SHANG3.getCode()
				.intValue()) {
			filterDevice = (filterDevice.isEmpty()) ? String.format(
					"mi.id in(select  t.online_merchant_id from share_device_info t where t.agents3_id=%d union "
							+ "select  t.shopkeeper_id from share_device_info t where t.agents3_id=%d union "
							+ "select  t.alliance_business_id from share_device_info t where t.agents3_id=%d)",
					roleMerchantRefInfoModel.getMerchantId(), roleMerchantRefInfoModel.getMerchantId(),
					roleMerchantRefInfoModel.getMerchantId())
					: String.format(
							"mi.id in(select  t.online_merchant_id from share_device_info t where t.agents3_id=%d union "
									+ "select  t.shopkeeper_id from share_device_info t where t.agents3_id=%d union "
									+ "select  t.alliance_business_id from share_device_info t where t.agents3_id=%d) or %s",
							roleMerchantRefInfoModel.getMerchantId(), roleMerchantRefInfoModel.getMerchantId(),
							roleMerchantRefInfoModel.getMerchantId(), filterDevice);
		}
		// 绑定铺货商户数据权限，能看此铺货商户所属设备的加盟，终端
		if (roleMerchantRefInfoModel.getMerchantType().intValue() == MerchantTypeEnum.PUHUO_SHANG.getCode()
				.intValue()) {
			filterDevice = (filterDevice.isEmpty()) ? String.format(
					"mi.id in(select  t.online_merchant_id from share_device_info t where t.shopkeeper_id=%d union "
							+ "select  t.alliance_business_id from share_device_info t where t.shopkeeper_id=%d)",
					roleMerchantRefInfoModel.getMerchantId(), roleMerchantRefInfoModel.getMerchantId())
					: String.format(
							"mi.id in(select  t.online_merchant_id from share_device_info t where t.shopkeeper_id=%d union "
									+ "select  t.alliance_business_id from share_device_info t where t.shopkeeper_id=%d) or %s",
							roleMerchantRefInfoModel.getMerchantId(), roleMerchantRefInfoModel.getMerchantId(),
							filterDevice);
		}
		// 绑定加盟商户数据权限，能看此加盟商户所属设备的终端
		if (roleMerchantRefInfoModel.getMerchantType().intValue() == MerchantTypeEnum.JIA_MENG_SHANG.getCode()
				.intValue()) {
			filterDevice = (filterDevice.isEmpty()) ? String.format(
					"mi.id in(select  t.online_merchant_id from share_device_info t where t.alliance_business_id=%d)",
					roleMerchantRefInfoModel.getMerchantId())
					: String.format(
							"mi.id in(select  t.online_merchant_id from share_device_info t where t.alliance_business_id=%d) or %s",
							roleMerchantRefInfoModel.getMerchantId(), filterDevice);
		}

		return filterDevice;
	}

}
