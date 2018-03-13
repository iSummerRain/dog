package com.dog.service.cache.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dog.service.cache.ICacheService;
import com.dog.service.cache.exception.CacheException;
import redis.clients.jedis.BinaryJedisPubSub;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.BinaryClient.LIST_POSITION;

public abstract class AbstractCacheService implements ICacheService {
    public AbstractCacheService() {
    }

    public boolean isExist(String key) {
        throw new CacheException("AbsentImplementMethod");
    }

    public boolean isExist(byte[] key) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long del(String key) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long del(byte[] keys) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long del(byte[]... keys) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long del(String... key) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long ttl(String key) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long ttl(byte[] key) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long expire(String key, int seconds) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long expire(byte[] key, int seconds) {
        throw new CacheException("AbsentImplementMethod");
    }

    public boolean setString(String key, String value) {
        throw new CacheException("AbsentImplementMethod");
    }

    /** @deprecated */
    @Deprecated
    public byte[] getByte(String key) {
        throw new CacheException("AbsentImplementMethod");
    }

    public byte[] getByte(byte[] key) {
        throw new CacheException("AbsentImplementMethod");
    }

    public boolean setByte(String key, byte[] value) {
        throw new CacheException("AbsentImplementMethod");
    }

    public boolean setByte(byte[] key, byte[] value) {
        throw new CacheException("AbsentImplementMethod");
    }

    public boolean setObject(String key, Object value) {
        throw new CacheException("AbsentImplementMethod");
    }

    public boolean setex(String key, String value, int seconds) {
        throw new CacheException("AbsentImplementMethod");
    }

    public boolean setex(byte[] key, byte[] value, int seconds) {
        throw new CacheException("AbsentImplementMethod");
    }

    public boolean setexObject(String key, Object value, int seconds) {
        throw new CacheException("AbsentImplementMethod");
    }

    public String getString(String key) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Object getObject(String key) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long incr(String key) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long incrBy(String key, long integer) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long incr(byte[] key) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long incrBy(byte[] key, long integer) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long decr(String key) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long decrBy(String key, long integer) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long decr(byte[] key) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long decrBy(byte[] key, long integer) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long llen(byte[] key) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long llen(String key) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long lpush(byte[] key, byte[]... values) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long lpush(String key, String... values) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long rpush(String key, String... values) {
        throw new CacheException("AbsentImplementMethod");
    }

    public List<String> lrange(String key, long start, long end) {
        throw new CacheException("AbsentImplementMethod");
    }

    public List<byte[]> lrange(byte[] key, long start, long end) {
        throw new CacheException("AbsentImplementMethod");
    }

    public byte[] lindex(byte[] key, long index) {
        throw new CacheException("AbsentImplementMethod");
    }

    public String lindex(String key, long index) {
        throw new CacheException("AbsentImplementMethod");
    }

    public byte[] lpop(byte[] key) {
        throw new CacheException("AbsentImplementMethod");
    }

    public String lpop(String key) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long lrem(String key, long count, String value) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long lrem(byte[] key, long count, byte[] value) {
        throw new CacheException("AbsentImplementMethod");
    }

    public String putMap(String key, Map<String, String> map) {
        throw new CacheException("AbsentImplementMethod");
    }

    public String putMap(byte[] key, Map<byte[], byte[]> map) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Map<String, String> getMap(String key) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Map<byte[], byte[]> getMap(byte[] key) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long hset(String key, String field, String value) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long hset(byte[] key, byte[] field, byte[] value) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long hsetObject(String key, String field, Object value) {
        throw new CacheException("AbsentImplementMethod");
    }

    public String hget(String key, String field) {
        throw new CacheException("AbsentImplementMethod");
    }

    public byte[] hget(byte[] key, byte[] field) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Object hgetObject(String key, String field) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long hsetnx(String key, String field, String value) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long hsetnx(byte[] key, byte[] field, byte[] value) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long hsetnxObject(String key, String field, Object value) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long hlen(String key) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long hlen(byte[] key) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Boolean hexists(String key, String field) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Boolean hexists(byte[] key, byte[] field) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Set<String> hkeys(String key) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Set<byte[]> hkeys(byte[] key) {
        throw new CacheException("AbsentImplementMethod");
    }

    public List<String> hvals(String key) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Collection<byte[]> hvals(byte[] key) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long hdel(String key, String... field) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long hdel(byte[] key, byte[]... field) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long sadd(String key, String... member) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long sadd(byte[] key, byte[]... member) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long scard(String key) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long scard(byte[] key) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Set<String> smembers(String key) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Set<byte[]> smembers(byte[] key) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long zadd(String key, double score, String member) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long zadd(byte[] key, double score, byte[] member) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Double zincrby(byte[] key, double score, byte[] member) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Double zincrby(String key, double score, String member) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Set<String> zrevrangeByScore(String key, double max, double min) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Set<byte[]> zrevrangeByScore(byte[] key, double max, double min) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Set<byte[]> zrevrangeByScore(byte[] key, double max, double min, int offset, int count) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Set<Tuple> zrevrangeByScoreWithScores(byte[] key, double max, double min) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min, int offset, int count) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Set<Tuple> zrevrangeByScoreWithScores(byte[] key, double max, double min, int offset, int count) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long zremrangeByScore(String key, double min, double max) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long zremrangeByScore(String key, String min, String max) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long zremrangeByScore(byte[] key, double min, double max) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long zremrangeByScore(byte[] key, byte[] min, byte[] max) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long zrem(String key, String... member) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long zrem(byte[] key, byte[]... member) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Set<String> keys(String pattern) {
        throw new CacheException("AbsentImplementMethod");
    }

    public String rpop(String key) {
        throw new CacheException("AbsentImplementMethod");
    }

    public byte[] rpop(byte[] key) {
        throw new CacheException("AbsentImplementMethod");
    }

    public boolean ltrim(String key, long start, long end) {
        throw new CacheException("AbsentImplementMethod");
    }

    public boolean ltrim(byte[] key, long start, long end) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long rpush(byte[] key, byte[]... values) {
        throw new CacheException("AbsentImplementMethod");
    }

    public String rpoplpush(String srckey, String dstkey) {
        throw new CacheException("AbsentImplementMethod");
    }

    public byte[] rpoplpush(byte[] srckey, byte[] dstkey) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long linsert(String key, LIST_POSITION where, String pivot, String value) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long linsert(byte[] key, LIST_POSITION where, byte[] pivot, byte[] value) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long lpushx(String key, String... values) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long lpushx(byte[] key, byte[]... values) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long rpushx(String key, String... values) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long rpushx(byte[] key, byte[]... values) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Double zscore(String key, String member) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Double zscore(byte[] key, byte[] member) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Set<String> zrange(String key, long start, long end) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Set<byte[]> zrange(byte[] key, long start, long end) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Set<Tuple> zrangeWithScores(String key, long start, long end) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Set<Tuple> zrangeWithScores(byte[] key, long start, long end) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max, int offset, int count) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Set<Tuple> zrangeByScoreWithScores(byte[] key, double min, double max) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Set<Tuple> zrangeByScoreWithScores(byte[] key, double min, double max, int offset, int count) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Set<String> zrevrange(String key, long start, long end) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Set<byte[]> zrevrange(byte[] key, long start, long end) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long zrank(String key, String member) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long zrank(byte[] key, byte[] member) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long zrevrank(String key, String member) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long zrevrank(byte[] key, byte[] member) {
        throw new CacheException("AbsentImplementMethod");
    }

    public List<String> mget(String... keys) {
        throw new CacheException("AbsentImplementMethod");
    }

    public List<byte[]> mget(byte[]... keys) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long publish(String channel, String message) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long publish(byte[] channel, byte[] message) {
        throw new CacheException("AbsentImplementMethod");
    }

    public void subscribe(JedisPubSub jedisPubSub, String... channels) {
        throw new CacheException("AbsentImplementMethod");
    }

    public void subscribe(BinaryJedisPubSub jedisPubSub, byte[]... channels) {
        throw new CacheException("AbsentImplementMethod");
    }

    public void psubscribe(JedisPubSub jedisPubSub, String... patterns) {
        throw new CacheException("AbsentImplementMethod");
    }

    public void psubscribe(BinaryJedisPubSub jedisPubSub, byte[]... patterns) {
        throw new CacheException("AbsentImplementMethod");
    }

    public String spop(String key) {
        throw new CacheException("AbsentImplementMethod");
    }

    public byte[] spop(byte[] key) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long srem(String key, String... member) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long srem(byte[] key, byte[]... member) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long setnx(String key, String value) {
        throw new CacheException("AbsentImplementMethod");
    }

    public Long setnx(byte[] key, byte[] value) {
        throw new CacheException("AbsentImplementMethod");
    }
}
