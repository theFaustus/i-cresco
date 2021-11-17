package com.evil.inc.icresco.web.dto;

import com.evil.inc.icresco.config.validation.OnCreate;
import com.evil.inc.icresco.config.validation.OnUpdate;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
public class UpsertUserRequest {
    @NotBlank(message = "{first.name.required}", groups = {OnCreate.class})
    private String firstName;
    @NotBlank(message = "{last.name.required}", groups = {OnCreate.class})
    private String lastName;
    @NotBlank(message = "{username.required}", groups = {OnCreate.class})
    private String username;
    @NotBlank(message = "{email.required}", groups = {OnCreate.class})
    @Email(message = "{email.invalid.format}", groups = {OnCreate.class})
    private String email;
    @NotBlank(message = "{gender.required}", groups = {OnCreate.class})
    private String gender;
    @NotBlank(message = "{password.required}", groups = {OnCreate.class})
    private String password;
    @NotEmpty(message = "{authorities.not.empty}", groups = {OnCreate.class})
    private Set<String> authorities;
}
