package com.ookshop.application.user_account;

import com.ookshop.application.tables.UserAccount;
import org.springframework.data.repository.CrudRepository;

public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {
}
