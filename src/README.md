# CacheHomework — Reviewer's Guide

## 📌 Goal of the Assignment
Implement a simple **in-memory cache** that stores:
- **Keys:** `String`
- **Values:** `Integer`

Each cache must support the following operations:

### Required API

void put(String key, int value);
Integer get(String key);     // returns null or -1 if key not found
void remove(String key);

### Optional API

void clear();
int getSize();
boolean containsKey(String key);


All cache implementations accept a capacity during construction.
If an insertion exceeds this capacity, the cache must evict one entry according to its replacement strategy.

### Implemented Cache Strategies
1. FIFOCache

Evicts entries in First-In-First-Out order.
The oldest inserted item is removed first, regardless of how often it is accessed.

2. LRUCache

Evicts the Least Recently Used entry.
Every get() or put() updates the usage order, and items that haven’t been used recently are removed first.
Uses LinkedHashMap with accessOrder=true for automatic tracking.

3. LFUCache (Optional / Advanced)

Evicts the Least Frequently Used entry.
Tracks how often each key is accessed. If multiple keys share the same frequency, the oldest among them is evicted first.

### Factory for Cache Creation

A CacheFactory class allows creating cache instances dynamically:

CacheFactory factory = new CacheFactory();

ICache fifo = factory.createCacheInstance(CacheImpl.CacheTypeEnum.FIFO, 3);
ICache lru  = factory.createCacheInstance(CacheImpl.CacheTypeEnum.LRU, 3);
ICache lfu  = factory.createCacheInstance(CacheImpl.CacheTypeEnum.LFU, 3); // optional

### Example Usage
ICache cache = factory.createCacheInstance(CacheImpl.CacheTypeEnum.FIFO, 2);

cache.put("a", 1);
cache.put("b", 2);
cache.put("c", 3); // evicts "a" in FIFO

System.out.println(cache.get("a")); // -1
System.out.println(cache.get("b")); // 2
System.out.println(cache.get("c")); // 3

cache.remove("b");
System.out.println(cache.containsKey("b")); // false
System.out.println(cache.getSize());        // 1

📐 UML Diagram (Conceptual)
            +----------------+
            |     ICache     | <<interface>>
            +----------------+
            | +put()         |
            | +get()         |
            | +remove()      |
            | +clear()       |
            | +getSize()     |
            | +containsKey() |
            +----------------+
                  ^
                  |
     -------------------------------
     |             |               |
+-----------+  +-----------+  +-----------+
| FIFOCache |  | LRUCache  |  | LFUCache  |
+-----------+  +-----------+  +-----------+

### Testing Instructions

1.Compile all files:

javac -d out src/**/*.java


2.Run the demo App.java:

java -cp out App


3.Verify expected behavior:

* FIFO removes oldest key first

* LRU removes least recently used key

* LFU removes least frequently used key

* Optional methods (clear, getSize, containsKey) behave correctly



✅ Notes for the Reviewer

All cache classes implement the ICache interface

Demonstrates knowledge of:

Java Collections (HashMap, LinkedHashMap, LinkedHashSet, Queue)

Eviction policies (FIFO, LRU, LFU)

Factory pattern (CacheFactory)

Code is modular and extensible for future cache strategies