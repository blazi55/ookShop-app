package com.ookshop.application.user;

import com.ookshop.application.exceptions.AppException;
import com.ookshop.application.tables.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.CharBuffer;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@Mock
	private PasswordEncoder passwordEncoder;

	@Mock
	private UserMapper userMapper;

	@InjectMocks
	private UserService userService;

	@Test
	void shouldCreateUserSuccessfully() {
		CreateUserDto dto = new CreateUserDto();
		dto.setLogin("test");
		dto.setPassword("pass");

		User user = new User();
		UserDto userDto = new UserDto();

		when(userRepository.findUserByLogin("test")).thenReturn(Optional.empty());
		when(userMapper.signUpToUser(dto)).thenReturn(user);
		when(passwordEncoder.encode(any(CharBuffer.class))).thenReturn("encoded");
		when(userMapper.toDto(user)).thenReturn(userDto);

		UserDto result = userService.createUser(dto);

		assertNotNull(result);
		assertEquals("encoded", user.getPassword());
		assertNotNull(user.getUserAccount());

		verify(userRepository).save(user);
	}

	@Test
	void shouldThrowWhenLoginExists() {
		CreateUserDto dto = new CreateUserDto();
		dto.setLogin("test");

		when(userRepository.findUserByLogin("test"))
				.thenReturn(Optional.of(new User()));

		AppException ex = assertThrows(AppException.class,
				() -> userService.createUser(dto));

		assertEquals(HttpStatus.BAD_REQUEST, ex.getStatus());
	}

	@Test
	void shouldUpdatePassword() {
		UpdateUserDto dto = new UpdateUserDto();
		dto.setEmail("test@mail.com");
		dto.setPassword("newPass");

		User user = new User();
		user.setEmail("test@mail.com");

		UserDto userDto = new UserDto();

		when(userRepository.findByEmail("test@mail.com")).thenReturn(user);
		when(userMapper.toDto(user)).thenReturn(userDto);

		UserDto result = userService.updatePasswordUser(dto);

		assertEquals("newPass", user.getPassword());
		verify(userRepository).save(user);
	}

	@Test
	void shouldThrowWhenEmailNull() {
		UpdateUserDto dto = new UpdateUserDto();
		dto.setEmail(null);

		assertThrows(IllegalArgumentException.class,
				() -> userService.updatePasswordUser(dto));
	}

	@Test
	void shouldLoginSuccessfully() {
		CredentialsDto dto = new CredentialsDto();
		dto.setLogin("test");
		dto.setPassword("pass");

		User user = new User();
		user.setPassword("encoded");

		UserDto userDto = new UserDto();

		when(userRepository.findUserByLogin("test"))
				.thenReturn(Optional.of(user));

		when(passwordEncoder.matches(any(CharBuffer.class), eq("encoded")))
				.thenReturn(true);

		when(userMapper.toDto(user)).thenReturn(userDto);

		UserDto result = userService.login(dto);

		assertNotNull(result);
	}

	@Test
	void shouldThrowWhenUserNotFound() {
		CredentialsDto dto = new CredentialsDto();
		dto.setLogin("test");

		when(userRepository.findUserByLogin("test"))
				.thenReturn(Optional.empty());

		assertThrows(IllegalArgumentException.class,
				() -> userService.login(dto));
	}

	@Test
	void shouldThrowWhenInvalidPassword() {
		CredentialsDto dto = new CredentialsDto();
		dto.setLogin("test");
		dto.setPassword("bad");

		User user = new User();
		user.setPassword("encoded");

		when(userRepository.findUserByLogin("test"))
				.thenReturn(Optional.of(user));

		when(passwordEncoder.matches(any(CharBuffer.class), eq("encoded")))
				.thenReturn(false);

		assertThrows(IllegalArgumentException.class,
				() -> userService.login(dto));
	}

	@Test
	void shouldFindByLogin() {
		User user = new User();
		UserDto dto = new UserDto();

		when(userRepository.findUserByLogin("test"))
				.thenReturn(Optional.of(user));
		when(userMapper.toDto(user)).thenReturn(dto);

		UserDto result = userService.findByLogin("test");

		assertNotNull(result);
	}

	@Test
	void shouldThrowWhenFindByLoginNotFound() {
		when(userRepository.findUserByLogin("test"))
				.thenReturn(Optional.empty());

		assertThrows(IllegalArgumentException.class,
				() -> userService.findByLogin("test"));
	}
}