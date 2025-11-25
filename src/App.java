import CacheImpl.CacheTypeEnum;
import Interfaces.CacheFactory;
import Interfaces.ICache;


public class App {
    public static void main(String [] args) {
        testFactoryWithFIFO();   // Run the FIFO factory test
        runCacheTests();   // Run the general cache tests
    }

    // Test Factory specifically with FIFO cache
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


    private static void runCacheTests(){

        CacheFactory factory = new CacheFactory();

        System.out.println( " === FIFO Test ===");
        ICache fifo = factory.createCacheInstance(CacheTypeEnum.FIFO,2);


        fifo.put("a",1);
        fifo.put("b",2);
        fifo.put("c",3); //removes "a"

        System.out.println(fifo.get("a")); //-1
        System.out.println(fifo.get("b")); //2
        System.out.println(fifo.get("c")); //3

        System.out.println( " === \n LRU Test ===");

        ICache lru = factory.createCacheInstance(CacheTypeEnum.LRU,2);

        lru.put("x",15);
        lru.put("y",25);
        lru.get("x"); //x becomes "recently used"

        lru.put("z",35);  //removes "y"

        System.out.println(lru.get("y"));//-1
        System.out.println(lru.get("x"));//15
        System.out.println(lru.get("z"));//35

        System.out.println("=== LFU Cache Test ===");
        ICache lfu = factory.createCacheInstance(CacheTypeEnum.LFU, 2);

        lfu.put("a", 1);
        lfu.put("b", 2);

        lfu.get("a");   // freq(a) = 2
        lfu.put("c", 3); // removes "b" (freq(b) = 1)

        System.out.println(lfu.get("a")); // 1
        System.out.println(lfu.get("b")); // -1
        System.out.println(lfu.get("c")); // 3

    }
}