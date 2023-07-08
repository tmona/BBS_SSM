package com.bbs.utils;

import com.bbs.pojo.Comment;

import java.util.HashMap;
import java.util.Map;

public class StringUtil {

    public static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static String filterSpecialChar(String str) {
        if (isBlank(str)) {
            return str;
        }
        // 定义需要替换的特殊字符
        Map<String, String> specialCharMap = new HashMap<>();
        specialCharMap.put("&", "&amp;");
        specialCharMap.put("<", "&lt;");
        specialCharMap.put(">", "&gt;");
        specialCharMap.put("\"", "&quot;");
        specialCharMap.put("'", "&#39;");
        specialCharMap.put("\\(", "&#40;");
        specialCharMap.put("\\)", "&#41;");
        specialCharMap.put("\\*", "&#42;");
        specialCharMap.put("\\+", "&#43;");
        specialCharMap.put(",", "&#44;");
        specialCharMap.put("-", "&#45;");
        specialCharMap.put("\\.", "&#46;");
        specialCharMap.put("/", "&#47;");
        specialCharMap.put(":", "&#58;");
        specialCharMap.put(";", "&#59;");
        specialCharMap.put("<", "&#60;");
        specialCharMap.put("=", "&#61;");
        specialCharMap.put(">", "&#62;");
        specialCharMap.put("\\?", "&#63;");
        specialCharMap.put("@", "&#64;");
        specialCharMap.put("\\[", "&#91;");
        specialCharMap.put("\\\\", "&#92;");
        specialCharMap.put("\\]", "&#93;");
        specialCharMap.put("\\^", "&#94;");
        specialCharMap.put("_", "&#95;");
        specialCharMap.put("`", "&#96;");
        specialCharMap.put("\\{", "&#123;");
        specialCharMap.put("\\|", "&#124;");
        specialCharMap.put("\\}", "&#125;");
        specialCharMap.put("~", "&#126;");

        // 替换特殊字符
        for (Map.Entry<String, String> entry : specialCharMap.entrySet()) {
            str = str.replaceAll(entry.getKey(), entry.getValue());
        }

        return str;
    }

}
