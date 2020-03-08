/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ktu.ds.lab3.Slegaityte;

import edu.ktu.ds.lab3.utils.HashType;
import edu.ktu.ds.lab3.utils.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 *
 * @author uslegaityte
 */
public class HashMapOA<K, V> implements Map<K, V> {

    public static final int DEFAULT_INITIAL_CAPACITY = 16;
    public static final float DEFAULT_LOAD_FACTOR = 0.75f;
    public static final HashType DEFAULT_HASHTYPE = HashType.DIVISION;
    protected Node<K, V>[] table;
    protected int size = 0;
    protected float loadFactor;

    protected int rehashesCounter = 0;
    protected int lastUpdatedEntry = 0;
    protected int index = 0;
    protected HashType ht;

    public HashMapOA() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR, DEFAULT_HASHTYPE);
    }

    public HashMapOA(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR, DEFAULT_HASHTYPE);
    }

    public HashMapOA(float loadFactor) {
        this(DEFAULT_INITIAL_CAPACITY, loadFactor, DEFAULT_HASHTYPE);
    }

    public HashMapOA(int initialCapacity, float loadFactor, HashType ht) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
        }
        if ((loadFactor <= 0.0) || (loadFactor > 1.0)) {
            throw new IllegalArgumentException("Illegal load factor: " + loadFactor);
        }

        table = new Node[initialCapacity];
        this.loadFactor = loadFactor;
        this.ht = ht;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        size = 0;
        index = 0;
        rehashesCounter = 0;
        lastUpdatedEntry = 0;
        Arrays.fill(table, null);

    }

    @Override
    public String[][] toArray() {
       String[][] result = new String[table.length][1];
       int count = 0;
        for (Node<K, V> node : table) {
            if(node != null)
                result[count][0] = node.toString();
            count++;
        }
        return result;
    }

    @Override
    public V put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key or value is null in put(Key key, Value value)");
        }
        
        index = Hash1(key, ht);
        int stepSize = Hash2(key);
        
        while(table[index] != null && table[index].value != null && !table[index].key.equals(key)){
            index += stepSize;
            index %= table.length;
        }
        
        if(table[index] == null || !table[index].key.equals(key))
            size++;
        
        table[index] = new Node(key, value);
        
        
        if (size > table.length * loadFactor) {
            rehash(table);
        } else {
            lastUpdatedEntry = index;
        }

        return value;
    }

    @Override
    public V get(K key) {
        if (key == null) 
            throw new IllegalArgumentException("Key is null in get(Key key)");
        
        index = Hash1(key, ht);
        int stepSize = Hash2(key);
        
        while(table[index] != null) {
            
            if(table[index].key.equals(key) && table[index].value != null)
                return table[index].value;
            
            index += stepSize;
            index %= table.length;
        }
        
       return null;    
    }

    @Override
    public V remove(K key) {
        if (key == null) 
            throw new IllegalArgumentException("Key is null in remove(Key key)");
        
        index = Hash1(key, ht);
        int stepSize = Hash2(key);
        
        while(table[index] != null){      
            
            if(table[index].key.equals(key) && table[index].value != null){
                V temp = table[index].value;
                table[index].value = null;
                lastUpdatedEntry = index;
                size--;
                return temp;
            }
                    
            index += stepSize;
            index %= table.length;
        }
         
       return null;
    }

    @Override
    public boolean contains(K key) {
        return get(key) != null;
    }

    private int Hash1(K key, HashType hashType) {
        int h = key.hashCode();
        switch (hashType) {
            case DIVISION:
                return Math.abs(h) % table.length;
            case MULTIPLICATION:
                double k = (Math.sqrt(5) - 1) / 2;
                return (int) (((k * Math.abs(h)) % 1) * table.length);
            case JCF7:
                h ^= (h >>> 20) ^ (h >>> 12);
                h = h ^ (h >>> 7) ^ (h >>> 4);
                return h & (table.length - 1);
            case JCF8:
                h = h ^ (h >>> 16);
                return h & (table.length - 1);
            default:
                return Math.abs(h) % table.length;
        }
    }

    private int Hash2(K key) {
       return 3 - Math.abs(key.hashCode() % 3);
    }

    private void rehash(Node<K, V>[] table) {
        HashMapOA<K, V> newTable = new HashMapOA<>(table.length * 2, loadFactor, ht);
        
        for (Node<K, V> node : table) {
            if(node != null){
             newTable.put(node.key, node.value);
            }
        }
        
        this.table = newTable.table;
        lastUpdatedEntry = newTable.lastUpdatedEntry;
        rehashesCounter++;
        
    }
    public String[][] getModelList(String delimiter) {
        String[][] result = new String[table.length][];
        int count = 0;
        for (Node<K, V> n : table) {
            List<String> list = new ArrayList<>();
            list.add("[ " + count + " ]");
            if (n != null) {
                list.add("-->");
                list.add(split(n.toString(), delimiter));
            }
            result[count] = list.toArray(new String[0]);
            count++;
        }
        return result;
    }
    
       private String split(String s, String delimiter) {
        int k = s.indexOf(delimiter);
        if (k <= 0) {
            return s;
        }
        return s.substring(0, k);
    }

    public int getRehashesCounter() {
        return rehashesCounter;
    }

    public int getLastUpdatedChain() {
        return lastUpdatedEntry;
    }

    public int getMaxChainSize() {
        if(size >= 1){
            return 1;
        }
        return 0;
    }

    public int getTableCapacity() {
        return table.length;
    }

    public int getChainsCounter() {
        return size;
    }

    public int numberOfEmpties() {
        return table.length - size;
    }

    public class Node<K, V> {

        public V value;
        public K key;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
        
    }

}
