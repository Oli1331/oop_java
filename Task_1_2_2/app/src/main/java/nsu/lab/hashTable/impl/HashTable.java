package nsu.lab.hashTable.impl;

import java.util.Iterator;
import java.util.Objects;

/**
 * Custom hash table implementation.
 *
 * @param <K> type of key.
 * @param <V> type of value.
 */
public class HashTable<K, V> implements Iterable<Node<K, V>> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    private Node<K, V>[] table;
    private int size;

    /**
     * Constructs an empty hash table with default initial capacity.
     */
    @SuppressWarnings("unchecked")
    public HashTable() {
        table = (Node<K, V>[]) new Node[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Constructs an empty hash table with specified initial capacity.
     *
     * @param size initial capacity.
     */
    @SuppressWarnings("unchecked")
    public HashTable(int size) {
        table = (Node<K, V>[]) new Node[size];
        this.size = size;
    }

    /**
     * Adds key-value pair to the table or updates existing key value.
     *
     * @param key   element key.
     * @param value element value.
     * @return old value associated with key, or null if key was not present.
     */
    public V add(K key, V value) {
        if (size > LOAD_FACTOR * table.length) {
            resize();
        }
        int hashKey = key == null ? 0 : key.hashCode();
        int index = (hashKey & 0x7FFFFFFF) % table.length;
        Node<K, V> current = table[index];
        while (current != null) {
            if (current.hash == hashKey && Objects.equals(current.key, key)) {
                V oldValue = current.value;
                current.value = value;
                return oldValue;
            }
            current = current.next;
        }

        table[index] = new Node<>(key, value, hashKey, table[index]);
        size++;
        return null;
    }

    /**
     * Removes key-value pair from the table.
     *
     * @param key element key to remove.
     * @return value associated with key, or null if key was not present.
     */
    public V cut(K key) {
        int hashKey = key == null ? 0 : key.hashCode();
        int index = (hashKey & 0x7FFFFFFF) % table.length;
        Node<K, V> current = table[index];
        if (current == null) {
            return null;
        }
        if (Objects.equals(current.key, key)) {
            table[index] = current.next;
            return current.value;
        }
        Node<K, V> beforeCurrent = current;
        current = current.next;
        while (current != null) {
            if (current.hash == hashKey && Objects.equals(current.key, key)) {
                beforeCurrent.next = current.next;
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    /**
     * Gets value associated with specified key.
     *
     * @param key element key.
     * @return associated value, or null if not found.
     */
    public V get(K key) {
        int hashKey = key == null ? 0 : key.hashCode();
        int index = (hashKey & 0x7FFFFFFF) % table.length;
        Node<K, V> current = table[index];
        while (current != null) {
            if (current.hash == hashKey && Objects.equals(current.key, key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    /**
     * Updates value for existing key.
     *
     * @param key   element key.
     * @param value new value.
     * @return updated value, or null if key does not exist.
     */
    public V set(K key, V value) {
        int hashKey = key == null ? 0 : key.hashCode();
        int index = (hashKey & 0x7FFFFFFF) % table.length;
        Node<K, V> current = table[index];
        while (current != null) {
            if (current.hash == hashKey && Objects.equals(current.key, key)) {
                current.value = value;
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    /**
     * Checks if key exists in hash table.
     *
     * @param key element key.
     * @return true if key exists, false otherwise.
     */
    public boolean has(K key) {
        int hashKey = key == null ? 0 : key.hashCode();
        int index = (hashKey & 0x7FFFFFFF) % table.length;
        Node<K, V> current = table[index];
        while (current != null) {
            if (current.hash == hashKey && Objects.equals(current.key, key)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("{");
        for (var i : this) {
            res.append(" ").append(i).append(",");
        }
        res.replace(res.length() - 1, res.length(), " }");
        return res.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        HashTable transformedObj = (HashTable) obj;
        if (size != transformedObj.size) {
            return false;
        }
        for (var i : this) {
            if (!transformedObj.has(i.key)) {
                return false;
            }
            if (!Objects.equals(transformedObj.get(i.key), i.value)) {
                return false;
            }
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        Node<K, V>[] newTable = (Node<K, V>[]) new Node[table.length * 2 + 1];
        for (var i : this) {
            int index = (i.hash & 0x7FFFFFFF) % newTable.length;
            i.next = newTable[index];
            newTable[index] = i;
        }
        table = newTable;
    }

    @Override
    public Iterator<Node<K, V>> iterator() {
        return new HashTableIterator();
    }

    private class HashTableIterator implements Iterator<Node<K, V>> {
        int currentIndex = 0;
        private Node<K, V> currentNode = table[currentIndex];

        HashTableIterator() {
            if (table[0] == null) {
                search();
            }
        }

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public Node<K, V> next() {
            Node<K, V> tmp = currentNode;
            search();
            return tmp;
        }

        private void search() {
            if (currentNode != null && currentNode.next != null) {
                currentNode = currentNode.next;
                return;
            }
            currentIndex++;
            while (currentIndex < table.length && table[currentIndex] == null) {
                currentIndex++;
            }
            if (currentIndex >= table.length) {
                currentNode = null;
                return;
            }
            currentNode = table[currentIndex];
        }
    }
}
