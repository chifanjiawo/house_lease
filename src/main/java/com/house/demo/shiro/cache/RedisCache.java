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

    private String cacheName;

    public RedisCache() {
    }
    public RedisCache(String cacheName){
        this.cacheName = cacheName;

    }

    @Override
    public V get(K k) throws CacheException {


        return (V) getRedisTemplate().opsForHash().get(cacheName,k.toString());
    }

    @Override
    public V put(K k, V v) throws CacheException {

        getRedisTemplate().opsForHash().put(cacheName,k.toString(),v);

        getRedisTemplate().expire(cacheName,10,TimeUnit.MINUTES);
        return null;
    }

    @Override
    public V remove(K k) throws CacheException {

        getRedisTemplate().delete(cacheName);

        return null;
    }

    @Override
    public void clear() throws CacheException {

        getRedisTemplate().delete(cacheName);
    }

    @Override
    public int size() {
        return Math.toIntExact(getRedisTemplate().opsForHash().size(cacheName));
    }

    @Override
    public Set<K> keys() {
        return getRedisTemplate().opsForHash().keys(cacheName);
    }

    @Override
    public Collection<V> values() {
        return getRedisTemplate().opsForHash().values(cacheName);
    }


    private RedisTemplate getRedisTemplate(){
        RedisTemplate redisTemplate = ApplicationContextUtil.getBean("redisTemplate");

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

        return redisTemplate;

    }
}
