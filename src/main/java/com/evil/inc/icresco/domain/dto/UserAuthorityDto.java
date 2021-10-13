package com.evil.inc.icresco.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserAuthorityDto {
    private String authority;
    private String userId;
}
