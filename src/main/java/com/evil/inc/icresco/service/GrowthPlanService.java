package com.evil.inc.icresco.service;

import com.evil.inc.icresco.domain.dto.AuthRequest;
import com.evil.inc.icresco.domain.dto.CreateGrowthPlanRequest;
import com.evil.inc.icresco.domain.dto.CreateUserRequest;
import com.evil.inc.icresco.domain.dto.GrowthPlanView;
import com.evil.inc.icresco.domain.dto.UserView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GrowthPlanService {
    Page<GrowthPlanView> findAll(Pageable pageable);

    GrowthPlanView findById(String id);

    GrowthPlanView createFor(CreateGrowthPlanRequest createGrowthPlanRequest, final String userId);

    Page<GrowthPlanView> findByUserId(String userId, final Pageable pageable);

    void delete(String id);
}
