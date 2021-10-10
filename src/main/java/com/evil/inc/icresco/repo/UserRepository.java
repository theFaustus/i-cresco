package com.evil.inc.icresco.repo;

import com.evil.inc.icresco.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String userName);
}