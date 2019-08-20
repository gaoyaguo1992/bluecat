package com.stylefeng.guns.sharecore.modular.system;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import com.stylefeng.guns.core.util.DateUtil;

public class Test {
	public static void main(String[] args) {	
			Date borrowDateTime =DateUtil.parseTime("2019-07-06 20:40:57");
			Date currentTime = DateUtil.parseTime("2019-07-07 06:06:46");
		    Long haveUsedSeconds = ((currentTime.getTime() - borrowDateTime.getTime()) / 1000);
		    BigDecimal usedSeconds = new BigDecimal(haveUsedSeconds);
		    // 计算使用分钟 ,向下取整
		    double usedMinutes = usedSeconds.divide(new BigDecimal(60), RoundingMode.CEILING).doubleValue();
		    double haveUseMinutes = 0;
			// 1.按小时计算的费用...
	        BigDecimal usedMinutesForAmount = new BigDecimal(usedMinutes);
	        BigDecimal usedAmount = new BigDecimal("0");
	        // 1.1 计算首付款
	        BigDecimal firstAmount =new BigDecimal("3");
	        Long firstMinutes = new Long("180");
	        if (firstAmount == null) {
	            firstAmount = new BigDecimal("0");
	        }
	        if (firstMinutes == null) {
	            firstMinutes = new Long("0");
	        }
	        BigDecimal firstMinutesDe = new BigDecimal(firstMinutes);
	        usedMinutesForAmount = usedMinutesForAmount.subtract(firstMinutesDe);
	        BigDecimal amountMax24hour=new BigDecimal("5");
	        BigDecimal amountPerHour=new BigDecimal("1");
	        
	        usedAmount = firstAmount;
	        if (usedMinutesForAmount.compareTo(new BigDecimal("0")) > 0) {
	      
	            // 2.判断是否设置了每天最高消费
	            if (amountMax24hour != null) {
	                // 2.1计算天
	                Double daysForAmount = usedMinutesForAmount.divide(new BigDecimal("1440"), RoundingMode.DOWN)
	                        .doubleValue();
	                BigDecimal daysForAmountBigDecimal = new BigDecimal(daysForAmount);
	                usedAmount = usedAmount
	                        .add(daysForAmountBigDecimal.multiply(amountMax24hour));
	                // 2.2 计算天外的时间
	                usedMinutesForAmount = usedMinutesForAmount.remainder(new BigDecimal("1440"));
	            }
	            // 3.判断每小时收费金额
	            if (amountPerHour != null) {
	                BigDecimal hourForAmount = usedMinutesForAmount.divide(new BigDecimal("60"), RoundingMode.CEILING);
	                usedAmount = usedAmount.add(hourForAmount.multiply(amountPerHour));
	            }
	            //如果计算出来的结果大于24hjf时，取24小时金额
	            if(usedAmount.compareTo(amountMax24hour)>0){
	            	usedAmount=amountMax24hour;
	            }
	        }
	        
	        System.out.println(String.format("usedAmount=%.2f", usedAmount));
		
	}
}
