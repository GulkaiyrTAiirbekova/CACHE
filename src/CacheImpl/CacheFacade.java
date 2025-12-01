package CacheImpl;

import Interfaces.ICache;
import java.text.MessageFormat;
import CacheImpl.CacheFactory;
import CacheImpl.CacheTypeEnum;

public class CacheFacade {
    private ICache fifoCache;
    private ICache lruCache;
    private ICache lfuCache;

    public CacheFacade(int size) {
        CacheFactory factory = new CacheFactory();
        fifoCache = factory.createCacheInstance(CacheTypeEnum.FIFO, size);
        lruCache = factory.createCacheInstance(CacheTypeEnum.LRU, size);
        lfuCache = factory.createCacheInstance(CacheTypeEnum.LFU, size);
    }

    public void put(String key, int value) {
        fifoCache.put(key, value);
        lruCache.put(key, value);
        lfuCache.put(key, value);
    }

    public int get(String key) {
        int value = lruCache.get(key);
        if (value != -1) return value;

        value = fifoCache.get(key);
        if (value != -1) return value;

        return lfuCache.get(key);
    }

    public void remove(String key) {
        fifoCache.remove(key);
        lruCache.remove(key);
        lfuCache.remove(key);
    }

    public int totalSize() {
        return fifoCache.getSize() + lruCache.getSize() + lfuCache.getSize();
    }
}

