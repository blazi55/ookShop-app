package com.ookshop.application.companyrole;

import com.ookshop.application.tables.CompanyRole;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRoleRepository extends CrudRepository<CompanyRole, Long> {

    CompanyRole findRoleById(Long Id);
}
