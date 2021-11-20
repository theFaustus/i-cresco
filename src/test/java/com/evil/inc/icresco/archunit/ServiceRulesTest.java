package com.evil.inc.icresco.archunit;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import static com.evil.inc.icresco.archunit.ArchitectureConstants.DEFAULT_PACKAGE;
import static com.evil.inc.icresco.archunit.ArchitectureConstants.SERVICE_IMPL_PACKAGE;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.constructors;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.fields;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;

@AnalyzeClasses(packages = DEFAULT_PACKAGE, importOptions = ImportOption.DoNotIncludeTests.class)
class ServiceRulesTest {

    @ArchTest
    static final ArchRule componentAnnotationIsNotAllowed = classes().that()
            .resideInAPackage(SERVICE_IMPL_PACKAGE)
            .should().notBeAnnotatedWith(Component.class)
            .because(String.format("Component annotation is not allowed in %s", SERVICE_IMPL_PACKAGE));

    @ArchTest
    static final ArchRule classesShouldBeAnnotatedWithServiceAnnotation = classes().that()
            .resideInAPackage(SERVICE_IMPL_PACKAGE)
            .should().beAnnotatedWith(Service.class)
            .because(String.format("Classes in %s package should be annotated with %s", "Service", "@Service"));

    @ArchTest
    static final ArchRule fieldsShouldNotBePublic = fields().that()
            .areDeclaredInClassesThat().resideInAPackage(SERVICE_IMPL_PACKAGE)
            .should().notBePublic()
            .because(String.format("Public fields are not allowed in %s", SERVICE_IMPL_PACKAGE));

    @ArchTest
    static final ArchRule constructorsShouldNotBePrivate = constructors().that()
            .areDeclaredInClassesThat().resideInAPackage(SERVICE_IMPL_PACKAGE).and()
            .areDeclaredInClassesThat().areNotAnonymousClasses()
            .should().bePublic()
            .because(String.format("Public constructors are only allowed in %s", SERVICE_IMPL_PACKAGE));

    @ArchTest
    static final ArchRule beanMethodsAreNotAllowed = methods().that()
            .areDeclaredInClassesThat().resideInAPackage(SERVICE_IMPL_PACKAGE)
            .should().notBeAnnotatedWith(Bean.class)
            .because(String.format("Bean methods are not allowed in %s", SERVICE_IMPL_PACKAGE));

    @ArchTest
    static final ArchRule implementationsShouldBePackagePrivate = classes().that()
            .resideInAPackage(SERVICE_IMPL_PACKAGE).and().areAnnotatedWith(Service.class)
            .should().bePackagePrivate()
            .because("@Bean annotation does not work in non public methods");
}
