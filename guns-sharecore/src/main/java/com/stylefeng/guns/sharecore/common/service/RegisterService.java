
package com.stylefeng.guns.sharecore.common.service;

import com.stylefeng.guns.sharecore.common.persistence.dao.CustAccountBySelfMapper;
import com.stylefeng.guns.sharecore.common.persistence.dao.CustInfoBySelfMapper;
import com.stylefeng.guns.sharecore.common.persistence.dao.CustInfoModelMapper;
import com.stylefeng.guns.sharecore.common.persistence.model.*;
import com.stylefeng.guns.sharecore.common.utils.SysUtil;
import me.chanjar.weixin.common.util.StringUtils;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 *
 */
@Service
public class RegisterService extends com.stylefeng.guns.sharecore.modular.system.service.BaseService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 数据库查旬
     */
    @Autowired
    private CustInfoModelMapper custInfoMapper;
    /**
     * 客户信息查询
     */
    @Autowired
    private CustInfoBySelfMapper custInfoBySelfMapper;
    /**
     * 客户账号信息查询
     */
    @Autowired
    private CustAccountBySelfMapper custAccountBySelfMapper;

    // 客户号
    @Value("${sharehelper.custNo}")
    private String zdCustNo;


    public CustInfoModel getCustInfo(String openId) {
        if (openId == null || openId.isEmpty()) {
            return null;
        }
        return custInfoBySelfMapper.selectByOpenId(openId);
    }

    /**
     * 根据unionid得到客户信息
     *
     * @param unionId
     * @return
     */
    public CustInfoModel getCustInfoByUnionId(String unionId) {
        if (unionId == null || unionId.isEmpty()) {
            return null;
        }
        return custInfoBySelfMapper.selectByUnionId(unionId);
    }

    /**
     * 根据wxAppOpenId得到客户
     *
     * @return
     */
    public CustInfoModel getCustInfoByWxAppOpenId(String wxAppOpenId) {
        if (wxAppOpenId == null || wxAppOpenId.isEmpty()) {
            return null;
        }
        return custInfoBySelfMapper.selectByWxAppOpenId(wxAppOpenId);
    }

    public CustInfoModel getCustInfoByCustNo(Long custNo) {
        return custInfoBySelfMapper.selectCustInfoAndCustAccountInfoByPrimaryKey(custNo);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 3, rollbackFor = Exception.class)
    public void updateCustInfo(CustInfoModel custInfo) {
        try {
            seqService.setNamesUtf8mb4();
            //custInfo=clearBmpUnicode(custInfo);
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("重新设备mysql数据的name utf8mb4.{}", e);
        }
        custInfoMapper.updateByPrimaryKeySelective(custInfo);
    }

    public String removeNonBmpUnicode(String str) {
        if (str == null) {
            return null;
        }
        str = str.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", "");
        return str;
    }

    /**
     * 注册客户...(小程序, 公众号)
     *
     * @param wxMpUser
     * @return
     * @throws Exception
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
    public CustInfoModel registeShareCust(ShareWxMpUser wxMpUser) throws Exception {

        logger.info(String.format("客户注册入参->openId:%s,WxOpenId:%s,UnionID:%s", wxMpUser.getOpenId(),
                wxMpUser.getWxAppOpenId(), wxMpUser.getUnionId()));

        Date createdDate = new Date();
        CustInfoModel ci = new CustInfoModel();
        Long custNoSeq = seqService.getCustNoSeq();
        ci.setCustNo(custNoSeq);
        ci.setOpenId(wxMpUser.getOpenId());
        ci.setRegTime(createdDate);
        ci.setCustType(CustTypeEnum.CUST.getCode());
        ci.setNickName(wxMpUser.getNickname());
        ci.setSex(wxMpUser.getSex());
        ci.setLanguage(wxMpUser.getLanguage());
        ci.setCity(wxMpUser.getCity());
        ci.setProvince(wxMpUser.getProvince());
        ci.setCountry(wxMpUser.getCountry());
        ci.setHeadImgUrl(wxMpUser.getHeadImgUrl());
        ci.setUnionId(wxMpUser.getUnionId());
        ci.setWexinRemark(wxMpUser.getRemark());
        ci.setGroupId(wxMpUser.getGroupId());
        ci.setWxAppOpenId(wxMpUser.getWxAppOpenId());
        if (StringUtils.isEmpty(wxMpUser.getWxAppOpenId())) {
            ci.setCustSourceType(LentEntryIdEnum.WX.getCode());
        } else {
            ci.setCustSourceType(LentEntryIdEnum.WX_APP.getCode());
        }
        // 判断一下是否存在..
        if (ci.getUnionId() != null && !ci.getUnionId().isEmpty()) {
            try {
                CustInfoModel tmp = custInfoBySelfMapper.selectByUnionId(ci.getUnionId());
                if (tmp != null) {
                    // 重新修改openId..
                    tmp.setOpenId(wxMpUser.getWxAppOpenId());
                    custInfoBySelfMapper.setNamesUtf8mb4();
                    //tmp=clearBmpUnicode(tmp);
                    custInfoBySelfMapper.updateByPrimaryKey(tmp);
                    return tmp;
                }
            } catch (Exception e) {
                logger.info(String.format("客户注册入参CustInfoMapper.selectByUnionId->openId:%s,WxOpenId:%s,UnionID:%s",
                        wxMpUser.getOpenId(), wxMpUser.getWxAppOpenId(), wxMpUser.getUnionId()));
            }
        }
        // 判断一下是否存在..
        if (ci.getWxAppOpenId() != null && !ci.getWxAppOpenId().isEmpty()) {
            try {
                CustInfoModel tmp = custInfoBySelfMapper.selectByWxAppOpenId(ci.getWxAppOpenId());
                if (tmp != null) {
                    // 重新修改unionId..
                    tmp.setUnionId(ci.getUnionId());
                    custInfoBySelfMapper.setNamesUtf8mb4();
                    //tmp=clearBmpUnicode(tmp);
                    custInfoBySelfMapper.updateByPrimaryKey(tmp);
                    return tmp;
                }
            } catch (Exception e) {
                logger.info(String.format("客户注册入参CustInfoMapper.selectByUnionId->openId:%s,WxOpenId:%s,UnionID:%s",
                        wxMpUser.getOpenId(), wxMpUser.getWxAppOpenId(), wxMpUser.getUnionId()));
            }
        }
        logger.info("ci:{}", ci);
        custInfoBySelfMapper.setNamesUtf8mb4();
        //ci=clearBmpUnicode(ci);
        custInfoBySelfMapper.insert(ci);

        CustAccountModel ca = new CustAccountModel();
        ca.setAccountId(seqService.getAccountNoSeq());
        ca.setCustNo(custNoSeq);
        ca.setAvailableBalance(BigDecimal.ZERO);
        ca.setBalance(BigDecimal.ZERO);
        ca.setFrozenBalance(BigDecimal.ZERO);
        ca.setCreateTime(createdDate);
        ca.setDataDigest(SysUtil.getDigest(ca));
        ca.setStatus(AccountStatusEnum.NORMAL.getCode());
        ca.setAmtSource(CustAccountSourceEnum.WEIXIN.getCode());
        ca.setAccountType(CustAccountTypeEnum.RECHARGERACCOUNT.getCode());
        custAccountBySelfMapper.insert(ca);

        CustAccountModel tsfAnt = new CustAccountModel();
        tsfAnt.setAccountId(seqService.getAccountNoSeq());
        tsfAnt.setCustNo(custNoSeq);
        tsfAnt.setAvailableBalance(BigDecimal.ZERO);
        tsfAnt.setBalance(BigDecimal.ZERO);
        tsfAnt.setFrozenBalance(BigDecimal.ZERO);
        tsfAnt.setCreateTime(createdDate);
        tsfAnt.setDataDigest(SysUtil.getDigest(tsfAnt));
        tsfAnt.setStatus(AccountStatusEnum.NORMAL.getCode());
        tsfAnt.setAmtSource(CustAccountSourceEnum.WEIXIN.getCode());
        tsfAnt.setAccountType(CustAccountTypeEnum.FREEZEACCOUNT.getCode());
        custAccountBySelfMapper.insert(tsfAnt);

        CustAccountModel jifenAnt = new CustAccountModel();
        jifenAnt.setAccountId(seqService.getAccountNoSeq());
        jifenAnt.setCustNo(custNoSeq);
        jifenAnt.setAvailableBalance(BigDecimal.ZERO);
        jifenAnt.setBalance(BigDecimal.ZERO);
        jifenAnt.setFrozenBalance(BigDecimal.ZERO);
        jifenAnt.setCreateTime(createdDate);
        jifenAnt.setDataDigest(SysUtil.getDigest(jifenAnt));
        jifenAnt.setStatus(AccountStatusEnum.NORMAL.getCode());
        jifenAnt.setAmtSource(CustAccountSourceEnum.WEIXIN.getCode());
        jifenAnt.setAccountType(CustAccountTypeEnum.INTEGRALACCOUNT.getCode());
        custAccountBySelfMapper.insert(jifenAnt);
        logger.info("created cust_info is:{}", ci);
        return ci;
    }

    /**
     * 微信用户注册(公众号注册)
     *
     * @param wxMpUser
     * @return
     * @throws Exception
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
    public CustInfoModel registeCust(WxMpUser wxMpUser) throws Exception {

        Date createdDate = new Date();
        CustInfoModel ci = new CustInfoModel();
        Long custNoSeq = seqService.getCustNoSeq();

        ci.setCustNo(custNoSeq);
        ci.setOpenId(wxMpUser.getOpenId());
        ci.setRegTime(createdDate);
        ci.setCustType(CustTypeEnum.CUST.getCode());

        ci.setNickName(wxMpUser.getNickname());
        ci.setSex(wxMpUser.getSex());
        ci.setLanguage(wxMpUser.getLanguage());
        ci.setCity(wxMpUser.getCity());
        ci.setProvince(wxMpUser.getProvince());
        ci.setCountry(wxMpUser.getCountry());
        ci.setHeadImgUrl(wxMpUser.getHeadImgUrl());
        ci.setUnionId(wxMpUser.getUnionId());
        ci.setWexinRemark(wxMpUser.getRemark());
        ci.setGroupId(wxMpUser.getGroupId());
        ci.setCustSourceType(LentEntryIdEnum.WX.getCode());
        if (StringUtils.isEmpty(ci.getUnionId())) {
            logger.error("系统账户注册异常,请重新关注公众号");
            throw new Exception("系统账户注册异常【union id is empty】,请重新关注公众号!");
        }
        try {
            // 1. 处理重复数据的问题 。。。
            if (ci.getUnionId() != null && !ci.getUnionId().isEmpty()) {
                CustInfoModel tmp = custInfoBySelfMapper.selectByUnionId(ci.getUnionId());
                if (tmp != null) {
                    tmp.setOpenId(ci.getOpenId());
                    //tmp=clearBmpUnicode(tmp);
                    custInfoBySelfMapper.updateByPrimaryKey(tmp);
                    return tmp;
                }
            }
            // 2. 处理重复数据的问题 。。。
            if (ci.getOpenId() != null && !ci.getOpenId().isEmpty()) {
                CustInfoModel tmp = custInfoBySelfMapper.selectByOpenId(ci.getOpenId());
                if (tmp != null) {
                    if (ci.getUnionId() != null && !ci.getUnionId().isEmpty()) {
                        tmp.setUnionId(ci.getUnionId());
                        //tmp=clearBmpUnicode(tmp);
                        custInfoBySelfMapper.updateByPrimaryKey(tmp);
                    }
                    return tmp;
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("注册客户.微信用户注册(公众号注册)..判断重复失败..", e);
        }
        custInfoBySelfMapper.setNamesUtf8mb4();
        //ci=clearBmpUnicode(ci);
        custInfoBySelfMapper.insert(ci);
        CustAccountModel ca = new CustAccountModel();
        ca.setAccountId(seqService.getAccountNoSeq());
        ca.setCustNo(custNoSeq);
        ca.setAvailableBalance(BigDecimal.ZERO);
        ca.setBalance(BigDecimal.ZERO);
        ca.setFrozenBalance(BigDecimal.ZERO);
        ca.setCreateTime(createdDate);
        ca.setDataDigest(SysUtil.getDigest(ca));
        ca.setStatus(AccountStatusEnum.NORMAL.getCode());
        ca.setAmtSource(CustAccountSourceEnum.WEIXIN.getCode());
        ca.setAccountType(CustAccountTypeEnum.RECHARGERACCOUNT.getCode());
        custAccountBySelfMapper.insert(ca);

        CustAccountModel tsfAnt = new CustAccountModel();
        tsfAnt.setAccountId(seqService.getAccountNoSeq());
        tsfAnt.setCustNo(custNoSeq);
        tsfAnt.setAvailableBalance(BigDecimal.ZERO);
        tsfAnt.setBalance(BigDecimal.ZERO);
        tsfAnt.setFrozenBalance(BigDecimal.ZERO);
        tsfAnt.setCreateTime(createdDate);
        tsfAnt.setDataDigest(SysUtil.getDigest(tsfAnt));
        tsfAnt.setStatus(AccountStatusEnum.NORMAL.getCode());
        tsfAnt.setAmtSource(CustAccountSourceEnum.WEIXIN.getCode());
        tsfAnt.setAccountType(CustAccountTypeEnum.FREEZEACCOUNT.getCode());
        custAccountBySelfMapper.insert(tsfAnt);

        CustAccountModel jifenAnt = new CustAccountModel();
        jifenAnt.setAccountId(seqService.getAccountNoSeq());
        jifenAnt.setCustNo(custNoSeq);
        jifenAnt.setAvailableBalance(BigDecimal.ZERO);
        jifenAnt.setBalance(BigDecimal.ZERO);
        jifenAnt.setFrozenBalance(BigDecimal.ZERO);
        jifenAnt.setCreateTime(createdDate);
        jifenAnt.setDataDigest(SysUtil.getDigest(jifenAnt));
        jifenAnt.setStatus(AccountStatusEnum.NORMAL.getCode());
        jifenAnt.setAmtSource(CustAccountSourceEnum.WEIXIN.getCode());
        jifenAnt.setAccountType(CustAccountTypeEnum.INTEGRALACCOUNT.getCode());
        custAccountBySelfMapper.insert(jifenAnt);

        logger.info("created cust_info is:{}", ci);
        logger.info("created account_info is:{}", ca);
        return ci;
    }

    /**
     * 根据支付宝userId查询用户
     *
     * @param alipayUserId
     * @return
     */
    public CustInfoModel queryZfbCustInfoByUserId(String alipayUserId) {
        CustInfoModel custInfoModel = custInfoBySelfMapper.selectByZfbUserId(alipayUserId);
        return custInfoModel;
    }

    /**
     * 注册支付宝小程序客户信息
     * @param registCust
     * @return
     * @throws Exception
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
    public CustInfoModel registeShareCustFromZfb(CustInfoModel registCust) throws Exception {
        logger.info(String.format("支付宝客户注册入参->userId:%s,AlipayUserInfoShareResponse:%s", registCust.getZfbUserId(), registCust));
        CustInfoModel existsCust = custInfoBySelfMapper.selectByZfbUserId(registCust.getZfbUserId());
        if(existsCust != null){
            return existsCust;
        }
        Date createdDate = new Date();
        CustInfoModel ci = new CustInfoModel();
        Long custNoSeq = seqService.getCustNoSeq();
        ci.setCustNo(custNoSeq);
        ci.setZfbUserId(registCust.getZfbUserId());
        ci.setRegTime(createdDate);
        ci.setCustType(CustTypeEnum.CUST.getCode());
        ci.setNickName(registCust.getNickName());
        ci.setSex(registCust.getSex());
        ci.setCity(registCust.getCity());
        ci.setProvince(registCust.getProvince());
        ci.setCountry(registCust.getCountry());
        ci.setHeadImgUrl(registCust.getHeadImgUrl());
        ci.setCustSourceType(LentEntryIdEnum.ZFB_APP.getCode());
        logger.info("ci:{}", ci);
        custInfoBySelfMapper.setNamesUtf8mb4();
        //ci=clearBmpUnicode(ci);
        custInfoBySelfMapper.insert(ci);

        CustAccountModel ca = new CustAccountModel();
        ca.setAccountId(seqService.getAccountNoSeq());
        ca.setCustNo(custNoSeq);
        ca.setAvailableBalance(BigDecimal.ZERO);
        ca.setBalance(BigDecimal.ZERO);
        ca.setFrozenBalance(BigDecimal.ZERO);
        ca.setCreateTime(createdDate);
        ca.setDataDigest(SysUtil.getDigest(ca));
        ca.setStatus(AccountStatusEnum.NORMAL.getCode());
        ca.setAmtSource(CustAccountSourceEnum.ZFB.getCode());
        ca.setAccountType(CustAccountTypeEnum.RECHARGERACCOUNT.getCode());
        custAccountBySelfMapper.insert(ca);

        CustAccountModel tsfAnt = new CustAccountModel();
        tsfAnt.setAccountId(seqService.getAccountNoSeq());
        tsfAnt.setCustNo(custNoSeq);
        tsfAnt.setAvailableBalance(BigDecimal.ZERO);
        tsfAnt.setBalance(BigDecimal.ZERO);
        tsfAnt.setFrozenBalance(BigDecimal.ZERO);
        tsfAnt.setCreateTime(createdDate);
        tsfAnt.setDataDigest(SysUtil.getDigest(tsfAnt));
        tsfAnt.setStatus(AccountStatusEnum.NORMAL.getCode());
        tsfAnt.setAmtSource(CustAccountSourceEnum.ZFB.getCode());
        tsfAnt.setAccountType(CustAccountTypeEnum.FREEZEACCOUNT.getCode());
        custAccountBySelfMapper.insert(tsfAnt);

        CustAccountModel jifenAnt = new CustAccountModel();
        jifenAnt.setAccountId(seqService.getAccountNoSeq());
        jifenAnt.setCustNo(custNoSeq);
        jifenAnt.setAvailableBalance(BigDecimal.ZERO);
        jifenAnt.setBalance(BigDecimal.ZERO);
        jifenAnt.setFrozenBalance(BigDecimal.ZERO);
        jifenAnt.setCreateTime(createdDate);
        jifenAnt.setDataDigest(SysUtil.getDigest(jifenAnt));
        jifenAnt.setStatus(AccountStatusEnum.NORMAL.getCode());
        jifenAnt.setAmtSource(CustAccountSourceEnum.ZFB.getCode());
        jifenAnt.setAccountType(CustAccountTypeEnum.INTEGRALACCOUNT.getCode());
        custAccountBySelfMapper.insert(jifenAnt);
        logger.info("created cust_info is:{}", ci);
        return ci;
    }
}
