package com.evil.inc.icresco.domain.mapper.impl;

import com.evil.inc.icresco.domain.entity.UserAuthority;
import com.evil.inc.icresco.domain.dto.UserAuthorityDto;
import com.evil.inc.icresco.domain.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
class UserAuthorityDtoMapperImpl implements Mapper<UserAuthority, UserAuthorityDto> {
    @Override
    public UserAuthorityDto map(final UserAuthority entity) {
        return new UserAuthorityDto(entity.getAuthority(), entity.getUser().getId());
    }
}
