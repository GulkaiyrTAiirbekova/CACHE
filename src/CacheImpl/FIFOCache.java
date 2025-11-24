package CacheImpl;

import Interfaces.ICache;

import Interfaces.ICache;
import java.util.*;

public class FIFOCache implements ICache {
    private final int capacity;
    private final Map<String,Integer>storage =  new HashMap<>();
    private final Queue<String> order = new LinkedList<>();

    public FIFOCache(int capacity){
        this.capacity = capacity;
    }

    @Override
    public void put(String key, int value){

        if(storage.containsKey(key)){
            storage.put(key,value);
            return;
        }

        if(storage.size() >= capacity){
            String oldestKey = order.poll();
            storage.remove(oldestKey);
        }
    }
    @Override
    public int get(String key){
        return storage.getOrDefault(key, -1);
    }


    @Override
    public void remove(String key) {
        if(storage.containsKey(key)){
            storage.remove(key);
            order.remove(key);
        }
    }

    @Override
    public void clear() {
        storage.clear();
        order.clear();

    }

    @Override
    public int getSize() {
        return storage.size();
    }

    @Override
    public boolean containsKey(String key) {
        return storage.containsKey(key);
    }

}
