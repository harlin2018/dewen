package com.dewen.project.utils;


/**
 * Created by 01468339 on 3/8/2016.
 */
public class TextUtils {

    public static boolean isEmpty(String... values) {
        for(String value : values) {
            if(value == null || value.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param text
     * @return true:the string is null or its content is empty
     */
    public static boolean isNullOrEmpty(String text) {
        return null == text || text.isEmpty();
    }

    public static String trim(String str) {
        return null == str ? null :str.trim();
    }

    /**
     * compare two string whether their content are the same
     * @param str1
     * @param str2
     * @return
     */
    public static boolean equals(String str1, String str2) {
        return !(null == str1 || null == str2) && str1.equals(str2);
    }

    /**
     * compare two string whether their content are the same with ignoring case
     * @param str1
     * @param str2
     * @return
     */
    public static boolean equalsIgnoreCase(String str1, String str2) {
        return !(null == str1 || null == str2) && str1.equalsIgnoreCase(str2);
    }

    public static boolean isNotNullOrEmpty(String text) {
        return null != text && !text.isEmpty();
    }
}
