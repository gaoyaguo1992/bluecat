package com.stylefeng.guns.sharecore.modular.system.model;

import java.io.Serializable;
/**
 * 密码因子值对....
 * @author Alan.huang
 *
 */
public class ChargerPwdsRefactors implements Serializable {
    /**
     * 密码因子，验证码...
     */
    private String factorsIdx;
    /**
     *充电器密码列表...
     */
    private String chargerPwds;
    /**
     * 密码因子..
     */
    private String refactors;

    public String getChargerPwds() {
        return chargerPwds;
    }

    public void setChargerPwds(String chargerPwds) {
        this.chargerPwds = chargerPwds;
    }

    public String getRefactors() {
        return refactors;
    }

    public void setRefactors(String refactors) {
        this.refactors = refactors;
    }

    public String getFactorsIdx() {
        return factorsIdx;
    }

    public void setFactorsIdx(String factorsIdx) {
        this.factorsIdx = factorsIdx;
    }
}

