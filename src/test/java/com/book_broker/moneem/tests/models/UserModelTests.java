package com.book_broker.moneem.tests.models;



import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.book_broker.moneem.models.User;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@SpringBootTest(classes = {User.class})
class UserModelTest {

	// import and declare Validator object
	private static Validator validator;

	// every test we get refreshed valisator object
	@BeforeAll()
	static void setUp() {
		// In order to validate a bean (in this case, our Book model class) we need a
		// Validator object. This is built by using a ValidatoryFactory
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	// test User model
	@Test
	void testUserModel() {
		User user = new User();
		user.setName("Moneem");
		user.setEmail("moneem.s@gmail.com");
		user.setPassword("password");
		user.setConfirmPass("password");
		Set<ConstraintViolation<User>> violations = validator.validate(user);
		for (ConstraintViolation<User> violation : violations) {
			// display the custom validation errors in the console
			System.out.println(violation.getMessage());
		}
		assertTrue(violations.isEmpty());
	}

	@Test
	void assumeNameIsNull() {
		User user = new User();
		user.setEmail("john@gmail.com");
		user.setPassword("password");
		user.setConfirmPass("password");
		Set<ConstraintViolation<User>> violations = validator.validate(user);
		assertFalse(violations.isEmpty());
	}

}
