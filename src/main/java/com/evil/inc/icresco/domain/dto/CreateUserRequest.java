package com.evil.inc.icresco.domain.dto;

import com.evil.inc.icresco.domain.entity.Authority;
import com.evil.inc.icresco.domain.entity.UserAuthority;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
public class CreateUserRequest {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String username;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String gender;
    @NotBlank
    private String password;
    private Set<String> authorities;

    public Set<UserAuthority> getUserAuthorities() {
        return getAuthorities().stream().map(a -> new UserAuthority(Authority.valueOf(a))).collect(Collectors.toSet());
    }
}
