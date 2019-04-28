package com.caiw.dsg.hive2hbase;

import org.apache.commons.lang3.StringUtils;

public class StrUtil {
    public static String DSG_PARAM_EMPTY_VALUE = "DSG_PARAM_EMPTY_VALUE";

    public static Boolean isEmpty(String str) {
        return StringUtils.isEmpty(str);
    }

    public static Boolean isNotEmpty(String str) {
        return StringUtils.isNotEmpty(str);
    }

    public static Boolean isBlank(String str) {
        return StringUtils.isBlank(str);
    }

    public static Boolean isNotBlank(String str) {
        return StringUtils.isNotBlank(str);
    }

    public static String trimEmptyAndSlash(String str) {

        return StringUtils.strip(str, "^[ /]*|[ /]*$");
//        return StringUtils.replace(str, "^[ /]*|[ /]*$", ""); // 不满足
    }

    public static Boolean isDsgParamBlank(String str) {
        return StringUtils.isBlank(str) || (DSG_PARAM_EMPTY_VALUE.equalsIgnoreCase(str));
    }

    public static String checkDsgParamBlank(String str) {
        if (isDsgParamBlank(str)) {
            return "";
        } else {
            return str;
        }
    }
}
