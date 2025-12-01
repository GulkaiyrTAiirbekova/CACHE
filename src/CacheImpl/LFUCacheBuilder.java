package CacheImpl;

public class LFUCacheBuilder {
    private int capacity;
    private String serverName;


    public LFUCacheBuilder setCapacity(int capacity){
        this.capacity=capacity;
        return this;
    }
    public LFUCacheBuilder setServerName(String serverName){
        this.serverName = serverName;
        return this;
    }

    public LFUCache build(){
        LFUCache  cache = new LFUCache(capacity);
        cache.setServerName(serverName);
        return cache;
    }
}
