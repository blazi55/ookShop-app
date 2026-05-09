package com.ookshop.application.user;

import com.ookshop.application.tables.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserById(Long userId);
    Optional<User> findUserByLogin(String login);
}
