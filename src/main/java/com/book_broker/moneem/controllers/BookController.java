package com.book_broker.moneem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.book_broker.moneem.models.Book;
import com.book_broker.moneem.models.User;
import com.book_broker.moneem.services.BookService;
import com.book_broker.moneem.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class BookController {
	
	
	// inject the BookService
	@Autowired 
	private BookService bookServ;
	
	
	// inject the UserService
		@Autowired 
		private UserService userServ;
		
		// Display route "/welcome" (render our welcome.jsp page)
		@GetMapping("/books")
		public String Dashboard(Model modelView,HttpSession session) {
			// test if the userId in session or not
			if(session.getAttribute("userId")==null){
				return "redirect:/";
			}
			Long id=(Long)session.getAttribute("userId");
			User loggedUser=userServ.findUserById(id);
			modelView.addAttribute("loggedUser", loggedUser);	
			
			List<Book> allBooks=bookServ.allBooks();
			modelView.addAttribute("books",allBooks );
			return "dashboard.jsp";
		}

	@GetMapping("/books/new")
	public String bookForm(@ModelAttribute("book") Book book,Model modelView,HttpSession session) {
	    if(session.getAttribute("userId") != null) {
			return "newBook.jsp";
	    }
	    return "redirect:/";
		
	}

	// Action route create book 
	@PostMapping("/create/book")
	public String addBook(@Valid @ModelAttribute("book") Book book,BindingResult result,HttpSession session) {
		
		if(result.hasErrors()) {
			// if there's errors -> render newBook jsp form to show user flash messages
			return "newBook.jsp";
		}
		Long userId=(Long)session.getAttribute("userId");
		User creatorBook=userServ.findUserById(userId);
		book.setCreator(creatorBook);
		bookServ.createBook(book);
		return "redirect:/books";
	}
	
	// display route info of one book
	@GetMapping("/books/{id}")
	public String ShowBookInfo(@PathVariable("id") Long id,Model modelView,HttpSession session) {
		 if(session.getAttribute("userId")!=null) {
			 Book book= bookServ.findBookById(id);
			 // send the book and the user id to jsp file for validation(for the edit and delete buttons)
				modelView.addAttribute("book", book);
				modelView.addAttribute("user_id", session.getAttribute("userId"));
				return "showBook.jsp"; 	 
		 }
		 return "redirect:/";
		
	}
	// delete book route & redirect to dashboard
	@GetMapping("/delete/books/{id}")
	public String destroyBook(@PathVariable Long id,HttpSession session) {
		if(session.getAttribute("userId")==null) {
			return "redirect:/";
		}
		bookServ.findBookById(id).setCreator(null);
		bookServ.deleteBookById(id);
		return "redirect:/books";
	}
	
	// edit route to display Book info to the form
	@GetMapping("/edit/books/{id}")
	public String editBook(@PathVariable("id") Long id,@ModelAttribute("updatedBook") Book book,Model modelView,HttpSession session) {
		if(session.getAttribute("userId")!=null) {
			modelView.addAttribute("updatedBook",bookServ.findBookById(id));
			return "editBook.jsp";
		}return "redirect:/";
		
	}
	
	// update the logged in user book
	@RequestMapping(value="/update/book/{id}",method=RequestMethod.PUT)
	public String updateBook(@Valid @ModelAttribute("updatedBook") Book book,BindingResult result,HttpSession session){
		
		if(result.hasErrors()) {
			return "editBook.jsp";
		}
		// get the user and update the book creator
		User user=userServ.findUserById((Long)session.getAttribute("userId"));
		book.setCreator(user);
		bookServ.updateBook(book);
		return "redirect:/books";
	}
	
		
}

