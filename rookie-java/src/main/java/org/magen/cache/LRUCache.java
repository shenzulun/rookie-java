package org.magen.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;


public class LRUCache<K,V> {
	static final int DEFAULT_INITIAL_CAPACITY = 16;
	private static final float DEFAULT_LOAD_FACTOR = 0.75f;
	private LinkedHashMap<K, V> map;
	private int cacheSize;
	
	public LRUCache(){
		this(DEFAULT_INITIAL_CAPACITY);
	}
	
	public LRUCache(int cacheSize){
		this.cacheSize = cacheSize;
		int hashTableCapacity = (int)Math.ceil(cacheSize / DEFAULT_LOAD_FACTOR) + 1;
		map = new LinkedHashMap<K,V>(hashTableCapacity, DEFAULT_LOAD_FACTOR, true){
			private static final long serialVersionUID = 1L;
			@Override
			protected boolean removeEldestEntry(Map.Entry<K, V> eldest){
				return size() > LRUCache.this.cacheSize;
			}
		};
	}
	
	public synchronized V get(K key){
		return map.get(key);
	}
	
	public synchronized void put(K key,V value){
		map.put(key, value);
	}
	
	public synchronized void clear(){
		map.clear();
	}
	
	public synchronized int usedEntries(){
		return map.size();
	}
	
	public synchronized boolean containKey(K key){
		return map.get(key)!=null;
	}
	
	public synchronized void remove(K key){
		map.remove(key);
	}
	
	public synchronized Collection<Map.Entry<K,V>> getAll(){
		return new ArrayList<Map.Entry<K,V>>(map.entrySet());
	}
}
