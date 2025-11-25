package CacheImpl;

import Interfaces.ICache;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache implements ICache {

    private final int capacity;

    private final LinkedHashMap<String, Integer> cache;

    // === Added for Builder ===
    private String name;
    private String serverName;

    public LRUCache(int capacity) {
        this.capacity = capacity;

        this.cache = new LinkedHashMap<>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, Integer> eldest) {
                return size() > LRUCache.this.capacity;
            }
        };
    }

    // === Builder setter methods ===
    public void setName(String name) {
        this.name = name;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    // === Cache Logic ===
    @Override
    public void put(String key, int value) {
        cache.put(key, value);
    }

    @Override
    public int get(String key) {
        return cache.getOrDefault(key, -1);
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
