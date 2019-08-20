/**
 * 
 */
package com.stylefeng.guns.sharecore.modular.system.constants;

/**
 * 
 * 审核状态
 *
 */
public enum ApproveStatusEnum {
	
	PASS(1,"通过"),
	REFUSE(2,"拒绝"),
	WAIT_FOR_APPROVE(3,"待审核"),
	NO_NEED_APPROVE(4,"无需审核");
	
	private Integer code;
	private String desc;

	ApproveStatusEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public static String getDesc(Integer code) {
		String desc = "";
		for (ApproveStatusEnum bn : values()) {
			if (code == bn.getCode()) {
				desc = bn.getDesc();
				break;
			}
		}
		return desc;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
}
