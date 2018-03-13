package com.dog.utils.common;

public class TableUtil {
    /**
     * 获取当前表名，格式
     * @param name 例如：t_km_click_log
     * @return t_km_click_log_20180108
     */
    public static String getTableName(String name) {
        String month = DateUtils.getCurrentDateString(DateUtils.DATE_PATTERN.MM);
        String year = DateUtils.getCurrentYear();
        String tableName = name +"_"+ year+month;
        return tableName;
    }
}
