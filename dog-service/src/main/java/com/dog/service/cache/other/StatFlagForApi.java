package com.dog.service.cache.other;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StatFlagForApi {

    private static final Map<String, Boolean> STAT_MAP = new ConcurrentHashMap();
    private static volatile boolean singleClusterStat = true;

    public StatFlagForApi() {
    }

    public static boolean isNormal(String clusterName) {
        if (StringUtils.isBlank(clusterName)) {
            return singleClusterStat;
        } else {
            Boolean stat = (Boolean)STAT_MAP.get(clusterName);
            return stat == null ? true : stat;
        }
    }

    public static void setNormal(String clusterName, boolean normal) {
        if (StringUtils.isBlank(clusterName)) {
            singleClusterStat = normal;
        } else {
            STAT_MAP.put(clusterName, normal);
        }
    }

}
