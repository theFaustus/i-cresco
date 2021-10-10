package com.evil.inc.icresco.domain.mapper.impl;

import com.evil.inc.icresco.domain.entity.UserAuthority;
import com.evil.inc.icresco.domain.dto.UserRoleDto;
import com.evil.inc.icresco.domain.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
class UserRoleDtoMapperImpl implements Mapper<UserAuthority, UserRoleDto> {
    @Override
    public UserRoleDto map(final UserAuthority entity) {
        return new UserRoleDto(entity.getAuthority(), entity.getUser().getId());
    }
}
