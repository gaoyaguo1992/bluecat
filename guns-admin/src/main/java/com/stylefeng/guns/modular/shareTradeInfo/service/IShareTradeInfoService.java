package com.stylefeng.guns.modular.shareTradeInfo.service;

import com.stylefeng.guns.modular.system.model.ShareTradeInfo;

import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author stylefeng
 * @since 2019-01-21
 */
public interface IShareTradeInfoService extends IService<ShareTradeInfo> {

	void exportExcel(int merchantType, HttpServletResponse response, HashMap<String, Object> pageFilter)throws Exception;

}
