package com.evil.inc.icresco.repo;

import com.evil.inc.icresco.domain.entity.PresentationRecord;
import com.evil.inc.icresco.domain.entity.BookRecord;
import com.evil.inc.icresco.domain.entity.PresentationRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PresentationRecordRepository extends JpaRepository<PresentationRecord, String> {
    @Query(value = "select p from PresentationRecord p where p.growthPlan.id = :growthPlanId order by p.createdDate",
            countQuery = "select count (p) from PresentationRecord p where p.growthPlan.id = :growthPlanId")
    Page<PresentationRecord> findAllByGrowthPlanId(@Param("growthPlanId") String growthPlanId, Pageable pageable);

    @Query(value = "select p from PresentationRecord p where p.growthPlan.user.id = :userId order by p.createdDate",
            countQuery = "select count (p) from PresentationRecord p where p.growthPlan.user.id = :userId")
    Page<PresentationRecord> findAllByUserId(@Param("userId") String userId, Pageable pageable);

}