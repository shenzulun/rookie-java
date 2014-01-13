package org.magen.test.cache;

import org.junit.Test;
import org.magen.cache.impl.BookModel;
import org.magen.cache.impl.CacheManager;

public class CacheTest1 {
	
	@Test
	public void testLRUCache(){
		System.out.println("test LRUCache");
		CacheManager cacheManager = CacheManager.getInstance(new BookModel());
		String isbn1 = "123-456-789";
		String isbn2 = "123-444-333";
		
		BookModel bookModel = (BookModel) cacheManager.get(isbn1);
		System.out.println(bookModel.getSource());
		System.out.println(bookModel.getName());
		
		BookModel bookMode2 = (BookModel) cacheManager.get(isbn2);
		System.out.println(bookMode2.getSource());
		
		BookModel bookMode3 = (BookModel) cacheManager.get(isbn1);
		System.out.println(bookMode3.getSource());
		
		BookModel bookMode4 = (BookModel) cacheManager.get(isbn2);
		System.out.println(bookMode4.getSource());
		
		System.out.println(bookModel == bookMode2);
		System.out.println(bookModel == bookMode3);
		System.out.println(bookMode4 == bookMode2);
		
		
	}

}
