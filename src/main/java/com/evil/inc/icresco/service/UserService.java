package com.evil.inc.icresco.service;

import com.evil.inc.icresco.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<User> findAll(Pageable pageable);
    User findById(String id);
    User create(User user);
    User findByUserName(String userName);

}
