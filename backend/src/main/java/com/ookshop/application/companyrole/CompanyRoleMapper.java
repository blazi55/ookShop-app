package com.ookshop.application.companyrole;

import com.ookshop.application.tables.CompanyRole;
import com.ookshop.application.user.UserDto;
import com.ookshop.application.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompanyRoleMapper {

    private final UserMapper userMapper;

    public CompanyRoleDto toDto(CompanyRole companyRole) {
        final UserDto userDto = userMapper.toDto(companyRole.getUser());
        return CompanyRoleDto.builder()
                .id(companyRole.getId())
                .role(companyRole.getRole())
                .userId(userDto.getId())
                .build();
    }
}
