package CacheImpl;

import Interfaces.ICache;
import java.util.*;

public class LRUCache implements ICache {

    private final int capacity;
    private final LinkedHashMap<String, Integer> cache;


    public  LRUCache(int capacity){
        this.capacity = capacity;

        this.cache = new LinkedHashMap<>(capacity, 0,75f, true){
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
        return cache.remove(key);
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