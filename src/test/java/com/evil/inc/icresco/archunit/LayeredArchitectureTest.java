package com.evil.inc.icresco.archunit;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.evil.inc.icresco.archunit.ArchitectureConstants.*;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = DEFAULT_PACKAGE, importOptions = ImportOption.DoNotIncludeTests.class)
class LayeredArchitectureTest {

    private static final String CONTROLLER = "Controller";
    private static final String DOMAIN = "Domain";
    private static final String REPOSITORY = "Repository";;
    private static final String SERVICE = "Service";
    private static final String CONFIGURATION = "Configuration";


    @ArchTest
    static final ArchRule layeredArchitectureRule = layeredArchitecture()
            .layer(CONTROLLER).definedBy(CONTROLLER_PACKAGE)
            .layer(DOMAIN).definedBy(DOMAIN_PACKAGE)
            .layer(REPOSITORY).definedBy(REPOSITORY_PACKAGE)
            .layer(SERVICE).definedBy(SERVICE_PACKAGE)
            .layer(CONFIGURATION).definedBy(CONFIGURATION_PACKAGE)

            .whereLayer(CONTROLLER).mayNotBeAccessedByAnyLayer()
            .whereLayer(DOMAIN).mayOnlyBeAccessedByLayers(REPOSITORY, SERVICE, CONFIGURATION)
            .whereLayer(REPOSITORY).mayOnlyBeAccessedByLayers(SERVICE, CONFIGURATION)
            .whereLayer(SERVICE).mayOnlyBeAccessedByLayers(CONTROLLER, SERVICE);
}
