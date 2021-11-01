package com.evil.inc.icresco.domain.mapper.impl;

import com.evil.inc.icresco.domain.dto.ArticleRecordView;
import com.evil.inc.icresco.domain.dto.BookRecordView;
import com.evil.inc.icresco.domain.entity.ArticleRecord;
import com.evil.inc.icresco.domain.entity.BookRecord;
import com.evil.inc.icresco.domain.mapper.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class ArticleRecordViewMapperImpl implements Mapper<ArticleRecord, ArticleRecordView> {

    @Override
    @Transactional
    public ArticleRecordView map(final ArticleRecord entity) {
        return ArticleRecordView.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .url(entity.getUrl())
                .description(entity.getDescription())
                .growthPlanId(entity.getGrowthPlan().getId())
                .created(entity.getCreatedDate())
                .build();
    }
}
