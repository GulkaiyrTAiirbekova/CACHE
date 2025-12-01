package CacheImpl;

import Interfaces.ICache;

public class CacheProxy  implements ICache {
    private ICache realCache;

    public CacheProxy(ICache cache){
        this.realCache = cache;
    }

    @Override
    public void put(String key, int value){
        System.out.println("[Proxy] Putting key: " + key);
        realCache.put(key,value);
    }
    @Override
    public int get(String key){
        System.out.println("[Proxy] Getting key: " + key);
        return realCache.get(key);
    }
    @Override
    public void remove(String key){
        System.out.println("[Proxy] Removing key: " + key);
        realCache.remove(key);
    }
    @Override
    public int getSize(){
        System.out.println("[Proxy] Getting cache size ");
        return realCache.getSize();
    }
    @Override
    public boolean containsKey(String key) {
        System.out.println("[Proxy] Checking containsKey for: " + key);
        return realCache.containsKey(key);
    }
    @Override
    public void clear() {
        System.out.println("[Proxy] Clearing cache");
        realCache.clear();
    }


}
