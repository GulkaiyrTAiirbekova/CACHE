package CacheImpl;

import Interfaces.ICache;

public class LegacyCacheAdapter implements ICache {
    private final LegacyCache legacyCache;
    private final int capacity;

    public LegacyCacheAdapter(int capacity) {
        this.capacity = capacity;
        this.legacyCache = new LegacyCache();
    }

    @Override
    public void put(String key, int value) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        if (legacyCache.getSize() >= capacity) {
            System.out.println("Cache is full. Please, remove items.");
            return;
        }

        legacyCache.put(key, value);
    }

    @Override
    public int get(String key) {
        return legacyCache.get(key);
    }

    @Override
    public void remove(String key) {
        legacyCache.remove(key);
    }

    @Override
    public void clear() {
        legacyCache.clear();
    }

    @Override
    public int getSize() {
        return legacyCache.getSize();
    }

    @Override
    public boolean containsKey(String key) {
        return legacyCache.containsKey(key);
    }
}
