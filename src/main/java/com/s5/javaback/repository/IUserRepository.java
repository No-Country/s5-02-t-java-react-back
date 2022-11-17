package com.s5.javaback.repository;

import com.s5.javaback.model.entity.User;
import com.s5.javaback.util.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsernameOrEmail(String username, String email);

    List<User> findByStatus(UserStatus status);

    User findByUsername(String userName);
}
