package CacheImpl;

public class LRUCacheBuilder {
    private LRUCache lruCache;

    public LRUCacheBuilder( int capacity) {
        LRUCache lruCache = new LRUCache(capacity);
    }

    public LRUCacheBuilder setName (String name){
        lruCache.setName(name);
        return this;
    }

    public LRUCacheBuilder setServerName(String serverName){
        lruCache.setServerName(serverName);
        return this;
    }

    public class LRUCache build(){
       return lruCache;
    }
}
