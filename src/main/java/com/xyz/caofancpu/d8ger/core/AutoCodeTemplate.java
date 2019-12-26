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
    public static final String TEMPLATE_MO = "\n" +
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
            "}\n";

    /**
     * SwaggerMO模板字符串
     */
    public static final String TEMPLATE_SWAGGER_MO = "\n" +
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
            "public class @MoName@ {\n" +
            "\n" +
            "@swaggerField@\n" +
            "\n" +
            "}\n";

    /**
     * Mapper模板字符串
     */
    public static final String TEMPLATE_MAPPER = "\n" +
            "import org.apache.ibatis.annotations.Mapper;\n" +
            "\n" +
            "import java.util.List;\n" +
            "\n" +
            "/**\n" +
            " * TODO: 注释\n\n" +
            " * @author @d8Author@\n" +
            " */\n" +
            "@Mapper\n" +
            "public interface @MoName@Mapper {\n" +
            "\n" +
            "    /**\n" +
            "     * 添加记录\n" +
            "     *\n" +
            "     * @param @uncapitallizeMoName@\n" +
            "     * @return 影响行数, 主键ID已置入参数@uncapitallizeMoName@\n" +
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
            "     * 更新记录\n" +
            "     *\n" +
            "     * @param @uncapitallizeMoName@\n" +
            "     * @return\n" +
            "     */\n" +
            "    int updateSelective(@MoName@ @uncapitallizeMoName@);\n" +
            "\n" +
            "    /**\n" +
            "     * [WARN]: 主键id物理删除\n" +
            "     *\n" +
            "     * @param id\n" +
            "     * @return\n" +
            "     */\n" +
            "    int deleteById(Integer id);\n" +
            "\n" +
            "}\n";


    /**
     * Mapper.xml模板字符串
     */
    public static final String TEMPLATE_MAPPER_XML = "";


    /**
     * ServiceInterface模板字符串
     */
    public static final String TEMPLATE_SERVICE_INTERFACE = "\n" +
            "import java.util.List;\n" +
            "\n" +
            "/**\n" +
            " * TODO: 注释\n\n" +
            " * @author @d8Author@\n" +
            " */\n" +
            "public interface @MoName@Service {\n" +
            "\n" +
            "    /**\n" +
            "     * 添加记录\n" +
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
            "     * 更新记录\n" +
            "     *\n" +
            "     * @param @uncapitallizeMoName@\n" +
            "     * @return\n" +
            "     */\n" +
            "    int updateSelective(@MoName@ @uncapitallizeMoName@);\n" +
            "\n" +
            "    /**\n" +
            "     * [WARN]: 主键id物理删除\n" +
            "     *\n" +
            "     * @param id\n" +
            "     * @return\n" +
            "     */\n" +
            "    int deleteById(Integer id);\n" +
            "\n" +
            "}\n";


    /**
     * ServiceImpl模板字符串
     */
    public static final String TEMPLATE_SERVICE_IMPL = "\n" +
            "import org.springframework.stereotype.Service;\n" +
            "\n" +
            "import javax.annotation.Resource;\n" +
            "import java.util.List;\n" +
            "\n" +
            "/**\n" +
            " * TODO: 注释\n\n" +
            " * @author @d8Author@\n" +
            " */\n" +
            "@Service\n" +
            "public class @MoName@ServiceImpl implements @MoName@Service {\n" +
            "\n" +
            "    @Resource\n" +
            "    private @MoName@Mapper @uncapitallizeMoName@Mapper;\n" +
            "\n" +
            "    /**\n" +
            "     * 添加记录\n" +
            "     *\n" +
            "     * @param @uncapitallizeMoName@\n" +
            "     * @return\n" +
            "     */\n" +
            "    @Override\n" +
            "    public int add(@MoName@ @uncapitallizeMoName@) {\n" +
            "        return @uncapitallizeMoName@Mapper.add(@uncapitallizeMoName@);\n" +
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
            "        return @uncapitallizeMoName@Mapper.batchAdd(@uncapitallizeMoName@List);\n" +
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
            "     * 更新记录\n" +
            "     *\n" +
            "     * @param @uncapitallizeMoName@\n" +
            "     * @return\n" +
            "     */\n" +
            "    @Override\n" +
            "    public int updateSelective(@MoName@ @uncapitallizeMoName@) {\n" +
            "        return @uncapitallizeMoName@Mapper.updateSelective(@uncapitallizeMoName@);\n" +
            "    }\n" +
            "\n" +
            "    /**\n" +
            "     * [WARN]: 主键id物理删除\n" +
            "     *\n" +
            "     * @param id\n" +
            "     * @return\n" +
            "     */\n" +
            "    @Override\n" +
            "    public int deleteById(Integer id) {\n" +
            "        return @uncapitallizeMoName@Mapper.deleteById(id);\n" +
            "    }\n" +
            "\n" +
            "}\n";


    /**
     * Controller模板字符串
     */
    public static final String TEMPLATE_CONTROLLER = "";


    /**
     * 渲染模板
     *
     * @param template
     * @param bindingMap
     * @return
     */
    public static String render(@NonNull String template, Map<String, String> bindingMap) {
        String result = template;
        for (Map.Entry<String, String> entry : bindingMap.entrySet()) {
            VerbalExpression regex = VerbalExpressionUtil.buildRegex(entry.getKey());
            result = VerbalExpressionUtil.executePatternRex(regex, result, entry.getValue());
        }
        return result;
    }

}
