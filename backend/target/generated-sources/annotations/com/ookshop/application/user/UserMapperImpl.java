package com.ookshop.application.user;

import com.ookshop.application.tables.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-11T18:37:51+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.9 (BellSoft)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.id( user.getId() );
        userDto.fullName( user.getFullName() );
        userDto.email( user.getEmail() );
        userDto.login( user.getLogin() );
        userDto.password( user.getPassword() );
        userDto.creationDate( user.getCreationDate() );

        return userDto.build();
    }

    @Override
    public User signUpToUser(CreateUserDto createUserDto) {
        if ( createUserDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.fullName( createUserDto.getFullName() );
        user.email( createUserDto.getEmail() );
        user.login( createUserDto.getLogin() );

        return user.build();
    }
}
