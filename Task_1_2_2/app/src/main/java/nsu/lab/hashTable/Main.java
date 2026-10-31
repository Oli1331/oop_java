package nsu.lab.hashTable;

import nsu.lab.hashTable.impl.HashTable;

public class Main{
    public  static void main(String[] args){
        HashTable<String,String> hashTable = new HashTable<>();
        hashTable.add("Java","anoonfieaorigjgnvoiroincogijjouo");
        System.out.println(hashTable);
        hashTable.add("Python","sgonjeroncv");
        System.out.println(hashTable);

    }
}