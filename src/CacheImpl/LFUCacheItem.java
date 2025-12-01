package CacheImpl;

public class LFUCacheItem {
    private String key;
    private int frequency;
    private Integer value;

    // Default constructor
    public LFUCacheItem() {
        this.frequency = 1; // optional default frequency
    }

    // Parameterized constructor
    public LFUCacheItem(String key, Integer value) {
        this.key = key;
        this.value = value;
        this.frequency = 1;
    }

    // Prototype method: clone
    public LFUCacheItem clone() {
        LFUCacheItem clonedCacheItem = new LFUCacheItem();
        clonedCacheItem.setKey(this.key);
        clonedCacheItem.setValue(this.value);
        clonedCacheItem.setFrequency(this.frequency);
        return clonedCacheItem;
    }

    // Getters and setters
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
}
