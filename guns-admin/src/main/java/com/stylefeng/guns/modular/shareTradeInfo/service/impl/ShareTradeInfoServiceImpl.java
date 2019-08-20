package com.stylefeng.guns.modular.shareTradeInfo.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.core.util.DateUtils;
import com.stylefeng.guns.modular.shareTradeInfo.service.IShareTradeInfoService;
import com.stylefeng.guns.modular.system.dao.ShareTradeInfoMapper;
import com.stylefeng.guns.modular.system.model.ShareTradeInfo;
import com.stylefeng.guns.sharecore.common.utils.ExcelUtils;
import com.stylefeng.guns.sharecore.modular.system.constants.SettlementTypeEnum;
import com.stylefeng.guns.sharecore.modular.system.constants.ShareTradeStatusEnum;
import com.stylefeng.guns.sharecore.modular.system.dao.ShareTradeInfoModelBySelfMapper;
import com.stylefeng.guns.sharecore.modular.system.model.ExcelData;
import com.stylefeng.guns.sharecore.modular.system.model.MerchantTypeEnum;
import com.stylefeng.guns.sharecore.modular.system.model.ShareTradeInfoModelSearchBO;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2019-01-21
 */
@Service("gunsAdminShareTradeInfoServiceImpl")
public class ShareTradeInfoServiceImpl extends ServiceImpl<ShareTradeInfoMapper, ShareTradeInfo>
		implements IShareTradeInfoService {

	@Autowired
	private ShareTradeInfoModelBySelfMapper shareTradeInfoModelBySelfMapper;

	@Override
	public void exportExcel(int merchantType, HttpServletResponse response, HashMap<String, Object> pageFilter)
			throws Exception {
		// 查询交易数据
		List<ShareTradeInfoModelSearchBO> list = shareTradeInfoModelBySelfMapper.selectByConditionForPage(pageFilter);
		String fileName = DateUtils.getCurrentDate(DateUtils.LONG_FORMAT) + ".xlsx";
		ExcelData excelData = new ExcelData();
		excelData.setName(fileName);
		// 表头
		List<String> titles = buildExcelTitles();
		excelData.setTitles(titles);
		List<List<Object>> rows = buildExcelRows(merchantType,list);
		excelData.setRows(rows);
		// 执行导出
		ExcelUtils.exportExcel(response, fileName, excelData);
	}

	/**
	 * 组装表格数据体
	 * @param merchantType 
	 * @param list
	 * @return
	 */
	private List<List<Object>> buildExcelRows(int merchantType, List<ShareTradeInfoModelSearchBO> list) {
		List<List<Object>> rows = new ArrayList<>();
		List<Object> row = null;
		for (int i = list.size() - 1; i >= 0; i--) {
			row = new ArrayList<>();
			row.add(list.get(i).getDeviceNo());
			row.add(list.get(i).getChargerId());
			row.add(list.get(i).getId());
			row.add(list.get(i).getTradeAmount());
			row.add(list.get(i).getYfjAmount());
			row.add(ShareTradeStatusEnum.getDesc(list.get(i).getTradeStatus()));
			String borrrowDateStr = list.get(i).getBorrowDatetime() == null ? ""
					: DateUtils.formatDate(list.get(i).getBorrowDatetime(), DateUtils.NORMAL_FORMAT);
			row.add(borrrowDateStr);
			String backDateStr = list.get(i).getBackDatetime() == null ? ""
					: DateUtils.formatDate(list.get(i).getBackDatetime(), DateUtils.NORMAL_FORMAT);
			row.add(backDateStr);
			row.add(list.get(i).getBackTradeId());
			row.add(list.get(i).getRefundAmount());
			row.add(SettlementTypeEnum.getDesc(list.get(i).getSettleAccountsStatus()));
			row.add(list.get(i).getBenefitStatus());
			String benefitDate = list.get(i).getBenefitDatetime() == null ? ""
					: DateUtils.formatDate(list.get(i).getBenefitDatetime(), DateUtils.NORMAL_FORMAT);
			row.add(benefitDate);
			row.add(list.get(i).getNickName());
			row.add(list.get(i).getMerchantName());
			row.add(list.get(i).getMerchantTelNo());
			row.add(list.get(i).getMerchantPersonName());
			BigDecimal platformAmount = null;
			BigDecimal agents1Amount = null;
			BigDecimal agents2Amount = null;
			BigDecimal agents3Amount = null;
			String agents1Name = null;
			String agents2Name = null;
			String agents3Name = null;
			String agents1TelNo = null;
			String agents2TelNo = null;
			String agents3TelNo = null;

			Long agents1No = null;
			Long agents2No = null;
			Long agents3No = null;
			//顶级代理能看平台，和一级代理数据
			if(merchantType == -1 || merchantType == MerchantTypeEnum.DAI_LI_SHANG1.getCode().intValue()) {
				platformAmount = list.get(i).getPlatformAmount();
				agents1Amount = list.get(i).getAgents1Amount();
				agents1Name = list.get(i).getAgents1Name();
				agents1TelNo = list.get(i).getAgents1TelNo();
				agents1No = list.get(i).getAgents1Id();
			}
			//顶级，一级代理能看一级代理数据
			if(merchantType == -1 || merchantType == MerchantTypeEnum.DAI_LI_SHANG2.getCode().intValue() 
					|| merchantType == MerchantTypeEnum.DAI_LI_SHANG1.getCode().intValue()) {
				agents2Amount = list.get(i).getAgents2Amount();
				agents2Name = list.get(i).getAgents2Name();
				agents2TelNo = list.get(i).getAgents2TelNo();
				agents2No = list.get(i).getAgents2Id();
			}
			//顶级，一级，二级能看二级数据
			if(merchantType == -1 || merchantType == MerchantTypeEnum.DAI_LI_SHANG2.getCode().intValue() 
					|| merchantType == MerchantTypeEnum.DAI_LI_SHANG1.getCode().intValue()
					||merchantType == MerchantTypeEnum.DAI_LI_SHANG3.getCode().intValue()) {
				agents3Amount = list.get(i).getAgents3Amount();
				agents3Name = list.get(i).getAgents3Name();
				agents3TelNo = list.get(i).getAgents3TelNo();
				agents3No = list.get(i).getAgents3Id();
			}
			row.add(platformAmount);
			row.add(agents1Amount);
			row.add(agents1Name);
			row.add(agents1TelNo);
			row.add(agents1No);

			row.add(agents2Amount);
			row.add(agents2Name);
			row.add(agents2TelNo);
			row.add(agents2No);

			row.add(agents3Amount);
			row.add(agents3Name);
			row.add(agents3TelNo);
			row.add(agents3No);

			row.add(list.get(i).getShopkeeperAmount());
			row.add(list.get(i).getShopkeeperName());
			row.add(list.get(i).getShopkeeperTelNo());
			row.add(list.get(i).getShopkeeperId());

			row.add(list.get(i).getAllianceBusinessAmount());
			row.add(list.get(i).getAllianceBussinessName());
			row.add(list.get(i).getAllianceBussinessTelNo());
			row.add(list.get(i).getAllianceBusinessId());
			rows.add(row);
		}
		return rows;
	}

	/**
	 * 组装表头
	 * 
	 * @return
	 */
	private List<String> buildExcelTitles() {
		List<String> titles = new ArrayList<>();
		titles.add("设备号");
		titles.add("充电器id");
		titles.add("交易id");
		titles.add("交易金额");
		titles.add("预付金");
		titles.add("交易状态");
		titles.add("借出时间");
		titles.add("归还时间");
		titles.add("退款订单");
		titles.add("退款金额");
		titles.add("结账状态");
		titles.add("分润状态");
		titles.add("分润时间");
		titles.add("客户昵称");
		titles.add("店铺名称");
		titles.add("联系电话");
		titles.add("店铺联系人");
		titles.add("平台收入");
		titles.add("顶级代理商收入");
		titles.add("顶级代理商名称");
		titles.add("顶级代理商联系电话");
		titles.add("顶级代理商ID");
		titles.add("一代理商收入");
		titles.add("一级代理商名称");
		titles.add("一级代理商联系电话");
		titles.add("一级代理商ID");
		titles.add("二代理商收入");
		titles.add("二级代理商名称");
		titles.add("二级代理商联系电话");
		titles.add("二级代理商ID");
		titles.add("铺货商收入");
		titles.add("铺货商名称");
		titles.add("铺货商联系电话");
		titles.add("铺货商ID");
		titles.add("加盟商收入");
		titles.add("加盟商名称");
		titles.add("加盟商联系电话");
		titles.add("加盟商ID");
		return titles;
	}

}
