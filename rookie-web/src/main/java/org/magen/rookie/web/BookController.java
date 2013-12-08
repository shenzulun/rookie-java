package org.magen.rookie.web;

import java.util.List;

import org.magen.rookie.entity.Book;
import org.magen.rookie.service.ICommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	@Qualifier("CommonService")
	ICommonService commonService;

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model){
		List<Book> books = commonService.listAll(Book.class);
		model.addAttribute("books",books);
		return "book/listBook";
	}
}
