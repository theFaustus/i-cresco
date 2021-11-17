package com.evil.inc.icresco.service;

import com.evil.inc.icresco.web.dto.AuthRequest;
import com.evil.inc.icresco.web.dto.UpsertUserRequest;
import com.evil.inc.icresco.web.dto.UserView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    Page<UserView> findAll(Pageable pageable);

    List<UserView> findAll();

    UserView findById(String id);

    UserView create(UpsertUserRequest upsertUserRequest);

    UserView findByUsername(String username);

    UserView authenticate(AuthRequest authRequest);

    void delete(String id);

    UserView update(String id, UpsertUserRequest request);
}
