package CacheImpl;

import java.util.HashMap;
import java.util.Map;

public class LegacyCache {
    private final Map<String , Integer> keyValueMap = new HashMap<>();

    // Put new pair {key:value} to cache.
    public void put(String key, Integer value) {
        keyValueMap.put(key, value);
    }

    //Get value by key

    public Integer get(String key){
        return keyValueMap.get(key);
    }

    // Remove item in cache by key.
    public void remove(String key){
        keyValueMap.remove(key);
    }

    //optional methods

    public void clear(){
        keyValueMap.clear();
    }

    public int getSize(){
        return keyValueMap.size();
    }

    public boolean containsKey(String key){
        return  keyValueMap.containsKey(key);
    }
}
