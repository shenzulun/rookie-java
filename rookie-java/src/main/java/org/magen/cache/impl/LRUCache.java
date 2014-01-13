package org.magen.cache.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.magen.cache.Cache;

public class LRUCache<K,V> implements Cache<K, V>{
	/**
	 * 默认容量
	 */
	public static final int DEFAULT_INITIAL_CAPACITY = 16;
	/**
	 * 装载因子
	 * currentSize >= maxSize * factor 时，map自动扩容
	 */
	public static final float DEFAULT_LOAD_FACTOR = 0.75f;
	private LinkedHashMap<K, V> map;
	private int cacheSize;
	final float factor;
	
	public LRUCache(){
		this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
	}
	
	public LRUCache(int size,float factor){
		this.cacheSize = size;
		this.factor = factor;
		int hashSize = (int)Math.ceil(size / factor) + 1;
		map = new LinkedHashMap<K, V>(hashSize, factor, true){
			private static final long serialVersionUID = 1L;
			
			@Override
			protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
		        return size() > LRUCache.this.cacheSize;
		    }
		};
	}

	public synchronized void put(K key, V value) {
		map.put(key, value);
	}

	public synchronized V get(Object key) {
		return map.get(key);
	}

	public synchronized boolean isEmpty() {
		return map.isEmpty();
	}

	public synchronized int size() {
		return map.size();
	}

	public synchronized void remove(K key) {
		map.remove(key);
	}

	public synchronized void clear() {
		map.clear();
	}

	public synchronized Collection<Entry<K, V>> getAll() {
		return new ArrayList<Map.Entry<K,V>>(map.entrySet());
	}

	public synchronized boolean containsKey(K key) {
		return map.containsKey(key);
	}


}
