package com.stylefeng.guns.modular.system.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.stylefeng.guns.sharecore.common.persistence.model.ResultCommandBaseObject;
import com.stylefeng.guns.sharecore.modular.system.constants.ShareChargerTypeEnum;
import com.stylefeng.guns.sharecore.modular.system.constants.ShareDeviceActivationModeEnum;
import com.stylefeng.guns.sharecore.modular.system.constants.ShareDeviceStatusEnum;
import com.stylefeng.guns.sharecore.modular.system.constants.ShareDeviceTypeEnum;
import com.stylefeng.guns.sharecore.modular.system.constants.ShareDeviceYfjRebackTypeEnum;
import com.stylefeng.guns.sharecore.modular.system.dao.ShareChargerModelBySelfMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.ShareChargerModelMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.ShareDeviceInfoModelBySelfMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.ShareDeviceInfoModelMapper;
import com.stylefeng.guns.sharecore.modular.system.model.ShareChargerForBatchModel;
import com.stylefeng.guns.sharecore.modular.system.model.ShareChargerModel;
import com.stylefeng.guns.sharecore.modular.system.model.ShareDeviceInfoForBatchModel;
import com.stylefeng.guns.sharecore.modular.system.model.ShareDeviceInfoModel;
import com.stylefeng.guns.sharecore.modular.system.service.BaseService;

/**
 *
 *处理充电器助手..
 *
 * @author alian li
 * @since 2019-01-20
 */
@Service("gunsadmin.ChargerInfoHelperService")
public class ChargerInfoHelperService extends BaseService{
	/**
	 * 设备mybatis操作..
	 */
	@Autowired
	private ShareDeviceInfoModelBySelfMapper shareDeviceInfoModelBySelfMapper;
	/**
	 * 设备mybatis操作..
	 */
	@Autowired
	private ShareDeviceInfoModelMapper shareDeviceInfoModelMapper;
	/**
	 * 充电器mybatis mapper类
	 */
	@Autowired
	private ShareChargerModelBySelfMapper shareChargerModelBySelfMapper;
	/**
	 * 充电器mybatis类
	 */
	@Autowired
	private ShareChargerModelMapper shareChargerModelMapper;
	/**
	 * 设备信息服务类..
	 */
	@Autowired
	private DeviceInfoHelperService deviceInfoHelperService;
	/**
	 * 激活设备模式(2:简单模式， 1：用户扫码方式)
	 */
	@Value("${sharehelper.activationMode}")
	private String activationMode;

	/**
	 * 批量生成设备信息...
	 * @param decice
	 * @param userId
	 * @throws Exception 
	 */
	public Long batchAddChargerInfo(ShareChargerForBatchModel shareCharger,Integer userId) throws Exception{
		if(shareCharger==null){
			throw new Exception("批量生成充电器参数不正确，请输入正确的参数!");
		}
		if(shareCharger.getChargerQty()==null||shareCharger.getChargerQty()<=0){
			throw new Exception("批量生成充电器数量必须大于0，请输入正确的批量生成充电器数量!");
		}
		if(shareCharger.getChargerTypeId()==null||shareCharger.getChargerQty()<=0){
			throw new Exception("充电器类型不能为空，请选择正确的充电器类型!");
		}
		ShareChargerTypeEnum  chargerTypeEnum=
				ShareChargerTypeEnum.getShareChargerTypeEnumByCode(shareCharger.getChargerTypeId());
		if(chargerTypeEnum==null){
			throw new Exception("充电器类型在系统中不存在，请联系管理员添加充电器类型!");
		}
		//1.初始他变量。。
		Long startChargerId=0L;
		Long startDeviceId=null;
		List<ShareDeviceInfoModel> listForDevice=new ArrayList<>();
		List<ShareChargerModel> listForCharger=new ArrayList<>();
		ShareDeviceTypeEnum deviceTypeEnum=
				ShareDeviceTypeEnum.getShareDeviceTypeEnumByCode(shareCharger.getChargerTypeId());
		//2. 生成seq..
		//0:不生成设备编号, 1:一个充电器生成一个设备编号, 2:只生成一个设备编号
		if(shareCharger.getGenerateType()==1){
			startDeviceId=seqService.getShareDeviceInfoSeqByNumber(
							deviceTypeEnum.getCode(), shareCharger.getChargerQty());
		}
		if(shareCharger.getGenerateType()==2){
			startDeviceId=seqService.getShareDeviceInfoSeq(deviceTypeEnum.getCode());
		}
		//得到充电器类型..
		startChargerId=seqService.getShareChargerSeqByNumber(
						chargerTypeEnum.getCode(), shareCharger.getChargerQty());
        ShareDeviceInfoModel deviceInfoModel=null, deviceInfoTmp=null;
        ShareChargerModel shareChargerModel=null,shareChargerTmp=null;
        Date now=new Date();
        //初始化设备信息
        if(shareCharger.getGenerateType()!=0){
	        deviceInfoModel=new ShareDeviceInfoModel();
	        deviceInfoModel.setId(startDeviceId);
	        deviceInfoModel.setDeviceTypeId(deviceTypeEnum.getCode());
	        deviceInfoModel.setDeviceTypeName(deviceTypeEnum.getDesc());
	        deviceInfoModel.setCreateId(userId.longValue());
	        deviceInfoModel.setCreateDatetime(now);
	        deviceInfoModel.setDeviceStatus(ShareDeviceStatusEnum.inactivation.getCode());
	        deviceInfoModel.setUpdateDatetime(now);
	        deviceInfoModel.setPlatformRato(new BigDecimal("100"));
	        deviceInfoModel.setYfjRebackType(
	        		ShareDeviceYfjRebackTypeEnum.RebackToBalance.getCode());
	        deviceInfoModel.setYfjRebackTypeName(
	        		ShareDeviceYfjRebackTypeEnum.RebackToBalance.getDesc());	
	    	try {
	    		deviceInfoModel.setActivationMode(new Integer(activationMode));
			} catch (Exception e) {
				// TODO: handle exception
				deviceInfoModel.setActivationMode(ShareDeviceActivationModeEnum.activation.getCode());
			}
			
	    	ResultCommandBaseObject<Object> result=new ResultCommandBaseObject<>();
	    	Boolean validate= deviceInfoHelperService.validateDeviceInfoForSave(deviceInfoModel, false, result);
	    	if(!validate){
	    		throw new Exception(
	    				result.getMessage());
	    	}
	    	if(shareCharger.getGenerateType()==2){
	        	listForDevice.add(deviceInfoModel);
	        }
        }
    	//处理充电器对应....
    	shareChargerModel=new ShareChargerModel();
    	shareChargerModel.setChargerTypeId(shareCharger.getChargerTypeId());
    	shareChargerModel.setChargerTypeName(chargerTypeEnum.getDesc());
    	shareChargerModel.setCreateDateTime(now);
    	shareChargerModel.setCreateId(userId.longValue());
    	shareChargerModel.setPwdIndex(0L);
    	shareChargerModel.setUpdateDateTime(now);
    	shareChargerModel.setUseCountTimesYesterday(0L);
    	shareChargerModel.setRemark(shareCharger.getRemark());
    	
		for(int i=0;i<shareCharger.getChargerQty();i++){
			//1.共充电器
			shareChargerTmp=new ShareChargerModel();
			BeanUtils.copyProperties(shareChargerModel,shareChargerTmp);   
			shareChargerTmp.setId(startChargerId+i);
			//2.设备...
			if(shareCharger.getGenerateType()==1){
				deviceInfoTmp=new ShareDeviceInfoModel();
				BeanUtils.copyProperties(deviceInfoModel,deviceInfoTmp); 
				deviceInfoTmp.setId(startDeviceId+i);
				listForDevice.add(deviceInfoTmp);
				//
				shareChargerTmp.setDeviceId(deviceInfoTmp.getId());
			}else{
				if(deviceInfoModel!=null){
					shareChargerTmp.setDeviceId(deviceInfoModel.getId());					
				}
			}
			listForCharger.add(shareChargerTmp);
		}
		//批量保存
		batchAddChargerInfoInsert(listForDevice,listForCharger);
		return startChargerId;
	}
	/**
	 * 批量保存数据...
	 * @param listForDevice
	 * @param listForCharger
     */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 10, rollbackFor = Exception.class)
	public void batchAddChargerInfoInsert(List<ShareDeviceInfoModel> listForDevice,List<ShareChargerModel> listForCharger) {
		//3. 批量插入设备表。
		if(listForDevice!=null&&listForDevice.size()>0){
			shareDeviceInfoModelBySelfMapper.insertBatch(listForDevice);
		}
		//4. 批量插入充电器表
		shareChargerModelBySelfMapper.insertBatch(listForCharger);
	}
}
