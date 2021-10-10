package com.evil.inc.icresco.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRoleDto {
    private String roleName;
    private String userId;
}
