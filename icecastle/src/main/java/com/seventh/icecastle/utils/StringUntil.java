package com.seventh.icecastle.utils;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUntil {

    /**
     * 判断字符串是否为空
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        int strLen;
        if (str != null && (strLen = str.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }
            return true;
        } else {
            return true;
        }
    }

    /**
     * 判断字符串是否不为空
     * @param str
     * @return
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * 判断是否为整数
     * @param str
     * @return
     */
    public static boolean isInteger(String str) {
        if (str == null || Objects.equals(str,"")) {
            return false;
        } else {
            int sz = str.length();
            int i = 0;
            if (str.charAt(i) == '-' || str.charAt(i) == '+')  i++;
            for(; i < sz; i++) {
                if (!Character.isDigit(str.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * 判断是否为小数
     * @param str
     * @return
     */
    public static boolean isDecimal(String str) {
        if (str == null || Objects.equals(str,"")) {
            return false;
        } else {
            int sz = str.length();
            int pointCount = 0;
            int i = 0;
            if (str.charAt(i) == '-' || str.charAt(i) == '+')  i++;
            for(; i < sz; i++) {
                if(str.charAt(i) == '.'){
                    if(pointCount++ == 1){
                        return false;
                    }
                    continue;
                } else if (!Character.isDigit(str.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * 验证邮箱
     * @param email
     * @return
     */
    public static boolean checkEmail(String email){
        boolean flag = false;
        try{
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        }catch(Exception e){
            flag = false;
        }
        return flag;
    }
}
