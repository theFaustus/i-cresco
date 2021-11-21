package com.evil.inc.icresco.service.mapper.impl;

import com.evil.inc.icresco.service.dto.BookRecordView;
import com.evil.inc.icresco.domain.entity.BookRecord;
import com.evil.inc.icresco.service.mapper.Mapper;
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
                .thumbnail(entity.getThumbnail())
                .pageCount(entity.getPageCount())
                .description(entity.getDescription())
                .growthPlanId(entity.getGrowthPlan().getId())
                .userId(entity.getGrowthPlan().getUser().getId())
                .created(entity.getCreatedDate())
                .build();
    }
}
