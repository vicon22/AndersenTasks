package eveiled;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * HandMade collection which implements {@link IMap} and use for it {@code hashcode}
 *
 *
 * @author Evhenii Isupov
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 *
 * @version 1.0
 */
public class HashMapImpl<K, V> implements IMap<K, V>{

    /**
     * Variable {@code size} contains how many keys with values contains at this {@code HashMap}
     */
    private int size;
    /**
     * {@code Capacity} describes how many {@link Node} contains at this {@code HashMap}
     */
    private int capacity;

    /**
     * The load factor used for understand when we have to increase Capacity.
     */
    private float loadFactor;

    /**
     * if {@code Threshold} <=  {@code Size} we need to resize buckets
     */
    private int threshold;

    /**
     * Buckets contains Nodes
     */
    private Node[] buckets;

    /**
     * Constructs an empty {@code HashMap} with the default initial capacity
     * (16) and the default load factor (0.75).
     */
    public HashMapImpl() {
        size = 0;
        capacity = 16;
        loadFactor = 0.75f;
        threshold = (int) (capacity * loadFactor);
        buckets = new Node[capacity];

    }


    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the old
     * value is replaced.
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with {@code key}, or
     *         {@code null} if there was no mapping for {@code key}.
     *         (A {@code null} return can also indicate that the map
     *         previously associated {@code null} with {@code key}.)
     */
    @Override
    public V put(K key, V value) {

        //добавить расширение мапы
        if (size >= threshold) {
            resize();
        }

        return addToHashTable(key, value);

    }

    /**
     * Method need for adding key-value pairs to hashTable
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with {@code key}, or
     *         {@code null} if there was no mapping for {@code key}.
     *         (A {@code null} return can also indicate that the map
     *         previously associated {@code null} with {@code key}.)
     */

    private V addToHashTable(K key, V value) {

        int index = (capacity - 1) & hash(key);

//        System.out.println("index: " + index + " KEY: " + key);

        if (buckets[index] == null) {
            buckets[index] = new Node(hash(key), key, value, null);
            size++;
            return null;
        } else {
            Node tempNode = buckets[index];
            while (tempNode != null) {
                if (tempNode.hash == hash(key) && tempNode.key.equals(key)) {
                    V tmp = (V) tempNode.getValue();

                    tempNode.setValue(value);
                    return tmp;
                }

                if (tempNode.next == null) {
                    tempNode.next = new Node(hash(key), key, value, null);
                    size++;
                    return null;
                }
                tempNode = tempNode.next;
            }
        }

        return null;
    }


    /**
     * Increase capacity if threshold <= size
     */
    private void resize() {


        capacity *= 2;
        Node oldBuckets[] = buckets;
        buckets = new Node[capacity];
        threshold = (int) (capacity * loadFactor);
        size = 0;

        for (int i = 0; i < capacity / 2; i++) {
            if (oldBuckets[i] != null) {
                Node tempNode = oldBuckets[i];
                while (tempNode != null) {
                    addToHashTable((K) tempNode.key, (V) tempNode.value);

                    tempNode = tempNode.next;
                }
            }
        }
    }

    /**
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     * @param key the key whose associated value is to be returned
     * @return V
     */
    @Override
    public V get(Object key) {

        int index = (capacity - 1) & hash(key);

            if (buckets[index] != null) {
                Node tempNode = buckets[index];
                while (tempNode != null) {
                    if (tempNode.key.equals(key)) {
                        return (V) tempNode.value;
                    }
                    tempNode = tempNode.next;
                }
            }


        return null;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     *
     * @param  key key whose mapping is to be removed from the map
     * @return the previous value associated with {@code key}, or
     *         {@code null} if there was no mapping for {@code key}.
     *         (A {@code null} return can also indicate that the map
     *         previously associated {@code null} with {@code key}.)
     */

    @Override
    public V remove(Object key) {

        V tmp = null;

        int index = (capacity - 1) & hash(key);

        if (buckets[index] != null) {
            Node tempNode = buckets[index];
            Node beforeTempNode = buckets[index];
            while (tempNode != null) {
                if (tempNode.key.equals(key)) {
                    tmp = (V) tempNode.value;

                    if (tempNode == buckets[index]){
                        buckets[index] = null;
                    } else {
                        beforeTempNode.next = tempNode.next;
                        tempNode = null;
                    }
                    size--;
                }
                beforeTempNode = tempNode;
                tempNode = tempNode.next;
            }
        }

        return tmp;
    }

    /**
     * Returns the number of key-value mappings in this map.
     *
     * @return the number of key-value mappings in this map
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns {@code true} if this map contains a mapping for the
     * specified key.
     *
     * @param   key   The key whose presence in this map is to be tested
     * @return {@code true} if this map contains a mapping for the specified
     * key.
     */

    @Override
    public boolean containsKey(Object key) {

        int index = (capacity - 1) & hash(key);

            if (buckets[index] != null) {
                Node tempNode = buckets[index];
                while (tempNode != null) {
                    if (tempNode.key.equals(key)) {
                        return true;
                    }
                    tempNode = tempNode.next;
                }
            }

        return false;
    }

    /**
     * Computes key.hashCode() and spreads (XORs) higher bits of hash
     * to lower.  Because the table uses power-of-two masking, sets of
     * hashes that vary only in bits above the current mask will
     * always collide. (Among known examples are sets of Float keys
     * holding consecutive whole numbers in small tables.)  So we
     * apply a transform that spreads the impact of higher bits
     * downward. There is a tradeoff between speed, utility, and
     * quality of bit-spreading. Because many common sets of hashes
     * are already reasonably distributed (so don't benefit from
     * spreading), and because we use trees to handle large sets of
     * collisions in bins, we just XOR some shifted bits in the
     * cheapest possible way to reduce systematic lossage, as well as
     * to incorporate impact of the highest bits that would otherwise
     * never be used in index calculations because of table bounds.
     */
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    /**
     * @return a {@link Set} view of the keys contained in this map.
     */
    @Override
    public Set<K> keySet() {

        List<K> kList = new LinkedList<>();

        for (int i = 0; i < capacity; i++) {
            if (buckets[i] != null) {
                Node tempNode = buckets[i];
                while (tempNode != null) {
                    kList.add((K) tempNode.key);
                    tempNode = tempNode.next;
                }
            }
        }

//        System.out.println("kList: " + kList);
        return (Set<K>) Set.of(kList.toArray());
    }

    @Override
    public String toString() {

        int temp = size;

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < capacity; i++) {
            if (buckets[i] != null) {

                Node tempNode = buckets[i];
                while (tempNode != null) {
//                    System.out.println("tempNode: " + tempNode.key);
                    if (temp > 1) {
                        stringBuilder.append(tempNode)
                                .append(", ");
                    } else {
                        stringBuilder.append(tempNode);
                    }
                    tempNode = tempNode.next;
                    temp--;
                }
            }
        }

       return "{" + stringBuilder.toString() + "}";
    }

    /**
     * Node, used for contains key-value pairs
     */
    class Node<K, V> {

        final int hash;
        final K key;
        private V value;
        Node next;

        public Node(int hash, K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.hash = hash;
            this.next = next;
        }


        public V getValue() {
            return value;
        }

        public V setValue(V value) {
            V tmpValue = this.value;
            this.value = value;
            return tmpValue;
        }


        @Override
        public String toString() {
            return key + "=" + value;
        }
    }
}
