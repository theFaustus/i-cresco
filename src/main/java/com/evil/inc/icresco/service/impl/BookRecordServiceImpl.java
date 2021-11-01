package com.evil.inc.icresco.service.impl;

import com.evil.inc.icresco.config.cache.properties.CacheNames;
import com.evil.inc.icresco.domain.dto.BookRecordView;
import com.evil.inc.icresco.domain.dto.CreateBookRecordRequest;
import com.evil.inc.icresco.domain.entity.BookRecord;
import com.evil.inc.icresco.domain.entity.GrowthPlan;
import com.evil.inc.icresco.domain.exception.NotFoundException;
import com.evil.inc.icresco.domain.mapper.Mapper;
import com.evil.inc.icresco.repo.BookRecordRepository;
import com.evil.inc.icresco.repo.GrowthPlanRepository;
import com.evil.inc.icresco.service.BookRecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@CacheConfig(cacheNames = CacheNames.BOOK_RECORDS_CACHE)
class BookRecordServiceImpl implements BookRecordService {

    private final BookRecordRepository bookRecordRepository;
    private final GrowthPlanRepository growthPlanRepository;
    private final Mapper<BookRecord, BookRecordView> bookRecordViewMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<BookRecordView> findAll(final Pageable pageable) {
        return bookRecordRepository.findAll(pageable).map(bookRecordViewMapper::map);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BookRecordView> findAllByUserId(final String userId, final Pageable pageable) {
        return bookRecordRepository.findAllByUserId(userId, pageable).map(bookRecordViewMapper::map);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BookRecordView> findAllByGrowthPlanId(final String growthPlanId, final Pageable pageable) {
        return bookRecordRepository.findAllByGrowthPlanId(growthPlanId, pageable).map(bookRecordViewMapper::map);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(key = "#p0")
    public BookRecordView findById(final String id) {
        final BookRecord bookRecord = bookRecordRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(BookRecord.class, "id", id));
        return bookRecordViewMapper.map(bookRecord);
    }

    @Override
    @Transactional
    @Caching(put = {@CachePut(key = "#p1 + '-' + #p0.title")})
    public BookRecordView createForGrowthPlan(final CreateBookRecordRequest createBookRecordRequest,
                                              final String growthPlanId) {
        final GrowthPlan growthPlan = growthPlanRepository.findById(growthPlanId)
                .orElseThrow(() -> new NotFoundException(BookRecord.class, "id", growthPlanId));
        final BookRecord bookRecord = new BookRecord(createBookRecordRequest.getTitle(),
                                                     createBookRecordRequest.getAuthor(),
                                                     createBookRecordRequest.getDescription());
        growthPlan.addBookRecord(bookRecord);
        bookRecordRepository.save(bookRecord);
        return bookRecordViewMapper.map(bookRecord);
    }

    @Override
    @Transactional
    @CacheEvict(allEntries = true)
    public void delete(final String id) {
        final BookRecord bookRecord = bookRecordRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(BookRecord.class, "id", id));
        bookRecordRepository.delete(bookRecord);
    }
}
