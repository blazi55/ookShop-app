package com.ookshop.application.companyrole;

import com.ookshop.application.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("roleOokShop")
public class CompanyRoleController {

    private final CompanyRoleService companyRoleService;

    @PostMapping("/admin")
    public CompanyRoleDto createAdminRole(@RequestBody CreateCompanyRole createCompanyRole) {
        return this.companyRoleService.registerAdminRole(createCompanyRole);
    }

    @PostMapping("/user")
    public CompanyRoleDto createUserRole(@RequestBody CreateCompanyRole createCompanyRole) {
        return this.companyRoleService.registerUserRole(createCompanyRole);
    }
}
