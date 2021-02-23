package com.house.demo.shiro.cache;

import com.house.demo.common.utils.ApplicationContextUtil;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author xjj
 */

public class RedisCache<K,V> implements Cache<K,V> {

    @Override
    public V get(K k) throws CacheException {
        RedisTemplate redisTemplate = ApplicationContextUtil.getBean("redisTemplate");
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return (V) redisTemplate.opsForValue().get(k.toString());
    }

    @Override
    public V put(K k, V v) throws CacheException {

        RedisTemplate redisTemplate = ApplicationContextUtil.getBean("redisTemplate");
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.opsForValue().set(k.toString(),v,5,TimeUnit.MINUTES);

        return null;
    }

    @Override
    public V remove(K k) throws CacheException {

        RedisTemplate redisTemplate = ApplicationContextUtil.getBean("redisTemplate");

        redisTemplate.setKeySerializer(new StringRedisSerializer());


        redisTemplate.delete(k.toString());
        return null;
    }

    @Override
    public void clear() throws CacheException {


    }

    @Override
    public int size() {

        return 0;
    }

    @Override
    public Set<K> keys() {

        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }
}
