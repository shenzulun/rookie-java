package org.magen.cache;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

public class BookCache {
	private static BookCache bookCache;
	private LRUCache<String, BookRef> cache;
	private ReferenceQueue<Book> queue;
	
	private BookCache(){
		cache = new LRUCache<String, BookRef>();
		queue = new ReferenceQueue<Book>();
	}
	
	public static BookCache getInstance(){
		synchronized (BookCache.class) {
			if(bookCache == null){
				bookCache = new BookCache();
			}
			return bookCache;
		}	
	}
	
	private void addCache(Book book){
		cleanCache();
		BookRef ref = new BookRef(book, queue);
		cache.put(book.getISBN(), ref);	
	}
	
	public Book getBook(String ISBN){
		Book book = null;
		if(cache.containKey(ISBN)){
			book = cache.get(ISBN).get();
			book.setSource(1);
		}
		if(book == null){
			book = new Book(ISBN);
			addCache(book);
		}
		return book;
	}
	
	private void cleanCache(){
		BookRef ref = null;
		while((ref=(BookRef)queue.poll()) != null){
			cache.remove(ref.ISDB);
		}
	}
	
	public void clear(){
		cleanCache();
		cache.clear();
		System.gc();
		System.runFinalization();
	}
	
	private class BookRef extends SoftReference<Book>{
		private String ISDB;
		
		public BookRef(Book book,ReferenceQueue<Book> queue) {
			super(book,queue);
			this.ISDB = book.getISBN();
		}
		
	}
}
