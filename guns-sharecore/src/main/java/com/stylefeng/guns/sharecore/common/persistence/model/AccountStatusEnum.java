package com.stylefeng.guns.sharecore.common.persistence.model;

public enum AccountStatusEnum {
	// 1：正常；2：冻结；3,未签约；4已解约；9：注销；80：主结算行切换中；99：异常；

		 NORMAL(1, "正常" ),
		 FROZEN(2, "冻结" ),
		 CANCEL(9, "注销" );

		private Integer code;
		private String desc;

		AccountStatusEnum(Integer code, String desc) {
			this.code = code;
			this.desc = desc;
		}
		//在枚举里加方法
		public int getCode() {
			return code;
		}

		public String getDesc() {
			return desc;
		}

		public static String getDesc(Integer code) {
			String desc = "";
			for (AccountStatusEnum bn : values()) {
				if (code.intValue()==bn.getCode() ) {
					desc = bn.desc;
					break;
				}
			}
			return desc;
		}
	}
