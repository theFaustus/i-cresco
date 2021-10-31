package com.evil.inc.icresco.repo;

import com.evil.inc.icresco.domain.entity.BookRecord;
import com.evil.inc.icresco.domain.entity.GrowthPlan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRecordRepository extends JpaRepository<BookRecord, String> {
    @Query(value = "select b from BookRecord b where b.growthPlan.id = :growthPlanId order by b.createdDate",
            countQuery = "select count (b) from BookRecord b where b.growthPlan.id = :growthPlanId")
    Page<BookRecord> findAllByGrowthPlanId(@Param("growthPlanId") String growthPlanId, Pageable pageable);

    @Query(value = "select b from BookRecord b where b.growthPlan.user.id = :userId order by b.createdDate",
            countQuery = "select count (b) from BookRecord b where b.growthPlan.user.id = :userId")
    Page<BookRecord> findAllByUserId(@Param("userId") String userId, Pageable pageable);
}