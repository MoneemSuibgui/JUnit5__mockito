package com.book_broker.moneem.controllers;




import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.book_broker.moneem.models.Book;
import com.book_broker.moneem.models.User;
import com.book_broker.moneem.services.BookService;
import com.book_broker.moneem.services.UserService;

import jakarta.servlet.http.HttpSession;


@Controller
public class BorrowerController {

	// inject UserService
	private UserService userServ;
	private BookService bookServ;

	public BorrowerController(UserService userServ,BookService bookServ) {
		this.userServ = userServ;
		this.bookServ=bookServ;
	}
	
	// Display route bookMarket page
	@GetMapping("/bookmarket")
	public String bookMarket(HttpSession session,Model modelView) {
		Long id=(Long)session.getAttribute("userId");
		User user=userServ.findUserById(id);
		if(session.getAttribute("userId")==null) {
			return "redirect:/";
		}
		// passing all books & the logged in user into jsp file
		modelView.addAttribute("books", bookServ.allBooks());
		modelView.addAttribute("user", user);
		return"bookMarket.jsp";
	}

	// Borrow book route
	@GetMapping("/borrow/books/{bookId}")
	public String borrowBook(@PathVariable("bookId") Long bookId,HttpSession session) {
		if(session.getAttribute("userId")==null) {
			return "redirect:/";
		}
		Book book=bookServ.findBookById(bookId);
		User loggedUser=userServ.findUserById((Long)session.getAttribute("userId"));
		// get the book and set the borrowed to the logged in user
		book.setBorrwed(loggedUser);
		// update the book
		bookServ.updateBook(book);
		return "redirect:/bookmarket";
	}
	
	// return book route //other way passing the logged user with @PathVariable annotation from jsp file to controller
	@GetMapping("/return/books/{bookId}/{userId}")
	public String returnBook(@PathVariable("bookId") Long bookId,@PathVariable("userId") Long userId,HttpSession session) {
		if(session.getAttribute("userId")==null) {
			return "redirect:/";
		}
		Book borrowedBook=bookServ.findBookById(bookId);
		// set the borrowed user to null
		borrowedBook.setBorrwed(null);
		// save the book(update)
		bookServ.updateBook(borrowedBook);
		return "redirect:/bookmarket";
	}
	
}
