package com.stylefeng.guns.sharecore.modular.system.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MerchantInfoModelExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MerchantInfoModelExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andAddrIsNull() {
            addCriterion("addr is null");
            return (Criteria) this;
        }

        public Criteria andAddrIsNotNull() {
            addCriterion("addr is not null");
            return (Criteria) this;
        }

        public Criteria andAddrEqualTo(String value) {
            addCriterion("addr =", value, "addr");
            return (Criteria) this;
        }

        public Criteria andAddrNotEqualTo(String value) {
            addCriterion("addr <>", value, "addr");
            return (Criteria) this;
        }

        public Criteria andAddrGreaterThan(String value) {
            addCriterion("addr >", value, "addr");
            return (Criteria) this;
        }

        public Criteria andAddrGreaterThanOrEqualTo(String value) {
            addCriterion("addr >=", value, "addr");
            return (Criteria) this;
        }

        public Criteria andAddrLessThan(String value) {
            addCriterion("addr <", value, "addr");
            return (Criteria) this;
        }

        public Criteria andAddrLessThanOrEqualTo(String value) {
            addCriterion("addr <=", value, "addr");
            return (Criteria) this;
        }

        public Criteria andAddrLike(String value) {
            addCriterion("addr like", value, "addr");
            return (Criteria) this;
        }

        public Criteria andAddrNotLike(String value) {
            addCriterion("addr not like", value, "addr");
            return (Criteria) this;
        }

        public Criteria andAddrIn(List<String> values) {
            addCriterion("addr in", values, "addr");
            return (Criteria) this;
        }

        public Criteria andAddrNotIn(List<String> values) {
            addCriterion("addr not in", values, "addr");
            return (Criteria) this;
        }

        public Criteria andAddrBetween(String value1, String value2) {
            addCriterion("addr between", value1, value2, "addr");
            return (Criteria) this;
        }

        public Criteria andAddrNotBetween(String value1, String value2) {
            addCriterion("addr not between", value1, value2, "addr");
            return (Criteria) this;
        }

        public Criteria andPersonNameIsNull() {
            addCriterion("person_name is null");
            return (Criteria) this;
        }

        public Criteria andPersonNameIsNotNull() {
            addCriterion("person_name is not null");
            return (Criteria) this;
        }

        public Criteria andPersonNameEqualTo(String value) {
            addCriterion("person_name =", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameNotEqualTo(String value) {
            addCriterion("person_name <>", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameGreaterThan(String value) {
            addCriterion("person_name >", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameGreaterThanOrEqualTo(String value) {
            addCriterion("person_name >=", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameLessThan(String value) {
            addCriterion("person_name <", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameLessThanOrEqualTo(String value) {
            addCriterion("person_name <=", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameLike(String value) {
            addCriterion("person_name like", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameNotLike(String value) {
            addCriterion("person_name not like", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameIn(List<String> values) {
            addCriterion("person_name in", values, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameNotIn(List<String> values) {
            addCriterion("person_name not in", values, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameBetween(String value1, String value2) {
            addCriterion("person_name between", value1, value2, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameNotBetween(String value1, String value2) {
            addCriterion("person_name not between", value1, value2, "personName");
            return (Criteria) this;
        }

        public Criteria andTelNoIsNull() {
            addCriterion("tel_no is null");
            return (Criteria) this;
        }

        public Criteria andTelNoIsNotNull() {
            addCriterion("tel_no is not null");
            return (Criteria) this;
        }

        public Criteria andTelNoEqualTo(String value) {
            addCriterion("tel_no =", value, "telNo");
            return (Criteria) this;
        }

        public Criteria andTelNoNotEqualTo(String value) {
            addCriterion("tel_no <>", value, "telNo");
            return (Criteria) this;
        }

        public Criteria andTelNoGreaterThan(String value) {
            addCriterion("tel_no >", value, "telNo");
            return (Criteria) this;
        }

        public Criteria andTelNoGreaterThanOrEqualTo(String value) {
            addCriterion("tel_no >=", value, "telNo");
            return (Criteria) this;
        }

        public Criteria andTelNoLessThan(String value) {
            addCriterion("tel_no <", value, "telNo");
            return (Criteria) this;
        }

        public Criteria andTelNoLessThanOrEqualTo(String value) {
            addCriterion("tel_no <=", value, "telNo");
            return (Criteria) this;
        }

        public Criteria andTelNoLike(String value) {
            addCriterion("tel_no like", value, "telNo");
            return (Criteria) this;
        }

        public Criteria andTelNoNotLike(String value) {
            addCriterion("tel_no not like", value, "telNo");
            return (Criteria) this;
        }

        public Criteria andTelNoIn(List<String> values) {
            addCriterion("tel_no in", values, "telNo");
            return (Criteria) this;
        }

        public Criteria andTelNoNotIn(List<String> values) {
            addCriterion("tel_no not in", values, "telNo");
            return (Criteria) this;
        }

        public Criteria andTelNoBetween(String value1, String value2) {
            addCriterion("tel_no between", value1, value2, "telNo");
            return (Criteria) this;
        }

        public Criteria andTelNoNotBetween(String value1, String value2) {
            addCriterion("tel_no not between", value1, value2, "telNo");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeIsNull() {
            addCriterion("merchant_type is null");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeIsNotNull() {
            addCriterion("merchant_type is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeEqualTo(Byte value) {
            addCriterion("merchant_type =", value, "merchantType");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeNotEqualTo(Byte value) {
            addCriterion("merchant_type <>", value, "merchantType");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeGreaterThan(Byte value) {
            addCriterion("merchant_type >", value, "merchantType");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("merchant_type >=", value, "merchantType");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeLessThan(Byte value) {
            addCriterion("merchant_type <", value, "merchantType");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeLessThanOrEqualTo(Byte value) {
            addCriterion("merchant_type <=", value, "merchantType");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeIn(List<Byte> values) {
            addCriterion("merchant_type in", values, "merchantType");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeNotIn(List<Byte> values) {
            addCriterion("merchant_type not in", values, "merchantType");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeBetween(Byte value1, Byte value2) {
            addCriterion("merchant_type between", value1, value2, "merchantType");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("merchant_type not between", value1, value2, "merchantType");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeCnIsNull() {
            addCriterion("merchant_type_cn is null");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeCnIsNotNull() {
            addCriterion("merchant_type_cn is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeCnEqualTo(String value) {
            addCriterion("merchant_type_cn =", value, "merchantTypeCn");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeCnNotEqualTo(String value) {
            addCriterion("merchant_type_cn <>", value, "merchantTypeCn");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeCnGreaterThan(String value) {
            addCriterion("merchant_type_cn >", value, "merchantTypeCn");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeCnGreaterThanOrEqualTo(String value) {
            addCriterion("merchant_type_cn >=", value, "merchantTypeCn");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeCnLessThan(String value) {
            addCriterion("merchant_type_cn <", value, "merchantTypeCn");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeCnLessThanOrEqualTo(String value) {
            addCriterion("merchant_type_cn <=", value, "merchantTypeCn");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeCnLike(String value) {
            addCriterion("merchant_type_cn like", value, "merchantTypeCn");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeCnNotLike(String value) {
            addCriterion("merchant_type_cn not like", value, "merchantTypeCn");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeCnIn(List<String> values) {
            addCriterion("merchant_type_cn in", values, "merchantTypeCn");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeCnNotIn(List<String> values) {
            addCriterion("merchant_type_cn not in", values, "merchantTypeCn");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeCnBetween(String value1, String value2) {
            addCriterion("merchant_type_cn between", value1, value2, "merchantTypeCn");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeCnNotBetween(String value1, String value2) {
            addCriterion("merchant_type_cn not between", value1, value2, "merchantTypeCn");
            return (Criteria) this;
        }

        public Criteria andParentMerchantIdIsNull() {
            addCriterion("parent_merchant_id is null");
            return (Criteria) this;
        }

        public Criteria andParentMerchantIdIsNotNull() {
            addCriterion("parent_merchant_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentMerchantIdEqualTo(Long value) {
            addCriterion("parent_merchant_id =", value, "parentMerchantId");
            return (Criteria) this;
        }

        public Criteria andParentMerchantIdNotEqualTo(Long value) {
            addCriterion("parent_merchant_id <>", value, "parentMerchantId");
            return (Criteria) this;
        }

        public Criteria andParentMerchantIdGreaterThan(Long value) {
            addCriterion("parent_merchant_id >", value, "parentMerchantId");
            return (Criteria) this;
        }

        public Criteria andParentMerchantIdGreaterThanOrEqualTo(Long value) {
            addCriterion("parent_merchant_id >=", value, "parentMerchantId");
            return (Criteria) this;
        }

        public Criteria andParentMerchantIdLessThan(Long value) {
            addCriterion("parent_merchant_id <", value, "parentMerchantId");
            return (Criteria) this;
        }

        public Criteria andParentMerchantIdLessThanOrEqualTo(Long value) {
            addCriterion("parent_merchant_id <=", value, "parentMerchantId");
            return (Criteria) this;
        }

        public Criteria andParentMerchantIdIn(List<Long> values) {
            addCriterion("parent_merchant_id in", values, "parentMerchantId");
            return (Criteria) this;
        }

        public Criteria andParentMerchantIdNotIn(List<Long> values) {
            addCriterion("parent_merchant_id not in", values, "parentMerchantId");
            return (Criteria) this;
        }

        public Criteria andParentMerchantIdBetween(Long value1, Long value2) {
            addCriterion("parent_merchant_id between", value1, value2, "parentMerchantId");
            return (Criteria) this;
        }

        public Criteria andParentMerchantIdNotBetween(Long value1, Long value2) {
            addCriterion("parent_merchant_id not between", value1, value2, "parentMerchantId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andMerchantLevelIsNull() {
            addCriterion("merchant_level is null");
            return (Criteria) this;
        }

        public Criteria andMerchantLevelIsNotNull() {
            addCriterion("merchant_level is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantLevelEqualTo(Byte value) {
            addCriterion("merchant_level =", value, "merchantLevel");
            return (Criteria) this;
        }

        public Criteria andMerchantLevelNotEqualTo(Byte value) {
            addCriterion("merchant_level <>", value, "merchantLevel");
            return (Criteria) this;
        }

        public Criteria andMerchantLevelGreaterThan(Byte value) {
            addCriterion("merchant_level >", value, "merchantLevel");
            return (Criteria) this;
        }

        public Criteria andMerchantLevelGreaterThanOrEqualTo(Byte value) {
            addCriterion("merchant_level >=", value, "merchantLevel");
            return (Criteria) this;
        }

        public Criteria andMerchantLevelLessThan(Byte value) {
            addCriterion("merchant_level <", value, "merchantLevel");
            return (Criteria) this;
        }

        public Criteria andMerchantLevelLessThanOrEqualTo(Byte value) {
            addCriterion("merchant_level <=", value, "merchantLevel");
            return (Criteria) this;
        }

        public Criteria andMerchantLevelIn(List<Byte> values) {
            addCriterion("merchant_level in", values, "merchantLevel");
            return (Criteria) this;
        }

        public Criteria andMerchantLevelNotIn(List<Byte> values) {
            addCriterion("merchant_level not in", values, "merchantLevel");
            return (Criteria) this;
        }

        public Criteria andMerchantLevelBetween(Byte value1, Byte value2) {
            addCriterion("merchant_level between", value1, value2, "merchantLevel");
            return (Criteria) this;
        }

        public Criteria andMerchantLevelNotBetween(Byte value1, Byte value2) {
            addCriterion("merchant_level not between", value1, value2, "merchantLevel");
            return (Criteria) this;
        }

        public Criteria andMerchantLevelCnIsNull() {
            addCriterion("merchant_level_cn is null");
            return (Criteria) this;
        }

        public Criteria andMerchantLevelCnIsNotNull() {
            addCriterion("merchant_level_cn is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantLevelCnEqualTo(String value) {
            addCriterion("merchant_level_cn =", value, "merchantLevelCn");
            return (Criteria) this;
        }

        public Criteria andMerchantLevelCnNotEqualTo(String value) {
            addCriterion("merchant_level_cn <>", value, "merchantLevelCn");
            return (Criteria) this;
        }

        public Criteria andMerchantLevelCnGreaterThan(String value) {
            addCriterion("merchant_level_cn >", value, "merchantLevelCn");
            return (Criteria) this;
        }

        public Criteria andMerchantLevelCnGreaterThanOrEqualTo(String value) {
            addCriterion("merchant_level_cn >=", value, "merchantLevelCn");
            return (Criteria) this;
        }

        public Criteria andMerchantLevelCnLessThan(String value) {
            addCriterion("merchant_level_cn <", value, "merchantLevelCn");
            return (Criteria) this;
        }

        public Criteria andMerchantLevelCnLessThanOrEqualTo(String value) {
            addCriterion("merchant_level_cn <=", value, "merchantLevelCn");
            return (Criteria) this;
        }

        public Criteria andMerchantLevelCnLike(String value) {
            addCriterion("merchant_level_cn like", value, "merchantLevelCn");
            return (Criteria) this;
        }

        public Criteria andMerchantLevelCnNotLike(String value) {
            addCriterion("merchant_level_cn not like", value, "merchantLevelCn");
            return (Criteria) this;
        }

        public Criteria andMerchantLevelCnIn(List<String> values) {
            addCriterion("merchant_level_cn in", values, "merchantLevelCn");
            return (Criteria) this;
        }

        public Criteria andMerchantLevelCnNotIn(List<String> values) {
            addCriterion("merchant_level_cn not in", values, "merchantLevelCn");
            return (Criteria) this;
        }

        public Criteria andMerchantLevelCnBetween(String value1, String value2) {
            addCriterion("merchant_level_cn between", value1, value2, "merchantLevelCn");
            return (Criteria) this;
        }

        public Criteria andMerchantLevelCnNotBetween(String value1, String value2) {
            addCriterion("merchant_level_cn not between", value1, value2, "merchantLevelCn");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("STATUS not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andMerchantZoneIsNull() {
            addCriterion("merchant_zone is null");
            return (Criteria) this;
        }

        public Criteria andMerchantZoneIsNotNull() {
            addCriterion("merchant_zone is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantZoneEqualTo(String value) {
            addCriterion("merchant_zone =", value, "merchantZone");
            return (Criteria) this;
        }

        public Criteria andMerchantZoneNotEqualTo(String value) {
            addCriterion("merchant_zone <>", value, "merchantZone");
            return (Criteria) this;
        }

        public Criteria andMerchantZoneGreaterThan(String value) {
            addCriterion("merchant_zone >", value, "merchantZone");
            return (Criteria) this;
        }

        public Criteria andMerchantZoneGreaterThanOrEqualTo(String value) {
            addCriterion("merchant_zone >=", value, "merchantZone");
            return (Criteria) this;
        }

        public Criteria andMerchantZoneLessThan(String value) {
            addCriterion("merchant_zone <", value, "merchantZone");
            return (Criteria) this;
        }

        public Criteria andMerchantZoneLessThanOrEqualTo(String value) {
            addCriterion("merchant_zone <=", value, "merchantZone");
            return (Criteria) this;
        }

        public Criteria andMerchantZoneLike(String value) {
            addCriterion("merchant_zone like", value, "merchantZone");
            return (Criteria) this;
        }

        public Criteria andMerchantZoneNotLike(String value) {
            addCriterion("merchant_zone not like", value, "merchantZone");
            return (Criteria) this;
        }

        public Criteria andMerchantZoneIn(List<String> values) {
            addCriterion("merchant_zone in", values, "merchantZone");
            return (Criteria) this;
        }

        public Criteria andMerchantZoneNotIn(List<String> values) {
            addCriterion("merchant_zone not in", values, "merchantZone");
            return (Criteria) this;
        }

        public Criteria andMerchantZoneBetween(String value1, String value2) {
            addCriterion("merchant_zone between", value1, value2, "merchantZone");
            return (Criteria) this;
        }

        public Criteria andMerchantZoneNotBetween(String value1, String value2) {
            addCriterion("merchant_zone not between", value1, value2, "merchantZone");
            return (Criteria) this;
        }

        public Criteria andIndustryCategoryCodeIsNull() {
            addCriterion("industry_category_code is null");
            return (Criteria) this;
        }

        public Criteria andIndustryCategoryCodeIsNotNull() {
            addCriterion("industry_category_code is not null");
            return (Criteria) this;
        }

        public Criteria andIndustryCategoryCodeEqualTo(Byte value) {
            addCriterion("industry_category_code =", value, "industryCategoryCode");
            return (Criteria) this;
        }

        public Criteria andIndustryCategoryCodeNotEqualTo(Byte value) {
            addCriterion("industry_category_code <>", value, "industryCategoryCode");
            return (Criteria) this;
        }

        public Criteria andIndustryCategoryCodeGreaterThan(Byte value) {
            addCriterion("industry_category_code >", value, "industryCategoryCode");
            return (Criteria) this;
        }

        public Criteria andIndustryCategoryCodeGreaterThanOrEqualTo(Byte value) {
            addCriterion("industry_category_code >=", value, "industryCategoryCode");
            return (Criteria) this;
        }

        public Criteria andIndustryCategoryCodeLessThan(Byte value) {
            addCriterion("industry_category_code <", value, "industryCategoryCode");
            return (Criteria) this;
        }

        public Criteria andIndustryCategoryCodeLessThanOrEqualTo(Byte value) {
            addCriterion("industry_category_code <=", value, "industryCategoryCode");
            return (Criteria) this;
        }

        public Criteria andIndustryCategoryCodeIn(List<Byte> values) {
            addCriterion("industry_category_code in", values, "industryCategoryCode");
            return (Criteria) this;
        }

        public Criteria andIndustryCategoryCodeNotIn(List<Byte> values) {
            addCriterion("industry_category_code not in", values, "industryCategoryCode");
            return (Criteria) this;
        }

        public Criteria andIndustryCategoryCodeBetween(Byte value1, Byte value2) {
            addCriterion("industry_category_code between", value1, value2, "industryCategoryCode");
            return (Criteria) this;
        }

        public Criteria andIndustryCategoryCodeNotBetween(Byte value1, Byte value2) {
            addCriterion("industry_category_code not between", value1, value2, "industryCategoryCode");
            return (Criteria) this;
        }

        public Criteria andIndustryCategoryCnIsNull() {
            addCriterion("industry_category_cn is null");
            return (Criteria) this;
        }

        public Criteria andIndustryCategoryCnIsNotNull() {
            addCriterion("industry_category_cn is not null");
            return (Criteria) this;
        }

        public Criteria andIndustryCategoryCnEqualTo(String value) {
            addCriterion("industry_category_cn =", value, "industryCategoryCn");
            return (Criteria) this;
        }

        public Criteria andIndustryCategoryCnNotEqualTo(String value) {
            addCriterion("industry_category_cn <>", value, "industryCategoryCn");
            return (Criteria) this;
        }

        public Criteria andIndustryCategoryCnGreaterThan(String value) {
            addCriterion("industry_category_cn >", value, "industryCategoryCn");
            return (Criteria) this;
        }

        public Criteria andIndustryCategoryCnGreaterThanOrEqualTo(String value) {
            addCriterion("industry_category_cn >=", value, "industryCategoryCn");
            return (Criteria) this;
        }

        public Criteria andIndustryCategoryCnLessThan(String value) {
            addCriterion("industry_category_cn <", value, "industryCategoryCn");
            return (Criteria) this;
        }

        public Criteria andIndustryCategoryCnLessThanOrEqualTo(String value) {
            addCriterion("industry_category_cn <=", value, "industryCategoryCn");
            return (Criteria) this;
        }

        public Criteria andIndustryCategoryCnLike(String value) {
            addCriterion("industry_category_cn like", value, "industryCategoryCn");
            return (Criteria) this;
        }

        public Criteria andIndustryCategoryCnNotLike(String value) {
            addCriterion("industry_category_cn not like", value, "industryCategoryCn");
            return (Criteria) this;
        }

        public Criteria andIndustryCategoryCnIn(List<String> values) {
            addCriterion("industry_category_cn in", values, "industryCategoryCn");
            return (Criteria) this;
        }

        public Criteria andIndustryCategoryCnNotIn(List<String> values) {
            addCriterion("industry_category_cn not in", values, "industryCategoryCn");
            return (Criteria) this;
        }

        public Criteria andIndustryCategoryCnBetween(String value1, String value2) {
            addCriterion("industry_category_cn between", value1, value2, "industryCategoryCn");
            return (Criteria) this;
        }

        public Criteria andIndustryCategoryCnNotBetween(String value1, String value2) {
            addCriterion("industry_category_cn not between", value1, value2, "industryCategoryCn");
            return (Criteria) this;
        }

        public Criteria andContractCycleIsNull() {
            addCriterion("contract_cycle is null");
            return (Criteria) this;
        }

        public Criteria andContractCycleIsNotNull() {
            addCriterion("contract_cycle is not null");
            return (Criteria) this;
        }

        public Criteria andContractCycleEqualTo(Byte value) {
            addCriterion("contract_cycle =", value, "contractCycle");
            return (Criteria) this;
        }

        public Criteria andContractCycleNotEqualTo(Byte value) {
            addCriterion("contract_cycle <>", value, "contractCycle");
            return (Criteria) this;
        }

        public Criteria andContractCycleGreaterThan(Byte value) {
            addCriterion("contract_cycle >", value, "contractCycle");
            return (Criteria) this;
        }

        public Criteria andContractCycleGreaterThanOrEqualTo(Byte value) {
            addCriterion("contract_cycle >=", value, "contractCycle");
            return (Criteria) this;
        }

        public Criteria andContractCycleLessThan(Byte value) {
            addCriterion("contract_cycle <", value, "contractCycle");
            return (Criteria) this;
        }

        public Criteria andContractCycleLessThanOrEqualTo(Byte value) {
            addCriterion("contract_cycle <=", value, "contractCycle");
            return (Criteria) this;
        }

        public Criteria andContractCycleIn(List<Byte> values) {
            addCriterion("contract_cycle in", values, "contractCycle");
            return (Criteria) this;
        }

        public Criteria andContractCycleNotIn(List<Byte> values) {
            addCriterion("contract_cycle not in", values, "contractCycle");
            return (Criteria) this;
        }

        public Criteria andContractCycleBetween(Byte value1, Byte value2) {
            addCriterion("contract_cycle between", value1, value2, "contractCycle");
            return (Criteria) this;
        }

        public Criteria andContractCycleNotBetween(Byte value1, Byte value2) {
            addCriterion("contract_cycle not between", value1, value2, "contractCycle");
            return (Criteria) this;
        }

        public Criteria andSettlementAccountIsNull() {
            addCriterion("settlement_account is null");
            return (Criteria) this;
        }

        public Criteria andSettlementAccountIsNotNull() {
            addCriterion("settlement_account is not null");
            return (Criteria) this;
        }

        public Criteria andSettlementAccountEqualTo(String value) {
            addCriterion("settlement_account =", value, "settlementAccount");
            return (Criteria) this;
        }

        public Criteria andSettlementAccountNotEqualTo(String value) {
            addCriterion("settlement_account <>", value, "settlementAccount");
            return (Criteria) this;
        }

        public Criteria andSettlementAccountGreaterThan(String value) {
            addCriterion("settlement_account >", value, "settlementAccount");
            return (Criteria) this;
        }

        public Criteria andSettlementAccountGreaterThanOrEqualTo(String value) {
            addCriterion("settlement_account >=", value, "settlementAccount");
            return (Criteria) this;
        }

        public Criteria andSettlementAccountLessThan(String value) {
            addCriterion("settlement_account <", value, "settlementAccount");
            return (Criteria) this;
        }

        public Criteria andSettlementAccountLessThanOrEqualTo(String value) {
            addCriterion("settlement_account <=", value, "settlementAccount");
            return (Criteria) this;
        }

        public Criteria andSettlementAccountLike(String value) {
            addCriterion("settlement_account like", value, "settlementAccount");
            return (Criteria) this;
        }

        public Criteria andSettlementAccountNotLike(String value) {
            addCriterion("settlement_account not like", value, "settlementAccount");
            return (Criteria) this;
        }

        public Criteria andSettlementAccountIn(List<String> values) {
            addCriterion("settlement_account in", values, "settlementAccount");
            return (Criteria) this;
        }

        public Criteria andSettlementAccountNotIn(List<String> values) {
            addCriterion("settlement_account not in", values, "settlementAccount");
            return (Criteria) this;
        }

        public Criteria andSettlementAccountBetween(String value1, String value2) {
            addCriterion("settlement_account between", value1, value2, "settlementAccount");
            return (Criteria) this;
        }

        public Criteria andSettlementAccountNotBetween(String value1, String value2) {
            addCriterion("settlement_account not between", value1, value2, "settlementAccount");
            return (Criteria) this;
        }

        public Criteria andHisTradeCountIsNull() {
            addCriterion("his_trade_count is null");
            return (Criteria) this;
        }

        public Criteria andHisTradeCountIsNotNull() {
            addCriterion("his_trade_count is not null");
            return (Criteria) this;
        }

        public Criteria andHisTradeCountEqualTo(Integer value) {
            addCriterion("his_trade_count =", value, "hisTradeCount");
            return (Criteria) this;
        }

        public Criteria andHisTradeCountNotEqualTo(Integer value) {
            addCriterion("his_trade_count <>", value, "hisTradeCount");
            return (Criteria) this;
        }

        public Criteria andHisTradeCountGreaterThan(Integer value) {
            addCriterion("his_trade_count >", value, "hisTradeCount");
            return (Criteria) this;
        }

        public Criteria andHisTradeCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("his_trade_count >=", value, "hisTradeCount");
            return (Criteria) this;
        }

        public Criteria andHisTradeCountLessThan(Integer value) {
            addCriterion("his_trade_count <", value, "hisTradeCount");
            return (Criteria) this;
        }

        public Criteria andHisTradeCountLessThanOrEqualTo(Integer value) {
            addCriterion("his_trade_count <=", value, "hisTradeCount");
            return (Criteria) this;
        }

        public Criteria andHisTradeCountIn(List<Integer> values) {
            addCriterion("his_trade_count in", values, "hisTradeCount");
            return (Criteria) this;
        }

        public Criteria andHisTradeCountNotIn(List<Integer> values) {
            addCriterion("his_trade_count not in", values, "hisTradeCount");
            return (Criteria) this;
        }

        public Criteria andHisTradeCountBetween(Integer value1, Integer value2) {
            addCriterion("his_trade_count between", value1, value2, "hisTradeCount");
            return (Criteria) this;
        }

        public Criteria andHisTradeCountNotBetween(Integer value1, Integer value2) {
            addCriterion("his_trade_count not between", value1, value2, "hisTradeCount");
            return (Criteria) this;
        }

        public Criteria andShopkeeperTypeIsNull() {
            addCriterion("shopkeeper_type is null");
            return (Criteria) this;
        }

        public Criteria andShopkeeperTypeIsNotNull() {
            addCriterion("shopkeeper_type is not null");
            return (Criteria) this;
        }

        public Criteria andShopkeeperTypeEqualTo(Byte value) {
            addCriterion("shopkeeper_type =", value, "shopkeeperType");
            return (Criteria) this;
        }

        public Criteria andShopkeeperTypeNotEqualTo(Byte value) {
            addCriterion("shopkeeper_type <>", value, "shopkeeperType");
            return (Criteria) this;
        }

        public Criteria andShopkeeperTypeGreaterThan(Byte value) {
            addCriterion("shopkeeper_type >", value, "shopkeeperType");
            return (Criteria) this;
        }

        public Criteria andShopkeeperTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("shopkeeper_type >=", value, "shopkeeperType");
            return (Criteria) this;
        }

        public Criteria andShopkeeperTypeLessThan(Byte value) {
            addCriterion("shopkeeper_type <", value, "shopkeeperType");
            return (Criteria) this;
        }

        public Criteria andShopkeeperTypeLessThanOrEqualTo(Byte value) {
            addCriterion("shopkeeper_type <=", value, "shopkeeperType");
            return (Criteria) this;
        }

        public Criteria andShopkeeperTypeIn(List<Byte> values) {
            addCriterion("shopkeeper_type in", values, "shopkeeperType");
            return (Criteria) this;
        }

        public Criteria andShopkeeperTypeNotIn(List<Byte> values) {
            addCriterion("shopkeeper_type not in", values, "shopkeeperType");
            return (Criteria) this;
        }

        public Criteria andShopkeeperTypeBetween(Byte value1, Byte value2) {
            addCriterion("shopkeeper_type between", value1, value2, "shopkeeperType");
            return (Criteria) this;
        }

        public Criteria andShopkeeperTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("shopkeeper_type not between", value1, value2, "shopkeeperType");
            return (Criteria) this;
        }

        public Criteria andShopkeeperTypeCnIsNull() {
            addCriterion("shopkeeper_type_cn is null");
            return (Criteria) this;
        }

        public Criteria andShopkeeperTypeCnIsNotNull() {
            addCriterion("shopkeeper_type_cn is not null");
            return (Criteria) this;
        }

        public Criteria andShopkeeperTypeCnEqualTo(String value) {
            addCriterion("shopkeeper_type_cn =", value, "shopkeeperTypeCn");
            return (Criteria) this;
        }

        public Criteria andShopkeeperTypeCnNotEqualTo(String value) {
            addCriterion("shopkeeper_type_cn <>", value, "shopkeeperTypeCn");
            return (Criteria) this;
        }

        public Criteria andShopkeeperTypeCnGreaterThan(String value) {
            addCriterion("shopkeeper_type_cn >", value, "shopkeeperTypeCn");
            return (Criteria) this;
        }

        public Criteria andShopkeeperTypeCnGreaterThanOrEqualTo(String value) {
            addCriterion("shopkeeper_type_cn >=", value, "shopkeeperTypeCn");
            return (Criteria) this;
        }

        public Criteria andShopkeeperTypeCnLessThan(String value) {
            addCriterion("shopkeeper_type_cn <", value, "shopkeeperTypeCn");
            return (Criteria) this;
        }

        public Criteria andShopkeeperTypeCnLessThanOrEqualTo(String value) {
            addCriterion("shopkeeper_type_cn <=", value, "shopkeeperTypeCn");
            return (Criteria) this;
        }

        public Criteria andShopkeeperTypeCnLike(String value) {
            addCriterion("shopkeeper_type_cn like", value, "shopkeeperTypeCn");
            return (Criteria) this;
        }

        public Criteria andShopkeeperTypeCnNotLike(String value) {
            addCriterion("shopkeeper_type_cn not like", value, "shopkeeperTypeCn");
            return (Criteria) this;
        }

        public Criteria andShopkeeperTypeCnIn(List<String> values) {
            addCriterion("shopkeeper_type_cn in", values, "shopkeeperTypeCn");
            return (Criteria) this;
        }

        public Criteria andShopkeeperTypeCnNotIn(List<String> values) {
            addCriterion("shopkeeper_type_cn not in", values, "shopkeeperTypeCn");
            return (Criteria) this;
        }

        public Criteria andShopkeeperTypeCnBetween(String value1, String value2) {
            addCriterion("shopkeeper_type_cn between", value1, value2, "shopkeeperTypeCn");
            return (Criteria) this;
        }

        public Criteria andShopkeeperTypeCnNotBetween(String value1, String value2) {
            addCriterion("shopkeeper_type_cn not between", value1, value2, "shopkeeperTypeCn");
            return (Criteria) this;
        }

        public Criteria andIsOnlineServiceIsNull() {
            addCriterion("is_online_service is null");
            return (Criteria) this;
        }

        public Criteria andIsOnlineServiceIsNotNull() {
            addCriterion("is_online_service is not null");
            return (Criteria) this;
        }

        public Criteria andIsOnlineServiceEqualTo(Byte value) {
            addCriterion("is_online_service =", value, "isOnlineService");
            return (Criteria) this;
        }

        public Criteria andIsOnlineServiceNotEqualTo(Byte value) {
            addCriterion("is_online_service <>", value, "isOnlineService");
            return (Criteria) this;
        }

        public Criteria andIsOnlineServiceGreaterThan(Byte value) {
            addCriterion("is_online_service >", value, "isOnlineService");
            return (Criteria) this;
        }

        public Criteria andIsOnlineServiceGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_online_service >=", value, "isOnlineService");
            return (Criteria) this;
        }

        public Criteria andIsOnlineServiceLessThan(Byte value) {
            addCriterion("is_online_service <", value, "isOnlineService");
            return (Criteria) this;
        }

        public Criteria andIsOnlineServiceLessThanOrEqualTo(Byte value) {
            addCriterion("is_online_service <=", value, "isOnlineService");
            return (Criteria) this;
        }

        public Criteria andIsOnlineServiceIn(List<Byte> values) {
            addCriterion("is_online_service in", values, "isOnlineService");
            return (Criteria) this;
        }

        public Criteria andIsOnlineServiceNotIn(List<Byte> values) {
            addCriterion("is_online_service not in", values, "isOnlineService");
            return (Criteria) this;
        }

        public Criteria andIsOnlineServiceBetween(Byte value1, Byte value2) {
            addCriterion("is_online_service between", value1, value2, "isOnlineService");
            return (Criteria) this;
        }

        public Criteria andIsOnlineServiceNotBetween(Byte value1, Byte value2) {
            addCriterion("is_online_service not between", value1, value2, "isOnlineService");
            return (Criteria) this;
        }

        public Criteria andHisTradeAmountIsNull() {
            addCriterion("his_trade_amount is null");
            return (Criteria) this;
        }

        public Criteria andHisTradeAmountIsNotNull() {
            addCriterion("his_trade_amount is not null");
            return (Criteria) this;
        }

        public Criteria andHisTradeAmountEqualTo(BigDecimal value) {
            addCriterion("his_trade_amount =", value, "hisTradeAmount");
            return (Criteria) this;
        }

        public Criteria andHisTradeAmountNotEqualTo(BigDecimal value) {
            addCriterion("his_trade_amount <>", value, "hisTradeAmount");
            return (Criteria) this;
        }

        public Criteria andHisTradeAmountGreaterThan(BigDecimal value) {
            addCriterion("his_trade_amount >", value, "hisTradeAmount");
            return (Criteria) this;
        }

        public Criteria andHisTradeAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("his_trade_amount >=", value, "hisTradeAmount");
            return (Criteria) this;
        }

        public Criteria andHisTradeAmountLessThan(BigDecimal value) {
            addCriterion("his_trade_amount <", value, "hisTradeAmount");
            return (Criteria) this;
        }

        public Criteria andHisTradeAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("his_trade_amount <=", value, "hisTradeAmount");
            return (Criteria) this;
        }

        public Criteria andHisTradeAmountIn(List<BigDecimal> values) {
            addCriterion("his_trade_amount in", values, "hisTradeAmount");
            return (Criteria) this;
        }

        public Criteria andHisTradeAmountNotIn(List<BigDecimal> values) {
            addCriterion("his_trade_amount not in", values, "hisTradeAmount");
            return (Criteria) this;
        }

        public Criteria andHisTradeAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("his_trade_amount between", value1, value2, "hisTradeAmount");
            return (Criteria) this;
        }

        public Criteria andHisTradeAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("his_trade_amount not between", value1, value2, "hisTradeAmount");
            return (Criteria) this;
        }

        public Criteria andInvestMoneyIsNull() {
            addCriterion("invest_money is null");
            return (Criteria) this;
        }

        public Criteria andInvestMoneyIsNotNull() {
            addCriterion("invest_money is not null");
            return (Criteria) this;
        }

        public Criteria andInvestMoneyEqualTo(BigDecimal value) {
            addCriterion("invest_money =", value, "investMoney");
            return (Criteria) this;
        }

        public Criteria andInvestMoneyNotEqualTo(BigDecimal value) {
            addCriterion("invest_money <>", value, "investMoney");
            return (Criteria) this;
        }

        public Criteria andInvestMoneyGreaterThan(BigDecimal value) {
            addCriterion("invest_money >", value, "investMoney");
            return (Criteria) this;
        }

        public Criteria andInvestMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("invest_money >=", value, "investMoney");
            return (Criteria) this;
        }

        public Criteria andInvestMoneyLessThan(BigDecimal value) {
            addCriterion("invest_money <", value, "investMoney");
            return (Criteria) this;
        }

        public Criteria andInvestMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("invest_money <=", value, "investMoney");
            return (Criteria) this;
        }

        public Criteria andInvestMoneyIn(List<BigDecimal> values) {
            addCriterion("invest_money in", values, "investMoney");
            return (Criteria) this;
        }

        public Criteria andInvestMoneyNotIn(List<BigDecimal> values) {
            addCriterion("invest_money not in", values, "investMoney");
            return (Criteria) this;
        }

        public Criteria andInvestMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("invest_money between", value1, value2, "investMoney");
            return (Criteria) this;
        }

        public Criteria andInvestMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("invest_money not between", value1, value2, "investMoney");
            return (Criteria) this;
        }

        public Criteria andSettlementCustNoIsNull() {
            addCriterion("settlement_cust_no is null");
            return (Criteria) this;
        }

        public Criteria andSettlementCustNoIsNotNull() {
            addCriterion("settlement_cust_no is not null");
            return (Criteria) this;
        }

        public Criteria andSettlementCustNoEqualTo(Long value) {
            addCriterion("settlement_cust_no =", value, "settlementCustNo");
            return (Criteria) this;
        }

        public Criteria andSettlementCustNoNotEqualTo(Long value) {
            addCriterion("settlement_cust_no <>", value, "settlementCustNo");
            return (Criteria) this;
        }

        public Criteria andSettlementCustNoGreaterThan(Long value) {
            addCriterion("settlement_cust_no >", value, "settlementCustNo");
            return (Criteria) this;
        }

        public Criteria andSettlementCustNoGreaterThanOrEqualTo(Long value) {
            addCriterion("settlement_cust_no >=", value, "settlementCustNo");
            return (Criteria) this;
        }

        public Criteria andSettlementCustNoLessThan(Long value) {
            addCriterion("settlement_cust_no <", value, "settlementCustNo");
            return (Criteria) this;
        }

        public Criteria andSettlementCustNoLessThanOrEqualTo(Long value) {
            addCriterion("settlement_cust_no <=", value, "settlementCustNo");
            return (Criteria) this;
        }

        public Criteria andSettlementCustNoIn(List<Long> values) {
            addCriterion("settlement_cust_no in", values, "settlementCustNo");
            return (Criteria) this;
        }

        public Criteria andSettlementCustNoNotIn(List<Long> values) {
            addCriterion("settlement_cust_no not in", values, "settlementCustNo");
            return (Criteria) this;
        }

        public Criteria andSettlementCustNoBetween(Long value1, Long value2) {
            addCriterion("settlement_cust_no between", value1, value2, "settlementCustNo");
            return (Criteria) this;
        }

        public Criteria andSettlementCustNoNotBetween(Long value1, Long value2) {
            addCriterion("settlement_cust_no not between", value1, value2, "settlementCustNo");
            return (Criteria) this;
        }

        public Criteria andClientTypeIsNull() {
            addCriterion("client_type is null");
            return (Criteria) this;
        }

        public Criteria andClientTypeIsNotNull() {
            addCriterion("client_type is not null");
            return (Criteria) this;
        }

        public Criteria andClientTypeEqualTo(Byte value) {
            addCriterion("client_type =", value, "clientType");
            return (Criteria) this;
        }

        public Criteria andClientTypeNotEqualTo(Byte value) {
            addCriterion("client_type <>", value, "clientType");
            return (Criteria) this;
        }

        public Criteria andClientTypeGreaterThan(Byte value) {
            addCriterion("client_type >", value, "clientType");
            return (Criteria) this;
        }

        public Criteria andClientTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("client_type >=", value, "clientType");
            return (Criteria) this;
        }

        public Criteria andClientTypeLessThan(Byte value) {
            addCriterion("client_type <", value, "clientType");
            return (Criteria) this;
        }

        public Criteria andClientTypeLessThanOrEqualTo(Byte value) {
            addCriterion("client_type <=", value, "clientType");
            return (Criteria) this;
        }

        public Criteria andClientTypeIn(List<Byte> values) {
            addCriterion("client_type in", values, "clientType");
            return (Criteria) this;
        }

        public Criteria andClientTypeNotIn(List<Byte> values) {
            addCriterion("client_type not in", values, "clientType");
            return (Criteria) this;
        }

        public Criteria andClientTypeBetween(Byte value1, Byte value2) {
            addCriterion("client_type between", value1, value2, "clientType");
            return (Criteria) this;
        }

        public Criteria andClientTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("client_type not between", value1, value2, "clientType");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeIsNull() {
            addCriterion("legal_representative is null");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeIsNotNull() {
            addCriterion("legal_representative is not null");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeEqualTo(String value) {
            addCriterion("legal_representative =", value, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeNotEqualTo(String value) {
            addCriterion("legal_representative <>", value, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeGreaterThan(String value) {
            addCriterion("legal_representative >", value, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeGreaterThanOrEqualTo(String value) {
            addCriterion("legal_representative >=", value, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeLessThan(String value) {
            addCriterion("legal_representative <", value, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeLessThanOrEqualTo(String value) {
            addCriterion("legal_representative <=", value, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeLike(String value) {
            addCriterion("legal_representative like", value, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeNotLike(String value) {
            addCriterion("legal_representative not like", value, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeIn(List<String> values) {
            addCriterion("legal_representative in", values, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeNotIn(List<String> values) {
            addCriterion("legal_representative not in", values, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeBetween(String value1, String value2) {
            addCriterion("legal_representative between", value1, value2, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeNotBetween(String value1, String value2) {
            addCriterion("legal_representative not between", value1, value2, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andUniformSocialCreditCodeIsNull() {
            addCriterion("uniform_social_credit_code is null");
            return (Criteria) this;
        }

        public Criteria andUniformSocialCreditCodeIsNotNull() {
            addCriterion("uniform_social_credit_code is not null");
            return (Criteria) this;
        }

        public Criteria andUniformSocialCreditCodeEqualTo(String value) {
            addCriterion("uniform_social_credit_code =", value, "uniformSocialCreditCode");
            return (Criteria) this;
        }

        public Criteria andUniformSocialCreditCodeNotEqualTo(String value) {
            addCriterion("uniform_social_credit_code <>", value, "uniformSocialCreditCode");
            return (Criteria) this;
        }

        public Criteria andUniformSocialCreditCodeGreaterThan(String value) {
            addCriterion("uniform_social_credit_code >", value, "uniformSocialCreditCode");
            return (Criteria) this;
        }

        public Criteria andUniformSocialCreditCodeGreaterThanOrEqualTo(String value) {
            addCriterion("uniform_social_credit_code >=", value, "uniformSocialCreditCode");
            return (Criteria) this;
        }

        public Criteria andUniformSocialCreditCodeLessThan(String value) {
            addCriterion("uniform_social_credit_code <", value, "uniformSocialCreditCode");
            return (Criteria) this;
        }

        public Criteria andUniformSocialCreditCodeLessThanOrEqualTo(String value) {
            addCriterion("uniform_social_credit_code <=", value, "uniformSocialCreditCode");
            return (Criteria) this;
        }

        public Criteria andUniformSocialCreditCodeLike(String value) {
            addCriterion("uniform_social_credit_code like", value, "uniformSocialCreditCode");
            return (Criteria) this;
        }

        public Criteria andUniformSocialCreditCodeNotLike(String value) {
            addCriterion("uniform_social_credit_code not like", value, "uniformSocialCreditCode");
            return (Criteria) this;
        }

        public Criteria andUniformSocialCreditCodeIn(List<String> values) {
            addCriterion("uniform_social_credit_code in", values, "uniformSocialCreditCode");
            return (Criteria) this;
        }

        public Criteria andUniformSocialCreditCodeNotIn(List<String> values) {
            addCriterion("uniform_social_credit_code not in", values, "uniformSocialCreditCode");
            return (Criteria) this;
        }

        public Criteria andUniformSocialCreditCodeBetween(String value1, String value2) {
            addCriterion("uniform_social_credit_code between", value1, value2, "uniformSocialCreditCode");
            return (Criteria) this;
        }

        public Criteria andUniformSocialCreditCodeNotBetween(String value1, String value2) {
            addCriterion("uniform_social_credit_code not between", value1, value2, "uniformSocialCreditCode");
            return (Criteria) this;
        }

        public Criteria andIdNumberIsNull() {
            addCriterion("id_number is null");
            return (Criteria) this;
        }

        public Criteria andIdNumberIsNotNull() {
            addCriterion("id_number is not null");
            return (Criteria) this;
        }

        public Criteria andIdNumberEqualTo(String value) {
            addCriterion("id_number =", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberNotEqualTo(String value) {
            addCriterion("id_number <>", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberGreaterThan(String value) {
            addCriterion("id_number >", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberGreaterThanOrEqualTo(String value) {
            addCriterion("id_number >=", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberLessThan(String value) {
            addCriterion("id_number <", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberLessThanOrEqualTo(String value) {
            addCriterion("id_number <=", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberLike(String value) {
            addCriterion("id_number like", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberNotLike(String value) {
            addCriterion("id_number not like", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberIn(List<String> values) {
            addCriterion("id_number in", values, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberNotIn(List<String> values) {
            addCriterion("id_number not in", values, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberBetween(String value1, String value2) {
            addCriterion("id_number between", value1, value2, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberNotBetween(String value1, String value2) {
            addCriterion("id_number not between", value1, value2, "idNumber");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNull() {
            addCriterion("province is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNotNull() {
            addCriterion("province is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceEqualTo(String value) {
            addCriterion("province =", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotEqualTo(String value) {
            addCriterion("province <>", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThan(String value) {
            addCriterion("province >", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("province >=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThan(String value) {
            addCriterion("province <", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThanOrEqualTo(String value) {
            addCriterion("province <=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLike(String value) {
            addCriterion("province like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotLike(String value) {
            addCriterion("province not like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceIn(List<String> values) {
            addCriterion("province in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotIn(List<String> values) {
            addCriterion("province not in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceBetween(String value1, String value2) {
            addCriterion("province between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotBetween(String value1, String value2) {
            addCriterion("province not between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("city is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("city is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(String value) {
            addCriterion("city =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(String value) {
            addCriterion("city <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(String value) {
            addCriterion("city >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(String value) {
            addCriterion("city >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(String value) {
            addCriterion("city <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(String value) {
            addCriterion("city <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLike(String value) {
            addCriterion("city like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotLike(String value) {
            addCriterion("city not like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<String> values) {
            addCriterion("city in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<String> values) {
            addCriterion("city not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(String value1, String value2) {
            addCriterion("city between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(String value1, String value2) {
            addCriterion("city not between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andZoneIsNull() {
            addCriterion("zone is null");
            return (Criteria) this;
        }

        public Criteria andZoneIsNotNull() {
            addCriterion("zone is not null");
            return (Criteria) this;
        }

        public Criteria andZoneEqualTo(String value) {
            addCriterion("zone =", value, "zone");
            return (Criteria) this;
        }

        public Criteria andZoneNotEqualTo(String value) {
            addCriterion("zone <>", value, "zone");
            return (Criteria) this;
        }

        public Criteria andZoneGreaterThan(String value) {
            addCriterion("zone >", value, "zone");
            return (Criteria) this;
        }

        public Criteria andZoneGreaterThanOrEqualTo(String value) {
            addCriterion("zone >=", value, "zone");
            return (Criteria) this;
        }

        public Criteria andZoneLessThan(String value) {
            addCriterion("zone <", value, "zone");
            return (Criteria) this;
        }

        public Criteria andZoneLessThanOrEqualTo(String value) {
            addCriterion("zone <=", value, "zone");
            return (Criteria) this;
        }

        public Criteria andZoneLike(String value) {
            addCriterion("zone like", value, "zone");
            return (Criteria) this;
        }

        public Criteria andZoneNotLike(String value) {
            addCriterion("zone not like", value, "zone");
            return (Criteria) this;
        }

        public Criteria andZoneIn(List<String> values) {
            addCriterion("zone in", values, "zone");
            return (Criteria) this;
        }

        public Criteria andZoneNotIn(List<String> values) {
            addCriterion("zone not in", values, "zone");
            return (Criteria) this;
        }

        public Criteria andZoneBetween(String value1, String value2) {
            addCriterion("zone between", value1, value2, "zone");
            return (Criteria) this;
        }

        public Criteria andZoneNotBetween(String value1, String value2) {
            addCriterion("zone not between", value1, value2, "zone");
            return (Criteria) this;
        }

        public Criteria andWithdrawScaleIsNull() {
            addCriterion("withdraw_scale is null");
            return (Criteria) this;
        }

        public Criteria andWithdrawScaleIsNotNull() {
            addCriterion("withdraw_scale is not null");
            return (Criteria) this;
        }

        public Criteria andWithdrawScaleEqualTo(Float value) {
            addCriterion("withdraw_scale =", value, "withdrawScale");
            return (Criteria) this;
        }

        public Criteria andWithdrawScaleNotEqualTo(Float value) {
            addCriterion("withdraw_scale <>", value, "withdrawScale");
            return (Criteria) this;
        }

        public Criteria andWithdrawScaleGreaterThan(Float value) {
            addCriterion("withdraw_scale >", value, "withdrawScale");
            return (Criteria) this;
        }

        public Criteria andWithdrawScaleGreaterThanOrEqualTo(Float value) {
            addCriterion("withdraw_scale >=", value, "withdrawScale");
            return (Criteria) this;
        }

        public Criteria andWithdrawScaleLessThan(Float value) {
            addCriterion("withdraw_scale <", value, "withdrawScale");
            return (Criteria) this;
        }

        public Criteria andWithdrawScaleLessThanOrEqualTo(Float value) {
            addCriterion("withdraw_scale <=", value, "withdrawScale");
            return (Criteria) this;
        }

        public Criteria andWithdrawScaleIn(List<Float> values) {
            addCriterion("withdraw_scale in", values, "withdrawScale");
            return (Criteria) this;
        }

        public Criteria andWithdrawScaleNotIn(List<Float> values) {
            addCriterion("withdraw_scale not in", values, "withdrawScale");
            return (Criteria) this;
        }

        public Criteria andWithdrawScaleBetween(Float value1, Float value2) {
            addCriterion("withdraw_scale between", value1, value2, "withdrawScale");
            return (Criteria) this;
        }

        public Criteria andWithdrawScaleNotBetween(Float value1, Float value2) {
            addCriterion("withdraw_scale not between", value1, value2, "withdrawScale");
            return (Criteria) this;
        }

        public Criteria andTradeAmountShowFlagIsNull() {
            addCriterion("trade_amount_show_flag is null");
            return (Criteria) this;
        }

        public Criteria andTradeAmountShowFlagIsNotNull() {
            addCriterion("trade_amount_show_flag is not null");
            return (Criteria) this;
        }

        public Criteria andTradeAmountShowFlagEqualTo(Byte value) {
            addCriterion("trade_amount_show_flag =", value, "tradeAmountShowFlag");
            return (Criteria) this;
        }

        public Criteria andTradeAmountShowFlagNotEqualTo(Byte value) {
            addCriterion("trade_amount_show_flag <>", value, "tradeAmountShowFlag");
            return (Criteria) this;
        }

        public Criteria andTradeAmountShowFlagGreaterThan(Byte value) {
            addCriterion("trade_amount_show_flag >", value, "tradeAmountShowFlag");
            return (Criteria) this;
        }

        public Criteria andTradeAmountShowFlagGreaterThanOrEqualTo(Byte value) {
            addCriterion("trade_amount_show_flag >=", value, "tradeAmountShowFlag");
            return (Criteria) this;
        }

        public Criteria andTradeAmountShowFlagLessThan(Byte value) {
            addCriterion("trade_amount_show_flag <", value, "tradeAmountShowFlag");
            return (Criteria) this;
        }

        public Criteria andTradeAmountShowFlagLessThanOrEqualTo(Byte value) {
            addCriterion("trade_amount_show_flag <=", value, "tradeAmountShowFlag");
            return (Criteria) this;
        }

        public Criteria andTradeAmountShowFlagIn(List<Byte> values) {
            addCriterion("trade_amount_show_flag in", values, "tradeAmountShowFlag");
            return (Criteria) this;
        }

        public Criteria andTradeAmountShowFlagNotIn(List<Byte> values) {
            addCriterion("trade_amount_show_flag not in", values, "tradeAmountShowFlag");
            return (Criteria) this;
        }

        public Criteria andTradeAmountShowFlagBetween(Byte value1, Byte value2) {
            addCriterion("trade_amount_show_flag between", value1, value2, "tradeAmountShowFlag");
            return (Criteria) this;
        }

        public Criteria andTradeAmountShowFlagNotBetween(Byte value1, Byte value2) {
            addCriterion("trade_amount_show_flag not between", value1, value2, "tradeAmountShowFlag");
            return (Criteria) this;
        }

        public Criteria andShareQrCodePathIsNull() {
            addCriterion("share_qr_code_path is null");
            return (Criteria) this;
        }

        public Criteria andShareQrCodePathIsNotNull() {
            addCriterion("share_qr_code_path is not null");
            return (Criteria) this;
        }

        public Criteria andShareQrCodePathEqualTo(String value) {
            addCriterion("share_qr_code_path =", value, "shareQrCodePath");
            return (Criteria) this;
        }

        public Criteria andShareQrCodePathNotEqualTo(String value) {
            addCriterion("share_qr_code_path <>", value, "shareQrCodePath");
            return (Criteria) this;
        }

        public Criteria andShareQrCodePathGreaterThan(String value) {
            addCriterion("share_qr_code_path >", value, "shareQrCodePath");
            return (Criteria) this;
        }

        public Criteria andShareQrCodePathGreaterThanOrEqualTo(String value) {
            addCriterion("share_qr_code_path >=", value, "shareQrCodePath");
            return (Criteria) this;
        }

        public Criteria andShareQrCodePathLessThan(String value) {
            addCriterion("share_qr_code_path <", value, "shareQrCodePath");
            return (Criteria) this;
        }

        public Criteria andShareQrCodePathLessThanOrEqualTo(String value) {
            addCriterion("share_qr_code_path <=", value, "shareQrCodePath");
            return (Criteria) this;
        }

        public Criteria andShareQrCodePathLike(String value) {
            addCriterion("share_qr_code_path like", value, "shareQrCodePath");
            return (Criteria) this;
        }

        public Criteria andShareQrCodePathNotLike(String value) {
            addCriterion("share_qr_code_path not like", value, "shareQrCodePath");
            return (Criteria) this;
        }

        public Criteria andShareQrCodePathIn(List<String> values) {
            addCriterion("share_qr_code_path in", values, "shareQrCodePath");
            return (Criteria) this;
        }

        public Criteria andShareQrCodePathNotIn(List<String> values) {
            addCriterion("share_qr_code_path not in", values, "shareQrCodePath");
            return (Criteria) this;
        }

        public Criteria andShareQrCodePathBetween(String value1, String value2) {
            addCriterion("share_qr_code_path between", value1, value2, "shareQrCodePath");
            return (Criteria) this;
        }

        public Criteria andShareQrCodePathNotBetween(String value1, String value2) {
            addCriterion("share_qr_code_path not between", value1, value2, "shareQrCodePath");
            return (Criteria) this;
        }

        public Criteria andReceiverIsNull() {
            addCriterion("receiver is null");
            return (Criteria) this;
        }

        public Criteria andReceiverIsNotNull() {
            addCriterion("receiver is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverEqualTo(String value) {
            addCriterion("receiver =", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotEqualTo(String value) {
            addCriterion("receiver <>", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverGreaterThan(String value) {
            addCriterion("receiver >", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverGreaterThanOrEqualTo(String value) {
            addCriterion("receiver >=", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverLessThan(String value) {
            addCriterion("receiver <", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverLessThanOrEqualTo(String value) {
            addCriterion("receiver <=", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverLike(String value) {
            addCriterion("receiver like", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotLike(String value) {
            addCriterion("receiver not like", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverIn(List<String> values) {
            addCriterion("receiver in", values, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotIn(List<String> values) {
            addCriterion("receiver not in", values, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverBetween(String value1, String value2) {
            addCriterion("receiver between", value1, value2, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotBetween(String value1, String value2) {
            addCriterion("receiver not between", value1, value2, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverTelIsNull() {
            addCriterion("receiver_tel is null");
            return (Criteria) this;
        }

        public Criteria andReceiverTelIsNotNull() {
            addCriterion("receiver_tel is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverTelEqualTo(String value) {
            addCriterion("receiver_tel =", value, "receiverTel");
            return (Criteria) this;
        }

        public Criteria andReceiverTelNotEqualTo(String value) {
            addCriterion("receiver_tel <>", value, "receiverTel");
            return (Criteria) this;
        }

        public Criteria andReceiverTelGreaterThan(String value) {
            addCriterion("receiver_tel >", value, "receiverTel");
            return (Criteria) this;
        }

        public Criteria andReceiverTelGreaterThanOrEqualTo(String value) {
            addCriterion("receiver_tel >=", value, "receiverTel");
            return (Criteria) this;
        }

        public Criteria andReceiverTelLessThan(String value) {
            addCriterion("receiver_tel <", value, "receiverTel");
            return (Criteria) this;
        }

        public Criteria andReceiverTelLessThanOrEqualTo(String value) {
            addCriterion("receiver_tel <=", value, "receiverTel");
            return (Criteria) this;
        }

        public Criteria andReceiverTelLike(String value) {
            addCriterion("receiver_tel like", value, "receiverTel");
            return (Criteria) this;
        }

        public Criteria andReceiverTelNotLike(String value) {
            addCriterion("receiver_tel not like", value, "receiverTel");
            return (Criteria) this;
        }

        public Criteria andReceiverTelIn(List<String> values) {
            addCriterion("receiver_tel in", values, "receiverTel");
            return (Criteria) this;
        }

        public Criteria andReceiverTelNotIn(List<String> values) {
            addCriterion("receiver_tel not in", values, "receiverTel");
            return (Criteria) this;
        }

        public Criteria andReceiverTelBetween(String value1, String value2) {
            addCriterion("receiver_tel between", value1, value2, "receiverTel");
            return (Criteria) this;
        }

        public Criteria andReceiverTelNotBetween(String value1, String value2) {
            addCriterion("receiver_tel not between", value1, value2, "receiverTel");
            return (Criteria) this;
        }

        public Criteria andReceiverAddrIsNull() {
            addCriterion("receiver_addr is null");
            return (Criteria) this;
        }

        public Criteria andReceiverAddrIsNotNull() {
            addCriterion("receiver_addr is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverAddrEqualTo(String value) {
            addCriterion("receiver_addr =", value, "receiverAddr");
            return (Criteria) this;
        }

        public Criteria andReceiverAddrNotEqualTo(String value) {
            addCriterion("receiver_addr <>", value, "receiverAddr");
            return (Criteria) this;
        }

        public Criteria andReceiverAddrGreaterThan(String value) {
            addCriterion("receiver_addr >", value, "receiverAddr");
            return (Criteria) this;
        }

        public Criteria andReceiverAddrGreaterThanOrEqualTo(String value) {
            addCriterion("receiver_addr >=", value, "receiverAddr");
            return (Criteria) this;
        }

        public Criteria andReceiverAddrLessThan(String value) {
            addCriterion("receiver_addr <", value, "receiverAddr");
            return (Criteria) this;
        }

        public Criteria andReceiverAddrLessThanOrEqualTo(String value) {
            addCriterion("receiver_addr <=", value, "receiverAddr");
            return (Criteria) this;
        }

        public Criteria andReceiverAddrLike(String value) {
            addCriterion("receiver_addr like", value, "receiverAddr");
            return (Criteria) this;
        }

        public Criteria andReceiverAddrNotLike(String value) {
            addCriterion("receiver_addr not like", value, "receiverAddr");
            return (Criteria) this;
        }

        public Criteria andReceiverAddrIn(List<String> values) {
            addCriterion("receiver_addr in", values, "receiverAddr");
            return (Criteria) this;
        }

        public Criteria andReceiverAddrNotIn(List<String> values) {
            addCriterion("receiver_addr not in", values, "receiverAddr");
            return (Criteria) this;
        }

        public Criteria andReceiverAddrBetween(String value1, String value2) {
            addCriterion("receiver_addr between", value1, value2, "receiverAddr");
            return (Criteria) this;
        }

        public Criteria andReceiverAddrNotBetween(String value1, String value2) {
            addCriterion("receiver_addr not between", value1, value2, "receiverAddr");
            return (Criteria) this;
        }

        public Criteria andCurrentRoundNumIsNull() {
            addCriterion("current_round_num is null");
            return (Criteria) this;
        }

        public Criteria andCurrentRoundNumIsNotNull() {
            addCriterion("current_round_num is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentRoundNumEqualTo(Integer value) {
            addCriterion("current_round_num =", value, "currentRoundNum");
            return (Criteria) this;
        }

        public Criteria andCurrentRoundNumNotEqualTo(Integer value) {
            addCriterion("current_round_num <>", value, "currentRoundNum");
            return (Criteria) this;
        }

        public Criteria andCurrentRoundNumGreaterThan(Integer value) {
            addCriterion("current_round_num >", value, "currentRoundNum");
            return (Criteria) this;
        }

        public Criteria andCurrentRoundNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("current_round_num >=", value, "currentRoundNum");
            return (Criteria) this;
        }

        public Criteria andCurrentRoundNumLessThan(Integer value) {
            addCriterion("current_round_num <", value, "currentRoundNum");
            return (Criteria) this;
        }

        public Criteria andCurrentRoundNumLessThanOrEqualTo(Integer value) {
            addCriterion("current_round_num <=", value, "currentRoundNum");
            return (Criteria) this;
        }

        public Criteria andCurrentRoundNumIn(List<Integer> values) {
            addCriterion("current_round_num in", values, "currentRoundNum");
            return (Criteria) this;
        }

        public Criteria andCurrentRoundNumNotIn(List<Integer> values) {
            addCriterion("current_round_num not in", values, "currentRoundNum");
            return (Criteria) this;
        }

        public Criteria andCurrentRoundNumBetween(Integer value1, Integer value2) {
            addCriterion("current_round_num between", value1, value2, "currentRoundNum");
            return (Criteria) this;
        }

        public Criteria andCurrentRoundNumNotBetween(Integer value1, Integer value2) {
            addCriterion("current_round_num not between", value1, value2, "currentRoundNum");
            return (Criteria) this;
        }

        public Criteria andTotalRoundNumIsNull() {
            addCriterion("total_round_num is null");
            return (Criteria) this;
        }

        public Criteria andTotalRoundNumIsNotNull() {
            addCriterion("total_round_num is not null");
            return (Criteria) this;
        }

        public Criteria andTotalRoundNumEqualTo(Integer value) {
            addCriterion("total_round_num =", value, "totalRoundNum");
            return (Criteria) this;
        }

        public Criteria andTotalRoundNumNotEqualTo(Integer value) {
            addCriterion("total_round_num <>", value, "totalRoundNum");
            return (Criteria) this;
        }

        public Criteria andTotalRoundNumGreaterThan(Integer value) {
            addCriterion("total_round_num >", value, "totalRoundNum");
            return (Criteria) this;
        }

        public Criteria andTotalRoundNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_round_num >=", value, "totalRoundNum");
            return (Criteria) this;
        }

        public Criteria andTotalRoundNumLessThan(Integer value) {
            addCriterion("total_round_num <", value, "totalRoundNum");
            return (Criteria) this;
        }

        public Criteria andTotalRoundNumLessThanOrEqualTo(Integer value) {
            addCriterion("total_round_num <=", value, "totalRoundNum");
            return (Criteria) this;
        }

        public Criteria andTotalRoundNumIn(List<Integer> values) {
            addCriterion("total_round_num in", values, "totalRoundNum");
            return (Criteria) this;
        }

        public Criteria andTotalRoundNumNotIn(List<Integer> values) {
            addCriterion("total_round_num not in", values, "totalRoundNum");
            return (Criteria) this;
        }

        public Criteria andTotalRoundNumBetween(Integer value1, Integer value2) {
            addCriterion("total_round_num between", value1, value2, "totalRoundNum");
            return (Criteria) this;
        }

        public Criteria andTotalRoundNumNotBetween(Integer value1, Integer value2) {
            addCriterion("total_round_num not between", value1, value2, "totalRoundNum");
            return (Criteria) this;
        }

        public Criteria andIdNameIsNull() {
            addCriterion("id_name is null");
            return (Criteria) this;
        }

        public Criteria andIdNameIsNotNull() {
            addCriterion("id_name is not null");
            return (Criteria) this;
        }

        public Criteria andIdNameEqualTo(String value) {
            addCriterion("id_name =", value, "idName");
            return (Criteria) this;
        }

        public Criteria andIdNameNotEqualTo(String value) {
            addCriterion("id_name <>", value, "idName");
            return (Criteria) this;
        }

        public Criteria andIdNameGreaterThan(String value) {
            addCriterion("id_name >", value, "idName");
            return (Criteria) this;
        }

        public Criteria andIdNameGreaterThanOrEqualTo(String value) {
            addCriterion("id_name >=", value, "idName");
            return (Criteria) this;
        }

        public Criteria andIdNameLessThan(String value) {
            addCriterion("id_name <", value, "idName");
            return (Criteria) this;
        }

        public Criteria andIdNameLessThanOrEqualTo(String value) {
            addCriterion("id_name <=", value, "idName");
            return (Criteria) this;
        }

        public Criteria andIdNameLike(String value) {
            addCriterion("id_name like", value, "idName");
            return (Criteria) this;
        }

        public Criteria andIdNameNotLike(String value) {
            addCriterion("id_name not like", value, "idName");
            return (Criteria) this;
        }

        public Criteria andIdNameIn(List<String> values) {
            addCriterion("id_name in", values, "idName");
            return (Criteria) this;
        }

        public Criteria andIdNameNotIn(List<String> values) {
            addCriterion("id_name not in", values, "idName");
            return (Criteria) this;
        }

        public Criteria andIdNameBetween(String value1, String value2) {
            addCriterion("id_name between", value1, value2, "idName");
            return (Criteria) this;
        }

        public Criteria andIdNameNotBetween(String value1, String value2) {
            addCriterion("id_name not between", value1, value2, "idName");
            return (Criteria) this;
        }

        public Criteria andAdvanceProfitFlagIsNull() {
            addCriterion("advance_profit_flag is null");
            return (Criteria) this;
        }

        public Criteria andAdvanceProfitFlagIsNotNull() {
            addCriterion("advance_profit_flag is not null");
            return (Criteria) this;
        }

        public Criteria andAdvanceProfitFlagEqualTo(Byte value) {
            addCriterion("advance_profit_flag =", value, "advanceProfitFlag");
            return (Criteria) this;
        }

        public Criteria andAdvanceProfitFlagNotEqualTo(Byte value) {
            addCriterion("advance_profit_flag <>", value, "advanceProfitFlag");
            return (Criteria) this;
        }

        public Criteria andAdvanceProfitFlagGreaterThan(Byte value) {
            addCriterion("advance_profit_flag >", value, "advanceProfitFlag");
            return (Criteria) this;
        }

        public Criteria andAdvanceProfitFlagGreaterThanOrEqualTo(Byte value) {
            addCriterion("advance_profit_flag >=", value, "advanceProfitFlag");
            return (Criteria) this;
        }

        public Criteria andAdvanceProfitFlagLessThan(Byte value) {
            addCriterion("advance_profit_flag <", value, "advanceProfitFlag");
            return (Criteria) this;
        }

        public Criteria andAdvanceProfitFlagLessThanOrEqualTo(Byte value) {
            addCriterion("advance_profit_flag <=", value, "advanceProfitFlag");
            return (Criteria) this;
        }

        public Criteria andAdvanceProfitFlagIn(List<Byte> values) {
            addCriterion("advance_profit_flag in", values, "advanceProfitFlag");
            return (Criteria) this;
        }

        public Criteria andAdvanceProfitFlagNotIn(List<Byte> values) {
            addCriterion("advance_profit_flag not in", values, "advanceProfitFlag");
            return (Criteria) this;
        }

        public Criteria andAdvanceProfitFlagBetween(Byte value1, Byte value2) {
            addCriterion("advance_profit_flag between", value1, value2, "advanceProfitFlag");
            return (Criteria) this;
        }

        public Criteria andAdvanceProfitFlagNotBetween(Byte value1, Byte value2) {
            addCriterion("advance_profit_flag not between", value1, value2, "advanceProfitFlag");
            return (Criteria) this;
        }

        public Criteria andTecFeeAccumulateIsNull() {
            addCriterion("tec_fee_accumulate is null");
            return (Criteria) this;
        }

        public Criteria andTecFeeAccumulateIsNotNull() {
            addCriterion("tec_fee_accumulate is not null");
            return (Criteria) this;
        }

        public Criteria andTecFeeAccumulateEqualTo(BigDecimal value) {
            addCriterion("tec_fee_accumulate =", value, "tecFeeAccumulate");
            return (Criteria) this;
        }

        public Criteria andTecFeeAccumulateNotEqualTo(BigDecimal value) {
            addCriterion("tec_fee_accumulate <>", value, "tecFeeAccumulate");
            return (Criteria) this;
        }

        public Criteria andTecFeeAccumulateGreaterThan(BigDecimal value) {
            addCriterion("tec_fee_accumulate >", value, "tecFeeAccumulate");
            return (Criteria) this;
        }

        public Criteria andTecFeeAccumulateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("tec_fee_accumulate >=", value, "tecFeeAccumulate");
            return (Criteria) this;
        }

        public Criteria andTecFeeAccumulateLessThan(BigDecimal value) {
            addCriterion("tec_fee_accumulate <", value, "tecFeeAccumulate");
            return (Criteria) this;
        }

        public Criteria andTecFeeAccumulateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("tec_fee_accumulate <=", value, "tecFeeAccumulate");
            return (Criteria) this;
        }

        public Criteria andTecFeeAccumulateIn(List<BigDecimal> values) {
            addCriterion("tec_fee_accumulate in", values, "tecFeeAccumulate");
            return (Criteria) this;
        }

        public Criteria andTecFeeAccumulateNotIn(List<BigDecimal> values) {
            addCriterion("tec_fee_accumulate not in", values, "tecFeeAccumulate");
            return (Criteria) this;
        }

        public Criteria andTecFeeAccumulateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("tec_fee_accumulate between", value1, value2, "tecFeeAccumulate");
            return (Criteria) this;
        }

        public Criteria andTecFeeAccumulateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("tec_fee_accumulate not between", value1, value2, "tecFeeAccumulate");
            return (Criteria) this;
        }

        public Criteria andMerchantSubTypeIsNull() {
            addCriterion("merchant_sub_type is null");
            return (Criteria) this;
        }

        public Criteria andMerchantSubTypeIsNotNull() {
            addCriterion("merchant_sub_type is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantSubTypeEqualTo(Byte value) {
            addCriterion("merchant_sub_type =", value, "merchantSubType");
            return (Criteria) this;
        }

        public Criteria andMerchantSubTypeNotEqualTo(Byte value) {
            addCriterion("merchant_sub_type <>", value, "merchantSubType");
            return (Criteria) this;
        }

        public Criteria andMerchantSubTypeGreaterThan(Byte value) {
            addCriterion("merchant_sub_type >", value, "merchantSubType");
            return (Criteria) this;
        }

        public Criteria andMerchantSubTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("merchant_sub_type >=", value, "merchantSubType");
            return (Criteria) this;
        }

        public Criteria andMerchantSubTypeLessThan(Byte value) {
            addCriterion("merchant_sub_type <", value, "merchantSubType");
            return (Criteria) this;
        }

        public Criteria andMerchantSubTypeLessThanOrEqualTo(Byte value) {
            addCriterion("merchant_sub_type <=", value, "merchantSubType");
            return (Criteria) this;
        }

        public Criteria andMerchantSubTypeIn(List<Byte> values) {
            addCriterion("merchant_sub_type in", values, "merchantSubType");
            return (Criteria) this;
        }

        public Criteria andMerchantSubTypeNotIn(List<Byte> values) {
            addCriterion("merchant_sub_type not in", values, "merchantSubType");
            return (Criteria) this;
        }

        public Criteria andMerchantSubTypeBetween(Byte value1, Byte value2) {
            addCriterion("merchant_sub_type between", value1, value2, "merchantSubType");
            return (Criteria) this;
        }

        public Criteria andMerchantSubTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("merchant_sub_type not between", value1, value2, "merchantSubType");
            return (Criteria) this;
        }

        public Criteria andMerchantSubTypeCnIsNull() {
            addCriterion("merchant_sub_type_cn is null");
            return (Criteria) this;
        }

        public Criteria andMerchantSubTypeCnIsNotNull() {
            addCriterion("merchant_sub_type_cn is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantSubTypeCnEqualTo(String value) {
            addCriterion("merchant_sub_type_cn =", value, "merchantSubTypeCn");
            return (Criteria) this;
        }

        public Criteria andMerchantSubTypeCnNotEqualTo(String value) {
            addCriterion("merchant_sub_type_cn <>", value, "merchantSubTypeCn");
            return (Criteria) this;
        }

        public Criteria andMerchantSubTypeCnGreaterThan(String value) {
            addCriterion("merchant_sub_type_cn >", value, "merchantSubTypeCn");
            return (Criteria) this;
        }

        public Criteria andMerchantSubTypeCnGreaterThanOrEqualTo(String value) {
            addCriterion("merchant_sub_type_cn >=", value, "merchantSubTypeCn");
            return (Criteria) this;
        }

        public Criteria andMerchantSubTypeCnLessThan(String value) {
            addCriterion("merchant_sub_type_cn <", value, "merchantSubTypeCn");
            return (Criteria) this;
        }

        public Criteria andMerchantSubTypeCnLessThanOrEqualTo(String value) {
            addCriterion("merchant_sub_type_cn <=", value, "merchantSubTypeCn");
            return (Criteria) this;
        }

        public Criteria andMerchantSubTypeCnLike(String value) {
            addCriterion("merchant_sub_type_cn like", value, "merchantSubTypeCn");
            return (Criteria) this;
        }

        public Criteria andMerchantSubTypeCnNotLike(String value) {
            addCriterion("merchant_sub_type_cn not like", value, "merchantSubTypeCn");
            return (Criteria) this;
        }

        public Criteria andMerchantSubTypeCnIn(List<String> values) {
            addCriterion("merchant_sub_type_cn in", values, "merchantSubTypeCn");
            return (Criteria) this;
        }

        public Criteria andMerchantSubTypeCnNotIn(List<String> values) {
            addCriterion("merchant_sub_type_cn not in", values, "merchantSubTypeCn");
            return (Criteria) this;
        }

        public Criteria andMerchantSubTypeCnBetween(String value1, String value2) {
            addCriterion("merchant_sub_type_cn between", value1, value2, "merchantSubTypeCn");
            return (Criteria) this;
        }

        public Criteria andMerchantSubTypeCnNotBetween(String value1, String value2) {
            addCriterion("merchant_sub_type_cn not between", value1, value2, "merchantSubTypeCn");
            return (Criteria) this;
        }

        public Criteria andStartShopTimeIsNull() {
            addCriterion("start_shop_time is null");
            return (Criteria) this;
        }

        public Criteria andStartShopTimeIsNotNull() {
            addCriterion("start_shop_time is not null");
            return (Criteria) this;
        }

        public Criteria andStartShopTimeEqualTo(String value) {
            addCriterion("start_shop_time =", value, "startShopTime");
            return (Criteria) this;
        }

        public Criteria andStartShopTimeNotEqualTo(String value) {
            addCriterion("start_shop_time <>", value, "startShopTime");
            return (Criteria) this;
        }

        public Criteria andStartShopTimeGreaterThan(String value) {
            addCriterion("start_shop_time >", value, "startShopTime");
            return (Criteria) this;
        }

        public Criteria andStartShopTimeGreaterThanOrEqualTo(String value) {
            addCriterion("start_shop_time >=", value, "startShopTime");
            return (Criteria) this;
        }

        public Criteria andStartShopTimeLessThan(String value) {
            addCriterion("start_shop_time <", value, "startShopTime");
            return (Criteria) this;
        }

        public Criteria andStartShopTimeLessThanOrEqualTo(String value) {
            addCriterion("start_shop_time <=", value, "startShopTime");
            return (Criteria) this;
        }

        public Criteria andStartShopTimeLike(String value) {
            addCriterion("start_shop_time like", value, "startShopTime");
            return (Criteria) this;
        }

        public Criteria andStartShopTimeNotLike(String value) {
            addCriterion("start_shop_time not like", value, "startShopTime");
            return (Criteria) this;
        }

        public Criteria andStartShopTimeIn(List<String> values) {
            addCriterion("start_shop_time in", values, "startShopTime");
            return (Criteria) this;
        }

        public Criteria andStartShopTimeNotIn(List<String> values) {
            addCriterion("start_shop_time not in", values, "startShopTime");
            return (Criteria) this;
        }

        public Criteria andStartShopTimeBetween(String value1, String value2) {
            addCriterion("start_shop_time between", value1, value2, "startShopTime");
            return (Criteria) this;
        }

        public Criteria andStartShopTimeNotBetween(String value1, String value2) {
            addCriterion("start_shop_time not between", value1, value2, "startShopTime");
            return (Criteria) this;
        }

        public Criteria andEndShopTimeIsNull() {
            addCriterion("end_shop_time is null");
            return (Criteria) this;
        }

        public Criteria andEndShopTimeIsNotNull() {
            addCriterion("end_shop_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndShopTimeEqualTo(String value) {
            addCriterion("end_shop_time =", value, "endShopTime");
            return (Criteria) this;
        }

        public Criteria andEndShopTimeNotEqualTo(String value) {
            addCriterion("end_shop_time <>", value, "endShopTime");
            return (Criteria) this;
        }

        public Criteria andEndShopTimeGreaterThan(String value) {
            addCriterion("end_shop_time >", value, "endShopTime");
            return (Criteria) this;
        }

        public Criteria andEndShopTimeGreaterThanOrEqualTo(String value) {
            addCriterion("end_shop_time >=", value, "endShopTime");
            return (Criteria) this;
        }

        public Criteria andEndShopTimeLessThan(String value) {
            addCriterion("end_shop_time <", value, "endShopTime");
            return (Criteria) this;
        }

        public Criteria andEndShopTimeLessThanOrEqualTo(String value) {
            addCriterion("end_shop_time <=", value, "endShopTime");
            return (Criteria) this;
        }

        public Criteria andEndShopTimeLike(String value) {
            addCriterion("end_shop_time like", value, "endShopTime");
            return (Criteria) this;
        }

        public Criteria andEndShopTimeNotLike(String value) {
            addCriterion("end_shop_time not like", value, "endShopTime");
            return (Criteria) this;
        }

        public Criteria andEndShopTimeIn(List<String> values) {
            addCriterion("end_shop_time in", values, "endShopTime");
            return (Criteria) this;
        }

        public Criteria andEndShopTimeNotIn(List<String> values) {
            addCriterion("end_shop_time not in", values, "endShopTime");
            return (Criteria) this;
        }

        public Criteria andEndShopTimeBetween(String value1, String value2) {
            addCriterion("end_shop_time between", value1, value2, "endShopTime");
            return (Criteria) this;
        }

        public Criteria andEndShopTimeNotBetween(String value1, String value2) {
            addCriterion("end_shop_time not between", value1, value2, "endShopTime");
            return (Criteria) this;
        }

        public Criteria andStarLevelIsNull() {
            addCriterion("star_level is null");
            return (Criteria) this;
        }

        public Criteria andStarLevelIsNotNull() {
            addCriterion("star_level is not null");
            return (Criteria) this;
        }

        public Criteria andStarLevelEqualTo(Float value) {
            addCriterion("star_level =", value, "starLevel");
            return (Criteria) this;
        }

        public Criteria andStarLevelNotEqualTo(Float value) {
            addCriterion("star_level <>", value, "starLevel");
            return (Criteria) this;
        }

        public Criteria andStarLevelGreaterThan(Float value) {
            addCriterion("star_level >", value, "starLevel");
            return (Criteria) this;
        }

        public Criteria andStarLevelGreaterThanOrEqualTo(Float value) {
            addCriterion("star_level >=", value, "starLevel");
            return (Criteria) this;
        }

        public Criteria andStarLevelLessThan(Float value) {
            addCriterion("star_level <", value, "starLevel");
            return (Criteria) this;
        }

        public Criteria andStarLevelLessThanOrEqualTo(Float value) {
            addCriterion("star_level <=", value, "starLevel");
            return (Criteria) this;
        }

        public Criteria andStarLevelIn(List<Float> values) {
            addCriterion("star_level in", values, "starLevel");
            return (Criteria) this;
        }

        public Criteria andStarLevelNotIn(List<Float> values) {
            addCriterion("star_level not in", values, "starLevel");
            return (Criteria) this;
        }

        public Criteria andStarLevelBetween(Float value1, Float value2) {
            addCriterion("star_level between", value1, value2, "starLevel");
            return (Criteria) this;
        }

        public Criteria andStarLevelNotBetween(Float value1, Float value2) {
            addCriterion("star_level not between", value1, value2, "starLevel");
            return (Criteria) this;
        }

        public Criteria andWeekUseScaleIsNull() {
            addCriterion("week_use_scale is null");
            return (Criteria) this;
        }

        public Criteria andWeekUseScaleIsNotNull() {
            addCriterion("week_use_scale is not null");
            return (Criteria) this;
        }

        public Criteria andWeekUseScaleEqualTo(BigDecimal value) {
            addCriterion("week_use_scale =", value, "weekUseScale");
            return (Criteria) this;
        }

        public Criteria andWeekUseScaleNotEqualTo(BigDecimal value) {
            addCriterion("week_use_scale <>", value, "weekUseScale");
            return (Criteria) this;
        }

        public Criteria andWeekUseScaleGreaterThan(BigDecimal value) {
            addCriterion("week_use_scale >", value, "weekUseScale");
            return (Criteria) this;
        }

        public Criteria andWeekUseScaleGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("week_use_scale >=", value, "weekUseScale");
            return (Criteria) this;
        }

        public Criteria andWeekUseScaleLessThan(BigDecimal value) {
            addCriterion("week_use_scale <", value, "weekUseScale");
            return (Criteria) this;
        }

        public Criteria andWeekUseScaleLessThanOrEqualTo(BigDecimal value) {
            addCriterion("week_use_scale <=", value, "weekUseScale");
            return (Criteria) this;
        }

        public Criteria andWeekUseScaleIn(List<BigDecimal> values) {
            addCriterion("week_use_scale in", values, "weekUseScale");
            return (Criteria) this;
        }

        public Criteria andWeekUseScaleNotIn(List<BigDecimal> values) {
            addCriterion("week_use_scale not in", values, "weekUseScale");
            return (Criteria) this;
        }

        public Criteria andWeekUseScaleBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("week_use_scale between", value1, value2, "weekUseScale");
            return (Criteria) this;
        }

        public Criteria andWeekUseScaleNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("week_use_scale not between", value1, value2, "weekUseScale");
            return (Criteria) this;
        }

        public Criteria andPerConsumeIsNull() {
            addCriterion("per_consume is null");
            return (Criteria) this;
        }

        public Criteria andPerConsumeIsNotNull() {
            addCriterion("per_consume is not null");
            return (Criteria) this;
        }

        public Criteria andPerConsumeEqualTo(BigDecimal value) {
            addCriterion("per_consume =", value, "perConsume");
            return (Criteria) this;
        }

        public Criteria andPerConsumeNotEqualTo(BigDecimal value) {
            addCriterion("per_consume <>", value, "perConsume");
            return (Criteria) this;
        }

        public Criteria andPerConsumeGreaterThan(BigDecimal value) {
            addCriterion("per_consume >", value, "perConsume");
            return (Criteria) this;
        }

        public Criteria andPerConsumeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("per_consume >=", value, "perConsume");
            return (Criteria) this;
        }

        public Criteria andPerConsumeLessThan(BigDecimal value) {
            addCriterion("per_consume <", value, "perConsume");
            return (Criteria) this;
        }

        public Criteria andPerConsumeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("per_consume <=", value, "perConsume");
            return (Criteria) this;
        }

        public Criteria andPerConsumeIn(List<BigDecimal> values) {
            addCriterion("per_consume in", values, "perConsume");
            return (Criteria) this;
        }

        public Criteria andPerConsumeNotIn(List<BigDecimal> values) {
            addCriterion("per_consume not in", values, "perConsume");
            return (Criteria) this;
        }

        public Criteria andPerConsumeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("per_consume between", value1, value2, "perConsume");
            return (Criteria) this;
        }

        public Criteria andPerConsumeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("per_consume not between", value1, value2, "perConsume");
            return (Criteria) this;
        }

        public Criteria andStorePhoneNoIsNull() {
            addCriterion("store_phone_no is null");
            return (Criteria) this;
        }

        public Criteria andStorePhoneNoIsNotNull() {
            addCriterion("store_phone_no is not null");
            return (Criteria) this;
        }

        public Criteria andStorePhoneNoEqualTo(String value) {
            addCriterion("store_phone_no =", value, "storePhoneNo");
            return (Criteria) this;
        }

        public Criteria andStorePhoneNoNotEqualTo(String value) {
            addCriterion("store_phone_no <>", value, "storePhoneNo");
            return (Criteria) this;
        }

        public Criteria andStorePhoneNoGreaterThan(String value) {
            addCriterion("store_phone_no >", value, "storePhoneNo");
            return (Criteria) this;
        }

        public Criteria andStorePhoneNoGreaterThanOrEqualTo(String value) {
            addCriterion("store_phone_no >=", value, "storePhoneNo");
            return (Criteria) this;
        }

        public Criteria andStorePhoneNoLessThan(String value) {
            addCriterion("store_phone_no <", value, "storePhoneNo");
            return (Criteria) this;
        }

        public Criteria andStorePhoneNoLessThanOrEqualTo(String value) {
            addCriterion("store_phone_no <=", value, "storePhoneNo");
            return (Criteria) this;
        }

        public Criteria andStorePhoneNoLike(String value) {
            addCriterion("store_phone_no like", value, "storePhoneNo");
            return (Criteria) this;
        }

        public Criteria andStorePhoneNoNotLike(String value) {
            addCriterion("store_phone_no not like", value, "storePhoneNo");
            return (Criteria) this;
        }

        public Criteria andStorePhoneNoIn(List<String> values) {
            addCriterion("store_phone_no in", values, "storePhoneNo");
            return (Criteria) this;
        }

        public Criteria andStorePhoneNoNotIn(List<String> values) {
            addCriterion("store_phone_no not in", values, "storePhoneNo");
            return (Criteria) this;
        }

        public Criteria andStorePhoneNoBetween(String value1, String value2) {
            addCriterion("store_phone_no between", value1, value2, "storePhoneNo");
            return (Criteria) this;
        }

        public Criteria andStorePhoneNoNotBetween(String value1, String value2) {
            addCriterion("store_phone_no not between", value1, value2, "storePhoneNo");
            return (Criteria) this;
        }

        public Criteria andProfileIsNull() {
            addCriterion("profile is null");
            return (Criteria) this;
        }

        public Criteria andProfileIsNotNull() {
            addCriterion("profile is not null");
            return (Criteria) this;
        }

        public Criteria andProfileEqualTo(String value) {
            addCriterion("profile =", value, "profile");
            return (Criteria) this;
        }

        public Criteria andProfileNotEqualTo(String value) {
            addCriterion("profile <>", value, "profile");
            return (Criteria) this;
        }

        public Criteria andProfileGreaterThan(String value) {
            addCriterion("profile >", value, "profile");
            return (Criteria) this;
        }

        public Criteria andProfileGreaterThanOrEqualTo(String value) {
            addCriterion("profile >=", value, "profile");
            return (Criteria) this;
        }

        public Criteria andProfileLessThan(String value) {
            addCriterion("profile <", value, "profile");
            return (Criteria) this;
        }

        public Criteria andProfileLessThanOrEqualTo(String value) {
            addCriterion("profile <=", value, "profile");
            return (Criteria) this;
        }

        public Criteria andProfileLike(String value) {
            addCriterion("profile like", value, "profile");
            return (Criteria) this;
        }

        public Criteria andProfileNotLike(String value) {
            addCriterion("profile not like", value, "profile");
            return (Criteria) this;
        }

        public Criteria andProfileIn(List<String> values) {
            addCriterion("profile in", values, "profile");
            return (Criteria) this;
        }

        public Criteria andProfileNotIn(List<String> values) {
            addCriterion("profile not in", values, "profile");
            return (Criteria) this;
        }

        public Criteria andProfileBetween(String value1, String value2) {
            addCriterion("profile between", value1, value2, "profile");
            return (Criteria) this;
        }

        public Criteria andProfileNotBetween(String value1, String value2) {
            addCriterion("profile not between", value1, value2, "profile");
            return (Criteria) this;
        }

        public Criteria andIsPhoneCheckIsNull() {
            addCriterion("is_phone_check is null");
            return (Criteria) this;
        }

        public Criteria andIsPhoneCheckIsNotNull() {
            addCriterion("is_phone_check is not null");
            return (Criteria) this;
        }

        public Criteria andIsPhoneCheckEqualTo(Byte value) {
            addCriterion("is_phone_check =", value, "isPhoneCheck");
            return (Criteria) this;
        }

        public Criteria andIsPhoneCheckNotEqualTo(Byte value) {
            addCriterion("is_phone_check <>", value, "isPhoneCheck");
            return (Criteria) this;
        }

        public Criteria andIsPhoneCheckGreaterThan(Byte value) {
            addCriterion("is_phone_check >", value, "isPhoneCheck");
            return (Criteria) this;
        }

        public Criteria andIsPhoneCheckGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_phone_check >=", value, "isPhoneCheck");
            return (Criteria) this;
        }

        public Criteria andIsPhoneCheckLessThan(Byte value) {
            addCriterion("is_phone_check <", value, "isPhoneCheck");
            return (Criteria) this;
        }

        public Criteria andIsPhoneCheckLessThanOrEqualTo(Byte value) {
            addCriterion("is_phone_check <=", value, "isPhoneCheck");
            return (Criteria) this;
        }

        public Criteria andIsPhoneCheckIn(List<Byte> values) {
            addCriterion("is_phone_check in", values, "isPhoneCheck");
            return (Criteria) this;
        }

        public Criteria andIsPhoneCheckNotIn(List<Byte> values) {
            addCriterion("is_phone_check not in", values, "isPhoneCheck");
            return (Criteria) this;
        }

        public Criteria andIsPhoneCheckBetween(Byte value1, Byte value2) {
            addCriterion("is_phone_check between", value1, value2, "isPhoneCheck");
            return (Criteria) this;
        }

        public Criteria andIsPhoneCheckNotBetween(Byte value1, Byte value2) {
            addCriterion("is_phone_check not between", value1, value2, "isPhoneCheck");
            return (Criteria) this;
        }

        public Criteria andWithdrawWayIdIsNull() {
            addCriterion("withdraw_way_id is null");
            return (Criteria) this;
        }

        public Criteria andWithdrawWayIdIsNotNull() {
            addCriterion("withdraw_way_id is not null");
            return (Criteria) this;
        }

        public Criteria andWithdrawWayIdEqualTo(Long value) {
            addCriterion("withdraw_way_id =", value, "withdrawWayId");
            return (Criteria) this;
        }

        public Criteria andWithdrawWayIdNotEqualTo(Long value) {
            addCriterion("withdraw_way_id <>", value, "withdrawWayId");
            return (Criteria) this;
        }

        public Criteria andWithdrawWayIdGreaterThan(Long value) {
            addCriterion("withdraw_way_id >", value, "withdrawWayId");
            return (Criteria) this;
        }

        public Criteria andWithdrawWayIdGreaterThanOrEqualTo(Long value) {
            addCriterion("withdraw_way_id >=", value, "withdrawWayId");
            return (Criteria) this;
        }

        public Criteria andWithdrawWayIdLessThan(Long value) {
            addCriterion("withdraw_way_id <", value, "withdrawWayId");
            return (Criteria) this;
        }

        public Criteria andWithdrawWayIdLessThanOrEqualTo(Long value) {
            addCriterion("withdraw_way_id <=", value, "withdrawWayId");
            return (Criteria) this;
        }

        public Criteria andWithdrawWayIdIn(List<Long> values) {
            addCriterion("withdraw_way_id in", values, "withdrawWayId");
            return (Criteria) this;
        }

        public Criteria andWithdrawWayIdNotIn(List<Long> values) {
            addCriterion("withdraw_way_id not in", values, "withdrawWayId");
            return (Criteria) this;
        }

        public Criteria andWithdrawWayIdBetween(Long value1, Long value2) {
            addCriterion("withdraw_way_id between", value1, value2, "withdrawWayId");
            return (Criteria) this;
        }

        public Criteria andWithdrawWayIdNotBetween(Long value1, Long value2) {
            addCriterion("withdraw_way_id not between", value1, value2, "withdrawWayId");
            return (Criteria) this;
        }

        public Criteria andRoomCountIsNull() {
            addCriterion("room_count is null");
            return (Criteria) this;
        }

        public Criteria andRoomCountIsNotNull() {
            addCriterion("room_count is not null");
            return (Criteria) this;
        }

        public Criteria andRoomCountEqualTo(Integer value) {
            addCriterion("room_count =", value, "roomCount");
            return (Criteria) this;
        }

        public Criteria andRoomCountNotEqualTo(Integer value) {
            addCriterion("room_count <>", value, "roomCount");
            return (Criteria) this;
        }

        public Criteria andRoomCountGreaterThan(Integer value) {
            addCriterion("room_count >", value, "roomCount");
            return (Criteria) this;
        }

        public Criteria andRoomCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("room_count >=", value, "roomCount");
            return (Criteria) this;
        }

        public Criteria andRoomCountLessThan(Integer value) {
            addCriterion("room_count <", value, "roomCount");
            return (Criteria) this;
        }

        public Criteria andRoomCountLessThanOrEqualTo(Integer value) {
            addCriterion("room_count <=", value, "roomCount");
            return (Criteria) this;
        }

        public Criteria andRoomCountIn(List<Integer> values) {
            addCriterion("room_count in", values, "roomCount");
            return (Criteria) this;
        }

        public Criteria andRoomCountNotIn(List<Integer> values) {
            addCriterion("room_count not in", values, "roomCount");
            return (Criteria) this;
        }

        public Criteria andRoomCountBetween(Integer value1, Integer value2) {
            addCriterion("room_count between", value1, value2, "roomCount");
            return (Criteria) this;
        }

        public Criteria andRoomCountNotBetween(Integer value1, Integer value2) {
            addCriterion("room_count not between", value1, value2, "roomCount");
            return (Criteria) this;
        }

        public Criteria andProvinceIdIsNull() {
            addCriterion("province_id is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIdIsNotNull() {
            addCriterion("province_id is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceIdEqualTo(Long value) {
            addCriterion("province_id =", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdNotEqualTo(Long value) {
            addCriterion("province_id <>", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdGreaterThan(Long value) {
            addCriterion("province_id >", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdGreaterThanOrEqualTo(Long value) {
            addCriterion("province_id >=", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdLessThan(Long value) {
            addCriterion("province_id <", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdLessThanOrEqualTo(Long value) {
            addCriterion("province_id <=", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdIn(List<Long> values) {
            addCriterion("province_id in", values, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdNotIn(List<Long> values) {
            addCriterion("province_id not in", values, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdBetween(Long value1, Long value2) {
            addCriterion("province_id between", value1, value2, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdNotBetween(Long value1, Long value2) {
            addCriterion("province_id not between", value1, value2, "provinceId");
            return (Criteria) this;
        }

        public Criteria andCityIdIsNull() {
            addCriterion("city_id is null");
            return (Criteria) this;
        }

        public Criteria andCityIdIsNotNull() {
            addCriterion("city_id is not null");
            return (Criteria) this;
        }

        public Criteria andCityIdEqualTo(Long value) {
            addCriterion("city_id =", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotEqualTo(Long value) {
            addCriterion("city_id <>", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdGreaterThan(Long value) {
            addCriterion("city_id >", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdGreaterThanOrEqualTo(Long value) {
            addCriterion("city_id >=", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdLessThan(Long value) {
            addCriterion("city_id <", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdLessThanOrEqualTo(Long value) {
            addCriterion("city_id <=", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdIn(List<Long> values) {
            addCriterion("city_id in", values, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotIn(List<Long> values) {
            addCriterion("city_id not in", values, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdBetween(Long value1, Long value2) {
            addCriterion("city_id between", value1, value2, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotBetween(Long value1, Long value2) {
            addCriterion("city_id not between", value1, value2, "cityId");
            return (Criteria) this;
        }

        public Criteria andZoneIdIsNull() {
            addCriterion("zone_id is null");
            return (Criteria) this;
        }

        public Criteria andZoneIdIsNotNull() {
            addCriterion("zone_id is not null");
            return (Criteria) this;
        }

        public Criteria andZoneIdEqualTo(Long value) {
            addCriterion("zone_id =", value, "zoneId");
            return (Criteria) this;
        }

        public Criteria andZoneIdNotEqualTo(Long value) {
            addCriterion("zone_id <>", value, "zoneId");
            return (Criteria) this;
        }

        public Criteria andZoneIdGreaterThan(Long value) {
            addCriterion("zone_id >", value, "zoneId");
            return (Criteria) this;
        }

        public Criteria andZoneIdGreaterThanOrEqualTo(Long value) {
            addCriterion("zone_id >=", value, "zoneId");
            return (Criteria) this;
        }

        public Criteria andZoneIdLessThan(Long value) {
            addCriterion("zone_id <", value, "zoneId");
            return (Criteria) this;
        }

        public Criteria andZoneIdLessThanOrEqualTo(Long value) {
            addCriterion("zone_id <=", value, "zoneId");
            return (Criteria) this;
        }

        public Criteria andZoneIdIn(List<Long> values) {
            addCriterion("zone_id in", values, "zoneId");
            return (Criteria) this;
        }

        public Criteria andZoneIdNotIn(List<Long> values) {
            addCriterion("zone_id not in", values, "zoneId");
            return (Criteria) this;
        }

        public Criteria andZoneIdBetween(Long value1, Long value2) {
            addCriterion("zone_id between", value1, value2, "zoneId");
            return (Criteria) this;
        }

        public Criteria andZoneIdNotBetween(Long value1, Long value2) {
            addCriterion("zone_id not between", value1, value2, "zoneId");
            return (Criteria) this;
        }

        public Criteria andIsOldShelfProcessIsNull() {
            addCriterion("is_old_shelf_process is null");
            return (Criteria) this;
        }

        public Criteria andIsOldShelfProcessIsNotNull() {
            addCriterion("is_old_shelf_process is not null");
            return (Criteria) this;
        }

        public Criteria andIsOldShelfProcessEqualTo(Byte value) {
            addCriterion("is_old_shelf_process =", value, "isOldShelfProcess");
            return (Criteria) this;
        }

        public Criteria andIsOldShelfProcessNotEqualTo(Byte value) {
            addCriterion("is_old_shelf_process <>", value, "isOldShelfProcess");
            return (Criteria) this;
        }

        public Criteria andIsOldShelfProcessGreaterThan(Byte value) {
            addCriterion("is_old_shelf_process >", value, "isOldShelfProcess");
            return (Criteria) this;
        }

        public Criteria andIsOldShelfProcessGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_old_shelf_process >=", value, "isOldShelfProcess");
            return (Criteria) this;
        }

        public Criteria andIsOldShelfProcessLessThan(Byte value) {
            addCriterion("is_old_shelf_process <", value, "isOldShelfProcess");
            return (Criteria) this;
        }

        public Criteria andIsOldShelfProcessLessThanOrEqualTo(Byte value) {
            addCriterion("is_old_shelf_process <=", value, "isOldShelfProcess");
            return (Criteria) this;
        }

        public Criteria andIsOldShelfProcessIn(List<Byte> values) {
            addCriterion("is_old_shelf_process in", values, "isOldShelfProcess");
            return (Criteria) this;
        }

        public Criteria andIsOldShelfProcessNotIn(List<Byte> values) {
            addCriterion("is_old_shelf_process not in", values, "isOldShelfProcess");
            return (Criteria) this;
        }

        public Criteria andIsOldShelfProcessBetween(Byte value1, Byte value2) {
            addCriterion("is_old_shelf_process between", value1, value2, "isOldShelfProcess");
            return (Criteria) this;
        }

        public Criteria andIsOldShelfProcessNotBetween(Byte value1, Byte value2) {
            addCriterion("is_old_shelf_process not between", value1, value2, "isOldShelfProcess");
            return (Criteria) this;
        }

        public Criteria andLatitudeXIsNull() {
            addCriterion("latitude_x is null");
            return (Criteria) this;
        }

        public Criteria andLatitudeXIsNotNull() {
            addCriterion("latitude_x is not null");
            return (Criteria) this;
        }

        public Criteria andLatitudeXEqualTo(BigDecimal value) {
            addCriterion("latitude_x =", value, "latitudeX");
            return (Criteria) this;
        }

        public Criteria andLatitudeXNotEqualTo(BigDecimal value) {
            addCriterion("latitude_x <>", value, "latitudeX");
            return (Criteria) this;
        }

        public Criteria andLatitudeXGreaterThan(BigDecimal value) {
            addCriterion("latitude_x >", value, "latitudeX");
            return (Criteria) this;
        }

        public Criteria andLatitudeXGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("latitude_x >=", value, "latitudeX");
            return (Criteria) this;
        }

        public Criteria andLatitudeXLessThan(BigDecimal value) {
            addCriterion("latitude_x <", value, "latitudeX");
            return (Criteria) this;
        }

        public Criteria andLatitudeXLessThanOrEqualTo(BigDecimal value) {
            addCriterion("latitude_x <=", value, "latitudeX");
            return (Criteria) this;
        }

        public Criteria andLatitudeXIn(List<BigDecimal> values) {
            addCriterion("latitude_x in", values, "latitudeX");
            return (Criteria) this;
        }

        public Criteria andLatitudeXNotIn(List<BigDecimal> values) {
            addCriterion("latitude_x not in", values, "latitudeX");
            return (Criteria) this;
        }

        public Criteria andLatitudeXBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("latitude_x between", value1, value2, "latitudeX");
            return (Criteria) this;
        }

        public Criteria andLatitudeXNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("latitude_x not between", value1, value2, "latitudeX");
            return (Criteria) this;
        }

        public Criteria andLongitudeYIsNull() {
            addCriterion("longitude_y is null");
            return (Criteria) this;
        }

        public Criteria andLongitudeYIsNotNull() {
            addCriterion("longitude_y is not null");
            return (Criteria) this;
        }

        public Criteria andLongitudeYEqualTo(BigDecimal value) {
            addCriterion("longitude_y =", value, "longitudeY");
            return (Criteria) this;
        }

        public Criteria andLongitudeYNotEqualTo(BigDecimal value) {
            addCriterion("longitude_y <>", value, "longitudeY");
            return (Criteria) this;
        }

        public Criteria andLongitudeYGreaterThan(BigDecimal value) {
            addCriterion("longitude_y >", value, "longitudeY");
            return (Criteria) this;
        }

        public Criteria andLongitudeYGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("longitude_y >=", value, "longitudeY");
            return (Criteria) this;
        }

        public Criteria andLongitudeYLessThan(BigDecimal value) {
            addCriterion("longitude_y <", value, "longitudeY");
            return (Criteria) this;
        }

        public Criteria andLongitudeYLessThanOrEqualTo(BigDecimal value) {
            addCriterion("longitude_y <=", value, "longitudeY");
            return (Criteria) this;
        }

        public Criteria andLongitudeYIn(List<BigDecimal> values) {
            addCriterion("longitude_y in", values, "longitudeY");
            return (Criteria) this;
        }

        public Criteria andLongitudeYNotIn(List<BigDecimal> values) {
            addCriterion("longitude_y not in", values, "longitudeY");
            return (Criteria) this;
        }

        public Criteria andLongitudeYBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("longitude_y between", value1, value2, "longitudeY");
            return (Criteria) this;
        }

        public Criteria andLongitudeYNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("longitude_y not between", value1, value2, "longitudeY");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}