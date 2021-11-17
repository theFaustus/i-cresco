package com.evil.inc.icresco.domain.dto;

import com.evil.inc.icresco.domain.entity.Authority;
import com.evil.inc.icresco.domain.entity.UserAuthority;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
public class CreateUserRequest {
    @NotBlank(message = "{first.name.required}")
    private String firstName;
    @NotBlank(message = "{last.name.required}")
    private String lastName;
    @NotBlank(message = "{username.required}")
    private String username;
    @NotBlank(message = "{email.required}")
    @Email(message = "{email.invalid.format}")
    private String email;
    @NotBlank(message = "{gender.required}")
    private String gender;
    @NotBlank(message = "{password.required}")
    private String password;
    @NotEmpty(message = "{authorities.not.empty}")
    private Set<String> authorities;
}
