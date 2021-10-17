package com.evil.inc.icresco.domain.entity;

import lombok.experimental.FieldNameConstants;

@FieldNameConstants(onlyExplicitlyIncluded = true)
public enum Authority {
    @FieldNameConstants.Include SIMPLE_USER,
    @FieldNameConstants.Include POWER_USER;
}
