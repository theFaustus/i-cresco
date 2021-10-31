package com.evil.inc.icresco.repo;

import com.evil.inc.icresco.domain.entity.ExerciseRecord;
import com.evil.inc.icresco.domain.entity.WaterIntakeRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaterIntakeRecordRepository extends JpaRepository<WaterIntakeRecord, String> {
}