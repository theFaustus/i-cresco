package com.evil.inc.icresco.archunit;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.conditions.ArchConditions;
import org.springframework.context.annotation.Bean;

import java.util.logging.Logger;

import static com.evil.inc.icresco.archunit.ArchitectureConstants.DEFAULT_PACKAGE;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.fields;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;
import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS;
import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING;

@AnalyzeClasses(packages = DEFAULT_PACKAGE, importOptions = ImportOption.DoNotIncludeTests.class)
class GeneralCodingRulesTest {


    @ArchTest
    static final ArchRule noGenericExceptions = NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS
            .because("Throw AlmundoException or any child of this instead");

    @ArchTest
    static final ArchRule noJavaUtilLogging = NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING
            .because("Use org.slf4j.Logger instead");

    @ArchTest
    static final ArchRule noFieldInjection = fields()
            .should(ArchConditions.notBeAnnotatedWith("org.springframework.beans.factory.annotation.Autowired"))
            .orShould(ArchConditions.notBeAnnotatedWith("com.google.inject.Inject"))
            .orShould(ArchConditions.notBeAnnotatedWith("javax.inject.Inject"))
            .orShould(ArchConditions.notBeAnnotatedWith("javax.annotation.Resource"))
            .because("Field injection is considered harmful. Use setter or constructor injection instead");

    @ArchTest
    static final ArchRule loggersShouldBePrivateStaticAndFinal = fields().that()
            .haveRawType(Logger.class)
            .should().bePrivate()
            .andShould().beStatic()
            .andShould().beFinal()
            .andShould().haveName("LOGGER")
            .because("Logger variables should be private, static and final, and it should be named as LOGGER");


    @ArchTest
    static final ArchRule finalStaticVariablesInUppercase = fields().that()
            .areStatic().and().areFinal().and().doNotHaveName("log").and().doNotHaveName("$VALUES")
            .should().haveNameMatching(".*^[A-Z].*")
            .because("Variables with static and final modifiers should be named in uppercase");

    @ArchTest
    static final ArchRule beanMethodsShouldBePublic = methods().that()
            .areAnnotatedWith(Bean.class).should().bePublic()
            .because("@Bean annotation does not work in non public methods");


}
