package org.magen.cache.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;
import org.magen.cache.Cache;



public class LRUCache1<K,V> implements Cache<K, V>{
	public static final int DEFAULT_INITIAL_CAPACITY = 16;
	private int cacheSize;
	private Hashtable<K, Node> nodes;
	private int currentSize;
	private Node first;    //链表头
	private Node last;		//链表尾
	
	public LRUCache1(){
		this(DEFAULT_INITIAL_CAPACITY);
	}
	
	public LRUCache1(int cacheSize){
		this.cacheSize = cacheSize;
		this.currentSize = 0;
		first = null;
		last = null;
		nodes = new Hashtable<K,Node>(cacheSize);
	}

	public void put(K key, V value) {
		Node node = nodes.get(key);
		if(node == null){
			if(currentSize >= cacheSize){
				//remove the last
				if(last != null){
					remove(last.key);
				}
				removeLast();
			}else{
				currentSize++;
			}
			node = new Node();
		}
		node.value = value;
		node.key = key;
		//move the new node to head
		moveToHead(node);
		nodes.put(key, node);
	}

	public V get(Object key) {
		Node node = nodes.get(key);
		if(node != null){
			moveToHead(node);
			return node.value;
		}else{
			return null;
		}
	}

	public boolean isEmpty() {
		return nodes.isEmpty();
	}

	public int size() {
		return nodes.size();
	}

	public void remove(K key) {
		Node node = nodes.get(key);
		if(node != null){
			if(node.prev != null){
				node.prev.next = node.next;
			}
			if(node.next != null){
				node.next.prev = node.prev;
			}
			if(last == node){
				last = node.prev;
			}
			if(first == node){
				first = node.next;
			}
			nodes.remove(key);
		}
	}

	public void clear() {
		nodes.clear();
		first = null;
		last = null;
		currentSize = 0;
	}

	public Collection<Entry<K, V>> getAll() {
		//TODO
//		ArrayList<Entry<K, V>> res = new ArrayList<Map.Entry<K,V>>();
//		Enumeration<Node> e = nodes.elements();
//		while(e.hasMoreElements()){
//			Node node = e.nextElement();
//			Entry<K, V> entry = new 
//		}
//		return new ArrayList<Map.Entry<K,V>>(nodes.elements());
		return new ArrayList<Map.Entry<K,V>>();
	}

	public boolean containsKey(K key) {
		return nodes.containsKey(key);
	}
	
	private void moveToHead(Node node){
		if(first == node){
			return;
		}
		if(node.prev != null){
			node.prev.next = node.next;
		}
		if(node.next != null){
			node.next.prev = node.prev;
		}
		if(last == node){
			last = node.prev;
		}
		if(first != null){
			node.next = first;
			first.prev = node;
		}
		first = node;
		node.prev = null;
		if(last == null){
			last = first;
		}
	}
	
	private void removeLast(){
		if(last != null){
			if(last.prev != null){
				last.prev.next = null;
			}else{
				first = null;
			}
			last = last.prev;
		}
	}
	
	class Node{
		Node prev;
		Node next;
		V value;
		K key;
		
		public V getValue(){
			return value;
		}
		public K getKey(){
			return key;
		}
	}

}
