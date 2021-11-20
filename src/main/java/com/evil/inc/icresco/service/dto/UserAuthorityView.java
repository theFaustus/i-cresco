package com.evil.inc.icresco.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserAuthorityView {
    private String authority;
    private String userId;
}
