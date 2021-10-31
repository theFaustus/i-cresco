package com.evil.inc.icresco.domain.mapper.impl;

import com.evil.inc.icresco.domain.dto.BookRecordView;
import com.evil.inc.icresco.domain.dto.GrowthPlanView;
import com.evil.inc.icresco.domain.entity.BookRecord;
import com.evil.inc.icresco.domain.entity.GrowthPlan;
import com.evil.inc.icresco.domain.mapper.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class BookRecordViewMapperImpl implements Mapper<BookRecord, BookRecordView> {

    @Override
    @Transactional
    public BookRecordView map(final BookRecord entity) {
        return BookRecordView.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .author(entity.getAuthor())
                .description(entity.getDescription())
                .growthPlanId(entity.getGrowthPlan().getId())
                .build();
    }
}
