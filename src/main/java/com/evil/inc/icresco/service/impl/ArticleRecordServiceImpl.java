package com.evil.inc.icresco.service.impl;

import com.evil.inc.icresco.config.cache.properties.CacheNames;
import com.evil.inc.icresco.service.dto.ArticleRecordView;
import com.evil.inc.icresco.service.dto.CreateArticleRecordRequest;
import com.evil.inc.icresco.domain.entity.ArticleRecord;
import com.evil.inc.icresco.domain.entity.GrowthPlan;
import com.evil.inc.icresco.domain.exception.NotFoundException;
import com.evil.inc.icresco.service.mapper.Mapper;
import com.evil.inc.icresco.repo.ArticleRecordRepository;
import com.evil.inc.icresco.repo.GrowthPlanRepository;
import com.evil.inc.icresco.service.ArticleRecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@CacheConfig(cacheNames = CacheNames.ARTICLE_RECORDS_CACHE)
class ArticleRecordServiceImpl implements ArticleRecordService {

    private final ArticleRecordRepository articleRecordRepository;
    private final GrowthPlanRepository growthPlanRepository;
    private final Mapper<ArticleRecord, ArticleRecordView> articleRecordViewMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<ArticleRecordView> findAll(final Pageable pageable) {
        return articleRecordRepository.findAll(pageable).map(articleRecordViewMapper::map);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ArticleRecordView> findAllByUserId(final String userId, final Pageable pageable) {
        return articleRecordRepository.findAllByUserId(userId, pageable).map(articleRecordViewMapper::map);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ArticleRecordView> findAllByGrowthPlanId(final String growthPlanId, final Pageable pageable) {
        return articleRecordRepository.findAllByGrowthPlanId(growthPlanId, pageable).map(articleRecordViewMapper::map);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(key = "#p0")
    public ArticleRecordView findById(final String id) {
        final ArticleRecord articleRecord = articleRecordRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ArticleRecord.class, "id", id));
        return articleRecordViewMapper.map(articleRecord);
    }

    @Override
    @Transactional
    public ArticleRecordView createForGrowthPlan(final CreateArticleRecordRequest createArticleRecordRequest,
                                                 final String growthPlanId) {
        final GrowthPlan growthPlan = growthPlanRepository.findById(growthPlanId)
                .orElseThrow(() -> new NotFoundException(GrowthPlan.class, "id", growthPlanId));
        final ArticleRecord articleRecord = new ArticleRecord(createArticleRecordRequest.getTitle(),
                                                           createArticleRecordRequest.getDescription(),
                                                           createArticleRecordRequest.getUrl());
        growthPlan.addArticleRecord(articleRecord);
        articleRecordRepository.save(articleRecord);
        return articleRecordViewMapper.map(articleRecord);
    }

    @Override
    @Transactional
    @CacheEvict(allEntries = true)
    public void delete(final String id) {
        final ArticleRecord articleRecord = articleRecordRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ArticleRecord.class, "id", id));
        articleRecordRepository.delete(articleRecord);
    }
}
