package com.evil.inc.icresco.web.rest;

import com.evil.inc.icresco.config.validation.OnCreate;
import com.evil.inc.icresco.service.dto.AuthRequest;
import com.evil.inc.icresco.service.dto.UpsertUserRequest;
import com.evil.inc.icresco.service.dto.UserView;
import com.evil.inc.icresco.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "Authentication", description = "Endpoints for managing authentication")
@RestController
@RequestMapping(path = "/api/v1/public")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserView> login(@RequestBody @Valid AuthRequest request) {
        final UserView userView = userService.authenticate(request);
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + userView.getAccessToken())
                .body(userView);
    }

    @PostMapping("/register")
    public UserView register(@RequestBody @Validated(OnCreate.class) UpsertUserRequest request) {
        return userService.create(request);
    }

}
