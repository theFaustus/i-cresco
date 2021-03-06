package com.evil.inc.icresco.service.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDateTime;

import static com.evil.inc.icresco.web.hateoas.CollectionRelation.ARTICLE_RECORDS;
import static com.evil.inc.icresco.web.hateoas.ItemRelation.ARTICLE_RECORD;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Relation(itemRelation = ARTICLE_RECORD, collectionRelation = ARTICLE_RECORDS)
public class ArticleRecordView {
    private String id;
    private String title;
    private String description;
    private String url;
    private String growthPlanId;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime created;
}
