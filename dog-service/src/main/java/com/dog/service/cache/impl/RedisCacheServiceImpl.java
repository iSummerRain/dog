package com.dog.service.cache.impl;

import com.dog.service.cache.ICacheService;
import com.dog.service.cache.other.StatFlagForApi;

import com.dog.service.cache.redis.SubRegistration;
import org.springframework.util.SerializationUtils;
import org.springframework.util.StringUtils;
import redis.clients.jedis.*;
import redis.clients.jedis.exceptions.JedisClusterException;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.util.*;
import java.util.Map.Entry;

public class RedisCacheServiceImpl extends AbstractCacheService {
    private String clusterName = null;
    private JedisCluster jedisCluster;
    private static final String IS_OK;

    public RedisCacheServiceImpl() {
    }

    public RedisCacheServiceImpl(String clusterName, JedisCluster jedisCluster) {
        this.clusterName = clusterName;
        this.jedisCluster = jedisCluster;
    }

    public JedisCluster getJedisCluster() {
        return this.jedisCluster;
    }

    public void setJedisCluster(String clusterName, JedisCluster jedisCluster) {
        this.clusterName = clusterName;
        this.jedisCluster = jedisCluster;
    }

    public boolean isExist(String key) {
        boolean flag = false;
        if (StringUtils.isEmpty(key)) {
            return flag;
        } else if (!StatFlagForApi.isNormal(this.clusterName)) {
            throw new JedisClusterException("Redis cluster is down");
        } else {
            flag = this.jedisCluster.exists(key.getBytes());
            return flag;
        }
    }

    public boolean isExist(byte[] key) {
        boolean flag = false;
        if (key != null && key.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                flag = this.jedisCluster.exists(key);
                return flag;
            }
        } else {
            return flag;
        }
    }

    public Long del(String key) {
        long result = 0L;
        if (StringUtils.isEmpty(key)) {
            return result;
        } else if (!StatFlagForApi.isNormal(this.clusterName)) {
            throw new JedisClusterException("Redis cluster is down");
        } else {
            result = this.jedisCluster.del(key.getBytes());
            return result;
        }
    }

    public Long del(byte[] key) {
        long result = 0L;
        if (key != null && key.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                result = this.jedisCluster.del(key);
                return result;
            }
        } else {
            return result;
        }
    }

    public Long del(byte[]... keys) {
        long result = 0L;
        if (!StatFlagForApi.isNormal(this.clusterName)) {
            throw new JedisClusterException("Redis cluster is down");
        } else {
            result = this.jedisCluster.del(keys);
            return result;
        }
    }

    public Long del(String... keys) {
        long result = 0L;
        if (!StatFlagForApi.isNormal(this.clusterName)) {
            throw new JedisClusterException("Redis cluster is down");
        } else {
            result = this.jedisCluster.del(keys);
            return result;
        }
    }

    public Long ttl(String key) {
        long result = 0L;
        if (StringUtils.isEmpty(key)) {
            return result;
        } else if (!StatFlagForApi.isNormal(this.clusterName)) {
            throw new JedisClusterException("Redis cluster is down");
        } else {
            result = this.jedisCluster.ttl(key.getBytes());
            return result;
        }
    }

    public Long ttl(byte[] key) {
        long result = 0L;
        if (key != null && key.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                result = this.jedisCluster.ttl(key);
                return result;
            }
        } else {
            return result;
        }
    }

    public Long expire(String key, int seconds) {
        long result = 0L;
        if (StringUtils.isEmpty(key)) {
            return result;
        } else if (!StatFlagForApi.isNormal(this.clusterName)) {
            throw new JedisClusterException("Redis cluster is down");
        } else if (seconds >= 0) {
            result = this.jedisCluster.expire(key.getBytes(), seconds);
            return result;
        } else {
            return result;
        }
    }

    public Long expire(byte[] key, int seconds) {
        long result = 0L;
        if (key != null && key.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else if (seconds >= 0) {
                result = this.jedisCluster.expire(key, seconds);
                return result;
            } else {
                return result;
            }
        } else {
            return result;
        }
    }

    public boolean setString(String key, String value) {
        boolean flag = false;
        if (!StringUtils.isEmpty(key) && !StringUtils.isEmpty(value)) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                byte[] setkey = key.getBytes();
                byte[] setvalue = value.getBytes();
                String result = this.jedisCluster.set(setkey, setvalue);
                if ("OK".equals(result)) {
                    flag = true;
                }

                return flag;
            }
        } else {
            return flag;
        }
    }

    public boolean setObject(String key, Object value) {
        boolean flag = false;
        if (!StringUtils.isEmpty(key) && null != value) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                byte[] setkey = key.getBytes();
                byte[] setvalue = SerializationUtils.serialize(value);
                String result = this.jedisCluster.set(setkey, setvalue);
                if ("OK".equals(result)) {
                    flag = true;
                }

                return flag;
            }
        } else {
            return flag;
        }
    }

    public boolean setByte(String key, byte[] value) {
        if (StringUtils.isEmpty(key)) {
            return false;
        } else if (!StatFlagForApi.isNormal(this.clusterName)) {
            throw new JedisClusterException("Redis cluster is down");
        } else {
            return this.setByte(key.getBytes(), value);
        }
    }

    public boolean setByte(byte[] key, byte[] value) {
        boolean flag = false;
        if (key != null && key.length != 0 && null != value && value.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                String result = this.jedisCluster.set(key, value);
                if ("OK".equals(result)) {
                    flag = true;
                }

                return flag;
            }
        } else {
            return flag;
        }
    }

    public boolean setex(String key, String value, int seconds) {
        boolean flag = false;
        if (!StringUtils.isEmpty(key) && !StringUtils.isEmpty(value)) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                if (seconds >= 0) {
                    byte[] setkey = key.getBytes();
                    byte[] setvalue = value.getBytes();
                    String result = this.jedisCluster.setex(setkey, seconds, setvalue);
                    if ("OK".equals(result)) {
                        flag = true;
                    }
                }

                return flag;
            }
        } else {
            return flag;
        }
    }

    public boolean setex(byte[] key, byte[] value, int seconds) {
        boolean flag = false;
        if (key != null && key.length != 0 && value != null && value.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                if (seconds >= 0) {
                    String result = this.jedisCluster.setex(key, seconds, value);
                    if ("OK".equals(result)) {
                        flag = true;
                    }
                }

                return flag;
            }
        } else {
            return flag;
        }
    }

    public boolean setexObject(String key, Object value, int seconds) {
        boolean flag = false;
        if (!StringUtils.isEmpty(key) && null != value) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                if (seconds >= 0) {
                    byte[] setkey = key.getBytes();
                    byte[] setvalue = SerializationUtils.serialize(value);
                    String result = this.jedisCluster.setex(setkey, seconds, setvalue);
                    if ("OK".equals(result)) {
                        flag = true;
                    }
                }

                return flag;
            }
        } else {
            return flag;
        }
    }

    public String getString(String key) {
        String value = null;
        if (StringUtils.isEmpty(key)) {
            return value;
        } else if (!StatFlagForApi.isNormal(this.clusterName)) {
            throw new JedisClusterException("Redis cluster is down");
        } else {
            byte[] result = this.jedisCluster.get(key.getBytes());
            if (result != null && result.length != 0) {
                value = new String(result);
                return value;
            } else {
                return value;
            }
        }
    }

    public byte[] getByte(String key) {
        if (StringUtils.isEmpty(key)) {
            return null;
        } else if (!StatFlagForApi.isNormal(this.clusterName)) {
            throw new JedisClusterException("Redis cluster is down");
        } else {
            return this.getByte(key.getBytes());
        }
    }

    public byte[] getByte(byte[] key) {
        byte[] value = null;
        if (key != null && key.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                byte[] $value = this.jedisCluster.get(key);
                return $value;
            }
        } else {
            return (byte[])value;
        }
    }

    public Object getObject(String key) {
        Object value = null;
        if (StringUtils.isEmpty(key)) {
            return value;
        } else if (!StatFlagForApi.isNormal(this.clusterName)) {
            throw new JedisClusterException("Redis cluster is down");
        } else {
            byte[] result = this.jedisCluster.get(key.getBytes());
            if (result != null && result.length != 0) {
                value = SerializationUtils.deserialize(result);
                return value;
            } else {
                return value;
            }
        }
    }

    public Long incr(String key) {
        long result = 0L;
        if (StringUtils.isEmpty(key)) {
            return result;
        } else if (!StatFlagForApi.isNormal(this.clusterName)) {
            throw new JedisClusterException("Redis cluster is down");
        } else {
            result = this.jedisCluster.incr(key.getBytes());
            return result;
        }
    }

    public Long incrBy(String key, long integer) {
        long result = 0L;
        if (StringUtils.isEmpty(key)) {
            return result;
        } else if (!StatFlagForApi.isNormal(this.clusterName)) {
            throw new JedisClusterException("Redis cluster is down");
        } else {
            result = this.jedisCluster.incrBy(key.getBytes(), integer);
            return result;
        }
    }

    public Long incr(byte[] key) {
        long result = 0L;
        if (key != null && key.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                result = this.jedisCluster.incr(key);
                return result;
            }
        } else {
            return result;
        }
    }

    public Long incrBy(byte[] key, long integer) {
        long result = 0L;
        if (key != null && key.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                result = this.jedisCluster.incrBy(key, integer);
                return result;
            }
        } else {
            return result;
        }
    }

    public Long decr(String key) {
        long result = 0L;
        if (StringUtils.isEmpty(key)) {
            return result;
        } else if (!StatFlagForApi.isNormal(this.clusterName)) {
            throw new JedisClusterException("Redis cluster is down");
        } else {
            result = this.jedisCluster.decr(key.getBytes());
            return result;
        }
    }

    public Long decrBy(String key, long integer) {
        long result = 0L;
        if (StringUtils.isEmpty(key)) {
            return result;
        } else if (!StatFlagForApi.isNormal(this.clusterName)) {
            throw new JedisClusterException("Redis cluster is down");
        } else {
            result = this.jedisCluster.decrBy(key.getBytes(), integer);
            return result;
        }
    }

    public Long decr(byte[] key) {
        long result = 0L;
        if (key != null && key.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                result = this.jedisCluster.decr(key);
                return result;
            }
        } else {
            return result;
        }
    }

    public Long decrBy(byte[] key, long integer) {
        long result = 0L;
        if (key != null && key.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                result = this.jedisCluster.decrBy(key, integer);
                return result;
            }
        } else {
            return result;
        }
    }

    public Long llen(byte[] key) {
        long result = 0L;
        if (key != null && key.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                result = this.jedisCluster.llen(key);
                return result;
            }
        } else {
            return result;
        }
    }

    public Long llen(String key) {
        long result = 0L;
        if (StringUtils.isEmpty(key)) {
            return result;
        } else if (!StatFlagForApi.isNormal(this.clusterName)) {
            throw new JedisClusterException("Redis cluster is down");
        } else {
            result = this.jedisCluster.llen(key.getBytes());
            return result;
        }
    }

    public Long lpush(byte[] key, byte[]... values) {
        long result = 0L;
        if (key != null && key.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                result = this.jedisCluster.lpush(key, values);
                return result;
            }
        } else {
            return result;
        }
    }

    public Long lpush(String key, String... values) {
        long result = 0L;
        if (StringUtils.isEmpty(key)) {
            return result;
        } else if (!StatFlagForApi.isNormal(this.clusterName)) {
            throw new JedisClusterException("Redis cluster is down");
        } else {
            result = this.jedisCluster.lpush(key, values);
            return result;
        }
    }

    public Long rpush(String key, String... values) {
        long result = 0L;
        if (StringUtils.isEmpty(key)) {
            return result;
        } else if (!StatFlagForApi.isNormal(this.clusterName)) {
            throw new JedisClusterException("Redis cluster is down");
        } else {
            result = this.jedisCluster.rpush(key, values);
            return result;
        }
    }

    public List<String> lrange(String key, long start, long end) {
        List<String> value = null;
        if (StringUtils.isEmpty(key)) {
            return value;
        } else if (!StatFlagForApi.isNormal(this.clusterName)) {
            throw new JedisClusterException("Redis cluster is down");
        } else {
            value = this.jedisCluster.lrange(key, start, end);
            return value;
        }
    }

    public List<byte[]> lrange(byte[] key, long start, long end) {
        List<byte[]> value = null;
        if (key != null && key.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                value = this.jedisCluster.lrange(key, start, end);
                return value;
            }
        } else {
            return value;
        }
    }

    public byte[] lindex(byte[] key, long index) {
        byte[] value = null;
        if (key != null && key.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                byte[] $value = this.jedisCluster.lindex(key, index);
                return $value;
            }
        } else {
            return (byte[])value;
        }
    }

    public String lindex(String key, long index) {
        String value = null;
        if (StringUtils.isEmpty(key)) {
            return value;
        } else if (!StatFlagForApi.isNormal(this.clusterName)) {
            throw new JedisClusterException("Redis cluster is down");
        } else {
            value = this.jedisCluster.lindex(key, index);
            return value;
        }
    }

    public byte[] lpop(byte[] key) {
        byte[] value = null;
        if (key != null && key.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                byte[] $value = this.jedisCluster.lpop(key);
                return $value;
            }
        } else {
            return (byte[])value;
        }
    }

    public String lpop(String key) {
        String value = null;
        if (StringUtils.isEmpty(key)) {
            return value;
        } else if (!StatFlagForApi.isNormal(this.clusterName)) {
            throw new JedisClusterException("Redis cluster is down");
        } else {
            value = this.jedisCluster.lpop(key);
            return value;
        }
    }

    public Long lrem(String key, long count, String value) {
        long result = 0L;
        if (StringUtils.isEmpty(key)) {
            return result;
        } else if (!StatFlagForApi.isNormal(this.clusterName)) {
            throw new JedisClusterException("Redis cluster is down");
        } else {
            return this.jedisCluster.lrem(key, count, value);
        }
    }

    public Long lrem(byte[] key, long count, byte[] value) {
        long result = 0L;
        if (StringUtils.isEmpty(key)) {
            return result;
        } else if (!StatFlagForApi.isNormal(this.clusterName)) {
            throw new JedisClusterException("Redis cluster is down");
        } else {
            return this.jedisCluster.lrem(key, count, value);
        }
    }

    public String putMap(String key, Map<String, String> map) {
        String result = null;
        if (!StringUtils.isEmpty(key) && map != null && map.size() >= 1) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                result = this.jedisCluster.hmset(key, map);
                return result;
            }
        } else {
            return result;
        }
    }

    public String putMap(byte[] key, Map<byte[], byte[]> map) {
        String result = null;
        if (key != null && key.length != 0 && map != null && map.size() >= 1) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                result = this.jedisCluster.hmset(key, map);
                return result;
            }
        } else {
            return result;
        }
    }

    public Map<String, String> getMap(String key) {
        Map<String, String> value = null;
        if (StringUtils.isEmpty(key)) {
            return value;
        } else if (!StatFlagForApi.isNormal(this.clusterName)) {
            throw new JedisClusterException("Redis cluster is down");
        } else {
            value = this.jedisCluster.hgetAll(key);
            return value;
        }
    }

    public Map<byte[], byte[]> getMap(byte[] key) {
        Map<byte[], byte[]> value = null;
        if (key != null && key.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                value = this.jedisCluster.hgetAll(key);
                return value;
            }
        } else {
            return value;
        }
    }

    public Long hset(String key, String field, String value) {
        long result = 0L;
        if (!StringUtils.isEmpty(key) && !StringUtils.isEmpty(field) && !StringUtils.isEmpty(value)) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                result = this.jedisCluster.hset(key, field, value);
                return result;
            }
        } else {
            return result;
        }
    }

    public Long hset(byte[] key, byte[] field, byte[] value) {
        long result = 0L;
        if (key != null && key.length != 0 && field != null && field.length != 0 && value != null && value.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                result = this.jedisCluster.hset(key, field, value);
                return result;
            }
        } else {
            return result;
        }
    }

    public Long hsetObject(String key, String field, Object value) {
        long result = 0L;
        if (!StringUtils.isEmpty(key) && !StringUtils.isEmpty(field) && null != value) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                byte[] setKey = key.getBytes();
                byte[] setField = field.getBytes();
                byte[] setValue = SerializationUtils.serialize(value);
                result = this.jedisCluster.hset(setKey, setField, setValue);
                return result;
            }
        } else {
            return result;
        }
    }

    public String hget(String key, String field) {
        String value = null;
        if (!StringUtils.isEmpty(key) && !StringUtils.isEmpty(field)) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                value = this.jedisCluster.hget(key, field);
                return value;
            }
        } else {
            return value;
        }
    }

    public byte[] hget(byte[] key, byte[] field) {
        byte[] value = null;
        if (key != null && key.length != 0 && field != null && field.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                byte[] $value = this.jedisCluster.hget(key, field);
                return $value;
            }
        } else {
            return (byte[])value;
        }
    }

    public Object hgetObject(String key, String field) {
        Object value = null;
        if (!StringUtils.isEmpty(key) && !StringUtils.isEmpty(field)) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                byte[] result = this.jedisCluster.hget(key.getBytes(), field.getBytes());
                if (result != null && result.length != 0) {
                    value = SerializationUtils.deserialize(result);
                    return value;
                } else {
                    return value;
                }
            }
        } else {
            return value;
        }
    }

    public Long hsetnx(String key, String field, String value) {
        long result = 0L;
        if (!StringUtils.isEmpty(key) && !StringUtils.isEmpty(field) && !StringUtils.isEmpty(value)) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                result = this.jedisCluster.hsetnx(key, field, value);
                return result;
            }
        } else {
            return result;
        }
    }

    public Long hsetnx(byte[] key, byte[] field, byte[] value) {
        long result = 0L;
        if (key != null && key.length != 0 && field != null && field.length != 0 && value != null && value.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                result = this.jedisCluster.hsetnx(key, field, value);
                return result;
            }
        } else {
            return result;
        }
    }

    public Long hsetnxObject(String key, String field, Object value) {
        long result = 0L;
        if (!StringUtils.isEmpty(key) && !StringUtils.isEmpty(field) && null != value) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                byte[] setvalue = SerializationUtils.serialize(value);
                result = this.jedisCluster.hsetnx(key.getBytes(), field.getBytes(), setvalue);
                return result;
            }
        } else {
            return result;
        }
    }

    public Long hlen(String key) {
        long result = 0L;
        if (StringUtils.isEmpty(key)) {
            return result;
        } else if (!StatFlagForApi.isNormal(this.clusterName)) {
            throw new JedisClusterException("Redis cluster is down");
        } else {
            result = this.jedisCluster.hlen(key);
            return result;
        }
    }

    public Long hlen(byte[] key) {
        long result = 0L;
        if (key != null && key.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                result = this.jedisCluster.hlen(key);
                return result;
            }
        } else {
            return result;
        }
    }

    public Boolean hexists(String key, String field) {
        boolean flag = false;
        if (!StringUtils.isEmpty(key) && !StringUtils.isEmpty(field)) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                flag = this.jedisCluster.hexists(key.getBytes(), field.getBytes());
                return flag;
            }
        } else {
            return flag;
        }
    }

    public Boolean hexists(byte[] key, byte[] field) {
        boolean flag = false;
        if (key != null && key.length != 0 && field != null && field.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                flag = this.jedisCluster.hexists(key, field);
                return flag;
            }
        } else {
            return flag;
        }
    }

    public Set<String> hkeys(String key) {
        Set<String> keys = null;
        if (StringUtils.isEmpty(key)) {
            return keys;
        } else if (!StatFlagForApi.isNormal(this.clusterName)) {
            throw new JedisClusterException("Redis cluster is down");
        } else {
            keys = this.jedisCluster.hkeys(key);
            return keys;
        }
    }

    public Set<byte[]> hkeys(byte[] key) {
        Set<byte[]> keys = null;
        if (key != null && key.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                keys = this.jedisCluster.hkeys(key);
                return keys;
            }
        } else {
            return keys;
        }
    }

    public List<String> hvals(String key) {
        List<String> values = null;
        if (StringUtils.isEmpty(key)) {
            return values;
        } else if (!StatFlagForApi.isNormal(this.clusterName)) {
            throw new JedisClusterException("Redis cluster is down");
        } else {
            values = this.jedisCluster.hvals(key);
            return values;
        }
    }

    public Collection<byte[]> hvals(byte[] key) {
        Collection<byte[]> values = null;
        if (key != null && key.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                values = this.jedisCluster.hvals(key);
                return values;
            }
        } else {
            return values;
        }
    }

    public Long hdel(String key, String... field) {
        long result = 0L;
        if (StringUtils.isEmpty(key)) {
            return result;
        } else if (!StatFlagForApi.isNormal(this.clusterName)) {
            throw new JedisClusterException("Redis cluster is down");
        } else {
            return this.jedisCluster.hdel(key, field);
        }
    }

    public Long hdel(byte[] key, byte[]... field) {
        long result = 0L;
        if (key != null && key.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                return this.jedisCluster.hdel(key, field);
            }
        } else {
            return result;
        }
    }

    public Long sadd(String key, String... member) {
        long result = 0L;
        if (StringUtils.isEmpty(key)) {
            return result;
        } else if (!StatFlagForApi.isNormal(this.clusterName)) {
            throw new JedisClusterException("Redis cluster is down");
        } else {
            result = this.jedisCluster.sadd(key, member);
            return result;
        }
    }

    public Long sadd(byte[] key, byte[]... member) {
        long result = 0L;
        if (key != null && key.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                result = this.jedisCluster.sadd(key, member);
                return result;
            }
        } else {
            return result;
        }
    }

    public Long scard(String key) {
        long result = 0L;
        if (StringUtils.isEmpty(key)) {
            return result;
        } else if (!StatFlagForApi.isNormal(this.clusterName)) {
            throw new JedisClusterException("Redis cluster is down");
        } else {
            result = this.jedisCluster.scard(key);
            return result;
        }
    }

    public Long scard(byte[] key) {
        long result = 0L;
        if (key != null && key.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                result = this.jedisCluster.scard(key);
                return result;
            }
        } else {
            return result;
        }
    }

    public Set<String> smembers(String key) {
        Set<String> value = null;
        if (StringUtils.isEmpty(key)) {
            return value;
        } else if (!StatFlagForApi.isNormal(this.clusterName)) {
            throw new JedisClusterException("Redis cluster is down");
        } else {
            value = this.jedisCluster.smembers(key);
            return value;
        }
    }

    public Set<byte[]> smembers(byte[] key) {
        Set<byte[]> value = null;
        if (key != null && key.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                value = this.jedisCluster.smembers(key);
                return value;
            }
        } else {
            return value;
        }
    }

    public Long zadd(String key, double score, String member) {
        long result = 0L;
        if (!StringUtils.isEmpty(key) && !StringUtils.isEmpty(member)) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                result = this.jedisCluster.zadd(key.getBytes(), score, member.getBytes());
                return result;
            }
        } else {
            return result;
        }
    }

    public Long zadd(byte[] key, double score, byte[] member) {
        long result = 0L;
        if (key != null && key.length != 0 && member != null && member.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                result = this.jedisCluster.zadd(key, score, member);
                return result;
            }
        } else {
            return result;
        }
    }

    public Double zincrby(byte[] key, double score, byte[] member) {
        Double result = null;
        if (key != null && key.length != 0 && member != null && member.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                result = this.jedisCluster.zincrby(key, score, member);
                return result;
            }
        } else {
            return result;
        }
    }

    public Double zincrby(String key, double score, String member) {
        Double result = null;
        if (!StringUtils.isEmpty(key) && !StringUtils.isEmpty(member)) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                result = this.jedisCluster.zincrby(key.getBytes(), score, member.getBytes());
                return result;
            }
        } else {
            return result;
        }
    }

    public Set<String> zrevrangeByScore(String key, double max, double min) {
        Set<String> value = null;
        if (StringUtils.isEmpty(key)) {
            return value;
        } else if (!StatFlagForApi.isNormal(this.clusterName)) {
            throw new JedisClusterException("Redis cluster is down");
        } else if (max > min) {
            value = this.jedisCluster.zrevrangeByScore(key, max, min);
            return value;
        } else {
            return value;
        }
    }

    public Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count) {
        Set<String> value = null;
        if (StringUtils.isEmpty(key)) {
            return value;
        } else if (!StatFlagForApi.isNormal(this.clusterName)) {
            throw new JedisClusterException("Redis cluster is down");
        } else if (max > min) {
            value = this.jedisCluster.zrevrangeByScore(key, max, min, offset, count);
            return value;
        } else {
            return value;
        }
    }

    public Set<byte[]> zrevrangeByScore(byte[] key, double max, double min) {
        Set<byte[]> value = null;
        if (key != null && key.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else if (max > min) {
                value = this.jedisCluster.zrevrangeByScore(key, max, min);
                return value;
            } else {
                return value;
            }
        } else {
            return value;
        }
    }

    public Set<byte[]> zrevrangeByScore(byte[] key, double max, double min, int offset, int count) {
        Set<byte[]> value = null;
        if (key != null && key.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else if (max > min) {
                value = this.jedisCluster.zrevrangeByScore(key, max, min, offset, count);
                return value;
            } else {
                return value;
            }
        } else {
            return value;
        }
    }

    public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min) {
        Set<Tuple> value = null;
        if (StringUtils.isEmpty(key)) {
            return value;
        } else if (!StatFlagForApi.isNormal(this.clusterName)) {
            throw new JedisClusterException("Redis cluster is down");
        } else if (max > min) {
            value = this.jedisCluster.zrevrangeByScoreWithScores(key, max, min);
            return value;
        } else {
            return value;
        }
    }

    public Set<Tuple> zrevrangeByScoreWithScores(byte[] key, double max, double min) {
        Set<Tuple> value = null;
        if (StringUtils.isEmpty(key)) {
            return value;
        } else if (!StatFlagForApi.isNormal(this.clusterName)) {
            throw new JedisClusterException("Redis cluster is down");
        } else if (max > min) {
            value = this.jedisCluster.zrevrangeByScoreWithScores(key, max, min);
            return value;
        } else {
            return value;
        }
    }

    public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min, int offset, int count) {
        Set<Tuple> value = null;
        if (StringUtils.isEmpty(key)) {
            return value;
        } else if (!StatFlagForApi.isNormal(this.clusterName)) {
            throw new JedisClusterException("Redis cluster is down");
        } else if (max > min) {
            value = this.jedisCluster.zrevrangeByScoreWithScores(key, max, min, offset, count);
            return value;
        } else {
            return value;
        }
    }

    public Set<Tuple> zrevrangeByScoreWithScores(byte[] key, double max, double min, int offset, int count) {
        Set<Tuple> value = null;
        if (key != null && key.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else if (max > min) {
                value = this.jedisCluster.zrevrangeByScoreWithScores(key, max, min, offset, count);
                return value;
            } else {
                return value;
            }
        } else {
            return value;
        }
    }

    public Long zremrangeByScore(String key, double min, double max) {
        long result = 0L;
        if (StringUtils.isEmpty(key)) {
            return result;
        } else if (!StatFlagForApi.isNormal(this.clusterName)) {
            throw new JedisClusterException("Redis cluster is down");
        } else {
            result = this.jedisCluster.zremrangeByScore(key, min, max);
            return result;
        }
    }

    public Long zremrangeByScore(String key, String min, String max) {
        long result = 0L;
        if (StringUtils.isEmpty(key)) {
            return result;
        } else if (!StatFlagForApi.isNormal(this.clusterName)) {
            throw new JedisClusterException("Redis cluster is down");
        } else {
            result = this.jedisCluster.zremrangeByScore(key, min, max);
            return result;
        }
    }

    public Long zremrangeByScore(byte[] key, double min, double max) {
        long result = 0L;
        if (StringUtils.isEmpty(key)) {
            return result;
        } else if (!StatFlagForApi.isNormal(this.clusterName)) {
            throw new JedisClusterException("Redis cluster is down");
        } else {
            result = this.jedisCluster.zremrangeByScore(key, min, max);
            return result;
        }
    }

    public Long zremrangeByScore(byte[] key, byte[] min, byte[] max) {
        long result = 0L;
        if (StringUtils.isEmpty(key)) {
            return result;
        } else if (!StatFlagForApi.isNormal(this.clusterName)) {
            throw new JedisClusterException("Redis cluster is down");
        } else {
            result = this.jedisCluster.zremrangeByScore(key, min, max);
            return result;
        }
    }

    public Long zrem(String key, String... member) {
        long result = 0L;
        if (StringUtils.isEmpty(key)) {
            return result;
        } else if (!StatFlagForApi.isNormal(this.clusterName)) {
            throw new JedisClusterException("Redis cluster is down");
        } else {
            result = this.jedisCluster.zrem(key, member);
            return result;
        }
    }

    public Long zrem(byte[] key, byte[]... member) {
        long result = 0L;
        if (key != null && key.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                result = this.jedisCluster.zrem(key, member);
                return result;
            }
        } else {
            return result;
        }
    }

    public Set<String> keys(String pattern) {
        if (StringUtils.isEmpty(pattern)) {
            return Collections.emptySet();
        } else {
            Set<String> keys = new LinkedHashSet();
            Map<String, JedisPool> clusterNodes = this.jedisCluster.getClusterNodes();
            Iterator i$ = clusterNodes.keySet().iterator();

            while(i$.hasNext()) {
                String k = (String)i$.next();
                Jedis connection = null;

                try {
                    JedisPool jp = (JedisPool)clusterNodes.get(k);
                    connection = jp.getResource();
                    keys.addAll(connection.keys(pattern));
                } finally {
                    if (connection != null) {
                        connection.close();
                    }

                }
            }

            return keys;
        }
    }

    public long clearMemory(String pattern, int seconds) {
        long delnums = 0L;
        if (StringUtils.isEmpty(pattern)) {
            return delnums;
        } else {
            Map<String, JedisPool> clusterNodes = this.jedisCluster.getClusterNodes();
            Iterator i$ = clusterNodes.keySet().iterator();

            while(i$.hasNext()) {
                String k = (String)i$.next();
                Jedis connection = null;
                LinkedHashSet keys = new LinkedHashSet();

                try {
                    JedisPool jp = (JedisPool)clusterNodes.get(k);
                    connection = jp.getResource();
                    keys.addAll(connection.keys(pattern));
                    Iterator $i$ = keys.iterator();

                    while($i$.hasNext()) {
                        String str = (String)$i$.next();
                        long time = connection.objectIdletime(str);
                        if (time > (long)seconds) {
                            connection.del(str);
                            ++delnums;
                        }
                    }
                } finally {
                    if (connection != null) {
                        connection.close();
                    }

                }
            }

            return delnums;
        }
    }

    public String rpop(String key) {
        String value = null;
        if (StringUtils.isEmpty(key)) {
            return value;
        } else if (!StatFlagForApi.isNormal(this.clusterName)) {
            throw new JedisClusterException("Redis cluster is down");
        } else {
            byte[] result = this.jedisCluster.rpop(key.getBytes());
            if (result != null && result.length != 0) {
                value = new String(result);
                return value;
            } else {
                return value;
            }
        }
    }

    public byte[] rpop(byte[] key) {
        byte[] value = null;
        if (key != null && key.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                byte[] $value = this.jedisCluster.rpop(key);
                return $value;
            }
        } else {
            return (byte[])value;
        }
    }

    public boolean ltrim(String key, long start, long end) {
        boolean flag = false;
        if (StringUtils.isEmpty(key)) {
            return flag;
        } else if (!StatFlagForApi.isNormal(this.clusterName)) {
            throw new JedisClusterException("Redis cluster is down");
        } else {
            String result = this.jedisCluster.ltrim(key, start, end);
            if ("OK".equals(result)) {
                flag = true;
            }

            return flag;
        }
    }

    public boolean ltrim(byte[] key, long start, long end) {
        boolean flag = false;
        if (key != null && key.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                String result = this.jedisCluster.ltrim(key, start, end);
                if ("OK".equals(result)) {
                    flag = true;
                }

                return flag;
            }
        } else {
            return flag;
        }
    }

    public Long rpush(byte[] key, byte[]... values) {
        long result = 0L;
        if (key != null && key.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                result = this.jedisCluster.rpush(key, values);
                return result;
            }
        } else {
            return result;
        }
    }

    public String rpoplpush(String srckey, String dstkey) {
        String value = null;
        if (!StringUtils.isEmpty(srckey) && !StringUtils.isEmpty(dstkey)) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                value = this.jedisCluster.rpoplpush(srckey, dstkey);
                return value;
            }
        } else {
            return value;
        }
    }

    public byte[] rpoplpush(byte[] srckey, byte[] dstkey) {
        byte[] value = null;
        if (srckey != null && srckey.length != 0 && dstkey != null && dstkey.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                byte[] $value = this.jedisCluster.rpoplpush(srckey, dstkey);
                return $value;
            }
        } else {
            return (byte[])value;
        }
    }

    public Long linsert(String key, BinaryClient.LIST_POSITION where, String pivot, String value) {
        long result = 0L;
        if (StringUtils.isEmpty(key)) {
            return result;
        } else if (!StatFlagForApi.isNormal(this.clusterName)) {
            throw new JedisClusterException("Redis cluster is down");
        } else {
            result = this.jedisCluster.linsert(key, where, pivot, value);
            return result;
        }
    }

    public Long linsert(byte[] key, BinaryClient.LIST_POSITION where, byte[] pivot, byte[] value) {
        long result = 0L;
        if (key != null && key.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                result = this.jedisCluster.linsert(key, where, pivot, value);
                return result;
            }
        } else {
            return result;
        }
    }

    public Long lpushx(String key, String... values) {
        long result = 0L;
        if (StringUtils.isEmpty(key)) {
            return result;
        } else if (!StatFlagForApi.isNormal(this.clusterName)) {
            throw new JedisClusterException("Redis cluster is down");
        } else {
            result = this.jedisCluster.lpushx(key, values);
            return result;
        }
    }

    public Long lpushx(byte[] key, byte[]... values) {
        long result = 0L;
        if (key != null && key.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                result = this.jedisCluster.lpushx(key, values);
                return result;
            }
        } else {
            return result;
        }
    }

    public Long rpushx(String key, String... values) {
        long result = 0L;
        if (StringUtils.isEmpty(key)) {
            return result;
        } else if (!StatFlagForApi.isNormal(this.clusterName)) {
            throw new JedisClusterException("Redis cluster is down");
        } else {
            result = this.jedisCluster.rpushx(key, values);
            return result;
        }
    }

    public Long rpushx(byte[] key, byte[]... values) {
        long result = 0L;
        if (key != null && key.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                result = this.jedisCluster.rpushx(key, values);
                return result;
            }
        } else {
            return result;
        }
    }

    public Double zscore(String key, String member) {
        Double result = null;
        if (StringUtils.isEmpty(key)) {
            return result;
        } else if (!StatFlagForApi.isNormal(this.clusterName)) {
            throw new JedisClusterException("Redis cluster is down");
        } else {
            result = this.jedisCluster.zscore(key, member);
            return result;
        }
    }

    public Double zscore(byte[] key, byte[] member) {
        Double result = null;
        if (key != null && key.length != 0 && member != null && member.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                result = this.jedisCluster.zscore(key, member);
                return result;
            }
        } else {
            return result;
        }
    }

    public Set<String> zrange(String key, long start, long end) {
        Set<String> value = null;
        if (StringUtils.isEmpty(key)) {
            return value;
        } else if (!StatFlagForApi.isNormal(this.clusterName)) {
            throw new JedisClusterException("Redis cluster is down");
        } else {
            value = this.jedisCluster.zrange(key, start, end);
            return value;
        }
    }

    public Set<byte[]> zrange(byte[] key, long start, long end) {
        Set<byte[]> value = null;
        if (key != null && key.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                value = this.jedisCluster.zrange(key, start, end);
                return value;
            }
        } else {
            return value;
        }
    }

    public Set<Tuple> zrangeWithScores(String key, long start, long end) {
        Set<Tuple> value = null;
        if (StringUtils.isEmpty(key)) {
            return value;
        } else if (!StatFlagForApi.isNormal(this.clusterName)) {
            throw new JedisClusterException("Redis cluster is down");
        } else {
            value = this.jedisCluster.zrangeWithScores(key, start, end);
            return value;
        }
    }

    public Set<Tuple> zrangeWithScores(byte[] key, long start, long end) {
        Set<Tuple> value = null;
        if (key != null && key.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                value = this.jedisCluster.zrangeWithScores(key, start, end);
                return value;
            }
        } else {
            return value;
        }
    }

    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max) {
        Set<Tuple> value = null;
        if (StringUtils.isEmpty(key)) {
            return value;
        } else if (!StatFlagForApi.isNormal(this.clusterName)) {
            throw new JedisClusterException("Redis cluster is down");
        } else if (max > min) {
            value = this.jedisCluster.zrangeByScoreWithScores(key, min, max);
            return value;
        } else {
            return value;
        }
    }

    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max, int offset, int count) {
        Set<Tuple> value = null;
        if (StringUtils.isEmpty(key)) {
            return value;
        } else if (!StatFlagForApi.isNormal(this.clusterName)) {
            throw new JedisClusterException("Redis cluster is down");
        } else if (max > min) {
            value = this.jedisCluster.zrangeByScoreWithScores(key, min, max, offset, count);
            return value;
        } else {
            return value;
        }
    }

    public Set<Tuple> zrangeByScoreWithScores(byte[] key, double min, double max) {
        Set<Tuple> value = null;
        if (key != null && key.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else if (max > min) {
                value = this.jedisCluster.zrangeByScoreWithScores(key, min, max);
                return value;
            } else {
                return value;
            }
        } else {
            return value;
        }
    }

    public Set<Tuple> zrangeByScoreWithScores(byte[] key, double min, double max, int offset, int count) {
        Set<Tuple> value = null;
        if (key != null && key.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else if (max > min) {
                value = this.jedisCluster.zrangeByScoreWithScores(key, min, max, offset, count);
                return value;
            } else {
                return value;
            }
        } else {
            return value;
        }
    }

    public Set<String> zrevrange(String key, long start, long end) {
        Set<String> value = null;
        if (StringUtils.isEmpty(key)) {
            return value;
        } else if (!StatFlagForApi.isNormal(this.clusterName)) {
            throw new JedisClusterException("Redis cluster is down");
        } else {
            value = this.jedisCluster.zrevrange(key, start, end);
            return value;
        }
    }

    public Set<byte[]> zrevrange(byte[] key, long start, long end) {
        Set<byte[]> value = null;
        if (key != null && key.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                value = this.jedisCluster.zrevrange(key, start, end);
                return value;
            }
        } else {
            return value;
        }
    }

    public Long zrank(String key, String member) {
        Long result = null;
        if (StringUtils.isEmpty(key)) {
            return result;
        } else if (!StatFlagForApi.isNormal(this.clusterName)) {
            throw new JedisClusterException("Redis cluster is down");
        } else {
            result = this.jedisCluster.zrank(key, member);
            return result;
        }
    }

    public Long zrank(byte[] key, byte[] member) {
        Long result = null;
        if (key != null && key.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                result = this.jedisCluster.zrank(key, member);
                return result;
            }
        } else {
            return result;
        }
    }

    public Long zrevrank(String key, String member) {
        Long result = null;
        if (StringUtils.isEmpty(key)) {
            return result;
        } else if (!StatFlagForApi.isNormal(this.clusterName)) {
            throw new JedisClusterException("Redis cluster is down");
        } else {
            result = this.jedisCluster.zrevrank(key, member);
            return result;
        }
    }

    public Long zrevrank(byte[] key, byte[] member) {
        Long result = null;
        if (key != null && key.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                result = this.jedisCluster.zrevrank(key, member);
                return result;
            }
        } else {
            return result;
        }
    }

    public List<String> mget(String... keys) {
        List<String> result = null;
        if (keys != null && keys.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                result = this.jedisCluster.mget(keys);
                return result;
            }
        } else {
            return result;
        }
    }

    public List<byte[]> mget(byte[]... keys) {
        List<byte[]> result = null;
        if (keys != null && keys.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                result = this.jedisCluster.mget(keys);
                return result;
            }
        } else {
            return result;
        }
    }

    public Long publish(String channel, String message) {
        if (channel != null && message != null) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                try {
                    return this.jedisCluster.publish(channel, message);
                } catch (JedisConnectionException var4) {
                    return this.reconnect().publish(channel, message);
                }
            }
        } else {
            return 0L;
        }
    }

    public Long publish(byte[] channel, byte[] message) {
        if (channel != null && message != null) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                try {
                    return this.jedisCluster.publish(channel, message);
                } catch (JedisConnectionException var4) {
                    return this.reconnect().publish(channel, message);
                }
            }
        } else {
            return 0L;
        }
    }

    public void subscribe(JedisPubSub jedisPubSub, String... channels) {
        if (jedisPubSub != null && channels != null && channels.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                try {
                    SubRegistration.registerChannelSub(this.clusterName, jedisPubSub, channels);
                    this.jedisCluster.subscribe(jedisPubSub, channels);
                } catch (JedisConnectionException var4) {
                    this.reconnect().subscribe(jedisPubSub, channels);
                }

            }
        }
    }

    public void subscribe(BinaryJedisPubSub jedisPubSub, byte[]... channels) {
        if (jedisPubSub != null && channels != null && channels.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                try {
                    SubRegistration.registerBinaryChannelSub(this.clusterName, jedisPubSub, channels);
                    this.jedisCluster.subscribe(jedisPubSub, channels);
                } catch (JedisConnectionException var4) {
                    this.reconnect().subscribe(jedisPubSub, channels);
                }

            }
        }
    }

    public void psubscribe(JedisPubSub jedisPubSub, String... patterns) {
        if (jedisPubSub != null && patterns != null && patterns.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                try {
                    SubRegistration.registerPatternSub(this.clusterName, jedisPubSub, patterns);
                    this.jedisCluster.psubscribe(jedisPubSub, patterns);
                } catch (JedisConnectionException var4) {
                    this.reconnect().psubscribe(jedisPubSub, patterns);
                }

            }
        }
    }

    public void psubscribe(BinaryJedisPubSub jedisPubSub, byte[]... patterns) {
        if (jedisPubSub != null && patterns != null && patterns.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                try {
                    SubRegistration.registerBinaryPatternSub(this.clusterName, jedisPubSub, patterns);
                    this.jedisCluster.psubscribe(jedisPubSub, patterns);
                } catch (JedisConnectionException var4) {
                    this.reconnect().psubscribe(jedisPubSub, patterns);
                }

            }
        }
    }

    private JedisCluster reconnect() {
        Map map = this.jedisCluster.getClusterNodes();
        Set entries = map.entrySet();
        Iterator iter = entries.iterator();
        HashSet haps = new HashSet();

        while(iter.hasNext()) {
            Entry entry = (Entry)iter.next();
            String[] arr = ((String)entry.getKey()).split(":");
            haps.add(new HostAndPort(arr[0], Integer.valueOf(arr[1])));
        }

        return new JedisCluster(haps, 2000, 18);
    }

    public String spop(String key) {
        String result = null;
        if (StringUtils.isEmpty(key)) {
            return result;
        } else if (!StatFlagForApi.isNormal(this.clusterName)) {
            throw new JedisClusterException("Redis cluster is down");
        } else {
            result = this.jedisCluster.spop(key);
            return result;
        }
    }

    public byte[] spop(byte[] key) {
        byte[] result = null;
        if (key != null && key.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                byte[] $result = this.jedisCluster.spop(key);
                return $result;
            }
        } else {
            return (byte[])result;
        }
    }

    public Long srem(String key, String... member) {
        long result = 0L;
        if (StringUtils.isEmpty(key)) {
            return result;
        } else if (!StatFlagForApi.isNormal(this.clusterName)) {
            throw new JedisClusterException("Redis cluster is down");
        } else {
            result = this.jedisCluster.srem(key, member);
            return result;
        }
    }

    public Long srem(byte[] key, byte[]... member) {
        long result = 0L;
        if (key != null && key.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                result = this.jedisCluster.srem(key, member);
                return result;
            }
        } else {
            return result;
        }
    }

    public Long setnx(String key, String value) {
        long result = 0L;
        if (!StringUtils.isEmpty(key) && !StringUtils.isEmpty(value)) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                result = this.jedisCluster.setnx(key, value);
                return result;
            }
        } else {
            return result;
        }
    }

    public Long setnx(byte[] key, byte[] value) {
        long result = 0L;
        if (key != null && key.length != 0 && null != value && value.length != 0) {
            if (!StatFlagForApi.isNormal(this.clusterName)) {
                throw new JedisClusterException("Redis cluster is down");
            } else {
                result = this.jedisCluster.setnx(key, value);
                return result;
            }
        } else {
            return result;
        }
    }

    static {
        IS_OK = Protocol.Keyword.OK.name();
    }
}
