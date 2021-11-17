package com.evil.inc.icresco.service;

import com.evil.inc.icresco.web.dto.CreateGrowthPlanRequest;
import com.evil.inc.icresco.web.dto.GrowthPlanView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GrowthPlanService {
    Page<GrowthPlanView> findAll(Pageable pageable);

    GrowthPlanView findById(String id);

    GrowthPlanView createFor(CreateGrowthPlanRequest createGrowthPlanRequest, final String userId);

    Page<GrowthPlanView> findByUserId(String userId, final Pageable pageable);

    void delete(String id);
}
