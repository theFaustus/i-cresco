package com.evil.inc.icresco.domain.dto;

import com.evil.inc.icresco.web.hateoas.domain.CollectionRelation;
import com.evil.inc.icresco.web.hateoas.domain.ItemRelation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.server.core.Relation;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import static com.evil.inc.icresco.web.hateoas.domain.CollectionRelation.BOOK_RECORDS;
import static com.evil.inc.icresco.web.hateoas.domain.ItemRelation.BOOK_RECORD;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Relation(itemRelation = BOOK_RECORD, collectionRelation = BOOK_RECORDS)
public class BookRecordView {
    private String id;
    private String title;
    private String author;
    private String description;
    private String growthPlanId;
}
