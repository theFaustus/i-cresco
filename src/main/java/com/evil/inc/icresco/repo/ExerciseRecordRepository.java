package com.evil.inc.icresco.repo;

import com.evil.inc.icresco.domain.entity.ArticleRecord;
import com.evil.inc.icresco.domain.entity.ExerciseRecord;
import com.evil.inc.icresco.domain.entity.ExerciseRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRecordRepository extends JpaRepository<ExerciseRecord, String> {
    @Query(value = "select e from ExerciseRecord e where e.growthPlan.id = :growthPlanId order by e.createdDate",
            countQuery = "select count (e) from ExerciseRecord e where e.growthPlan.id = :growthPlanId")
    Page<ExerciseRecord> findAllByGrowthPlanId(@Param("growthPlanId") String growthPlanId, Pageable pageable);

    @Query(value = "select e from ExerciseRecord e where e.growthPlan.user.id = :userId order by e.createdDate",
            countQuery = "select count (e) from ExerciseRecord e where e.growthPlan.user.id = :userId")
    Page<ExerciseRecord> findAllByUserId(@Param("userId") String userId, Pageable pageable);
}