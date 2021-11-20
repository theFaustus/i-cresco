package com.evil.inc.icresco.archunit;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.stereotype.Repository;

import static com.evil.inc.icresco.archunit.ArchitectureConstants.DEFAULT_PACKAGE;
import static com.evil.inc.icresco.archunit.ArchitectureConstants.REPOSITORY_PACKAGE;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = DEFAULT_PACKAGE, importOptions = ImportOption.DoNotIncludeTests.class)
class RepositoryRulesTest {
	
	@ArchTest
	static final ArchRule classesShouldBeAnnotatedWithRepository = classes().that()
			.resideInAPackage(REPOSITORY_PACKAGE).should()
			.beAnnotatedWith(Repository.class)
			.because(String.format("Classes in %s package should be annotated with %s", "Repository", "@Repository"));

    @ArchTest
    static final ArchRule classesShouldBeInterfaces = classes().that()
			.resideInAPackage(REPOSITORY_PACKAGE)
			.and().resideOutsideOfPackages()
			.should().beInterfaces()
			.because(String.format("Resources should be interfaces in %s", REPOSITORY_PACKAGE));
}
