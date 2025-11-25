import CacheImpl.CacheTypeEnum;
import CacheImpl.LRUCacheBuilder;
import Interfaces.CacheFactory;
import Interfaces.ICache;

public class App {
    public static void main(String[] args) {
        // Uncomment any test you want to run

        //testFactoryWithFIFO();
        //runCacheTests();
        testLRUCacheBuilder();   // <-- Running Builder Test
    }

    // -------------------------
    // LRU Builder Test
    // -------------------------
    private static void testLRUCacheBuilder() {
        System.out.println("=== LRU Cache Builder Test ===");

        LRUCacheBuilder builder = new LRUCacheBuilder(2);

        ICache lruCache = builder
                .setName("LRU custom cache")
                .setServerName("localhost")
                .build();

        lruCache.put("key1", 100);
        lruCache.put("key2", 200);
        lruCache.get("key1");   // key1 becomes recently used
        lruCache.put("key3", 300); // should evict key2

        System.out.println(lruCache.get("key1")); // 100
        System.out.println(lruCache.get("key2")); // -1
        System.out.println(lruCache.get("key3")); // 300

        System.out.println("LRU Builder test completed.\n");
    }

    // -------------------------
    // Factory Test (FIFO)
    // -------------------------
    private static void testFactoryWithFIFO() {
        CacheFactory factory = new CacheFactory();
        System.out.println("=== Factory Test: FIFO ===");

        ICache fifo = factory.createCacheInstance(CacheTypeEnum.FIFO, 2);

        fifo.put("key1", 100);
        fifo.put("key2", 200);
        fifo.put("key3", 300); // Should evict "key1"

        System.out.println(fifo.get("key1")); // -1
        System.out.println(fifo.get("key2")); // 200
        System.out.println(fifo.get("key3")); // 300
        System.out.println("FIFO factory test completed.\n");
    }

    // -------------------------
    // All Caches Test
    // -------------------------
    private static void runCacheTests() {
        CacheFactory factory = new CacheFactory();

        System.out.println("=== FIFO Test ===");
        ICache fifo = factory.createCacheInstance(CacheTypeEnum.FIFO, 2);

        fifo.put("a", 1);
        fifo.put("b", 2);
        fifo.put("c", 3); // evicts a

        System.out.println(fifo.get("a")); // -1
        System.out.println(fifo.get("b")); // 2
        System.out.println(fifo.get("c")); // 3

        System.out.println("\n=== LRU Test ===");
        ICache lru = factory.createCacheInstance(CacheTypeEnum.LRU, 2);

        lru.put("x", 15);
        lru.put("y", 25);
        lru.get("x"); // x becomes recent
        lru.put("z", 35); // evicts y

        System.out.println(lru.get("y")); // -1
        System.out.println(lru.get("x")); // 15
        System.out.println(lru.get("z")); // 35

        System.out.println("\n=== LFU Test ===");
        ICache lfu = factory.createCacheInstance(CacheTypeEnum.LFU, 2);

        lfu.put("a", 1);
        lfu.put("b", 2);
        lfu.get("a"); // freq a = 2

        lfu.put("c", 3); // evicts b (least freq = 1)

        System.out.println(lfu.get("a")); // 1
        System.out.println(lfu.get("b")); // -1
        System.out.println(lfu.get("c")); // 3
    }
}
