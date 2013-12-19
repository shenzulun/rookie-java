package org.magen.cache;

import java.util.Enumeration;
import java.util.Hashtable;

public class LRUCache1 {
	static final int DEFAULT_INITIAL_CAPACITY = 16;

	private int cacheSize;
	private Hashtable<Object, Node> nodes;
	private int currentSize;
	private Node first;    //链表头
	private Node last;		//链表尾
	
	public LRUCache1(){
		this(DEFAULT_INITIAL_CAPACITY);
	}
	
	public LRUCache1(int cacheSize){
		first = null;
		last = null;
		this.cacheSize = cacheSize;
		this.currentSize = 0;
		nodes = new Hashtable<Object, Node>(cacheSize);
	}
	
	public Node get(Object key){
		Node node = nodes.get(key);
		if(node != null){
			//移动到链表头部
			moveToHead(node);
			return node;
		}else{
			return null;
		}
	}
	
	public void put(Object key,Object value){
		Node node = nodes.get(key);
		if(node == null){
			if(currentSize >= cacheSize){
				//remove the last node
				if(last != null){
					nodes.remove(last.key);
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
	
	public void remove(Object key){
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
		}
		nodes.remove(key);
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
	
	private void moveToHead(Node node){
		if(first == node){
			return ;
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
	
	public void clear(){
		first = null;
		last = null;
		currentSize = 0;
	}
	
	public Enumeration<Node> getAll(){
		return nodes.elements();
	}
	
	public Node getHead(){
		return first;
	}
	
	public Node getLast(){
		return last;
	}
	
	
	public static class Node{
		Node prev;
		Node next;
		Object value;
		Object key;
		
		public Object getValue(){
			return value;
		}
		public Object getKey(){
			return key;
		}
	}
	
}
