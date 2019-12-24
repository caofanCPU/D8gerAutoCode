package com.xyz.caofancpu.d8ger.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by caofanCPU on 2018/2/26.
 */
@Slf4j
public class FileUtil {

    /**
     * 生成唯一序列标识
     *
     * @return
     */
    public static String getUuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 将文件转成base64 字符串
     *
     * @param path 文件路径
     * @return
     * @throws Exception
     */
    public static String encodeBase64(String path)
            throws IOException {
        byte[] buffer = FileUtils.readFileToByteArray(new File(path));
        return Base64.encodeBase64String(buffer);
    }

    /**
     * 保存字符串到指定文件
     *
     * @param content
     * @param fileFullPath
     * @throws FileNotFoundException
     */
    public static void writeStringToFile(String content, String fileFullPath)
            throws IOException {
        File file = new File(fileFullPath);
        FileUtils.writeStringToFile(file, content);
    }

    /**
     * 复制文件
     *
     * @param sourceFileFullPath
     * @param destFileFullPath
     * @throws IOException
     */
    public static void copyFile(String sourceFileFullPath, String destFileFullPath)
            throws IOException {
        File sourceFile = new File(sourceFileFullPath);
        File destFile = new File(destFileFullPath);
        FileUtils.copyFile(sourceFile, destFile);
    }

    public static String readFileToString(String fileFullPath)
            throws IOException {
        File file = new File(fileFullPath);
        return FileUtils.readFileToString(file);
    }

    /**
     * 将base64字符解码保存文件
     *
     * @param base64Code
     * @param fileFullPath
     * @throws Exception
     */

    public static void decodeBase64WithSave(String base64Code, String fileFullPath)
            throws IOException {
        byte[] buffer = Base64.decodeBase64(base64Code);
        FileUtils.writeByteArrayToFile(new File(fileFullPath), buffer);
    }

}
