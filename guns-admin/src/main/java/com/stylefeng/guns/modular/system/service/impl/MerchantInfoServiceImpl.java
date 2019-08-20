package com.stylefeng.guns.modular.system.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.core.util.DateUtils;
import com.stylefeng.guns.modular.system.dao.MerchantInfoMapper;
import com.stylefeng.guns.modular.system.model.MerchantInfo;
import com.stylefeng.guns.modular.system.service.IMerchantInfoService;
import com.stylefeng.guns.modular.wxApplication.bo.RegisterTerminalByCodeBO;
import com.stylefeng.guns.modular.wxApplication.service.MerchantInfoHelperService;
import com.stylefeng.guns.sharecore.common.persistence.dao.CustInfoModelMapper;
import com.stylefeng.guns.sharecore.common.persistence.model.CustInfoModel;
import com.stylefeng.guns.sharecore.common.persistence.model.ResultCommandBaseObject;
import com.stylefeng.guns.sharecore.common.utils.ExportExcel;
import com.stylefeng.guns.sharecore.modular.system.dao.MerchantInfoModelBySelfMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.MerchantInfoModelMapper;
import com.stylefeng.guns.sharecore.modular.system.model.MerchantInfoModel;
import com.stylefeng.guns.sharecore.modular.system.model.MerchantQrCodeTypeEnum;
import com.stylefeng.guns.sharecore.modular.system.model.MerchantTypeEnum;
import com.stylefeng.guns.sharecore.modular.system.model.ShareDeviceInfoModel;
import com.stylefeng.guns.sharecore.modular.system.service.SequenceService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2019-01-20
 */
@Service
public class MerchantInfoServiceImpl extends ServiceImpl<MerchantInfoMapper, MerchantInfo>
		implements IMerchantInfoService {

	private static final Logger logger = LoggerFactory.getLogger(MerchantInfoServiceImpl.class);
	@Autowired
	private MerchantInfoModelMapper merchantInfoModelMapper;

	@Autowired
	private MerchantInfoModelBySelfMapper merchantInfoModelBySelfMapper;

	@Autowired
	private CustInfoModelMapper custInfoModelMapper;

	@Autowired
	private MerchantInfoHelperService merchantInfoHelperService;

	@Autowired
	@Qualifier("common.SequenceService")
	protected SequenceService seqService;

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 10, rollbackFor = Exception.class)
	public ResultCommandBaseObject<Object> registerMerchant(RegisterTerminalByCodeBO registerTerminalByCodeBO)
			throws Exception {
		ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
		// 参数合法性检查
		result = validateRegisterTerminalByCodeBO(registerTerminalByCodeBO);
		if (result.getResult().equals(ResultCommandBaseObject.FAILED)) {
			return result;
		}

		CustInfoModel custInfoModel = custInfoModelMapper
				.selectByPrimaryKey(Long.valueOf(registerTerminalByCodeBO.getCustNo()));

		if (custInfoModel == null) {
			logger.warn("custNo:{}客户信息不存在，请关注公众号重新扫码！", registerTerminalByCodeBO.getCustNo());
			result.setMessage("客户信息不存在，请关注公众号重新扫码！");
			result.setResult(ResultCommandBaseObject.FAILED);
			return result;
		}

		// 组装商户信息
		MerchantInfoModel merchantModel = new MerchantInfoModel();
		// 复制属性
		logger.info("对象属性复制前：merchantInfoModel:{},registerTerminalByCodeBO:{}", merchantModel,
				registerTerminalByCodeBO);
		BeanUtils.copyProperties(registerTerminalByCodeBO, merchantModel);
		logger.info("对象属性复制后：merchantInfoModel:{},registerTerminalByCodeBO:{}", merchantModel,
				registerTerminalByCodeBO);

		// 关联客户
		merchantModel.setSettlementAccount(registerTerminalByCodeBO.getCustNo());
		merchantModel.setSettlementCustNo(custInfoModel.getCustNo());
		Long parentMerchantId = StringUtils.isEmpty(registerTerminalByCodeBO.getSuperMerchantId()) ? null
				: Long.valueOf(registerTerminalByCodeBO.getSuperMerchantId());

		// 父商户号
		merchantModel.setParentMerchantId(parentMerchantId);

		Integer merchantType = null;
		String merchantTypeCn = "";
		String opType = registerTerminalByCodeBO.getOpType();
		if (MerchantQrCodeTypeEnum.ADD_DAI_LI_SHANG2.getCode().intValue() == Integer.valueOf(opType).intValue()) {
			merchantType = MerchantTypeEnum.DAI_LI_SHANG2.getCode();
			merchantTypeCn = MerchantTypeEnum.DAI_LI_SHANG2.getDesc();
		}
		if (MerchantQrCodeTypeEnum.ADD_DAI_LI_SHANG3.getCode().intValue() == Integer.valueOf(opType).intValue()) {
			merchantType = MerchantTypeEnum.DAI_LI_SHANG3.getCode();
			merchantTypeCn = MerchantTypeEnum.DAI_LI_SHANG3.getDesc();
		}
		if (MerchantQrCodeTypeEnum.ADD_PUHUO_SHANG.getCode().intValue() == Integer.valueOf(opType).intValue()) {
			merchantType = MerchantTypeEnum.PUHUO_SHANG.getCode();
			merchantTypeCn = MerchantTypeEnum.PUHUO_SHANG.getDesc();
		}
		if (MerchantQrCodeTypeEnum.ADD_JIA_MENG_SHANG.getCode().intValue() == Integer.valueOf(opType).intValue()) {
			merchantType = MerchantTypeEnum.JIA_MENG_SHANG.getCode();
			merchantTypeCn = MerchantTypeEnum.JIA_MENG_SHANG.getDesc();
		}
		if (MerchantQrCodeTypeEnum.ADD_TERMINAL.getCode().intValue() == Integer.valueOf(opType).intValue()) {
			merchantType = MerchantTypeEnum.TERMINAL.getCode();
			merchantTypeCn = MerchantTypeEnum.TERMINAL.getDesc();
		}
		merchantModel.setMerchantType(merchantType.byteValue());
		merchantModel.setMerchantTypeCn(merchantTypeCn);
		merchantInfoHelperService.registerMerchantInfo(merchantModel);
		return result;

	}

	/**
	 * 检查注册终端BO的合法
	 * 
	 * @param registerTerminalByCodeBO
	 * @return
	 */
	private ResultCommandBaseObject<Object> validateRegisterTerminalByCodeBO(
			RegisterTerminalByCodeBO registerTerminalByCodeBO) {
		ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
		result.setResult(ResultCommandBaseObject.FAILED);

		if (registerTerminalByCodeBO == null) {
			logger.warn("注册BO对象为空！");
			result.setMessage("提交信息不能为空！");
			return result;
		}
		if (StringUtils.isEmpty(registerTerminalByCodeBO.getCustNo())) {
			logger.warn("注册人客户号不能为空！");
			result.setMessage("注册人客户号不能为空！");
			return result;
		}
		if (StringUtils.isEmpty(registerTerminalByCodeBO.getSuperMerchantId())) {
			logger.warn("推荐人商户id不能为空！");
			result.setMessage("推荐人商户id不能为空！");
			return result;
		}
		if (StringUtils.isEmpty(registerTerminalByCodeBO.getOpType())) {
			logger.warn("注册的商户类型不能为空！");
			result.setMessage("注册的商户类型不能为空！");
			return result;
		}

		if (StringUtils.isEmpty(registerTerminalByCodeBO.getTelNo())) {
			logger.warn("手机号码不能为空!");
			result.setMessage("手机号码不能为空!");
			return result;
		}
		if (StringUtils.isEmpty(registerTerminalByCodeBO.getPersonName())) {
			result.setMessage("联系人姓名不能为空!");
			logger.warn("联系人姓名不能为空!");
			return result;
		}

		String startShopTime = registerTerminalByCodeBO.getStartShopTime();
		String endShopTime = registerTerminalByCodeBO.getEndShopTime();
		if (!StringUtils.isEmpty(startShopTime) && !StringUtils.isEmpty(endShopTime)) {
			String[] starArray = startShopTime.split(":");
			String[] endArray = endShopTime.split(":");
			Integer start = new Integer(starArray[0]) * 100 + new Integer(starArray[1]);
			Integer end = new Integer(endArray[0]) * 100 + new Integer(endArray[1]);
			if (start >= end) {
				result.setMessage("抱歉，您的开始营业时间应该小于结束营业时间!");
				result.setResult(ResultCommandBaseObject.FAILED);
				return result;
			}
		}
		result.setResult(ResultCommandBaseObject.SUCCESS);
		return result;
	}

	@Override
	public void exportExcel(HttpServletResponse response, HashMap<String, Object> pageFilter) throws IOException {
		List<MerchantInfoModel> list = merchantInfoModelBySelfMapper.selectByConditionForPage(pageFilter);
		String fileName = DateUtils.getCurrentDate(DateUtils.LONG_FORMAT) + ".xlsx";
		new ExportExcel("商户数据", MerchantInfoModel.class, 2).setDataList(list).write(response, fileName).dispose();

	}

}
