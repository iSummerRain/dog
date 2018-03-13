package com.dog.service.cache.redis;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.BinaryJedisPubSub;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPubSub;

public class SubRegistration {
    private static final Map<String, Map<String, Map>> REGISTRATION = new HashMap();
    private static final Map<String, JedisPubSub> CHANNEL_SUB = new ConcurrentHashMap();
    private static final Map<byte[], BinaryJedisPubSub> BIN_CHANNEL_SUB = new ConcurrentHashMap();
    private static final Map<String, JedisPubSub> PATTERN_SUB = new ConcurrentHashMap();
    private static final Map<byte[], BinaryJedisPubSub> BIN_PATTERN_SUB = new ConcurrentHashMap();
    private static final String SINGLE_CLUSTER = "SINGLE_CLUSTER";
    private static final String CHANNEL_SUB_REG = "CHANNEL_SUB";
    private static final String BIN_CHANNEL_SUB_REG = "BIN_CHANNEL_SUB";
    private static final String PATTERN_SUB_REG = "PATTERN_SUB";
    private static final String BIN_PATTERN_SUB_REG = "BIN_PATTERN_SUB";
    private static ExecutorService threadPool = Executors.newCachedThreadPool();

    public SubRegistration() {
    }

    public static void registerChannelSub(String clusterName, JedisPubSub jedisPubSub, String... channel) {
        String[] arr$ = channel;
        int len$ = channel.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            String c = arr$[i$];
//            Map map = (Map)getRegisterMap(clusterName).get("CHANNEL_SUB");
//            map.put(c, jedisPubSub);
        }

    }

    public static void registerBinaryChannelSub(String clusterName, BinaryJedisPubSub jedisPubSub, byte[]... channel) {
        byte[][] arr$ = channel;
        int len$ = channel.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            byte[] c = arr$[i$];
//            Map map = (Map)getRegisterMap(clusterName).get("BIN_CHANNEL_SUB");
//            map.put(c, jedisPubSub);
        }

    }

    public static void registerPatternSub(String clusterName, JedisPubSub jedisPubSub, String... pattern) {
        String[] arr$ = pattern;
        int len$ = pattern.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            String p = arr$[i$];
//            Map map = (Map)getRegisterMap(clusterName).get("PATTERN_SUB");
//            map.put(p, jedisPubSub);
        }

    }

    public static void registerBinaryPatternSub(String clusterName, BinaryJedisPubSub jedisPubSub, byte[]... pattern) {
        byte[][] arr$ = pattern;
        int len$ = pattern.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            byte[] p = arr$[i$];
//            Map map = (Map)getRegisterMap(clusterName).get("BIN_PATTERN_SUB");
//            map.put(p, jedisPubSub);
        }

    }

    /*
    public static void reconnectSubscribers(final String clusterName, JedisCluster jedisCluster) {
        Map reg = getRegisterMap(clusterName);
        if (reg != null) {
            threadPool.shutdown();
            threadPool = Executors.newCachedThreadPool();
            final Map channelSubs = (Map)reg.get("CHANNEL_SUB");
            Set<String> c1Set = new HashSet(channelSubs.keySet());
            Iterator $i$ = c1Set.iterator();

            while($i$.hasNext()) {
                final String channel = (String)$i$.next();
                threadPool.submit(new Runnable() {
                    public void run() {
                        JedisPubSub sub = (JedisPubSub)channelSubs.get(channel);
                        if (sub.isSubscribed()) {
                            JedisClusterFactory.getJedisCluster(clusterName).subscribe(sub, new String[]{channel});
                        } else {
                            channelSubs.remove(channel);
                        }

                    }
                });
            }

            final Map binChannelSubs = (Map)reg.get("BIN_CHANNEL_SUB");
            Set<byte[]> c2Set = new HashSet(binChannelSubs.keySet());
            Iterator i = c2Set.iterator();

            while(i.hasNext()) {
                final byte[] channel = (byte[])i.next();
                threadPool.submit(new Runnable() {
                    public void run() {
                        BinaryJedisPubSub sub = (BinaryJedisPubSub)binChannelSubs.get(channel);
                        if (sub.isSubscribed()) {
                            JedisClusterFactory.getJedisCluster(clusterName).subscribe(sub, new byte[][]{channel});
                        } else {
                            binChannelSubs.remove(channel);
                        }

                    }
                });
            }

            final Map patternSubs = (Map)reg.get("PATTERN_SUB");
            Set<String> c3Set = new HashSet(patternSubs.keySet());
            Iterator $i$$ = c3Set.iterator();

            while($i$$.hasNext()) {
                final String pattern = (String)$i$$.next();
                threadPool.submit(new Runnable() {
                    public void run() {
                        JedisPubSub sub = (JedisPubSub)patternSubs.get(pattern);
                        if (sub.isSubscribed()) {
                            JedisClusterFactory.getJedisCluster(clusterName).psubscribe(sub, new String[]{pattern});
                        } else {
                            patternSubs.remove(pattern);
                        }

                    }
                });
            }

            final Map binPatternSubs = (Map)reg.get("BIN_PATTERN_SUB");
            Set<byte[]> c4Set = new HashSet(binPatternSubs.keySet());
            Iterator i$ = c4Set.iterator();

            while(i$.hasNext()) {
                final byte[] pattern = (byte[])i$.next();
                threadPool.submit(new Runnable() {
                    public void run() {
                        BinaryJedisPubSub sub = (BinaryJedisPubSub)binPatternSubs.get(pattern);
                        if (sub.isSubscribed()) {
                            JedisClusterFactory.getJedisCluster(clusterName).psubscribe(sub, new byte[][]{pattern});
                        } else {
                            binPatternSubs.remove(pattern);
                        }

                    }
                });
            }

        }
    }


    private static Map createRegisterMap() {
        Map map = new HashMap();
        map.put("CHANNEL_SUB", CHANNEL_SUB);
        map.put("BIN_CHANNEL_SUB", BIN_CHANNEL_SUB);
        map.put("PATTERN_SUB", PATTERN_SUB);
        map.put("BIN_PATTERN_SUB", BIN_PATTERN_SUB);
        return map;
    }

    private static Map getRegisterMap(String clusterName) {
        String cluster = StringUtils.isBlank(clusterName) ? "SINGLE_CLUSTER" : clusterName;
        return (Map)REGISTRATION.get(cluster);
    }

    static {
        Set<String> clusters = JedisClusterFactory.getAllClusterNames();
        if (clusters != null && clusters.size() > 0) {
            Iterator i$ = clusters.iterator();

            while(i$.hasNext()) {
                String cluster = (String)i$.next();
                REGISTRATION.put(cluster, createRegisterMap());
            }
        } else {
            REGISTRATION.put("SINGLE_CLUSTER", createRegisterMap());
        }

    }
    */
}
