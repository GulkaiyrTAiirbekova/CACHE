import CacheImpl.FIFOCache;
import Interfaces.ICache;

public class App {
    public static void  main(String [] args) throws Exception {
        testFIFO();

    }

    private static void testFIFO(){
        ICache fifoCache = new FIFOCache();
        fifoCache.put("key1",1);
        fifoCache.put("key2", 123);
        fifoCache.getSize();// it should return only both elements

        System.out.println(fifoCache.get("key2")); //123


        fifoCache.remove("key1");
        fifoCache.getSize();// it should return only 1 element
        System.out.println(fifoCache.getSize());


    }
}
