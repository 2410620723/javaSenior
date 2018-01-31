package com.java.hash;

public class MyHashMap<K, V> implements MyMap<K, V> {
    private static Integer defaultLength = 16;
    private static double defaultLoad = 0.75;
    private Entry<K, V>[] table = null;
    private int size = 0;// 记录数组元素个数，便于扩容

    MyHashMap(int defaultLength, double defaultLoad) {
        this.defaultLength = defaultLength;
        this.defaultLoad = defaultLoad;
        table = new Entry[defaultLength];
    }

    MyHashMap() {
        this(defaultLength, defaultLoad);
    }

    /**
     * 根据取模算法来计算key的值
     *
     * @param key
     * @return
     */
    private int getIndex(K key) {
        int m = this.defaultLength - 1;
        return key.hashCode() % m;
    }

    @Override
    public V put(K key, V value) {
        // 获取key的下标
        int index = this.getIndex(key);
        Entry<K, V> entry = table[index];
        if (entry == null) {
            table[index] = new Entry<>(key, value, null, index);
            size++;
        } else {
            Entry newEntry = new Entry(key, value, entry, index);
            table[index] = newEntry;
        }
        return table[index].getValue();
    }

    @Override
    public V get(K key) {
        int index = this.getIndex(key);
        return table[index] == null ? null : table[index].getValue();
    }

    @Override
    public int size() {
        return size;
    }

    class Entry<K, V> implements MyMap.Entry<K, V> {
        private K key;
        private V value;
        private Entry<K, V> next;
        int index;

        public Entry(K key, V value, Entry<K, V> next, int index) {
            this.key = key;
            this.value = value;
            this.next = next;
            this.index = index;
        }

        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }
    }
}
