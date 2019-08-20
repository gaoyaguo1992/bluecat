package com.stylefeng.guns.sharecore.modular.system.service;

import com.stylefeng.guns.sharecore.common.persistence.model.CallLog;

import javax.servlet.http.HttpServletRequest;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  处理操作日志服务类
 * </p>
 *
 * @author alan li
 * @since 2018-12-30
 */
public interface ICallLogService extends IService<CallLog> {
	/**
	 *保存操作日志
	 * @param request
	 * @param paramValues
	 * @param keyCode
	 * @param session3rd
	 */
	void saveCallLog(HttpServletRequest request, String paramValues, String keyCode, String session3rd);
}
