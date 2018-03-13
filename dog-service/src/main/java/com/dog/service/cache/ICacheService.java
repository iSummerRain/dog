package com.dog.service.cache;

import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.BinaryJedisPubSub;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.Tuple;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * redis cache
 */
public interface ICacheService {

    boolean isExist(String var1);

    boolean isExist(byte[] var1);

    Long del(String var1);

    Long del(byte[] var1);

    Long del(byte[]... var1);

    Long del(String... var1);

    Long ttl(String var1);

    Long ttl(byte[] var1);

    Long expire(String var1, int var2);

    Long expire(byte[] var1, int var2);

    boolean setString(String var1, String var2);

    /** @deprecated */
    @Deprecated
    boolean setByte(String var1, byte[] var2);

    boolean setByte(byte[] var1, byte[] var2);

    boolean setObject(String var1, Object var2);

    boolean setex(String var1, String var2, int var3);

    boolean setex(byte[] var1, byte[] var2, int var3);

    boolean setexObject(String var1, Object var2, int var3);

    String getString(String var1);

    /** @deprecated */
    @Deprecated
    byte[] getByte(String var1);

    byte[] getByte(byte[] var1);

    Object getObject(String var1);

    Long incr(String var1);

    Long incrBy(String var1, long var2);

    Long incr(byte[] var1);

    Long incrBy(byte[] var1, long var2);

    Long decr(String var1);

    Long decrBy(String var1, long var2);

    Long decr(byte[] var1);

    Long decrBy(byte[] var1, long var2);

    Long llen(byte[] var1);

    Long llen(String var1);

    Long lpush(byte[] var1, byte[]... var2);

    Long lpush(String var1, String... var2);

    Long rpush(String var1, String... var2);

    List<String> lrange(String var1, long var2, long var4);

    List<byte[]> lrange(byte[] var1, long var2, long var4);

    byte[] lindex(byte[] var1, long var2);

    String lindex(String var1, long var2);

    byte[] lpop(byte[] var1);

    String lpop(String var1);

    Long lrem(String var1, long var2, String var4);

    Long lrem(byte[] var1, long var2, byte[] var4);

    String putMap(String var1, Map<String, String> var2);

    String putMap(byte[] var1, Map<byte[], byte[]> var2);

    Map<String, String> getMap(String var1);

    Map<byte[], byte[]> getMap(byte[] var1);

    Long hset(String var1, String var2, String var3);

    Long hset(byte[] var1, byte[] var2, byte[] var3);

    Long hsetObject(String var1, String var2, Object var3);

    String hget(String var1, String var2);

    byte[] hget(byte[] var1, byte[] var2);

    Object hgetObject(String var1, String var2);

    Long hsetnx(String var1, String var2, String var3);

    Long hsetnxObject(String var1, String var2, Object var3);

    Long hsetnx(byte[] var1, byte[] var2, byte[] var3);

    Long hlen(String var1);

    Long hlen(byte[] var1);

    Boolean hexists(String var1, String var2);

    Boolean hexists(byte[] var1, byte[] var2);

    Set<String> hkeys(String var1);

    Set<byte[]> hkeys(byte[] var1);

    List<String> hvals(String var1);

    Collection<byte[]> hvals(byte[] var1);

    Long hdel(String var1, String... var2);

    Long hdel(byte[] var1, byte[]... var2);

    Long sadd(String var1, String... var2);

    Long sadd(byte[] var1, byte[]... var2);

    Long scard(String var1);

    Long scard(byte[] var1);

    Set<String> smembers(String var1);

    Set<byte[]> smembers(byte[] var1);

    Long zadd(String var1, double var2, String var4);

    Long zadd(byte[] var1, double var2, byte[] var4);

    Double zincrby(byte[] var1, double var2, byte[] var4);

    Double zincrby(String var1, double var2, String var4);

    Set<String> zrevrangeByScore(String var1, double var2, double var4);

    Set<String> zrevrangeByScore(String var1, double var2, double var4, int var6, int var7);

    Set<byte[]> zrevrangeByScore(byte[] var1, double var2, double var4);

    Set<byte[]> zrevrangeByScore(byte[] var1, double var2, double var4, int var6, int var7);

    Set<Tuple> zrevrangeByScoreWithScores(String var1, double var2, double var4);

    Set<Tuple> zrevrangeByScoreWithScores(byte[] var1, double var2, double var4);

    Set<Tuple> zrevrangeByScoreWithScores(String var1, double var2, double var4, int var6, int var7);

    Set<Tuple> zrevrangeByScoreWithScores(byte[] var1, double var2, double var4, int var6, int var7);

    Long zremrangeByScore(String var1, double var2, double var4);

    Long zremrangeByScore(String var1, String var2, String var3);

    Long zremrangeByScore(byte[] var1, double var2, double var4);

    Long zremrangeByScore(byte[] var1, byte[] var2, byte[] var3);

    Long zrem(String var1, String... var2);

    Long zrem(byte[] var1, byte[]... var2);

    Set<String> keys(String var1);

    String rpop(String var1);

    byte[] rpop(byte[] var1);

    boolean ltrim(String var1, long var2, long var4);

    boolean ltrim(byte[] var1, long var2, long var4);

    Long rpush(byte[] var1, byte[]... var2);

    /** @deprecated */
    @Deprecated
    String rpoplpush(String var1, String var2);

    /** @deprecated */
    @Deprecated
    byte[] rpoplpush(byte[] var1, byte[] var2);

    Long linsert(String var1, BinaryClient.LIST_POSITION var2, String var3, String var4);

    Long linsert(byte[] var1, BinaryClient.LIST_POSITION var2, byte[] var3, byte[] var4);

    Long lpushx(String var1, String... var2);

    Long lpushx(byte[] var1, byte[]... var2);

    Long rpushx(String var1, String... var2);

    Long rpushx(byte[] var1, byte[]... var2);

    Double zscore(String var1, String var2);

    Double zscore(byte[] var1, byte[] var2);

    Set<String> zrange(String var1, long var2, long var4);

    Set<byte[]> zrange(byte[] var1, long var2, long var4);

    Set<Tuple> zrangeWithScores(String var1, long var2, long var4);

    Set<Tuple> zrangeWithScores(byte[] var1, long var2, long var4);

    Set<Tuple> zrangeByScoreWithScores(String var1, double var2, double var4);

    Set<Tuple> zrangeByScoreWithScores(String var1, double var2, double var4, int var6, int var7);

    Set<Tuple> zrangeByScoreWithScores(byte[] var1, double var2, double var4);

    Set<Tuple> zrangeByScoreWithScores(byte[] var1, double var2, double var4, int var6, int var7);

    Set<String> zrevrange(String var1, long var2, long var4);

    Set<byte[]> zrevrange(byte[] var1, long var2, long var4);

    Long zrank(String var1, String var2);

    Long zrank(byte[] var1, byte[] var2);

    Long zrevrank(String var1, String var2);

    Long zrevrank(byte[] var1, byte[] var2);

    List<String> mget(String... var1);

    List<byte[]> mget(byte[]... var1);

    Long publish(String var1, String var2);

    Long publish(byte[] var1, byte[] var2);

    void subscribe(JedisPubSub var1, String... var2);

    void subscribe(BinaryJedisPubSub var1, byte[]... var2);

    void psubscribe(JedisPubSub var1, String... var2);

    void psubscribe(BinaryJedisPubSub var1, byte[]... var2);

    String spop(String var1);

    byte[] spop(byte[] var1);

    Long srem(String var1, String... var2);

    Long srem(byte[] var1, byte[]... var2);

    Long setnx(String var1, String var2);

    Long setnx(byte[] var1, byte[] var2);
}
