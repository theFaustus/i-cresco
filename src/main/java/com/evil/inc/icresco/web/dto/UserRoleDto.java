package com.evil.inc.icresco.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.aspectj.lang.annotation.Aspect;

@Data
@AllArgsConstructor
public class UserRoleDto {
    private String roleName;
    private String userId;
}
