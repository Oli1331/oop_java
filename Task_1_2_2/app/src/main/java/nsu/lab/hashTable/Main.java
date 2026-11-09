package nsu.lab.hashTable;

import nsu.lab.hashTable.impl.HashTable;

/**
 * Main application class.
 */
public class Main {

    /**
     * Entry point of the application.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        HashTable<String, String> hashTable = new HashTable<>();
        hashTable.put("Java", "anoonfieaorigjgnvoiroincogijjouo");
        System.out.println(hashTable);
        hashTable.put("Python", "sgonjeroncv");
        System.out.println(hashTable);
    }
}
