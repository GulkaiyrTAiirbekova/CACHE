package Interfaces;

import CacheImpl.*;

public class CacheFactory {

    public ICache createCacheInstance(CacheTypeEnum type, int capacity) {
        return switch (type) {
            case FIFO -> new FIFOCache(capacity);
            case LRU -> new LRUCache(capacity);
            case LFU -> new LFUCache(capacity);
        };
    }
}

