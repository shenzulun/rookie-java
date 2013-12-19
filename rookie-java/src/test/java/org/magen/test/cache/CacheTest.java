package org.magen.test.cache;

import java.util.Enumeration;
import java.util.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.magen.cache.Book;
import org.magen.cache.BookCache;
import org.magen.cache.LRUCache;
import org.magen.cache.LRUCache1;
import org.magen.cache.LRUCache1.Node;

public class CacheTest {
	private static final int LOOP_DEPTH = 1000;
	private static int cnt = 1;
	private static long start;
	//private static final int LOOP_THRESHOLD = 16;
	
	@Before
	public void testBefore(){
		start = System.currentTimeMillis();
		System.out.println("test["+ cnt +"]...");
	}
	
	@After
	public void testAfter(){
		long end = System.currentTimeMillis();
		System.out.println("end["+ cnt++ +"]...cost time-->"+(end - start));
	}
	
	@Test
	public void testLRUCache(){
		System.out.println("test LRUCache");
		LRUCache<Integer, Object> cache = new LRUCache<Integer, Object>();
		for (int i = 0; i < LOOP_DEPTH; i++) {
			cache.put(Integer.valueOf(i), i);
		}
		for(Map.Entry<Integer, Object> entry : cache.getAll()){
			System.out.println(entry.getKey() + "--->" + entry.getValue());
		}
	}
	
	@Test
	public void testLRUCache1(){
		System.out.println("test LRUCache1");
		LRUCache1 cache = new LRUCache1();
		for (int i = 0; i < LOOP_DEPTH; i++) {
			cache.put(Integer.valueOf(i), i);
		}
		Enumeration<Node> enumeration = cache.getAll();
		while(enumeration.hasMoreElements()){
			Node node = enumeration.nextElement();
			System.out.println(node.getKey()+"--->"+node.getValue());
		}
		System.out.println("head:"+cache.getHead().getValue());
		System.out.println("last:"+cache.getLast().getValue());
	}
	
	@Test
	public void testBookCache(){
		System.out.println("test BookCache");
		BookCache cache = BookCache.getInstance();
		String isbn1 = "123-456-789";
		String isbn2 = "123-444-333";
		Book book1 = cache.getBook(isbn1);
		System.out.println(book1.getSource());
		Book book2 = cache.getBook(isbn2);
		System.out.println(book2.getSource());
		
		Book book3 = cache.getBook(isbn1);
		System.out.println(book3.getSource());
		Book book4 = cache.getBook(isbn2);
		System.out.println(book4.getSource());
		
		System.out.println(book1 == book3);
		System.out.println(book2 == book4);
		
	}

}
