package CacheImpl;

import Interfaces.ICache;

public abstract class CacheDecorator implements ICache {
    private final ICache cache;

    public CacheDecorator(ICache cache) {
        this.cache = cache;
    }

    @Override
    public void clear() {cache.clear();}

    @Override
    public boolean containsKey(String key) {
        return cache.containsKey(key);
    }

    @Override
    public int get(String key) {
        return cache.get(key);
    }

    @Override
    public int getSize() {
        return cache.getSize();
    }

    @Override
    public void put(String key, int value) {
        cache.put(key, value);
    }

    @Override
    public void remove(String key) {
        cache.remove(key);
    }
}

