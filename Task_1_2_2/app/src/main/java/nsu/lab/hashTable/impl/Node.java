package nsu.lab.hashTable.impl;

/**
 * Node class representing an element in a hash table.
 *
 * @param <K> type of key.
 * @param <V> type of value.
 */
public class Node<K, V> {

    final K key;
    V value;
    Node<K, V> next;
    int hash;

    /**
     * Constructs a new Node.
     *
     * @param key   node key.
     * @param value node value.
     * @param hash  hash code of the key.
     * @param next  reference to the next node in bucket.
     */
    public Node(K key, V value, int hash, Node<K, V> next) {
        this.key = key;
        this.value = value;
        this.next = next;
        this.hash = hash;
    }

    @Override
    public String toString() {
        return "(" + key + "," + value + ")";
    }
}
