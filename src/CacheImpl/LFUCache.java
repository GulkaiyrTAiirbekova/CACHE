package CacheImpl;

import Interfaces.ICache;
import java.util.*;

public class LFUCache implements ICache {

    private final int capacity;
    private int minFreq;
    private String serverName; // Added for Builder compatibility

    private final Map<String, Integer> valueMap = new HashMap<>();
    private final Map<String, Integer> freqMap = new HashMap<>();
    private final Map<Integer, LinkedHashSet<String>> freqList = new HashMap<>();

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFreq = 1;
    }

    // -------------------------
    // Server name (Builder API)
    // -------------------------
    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getServerName() {
        return serverName;
    }

    // -------------------------
    // Core LFU Logic
    // -------------------------
    @Override
    public void put(String key, int value) {
        if (capacity == 0) return;

        // If key exists → update
        if (valueMap.containsKey(key)) {
            valueMap.put(key, value);
            increaseFrequency(key);
            return;
        }

        // If full → remove LFU
        if (valueMap.size() >= capacity) {
            removeLFUKey();
        }

        // Insert new key with frequency 1
        valueMap.put(key, value);
        freqMap.put(key, 1);
        freqList.computeIfAbsent(1, k -> new LinkedHashSet<>()).add(key);
        minFreq = 1;
    }

    @Override
    public int get(String key) {
        if (!valueMap.containsKey(key)) return -1;

        increaseFrequency(key);
        return valueMap.get(key);
    }

    private void increaseFrequency(String key) {
        int freq = freqMap.get(key);
        freqList.get(freq).remove(key);

        // If this was the last key with this freq → increment minFreq
        if (freq == minFreq && freqList.get(freq).isEmpty()) {
            minFreq++;
        }

        int newFreq = freq + 1;
        freqMap.put(key, newFreq);
        freqList.computeIfAbsent(newFreq, k -> new LinkedHashSet<>()).add(key);
    }

    private void removeLFUKey() {
        LinkedHashSet<String> keys = freqList.get(minFreq);
        String leastUsedKey = keys.iterator().next();

        keys.remove(leastUsedKey);
        valueMap.remove(leastUsedKey);
        freqMap.remove(leastUsedKey);
    }

    @Override
    public void remove(String key) {
        if (!valueMap.containsKey(key)) return;

        int freq = freqMap.get(key);
        freqList.get(freq).remove(key);

        valueMap.remove(key);
        freqMap.remove(key);
    }

    @Override
    public void clear() {
        valueMap.clear();
        freqMap.clear();
        freqList.clear();
        minFreq = 1;
    }

    @Override
    public int getSize() {
        return valueMap.size();
    }

    @Override
    public boolean containsKey(String key) {
        return valueMap.containsKey(key);
    }
}

