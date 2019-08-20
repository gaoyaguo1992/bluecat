package com.stylefeng.guns.modular;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.base.tips.SuccessTip;
import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;
import com.stylefeng.guns.core.page.PageInfoBT;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.support.HttpKit;
import com.stylefeng.guns.core.util.FileUtil;
import com.stylefeng.guns.sharecore.modular.system.dao.RoleMerchantRefInfoModelBySelfMapper;
import com.stylefeng.guns.sharecore.modular.system.model.MerchantTypeEnum;
import com.stylefeng.guns.sharecore.modular.system.model.RoleMerchantRefInfoModel;

public class BaseController {

	protected static String SUCCESS = "SUCCESS";
	protected static String ERROR = "ERROR";

	protected static String REDIRECT = "redirect:";
	protected static String FORWARD = "forward:";

	protected static SuccessTip SUCCESS_TIP = new SuccessTip();
	/**
	 * 查询商户类型
	 */
	@Autowired
	private RoleMerchantRefInfoModelBySelfMapper roleMerchantRefInfoModelBySelfMapper;

	/**
	 * 登陆者数据权限控制
	 * 
	 * @param filterMap
	 */
	public void initFilterMapForRight(HashMap<String, Object> filterMap, String aliasTable) {
		if (StringUtils.isEmpty(aliasTable)) {
			aliasTable = "";
		}
		if (filterMap == null) {
			filterMap = new HashMap<String, Object>();
		}
		Integer userId = ShiroKit.getUser().getId();
		// 1.admin有所有的权限..admin...
		if (userId == null || userId.intValue() == 1) {
			return;
		}
		// 2. 判断是否设置了角色
		List<Integer> listForRole = ShiroKit.getUser().getRoleList();
		if (listForRole == null || listForRole.size() <= 0) {
			filterMap.put("filter", "and 1<>1"); // 没有权限
			return;
		}
		// 3.判断是否设置了数据权限..
		List<RoleMerchantRefInfoModel> list = getRoleMerchantRefInfoList(listForRole);
		if (list == null || list.size() <= 0) {
			filterMap.put("filter", "and 1<>1"); // 没有权限
			return;
		}
		String filter = "";
		// 4. 解析数据权限
		for (RoleMerchantRefInfoModel roleMerchantRefInfoModel : list) {
			if (roleMerchantRefInfoModel.getMerchantType().intValue() == MerchantTypeEnum.DAI_LI_SHANG1.getCode()
					.intValue()) {
				filter = (filter.isEmpty())
						? String.format("%sagents1_id=%d", aliasTable, roleMerchantRefInfoModel.getMerchantId())
						: String.format("%sagents1_id=%d or %s", aliasTable, roleMerchantRefInfoModel.getMerchantId(),
								filter);
			} else if (roleMerchantRefInfoModel.getMerchantType().intValue() == MerchantTypeEnum.DAI_LI_SHANG2.getCode()
					.intValue()) {
				filter = (filter.isEmpty())
						? String.format("%sagents2_id=%d", aliasTable, roleMerchantRefInfoModel.getMerchantId())
						: String.format("%sagents2_id=%d or %s", aliasTable, roleMerchantRefInfoModel.getMerchantId(),
								filter);
			} else if (roleMerchantRefInfoModel.getMerchantType().intValue() == MerchantTypeEnum.DAI_LI_SHANG3.getCode()
					.intValue()) {
				filter = (filter.isEmpty())
						? String.format("%sagents3_id=%d", aliasTable, roleMerchantRefInfoModel.getMerchantId())
						: String.format("%sagents3_id=%d or %s", aliasTable, roleMerchantRefInfoModel.getMerchantId(),
								filter);
			} else if (roleMerchantRefInfoModel.getMerchantType().intValue() == MerchantTypeEnum.PUHUO_SHANG.getCode()
					.intValue()) {
				filter = (filter.isEmpty())
						? String.format("%sshopkeeper_id=%d", aliasTable, roleMerchantRefInfoModel.getMerchantId())
						: String.format("%sshopkeeper_id=%d or %s", aliasTable,
								roleMerchantRefInfoModel.getMerchantId(), filter);
			} else if (roleMerchantRefInfoModel.getMerchantType().intValue() == MerchantTypeEnum.JIA_MENG_SHANG
					.getCode().intValue()) {
				filter = (filter.isEmpty())
						? String.format("%salliance_business_id=%d", aliasTable,
								roleMerchantRefInfoModel.getMerchantId())
						: String.format("%salliance_business_id=%d or %s", aliasTable,
								roleMerchantRefInfoModel.getMerchantId(), filter);
			} else if (roleMerchantRefInfoModel.getMerchantType().intValue() == MerchantTypeEnum.TERMINAL.getCode()
					.intValue()) {
				filter = (filter.isEmpty())
						? String.format("%sonline_merchant_id=%d", aliasTable, roleMerchantRefInfoModel.getMerchantId())
						: String.format("%sonline_merchant_id=%d or %s", aliasTable,
								roleMerchantRefInfoModel.getMerchantId(), filter);
			}
		}
		if (StringUtils.isNotEmpty(filter)) {
			filterMap.put("filter", String.format(" and (%s)", filter));
		}
	}

	protected HttpServletRequest getHttpServletRequest() {
		return HttpKit.getRequest();
	}

	protected HttpServletResponse getHttpServletResponse() {
		return HttpKit.getResponse();
	}

	protected HttpSession getSession() {
		return HttpKit.getRequest().getSession();
	}

	protected HttpSession getSession(Boolean flag) {
		return HttpKit.getRequest().getSession(flag);
	}

	protected String getPara(String name) {
		return HttpKit.getRequest().getParameter(name);
	}

	protected void setAttr(String name, Object value) {
		HttpKit.getRequest().setAttribute(name, value);
	}

	protected Integer getSystemInvokCount() {
		return (Integer) this.getHttpServletRequest().getServletContext().getAttribute("systemCount");
	}

	/**
	 * 把service层的分页信息，封装为bootstrap table通用的分页封装
	 */
	protected <T> PageInfoBT<T> packForBT(Page<T> page) {
		return new PageInfoBT<T>(page);
	}

	/**
	 * 包装一个list，让list增加额外属性
	 */
	protected Object warpObject(BaseControllerWarpper warpper) {
		return warpper.warp();
	}

	/**
	 * 删除cookie
	 */
	protected void deleteCookieByName(String cookieName) {
		Cookie[] cookies = this.getHttpServletRequest().getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(cookieName)) {
				Cookie temp = new Cookie(cookie.getName(), "");
				temp.setMaxAge(0);
				this.getHttpServletResponse().addCookie(temp);
			}
		}
	}

	/**
	 * 删除所有cookie
	 */
	protected void deleteAllCookie() {
		Cookie[] cookies = this.getHttpServletRequest().getCookies();
		for (Cookie cookie : cookies) {
			Cookie temp = new Cookie(cookie.getName(), "");
			temp.setMaxAge(0);
			this.getHttpServletResponse().addCookie(temp);
		}
	}

	/**
	 * 返回前台文件流
	 *
	 * @author fengshuonan
	 * @date 2017年2月28日 下午2:53:19
	 */
	protected ResponseEntity<byte[]> renderFile(String fileName, String filePath) {
		byte[] bytes = FileUtil.toByteArray(filePath);
		return renderFile(fileName, bytes);
	}

	/**
	 * 返回前台文件流
	 *
	 * @author fengshuonan
	 * @date 2017年2月28日 下午2:53:19
	 */
	protected ResponseEntity<byte[]> renderFile(String fileName, byte[] fileBytes) {
		String dfileName = null;
		try {
			dfileName = new String(fileName.getBytes("gb2312"), "iso8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", dfileName);
		return new ResponseEntity<byte[]>(fileBytes, headers, HttpStatus.CREATED);
	}

	/**
	 * 得到当前用户所有的商户列表...
	 * 
	 * @param listRoleId
	 * @return
	 */
	protected List<RoleMerchantRefInfoModel> getRoleMerchantRefInfoList(List<Integer> listRoleId) {
		return roleMerchantRefInfoModelBySelfMapper.getRoleMerchantRefInfoList(listRoleId);
	}

}
