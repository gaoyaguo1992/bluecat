package com.stylefeng.guns.modular.system.warpper;

import java.util.List;
import java.util.Map;

import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;
import com.stylefeng.guns.sharecore.modular.system.model.MerchantTypeEnum;
import com.stylefeng.guns.sharecore.modular.system.model.ShareTradeInfoModelSearchBO;

/**
 * 过滤交易信息字段
 * 
 * @author seven
 *
 */
public class ShareTradeInfoWarpper extends BaseControllerWarpper {

	public ShareTradeInfoWarpper(Map<String, Object> resultMap) {
		super(resultMap);
	}

	@Override
	protected void warpTheMap(Map<String, Object> map) {
		Integer merchantType = (Integer) map.get("merchantType");
		// admin用户merchantType 为null
		// 不指定用户商户类型的 merchantType 为 -1
		// 此类数据直接返回到页面，不用做处理
		if (merchantType == null) {
			return;
		}
		if (merchantType == -1) {
			return;
		}

		// 交易数据
		List<ShareTradeInfoModelSearchBO> tradeInfoList = (List<ShareTradeInfoModelSearchBO>) map.get("rows");

		for (ShareTradeInfoModelSearchBO bo : tradeInfoList) {
			// 敏感数据置null
			// 顶级代理可以看所有数据
			if (merchantType.intValue() == MerchantTypeEnum.DAI_LI_SHANG2.getCode().intValue()) {
				// 一级代理,不能看平台收入，和顶级代理信息
				map.put("totalAgents1Amount", "*");
				map.put("totalPlatFormAmount", "*");
				bo.setPlatformAmount(null);
				bo.setAgents1Amount(null);
				bo.setAgents1Id(null);
				bo.setAgents1Name(null);
				bo.setAgents1Addr(null);
				bo.setAgents1PersonName(null);
				bo.setAgents1TelNo(null);
			}
			if (merchantType.intValue() == MerchantTypeEnum.DAI_LI_SHANG3.getCode().intValue()) {
				// 二级代理,不能看平台收入，顶级代理，一级代理信息
				map.put("totalPlatFormAmount", "*");
				map.put("totalAgents1Amount", "*");
				map.put("totalAgents2Amount", "*");
				bo.setPlatformAmount(null);
				bo.setAgents1Amount(null);
				bo.setAgents1Id(null);
				bo.setAgents1Name(null);
				bo.setAgents1Addr(null);
				bo.setAgents1PersonName(null);
				bo.setAgents1TelNo(null);

				bo.setAgents2Amount(null);
				bo.setAgents2Id(null);
				bo.setAgents2Name(null);
				bo.setAgents2Addr(null);
				bo.setAgents2PersonName(null);
				bo.setAgents2TelNo(null);

			}
			if (merchantType.intValue() == MerchantTypeEnum.PUHUO_SHANG.getCode().intValue()) {
				// 铺货商,不能看平台收入，顶级代理，一级代理，二级代理信息
				map.put("totalPlatFormAmount", "*");
				map.put("totalAgents1Amount", "*");
				map.put("totalAgents2Amount", "*");
				map.put("totalAgents3Amount", "*");
				bo.setPlatformAmount(null);
				bo.setAgents1Amount(null);
				bo.setAgents1Id(null);
				bo.setAgents1Name(null);
				bo.setAgents1Addr(null);
				bo.setAgents1PersonName(null);
				bo.setAgents1TelNo(null);

				bo.setAgents2Amount(null);
				bo.setAgents2Id(null);
				bo.setAgents2Name(null);
				bo.setAgents2Addr(null);
				bo.setAgents2PersonName(null);
				bo.setAgents2TelNo(null);
				
				bo.setAgents3Amount(null);
				bo.setAgents3Id(null);
				bo.setAgents3Name(null);
				bo.setAgents3Addr(null);
				bo.setAgents3PersonName(null);
				bo.setAgents3TelNo(null);

			}

		}
	}

}
