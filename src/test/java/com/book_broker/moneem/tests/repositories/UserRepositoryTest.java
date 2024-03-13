package com.book_broker.moneem.tests.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.book_broker.moneem.models.User;
import com.book_broker.moneem.repositories.UserRepository;

@DataJpaTest
class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	// in UserRepository we have one custom method should tested findByEmail(String email);
	

	@Test
	void testUserShouldExistByEmail() throws Exception {
		// given
		User user = new User("Maria", "maria@gmail.com", "password", "password");
		userRepository.save(user);

		// when
		User expected = userRepository.findByEmail("maria@gmail.com").get();
		// then
		assertTrue(user.equals(expected));

	}


	// test if User dosen't exist by email in db 
	@Test
	void testUsershouldNotExistByEmail() throws Exception {
		// given
		String email="maria@gmail.com";
		// when
		Optional<User> expectedUser = userRepository.findByEmail(email);
		// then
		assertThat(expectedUser.isEmpty()).isTrue();
		

	}

}
