package com.xyz.caofancpu.d8ger.test.D8AutoCode;

import com.xyz.caofancpu.d8ger.test.YesNoEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * TicketMo对应的Example单表操作对象
 *
 * @author d8ger
 */
public class TicketExample {

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TicketExample() {
        oredCriteria = new ArrayList<>();
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
        return new Criteria();
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
            criteria = new ArrayList<>();
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

        /**
         * id为null
         *
         * @return
         */
        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        /**
         * id不为null
         *
         * @return
         */
        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        /**
         * id等于
         *
         * @return
         */
        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        /**
         * id不等于
         *
         * @return
         */
        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        /**
         * id大于
         *
         * @return
         */
        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        /**
         * id大于等于
         *
         * @return
         */
        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        /**
         * id小于
         *
         * @return
         */
        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        /**
         * id小于等于
         *
         * @return
         */
        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        /**
         * id在列表内
         *
         * @return
         */
        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        /**
         * id不在列表内
         *
         * @return
         */
        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        /**
         * id在起始值范围内
         *
         * @return
         */
        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        /**
         * id不在起始值范围内
         *
         * @return
         */
        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        /**
         * startStationName为null
         *
         * @return
         */
        public Criteria andStartStationNameIsNull() {
            addCriterion("start_station_name is null");
            return (Criteria) this;
        }

        /**
         * startStationName不为null
         *
         * @return
         */
        public Criteria andStartStationNameIsNotNull() {
            addCriterion("start_station_name is not null");
            return (Criteria) this;
        }

        /**
         * startStationName等于
         *
         * @return
         */
        public Criteria andStartStationNameEqualTo(String value) {
            addCriterion("start_station_name =", value, "startStationName");
            return (Criteria) this;
        }

        /**
         * startStationName不等于
         *
         * @return
         */
        public Criteria andStartStationNameNotEqualTo(String value) {
            addCriterion("start_station_name <>", value, "startStationName");
            return (Criteria) this;
        }

        /**
         * startStationName大于
         *
         * @return
         */
        public Criteria andStartStationNameGreaterThan(String value) {
            addCriterion("start_station_name >", value, "startStationName");
            return (Criteria) this;
        }

        /**
         * startStationName大于等于
         *
         * @return
         */
        public Criteria andStartStationNameGreaterThanOrEqualTo(String value) {
            addCriterion("start_station_name >=", value, "startStationName");
            return (Criteria) this;
        }

        /**
         * startStationName小于
         *
         * @return
         */
        public Criteria andStartStationNameLessThan(String value) {
            addCriterion("start_station_name <", value, "startStationName");
            return (Criteria) this;
        }

        /**
         * startStationName小于等于
         *
         * @return
         */
        public Criteria andStartStationNameLessThanOrEqualTo(String value) {
            addCriterion("start_station_name <=", value, "startStationName");
            return (Criteria) this;
        }

        /**
         * startStationName在列表内
         *
         * @return
         */
        public Criteria andStartStationNameIn(List<String> values) {
            addCriterion("start_station_name in", values, "startStationName");
            return (Criteria) this;
        }

        /**
         * startStationName不在列表内
         *
         * @return
         */
        public Criteria andStartStationNameNotIn(List<String> values) {
            addCriterion("start_station_name not in", values, "startStationName");
            return (Criteria) this;
        }

        /**
         * startStationName在起始值范围内
         *
         * @return
         */
        public Criteria andStartStationNameBetween(String value1, String value2) {
            addCriterion("start_station_name between", value1, value2, "startStationName");
            return (Criteria) this;
        }

        /**
         * startStationName不在起始值范围内
         *
         * @return
         */
        public Criteria andStartStationNameNotBetween(String value1, String value2) {
            addCriterion("start_station_name not between", value1, value2, "startStationName");
            return (Criteria) this;
        }

        /**
         * startStationName模糊查询以前缀开头
         *
         * @return
         */
        public Criteria andStartStationNameLike(String value) {
            addCriterion("start_station_name like", value + "%", "startStationName");
            return (Criteria) this;
        }

        /**
         * startStationName模糊查询不以前缀匹开头
         *
         * @return
         */
        public Criteria andStartStationNameNotLike(String value) {
            addCriterion("start_station_name not like", value + "%", "startStationName");
            return (Criteria) this;
        }

        /**
         * endStationName为null
         *
         * @return
         */
        public Criteria andEndStationNameIsNull() {
            addCriterion("end_station_name is null");
            return (Criteria) this;
        }

        /**
         * endStationName不为null
         *
         * @return
         */
        public Criteria andEndStationNameIsNotNull() {
            addCriterion("end_station_name is not null");
            return (Criteria) this;
        }

        /**
         * endStationName等于
         *
         * @return
         */
        public Criteria andEndStationNameEqualTo(String value) {
            addCriterion("end_station_name =", value, "endStationName");
            return (Criteria) this;
        }

        /**
         * endStationName不等于
         *
         * @return
         */
        public Criteria andEndStationNameNotEqualTo(String value) {
            addCriterion("end_station_name <>", value, "endStationName");
            return (Criteria) this;
        }

        /**
         * endStationName大于
         *
         * @return
         */
        public Criteria andEndStationNameGreaterThan(String value) {
            addCriterion("end_station_name >", value, "endStationName");
            return (Criteria) this;
        }

        /**
         * endStationName大于等于
         *
         * @return
         */
        public Criteria andEndStationNameGreaterThanOrEqualTo(String value) {
            addCriterion("end_station_name >=", value, "endStationName");
            return (Criteria) this;
        }

        /**
         * endStationName小于
         *
         * @return
         */
        public Criteria andEndStationNameLessThan(String value) {
            addCriterion("end_station_name <", value, "endStationName");
            return (Criteria) this;
        }

        /**
         * endStationName小于等于
         *
         * @return
         */
        public Criteria andEndStationNameLessThanOrEqualTo(String value) {
            addCriterion("end_station_name <=", value, "endStationName");
            return (Criteria) this;
        }

        /**
         * endStationName在列表内
         *
         * @return
         */
        public Criteria andEndStationNameIn(List<String> values) {
            addCriterion("end_station_name in", values, "endStationName");
            return (Criteria) this;
        }

        /**
         * endStationName不在列表内
         *
         * @return
         */
        public Criteria andEndStationNameNotIn(List<String> values) {
            addCriterion("end_station_name not in", values, "endStationName");
            return (Criteria) this;
        }

        /**
         * endStationName在起始值范围内
         *
         * @return
         */
        public Criteria andEndStationNameBetween(String value1, String value2) {
            addCriterion("end_station_name between", value1, value2, "endStationName");
            return (Criteria) this;
        }

        /**
         * endStationName不在起始值范围内
         *
         * @return
         */
        public Criteria andEndStationNameNotBetween(String value1, String value2) {
            addCriterion("end_station_name not between", value1, value2, "endStationName");
            return (Criteria) this;
        }

        /**
         * endStationName模糊查询以前缀开头
         *
         * @return
         */
        public Criteria andEndStationNameLike(String value) {
            addCriterion("end_station_name like", value + "%", "endStationName");
            return (Criteria) this;
        }

        /**
         * endStationName模糊查询不以前缀匹开头
         *
         * @return
         */
        public Criteria andEndStationNameNotLike(String value) {
            addCriterion("end_station_name not like", value + "%", "endStationName");
            return (Criteria) this;
        }

        /**
         * money为null
         *
         * @return
         */
        public Criteria andMoneyIsNull() {
            addCriterion("money is null");
            return (Criteria) this;
        }

        /**
         * money不为null
         *
         * @return
         */
        public Criteria andMoneyIsNotNull() {
            addCriterion("money is not null");
            return (Criteria) this;
        }

        /**
         * money等于
         *
         * @return
         */
        public Criteria andMoneyEqualTo(BigDecimal value) {
            addCriterion("money =", value, "money");
            return (Criteria) this;
        }

        /**
         * money不等于
         *
         * @return
         */
        public Criteria andMoneyNotEqualTo(BigDecimal value) {
            addCriterion("money <>", value, "money");
            return (Criteria) this;
        }

        /**
         * money大于
         *
         * @return
         */
        public Criteria andMoneyGreaterThan(BigDecimal value) {
            addCriterion("money >", value, "money");
            return (Criteria) this;
        }

        /**
         * money大于等于
         *
         * @return
         */
        public Criteria andMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("money >=", value, "money");
            return (Criteria) this;
        }

        /**
         * money小于
         *
         * @return
         */
        public Criteria andMoneyLessThan(BigDecimal value) {
            addCriterion("money <", value, "money");
            return (Criteria) this;
        }

        /**
         * money小于等于
         *
         * @return
         */
        public Criteria andMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("money <=", value, "money");
            return (Criteria) this;
        }

        /**
         * money在列表内
         *
         * @return
         */
        public Criteria andMoneyIn(List<BigDecimal> values) {
            addCriterion("money in", values, "money");
            return (Criteria) this;
        }

        /**
         * money不在列表内
         *
         * @return
         */
        public Criteria andMoneyNotIn(List<BigDecimal> values) {
            addCriterion("money not in", values, "money");
            return (Criteria) this;
        }

        /**
         * money在起始值范围内
         *
         * @return
         */
        public Criteria andMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("money between", value1, value2, "money");
            return (Criteria) this;
        }

        /**
         * money不在起始值范围内
         *
         * @return
         */
        public Criteria andMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("money not between", value1, value2, "money");
            return (Criteria) this;
        }

        /**
         * orderTime为null
         *
         * @return
         */
        public Criteria andOrderTimeIsNull() {
            addCriterion("order_time is null");
            return (Criteria) this;
        }

        /**
         * orderTime不为null
         *
         * @return
         */
        public Criteria andOrderTimeIsNotNull() {
            addCriterion("order_time is not null");
            return (Criteria) this;
        }

        /**
         * orderTime等于
         *
         * @return
         */
        public Criteria andOrderTimeEqualTo(Date value) {
            addCriterion("order_time =", value, "orderTime");
            return (Criteria) this;
        }

        /**
         * orderTime不等于
         *
         * @return
         */
        public Criteria andOrderTimeNotEqualTo(Date value) {
            addCriterion("order_time <>", value, "orderTime");
            return (Criteria) this;
        }

        /**
         * orderTime大于
         *
         * @return
         */
        public Criteria andOrderTimeGreaterThan(Date value) {
            addCriterion("order_time >", value, "orderTime");
            return (Criteria) this;
        }

        /**
         * orderTime大于等于
         *
         * @return
         */
        public Criteria andOrderTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("order_time >=", value, "orderTime");
            return (Criteria) this;
        }

        /**
         * orderTime小于
         *
         * @return
         */
        public Criteria andOrderTimeLessThan(Date value) {
            addCriterion("order_time <", value, "orderTime");
            return (Criteria) this;
        }

        /**
         * orderTime小于等于
         *
         * @return
         */
        public Criteria andOrderTimeLessThanOrEqualTo(Date value) {
            addCriterion("order_time <=", value, "orderTime");
            return (Criteria) this;
        }

        /**
         * orderTime在列表内
         *
         * @return
         */
        public Criteria andOrderTimeIn(List<Date> values) {
            addCriterion("order_time in", values, "orderTime");
            return (Criteria) this;
        }

        /**
         * orderTime不在列表内
         *
         * @return
         */
        public Criteria andOrderTimeNotIn(List<Date> values) {
            addCriterion("order_time not in", values, "orderTime");
            return (Criteria) this;
        }

        /**
         * orderTime在起始值范围内
         *
         * @return
         */
        public Criteria andOrderTimeBetween(Date value1, Date value2) {
            addCriterion("order_time between", value1, value2, "orderTime");
            return (Criteria) this;
        }

        /**
         * orderTime不在起始值范围内
         *
         * @return
         */
        public Criteria andOrderTimeNotBetween(Date value1, Date value2) {
            addCriterion("order_time not between", value1, value2, "orderTime");
            return (Criteria) this;
        }

        /**
         * ridingTime为null
         *
         * @return
         */
        public Criteria andRidingTimeIsNull() {
            addCriterion("riding_time is null");
            return (Criteria) this;
        }

        /**
         * ridingTime不为null
         *
         * @return
         */
        public Criteria andRidingTimeIsNotNull() {
            addCriterion("riding_time is not null");
            return (Criteria) this;
        }

        /**
         * ridingTime等于
         *
         * @return
         */
        public Criteria andRidingTimeEqualTo(LocalDateTime value) {
            addCriterion("riding_time =", value, "ridingTime");
            return (Criteria) this;
        }

        /**
         * ridingTime不等于
         *
         * @return
         */
        public Criteria andRidingTimeNotEqualTo(LocalDateTime value) {
            addCriterion("riding_time <>", value, "ridingTime");
            return (Criteria) this;
        }

        /**
         * ridingTime大于
         *
         * @return
         */
        public Criteria andRidingTimeGreaterThan(LocalDateTime value) {
            addCriterion("riding_time >", value, "ridingTime");
            return (Criteria) this;
        }

        /**
         * ridingTime大于等于
         *
         * @return
         */
        public Criteria andRidingTimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("riding_time >=", value, "ridingTime");
            return (Criteria) this;
        }

        /**
         * ridingTime小于
         *
         * @return
         */
        public Criteria andRidingTimeLessThan(LocalDateTime value) {
            addCriterion("riding_time <", value, "ridingTime");
            return (Criteria) this;
        }

        /**
         * ridingTime小于等于
         *
         * @return
         */
        public Criteria andRidingTimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("riding_time <=", value, "ridingTime");
            return (Criteria) this;
        }

        /**
         * ridingTime在列表内
         *
         * @return
         */
        public Criteria andRidingTimeIn(List<LocalDateTime> values) {
            addCriterion("riding_time in", values, "ridingTime");
            return (Criteria) this;
        }

        /**
         * ridingTime不在列表内
         *
         * @return
         */
        public Criteria andRidingTimeNotIn(List<LocalDateTime> values) {
            addCriterion("riding_time not in", values, "ridingTime");
            return (Criteria) this;
        }

        /**
         * ridingTime在起始值范围内
         *
         * @return
         */
        public Criteria andRidingTimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("riding_time between", value1, value2, "ridingTime");
            return (Criteria) this;
        }

        /**
         * ridingTime不在起始值范围内
         *
         * @return
         */
        public Criteria andRidingTimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("riding_time not between", value1, value2, "ridingTime");
            return (Criteria) this;
        }

        /**
         * mile为null
         *
         * @return
         */
        public Criteria andMileIsNull() {
            addCriterion("mile is null");
            return (Criteria) this;
        }

        /**
         * mile不为null
         *
         * @return
         */
        public Criteria andMileIsNotNull() {
            addCriterion("mile is not null");
            return (Criteria) this;
        }

        /**
         * mile等于
         *
         * @return
         */
        public Criteria andMileEqualTo(Long value) {
            addCriterion("mile =", value, "mile");
            return (Criteria) this;
        }

        /**
         * mile不等于
         *
         * @return
         */
        public Criteria andMileNotEqualTo(Long value) {
            addCriterion("mile <>", value, "mile");
            return (Criteria) this;
        }

        /**
         * mile大于
         *
         * @return
         */
        public Criteria andMileGreaterThan(Long value) {
            addCriterion("mile >", value, "mile");
            return (Criteria) this;
        }

        /**
         * mile大于等于
         *
         * @return
         */
        public Criteria andMileGreaterThanOrEqualTo(Long value) {
            addCriterion("mile >=", value, "mile");
            return (Criteria) this;
        }

        /**
         * mile小于
         *
         * @return
         */
        public Criteria andMileLessThan(Long value) {
            addCriterion("mile <", value, "mile");
            return (Criteria) this;
        }

        /**
         * mile小于等于
         *
         * @return
         */
        public Criteria andMileLessThanOrEqualTo(Long value) {
            addCriterion("mile <=", value, "mile");
            return (Criteria) this;
        }

        /**
         * mile在列表内
         *
         * @return
         */
        public Criteria andMileIn(List<Long> values) {
            addCriterion("mile in", values, "mile");
            return (Criteria) this;
        }

        /**
         * mile不在列表内
         *
         * @return
         */
        public Criteria andMileNotIn(List<Long> values) {
            addCriterion("mile not in", values, "mile");
            return (Criteria) this;
        }

        /**
         * mile在起始值范围内
         *
         * @return
         */
        public Criteria andMileBetween(Long value1, Long value2) {
            addCriterion("mile between", value1, value2, "mile");
            return (Criteria) this;
        }

        /**
         * mile不在起始值范围内
         *
         * @return
         */
        public Criteria andMileNotBetween(Long value1, Long value2) {
            addCriterion("mile not between", value1, value2, "mile");
            return (Criteria) this;
        }

        /**
         * success为null
         *
         * @return
         */
        public Criteria andSuccessIsNull() {
            addCriterion("success is null");
            return (Criteria) this;
        }

        /**
         * success不为null
         *
         * @return
         */
        public Criteria andSuccessIsNotNull() {
            addCriterion("success is not null");
            return (Criteria) this;
        }

        /**
         * success等于
         *
         * @return
         */
        public Criteria andSuccessEqualTo(Boolean value) {
            addCriterion("success =", value, "success");
            return (Criteria) this;
        }

        /**
         * success不等于
         *
         * @return
         */
        public Criteria andSuccessNotEqualTo(Boolean value) {
            addCriterion("success <>", value, "success");
            return (Criteria) this;
        }

        /**
         * success大于
         *
         * @return
         */
        public Criteria andSuccessGreaterThan(Boolean value) {
            addCriterion("success >", value, "success");
            return (Criteria) this;
        }

        /**
         * success大于等于
         *
         * @return
         */
        public Criteria andSuccessGreaterThanOrEqualTo(Boolean value) {
            addCriterion("success >=", value, "success");
            return (Criteria) this;
        }

        /**
         * success小于
         *
         * @return
         */
        public Criteria andSuccessLessThan(Boolean value) {
            addCriterion("success <", value, "success");
            return (Criteria) this;
        }

        /**
         * success小于等于
         *
         * @return
         */
        public Criteria andSuccessLessThanOrEqualTo(Boolean value) {
            addCriterion("success <=", value, "success");
            return (Criteria) this;
        }

        /**
         * success在列表内
         *
         * @return
         */
        public Criteria andSuccessIn(List<Boolean> values) {
            addCriterion("success in", values, "success");
            return (Criteria) this;
        }

        /**
         * success不在列表内
         *
         * @return
         */
        public Criteria andSuccessNotIn(List<Boolean> values) {
            addCriterion("success not in", values, "success");
            return (Criteria) this;
        }

        /**
         * success在起始值范围内
         *
         * @return
         */
        public Criteria andSuccessBetween(Boolean value1, Boolean value2) {
            addCriterion("success between", value1, value2, "success");
            return (Criteria) this;
        }

        /**
         * success不在起始值范围内
         *
         * @return
         */
        public Criteria andSuccessNotBetween(Boolean value1, Boolean value2) {
            addCriterion("success not between", value1, value2, "success");
            return (Criteria) this;
        }

        /**
         * deleted为null
         *
         * @return
         */
        public Criteria andDeletedIsNull() {
            addCriterion("deleted is null");
            return (Criteria) this;
        }

        /**
         * deleted不为null
         *
         * @return
         */
        public Criteria andDeletedIsNotNull() {
            addCriterion("deleted is not null");
            return (Criteria) this;
        }

        /**
         * deleted等于
         *
         * @return
         */
        public Criteria andDeletedEqualTo(YesNoEnum value) {
            addCriterion("deleted =", value, "deleted");
            return (Criteria) this;
        }

        /**
         * deleted不等于
         *
         * @return
         */
        public Criteria andDeletedNotEqualTo(YesNoEnum value) {
            addCriterion("deleted <>", value, "deleted");
            return (Criteria) this;
        }

        /**
         * deleted大于
         *
         * @return
         */
        public Criteria andDeletedGreaterThan(YesNoEnum value) {
            addCriterion("deleted >", value, "deleted");
            return (Criteria) this;
        }

        /**
         * deleted大于等于
         *
         * @return
         */
        public Criteria andDeletedGreaterThanOrEqualTo(YesNoEnum value) {
            addCriterion("deleted >=", value, "deleted");
            return (Criteria) this;
        }

        /**
         * deleted小于
         *
         * @return
         */
        public Criteria andDeletedLessThan(YesNoEnum value) {
            addCriterion("deleted <", value, "deleted");
            return (Criteria) this;
        }

        /**
         * deleted小于等于
         *
         * @return
         */
        public Criteria andDeletedLessThanOrEqualTo(YesNoEnum value) {
            addCriterion("deleted <=", value, "deleted");
            return (Criteria) this;
        }

        /**
         * deleted在列表内
         *
         * @return
         */
        public Criteria andDeletedIn(List<YesNoEnum> values) {
            addCriterion("deleted in", values, "deleted");
            return (Criteria) this;
        }

        /**
         * deleted不在列表内
         *
         * @return
         */
        public Criteria andDeletedNotIn(List<YesNoEnum> values) {
            addCriterion("deleted not in", values, "deleted");
            return (Criteria) this;
        }

        /**
         * deleted在起始值范围内
         *
         * @return
         */
        public Criteria andDeletedBetween(YesNoEnum value1, YesNoEnum value2) {
            addCriterion("deleted between", value1, value2, "deleted");
            return (Criteria) this;
        }

        /**
         * deleted不在起始值范围内
         *
         * @return
         */
        public Criteria andDeletedNotBetween(YesNoEnum value1, YesNoEnum value2) {
            addCriterion("deleted not between", value1, value2, "deleted");
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