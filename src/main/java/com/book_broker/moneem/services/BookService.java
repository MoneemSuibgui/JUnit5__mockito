package com.book_broker.moneem.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book_broker.moneem.models.Book;
import com.book_broker.moneem.repositories.BookRepository;

@Service
public class BookService {

	// inject BookRepository
	@Autowired
	private BookRepository bookRepo;
	
	//create book
	public Book createBook(Book book) {
		return bookRepo.save(book);
	}
	
	//update book
		public Book updateBook(Book book) {
			return bookRepo.save(book);
		}
	
	// get all books
	public List<Book> allBooks(){
		return bookRepo.findAll();
	}
	
	// get One book
	public Book findBookById(Long id) {
		Optional<Book> book=bookRepo.findById(id);
		if(book.isPresent()) {
			return book.get();
		}
		return null;
	}
	
	// delete one book by id
	public void deleteBookById(Long id) {
		bookRepo.deleteById(id);
	}
	
	// find all Books not in BorrowedBooks
	//public List<Book> allBooksNotBorrowing(){
	//	return bookRepo.findAllNotInBorrowedBooks();
	//}
}
