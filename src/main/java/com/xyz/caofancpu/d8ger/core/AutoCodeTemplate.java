package com.xyz.caofancpu.d8ger.core;

import com.xyz.caofancpu.d8ger.util.VerbalExpressionUtil;
import lombok.NonNull;
import ru.lanwen.verbalregex.VerbalExpression;

import java.util.Map;

/**
 * 代码模板字符串常量
 *
 * @author caofanCPU
 */
public class AutoCodeTemplate {

    /**
     * MO模板字符串
     */
    public static final StringBuilder TEMPLATE_MO = new StringBuilder("package @package@;\n\n" +
            "import lombok.AllArgsConstructor;\n" +
            "import lombok.Data;\n" +
            "import lombok.NoArgsConstructor;\n" +
            "import lombok.experimental.Accessors;\n" +
            "\n" +
            "/**\n" +
            " * TODO: 注释\n" +
            " *\n" +
            " * @author @d8Author@\n" +
            " */\n" +
            "@Data\n" +
            "@NoArgsConstructor\n" +
            "@AllArgsConstructor\n" +
            "@Accessors(chain = true)\n" +
            "public class @MoName@ {\n" +
            "\n" +
            "@field@\n" +
            "\n" +
            "}\n");

    /**
     * SwaggerMO模板字符串
     */
    public static final StringBuilder TEMPLATE_SWAGGER_MO = new StringBuilder("package @package@;\n\n" +
            "import io.swagger.annotations.ApiModel;\n" +
            "import io.swagger.annotations.ApiModelProperty;\n" +
            "import lombok.AllArgsConstructor;\n" +
            "import lombok.Data;\n" +
            "import lombok.NoArgsConstructor;\n" +
            "import lombok.experimental.Accessors;\n" +
            "\n" +
            "/**\n" +
            " * TODO: 注释\n" +
            " *\n" +
            " * @author @d8Author@\n" +
            " */\n" +
            "@Data\n" +
            "@NoArgsConstructor\n" +
            "@AllArgsConstructor\n" +
            "@Accessors(chain = true)\n" +
            "@ApiModel\n" +
            "public class @MoName@Mo {\n" +
            "\n" +
            "@swaggerField@\n" +
            "\n" +
            "}\n");

    /**
     * Sql模板字符串
     */
    public static final StringBuilder TEMPLATE_MO_SQL = new StringBuilder("\n" +
            "-- ----------------------------\n" +
            "-- Table structure for `@mo_table_name@`\n" +
            "-- D8ger-Sql-Auto-Generated\n" +
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
     * Mapper模板字符串
     */
    public static final StringBuilder TEMPLATE_MAPPER = new StringBuilder("package @package@;\n" +
            "\n" +
            "import @package@.@MoName@Example;\n" +
            "import @package@.@MoName@;\n" +
            "import org.apache.ibatis.annotations.Param;\n" +
            "import org.apache.ibatis.annotations.Mapper;\n" +
            "\n" +
            "import java.util.List;\n" +
            "\n" +
            "@Mapper\n" +
            "public interface @MoName@Mapper {\n" +
            "\n" +
            "    /**\n" +
            "     * 根据条件查询列表\n" +
            "     *\n" +
            "     * @param @uncapitallizeMoName@Example\n" +
            "     * @return\n" +
            "     */\n" +
            "    List<@MoName@> selectByExample(@MoName@Example @uncapitallizeMoName@Example);\n" +
            "\n" +
            "    /**\n" +
            "     * 批量更新, 根据主键更新非null字段\n" +
            "     *\n" +
            "     * @param @uncapitallizeMoName@List\n" +
            "     * @return\n" +
            "     */\n" +
            "    int updateBatchByPrimaryKeySelective(List<@MoName@> @uncapitallizeMoName@List);\n" +
            "\n" +
            "    /**\n" +
            "     * 根据条件更新非null字段\n" +
            "     *\n" +
            "     * @param @uncapitallizeMoName@\n" +
            "     * @param @uncapitallizeMoName@Example\n" +
            "     * @return\n" +
            "     */\n" +
            "    int updateByExampleSelective(@Param(\"record\") @MoName@ @uncapitallizeMoName@, @Param(\"example\") @MoName@Example @uncapitallizeMoName@Example);\n" +
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
            "     * @param @uncapitallizeMoName@\n" +
            "     * @return\n" +
            "     */\n" +
            "    int insertWithId(@MoName@ @uncapitallizeMoName@);\n" +
            "\n" +
            "    /**\n" +
            "     * 批量增加记录, 并为入参设置ID\n" +
            "     *\n" +
            "     * @param @uncapitallizeMoName@List\n" +
            "     * @return\n" +
            "     */\n" +
            "    int insertBatchWithId(List<@MoName@> @uncapitallizeMoName@List);\n" +
            "\n" +
            "    /**\n" +
            "     * @MoName@列表查询\n" +
            "     *\n" +
            "     * @param @uncapitallizeMoName@\n" +
            "     * @return\n" +
            "     */\n" +
            "    List<@MoName@> query@MoName@List(@MoName@ @uncapitallizeMoName@);\n" +
            "\n" +
            "    /**\n" +
            "     * 根据ID查询对象\n" +
            "     *\n" +
            "     * @param id\n" +
            "     * @return\n" +
            "     */\n" +
            "    <T extends Number> @MoName@ selectByPrimaryKey(T id);\n" +
            "\n" +
            "    /**\n" +
            "     * 根据主键只更新非null字段\n" +
            "     *\n" +
            "     * @param @uncapitallizeMoName@\n" +
            "     * @return\n" +
            "     */\n" +
            "    int updateByPrimaryKeySelective(@MoName@ @uncapitallizeMoName@);\n" +
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
     * MoExample模板字符串
     */
    public static final StringBuilder TEMPLATE_MO_EXAMPLE = new StringBuilder("package @package@;\n" +
            "\n" +
            "import java.util.ArrayList;\n" +
            "import java.util.Date;\n" +
            "import java.util.List;\n" +
            "\n" +
            "/**\n" +
            " * @MoName@单表操作对象\n" +
            " *\n" +
            " * @author MyBatis Generator\n" +
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
     * Mapper.xml模板字符串
     */
    public static final StringBuilder TEMPLATE_MAPPER_XML = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n" +
            "<mapper namespace=\"@package@.@MoName@Mapper\">\n" +
            "\n" +
            "    <resultMap id=\"BaseResultMap\" type=\"@package@.@MoName@\">\n" +
            "        <id column=\"id\" property=\"id\"/>\n" +
            "@BaseResultMap@\n" +
            "    </resultMap>\n" +
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
            "    <select id=\"selectByExample\" parameterType=\"@package@.@MoName@Example\" resultMap=\"BaseResultMap\">\n" +
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
            "            WHERE id = #{id}\n" +
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
            "    <delete id=\"deleteByExample\" parameterType=\"@package@.ActivityExample\">\n" +
            "        DELETE FROM `@mo_table_name@`\n" +
            "        <if test=\"_parameter != null\">\n" +
            "            <include refid=\"Example_Where_Clause\"/>\n" +
            "        </if>\n" +
            "    </delete>\n" +
            "\n" +
            "    <!-- 5.根据条件统计记录 -->\n" +
            "    <select id=\"countByExample\" parameterType=\"@package@.@MoName@Example\">\n" +
            "        SELECT\n" +
            "            COUNT(*)\n" +
            "        FROM `@mo_table_name@`\n" +
            "        <if test=\"_parameter != null\">\n" +
            "            <include refid=\"Example_Where_Clause\"/>\n" +
            "        </if>\n" +
            "    </select>\n" +
            "\n" +
            "    <!-- 6.增加单条记录, 返回主键 -->\n" +
            "    <insert id=\"insertWithId\" parameterType=\"@package@.@MoName@\" useGeneratedKeys=\"true\" keyProperty=\"id\">\n" +
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
            "    <select id=\"query@MoName@List\" parameterType=\"@package@.@MoName@\"  resultMap=\"BaseResultMap\">\n" +
            "        SELECT\n" +
            "@SelectBaseColumnList@\n" +
            "        FROM `@mo_table_name@`\n" +
            "        WHERE 1 = 1\n" +
            "@MoListQuery@\n" +
            "    </select>\n" +
            "\n" +
            "    <!-- 9.根据ID查询对象 -->\n" +
            "    <select id=\"selectByPrimaryKey\" resultMap=\"BaseResultMap\">\n" +
            "        SELECT\n" +
            "@SelectBaseColumnList@\n" +
            "        FROM `@mo_table_name@`\n" +
            "        WHERE id = #{id}\n" +
            "    </select>\n" +
            "\n" +
            "    <!-- 10.根据主键只更新非null字段 -->\n" +
            "    <update id=\"updateByPrimaryKeySelective\" parameterType=\"@package@.@MoName@\">\n" +
            "        UPDATE `@mo_table_name@`\n" +
            "        <set>\n" +
            "@UpdateNonNullFieldByID@\n" +
            "        </set>\n" +
            "        WHERE id = #{id}\n" +
            "    </update>\n" +
            "\n" +
            "    <!-- 11.根据条件删除记录 -->\n" +
            "    <delete id=\"deleteByPrimaryKey\">\n" +
            "        DELETE FROM `@mo_table_name@` WHERE id = #{id}\n" +
            "    </delete>\n" +
            "\n" +
            "</mapper>");


    /**
     * ServiceInterface模板字符串
     */
    public static final StringBuilder TEMPLATE_SERVICE_INTERFACE = new StringBuilder("package @package@;\n" +
            "\n" +
            "import java.util.List;\n" +
            "\n" +
            "/**\n" +
            " * TODO: 注释\n" +
            " *\n" +
            " * @author @d8Author@\n" +
            " */\n" +
            "public interface @MoName@Service {\n" +
            "\n" +
            "    /**\n" +
            "     * 插入单条记录\n" +
            "     *\n" +
            "     * @param @uncapitallizeMoName@\n" +
            "     * @return\n" +
            "     */\n" +
            "    int add(@MoName@ @uncapitallizeMoName@);\n" +
            "\n" +
            "    /**\n" +
            "     * 批量插入\n" +
            "     *\n" +
            "     * @param @uncapitallizeMoName@List\n" +
            "     * @return\n" +
            "     */\n" +
            "    int batchAdd(List<@MoName@> @uncapitallizeMoName@List);\n" +
            "\n" +
            "    /**\n" +
            "     * 查询列表\n" +
            "     *\n" +
            "     * @param @uncapitallizeMoName@\n" +
            "     * @return\n" +
            "     */\n" +
            "    List<@MoName@> query@MoName@List(@MoName@ @uncapitallizeMoName@);\n" +
            "\n" +
            "    /**\n" +
            "     * 更新非null字段\n" +
            "     *\n" +
            "     * @param @uncapitallizeMoName@\n" +
            "     * @return\n" +
            "     */\n" +
            "    int updateSelectiveById(@MoName@ @uncapitallizeMoName@);\n" +
            "\n" +
            "    /**\n" +
            "     * 根据id物理删除\n" +
            "     *\n" +
            "     * @param id\n" +
            "     * @return\n" +
            "     */\n" +
            "    int delete(Integer id);\n" +
            "\n" +
            "}");


    /**
     * ServiceImpl模板字符串
     */
    public static final StringBuilder TEMPLATE_SERVICE_IMPL = new StringBuilder("package @package@;\n" +
            "\n" +
            "import org.springframework.stereotype.Service;\n" +
            "\n" +
            "import javax.annotation.Resource;\n" +
            "import java.util.List;\n" +
            "\n" +
            "/**\n" +
            " * TODO: 注释\n" +
            " *\n" +
            " * @author @d8Author@\n" +
            " */\n" +
            "@Service\n" +
            "public class @MoName@ServiceImpl implements @MoName@Service {\n" +
            "\n" +
            "    @Resource\n" +
            "    private @MoName@Mapper @uncapitallizeMoName@Mapper;\n" +
            "\n" +
            "    /**\n" +
            "     * 插入单条记录\n" +
            "     *\n" +
            "     * @param @uncapitallizeMoName@\n" +
            "     * @return\n" +
            "     */\n" +
            "    @Override\n" +
            "    public int add(@MoName@ @uncapitallizeMoName@) {\n" +
            "        return @uncapitallizeMoName@Mapper.insertWithId(@uncapitallizeMoName@);\n" +
            "    }\n" +
            "\n" +
            "    /**\n" +
            "     * 批量插入\n" +
            "     *\n" +
            "     * @param @uncapitallizeMoName@List\n" +
            "     * @return\n" +
            "     */\n" +
            "    @Override\n" +
            "    public int batchAdd(List<@MoName@> @uncapitallizeMoName@List) {\n" +
            "        return @uncapitallizeMoName@Mapper.insertBatchWithId(@uncapitallizeMoName@List);\n" +
            "    }\n" +
            "\n" +
            "    /**\n" +
            "     * 查询列表\n" +
            "     *\n" +
            "     * @param @uncapitallizeMoName@\n" +
            "     * @return\n" +
            "     */\n" +
            "    @Override\n" +
            "    public List<@MoName@> query@MoName@List(@MoName@ @uncapitallizeMoName@) {\n" +
            "        return @uncapitallizeMoName@Mapper.query@MoName@List(@uncapitallizeMoName@);\n" +
            "    }\n" +
            "\n" +
            "    /**\n" +
            "     * 更新非null字段\n" +
            "     *\n" +
            "     * @param @uncapitallizeMoName@\n" +
            "     * @return\n" +
            "     */\n" +
            "    @Override\n" +
            "    public int updateSelectiveById(@MoName@ @uncapitallizeMoName@) {\n" +
            "        return @uncapitallizeMoName@Mapper.updateBatchByPrimaryKeySelective(@uncapitallizeMoName@);\n" +
            "    }\n" +
            "\n" +
            "    /**\n" +
            "     * 根据id物理删除\n" +
            "     *\n" +
            "     * @param id\n" +
            "     * @return\n" +
            "     */\n" +
            "    @Override\n" +
            "    public int delete(Integer id) {\n" +
            "        return @uncapitallizeMoName@Mapper.deleteByPrimaryKey(id);\n" +
            "    }\n" +
            "\n" +
            "}");


    /**
     * Controller模板字符串
     */
    public static final StringBuilder TEMPLATE_CONTROLLER = new StringBuilder();


    /**
     * 渲染模板
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
