package com.stylefeng.guns.modular.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.core.util.DateUtils;
import com.stylefeng.guns.modular.system.dao.DeviceInfoMapper;
import com.stylefeng.guns.modular.system.model.DeviceInfo;
import com.stylefeng.guns.modular.system.service.IDeviceInfoService;
import com.stylefeng.guns.sharecore.common.utils.ExportExcel;
import com.stylefeng.guns.sharecore.modular.system.dao.ShareDeviceInfoModelBySelfMapper;
import com.stylefeng.guns.sharecore.modular.system.model.MerchantTypeEnum;
import com.stylefeng.guns.sharecore.modular.system.model.ShareDeviceInfoBO;
import com.stylefeng.guns.sharecore.modular.system.model.ShareDeviceInfoBySelfModel;
import com.stylefeng.guns.sharecore.modular.system.model.ShareDeviceInfoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 设备表 服务实现类
 * </p>
 *
 * @author alian li
 * @since 2019-01-20
 */
@Service
public class DeviceInfoServiceImpl extends ServiceImpl<DeviceInfoMapper, DeviceInfo> implements IDeviceInfoService {

    @Autowired
    private ShareDeviceInfoModelBySelfMapper shareDeviceInfoModelBySelfMapper;

    @Value("${sharehelper.isValidateBindMer}")
    private String isValidateBindMer;

    @Override
    public void exportExcel(HttpServletResponse response, HashMap<String, Object> pageFilter) throws IOException {
        List<ShareDeviceInfoBySelfModel> list = shareDeviceInfoModelBySelfMapper.selectByConditionForPage(pageFilter);
        String fileName = DateUtils.getCurrentDate(DateUtils.LONG_FORMAT) + ".xlsx";
        new ExportExcel("设备数据", ShareDeviceInfoModel.class, 2).setDataList(list).write(response, fileName).dispose();

    }

    @Override
    public boolean isBindMer(ShareDeviceInfoBySelfModel deviceInfoMode, Integer merchantType) {
        if ("false".equals(isValidateBindMer)) {
            return Boolean.FALSE;
        }
        if (deviceInfoMode == null) {
            return Boolean.FALSE;
        }
        if (MerchantTypeEnum.DAI_LI_SHANG1.getCode().intValue() == merchantType.intValue()) {
            if (deviceInfoMode.getAgents1Id() != null && deviceInfoMode.getAgents1Id().longValue() > 1L) {
                return Boolean.TRUE;
            }
        }
        if (MerchantTypeEnum.DAI_LI_SHANG2.getCode().intValue() == merchantType.intValue()) {
            if (deviceInfoMode.getAgents2Id() != null && deviceInfoMode.getAgents2Id().longValue() > 1L) {
                return Boolean.TRUE;
            }
        }
        if (MerchantTypeEnum.DAI_LI_SHANG3.getCode().intValue() == merchantType.intValue()) {
            if (deviceInfoMode.getAgents3Id() != null && deviceInfoMode.getAgents3Id().longValue() > 1L) {
                return Boolean.TRUE;
            }
        }
        if (MerchantTypeEnum.PUHUO_SHANG.getCode().intValue() == merchantType.intValue()) {
            if (deviceInfoMode.getShopkeeperId() != null && deviceInfoMode.getShopkeeperId().longValue() > 1L) {
                return Boolean.TRUE;
            }
        }
        if (MerchantTypeEnum.JIA_MENG_SHANG.getCode().intValue() == merchantType.intValue()) {
            if (deviceInfoMode.getAllianceBusinessId() != null && deviceInfoMode.getAllianceBusinessId().longValue() > 1L) {
                return Boolean.TRUE;
            }
        }
        if (MerchantTypeEnum.TERMINAL.getCode().intValue() == merchantType.intValue()) {
            if (deviceInfoMode.getOnlineMerchantId() != null && deviceInfoMode.getOnlineMerchantId().longValue() > 1L) {
                return Boolean.TRUE;
            }
        }

        return Boolean.FALSE;
    }

    @Override
    public boolean isBindMer(ShareDeviceInfoBySelfModel existsDevice, ShareDeviceInfoBySelfModel newDevice) {
        if ("false".equals(isValidateBindMer)) {
            return Boolean.FALSE;
        }
        if (existsDevice == null) {
            return Boolean.FALSE;
        }
        if (newDevice == null) {
            return Boolean.FALSE;
        }
        if (newDevice.getAgents1Id() != null && newDevice.getAgents1Id() > 1L && isBindMer(existsDevice,MerchantTypeEnum.DAI_LI_SHANG1.getCode())) {
            if (!newDevice.getAgents1Id().equals(existsDevice.getAgents1Id())) {
                return Boolean.TRUE;
            }
        }
        if (newDevice.getAgents2Id() != null && newDevice.getAgents2Id() > 1L && isBindMer(existsDevice,MerchantTypeEnum.DAI_LI_SHANG2.getCode())) {
            if (!newDevice.getAgents2Id().equals(existsDevice.getAgents2Id())) {
                return Boolean.TRUE;
            }
        }
        if (newDevice.getAgents3Id() != null && newDevice.getAgents3Id() > 1L && isBindMer(existsDevice,MerchantTypeEnum.DAI_LI_SHANG3.getCode())) {
            if (!newDevice.getAgents3Id().equals(existsDevice.getAgents3Id())) {
                return Boolean.TRUE;
            }
        }
        if (newDevice.getShopkeeperId() != null && newDevice.getShopkeeperId() > 1L && isBindMer(existsDevice,MerchantTypeEnum.PUHUO_SHANG.getCode())) {
            if (!newDevice.getShopkeeperId().equals(existsDevice.getShopkeeperId())) {
                return Boolean.TRUE;
            }
        }
        if (newDevice.getAllianceBusinessId() != null && newDevice.getAllianceBusinessId() > 1L && isBindMer(existsDevice,MerchantTypeEnum.JIA_MENG_SHANG.getCode())) {
            if (!newDevice.getAllianceBusinessId().equals(existsDevice.getAllianceBusinessId())) {
                return Boolean.TRUE;
            }
        }
        if (newDevice.getOnlineMerchantId() != null && newDevice.getOnlineMerchantId() > 1L && isBindMer(existsDevice,MerchantTypeEnum.TERMINAL.getCode())) {
            if (!newDevice.getOnlineMerchantId().equals(existsDevice.getOnlineMerchantId())) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }



    @Override
    public boolean isBindMer(ShareDeviceInfoBySelfModel existsDevice, ShareDeviceInfoModel newDevice) {
        ShareDeviceInfoBySelfModel tempModel = new ShareDeviceInfoBySelfModel();
        tempModel.setAgents1Id(newDevice.getAgents1Id());
        tempModel.setAgents2Id(newDevice.getAgents2Id());
        tempModel.setAgents3Id(newDevice.getAgents3Id());
        tempModel.setShopkeeperId(newDevice.getShopkeeperId());
        tempModel.setAllianceBusinessId(newDevice.getAllianceBusinessId());
        tempModel.setOnlineMerchantId(newDevice.getOnlineMerchantId());
        return isBindMer(existsDevice, tempModel);
    }

    @Override
    public boolean isBindMer(ShareDeviceInfoBySelfModel existsDevice, ShareDeviceInfoBO newDevice) {
        ShareDeviceInfoBySelfModel tempModel = new ShareDeviceInfoBySelfModel();
        tempModel.setAgents1Id(newDevice.getAgents1Id());
        tempModel.setAgents2Id(newDevice.getAgents2Id());
        tempModel.setAgents3Id(newDevice.getAgents3Id());
        tempModel.setShopkeeperId(newDevice.getShopkeeperId());
        tempModel.setAllianceBusinessId(newDevice.getAllianceBusinessId());
        tempModel.setOnlineMerchantId(newDevice.getOnlineMerchantId());
        return isBindMer(existsDevice, tempModel);
    }

    @Override
    public boolean isBindMer(ShareDeviceInfoModel existsDevice, ShareDeviceInfoBO newDevice) {
        ShareDeviceInfoBySelfModel tempModel = new ShareDeviceInfoBySelfModel();
        tempModel.setAgents1Id(existsDevice.getAgents1Id());
        tempModel.setAgents2Id(existsDevice.getAgents2Id());
        tempModel.setAgents3Id(existsDevice.getAgents3Id());
        tempModel.setShopkeeperId(existsDevice.getShopkeeperId());
        tempModel.setAllianceBusinessId(existsDevice.getAllianceBusinessId());
        tempModel.setOnlineMerchantId(existsDevice.getOnlineMerchantId());
        return isBindMer(tempModel, newDevice);
    }

    @Override
    public boolean isBindMer(Long startDeviceNo, Long endDeviceNo, ShareDeviceInfoModel newDevice) {
        if ("false".equals(isValidateBindMer)) {
            return Boolean.FALSE;
        }
        HashMap<String,Object> queryParam = new HashMap<>();
        queryParam.put("start",startDeviceNo);
        queryParam.put("end",endDeviceNo);
        List<ShareDeviceInfoBySelfModel> existsDevices = shareDeviceInfoModelBySelfMapper.selectByConditionForPage(queryParam);
        boolean temp = Boolean.FALSE;
        for(ShareDeviceInfoBySelfModel model : existsDevices){
            temp = isBindMer(model,newDevice);
            if(temp){
                return temp;
            }
        }
        return temp;
    }

    @Override
    public boolean isBindMer(List<Long> listForDeviceIds, ShareDeviceInfoModel newDevice) {
        if ("false".equals(isValidateBindMer)) {
            return Boolean.FALSE;
        }
        HashMap<String,Object> queryParam = new HashMap<>();
        queryParam.put("deviceIdList",listForDeviceIds);
        List<ShareDeviceInfoBySelfModel> existsDevices = shareDeviceInfoModelBySelfMapper.selectByConditionForPage(queryParam);
        boolean temp = Boolean.FALSE;
        for(ShareDeviceInfoBySelfModel model : existsDevices){
            temp = isBindMer(model,newDevice);
            if(temp){
                return temp;
            }
        }
        return temp;
    }


}
