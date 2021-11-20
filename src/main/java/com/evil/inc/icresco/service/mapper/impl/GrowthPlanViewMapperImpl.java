package com.evil.inc.icresco.service.mapper.impl;

import com.evil.inc.icresco.service.dto.GrowthPlanView;
import com.evil.inc.icresco.domain.entity.GrowthPlan;
import com.evil.inc.icresco.service.mapper.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class GrowthPlanViewMapperImpl implements Mapper<GrowthPlan, GrowthPlanView> {

    @Override
    @Transactional
    public GrowthPlanView map(final GrowthPlan entity) {
        return GrowthPlanView.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .userId(entity.getUser().getId())
                .created(entity.getCreatedDate())
                .build();
    }
}
