package org.magen.cache;

import java.util.Collection;
import java.util.Map;

/**
 * 缓存需要实现这个接口
 * @author magen
 *
 * @param <K>
 * @param <V>
 */
public interface Cache<K,V> {
	
	void put(K key, V value);
	
	V get(Object key);
	
	boolean isEmpty();
	
	int size();
	
	void remove(K key);
	
	void clear();
	
	Collection<Map.Entry<K,V>> getAll();
	
	boolean containsKey(K key);
	
}
