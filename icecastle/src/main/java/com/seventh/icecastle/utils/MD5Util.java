package com.seventh.icecastle.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class MD5Util {

    /**
     * 使用MD5加密
     * @param plainText
     * @return
     */
    public static String encryptionByMD5(String plainText) {
        String md5code;
        try {
            byte[] secretBytes = MessageDigest.getInstance("md5").digest(plainText.getBytes());
            md5code = new BigInteger(1, secretBytes).toString(16);
            for (int i = 0; i < 32 - md5code.length(); i++) {
                md5code = "0" + md5code;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return md5code;
    }
}
