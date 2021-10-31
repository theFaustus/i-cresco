package com.evil.inc.icresco.repo;

import com.evil.inc.icresco.domain.entity.ArticleRecord;
import com.evil.inc.icresco.domain.entity.ExerciseRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRecordRepository extends JpaRepository<ExerciseRecord, String> {
}