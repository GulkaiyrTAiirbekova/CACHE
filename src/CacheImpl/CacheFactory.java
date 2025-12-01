package CacheImpl;

import Interfaces.ICache;
import java.text.MessageFormat;

public class CacheFactory {

    public ICache createCacheInstance(CacheTypeEnum type, int capacity) {
        switch (type) {

            case FIFO:
                return new FIFOCache(capacity);

            case LFU:
                return new LFUCacheBuilder()
                        .setCapacity(capacity)
                        .setServerName("localhost: 8080")
                        .build();


            case LRU:
                return new LRUCache(capacity);


            default:
                System.out.println(MessageFormat.format(
                        "Cache with type {0} is not implemented yet.", type));
                return null; //must  return something
        }
    }
}