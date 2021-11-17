package com.evil.inc.icresco.service;

import com.evil.inc.icresco.web.dto.PresentationRecordView;
import com.evil.inc.icresco.web.dto.CreatePresentationRecordRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PresentationRecordService {
    Page<PresentationRecordView> findAll(Pageable pageable);

    Page<PresentationRecordView> findAllByUserId(String userId, Pageable pageable);

    Page<PresentationRecordView> findAllByGrowthPlanId(String growthPlanId, Pageable pageable);

    PresentationRecordView findById(String id);

    PresentationRecordView createForGrowthPlan(CreatePresentationRecordRequest createPresentationRecordRequest,
                                               String growthPlanId);

    void delete(String id);

}
