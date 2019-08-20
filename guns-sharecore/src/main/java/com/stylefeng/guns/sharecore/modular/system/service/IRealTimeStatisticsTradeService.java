package com.stylefeng.guns.sharecore.modular.system.service;

import java.math.BigDecimal;

/**
 * 
 * <P>实时分润</P>
 */
public interface IRealTimeStatisticsTradeService {
	
	/**
	 * 
	 * <p>实时分润</p>
	 * @param ti
	 * @param tradeAmount
	 * @throws Exception
	 */
    void realTimeStatisticsTrade(Long tradeId, BigDecimal tradeAmount) throws Exception;
	
}
