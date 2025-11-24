package Interfaces;

import CacheImpl.LRUCache;
import  Interfaces.ICache;

import CacheImpl.CacheTypeEnum;
import CacheImpl.FIFOCache;

import java.text.MessageFormat;

public class CacheFactory {
    public ICache createCacheInstance(CacheTypeEnum cacheTypeEnum, int capacity){
        return switch (cacheTypeEnum) {
            case FIFO -> {
                ;
                return new FIFOCache(capacity);
            }
            case LRU -> {
                ;
                return new LRUCache(capacity);
            }
            default -> {
                System.out.println(MessageFormat.format("Cache with type{0} is not implemented yet.", cacheTypeEnum));
                throw new AssertionError();
            }
        };
    }
}
