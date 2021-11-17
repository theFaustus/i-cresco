package com.evil.inc.icresco.service;

import com.evil.inc.icresco.web.dto.ArticleRecordView;
import com.evil.inc.icresco.web.dto.BookRecordView;
import com.evil.inc.icresco.web.dto.CreateArticleRecordRequest;
import com.evil.inc.icresco.web.dto.CreateBookRecordRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticleRecordService {
    Page<ArticleRecordView> findAll(Pageable pageable);

    Page<ArticleRecordView> findAllByUserId(String userId, Pageable pageable);

    Page<ArticleRecordView> findAllByGrowthPlanId(String growthPlanId, Pageable pageable);

    ArticleRecordView findById(String id);

    ArticleRecordView createForGrowthPlan(CreateArticleRecordRequest createArticleRecordRequest, String growthPlanId);

    void delete(String id);
}
