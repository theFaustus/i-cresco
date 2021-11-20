package com.evil.inc.icresco.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatePresentationRecordRequest {
    private String title;
    private String description;
    private String url;
}
