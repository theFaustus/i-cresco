package com.evil.inc.icresco.archunit;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.core.domain.JavaModifier;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import com.tngtech.archunit.thirdparty.com.google.common.collect.Maps;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Optional;

import static com.evil.inc.icresco.archunit.ArchitectureConstants.DEFAULT_PACKAGE;
import static com.evil.inc.icresco.archunit.ArchitectureConstants.DOMAIN_PACKAGE;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.fields;

@AnalyzeClasses(packages = DEFAULT_PACKAGE, importOptions = ImportOption.DoNotIncludeTests.class)
class DomainRulesTest {

    private static final String EQUALS_AND_HASH_CODE_DESCRIPTION = "have equals and hashCode";
    private static final String EQUALS_OR_HASH_CODE_NOT_PRESENT_ERROR_MESSAGE = "%s not found in %s";
    private static final String EQUALS_METHOD = "equals";
    private static final String HASH_CODE_METHOD = "hashCode";

    @ArchTest
    static final ArchRule springAnnotationsAreNotAllowed = classes().that()
            .resideInAnyPackage(DOMAIN_PACKAGE)
            .should().notBeAnnotatedWith(Service.class)
            .andShould().notBeAnnotatedWith(Component.class)
            .andShould().notBeAnnotatedWith(Configuration.class)
            .andShould().notBeAnnotatedWith(ConfigurationProperties.class)
            .andShould().notBeAnnotatedWith(Bean.class)
            .andShould().notBeAnnotatedWith(Controller.class)
            .andShould().notBeAnnotatedWith(RestController.class)
            .because(String.format("Classes in %s should not be annotated with Spring annotations", DOMAIN_PACKAGE));

    @ArchTest
    static final ArchRule classesShouldOverrideEqualsAndHashCode =
            classes().that()
                    .resideInAnyPackage(DOMAIN_PACKAGE)
                    .and().areNotMemberClasses()
                    .and().areNotAssignableTo(Exception.class)
                    .should(new ArchCondition<>(EQUALS_AND_HASH_CODE_DESCRIPTION) {
                        @Override
                        public void check(JavaClass javaClass, ConditionEvents events) {
                            Optional<JavaMethod> equalsMethod = findPublicMethodFromClass(javaClass, EQUALS_METHOD);
                            Optional<JavaMethod> hashCodeMethod = findPublicMethodFromClass(javaClass,
                                                                                            HASH_CODE_METHOD);

                            final com.tngtech.archunit.base.Optional<JavaClass> superclass = javaClass.getRawSuperclass();
                            if (superclass.isPresent()) {
                                equalsMethod = findPublicMethodFromClass(superclass.get(), EQUALS_METHOD);
                                hashCodeMethod = findPublicMethodFromClass(superclass.get(), HASH_CODE_METHOD);
                            }

                            if (equalsMethod.isEmpty()) {
                                raiseViolation(javaClass, events, EQUALS_METHOD);
                            }

                            if (hashCodeMethod.isEmpty()) {
                                raiseViolation(javaClass, events, HASH_CODE_METHOD);
                            }
                        }
                    }).because("Domain classes should override equals and hashCode methods");

    private static void raiseViolation(final JavaClass javaClass, final ConditionEvents events,
                                       final String equalsMethod2) {
        events.add(SimpleConditionEvent.violated(javaClass,
                                                 String.format(
                                                         EQUALS_OR_HASH_CODE_NOT_PRESENT_ERROR_MESSAGE,
                                                         equalsMethod2,
                                                         javaClass.getName())));
    }


    private static Optional<JavaMethod> findPublicMethodFromClass(JavaClass javaClass, String methodName) {
        return javaClass.getMethods().stream()
                .filter(m -> m.getModifiers().contains(JavaModifier.PUBLIC) && methodName.equals(m.getName()))
                .findFirst();
    }


}
