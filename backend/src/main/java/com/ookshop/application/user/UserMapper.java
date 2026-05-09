package com.ookshop.application.user;

import com.ookshop.application.tables.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

	UserDto toDto(User user);

	@Mapping(target = "password", ignore = true)
	User signUpToUser(CreateUserDto createUserDto);
}
