package com.evil.inc.icresco.service.impl;

import com.evil.inc.icresco.config.cache.properties.CacheNames;
import com.evil.inc.icresco.web.dto.CreateGrowthPlanRequest;
import com.evil.inc.icresco.web.dto.GrowthPlanView;
import com.evil.inc.icresco.domain.entity.GrowthPlan;
import com.evil.inc.icresco.domain.entity.User;
import com.evil.inc.icresco.domain.exception.NotFoundException;
import com.evil.inc.icresco.service.mapper.Mapper;
import com.evil.inc.icresco.repo.GrowthPlanRepository;
import com.evil.inc.icresco.repo.UserRepository;
import com.evil.inc.icresco.service.GrowthPlanService;
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
@CacheConfig(cacheNames = CacheNames.GROWTH_PLANS_CACHE)
class GrowthPlanServiceImpl implements GrowthPlanService {

    private final GrowthPlanRepository growthPlanRepository;
    private final UserRepository userRepository;
    private final Mapper<GrowthPlan, GrowthPlanView> growthPlanViewMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<GrowthPlanView> findAll(final Pageable pageable) {
        return growthPlanRepository.findAll(pageable).map(growthPlanViewMapper::map);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(key = "#p0")
    public GrowthPlanView findById(final String id) {
        final GrowthPlan growthPlan = growthPlanRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(GrowthPlan.class, "id", id));
        return growthPlanViewMapper.map(growthPlan);
    }

    @Override
    @Transactional
    public GrowthPlanView createFor(final CreateGrowthPlanRequest createGrowthPlanRequest,
                                    final String userId) {
        final User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(User.class, "id", userId));
        final GrowthPlan growthPlan = new GrowthPlan(createGrowthPlanRequest.getTitle(),
                                                     createGrowthPlanRequest.getDescription());
        user.addGrowthPlan(growthPlan);
        growthPlanRepository.save(growthPlan);
        return growthPlanViewMapper.map(growthPlan);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<GrowthPlanView> findByUserId(final String userId,
                                             final Pageable pageable) {
        return growthPlanRepository.findByUserId(userId, pageable).map(growthPlanViewMapper::map);
    }

    @Override
    @Transactional
    @CacheEvict(allEntries = true)
    public void delete(final String id) {
        final GrowthPlan growthPlan = growthPlanRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(GrowthPlan.class, "id", id));
        growthPlanRepository.delete(growthPlan);
    }
}
