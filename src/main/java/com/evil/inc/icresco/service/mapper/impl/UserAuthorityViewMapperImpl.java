package com.evil.inc.icresco.service.mapper.impl;

import com.evil.inc.icresco.domain.entity.UserAuthority;
import com.evil.inc.icresco.service.dto.UserAuthorityView;
import com.evil.inc.icresco.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
class UserAuthorityViewMapperImpl implements Mapper<UserAuthority, UserAuthorityView> {

    @Override
    public UserAuthorityView map(final UserAuthority entity) {
        return new UserAuthorityView(entity.getAuthority(), entity.getUser().getId());
    }
}
