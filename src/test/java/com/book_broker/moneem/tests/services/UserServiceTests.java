package com.book_broker.moneem.tests.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;

import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.book_broker.moneem.models.User;
import com.book_broker.moneem.repositories.UserRepository;
import com.book_broker.moneem.services.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

	// we should mock userRepository because we tested it and we dont need to tested
	// again using @Mock annotationa and avoid typing lines of code to set up
	// autocloseable so we use @ExtendWith(MockitoExtension.class) to setup Mocking
	@Mock
	private UserRepository userRepository;
	private UserService userService;

	@BeforeEach
	public void setUp() {
		
		// every time we test UserServiceTests class we initialise fresh userService of type UserService
		userService = new UserService(userRepository);

	}

	@Test
	public void testShouldAddUser() throws Exception {
		// given
		User newUser = new User("Elon Mask", "elon.mask@gmail.com", "password", "password");

		// when
		userService.saveUser(newUser);
		
		// then
		ArgumentCaptor<User> userArgumentCapture = ArgumentCaptor.forClass(User.class);

		// check if the userRepository was invoke when we call saveUser method in the userService
		verify(userRepository).save(userArgumentCapture.capture());

		User captureUserValue = userArgumentCapture.getValue();
		assertThat(captureUserValue).isEqualTo(newUser);

	}
	
	@Test
	public void TestShouldThrownWhenEmailExist() throws Exception{
		// given
		User newUser = new User("Elon Mask", "elon.mask@gmail.com", "password", "password");
		
		// when
		BDDMockito.given(userRepository.findByEmail(newUser.getEmail()).isPresent())
				.willReturn(true);
			
		// then
				assertThatThrownBy(()->userRepository.save(newUser))
								.isInstanceOf(BadRequestException.class)
								.hasMessageContaining("Email "+newUser.getEmail()+" already token !!!");
				

		
	}
	
}
