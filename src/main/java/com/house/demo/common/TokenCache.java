package com.house.demo.common;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author xjj
 */
public class TokenCache<K,V> extends LinkedHashMap<K,V> {

    private int maxSize;
    public TokenCache(int maxSize){
        super(16,0.75F);
        this.maxSize = maxSize;
    }
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size()>maxSize;
    }

}
