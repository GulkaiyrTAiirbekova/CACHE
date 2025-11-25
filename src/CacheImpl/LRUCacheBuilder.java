package CacheImpl;

import Interfaces.ICache;

public class LRUCacheBuilder {

    private LRUCache lruCache;

    public LRUCacheBuilder(int capacity) {
        this.lruCache = new LRUCache(capacity);
    }

    public LRUCacheBuilder setName(String name) {
        lruCache.setName(name);
        return this;
    }

    public LRUCacheBuilder setServerName(String serverName) {
        lruCache.setServerName(serverName);
        return this;
    }

    public ICache build() {
        return lruCache;
    }
}
