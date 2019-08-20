package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.config.properties.GunsProperties;
import com.stylefeng.guns.core.base.tips.Tip;
import com.stylefeng.guns.core.common.annotion.BussinessLog;
import com.stylefeng.guns.core.common.annotion.Permission;
import com.stylefeng.guns.core.common.constant.Const;
import com.stylefeng.guns.core.common.constant.dictmap.UserDict;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.common.constant.state.ManagerStatus;
import com.stylefeng.guns.core.common.exception.BizExceptionEnum;
import com.stylefeng.guns.core.datascope.DataScope;
import com.stylefeng.guns.core.db.Db;
import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.shiro.ShiroUser;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.BaseController;
import com.stylefeng.guns.modular.system.dao.UserMapper;
import com.stylefeng.guns.modular.system.factory.UserFactory;
import com.stylefeng.guns.modular.system.model.Role;
import com.stylefeng.guns.modular.system.model.User;
import com.stylefeng.guns.modular.system.service.IRoleService;
import com.stylefeng.guns.modular.system.service.IUserService;
import com.stylefeng.guns.modular.system.transfer.UserDto;
import com.stylefeng.guns.modular.system.warpper.UserWarpper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.NoPermissionException;
import javax.validation.Valid;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 系统管理员控制器
 *
 * @author fengshuonan
 * @Date 2017年1月11日 下午1:08:17
 */
@Controller
@RequestMapping("/mgr")
public class UserMgrController extends BaseController {

    private static String PREFIX = "/system/user/";

    @Autowired
    private GunsProperties gunsProperties;

    @Autowired
    private IRoleService roleService;
    
    @Autowired
    private IUserService userService;

    /**
     * 跳转到查看管理员列表的页面
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "user.html";
    }

    /**
     * 跳转到查看管理员列表的页面
     */
    @RequestMapping("/user_add")
    public String addView() {
        return PREFIX + "user_add.html";
    }

    /**
     * 跳转到角色分配页面
     */
    //@RequiresPermissions("/mgr/role_assign")  //利用shiro自带的权限检查
    @Permission
    @RequestMapping("/role_assign/{userId}")
    public String roleAssign(@PathVariable Integer userId, Model model) {
        if (ToolUtil.isEmpty(userId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        User user = Db.create(UserMapper.class).selectOneByCon("id", userId);
        model.addAttribute("userId", userId);
        model.addAttribute("userAccount", user.getAccount());
        return PREFIX + "user_roleassign.html";
    }

    /**
     * 跳转到编辑管理员页面
     */
    @Permission
    @RequestMapping("/user_edit/{userId}")
    public String userEdit(@PathVariable Integer userId, Model model) {
        if (ToolUtil.isEmpty(userId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        assertAuth(userId);
        User user = this.userService.selectById(userId);
        model.addAttribute(user);
        model.addAttribute("roleName", ConstantFactory.me().getRoleName(user.getRoleid()));
        model.addAttribute("deptName", ConstantFactory.me().getDeptName(user.getDeptid()));
        LogObjectHolder.me().set(user);
        return PREFIX + "user_edit.html";
    }

    /**
     * 跳转到查看用户详情页面
     */
    @RequestMapping("/user_info")
    public String userInfo(Model model) {
        Integer userId = ShiroKit.getUser().getId();
        if (ToolUtil.isEmpty(userId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        User user = this.userService.selectById(userId);
        model.addAttribute(user);
        model.addAttribute("roleName", ConstantFactory.me().getRoleName(user.getRoleid()));
        model.addAttribute("deptName", ConstantFactory.me().getDeptName(user.getDeptid()));
        LogObjectHolder.me().set(user);
        return PREFIX + "user_view.html";
    }

    /**
     * 跳转到修改密码界面
     */
    @RequestMapping("/user_chpwd")
    public String chPwd() {
        return PREFIX + "user_chpwd.html";
    }
    
    /**
     * 跳转到编辑管理员页面
     */
    @RequestMapping("/use_assign_data/{userId}")
    public String useAssignData(@PathVariable Integer userId, Model model) {
        if (ToolUtil.isEmpty(userId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        //0. 管理员无权设置...
        if(userId.equals(new Integer("1"))){
        	throw new GunsException(BizExceptionEnum.ADMIN_MUST_ROLE_SET);
        }
        //1.得到当前用户id
        Integer currUserId = ShiroKit.getUser().getId();       
        //2.要修改的用户id        
        User user = this.userService.selectById(userId);
        if(user==null){
        	throw new GunsException(BizExceptionEnum.NO_THIS_USER);
        }
        if(currUserId.intValue()!=1){
	        if(!user.getCreateid().equals(currUserId)){
	            throw new GunsException(BizExceptionEnum.NO_RIHGT_MODIFY_NO_PARENT);
	        }
        }
        //3.得到当前用户角色.
        Role role=(userId.equals(new Integer("1")))?this.roleService.selectById(1):        		
        				this.roleService.selectRoleByName(String.format("%s_%d", user.getAccount(),user.getId()));
        
        if(role==null){
        	//创建
        	role=new Role();
        	role.setDeptid(new Integer(0));
        	role.setId(null);
        	role.setName(String.format("%s_%d", user.getAccount(),user.getId()));
        	role.setNum(0);
        	role.setPid(0);
        	role.setTips("创建用户");
        	this.roleService.insert(role);
        	//建立关系
        	Role rl=this.roleService.selectRoleByName(role.getName());
        	if(rl!=null){
            	this.userService.setRoles(userId, String.format("%s", rl.getId()));
        	}
        }
        model.addAttribute(role);
        model.addAttribute(user);
        model.addAttribute("roleId", role.getId());
        model.addAttribute("userId", user.getId());
        model.addAttribute("roleName", ConstantFactory.me().getRoleName(user.getRoleid()));
        model.addAttribute("deptName", ConstantFactory.me().getDeptName(user.getDeptid()));
        LogObjectHolder.me().set(user);
        return PREFIX + "use_assign_data.html";
    }
    /**
     * 跳转到编辑管理员页面
     */
    @RequestMapping("/use_assign/{userId}")
    public String useAssign(@PathVariable Integer userId, Model model) {
        if (ToolUtil.isEmpty(userId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        
        //1.得到当前用户id
        Integer currUserId = ShiroKit.getUser().getId();       
        //2.要修改的用户id        
        User user = this.userService.selectById(userId);
        if(user==null){
        	throw new GunsException(BizExceptionEnum.NO_THIS_USER);
        }
        if(currUserId.intValue()!=1){
	        if(!user.getCreateid().equals(currUserId)){
	            throw new GunsException(BizExceptionEnum.NO_RIHGT_MODIFY_NO_PARENT);
	        }
        }
        if(userId.equals(new Integer("1"))){
        	throw new GunsException(BizExceptionEnum.ADMIN_MUST_ROLE_SET);
        }
        //3.得到当前用户角色.
        Role role=(userId.equals(new Integer("1")))?this.roleService.selectById(1):        		
        				this.roleService.selectRoleByName(String.format("%s_%d", user.getAccount(),user.getId()));
        if(role==null){
        	//创建
        	role=new Role();
        	role.setDeptid(new Integer(0));
        	role.setId(null);
        	role.setName(String.format("%s_%d", user.getAccount(),user.getId()));
        	role.setNum(0);
        	role.setPid(0);
        	role.setTips("创建用户");
        	this.roleService.insert(role);
        	//建立关系
        	Role rl=this.roleService.selectRoleByName(role.getName());
        	if(rl!=null){
            	this.userService.setRoles(userId, String.format("%s", rl.getId()));
        	}
        }
        model.addAttribute(role);
        model.addAttribute(user);
        model.addAttribute("roleId", role.getId());
        model.addAttribute("userId", user.getId());
        model.addAttribute("roleName", ConstantFactory.me().getRoleName(user.getRoleid()));
        model.addAttribute("deptName", ConstantFactory.me().getDeptName(user.getDeptid()));
        LogObjectHolder.me().set(user);
        return PREFIX + "use_assign.html";
    }

    /**
     * 修改当前用户的密码
     */
    @RequestMapping("/changePwd")
    @ResponseBody
    public Object changePwd(@RequestParam String oldPwd, @RequestParam String newPwd, @RequestParam String rePwd) {
        if (!newPwd.equals(rePwd)) {
            throw new GunsException(BizExceptionEnum.TWO_PWD_NOT_MATCH);
        }
        Integer userId = ShiroKit.getUser().getId();
        User user = userService.selectById(userId);
        String oldMd5 = ShiroKit.md5(oldPwd, user.getSalt());
        if (user.getPassword().equals(oldMd5)) {
            String newMd5 = ShiroKit.md5(newPwd, user.getSalt());
            user.setPassword(newMd5);
            user.updateById();
            return SUCCESS_TIP;
        } else {
            throw new GunsException(BizExceptionEnum.OLD_PWD_NOT_RIGHT);
        }
    }

    /**
     * 查询管理员列表
     */
    @RequestMapping("/list")
    @Permission
    @ResponseBody
    public Object list(@RequestParam(required = false) String name, @RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime, @RequestParam(required = false) Integer deptid) {
        Integer userId= ShiroKit.getUser().getId();
    	if (userId.equals(new Integer("1"))) {
            List<Map<String, Object>> users = userService.selectUsers(null, name, beginTime, endTime, deptid);
            return new UserWarpper(users).warp();
        } else {
            DataScope dataScope = new DataScope(ShiroKit.getDeptDataScope());
            dataScope.getDeptIds().add(new Integer(0));
            List<Map<String, Object>> users = userService.selectUsersByCreateid(
            							dataScope, name, beginTime, endTime, deptid, userId);
            return new UserWarpper(users).warp();
        }
    }

    /**
     * 添加管理员
     */
    @RequestMapping("/add")
    @BussinessLog(value = "添加管理员", key = "account", dict = UserDict.class)
    //@Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Tip add(@Valid UserDto user, BindingResult result) {
        if (result.hasErrors()) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }

        // 判断账号是否重复
        User theUser = userService.getByAccount(user.getAccount());
        if (theUser != null) {
            throw new GunsException(BizExceptionEnum.USER_ALREADY_REG);
        }
        Integer userId= ShiroKit.getUser().getId();

        //1 完善账号信息
        user.setSalt(ShiroKit.getRandomSalt(5));
        user.setPassword(ShiroKit.md5(user.getPassword(), user.getSalt()));
        user.setStatus(ManagerStatus.OK.getCode());
        user.setCreateid(userId);
        user.setCreatetime(new Date());
        this.userService.insert(UserFactory.createUser(user));
        //2、设置功能权限
        User theUserTmp= userService.getByAccount(user.getAccount());
        Role role=new Role();
        role.setId(null);
        role.setDeptid(0);
        role.setName(String.format("%s_%d", theUserTmp.getAccount(),theUserTmp.getId()));
        role.setNum(1);
        role.setPid(0);
        role.setTips("");
        role.setVersion(new Integer("1"));        
        this.roleService.insert(role);
        //3. 绑定角
        Role rl=this.roleService.selectRoleByName(role.getName());
        if(rl!=null){
           this.userService.setRoles(theUserTmp.getId(), String.format("%d", rl.getId()));
        }
        return SUCCESS_TIP;
    }

    /**
     * 修改管理员
     *
     */
    @RequestMapping("/edit")
    @BussinessLog(value = "修改管理员", key = "account", dict = UserDict.class)
    @ResponseBody
    public Tip edit(@Valid UserDto user, BindingResult result) {
        if (result.hasErrors()) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }

        User oldUser = userService.selectById(user.getId());

        if (ShiroKit.hasRole(Const.ADMIN_NAME)) {
            this.userService.updateById(UserFactory.editUser(user, oldUser));
            return SUCCESS_TIP;
        } else {
            assertAuth(user.getId());
            ShiroUser shiroUser = ShiroKit.getUser();
            if (shiroUser.getId().equals(user.getId())||shiroUser.getId().equals(user.getCreateid())) {
                this.userService.updateById(UserFactory.editUser(user, oldUser));
                return SUCCESS_TIP;
            } else {
                throw new GunsException(BizExceptionEnum.NO_PERMITION);
            }
        }
    }

    /**
     * 删除管理员（逻辑删除）
     */
    @RequestMapping("/delete")
    @BussinessLog(value = "删除管理员", key = "userId", dict = UserDict.class)
    @Permission
    @ResponseBody
    public Tip delete(@RequestParam Integer userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        //不能删除超级管理员
        if (userId.equals(Const.ADMIN_ID)) {
            throw new GunsException(BizExceptionEnum.CANT_DELETE_ADMIN);
        }
        assertAuth(userId);
        this.userService.setStatus(userId, ManagerStatus.DELETED.getCode());
        return SUCCESS_TIP;
    }

    /**
     * 查看管理员详情
     */
    @RequestMapping("/view/{userId}")
    @ResponseBody
    public User view(@PathVariable Integer userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        assertAuth(userId);
        return this.userService.selectById(userId);
    }

    /**
     * 重置管理员的密码
     */
    @RequestMapping("/reset")
    @BussinessLog(value = "重置管理员密码", key = "userId", dict = UserDict.class)
    //@Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Tip reset(@RequestParam Integer userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        assertAuth(userId);
        User user = this.userService.selectById(userId);
        user.setSalt(ShiroKit.getRandomSalt(5));
        user.setPassword(ShiroKit.md5(Const.DEFAULT_PWD, user.getSalt()));
        this.userService.updateById(user);
        return SUCCESS_TIP;
    }

    /**
     * 冻结用户
     */
    @RequestMapping("/freeze")
    @BussinessLog(value = "冻结用户", key = "userId", dict = UserDict.class)
    //@Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Tip freeze(@RequestParam Integer userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        if (ShiroKit.hasRole(Const.ADMIN_NAME)) {
            //不能冻结超级管理员
            if (userId.equals(Const.ADMIN_ID)) {
                throw new GunsException(BizExceptionEnum.CANT_FREEZE_ADMIN);
            }
            assertAuth(userId);
            this.userService.setStatus(userId, ManagerStatus.FREEZED.getCode());
        }else{
        	User user= this.userService.selectById(userId);
        	if(user.getCreateid()==null||user.getCreateid().equals(userId)){
                throw new GunsException(BizExceptionEnum.NO_PERMITION);
        	}
            this.userService.setStatus(userId, ManagerStatus.FREEZED.getCode());
        }
        return SUCCESS_TIP;
    }

    /**
     * 解除冻结用户
     */
    @RequestMapping("/unfreeze")
    @BussinessLog(value = "解除冻结用户", key = "userId", dict = UserDict.class)
    //@Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Tip unfreeze(@RequestParam Integer userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        if (ShiroKit.hasRole(Const.ADMIN_NAME)) {
	        assertAuth(userId);
	        this.userService.setStatus(userId, ManagerStatus.OK.getCode());
        }else{
        	User user= this.userService.selectById(userId);
        	if(user.getCreateid()==null||user.getCreateid().equals(userId)){
                throw new GunsException(BizExceptionEnum.NO_PERMITION);
        	}
	        this.userService.setStatus(userId, ManagerStatus.OK.getCode());
        }
        return SUCCESS_TIP;
    }

    /**
     * 分配角色
     */
    @RequestMapping("/setRole")
    @BussinessLog(value = "分配角色", key = "userId,roleIds", dict = UserDict.class)
    //@Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Tip setRole(@RequestParam("userId") Integer userId, @RequestParam("roleIds") String roleIds) {
        if (ToolUtil.isOneEmpty(userId, roleIds)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        if (ShiroKit.hasRole(Const.ADMIN_NAME)) {
	        //不能修改超级管理员
	        if (userId.equals(Const.ADMIN_ID)) {
	            throw new GunsException(BizExceptionEnum.CANT_CHANGE_ADMIN);
	        }
	        assertAuth(userId);
	        this.userService.setRoles(userId, roleIds);
        }else{
        	User user= this.userService.selectById(userId);
        	if(user.getCreateid()==null||user.getCreateid().equals(userId)){
                throw new GunsException(BizExceptionEnum.NO_PERMITION);
        	}
        	if (userId.equals(Const.ADMIN_ID)) {
 	            throw new GunsException(BizExceptionEnum.CANT_CHANGE_ADMIN);
 	        }
            this.userService.setRoles(userId, roleIds);
        }
        return SUCCESS_TIP;
    }

    /**
     * 上传图片
     */
    @RequestMapping(method = RequestMethod.POST, path = "/upload")
    @ResponseBody
    public String upload(@RequestPart("file") MultipartFile picture) {

        String pictureName = UUID.randomUUID().toString() + "." + ToolUtil.getFileSuffix(picture.getOriginalFilename());
        try {
            String fileSavePath = gunsProperties.getFileUploadPath();
            picture.transferTo(new File(fileSavePath + pictureName));
        } catch (Exception e) {
            throw new GunsException(BizExceptionEnum.UPLOAD_ERROR);
        }
        return pictureName;
    }

    /**
     * 判断当前登录的用户是否有操作这个用户的权限
     */
    private void assertAuth(Integer userId) {
        if (ShiroKit.isAdmin()) {
            return;
        }
        List<Integer> deptDataScope = ShiroKit.getDeptDataScope();
        User user = this.userService.selectById(userId);
        Integer deptid = user.getDeptid();
        if (deptDataScope.contains(deptid)) {
            return;
        } else {
            throw new GunsException(BizExceptionEnum.NO_PERMITION);
        }

    }
}
