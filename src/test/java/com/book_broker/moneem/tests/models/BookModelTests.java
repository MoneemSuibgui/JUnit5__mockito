package com.book_broker.moneem.tests.models;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.book_broker.moneem.models.Book;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@SpringBootTest
class BookModelTests {
	
	// injcet Validator object to BookModelTests using @Autowired annotation
	@Autowired
	private Validator validator;

	
	@BeforeEach
	void setUp() throws Exception {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator= factory.getValidator();
	}

	
	@Test
	void TestBookModel() {
		// given 
		Book newBook =new Book();
		
		// when
		newBook.setTitle("Clean code");
		newBook.setAuthor("Robert C. Martin");
		newBook.setThoughts("great book for writing clean, maintainable, and efficient code !!!");
		
		
		Set<ConstraintViolation<Book>> violationsBook= validator.validate(newBook);
		for (ConstraintViolation<Book> violation : violationsBook) {
			// display the custom validation errors in the console
			System.out.println(violation.getMessage());
		}
		//then
		assertThat(violationsBook.isEmpty()).isTrue();
	}
	

	@Test
	void assumeBookModelFiledsIsNull() {
		// given 
		Book newBook =new Book();
		Set<ConstraintViolation<Book>> violationsBook= validator.validate(newBook);
		for (ConstraintViolation<Book> violation : violationsBook) {
			// display the custom validation errors in the console
			System.out.println(violation.getMessage());
		}
		//then
		assertFalse(violationsBook.isEmpty());
	}
	
	

}
