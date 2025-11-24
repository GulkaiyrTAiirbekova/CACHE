# CacheHomework — Reviewer's Guide

## Objective
Implement a simple in-memory cache with:
- Keys: `String`
- Values: `Integer`
- Operations:
    - `void put(String key, int value)`
    - `Integer get(String key)` (returns `null` if absent)
    - `void remove(String key)`
    - Optional:
        - `void clear()`
        - `int size()`
        - `boolean containsKey(String key)`

Implementations provided:
- `FIFOCache` — evicts entries in first-in-first-out order
- `LRUCache` — evicts the least recently used entry

Both caches accept a `capacity` parameter at construction. When adding an entry beyond capacity, the cache evicts one existing entry according to its policy.