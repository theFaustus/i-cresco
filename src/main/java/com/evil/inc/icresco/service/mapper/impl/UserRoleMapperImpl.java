package com.evil.inc.icresco.service.mapper.impl;

import com.evil.inc.icresco.model.UserRole;
import com.evil.inc.icresco.service.mapper.Mapper;
import com.evil.inc.icresco.web.dto.UserRoleDto;
import org.springframework.stereotype.Component;

@Component
class UserRoleMapperImpl implements Mapper<UserRole, UserRoleDto> {
    @Override
    public UserRoleDto map(final UserRole entity) {
        return new UserRoleDto(entity.getRoleName(), entity.getUser().getId());
    }
}
