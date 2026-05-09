package com.ookshop.application.user;

import com.ookshop.application.security.UserAuthenticationProvider;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("userOokShop")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserAuthenticationProvider userAuthenticationProvider;

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody @Valid CredentialsDto credentialsDto) {
        UserDto userDto = userService.login(credentialsDto);
        userDto.setToken(userAuthenticationProvider.createToken(userDto.getLogin()));
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody @Valid CreateUserDto createUserDto) {
        UserDto createdUser = userService.createUser(createUserDto);
        createdUser.setToken(userAuthenticationProvider.createToken(createUserDto.getLogin()));
        return ResponseEntity.created(URI.create("/users/" + createdUser.getId())).body(createdUser);
    }

    @PutMapping
    public UserDto updatePassword(@RequestBody UpdateUserDto updateUserDto) {
        return userService.updatePasswordUser(updateUserDto);
    }
}
