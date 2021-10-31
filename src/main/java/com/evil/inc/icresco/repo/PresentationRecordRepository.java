package com.evil.inc.icresco.repo;

import com.evil.inc.icresco.domain.entity.BookRecord;
import com.evil.inc.icresco.domain.entity.PresentationRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresentationRecordRepository extends JpaRepository<PresentationRecord, String> {
}