package com.stylefeng.guns.core.model;
/**
 * 
 * <P>时间差值业务对象</P>
 */
public class TimeGapBO {

	/**
	 * 天
	 */
	private Long day; 
	
	/**
	 * 小时
	 */
	private Long hour;  
	
	/**
	 * 分钟
	 */
	private Long min;  
	
	/**
	 * 秒
	 */
	private Long sec;

	public Long getDay() {
		return day;
	}

	public void setDay(Long day) {
		this.day = day;
	}

	public Long getHour() {
		return hour;
	}

	public void setHour(Long hour) {
		this.hour = hour;
	}

	public Long getMin() {
		return min;
	}

	public void setMin(Long min) {
		this.min = min;
	}

	public Long getSec() {
		return sec;
	}

	public void setSec(Long sec) {
		this.sec = sec;
	}

	public TimeGapBO(Long day, Long hour, Long min, Long sec) {
		super();
		this.day = day;
		this.hour = hour;
		this.min = min;
		this.sec = sec;
	}
	
}
