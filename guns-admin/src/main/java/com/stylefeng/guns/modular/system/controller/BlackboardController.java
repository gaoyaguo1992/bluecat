package com.stylefeng.guns.modular.system.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stylefeng.guns.modular.BaseController;
import com.stylefeng.guns.modular.system.service.INoticeService;
import com.stylefeng.guns.sharecore.common.persistence.model.ResultCommandBaseObject;
import com.stylefeng.guns.sharecore.modular.system.dao.ShareChargerModelMapper;
import com.stylefeng.guns.sharecore.modular.system.model.ChargerPwdsRefactors;
import com.stylefeng.guns.sharecore.modular.system.model.ShareChargerModel;
import com.stylefeng.guns.sharecore.modular.system.service.ShareChargerService;
import com.stylefeng.guns.sharecore.modular.system.utils.GeneratePwdCalacutorWithFactors;

/**
 * 总览信息
 *
 * @author fengshuonan
 * @Date 2017年3月4日23:05:54
 */
@Controller
@RequestMapping("/blackboard")
public class BlackboardController extends BaseController {
	// 操作日志..
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private INoticeService noticeService;
    /**
     * 引入操作充电器的服务。。
     */
    @Autowired
    private ShareChargerService shareChargerService;
    /**
     * 充电器处理..
     */
    @Autowired
    private ShareChargerModelMapper shareChargerModelMapper;
    /**
     * 跳转到黑板
     */
    @RequestMapping("")
    public String blackboard(Model model) {
        List<Map<String, Object>> notices = noticeService.list(null);
        model.addAttribute("noticeList", notices);
        return "/blackboard.html";
    }
    /**
     * 得到充电器密码因子..
     * @param request
     * @param response
     * @return
     */
	@RequestMapping("/getPasswordForRecharge")
	@ResponseBody
	public ResultCommandBaseObject<Object> getChargerRefactorIdx(HttpServletRequest request, HttpServletResponse response){
		//得到要生成充电器的充电器id
		String chargerId=request.getParameter("chargerId");
		String hour=request.getParameter("hour");
		//生成充电器的密码的验证码
		logger.info(String.format("得到充电器密码因子。。。chargerId:%s",chargerId));
		
		ResultCommandBaseObject<Object>  result=new ResultCommandBaseObject<>();
		
		//1. 判断充电器是否正确
		String msg="";
		if(chargerId==null||chargerId.isEmpty()||chargerId.length()<=8){
			msg=String.format("充电器(%s)在系统中不存在，请输入正确的充电器编号",chargerId);
			result.setResult(ResultCommandBaseObject.FAILED);
			result.setResult(msg);			
			logger.info(String.format("得到充电器密码因子。。。chargerId:%s--%s",chargerId,msg));
			return result;
		}
		Long chargerIDLng=0L;
		try {
			chargerIDLng=Long.parseLong(chargerId);
		} catch (Exception e) {
			result.setResult(ResultCommandBaseObject.FAILED);
			msg=String.format("充电器(%s)在系统中不存在，请输入正确的充电器编号",chargerId);
			logger.error(String.format("得到充电器密码因子。。。chargerId:%s--%s",chargerId,msg),e);
			result.setResult(msg);
			return result;
		}
		Integer iHour=0;
		try {
			iHour=Integer.parseInt(hour);
		} catch (Exception e) {}
		
		
		try {
			//判断密码原子是否已经存在.
			ShareChargerModel chargerModel=shareChargerModelMapper.selectByPrimaryKey(chargerIDLng);
			if(chargerModel==null){
				msg=String.format("充电器(%s)在系统中不存在，请输入正确的充电器编号",chargerId);
				result.setResult(ResultCommandBaseObject.FAILED);
				result.setResult(msg);			
				logger.info(String.format("得到充电器密码因子。。。chargerId:%s--%s",chargerId,msg));
				return result;
			}
			//表示已经烧过..
			if(chargerModel.getRefactorIdx()!=null&&chargerModel.getRefactorIdx()!=""&&chargerModel.getRefactorIdx()!="null"){
				msg=String.format("充电器(%s)密码已烧录，无法再次烧录，请联系管理员",chargerId);
				result.setResult(ResultCommandBaseObject.FAILED);
				result.setResult(msg);			
				logger.info(msg);
				return result;
			}
			//2. 充电器密码。。			
			ChargerPwdsRefactors chargerPwdsRefactors =shareChargerService.getChargerRefactorIdx(chargerIDLng);
			String sPwd=chargerPwdsRefactors.getChargerPwds();
			logger.info(String.format("====1=得到充电器密码因子。。chargerIDLng:%d,Pwd:%s, iHour:%d",chargerIDLng,sPwd,iHour));
			if(iHour>0){
				String[] ary=sPwd.split(";");
				StringBuffer strBuffer=new StringBuffer();
				for (int i = 0; i < ary.length; i++) {
					sPwd=ary[i];
					if(sPwd!=""){
						sPwd=GeneratePwdCalacutorWithFactors.getPwdForTimesByHoursAndPwds(
													iHour, sPwd,chargerModel.getChargerTypeId());
						strBuffer.append(sPwd);
						if(i<ary.length-1){
							strBuffer.append(";");
						}
					}
				}
				chargerPwdsRefactors.setChargerPwds(strBuffer.toString());
            }
			result.setResponseInfo(chargerPwdsRefactors);
			result.setResult(ResultCommandBaseObject.SUCCESS);
			logger.info(String.format("===2=得到充电器密码因子。。chargerIDLng:%d,Pwd:%s, iHour:%d",chargerIDLng,sPwd,iHour));
			return result;
		} catch (Exception e) {
			result.setResult(ResultCommandBaseObject.FAILED);
			result.setResult(e.getMessage());
			logger.error(String.format("得到充电器密码因子。。。chargerId:%s",chargerId),e);
			return result;
		}
	}
	/**
	 * 更新密码因子
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/updatePasswordForRecharge")
	@ResponseBody
	public ResultCommandBaseObject<Object> updatePasswordForRecharge(HttpServletRequest request, HttpServletResponse response){
		ResultCommandBaseObject<Object> result=new ResultCommandBaseObject<>();
		//chargerId=%s&factorsIdx=%s&chargerPwds=%s
		String chargerId=request.getParameter("chargerId");
		String factorsIdx=request.getParameter("factorsIdx");
		String chargerPwds=request.getParameter("chargerPwds");
		
		logger.info(String.format("更新密码因子。。。chargerId:%s,factorsIdx:%s,chargerPwds:%s"
				,chargerId,factorsIdx,chargerPwds));
		
		//计算密码...
		try {
			String chargerIdSuffix = chargerId.substring(chargerId.length() - 8);
			Long lngchargerId=Long.parseLong(chargerId);
			ShareChargerModel chargerModel= shareChargerModelMapper.selectByPrimaryKey(lngchargerId);
			if(chargerModel==null){
				logger.info(String.format("更新密码因子。。更新密码因子失败。chargerId:%s 失败。。未找到充电器",chargerId));				
				result.setResult(ResultCommandBaseObject.FAILED);
				result.setMessage("更新密码因子失败!");
				return result;
			}
			String pwds = GeneratePwdCalacutorWithFactors.calculate(chargerIdSuffix, factorsIdx,chargerModel.getChargerTypeId());
			if(!pwds.equalsIgnoreCase(chargerPwds)){
				logger.info(String.format("更新密码因子。。更新密码因子失败。pwds:%s,chargerPwds:%s 失败。。未找到充电器",pwds,chargerPwds));
				result.setResult(ResultCommandBaseObject.FAILED);
				result.setMessage("更新密码因子失败!");
				return result;
			}
			
			logger.info("更新密码因子。。更新密码因子成功",pwds,chargerPwds);
			ShareChargerModel  chrgModel=new ShareChargerModel();
			chrgModel.setId(lngchargerId);
			chrgModel.setRefactorIdx(factorsIdx);
			chrgModel.setUpdateDateTime(new Date());
			shareChargerModelMapper.updateByPrimaryKeySelective(chrgModel);
			result.setResult(ResultCommandBaseObject.SUCCESS);
			result.setMessage("更新密码因子成功!"); 
			return result;
		} catch (Exception e) {
			logger.error(String.format("更新密码因子。。更新密码因子失败。chargerId:%s 失败。。未找到充电器",chargerId),e);
			// TODO Auto-generated catch block
			result.setResult(ResultCommandBaseObject.FAILED);
			result.setMessage("更新密码因子失败!");
		}
		return result;
	}
	
}
