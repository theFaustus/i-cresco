package com.evil.inc.icresco.archunit;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ArchitectureConstants {

    // Packages
    public static final String CONTROLLER_PACKAGE = "..web..";
    public static final String DOMAIN_PACKAGE = "..domain..";
    public static final String DTO_PACKAGE = "..dto..";
    public static final String REPOSITORY_PACKAGE = "..repo..";
    public static final String SERVICE_PACKAGE = "..service..";
    public static final String SERVICE_IMPL_PACKAGE = "..service.impl..";
    public static final String CONFIGURATION_PACKAGE = "..config..";


    // Package to scan
    public static final String DEFAULT_PACKAGE = "com.evil.inc.icresco";

}
