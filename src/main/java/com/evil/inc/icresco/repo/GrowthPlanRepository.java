package com.evil.inc.icresco.repo;

import com.evil.inc.icresco.domain.entity.GrowthPlan;
import com.evil.inc.icresco.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GrowthPlanRepository extends JpaRepository<GrowthPlan, String> {
    @Query(value = "select g from GrowthPlan g where g.user.id = :userId order by g.createdDate",
            countQuery = "select count(g) from GrowthPlan g where g.user.id = :userId")
    Page<GrowthPlan> findByUserId(@Param("userId") String userId, Pageable pageable);
}