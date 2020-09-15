package com.xyz.caofancpu.d8ger.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.NonNull;

/**
 * JSON tool
 *
 * @author caofanCPU
 */
public class JSONUtil {

    public static String formatStandardJSON(@NonNull String source) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement element = JsonParser.parseString(source);
        return gson.toJson(element);
    }
}
