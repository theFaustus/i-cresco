package com.evil.inc.icresco.domain.mapper.impl;

import com.evil.inc.icresco.domain.dto.GrowthPlanView;
import com.evil.inc.icresco.domain.entity.GrowthPlan;
import com.evil.inc.icresco.domain.entity.User;
import com.evil.inc.icresco.domain.dto.UserView;
import com.evil.inc.icresco.domain.entity.UserAuthority;
import com.evil.inc.icresco.domain.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class UserViewMapperImpl implements Mapper<User, UserView> {

    private final Mapper<GrowthPlan, GrowthPlanView> growthPlanViewMapper;

    @Override
    @Transactional
    public UserView map(final User entity) {
        return UserView.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .username(entity.getUsername())
                .gender(entity.getGender().name())
                .authorities(
                        entity.getAuthorities().stream().map(UserAuthority::getAuthority).collect(Collectors.toSet()))
                .growthPlans(growthPlanViewMapper.map(entity.getGrowthPlans()))
                .build();
    }
}
