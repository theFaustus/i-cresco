package com.evil.inc.icresco.config.cache.properties;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CachePropertiesEntry {
    private final int timeToLiveMinutes;
    private final boolean enabled;
}
