package CacheImpl;

import Interfaces.ICache;
import java.util.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache implements ICache {

    int capacity;
    String name;

    private final int capacity;
    private final LinkedHashMap<String, Integer> cache;


    public  LRUCache(int capacity){
        this.capacity = capacity;

        this.cache = new LinkedHashMap<>(capacity, 0.75f, true){

            @Override
            protected boolean removeEldestEntry(Map.Entry<String, Integer> eldest) {
                return size()> LRUCache.this.capacity;
            }
        };

    }


    @Override
    public void put(String key, int value){
        cache.put(key,value);
    }


    @Override
    public int get(String key){
        return cache.getOrDefault(key,-1);
    }


    @Override
    public void remove(String key) {
        cache.remove(key);
    }


    @Override
    public void clear() {
        cache.clear();
    }


    @Override
    public int getSize() {
        return cache.size();
    }


    @Override
    public boolean containsKey(String key) {
        return cache.containsKey(key);
    }
}