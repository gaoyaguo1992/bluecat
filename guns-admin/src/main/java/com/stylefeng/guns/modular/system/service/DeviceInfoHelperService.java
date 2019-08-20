package com.stylefeng.guns.modular.system.service;

import com.stylefeng.guns.core.util.SpringMvcParamTransferred;
import com.stylefeng.guns.modular.wxApplication.service.MerchantInfoHelperService;
import com.stylefeng.guns.sharecore.common.persistence.model.CustInfoModel;
import com.stylefeng.guns.sharecore.common.persistence.model.ResultCommandBaseObject;
import com.stylefeng.guns.sharecore.modular.system.constants.*;
import com.stylefeng.guns.sharecore.modular.system.dao.*;
import com.stylefeng.guns.sharecore.modular.system.model.*;
import com.stylefeng.guns.sharecore.modular.system.service.BaseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 处理设备助手..
 *
 * @author alian li
 * @since 2019-01-20
 */
@Service("gunsadmin.DeviceInfoHelperService")
public class DeviceInfoHelperService extends BaseService {
    /**
     * 设备操作..
     */
    @Autowired
    private ShareDeviceInfoModelBySelfMapper shareDeviceInfoModelBySelfMapper;
    /**
     * 操作..
     */
    @Autowired
    private ShareDeviceInfoModelMapper shareDeviceInfoModelMapper;
    /**
     * 获到商户信息...
     */
    @Autowired
    private MerchantInfoModelMapper merchantInfoModelMapper;
    /**
     * 充蛡器
     */
    @Autowired
    private ShareChargerModelBySelfMapper shareChargerModelBySelfMapper;
    /**
     * 客户商户关系表
     */
    @Autowired
    private CustMerchantRefInfoBySelfModelMapper custMerchantRefInfoBySelfModelMapper;
    /**
     * 客户商户关系表..
     */
    @Autowired
    private CustMerchantRefInfoModelMapper custMerchantRefInfoModelMapper;
    /**
     * 处理商户服务。。。
     */
    @Autowired
    @Qualifier("gunsadmin.MerchantInfoHelperService")
    private MerchantInfoHelperService merchantInfoHelperService;

    /**
     * 检验设备信息是否可以保存到数据库中
     *
     * @param deviceInfo
     * @param checkFee
     * @param result
     * @return
     */
    public Boolean validateDeviceInfoForSave(ShareDeviceInfoModel deviceInfo, Boolean checkFee,
                                             ResultCommandBaseObject<Object> result) {
        if (result == null) {
            result = new ResultCommandBaseObject<>();
        }
        if (deviceInfo == null) {
            result.setResult(ResultCommandBaseObject.FAILED);
            result.setMessage("请输入选择正确的充电设备！");
            return false;
        }
        if (deviceInfo.getDeviceTypeId() == null) {
            result.setResult(ResultCommandBaseObject.FAILED);
            result.setMessage("设备类型不能为空，请选择正确的设备类型！");
            return false;
        }

        ShareDeviceTypeEnum deviceTypeEnum = ShareDeviceTypeEnum
                .getShareDeviceTypeEnumByCode(deviceInfo.getDeviceTypeId());
        if (deviceTypeEnum == null) {
            result.setResult(ResultCommandBaseObject.FAILED);
            result.setMessage("设备类型不能为空，请选择正确的设备类型！");
            return false;
        }
        if (checkFee
                && (deviceTypeEnum.getPasswordType() == ShareChargerPasswordTypeEnum.PW_10.getCode()
                || deviceTypeEnum.getPasswordType() == ShareChargerPasswordTypeEnum.PW_20.getCode())
                && (deviceInfo.getFeeTypeId() == null || deviceInfo.getFeeTypeId() == 0
                || deviceInfo.getAmountPerHour() == null || deviceInfo.getAmountMax24hour() == null
                || deviceInfo.getYfjAmount() == null)) {
            result.setResult(ResultCommandBaseObject.FAILED);
            result.setMessage("未设置使用费用，请选设置使用费用！");
            return false;
        }
        if (checkFee
                && (deviceTypeEnum.getPasswordType() == ShareChargerPasswordTypeEnum.PW_11.getCode())
                && (deviceInfo.getFee1TypeId() == null || deviceInfo.getFee1HourAmount() == null)
                && (deviceInfo.getFee2TypeId() == null || deviceInfo.getFee2HourAmount() == null)
                && (deviceInfo.getFee3TypeId() == null || deviceInfo.getFee3HourAmount() == null)) {
            result.setResult(ResultCommandBaseObject.FAILED);
            result.setMessage("未设置使用费用，请选设置使用费用！");
            return false;
        }
        // fee type 处理参数
        if (deviceTypeEnum.getPasswordType() == ShareChargerPasswordTypeEnum.PW_11.getCode()) {
            deviceInfo.setFeeTypeId(0);
            if (deviceInfo.getFee1TypeId() == null && deviceInfo.getFee2TypeId() == null
                    && deviceInfo.getFee3TypeId() == null) {
                deviceInfo.setFee1TypeId(ShareFeeTypeEnum.Designated_Time_1.getCode());
            }
        }
        if (deviceTypeEnum.getPasswordType() == ShareChargerPasswordTypeEnum.PW_30.getCode()|| deviceTypeEnum.getPasswordType() == ShareChargerPasswordTypeEnum.PW_21.getCode()) {
            if (deviceInfo.getFeeTypeId() == null) {
                deviceInfo.setFeeTypeId(0);
                deviceInfo.setFeeTypeName(null);
                deviceInfo.setYfjAmount(null);
                deviceInfo.setFirstAmount(null);
                deviceInfo.setFirstMinutes(null);
                deviceInfo.setAmountPerHour(null);
                deviceInfo.setAmountMax24hour(null);
            } else {
                deviceInfo.setFee1TypeId(null);
                deviceInfo.setFee1TypeName(null);
                deviceInfo.setFee1HourType(null);
                deviceInfo.setFee1HourAmount(null);
                deviceInfo.setFee2TypeId(null);
                deviceInfo.setFee2TypeName(null);
                deviceInfo.setFee2HourType(null);
                deviceInfo.setFee2HourAmount(null);
                deviceInfo.setFee3TypeId(null);
                deviceInfo.setFee3TypeName(null);
                deviceInfo.setFee3HourType(null);
                deviceInfo.setFee3HourAmount(null);

            }
        }
        if (deviceTypeEnum.getPasswordType() == ShareChargerPasswordTypeEnum.PW_10.getCode()
                || deviceTypeEnum.getPasswordType() == ShareChargerPasswordTypeEnum.PW_20.getCode()) {
            if (deviceInfo.getFeeTypeId() == null) {
                deviceInfo.setFeeTypeId(ShareFeeTypeEnum.Prepayment.getCode());
            }
        }
        deviceInfo.setDeviceTypeName(ShareDeviceTypeEnum.getDesc(deviceInfo.getDeviceTypeId()));
        if (deviceInfo.getFeeTypeId() != null && deviceInfo.getFeeTypeId() > 0) {
            deviceInfo.setFeeTypeName(ShareFeeTypeEnum.getDesc(deviceInfo.getFeeTypeId()));
        }
        if (deviceInfo.getYfjAmount() == null) {
            deviceInfo.setYfjAmount(new BigDecimal("10"));
        }
        if (deviceInfo.getAmountPerHour() == null) {
            deviceInfo.setAmountPerHour(new BigDecimal("1"));
        }
        if (deviceInfo.getAmountMax24hour() == null) {
            deviceInfo.setAmountMax24hour(new BigDecimal("10"));
        }
        if (deviceInfo.getFirstMinutes() == null) {
            deviceInfo.setFirstMinutes(new Long("180"));
        }
        if (deviceInfo.getFirstAmount() == null) {
            deviceInfo.setFirstAmount(new BigDecimal("1"));
        }
        // fee 1
        if (deviceInfo.getFee1TypeId() != null && deviceInfo.getFee1TypeId() > 0) {
            ShareFeeTypeEnum tmpEnum = ShareFeeTypeEnum
                    .getShareFeeTypeEnumByCode(deviceInfo.getFee1TypeId().toString());
            deviceInfo.setFee1TypeName(tmpEnum.getDesc());
            deviceInfo.setFee1HourType(tmpEnum.getUseTimesForHour().intValue());
        }
        if (deviceInfo.getFee1HourAmount() == null) {
            deviceInfo.setFee1HourAmount(new BigDecimal("1"));
        }
        // fee 2
        if (deviceInfo.getFee2TypeId() != null && deviceInfo.getFee2TypeId() > 0) {
            ShareFeeTypeEnum tmpEnum = ShareFeeTypeEnum
                    .getShareFeeTypeEnumByCode(deviceInfo.getFee2TypeId().toString());
            deviceInfo.setFee2TypeName(tmpEnum.getDesc());
            deviceInfo.setFee2HourType(tmpEnum.getUseTimesForHour().intValue());
        }
        if (deviceInfo.getFee2HourAmount() == null) {
            deviceInfo.setFee2HourAmount(new BigDecimal("1"));
        }
        // fee 3
        if (deviceInfo.getFee3TypeId() != null && deviceInfo.getFee3TypeId() > 0) {
            ShareFeeTypeEnum tmpEnum = ShareFeeTypeEnum
                    .getShareFeeTypeEnumByCode(deviceInfo.getFee3TypeId().toString());
            deviceInfo.setFee3TypeName(tmpEnum.getDesc());
            deviceInfo.setFee3HourType(tmpEnum.getUseTimesForHour().intValue());
        }
        if (deviceInfo.getFee3HourAmount() == null) {
            deviceInfo.setFee3HourAmount(new BigDecimal("1"));
        }
        if (deviceInfo.getYfjRebackType() != null && deviceInfo.getYfjRebackType().compareTo(new Integer("0")) > 0) {
            deviceInfo.setYfjRebackTypeName(ShareDeviceYfjRebackTypeEnum.getDesc(deviceInfo.getYfjRebackType()));
        } else {
            deviceInfo.setYfjRebackType(null);
        }
        // 处理转义 WEB开发时，在前端通过get / post 方法传递参数的时候 如果实参附带特殊符号，后端接收到的值中特殊符号就会被转义
        // 张三&#40;1&#41;
        SpringMvcParamTransferred.doParamForString(deviceInfo);
        return true;
    }

    /**
     * 批量生成设备信息...
     *
     * @param decice
     * @param userId
     * @throws Exception
     */
    public Long batchAddDeviceInfo(ShareDeviceInfoForBatchModel deviceInfoModel, Integer userId) throws Exception {
        if (deviceInfoModel == null) {
            throw new Exception("批量生成设备参数不正确，请输入正确的参数!");
        }
        if (deviceInfoModel.getDeviceQty() == null || deviceInfoModel.getDeviceQty() <= 0) {
            throw new Exception("批量生成设备数量必须大于0，请输入正确的批量生成设备数量!");
        }
        if (deviceInfoModel.getDeviceTypeId() == null || deviceInfoModel.getDeviceQty() <= 0) {
            throw new Exception("设备类型不能为空，请选择正确的设备类型!");
        }
        if (deviceInfoModel.getBatchSetCharger()
                && (deviceInfoModel.getBatchChargerQty() == null || deviceInfoModel.getBatchChargerQty() <= 0)) {
            throw new Exception("批量生成充电器数量必须大于0，请输入正确的批量生成充电器数量!");
        }
        //1.初始他变量。。
        Long startDeviceId = 0L;
        List<ShareDeviceInfoModel> listForDevice = new ArrayList<>();
        Long startChargerId = 0L;
        List<ShareChargerModel> listForCharger = new ArrayList<>();
        ShareChargerTypeEnum chargerTypeEnum = 
        		ShareChargerTypeEnum.getShareChargerTypeEnumByDeviceTypeId(deviceInfoModel.getDeviceTypeId());
        //2. 生成seq..
        if(deviceInfoModel.getPasswordType()!=null&&
        		deviceInfoModel.getPasswordType().equals(ShareDeviceInfoNumberTypeEnum.YYMMBBXXXXX.getCode())){
        	Long seqIndexLng=0L;
        	try {
        		seqIndexLng=Long.parseLong(String.format("%s%s%s",deviceInfoModel.getPasswordYear(),
        							deviceInfoModel.getPasswordMonth(),deviceInfoModel.getPasswordBatch()));        		
			} catch (Exception e) {
				// TODO: handle exception
			}
        	startDeviceId=seqService.getShareDeviceInfoSeqByNumberForYYMMBB(
        								seqIndexLng,deviceInfoModel.getDeviceQty());
        	startChargerId=startDeviceId;
        }else{
	        startDeviceId = seqService.getShareDeviceInfoSeqByNumber(
	        		deviceInfoModel.getDeviceTypeId(),deviceInfoModel.getDeviceQty());
	        // 得到充电器类型..
	        startChargerId = (deviceInfoModel.getBatchSetCharger()) ? seqService.getShareChargerSeqByNumber(
	                chargerTypeEnum.getCode(), deviceInfoModel.getBatchChargerQty() * deviceInfoModel.getDeviceQty()) : 0L;
        }
                
        ShareDeviceInfoModel deviceInfoTmp = null;
        ShareChargerModel shareChargerModel = null;
        Date now = new Date();
        for (int i = 0; i < deviceInfoModel.getDeviceQty(); i++) {
            deviceInfoTmp = new ShareDeviceInfoModel();
            BeanUtils.copyProperties(deviceInfoModel, deviceInfoTmp);
            SpringMvcParamTransferred.doParamForString(deviceInfoTmp);
            deviceInfoTmp.setId(startDeviceId + i);
            deviceInfoTmp.setCreateId(userId.longValue());
            if (deviceInfoTmp.getYfjRebackType() == null) {
                deviceInfoTmp.setYfjRebackType(ShareDeviceYfjRebackTypeEnum.RebackToBalance.getCode());
                deviceInfoTmp.setYfjRebackTypeName(ShareDeviceYfjRebackTypeEnum.RebackToBalance.getDesc());
            }
            deviceInfoTmp.setUpdateDatetime(now);
            deviceInfoTmp.setCreateDatetime(now);
            deviceInfoTmp.setPasswordType(deviceInfoModel.getPasswordType());
            deviceInfoTmp.setPasswordYear(deviceInfoModel.getPasswordYear());
            deviceInfoTmp.setPasswordMonth(deviceInfoModel.getPasswordMonth());
            deviceInfoTmp.setPasswordBatch(deviceInfoModel.getPasswordBatch());
            deviceInfoTmp.setPasswordKey(deviceInfoModel.getPasswordKey());
            
            listForDevice.add(deviceInfoTmp);
            if (deviceInfoModel.getBatchSetCharger() && deviceInfoModel.getBatchChargerQty() != 0
                    && deviceInfoModel.getBatchChargerQty() > 0) {
                for (int j = 0; j < deviceInfoModel.getBatchChargerQty(); j++) {
                    shareChargerModel = new ShareChargerModel();
                    shareChargerModel.setId(startChargerId);
                    shareChargerModel.setAgents1Id(deviceInfoModel.getAgents1Id());
                    shareChargerModel.setAgents2Id(deviceInfoModel.getAgents2Id());
                    shareChargerModel.setAgents3Id(deviceInfoModel.getAgents3Id());
                    shareChargerModel.setChargerTypeId(chargerTypeEnum.getCode());
                    shareChargerModel.setChargerTypeName(chargerTypeEnum.getDesc());
                    shareChargerModel.setCreateDateTime(now);
                    shareChargerModel.setCreateId(userId.longValue());
                    shareChargerModel.setDeviceId(deviceInfoTmp.getId());
                    shareChargerModel.setRemark(deviceInfoModel.getBatchChargerRemark());
                    shareChargerModel.setPwdIndex(0L);
                    shareChargerModel.setTotalAmountYesterday(0L);
                    shareChargerModel.setUpdateDateTime(now);
                    shareChargerModel.setUseCountTimesYesterday(0L);
                    listForCharger.add(shareChargerModel);
                    startChargerId = startChargerId + 1;
                }
            }
        }
        // 批量保存
        batchAddDeviceInfoInsert(listForDevice, listForCharger);
        return startDeviceId;
    }

    /**
     * 批量保存数据...
     *
     * @param listForDevice
     * @param listForCharger
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 10, rollbackFor = Exception.class)
    public void batchAddDeviceInfoInsert(List<ShareDeviceInfoModel> listForDevice,
                                         List<ShareChargerModel> listForCharger) {
        // 3. 批量插入设备表。
        shareDeviceInfoModelBySelfMapper.insertBatch(listForDevice);
        // shareDeviceInfoModelMapper.insert(listForDevice.get(0));
        // 4. 批量插入充电器表
        if (listForCharger.size() > 0) {
            shareChargerModelBySelfMapper.insertBatch(listForCharger);
        }
    }

    /**
     * 批量保存数据...
     *
     * @param listForDevice
     * @param listForCharger
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 10, rollbackFor = Exception.class)
    public void batchActiveDeviceByMerchant(CustInfoModel custInfoModel
            , MerchantInfoModel merchantInfoModel, ShareDeviceInfoModel shareDeviceInfoModel) {
        //1. 绑定客户及商户关系
        CustMerchantRefInfoModelKey key = new CustMerchantRefInfoModelKey();
        key.setCustNo(custInfoModel.getCustNo());
        key.setMerchantId(merchantInfoModel.getId());
        CustMerchantRefInfoModel custMerchantRefInfo = custMerchantRefInfoModelMapper.selectByPrimaryKey(key);
        if (custMerchantRefInfo == null) {
            custMerchantRefInfo = new CustMerchantRefInfoModel();
            custMerchantRefInfo.setCreateId(-1L);
            custMerchantRefInfo.setCreateTime(new Date());
            custMerchantRefInfo.setCustNo(custInfoModel.getCustNo());
            if (custInfoModel.getCustType() == null) {
                custMerchantRefInfo.setCustType(custInfoModel.getCustType().byteValue());
            }
            custMerchantRefInfo.setIsDefault((byte) 0);
            custMerchantRefInfo.setMerchantId(merchantInfoModel.getId());
            custMerchantRefInfo.setMerchantName(merchantInfoModel.getName());
            if (merchantInfoModel.getMerchantType() != null) {
                custMerchantRefInfo.setMerchantType(merchantInfoModel.getMerchantType().byteValue());
                custMerchantRefInfo.setMerchantTypeCn(merchantInfoModel.getMerchantTypeCn());
            }
            custMerchantRefInfoModelMapper.insert(custMerchantRefInfo);
        }
        // 2. 批量修改设备表。
        String filter = "and device_status!=10";
        shareDeviceInfoModelBySelfMapper.batchBindFeeByOnlineMerchId(shareDeviceInfoModel, merchantInfoModel.getId(), filter);
        //
    }

    /**
     * 处理设备绑定商户的业务逻辑。。。
     *
     * @param deviceInfo
     * @throws Exception
     */
    public void bindMerchantForDevice(ShareDeviceInfoBO deviceInfo) throws Exception {
        if (deviceInfo.getActivationMode() != null
                && deviceInfo.getActivationMode().intValue() == ShareDeviceActivationModeEnum.waitForConfirmationStatus
                .getCode()
                && deviceInfo.getDeviceStatus() != null
                && deviceInfo.getDeviceStatus().intValue() == ShareDeviceStatusEnum.activation.getCode()) {
            // 1.1 初始化更新参数...
            // update..
            Date now = new Date();
            MerchantInfoModel newMerchantInfo = new MerchantInfoModel();
            newMerchantInfo.setAddr(deviceInfo.getTerminalMerchantAddress());
            newMerchantInfo.setProvince(deviceInfo.getTerminalProvince());
            try {
                newMerchantInfo.setProvinceId(Long.parseLong(deviceInfo.getTerminalProvinceId()));
            } catch (Exception e) {
                // TODO: handle exception
            }
            newMerchantInfo.setCity(deviceInfo.getTerminalCity());
            try {
                newMerchantInfo.setCityId(Long.parseLong(deviceInfo.getTerminalCityId()));
            } catch (Exception e) {
                // TODO: handle exception
            }
            newMerchantInfo.setZone(deviceInfo.getTerminalZone());
            try {
                newMerchantInfo.setZoneId(Long.parseLong(deviceInfo.getTerminalZoneId()));
            } catch (Exception e) {
                // TODO: handle exception
            }
            newMerchantInfo.setName(deviceInfo.getTerminalMerchantName());
            newMerchantInfo.setPersonName(deviceInfo.getTerminalPersonName());
            newMerchantInfo.setTelNo(deviceInfo.getTerminalTelNo());
            newMerchantInfo.setMerchantType(MerchantTypeEnum.TERMINAL.getCode().byteValue());
            newMerchantInfo.setMerchantTypeCn(MerchantTypeEnum.TERMINAL.getDesc());
            newMerchantInfo.setCreateTime(now);
            if (deviceInfo.getTerminalRemark() != null && !deviceInfo.getTerminalRemark().isEmpty()) {
                deviceInfo.setRemark(String.format("%s;%s"
                        , deviceInfo.getTerminalRemark(), deviceInfo.getRemark()));
            }
            // 1.1 判断是否是已经存
            if (deviceInfo.getTerminalMerchantId() != null) {
                MerchantInfoModel merchantInfoTmp = merchantInfoModelMapper
                        .selectByPrimaryKey(deviceInfo.getTerminalMerchantId());
                if (merchantInfoTmp == null
                        || merchantInfoTmp.getMerchantType() != MerchantTypeEnum.TERMINAL.getCode().byteValue()) {
                    throw new Exception(String.format("设备(%d)修改失败，要绑定的终端商户(%d)在系统中不存在或类型不对！", deviceInfo.getId(),
                            deviceInfo.getTerminalMerchantId()));
                }
                newMerchantInfo.setUpdateTime(now);
                newMerchantInfo.setId(merchantInfoTmp.getId());
                merchantInfoModelMapper.updateByPrimaryKeySelective(newMerchantInfo);

                //1.2 判断是否有已经上架的设备..
                Long activeDevicesCount =
                        shareDeviceInfoModelBySelfMapper.getActiveDevicesCountByMerchantId(newMerchantInfo.getId());
                if (activeDevicesCount > 0) {
                    deviceInfo.setDeviceStatus(ShareDeviceStatusEnum.activation.getCode());
                } else {
                    deviceInfo.setDeviceStatus(ShareDeviceStatusEnum.waitForConfirmationStatus.getCode());
                }
            } else {
                //1.3 新建商户..
                newMerchantInfo = merchantInfoHelperService.registerMerchantInfo(newMerchantInfo);
                deviceInfo.setDeviceStatus(ShareDeviceStatusEnum.waitForConfirmationStatus.getCode());
            }
            deviceInfo.setOnlineAddress(newMerchantInfo.getAddr());
            deviceInfo.setOnlineCity(newMerchantInfo.getCity());
            deviceInfo.setOnlineProvince(newMerchantInfo.getProvince());
            deviceInfo.setOnlineZone(newMerchantInfo.getZone());
            deviceInfo.setOnlineDatetime(now);
            deviceInfo.setOnlineMerchantId(newMerchantInfo.getId());
            deviceInfo.setOnlineMerchantCn(newMerchantInfo.getName());
        }
    }
}
