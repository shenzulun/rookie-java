package org.magen.rookie.test;

import java.util.List;
import org.junit.Test;
import org.magen.rookie.entity.Book;
import org.magen.rookie.service.ICommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Repeat;

public class CommonServiceTest extends BaseTest{
	
	@Autowired
	@Qualifier("CommonService")
	private ICommonService commonService;
	
	@Test
	@Repeat(2)
	public void testGet(){
		Book book = commonService.get(Book.class, 1L);
		book.desc();
	}
	
	@Test
	public void testListAll(){
		 List<Book> books = commonService.listAll(Book.class);
		 for(Book book : books){
			 book.desc();
		 }
	}
	
	@Test
	public void testCountAll(){
		int cnt = commonService.countAll(Book.class);
		System.out.println(cnt);
	}
	
}
