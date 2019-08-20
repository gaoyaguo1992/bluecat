package com.stylefeng.guns.modular.system.service;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.system.model.MerchantInfo;
import com.stylefeng.guns.modular.wxApplication.bo.RegisterTerminalByCodeBO;
import com.stylefeng.guns.sharecore.common.persistence.model.ResultCommandBaseObject;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author stylefeng
 * @since 2019-01-20
 */
public interface IMerchantInfoService extends IService<MerchantInfo> {

	/**
	 * 注册商户信息
	 * 
	 * @param superMerchantId 上级商户id
	 * @param custNo          将要注册商户的客户号
	 * @param merchantInfo    提交注册的商户信息
	 * @param opType          要注册的商户类型
	 * @throws Exception
	 */
	ResultCommandBaseObject<Object> registerMerchant(RegisterTerminalByCodeBO registerTerminalByCodeBO)
			throws Exception;

	/**
	 * 导出数据
	 * 
	 * @param response
	 * @param pageFilter
	 */
	void exportExcel(HttpServletResponse response, HashMap<String, Object> pageFilter) throws IOException;

}
