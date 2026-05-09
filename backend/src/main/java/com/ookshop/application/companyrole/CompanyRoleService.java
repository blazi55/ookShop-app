package com.ookshop.application.companyrole;

import com.ookshop.application.tables.CompanyRole;
import com.ookshop.application.tables.User;
import com.ookshop.application.user.AppRole;
import com.ookshop.application.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.ookshop.application.user.AppRole.ADMIN;
import static com.ookshop.application.user.AppRole.USER;

@Service
@RequiredArgsConstructor
public class CompanyRoleService {

    private final CompanyRoleRepository companyRoleRepository;
    private final UserRepository userRepository;
    private final CompanyRoleMapper companyRoleMapper;

    @Transactional
    public CompanyRoleDto registerAdminRole(CreateCompanyRole createCompanyRole) {
        return registerUserRole(createCompanyRole, ADMIN);
    }

    @Transactional
    public CompanyRoleDto registerUserRole(CreateCompanyRole createCompanyRole) {
        return registerUserRole(createCompanyRole, USER);
    }

    private CompanyRoleDto registerUserRole(CreateCompanyRole createCompanyRole, AppRole appRole) {
        final User user = findUser(createCompanyRole.getEmail());
        final CompanyRole companyRole = CompanyRole.builder()
                .role(String.valueOf(appRole))
                .user(user)
                .build();
        companyRoleRepository.save(companyRole);
        return companyRoleMapper.toDto(companyRole);
    }

    private User findUser(String userEmail) {
        return userRepository.findUserByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("User with Email " +
                        userEmail + " doesn't exist. " ));

    }
}
