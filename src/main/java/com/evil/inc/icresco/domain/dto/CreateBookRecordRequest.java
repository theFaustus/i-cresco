package com.evil.inc.icresco.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.server.core.Relation;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookRecordRequest {
    private String title;
    private String author;
    private String description;
}
