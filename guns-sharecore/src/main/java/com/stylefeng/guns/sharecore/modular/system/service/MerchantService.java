package com.stylefeng.guns.sharecore.modular.system.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.stylefeng.guns.core.util.DecimalUtil;
import com.stylefeng.guns.sharecore.common.persistence.dao.CustInfoModelMapper;
import com.stylefeng.guns.sharecore.common.persistence.model.CapitalChangeTypeEnum;
import com.stylefeng.guns.sharecore.common.persistence.model.CustInfoModel;
import com.stylefeng.guns.sharecore.common.persistence.model.CustTypeEnum;
import com.stylefeng.guns.sharecore.common.persistence.model.ResultCommand;
import com.stylefeng.guns.sharecore.common.service.WithdrawService;
import com.stylefeng.guns.sharecore.modular.system.constants.ApproveStatusEnum;
import com.stylefeng.guns.sharecore.modular.system.constants.PayWayEnum;
import com.stylefeng.guns.sharecore.modular.system.constants.ShareTradeTypeEnum;
import com.stylefeng.guns.sharecore.modular.system.constants.WithdrawStatusEnum;
import com.stylefeng.guns.sharecore.modular.system.dao.CapitalChangeRecordModelMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.MerchantInfoModelBySelfMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.MerchantInfoModelMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.ShareDeviceInfoModelBySelfMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.WithdrawApplyRecordModelMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.WithdrawOrderInfoModelMapper;
import com.stylefeng.guns.sharecore.modular.system.model.CapitalChangeRecordModel;
import com.stylefeng.guns.sharecore.modular.system.model.CapitalStatusEnum;
import com.stylefeng.guns.sharecore.modular.system.model.ChildAgentsInfoBO;
import com.stylefeng.guns.sharecore.modular.system.model.ChildAgentsQueryCommand;
import com.stylefeng.guns.sharecore.modular.system.model.CommonPagingQueryBO;
import com.stylefeng.guns.sharecore.modular.system.model.MerchantInfoBo;
import com.stylefeng.guns.sharecore.modular.system.model.MerchantInfoModel;
import com.stylefeng.guns.sharecore.modular.system.model.MerchantTypeEnum;
import com.stylefeng.guns.sharecore.modular.system.model.TradeStatusEnum;
import com.stylefeng.guns.sharecore.modular.system.model.WithdrawApplyRecordModel;
import com.stylefeng.guns.sharecore.modular.system.model.WithdrawOrderInfoModel;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.MmpaymkttransferResult;

@Service
public class MerchantService {
	/**
	 * 处理日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(MerchantService.class);
	/**
	 * 数据库操作...
	 */
	@Autowired
	private ShareDeviceInfoModelBySelfMapper shareDeviceInfoModelBySelfMapper;
	/**
	 * 商户操作类
	 */
	@Autowired
	private MerchantInfoModelMapper merchantInfoModelMapper;
	/**
	 * 商户操作类
	 */
	@Autowired
	private MerchantInfoModelBySelfMapper merchantInfoModelBySelfMapper;

	/**
	 * 处理微信相关服务...
	 */
	@Autowired
	protected WxMpService wxMpService;
	/**
	 * 接口...
	 */
	@Autowired
	protected WxAppApiService wxAppApiService;
	/**
	 * 处理企业提现
	 */
	@Autowired
	protected WithdrawService withdrawService;
	/**
	 * 提现申请
	 */
	@Autowired
	protected WithdrawApplyRecordModelMapper withdrawApplyRecordModelMapper;
	/**
	 * 客服操作
	 */
	@Autowired
	protected CustInfoModelMapper custInfoModelMapper;
	/**
	 * 数据库操作
	 */
	@Autowired
	private SequenceService seq;
	/**
	 * 提现记录..
	 */
	@Autowired
	private CapitalChangeRecordModelMapper capitalChangeRecordModelMapper;
	/**
	 * 提现账号服务类
	 */
	@Autowired
	private CustWexinRewardAccountService custWexinRewardAccountService;
	/**
	 * 企业提现数据库操作
	 */
	@Autowired
	private WithdrawOrderInfoModelMapper tradeInfoMapper;
	

	/**
	 * <p>
	 * 子代理商列表
	 * </p>
	 * 
	 * @param command
	 * @param pageNum
	 * @param rows
	 */
	public CommonPagingQueryBO queryChildAgentsInfoList(ChildAgentsQueryCommand command) {
		logger.info("获取子代理商列表的消息[service层][queryChildMerchantInfo方法]入参,merchantId:{}", command);

		Long parentMerchantId = command.getParentMerchantId();
		// 通过父级商户Id查询该商户类型 自买自铺代理商（顶级代理商），一级代理商，二级代理商，铺货商
		MerchantInfoModel merchantInfoModel = merchantInfoModelMapper.selectByPrimaryKey(parentMerchantId);

		final String MERCHANT_ID = "online_merchant_id";
		final String AGENTS_ID = "agents1_id";
		final String FIRST_LEVEL_AGENTS_ID = "agents2_id";
		final String SECOND_LEVEL_AGENTS_ID = "agents3_id";
		final String FRANCHISEE_ID = "alliance_business_id";
		final String SHOPKEEPER_ID = "shopkeeper_id";

		// 顶级商户下级商户集合 去设备表查询下级相关信息
		Set<Long> merchantIdList = new HashSet<>();
		// 通过顶级商户Id 商户表里面查询下级信息
		merchantIdList = new HashSet<Long>(merchantInfoModelBySelfMapper.queryMerchantIdByPId(parentMerchantId));

		// 代理商
		if (merchantInfoModel.getMerchantType().toString()
				.equals(MerchantTypeEnum.DAI_LI_SHANG1.getCode().toString())) {
			// 设备表中的终端信息
			List<Long> dMerchantList = shareDeviceInfoModelBySelfMapper.queryMerchantId(AGENTS_ID, parentMerchantId, MERCHANT_ID);
			// 设备表中的一级代理商信息
			List<Long> dFirstList = shareDeviceInfoModelBySelfMapper.queryMerchantId(AGENTS_ID, parentMerchantId,
					FIRST_LEVEL_AGENTS_ID);
			// 设备表中的二级代理商信息
			List<Long> dSecondList = shareDeviceInfoModelBySelfMapper.queryMerchantId(AGENTS_ID, parentMerchantId,
					SECOND_LEVEL_AGENTS_ID);
			// 设备表中的加盟商信息
			List<Long> franchiseeList = shareDeviceInfoModelBySelfMapper.queryMerchantId(AGENTS_ID, parentMerchantId,
					FRANCHISEE_ID);
			// 设备表中的铺货商信息
			List<Long> shopList = shareDeviceInfoModelBySelfMapper.queryMerchantId(AGENTS_ID, parentMerchantId, SHOPKEEPER_ID);
			// 添加终端
			if (dMerchantList != null && dMerchantList.size() > 0) {
				for (Long merchantId : dMerchantList) {
					merchantIdList.add(merchantId);
				}
			}
			// 添加一级代理商
			if (dFirstList != null && dFirstList.size() > 0) {
				for (Long firstLevelAgentsId : dFirstList) {
					merchantIdList.add(firstLevelAgentsId);
				}
			}
			// 添加二级代理商
			if (dSecondList != null && dSecondList.size() > 0) {
				for (Long secondLevelAgentsId : dSecondList) {
					merchantIdList.add(secondLevelAgentsId);
				}
			}
			// 添加加盟
			if (franchiseeList != null && franchiseeList.size() > 0) {
				for (Long franchiseeId : franchiseeList) {
					merchantIdList.add(franchiseeId);
				}
			}
			// 添加铺货
			if (shopList != null && shopList.size() > 0) {
				for (Long shopkeeperId : shopList) {
					merchantIdList.add(shopkeeperId);
				}
			}
		}
		// 一级代理商列表
		else if (merchantInfoModel.getMerchantType().toString()
				.equals(MerchantTypeEnum.DAI_LI_SHANG2.getCode().toString())) {
			// 设备表中的终端信息
			List<Long> dMerchantList = shareDeviceInfoModelBySelfMapper.queryMerchantId(FIRST_LEVEL_AGENTS_ID, parentMerchantId,
					MERCHANT_ID);
			// 添加终端
			if (dMerchantList != null && dMerchantList.size() > 0) {
				for (Long merchantId : dMerchantList) {
					merchantIdList.add(merchantId);
				}
			}
		}
		// 二级代理商列表
		else if (merchantInfoModel.getMerchantType().toString()
				.equals(MerchantTypeEnum.DAI_LI_SHANG3.getCode().toString())) {
			// 设备表中的终端信息
			List<Long> dMerchantList = shareDeviceInfoModelBySelfMapper.queryMerchantId(SECOND_LEVEL_AGENTS_ID, parentMerchantId,
					MERCHANT_ID);
			// 添加终端
			if (dMerchantList != null && dMerchantList.size() > 0) {
				for (Long merchantId : dMerchantList) {
					merchantIdList.add(merchantId);
				}
			}
		}
		// 铺货商列表
		else if (merchantInfoModel.getMerchantType().toString()
				.equals(MerchantTypeEnum.PUHUO_SHANG.getCode().toString())) {
			// 设备表中的终端信息
			List<Long> dMerchantList = shareDeviceInfoModelBySelfMapper.queryMerchantId(SHOPKEEPER_ID, parentMerchantId,
					MERCHANT_ID);
			// 添加终端
			if (dMerchantList != null && dMerchantList.size() > 0) {
				for (Long merchantId : dMerchantList) {
					merchantIdList.add(merchantId);
				}
			}
		}
		// 加盟商列表
		else if (merchantInfoModel.getMerchantType().toString()
				.equals(MerchantTypeEnum.JIA_MENG_SHANG.getCode().toString())) {
			// 设备表中的终端信息
			List<Long> dMerchantList = shareDeviceInfoModelBySelfMapper.queryMerchantId(FRANCHISEE_ID, parentMerchantId,
					MERCHANT_ID);
			// 添加终端
			if (dMerchantList != null && dMerchantList.size() > 0) {
				for (Long merchantId : dMerchantList) {
					merchantIdList.add(merchantId);
				}
			}
		}
		// 一：通过MerchantId查询到所有子代理商列表，使用分页插件
		CommonPagingQueryBO commonPagingQueryBO = new CommonPagingQueryBO();
		// PageHelper.startPage(command.getPage(), command.getRows());
		// 判断商户查询列表是否为空
		if (merchantIdList.isEmpty()) {
			List listRow = new ArrayList<>();
			commonPagingQueryBO.setRows(listRow);
			commonPagingQueryBO.setMessage("该代理商没有下级商户");
			commonPagingQueryBO.setResult(ResultCommand.SUCCESS);
			return commonPagingQueryBO;
		}
		command.setMerchantIds(new ArrayList<Long>(merchantIdList));

		// 在查询结果集里面查询出相应的代理商和终端商户信息（终端商户对应父Id为，所有去掉这个条件）
		command.setParentMerchantId(null);
		command.setStart(command.getPage() * command.getRows() - command.getRows());

		List<ChildAgentsInfoBO> pageInfo = merchantInfoModelBySelfMapper.queryMerchantInfoAll(command);
		Long totalRows = merchantInfoModelBySelfMapper.getMerInfoAllCount(command);
		HashMap<String, Object> pageFilter = null;
		Long cnt = 0L;
		for (ChildAgentsInfoBO childAgentsInfoBO : pageInfo) {
			if (childAgentsInfoBO.getName() == null)
				childAgentsInfoBO.setName("");
			if (childAgentsInfoBO.getAddr() == null)
				childAgentsInfoBO.setAddr("");
			if (childAgentsInfoBO.getPersonName() == null)
				childAgentsInfoBO.setPersonName("");
			if (childAgentsInfoBO.getProvince() == null)
				childAgentsInfoBO.setProvince("");
			if (childAgentsInfoBO.getCity() == null)
				childAgentsInfoBO.setCity("");
			if (childAgentsInfoBO.getZone() == null)
				childAgentsInfoBO.setZone("");

			// 判断一级和二级代理商是否绑定了设备 绑定了就无法编辑商户信息
			if (childAgentsInfoBO.getMerchantType() != null) {
				pageFilter = new HashMap<>();
				if (childAgentsInfoBO.getMerchantType().intValue() == MerchantTypeEnum.DAI_LI_SHANG1.getCode()
						.intValue()) {// 如果是代理商
					// 如果是一级代理商
					pageFilter.put("agents1Id", childAgentsInfoBO.getMerchantId());
				} else if (childAgentsInfoBO.getMerchantType().intValue() == MerchantTypeEnum.DAI_LI_SHANG2.getCode()
						.intValue()) {
					pageFilter.put("agents2Id", childAgentsInfoBO.getMerchantId());
				} else if (childAgentsInfoBO.getMerchantType().intValue() == MerchantTypeEnum.DAI_LI_SHANG3.getCode()
						.intValue()) {
					pageFilter.put("agents3Id", childAgentsInfoBO.getMerchantId());
				} else if (childAgentsInfoBO.getMerchantType().intValue() == MerchantTypeEnum.PUHUO_SHANG.getCode()
						.intValue()) {
					pageFilter.put("shopkeeperId", childAgentsInfoBO.getMerchantId());
				} else if (childAgentsInfoBO.getMerchantType().intValue() == MerchantTypeEnum.JIA_MENG_SHANG.getCode()
						.intValue()) {
					pageFilter.put("allianceBusinessId", childAgentsInfoBO.getMerchantId());
				} else {
					pageFilter.put("onlineMerchantId", childAgentsInfoBO.getMerchantId());
				}
				cnt = shareDeviceInfoModelBySelfMapper.countByConditionForPage(pageFilter);
				childAgentsInfoBO.setDeviceCount(cnt);//商户拥有的设备数量
				if (cnt > 0) {
					childAgentsInfoBO.setIsBindDevice("1");
				} else {
					childAgentsInfoBO.setIsBindDevice("0");
				}
			}
		}
		if (pageInfo.size() == 0) {
			commonPagingQueryBO.setRows(pageInfo);
			commonPagingQueryBO.setMessage("该代理商没有下级商户");
			commonPagingQueryBO.setResult(ResultCommand.SUCCESS);
			return commonPagingQueryBO;
		}

		commonPagingQueryBO.setRows(pageInfo);
		commonPagingQueryBO.setTotal(totalRows);
		commonPagingQueryBO.setMessage("获取子代理商列表成功");
		commonPagingQueryBO.setResult(ResultCommand.SUCCESS);
		logger.info("获取子代理商的消息[service层][queryChildMerchantInfo方法]出参,pageInfo:{}", pageInfo);
		return commonPagingQueryBO;
	}

	/**
	 * 处理商户提现，企业付款
	 * 
	 * @param preTaxAmount
	 * @param mib
	 * @param spbillCreateIp
	 * @param receiveCustInfo
	 * @param ismmPay 是否执行企业付款
	 * @throws Exception
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 15, rollbackFor = Exception.class)
	public void executeMerchantWithdraw(BigDecimal preTaxAmount, MerchantInfoBo mib, String spbillCreateIp,
			CustInfoModel receiveCustInfo,Boolean ismmPay,PayWayEnum payWayEnum) throws Exception {
		// 1.计算税后提现金额
		BigDecimal aftTaxAmount = preTaxAmount;// withdrawService.computeAftTax(preTaxAmount, mib);
		// 2.商户提现申请记录创建
		Long withdrawId = withdrawService.createMerchantWithdrawRecord(preTaxAmount, aftTaxAmount, mib,payWayEnum);
		// 加锁
		WithdrawApplyRecordModel withdrawApplyRecordModel = 
				withdrawApplyRecordModelMapper.selectByPrimaryKeyForUpdate(withdrawId);
		// 获取商户类型的客户信息
		List<CustInfoModel> custInfos = custInfoModelMapper
				.selectCustInfoAndAccountInfoByMerchantIdAndCustType(mib.getId(), CustTypeEnum.MERCHANT.getCode());
		// 
		CustInfoModel merchantCustInfo = custInfos.get(0);

		// 3.商户付款方式处理
		// 3.1自动付款
		// 3.1.1出金
		CapitalChangeRecordModel outCcr = new CapitalChangeRecordModel();
		outCcr.setTradeRecordId(withdrawApplyRecordModel.getId());
		outCcr.setAmount(DecimalUtil.format(aftTaxAmount));
		Long outCcId = seq.getCapitalChangeSeq();
		outCcr.setCapitalChangeId(outCcId);
		outCcr.setCapitalStatus(CapitalStatusEnum.SUCCESS.getCode());
		outCcr.setPayerAccountId(merchantCustInfo.getCustTsfAmtAccountModel().getAccountId());
		outCcr.setPayerCustNo(merchantCustInfo.getCustTsfAmtAccountModel().getCustNo());
		outCcr.setCreateTime(new Date());
		outCcr.setFlowType(CapitalChangeTypeEnum.WITHDRAW.getCode());
		capitalChangeRecordModelMapper.insert(outCcr);
		custWexinRewardAccountService.out(merchantCustInfo, outCcr);
		// 3.1.2更新提现申请记录
		WithdrawApplyRecordModel updateRecord = new WithdrawApplyRecordModel();
		updateRecord.setId(withdrawId);
		updateRecord.setWithdrawStatus(WithdrawStatusEnum.SUCCESS.getCode());
		updateRecord.setWithdrawStatusCn(WithdrawStatusEnum.SUCCESS.getDesc());
		updateRecord.setApproveStatus(ApproveStatusEnum.NO_NEED_APPROVE.getCode());
		updateRecord.setApproveStatusCn(ApproveStatusEnum.NO_NEED_APPROVE.getDesc());
		updateRecord.setPayer((ismmPay)?"系统自动付款，企业付款":"管理员手动提现");
		updateRecord.setPayTime(new Date());
		withdrawApplyRecordModelMapper.updateByPrimaryKeySelective(updateRecord);
		// 3.1.3企业付款到零钱
		if(ismmPay){
			Boolean payResult = mmpaymkttransfers(Boolean.TRUE, mib.getPersonName(),
					receiveCustInfo, aftTaxAmount,"共享充电分润收益", spbillCreateIp, withdrawId);
			if (!payResult) {
				throw new Exception("付款失败！");
			}
		}
	}

	/**
	 * 
	 * <p>
	 * 微信企业付款到零钱接口
	 * </p>
	 * 
	 * @param checkNameFlag:校验用户姓名选项
	 * @param reUserName:收款用户姓名
	 * @param receiveCustInfo:客户微信用户信息
	 * @param transferAmount:付款金额
	 * @param comment:付款备注信息
	 * @param spbillCreateIp:发起付款的主机IP
	 * @return
	 */
	public boolean mmpaymkttransfers(Boolean checkNameFlag, String reUserName, CustInfoModel receiveCustInfo,
			BigDecimal transferAmount, String comment, String spbillCreateIp, Long withdrawId) {
		// 1.生成一笔处理中的企业付款交易记录
		WithdrawOrderInfoModel tradeInfo = new WithdrawOrderInfoModel();
		// Long tradeId = seq.getTradeIdSeq();
		tradeInfo.setCustNo(receiveCustInfo.getCustNo());
		tradeInfo.setTradeId(withdrawId);
		tradeInfo.setStatus(TradeStatusEnum.PROCESSING.getCode());
		tradeInfo.setStatusCn(TradeStatusEnum.PROCESSING.getDesc());
		tradeInfo.setTradeType(ShareTradeTypeEnum.ENTERPRISE_PAYMENT.getCode());
		tradeInfo.setRefTradeId(withdrawId);
		tradeInfo.setTradeName(ShareTradeTypeEnum.ENTERPRISE_PAYMENT.getDesc());
		Date createTime = new Date();
		tradeInfo.setCreateTime(createTime);
		tradeInfo.setTradeAmount(DecimalUtil.format(transferAmount));
		tradeInfoMapper.insert(tradeInfo);

		// 2.组装企业付款请求参数集合
		Map<String, String> parmMap = new HashMap<String, String>();
		// parmMap.put("partner_trade_no", PayUtil.getTransferNo()); //商户订单号
		parmMap.put("partner_trade_no", withdrawId.toString()); // 商户订单号
		parmMap.put("openid", receiveCustInfo.getOpenId()); // 用户openid
		if (checkNameFlag) {
			parmMap.put("check_name", "FORCE_CHECK"); // 校验用户姓名选项 OPTION_CHECK
			parmMap.put("re_user_name", reUserName); // check_name设置为FORCE_CHECK或OPTION_CHECK，则必填
		} else {
			parmMap.put("check_name", "NO_CHECK"); // 不校验真实姓名
		}
		String amountStr = String.valueOf(transferAmount.multiply(DecimalUtil.ONE_HUNDRED));
		if (amountStr.contains(".")) {
			String[] arr = amountStr.split("\\.");
			amountStr = arr[0];
		}
		parmMap.put("amount", amountStr); // 转账金额,货币单位：分
		parmMap.put("desc", comment); // 企业付款描述信息
		parmMap.put("spbill_create_ip", spbillCreateIp); 

		// 3.调用企业付款接口
		MmpaymkttransferResult result = null;
		try {
			logger.info("调用企业付款入参：{}", parmMap);
			result = wxMpService.mmpaymkttransfer(parmMap);
			// 调用成功
			if (result != null && "SUCCESS".equals(result.getResult_code())) {
				logger.error("调用企业付款成功：{}", result);
				tradeInfo.setStatus(TradeStatusEnum.SUCCESS.getCode());
				tradeInfo.setStatusCn(TradeStatusEnum.SUCCESS.getDesc());
				tradeInfo.setUpdateTime(new Date());
				tradeInfo.setZjAddr(result.getErr_code_des());
				tradeInfoMapper.updateByPrimaryKeySelective(tradeInfo);
				return true;
			} else {
				logger.error("调用企业付款失败：{}", result);
				tradeInfo.setStatus(TradeStatusEnum.FAILURE.getCode());
				tradeInfo.setStatusCn(TradeStatusEnum.FAILURE.getDesc());
				tradeInfo.setUpdateTime(new Date());
				tradeInfo.setZjAddr(result.getErr_code_des());
				tradeInfoMapper.updateByPrimaryKeySelective(tradeInfo);
				return false;
			}
		} catch (WxErrorException e) {
			logger.error("调用企业付款异常:{}", e);
			// 发生异常
			tradeInfo.setStatus(TradeStatusEnum.FAILURE.getCode());
			tradeInfo.setStatusCn(TradeStatusEnum.FAILURE.getDesc());
			tradeInfo.setUpdateTime(new Date());
			tradeInfoMapper.updateByPrimaryKeySelective(tradeInfo);
			tradeInfo.setZjAddr(e.getMessage());
			return false;
		}
	}
}
