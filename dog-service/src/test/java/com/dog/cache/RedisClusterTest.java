package com.dog.cache;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Description: Redis集群测试 .
 * @version : V1.0
 */
public class RedisClusterTest {
	private static final Log log = LogFactory.getLog(RedisClusterTest.class);

	public static void main(String[] args) {
		
		// 数据库链接池配置
		JedisPoolConfig config = new JedisPoolConfig();  
        config.setMaxTotal(100);  
        config.setMaxIdle(50);  
        config.setMinIdle(20);  
        config.setMaxWaitMillis(6 * 1000);  
        config.setTestOnBorrow(true);  
		
		// Redis集群的节点集合
		Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
		jedisClusterNodes.add(new HostAndPort("192.168.1.111", 7111));
		jedisClusterNodes.add(new HostAndPort("192.168.1.112", 7112));
		jedisClusterNodes.add(new HostAndPort("192.168.1.113", 7113));
		jedisClusterNodes.add(new HostAndPort("192.168.1.114", 7114));
		jedisClusterNodes.add(new HostAndPort("192.168.1.115", 7115));
		jedisClusterNodes.add(new HostAndPort("192.168.1.116", 7116));
		
		// 根据节点集创集群链接对象
		//JedisCluster jedisCluster = new JedisCluster(jedisClusterNodes);
		// 集群各节点集合，超时时间，最多重定向次数，链接池
		JedisCluster jedisCluster = new JedisCluster(jedisClusterNodes, 2000, 100, config);
		int num = 1000;
		String key = "guoxg";
		String value = "";
		for (int i=1; i <= num; i++){
			// 存数据
			jedisCluster.set(key+i, "XiaogangGuo"+i);
			// 取数据
			value = jedisCluster.get(key+i); 
			log.info(key+i + "=" + value);
			// 删除数据
			//jedisCluster.del(key+i); 
			//value = jedisCluster.get(key+i); 
			//log.info(key+i + "=" + value);
		}
	}
}
