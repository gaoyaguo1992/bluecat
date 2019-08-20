package com.stylefeng.guns.sharecore.modular.system.constants;

public enum IsLastLoginEnum {
	YES(1,"是"),
	NO(2,"不是");
	
	private Integer code;
	private String desc;
	
	IsLastLoginEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public static String getDesc(Integer code){
		String desc = "";
		for(IsLastLoginEnum bst : values()){
			if(code == bst.getCode()){
				desc = bst.getDesc();
				break;
			}
		}
		return desc;
	}
}