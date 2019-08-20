package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.core.base.tips.Tip;
import com.stylefeng.guns.core.cache.CacheKit;
import com.stylefeng.guns.core.common.annotion.BussinessLog;
import com.stylefeng.guns.core.common.annotion.Permission;
import com.stylefeng.guns.core.common.constant.Const;
import com.stylefeng.guns.core.common.constant.cache.Cache;
import com.stylefeng.guns.core.common.constant.dictmap.RoleDict;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.common.exception.BizExceptionEnum;
import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.util.Convert;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.BaseController;
import com.stylefeng.guns.modular.system.model.Role;
import com.stylefeng.guns.modular.system.model.User;
import com.stylefeng.guns.modular.system.service.IRoleService;
import com.stylefeng.guns.modular.system.service.IUserService;
import com.stylefeng.guns.modular.system.warpper.RoleWarpper;
import com.stylefeng.guns.sharecore.common.persistence.model.ResultCommandBaseObject;
import com.stylefeng.guns.sharecore.modular.system.dao.MerchantInfoModelMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.RoleMerchantRefInfoModelBySelfMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.RoleMerchantRefInfoModelMapper;
import com.stylefeng.guns.sharecore.modular.system.dao.ShareDeviceInfoModelBySelfMapper;
import com.stylefeng.guns.sharecore.modular.system.model.CustMerchantRefInfoModelExample;
import com.stylefeng.guns.sharecore.modular.system.model.MerchantInfoModel;
import com.stylefeng.guns.sharecore.modular.system.model.MerchantInfoModelExample.Criteria;
import com.stylefeng.guns.sharecore.modular.system.model.MerchantTypeEnum;
import com.stylefeng.guns.sharecore.modular.system.model.RoleMerchantRefInfoModel;
import com.stylefeng.guns.sharecore.modular.system.model.RoleMerchantRefInfoModelExample;
import com.stylefeng.guns.sharecore.modular.system.model.RoleMerchantRefInfoModelKey;
import com.stylefeng.guns.sharecore.modular.system.model.ShareDeviceInfoBySelfModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色控制器
 *
 * @author fengshuonan
 * @Date 2017年2月12日21:59:14
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

    private static String PREFIX = "/system/role";
    	/**
	 * 处理日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
    

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;
 
    @Autowired
	private RoleMerchantRefInfoModelMapper roleMerchantRefInfoModelMapper;
    /**
     * 角色商户
     */
    @Autowired
    private RoleMerchantRefInfoModelBySelfMapper roleMerchantRefInfoModelBySelfMapper;
	/**
	*商户。。。
	*/
	@Autowired
	private MerchantInfoModelMapper merchantInfoModelMapper;
	/**
	 * 设备对象表...
	 */
	@Autowired
	private ShareDeviceInfoModelBySelfMapper shareDeviceInfoModelBySelfMapper;
    /**
     * 跳转到角色列表页面
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/role.html";
    }

    /**
     * 跳转到添加角色
     */
    @RequestMapping(value = "/role_add")
    public String roleAdd() {
        return PREFIX + "/role_add.html";
    }    

    /**
     * 跳转到修改角色
     */
    @Permission
    @RequestMapping(value = "/role_edit/{roleId}")
    public String roleEdit(@PathVariable Integer roleId, Model model) {
        if (ToolUtil.isEmpty(roleId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        Role role = this.roleService.selectById(roleId);
        model.addAttribute(role);
        model.addAttribute("pName", ConstantFactory.me().getSingleRoleName(role.getPid()));
        model.addAttribute("deptName", ConstantFactory.me().getDeptName(role.getDeptid()));
        LogObjectHolder.me().set(role);
        return PREFIX + "/role_edit.html";
    }

    /**
     * 跳转到角色分配
     */
    @Permission
    @RequestMapping(value = "/role_assign/{roleId}")
    public String roleAssign(@PathVariable("roleId") Integer roleId, Model model) {
        if (ToolUtil.isEmpty(roleId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        model.addAttribute("roleId", roleId);
        model.addAttribute("roleName", ConstantFactory.me().getSingleRoleName(roleId));
        return PREFIX + "/role_assign.html";
    }

    /**
     * 跳转到添加角色
     */
    //@Permission
    @RequestMapping(value = "/role_assign_data/{roleId}")
    public String roleAssignData(@PathVariable("roleId") Integer roleId, Model model) {
        if (ToolUtil.isEmpty(roleId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        model.addAttribute("roleId", roleId);
        model.addAttribute("roleName", ConstantFactory.me().getSingleRoleName(roleId));
        return PREFIX + "/role_assign_data.html";
    }
    /**
     * 获取角色列表
     */
    @Permission
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String roleName) {
        List<Map<String, Object>> roles = this.roleService.selectRoles(super.getPara("roleName"));
        return super.warpObject(new RoleWarpper(roles));
    }

    /**
     * 角色新增
     */
    @RequestMapping(value = "/add")
    @BussinessLog(value = "添加角色", key = "name", dict = RoleDict.class)
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Tip add(@Valid Role role, BindingResult result) {
        if (result.hasErrors()) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        role.setId(null);
        this.roleService.insert(role);
        return SUCCESS_TIP;
    }

    /**
     * 角色修改
     */
    @RequestMapping(value = "/edit")
    @BussinessLog(value = "修改角色", key = "name", dict = RoleDict.class)
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Tip edit(@Valid Role role, BindingResult result) {
        if (result.hasErrors()) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        this.roleService.updateById(role);

        //删除缓存
        CacheKit.removeAll(Cache.CONSTANT);
        return SUCCESS_TIP;
    }

    /**
     * 删除角色
     */
    @RequestMapping(value = "/remove")
    @BussinessLog(value = "删除角色", key = "roleId", dict = RoleDict.class)
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Tip remove(@RequestParam Integer roleId) {
        if (ToolUtil.isEmpty(roleId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }

        //不能删除超级管理员角色
        if (roleId.equals(Const.ADMIN_ROLE_ID)) {
            throw new GunsException(BizExceptionEnum.CANT_DELETE_ADMIN);
        }

        //缓存被删除的角色名称
        LogObjectHolder.me().set(ConstantFactory.me().getSingleRoleName(roleId));

        this.roleService.delRoleById(roleId);

        //删除缓存
        CacheKit.removeAll(Cache.CONSTANT);
        return SUCCESS_TIP;
    }

    /**
     * 查看角色
     */
    @RequestMapping(value = "/view/{roleId}")
    @ResponseBody
    public Tip view(@PathVariable Integer roleId) {
        if (ToolUtil.isEmpty(roleId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        this.roleService.selectById(roleId);
        return SUCCESS_TIP;
    }

    /**
     * 配置权限
     */
    @RequestMapping("/setAuthority")
    @BussinessLog(value = "配置权限", key = "roleId,ids", dict = RoleDict.class)
    @ResponseBody
    public Tip setAuthority(@RequestParam("roleId") Integer roleId, @RequestParam("ids") String ids) {
        if (ToolUtil.isOneEmpty(roleId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        this.roleService.setAuthority(roleId, ids);
        return SUCCESS_TIP;
    }

    /**
     * 配置数据权限
     */
    @RequestMapping("/setDataAuthority")
    @BussinessLog(value = "配置数据权限", key = "roleId,ids", dict = RoleDict.class)
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Tip setDataAuthority(@RequestParam("roleId") Integer roleId, @RequestParam("ids") String ids) {
        if (ToolUtil.isOneEmpty(roleId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        this.roleService.setAuthority(roleId, ids);
        return SUCCESS_TIP;
    }
    /**
     * 获取角色列表
     */
    @RequestMapping(value = "/roleTreeList")
    @ResponseBody
    public List<ZTreeNode> roleTreeList() {
        List<ZTreeNode> roleTreeList = this.roleService.roleTreeList();
        roleTreeList.add(ZTreeNode.createParent());
        return roleTreeList;
    }

    /**
     * 获取角色列表
     */
    @RequestMapping(value = "/roleTreeListByUserId/{userId}")
    @ResponseBody
    public List<ZTreeNode> roleTreeListByUserId(@PathVariable Integer userId) {
        User theUser = this.userService.selectById(userId);
        String roleid = theUser.getRoleid();
        if (ToolUtil.isEmpty(roleid)) {
            List<ZTreeNode> roleTreeList = this.roleService.roleTreeList();
            return roleTreeList;
        } else {
            String[] strArray = Convert.toStrArray(",", roleid);
            List<ZTreeNode> roleTreeListByUserId = this.roleService.roleTreeListByRoleId(strArray);
            return roleTreeListByUserId;
        }
    }
    /**
     * 获取用户信息列表
     */
    @RequestMapping(value = "/getCustRelMerlist")
    @ResponseBody
    public Object getCustRelMerlist(HttpServletRequest request, HttpServletResponse response) {
    	Integer userId = ShiroKit.getUser().getId();
		String roleId = request.getParameter("roleId");
		Long iRoleId=0L;
		try {
			iRoleId=Long.parseLong(roleId);
		} catch (Exception e) {
			iRoleId=0L;
		}
		RoleMerchantRefInfoModelExample example=new RoleMerchantRefInfoModelExample();
		com.stylefeng.guns.sharecore.modular.system.model.RoleMerchantRefInfoModelExample.Criteria
		criteria= example.createCriteria();
		criteria.andRoleIdEqualTo(iRoleId);
		//查询...
        return roleMerchantRefInfoModelMapper.selectByExample(example);
    }
      /**
     * 绑定商户    
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/addMerchantSubmit")
    @ResponseBody
    public Object addMerchantSubmit(HttpServletRequest request, HttpServletResponse response) {
    	//1.接收参数
    	Integer userId = ShiroKit.getUser().getId();
		String roleId = request.getParameter("roleId");
		String merchantId = request.getParameter("merchantId");
		ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
		Long iRoleId=0L;
		//2. 处理参数
		logger.info(String.format("addMerchantSubmit--userId：%d,roleId:%s,merchantId:%s",userId,roleId,merchantId));
		try {
			iRoleId=Long.parseLong(roleId);
		} catch (Exception e) {
			// TODO: handle exception
			iRoleId=0L;
		}
		Role roleModel=roleService.selectById(iRoleId);
		if(roleModel==null){
			result.setResult(ResultCommandBaseObject.FAILED);
			result.setMessage("输入的角色类型不正确，请输入正确的角色类型！");
			return result;
		}
		
		Long iMerchantId=0L;
		try {
			iMerchantId=Long.parseLong(merchantId);
		} catch (Exception e) {
			// TODO: handle exception
			iMerchantId=0L;
		}
		//处理权限
		if(iMerchantId.equals(new Long("0"))){
			result.setResult(ResultCommandBaseObject.FAILED);
			result.setMessage("未输入商户编号，请输入正确的商户编号！");
			return result;
		}		
		MerchantInfoModel merInfoModel=merchantInfoModelMapper.selectByPrimaryKey(iMerchantId);
		if(merInfoModel==null){			
			result.setResult(ResultCommandBaseObject.FAILED);
			result.setMessage(String.format("商户(%s)在系统中不存在！",merchantId));
			return result;
		}
		//2.2 得到所有角色
		if(userId.intValue()!=1){
			Boolean bol=false; 
			if(merInfoModel.getUserId()==null
					||merInfoModel.getUserId().equals(userId)){
				bol=true;
			}
			if(!bol){
				List<Integer> rlList=ShiroKit.getUser().getRoleList();
				if(rlList.size()<0){
					result.setResult(ResultCommandBaseObject.FAILED);
					result.setMessage(String.format("此商户非您下级商户，你无权限设置！",merchantId));
					return result;
				}
				//2.3 得关联商户
				List<RoleMerchantRefInfoModel> listForMerchant=
						roleMerchantRefInfoModelBySelfMapper.getRoleMerchantRefInfoList(rlList);
				
				//2.4 判断父或者本身是否有权限..
				Long parentMerchantId=merInfoModel.getParentMerchantId();		
				for (RoleMerchantRefInfoModel roleMerchantRefInfoModel : listForMerchant) {
					//A.判断父..
					if(parentMerchantId!=null&&roleMerchantRefInfoModel.getMerchantId().equals(parentMerchantId)){
						bol=true;
						break;
					}
					//B.判断本身
					if(roleMerchantRefInfoModel.getMerchantId().equals(merInfoModel.getId())){
						bol=true;
						break;
					}
				}
				//2.5 是否有上级的设备绑定了对应的权限....
				String filter="", merchantFilter="";
				//我的指商户..
				if(merInfoModel.getMerchantType().intValue()==MerchantTypeEnum.DAI_LI_SHANG1.getCode().intValue()){
					bol=false;
				}else if(merInfoModel.getMerchantType().intValue()==MerchantTypeEnum.DAI_LI_SHANG2.getCode().intValue()){
					merchantFilter=String.format("agents2_id=%d",merInfoModel.getId());
					//一级
					for (RoleMerchantRefInfoModel tmp : listForMerchant) {
						if(tmp.getMerchantType().intValue()==MerchantTypeEnum.DAI_LI_SHANG1.getCode().intValue()){
							filter=(filter.isEmpty())?String.format(" agents1_id=%d", tmp.getMerchantId()):
													 String.format(" %s or agents1_id=%d", tmp.getMerchantId());
						}
					}
				}else if(merInfoModel.getMerchantType().intValue()==MerchantTypeEnum.DAI_LI_SHANG3.getCode().intValue()){
					//二级
					merchantFilter=String.format("agents3_id=%d",merInfoModel.getId());
					for (RoleMerchantRefInfoModel tmp : listForMerchant) {
						if(tmp.getMerchantType().intValue()==MerchantTypeEnum.DAI_LI_SHANG1.getCode().intValue()){
							filter=(filter.isEmpty())?String.format(" agents1_id=%d", tmp.getMerchantId()):
													  String.format(" %s or agents1_id=%d", tmp.getMerchantId());
						}
						if(tmp.getMerchantType().intValue()==MerchantTypeEnum.DAI_LI_SHANG2.getCode().intValue()){
							filter=(filter.isEmpty())?String.format(" agents2_id=%d", tmp.getMerchantId()):
													  String.format(" %s or agents2_id=%d", tmp.getMerchantId());
						}
					}
					
				}else if(merInfoModel.getMerchantType().intValue()==MerchantTypeEnum.PUHUO_SHANG.getCode().intValue()){
					//铺货
					merchantFilter=String.format("shopkeeper_id=%d",merInfoModel.getId());
					for (RoleMerchantRefInfoModel tmp : listForMerchant) {
						if(tmp.getMerchantType().intValue()==MerchantTypeEnum.DAI_LI_SHANG1.getCode().intValue()){
							filter=(filter.isEmpty())?String.format(" agents1_id=%d", tmp.getMerchantId()):
													  String.format(" %s or agents1_id=%d", tmp.getMerchantId());
						}
						if(tmp.getMerchantType().intValue()==MerchantTypeEnum.DAI_LI_SHANG2.getCode().intValue()){
							filter=(filter.isEmpty())?String.format(" agents2_id=%d", tmp.getMerchantId()):
													  String.format(" %s or agents2_id=%d", tmp.getMerchantId());
						}
						if(tmp.getMerchantType().intValue()==MerchantTypeEnum.DAI_LI_SHANG2.getCode().intValue()){
							filter=(filter.isEmpty())?String.format(" agents3_id=%d", tmp.getMerchantId())
													 :String.format(" %s or agents3_id=%d", tmp.getMerchantId());
						}
					}
				}else if(merInfoModel.getMerchantType().intValue()==MerchantTypeEnum.JIA_MENG_SHANG.getCode().intValue()){
					//加盟
					merchantFilter=String.format("alliance_business_id=%d",merInfoModel.getId());
					for (RoleMerchantRefInfoModel tmp : listForMerchant) {
						if(tmp.getMerchantType().intValue()==MerchantTypeEnum.DAI_LI_SHANG1.getCode().intValue()){
							filter=(filter.isEmpty())?String.format(" agents1_id=%d", tmp.getMerchantId()):
													  String.format(" %s or agents1_id=%d", tmp.getMerchantId());
						}
						if(tmp.getMerchantType().intValue()==MerchantTypeEnum.DAI_LI_SHANG2.getCode().intValue()){
							filter=(filter.isEmpty())?String.format(" agents2_id=%d", tmp.getMerchantId()):
													  String.format(" %s or agents2_id=%d", tmp.getMerchantId());
						}
						if(tmp.getMerchantType().intValue()==MerchantTypeEnum.DAI_LI_SHANG2.getCode().intValue()){
							filter=(filter.isEmpty())?String.format(" agents3_id=%d", tmp.getMerchantId())
													 :String.format(" %s or agents3_id=%d", tmp.getMerchantId());
						}
						if(tmp.getMerchantType().intValue()==MerchantTypeEnum.DAI_LI_SHANG2.getCode().intValue()){
							filter=(filter.isEmpty())?String.format(" shopkeeper_id=%d", tmp.getMerchantId())
									 :String.format(" %s or shopkeeper_id=%d", tmp.getMerchantId());
						}
					}
				}else if(merInfoModel.getMerchantType().intValue()==MerchantTypeEnum.TERMINAL.getCode().intValue()){
					//加盟
					merchantFilter=String.format("online_merchant_id=%d",merInfoModel.getId());
					for (RoleMerchantRefInfoModel tmp : listForMerchant) {
						if(tmp.getMerchantType().intValue()==MerchantTypeEnum.DAI_LI_SHANG1.getCode().intValue()){
							filter=(filter.isEmpty())?String.format(" agents1_id=%d", tmp.getMerchantId()):
													  String.format(" %s or agents1_id=%d", tmp.getMerchantId());
						}
						if(tmp.getMerchantType().intValue()==MerchantTypeEnum.DAI_LI_SHANG2.getCode().intValue()){
							filter=(filter.isEmpty())?String.format(" agents2_id=%d", tmp.getMerchantId()):
													  String.format(" %s or agents2_id=%d", tmp.getMerchantId());
						}
						if(tmp.getMerchantType().intValue()==MerchantTypeEnum.DAI_LI_SHANG2.getCode().intValue()){
							filter=(filter.isEmpty())?String.format(" agents3_id=%d", tmp.getMerchantId())
													 :String.format(" %s or agents3_id=%d", tmp.getMerchantId());
						}
						if(tmp.getMerchantType().intValue()==MerchantTypeEnum.DAI_LI_SHANG2.getCode().intValue()){
							filter=(filter.isEmpty())?String.format(" shopkeeper_id=%d", tmp.getMerchantId())
									 :String.format(" %s or shopkeeper_id=%d", tmp.getMerchantId());
						}
						if(tmp.getMerchantType().intValue()==MerchantTypeEnum.DAI_LI_SHANG2.getCode().intValue()){
							filter=(filter.isEmpty())?String.format(" alliance_business_id=%d", tmp.getMerchantId())
									 :String.format(" %s or alliance_business_id=%d", tmp.getMerchantId());
						}
					}
				}
				if(filter.isEmpty()||merchantFilter.isEmpty()){
					bol=false;
				}else{
					filter=String.format(" and %s and(%s)", merchantFilter,filter);
					HashMap<String,Object> pageFilter=new HashMap<>();
					pageFilter.put("filter", filter);
					pageFilter.put("offset", 1L);
					pageFilter.put("limit", 1L);
					List<ShareDeviceInfoBySelfModel> devicesList=
							shareDeviceInfoModelBySelfMapper.selectByConditionForPage(pageFilter);
					if(devicesList.size()>0){
						bol=true;
					}
				}
			}
			if(!bol){
				result.setResult(ResultCommandBaseObject.FAILED);
				result.setMessage(String.format("此商户非您下级商户或你设备绑定的下级权限，无权限设置！",merchantId));
				return result;
			}
		}
		//3. 保存...
		RoleMerchantRefInfoModel roleMerchantRefInfoModel=new RoleMerchantRefInfoModel();
		roleMerchantRefInfoModel.setCreateId(userId.longValue());
		roleMerchantRefInfoModel.setCreateTime(new Date());
		roleMerchantRefInfoModel.setIsDefault((byte) 0);
		roleMerchantRefInfoModel.setMerchantId(merInfoModel.getId());
		roleMerchantRefInfoModel.setMerchantName(merInfoModel.getName());
		roleMerchantRefInfoModel.setMerchantType(merInfoModel.getMerchantType());
		roleMerchantRefInfoModel.setMerchantTypeCn(merInfoModel.getMerchantTypeCn());
		roleMerchantRefInfoModel.setRoleId(roleModel.getId().longValue());
		RoleMerchantRefInfoModelKey key= new RoleMerchantRefInfoModelKey();
		key.setMerchantId(merInfoModel.getId());
		key.setRoleId(roleMerchantRefInfoModel.getRoleId());		  
		RoleMerchantRefInfoModel roleMerchantRefInfoModelBySearch=
							roleMerchantRefInfoModelMapper.selectByPrimaryKey(key);
		if(roleMerchantRefInfoModelBySearch==null){			
			roleMerchantRefInfoModelMapper.insert(roleMerchantRefInfoModel);
		}else{
			roleMerchantRefInfoModelMapper.updateByPrimaryKeySelective(roleMerchantRefInfoModel);
		}
		//4. 返回..
		result.setResult(ResultCommandBaseObject.SUCCESS);
		result.setMessage(String.format("商户(%d)绑定成功！",
						roleMerchantRefInfoModel.getMerchantId()));
		return result;
    }
     /**
     * 删除绑定商户    
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/delMerchantSubmit")
    @ResponseBody
    public Object delMerchantSubmit(HttpServletRequest request, HttpServletResponse response) {
    	//1.接收参数
    	Integer userId = ShiroKit.getUser().getId();
		String roleId = request.getParameter("roleId");
		String merchantId = request.getParameter("merchantId");
		ResultCommandBaseObject<Object> result = new ResultCommandBaseObject<>();
		Long iRoleId=0L;
		//2. 处理参数
		logger.info(String.format("addMerchantSubmit--userId：%d,roleId:%s,merchantId:%s",userId,roleId,merchantId));
		try {
			iRoleId=Long.parseLong(roleId);
		} catch (Exception e) {
			// TODO: handle exception
			iRoleId=0L;
		}
		Role roleModel=roleService.selectById(iRoleId);
		if(roleModel==null){
			result.setResult(ResultCommandBaseObject.FAILED);
			result.setMessage("输入的角色类型不正确，请输入正确的角色类型！");
			return result;
		}
		
		Long iMerchantId=0L;
		try {
			iMerchantId=Long.parseLong(merchantId);
		} catch (Exception e) {
			// TODO: handle exception
			iMerchantId=0L;
		}
		//处理权限
		if(iMerchantId.equals(new Long("0"))){
			result.setResult(ResultCommandBaseObject.FAILED);
			result.setMessage("未输入商户编号，请输入正确的商户编号！");
			return result;
		}		
		MerchantInfoModel merInfoModel=merchantInfoModelMapper.selectByPrimaryKey(iMerchantId);
		if(merInfoModel==null){			
			result.setResult(ResultCommandBaseObject.FAILED);
			result.setMessage(String.format("商户(%s)在系统中不存在！",merchantId));
			return result;
		}
		//3. 保存...
		RoleMerchantRefInfoModelKey key= new RoleMerchantRefInfoModelKey();
		key.setMerchantId(merInfoModel.getId());
		key.setRoleId(roleModel.getId().longValue());
		roleMerchantRefInfoModelMapper.deleteByPrimaryKey(key);
		//4. 返回..
		result.setResult(ResultCommandBaseObject.SUCCESS);
		result.setMessage(String.format("商户(%d)删除成功！",merInfoModel.getId()));
		return result;
    }
}
