package com.dog.web.controller.user;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.InetSocketTransportAddress;
//import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.util.concurrent.atomic.AtomicInteger;

public class EngineClient {

//    private transient static Log log = LogFactory.getLog(EngineClient.class);
//
//    private static AtomicInteger atomic = new AtomicInteger();
//    private static TransportClient client = null;
//
//    private static TransportClient initTransportClient() throws Exception {
//        if (client == null) {
//            synchronized (atomic) {
//                if (client == null) {
//                    Settings settings = Settings.builder()
//                            // 集群名称
//                            .put("cluster.name", "xxxxx")
//                            // 客户端自动嗅探整个集群的状态
//                            .put("client.transport.sniff", "xxxxx")
//                            // 节点检测间隔
//                            .put("client.transport.nodes_sampler_interval", "5s")
//                            // 请求超时
//                            .put("client.transport.ping_timeout", "30s")
//                            // 忽略集群名称检查
//                            .put("client.transport.ignore_cluster_name", false).build();
//
//                    client = new PreBuiltTransportClient(settings);
//
//                    String transportAddresses = "33333333";
//
//                    if (StringUtils.isNotEmpty(transportAddresses)) {
//                        String[] strArr = transportAddresses.split(",");
//                        for (String str : strArr) {
//                            String[] addressAndPort = str.split(":");
//
//                            String address = addressAndPort[0];
//                            int port = Integer.valueOf(addressAndPort[1]);
//
//                            client.addTransportAddresses(
//                                    new InetSocketTransportAddress(InetAddress.getByName(address), port));
//                            if (log.isDebugEnabled()) {
//                                log.debug("节点［" + str + "］连接成功");
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return client;
//    }
//
//    public static TransportClient getConnection() throws Exception {
//        return initTransportClient();
//    }
//
//    public synchronized static void close() {
//        if (client != null) {
//            client.close();
//        }
//    }
}
