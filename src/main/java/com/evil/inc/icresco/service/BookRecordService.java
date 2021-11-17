package com.evil.inc.icresco.service;

import com.evil.inc.icresco.web.dto.BookRecordView;
import com.evil.inc.icresco.web.dto.CreateBookRecordRequest;
import com.evil.inc.icresco.web.dto.CreateGrowthPlanRequest;
import com.evil.inc.icresco.web.dto.GrowthPlanView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookRecordService {
    Page<BookRecordView> findAll(Pageable pageable);

    Page<BookRecordView> findAllByUserId(String userId, Pageable pageable);

    Page<BookRecordView> findAllByGrowthPlanId(String growthPlanId, Pageable pageable);

    BookRecordView findById(String id);

    BookRecordView createForGrowthPlan(CreateBookRecordRequest createBookRecordRequest, String growthPlanId);

    void delete(String id);
}
