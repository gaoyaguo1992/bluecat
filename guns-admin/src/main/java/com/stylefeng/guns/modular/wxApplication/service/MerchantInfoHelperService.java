package com.stylefeng.guns.modular.wxApplication.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.stylefeng.guns.modular.wxApplication.bo.MerchantQrCodeBO;
import com.stylefeng.guns.sharecore.common.persistence.dao.CustInfoModelMapper;
import com.stylefeng.guns.sharecore.common.persistence.model.AccountStatusEnum;
import com.stylefeng.guns.sharecore.common.persistence.model.CustAccountModel;
import com.stylefeng.guns.sharecore.common.persistence.model.CustAccountSourceEnum;
import com.stylefeng.guns.sharecore.common.persistence.model.CustAccountTypeEnum;
import com.stylefeng.guns.sharecore.common.persistence.model.CustInfoModel;
import com.stylefeng.guns.sharecore.common.persistence.model.CustTypeEnum;
import com.stylefeng.guns.sharecore.common.persistence.model.ResultCommandBaseObject;
import com.stylefeng.guns.sharecore.common.utils.SysUtil;
import com.stylefeng.guns.sharecore.modular.system.dao.CustAccountModelMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.CustMerchantRefInfoModelMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.MerchantInfoModelMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.ShareDeviceInfoModelBySelfMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.ShareDeviceInfoModelMapper;
import com.stylefeng.guns.sharecore.modular.system.model.CustMerchantRefInfoModel;
import com.stylefeng.guns.sharecore.modular.system.model.CustMerchantRefInfoModelExample;
import com.stylefeng.guns.sharecore.modular.system.model.MerchantInfoModel;
import com.stylefeng.guns.sharecore.modular.system.model.MerchantQrCodeTypeEnum;
import com.stylefeng.guns.sharecore.modular.system.model.MerchantTypeEnum;
import com.stylefeng.guns.sharecore.modular.system.model.ShareDeviceInfoModel;
import com.stylefeng.guns.sharecore.modular.system.service.BaseService;

import net.sf.json.JSONObject;

/**
 *
 * 处理设备助手..
 *
 * @author alian li
 * @since 2019-04-20
 */
@Service("gunsadmin.MerchantInfoHelperService")
public class MerchantInfoHelperService extends BaseService {
	/**
	 * 写日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(MerchantInfoHelperService.class);
	/**
	 * 设备操作..
	 */
	@Autowired
	private ShareDeviceInfoModelBySelfMapper shareDeviceInfoModelBySelfMapper;
	/**
	 * 操作..
	 */
	@Autowired
	private ShareDeviceInfoModelMapper shareDeviceInfoModelMapper;
	/**
	 * 商户数据库操作...
	 */
	@Autowired
	private MerchantInfoModelMapper merchantInfoModelMapper;
	/**
	 * 客户和商户关系
	 */
	@Autowired
	private CustMerchantRefInfoModelMapper custMerchantRefInfoModelMapper;
	/**
	 * 客户处理类..
	 */
	@Autowired
	private CustInfoModelMapper custInfoModelMapper;
	/**
	 * 注册商户
	 */
	@Autowired
	private CustAccountModelMapper custAccountModelMapper;

	/**
	 * 注册商户...
	 * 
	 * @param custModel
	 * @param merchantModel
	 * @param di
	 * @param latitude
	 * @param longitude
	 * @throws Exception
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 10, rollbackFor = Exception.class)
	public MerchantInfoModel registerMerchantInfo(MerchantInfoModel merchantModel) throws Exception {
		Date now = new Date();
		// 1. 保存商户
		MerchantTypeEnum merchantTypeEnum = MerchantTypeEnum
				.getMerchantTypeEnumByCode(merchantModel.getMerchantType().intValue());
		Long id = seqService.getMerchantIdSeq(merchantTypeEnum.getCode());
		merchantModel.setId(id);
		merchantModel.setCreateTime(now);
		merchantModel.setUpdateTime(now);
		merchantModel.setMerchantTypeCn(merchantTypeEnum.getDesc());
		merchantModel.setWithdrawWayId(1L);
		merchantInfoModelMapper.insert(merchantModel);
		// 2. 创建客户
		id = seqService.getCustNoSeq();
		CustInfoModel custInfoModel = new CustInfoModel();
		custInfoModel.setCustNo(id);
		custInfoModel.setOpenId(id.toString());
		custInfoModel.setUnionId(id.toString());
		custInfoModel.setCustName(merchantModel.getName());
		custInfoModel.setCustType(CustTypeEnum.MERCHANT.getCode());
		custInfoModel.setCustTypeCn(CustTypeEnum.MERCHANT.getName());
		custInfoModel.setTelNo(merchantModel.getTelNo());
		custInfoModel.setRegTime(now);
		custInfoModel.setUpdateTime(now);
		custInfoModel.setMerchantId(merchantModel.getId());
		custInfoModelMapper.insertForMerchant(custInfoModel);
		// 3. 创建账户..
		CustAccountModel custAccountModel = new CustAccountModel();
		id = seqService.getAccountNoSeq();
		custAccountModel.setAccountId(id);
		custAccountModel.setAccountType(CustAccountTypeEnum.RECHARGERACCOUNT.getCode());
		custAccountModel.setAvailableBalance(BigDecimal.ZERO);
		custAccountModel.setBalance(BigDecimal.ZERO);
		custAccountModel.setFrozenBalance(BigDecimal.ZERO);
		custAccountModel.setCreateTime(now);
		custAccountModel.setCustNo(custInfoModel.getCustNo());
		custAccountModel.setAmtSource(CustAccountSourceEnum.WEIXIN.getCode());
		custAccountModel.setMerchantId(merchantModel.getId());
		custAccountModel.setDataDigest(SysUtil.getDigest(custAccountModel));
		custAccountModel.setStatus(AccountStatusEnum.NORMAL.getCode());
		custAccountModelMapper.insert(custAccountModel);
		// 4. 如果用户已经输入客户编号，创建关联关系
		if (merchantModel.getSettlementAccount() != null && !merchantModel.getSettlementAccount().isEmpty()) {
			Long custNo = Long.parseLong(merchantModel.getSettlementAccount());
			CustInfoModel infoModelForCust = custInfoModelMapper.selectByPrimaryKey(custNo);
			if (infoModelForCust != null) {
				CustMerchantRefInfoModel refInfoModel = new CustMerchantRefInfoModel();
				refInfoModel.setCreateId(0L);
				refInfoModel.setCreateTime(now);
				refInfoModel.setCustNo(custNo);
				if (infoModelForCust.getCustType() != null) {
					refInfoModel.setCustType(infoModelForCust.getCustType().byteValue());
				} else {
					refInfoModel.setCustType((byte) 1);
				}
				refInfoModel.setIsDefault((byte) 0);
				refInfoModel.setMerchantId(merchantModel.getId());
				refInfoModel.setMerchantName(merchantModel.getName());
				refInfoModel.setMerchantType(merchantModel.getMerchantType());
				refInfoModel.setMerchantTypeCn(merchantModel.getMerchantTypeCn());
				custMerchantRefInfoModelMapper.insert(refInfoModel);
			}

		}

		return merchantModel;
	}

	/**
	 * 注册终端信息....
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 10, rollbackFor = Exception.class)
	public void registerTerminal(CustInfoModel custModel, MerchantInfoModel merchantModel, ShareDeviceInfoModel di,
			BigDecimal latitude, BigDecimal longitude) {
		Date now = new Date();
		// 1. 保存商户
		Long merchantId = seqService.getMerchantIdSeq(MerchantTypeEnum.TERMINAL.getCode());
		merchantModel.setId(merchantId);
		merchantModel.setMerchantType(MerchantTypeEnum.TERMINAL.getCode().byteValue());
		merchantModel.setMerchantTypeCn(MerchantTypeEnum.TERMINAL.getDesc());
		merchantModel.setCreateTime(now);
		merchantModel.setLatitudeX(latitude);
		merchantModel.setLongitudeY(longitude);
		merchantInfoModelMapper.insert(merchantModel);
		// 2. 保存商户和客户信息关系
		CustMerchantRefInfoModel refInfoModel = new CustMerchantRefInfoModel();
		refInfoModel.setCreateId(custModel.getCustNo());
		refInfoModel.setCreateTime(now);
		refInfoModel.setCustNo(custModel.getCustNo());
		refInfoModel.setCustType(custModel.getCustType().byteValue());
		refInfoModel.setIsDefault((byte) 0);
		refInfoModel.setMerchantId(merchantModel.getId());
		refInfoModel.setMerchantName(merchantModel.getName());
		refInfoModel.setMerchantType(merchantModel.getMerchantType());
		refInfoModel.setMerchantTypeCn(merchantModel.getMerchantTypeCn());
		custMerchantRefInfoModelMapper.insert(refInfoModel);
	}

	/**
	 * 更新默认状态..
	 * 
	 * @param model
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 10, rollbackFor = Exception.class)
	public void updateCustMerRefDefault(CustMerchantRefInfoModel model) {
		// 1. 取消原来的default设置..
		CustMerchantRefInfoModelExample example = new CustMerchantRefInfoModelExample();
		com.stylefeng.guns.sharecore.modular.system.model.CustMerchantRefInfoModelExample.Criteria critera = example
				.createCriteria();
		critera.andCustNoEqualTo(model.getCustNo());
		CustMerchantRefInfoModel record = new CustMerchantRefInfoModel();
		record.setIsDefault((byte) 0);
		custMerchantRefInfoModelMapper.updateByExampleSelective(record, example);
		// 2. 设置default..
		model.setIsDefault((byte) 1);
		custMerchantRefInfoModelMapper.updateByPrimaryKey(model);
	}

	/**
	 * 获取添加商户功能的二维码 1.获取商户信息 2.按商户类型封装返回结果，结果格式：key(要添加的商户类型):value(二维码全地址)
	 * 如：代理商：https://rchg.xiaobangshou.net.cn/gunsAdmin/wx/merchantQRCode?merchantId=1000000001
	 * 
	 * @param merchantId 商户id
	 * @param custNo
	 * @param openId
	 * @return
	 */
	// 1.顶级代理商能添加代理商，加盟商，铺货商，终端商户
	// 2.一二级代理商，能添加加盟商，铺货商，终端商户
	// 3.加盟商能添加终端商户
	// 4.铺货商能添加加盟商与终端商户
	public ResultCommandBaseObject<Object> addmerchantInfoQRCode(String merchantId, String rootUrl) {
		ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
		List<Object> resultList = new ArrayList<>();
		if (StringUtils.isEmpty(merchantId)) {
			result.setErrorCode(ResultCommandBaseObject.FAILED);
			result.setMessage("获取不到商户ID!");
			result.setResult(ResultCommandBaseObject.FAILED);
			return result;
		}
		MerchantInfoModel merchantInfoModel = merchantInfoModelMapper.selectByPrimaryKey(Long.valueOf(merchantId));

		if (merchantInfoModel == null) {
			result.setErrorCode(ResultCommandBaseObject.FAILED);
			result.setMessage("系统不存在此商户信息!");
			result.setResult(ResultCommandBaseObject.FAILED);
			return result;
		}
		// 商户类型
		int merchantType = merchantInfoModel.getMerchantType().byteValue();
		// 1.顶级代理
		if (MerchantTypeEnum.DAI_LI_SHANG1.getCode().intValue() == merchantType) {

			JSONObject daiLiShang2BO = builMerchantQrCodeMap(
					MerchantQrCodeTypeEnum.ADD_DAI_LI_SHANG2.getCode(), merchantId, rootUrl);
			JSONObject puHuoShangBO = builMerchantQrCodeMap(MerchantQrCodeTypeEnum.ADD_PUHUO_SHANG.getCode(),
					merchantId, rootUrl);
			JSONObject jiaMengShangBO = builMerchantQrCodeMap(
					MerchantQrCodeTypeEnum.ADD_JIA_MENG_SHANG.getCode(), merchantId, rootUrl);
			JSONObject terminalBO = builMerchantQrCodeMap(MerchantQrCodeTypeEnum.ADD_TERMINAL.getCode(),
					merchantId, rootUrl);

			resultList.add(daiLiShang2BO);
			resultList.add(puHuoShangBO);
			resultList.add(jiaMengShangBO);
			resultList.add(terminalBO);
		}
		// 2.一级代理
		if (MerchantTypeEnum.DAI_LI_SHANG2.getCode().intValue() == merchantType) {
			JSONObject daiLiShang3Map = builMerchantQrCodeMap(
					MerchantQrCodeTypeEnum.ADD_DAI_LI_SHANG3.getCode(), merchantId, rootUrl);
			JSONObject puHuoShangMap = builMerchantQrCodeMap(MerchantQrCodeTypeEnum.ADD_PUHUO_SHANG.getCode(),
					merchantId, rootUrl);
			JSONObject jiaMengShangMap = builMerchantQrCodeMap(
					MerchantQrCodeTypeEnum.ADD_JIA_MENG_SHANG.getCode(), merchantId, rootUrl);
			JSONObject terminalMap = builMerchantQrCodeMap(MerchantQrCodeTypeEnum.ADD_TERMINAL.getCode(),
					merchantId, rootUrl);
			resultList.add(daiLiShang3Map);
			resultList.add(puHuoShangMap);
			resultList.add(jiaMengShangMap);
			resultList.add(terminalMap);
		}
		// 3.二级代理
		if (MerchantTypeEnum.DAI_LI_SHANG3.getCode().intValue() == merchantType) {
			JSONObject puHuoShangMap = builMerchantQrCodeMap(MerchantQrCodeTypeEnum.ADD_PUHUO_SHANG.getCode(),
					merchantId, rootUrl);
			JSONObject jiaMengShangMap = builMerchantQrCodeMap(
					MerchantQrCodeTypeEnum.ADD_JIA_MENG_SHANG.getCode(), merchantId, rootUrl);
			JSONObject terminalMap = builMerchantQrCodeMap(MerchantQrCodeTypeEnum.ADD_TERMINAL.getCode(),
					merchantId, rootUrl);
			resultList.add(puHuoShangMap);
			resultList.add(jiaMengShangMap);
			resultList.add(terminalMap);
		}
		// 4.铺货商
		if (MerchantTypeEnum.PUHUO_SHANG.getCode().intValue() == merchantType) {
			JSONObject jiaMengShangMap = builMerchantQrCodeMap(
					MerchantQrCodeTypeEnum.ADD_JIA_MENG_SHANG.getCode(), merchantId, rootUrl);
			JSONObject terminalMap = builMerchantQrCodeMap(MerchantQrCodeTypeEnum.ADD_TERMINAL.getCode(),
					merchantId, rootUrl);
			resultList.add(jiaMengShangMap);
			resultList.add(terminalMap);
		}
		// 5.加盟商
		if (MerchantTypeEnum.JIA_MENG_SHANG.getCode().intValue() == merchantType) {
			JSONObject terminalMap = builMerchantQrCodeMap(MerchantQrCodeTypeEnum.ADD_TERMINAL.getCode(),
					merchantId, rootUrl);
			resultList.add(terminalMap);
		}
		result.setResponseInfo(resultList);
		return result;
	}

	/**
	 * 封装添加商户二维码信息
	 * 
	 * @param code
	 * @param merchantId
	 * @param rootUrl
	 * @return
	 */
	private JSONObject builMerchantQrCodeMap(Integer code, String merchantId, String rootUrl) {
		MerchantQrCodeBO bo = new MerchantQrCodeBO();
		bo.setOpTypeCn(MerchantQrCodeTypeEnum.getDesc(code));
		bo.setOpType(code);
		bo.setQrCodeUrl(rootUrl + "wx/addMerchantInfoUI?merchantId=" + merchantId + "&opType=" + code);
		return JSONObject.fromObject(bo);
	}
}
