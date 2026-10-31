package nsu.lab.hashTable.impl;

public class Node<K, V> {

    final K key;
    V value;
    Node<K, V> next;
    int hash;

    public Node(K key, V value, int hash,Node<K,V> next) {
        this.key = key;
        this.value = value;
        this.next = next;
        this.hash = hash;
    }

    @Override
    public String toString() {
        return "("+key+","+value+")";
    }
}
