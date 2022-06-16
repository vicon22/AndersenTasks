package eveiled;

import java.util.Set;

/**
 * Interface for implementation in handMade collection.
 * <br>
 *
 * An object that maps keys to values.  A map cannot contain duplicate keys;
 * each key can map to at most one value.
 *
 * @author Evgenii Isupov
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 *
 * @version 1.0
 */
public interface IMap<K, V> {


    /**
     *Associates the specified value with the specified key in this map
     * (optional operation).  If the map previously contained a mapping for
     * the key, the old value is replaced by the specified value.
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with {@code key}, or
     *  {@code null}
     */
    V put(K key, V value);

    /**
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     * @param key the key whose associated value is to be returned
     * @return value associated with {@code key}, or
     *  {@code null}
     */
    V get(Object key);

    /**
     * Removes the mapping for a key from this map if it is present
     * @param key key whose mapping is to be removed from the map
     * @return the previous value associated with {@code key}, or
     * {@code null} if there was no mapping for {@code key}.
     */
    V remove(Object key);

    /**
     * Returns the number of key-value mappings in this map.  If the
     * map contains more than {@code Integer.MAX_VALUE} elements, returns
     * {@code Integer.MAX_VALUE}.
     *
     * @return the number of key-value mappings in this map
     */

    int size();

    /**
     * Returns {@code true} if this map contains a mapping for the specified
     * key.  More formally, returns {@code true} if and only if
     * this map contains a mapping for a key {@code k} such that
     * {@code Objects.equals(key, k)}.  (There can be
     * at most one such mapping.)
     *
     * @param key key whose presence in this map is to be tested
     * @return {@code true} if this map contains a mapping for the specified
     *         key
     */
    boolean containsKey(Object key);

    /**
     * Returns a {@link Set} view of the keys contained in this map.
     * @return a set view of the keys contained in this map
     */
    Set<K> keySet();

}
