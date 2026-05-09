package com.ookshop.application.user;

import com.ookshop.application.exceptions.AppException;
import com.ookshop.application.tables.User;
import com.ookshop.application.tables.UserAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserDto createUser(final CreateUserDto createUserDto) {
        final Optional<User> optionalUser = userRepository.findUserByLogin(createUserDto.getLogin());
        if (optionalUser.isPresent()) {
            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }
        final User user = userMapper.signUpToUser(createUserDto);
        final UserAccount userAccount = UserAccount.builder()
                .user(user)
                .amountBook(0L)
                .build();
        user.setUserAccount(userAccount);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(createUserDto.getPassword())));
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    public UserDto updatePasswordUser(final UpdateUserDto updateUserDto) {
        if (updateUserDto.getEmail() == null) {
            throw new IllegalArgumentException("Id shouldn't be null. Id= " + updateUserDto.getEmail());
        }
        final User user = userRepository.findByEmail(updateUserDto.getEmail());

        if (user.getEmail() == null) {
            throw new IllegalArgumentException("Id shouldn't be null. Email= " + user.getEmail());
        }

        user.setPassword(updateUserDto.getPassword());
        //user.setPassword(new BCryptPasswordEncoder().encode(updateUserDto.getPassword()));
        this.userRepository.save(user);
        return this.userMapper.toDto(user);
    }

    public UserDto login(final CredentialsDto credentialsDto) {
        final User user = userRepository.findUserByLogin(credentialsDto.getLogin())
                .orElseThrow(() -> new IllegalArgumentException("Unknown user"));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), user.getPassword())) {
            return userMapper.toDto(user);
        }
        throw new IllegalArgumentException("Invalid password");
    }

    public UserDto findByLogin(final String login) {
        final User user = userRepository.findUserByLogin(login)
                .orElseThrow(() -> new IllegalArgumentException("Unknown user"));
        return userMapper.toDto(user);
    }
}
