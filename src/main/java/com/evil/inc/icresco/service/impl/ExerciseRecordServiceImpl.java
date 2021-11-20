package com.evil.inc.icresco.service.impl;

import com.evil.inc.icresco.config.cache.properties.CacheNames;
import com.evil.inc.icresco.service.dto.ExerciseRecordView;
import com.evil.inc.icresco.service.dto.CreateExerciseRecordRequest;
import com.evil.inc.icresco.domain.entity.ExerciseRecord;
import com.evil.inc.icresco.domain.entity.GrowthPlan;
import com.evil.inc.icresco.domain.exception.NotFoundException;
import com.evil.inc.icresco.service.mapper.Mapper;
import com.evil.inc.icresco.repo.ExerciseRecordRepository;
import com.evil.inc.icresco.repo.GrowthPlanRepository;
import com.evil.inc.icresco.service.ExerciseRecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;

@Service
@Slf4j
@RequiredArgsConstructor
@CacheConfig(cacheNames = CacheNames.EXERCISE_RECORDS_CACHE)
class ExerciseRecordServiceImpl implements ExerciseRecordService {

    private final ExerciseRecordRepository exerciseRecordReposritory;
    private final GrowthPlanRepository growthPlanRepository;
    private final Mapper<ExerciseRecord, ExerciseRecordView> exerciseRecordViewMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<ExerciseRecordView> findAll(final Pageable pageable) {
        return exerciseRecordReposritory.findAll(pageable).map(exerciseRecordViewMapper::map);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ExerciseRecordView> findAllByUserId(final String userId, final Pageable pageable) {
        return exerciseRecordReposritory.findAllByUserId(userId, pageable).map(exerciseRecordViewMapper::map);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ExerciseRecordView> findAllByGrowthPlanId(final String growthPlanId, final Pageable pageable) {
        return exerciseRecordReposritory.findAllByGrowthPlanId(growthPlanId, pageable).map(exerciseRecordViewMapper::map);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(key = "#p0")
    public ExerciseRecordView findById(final String id) {
        final ExerciseRecord exerciseRecord = exerciseRecordReposritory.findById(id)
                .orElseThrow(() -> new NotFoundException(ExerciseRecord.class, "id", id));
        return exerciseRecordViewMapper.map(exerciseRecord);
    }

    @Override
    @Transactional
    public ExerciseRecordView createForGrowthPlan(final CreateExerciseRecordRequest createExerciseRecordRequest,
                                                 final String growthPlanId) {
        final GrowthPlan growthPlan = growthPlanRepository.findById(growthPlanId)
                .orElseThrow(() -> new NotFoundException(GrowthPlan.class, "id", growthPlanId));
        final ExerciseRecord exerciseRecord = new ExerciseRecord(createExerciseRecordRequest.getExerciseType(),
                                                                 Duration.ofMinutes(createExerciseRecordRequest.getDurationInMinutes()),
                                                                 createExerciseRecordRequest.getCaloriesBurnt());
        growthPlan.addExerciseRecord(exerciseRecord);
        exerciseRecordReposritory.save(exerciseRecord);
        return exerciseRecordViewMapper.map(exerciseRecord);
    }

    @Override
    @Transactional
    @CacheEvict(allEntries = true)
    public void delete(final String id) {
        final ExerciseRecord exerciseRecord = exerciseRecordReposritory.findById(id)
                .orElseThrow(() -> new NotFoundException(ExerciseRecord.class, "id", id));
        exerciseRecordReposritory.delete(exerciseRecord);
    }
}
