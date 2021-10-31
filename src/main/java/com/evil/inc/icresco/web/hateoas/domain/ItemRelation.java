package com.evil.inc.icresco.web.hateoas.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.web.bind.annotation.RestController;

public interface ItemRelation {
     String USER = "user";
     String GROWTH_PLAN = "growthPlan";
     String BOOK_RECORD = "bookRecord";
}
