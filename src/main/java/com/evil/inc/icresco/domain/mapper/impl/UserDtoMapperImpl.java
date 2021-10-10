package com.evil.inc.icresco.domain.mapper.impl;

import com.evil.inc.icresco.domain.entity.User;
import com.evil.inc.icresco.domain.dto.UserDto;
import com.evil.inc.icresco.domain.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
class UserDtoMapperImpl implements Mapper<User, UserDto> {
    @Override
    public UserDto map(final User entity) {
        return UserDto.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .userName(entity.getUsername())
                .gender(entity.getGender().name())
//TODO          .growthPlans()
                .build();
    }
}
