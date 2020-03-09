package com.xyz.caofancpu.d8ger.core;

import com.xyz.caofancpu.d8ger.util.VerbalExpressionUtil;
import lombok.NonNull;
import ru.lanwen.verbalregex.VerbalExpression;

import java.util.Map;

/**
 * Code template string constants
 *
 * @author caofanCPU
 */
public class AutoCodeTemplate {
    public static Boolean IS_EN_LOCALE = Boolean.TRUE;

    /**
     * MO template string
     */
    public static StringBuilder ZN_TEMPLATE_MO = new StringBuilder("package @moPackage@;\n" +
            "\n" +
            "import lombok.AllArgsConstructor;\n" +
            "import lombok.Data;\n" +
            "import lombok.NoArgsConstructor;\n" +
            "import lombok.experimental.Accessors;\n" +
            "\n" +
            "import java.math.BigDecimal;\n" +
            "import java.time.LocalDateTime;\n" +
            "import java.util.Date;\n" +
            "\n" +
            "/**\n" +
            " * @MoName@Mo\n" +
            " *\n" +
            " * @author @d8Author@\n" +
            " */\n" +
            "@Data\n" +
            "@NoArgsConstructor\n" +
            "@AllArgsConstructor\n" +
            "@Accessors(chain = true)\n" +
            "public class @MoName@Mo {\n" +
            "\n" +
            "@field@\n" +
            "\n" +
            "}");
    /**
     * Swagger VO template string
     */
    public static StringBuilder ZN_TEMPLATE_SWAGGER_VO = new StringBuilder("package @swaggerMoPackage@;\n" +
            "\n" +
            "import io.swagger.annotations.ApiModel;\n" +
            "import io.swagger.annotations.ApiModelProperty;\n" +
            "import lombok.AllArgsConstructor;\n" +
            "import lombok.Data;\n" +
            "import lombok.NoArgsConstructor;\n" +
            "import lombok.experimental.Accessors;\n" +
            "\n" +
            "import java.math.BigDecimal;\n" +
            "import java.time.LocalDateTime;\n" +
            "import java.util.Date;\n" +
            "\n" +
            "/**\n" +
            " * @MoName@Mo对应的SwaggerApi增强Vo对象\n" +
            " *\n" +
            " * @author @d8Author@\n" +
            " */\n" +
            "@Data\n" +
            "@NoArgsConstructor\n" +
            "@AllArgsConstructor\n" +
            "@Accessors(chain = true)\n" +
            "@ApiModel\n" +
            "public class @MoName@Vo {\n" +
            "\n" +
            "@swaggerField@\n" +
            "\n" +
            "}");
    /**
     * Sql template string
     */
    public static StringBuilder ZN_TEMPLATE_MO_SQL = new StringBuilder("\n" +
            "-- ----------------------------\n" +
            "-- D8ger-Sql-Auto-Generated\n" +
            "-- Table structure for `@mo_table_name@`\n" +
            "-- @author @d8Author@\n" +
            "-- ----------------------------\n" +
            "-- DROP TABLE IF EXISTS `@mo_table_name@`;\n" +
            "CREATE TABLE `@mo_table_name@`\n" +
            "(\n" +
            "@id@" +
            "@sql_column@\n" +
            "@create_time@" +
            "@update_time@" +
            ")\n" +
            "    comment '@MoName@表' charset = utf8mb4;\n");
    /**
     * Mapper template string
     */
    public static StringBuilder ZN_TEMPLATE_MAPPER = new StringBuilder("package @mapperPackage@;\n" +
            "\n" +
            "import @moExamplePackage@.@MoName@Example;\n" +
            "import @moPackage@.@MoName@Mo;\n" +
            "import org.apache.ibatis.annotations.Param;\n" +
            "import org.apache.ibatis.annotations.Mapper;\n" +
            "\n" +
            "import java.util.List;\n" +
            "\n" +
            "/**\n" +
            " * @MoName@Mo对应的Mapper\n" +
            " *\n" +
            " * @author @d8Author@\n" +
            " */\n" +
            "@Mapper\n" +
            "public interface @MoName@Mapper {\n" +
            "\n" +
            "    /**\n" +
            "     * 根据条件查询列表\n" +
            "     *\n" +
            "     * @param @uncapitallizeMoName@Example\n" +
            "     * @return\n" +
            "     */\n" +
            "    List<@MoName@Mo> selectByExample(@MoName@Example @uncapitallizeMoName@Example);\n" +
            "\n" +
            "    /**\n" +
            "     * 批量更新, 根据主键更新非null字段\n" +
            "     *\n" +
            "     * @param @uncapitallizeMoName@MoList\n" +
            "     * @return\n" +
            "     */\n" +
            "    int updateBatchByPrimaryKeySelective(List<@MoName@Mo> @uncapitallizeMoName@MoList);\n" +
            "\n" +
            "    /**\n" +
            "     * 根据条件更新非null字段\n" +
            "     *\n" +
            "     * @param @uncapitallizeMoName@Mo\n" +
            "     * @param @uncapitallizeMoName@Example\n" +
            "     * @return\n" +
            "     */\n" +
            "    int updateByExampleSelective(@Param(\"record\") @MoName@Mo @uncapitallizeMoName@Mo, @Param(\"example\") @MoName@Example @uncapitallizeMoName@Example);\n" +
            "\n" +
            "    /**\n" +
            "     * 根据条件删除记录\n" +
            "     *\n" +
            "     * @param @uncapitallizeMoName@Example\n" +
            "     * @return\n" +
            "     */\n" +
            "    int deleteByExample(@MoName@Example @uncapitallizeMoName@Example);\n" +
            "\n" +
            "    /**\n" +
            "     * 根据条件统计记录\n" +
            "     *\n" +
            "     * @param @uncapitallizeMoName@Example\n" +
            "     * @return 记录条数\n" +
            "     */\n" +
            "    int countByExample(@MoName@Example @uncapitallizeMoName@Example);\n" +
            "\n" +
            "    /**\n" +
            "     * 增加单条记录, 并为入参设置ID\n" +
            "     *\n" +
            "     * @param @uncapitallizeMoName@Mo\n" +
            "     * @return\n" +
            "     */\n" +
            "    int insertWithId(@MoName@Mo @uncapitallizeMoName@Mo);\n" +
            "\n" +
            "    /**\n" +
            "     * 批量增加记录, 并为入参设置ID\n" +
            "     *\n" +
            "     * @param @uncapitallizeMoName@MoList\n" +
            "     * @return\n" +
            "     */\n" +
            "    int insertBatchWithId(List<@MoName@Mo> @uncapitallizeMoName@MoList);\n" +
            "\n" +
            "    /**\n" +
            "     * @MoName@列表查询\n" +
            "     *\n" +
            "     * @param @uncapitallizeMoName@Mo\n" +
            "     * @return\n" +
            "     */\n" +
            "    List<@MoName@Mo> query@MoName@MoList(@MoName@Mo @uncapitallizeMoName@Mo);\n" +
            "\n" +
            "    /**\n" +
            "     * 根据ID查询对象\n" +
            "     *\n" +
            "     * @param id\n" +
            "     * @return\n" +
            "     */\n" +
            "    <T extends Number> @MoName@Mo selectByPrimaryKey(T id);\n" +
            "\n" +
            "    /**\n" +
            "     * 根据主键只更新非null字段\n" +
            "     *\n" +
            "     * @param @uncapitallizeMoName@Mo\n" +
            "     * @return\n" +
            "     */\n" +
            "    int updateByPrimaryKeySelective(@MoName@Mo @uncapitallizeMoName@Mo);\n" +
            "\n" +
            "    /**\n" +
            "     * 根据ID删除记录\n" +
            "     *\n" +
            "     * @param id\n" +
            "     * @return\n" +
            "     */\n" +
            "    <T extends Number> int deleteByPrimaryKey(T id);\n" +
            "}");
    /**
     * MoExample template string
     */
    public static StringBuilder ZN_TEMPLATE_MO_EXAMPLE = new StringBuilder("package @moExamplePackage@;\n" +
            "\n" +
            "import java.math.BigDecimal;\n" +
            "import java.time.LocalDateTime;\n" +
            "import java.util.ArrayList;\n" +
            "import java.util.Date;\n" +
            "import java.util.List;\n" +
            "\n" +
            "/**\n" +
            " * @MoName@Mo对应的Example单表操作对象\n" +
            " *\n" +
            " * @author @d8Author@\n" +
            " */\n" +
            "public class @MoName@Example {\n" +
            "\n" +
            "    protected String orderByClause;\n" +
            "\n" +
            "    protected boolean distinct;\n" +
            "\n" +
            "    protected List<Criteria> oredCriteria;\n" +
            "\n" +
            "    public @MoName@Example() {\n" +
            "        oredCriteria = new ArrayList<>();\n" +
            "    }\n" +
            "\n" +
            "    public void setOrderByClause(String orderByClause) {\n" +
            "        this.orderByClause = orderByClause;\n" +
            "    }\n" +
            "\n" +
            "    public String getOrderByClause() {\n" +
            "        return orderByClause;\n" +
            "    }\n" +
            "\n" +
            "    public void setDistinct(boolean distinct) {\n" +
            "        this.distinct = distinct;\n" +
            "    }\n" +
            "\n" +
            "    public boolean isDistinct() {\n" +
            "        return distinct;\n" +
            "    }\n" +
            "\n" +
            "    public List<Criteria> getOredCriteria() {\n" +
            "        return oredCriteria;\n" +
            "    }\n" +
            "\n" +
            "    public void or(Criteria criteria) {\n" +
            "        oredCriteria.add(criteria);\n" +
            "    }\n" +
            "\n" +
            "    public Criteria or() {\n" +
            "        Criteria criteria = createCriteriaInternal();\n" +
            "        oredCriteria.add(criteria);\n" +
            "        return criteria;\n" +
            "    }\n" +
            "\n" +
            "    public Criteria createCriteria() {\n" +
            "        Criteria criteria = createCriteriaInternal();\n" +
            "        if (oredCriteria.size() == 0) {\n" +
            "            oredCriteria.add(criteria);\n" +
            "        }\n" +
            "        return criteria;\n" +
            "    }\n" +
            "\n" +
            "    protected Criteria createCriteriaInternal() {\n" +
            "        return new Criteria();\n" +
            "    }\n" +
            "\n" +
            "    public void clear() {\n" +
            "        oredCriteria.clear();\n" +
            "        orderByClause = null;\n" +
            "        distinct = false;\n" +
            "    }\n" +
            "\n" +
            "    protected abstract static class GeneratedCriteria {\n" +
            "        protected List<Criterion> criteria;\n" +
            "\n" +
            "        protected GeneratedCriteria() {\n" +
            "            super();\n" +
            "            criteria = new ArrayList<>();\n" +
            "        }\n" +
            "\n" +
            "        public boolean isValid() {\n" +
            "            return criteria.size() > 0;\n" +
            "        }\n" +
            "\n" +
            "        public List<Criterion> getAllCriteria() {\n" +
            "            return criteria;\n" +
            "        }\n" +
            "\n" +
            "        public List<Criterion> getCriteria() {\n" +
            "            return criteria;\n" +
            "        }\n" +
            "\n" +
            "        protected void addCriterion(String condition) {\n" +
            "            if (condition == null) {\n" +
            "                throw new RuntimeException(\"Value for condition cannot be null\");\n" +
            "            }\n" +
            "            criteria.add(new Criterion(condition));\n" +
            "        }\n" +
            "\n" +
            "        protected void addCriterion(String condition, Object value, String property) {\n" +
            "            if (value == null) {\n" +
            "                throw new RuntimeException(\"Value for \" + property + \" cannot be null\");\n" +
            "            }\n" +
            "            criteria.add(new Criterion(condition, value));\n" +
            "        }\n" +
            "\n" +
            "        protected void addCriterion(String condition, Object value1, Object value2, String property) {\n" +
            "            if (value1 == null || value2 == null) {\n" +
            "                throw new RuntimeException(\"Between values for \" + property + \" cannot be null\");\n" +
            "            }\n" +
            "            criteria.add(new Criterion(condition, value1, value2));\n" +
            "        }\n" +
            "\n" +
            "@ExampleDefinitionMethod@\n" +
            "    }\n" +
            "\n" +
            "    public static class Criteria extends GeneratedCriteria {\n" +
            "        protected Criteria() {\n" +
            "            super();\n" +
            "        }\n" +
            "    }\n" +
            "\n" +
            "    public static class Criterion {\n" +
            "        private String condition;\n" +
            "\n" +
            "        private Object value;\n" +
            "\n" +
            "        private Object secondValue;\n" +
            "\n" +
            "        private boolean noValue;\n" +
            "\n" +
            "        private boolean singleValue;\n" +
            "\n" +
            "        private boolean betweenValue;\n" +
            "\n" +
            "        private boolean listValue;\n" +
            "\n" +
            "        private String typeHandler;\n" +
            "\n" +
            "        public String getCondition() {\n" +
            "            return condition;\n" +
            "        }\n" +
            "\n" +
            "        public Object getValue() {\n" +
            "            return value;\n" +
            "        }\n" +
            "\n" +
            "        public Object getSecondValue() {\n" +
            "            return secondValue;\n" +
            "        }\n" +
            "\n" +
            "        public boolean isNoValue() {\n" +
            "            return noValue;\n" +
            "        }\n" +
            "\n" +
            "        public boolean isSingleValue() {\n" +
            "            return singleValue;\n" +
            "        }\n" +
            "\n" +
            "        public boolean isBetweenValue() {\n" +
            "            return betweenValue;\n" +
            "        }\n" +
            "\n" +
            "        public boolean isListValue() {\n" +
            "            return listValue;\n" +
            "        }\n" +
            "\n" +
            "        public String getTypeHandler() {\n" +
            "            return typeHandler;\n" +
            "        }\n" +
            "\n" +
            "        protected Criterion(String condition) {\n" +
            "            super();\n" +
            "            this.condition = condition;\n" +
            "            this.typeHandler = null;\n" +
            "            this.noValue = true;\n" +
            "        }\n" +
            "\n" +
            "        protected Criterion(String condition, Object value, String typeHandler) {\n" +
            "            super();\n" +
            "            this.condition = condition;\n" +
            "            this.value = value;\n" +
            "            this.typeHandler = typeHandler;\n" +
            "            if (value instanceof List<?>) {\n" +
            "                this.listValue = true;\n" +
            "            } else {\n" +
            "                this.singleValue = true;\n" +
            "            }\n" +
            "        }\n" +
            "\n" +
            "        protected Criterion(String condition, Object value) {\n" +
            "            this(condition, value, null);\n" +
            "        }\n" +
            "\n" +
            "        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {\n" +
            "            super();\n" +
            "            this.condition = condition;\n" +
            "            this.value = value;\n" +
            "            this.secondValue = secondValue;\n" +
            "            this.typeHandler = typeHandler;\n" +
            "            this.betweenValue = true;\n" +
            "        }\n" +
            "\n" +
            "        protected Criterion(String condition, Object value, Object secondValue) {\n" +
            "            this(condition, value, secondValue, null);\n" +
            "        }\n" +
            "    }\n" +
            "}");
    /**
     * Mapper.xml template string
     */
    public static StringBuilder ZN_TEMPLATE_MAPPER_XML = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n" +
            "<mapper namespace=\"@mapperPackage@.@MoName@Mapper\">\n" +
            "\n" +
            "    <!-- 查询操作时条件 -->\n" +
            "    <sql id=\"Example_Where_Clause\">\n" +
            "        <where>\n" +
            "            <foreach collection=\"oredCriteria\" item=\"criteria\" separator=\"or\">\n" +
            "                <if test=\"criteria.valid\">\n" +
            "                    <trim prefix=\"(\" prefixOverrides=\"and\" suffix=\")\">\n" +
            "                        <foreach collection=\"criteria.criteria\" item=\"criterion\">\n" +
            "                            <choose>\n" +
            "                                <when test=\"criterion.noValue\">\n" +
            "                                    AND ${criterion.condition}\n" +
            "                                </when>\n" +
            "                                <when test=\"criterion.singleValue\">\n" +
            "                                    AND ${criterion.condition} #{criterion.value}\n" +
            "                                </when>\n" +
            "                                <when test=\"criterion.betweenValue\">\n" +
            "                                    AND ${criterion.condition} #{criterion.value} AND #{criterion.secondValue}\n" +
            "                                </when>\n" +
            "                                <when test=\"criterion.listValue\">\n" +
            "                                    AND ${criterion.condition}\n" +
            "                                    <foreach close=\")\" collection=\"criterion.value\" item=\"listItem\" open=\"(\" separator=\",\">\n" +
            "                                        #{listItem}\n" +
            "                                    </foreach>\n" +
            "                                </when>\n" +
            "                            </choose>\n" +
            "                        </foreach>\n" +
            "                    </trim>\n" +
            "                </if>\n" +
            "            </foreach>\n" +
            "        </where>\n" +
            "    </sql>\n" +
            "\n" +
            "    <!-- 更新操作时条件 -->\n" +
            "    <sql id=\"Update_By_Example_Where_Clause\">\n" +
            "        <where>\n" +
            "            <foreach collection=\"example.oredCriteria\" item=\"criteria\" separator=\"or\">\n" +
            "                <if test=\"criteria.valid\">\n" +
            "                    <trim prefix=\"(\" prefixOverrides=\"and\" suffix=\")\">\n" +
            "                        <foreach collection=\"criteria.criteria\" item=\"criterion\">\n" +
            "                            <choose>\n" +
            "                                <when test=\"criterion.noValue\">\n" +
            "                                    AND ${criterion.condition}\n" +
            "                                </when>\n" +
            "                                <when test=\"criterion.singleValue\">\n" +
            "                                    AND ${criterion.condition} #{criterion.value}\n" +
            "                                </when>\n" +
            "                                <when test=\"criterion.betweenValue\">\n" +
            "                                    AND ${criterion.condition} #{criterion.value} AND #{criterion.secondValue}\n" +
            "                                </when>\n" +
            "                                <when test=\"criterion.listValue\">\n" +
            "                                    AND ${criterion.condition}\n" +
            "                                    <foreach close=\")\" collection=\"criterion.value\" item=\"listItem\" open=\"(\" separator=\",\">\n" +
            "                                        #{listItem}\n" +
            "                                    </foreach>\n" +
            "                                </when>\n" +
            "                            </choose>\n" +
            "                        </foreach>\n" +
            "                    </trim>\n" +
            "                </if>\n" +
            "            </foreach>\n" +
            "        </where>\n" +
            "    </sql>\n" +
            "\n" +
            "    <!-- 1.根据条件查询列表 -->\n" +
            "    <select id=\"selectByExample\" parameterType=\"@moExamplePackage@.@MoName@Example\" resultType=\"@moPackage@.@MoName@Mo\">\n" +
            "        SELECT\n" +
            "        <if test=\"distinct\">\n" +
            "            DISTINCT\n" +
            "        </if>\n" +
            "@SelectBaseColumnList@\n" +
            "        FROM `@mo_table_name@`\n" +
            "        <if test=\"_parameter != null\">\n" +
            "            <include refid=\"Example_Where_Clause\"/>\n" +
            "        </if>\n" +
            "        <if test=\"orderByClause != null\">\n" +
            "            ORDER BY ${orderByClause}\n" +
            "        </if>\n" +
            "    </select>\n" +
            "\n" +
            "    <!-- 2.批量更新, 根据主键更新非null字段 -->\n" +
            "    <update id=\"updateBatchByPrimaryKeySelective\" parameterType=\"java.util.List\">\n" +
            "        <foreach collection=\"list\" open=\"\" close=\"\" separator=\";\" item=\"item\">\n" +
            "            UPDATE `@mo_table_name@`\n" +
            "            <set>\n" +
            "@BatchUpdateNonNullFieldByID@\n" +
            "            </set>\n" +
            "            WHERE `id` = #{item.id}\n" +
            "        </foreach>\n" +
            "    </update>\n" +
            "\n" +
            "    <!-- 3.根据条件更新非null字段 -->\n" +
            "    <update id=\"updateByExampleSelective\" parameterType=\"map\">\n" +
            "        UPDATE `@mo_table_name@`\n" +
            "        <set>\n" +
            "@UpdateNonNullFieldByExample@\n" +
            "        </set>\n" +
            "        <if test=\"_parameter != null\">\n" +
            "            <include refid=\"Update_By_Example_Where_Clause\"/>\n" +
            "        </if>\n" +
            "    </update>\n" +
            "\n" +
            "    <!-- 4.根据条件删除记录 -->\n" +
            "    <delete id=\"deleteByExample\" parameterType=\"@moExamplePackage@.@MoName@Example\">\n" +
            "        DELETE FROM `@mo_table_name@`\n" +
            "        <if test=\"_parameter != null\">\n" +
            "            <include refid=\"Example_Where_Clause\"/>\n" +
            "        </if>\n" +
            "    </delete>\n" +
            "\n" +
            "    <!-- 5.根据条件统计记录 -->\n" +
            "    <select id=\"countByExample\" parameterType=\"@moExamplePackage@.@MoName@Example\">\n" +
            "        SELECT\n" +
            "            COUNT(*)\n" +
            "        FROM `@mo_table_name@`\n" +
            "        <if test=\"_parameter != null\">\n" +
            "            <include refid=\"Example_Where_Clause\"/>\n" +
            "        </if>\n" +
            "    </select>\n" +
            "\n" +
            "    <!-- 6.增加单条记录, 返回主键 -->\n" +
            "    <insert id=\"insertWithId\" parameterType=\"@moPackage@.@MoName@Mo\" useGeneratedKeys=\"true\" keyProperty=\"id\">\n" +
            "        INSERT INTO `@mo_table_name@` (\n" +
            "@BaseColumnList@\n" +
            "        )\n" +
            "        values (\n" +
            "@InsertField@\n" +
            "        )\n" +
            "    </insert>\n" +
            "\n" +
            "    <!-- 7.批量增加记录, 返回主键 -->\n" +
            "    <insert id=\"insertBatchWithId\" parameterType=\"java.util.List\" useGeneratedKeys=\"true\" keyProperty=\"id\">\n" +
            "        INSERT INTO `@mo_table_name@` (\n" +
            "@BaseColumnList@\n" +
            "        )\n" +
            "        VALUES\n" +
            "        <foreach collection=\"list\" item=\"item\" separator=\",\">\n" +
            "@BatchInsertField@\n" +
            "        </foreach>\n" +
            "    </insert>\n" +
            "\n" +
            "    <!-- 8.@MoName@列表查询 -->\n" +
            "    <select id=\"query@MoName@MoList\" parameterType=\"@moPackage@.@MoName@Mo\"  resultType=\"@moPackage@.@MoName@Mo\">\n" +
            "        SELECT\n" +
            "@SelectBaseColumnList@\n" +
            "        FROM `@mo_table_name@`\n" +
            "        WHERE 1 = 1\n" +
            "@MoListQuery@\n" +
            "    </select>\n" +
            "\n" +
            "    <!-- 9.根据ID查询对象 -->\n" +
            "    <select id=\"selectByPrimaryKey\" resultType=\"@moPackage@.@MoName@Mo\">\n" +
            "        SELECT\n" +
            "@SelectBaseColumnList@\n" +
            "        FROM `@mo_table_name@`\n" +
            "        WHERE `id` = #{id}\n" +
            "    </select>\n" +
            "\n" +
            "    <!-- 10.根据主键只更新非null字段 -->\n" +
            "    <update id=\"updateByPrimaryKeySelective\" parameterType=\"@moPackage@.@MoName@Mo\">\n" +
            "        UPDATE `@mo_table_name@`\n" +
            "        <set>\n" +
            "@UpdateNonNullFieldByID@\n" +
            "        </set>\n" +
            "        WHERE `id` = #{id}\n" +
            "    </update>\n" +
            "\n" +
            "    <!-- 11.根据ID删除记录 -->\n" +
            "    <delete id=\"deleteByPrimaryKey\">\n" +
            "        DELETE FROM `@mo_table_name@` WHERE `id` = #{id}\n" +
            "    </delete>\n" +
            "\n" +
            "</mapper>");
    /**
     * Service interface template string
     */
    public static StringBuilder ZN_TEMPLATE_SERVICE_INTERFACE = new StringBuilder("package @serviceInterfacePackage@;\n" +
            "\n" +
            "import @moPackage@.@MoName@Mo;\n" +
            "import java.util.List;\n" +
            "\n" +
            "/**\n" +
            " * @MoName@Mo对应的Service接口定义\n" +
            " *\n" +
            " * @author @d8Author@\n" +
            " */\n" +
            "public interface @MoName@Service {\n" +
            "\n" +
            "    /**\n" +
            "     * 插入单条记录\n" +
            "     *\n" +
            "     * @param @uncapitallizeMoName@Mo\n" +
            "     * @return\n" +
            "     */\n" +
            "    int add(@MoName@Mo @uncapitallizeMoName@Mo);\n" +
            "\n" +
            "    /**\n" +
            "     * 批量插入\n" +
            "     *\n" +
            "     * @param @uncapitallizeMoName@MoList\n" +
            "     * @return\n" +
            "     */\n" +
            "    int batchAdd(List<@MoName@Mo> @uncapitallizeMoName@MoList);\n" +
            "\n" +
            "    /**\n" +
            "     * 查询列表, 如果携带分页参数则返回分页后的列表\n" +
            "     *\n" +
            "     * @param @uncapitallizeMoName@Mo\n" +
            "     * @param pageParams 可选分页参数\n" +
            "     * @return\n" +
            "     */\n" +
            "    List<@MoName@Mo> query@MoName@MoList(@MoName@Mo @uncapitallizeMoName@Mo, Integer... pageParams);\n" +
            "\n" +
            "    /**\n" +
            "     * 根据id更新非null字段\n" +
            "     *\n" +
            "     * @param @uncapitallizeMoName@Mo\n" +
            "     * @return\n" +
            "     */\n" +
            "    int updateSelectiveById(@MoName@Mo @uncapitallizeMoName@Mo);\n" +
            "\n" +
            "    /**\n" +
            "     * 批量根据id更新非null字段\n" +
            "     *\n" +
            "     * @param @uncapitallizeMoName@MoList\n" +
            "     * @return\n" +
            "     */\n" +
            "    int batchUpdateSelectiveById(List<@MoName@Mo> @uncapitallizeMoName@MoList);\n" +
            "\n" +
            "    /**\n" +
            "     * 根据id物理删除\n" +
            "     *\n" +
            "     * @param id\n" +
            "     * @return\n" +
            "     */\n" +
            "    <T extends Number> int delete(T id);\n" +
            "\n" +
            "}");
    /**
     * Service implement template string
     */
    public static StringBuilder ZN_TEMPLATE_SERVICE_IMPL = new StringBuilder("package @serviceImplPackage@;\n" +
            "\n" +
            "import @serviceInterfacePackage@.@MoName@Service;\n" +
            "import @mapperPackage@.@MoName@Mapper;\n" +
            "import @moPackage@.@MoName@Mo;\n" +
            "import com.github.pagehelper.PageHelper;\n" +
            "import org.springframework.stereotype.Service;\n" +
            "import lombok.extern.slf4j.Slf4j;\n" +
            "\n" +
            "import javax.annotation.Resource;\n" +
            "import java.util.List;\n" +
            "import java.util.Objects;\n" +
            "\n" +
            "/**\n" +
            " * @MoName@Mo对应的ServiceImpl\n" +
            " *\n" +
            " * @author @d8Author@\n" +
            " */\n" +
            "@Service\n" +
            "@Slf4j\n" +
            "public class @MoName@ServiceImpl implements @MoName@Service {\n" +
            "\n" +
            "    @Resource\n" +
            "    private @MoName@Mapper @uncapitallizeMoName@Mapper;\n" +
            "\n" +
            "    /**\n" +
            "     * 插入单条记录\n" +
            "     *\n" +
            "     * @param @uncapitallizeMoName@Mo\n" +
            "     * @return\n" +
            "     */\n" +
            "    @Override\n" +
            "    public int add(@MoName@Mo @uncapitallizeMoName@Mo) {\n" +
            "        @uncapitallizeMoName@Mo.setId(null);\n" +
            "        return @uncapitallizeMoName@Mapper.insertWithId(@uncapitallizeMoName@Mo);\n" +
            "    }\n" +
            "\n" +
            "    /**\n" +
            "     * 批量插入\n" +
            "     *\n" +
            "     * @param @uncapitallizeMoName@MoList\n" +
            "     * @return\n" +
            "     */\n" +
            "    @Override\n" +
            "    public int batchAdd(List<@MoName@Mo> @uncapitallizeMoName@MoList) {\n" +
            "        @uncapitallizeMoName@MoList.forEach(item -> item.setId(null));\n" +
            "        return @uncapitallizeMoName@Mapper.insertBatchWithId(@uncapitallizeMoName@MoList);\n" +
            "    }\n" +
            "\n" +
            "    /**\n" +
            "     * 查询列表, 如果携带分页参数则返回分页后的列表\n" +
            "     *\n" +
            "     * @param @uncapitallizeMoName@Mo\n" +
            "     * @param pageParams 可选分页参数\n" +
            "     * @return\n" +
            "     */\n" +
            "    @Override\n" +
            "    public List<@MoName@Mo> query@MoName@MoList(@MoName@Mo @uncapitallizeMoName@Mo, Integer... pageParams) {\n" +
            "        if (Objects.nonNull(pageParams) && pageParams.length > 0) {\n" +
            "            int pageNum = pageParams[0];\n" +
            "            int pageSize = pageParams.length > 1 ? pageParams[1] : 10;\n" +
            "            PageHelper.startPage(pageNum, pageSize);\n" +
            "        }\n" +
            "        return @uncapitallizeMoName@Mapper.query@MoName@MoList(@uncapitallizeMoName@Mo);\n" +
            "    }\n" +
            "\n" +
            "    /**\n" +
            "     * 根据id更新非null字段\n" +
            "     *\n" +
            "     * @param @uncapitallizeMoName@Mo\n" +
            "     * @return\n" +
            "     */\n" +
            "    @Override\n" +
            "    public int updateSelectiveById(@MoName@Mo @uncapitallizeMoName@Mo) {\n" +
            "        return @uncapitallizeMoName@Mapper.updateByPrimaryKeySelective(@uncapitallizeMoName@Mo);\n" +
            "    }\n" +
            "\n" +
            "    /**\n" +
            "     * 批量根据id更新非null字段\n" +
            "     *\n" +
            "     * @param @uncapitallizeMoName@MoList\n" +
            "     * @return\n" +
            "     */\n" +
            "    @Override\n" +
            "    public int batchUpdateSelectiveById(List<@MoName@Mo> @uncapitallizeMoName@MoList) {\n" +
            "        return @uncapitallizeMoName@Mapper.updateBatchByPrimaryKeySelective(@uncapitallizeMoName@MoList);\n" +
            "    }\n" +
            "\n" +
            "    /**\n" +
            "     * 根据id物理删除\n" +
            "     *\n" +
            "     * @param id\n" +
            "     * @return\n" +
            "     */\n" +
            "    @Override\n" +
            "    public <T extends Number> int delete(T id) {\n" +
            "        return @uncapitallizeMoName@Mapper.deleteByPrimaryKey(id);\n" +
            "    }\n" +
            "\n" +
            "}");
    /**
     * Controller template string
     */
    public static StringBuilder ZN_TEMPLATE_CONTROLLER = new StringBuilder("package @controllerPackage@;\n" +
            "\n" +
            "import @serviceInterfacePackage@.@MoName@Service;\n" +
            "import @moPackage@.@MoName@Mo;\n" +
            "import @swaggerMoPackage@.@MoName@Vo;\n" +
            "import com.alibaba.fastjson.JSONObject;\n" +
            "import com.github.pagehelper.PageInfo;\n" +
            "import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;\n" +
            "import com.github.xiaoymin.knife4j.annotations.ApiSort;\n" +
            "import io.swagger.annotations.Api;\n" +
            "import io.swagger.annotations.ApiOperation;\n" +
            "import lombok.extern.slf4j.Slf4j;\n" +
            "import org.springframework.web.bind.annotation.PostMapping;\n" +
            "import org.springframework.web.bind.annotation.RequestBody;\n" +
            "import org.springframework.web.bind.annotation.RestController;\n" +
            "\n" +
            "import javax.annotation.Resource;\n" +
            "import javax.validation.Valid;\n" +
            "import javax.validation.constraints.NotEmpty;\n" +
            "import java.util.ArrayList;\n" +
            "import java.util.List;\n" +
            "\n" +
            "/**\n" +
            " * @MoName@Mo控制器\n" +
            " *\n" +
            " * @author @d8Author@\n" +
            " */\n" +
            "@RestController\n" +
            "@Api(tags = {\"@MoName@Mo模块接口\"})\n" +
            "@ApiSort(0)\n" +
            "@Slf4j\n" +
            "public class @MoName@Controller {\n" +
            "\n" +
            "    @Resource\n" +
            "    private @MoName@Service @uncapitallizeMoName@Service;\n" +
            "\n" +
            "    @PostMapping(value = \"@apiUrlPrefix@/@uncapitallizeMoName@Mo/add\")\n" +
            "    @ApiOperationSupport(order = 1)\n" +
            "    @ApiOperation(value = \"@MoName@Mo新增记录\")\n" +
            "    public Object add(@Valid @RequestBody @MoName@Vo @uncapitallizeMoName@Vo) {\n" +
            "        // 转换数据\n" +
            "        @MoName@Mo @uncapitallizeMoName@Mo = JSONObject.parseObject(JSONObject.toJSONString(@uncapitallizeMoName@Vo), @MoName@Mo.class);\n" +
            "        @uncapitallizeMoName@Service.add(@uncapitallizeMoName@Mo);\n" +
            "        return @uncapitallizeMoName@Mo.getId();\n" +
            "    }\n" +
            "\n" +
            "    @PostMapping(value = \"@apiUrlPrefix@/@uncapitallizeMoName@Mo/batchAdd\")\n" +
            "    @ApiOperationSupport(order = 2)\n" +
            "    @ApiOperation(value = \"@MoName@Mo批量新增\")\n" +
            "    public Object batchAdd(@Valid @RequestBody List<@MoName@Vo> @uncapitallizeMoName@VoList) {\n" +
            "        List<@MoName@Mo> @uncapitallizeMoName@MoList = new ArrayList<>(@uncapitallizeMoName@VoList.size());\n" +
            "        for (@MoName@Vo @uncapitallizeMoName@Vo : @uncapitallizeMoName@VoList) {\n" +
            "            @uncapitallizeMoName@MoList.add(JSONObject.parseObject(JSONObject.toJSONString(@uncapitallizeMoName@Vo), @MoName@Mo.class));\n" +
            "        }\n" +
            "        return @uncapitallizeMoName@Service.batchAdd(@uncapitallizeMoName@MoList);\n" +
            "    }\n" +
            "\n" +
            "    @PostMapping(value = \"@apiUrlPrefix@/@uncapitallizeMoName@Mo/query@MoName@MoList\")\n" +
            "    @ApiOperationSupport(order = 3)\n" +
            "    @ApiOperation(value = \"@MoName@Mo列表查询\")\n" +
            "    public Object query@MoName@MoList(@Valid @RequestBody @MoName@Vo @uncapitallizeMoName@Vo) {\n" +
            "        // 转换数据\n" +
            "        @MoName@Mo @uncapitallizeMoName@Mo = JSONObject.parseObject(JSONObject.toJSONString(@uncapitallizeMoName@Vo), @MoName@Mo.class);\n" +
            "        return @uncapitallizeMoName@Service.query@MoName@MoList(@uncapitallizeMoName@Mo);\n" +
            "    }\n" +
            "\n" +
            "    @PostMapping(value = \"@apiUrlPrefix@/@uncapitallizeMoName@Mo/query@MoName@MoPage\")\n" +
            "    @ApiOperationSupport(order = 4)\n" +
            "    @ApiOperation(value = \"@MoName@Mo分页查询\")\n" +
            "    public Object query@MoName@MoPage(@Valid @RequestBody @MoName@Vo @uncapitallizeMoName@Vo) {\n" +
            "        // 转换数据\n" +
            "        @MoName@Mo @uncapitallizeMoName@Mo = JSONObject.parseObject(JSONObject.toJSONString(@uncapitallizeMoName@Vo), @MoName@Mo.class);\n" +
            "        List<@MoName@Mo> result@MoName@MoList = @uncapitallizeMoName@Service.query@MoName@MoList(@uncapitallizeMoName@Mo, @uncapitallizeMoName@Vo.getPageNum(), @uncapitallizeMoName@Vo.getPageSize());\n" +
            "        return PageInfo.of(result@MoName@MoList);\n" +
            "    }\n" +
            "\n" +
            "    @PostMapping(value = \"@apiUrlPrefix@/@uncapitallizeMoName@Mo/update\")\n" +
            "    @ApiOperationSupport(order = 5)\n" +
            "    @ApiOperation(value = \"@MoName@Mo修改记录\")\n" +
            "    public Object update(@Valid @RequestBody @MoName@Vo @uncapitallizeMoName@Vo) {\n" +
            "        // 转换数据\n" +
            "        @MoName@Mo @uncapitallizeMoName@Mo = JSONObject.parseObject(JSONObject.toJSONString(@uncapitallizeMoName@Vo), @MoName@Mo.class);\n" +
            "        return @uncapitallizeMoName@Service.updateSelectiveById(@uncapitallizeMoName@Mo);\n" +
            "    }\n" +
            "\n" +
            "    @PostMapping(value = \"@apiUrlPrefix@/@uncapitallizeMoName@Mo/batchUpdate\")\n" +
            "    @ApiOperationSupport(order = 5)\n" +
            "    @ApiOperation(value = \"@MoName@Mo批量修改记录\")\n" +
            "    public Object batchUpdate(@NotEmpty @RequestBody List<@MoName@Vo> @uncapitallizeMoName@VoList) {\n" +
            "        // 转换数据\n" +
            "        List<@MoName@Mo> @uncapitallizeMoName@List = new ArrayList<>(@uncapitallizeMoName@VoList.size());\n" +
            "        @uncapitallizeMoName@VoList.forEach(item -> @uncapitallizeMoName@List.add(JSONObject.parseObject(JSONObject.toJSONString(item), @MoName@Mo.class)));\n" +
            "        return @uncapitallizeMoName@Service.batchUpdateSelectiveById(@uncapitallizeMoName@List);\n" +
            "    }\n" +
            "\n" +
            "    @PostMapping(value = \"@apiUrlPrefix@/@uncapitallizeMoName@Mo/delete\")\n" +
            "    @ApiOperationSupport(order = 6)\n" +
            "    @ApiOperation(value = \"@MoName@Mo删除记录\")\n" +
            "    public Object delete(@Valid @RequestBody @MoName@Vo @uncapitallizeMoName@Vo) {\n" +
            "        return @uncapitallizeMoName@Service.delete(@uncapitallizeMoName@Vo.getId());\n" +
            "    }\n" +
            "\n" +
            "\n" +
            "}\n");
    /**
     * Mo template string
     */
    public static StringBuilder TEMPLATE_MO = new StringBuilder("package @moPackage@;\n" +
            "\n" +
            "import lombok.AllArgsConstructor;\n" +
            "import lombok.Data;\n" +
            "import lombok.NoArgsConstructor;\n" +
            "import lombok.experimental.Accessors;\n" +
            "\n" +
            "import java.math.BigDecimal;\n" +
            "import java.time.LocalDateTime;\n" +
            "import java.util.Date;\n" +
            "\n" +
            "/**\n" +
            " * @MoName@Mo\n" +
            " *\n" +
            " * @author @d8Author@\n" +
            " */\n" +
            "@Data\n" +
            "@NoArgsConstructor\n" +
            "@AllArgsConstructor\n" +
            "@Accessors(chain = true)\n" +
            "public class @MoName@Mo {\n" +
            "\n" +
            "@field@\n" +
            "\n" +
            "}");
    /**
     * Swagger enhance VO template string
     */
    public static StringBuilder TEMPLATE_SWAGGER_VO = new StringBuilder("package @swaggerMoPackage@;\n" +
            "\n" +
            "import io.swagger.annotations.ApiModel;\n" +
            "import io.swagger.annotations.ApiModelProperty;\n" +
            "import lombok.AllArgsConstructor;\n" +
            "import lombok.Data;\n" +
            "import lombok.NoArgsConstructor;\n" +
            "import lombok.experimental.Accessors;\n" +
            "\n" +
            "import java.math.BigDecimal;\n" +
            "import java.time.LocalDateTime;\n" +
            "import java.util.Date;\n" +
            "\n" +
            "/**\n" +
            " * @MoName@Mo with SwaggerApi Enhance Vo\n" +
            " *\n" +
            " * @author @d8Author@\n" +
            " */\n" +
            "@Data\n" +
            "@NoArgsConstructor\n" +
            "@AllArgsConstructor\n" +
            "@Accessors(chain = true)\n" +
            "@ApiModel\n" +
            "public class @MoName@Vo {\n" +
            "\n" +
            "@swaggerField@\n" +
            "\n" +
            "}");
    /**
     * Sql template string
     */
    public static StringBuilder TEMPLATE_MO_SQL = new StringBuilder("\n" +
            "-- ----------------------------\n" +
            "-- D8ger-Sql-Auto-Generated\n" +
            "-- Table structure for `@mo_table_name@`\n" +
            "-- @author @d8Author@\n" +
            "-- ----------------------------\n" +
            "-- DROP TABLE IF EXISTS `@mo_table_name@`;\n" +
            "CREATE TABLE `@mo_table_name@`\n" +
            "(\n" +
            "@id@@sql_column@\n" +
            "@create_time@@update_time@\n" +
            ")\n" +
            "    comment '@MoName@' charset = utf8mb4;\n");
    /**
     * Mapper template string
     */
    public static StringBuilder TEMPLATE_MAPPER = new StringBuilder("package @mapperPackage@;\n" +
            "\n" +
            "import @moExamplePackage@.@MoName@Example;\n" +
            "import @moPackage@.@MoName@Mo;\n" +
            "import org.apache.ibatis.annotations.Param;\n" +
            "import org.apache.ibatis.annotations.Mapper;\n" +
            "\n" +
            "import java.util.List;\n" +
            "\n" +
            "/**\n" +
            " * @MoName@Mo Database Mapper\n" +
            " *\n" +
            " * @author @d8Author@\n" +
            " */\n" +
            "@Mapper\n" +
            "public interface @MoName@Mapper {\n" +
            "\n" +
            "    /**\n" +
            "     * Query list by criteria, supporting all conditions for querying a single table\n" +
            "     *\n" +
            "     * @param @uncapitallizeMoName@Example\n" +
            "     * @return\n" +
            "     */\n" +
            "    List<@MoName@Mo> selectByExample(@MoName@Example @uncapitallizeMoName@Example);\n" +
            "\n" +
            "    /**\n" +
            "     * Batch update non-null fields by ID for multi-records\n" +
            "     *\n" +
            "     * @param @uncapitallizeMoName@MoList\n" +
            "     * @return\n" +
            "     */\n" +
            "    int updateBatchByPrimaryKeySelective(List<@MoName@Mo> @uncapitallizeMoName@MoList);\n" +
            "\n" +
            "    /**\n" +
            "     * Update non-null fields by ID for a single record\n" +
            "     *\n" +
            "     * @param @uncapitallizeMoName@Mo\n" +
            "     * @param @uncapitallizeMoName@Example\n" +
            "     * @return\n" +
            "     */\n" +
            "    int updateByExampleSelective(@Param(\"record\") @MoName@Mo @uncapitallizeMoName@Mo, @Param(\"example\") @MoName@Example @uncapitallizeMoName@Example);\n" +
            "\n" +
            "    /**\n" +
            "     * Delete a single record by condition\n" +
            "     *\n" +
            "     * @param @uncapitallizeMoName@Example\n" +
            "     * @return\n" +
            "     */\n" +
            "    int deleteByExample(@MoName@Example @uncapitallizeMoName@Example);\n" +
            "\n" +
            "    /**\n" +
            "     * Statistical records by query conditions\n" +
            "     *\n" +
            "     * @param @uncapitallizeMoName@Example\n" +
            "     * @return Number of records\n" +
            "     */\n" +
            "    int countByExample(@MoName@Example @uncapitallizeMoName@Example);\n" +
            "\n" +
            "    /**\n" +
            "     * Add a single record, and set the ID for the input Mo parameter\n" +
            "     *\n" +
            "     * @param @uncapitallizeMoName@Mo\n" +
            "     * @return\n" +
            "     */\n" +
            "    int insertWithId(@MoName@Mo @uncapitallizeMoName@Mo);\n" +
            "\n" +
            "    /**\n" +
            "     * Batch add records, and set the ID for the input MoList parameters\n" +
            "     *\n" +
            "     * @param @uncapitallizeMoName@MoList\n" +
            "     * @return\n" +
            "     */\n" +
            "    int insertBatchWithId(List<@MoName@Mo> @uncapitallizeMoName@MoList);\n" +
            "\n" +
            "    /**\n" +
            "     * @MoName@ query list\n" +
            "     *\n" +
            "     * @param @uncapitallizeMoName@Mo\n" +
            "     * @return\n" +
            "     */\n" +
            "    List<@MoName@Mo> query@MoName@MoList(@MoName@Mo @uncapitallizeMoName@Mo);\n" +
            "\n" +
            "    /**\n" +
            "     * Query just one record by ID\n" +
            "     *\n" +
            "     * @param id\n" +
            "     * @return\n" +
            "     */\n" +
            "    <T extends Number> @MoName@Mo selectByPrimaryKey(T id);\n" +
            "\n" +
            "    /**\n" +
            "     * Update non-null fields by ID for a single record\n" +
            "     *\n" +
            "     * @param @uncapitallizeMoName@Mo\n" +
            "     * @return\n" +
            "     */\n" +
            "    int updateByPrimaryKeySelective(@MoName@Mo @uncapitallizeMoName@Mo);\n" +
            "\n" +
            "    /**\n" +
            "     * Delete a record by ID\n" +
            "     *\n" +
            "     * @param id\n" +
            "     * @return\n" +
            "     */\n" +
            "    <T extends Number> int deleteByPrimaryKey(T id);\n" +
            "}");
    /**
     * Example template string
     */
    public static StringBuilder TEMPLATE_MO_EXAMPLE = new StringBuilder("package @moExamplePackage@;\n" +
            "\n" +
            "import java.math.BigDecimal;\n" +
            "import java.time.LocalDateTime;\n" +
            "import java.util.ArrayList;\n" +
            "import java.util.Date;\n" +
            "import java.util.List;\n" +
            "\n" +
            "/**\n" +
            " * @MoName@Mo with multi-conditions object, Example\n" +
            " *\n" +
            " * @author @d8Author@\n" +
            " */\n" +
            "public class @MoName@Example {\n" +
            "\n" +
            "    protected String orderByClause;\n" +
            "\n" +
            "    protected boolean distinct;\n" +
            "\n" +
            "    protected List<Criteria> oredCriteria;\n" +
            "\n" +
            "    public @MoName@Example() {\n" +
            "        oredCriteria = new ArrayList<>();\n" +
            "    }\n" +
            "\n" +
            "    public void setOrderByClause(String orderByClause) {\n" +
            "        this.orderByClause = orderByClause;\n" +
            "    }\n" +
            "\n" +
            "    public String getOrderByClause() {\n" +
            "        return orderByClause;\n" +
            "    }\n" +
            "\n" +
            "    public void setDistinct(boolean distinct) {\n" +
            "        this.distinct = distinct;\n" +
            "    }\n" +
            "\n" +
            "    public boolean isDistinct() {\n" +
            "        return distinct;\n" +
            "    }\n" +
            "\n" +
            "    public List<Criteria> getOredCriteria() {\n" +
            "        return oredCriteria;\n" +
            "    }\n" +
            "\n" +
            "    public void or(Criteria criteria) {\n" +
            "        oredCriteria.add(criteria);\n" +
            "    }\n" +
            "\n" +
            "    public Criteria or() {\n" +
            "        Criteria criteria = createCriteriaInternal();\n" +
            "        oredCriteria.add(criteria);\n" +
            "        return criteria;\n" +
            "    }\n" +
            "\n" +
            "    public Criteria createCriteria() {\n" +
            "        Criteria criteria = createCriteriaInternal();\n" +
            "        if (oredCriteria.size() == 0) {\n" +
            "            oredCriteria.add(criteria);\n" +
            "        }\n" +
            "        return criteria;\n" +
            "    }\n" +
            "\n" +
            "    protected Criteria createCriteriaInternal() {\n" +
            "        return new Criteria();\n" +
            "    }\n" +
            "\n" +
            "    public void clear() {\n" +
            "        oredCriteria.clear();\n" +
            "        orderByClause = null;\n" +
            "        distinct = false;\n" +
            "    }\n" +
            "\n" +
            "    protected abstract static class GeneratedCriteria {\n" +
            "        protected List<Criterion> criteria;\n" +
            "\n" +
            "        protected GeneratedCriteria() {\n" +
            "            super();\n" +
            "            criteria = new ArrayList<>();\n" +
            "        }\n" +
            "\n" +
            "        public boolean isValid() {\n" +
            "            return criteria.size() > 0;\n" +
            "        }\n" +
            "\n" +
            "        public List<Criterion> getAllCriteria() {\n" +
            "            return criteria;\n" +
            "        }\n" +
            "\n" +
            "        public List<Criterion> getCriteria() {\n" +
            "            return criteria;\n" +
            "        }\n" +
            "\n" +
            "        protected void addCriterion(String condition) {\n" +
            "            if (condition == null) {\n" +
            "                throw new RuntimeException(\"Value for condition cannot be null\");\n" +
            "            }\n" +
            "            criteria.add(new Criterion(condition));\n" +
            "        }\n" +
            "\n" +
            "        protected void addCriterion(String condition, Object value, String property) {\n" +
            "            if (value == null) {\n" +
            "                throw new RuntimeException(\"Value for \" + property + \" cannot be null\");\n" +
            "            }\n" +
            "            criteria.add(new Criterion(condition, value));\n" +
            "        }\n" +
            "\n" +
            "        protected void addCriterion(String condition, Object value1, Object value2, String property) {\n" +
            "            if (value1 == null || value2 == null) {\n" +
            "                throw new RuntimeException(\"Between values for \" + property + \" cannot be null\");\n" +
            "            }\n" +
            "            criteria.add(new Criterion(condition, value1, value2));\n" +
            "        }\n" +
            "\n" +
            "@ExampleDefinitionMethod@\n" +
            "    }\n" +
            "\n" +
            "    public static class Criteria extends GeneratedCriteria {\n" +
            "        protected Criteria() {\n" +
            "            super();\n" +
            "        }\n" +
            "    }\n" +
            "\n" +
            "    public static class Criterion {\n" +
            "        private String condition;\n" +
            "\n" +
            "        private Object value;\n" +
            "\n" +
            "        private Object secondValue;\n" +
            "\n" +
            "        private boolean noValue;\n" +
            "\n" +
            "        private boolean singleValue;\n" +
            "\n" +
            "        private boolean betweenValue;\n" +
            "\n" +
            "        private boolean listValue;\n" +
            "\n" +
            "        private String typeHandler;\n" +
            "\n" +
            "        public String getCondition() {\n" +
            "            return condition;\n" +
            "        }\n" +
            "\n" +
            "        public Object getValue() {\n" +
            "            return value;\n" +
            "        }\n" +
            "\n" +
            "        public Object getSecondValue() {\n" +
            "            return secondValue;\n" +
            "        }\n" +
            "\n" +
            "        public boolean isNoValue() {\n" +
            "            return noValue;\n" +
            "        }\n" +
            "\n" +
            "        public boolean isSingleValue() {\n" +
            "            return singleValue;\n" +
            "        }\n" +
            "\n" +
            "        public boolean isBetweenValue() {\n" +
            "            return betweenValue;\n" +
            "        }\n" +
            "\n" +
            "        public boolean isListValue() {\n" +
            "            return listValue;\n" +
            "        }\n" +
            "\n" +
            "        public String getTypeHandler() {\n" +
            "            return typeHandler;\n" +
            "        }\n" +
            "\n" +
            "        protected Criterion(String condition) {\n" +
            "            super();\n" +
            "            this.condition = condition;\n" +
            "            this.typeHandler = null;\n" +
            "            this.noValue = true;\n" +
            "        }\n" +
            "\n" +
            "        protected Criterion(String condition, Object value, String typeHandler) {\n" +
            "            super();\n" +
            "            this.condition = condition;\n" +
            "            this.value = value;\n" +
            "            this.typeHandler = typeHandler;\n" +
            "            if (value instanceof List<?>) {\n" +
            "                this.listValue = true;\n" +
            "            } else {\n" +
            "                this.singleValue = true;\n" +
            "            }\n" +
            "        }\n" +
            "\n" +
            "        protected Criterion(String condition, Object value) {\n" +
            "            this(condition, value, null);\n" +
            "        }\n" +
            "\n" +
            "        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {\n" +
            "            super();\n" +
            "            this.condition = condition;\n" +
            "            this.value = value;\n" +
            "            this.secondValue = secondValue;\n" +
            "            this.typeHandler = typeHandler;\n" +
            "            this.betweenValue = true;\n" +
            "        }\n" +
            "\n" +
            "        protected Criterion(String condition, Object value, Object secondValue) {\n" +
            "            this(condition, value, secondValue, null);\n" +
            "        }\n" +
            "    }\n" +
            "}");
    /**
     * Sql xml template string
     */
    public static StringBuilder TEMPLATE_MAPPER_XML = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n" +
            "<mapper namespace=\"@mapperPackage@.@MoName@Mapper\">\n" +
            "\n" +
            "    <!-- Conditions during query operation -->\n" +
            "    <sql id=\"Example_Where_Clause\">\n" +
            "        <where>\n" +
            "            <foreach collection=\"oredCriteria\" item=\"criteria\" separator=\"or\">\n" +
            "                <if test=\"criteria.valid\">\n" +
            "                    <trim prefix=\"(\" prefixOverrides=\"and\" suffix=\")\">\n" +
            "                        <foreach collection=\"criteria.criteria\" item=\"criterion\">\n" +
            "                            <choose>\n" +
            "                                <when test=\"criterion.noValue\">\n" +
            "                                    AND ${criterion.condition}\n" +
            "                                </when>\n" +
            "                                <when test=\"criterion.singleValue\">\n" +
            "                                    AND ${criterion.condition} #{criterion.value}\n" +
            "                                </when>\n" +
            "                                <when test=\"criterion.betweenValue\">\n" +
            "                                    AND ${criterion.condition} #{criterion.value} AND #{criterion.secondValue}\n" +
            "                                </when>\n" +
            "                                <when test=\"criterion.listValue\">\n" +
            "                                    AND ${criterion.condition}\n" +
            "                                    <foreach close=\")\" collection=\"criterion.value\" item=\"listItem\" open=\"(\" separator=\",\">\n" +
            "                                        #{listItem}\n" +
            "                                    </foreach>\n" +
            "                                </when>\n" +
            "                            </choose>\n" +
            "                        </foreach>\n" +
            "                    </trim>\n" +
            "                </if>\n" +
            "            </foreach>\n" +
            "        </where>\n" +
            "    </sql>\n" +
            "\n" +
            "    <!-- Conditions during update operation -->\n" +
            "    <sql id=\"Update_By_Example_Where_Clause\">\n" +
            "        <where>\n" +
            "            <foreach collection=\"example.oredCriteria\" item=\"criteria\" separator=\"or\">\n" +
            "                <if test=\"criteria.valid\">\n" +
            "                    <trim prefix=\"(\" prefixOverrides=\"and\" suffix=\")\">\n" +
            "                        <foreach collection=\"criteria.criteria\" item=\"criterion\">\n" +
            "                            <choose>\n" +
            "                                <when test=\"criterion.noValue\">\n" +
            "                                    AND ${criterion.condition}\n" +
            "                                </when>\n" +
            "                                <when test=\"criterion.singleValue\">\n" +
            "                                    AND ${criterion.condition} #{criterion.value}\n" +
            "                                </when>\n" +
            "                                <when test=\"criterion.betweenValue\">\n" +
            "                                    AND ${criterion.condition} #{criterion.value} AND #{criterion.secondValue}\n" +
            "                                </when>\n" +
            "                                <when test=\"criterion.listValue\">\n" +
            "                                    AND ${criterion.condition}\n" +
            "                                    <foreach close=\")\" collection=\"criterion.value\" item=\"listItem\" open=\"(\" separator=\",\">\n" +
            "                                        #{listItem}\n" +
            "                                    </foreach>\n" +
            "                                </when>\n" +
            "                            </choose>\n" +
            "                        </foreach>\n" +
            "                    </trim>\n" +
            "                </if>\n" +
            "            </foreach>\n" +
            "        </where>\n" +
            "    </sql>\n" +
            "\n" +
            "    <!-- 1.Query list by criteria -->\n" +
            "    <select id=\"selectByExample\" parameterType=\"@moExamplePackage@.@MoName@Example\" resultType=\"@moPackage@.@MoName@Mo\">\n" +
            "        SELECT\n" +
            "        <if test=\"distinct\">\n" +
            "            DISTINCT\n" +
            "        </if>\n" +
            "@SelectBaseColumnList@\n" +
            "        FROM `@mo_table_name@`\n" +
            "        <if test=\"_parameter != null\">\n" +
            "            <include refid=\"Example_Where_Clause\"/>\n" +
            "        </if>\n" +
            "        <if test=\"orderByClause != null\">\n" +
            "            ORDER BY ${orderByClause}\n" +
            "        </if>\n" +
            "    </select>\n" +
            "\n" +
            "    <!-- 2.Batch update non-null fields by ID for multi-records -->\n" +
            "    <update id=\"updateBatchByPrimaryKeySelective\" parameterType=\"java.util.List\">\n" +
            "        <foreach collection=\"list\" open=\"\" close=\"\" separator=\";\" item=\"item\">\n" +
            "            UPDATE `@mo_table_name@`\n" +
            "            <set>\n" +
            "@BatchUpdateNonNullFieldByID@\n" +
            "            </set>\n" +
            "            WHERE `id` = #{item.id}\n" +
            "        </foreach>\n" +
            "    </update>\n" +
            "\n" +
            "    <!-- 3.Update non-null fields by ID for a single record -->\n" +
            "    <update id=\"updateByExampleSelective\" parameterType=\"map\">\n" +
            "        UPDATE `@mo_table_name@`\n" +
            "        <set>\n" +
            "@UpdateNonNullFieldByExample@\n" +
            "        </set>\n" +
            "        <if test=\"_parameter != null\">\n" +
            "            <include refid=\"Update_By_Example_Where_Clause\"/>\n" +
            "        </if>\n" +
            "    </update>\n" +
            "\n" +
            "    <!-- 4.Delete a single record by condition -->\n" +
            "    <delete id=\"deleteByExample\" parameterType=\"@moExamplePackage@.@MoName@Example\">\n" +
            "        DELETE FROM `@mo_table_name@`\n" +
            "        <if test=\"_parameter != null\">\n" +
            "            <include refid=\"Example_Where_Clause\"/>\n" +
            "        </if>\n" +
            "    </delete>\n" +
            "\n" +
            "    <!-- 5.Statistical records by query conditions -->\n" +
            "    <select id=\"countByExample\" parameterType=\"@moExamplePackage@.@MoName@Example\">\n" +
            "        SELECT\n" +
            "            COUNT(*)\n" +
            "        FROM `@mo_table_name@`\n" +
            "        <if test=\"_parameter != null\">\n" +
            "            <include refid=\"Example_Where_Clause\"/>\n" +
            "        </if>\n" +
            "    </select>\n" +
            "\n" +
            "    <!-- 6.Add a single record, and return the ID -->\n" +
            "    <insert id=\"insertWithId\" parameterType=\"@moPackage@.@MoName@Mo\" useGeneratedKeys=\"true\" keyProperty=\"id\">\n" +
            "        INSERT INTO `@mo_table_name@` (\n" +
            "@BaseColumnList@\n" +
            "        )\n" +
            "        values (\n" +
            "@InsertField@\n" +
            "        )\n" +
            "    </insert>\n" +
            "\n" +
            "    <!-- 7.Batch add records, and return the IDs -->\n" +
            "    <insert id=\"insertBatchWithId\" parameterType=\"java.util.List\" useGeneratedKeys=\"true\" keyProperty=\"id\">\n" +
            "        INSERT INTO `@mo_table_name@` (\n" +
            "@BaseColumnList@\n" +
            "        )\n" +
            "        VALUES\n" +
            "        <foreach collection=\"list\" item=\"item\" separator=\",\">\n" +
            "@BatchInsertField@\n" +
            "        </foreach>\n" +
            "    </insert>\n" +
            "\n" +
            "    <!-- 8.@MoName@ query list -->\n" +
            "    <select id=\"query@MoName@MoList\" parameterType=\"@moPackage@.@MoName@Mo\"  resultType=\"@moPackage@.@MoName@Mo\">\n" +
            "        SELECT\n" +
            "@SelectBaseColumnList@\n" +
            "        FROM `@mo_table_name@`\n" +
            "        WHERE 1 = 1\n" +
            "@MoListQuery@\n" +
            "    </select>\n" +
            "\n" +
            "    <!-- 9.Query just one record by ID -->\n" +
            "    <select id=\"selectByPrimaryKey\" resultType=\"@moPackage@.@MoName@Mo\">\n" +
            "        SELECT\n" +
            "@SelectBaseColumnList@\n" +
            "        FROM `@mo_table_name@`\n" +
            "        WHERE `id` = #{id}\n" +
            "    </select>\n" +
            "\n" +
            "    <!-- 10.Update non-null fields by ID for a single record -->\n" +
            "    <update id=\"updateByPrimaryKeySelective\" parameterType=\"@moPackage@.@MoName@Mo\">\n" +
            "        UPDATE `@mo_table_name@`\n" +
            "        <set>\n" +
            "@UpdateNonNullFieldByID@\n" +
            "        </set>\n" +
            "        WHERE `id` = #{id}\n" +
            "    </update>\n" +
            "\n" +
            "    <!-- 11.Delete a record by ID -->\n" +
            "    <delete id=\"deleteByPrimaryKey\">\n" +
            "        DELETE FROM `@mo_table_name@` WHERE `id` = #{id}\n" +
            "    </delete>\n" +
            "\n" +
            "</mapper>");
    /**
     * Service interface template string
     */
    public static StringBuilder TEMPLATE_SERVICE_INTERFACE = new StringBuilder("package @serviceInterfacePackage@;\n" +
            "\n" +
            "import @moPackage@.@MoName@Mo;\n" +
            "import java.util.List;\n" +
            "\n" +
            "/**\n" +
            " * @MoName@MoService Interface Definition\n" +
            " *\n" +
            " * @author @d8Author@\n" +
            " */\n" +
            "public interface @MoName@Service {\n" +
            "\n" +
            "    /**\n" +
            "     * Insert a record\n" +
            "     *\n" +
            "     * @param @uncapitallizeMoName@Mo\n" +
            "     * @return\n" +
            "     */\n" +
            "    int add(@MoName@Mo @uncapitallizeMoName@Mo);\n" +
            "\n" +
            "    /**\n" +
            "     * Batch insert records\n" +
            "     *\n" +
            "     * @param @uncapitallizeMoName@MoList\n" +
            "     * @return\n" +
            "     */\n" +
            "    int batchAdd(List<@MoName@Mo> @uncapitallizeMoName@MoList);\n" +
            "\n" +
            "    /**\n" +
            "     * Query list, if the paging parameter is carried, return the list after paging\n" +
            "     *\n" +
            "     * @param @uncapitallizeMoName@Mo\n" +
            "     * @param pageParams Optional paging parameters\n" +
            "     * @return\n" +
            "     */\n" +
            "    List<@MoName@Mo> query@MoName@MoList(@MoName@Mo @uncapitallizeMoName@Mo, Integer... pageParams);\n" +
            "\n" +
            "    /**\n" +
            "     * Update non-null fields by ID for a record\n" +
            "     *\n" +
            "     * @param @uncapitallizeMoName@Mo\n" +
            "     * @return\n" +
            "     */\n" +
            "    int updateSelectiveById(@MoName@Mo @uncapitallizeMoName@Mo);\n" +
            "\n" +
            "    /**\n" +
            "     * Batch Update non-nulls field by ID for multi-records\n" +
            "     *\n" +
            "     * @param @uncapitallizeMoName@MoList\n" +
            "     * @return\n" +
            "     */\n" +
            "    int batchUpdateSelectiveById(List<@MoName@Mo> @uncapitallizeMoName@MoList);\n" +
            "\n" +
            "    /**\n" +
            "     * Delete a record by ID\n" +
            "     *\n" +
            "     * @param id\n" +
            "     * @return\n" +
            "     */\n" +
            "    <T extends Number> int delete(T id);\n" +
            "\n" +
            "}");
    /**
     * Service implement template string
     */
    public static StringBuilder TEMPLATE_SERVICE_IMPL = new StringBuilder("package @serviceImplPackage@;\n" +
            "\n" +
            "import @serviceInterfacePackage@.@MoName@Service;\n" +
            "import @mapperPackage@.@MoName@Mapper;\n" +
            "import @moPackage@.@MoName@Mo;\n" +
            "import com.github.pagehelper.PageHelper;\n" +
            "import org.springframework.stereotype.Service;\n" +
            "import lombok.extern.slf4j.Slf4j;\n" +
            "\n" +
            "import javax.annotation.Resource;\n" +
            "import java.util.List;\n" +
            "import java.util.Objects;\n" +
            "\n" +
            "/**\n" +
            " * @MoName@MoService Implement Class\n" +
            " *\n" +
            " * @author @d8Author@\n" +
            " */\n" +
            "@Service\n" +
            "@Slf4j\n" +
            "public class @MoName@ServiceImpl implements @MoName@Service {\n" +
            "\n" +
            "    @Resource\n" +
            "    private @MoName@Mapper @uncapitallizeMoName@Mapper;\n" +
            "\n" +
            "    /**\n" +
            "     * Insert a record\n" +
            "     *\n" +
            "     * @param @uncapitallizeMoName@Mo\n" +
            "     * @return\n" +
            "     */\n" +
            "    @Override\n" +
            "    public int add(@MoName@Mo @uncapitallizeMoName@Mo) {\n" +
            "        @uncapitallizeMoName@Mo.setId(null);\n" +
            "        return @uncapitallizeMoName@Mapper.insertWithId(@uncapitallizeMoName@Mo);\n" +
            "    }\n" +
            "\n" +
            "    /**\n" +
            "     * Batch insert records\n" +
            "     *\n" +
            "     * @param @uncapitallizeMoName@MoList\n" +
            "     * @return\n" +
            "     */\n" +
            "    @Override\n" +
            "    public int batchAdd(List<@MoName@Mo> @uncapitallizeMoName@MoList) {\n" +
            "        @uncapitallizeMoName@MoList.forEach(item -> item.setId(null));\n" +
            "        return @uncapitallizeMoName@Mapper.insertBatchWithId(@uncapitallizeMoName@MoList);\n" +
            "    }\n" +
            "\n" +
            "    /**\n" +
            "     * Query list, if the paging parameter is carried, return the list after paging\n" +
            "     *\n" +
            "     * @param @uncapitallizeMoName@Mo\n" +
            "     * @param pageParams Optional paging parameters\n" +
            "     * @return\n" +
            "     */\n" +
            "    @Override\n" +
            "    public List<@MoName@Mo> query@MoName@MoList(@MoName@Mo @uncapitallizeMoName@Mo, Integer... pageParams) {\n" +
            "        if (Objects.nonNull(pageParams) && pageParams.length > 0) {\n" +
            "            int pageNum = pageParams[0];\n" +
            "            int pageSize = pageParams.length > 1 ? pageParams[1] : 10;\n" +
            "            PageHelper.startPage(pageNum, pageSize);\n" +
            "        }\n" +
            "        return @uncapitallizeMoName@Mapper.query@MoName@MoList(@uncapitallizeMoName@Mo);\n" +
            "    }\n" +
            "\n" +
            "    /**\n" +
            "     * Update non-null fields by ID for a single record\n" +
            "     *\n" +
            "     * @param @uncapitallizeMoName@Mo\n" +
            "     * @return\n" +
            "     */\n" +
            "    @Override\n" +
            "    public int updateSelectiveById(@MoName@Mo @uncapitallizeMoName@Mo) {\n" +
            "        return @uncapitallizeMoName@Mapper.updateByPrimaryKeySelective(@uncapitallizeMoName@Mo);\n" +
            "    }\n" +
            "\n" +
            "    /**\n" +
            "     * Batch Update non-nulls field by ID for multi-records\n" +
            "     *\n" +
            "     * @param @uncapitallizeMoName@MoList\n" +
            "     * @return\n" +
            "     */\n" +
            "    @Override\n" +
            "    public int batchUpdateSelectiveById(List<@MoName@Mo> @uncapitallizeMoName@MoList) {\n" +
            "        return @uncapitallizeMoName@Mapper.updateBatchByPrimaryKeySelective(@uncapitallizeMoName@MoList);\n" +
            "    }\n" +
            "\n" +
            "    /**\n" +
            "     * Delete a record by ID\n" +
            "     *\n" +
            "     * @param id\n" +
            "     * @return\n" +
            "     */\n" +
            "    @Override\n" +
            "    public <T extends Number> int delete(T id) {\n" +
            "        return @uncapitallizeMoName@Mapper.deleteByPrimaryKey(id);\n" +
            "    }\n" +
            "\n" +
            "}");
    /**
     * Controller template string
     */
    public static StringBuilder TEMPLATE_CONTROLLER = new StringBuilder("package @controllerPackage@;\n" +
            "\n" +
            "import @serviceInterfacePackage@.@MoName@Service;\n" +
            "import @moPackage@.@MoName@Mo;\n" +
            "import @swaggerMoPackage@.@MoName@Vo;\n" +
            "import com.alibaba.fastjson.JSONObject;\n" +
            "import com.github.pagehelper.PageInfo;\n" +
            "import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;\n" +
            "import com.github.xiaoymin.knife4j.annotations.ApiSort;\n" +
            "import io.swagger.annotations.Api;\n" +
            "import io.swagger.annotations.ApiOperation;\n" +
            "import lombok.extern.slf4j.Slf4j;\n" +
            "import org.springframework.web.bind.annotation.PostMapping;\n" +
            "import org.springframework.web.bind.annotation.RequestBody;\n" +
            "import org.springframework.web.bind.annotation.RestController;\n" +
            "\n" +
            "import javax.annotation.Resource;\n" +
            "import javax.validation.Valid;\n" +
            "import javax.validation.constraints.NotEmpty;\n" +
            "import java.util.ArrayList;\n" +
            "import java.util.List;\n" +
            "\n" +
            "/**\n" +
            " * @MoName@MoController\n" +
            " *\n" +
            " * @author @d8Author@\n" +
            " */\n" +
            "@RestController\n" +
            "@Api(tags = {\"@MoName@Mo-ModuleAPI\"})\n" +
            "@ApiSort(0)\n" +
            "@Slf4j\n" +
            "public class @MoName@Controller {\n" +
            "\n" +
            "    @Resource\n" +
            "    private @MoName@Service @uncapitallizeMoName@Service;\n" +
            "\n" +
            "    @PostMapping(value = \"@apiUrlPrefix@/@uncapitallizeMoName@Mo/add\")\n" +
            "    @ApiOperationSupport(order = 1)\n" +
            "    @ApiOperation(value = \"@MoName@Mo-AddRecord\")\n" +
            "    public Object add(@Valid @RequestBody @MoName@Vo @uncapitallizeMoName@Vo) {\n" +
            "        // convert Vo to Mo\n" +
            "        @MoName@Mo @uncapitallizeMoName@Mo = JSONObject.parseObject(JSONObject.toJSONString(@uncapitallizeMoName@Vo), @MoName@Mo.class);\n" +
            "        @uncapitallizeMoName@Service.add(@uncapitallizeMoName@Mo);\n" +
            "        return @uncapitallizeMoName@Mo.getId();\n" +
            "    }\n" +
            "\n" +
            "    @PostMapping(value = \"@apiUrlPrefix@/@uncapitallizeMoName@Mo/batchAdd\")\n" +
            "    @ApiOperationSupport(order = 2)\n" +
            "    @ApiOperation(value = \"@MoName@Mo-BatchAddRecords\")\n" +
            "    public Object batchAdd(@Valid @RequestBody List<@MoName@Vo> @uncapitallizeMoName@VoList) {\n" +
            "        List<@MoName@Mo> @uncapitallizeMoName@MoList = new ArrayList<>(@uncapitallizeMoName@VoList.size());\n" +
            "        for (@MoName@Vo @uncapitallizeMoName@Vo : @uncapitallizeMoName@VoList) {\n" +
            "            @uncapitallizeMoName@MoList.add(JSONObject.parseObject(JSONObject.toJSONString(@uncapitallizeMoName@Vo), @MoName@Mo.class));\n" +
            "        }\n" +
            "        return @uncapitallizeMoName@Service.batchAdd(@uncapitallizeMoName@MoList);\n" +
            "    }\n" +
            "\n" +
            "    @PostMapping(value = \"@apiUrlPrefix@/@uncapitallizeMoName@Mo/query@MoName@MoList\")\n" +
            "    @ApiOperationSupport(order = 3)\n" +
            "    @ApiOperation(value = \"@MoName@Mo-QueryList\")\n" +
            "    public Object query@MoName@MoList(@Valid @RequestBody @MoName@Vo @uncapitallizeMoName@Vo) {\n" +
            "        // convert Vo to Mo\n" +
            "        @MoName@Mo @uncapitallizeMoName@Mo = JSONObject.parseObject(JSONObject.toJSONString(@uncapitallizeMoName@Vo), @MoName@Mo.class);\n" +
            "        return @uncapitallizeMoName@Service.query@MoName@MoList(@uncapitallizeMoName@Mo);\n" +
            "    }\n" +
            "\n" +
            "    @PostMapping(value = \"@apiUrlPrefix@/@uncapitallizeMoName@Mo/query@MoName@MoPage\")\n" +
            "    @ApiOperationSupport(order = 4)\n" +
            "    @ApiOperation(value = \"@MoName@Mo-QueryListWithPaging\")\n" +
            "    public Object query@MoName@MoPage(@Valid @RequestBody @MoName@Vo @uncapitallizeMoName@Vo) {\n" +
            "        // convert Vo to Mo\n" +
            "        @MoName@Mo @uncapitallizeMoName@Mo = JSONObject.parseObject(JSONObject.toJSONString(@uncapitallizeMoName@Vo), @MoName@Mo.class);\n" +
            "        List<@MoName@Mo> result@MoName@MoList = @uncapitallizeMoName@Service.query@MoName@MoList(@uncapitallizeMoName@Mo, @uncapitallizeMoName@Vo.getPageNum(), @uncapitallizeMoName@Vo.getPageSize());\n" +
            "        return PageInfo.of(result@MoName@MoList);\n" +
            "    }\n" +
            "\n" +
            "    @PostMapping(value = \"@apiUrlPrefix@/@uncapitallizeMoName@Mo/update\")\n" +
            "    @ApiOperationSupport(order = 5)\n" +
            "    @ApiOperation(value = \"@MoName@Mo-UpdateRecord\")\n" +
            "    public Object update(@Valid @RequestBody @MoName@Vo @uncapitallizeMoName@Vo) {\n" +
            "        // convert Vo to Mo\n" +
            "        @MoName@Mo @uncapitallizeMoName@Mo = JSONObject.parseObject(JSONObject.toJSONString(@uncapitallizeMoName@Vo), @MoName@Mo.class);\n" +
            "        return @uncapitallizeMoName@Service.updateSelectiveById(@uncapitallizeMoName@Mo);\n" +
            "    }\n" +
            "\n" +
            "    @PostMapping(value = \"@apiUrlPrefix@/@uncapitallizeMoName@Mo/batchUpdate\")\n" +
            "    @ApiOperationSupport(order = 5)\n" +
            "    @ApiOperation(value = \"@MoName@Mo-BatchUpdateRecords\")\n" +
            "    public Object batchUpdate(@NotEmpty @RequestBody List<@MoName@Vo> @uncapitallizeMoName@VoList) {\n" +
            "        // convert Vo to Mo\n" +
            "        List<@MoName@Mo> @uncapitallizeMoName@List = new ArrayList<>(@uncapitallizeMoName@VoList.size());\n" +
            "        @uncapitallizeMoName@VoList.forEach(item -> @uncapitallizeMoName@List.add(JSONObject.parseObject(JSONObject.toJSONString(item), @MoName@Mo.class)));\n" +
            "        return @uncapitallizeMoName@Service.batchUpdateSelectiveById(@uncapitallizeMoName@List);\n" +
            "    }\n" +
            "\n" +
            "    @PostMapping(value = \"@apiUrlPrefix@/@uncapitallizeMoName@Mo/delete\")\n" +
            "    @ApiOperationSupport(order = 6)\n" +
            "    @ApiOperation(value = \"@MoName@Mo-DeleteRecord\")\n" +
            "    public Object delete(@Valid @RequestBody @MoName@Vo @uncapitallizeMoName@Vo) {\n" +
            "        return @uncapitallizeMoName@Service.delete(@uncapitallizeMoName@Vo.getId());\n" +
            "    }\n" +
            "\n" +
            "\n" +
            "}\n");
    /**
     * Config template string
     */
    public static String TEMPLATE_D8GER = "# The default author called 'D8ger', but it's highly recommended to modify\n" +
            "author=D8ger\n" +
            "\n" +
            "\n" +
            "#### Basic generate, support two item configs such as:\n" +
            "#### autoCreateMo=true, /src/main/java/com/xyz/caofancpu/d8ger/test/Mo\n" +
            "#### ATTENTION: if you config directory path, you should make sure it exists first\n" +
            "#### WARNING: if you config error, the auto code file will put into the origin directory named 'D8AutoCode'\n" +
            "#### THEN plugin will create Mo.java file\n" +
            "#### AND put the file into 'com.xyz.caofancpu.d8ger.test.Mo' directory\n" +
            "#### UNDER current project source directory which include '/src/main/java'\n" +
            "autoCreateMo=true, /src/main/java/com/xyz/caofancpu/d8ger/test/Mo\n" +
            "autoCreateMapper=true, /src/main/java/com/xyz/caofancpu/d8ger/test/Mapper\n" +
            "autoCreateExample=true, /src/main/java/com/xyz/caofancpu/d8ger/test/Mapper/Example\n" +
            "autoCreateXML=true, /template\n" +
            "autoCreateDefinitionSQL=true, /doc\n" +
            "\n" +
            "\n" +
            "#### Enhance generate, also support two item configs, please refer Basic generate\n" +
            "autoCreateSwaggerMo=true, /src/main/java/com/xyz/caofancpu/d8ger/test/Vo\n" +
            "autoCreateServiceInterface=true, /src/main/java/com/xyz/caofancpu/d8ger/test/Service\n" +
            "autoCreateServiceImpl=true, /src/main/java/com/xyz/caofancpu/d8ger/test/Service/Impl\n" +
            "autoCreateController=true, /src/main/java/com/xyz/caofancpu/d8ger/test/Controller\n" +
            "\n" +
            "\n" +
            "#### Other normal binary config\n" +
            "autoFormatStyle=true\n" +
            "\n" +
            "\n" +
            "#### Api path prefix\n" +
            "# apiUrlPrefix=/D8ger\n" +
            "\n" +
            "\n" +
            "#### Language just for EN(English) and ZN(Chinese Simplified)\n" +
            "# locale=EN";

    public static String TEMPLATE_REGEX = "0.About details, please refer https://github.com/caofanCPU/JavaVerbalExpressions\n" +
            "Usage, static pattern can improve performance in regex searching:\n" +
            "    public static final Pattern XXX_REGEX = Pattern.compile(\"...Regex string...\");\n" +
            "\n" +
            "1.Whitespace Regex\n" +
            "((?:\\s)+)\n" +
            "\n" +
            "2.One or more newlines Regex\n" +
            "(?:\\\\n|(?:\\\\r\\\\n))+\n" +
            "\n" +
            "3.Phone validate Regex\n" +
            "^1[0-9]{10}$\n" +
            "\n" +
            "4.Email validate Regex\n" +
            "^([a-z0-9A-Z]+[-|\\\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\\\.)+[a-zA-Z]{2,}$\n" +
            "\n" +
            "5.Password validate, rules: digital, uppercase, lowercase, special character >= 3 species\n" +
            "^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z\\\\W_]+$)(?![a-z0-9]+$)(?![a-z\\\\W_]+$)(?![0-9\\\\W_]+$)[a-zA-Z0-9\\\\W_]{8,30}$\n" +
            "\n" +
            "6.Keyword detect Regex, for example, position[may be have space]=[may be have space][may be have digital]\n" +
            "(?:position)(?:\\\\s)*(?:\\\\=)(?:\\\\s)*(?:\\\\d)*\n" +
            "\n" +
            "7.No '_' and begin with [A-Z] in word Regex\n" +
            "^(?!_)(?:[A-Z])[a-zA-Z0-9\\\\W]+$\n" +
            "\n" +
            "8.No upper case in word Regex\n" +
            "^(?![A-Z])[a-z0-9\\\\W_]+$\n" +
            "\n" +
            "9.No lower case in word Regex\n" +
            "^(?![a-z])[A-Z0-9\\\\W_]+$\n" +
            "\n" +
            "10.'www' Url detect Regex\n" +
            "^(?:http)(?:s)?(?:\\:\\/\\/)(?:www\\.)?(?:[^\\ ]*)$\n" +
            "\n" +
            "11.IDEA Skills\n" +
            "- 11.1 For multi-lines, how to add some same fix characters?\n" +
            " ResearchRegex@   (?:\\n|(?:\\r\\n))+\n" +
            " ReplaceString@   ,\\r\\n\n" +
            "\n" +
            "- 11.2 For multi-lines, especially for SQL batch replacing, such as:\n" +
            "    - zcy_cf,  --> WE'RE(zcy_cf) AS zcy_cf,\n" +
            "    - cf_zcy   --> WE'RE(cf_zcy) AS cf_zcy\n" +
            "    - note: WE'RE is just a function\n" +
            " I.Clear the character ','\n" +
            "  ResearchRegex@   (?:,)+\n" +
            "  ReplaceString@   [EMPTY]\n" +
            " II.Batch replace\n" +
            "  ResearchRegex@   ((?:\\w+)+)\n" +
            "  ReplaceString@   WE'RE($0) AS $0,\n" +
            " III.Clear the rare character ',' at the last line\n" +
            "\n" +
            "- 11.3 For multi-lines, especially for SQL batch reduce-replacing, such as:\n" +
            "    - WE'RE(zcy_cf) AS zcy_cf,  --> zcy_cf,\n" +
            "    - WE'RE(cf_zcy) AS cf_zcy   --> cf_zcy\n" +
            "    - note: WE'RE is just a function\n" +
            " I.Handle prefix\n" +
            "  ResearchRegex@   (?:WE'RE\\()+\n" +
            "  ReplaceString@   [EMPTY]\n" +
            " II.Handle suffix\n" +
            "  ResearchRegex@   (?:\\))(?:[^\\,]*)\n" +
            "  ReplaceString@   [EMPTY]\n" +
            " III.Watch out whether need to clear the rare character ',' at the last line\n" +
            "\n" +
            "\n";

    public static String TEMPLATE_NASA = "=========================================NASA=========================================\n" +
            "Note: 1.D8ger-ALIGN(included by character '@') is the config keyword of this text,\n" +
            "      which these context included will be ignored;\n" +
            "      2.Multi-lines to be handled must include ','\n" +
            "      as the split keyword\n" +
            "      3.config example:\n" +
            "      - @<prefix=D8(>@         , add 'D8(' before the start of each line\n" +
            "      - @<suffix=)>@           , add ')' after the end of each line\n" +
            "      - @<alignStyle=LEFT>@    , you can config CENTER, RIGHT too\n" +
            "      - @<formatSQL=false>@    , if config SQL then it will append 'AS' alias name\n" +
            "      - @<formatAsCamel=false>@ , special for SQL column alias camel name\n" +
            "      4.As example below, one handled what you will find like this:\n" +
            "      first_name,       --> D8(first_name)        AS  firstName,\n" +
            "      current_age,      --> D8(current_age)       AS  currentAge,\n" +
            "      blog_url,         --> D8(blog_url)          AS  blogUrl,\n" +
            "      graduated_school, --> D8(graduated_school)  AS  graduatedSchool,\n" +
            "      total_assets      --> D8(total_assets)      AS  totalAssets\n" +
            "=========================================NASA=========================================\n" +
            "The next line is very import below, do not modify anything or you'll get nothing\n" +
            "@D8ger-ALIGN@\n" +
            "\n" +
            "\n" +
            "first_name,\n" +
            "current_age,\n" +
            "blog_url,\n" +
            "graduated_school,\n" +
            "total_assets\n";

    public static String TEMPLATE_END = "=========================================END=========================================\n" +
            "Note: 1.D8ger-ALIGN(included by character '@') is the config keyword of this text,\n" +
            "      which these context included will be ignored;\n" +
            "      2.Considering compatibility separator, Multi-lines to be handled\n" +
            "      must include ',' or line break or '，' as the split keyword.\n" +
            "      3.config example:\n" +
            "      - @<alignStyle=LEFT>@    , LEFT(default) and you can config CENTER, RIGHT too\n" +
            "      - @<algorithmType=1>@    , 1(default) as 'AES' and 2 as 'PinYin'\n" +
            "      - @<operateType=0>@      , 1 as encrypt, 2 as decrypt\n" +
            "                                 and 0(default) is encrypt + decrypt for complete\n" +
            "      4.As example below, one handled what you will find like this:\n" +
            "      When algorithmType=1 && operateType = 0\n" +
            "        MyName -->(first AES encryption) d8gerX==\n" +
            "               -->(then AES decryption)  MyName\n" +
            "      When algorithmType=2 && operateType = 0\n" +
            "        帝八哥  -->(first Fetch Chinese PinYin) dibage\n" +
            "               -->(then PinYin encryption)     d8gerY==\n" +
            "               -->(last PinYin decryption)     dibage\n" +
            "      Other case, just refer the two above.\n" +
            "=========================================END=========================================\n" +
            "The next line is very import below, do not modify anything or you'll get nothing\n" +
            "@D8ger-END@\n" +
            "\n" +
            "\n" +
            "AName";

    /**
     * Template rendering
     *
     * @param template
     * @param bindingMap
     * @return
     */
    public static StringBuilder render(@NonNull StringBuilder template, Map<String, StringBuilder> bindingMap) {
        StringBuilder result = template;
        for (Map.Entry<String, StringBuilder> entry : bindingMap.entrySet()) {
            VerbalExpression regex = VerbalExpressionUtil.buildRegex(entry.getKey());
            result = VerbalExpressionUtil.executePatternRex(regex, result, entry.getValue());
        }
        return result;
    }

}
