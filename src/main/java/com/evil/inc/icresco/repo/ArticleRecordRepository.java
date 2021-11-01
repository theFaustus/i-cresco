package com.evil.inc.icresco.repo;

import com.evil.inc.icresco.domain.entity.ArticleRecord;
import com.evil.inc.icresco.domain.entity.BookRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRecordRepository extends JpaRepository<ArticleRecord, String> {
    @Query(value = "select a from ArticleRecord a where a.growthPlan.id = :growthPlanId order by a.createdDate",
            countQuery = "select count (a) from ArticleRecord a where a.growthPlan.id = :growthPlanId")
    Page<ArticleRecord> findAllByGrowthPlanId(@Param("growthPlanId") String growthPlanId, Pageable pageable);

    @Query(value = "select a from ArticleRecord a where a.growthPlan.user.id = :userId order by a.createdDate",
            countQuery = "select count (a) from ArticleRecord a where a.growthPlan.user.id = :userId")
    Page<ArticleRecord> findAllByUserId(@Param("userId") String userId, Pageable pageable);
}