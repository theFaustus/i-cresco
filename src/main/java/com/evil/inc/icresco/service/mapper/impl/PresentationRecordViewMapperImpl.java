package com.evil.inc.icresco.service.mapper.impl;

import com.evil.inc.icresco.service.dto.PresentationRecordView;
import com.evil.inc.icresco.domain.entity.PresentationRecord;
import com.evil.inc.icresco.service.mapper.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class PresentationRecordViewMapperImpl implements Mapper<PresentationRecord, PresentationRecordView> {

    @Override
    @Transactional
    public PresentationRecordView map(final PresentationRecord entity) {
        return PresentationRecordView.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .url(entity.getUrl())
                .description(entity.getDescription())
                .growthPlanId(entity.getGrowthPlan().getId())
                .created(entity.getCreatedDate())
                .build();
    }
}
