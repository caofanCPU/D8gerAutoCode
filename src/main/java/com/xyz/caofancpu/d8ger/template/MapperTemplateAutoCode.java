package com.xyz.caofancpu.d8ger.template;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

/**
 * @author caofanCPU
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class MapperTemplateAutoCode implements AutoCodeGenerateTemplate {

    public static String build(@NonNull String moNameKey) {
        return new MapperTemplateAutoCode().parseTemplate(moNameKey);
    }

    @Override
    public String parseTemplate(@NonNull String moNameKey) {
        initStorageMoNameKeyMap(moNameKey);
        String template = buildTemplate();
        return template.replaceAll(MO_NAME_KEY, storageMoNameKeyMap.get(MO_NAME_KEY))
                .replaceAll(MO_UNCAPITALLIZE_NAME_KEY, storageMoNameKeyMap.get(MO_UNCAPITALLIZE_NAME_KEY))
                .replaceAll(MO_LIST_NAME_KEY, storageMoNameKeyMap.get(MO_LIST_NAME_KEY));
    }

    private String buildTemplate() {
        return "@Mapper\n" +
                "public interface " + storageMoNameKeyMap.get(MO_NAME_KEY) + "Mapper {\n" +
                "\n" +
                "    /**\n" +
                "     * 添加记录\n" +
                "     *\n" +
                "     * @param " + storageMoNameKeyMap.get(MO_UNCAPITALLIZE_NAME_KEY) + "\n" +
                "     * @return\n" +
                "     */\n" +
                "    int add(" + storageMoNameKeyMap.get(MO_NAME_KEY) + " " + storageMoNameKeyMap.get(MO_UNCAPITALLIZE_NAME_KEY) + ");\n" +
                "\n" +
                "    /**\n" +
                "     * 批量插入\n" +
                "     *\n" +
                "     * @param " + storageMoNameKeyMap.get(MO_LIST_NAME_KEY) + "\n" +
                "     * @return\n" +
                "     */\n" +
                "    int batchAdd(List<" + storageMoNameKeyMap.get(MO_NAME_KEY) + ">" + storageMoNameKeyMap.get(MO_LIST_NAME_KEY) + ");\n" +
                "\n" +
                "    /**\n" +
                "     * 查询列表\n" +
                "     *\n" +
                "     * @param " + storageMoNameKeyMap.get(MO_UNCAPITALLIZE_NAME_KEY) + "\n" +
                "     * @return\n" +
                "     */\n" +
                "    List<" + storageMoNameKeyMap.get(MO_NAME_KEY) + "> queryList(" + storageMoNameKeyMap.get(MO_NAME_KEY) + " " + storageMoNameKeyMap.get(MO_UNCAPITALLIZE_NAME_KEY) + ");\n" +
                "\n" +
                "    /**\n" +
                "     * 更新记录\n" +
                "     *\n" +
                "     * @param " + storageMoNameKeyMap.get(MO_UNCAPITALLIZE_NAME_KEY) + "\n" +
                "     * @return\n" +
                "     */\n" +
                "    int update(" + storageMoNameKeyMap.get(MO_NAME_KEY) + " " + storageMoNameKeyMap.get(MO_UNCAPITALLIZE_NAME_KEY) + ");\n" +
                "\n" +
                "    /**\n" +
                "     * [WARN]: 主键id物理删除\n" +
                "     *\n" +
                "     * @param id\n" +
                "     * @return\n" +
                "     */\n" +
                "    <T extends Number> int deleteById(T id);\n" +
                "\n" +
                "}";
    }
}
