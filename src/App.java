/*
public class App {


    public static void main(String[] args) {
        // Cache tests
        testFactoryWithFIFO();
        runCacheTests();
        testLRUCacheBuilder();   // LRU Builder Test

        // Animal builder test
        testAnimal();

        // FastFoodRestaurant builder test
        testFFR();
    }

    // -------------------------
    // Animal Builder Test
    // -------------------------
    private static void testAnimal() {
        Animal giraffe = new AnimalBuilder().setAge(1).setHeight(150).setWeight(10).build();
        Animal lion = new AnimalBuilder().setAge(2).setHeight(10).setWeight(12).setName("Mufasa").build();
        Animal tiger = new AnimalBuilder().setAge(3).setHeight(11).setWeight(25).setName("Ragnar").build();

        System.out.println("Animal Tests:");
        System.out.println(giraffe);
        System.out.println(lion);
        System.out.println(tiger);
        System.out.println();
    }

    // -------------------------
    // LRU Cache Builder Test
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
        lruCache.put("key3", 300); // evicts key2

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
        lfu.put("c", 3); // evicts b
        System.out.println(lfu.get("a")); // 1
        System.out.println(lfu.get("b")); // -1
        System.out.println(lfu.get("c")); // 3
        System.out.println();
    }

    // -------------------------
    // FastFoodRestaurant Builder Test
    // -------------------------
    private static void testFFR() {
        // f1: Kids menu first, then Christmas menu (additive)
        FastFoodRestaurant f1 = new FastFoodRestaurantBuilder()
                .buildKidsMenu()
                .buildChristmasMenu()
                .build();

        // f2: Christmas menu first, then Kids menu (additive)
        FastFoodRestaurant f2 = new FastFoodRestaurantBuilder()
                .buildChristmasMenu()
                .buildKidsMenu()
                .build();

        System.out.println("FastFoodRestaurant Tests:");
        System.out.println(f1);
        System.out.println(f2);
    }
}

 */


import CacheImpl.*;
import Interfaces.ICache;

// To make code cleaner
public class App {
    public static void main(String[] args) throws Exception {
        // testLFUwithFactory();
        // testFIFOwithFactory();
        // testLegacyCacheAdapter();
        testCacheTimeMeasureDecorator();
        testProxyCache();
        testCacheFacade();
    }

    private static void testCacheTimeMeasureDecorator() {
        ICache lfuCache = new CacheFactory().createCacheInstance(CacheTypeEnum.LFU, 10);
        ICache decorator = new CacheTimeMeasureDecorator(lfuCache);

        decorator.put("item1", 1);
        decorator.put("item2", 2);

        decorator.get("item1");
        decorator.get("item3"); // non-existent key
    }

    private static void testLegacyCacheAdapter() {
        ICache legacyCache = new LegacyCacheAdapter(4);

        legacyCache.put("item1", 1);
        legacyCache.put("item2", 2);
        legacyCache.put("item3", 3);
        legacyCache.put("item4", 4);

        System.out.println("Size after 4 inserts: " + legacyCache.getSize());
        legacyCache.put("item5", 5); // should print "Cache is full"
        System.out.println("Size after trying 5th insert: " + legacyCache.getSize());

        legacyCache.remove("item2");
        System.out.println("Size after removing item2: " + legacyCache.getSize());

        legacyCache.put("item6", 6);
        System.out.println("Size after inserting item6: " + legacyCache.getSize());
    }

    private static void testLFUwithFactory() {
        ICache lfuCache = new CacheFactory().createCacheInstance(CacheTypeEnum.LFU, 10);

        lfuCache.put("key1", 1);
        lfuCache.put("key2", 2);
        System.out.println("Size after 2 inserts: " + lfuCache.getSize());

        lfuCache.remove("key1");
        System.out.println("Size after removing key1: " + lfuCache.getSize());
    }

    private static void testFIFOwithFactory() {
        ICache fifoCache = new CacheFactory().createCacheInstance(CacheTypeEnum.FIFO, 9);

        fifoCache.put("key1", 1);
        fifoCache.put("key2", 2);

        System.out.println("Size after 2 inserts: " + fifoCache.getSize());
        System.out.println("Value of key2: " + fifoCache.get("key2"));

        fifoCache.remove("key1");
        System.out.println("Size after removing key1: " + fifoCache.getSize());
    }

// -------------------------
// Proxy Cache Test
// -------------------------



    private static void testProxyCache(){
        System.out.println("==== Proxy Cache Tes===");
        ICache lru = new CacheFactory().createCacheInstance(CacheTypeEnum.LFU,2);
        ICache proxyCache = new CacheProxy(lru);

        proxyCache.put("x", 100);
        System.out.println("Get x: " + proxyCache.get("x"));

        proxyCache.remove("x");
        System.out.println("Get x after remove: " +proxyCache.get("x"));

        System.out.println("Proxy Cache test done. /n");



    }
    // -------------------------
    //  Facade  Cache Test
    // -------------------------

    private static void testCacheFacade(){
        System.out.println("==== Facade Cache Tes===");

        CacheFacade facade = new CacheFacade(2);

        facade.put("a", 1);
        facade.put("b", 2);

        System.out.println("Get a: " + facade.get("a")); //1
        System.out.println("Get b: " + facade.get("b")); //2

        facade.remove("b");
        System.out.println("Total size after removing b: " +facade.totalSize());

        System.out.println("Facade Cache test done. /n");





    }

}


