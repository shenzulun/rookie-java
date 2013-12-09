package org.magen.rookie.web;

import java.util.List;

import org.hibernate.criterion.Order;
import org.magen.rookie.entity.Book;
import org.magen.rookie.repository.util.OrderTool;
import org.magen.rookie.service.ICommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	@Qualifier("CommonService")
	ICommonService commonService;

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model){
		OrderTool order = new OrderTool();
		order.add(Order.asc("name"));
		List<Book> books = commonService.listAll(Book.class,order);
		model.addAttribute("books",books);
		return "book/listBook";
	}
	
	@RequestMapping(value="/{id}/delete",method = RequestMethod.GET)
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
		//commonService.delete(Book.class, id);
		Book book = commonService.get(Book.class, id);
		commonService.deleteObject(book);
		redirectAttributes.addFlashAttribute("message","取书["+book.getName()+"]成功!");
		return "redirect:/book";
	}
	
	@RequestMapping(value="/add",method = RequestMethod.GET)
	public String toAdd(){
		
		return "book/addBook";
	}
	
	@RequestMapping(value="/add",method = RequestMethod.POST)
	public String add(Book book, RedirectAttributes redirectAttributes){
		commonService.save(book);
		redirectAttributes.addFlashAttribute("message","存书["+book.getName()+"]成功!");
		return "redirect:/book";
	}
	
	@RequestMapping(value="/{id}/update",method = RequestMethod.GET)
	public String toUpdate(@PathVariable("id") Long id,Model model){
		Book book = commonService.get(Book.class, id);
		model.addAttribute("book",book);
		return "book/updateBook";
	}
	
	@RequestMapping(value="/update",method = RequestMethod.POST)
	public String update(Book book, RedirectAttributes redirectAttributes){
		commonService.update(book);
		redirectAttributes.addFlashAttribute("message","更新["+book.getName()+"]成功!");
		return "redirect:/book";
	}
}
