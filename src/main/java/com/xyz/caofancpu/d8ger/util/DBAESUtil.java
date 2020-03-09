package com.xyz.caofancpu.d8ger.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.security.Security;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class DBAESUtil {
    private static final int N = 4;
    /**
     * 密钥算法
     */
    private static final String ALGORITHM = "AES";
    /**
     * 加解密算法/工作模式/填充方式
     */
    private static final String ALGORITHM_MODE_PADDING = "AES/ECB/PKCS7Padding";
    private static Map<Character, Integer> letterToValue = new HashMap<>(64);
    /**
     * 生成key
     */
    private static String ek = "ArNgOfn1PjnxGZULcXbZOyhm6AxeoxFp";
    private static SecretKeySpec secretKeySpec = new SecretKeySpec(MD5Util.MD5Encode(ek, "UTF-8").toLowerCase().getBytes(), ALGORITHM);
    private static ThreadLocal<Cipher> encryptionCipher = ThreadLocal.withInitial(() -> {
        Cipher encryptionCipher = null;
        try {
            Security.addProvider(new BouncyCastleProvider());
            // 创建密码器
            encryptionCipher = Cipher.getInstance(ALGORITHM_MODE_PADDING, "BC");
            // 初始化
            encryptionCipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        } catch (Exception e) {
            log.error("加密初始化异常", e);
        }
        return encryptionCipher;
    });
    private static ThreadLocal<Cipher> decryptionCipher = ThreadLocal.withInitial(() -> {
        Cipher decryptionCipher = null;
        try {
            Security.addProvider(new BouncyCastleProvider());
            decryptionCipher = Cipher.getInstance(ALGORITHM_MODE_PADDING, "BC");
            decryptionCipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        } catch (Exception e) {
            log.error("解密初始化异常", e);
        }
        return decryptionCipher;
    });

    static {
        String errorString = "Failed manually overriding key-length permissions.";
        int newMaxKeyLength;
        try {
            if ((newMaxKeyLength = Cipher.getMaxAllowedKeyLength("AES")) < 256) {
                Class<?> c = Class.forName("javax.crypto.CryptoAllPermissionCollection");
                Constructor<?> con = c.getDeclaredConstructor();
                con.setAccessible(true);
                Object allPermissionCollection = con.newInstance();
                Field f = c.getDeclaredField("all_allowed");
                f.setAccessible(true);
                f.setBoolean(allPermissionCollection, true);

                c = Class.forName("javax.crypto.CryptoPermissions");
                con = c.getDeclaredConstructor();
                con.setAccessible(true);
                Object allPermissions = con.newInstance();
                f = c.getDeclaredField("perms");
                f.setAccessible(true);
                ((Map) f.get(allPermissions)).put("*", allPermissionCollection);

                c = Class.forName("javax.crypto.JceSecurityManager");
                f = c.getDeclaredField("defaultPolicy");
                f.setAccessible(true);
                Field mf = Field.class.getDeclaredField("modifiers");
                mf.setAccessible(true);
                mf.setInt(f, f.getModifiers() & ~Modifier.FINAL);
                f.set(null, allPermissions);

                newMaxKeyLength = Cipher.getMaxAllowedKeyLength("AES");
            }
        } catch (Exception e) {
            throw new RuntimeException(errorString, e);
        }
        if (newMaxKeyLength < 256)
            throw new RuntimeException(errorString); // hack failed
    }

    static {
        for (int i = 65; i <= 90; i++) {
            Character c = (char) i;
            letterToValue.put(c, i);
        }
        for (int i = 97; i <= 122; i++) {
            Character c = (char) i;
            letterToValue.put(c, i);
        }
    }

    /**
     * AES加密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static String encryptData(String data)
            throws Throwable {
        if (StringUtils.isEmpty(data)) {
            return data;
        }

        return Base64Util.encode(encryptionCipher.get().doFinal(data.getBytes()));
    }

    public static String encryptDataWithoutException(String data) {
        try {
            return encryptData(data);
        } catch (Throwable e) {
            log.error("解密异常", e);
            return "[空-配置错误]";
        }
    }

    /**
     * AES解密
     * <p>
     * （1）对加密串A做base64解码，得到加密串B
     * （2）用key*对加密串B做AES-256-ECB解密（PKCS7Padding）
     *
     * @param base64Data
     * @return
     * @throws Throwable
     */
    public static String decryptData(String base64Data)
            throws Throwable {
        if (StringUtils.isEmpty(base64Data)) {
            return base64Data;
        }
        return new String(decryptionCipher.get().doFinal(Base64Util.decode(base64Data)));
    }

    public static String decryptDataWithoutException(String base64Data) {
        try {
            return decryptData(base64Data);
        } catch (Throwable e) {
            log.error("解密异常", e);
            return "[空-配置错误]";
        }
    }

    /**
     * 解析拼音
     *
     * @param name
     * @return
     */
    public static String fetchPinYin(String name) {
        return PinyinUtil.getStringPinYin(name);
    }

    /**
     * 加密字符串全拼
     *
     * @param name
     * @return
     */
    public static String encryptionNamePinYin(String name) {
        if (StringUtils.isEmpty(name)) {
            return name;
        }
        String pinYin = fetchPinYin(name);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pinYin.length(); i++) {
            char c = pinYin.charAt(i);
            if (letterToValue.containsKey(c)) {
                // 对拼音字母做了一次移位
                Character newChar = (char) (letterToValue.get(c) + N);
                sb.append(newChar);
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 解密字符串全拼
     *
     * @param data
     * @return
     */
    public static String decryptionNamePinYin(String data) {
        if (StringUtils.isEmpty(data)) {
            return data;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            char c = data.charAt(i);
            int value = c;
            if (value >= 65 && value <= 122 + N) {
                // 对拼音字母做了一次移位
                Character newChar = (char) (value - N);
                sb.append(newChar);
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

}