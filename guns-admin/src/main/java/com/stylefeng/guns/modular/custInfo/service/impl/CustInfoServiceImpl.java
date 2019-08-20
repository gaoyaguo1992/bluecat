package com.stylefeng.guns.modular.custInfo.service.impl;

import com.stylefeng.guns.sharecore.common.persistence.dao.CustInfoMapper;
import com.stylefeng.guns.sharecore.common.persistence.model.CustInfo;
import com.stylefeng.guns.modular.custInfo.service.ICustInfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author alian li
 * @since 2019-01-21
 */
@Service("gunsadminCustInfoServiceImpl")
public class CustInfoServiceImpl extends ServiceImpl<CustInfoMapper, CustInfo> implements ICustInfoService {

}
