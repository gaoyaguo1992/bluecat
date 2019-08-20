package com.stylefeng.guns.sharecore.modular.system.service.impl;

import com.stylefeng.guns.sharecore.common.persistence.model.CustInfo;
import com.stylefeng.guns.sharecore.common.persistence.dao.CustInfoMapper;
import com.stylefeng.guns.sharecore.modular.system.service.ICustInfoService;
import com.stylefeng.guns.sharecore.modular.system.service.IRealTimeStatisticsTradeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author alan li
 * @since 2019-01-01
 */
@Service("realTimeStatisticsTradeService")
public class RealTimeStatisticsTradeService implements IRealTimeStatisticsTradeService {

	@Override
	public void realTimeStatisticsTrade(Long tradeId, BigDecimal tradeAmount) {
		// TODO Auto-generated method stub
		
	}

}
