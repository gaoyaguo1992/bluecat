package com.stylefeng.guns.sharecore.modular.system.model;

import com.stylefeng.guns.sharecore.common.persistence.model.BaseObject;

/**
 *
 */
public class CapitalAccountInfoBO extends BaseObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6818123179530672442L;

	private String balance = "";

	private String frozenBalance = "";

	private String availableBalance ="";
	
	private String rewardAvailableBalance ="";
	
	private String openId;
	
	protected String nickName;
	
	protected String msg;
	
	private String result;
	
	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	protected String headImgUrl;

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getFrozenBalance() {
		return frozenBalance;
	}

	public void setFrozenBalance(String frozenBalance) {
		this.frozenBalance = frozenBalance;
	}

	public String getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(String availableBalance) {
		this.availableBalance = availableBalance;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getRewardAvailableBalance() {
		return rewardAvailableBalance;
	}

	public void setRewardAvailableBalance(String rewardAvailableBalance) {
		this.rewardAvailableBalance = rewardAvailableBalance;
	}
}
