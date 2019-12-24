package com.xyz.caofancpu.d8ger.template;

import com.xyz.caofancpu.d8ger.util.ConstantUtil;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 模板接口
 *
 * @author caofanCPU
 */
public interface AutoCodeGenerateTemplate {
    String MO_NAME_KEY = "D8ger@moName@";
    String MO_UNCAPITALLIZE_NAME_KEY = "D8ger@moUncapitalizeName@";
    String MO_LIST_NAME_KEY = "D8ger@moListName@";
    Map<String, String> storageMoNameKeyMap = new HashMap<>(4, 0.75f);

    /**
     * 构建方法参数名称map
     *
     * @param moNameKey
     * @return
     */
    default void initStorageMoNameKeyMap(@NonNull String moNameKey) {
        storageMoNameKeyMap.put(MO_NAME_KEY, moNameKey);
        storageMoNameKeyMap.put(MO_UNCAPITALLIZE_NAME_KEY, StringUtils.uncapitalize(moNameKey));
        storageMoNameKeyMap.put(MO_LIST_NAME_KEY, storageMoNameKeyMap.get(MO_UNCAPITALLIZE_NAME_KEY).concat(ConstantUtil.LIST));
    }

    /**
     * 由子类解析模板
     *
     * @param moNameKey
     * @return
     */
    String parseTemplate(@NonNull String moNameKey);
}
