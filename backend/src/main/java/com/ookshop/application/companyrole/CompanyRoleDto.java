package com.ookshop.application.companyrole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRoleDto {

    private Long id;
    private String role;
    private Long userId;
}
