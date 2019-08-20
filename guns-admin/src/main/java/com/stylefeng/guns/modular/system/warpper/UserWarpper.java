package com.stylefeng.guns.modular.system.warpper;

import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.sharecore.modular.system.model.MerchantTypeEnum;
import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;

import java.util.List;
import java.util.Map;

/**
 * 用户管理的包装类
 *
 * @author fengshuonan
 * @date 2017年2月13日 下午10:47:03
 */
public class UserWarpper extends BaseControllerWarpper {

    public UserWarpper(List<Map<String, Object>> list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {
        map.put("sexName", ConstantFactory.me().getSexName((Integer) map.get("sex")));
        map.put("roleName", ConstantFactory.me().getRoleName((String) map.get("roleid")));
        map.put("deptName", ConstantFactory.me().getDeptName((Integer) map.get("deptid")));
        map.put("statusName", ConstantFactory.me().getStatusName((Integer) map.get("status")));
        Integer merchantType =  (Integer) map.get("merchant_type");
        if(merchantType != null && merchantType.intValue() == MerchantTypeEnum.DAI_LI_SHANG1.getCode().intValue()) {
        	map.put("merchantTypeCn", MerchantTypeEnum.DAI_LI_SHANG1.getDesc());
        }
        if(merchantType != null && merchantType.intValue() == MerchantTypeEnum.DAI_LI_SHANG2.getCode().intValue()) {
        	map.put("merchantTypeCn", MerchantTypeEnum.DAI_LI_SHANG2.getDesc());
        }
        if(merchantType != null && merchantType.intValue() == MerchantTypeEnum.DAI_LI_SHANG3.getCode().intValue()) {
        	map.put("merchantTypeCn", MerchantTypeEnum.DAI_LI_SHANG3.getDesc());
        }
        if(merchantType != null && merchantType.intValue() == MerchantTypeEnum.PUHUO_SHANG.getCode().intValue()) {
        	map.put("merchantTypeCn", MerchantTypeEnum.PUHUO_SHANG.getDesc());
        }
        if(merchantType != null && merchantType.intValue() == -1) {
        	map.put("merchantTypeCn", "不指定商户类型");
        }
    }

}
