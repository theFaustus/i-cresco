package com.evil.inc.icresco.service.impl;

import com.evil.inc.icresco.config.cache.properties.CacheNames;
import com.evil.inc.icresco.web.dto.PresentationRecordView;
import com.evil.inc.icresco.web.dto.CreatePresentationRecordRequest;
import com.evil.inc.icresco.domain.entity.PresentationRecord;
import com.evil.inc.icresco.domain.entity.GrowthPlan;
import com.evil.inc.icresco.domain.exception.NotFoundException;
import com.evil.inc.icresco.service.mapper.Mapper;
import com.evil.inc.icresco.repo.PresentationRecordRepository;
import com.evil.inc.icresco.repo.GrowthPlanRepository;
import com.evil.inc.icresco.service.PresentationRecordService;
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
@CacheConfig(cacheNames = CacheNames.PRESENTATION_RECORDS_CACHE)
class PresentationRecordServiceImpl implements PresentationRecordService {

    private final PresentationRecordRepository presentationRecordRepository;
    private final GrowthPlanRepository growthPlanRepository;
    private final Mapper<PresentationRecord, PresentationRecordView> presentationRecordViewMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<PresentationRecordView> findAll(final Pageable pageable) {
        return presentationRecordRepository.findAll(pageable).map(presentationRecordViewMapper::map);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PresentationRecordView> findAllByUserId(final String userId, final Pageable pageable) {
        return presentationRecordRepository.findAllByUserId(userId, pageable).map(presentationRecordViewMapper::map);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PresentationRecordView> findAllByGrowthPlanId(final String growthPlanId, final Pageable pageable) {
        return presentationRecordRepository.findAllByGrowthPlanId(growthPlanId, pageable).map(presentationRecordViewMapper::map);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(key = "#p0")
    public PresentationRecordView findById(final String id) {
        final PresentationRecord presentationRecord = presentationRecordRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PresentationRecord.class, "id", id));
        return presentationRecordViewMapper.map(presentationRecord);
    }

    @Override
    @Transactional
    public PresentationRecordView createForGrowthPlan(final CreatePresentationRecordRequest createPresentationRecordRequest,
                                                 final String growthPlanId) {
        final GrowthPlan growthPlan = growthPlanRepository.findById(growthPlanId)
                .orElseThrow(() -> new NotFoundException(GrowthPlan.class, "id", growthPlanId));
        final PresentationRecord presentationRecord = new PresentationRecord(createPresentationRecordRequest.getTitle(),
                                                           createPresentationRecordRequest.getDescription(),
                                                           createPresentationRecordRequest.getUrl());
        growthPlan.addPresentationRecord(presentationRecord);
        presentationRecordRepository.save(presentationRecord);
        return presentationRecordViewMapper.map(presentationRecord);
    }

    @Override
    @Transactional
    @CacheEvict(allEntries = true)
    public void delete(final String id) {
        final PresentationRecord presentationRecord = presentationRecordRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PresentationRecord.class, "id", id));
        presentationRecordRepository.delete(presentationRecord);
    }
}
