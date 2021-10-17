package com.evil.inc.icresco.service;

import com.evil.inc.icresco.domain.dto.AuthRequest;
import com.evil.inc.icresco.domain.dto.CreateUserRequest;
import com.evil.inc.icresco.domain.dto.UserView;
import com.evil.inc.icresco.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<UserView> findAll(Pageable pageable);
    UserView findById(String id);
    UserView create(CreateUserRequest createUserRequest);
    UserView findByUsername(String username);
    UserView authenticate(AuthRequest authRequest);
    void delete(String id);
}
